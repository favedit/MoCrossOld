package org.mo.com.text.parser;

import org.mo.com.io.FStringFile;
import org.mo.com.lang.FObject;
import org.mo.com.lang.FString;

//============================================================
// <T>文本解析器。</T>
//============================================================
public class FTextParser
      extends FObject
{
   // 文本环境
   protected FTextContext _context = new FTextContext();

   protected FTextToken _token = new FTextToken();

   //============================================================
   // <T>构造文本解析器。</T>
   //============================================================
   public FTextParser(){
   }

   //============================================================
   // <T>获得文本环境。</T>
   //
   // @return 文本环境
   //============================================================
   public FTextContext context(){
      return _context;
   }

   public void setContext(FTextContext context){
      _context = context;
   }

   public FTextToken token(){
      return _token;
   }

   //============================================================
   // <T>解析文本内容。</T>
   //============================================================
   public void parse(String source){
      // 获得行集合
      FTextLines lines = _context.lines();
      // 格式化代码，分隔成行
      char[] chars = source.toCharArray();
      int n = -1;
      int end = chars.length;
      FString code = new FString();
      while(++n < end){
         char value = chars[n];
         if(_context.isLine(value)){
            if(!code.isEmpty()){
               FTextLine line = new FTextLine();
               line.load(lines.count(), code.toString());
               lines.push(line);
               code.clear();
            }
         }else{
            code.append(value);
         }
      }
      if(!code.isEmpty()){
         FTextLine line = new FTextLine();
         line.load(lines.count(), code.toString());
         lines.push(line);
         code.clear();
      }
      // 格式化代码，分隔成树
      _token.setContext(_context);
      _token.parse(source);
   }

   //   //============================================================
   //   // <T>解析文本内容。</T>
   //   //============================================================
   //   public void parseSelf(){
   //      // 格式化代码，分隔成树
   //      _token.setContext(_context);
   //      _token.parseSelf();
   //   }
   public static void main(String[] args){
      // 打开文件
      String fileName = "D:\\WP-Client3d\\mo-1-common\\1-common\\mo\\cm\\lang\\FObject.as";
      FStringFile file = new FStringFile(fileName);
      FTextParser parser = new FTextParser();
      parser.parse(file.toString());
   }
}
