using System;
using System.Collections.Generic;
using System.Text;
using System.Runtime.InteropServices;
using MO.Core.Window.Api;

namespace MO.Core.Window.Api {

   [ComImport()]
   [InterfaceType(ComInterfaceType.InterfaceIsIUnknown)]
   [GuidAttribute("000214e4-0000-0000-c000-000000000046")]
   public interface IContextMenu {

      /* STDMETHOD(QueryContextMenu)(THIS_
       *    HMENU hmenu,
       *    UINT indexMenu,
       *    UINT idCmdFirst,
       *    UINT idCmdLast,
       *    UINT uFlags
       * ) */
      [PreserveSig()]
      Int32 QueryContextMenu(
          IntPtr hmenu,
          uint iMenu,
          uint idCmdFirst,
          uint idCmdLast,
          ECmf uFlags);

      /* STDMETHOD(InvokeCommand)(THIS_
       *    LPCMINVOKECOMMANDINFO lpici
       * ) */
      [PreserveSig()]
      Int32 InvokeCommand(
          ref SCmInvokeCommandInfoEX info);

      /* STDMETHOD(GetCommandString)(THIS_
       *    UINT_PTR    idCmd,
       *    UINT        uType,
       *    UINT      * pwReserved,
       *    LPSTR       pszName,
       *    UINT        cchMax
       * ) */
      [PreserveSig()]
      void GetCommandString(
          int idcmd,
          EGetCommandStringInformations uflags,
          int reserved,
          StringBuilder commandstring,
          int cch);
   }

}
