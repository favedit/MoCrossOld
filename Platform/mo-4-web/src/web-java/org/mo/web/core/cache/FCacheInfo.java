package org.mo.web.core.cache;

import org.mo.com.lang.FObject;
import org.mo.com.lang.FString;

//============================================================
// <T>缓冲信息。</T>
//============================================================
public class FCacheInfo
      extends FObject
{
   // 缓冲字符串
   protected FString _buffer;

   //============================================================
   // <T>获得缓冲字符串。</T>
   //
   // @return 缓冲字符串
   //============================================================
   public FString buffer(){
      return _buffer;
   }

   //============================================================
   // <T>设置缓冲字符串。</T>
   //
   // @param buffer 缓冲字符串
   //============================================================
   public void setBuffer(FString buffer){
      _buffer = buffer;
   }
}
