package org.mo.com.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.mo.com.lang.FFatalError;
import org.mo.com.lang.FString;
import org.mo.com.lang.RString;
import org.mo.com.lang.cultrue.REncoding;

//============================================================
// <T>字符串文件。</T>
//============================================================
public class FStringFile
      extends FString
{
   // 文件名称
   protected String _fileName;

   // 编码名称
   protected String _charset;

   //============================================================
   // <T>构造字符串文件。</T>
   //============================================================
   public FStringFile(){
   }

   //============================================================
   // <T>构造字符串文件。</T>
   //
   // @param fileName 文件名称
   //============================================================
   public FStringFile(String fileName){
      _fileName = fileName;
      loadFile(fileName);
   }

   //============================================================
   // <T>构造字符串文件。</T>
   //
   // @param fileName 文件名称
   // @param loaded 加载
   //============================================================
   public FStringFile(String fileName,
                      boolean loaded){
      _fileName = fileName;
      if(loaded){
         loadFile(fileName);
      }
   }

   //============================================================
   // <T>构造字符串文件。</T>
   //
   // @param fileName 文件名称
   // @param charset 编码名称
   //============================================================
   public FStringFile(String fileName,
                      String charset){
      loadFile(fileName, charset);
   }

   //============================================================
   // <T>构造字符串文件。</T>
   //
   // @param fileName 文件名称
   // @param charset 编码名称
   // @param loaded 加载
   //============================================================
   public FStringFile(String fileName,
                      String charset,
                      boolean loaded){
      _fileName = fileName;
      _charset = charset;
      if(loaded){
         loadFile(fileName, charset);
      }
   }

   //============================================================
   // <T>构造字符串文件。</T>
   //
   // @param file 文件对象
   //============================================================
   public FStringFile(File file){
      loadFile(file);
   }

   //============================================================
   // <T>构造字符串文件。</T>
   //
   // @param file 文件对象
   // @param charset 编码名称
   //============================================================
   public FStringFile(File file,
                      String charset){
      loadFile(file, charset);
   }

   //============================================================
   // <T>获得文件名称。</T>
   //
   // @return 文件名称
   //============================================================
   public String fileName(){
      return _fileName;
   }

   //============================================================
   // <T>设置文件名称。</T>
   //
   // @param fileName 文件名称
   //============================================================
   public void setFileName(String fileName){
      _fileName = fileName;
   }

   //============================================================
   // <T>加载文件内容。</T>
   //
   // @param fileName 文件名称
   //============================================================
   public void loadFile(String filename){
      loadFile(new File(filename), null);
   }

   //============================================================
   // <T>加载文件内容。</T>
   //
   // @param fileName 文件名称
   // @param charset 编码名称
   //============================================================
   public void loadFile(String fileName,
                        String charset){
      loadFile(new File(fileName), charset);
   }

   //============================================================
   // <T>加载文件内容。</T>
   //
   // @param file 文件对象
   //============================================================
   public void loadFile(File file){
      loadFile(file, null);
   }

   //============================================================
   // <T>加载文件内容。</T>
   //
   // @param file 文件对象
   // @param charset 编码名称
   //============================================================
   public void loadFile(File file,
                        String charset){
      FileInputStream fileInputStream = null;
      try{
         int length = (int)file.length();
         if(length > 0){
            // 读取文件
            byte[] buffer = new byte[length];
            fileInputStream = new FileInputStream(file);
            fileInputStream.read(buffer);
            _fileName = file.getAbsolutePath();
            _charset = charset;
            // 追加内容
            char[] chars = null;
            if(null == charset){
               chars = new String(buffer).toCharArray();
            }else{
               chars = REncoding.convertString(buffer, charset).toCharArray();
            }
            append(chars);
         }
      }catch(Exception e){
         throw new FFatalError(e, "Load file failure. (file_name={1}, charset={2})", file.getAbsolutePath(), charset);
      }finally{
         if(null != fileInputStream){
            try{
               fileInputStream.close();
            }catch(Exception e){
               throw new FFatalError(e, "Close file failure. (file_name={1}, charset={2})", file.getAbsolutePath(), charset);
            }
         }
      }
   }

   //============================================================
   // <T>重新加载文件内容。</T>
   //============================================================
   public boolean reload(){
      File file = new File(_fileName);
      if(file.exists()){
         loadFile(new File(_fileName), null);
         return true;
      }
      return false;
   }

   //============================================================
   // <T>保存文件内容。</T>
   //
   // @param fileName 文件名称
   //============================================================
   public void saveFile(String filename){
      saveFile(new File(filename), null);
   }

   //============================================================
   // <T>保存文件内容。</T>
   //
   // @param fileName 文件名称
   // @param charset 编码名称
   //============================================================
   public void saveFile(String fileName,
                        String charset){
      saveFile(new File(fileName), charset);
   }

   //============================================================
   // <T>保存文件内容。</T>
   //
   // @param file 文件对象
   //============================================================
   public void saveFile(File file){
      saveFile(file, null);
   }

   //============================================================
   // <T>保存文件内容。</T>
   //
   // @param file 文件对象
   // @param charset 编码名称
   //============================================================
   public void saveFile(File file,
                        String charset){
      FileOutputStream fileOutputStream = null;
      // 建立文件目录
      File parent = null;
      try{
         parent = file.getParentFile();
         if(!parent.isDirectory()){
            parent.mkdirs();
         }
      }catch(Exception e){
         throw new FFatalError(e, "Make file parent path failure. (file_name={1}, parent={2})", file.getAbsolutePath(), parent);
      }
      // 设置编码
      try{
         byte[] data = null;
         if(null != _memory){
            String source = new String(_memory, 0, _length);
            if(RString.isEmpty(charset)){
               data = source.getBytes();
            }else{
               data = source.getBytes(charset);
            }
         }
         // 存储文件
         fileOutputStream = new FileOutputStream(file);
         if(null != data){
            fileOutputStream.write(data);
         }
      }catch(Exception e){
         throw new FFatalError(e, "Save file failure. (file_name={1}, charset={2})", file.getAbsolutePath(), charset);
      }finally{
         if(null != fileOutputStream){
            try{
               fileOutputStream.close();
            }catch(Exception e){
               throw new FFatalError(e, "Close file failure. (file_name={1}, charset={2})", file.getAbsolutePath(), charset);
            }
         }
      }
   }

   //============================================================
   // <T>存储文件内容。</T>
   //============================================================
   public void store(){
      saveFile(new File(_fileName), null);
   }

   //============================================================
   // <T>存储文件内容。</T>
   //
   // @param charset 编码名称
   //============================================================
   public void store(String charset){
      saveFile(new File(_fileName), charset);
   }
}
