using System;
using System.Collections.Generic;
using System.Text;
using System.Runtime.InteropServices;

namespace MO.Core.Window.Api {

   [ComImport]
   [InterfaceType(ComInterfaceType.InterfaceIsIUnknown)]
   [Guid("000214E3-0000-0000-C000-000000000046")]
   public interface IShellView {
      /* HRESULT STDMETHODCALLTYPE TranslateAccelerator(
       *    [in] MSG *pmsg
       * ) */
      [PreserveSig]
      int TranslateAccelerator(
         ref SMsg pmsg);

      /* HRESULT STDMETHODCALLTYPE EnableModeless(
       *    [in] BOOL fEnable
       * ) */
      [PreserveSig]
      int EnableModeless(
         bool fEnable);

      /* HRESULT STDMETHODCALLTYPE UIActivate(
       *    [in] UINT uState
       * ) */
      [PreserveSig]
      int UIActivate(
         uint uState);

      /* HRESULT STDMETHODCALLTYPE Refresh(void) */
      [PreserveSig]
      int Refresh();

      /* HRESULT STDMETHODCALLTYPE CreateViewWindow(
       *    [in] IShellView *psvPrevious,
       *    [in] LPCFOLDERSETTINGS pfs,
       *    [in] IShellBrowser *psb,
       *    [out] RECT *prcView,
       *    [out] HWND *phWnd
       * ) */
      [PreserveSig]
      int CreateViewWindow(
         ref IShellView psvPrevious,
         SFolderSettings pfs,
         ref IShellBrowser psb,
         ref SRect prcView,
         ref IntPtr phWnd);

      /* HRESULT STDMETHODCALLTYPE DestroyViewWindow(void) */
      [PreserveSig]
      int DestroyViewWindow();

      /* HRESULT STDMETHODCALLTYPE GetCurrentInfo(
       *    [out] LPFOLDERSETTINGS pfs
       * ) */
      [PreserveSig]
      int GetCurrentInfo(
         ref SFolderSettings pfs);

      /* [local] HRESULT STDMETHODCALLTYPE AddPropertySheetPages(
       *    [in] DWORD dwReserved,
       *    [in] LPFNSVADDPROPSHEETPAGE pfn,
       *    [in] LPARAM lparam
       * ) */
      [PreserveSig]
      int AddPropertySheetPages(
         uint dwReserved,
         IntPtr pfn,
         int lparam);

      /* HRESULT STDMETHODCALLTYPE SaveViewState(void) */
      [PreserveSig]
      int SaveViewState();

      /* HRESULT STDMETHODCALLTYPE SelectItem(
       *    [in] LPCITEMIDLIST pidlItem,
       *    [in] SVSIF uFlags
       * ) */
      [PreserveSig]
      int SelectItem(
         out IntPtr pidlItem,
         uint uFlags);

      /* HRESULT STDMETHODCALLTYPE GetItemObject(
       *    [in] UINT uItem,
       *    [in] REFIID riid,
       *    [iid_is][out] void **ppv
       * ) */
      [PreserveSig]
      int GetItemObject(
         uint uItem,
         ref Guid riid,
         IntPtr ppv);
   }
}
