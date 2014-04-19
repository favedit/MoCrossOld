using System;
using System.Collections.Generic;
using System.Text;
using System.Runtime.InteropServices;

namespace MO.Core.Window.Api {

   public class RShell32 {

      public const int MAX_PATH = 260;

      /* UINT ExtractIconEx(
       *    LPCTSTR lpszFile,
       *    int nIconIndex,
       *    HICON *phiconLarge,
       *    HICON *phiconSmall,
       *    UINT nIcons
       * ) */
      [DllImport("shell32.dll")]
      public static extern int ExtractIconEx(
         string lpszFile,
         int niconIndex,
         ref IntPtr phiconLarge,
         ref IntPtr phiconSmall,
         int nIcons);

      /* BOOL ILIsEqual(
       *    PCIDLIST_ABSOLUTE pidl1,
       *    PCIDLIST_ABSOLUTE pidl2
       * ) */
      [DllImport("shell32.dll", EntryPoint = "ILIsEqual", ExactSpelling = true, CharSet = CharSet.Ansi, SetLastError = true)]
      public static extern bool ILIsEqual(
         IntPtr pidl1,
         IntPtr pidl2);

      /* BOOL WINAPI SHChangeNotifyDeregister(
       *    HWND hwnd
       * ) */
      [DllImport("SHELL32.DLL", EntryPoint = "#4", CharSet = CharSet.Auto)]
      public static extern uint SHChangeNotifyDeregister(uint hNotify);

      /* ULONG SHChangeNotifyRegister(
       *    HWND hwnd,
       *    int fSources,
       *    LONG fEvents,
       *    UINT wMsg,
       *    int cEntries,
       *    const SHChangeNotifyEntry *pshcne
       * ) */
      /// <summary>
      /// 可以把指定的窗口添加到系统的消息监视链中，这样窗口就能接收到来自文件系统或者Shell的通知了
      /// </summary>
      /// <param name="hwnd">将要接收改变或通知消息的窗口的句柄</param>
      /// <param name="fSources">指示接收消息的事件类型</param>
      /// <param name="fEvents">要捕捉的事件</param>
      /// <param name="wMsg">产生对应的事件后，发往窗口的消息</param>
      /// <param name="cEntries">指向的数组的成员的个数</param>
      /// <param name="refFsne">结构体数组的起始指针</param>
      /// <returns></returns>
      [DllImport("shell32.dll", EntryPoint = "#2", CharSet = CharSet.Auto)]
      public static extern uint SHChangeNotifyRegister(
         IntPtr hwnd,
         Int32 fSources,
         Int32 fEvents,
         UInt32 wMsg,
         Int32 cEntries,
         ref SHChangeNotifyEntry refFsne);

      /* HRESULT SHGetDesktopFolder(
       *    IShellFolder **ppshf
       * ) */
      [DllImport("SHELL32.DLL")]
      public static extern Int32 SHGetDesktopFolder(
         out IntPtr ppshf);

      /* DWORD_PTR SHGetFileInfo(
       *    LPCTSTR pszPath,
       *    DWORD dwFileAttributes,
       *    SHFILEINFO *psfi,
       *    UINT cbFileInfo,
       *    UINT uFlags
       * ) */
      [DllImport("SHELL32.DLL", EntryPoint = "SHGetFileInfo", ExactSpelling = false, CharSet = CharSet.Auto, SetLastError = true)]
      public static extern IntPtr SHGetFileInfo(
         IntPtr ppidl,
         EFileAttribute dwFileAttributes,
         ref SShFileInfo sfi,
         int cbFileInfo,
         EShGetFileInfo uFlags);

      /* DWORD_PTR SHGetFileInfo(
       *    LPCTSTR pszPath,
       *    DWORD dwFileAttributes,
       *    SHFILEINFO *psfi,
       *    UINT cbFileInfo,
       *    UINT uFlags
       * ) */
      [DllImport("Shell32.dll", CharSet = CharSet.Auto)]
      public static extern IntPtr SHGetFileInfo(
         string Path,
         EFileAttribute fileAttributes,
         out SShFileInfo sfi,
         int cbFileInfo,
         EShGetFileInfo flags);

      /* HRESULT SHGetRealIDL(
       *    IShellFolder *psf,
       *    PCUITEMID_CHILD pidlSimple,
       *    PITEMID_CHILD *ppidlReal
       * ) */
      [DllImport("shell32.dll")]
      public static extern Int32 SHGetRealIDL(
         IShellFolder psf,
         IntPtr pidlSimple,
         out IntPtr ppidlReal);

      /* HRESULT SHGetSpecialFolderLocation(
       *    HWND hwndOwner,
       *    int nFolder,
       *    PIDLIST_ABSOLUTE *ppidl
       * ) */
      [DllImport("SHELL32.DLL", CharSet = CharSet.Auto)]
      public static extern uint SHGetSpecialFolderLocation(
         IntPtr hWnd,
         ECsidl nFolder,
         out IntPtr Pidl);

      /* BOOL SHGetSpecialFolderPath(
       *    HWND hwndOwner,
       * LPTSTR lpszPath,
       * int nFolder,
       * BOOL fCreate
       * ) */
      [DllImport("SHELL32.DLL")]
      public static extern bool SHGetSpecialFolderPath(
         IntPtr hwndOwner,
         StringBuilder lpszPath,
         EShellSpecialFolders nFolder,
         bool fCreate);

   }

}
