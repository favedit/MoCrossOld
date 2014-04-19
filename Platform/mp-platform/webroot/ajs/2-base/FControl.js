//==========================================================
// <T>所有控件的基类。</T>
// <P>支持控件的样式、建立、显示、设计、模式功能。</P>
//
// @class FComponent, MStyle, MSize
// @history 090930 MAOCY 创建
//==========================================================
function FControl(o){
   o = RClass.inherits(this, o, FComponent, MStyle, MSize);
   //..........................................................
   // @property Boolean 是否回行
   o.nowrap        = RClass.register(o, new TPtyBool('nowrap', false));
   // @property Boolean 是否禁止
   o.disable       = RClass.register(o, new TPtyBool('disable', false));
   // @property Integer 左空余
   o.padLeft       = RClass.register(o, new TPtyInt('padLeft', 0));
   // @property Integer 上空余
   o.padTop        = RClass.register(o, new TPtyInt('padTop', 0));
   // @property Integer 右空余
   o.padRight      = RClass.register(o, new TPtyInt('padRight', 0));
   // @property Integer 下空余
   o.padTottom     = RClass.register(o, new TPtyInt('padTottom', 0));
   // @property String 提示信息
   o.hint          = RClass.register(o, new TPtyStr('hint'));
   //..........................................................
   // @style
   o.stPanel       = RClass.register(o, new TStyle('Panel'));
   // @style
   o.stDesign      = RClass.register(o, new TStyle('Design'));
   // @style
   o.stDesignHover = RClass.register(o, new TStyle('DesignHover'));
   // @style
   o.stDesignDrag  = RClass.register(o, new TStyle('DesignDrag'));
   // @style
   o.stDesignMove  = RClass.register(o, new TStyle('DesignMove'));
   //..........................................................
   /// @attribute
   o._esize        = ESize.Normal;
   o._emode        = EMode.Update;
   o._events       = null;
   o.controls      = null;
   o.storage       = null;
   o.disabled      = false;
   o.isBuilded     = false;
   //..........................................................
   // @html 布局表单
   o.hLayoutForm   = null;
   // @html 布局行
   o.hLayoutRow    = null;
   // @html 布局底板
   o.hLayoutCell   = null;
   // @html 底板
   o.hPanel        = null;
   //..........................................................
   // @event
   o.onEnter       = RClass.register(o, new HMouseEnter('onEnter'), FControl_onEnter);
   o.onLeave       = RClass.register(o, new HMouseLeave('onLeave'), FControl_onLeave);
   o.onMouseOver   = RClass.register(o, new HMouseOver('onMouseOver'));
   o.onMouseOut    = RClass.register(o, new HMouseOut('onMouseOut'));
   o.onMouseDown   = RClass.register(o, new HMouseDown('onMouseDown'));
   o.onMouseUp     = RClass.register(o, new HMouseUp('onMouseUp'));
   o.onClick       = RClass.register(o, new HClick('onClick'));
   o.onDoubleClick = RClass.register(o, new HDoubleClick('onDoubleClick'));
   o.onKeyDown     = RClass.register(o, new HKeyDown('onKeyDown'));
   o.onKeyPress    = RClass.register(o, new HKeyPress('onKeyPress'));
   o.onKeyUp       = RClass.register(o, new HKeyUp('onKeyUp'));
   o.onResize      = RClass.register(o, new HResize('onResize'));
   o.onBuildPanel  = FControl_onBuildPanel;
   //..........................................................
   // @process
   o.oeBuild       = FControl_oeBuild;
   o.oeMode        = FControl_oeMode;
   o.oeEnable      = FControl_oeEnable;
   o.oeVisible     = FControl_oeVisible;
   o.oeResize      = FControl_oeResize;
   o.oeRefresh     = FControl_oeRefresh;
   //..........................................................
   // @method
   o.topControl    = FControl_topControl;
   o.attachEvent   = FControl_attachEvent;
   o.linkEvent     = FControl_linkEvent;
   o.panel         = FControl_panel;
   // @method
   o.isEnable      = FControl_isEnable;
   o.setEnable     = FControl_setEnable;
   o.enable        = FControl_enable;
   o.disable       = FControl_disable;
   // @method
   o.isVisible     = FControl_isVisible;
   o.setVisible    = FControl_setVisible;
   o.show          = FControl_show;
   o.hide          = FControl_hide;
   o.callEvent     = FControl_callEvent;
   // @method
   o.psBuild       = FControl_psBuild;
   o.psMode        = FControl_psMode;
   o.psDesign      = FControl_psDesign;
   o.psEnable      = FControl_psEnable;
   o.psVisible     = FControl_psVisible;
   o.psResize      = FControl_psResize;
   o.psRefresh     = FControl_psRefresh;
   // @method
   o.setPanel      = FControl_setPanel;
   o.setPads       = FControl_setPads;
   o.push          = FControl_push;
   o.dispose       = FControl_dispose;
   return o;
}

