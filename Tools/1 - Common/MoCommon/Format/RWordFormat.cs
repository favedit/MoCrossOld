using System;
using MO.Common.Lang;

namespace MO.Common.Format
{
   public class RWordFormat
   {
      public static string FormatUnderlineToUpper(string source) {
         FString buffer = new FString();
         char[] values = source.ToCharArray();
         int count = values.Length;
         for(int n = 0; n < count; n++) {
            char value = values[n];
            if(value == '_') {
               if(++n < count) {
                  value = Char.ToUpper(values[n]);
               }
            }
            buffer.Append(value);
         }
         return buffer.ToString();
      }
   }
}
