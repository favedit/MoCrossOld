package org.mo.com.lang.generic;

import org.mo.com.lang.FFatalError;
import org.mo.com.lang.FObject;

//============================================================
// <T>循环链表。</T>
//============================================================
public class MLooper<T>
      extends FObject
{
   // 链表元素的总个数
   public int count;

   // 当前元素
   public SLooperEntry<T> entryCurrent;

   // 移除后的元素
   public SLooperEntry<T> entryUnused;

   // 刻点个数
   public int recordCount = -1;

   //============================================================
   // <T>构造循环链表。</T>
   //============================================================
   public MLooper(){
   }

   //============================================================
   // <T>插入当前列表</T>
   //
   // @params p:entry 值
   //============================================================
   protected void innerPush(SLooperEntry<T> p){
      if(null != entryCurrent){
         SLooperEntry<T> l = entryCurrent.prior;
         p.prior = l;
         l.next = p;
         p.next = entryCurrent;
         entryCurrent.prior = p;
      }else{
         p.next = p;
         p.prior = p;
         entryCurrent = p;
      }
   }

   //============================================================
   // <T>当前链表是否为空</T>
   //
   // @return 当前链表是否为空
   //============================================================
   public boolean isEmpty(){
      return (0 == count);
   }

   //============================================================
   // <T>判断是否包含指定名称的值。</T>
   //
   // @param p:name 名称
   // @return 是否包含
   //============================================================
   public boolean contains(T p){
      if(null != entryCurrent){
         SLooperEntry<T> e = entryCurrent;
         for(int n = 0; n < count; n++){
            if(e.value == p){
               return true;
            }
            e = e.next;
         }
      }
      return false;
   }

   //============================================================
   // <T>返回链表的当前位置元素的值。</T>
   //
   // @return 当前元素所对应值
   //============================================================
   public T current(){
      return (null != entryCurrent) ? entryCurrent.value : null;
   }

   //============================================================
   // <T>获得链表的下一个元素。</T>
   //
   // @return 下一个链表元素
   //============================================================
   public T next(){
      // 移动当前点
      if(null != entryCurrent){
         entryCurrent = entryCurrent.next;
      }
      // 检查刻录点（当只有一个元素时，刻录点无效）
      if(recordCount > 0){
         recordCount--;
      }else if(recordCount == 0){
         return null;
      }
      // 返回内容
      return null != entryCurrent ? entryCurrent.value : null;
   }

   //============================================================
   // <T>插入尾元素</T>
   //
   // @params p:value 值
   //============================================================
   public void push(T p){
      if(null != p){
         SLooperEntry<T> e = entryUnused;
         if(null != e){
            entryUnused = entryUnused.next;
         }else{
            e = new SLooperEntry<T>();
         }
         e.value = p;
         innerPush(e);
         count++;
      }
   }

   //============================================================
   // <T>插入唯一元素</T>
   //
   // @params p:value 值
   //============================================================
   public void pushUnique(T p){
      if(null != p){
         if(contains(p)){
            throw new FFatalError("Value is already exists in looper.");
         }
         push(p);
      }
   }

   //============================================================
   // <T>移除一个元素。</T>
   //
   // @param p:value要移除的元素
   //============================================================
   public T erase(T p){
      if(count > 0){
         if((p == entryCurrent.value)){
            return remove();
         }
         SLooperEntry<T> e = entryCurrent.next;
         while(e != entryCurrent){
            if(e.value == p){
               e.prior.next = e.next;
               e.next.prior = e.prior;
               count--;
               entryCurrent = 0 != count ? e.next : null;
               e.next = entryUnused;
               entryUnused = e;
               T v = e.value;
               e.value = null;
               return v;
            }
            e = e.next;
         }
      }
      return null;
   }

   //============================================================
   // <T>移除当前的元素，并返回该元素的值</T>
   //
   // @return 移除内容
   //============================================================
   public T remove(){
      SLooperEntry<T> e = entryCurrent;
      if(null != e){
         e.prior.next = e.next;
         e.next.prior = e.prior;
         count--;
         entryCurrent = 0 != count ? e.next : null;
         e.next = entryUnused;
         entryUnused = e;
         T v = e.value;
         e.value = null;
         return v;
      }
      return null;
   }

   //============================================================
   // <T>记录当前刻录点。</T>
   //============================================================
   public void record(){
      recordCount = count;
   }

   //============================================================
   // <T>消除当前刻录点。</T>
   //============================================================
   public void unrecord(){
      recordCount = -1;
   }

   //============================================================
   // <T>清除循环链表所有元素。</T>
   //============================================================
   public void clear(){
      if(null != entryCurrent){
         entryCurrent.prior.next = null;
         entryCurrent.prior = entryUnused;
         entryUnused = entryCurrent;
         entryCurrent = null;
      }
      count = 0;
   }
   //============================================================
   // <T>对元素进行权重排序，将当前权重最高的元素设置为当前元素。</T>
   //
   // @param p:function 比较函数
   //============================================================
   // public T nextSort(p:Function){
   // if(null != entryCurrent){
   // int m = 0;
   // SListEntry<T> e = entryCurrent = entryCurrent.next;
   // for(int n = count - 1; n >= 0; n--){
   // int f = p(e.value);
   // if(f > m){
   // entryCurrent = e;
   // m = f;
   // }
   // e = e.next;
   // }
   // return entryCurrent.value;
   // }
   // return null;
   // }
}
