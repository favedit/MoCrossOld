namespace MO.Common.Lang
{
   //============================================================
   // <T>字符串列表工具类。</T>
   //
   // @history 110626 MAOCY 创建
   //============================================================
   public class RStrings
   {
      //============================================================
      // <T>删除字符串数组两侧的空字符串。</T>
      //
      // @param lines 字符串数组
      // @param blankLine 是否保持空行
      // @return 字符串数组
      //============================================================
      public static string[] Trim(string[] lines, bool blankLine) {
         if (lines != null) {
            FStrings result = new FStrings();
            for (int n = 0; n < lines.Length; n++) {
               string line = lines[n].Trim();
               if (blankLine) {
                  result.Push(line);
               } else if (!blankLine && !RString.IsEmpty(line)) {
                  result.Push(line);
               }
            }
            return result.ToArray();
         }
         return null;
      }
   }
}
