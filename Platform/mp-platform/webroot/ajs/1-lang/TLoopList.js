//===========================================================
// 用链表实现循环单链表功能的工具类
//
// @tool
// @author maochunyang
// @version 1.0.1
//===========================================================
function TLoopList(){
   var o = this;
   // Property
   o.count      = 0;
   o.size       = 0;
   o.start      = new Object();
   // Method
   o.setSize    = TLoopList_setSize;
   o.find       = TLoopList_find;
   o.contains   = TLoopList_contains;
   o.indexOf    = TLoopList_indexOf;
   o.get        = TLoopList_get;
   o.set        = TLoopList_set;
   o.push       = TLoopList_push;
   o.sync       = TLoopList_sync;
   o.remove     = TLoopList_remove;
   o.removeItem = TLoopList_removeItem;
   o.clear      = TLoopList_clear;
   o.dump       = TLoopList_dump;
   return o;
}

//===========================================================
// 构建LoopList，没有存储任何值
//
// @method
// @param size:size:Integer 循环链表的节点数目
//===========================================================
function TLoopList_setSize(size){
   var item = this.start;
   for(var n=0; n<size-1; n++){
      if(!item.next){
         item.next = new Object();
         item.value = null;
      }
      item = item.next;
   }
   item.next = this.start;
   this.size = size;
}

//===========================================================
// 查找指定索引的节点
//
// @method
// @param idx:index:Integer 要查找的索引
// @return Object 查找到的节点，没找到返回null
//===========================================================
function TLoopList_find(idx){
   var item = this.start;
   if(idx >=0 && idx<this.count){
      for(var n=0; n<this.count; n++){
         if(n == idx){
            return item;
         }
         item = item.next;
      }
   }
   return null;
}

//===========================================================
// 查找指定索引的节点
//
// @method
// @param idx:index:Integer 要查找的索引
// @return Boolean 返回Boolean类型
//===========================================================
function TLoopList_isEmpty(){
   return (this.count == 0);
}

//===========================================================
// 链表中是否包含指定的对象
//
// @method
// @param obj:object:Object 指定的对象
// @return Boolean 返回Boolean类型
//===========================================================
function TLoopList_contains(obj){
   return (this.indexOf(obj) != -1);
}

//===========================================================
// 查找对象在链表中的索引位置
//
// @method
// @param obj:object:Object 要查找的索引
// @return Integer 索引位置，没找到返回-1
//===========================================================
function TLoopList_indexOf(obj){
   var item = this.start;
   if(obj != null){
      for(var n=0; n<this.count; n++){
         if(item.value == obj){
            return n;
         }
         item = item.next;
      }
   }
   return -1;
}

//===========================================================
// 取得指定索引的节点值
//
// @method
// @param idx:index:Integer 要查找的索引
// @return Object 索引的节点对象的值
//===========================================================
function TLoopList_get(idx){
   var item = this.find(idx);
   return (item != null) ? item.value : null;
}

//===========================================================
// 设置索引上的对象
//
// @method
// @param idx:index:Integer 要查找的索引
//===========================================================
function TLoopList_set(idx, obj){
   var item = this.find(idx);
   if(item != null){
      item.value = obj;
   }
}

//===========================================================
// 把这个对象压放到链表的最后
//
// @method
// @param obj:object:Object 压入链表的对象
//===========================================================
function TLoopList_push(obj){
   if(this.count + 1 > this.size){
      this.start.value = obj;
      this.start = this.start.next;
   }else{
      this.set(this.count++, obj);
   }
}

//===========================================================
// 把对象放到链表里。存在就返回存储位置
//
// @method
// @param idx:index:Integer 要查找的索引
// @return Object 索引的节点对象的值
//===========================================================
function TLoopList_sync(obj){
   var idx = this.indexOf(obj);
   return (idx == -1) ? this.push(obj) : idx;
}

//===========================================================
// 移出指定索引的对象
//
// @method
// @param idx:index:Integer 要查找的索引
// @return Object 索引的节点对象的值
//===========================================================
function TLoopList_remove(idx){
   var obj = null;
   var item = this.find(idx);
   if(item != null){
      obj = item.value;
      for(var n=idx; n<this.count; n++){
         item.value = item.next.value;
      }
   }
   return obj;
}

//===========================================================
// 移除指定的对象
//
// @method
// @param obj:object:Object 压入链表的对象
// @return Object 索引的节点对象的值
//===========================================================
function TLoopList_removeItem(obj){
   var idx = this.indexOf(obj);
   if(idx != -1){
      this.remove(idx);
   }
}

//===========================================================
// LoopList计数器清零，清空链表中储存的所有数据
//
// @method
//===========================================================
function TLoopList_clear(){
   this.count = 0;
}

//===========================================================
//
//===========================================================
function TLoopList_dump(){
   var dump = new FString();
   dump.append(IClass.name(this), ': ', this.count, '/', this.size);
   var item = this.start;
   for(var n=0; n<this.count; n++){
      dump.append(' [', item.value, ']');
      item = item.next;
   }
   return dump;
}
