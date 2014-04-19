using System;
using MO.Common.Lang;

namespace MO.Common.Content
{
   public class FCsvLine : IDump
   {
      private FStrings _values = new FStrings();

      internal FCsvHeads _heads;

      internal FCsvLineType _type;

      public FCsvLine() {
         _type = FCsvLineType.Data;
      }

      public FCsvLine(FCsvLineType type) {
         _type = type;
      }

      public string this[int index] {
         get { return Get(index); }
         set { Set(index, value); }
      }

      public string this[string name] {
         get { return Get(name); }
         set { Set(name, value); }
      }

      public string First {
         get { return _values.First; }
      }

      public string Last {
         get { return _values.Last; }
      }

      public FCsvLineType Type {
         get { return _type; }
      }

      public bool IsEmpty {
         get {
            if(_values.Count == 0) {
               return true;
            }
            if(_values.Count == 1 && RString.IsEmpty(_values[0])) {
               return true;
            }
            return false;
         }
      }

      public int Count {
         get { return _values.Count; }
      }

      public FCsvHeads Heads {
         get { return _heads; }
      }

      public FStrings Values {
         get { return _values; }
      }

      public bool Contains(string name) {
         return _heads.Contains(name);
      }

      public string Get(int index) {
         return _values.Get(index);
      }

      public void Set(int index, string value) {
         _values.Set(index, value);
      }

      public string Get(string name) {
         if(_heads != null) {
            /*int index = _heads.Find(name);
            if(index != -1) {
               return _values.Get(index);
            }*/
         }
         return null;
      }

      public int Set(string name, string value) {
         int index = -1;
         /*if(_heads != null) {
            index = _heads.Find(name);
            if(index != -1) {
               _values.Set(index, value);
            }
         }*/
         return index;
      }

      public void Append(string value) {
         if(value != null) {
            _values.Push(value);
         }
      }

      public void Append(int value) {
         _values.Push(value.ToString());
      }

      public int Find(string name) {
         //return _heads.Find(name);
         return 0;
      }

      public string KeyString {
         get {
            FString result = new FString();
            if(_heads != null) {
               int count = _heads.Count;
               for(int n = 0; n < count; n++) {
                  if(_heads[n].Key) {
                     if(!result.IsEmpty) {
                        result.Append(',');
                     }
                     result.Append(_values[n]);
                  }
               }
            }
            return result.ToString();
         }
      }

      public void Clear() {
         _values.Clear();
      }

      public string GetValuesString(char split, params string[] names) {
         FString result = new FString();
         /*if(_heads != null && names != null) {
            int count = names.Length;
            for(int n = 0; n < count; n++) {
               int find = _heads.Find(names[n]);
               if(find != -1) {
                  if(!result.IsEmpty()) {
                     result.Append(split);
                  }
                  result.Append(_values[find]);
               }
            }
         }*/
         return result.ToString();
      }

      public override string ToString() {
         FString line = new FString();
         int count = _values.Count;
         for(int n = 0; n < count; n++) {
            if(n > 0) {
               line.Append(',');
            }
            line.Append(_values[n]);
         }
         return line.ToString();
      }

      public void ToString(FString result) {
         int count = _values.Count;
         for(int n = 0; n < count; n++) {
            if(n > 0) {
               result.Append(',');
            }
            object value = _values[n];
            if(value != null) {
               RCsv.FormatField(result, value.ToString(), false);
            }
         }
      }

      public void ToString(FString result, char split, bool quot) {
         int count = _values.Count;
         for(int n = 0; n < count; n++) {
            if(n > 0) {
               result.Append(split);
            }
            object value = _values[n];
            if(value != null) {
               RCsv.FormatField(result, value.ToString(), quot);
            }
         }
      }

      #region IDump members

      public FDumpInfo Dump() {
         return Dump(new FDumpInfo(this));
      }

      public virtual FDumpInfo Dump(FDumpInfo info) {
         RDump.StartDump(info);
         info.Append(" Count=", Count, " {");
         int count = _values.Count;
         for(int n = 0; n < count; n++) {
            if(n > 0) {
               info.Append(",");
            }
            object value = _values[n];
            if(value != null) {
               info.Append(value.ToString().Length, '[', value.ToString().Replace("\n", @"\n"), ']');
            } else {
               info.Append("0[]");
            }
         }
         info.Append("}");
         return info;
      }

      #endregion
   }
}
