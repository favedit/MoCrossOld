using System;
using System.Collections.Generic;
using System.Text;
using System.Runtime.InteropServices;
using MO.Common.Lang;
using MO.Core.Window.Api;

namespace MO.Core.Window.Core {

   public class FShellFolder : FShellObject {

      public FShellFolder() {
      }

      public FShellFolder(IntPtr pidl)
         : base(pidl) {
      }

      internal override void SetHandle(IntPtr handle) {
         _parent._face.BindToObject(
            handle, IntPtr.Zero, ref Guids.IID_IShellFolder, out _face);
         base.SetHandle(handle);
      }

      internal string GetPathByIShell(IntPtr pidlSub) {
         IntPtr strr = Marshal.AllocCoTaskMem(RShell32.MAX_PATH * 2 + 4);
         Marshal.WriteInt32(strr, 0, 0);
         StringBuilder buf = new StringBuilder(RShell32.MAX_PATH);
         _face.GetDisplayNameOf(pidlSub, EShgno.ForAddressBar | EShgno.ForParsing, strr);
         RShlwapi.StrRetToBuf(strr, pidlSub, buf, RShell32.MAX_PATH);
         Marshal.FreeCoTaskMem(strr);
         return buf.ToString();
      }

      internal string GetSubObjectName(IntPtr pidlSub) {
         IntPtr strr = Marshal.AllocCoTaskMem(RWinShell.MAX_PATH * 2 + 4);
         Marshal.WriteInt32(strr, 0, 0);
         StringBuilder buf = new StringBuilder(RWinShell.MAX_PATH);
         _face.GetDisplayNameOf(pidlSub, EShgno.InFolder, strr);
         RShlwapi.StrRetToBuf(strr, pidlSub, buf, RWinShell.MAX_PATH);
         Marshal.FreeCoTaskMem(strr);
         return buf.ToString();
      }

      internal FShellFolder SelectFolder(string path) {
         IntPtr pidl = IntPtr.Zero;
         uint i, j = 0;
         FShellFolder folder = new FShellFolder();
         _face.ParseDisplayName(_handle, IntPtr.Zero, path, out i, out pidl, ref j);
         folder._handle = pidl;
         _face.BindToObject(pidl, IntPtr.Zero, ref Guids.IID_IShellFolder, out folder._face);
         return folder;
      }

      public FShellObject[] ListChildren(int type) {
         /*IntPtr ptr = IntPtr.Zero;
         if (_face.EnumObjects(_handle, type, out ptr) == RWinShell.S_OK) {
            IntPtr pidlSub;
            int celtFetched;
            FObjects<FShellObject> folders = new FObjects<FShellObject>();
            IEnumIDList list = (IEnumIDList)Marshal.GetObjectForIUnknown(ptr);
            while (list.Next(1, out pidlSub, out celtFetched) == RWinShell.S_OK) {
               if(celtFetched != RWinShell.S_FALSE){
                  break;
               }
               FShellFolder folder = new FShellFolder();
               folder._name = GetSubObjectName(pidlSub);
               folder._handle = pidlSub;
               _face.BindToObject(pidlSub, IntPtr.Zero, ref Guids.IID_IShellFolder, out folder._face);
               folders.Push(folder);
            }
            return folders.ToArray();
         }*/
         return null;
      }

      public FShellFolder[] ListFolder() {
         IntPtr ptr = IntPtr.Zero;
         if (_face.EnumObjects(_handle, EShcontf.Folders | EShcontf.IncludeHidden, out ptr) == RWinShell.S_OK) {
            IntPtr pidlSub;
            int celtFetched;
            FObjects<FShellFolder> folders = new FObjects<FShellFolder>();
            IEnumIDList list = (IEnumIDList)Marshal.GetObjectForIUnknown(ptr);
            while (list.Next(1, out pidlSub, out celtFetched) == RWinShell.S_OK) {
               if (celtFetched != RWinShell.S_FALSE) {
                  break;
               }
               FShellFolder folder = new FShellFolder();
               folder.Parent = this;
               folder.SetHandle(pidlSub);
               folder.RefreshInfo();
               folders.Push(folder);
            }
            return folders.ToArray();
         }
         return null;
      }

      public FShellFile[] ListFile() {
         IntPtr ptr = IntPtr.Zero;
         if (_face.EnumObjects(_handle, EShcontf.NonFolders | EShcontf.IncludeHidden, out ptr) == RWinShell.S_OK) {
            IntPtr pidlSub;
            int celtFetched;
            FObjects<FShellFile> files = new FObjects<FShellFile>();
            IEnumIDList list = (IEnumIDList)Marshal.GetObjectForIUnknown(ptr);
            while (list.Next(1, out pidlSub, out celtFetched) == RWinShell.S_OK && celtFetched == RWinShell.S_FALSE) {
               FShellFile file = new FShellFile();
               file.Parent = this;
               file.SetHandle(pidlSub);
               file.RefreshInfo();
               files.Push(file);
            }
            return files.ToArray();
         }
         return null;
      }

   }

}
