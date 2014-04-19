//==========================================================
// <T>名称和内容的关联保存表的工具类。</T>
//
// @tool
// @param ds:dataset:TDataset 数据集对象
// @history 091012 MAOCY 创建
//==========================================================
function TMap(o){
   o = o ? o : this;
   // @attribute
   o._table        = new Object();
   // @attribute
   o.count         = 0;
   o.names         = new Array();
   o.values        = new Array();
   // @method
   o.isEmpty       = TMap_isEmpty;
   o.contains      = TMap_contains;
   o.containsValue = TMap_containsValue;
   o.indexOf       = TMap_indexOf;
   o.indexOfValue  = TMap_indexOfValue;
   o.first         = TMap_first;
   o.last          = TMap_last;
   o.name          = TMap_name;
   o.value         = TMap_value;
   o.setValue      = TMap_setValue;
   o.get           = TMap_get;
   o.nvl           = TMap_nvl;
   o.set           = TMap_set;
   o.assign        = TMap_assign;
   o.append        = TMap_append;
   o.insert        = TMap_insert;
   o.remove        = TMap_remove;
   o.removeName    = TMap_removeName;
   o.removeValue   = TMap_removeValue;
   o.rebuild       = TMap_rebuild;
   o.clear         = TMap_clear;
   o.release       = TMap_release;
   o.toString      = TMap_toString;
   o.dump          = TMap_dump;
   return o;
}

//==========================================================
// <T>判断表中是否有内容。</T>
//
// @method
// @return Boolean
//   <L value='true'>为空</L>
//   <L value='false'>不为空</L>
//==========================================================
function TMap_isEmpty(){
   return (0 == this.count);
}

//==========================================================
// <T>判断表中是否含有指定的名称。</T>
//
// @method
// @param n:name:String 名称
// @return Boolean
//   <L value='true'>含有</L>
//   <L value='false'>不含有</L>
//==========================================================
function TMap_contains(n){
   return (null != n) ? (null != this._table[n.toString().toLowerCase()]) : false;
}

//==========================================================
// <T>判断表中是否含有指定的内容。</T>
//
// @method
// @param v:value:Object 内容
// @return Boolean
//   <L value='true'>含有</L>
//   <L value='false'>不含有</L>
//==========================================================
function TMap_containsValue(v){
   return (-1 != this.indexOfValue(v));
}

//==========================================================
// <T>查找指定名称在表中的索引位置，不存在则返回-1。</T>
//
// @method
// @param n:name:String 名称
// @return Integer 索引位置
//==========================================================
function TMap_indexOf(n){
   if(null != n){
      var r = this._table[n.toString().toLowerCase()];
      return (null != r) ? r : -1;
   }
   return -1;
}

//==========================================================
// <T>查找指定对象在表中的第一次出现的索引位置，不存在则返回-1。</T>
//
// @method
// @param v:value:Object 内容
// @return Integer 索引位置
//==========================================================
function TMap_indexOfValue(v){
   var o = this;
   var c = o.count;
   for(var n=0; n<c; n++){
      if(o.values[n] == v){
         return n;
      }
   }
   return -1;
}

//==========================================================
// <T>查找第一个内容。</T>
//
// @method
// @return Object 内容
//==========================================================
function TMap_first(){
   return this.count ? this.values[0] : null;
}

//==========================================================
// <T>查找最后一个内容。</T>
//
// @method
// @return Object 内容
//==========================================================
function TMap_last(){
   return this.count ? this.values[this.count-1] : null;
}

//==========================================================
// <T>根据索引位置获得名称。</T>
//
// @method
// @param n:index:Integer 索引位置
// @return String 名称
//==========================================================
function TMap_name(n){
   return (n>=0 && n<this.count) ? this.names[n] : null;
}

//==========================================================
// <T>根据索引位置获得内容。</T>
//
// @method
// @param n:index:Integer 索引位置
// @return Object 内容
//==========================================================
function TMap_value(n){
   return (n>=0 && n<this.count) ? this.values[n] : null;
}

//==========================================================
// <T>根据索引位置设置内容。</T>
//
// @method
// @param n:index:Integer 索引位置
// @param v:value:Object 内容
//==========================================================
function TMap_setValue(n, v){
   if(n>=0 && n<this.count){
      this.values[n] = v;
   }
}

//==========================================================
// <T>根据名称查找内容。</T>
// <P>如果内容不存在，返回默认内容。</P>
//
// @method
// @param n:name:String 名称
// @param v:value:Object 默认内容
// @return Object 内容
//==========================================================
function TMap_get(n, v){
   if(null != n){
      var o = this;
      var i = o._table[n.toString().toLowerCase()];
      if(null != i){
         return o.values[i];
      }
   }
   return v;
}

