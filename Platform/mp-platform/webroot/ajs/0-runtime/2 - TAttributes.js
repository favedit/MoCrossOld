//==========================================================
// <T>名称和内容都是字符串的关联保存表的工具类。</T>
//
// @tool
// @history 090729 MAOCY 创建
//==========================================================
function TAttributes(o){
   o = o ? o : this;
   TMap(o);
   // @method
   o.nvl    = TAttributes_nvl;
   o.join   = TAttributes_join;
   o.split  = TAttributes_split;
   o.pack   = TAttributes_pack;
   o.unpack = TAttributes_unpack;
   return o;
}

//==========================================================
// <T>根据名称查找内容，如果内容为空，则返回默认内容。</T>
//
// @method
// @param n:name:String 名称
// @param v:value:String 默认内容
// @return String 内容
//==========================================================
function TAttributes_nvl(n, v){
   return RString.nvl(this.get(n), v);
}

//==========================================================
// <T>将内部所有项目关联成一个字符串。</T>
//
// @method
// @param n:name:String 分隔名称的字符
// @param v:value:String 分隔内容的字符
// @return String 字符串
//==========================================================
function TAttributes_join(n, v){
   var o = this;
   var r = new TString();
   n = RString.nvl(n, '=');
   v = RString.nvl(v, ',');
   for(var i=0; i<o.count; i++){
      if(i > 0){
         r.append(v);
      }
      r.append(o.names[i]);
      r.append(n);
      r.append(o.values[i]);
   }
   return r.toString();
}

//==========================================================
// <T>将字符串分割为子项。</T>
//
// @method
// @param s:source:String 字符串
// @param n:name:String 分隔名称的字符
// @param v:value:String 分隔内容的字符
//==========================================================
function TAttributes_split(s, n, v){
   var o = this;
   var ss = s.split(v);
   for(var i=0; i<ss.length; i++){
      var ln = RString.trim(ss[i]);
      if(ln.length){
         var sb = ln.split(n);
         if(2 == sb.length){
            o.set(RString.trim(sb[0]), RString.rtrim(sb[1]));
         }else{
            o.set(RString.trim(ln), '');
         }
      }
   }
}

//==========================================================
// <T>将表中所有数据连接成一个字符串。</T>
// <P>打包方式：项目1(名称长度的长度+名称长度+名称+内容长度的长度+内容长度+内容)+...。</P>
//
// @method
// @return TString 打包字符串
//==========================================================
function TAttributes_pack(){
   var o = this;
   var c = o.count;
   var p = new TString();
   for(var n=0; n<c; n++){
      var l = o.names[n].length;
      p.append(l.toString().length, l, o.names[n]);
      if(null != o.values[n]){
         var v = o.values[n] + '';
         l = v.length;
         p.append(l.toString().length, l, v);
      }else{
         p.append('0');
      }
   }
   return p.toString();
}

//==========================================================
// <T>将一个打包字符串分解为所有子项。</T>
//
// @method
// @param p:pack:String 打包字符串
//==========================================================
function TAttributes_unpack(p){
   if(p && p.length){
      var o = this;
      var n = null;
      var v = null;
      var f = 0;
      o.count = 0;
      var pl = p.length;
      while(f < pl){
         // 解析名称
         var ll = parseInt(p.substr(f++, 1));
         var l = parseInt(p.substr(f, ll));
         n = p.substr(f + ll, l);
         f += ll + l;
         // 解析内容
         ll = parseInt(p.substr(f++, 1));
         if(0 == ll){
            v = null;
         }else{
            l = parseInt(p.substr(f, ll));
            v = p.substr(f + ll, l);
            f += ll + l;
         }
         // 设置分解后的内容
         o.set(n, v);
      }
   }
}
