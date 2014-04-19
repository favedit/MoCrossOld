package org.mo.com.text;

import java.util.StringTokenizer;
import org.mo.com.lang.FString;
import org.mo.com.lang.FStrings;

public class RStringTokenizer
{
   public static FStrings split(FString source,
                                String delimiter){
      return split(source.toString(), delimiter);
   }

   public static FStrings split(String source,
                                String delimiter){
      StringTokenizer tokenizer = new StringTokenizer(source, delimiter);
      FStrings lines = new FStrings();
      // 获得每个token
      while(tokenizer.hasMoreTokens()){
         lines.push(tokenizer.nextToken());
      }
      return lines;
   }
}
