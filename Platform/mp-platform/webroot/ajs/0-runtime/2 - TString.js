//==========================================================
// <T>字符串操作的工具类</T>
//
// @tool
// @param v:value:String... 字符串
// @author maocy
// @version 1.0.1
//==========================================================
function TString(v){
   var o = this;
   // Attribute
   o.count      = 0;
   o.memory     = new Array();
   // Method
   o.isEmpty    = TString_isEmpty;
   o.assign     = TString_assign;
   o.append     = TString_append;
   o.appendIf   = TString_appendIf;
   o.appendLine = TString_appendLine;
   o.push       = TString_push;
   o.clear      = TString_clear;
   o.toString   = TString_toString;
   o.dump       = TString_dump;
   // Construct
   var a = arguments;
   var c = a.length;
   for(var n=0; n<c; n++){
      if(null != a[n]){
         o.memory[o.count++] = a[n] + '';
      }
   }
   return o;
}

//==========================================================
// <T>判断字符串内容是否为空。</T>
//
// @method
// @return Boolean
//    <L value='true'>为空</L>
//    <L value='false'>非空</L>
//==========================================================
function TString_isEmpty(){
   return (0 == this.count);
}

//==========================================================
// <T>用另外一个或多个字符串替换当前字符串。</T>
//
// @method
// @param v:values:String... 字符串
//==========================================================
function TString_assign(v){
   var o = this;
   var c = arguments.length;
   o.count = 0;
   for(var n=0; n<c; n++){
      if(null != arguments[n]){
         o.memory[o.count++] = arguments[n] + '';
      }
   }
   return o;
}

//==========================================================
// <T>追加一个或多个字符串到当前字符串尾部。</T>
// <P>被追加的内容转换为字符串，放在当前字符串的末尾。</P>
//
// @method
// @param v:values:String... 字符串
//==========================================================
function TString_append(v){
   var o = this;
   var c = arguments.length;
   for(var n=0; n<c; n++){
      if(null != arguments[n]){
         o.memory[o.count++] = arguments[n] + '';
      }
   }
   return o;
}

//==========================================================
// <T>当传入条件为真时，追加一个或多个字符串到当前字符串尾部。</T>
//
// @method
// @param f:flag:Boolean 条件标识
// @param v:values:String... 字符串
//==========================================================
function TString_appendIf(f, v){
   var o = this;
   if(arguments[0]){
      var c = arguments.length;
      for(var n=1; n<c; n++){
         if(null != arguments[n]){
            o.memory[o.count++] = arguments[n] + '';
         }
      }
   }
   return o;
}

//==========================================================
// <T>追加一行字符串的内容到当前字符串内。</T>
//
// @method
// @param v:values:String... 字符串
//==========================================================
function TString_appendLine(v){
   var o = this;
   var c = arguments.length;
   for(var n=0; n<c; n++){
      if(null != arguments[n]){
         o.memory[o.count++] = arguments[n] + '';
      }
   }
   o.memory[o.count++] = '\n';
   return o;
}

//==========================================================
// <T>将字符串的内容加在当前字符串末尾。</T>
// <P>被追加的内容不做任何转换，放在当前字符串的末尾。</P>
//
// @method
// @param v:values:Object... 字符串
//==========================================================
function TString_push(v){
   var o = this;
   var c = arguments.length;
   for(var n=0; n<c; n++){
      if(null != arguments[n]){
         o.memory[o.count++] = arguments[n];
      }
   }
   return o;
}

//==========================================================
// <T>清除字符串内容。</T>
//
// @method
// @param v:values:Object... 字符串
//==========================================================
function TString_clear(){
   this.count = 0;
}

//==========================================================
// <T>将当前字符串对象转换为字符串。</T>
//
// @method
// @return String 字符串
//==========================================================
function TString_toString(){
   return this.memory.slice(0, this.count).join('');
}

//==========================================================
// <T>获得当前字符串对象的内部信息。</T>
//
// @method
// @return String 字符串
//==========================================================
function TString_dump(){
   var o = this;
   var s = o.toString();
   return RClass.name(o) + ':' + s.length + '[' + s + ']';
}
