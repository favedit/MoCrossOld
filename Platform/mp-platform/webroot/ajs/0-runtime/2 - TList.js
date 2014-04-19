// =========================================================
// <T>列表的操作类。</T>
//
// @tool
// @author maocy
// @version 1.0.1
// =========================================================
function TList(o){
   if(!o){o=this;}
   // Attribute
   o.count      = 0;
   o.memory     = new Array();
   // Method
   o.isEmpty    = TList_isEmpty;
   o.contains   = TList_contains;
   o.indexOf    = TList_indexOf;
   o.first      = TList_first;
   o.last       = TList_last;
   o.get        = TList_get;
   o.set        = TList_set;
   o.insert     = TList_insert;
   o.push       = TList_push;
   o.pushUnique = TList_pushUnique;
   o.pop        = TList_pop;
   o.swap       = TList_swap;
   o.sort       = TList_sort;
   o.remove     = TList_remove;
   o.extract    = TList_extract;
   o.compress   = TList_compress;
   o.append     = TList_append;
   o.clear      = TList_clear;
   o.release    = TList_release;
   o.pack       = TList_pack;
   o.dump       = TList_dump;
   return o;
}

//===========================================================
// <T>判断列表是否为空。</T>
//
// @method
// @return Boolean
//   <L value='true'>为空</L>
//   <L value='false'>不为空</L>
//===========================================================
function TList_isEmpty(){
   return (0 == this.count);
}

//===========================================================
// <T>判断列表是否含有指定的对象。</T>
//
// @method
// @param v:value:Object 对象
// @return Boolean
//   <L value='true'>含有</L>
//   <L value='false'>不含有</L>
//===========================================================
function TList_contains(v){
   return (-1 != this.indexOf(v));
}

//===========================================================
// <T>查找指定对象在列表中的索引位置，不存在则返回-1。</T>
//
// @method
// @param v:value:Object 对象
// @return Integer 索引位置
//===========================================================
function TList_indexOf(v){
   var c = this.count;
   for(var n=0; n<c; n++){
      if(this.memory[n] == v){
         return n;
      }
   }
   return -1;
}

//===========================================================
// <T>获得列表中第一个对象。</T>
//
// @method
// @return 第一个对象
//===========================================================
function TList_first(){
   var o = this;
   return o.count ? this.memory[0] : null;
}

//===========================================================
// <T>获得列表中最后一个对象。</T>
//
// @method
// @return 最后一个对象
//===========================================================
function TList_last(){
   var o = this;
   return o.count ? this.memory[o.count-1] : null;
}

//===========================================================
// <T>取得指定索引对应的对象。</T>
//
// @method
// @param n:index:Integer 索引位置
// @return 当前位置上的对象
//===========================================================
function TList_get(n){
   return (n>=0 && n<this.count) ? this.memory[n] : null;
}

//===========================================================
// <T>把对象存储在指定的索引处。</T>
//
// @method
// @param n:index:Integer 索引位置
// @param v:value:Object 对象
//===========================================================
function TList_set(n, v){
   if(n>=0 && n<this.count){
      this.memory[n] = v;
   }
}

//===========================================================
//<T>把对象插入在指定的索引处。</T>
//
//@method
//@param i:index:Integer 索引位置
//@param v:value:Object 对象
//===========================================================
function TList_insert(i, v){
   var o = this;
   var c = o.count;
   if(i >=0 && i<=c){
      for(var n=c; n>i; n--){
         o.memory[n] = o.memory[n-1];
      }
      o.memory[i] = v;
   }
}

//===========================================================
// <T>把对象追加到列表的最后位置。</T>
//
// @method
// @param v:value:Object 对象
// @return Integer 索引值
//===========================================================
function TList_push(v){
   var n = this.count++;
   this.memory[n] = v;
   return n;
}

