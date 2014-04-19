package org.mo.com.lang.generic;

//============================================================
// <T>双向链表。</T>
//============================================================
public class MList<T>
{
   // 链表中元素的数量
   protected int _count;

   // 指向首元素
   protected SListEntry<T> _first;

   // 指向尾元素
   protected SListEntry<T> _last;

   // 指向未使用的元素
   protected SListEntry<T> _unused;

   //============================================================
   // <T>链表构造函数。</T>
   //============================================================   
   public MList(){
   }

   //============================================================
   // <T>内部收集一个节点。</T>
   //
   // @return 节点
   //============================================================
   protected SListEntry<T> entryAlloc(){
      SListEntry<T> entry = null;
      if(_unused != null){
         entry = _unused;
         _unused = _unused.next;
      }else{
         entry = new SListEntry<T>();
      }
      return entry;
   }

   //============================================================
   // <T>内部释放一个节点。</T>
   //
   // @param entry 节点
   //============================================================
   protected void entryFree(SListEntry<T> entry){
      if(entry != null){
         entry.next = _unused;
         _unused = entry;
      }
   }

   //============================================================
   // <T>将链表节点压入首位置。</T>
   //
   // @param entry 节点
   //============================================================
   protected void entryUnshift(SListEntry<T> entry){
      if(entry != null){
         if(_first == null){
            _last = entry;
         }else{
            _first.prior = entry;
         }
         entry.prior = null;
         entry.next = _first;
         _first = entry;
         _count++;
      }
   }

   //============================================================
   // <T>弹出链表的首节点。</T>
   //
   // @return 节点
   //============================================================
   protected SListEntry<T> entryShift(){
      SListEntry<T> entry = _first;
      if(entry != null){
         // 读指针指向下一个位置
         _first = _first.next;
         if(null == _first){
            _last = null;
         }else{
            _first.prior = null;
         }
         // 设置内容
         _count--;
      }
      return entry;
   }

   //============================================================
   // <T>将链表节点压入尾位置。</T>
   //
   // @param entry 节点
   //============================================================
   protected void entryPush(SListEntry<T> entry){
      if(entry != null){
         if(null == _last){
            _first = entry;
         }else{
            _last.next = entry;
         }
         entry.prior = _last;
         entry.next = null;
         _last = entry;
         _count++;
      }
   }

   //============================================================
   // <T>弹出链表的尾节点。</T>
   //
   // @return 节点
   //============================================================
   protected SListEntry<T> entryPop(){
      SListEntry<T> pEntry = _last;
      if(null != pEntry){
         _last = _last.prior;
         if(null == _last){
            _first = null;
         }else{
            _last.next = null;
         }
         // 设置内容
         _count--;
      }
      return pEntry;
   }

   //============================================================
   // <T>在指定链表节点后插入一个新链表节点。</T>
   //
   // @param entry 节点
   //============================================================
   protected void entryInsert(SListEntry<T> prior,
                              SListEntry<T> entry){
      if((prior != null) && (entry != null)){
         SListEntry<T> next = prior.next;
         prior.next = entry;
         entry.prior = prior;
         if(next == null){
            entry.next = null;
            _last = entry;
         }else{
            next.prior = entry;
            entry.next = next;
         }
      }
   }

   //============================================================
   // <T>从链表上删除链表节点。</T>
   //
   // @param entry 节点
   //============================================================
   protected void entryRemove(SListEntry<T> entry){
      if(entry != null){
         SListEntry<T> prior = entry.prior;
         SListEntry<T> next = entry.next;
         // 处理前节点
         if(prior == null){
            _first = next;
         }else{
            prior.next = next;
         }
         // 处理后节点
         if(next == null){
            _last = prior;
         }else{
            next.prior = prior;
         }
         // 设置内容
         _count--;
      }
   }

   //============================================================
   // <T>判断链表是否为空。</T>
   //
   // @return 是否为空
   //============================================================
   public boolean isEmpty(){
      return (_count <= 0);
   }

   //============================================================
   // <T>获得节点总数。</T>
   //
   // @return 总数
   //============================================================
   public int count(){
      return _count;
   }

   //============================================================
   // <T>判断指定元素是否在链表中存在。</T>
   //
   // @param value 数据
   // @return 是否存在
   //============================================================
   public boolean contains(T value){
      if(null != _first){
         SListEntry<T> entry = _first;
         while(entry != null){
            if(entry.value == value){
               return true;
            }
            entry = entry.next;
         }
      }
      return false;
   }

   //============================================================
   // <T>获得数据在链表中的索引位置。</T>
   //
   // @param value 数据
   // @return 索引位置
   //============================================================
   public int indexOf(T value){
      int index = 0;
      if(null != _first){
         SListEntry<T> entry = _first;
         while(entry != null){
            if(entry.value == value){
               return index;
            }
            entry = entry.next;
            index++;
         }
      }
      return -1;
   }

   //============================================================
   // <T>获得首位置的数据。</T>
   //
   // @return 数据
   //============================================================
   public T first(){
      return (_first != null) ? _first.value : null;
   }

   //============================================================
   // <T>获得尾位置的数据。</T>
   //
   // @return 数据
   //============================================================
   public T last(){
      return (_last != null) ? _last.value : null;
   }

   //============================================================
   // <T>将数据压入首位置。</T>
   //
   // @param value 数据内容
   //============================================================
   public void unshift(T value){
      // 收集一个未使用得节点
      SListEntry<T> entry = entryAlloc();
      // 将节点压入尾部
      entry.value = value;
      entryUnshift(entry);
   }

   //============================================================
   // <T>弹出链表的首数据。</T>
   //
   // @return 数据
   //============================================================
   public T shift(){
      SListEntry<T> entry = entryShift();
      T value = entry.value;
      entryFree(entry);
      return value;
   }

   //============================================================
   // <T>将数据压入尾位置。</T>
   //
   // @param value 数据内容
   //============================================================
   public void push(T value){
      // 收集一个未使用得节点
      SListEntry<T> entry = entryAlloc();
      // 将节点压入尾部
      entry.value = value;
      entryPush(entry);
   }

   //============================================================
   // <T>弹出链表的尾数据。</T>
   //
   // @return 数据
   //============================================================
   public T pop(){
      SListEntry<T> entry = entryPop();
      T value = entry.value;
      entryFree(entry);
      return value;
   }

   //============================================================
   // <T>从链表上删除数据。</T>
   //
   // @param value 数据内容
   //============================================================
   public void remove(T value){
      if(_count > 0){
         SListEntry<T> entry = _first;
         while(entry != null){
            SListEntry<T> next = entry.next;
            if(entry.value == value){
               // 移除节点
               entryRemove(entry);
               // 释放节点
               entryFree(entry);
            }
            entry = next;
         }
      }
   }

   //============================================================
   // <T>清空所有数据。</T>
   //============================================================
   public void clear(){
      if(_count > 0){
         // 重用节点
         _last.next = _unused;
         _unused = _first;
      }
      // 重置内容
      _count = 0;
      _first = null;
      _last = null;
   }

   //============================================================
   // <T>释放处理。</T>
   //============================================================
   public void release(){
      // 将所有节点设置为未使用
      if(_count > 0){
         _last.next = _unused;
         _unused = _first;
      }
      // 删除所有节点
      SListEntry<T> entry = _unused;
      while(entry != null){
         SListEntry<T> next = entry.next;
         entry.dispose();
         entry = next;
      }
      // 设置内容
      _count = 0;
      _first = null;
      _last = null;
      _unused = null;
   }
}
