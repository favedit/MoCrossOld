using System;
using System.Collections.Generic;
using System.Text;

namespace MO.Core.Window.Api {

   [Flags()]
   public enum ESfgao {
      CANCOPY = 0x1,
      CANMOVE = 0x2,
      CANLINK = 0x4,
      STORAGE = 0x8,
      CANRENAME = 0x10,
      CANDELETE = 0x20,
      HASPROPSHEET = 0x40,
      DROPTARGET = 0x100,
      CAPABILITYMASK = 0x177,
      ENCRYPTED = 0x2000,
      ISSLOW = 0x4000,
      GHOSTED = 0x8000,
      LINK = 0x10000,
      SHARE = 0x20000,
      READONLY = 0x40000,
      HIDDEN = 0x80000,
      DISPLAYATTRMASK = 0xFC000,
      FILESYSANCESTOR = 0x10000000,
      FOLDER = 0x20000000,
      FILESYSTEM = 0x40000000,
      HASSUBFOLDER = unchecked((int)0x80000000),
      CONTENTSMASK = unchecked((int)0x80000000),
      VALIDATE = 0x1000000,
      REMOVABLE = 0x2000000,
      COMPRESSED = 0x4000000,
      BROWSABLE = 0x8000000,
      NONENUMERATED = 0x100000,
      NEWCONTENT = 0x200000,
      CANMONIKER = 0x400000,
      HASSTORAGE = 0x400000,
      STREAM = 0x400000,
      STORAGEANCESTOR = 0x800000,
      STORAGECAPMASK = 0x70C50008
   }

}
