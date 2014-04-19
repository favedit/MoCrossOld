using System;
using System.Collections.Generic;
using System.Text;
using System.Runtime.InteropServices;

namespace MO.Core.Window.Api {

   [ComImport()]
   [Guid("000214F2-0000-0000-C000-000000000046")]
   [InterfaceType(ComInterfaceType.InterfaceIsIUnknown)]
   public interface IEnumIDList {

      /* HRESULT STDMETHODCALLTYPE Next(
       *    [in] ULONG celt,
       *    [length_is][size_is][out] LPITEMIDLIST *rgelt,
       *    [out] ULONG *pceltFetched
       * ) */
      [PreserveSig]
      int Next(
          uint celt,
          out IntPtr rgelt,
          out int pceltFetched);

      /* HRESULT STDMETHODCALLTYPE Skip(
       *    [in] ULONG celt
       * ) */
      [PreserveSig]
      int Skip(
          uint celt);

      /* HRESULT STDMETHODCALLTYPE Reset(void) */
      [PreserveSig]
      int Reset();

      /* HRESULT STDMETHODCALLTYPE Clone(
       *    [out] IEnumIDList **ppenum
       * ) */
      [PreserveSig]
      int Clone(
         ref IEnumIDList ppenum);
   }

}