//==========================================================
// <T>当该控件获得热点时的处理</T>
//
// @method
// @param e:event:TEvent 事件对象
//==========================================================
function FControl_onEnter(e){
   var o = this;
   RConsole.find(FFocusConsole).enter(o);
   if(o.hint){
      window.status = o.hint;
   }
}

//==========================================================
// <T>当该控件失去热点时的处理</T>
//
// @method
// @param e:event:TEvent 事件对象
//==========================================================
function FControl_onLeave(e){
   var o = this;
   RConsole.find(FFocusConsole).leave(o);
   if(o.hint){
      window.status = '';
   }
}

//==========================================================
// <T>创建一个控件底板</T>
// <P>默认为DIV页面元素。</P>
//
// @method
// @return HTML 页面元素
//==========================================================
function FControl_onBuildPanel(){
   var o = this;
   var h = o.hPanel = RBuilder.newDiv();
   return h;
}

//==========================================================
// <T>建立当前控件的显示框架。</T>
//
// @method
// @param e:event:TEvent 事件
// @return EEventStatus 执行状态
//==========================================================
function FControl_oeBuild(e){
   var o = this;
   if(e.isBefore()){
      // 建立控件的最底层HTML容器
      o.onBuildPanel();
      // 设置容器样式
      var h = o.hPanel;
      RHtml.link(h, 'control', o);
      if(!h.className){
         h.className = o.style('Panel');
      }
      // 关联容器事件
      o.attachEvent('onEnter', h);
      o.attachEvent('onLeave', h);
      o.attachEvent('onMouseOver', h);
      o.attachEvent('onMouseOut', h);
      o.attachEvent('onMouseDown', h, o.onMouseDown);
      o.attachEvent('onMouseUp', h);
      o.attachEvent('onClick', h);
      o.attachEvent('onDoubleClick', h);
      o.attachEvent('onKeyDown', h);
      o.attachEvent('onKeyPress', h);
      // 设置容器位置，大小，空余
      o.setBounds(o.left, o.top, o.right, o.bottom, true);
      o.setSize(o.width, o.height);
      o.setPads(o.padLeft, o.padTop, o.padRight, o.padBottom, true);
      // 如果父容器是可以容纳控件的，则将自己添加到父容器
      if(RClass.isClass(o.parent, MContainer)){
         o.parent.appendChild(o);
      }
   }
   o.isBuilded = true;
   return EEventStatus.Continue;
}

//==========================================================
// <T>改变当前控件的工作模式。</T>
//
// @method
// @param e:event:TEvent 事件
// @return EEventStatus 执行状态
//==========================================================
function FControl_oeMode(e){
   var o = this;
   o._emode = e.mode;
   return EEventStatus.Continue;
}

//==========================================================
// <T>改变当前控件的操作模式。</T>
//
// @method
// @param e:event:TEvent 事件
//==========================================================
function FControl_oeEnable(e){
   var o = this;
   if(e.isBefore()){
      o.setEnable(e.enable);
   }
   return EEventStatus.Continue;
}

//==========================================================
// <T>改变当前控件的显示模式。</T>
//
// @method
// @param e:event:TEvent 事件
//==========================================================
function FControl_oeVisible(e){
   var o = this;
   if(e.isBefore()){
      o.setVisible(e.visible);
   }
   return EEventStatus.Continue;
}

//==========================================================
// <T>改变当前控件的显示大小。</T>
//
// @method
// @param e:event:TEvent 事件
// @return EEventStatus 执行状态
//==========================================================
function FControl_oeResize(e){
   return EEventStatus.Continue;
}

//==========================================================
// <T>刷新当前控件的显示内容。</T>
//
// @method
// @param e:event:TEvent 事件
// @return EEventStatus 执行状态
//==========================================================
function FControl_oeRefresh(e){
   return EEventStatus.Continue;
}

//==========================================================
// <T>获得指定类型的父控件。</T>
//
// @method
// @param c:class:Class 类
// @return FControl 父控件
//==========================================================
function FControl_topControl(c){
   var r = this;
   if(c){
      while(r.parent){
         if(RClass.isClass(r.parent, c)){
            return r.parent;
         }
         r = r.parent;
      }
      if(!RClass.isClass(r, c)){
         return null;
      }
   }else{
      while(r.parent){
         if(!RClass.isClass(r.parent, FControl)){
            break;
         }
         r = r.parent;
      }
   }
   return r;
}