//==========================================================
// <T>根据名称查找内容。</T>
// <P>如果内容不存在或为空，返回默认内容。</P>
//
// @method
// @param n:name:String 名称
// @param v:value:Object 默认内容
// @return Object 内容
//==========================================================
function TMap_nvl(n, v){
   var r = this.get(n);
   return r ? r : v;
}

//==========================================================
// <T>根据名称设置内容。</T>
//
// @method
// @param n:name:String 名称
// @param v:value:Object 默认内容
// @return Object 内容
//==========================================================
function TMap_set(n, v){
   if(null != n){
      var o = this;
      var l = n.toString().toLowerCase();
      var i = o._table[l];
      if(null == i || i >= o.count){
         i = o.count++;
         o.names[i] = n;
         o._table[l] = i;
      }
      o.values[i] = v;
   }
}

//==========================================================
// <T>将当前表内容全部置为另一个表的全部内容。</T>
//
// @method
// @param m:map:TMap 表
//==========================================================
function TMap_assign(m){
   var o = this;
   o.clear();
   o.append(m);
}

//==========================================================
// <T>在当前表中追加另一个表的全部内容。</T>
//
// @method
// @param m:map:TMap 表
//==========================================================
function TMap_append(m){
   if(m){
      var c = m.count;
      for(var n=0; n<c; n++){
         this.set(m.name(n), m.value(n));
      }
   }
}

//==========================================================
// <T>在索引位置插入一个新的名称和内容。</T>
//
// @method
// @param i:index:Integer 索引位置
// @param n:name:String 名称
// @param v:value:Object 内容
//==========================================================
function TMap_insert(i, n, v){
   var o = this;
   var c = o.count;
   if(i >= 0 && i <= c){
      for(var p=c; p>i; p--){
         o.names[p] = o.names[p-1];
         o.values[p] = o.values[p-1];
      }
      o.names[i] = n;
      o.values[i] = v;
      o.count++;
      o.rebuild();
   }
}

//==========================================================
// <T>删除索引位置的内容。</T>
//
// @method
// @param i:index:Integer 索引位置
// @return Object 删除的内容
//==========================================================
function TMap_remove(i){
   var o = this;
   var r = null;
   var c = o.count;
   if(i >= 0 && i < c){
      r = o.values[i];
      for(var p=i; p<c; p++){
         o.names[p] = o.names[p+1];
         o.values[p] = o.values[p+1];
      }
      o.count--;
      o.rebuild();
   }
   return r;
}

//==========================================================
// <T>删除指定名称的内容。</T>
//
// @method
// @param n:name:String 名称
// @return Object 删除的内容
//==========================================================
function TMap_removeName(n){
   var o = this;
   var i = o.indexOf(n);
   return (-1 != i) ? o.remove(i) : null;
}

//==========================================================
// <T>删除指定的内容。</T>
//
// @method
// @param v:value:Object 内容
//==========================================================
function TMap_removeValue(v){
   var o = this;
   var i = 0;
   var c = o.count;
   for(var n=0; n<c; n++){
      var s = o.values[n];
      if(v != s){
         if(i != n){
            o.names[i] = o.names[n];
            o.values[i] = s;
         }
         i++;
      }
   }
   o.count = i;
   o.rebuild();
}

//==========================================================
// <T>根据对象内名称数组和内容数组重新建立对照表。</T>
//
// @method
//==========================================================
function TMap_rebuild(){
   var o = this;
   // 清除对照表数据
   var t = o._table;
   for(var n in t){
      delete t[n];
   }
   // 重建对照表数据
   var c = o.count;
   for(var n=0; n<c; n++){
      t[o.names[n].toLowerCase()] = n;
   }
}

//==========================================================
// <T>清除所有内容。</T>
//
// @method
//==========================================================
function TMap_clear(){
   var o = this;
   o.count = 0;
   // 清除对照表数据
   var t = o._table;
   for(var n in t){
      delete t[n];
   }
}

//==========================================================
// <T>释放所有内容。</T>
//
// @method
//==========================================================
function TMap_release(){
   var o = this;
   o.clear();
   // 清空名称集
   for(var n in o.names){
      delete o.names[n];
   }
   // 清空数据集
   for(var n in o.values){
      delete o.values[n];
   }
}

//==========================================================
// <T>获得数组的内部信息。</T>
//
// @method
// @return String 信息字符串
//==========================================================
function TMap_toString(){
   return this.dump().toString();
}

//==========================================================
// <T>获得数组的内部信息。</T>
//
// @method
// @param d:dump:TString 输出字符串
// @return TString 含有数组内部信息的字符串
//==========================================================
function TMap_dump(d){
   var o = this;
   d = RString.nvlStr(d);
   d.appendLine(RClass.name(o), ': ', o.count);
   var c = o.count;
   d.append(' {');
   for(var n=0; n<c; n++){
      d.appendLine(o.names[n], '=', ' [', o.values[n], ']');
   }
   d.append('}');
   return d;
}
