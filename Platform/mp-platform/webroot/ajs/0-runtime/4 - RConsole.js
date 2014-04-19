//==========================================================
// <T>控制台对象的管理类。</T>
//
// @reference
// @author maocy
// @version 1.0.1
//==========================================================
var RConsole = new function(){
   var o = this;
   // Constant
   o.ConsolePreFix = 'console:';
   // Attribute
   o.registers     = new TList();
   o.consoles      = new TMap();
   o.localConsoles = new TMap();
   // Member
   o.register      = RConsole_register;
   o.initialize    = RConsole_initialize;
   o.create        = RConsole_create;
   o.createByName  = RConsole_createByName;
   o.find          = RConsole_find;
   o.release       = RConsole_release;
   // Construct
   RMemory.register('RConsole', o);
   return o;
}
 
//==========================================================
// TConsole
//==========================================================
function RConsole_register(c){
   this.registers.push(c);
}

//==========================================================
//
//==========================================================
function RConsole_initialize(){
   var o = this;
   var rs = o.registers
   var c = rs.count;
   for(var n=0; n<rs; n++){
      var r = rs.get(n);
      if(r.force){
         o.find(r.clazz);
      }
   }
}

//==========================================================
// <T>根据类名称创建一个控制台实例。</T>
// <P>如果实例中有属性用'lnk'开始，并且内容以'&开始的话，可以自动和其他对象关联。</P>
//
// @method
// @param n:name:String 类名称
// @return Object 控制台实例
//==========================================================
function RConsole_create(n){
   var r = null;
   if(n){
      // 创建对象实例
      r = RClass.create(n);
      // 关联对象属性
      var o = this;
      for(var rn in r){
         if(RString.startsWith(rn, 'lnk')){
            var v = r[rn];
            if('string' == typeof(v) && RString.startsWith(v, '&')){
               var c = o.find(v.substr(1));
               if(!c){
                  return RMessage.fatal(o, null, "Can't link console. (name={0}, property={1}:{2})", n, rn, v);
               }
               r[rn] = c;
            }
         }
      }
   }
   return r;
}
//==========================================================
//
//==========================================================
function RConsole_createByName(n){
   var r = null;
   if(n){
      // 创建对象实例
      r = RClass.createByName(n);
      // 关联对象属性
      var o = this;
      for(var rn in r){
         if(RString.startsWith(rn, 'lnk')){
            var v = r[rn];
            if('string' == typeof(v) && RString.startsWith(v, '&')){
               var c = o.find(v.substr(1));
               if(!c){
                  return RMessage.fatal(o, null, "Can't link console. (name={0}, property={1}:{2})", n, rn, v);
               }
               r[rn] = c;
            }
         }
      }
   }
   return r;
}

//==========================================================
// <T>根据类函数查找一个控制台实例。</T>
//
// @method
// @param n:name:Object 类名称，类函数
// @return Object 控制台实例
//==========================================================
function RConsole_find(n){
   var o = this;
   var t = typeof(n);
   if('function' == t){
      n = RClass.name(n);
   }else if('string' != t){
      return RMessage.fatal(o, null, 'Param type is invalid. (console={0})', n);
   }
   // 查找全局控制台
   var c = RGlobal.get(o.ConsolePreFix + n);
   if(c){
      return c;
   }
   // 查找本地控制台
   var cs = o.consoles;
   if(cs.contains(n)){
      return cs.get(n);
   }
   // 创建控制台实例
   var cls = RClass.forName(n);
   switch(cls.instance.scope){
      case EScope.Global:
         // 从顶层对象重新创建
         c = top.RConsole.createByName(n);
         RGlobal.set(o.ConsolePreFix + n, c);
         // 在本地保存当前对象
         cs.set(n, c);
         break;
      case EScope.Page:
         // 在本地保存当前对象
         c = o.createByName(n);
         cs.set(n, c);
         o.localConsoles.set(n, c);
         break;
      default:
         return RMessage.fatal(o, 'Unknown name. (name={0})', n);
   }
   
   return c;
}

//==========================================================
// <T>释放当前页面内的所有控制台。</T>
//
// @method
// @param n:name:Object 类名称，类函数
// @return Object 控制台实例
//==========================================================
function RConsole_release(){
   var o = this;
   RMemory.free(this.localConsoles);
   o.registers = null;
   o.consoles = null;
   o.localConsoles = null;
}
