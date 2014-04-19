using System;
using System.Collections.Generic;
using System.Text;
using MO.Core.Window.Api;
using System.Runtime.InteropServices;

namespace MO.Core.Window.Core {

   public class FShellDesktop : FShellFolder {

      public FShellDesktop() {
         Link();
      }

      protected void Link() {
         IntPtr facePtr;
         RShell32.SHGetDesktopFolder(out facePtr);
         _face = (IShellFolder)Marshal.GetObjectForIUnknown(facePtr);
         RShell32.SHGetSpecialFolderLocation(IntPtr.Zero, ECsidl.CSIDL_DESKTOP, out _handle);
      }

   }

}
