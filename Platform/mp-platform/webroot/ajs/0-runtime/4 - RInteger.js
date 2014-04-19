//==========================================================
// 整型对象的操作类
//
// @reference
// @author maochunyang
// @version 1.0.1
//==========================================================
var RInteger = new function(o){
   if(!o){o=this};
   // Define
   o.Chars     = '0123456789-%';
   o.NUMBER    = '0123456789-%';
   o.LEFT_CHAR = '0';
   // Method
   o.isInt     = RInteger_isInt;
   o.parse     = RInteger_parse;
   o.format    = RInteger_format;
   o.nvl       = RInteger_nvl;
   o.toRange   = RInteger_toRange;
   o.sum       = RInteger_sum;
   o.alg       = RInteger_alg;
   // Construct
   RMemory.register('RInteger', o);
   return o;
}
var RInt = RInteger;
//==========================================================
// <T>检验传入值是否是整型值。</T>
//
// @method
// @param value:value:String 待检验的字符串
// @return boolean 真：传入值为整型；
//                  假：传入值不为整型。
//==========================================================
function RInteger_isInt(value){
   return RString.isPattern(value, 'n');
}
 
//==========================================================
// <T>将传入值转换为整型值。</T>
//
// @method
// @param value:value:String 待转换的字符串
// @return int 转换后的整型值 
//==========================================================
function RInteger_parse(v, d){
   // 设置默认值
   if(null == d){
      d = 0;
   }
   // 判断内容为空
   if(null == v){
      return d;
   }
   v = RString.trim(v.toString());
   // 去掉左边0字符
   while(true){
      if('0' != v.charAt(0)){
         break;
      }
      v = v.substr(1);
   }
   // 变换类型
   var r = (v.length > 0) ? parseInt(v) : d;
   return isNaN(r) ? d : r;
}

//==========================================================
//
//==========================================================
function RInteger_format(value, len, pad){
   if(!pad){
      pad = this.LEFT_CHAR;
   }
   var value = value.toString();
   var left = parseInt(len) - value.length;
   for(var i=0; i<left; i++){
      value = pad + value;
   }
   return value;
}

//==========================================================
//
//==========================================================
function RInteger_nvl(v, d){
   return v ? v : (d ? d : 0);
}

//==========================================================
//
//==========================================================
function RInteger_toRange(v, min, max){
   if(null == v){
      v = 0;
   }
   return Math.min(Math.max(v, min), max);
}

//==========================================================
// ...
//==========================================================
function RInteger_sum(){
   var sum = 0;
   for(var n=0; n<arguments.length; n++){
      if(null != arguments[n]){
         sum += parseInt(arguments[n]);
      }
   }
   return sum;
}

//==========================================================
// 把两个字符串 进行算术运算
//==========================================================
function RInteger_alg(f,a,b){
     var a = RInteger.parse(a);
     var b = RInteger.parse(b);
     if(f){
        return (a + b).toString();
     }else{
        return (a - b).toString();
     }
}
