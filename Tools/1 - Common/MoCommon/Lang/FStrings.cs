namespace MO.Common.Lang
{
   //============================================================
   // <T>字符串集合。</T>
   //============================================================
   public class FStrings : FObjects<string>
   {
      //============================================================
      // <T>分割字符串。</T>
      //
      // @param value 字符串
      // @param split 分割字符
      //============================================================
      public void Split(string value, char split) {
         if(null != value) {
            Append(value.Split(split));
         }
      }

      //============================================================
      // <T>分割字符串。</T>
      //
      // @param value 字符串
      // @param split 分割字符
      // @param limit 分割限制
      //============================================================
      public void Split(string value, char split, int limit) {
         int length = value.Length;
         char[] memory = value.ToCharArray();
         int blockSize = 1;
         for(int n = 0; n < length; n++) {
            if(memory[n] == split) {
               blockSize++;
               if(blockSize >= limit) {
                  break;
               }
            }
         }
         if(blockSize > 1) {
            blockSize = 0;
            int pos = 0;
            for(int n = 0; n < length; n++) {
               if(memory[n] == split) {
                  if(blockSize >= limit - 1) {
                     break;
                  }
                  Push(new string(memory, pos, n - pos));
                  pos = n + 1;
               }
            }
            if(pos < length) {
               Push(new string(memory, pos, length - pos));
            }
         } else {
            Push(value);
         }
      }
   }
}
