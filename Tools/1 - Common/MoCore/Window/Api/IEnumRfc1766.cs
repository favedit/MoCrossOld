using System;
using System.Runtime.CompilerServices;
using System.Runtime.InteropServices;
using System.Security;

namespace MO.Core.Window.Api {

   [ComImport]
   [InterfaceType(ComInterfaceType.InterfaceIsIUnknown)]
   [Guid("3DC39D1D-C030-11D0-B81B-00C04FC9B31F")]
   public interface IEnumRfc1766 {

      [MethodImpl(MethodImplOptions.InternalCall, MethodCodeType = MethodCodeType.Runtime)]
      void Clone([MarshalAs(UnmanagedType.Interface)] out IEnumRfc1766 ppEnum);

      [MethodImpl(MethodImplOptions.InternalCall, MethodCodeType = MethodCodeType.Runtime)]
      void Next([In] uint celt, out SRfc1766Info rgelt, out uint pceltFetched);

      [MethodImpl(MethodImplOptions.InternalCall, MethodCodeType = MethodCodeType.Runtime)]
      void Reset();

      [MethodImpl(MethodImplOptions.InternalCall, MethodCodeType = MethodCodeType.Runtime)]
      void Skip([In] uint celt);
   }

}
