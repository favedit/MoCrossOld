using System;
using System.Collections.Generic;
using System.Text;
using System.Runtime.InteropServices;

namespace MO.Core.Window.Api {

   [ComImport]
   [InterfaceType(ComInterfaceType.InterfaceIsIUnknown)]
   [Guid("C0A651F5-B48B-11d2-B5ED-006097C686F6")]
   public interface IFolderFilterSite {
      // Exposed by a host to allow clients to pass the host their IUnknown interface pointers.
      [PreserveSig]
      Int32 SetFilter(
         [MarshalAs(UnmanagedType.Interface)]Object punk);		// A pointer to the client's IUnknown interface. To notify the host to terminate 
      // filtering and stop calling your IFolderFilter interface, set this parameter to NULL. 
   }

}
