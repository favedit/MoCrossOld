using System;
using System.Collections.Generic;
using System.Text;
using System.Runtime.InteropServices;
using MO.Core.Window.Api;

namespace MO.Core.Window.Api {

   [ComImport]
   [InterfaceType(ComInterfaceType.InterfaceIsIUnknown)]
   [Guid("000214E6-0000-0000-C000-000000000046")]
   public interface IShellFolder {

      /* HRESULT STDMETHODCALLTYPE ParseDisplayName( 
       *    [in] HWND hwnd,
       *    [in] LPBC pbc,
       *    [string][in] LPOLESTR pszDisplayName,
       *    [out] ULONG *pchEaten,
       *    [out] LPITEMIDLIST *ppidl,
       *    [unique][out][in] ULONG *pdwAttributes
       * ) */
      void ParseDisplayName(
         IntPtr hwnd,
         IntPtr pbc,
         [MarshalAs(UnmanagedType.LPWStr)] string pszDisplayName,
         out uint pchEaten,
         out IntPtr ppidl,
         ref uint pdwAttributes);

      /* HRESULT STDMETHODCALLTYPE EnumObjects(
       *    [in] HWND hwnd,
       *    [in] SHCONTF grfFlags,
       *    [out] IEnumIDList **ppenumIDList
       * ) */
      [PreserveSig]
      int EnumObjects(
         IntPtr hWnd,
         EShcontf flags,
         out IntPtr enumIDList);

      /* HRESULT STDMETHODCALLTYPE BindToObject(
       *    [in] LPCITEMIDLIST pidl,
       *    [in] LPBC pbc,
       *    [in] REFIID riid,
       *    [iid_is][out] void **ppv
       * ) */
      void BindToObject(
         IntPtr pidl,
         IntPtr pbc,
         [In()] ref Guid riid,
         out IShellFolder ppv);

      /* HRESULT STDMETHODCALLTYPE BindToStorage(
       *    [in] LPCITEMIDLIST pidl,
       *    [in] LPBC pbc,
       *    [in] REFIID riid,
       *    [iid_is][out] void **ppv
       * ) */
      void BindToStorage(
         IntPtr pidl,
         IntPtr pbc,
         [In()] ref Guid riid,
         [MarshalAs(UnmanagedType.Interface)] out object ppv);

      /* HRESULT STDMETHODCALLTYPE CompareIDs(
       *    [in] LPARAM lParam,
       *    [in] LPCITEMIDLIST pidl1,
       *    [in] LPCITEMIDLIST pidl2
       * ) */
      [PreserveSig()]
      uint CompareIDs(
         int lParam,
         IntPtr pidl1,
         IntPtr pidl2);

      /* HRESULT STDMETHODCALLTYPE CreateViewObject(
       *    [in] HWND hwndOwner,
       *    [in] REFIID riid,
       *    [iid_is][out] void **ppv
       * ) */
      void CreateViewObject(
         IntPtr hwndOwner,
         [In()] ref Guid riid,
         [MarshalAs(UnmanagedType.Interface)] out object ppv);

      /* HRESULT STDMETHODCALLTYPE GetAttributesOf(
       *    [in] UINT cidl,
       *    [size_is][in] LPCITEMIDLIST *apidl,
       *    [out][in] SFGAOF *rgfInOut
       * ) */
      void GetAttributesOf(
         uint cidl,
         [In(), MarshalAs(UnmanagedType.LPArray)] IntPtr[] apidl,
         ref ESfgao rgfInOut);

      /* HRESULT STDMETHODCALLTYPE GetUIObjectOf(
       *    [in] HWND hwndOwner,
       *    [in] UINT cidl,
       *    [size_is][in] LPCITEMIDLIST *apidl,
       *    [in] REFIID riid,
       *    [unique][out][in] UINT *rgfReserved,
       *    [iid_is][out] void **ppv
       * ) */
      IntPtr GetUIObjectOf(
         IntPtr hwndOwner,
         uint cidl,
         [MarshalAs(UnmanagedType.LPArray)] IntPtr[] apidl,
         [In()] ref Guid riid,
         out IntPtr rgfReserved);

      /* HRESULT STDMETHODCALLTYPE GetDisplayNameOf(
       *    [in] LPCITEMIDLIST pidl,
       *    [in] SHGDNF uFlags,
       *    [out] STRRET *pName
       * ) */
      void GetDisplayNameOf(
         IntPtr pidl,
         EShgno uFlags,
         IntPtr lpName);

      /* HRESULT STDMETHODCALLTYPE SetNameOf(
       *    [in] HWND hwnd,
       *    [in] LPCITEMIDLIST pidl,
       *    [string][in] LPCOLESTR pszName,
       *    [in] SHGDNF uFlags,
       *    [out] LPITEMIDLIST *ppidlOut
       * ) */
      IntPtr SetNameOf(
         IntPtr hwnd,
         IntPtr pidl,
         [MarshalAs(UnmanagedType.LPWStr)] string pszName,
         EShgno uFlags);
   }
}
