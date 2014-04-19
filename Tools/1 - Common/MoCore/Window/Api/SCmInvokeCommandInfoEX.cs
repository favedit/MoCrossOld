using System;
using System.Collections.Generic;
using System.Text;
using System.Runtime.InteropServices;

namespace MO.Core.Window.Api {

   /* typedef struct CMINVOKECOMMANDINFOEX {
    *    DWORD cbSize;
    *    DWORD fMask;
    *    HWND hwnd;
    *    LPCSTR lpVerb;
    *    LPCSTR lpParameters;
    *    LPCSTR lpDirectory;
    *    int nShow;
    *    DWORD dwHotKey;
    *    HANDLE hIcon;
    *    LPCSTR lpTitle;
    *    LPCWSTR lpVerbW;
    *    LPCWSTR lpParametersW;
    *    LPCWSTR lpDirectoryW;
    *    LPCWSTR lpTitleW;
    *    POINT ptInvoke;
    * } */
   [StructLayout(LayoutKind.Sequential, CharSet = CharSet.Unicode)]
   public struct SCmInvokeCommandInfoEX {
      public int cbSize;
      public uint fMask;
      public IntPtr hwnd;
      public IntPtr lpVerb;
      [MarshalAs(UnmanagedType.LPStr)] public string lpParameters;
      [MarshalAs(UnmanagedType.LPStr)] public string lpDirectory;
      public int nShow;
      public int dwHotKey;
      public IntPtr hIcon;
      [MarshalAs(UnmanagedType.LPStr)] public string lpTitle;
      public IntPtr lpVerbW;
      [MarshalAs(UnmanagedType.LPWStr)] public string lpParametersW;
      [MarshalAs(UnmanagedType.LPWStr)] public string lpDirectoryW;
      [MarshalAs(UnmanagedType.LPWStr)] public string lpTitleW;
      public SPoint ptInvoke;
   }

}
