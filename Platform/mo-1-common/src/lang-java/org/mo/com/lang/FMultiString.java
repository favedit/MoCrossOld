package org.mo.com.lang;

import org.mo.com.lang.cultrue.RCulture;
import org.mo.com.lang.face.AObjects;
import org.mo.com.lang.generic.MStringPairs;
import org.mo.com.lang.temp.RMultiString;

//============================================================
// <T>多语言字符串。</T>
//============================================================
@AObjects
public class FMultiString
      extends MStringPairs
{
   //============================================================
   // <T>构造多语言字符串。</T>
   //============================================================
   public FMultiString(){
   }

   //============================================================
   // <T>获得当前语言的内容字符串。</T>
   //
   // @return 内容字符串
   //============================================================
   public String get(){
      String language = RCulture.currentLanguage();
      return super.get(language, null);
   }

   //============================================================
   // <T>设置当前语言的内容字符串。</T>
   //
   // @param value 内容字符串
   //============================================================
   public void set(String value){
      String language = RCulture.currentLanguage();
      super.set(language, value);
   }

   //============================================================
   // <T>获得名称和内容的打包字符串。</T>
   //
   // @return 打包字符串
   //============================================================
   @Override
   public String pack(){
      FString pack = new FString(RMultiString.FLAG);
      int n = -1;
      while(++n < _count){
         String name = _names[n];
         if(null != name){
            String value = _values[n];
            if(RString.isEmpty(value)){
               continue;
            }
            String length = Integer.toString(name.length());
            pack.appendInt(length.length());
            pack.append(length);
            pack.append(name);
            if(null == value){
               pack.append("0");
            }else{
               length = Integer.toString(value.length());
               pack.appendInt(length.length());
               pack.append(length);
               pack.append(value);
            }
         }
      }
      return pack.toString();
   }

   //============================================================
   // <T>解包打包字符串。</T>
   //
   // @param pack 打包字符串
   //============================================================
   @Override
   public void unpack(String pack){
      if(!RString.isEmpty(pack)){
         if(pack.startsWith(RMultiString.FLAG)){
            try{
               String name;
               int len, length, n = 0;
               char[] chars = pack.substring(RMultiString.FLAG_LEN).toCharArray();
               int total = chars.length;
               while(n < total){
                  // Name
                  len = chars[n++] - '0';
                  length = Integer.parseInt(new String(chars, n, len));
                  n += len;
                  name = new String(chars, n, length);
                  n += length;
                  // Value
                  len = chars[n++] - '0';
                  if(len > 0){
                     length = Integer.parseInt(new String(chars, n, len));
                     n += len;
                     set(name, new String(chars, n, length));
                     n += length;
                  }else{
                     set(name, null);
                  }
               }
            }catch(Throwable t){
               throw new FFatalError(t, "pack=[{0}]", pack);
            }
         }else{
            super.set(RCulture.currentLanguage(), pack);
         }
      }
   }
}
