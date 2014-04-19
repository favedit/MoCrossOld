using System;
using System.Collections.Generic;
using System.Text;
using MO.Common.Lang;

namespace MO.Common.Content {

   public class FCsvFormat {

      protected char _delimiter = ',';

      protected bool _dataClose = false;

      protected bool _header = false;

      protected bool _footer = false;

      protected bool _footerCheck = false;

      protected string _footerChar;

      protected string _footerOpt;

      public FCsvFormat() {
      }

      public char Delimiter {
         get { return _delimiter; }
         set { _delimiter = value; }
      }

      public bool DataClose {
         get { return _dataClose; }
         set { _dataClose = value; }
      }

      public bool Header {
         get { return _header; }
         set { _header = value; }
      }

      public bool Footer {
         get { return _footer; }
         set { _footer = value; }
      }

      public bool FooterCheck {
         get { return _footerCheck; }
         set { _footerCheck = value; }
      }

      public string FooterChar {
         get { return _footerChar; }
         set { _footerChar = value; }
      }

      public string FooterOpt {
         get { return _footerOpt; }
         set { _footerOpt = value; }
      }

   }

}
