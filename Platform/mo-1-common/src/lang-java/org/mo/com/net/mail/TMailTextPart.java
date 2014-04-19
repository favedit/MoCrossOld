package org.mo.com.net.mail;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.activation.DataSource;
import org.mo.com.lang.FFatalError;

//============================================================
// <T>邮件文本附件。</T>
//============================================================ 
public class TMailTextPart
      extends TMailPart
{
   //============================================================
   // <T>文本数据。</T>
   //============================================================ 
   class FMailSource
         implements
            DataSource
   {
      // 数据内容
      protected byte[] _bytes;

      // 内容类型
      protected String _contentType;

      //============================================================
      // <T>构造文本数据。</T>
      //
      // @param bytes 数据内容
      // @param contentType 内容类型
      //============================================================ 
      public FMailSource(byte[] bytes,
                         String contentType){
         _bytes = bytes;
         _contentType = contentType;
      }

      //============================================================
      // <T>获得名称。</T>
      //
      // @return 名称
      //============================================================ 
      public String getName(){
         return "pipi";
      }

      //============================================================
      // <T>获得内容类型。</T>
      //
      // @return 内容类型
      //============================================================ 
      public String getContentType(){
         return _contentType;
      }

      //============================================================
      // <T>获得输入流。</T>
      //
      // @return 输入流
      //============================================================ 
      public InputStream getInputStream() throws java.io.IOException{
         return new ByteArrayInputStream(_bytes);
      }

      //============================================================
      // <T>获得输出流。</T>
      //
      // @return 输出流
      //============================================================ 
      public OutputStream getOutputStream() throws IOException{
         throw new IOException("Not support this method!");
      }
   }

   //============================================================
   // <T>构造邮件文本附件。</T>
   //============================================================ 
   public TMailTextPart(){
   }

   //============================================================
   // <T>构造邮件文本附件。</T>
   //
   // @param text 文本内容
   // @param mimeType 内容类型
   //============================================================ 
   public TMailTextPart(String text,
                        String mimeType){
      _mimeType = mimeType;
      _source = new FMailSource(text.getBytes(), mimeType);
   }

   //============================================================
   // <T>构造邮件文本附件。</T>
   //
   // @param text 文本内容
   // @param encode 编码方式
   // @param mimeType 内容类型
   //============================================================ 
   public TMailTextPart(String text,
                        String encode,
                        String mimeType){
      _mimeType = mimeType;
      try{
         _source = new FMailSource(text.getBytes(encode), mimeType);
      }catch(Exception e){
         throw new FFatalError(e);
      }
   }
}
