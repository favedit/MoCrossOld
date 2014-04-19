//==========================================================
// <T>所有组件的基类</T>
// <P>非可视对象，支持以下功能：
//    1. 由多个子组件构成，支持添加、查找、删除功能。
//    2. 属性的管理，支持注册、加载、保存功能。
//    3. 事件向所有子组件中纷发功能，支持初始化，释放功能。
//    4. 自身对象的复制。
// </P>
//
// @class FObject
// @face MConfig, MClone
// @history 090930 MAOCY 创建
//==========================================================
function FComponent(o){
   o = RClass.inherits(this, o, FObject, MConfig, MClone);
   //..........................................................
   /// @property String 组件名称
   o.name         = RClass.register(o, new TPtyStr('name'));
   /// @property String 组件标签
   o.label        = RClass.register(o, new TPtyStr('label'));
   //..........................................................
   /// @attribute FComponent 父组件
   o.parent       = null;
   /// @attribute TMap 子组件哈希表
   o.components   = null;
   //..........................................................
   /// @process
   o.oeInitialize = FComponent_oeInitialize;
   o.oeRelease    = FComponent_oeRelease;
   //..........................................................
   /// @method
   o.isParent     = FComponent_isParent;
   o.topComponent = FComponent_topComponent;
   o.fullPath     = FComponent_fullPath;
   o.component    = FComponent_component;
   o.findByClass  = FComponent_findByClass;
   o.findByPath   = FComponent_findByPath;
   o.createChild  = FComponent_createChild;
   o.push         = FComponent_push;
   o.process      = FComponent_process;
   o.psInitialize = FComponent_psInitialize;
   o.psRelease    = FComponent_psRelease;
   o.toString     = FComponent_toString;
   o.dispose      = FComponent_dispose;
   o.innerDump    = FComponent_innerDump;
   o.dump         = FComponent_dump;
   return o;
}

//==========================================================
// <T>处理初始化事件。</T>
//
// @method
// @param e:event:TEvent 事件对象
// @return EEventStatus 处理状态
//==========================================================
function FComponent_oeInitialize(e){
   return EEventStatus.Continue;
}

//==========================================================
// <T>处理释放事件。</T>
//
// @method
// @param e:event:TEvent 事件对象
// @return EEventStatus 处理状态
//==========================================================
function FComponent_oeRelease(e){
   return EEventStatus.Continue;
}

//==========================================================
// <T>判断自己是否指定组件的父。</T>
//
// @method
// @param p:component:FComponent 组件
// @return Boolean 是否指定组件的父
//==========================================================
function FComponent_isParent(p){
   while(p){
      if(p == this){
         return true;
      }
      p = p.parent;
   }
}

//==========================================================
// <T>得到符合指定类的父组件。</T>
// <P>如果没有指定类，则获得最顶层组件。</P>
//
// @method
// @param c:class:Class 类
// @return FComponent 组件
//==========================================================
function FComponent_topComponent(c){
   var p = this;
   if(c){
      while(RClass.isClass(p.parent, c)){
         p = p.parent;
      }
   }else{
      while(p.parent){
         p = p.parent;
      }
   }
   return p;
}

//==========================================================
// <T>获得控件所在的全路径。</T>
//
// @method
// @return String 全路径
//==========================================================
function FComponent_fullPath(){
   var p = this;
   var r = '';
   if(p.parent){
      while(p.parent){
         r = '/' + p.name + r;
         p = p.parent;
      }
   }else{
      r = '/';
   }
   return r;
}

//==========================================================
// <T>获得指定名称的子组件对象。</T>
//
// @method
// @param n:name:String 子组件名称
// @return FComponent 子组件对象
//==========================================================
function FComponent_component(n){
   var ps = this.components;
   return ps ? ps.get(n) : null;
}

//==========================================================
// <T>找到第一个符合指定类的子组件。</T>
// <P>只查找当前组件下的一层子组件，不查找子组件的子组件。</P>
//
// @method
// @param c:class:Class 类型
// @return FComponent 子组件对象
//==========================================================
function FComponent_findByClass(c){
   var ps = this.components;
   if(ps){
      for(var n=0; n<ps.count; n++){
         var p = ps.value(n);
         if(RClass.isClass(p, c)){
            return p;
         }
      }
   }
}

//==========================================================
// <T>根据路径找到子控件。</T>
// <P>路径使用"/"，"."或者"\"字符分割均可。</P>
// <P>如果首字母为跟路径字符，则从当前组件的最顶层组件查找，
//    否则从当前组件下的子组件开始查找。</P>
//
// @method
// @param p:path:String 路径
// @return FComponent 组件
//==========================================================
function FComponent_findByPath(p){
   var o = this;
   // 检查路径有效性
   if(!p){
      return o;
   }
   // 分割路径
   var r = o;
   p = p.replace(/\\/g, '/');
   if(RString.startsWith(p, '/')){
      r = o.topComponent();
      p = p.substring(1);
   }
   var s = p.split('/');
   // 查询子对象
   var c = s.length;
   if(c){
      for(var i=0; i<c; i++){
         var n = s[i];
         if(n.length){
            r = r.components ? r.components.get(n) : null;
            if(!r){
               break;
            }
         }
      }
   }
   return r;
}

//==========================================================
// <T>根据设置信息来创建相应的子控件</T>
// <P>可以通过对这个函数的重载而改变创建子组件的类型。</P>
//
// @method
// @param x:config:TNode XML的节点对象
// @return FComponent 组件对象
// @see RClass.createByName
//==========================================================
function FComponent_createChild(x){
   return RClass.createByName('F' + x.name);
}

