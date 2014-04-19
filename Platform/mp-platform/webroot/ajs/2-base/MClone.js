/**************************************************************
 * 复制对象的接口类
 * @manger
 * @author maochunyang
 * @version 1.0.1
 **************************************************************/
function MClone(o){
   o = RClass.inherits(this, o);
   // Method
   o.assign = MClone_assign;
   o.clone  = MClone_clone;
   return o;
}
// ------------------------------------------------------------
// control, type
function MClone_assign(c, t){
   var o = this;
   if(t && EAssign.All == t){
      alert('EAssign.All');
   }else if(t && EAssign.Clone == t){
      alert('EAssign.Clone');
   }else{
      var ps = RClass.find(o.constructor).annotations(EAnnotation.Property);
      for(var n in ps){
         var p = ps[n];
         o[p.name] = c[p.name];
      }
   }
}
// ------------------------------------------------------------
function MClone_clone(){
   var o = this;
   var r = RClass.create(o.constructor);
   for(var n in o){
      v = o[n];
      if(null != v){
         var t = v.constructor;
         if(t == Boolean || t == Date || t == String || t == Number){
            r[n] = v;
         }
      }
   }
   return r;
}
// ------------------------------------------------------------
