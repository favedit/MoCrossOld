package org.mo.com.net.mail;

import org.mo.com.lang.FObjects;

//============================================================
// <T>邮件附件集合。</T>
//============================================================ 
public class TMailPartCollection
      extends FObjects<TMailPart>
{
   //============================================================
   // <T>构造邮件附件集合。</T>
   //============================================================ 
   public TMailPartCollection(){
      super(TMailPart.class);
   }
}
