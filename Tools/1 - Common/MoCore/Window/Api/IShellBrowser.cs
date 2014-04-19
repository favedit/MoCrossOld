using System;
using System.Collections.Generic;
using System.Text;
using System.Runtime.InteropServices;
using System.Runtime.InteropServices.ComTypes;

namespace MO.Core.Window.Api {

   [ComImport]
   [InterfaceType(ComInterfaceType.InterfaceIsIUnknown)]
   [Guid("000214E2-0000-0000-C000-000000000046")]
   public interface IShellBrowser {

      /* HRESULT STDMETHODCALLTYPE InsertMenusSB(
       *    [in] HMENU hmenuShared,
       *    [out][in] LPOLEMENUGROUPWIDTHS lpMenuWidths
       * ) */
      int InsertMenusSB(
         IntPtr hmenuShared,
         ref SOleMenuGroupWidths lpMenuWidths);

      /* HRESULT STDMETHODCALLTYPE SetMenuSB(
       *    [in] HMENU hmenuShared,
       *    [in] HOLEMENU holemenuRes,
       *    [in] HWND hwndActiveObject
       * ) */
      int SetMenuSB(
         IntPtr hmenuShared,
         IntPtr holemenuRes,
          IntPtr hwndActiveObject);

      /* HRESULT STDMETHODCALLTYPE RemoveMenusSB(
       *    [in] HMENU hmenuShared
       * ) */
      int RemoveMenusSB(
         IntPtr hmenuShared);

      /* HRESULT STDMETHODCALLTYPE SetStatusTextSB(
       *    [unique][in] LPCOLESTR pszStatusText
       * ) */
      int SetStatusTextSB(
         string pszStatusText);

      /* HRESULT STDMETHODCALLTYPE EnableModelessSB(
       *    [in] BOOL fEnable
       * ) */
      int EnableModelessSB(
         bool fEnable);

      /* HRESULT STDMETHODCALLTYPE TranslateAcceleratorSB(
       *    [in] MSG *pmsg,
       *    [in] WORD wID
       * ) */
      int TranslateAcceleratorSB(
         ref SMsg pmsg,
         short wID);

      /* HRESULT STDMETHODCALLTYPE BrowseObject( 
       *    [in] LPCITEMIDLIST pidl,
       *    [in] UINT wFlags
       * ) */
      int BrowseObject(
         out IntPtr pidl,
         uint wFlags);

      /* HRESULT STDMETHODCALLTYPE GetViewStateStream(
       *    [in] DWORD grfMode,
       *    [out] IStream **ppStrm
       * ) */
      int GetViewStateStream(
         uint grfMode,
         ref IStream ppStrm);

      /* HRESULT STDMETHODCALLTYPE GetControlWindow(
       *    [in] UINT id,
       *    [out] HWND *phwnd
       * ) */
      int GetControlWindow(
         uint id,
         ref IntPtr phwnd);

      /* [local] HRESULT STDMETHODCALLTYPE SendControlMsg(
       *    [in] UINT id,
       *    [in] UINT uMsg,
       *    [in] WPARAM wParam,
       *    [in] LPARAM lParam,
       *    [in] LRESULT *pret
       * ) */
      int SendControlMsg(
         uint id,
         uint uMsg,
         uint wParam,
         uint lParam,
         ref uint pret);

      /* HRESULT STDMETHODCALLTYPE QueryActiveShellView(
       *    [out] IShellView **ppshv
       * ) */
      int QueryActiveShellView(
         ref IShellView ppshv);

      /* HRESULT STDMETHODCALLTYPE OnViewWindowActive(
       *    [in] IShellView *pshv
       * ) */
      int OnViewWindowActive(
         ref IShellView pshv);

      /* [local] HRESULT STDMETHODCALLTYPE SetToolbarItems(
       *    [in] LPTBBUTTONSB lpButtons,
       *    [in] UINT nButtons,
       *    [in] UINT uFlags
       * ) */
      int SetToolbarItems(
         STbButton lpButtons,
         uint nButtons,
         uint uFlags);
   }
}
