using System;
using System.Collections.Generic;
using System.Text;
using System.Runtime.InteropServices;

namespace MO.Core.Window.Api {

   [ComImport]
   [InterfaceType(ComInterfaceType.InterfaceIsIUnknown)]
   [Guid("0000000C-0000-0000-C000-000000000046")]
   public interface IStream {

      /* [local] HRESULT STDMETHODCALLTYPE Seek(
       *    [in] LARGE_INTEGER dlibMove,
       *    [in] DWORD dwOrigin,
       *    [out] ULARGE_INTEGER *plibNewPosition
       * ) */
      int Seek(
         Int64 dlibMove,
         uint dwOrigin,
         Int64 plibNewPosition);

      /* HRESULT STDMETHODCALLTYPE SetSize(
       *    [in] ULARGE_INTEGER libNewSize
       * ) */
      int SetSize(
         Int64 libNewSize);

      /* [local]  HRESULT STDMETHODCALLTYPE CopyTo(
       *    [unique][in] IStream *pstm,
       *    [in] ULARGE_INTEGER cb,
       *    [out] ULARGE_INTEGER *pcbRead,
       *    [out] ULARGE_INTEGER *pcbWritten
       * ) */
      int CopyTo(
         ref IStream pstm,
         Int64 cb,
         ref Int64 pcbRead,
         ref Int64 pcbWritten);

      /* HRESULT STDMETHODCALLTYPE Commit(
       *    [in] DWORD grfCommitFlags
       * ) */
      int Commit(
         uint grfCommitFlags);

      /* HRESULT STDMETHODCALLTYPE Revert(void) */
      int Revert();

      /* HRESULT STDMETHODCALLTYPE LockRegion(
       *    [in] ULARGE_INTEGER libOffset,
       *    [in] ULARGE_INTEGER cb,
       *    [in] DWORD dwLockType
       * ) */
      int LockRegion(
         Int64 libOffset,
         Int64 cb,
         uint dwLockType);

      /* HRESULT STDMETHODCALLTYPE UnlockRegion(
       *    [in] ULARGE_INTEGER libOffset,
       *    [in] ULARGE_INTEGER cb,
       *    [in] DWORD dwLockType
       * ) */
      int UnlockRegion(
         Int64 libOffset,
         Int64 cb,
         int dwLockType);

      /* HRESULT STDMETHODCALLTYPE Stat(
       *    [out] STATSTG *pstatstg,
       *    [in] DWORD grfStatFlag
       * ) */
      int Stat(
         ref System.Runtime.InteropServices.ComTypes.STATSTG pstatstg,
         uint grfStatFlag);

      /* HRESULT STDMETHODCALLTYPE Clone(
       *    [out] IStream **ppstm
       * ) */
      int Clone(
         ref IStream ppstm);

   }

}