//==========================================================
// <T>将一个页面元素和已经注册过的事件相关联。</T>
// <P>如果指定了立即函数，则发生事件时，会立即执行立即函数。
//    该立即函数的this指针指向当前控件实例。</P>
// <P>如果存在注册过的队列函数，则发生事件时，该事件被排到队列中等待执行。
//    执行时该函数的this指针指向当前控件实例。</P>
//
// @method
// @param n:name:String 注册过的事件名称
// @param h:hPanel:HTML 页面元素
// @param m:method:Function 立即函数
// @return TEvent 关联的事件对象
// @see RControl.attachEvent
//==========================================================
function FControl_attachEvent(n, h, m){
   return RControl.attachEvent(this, n, h, m);
}

//==========================================================
// <T>将一个页面元素和已经注册过的事件相关联。</T>
// <P>如果指定了立即函数，则发生事件时，会立即执行立即函数。
//    该立即函数的this指针指向到达者控件实例。
//    可以通过事件对象的发出者访问到该发出对象。</P>
// <P>如果存在注册过的队列函数，则发生事件时，该事件被排到队列中等待执行。
//    执行时该函数的this指针指向到达者控件实例。
//    可以通过事件对象的发出者访问到该发出对象。</P>
//
// @method
// @param t:target:FControl 到达者控件
// @param n:name:String 注册过的事件名称
// @param h:hPanel:HTML 页面元素
// @param m:method:Function 立即函数
// @return TEvent 关联的事件对象
// @see RControl.linkEvent
//==========================================================
function FControl_linkEvent(t, n, h, m){
   return RControl.linkEvent(this, t, n, h, m);
}

//==========================================================
// <T>根据底板类型得到相应的页面元素。</T>
//
// @method
// @param t:type:EPanel 底板类型
// @return HTML 页面元素
//==========================================================
function FControl_panel(t){
   return this.hPanel;
}

//==========================================================
// <T>判断当前控件是否可以操作。</T>
//
// @method
// @return Boolean 是否可以以操作
//==========================================================
function FControl_isEnable(){
   return true;
}

//==========================================================
// <T>设置控件的可操作和禁止。</T>
//
// @method
// @param v:enable:Boolean 是否可见
// @see FControl.panel
//==========================================================
function FControl_setEnable(v){
   var o = this;
   o.disabled = !v;
   var h = o.panel(EPanel.Display);
   if(h){
      h.style.disabled = !v;
   }
}

//==========================================================
// <T>使当前控件进入可操作状态。</T>
//
// @method
// @see FControl.setEnable
//==========================================================
function FControl_enable(){
   var o = this;
   if(o.disabled){
      o.setEnable(true);
   }
}

//==========================================================
// <T>使当前控件进入禁止状态。</T>
//
// @method
// @see FControl.setEnable
//==========================================================
function FControl_disable(){
   var o = this;
   if(!o.disabled){
      o.setEnable(false);
   }
}

//==========================================================
// <T>判断当前控件是否可以显示。</T>
//
// @method
// @return Boolean 是否可以显示
// @see FControl.panel
//==========================================================
function FControl_isVisible(){
   var h = this.panel(EPanel.Display);
   if(h){
      return h.offsetWidth > 0;
   }
   return h ? (h.style.display != 'none') : false;
}

//==========================================================
// <T>设置控件的隐藏和显示。</T>
//
// @method
// @param v:visible:Boolean 是否可见
// @see FControl.panel
//==========================================================
function FControl_setVisible(v){
   var o = this;
   o._visible = v;
   // 设置布局底板的可见性
   var hp = o.hLayoutCell;
   if(hp){
      hp.style.display = v ? 'inline' : 'none';
   }
   // 设置控件底板的可见性
   var h = o.panel(EPanel.Display);
   if(h){
      h.style.display = v ? 'inline' : 'none';
   }
}

//==========================================================
// <T>使当前控件进入显示状态。</T>
//
// @method
// @see FControl.setVisible
//==========================================================
function FControl_show(){
   var o = this;
   if(!o.isVisible()){
      o.setVisible(true);
   }
}

//==========================================================
// <T>使当前控件进入隐藏状态。</T>
//
// @method
// @see FControl.setVisible
//==========================================================
function FControl_hide(){
   var o = this;
   if(o.isVisible()){
      o.setVisible(false);
   }
}

//==========================================================
// <T>调用控件的关联事件。</T>
//
// @method
// @param n:name:String 事件名称
// @param s:source:FControl 事件源
// @param e:event:TEvent 事件对象
//==========================================================
function FControl_callEvent(n, s, e){
   var o = this;
   var es = o._events;
   if(es){
      var ec = es.get(n);
      if(ec){
         //ec.invoke(o, s, e);
         ec.invoke(s, s, e);
      }
   }
}

