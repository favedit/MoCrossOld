package org.mo.com.lang.generic;

//============================================================
// <T>列表入口。</T>
//============================================================
public class SListEntry<T>
{
   // 前节点
   public SListEntry<T> prior;

   // 后节点
   public SListEntry<T> next;

   // 内容
   public T value;

   //============================================================
   // <T>释放处理。</T>
   //============================================================
   public void dispose(){
      prior = null;
      next = null;
      value = null;
   }
}
