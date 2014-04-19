package org.mo.com.io;

import java.io.File;
import org.mo.com.lang.FStrings;
import org.mo.com.lang.RString;

//============================================================
// <T>分行字符串文件。</T>
//============================================================
public class FLinesFile
      extends FStringFile
{
   // 行集合
   private FStrings _lines;

   //============================================================
   // <T>构造分行字符串文件。</T>
   //============================================================
   public FLinesFile(){
   }

   //============================================================
   // <T>构造分行字符串文件。</T>
   //
   // @param file 文件
   //============================================================
   public FLinesFile(File file){
      super(file);
   }

   //============================================================
   // <T>构造分行字符串文件。</T>
   //
   // @param file 文件
   // @param charset 编码
   //============================================================
   public FLinesFile(File file,
                     String charset){
      super(file, charset);
   }

   //============================================================
   // <T>构造分行字符串文件。</T>
   //
   // @param fileName 文件名称
   //============================================================
   public FLinesFile(String fileName){
      super(fileName);
   }

   //============================================================
   // <T>构造分行字符串文件。</T>
   //
   // @param fileName 文件名称
   // @param read 是否读取
   //============================================================
   public FLinesFile(String fileName,
                     boolean read){
      super(fileName, read);
   }

   //============================================================
   // <T>构造分行字符串文件。</T>
   //
   // @param fileName 文件名称
   // @param charset 编码
   //============================================================
   public FLinesFile(String fileName,
                     String charset){
      super(fileName, charset);
   }

   //============================================================
   // <T>构造分行字符串文件。</T>
   //
   // @param fileName 文件名称
   // @param charset 编码
   // @param read 是否读取
   //============================================================
   public FLinesFile(String fileName,
                     String charset,
                     boolean read){
      super(fileName, charset, read);
   }

   //============================================================
   // <T>获得行数。</T>
   //
   // @return 行数
   //============================================================
   public int count(){
      return _lines.count();
   }

   //============================================================
   // <T>根据索引获得行内容。</T>
   //
   // @param index 索引 
   // @return 行内容
   //============================================================
   public String line(int index){
      return _lines.get(index);
   }

   //============================================================
   // <T>根据行集合。</T>
   //
   // @return 行集合
   //============================================================
   public FStrings lines(){
      if(_lines == null){
         _lines = new FStrings();
      }
      return _lines;
   }

   //============================================================
   // <T>加载文件内容。</T>
   //
   // @param file 文件
   // @param charset 编码
   //============================================================
   @Override
   public void loadFile(File file,
                        String charset){
      super.loadFile(file, charset);
      String[] lines = RString.split(toString(), '\n');
      lines().append(lines);
   }
}