//==========================================================
// <T>分发控件建立的事件。</T>
//
// @method
// @param h:hPanel:HTML 页面元素
// @param b:builder:TBuilder 页面工厂对象
// @see FComponent.process
//==========================================================
function FControl_psBuild(h, b){
   var o = this;
   // 收集事件对象
   var e = new TEventProcess(o, 'oeBuild', FControl);
   // 设置页面元素工厂
   if(!b){
      b = RWindow.builder();
   }
   e.builder = b;
   // 处理所有消息
   o.process(e);
   // 设置父节点
   if(h){
      o.setPanel(h);
   }
}

//==========================================================
// <T>分发工作模式的事件。</T>
//
// @method
// @param m:mode:EMode 工作模式
// @see FComponent.process
//==========================================================
function FControl_psMode(m){
   var o = this;
   var e = new TEventProcess(o, 'oeMode', FControl);
   e.mode = m;
   o._emode = m;
   o.process(e);
}

//==========================================================
// <T>分发改变控件设计状态的事件。</T>
//
// @method
// @param m:mode:EDesign 设计模式
// @param f:flag:Boolean 开始还是结束
// @see FComponent.process
//==========================================================
function FControl_psDesign(m, f){
   var o = this;
   RConsole.find(FDesignConsole).setFlag(m, f, o);
   var e = new TEventProcess(o, 'oeDesign', MDesign)
   e.mode = m;
   e.flag = f;
   o.process(e);
}

//==========================================================
// <T>分发改变控件可操作和禁止的事件。</T>
//
// @method
// @param v:enable:Boolean 是否可操作
// @see FComponent.process
//==========================================================
function FControl_psEnable(v){
   var o = this;
   var e = new TEventProcess(o, 'oeEnable', FControl)
   e.enable = v;
   o.process(e);
}

//==========================================================
// <T>分发改变控件隐藏和显示的事件。</T>
//
// @method
// @param v:visible:Boolean 是否可见
// @see FComponent.process
//==========================================================
function FControl_psVisible(v){
   var o = this;
   var e = new TEventProcess(o, 'oeVisible', FControl);
   e.visible = v;
   o.process(e);
}

//==========================================================
// <T>分发改变控件大小的事件。</T>
//
// @method
// @see FComponent.process
//==========================================================
function FControl_psResize(){
   var o = this;
   o.process(new TEventProcess(o, 'oeResize', FControl));
}

//==========================================================
// <T>分发控件刷新的事件。</T>
//
// @method
// @param t:type:String 刷新类型
// @see FComponent.process
//==========================================================
function FControl_psRefresh(t){
   var o = this;
   o.process(new TEventProcess(o, 'oeRefresh', FControl));
}

//==========================================================
// <T>设置控件的页面父容器。</T>
//
// @method
// @param h:hPanel:HTML 页面元素
//==========================================================
function FControl_setPanel(h){
   var o = this;
   o.hParent = h;
   if(h && o.hPanel){
      h.appendChild(o.hPanel);
   }
}

//==========================================================
// <T>设置控件四周的空白。</T>
//
// @method
// @param l:left:Integer 左空白
// @param t:top:Integer 上空白
// @param r:right:Integer 右空白
// @param b:bottom:Integer 下空白
//==========================================================
function FControl_setPads(l, t, r, b){
   var s = this.hPanel.style;
   if(l){
      s.paddingLeft = l;
   }
   if(t){
      s.paddingTop = t;
   }
   if(r){
      s.paddingRight = r;
   }
   if(b){
      s.paddingBottom = b;
   }
}

//==========================================================
// <T>如果当前组件是控件类型则放入自己的控件哈希表中。</T>
// <P>同时将子组件放入组件哈希表中。</P>
// <P>如果子控件的名称为空，则给当前子控件创建一个数字的索引名称，
//    保证子控件不会不其他未命名的子控件所覆盖。</P>
//
// @method
// @param p:component:FComponent 组件对象
//==========================================================
function FControl_push(p){
   var o = this;
   // 加载事件定义
   if(RClass.isClass(p, FEvent)){
      var es = o._events;
      var t = o.topComponent();
      if(!es){
         es = o._events = new TDictionary();
      }
      var en = p.name + '@' + t.name + o.fullPath();
      var e = RControl.events.get(en);
      if(!e){
         e = p;
         RControl.events.set(en, p);
      }
      es.set(e.name, e);
      return;
   }
   // 加载组件
   o.base.FComponent.push.call(o, p);
   // 加载控件
   if(RClass.isClass(p, FControl)){
      var cs = o.controls;
      if(!cs){
         cs = o.controls = new TDictionary();
      }
      if(!p.name){
         p.name = cs.count;
      }
      cs.set(p.name, p);
   }
}

//==========================================================
// <T>释放当前实例。</T>
//
// @method
//==========================================================
function FControl_dispose(){
   var o = this;
   o.base.FComponent.dispose.call(o)
   RMemory.freeHtml(o.hPanel);
   o.hLayoutCell = null;
   o.hLayoutRow = null;
   o.hPanel = null;
}
