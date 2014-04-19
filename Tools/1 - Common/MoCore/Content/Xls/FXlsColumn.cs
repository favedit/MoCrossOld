using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace MO.Core.Content.Xls
{
   //============================================================
   public class FXlsColumn
   {
      protected string _group;

      protected string _id;

      protected int _index;

      protected string _label;

      protected string _name;

      protected string _type;

      protected string _dataType;

      protected string _translate;

      protected string _lenth;

      protected string _total;

      protected string _note;

      //============================================================
      public FXlsColumn() {
      }

      //============================================================
      public string Group {
         get { return _group; }
         set { _group = value; }
      }

      //============================================================
      public int Index {
         get { return _index; }
         set { _index = value; }
      }

      //============================================================
      public string Id {
         get { return _id; }
         set { _id = value; }
      }

      //============================================================
      public string Name {
         get { return _name; }
         set { _name = value; }
      }

      //============================================================
      public string Label {
         get { return _label; }
         set { _label = value; }
      }

      //============================================================
      public string Type {
         get { return _type; }
         set { _type = value; }
      }

      //============================================================
      public string DataType {
         get { return _dataType; }
         set { _dataType = value; }
      }

      //============================================================
      public string Translate {
         get { return _translate; }
         set { _translate = value; }
      }

      //============================================================
      public string Lenth {
         get { return _lenth; }
         set { _lenth = value; }
      }

      //============================================================
      public string Total {
         get { return _total; }
         set { _total = value; }
      }

      //============================================================
      public string Note {
         get { return _note; }
         set { _note = value; }
      }
   }
}
