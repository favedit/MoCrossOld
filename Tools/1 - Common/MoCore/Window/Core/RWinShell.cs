using System;
using System.Collections.Generic;
using System.Text;
using System.Runtime.InteropServices;
using MO.Core.Window.Api;
using System.Drawing;

namespace MO.Core.Window.Core {

   public class RWinShell {

      public const int S_OK = 0;
      public const int S_FALSE = 1;

      public const uint CMD_FIRST = 1;
      public const uint CMD_LAST = 30000;

      public const int MAX_PATH = 260;

      public const int TV_FIRST = 0x1100;
      public const int TVM_SETIMAGELIST = (TV_FIRST + 9);
      public const int TVSIL_NORMAL = 0;

      public const int LVM_FIRST = 0x1000;
      public const int LVM_GETHEADER = (LVM_FIRST + 31);
      public const int LVM_SETIMAGELIST = LVM_FIRST + 3;
      public const int LVSIL_NORMAL = 0;
      public const int LVSIL_SMALL = 1;

      public static Icon[] ExtractIconFromFile(string filename) {
         IntPtr pLargeIcon = IntPtr.Zero;
         IntPtr pSmallIcon = IntPtr.Zero;
         int iconCount = RShell32.ExtractIconEx(filename, -1, ref pLargeIcon, ref pSmallIcon, 1);
         Icon[] icons = new Icon[iconCount];
         for (int n = 0; n < iconCount; n++) {
            RShell32.ExtractIconEx(filename, n, ref pLargeIcon, ref pSmallIcon, 1);
            icons[n] = Icon.FromHandle(pLargeIcon);
         }
         return icons;
      }

      public static string GetNameByPIDL(IntPtr pidl) {
         SShFileInfo info = new SShFileInfo();
         RShell32.SHGetFileInfo(pidl, 0, ref info, Marshal.SizeOf(typeof(SShFileInfo)),
             EShGetFileInfo.Pidl | EShGetFileInfo.DisplayName | EShGetFileInfo.TypeName);
         return info.szDisplayName;
      }

      public static string GetSpecialFolderPath(IntPtr hwnd, EShellSpecialFolders nFolder) {
         StringBuilder sb = new StringBuilder(RShell32.MAX_PATH);
         RShell32.SHGetSpecialFolderPath(hwnd, sb, nFolder, false);
         return sb.ToString();
      }

      public static IShellFolder GetShellFolder(IShellFolder desktop, string path, out IntPtr Pidl) {
         IShellFolder IFolder;
         uint i, j = 0;
         desktop.ParseDisplayName(IntPtr.Zero, IntPtr.Zero, path, out i, out Pidl, ref j);
         desktop.BindToObject(Pidl, IntPtr.Zero, ref Guids.IID_IShellFolder, out IFolder);
         return IFolder;
      }

      public static IShellFolder GetShellFolder(IShellFolder desktop, string path) {
         IntPtr Pidl;
         return GetShellFolder(desktop, path, out Pidl);
      }

      public static int GetLargeIconIndex(string strFilename) {
         SShFileInfo psfi = new SShFileInfo();
         //SHGFI dwflag = SHGFI.ICON | SHGFI.LARGEICON | SHGFI.SYSICONINDEX;
         //IntPtr ipIcon = RShell32.SHGetFileInfo(strFilename, 0, out psfi, Marshal.SizeOf(psfi), dwflag);
         return psfi.iIcon;
      }

      public static int GetLargeIconIndex(IntPtr ipIDList) {
         SShFileInfo psfi = new SShFileInfo();
         EShGetFileInfo dwflag = EShGetFileInfo.Icon | EShGetFileInfo.LargeIcon | EShGetFileInfo.Pidl | EShGetFileInfo.SysIconIndex;
         IntPtr ipIcon = RShell32.SHGetFileInfo(ipIDList, 0, ref psfi, Marshal.SizeOf(psfi), dwflag);

         return psfi.iIcon;
      }

      public static int GetSmallIconIndex(string strFilename) {
         SShFileInfo psfi = new SShFileInfo();
         //SHGFI dwflag = SHGFI.ICON | SHGFI.SMALLICON | SHGFI.SYSICONINDEX;
         //IntPtr ipIcon = RShell32.SHGetFileInfo(strFilename, 0, out psfi, Marshal.SizeOf(psfi), dwflag);
         return psfi.iIcon;
      }

      public static int GetSmallIconIndex(IntPtr ipIDList) {
         SShFileInfo psfi = new SShFileInfo();
         EShGetFileInfo dwflag = EShGetFileInfo.Icon | EShGetFileInfo.Pidl | EShGetFileInfo.SmallIcon | EShGetFileInfo.SysIconIndex;
         IntPtr ipIcon = RShell32.SHGetFileInfo(ipIDList, 0, ref psfi, Marshal.SizeOf(psfi), dwflag);

         return psfi.iIcon;
      }
   }

}
