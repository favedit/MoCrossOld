package org.mo.com.net;

//============================================================
// <T>内容类型。</T>
//============================================================
public enum EMime{
   // 图片BMP
   Bmp("bmp", "image/bmp"),
   // 图片GIF
   Gif("gif", "image/gif"),
   // 图片JPG
   Jpg("jpg", "image/jpeg"),
   // 图片PNG
   Png("png", "image/png"),
   // 二进制数据
   Bin("bin", "application/octet-stream"),
   // 可执行文件
   Exe("exe", "application/octet-stream");
   // 类型名称
   protected String _type;

   // 内容类型
   protected String _mime;

   //============================================================
   // <T>构造内容类型。</T>
   //
   // @param type 类型名称
   // @param mime 内容类型
   //============================================================
   private EMime(String type,
                 String mime){
      _type = type;
      _mime = mime;
   }

   //============================================================
   // <T>获得类型名称。</T>
   //
   // @return 类型名称
   //============================================================
   public String type(){
      return _type;
   }

   //============================================================
   // <T>获得内容类型。</T>
   //
   // @return 内容类型
   //============================================================
   public String mime(){
      return _mime;
   }
}
