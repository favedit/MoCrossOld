package org.mo.web.core.parser.js;

import org.mo.com.io.FStringFile;
import org.mo.com.lang.FStrings;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.com.text.RStringTokenizer;

public class FJsXmlParser
{

   private static ILogger _logger = RLogger.find(FJsParser.class);

   public FJsXmlClass parse(String fileName,
                            String encoding){
      _logger.debug(this, "parse", "Parse file (file={0},encoding={1})", fileName, encoding);
      // 建立文件和分解内容
      FStringFile file = new FStringFile();
      file.loadFile(fileName, encoding);
      FStrings lines = RStringTokenizer.split(file, "\t\r\n");
      for(int n = 0; n < lines.count(); n++){
         lines.set(n, lines.get(n).trim());
      }
      // 获取文件空间
      // 分解当前类
      FJsXmlClass clazz = new FJsXmlClass();
      if(clazz.parse(lines, 0, lines.count())){
         return clazz;
      }
      return null;
   }
}
