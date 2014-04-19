package org.mo.com.io;

import org.mo.com.lang.FObjects;

//============================================================
// <T>过滤器。</T>
//============================================================
public class FFilter
      extends FObjects<FFilterItem>
{
   //============================================================
   // <T>过滤器。</T>
   //============================================================
   public FFilter(){
      super(FFilterItem.class);
   }

   //============================================================
   // <T>注册一个过滤项目。</T>
   //
   // @param modeCd 模式
   // @param value 内容
   //============================================================
   public void register(EFilterMode modeCd,
                        String value){
      FFilterItem item = new FFilterItem();
      item.setModeCd(modeCd);
      item.setValue(value);
      push(item);
   }
}
