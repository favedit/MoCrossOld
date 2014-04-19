package org.mo.com.net.mail;

import javax.mail.internet.InternetAddress;
import org.mo.com.lang.FFatalError;

//============================================================
// <T>邮件地址。</T>
//============================================================ 
public class TMailAddress
{
   // 邮件地址
   private InternetAddress _address;

   //============================================================
   // <T>构造邮件地址。</T>
   //
   // @param address 地址
   //============================================================ 
   public TMailAddress(String address){
      try{
         _address = new InternetAddress(address);
      }catch(Exception e){
         throw new FFatalError(e);
      }
   }

   //============================================================
   // <T>构造邮件地址。</T>
   //
   // @param address 地址
   //============================================================ 
   public TMailAddress(InternetAddress address){
      _address = address;
   }

   //============================================================
   // <T>获得邮件地址。</T>
   //
   // @return 邮件地址
   //============================================================ 
   public InternetAddress address(){
      return _address;
   }
}
