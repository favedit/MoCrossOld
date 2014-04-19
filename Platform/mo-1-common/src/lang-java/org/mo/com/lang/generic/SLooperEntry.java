package org.mo.com.lang.generic;

//============================================================
// <T>循环入口。</T>
//============================================================
public class SLooperEntry<T>
{
   // 前节点
   public SLooperEntry<T> prior;

   // 后节点
   public SLooperEntry<T> next;

   // 内容
   T value;
}
