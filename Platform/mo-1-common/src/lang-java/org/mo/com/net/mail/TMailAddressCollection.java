package org.mo.com.net.mail;

import javax.mail.internet.InternetAddress;
import org.mo.com.lang.FObjects;
import org.mo.com.lang.RString;

//============================================================
// <T>邮件地址集合。</T>
//============================================================ 
public class TMailAddressCollection
      extends FObjects<TMailAddress>
{
   //============================================================
   // <T>构造邮件地址集合。</T>
   //============================================================ 
   public TMailAddressCollection(){
      super(TMailAddress.class);
   }

   //============================================================
   // <T>构造邮件地址集合。</T>
   //
   // @param address 地址
   //============================================================ 
   public TMailAddressCollection(String address){
      super(TMailAddress.class);
      add(address);
   }

   //============================================================
   // <T>增加一个邮件地址。</T>
   //
   // @param address 地址
   //============================================================ 
   public void add(String address){
      if(!RString.isEmpty(address)){
         String[] items = RString.split(address, ';');
         for(String item : items){
            if(null != item){
               item = item.trim();
               if(!item.isEmpty()){
                  super.push(new TMailAddress(item));
               }
            }
         }
      }
   }

   //============================================================
   // <T>获得邮件地址集合。</T>
   //
   // @return 邮件地址集合
   //============================================================ 
   public InternetAddress[] toAddresses(){
      InternetAddress[] addresses = new InternetAddress[_count];
      for(int n = 0; n < _count; n++){
         addresses[n] = _items[n].address();
      }
      return addresses;
   }
}
