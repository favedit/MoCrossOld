using System;
using System.Collections.Generic;
using System.Text;
using MO.Core.Window.Api;
using MO.Common.Lang;

namespace MO.Core.Window.Core {

   public class FProcessInfo : IDisposable {

      private int _id;

      private IntPtr _handle = IntPtr.Zero;

      public int Threads;

      public int PriClassBase;

      public string FileName;

      private FModuleInfoCollection _modules;

      public FProcessInfo() {
      }

      public FProcessInfo(int id) {
         Open(id, EProcessAccess.QueryInformation | EProcessAccess.VmRead);
      }

      public FProcessInfo(int id, EProcessAccess access) {
         Open(id, access);
      }

      public int Id {
         get { return _id; }
         set { _id = value; }
      }

      public IntPtr Handle {
         get { return _handle; }
         set { _handle = value; }
      }

      public void Open() {
         Open(_id, EProcessAccess.QueryInformation | EProcessAccess.VmRead);
      }

      public void Open(int id, EProcessAccess access) {
         _id = id;
         _handle = RKernel32.OpenProcess(access, false, _id);
         if (_handle == IntPtr.Zero) {
            throw new FFatalException("Open process error. (id={0})", _id);
         }
      }

      public bool OpenAllModules() {
         bool result = true;
         foreach (FModuleInfo module in Modules) {
            if (!module.Open()) {
               result = false;
            }
         }
         return result;
      }

      public T ReadStructure<T>(IntPtr hAddress) {
         return RProcess.PtrToStructure<T>(_handle, hAddress);
      }

      public int Read(uint address, byte[] buffer) {
         return Read(address, buffer, buffer.Length);
      }

      public int Read(uint address, byte[] buffer, int size) {
         int readed = 0;
         RKernel32.ReadProcessMemory(_handle, address, buffer, size, out readed);
         return readed;
      }

      public int Write(uint address, byte[] buffer) {
         return Write(address, buffer, buffer.Length);
      }

      public int Write(IntPtr ptr, byte[] buffer) {
         return Write((uint)ptr.ToInt32(), buffer, buffer.Length);
      }

      public int Write(uint address, byte[] buffer, int size) {
         int writed = 0;
         RKernel32.WriteProcessMemory(_handle, address, buffer, size, out writed);
         return writed;
      }

      public FModuleInfoCollection Modules {
         get {
            if (_modules == null) {
               _modules = new FModuleInfoCollection(this);
               RModule.List(_modules, _id);
            }
            return _modules;
         }
      }

      public void Close() {
         if (_handle != IntPtr.Zero) {
            RKernel32.CloseHandle(_handle);
         }
      }

      public string GetInfo() {
         return Id.ToString("X8") + " " + FileName;
      }

      public override string ToString() {
         return Id.ToString("X8") + " " + FileName;
      }

      #region IDisposable members

      public void Dispose() {
         Close();
      }

      #endregion
   }

}