//==========================================================
// <T>将子组件放入自己的哈希表中</T>
// <P>如果子组件的名称为空，则给当前子组件创建一个数字的索引名称，
//    保证子组件不会被其他未命名的子组件所覆盖。</P>
// 
// @method
// @param p:component:FComponent 组件对象
//==========================================================
function FComponent_push(p){
   var o = this;
   if(RClass.isClass(p, FComponent)){
      // 获得子组件集合
      var ps = o.components;
      if(!ps){
         ps = o.components = new TMap();
      }
      // 设置子组件名称
      p.parent = o;
      if(!p.name){
         p.name = ps.count;
      }
      // 存储子组件
      ps.set(p.name, p);
   }
}

//==========================================================
// <T>遍历子组件进行事件处理。<T>
// <P>
//    <OL>
//       <L>当前组件的事件前处理。
//          如果返回值为停止状态，则跳过当前组件的所有子组件的处理，直接返回上一层，继续上一层中同一层的其他组件的处理。</L>
//       <L>如果当前组件支持容器接口，则可以进行子组件的事件处理，否则直接返回上一层处理。
//          注意：不支持容器接口的对象并不表示没有子组件</L>
//       <L>子组件按照存储顺序进行事件处理。</L>
//       <L>当前组件的事件后处理。</L>
//    </OL>
//    注意：任何事件调用返回取消状态的话，则跳过后面所有的组件处理，直接返回到最开始的调用函数。</L>
// </P>
//
// @method
// @param e:event:TEventProcess 事件处理对象
// @param c:class:TClass 类对象，如果不为空，则只处理符合当前类型的所有子组件，自动缺省为组件类
// @param k:skip:Boolean 是否不包含自己，如果为真，则只处理子对象
// @return EEventStatus 处理状态
// @see EEventType
// @see EEventStatus
// @see MContainer
//==========================================================
function FComponent_process(e, c, k){
   var o = this;
   // 设置缺省类型
   if(!c){
      c = FComponent;
   }
   var s = !k && RClass.isClass(o, c);
   // 响应事件前处理
   try{
      if(s){
         var r = e.process(o, EEventType.Before);
         if(EEventStatus.Stop == r || EEventStatus.Cancel == r){
            return r;
         }
      }
   }catch(ex){
      RMessage.fatal(o, ex, 'Process component before failure. (event={1}, name={2}, error={3})', e.dump(), o.name, ex);
   }
   // 当前组件为容器对象时才进行所有子组件的事件处理
   if(!RClass.isClass(o, MContainer)){
      return EEventStatus.Stop;
   }
   // 处理所有子对象
   var ps = o.components;
   if(ps){
      var pc = ps.count;
      if(pc){
         for(var n=0; n<pc; n++){
            var p = ps.value(n);
            if(p){
               if(EEventStatus.Cancel == p.process(e, c)){
                  return EEventStatus.Cancel;
               }
            }
         }
      }
   }
   // 响应事件后处理
   try{
      if(s){
         if(EEventStatus.Cancel == e.process(o, EEventType.After)){
            return EEventStatus.Cancel;
         }
      }
   }catch(ex){
      RMessage.fatal(o, ex, 'Process component after failure. (name={1}, error={2})', o.name, ex);
   }
   return EEventStatus.Continue;
}

//==========================================================
// <T>初始化当前组件和所有子组件。</T>
//
// @method
// @see FComponent.process
//==========================================================
function FComponent_psInitialize(){
   var o = this;
   o.process(new TEventProcess(o, 'oeInitialize', FComponent));
}

//==========================================================
// <T>释放当前组件和所有子组件。</T>
//
// @method
// @see FComponent.process
//==========================================================
function FComponent_psRelease(){
   var o = this;
   o.process(new TEventProcess(o, 'oeRelease', FComponent));
   o.parent = null;
   o.components = null;
}

//==========================================================
// <T>获取当前实例的信息。</T>
//
// @method
// @return String 含有内部信息的字符串
//==========================================================
function FComponent_toString(){
   var o = this;
   return RClass.dump(o) + ':label=' + o.label;
}

//==========================================================
// <T>释放当前实例。</T>
//
// @method
//==========================================================
function FComponent_dispose(){
   var o = this;
   o.base.FObject.dispose.call(o);
   o.parent = null;
   o.components = null;
}

//==========================================================
// <T>获取当前实例的信息。</T>
//
// @method
// @param s:dump:TString 字符串
// @param l:level:Integer 递归层次
//==========================================================
function FComponent_innerDump(s, l){
   var o = this;
   s.append(RClass.dump(o));
   s.append(':name=', o.name);
   s.append(',label=', o.label);
}

//==========================================================
// <T>获取当前实例的信息。</T>
//
// @method
// @param s:dump:TString 字符串
// @param l:level:Integer 递归层次
// @return TString 含有内部信息的字符串
//==========================================================
function FComponent_dump(s, l){
   var o = this;
   s = RStringBuffer.nvl(s);
   l = RInteger.nvl(l);
   o.innerDump(s, l);
   // 获取所有子组件的内部信息
   var ps = o.components;
   if(ps){
      s.appendLine();
      for(var n=0; n<ps.count; n++){
         var p = ps.value(n);
         if(p){
            p.dump(s, l+1);
         }
      }
   }
   return s;
}