//===========================================================
// <T>把唯一对象追加到列表的最后位置。</T>
//
// @method
// @param v:value:Object 对象
// @return Integer 索引值
//===========================================================
function TList_pushUnique(v){
   var o = this;
   // 查询存在性
   for(var n=o.count-1; n>=0; n--){
      if(o.memory[n] == v){
         return n;
      }
   }
   // 追加到尾部
   var n = o.count++;
   o.memory[n] = v;
   return n;
}

//===========================================================
// <T>将最后一个对象弹出列表。</T>
//
// @method
// @return Object 对象
//===========================================================
function TList_pop(){
   var o = this;
   if(o.count){
      return o.memory[--o.count];
   }
}

//===========================================================
// <T>在列表中交换两个索引对应的对象。</T>
//
// @method
// @param l:left:Integer 第一个对象的索引值
// @param r:right:Integer 第二个对象的索引值
//===========================================================
function TList_swap(l, r){
   var o = this;
   if(l>= 0 && l<o.count && r>=0 && r<o.count && l!=r){
      var v = o.memory[l];
      o.memory[l] = this.memory[r];
      o.memory[r] = v;
   }
}

//===========================================================
// <T>对列表内容进行排序。</T>
//
// @method
//===========================================================
function TList_sort(){
   this.memory.sort();
}

//===========================================================
// <T>移除指定索引的存储对象。</T>
//
// @method
// @param n:index:Integer 索引位置
// @return Object 被删除的对象
//===========================================================
function TList_remove(n){
   var v = null;
   var o = this;
   if(n>= 0 && n<o.count){
      v = o.memory[n];
      var c = --o.count;
      for(var i=n; i<c; i++){
         o.memory[i] = o.memory[i+1];
      }
   }
   return v;
}

//===========================================================
// <T>移除所有指定对象。</T>
//
// @method
// @param v:valud:Object 指定对象
//===========================================================
function TList_extract(v){
   if(null != v){
      var o = this;
      var n = 0;
      var c = o.count;
      for(var i=n; i<c; i++){
         if(o.memory[i] != v){
            o.memory[n++] = o.memory[i];
         }
      }
      o.count = n;
   }
   return v;
}

//===========================================================
// <T>将列表内项目为空的位置全部删除。</T>
//
// @method
//===========================================================
function TList_compress(){
   var o = this;
   var c = o.count;
   var l = 0;
   for(var n=0; n<c; n++){
      var v = o.memory[n];
      if(null != v){
         o.memory[l++] = v;
      }
   }
   o.count = l;
}

//===========================================================
// <T>将列表内项目为空的位置全部删除。</T>
//
// @method
//===========================================================
function TList_append(ls){
   var o = this;
   var ct = ls.count;
   for(var n=0; n<ct; n++){
      o.push(ls.get(n));
   }
}

//===========================================================
// <T>清除列表的所有内容。</T>
//
// @method
//===========================================================
function TList_clear(){
   this.count = 0;
}

//===========================================================
// <T>释放当前对象。</T>
//
// @method
//===========================================================
function TList_release(){
   var o = this;
   o.count = 0;
   for(var n in o.memory){
      delete o.memory[n];
   }
}

//==========================================================
//<T>打包列表中的内容。</T>
//
//@method
//@return TStrings 打包字符串
//==========================================================
function TList_pack(){
   var o = this;
   var ss = new TStrings();
   for(var n = 0; n < o.count; n++){
      ss.push(o.get(n).pack());
   }
   return ss.pack();
}

//===========================================================
// <T>获得列表的内部信息。</T>
//
// @method
// @param d:dump:TString 输出字符串
// @return TString 含有列表内部信息的字符串
//===========================================================
function TList_dump(d){
   var o = this;
   var c = o.count;
   d = RString.nvlStr(d);
   d.append(RClass.name(o), ': ', c);
   for(var n=0; n<c; n++){
      d.append(' [', o.memory[n], ']');
   }
   return d;
}
