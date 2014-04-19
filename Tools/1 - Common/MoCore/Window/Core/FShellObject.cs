using System;
using System.Collections.Generic;
using System.Text;
using System.Runtime.InteropServices;
using MO.Common.Lang;
using MO.Core.Window.Api;

namespace MO.Core.Window.Core {

   public abstract class FShellObject : IDisposable {

      internal FShellObject _parent;

      internal IntPtr _handle;

      internal IShellFolder _face;

      internal string _name;

      internal int _icon = -1;

      public FShellObject() {
      }

      public FShellObject(IntPtr pidl) {
         _handle = pidl;
         RefreshInfo();
      }

      public FShellObject Parent {
         get { return _parent; }
         internal set { _parent = value; }
      }

      public IntPtr Handle {
         get { return _handle; }
         set { SetHandle(value); }
      }

      public IShellFolder Face {
         get { return _face; }
         set { _face = value; }
      }

      public string Name {
         get { return _name; }
         internal set { _name = value; }
      }

      public int Icon {
         get { return _icon; }
      }

      internal virtual void SetHandle(IntPtr handle) {
         _handle = handle;
      }

      public void RefreshInfo() {
         // Name
         if (_parent != null && _parent is FShellFolder) {
            FShellFolder folder = (FShellFolder)_parent;
            _name = folder.GetSubObjectName(_handle);
         }
         // Info
         SShFileInfo info = new SShFileInfo();
         EShGetFileInfo flag = EShGetFileInfo.Pidl |
            EShGetFileInfo.DisplayName |
            EShGetFileInfo.TypeName |
            EShGetFileInfo.Pidl |
            EShGetFileInfo.Icon |
            EShGetFileInfo.SmallIcon |
            EShGetFileInfo.SysIconIndex;
         IntPtr rsPtr = RShell32.SHGetFileInfo(FullPidl.Ptr, 0, ref info, Marshal.SizeOf(typeof(SShFileInfo)), flag);
         if (_name == null) {
            _name = info.szDisplayName;
         }
         _icon = info.iIcon;
      }

      public FContextMenu GetContextMenu() {
         IntPtr[] pidls = new IntPtr[1];
         pidls[0] = _handle;
         FContextMenu menu = new FContextMenu();
         menu._handle = _parent._face.GetUIObjectOf(
            IntPtr.Zero, (uint)pidls.Length, pidls, ref Guids.IID_IContextMenu, out menu._handle);
         menu._face = (IContextMenu)Marshal.GetObjectForIUnknown(menu._handle);
         return menu;
      }

      public FShellView GetShellView() {
         IntPtr[] pidls = new IntPtr[1];
         pidls[0] = _handle;
         FShellView view = new FShellView();
         view._handle = _parent._face.GetUIObjectOf(
            IntPtr.Zero, (uint)pidls.Length, pidls, ref Guids.IID_IShellView, out view._handle);
         view._face = (IShellView)Marshal.GetObjectForIUnknown(view._handle);
         return view;
      }

      public FPidl FullPidl {
         get {
            FPidl pidl = new FPidl(_handle, true);
            FShellObject parent = _parent;
            while (parent != null) {
               pidl.Insert(parent._handle);
               parent = parent._parent;
            }
            return pidl;
         }
      }

      #region IDisposable implements

      public void Dispose() {
         if (_face != null) {
            Marshal.ReleaseComObject(_face);
         }
      }

      #endregion
   }

}
