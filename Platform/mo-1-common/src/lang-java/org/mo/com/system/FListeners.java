package org.mo.com.system;

import org.mo.com.lang.FObjects;

//============================================================
// <T>监听器集合。</T>
//============================================================
public class FListeners
      extends FObjects<IListener>
{
   //============================================================
   // <T>构造监听器集合。</T>
   //============================================================
   public FListeners(){
      super(IListener.class);
   }

   //============================================================
   // <T>执行处理。</T>
   //
   // @param sender 发送者
   // @param command 命令
   // @param params 参数
   // @return 执行结果
   //============================================================
   public boolean process(Object sender,
                          int command,
                          Object params){
      if(_count > 0){
         for(int n = 0; n < _count; n++){
            if(!_items[n].process(sender, command, params)){
               return false;
            }
         }
      }
      return true;
   }
}
