using System;
using System.Collections.Generic;
using System.Text;

namespace MO.Common.Content
{
   //============================================================
   public class FCsvFile : IDisposable
   {
      private string _filename;

      protected FCsvHeads _heads = new FCsvHeads();

      protected FCsvLines _lines = new FCsvLines();

      //============================================================
      public FCsvLines Lines {
         get { return _lines; }
      }

      //============================================================
      public string FileName {
         get { return _filename; }
         set { _filename = null; }
      }

      //============================================================
      public void LoadConfig(FXmlNode config) {
         if(config != null) {
            FXmlNode headsNode = config.Find(FCsvHeads.TAG_NAME);
            if(headsNode != null) {
               _heads.LoadConfig(headsNode);
            }
         }
      }

      //============================================================
      public void LoadFile() {
         LoadFile(_filename);
      }

      //============================================================
      public void LoadFile(string filename) {
         FCsvReader reader = new FCsvReader(filename);
         while(reader.HasNext()) {
            FCsvLine line = reader.ReadLine();
            line._heads = _heads;
            _lines.Push(line);
         }
      }

      //============================================================
      public void SaveFile() {
      }

      #region IDisposable implements

      public void Dispose() {
      }

      #endregion
   }
}
