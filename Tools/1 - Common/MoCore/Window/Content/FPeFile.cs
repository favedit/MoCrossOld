using System;
using System.Runtime.InteropServices;
using MO.Common.Collection;
using MO.Common.IO;
using MO.Common.Lang;
using MO.Core.Window.Api;
using MO.Core.Window.Core;

namespace MO.Core.Window.Context {

   public class FPeFile {

      private SImageDosHeader _dosHeader;

      private SImageNtHeaders _ntHeader;

      private SImageSectionHeader[] _sections;

      private FPeExport _export = new FPeExport();

      private FPeImport _import = new FPeImport();

      private FBytes _memory;

      public SImageDosHeader DosHeader {
         get { return _dosHeader; }
      }

      public SImageNtHeaders NtHeader {
         get { return _ntHeader; }
      }

      public SImageSectionHeader[] Sections {
         get { return _sections; }
      }

      public FPeExport Export {
         get { return _export; }
      }

      public FPeImport Import {
         get { return _import; }
      }

      public FBytes Memory {
         get { return _memory; }
      }

      public FPeFile() {
      }

      public FPeFile(string filename) {
         LoadFile(filename);
      }

      public bool LoadFile(string filename) {
         FByteFile file = new FByteFile(filename);
         _memory = file;
         // DosHead
         _dosHeader = (SImageDosHeader)file.GetStruct(0, typeof(SImageDosHeader));
         if (_dosHeader.e_magic != (uint)EImageSignature.Dos) {
            return false;
         }
         // NtHead
         _ntHeader = (SImageNtHeaders)file.GetStruct((int)_dosHeader.e_lfanew, typeof(SImageNtHeaders));
         if (_ntHeader.Signature != (uint)EImageSignature.Nt) {
            return false;
         }
         // Module
         ReadSections(file);
         ReadOptionalExport(file);
         ReadOptionalImport(file);
         // Success
         return true;
      }

      protected int ConvertRva(uint virtualAddress) {
         int count = _sections.Length;
         for (int n = 0; n < count; n++) {
            uint va = _sections[n].VirtualAddress;
            if ((virtualAddress >= va) && (virtualAddress < va + _sections[n].SizeOfRawData)) {
               return (int)(virtualAddress - va + _sections[n].PointerToRawData);
            }
         }
         return -1;
      }

      protected bool ReadSections(FByteFile file) {
         uint address = _dosHeader.e_lfanew + (uint)Marshal.SizeOf(typeof(SImageNtHeaders));
         int count = _ntHeader.FileHeader.NumberOfSections;
         _sections = new SImageSectionHeader[count];
         uint size = (uint)Marshal.SizeOf(typeof(SImageSectionHeader));
         for (int n = 0; n < count; n++) {
            _sections[n] = (SImageSectionHeader)file.GetStruct((int)address, typeof(SImageSectionHeader));
            address += size;
         }
         return true;
      }

      protected void ReadOptionalExport(FByteFile file) {
         _export.Clear();
         FFunctionInfos functions = _export.Functions;
         SImageDataDirectory idd = _ntHeader.OptionalHeader.DataDirectory[(int)EImageDirectoryEntry.Export];
         if (idd.VirtualAddress != 0) {
            SImageExportDirectory imgExp = (SImageExportDirectory)file.GetStruct(ConvertRva(idd.VirtualAddress), typeof(SImageExportDirectory));
            _export.Data = imgExp;
            _export.Name = file.GetString(ConvertRva(imgExp.Name));
            // Read function
            int funcCount = imgExp.NumberOfFunctions;
            int funcAddr = ConvertRva(imgExp.AddressOfFunctions);
            for (int n = 0; n < funcCount; n++, funcAddr += RInt.BYTE_SIZE) {
               FFunctionInfo function = new FFunctionInfo();
               function.FunctionIndex = (int)(n + imgExp.Base);
               function.FunctionAddress = file.GetUint32(funcAddr);
               function.FunctionAddressRva = ConvertRva(function.FunctionAddress);
               functions.Push(function);
            }
            // Read function names
            int nameCount = imgExp.NumberOfNames;
            int nameAddr = ConvertRva(imgExp.AddressOfNames);
            int nameOrdAddr = ConvertRva(imgExp.AddressOfNameOrdinals);
            for (int n = 0; n < nameCount; n++, nameAddr += RInt.BYTE_SIZE, nameOrdAddr += RShort.BYTE_SIZE) {
               int funcIndex = file.GetUint16(nameOrdAddr);
               FFunctionInfo function = functions[funcIndex];
               function.NameAddress = file.GetUint32(nameAddr);
               function.NameAddressRva = ConvertRva(function.NameAddress);
               function.Name = file.GetString(function.NameAddressRva);
            }
         }
      }

      protected bool ReadOptionalImport(FByteFile file) {
         FModuleInfoCollection modules = _import.Modules;
         modules.Clear();
         SImageDataDirectory idd = _ntHeader.OptionalHeader.DataDirectory[(int)EImageDirectoryEntry.Import];
         if (idd.VirtualAddress == 0) {
            return false;
         }
         // Read modules
         int vaddress = ConvertRva(idd.VirtualAddress);
         int size = Marshal.SizeOf(typeof(SImageImportDescriptor));
         while (true) {
            SImageImportDescriptor impDesc = (SImageImportDescriptor)file.GetStruct(vaddress, typeof(SImageImportDescriptor));
            if (impDesc.Name == 0) {
               break;
            }
            FModuleInfo module = new FModuleInfo();
            module.NameAddress = impDesc.Name;
            module.Name = file.GetString(ConvertRva(impDesc.Name));
            module.FirstThunk = impDesc.FirstThunk;
            module.OriginalFirstThunk = impDesc.OriginalFirstThunk;
            ReadTrunks(file, module);
            modules.Push(module);
            vaddress += size;
         }
         return true;
      }

      protected bool ReadTrunks(FByteFile file, FModuleInfo module) {
         // Trunk
         int ftSize = Marshal.SizeOf(typeof(SImageThunkData32));
         int pOrgFt = ConvertRva(module.OriginalFirstThunk);
         int pFt = ConvertRva(module.FirstThunk);
         while (true) {
            SImageThunkData32 origThunk = (SImageThunkData32)file.GetStruct(pOrgFt, typeof(SImageThunkData32));
            SImageThunkData32 realThunk = (SImageThunkData32)file.GetStruct(pFt, typeof(SImageThunkData32));
            if (origThunk.Function == 0) {
               break;
            }
            if ((origThunk.Ordinal & 0x80000000) == 0x80000000) {
               break;
            }
            // Read name
            SImageImportByName impName = (SImageImportByName)file.GetStruct(ConvertRva(origThunk.AddressOfData), typeof(SImageImportByName));
            if (impName.Name[0] == 0) {
               break;
            }
            // TrunkInfo
            FTrunkInfo trunk = new FTrunkInfo();
            trunk.Name = RAscii.GetString(impName.Name);
            trunk.Address = origThunk.Function;
            trunk.Entry = (IntPtr)realThunk.Function;
            trunk.Hint = impName.Hint;
            module.Trunks.Push(trunk);
            // Loop
            pOrgFt += ftSize;
            pFt += ftSize;
         }
         return true;
      }

   }


}
