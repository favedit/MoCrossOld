//==========================================================
// <T>对象类的描述信息。</T>
//
// @tool
// @param n:name:String 类名称
// @author maocy
// @version 1.0.1
//==========================================================
function TClass(n){
   var o = this;
   // Attribute [private]
   o._annotations = new Object();
   // 本类是安全对象，禁止内存管理器自动释放
   o._disposed = true;
   o._unused      = null;
   // Attribute
   o.name         = n;
   o.parent       = null;
   o.base         = null;
   o.clazz        = null;
   o.instance     = null;
   o.abstract    = false;
   o.styles       = new Array();
   o.instances    = new Array();
   // Method
   o.register     = TClass_register;
   o.assign       = TClass_assign;
   o.annotations  = TClass_annotations;
   o.annotation   = TClass_annotation;
   o.style        = TClass_style;
   o.newInstance  = TClass_newInstance;
   o.free         = TClass_free;
   o.alloc        = TClass_alloc;
   return o;
}

//==========================================================
// <T>向当前类对象注册一个属性。</T>
//
// @method
// @param v:value:Object 描述对象
//==========================================================
function TClass_register(v){
   var o = this;
   // 检查类型和名称的合法性
   var a = v.annotation;
   var n = v.name;
   if(!a || !n){
      RMessage.fatal(o, null, "Unknown annotation. (class={1},annotation={2},name={3})", RClass.dump(o), a, n);
   }
   // 获得一个Annotation的类型容器
   var as = o._annotations[a];
   if(!as){
      as = o._annotations[a] = new Object();
   }
   if(as[n]){
      RMessage.fatal(o, null, "Duplicate annotation. (class={1},annotation={2},name={3})", RClass.dump(o), a, n);
   }
   as[n] = v;
}

//==========================================================
// <T>当前类接收其他类所有的描述信息。</T>
//
// @method
// @param c:class:TClass 类对象
//==========================================================
function TClass_assign(c){
   var o = this;
   for(var an in c._annotations){
      // 在自己当前对象内查找Annotation的类型容器
      var ls = o._annotations[an];
      if(!ls){
         ls = o._annotations[an] = new Object();
      }
      // 复制指定对象内的类型到自己对象内
      var as = c._annotations[an];
      for(var n in as){
         if(ls[n]){
            RMessage.fatal(o, null, "Duplicate annotation. (annotation={1}, {2}.{3}={4}.{5})", an, o.name, n, c.name, n);
         }
         ls[n] = as[n];
      }
   }
}

//==========================================================
// <T>获得一个描述类型的描述对象集合。</T>
//
// @method
// @param a:annotation:EAnnotation 描述类型
// @return Object 描述对象集合
//==========================================================
function TClass_annotations(a){
   var o = this;
   var r = o._annotations[a];
   if(!r){
      RMessage.fatal(o, null, "Can't find annotations. (annotation={1}, class={2})", a, o.name);
   }
   return r;
}

//==========================================================
// <T>获得一个描述类型下的一个描述对象。</T>
//
// @method
// @param a:annotation:EAnnotation 描述类型
// @param n:name:String 名称
// @return Object 描述对象
//==========================================================
function TClass_annotation(a, n){
   var o = this;
   var r = null;
   var as = o._annotations[a];
   if(as){
      r = as[n];
   }
   if(!r){
      RMessage.fatal(o, null, "Can't find annotation. (annotation={1}, name={2}, class={3})", a, n, o.name);
   }
   return r;
}

//==========================================================
// <T>获得一个类关联的样式描述。</T>
//
// @method
// @param n:name:String 名称
// @return String 样式名称
//==========================================================
function TClass_style(n){
   var o = this;
   // 从缓冲中获得样式名称，如果存在，则直接返回
   if(o.styles[n]){
      return o.styles[n];
   }
   // 递规找到自己或父类上注册的名称
   var a = null;
   var p = o;
   while(p){
      var as = p._annotations[EAnnotation.Style];
      if(as){
         a = as[n];
         if(a){
            break;
         }
      }
      p = p.parent;
   }
   // 如果未注册，则告诉用户错误
   if(!a){
      RMessage.fatal(o, null, "No register style annotation. (name={1}, class={2})", o.name + '_' + n, o.name);
   }
   // 生成样式名称，递规检查样式的存在性
   var p = o;
   var sn = null;
   while(p){
      var pn = p.name + '_' + n;
      if(RCss.has(pn)){
         sn = pn;
         break;
      }
      p = p.parent;
   }
   if(!sn){
      RMessage.fatal(o, null, "Css is undefined. (name={1}, class={2})", o.name + '_' + n, o.name);
   }
   o.styles[n] = sn;
   return sn;
}

//==========================================================
// <T>创建当前类对象的一个实例。</T>
//
// @method
// @param c:class:TClass 类对象
// @return Object 对象实例
//==========================================================
function TClass_newInstance(){
   var o = this;
   // 检测要实例化的类是否为虚类
   var r = o.alloc();
   if(!r){
      if(o.abstract){
         var s = new TString();
         for(var n in o.instance){
            var v = o.instance[n];
            if(RMethod.isVirtual(v)){
               if(!s.isEmpty()){
                  s.append(',');
               }
               s.append(v._name);
            }
         }
         return RMessage.fatal(o, null, "Abstract Class can't be create.(name={1})\n[{2}]", o.name, s);
      }
      // 同一个类的实例中全部共享base对象，中间不能存私有树据。
      var ro = o.instance;
      if(!ro){
         return RMessage.fatal(o, null, "Class instance is empty. (name={1})", o.name);
      }
      r = new ro.constructor();
      r._class = o;
      for(var n in ro){
         var v = ro[n];
         if(null != v){
            // 如果属性名称为base的话，则设置为共有对象
            if('base' == n){
               r[n] = ro.base;
               continue;
            }
            // 递归创建所有子对象
            var t = typeof(v);
            if(('boolean' != t) && ('date' != t) && ('string' != t) && ('number' != t) && ('function' != t)){
               v = RObject.clone(v);
            }
         }
         r[n] = v;
      }
      // 初始化对象
      if(r.construct){
         r.construct();
      }
   }
   return r;
}

//==========================================================
//<T>获得当前没有使用的对象。</T>
//
//@method
//@param v:value:Object 对象
//==========================================================
function TClass_alloc(){
   var o = this;
   var e = o._unused;
   if(e){
      o._unused = e.cnext;
      e.cnext = null;
      e._using = true;
   }
   return e;
}

//==========================================================
//<T>回收对象。</T>
//
//@method
//@param v:value:Object 对象
//==========================================================
function TClass_free(v){
   var o = this;
   if(v._using){
      var u = o._unused;
      v.cnext = u;
      o._unused = v;
      v._using = false;
      // 遍历释放子节点
      for(var n in v){
         var cv = v[n];
         if(cv){
            var t = typeof(cv);
            if('boolean' != t && 'date' != t && 'string' != t && 'number' != t && 'function' != t){
               // 如果是含有_class的对象，直接free
               if(cv._class){
                  RClass.free(cv);
               }else if(RClass.isClass(cv, Array)){
                  for(var i = 0; i < cv.length; i++){
                     var mv = cv[i];
                     if(mv._class){
                        RClass.free(mv);
                     }
                  }
               }
            }
         }
      }
   }
}
