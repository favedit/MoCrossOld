//==========================================================
// <T>对象实例的管理类。</T>
//
// @reference
// @author maocy
// @version 1.0.1
//==========================================================
var RObject = new function(){
   var o = this;
   // Method
   o.nvl     = RObject_nvl;
   o.nvlObj  = RObject_nvlObj;
   o.clone   = RObject_clone;
   o.copy    = RObject_copy;
   // Construct
   RMemory.register('RObject', o);
   return o;
}
 
//==========================================================
//
//==========================================================
function RObject_nvl(v){
   for(var n=0; n<arguments.length; n++){
      if(null != arguments[n]){
         return arguments[n];
      }
   }
   return null;
}

//==========================================================
// <T>获得一个非空的对象。</T>
//
// @method
// @param v:value:Object 对象
// @return 非空对象
//==========================================================
function RObject_nvlObj(v){
   return v ? v : new Object();
}

//==========================================================
// Object
//==========================================================
function RObject_clone(o){
   var r = new o.constructor();
   for(var n in o){
      var v = o[n];
      if(null != v){
         var c = v.constructor;
         if(c != Boolean && c != Date && c != String && c != Number && c != Function){
            v = this.clone(v);
         }
      }
      r[n] = v;
   }
   return r;
}

//==========================================================
// Source, Target
//==========================================================
function RObject_copy(s, t){
   if(null!=s && null!=t){
      for(var n in s){
         var v = s[n];
         if(null != v){
            var c = v.constructor;
            if(c != Boolean && c != Date && c != String && c != Number && c != Function){
               if(null == t[n]){
                  t[n] = new c();
               }
               this.copy(v, t[n]);
               continue;
            }
         }
         t[n] = v;
      }
   }
}

