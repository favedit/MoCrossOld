//==========================================================
// <T>数组的操作类。</T>
//
// @tool
// @author maocy
// @version 1.0.1
//==========================================================
function TArray(o){
   var o = moNvl(o, this);
   // Attribute
   o.length   = 0;
   o.memory   = new Array();
   // Method
   o.isEmpty  = TArray_isEmpty;
   o.contains = TArray_contains;
   o.indexOf  = TArray_indexOf;
   o.get      = TArray_get;
   o.set      = TArray_set;
   o.push     = TArray_push;
   o.swap     = TArray_swap;
   o.sort     = TArray_sort;
   o.remove   = TArray_remove;
   o.extract  = TArray_extract;
   o.compress = TArray_compress;
   o.clear    = TArray_clear;
   o.dump     = TArray_dump;
   return o;
}

//==========================================================
// <T>判断数组是否为空。</T>
//
// @method
// @return Boolean
//   <L value='true'>为空</L>
//   <L value='false'>不为空</L>
//==========================================================
function TArray_isEmpty(){
   return (0 == this.length);
}

//==========================================================
// <T>判断数组是否含有指定的对象。</T>
//
// @method
// @param v:value:Object 对象
// @return Boolean
//   <L value='true'>含有</L>
//   <L value='false'>不含有</L>
//==========================================================
function TArray_contains(v){
   return (-1 != this.indexOf(v));
}

//==========================================================
// <T>查找指定对象在数组中的索引位置，不存在则返回-1。</T>
//
// @method
// @param v:value:Object 对象
// @return Integer 索引位置
//==========================================================
function TArray_indexOf(v){
   var c = this.length;
   for(var n=0; n<c; n++){
      if(this.memory[n] == v){
         return n;
      }
   }
   return -1;
}

//==========================================================
// <T>取得指定索引对应的对象。</T>
//
// @method
// @param n:index:Integer 索引位置
// @return 当前位置上的对象
//==========================================================
function TArray_get(n){
   return (n>=0 && n<this.length) ? this.memory[n] : null;
}

//==========================================================
// <T>把对象存储在指定的索引处。</T>
//
// @method
// @param n:index:Integer 索引位置
// @param v:value:Object 对象
//==========================================================
function TArray_set(n, v){
   if(n>=0 && n<this.length){
      this.memory[n] = v;
   }
}

//==========================================================
// <T>把对象追加到数组的最后位置。</T>
//
// @method
// @param v:value:Object 对象
// @return Integer 索引值
//==========================================================
function TArray_push(v){
   var n = this.length++;
   this.memory[n] = v;
   return n;
}

//==========================================================
// <T>在数组中交换两个索引对应的对象。</T>
//
// @method
// @param l:left:Integer 第一个对象的索引值
// @param r:right:Integer 第二个对象的索引值
//==========================================================
function TArray_swap(l, r){
   var o = this;
   if(l>= 0 && l<o.length && r>=0 && r<o.length && l!=r){
      var v = o.memory[l];
      o.memory[l] = this.memory[r];
      o.memory[r] = v;
   }
}

//==========================================================
// <T>对数组内容进行排序。</T>
//
// @method
//==========================================================
function TArray_sort(){
   this.memory.sort();
}

//==========================================================
// <T>移除指定索引的存储对象。</T>
//
// @method
// @param n:index:Integer 索引位置
// @return Object 被删除的对象
//==========================================================
function TArray_remove(n){
   var v = null;
   if(n>= 0 && n<c){
      var o = this;
      var c = --o.length;
      v = o.memory[n];
      for(var i=n; i<c; i++){
         o.memory[i] = o.memory[i+1];
      }
   }
   return v;
}

//==========================================================
// <T>移除所有指定对象。</T>
//
// @method
// @param v:valud:Object 指定对象
//==========================================================
function TArray_extract(v){
   if(null != v){
      var o = this;
      var n = 0;
      var c = o.length;
      for(var i=n; i<c; i++){
         if(o.memory[i] != v){
            o.memory[n++] = o.memory[i];
         }
      }
      o.length = n;
   }
   return v;
}

//==========================================================
// <T>将数组内项目为空的位置全部删除。</T>
//
// @method
//==========================================================
function TArray_compress(){
   var o = this;
   var c = o.length;
   var l = 0;
   for(var n=0; n<c; n++){
      var v = o.memory[n];
      if(null != v){
         o.memory[l++] = v;
      }
   }
   o.length = l;
}

//==========================================================
// <T>清除数组的所有内容。</T>
//
// @method
//==========================================================
function TArray_clear(){
   this.length = 0;
}

//==========================================================
// <T>获得数组的内部信息。</T>
//
// @method
// @param d:dump:TString 输出字符串
// @return TString 含有数组内部信息的字符串
//==========================================================
function TArray_dump(d){
   var o = this;
   var c = o.length;
   d = RString.nvlStr(d);
   d.append(RClass.name(o), ': ', c);
   for(var n=0; n<c; n++){
      d.append(' [', o.memory[n], ']');
   }
   return d;
}
