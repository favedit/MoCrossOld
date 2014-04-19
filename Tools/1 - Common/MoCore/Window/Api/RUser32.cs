using System;
using System.Collections.Generic;
using System.Text;
using System.Runtime.InteropServices;

namespace MO.Core.Window.Api {

   public class RUser32 {

      public delegate bool HEnumWindows(
         IntPtr hWnd,
         int lParam);

      public delegate IntPtr HWindowsHook(
         int code,
         IntPtr wParam,
         IntPtr lParam);

      /* LRESULT CallNextHookEx(
       *    HHOOK hhk,
       *    int nCode,
       *    WPARAM wParam,
       *    LPARAM lParam
       * ) */
      [DllImport("USER32.DLL")]
      public extern static IntPtr CallNextHookEx(
         IntPtr handle,
         int code,
         IntPtr wparam,
         IntPtr lparam);

      /* LRESULT CallWindowProc(
       *    WNDPROC lpPrevWndFunc,
       *    HWND hWnd,
       *    UINT Msg,
       *    WPARAM wParam,
       *    LPARAM lParam
       * ) */
      [DllImport("USER32.DLL", EntryPoint = "CallWindowProc")]
      public static extern int CallWindowProc(
         int lpPrevWndFunc,
         int hwnd,
         int MSG,
         int wParam,
         int lParam);

      /* HMENU CreatePopupMenu(VOID) */
      [DllImport("USER32.DLL", SetLastError = true, CharSet = CharSet.Auto)]
      public static extern IntPtr CreatePopupMenu();

      /* BOOL EnumChildWindows(
       *    HWND hWndParent,
       *    WNDENUMPROC lpEnumFunc,
       *    LPARAM lParam
       * ) */
      [DllImport("USER32.DLL")]
      public static extern bool EnumChildWindows(
         IntPtr hWndParent,
         HEnumWindows lpEnumFunc,
         int lParam);

      /* BOOL EnumWindows(
       *    WNDENUMPROC lpEnumFunc,
       *    LPARAM lParam
       * ) */
      [DllImport("USER32.DLL")]
      public static extern int EnumWindows(
         HEnumWindows x,
         int y);

      /* BOOL DestroyMenu(
       *    HMENU hMenu
       * ) */
      [DllImport("USER32.DLL")]
      public static extern bool DestroyMenu(
         IntPtr hMenu);

      /* int GetClassName(
       *    HWND hWnd,
       *    LPTSTR lpClassName,
       *    int nMaxCount
       * ) */
      [DllImport("USER32.DLL")]
      public static extern int GetClassName(
         IntPtr hWnd,
         StringBuilder lpClassName,
         int nMaxCount);

      /* UINT GetMenuDefaultItem(
       *    HMENU hMenu,
       *    UINT fByPos,
       *    UINT gmdiFlags
       * ) */
      [DllImport("USER32.DLL", SetLastError = true, CharSet = CharSet.Auto)]
      public static extern int GetMenuDefaultItem(
         IntPtr hMenu,
         bool fByPos,
         uint gmdiFlags);

      /* int GetWindowText(
       *    HWND hWnd,
       *    LPTSTR lpString,
       *    int nMaxCount
       * ) */
      [DllImport("USER32.DLL", SetLastError = true, CharSet = CharSet.Auto)]
      public static extern int GetWindowText(
         IntPtr hWnd,
         StringBuilder lpString,
         int nMaxCount);

      /* int GetWindowTextLength(
       *    HWND hWnd
       * ) */
      [DllImport("USER32.DLL")]
      public static extern int GetWindowTextLength(
         IntPtr hWnd);

      /* BOOL InsertMenu(
       *    HMENU hMenu,
       *    UINT uPosition,
       *    UINT uFlags,
       *    PTR uIDNewItem,
       *    LPCTSTR lpNewItem
       * ) */
      [DllImport("USER32.DLL", SetLastError = true, CharSet = CharSet.Auto)]
      public static extern bool InsertMenu(
         IntPtr hmenu,
         uint uPosition,
         EMft uflags,
         uint uIDNewItem,
         [MarshalAs(UnmanagedType.LPTStr)] 
         string lpNewItem);

      /* LRESULT SendMessage(
       *    HWND hWnd,
       *    UINT Msg,
       *    WPARAM wParam,
       *    LPARAM lParam
       * ) */
      [DllImport("USER32.DLL", EntryPoint = "SendMessage", SetLastError = true, CallingConvention = CallingConvention.StdCall)]
      public static extern int SendMessage(
         IntPtr hwnd,
         uint Msg,
         int wParam,
         IntPtr lParam);

      /* BOOL SetMenuDefaultItem(
       *    HMENU hMenu,
       *    UINT uItem,
       *    UINT fByPos
       * ) */
      [DllImport("USER32.DLL", SetLastError = true, CharSet = CharSet.Auto)]
      public static extern bool SetMenuDefaultItem(
         IntPtr hMenu,
         uint uItem,
         bool fByPos);

      /* HHOOK SetWindowsHookEx(
       *    int idHook,
       *    HOOKPROC lpfn,
       *    HINSTANCE hMod,
       *    DWORD dwThreadId
       * ) */
      [DllImport("USER32.DLL")]
      public extern static IntPtr SetWindowsHookEx(
         int idHook,
         [MarshalAs(UnmanagedType.FunctionPtr)] 
         HWindowsHook lpfn,
         IntPtr hinstance,
         int threadID);

      /* BOOL SetWindowText(
       *    HWND hWnd,
       *    LPCTSTR lpString
       * ) */
      [DllImport("USER32.DLL")]
      public extern static bool SetWindowText(
         IntPtr hWnd,
         string lpString);

      /* BOOL TrackPopupMenuEx(
       *    HMENU hmenu,
       *    UINT fuFlags,
       *    int x,
       *    int y,
       *    HWND hwnd,
       *    LPTPMPARAMS lptpm
       * ) */
      [DllImport("USER32.DLL", ExactSpelling = true, CharSet = CharSet.Auto)]
      public static extern uint TrackPopupMenuEx(
         IntPtr hmenu,
         ETrackPopupMenu flags,
         int x,
         int y,
         IntPtr hwnd,
         IntPtr lptpm);

      /* BOOL UnhookWindowsHookEx(
       *    HHOOK hhk
       * ) */
      [DllImport("USER32.DLL")]
      public extern static void UnhookWindowsHookEx(
         IntPtr handle);


      [DllImport("USER32.DLL", CharSet = CharSet.Auto, CallingConvention = CallingConvention.Winapi)]
      public extern static IntPtr GetFocus();
   }

}
