﻿using System.IO;
using System.Collections.Generic;

namespace MS.Common.IO
{
   //============================================================
   // <T>文件信息。</T>
   //
   // @history MAOCY 140414
   //============================================================
   public class FFileInfo
   {
      private FileInfo _info;

      //============================================================
      // <T>构造文件信息。</T>
      //============================================================
      public FFileInfo(string filename) {
         _info = new FileInfo(filename);
      }

      //============================================================
      public FileInfo Info {
         get { return _info; }
      }

      //============================================================
      public string Name {
         get { return _info.Name; }
      }

      //============================================================
      public string ShortName {
         get {
            string name = _info.Name;
            return name.Substring(0, name.Length - _info.Extension.Length);
         }
      }

      //============================================================
      public string FullName {
         get {
            string name = _info.Directory + "\\" + _info.Name;
            return name;
         }
      }

      //============================================================
      public string DirectoryName {
         get { return _info.DirectoryName; }
      }
   }
}
