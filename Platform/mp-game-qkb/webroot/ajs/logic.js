function FLgMenu(o){
   o = RClass.inherits(this, o, FContainer);
   o.viewId         = RClass.register(o, new TPtyInt('viewId'));
   o.ouid           = RClass.register(o, new TPtyStr('ouid'));
   o.properties     = RClass.register(o, new TPtyStr('properties'));
   o.isEditable     = RClass.register(o, new TPtyBool('isEditable'), false);
   o.stMenuNormal   = RClass.register(o, new TStyle('MenuNormal'));
   o.stMenuHover    = RClass.register(o, new TStyle('MenuHover'));
   o.stMenuSelect   = RClass.register(o, new TStyle('MenuSelect'));
   o.isSelected     = false;
   o.menus          = new TMap();
   o.lsnsClick      = new TListeners();
   o.lsnsEnter      = new TListeners();
   o.lsnsLeave      = new TListeners();
   o.hLeft          = null;
   o.hRight         = null;
   o.oeBuild        = FLgMenu_oeBuild;
   o.onBuildPanel   = FLgMenu_onBuildPanel;
   o.onEnter        = FLgMenu_onEnter;
   o.onLeave        = FLgMenu_onLeave;
   o.onClick        = FLgMenu_onClick;
   o.select         = FLgMenu_select;
   o.selectSubMenu  = FLgMenu_selectSubMenu;
   o.push           = FLgMenu_push;
   o.buildSubMenus  = FLgMenu_buildSubMenus;
   return o;
}
function FLgMenu_oeBuild(e){
   var o = this;
   var r = o.base.FContainer.oeBuild.call(o, e);
   if(e.isBefore()){
      o.hLeft.width = 1;
      var hp = o.hPanel;
	      hp.className = o.style('MenuNormal');
	      o.hText = RBuilder.appendText(hp, o.label);
	      o.hRight.width = 1;
   }
   return r;
}
function FLgMenu_onBuildPanel(){
   var o = this;
   o.hLeft = RBuilder.create(null, 'TD');
   o.hPanel = RBuilder.create(null, 'TD');
   o.hRight = RBuilder.create(null, 'TD');
}
function FLgMenu_onEnter(){
   var o = this;
	   if(!o.isSelected){
	      o.hPanel.className = o.style('MenuHover');
	   }
   o.lsnsEnter.process(o);
}
function FLgMenu_onLeave(){
   var o = this;
	   if(!o.isSelected){
	      o.hPanel.className = o.style('MenuNormal');
	   }
   o.lsnsLeave.process(o);
}
function FLgMenu_onClick(e, s){
   var o = this;
      o.lsnsClick.process(e, RObject.nvl(s, o));
      o.menuBar.select(this);
}
function FLgMenu_select(s){
   var o = this;
   var b = o.menuBar;
   o.isSelected = s;
   var mc = b.menus.count;
   o.hPanel.className = o.style(s ? 'MenuSelect' : 'MenuNormal');
   o.hLeft.style.display = (o.index == 0) ? 'none' : 'block';
   o.hRight.style.display = (o.index == mc-1) ? 'block' : 'none';
   if(o.index == 0){
   }
   if(o.index == mc-1){
   }
   var cs = o.components;
   if(cs){
      for(var n=0; n<cs.count; n++){
         cs.value(n).setVisible(s);
      }
   }
}
function FLgMenu_selectSubMenu(s){
   var o = this;
   var cs = o.components;
   if(cs){
      for(var n=0; n<cs.count; n++){
         var c = cs.value(n);
         c.select(s == c);
      }
   }
}
function FLgMenu_push(c){
   var o = this;
   o.base.FContainer.push.call(o, c);
   if(RClass.isClass(c, FLgSubMenu)){
      c.menu = o;
      c.index = o.menus.count;
      c.lsnsClick.register(o, o.onClick);
      o.menus.set(c.name, c);
   }
}
function FLgMenu_buildSubMenus(hSubMenu){
   var o = this;
   var ms = o.menus;
   for(var n=0; n<ms.count; n++){
      var m = ms.value(n);
      hSubMenu.appendChild(m.hPanel);
   }
}
function FLgMenuBar(o){
   o = RClass.inherits(this, o, FContainer);
   o.hTop             = null;
   o.hLine            = null;
   o.hBottom          = null;
   o.hSheets          = null;
   o.menus            = new TMap();
   o.selected         = null;
   o.lsnsClick        = new TListeners();
   o.oeBuild          = FLgMenuBar_oeBuild;
   o.onBuildPanel     = FLgMenuBar_onBuildPanel;
   o.onClick          = FLgMenuBar_onClick;
   o.findMenu         = FLgMenuBar_findMenu;
   o.select           = FLgMenuBar_select;
   o.selectIndex      = FLgMenuBar_selectIndex;
   o.selectAttribute  = FLgMenuBar_selectAttribute;
   o.menu             = FLgMenuBar_menu;
   o.push             = FLgMenuBar_push;
   o.buildSubMenus    = FLgMenuBar_buildSubMenus;
   return o;
}
function FLgMenuBar_oeBuild(e){
   var o = this;
   o.base.FContainer.oeBuild.call(o, e);
   if(e.isBefore()){
      o.hRows = RBuilder.append(o.hPanel, 'TBODY');
      o.hRow = RBuilder.append(o.hRows, 'TR');
   }else if(e.isAfter()){
      var ms = o.menus;
      for(var n=0; n<ms.count; n++){
         var m = ms.value(n);
         o.hRow.appendChild(m.hLeft);
         o.hRow.appendChild(m.hPanel);
         o.hRow.appendChild(m.hRight);
      }
   }
}
function FLgMenuBar_onBuildPanel(){
   this.hPanel = RBuilder.newTable();
}
function FLgMenuBar_onClick(e, s){
   this.lsnsClick.process(e, RObject.nvl(s, this));
}
function FLgMenuBar_findMenu(name, value){
   var ms = this.menus;
   for(var n = 0; n<ms.count; n++){
      var m = ms.value(n);
      if(m[name] == value){
         return m;
      }
   }
}
function FLgMenuBar_select(m){
   var o = this;
   o.selected = m;
   for(var n=0; n<this.menus.count; n++){
      var cm = this.menus.value(n);
      cm.select(m == cm);
   }
}
function FLgMenuBar_selectIndex(n){
   var o = this;
   o.select(o.menus.value(n));
}
function FLgMenuBar_selectAttribute(name, value){
   var ms = this.menus;
   for(var n = 0; n<ms.count; n++){
     var m = ms.value(n);
     m.select(m[name] == value);
   }
}
function FLgMenuBar_menu(name){
   return this.menus.get(name);
}
function FLgMenuBar_push(c){
   var o = this;
   o.base.FContainer.push.call(o, c);
   if(RClass.isClass(c, FLgMenu)){
      c.lsnsClick.register(o, o.onClick);
      c.menuBar = o;
      c.index = o.menus.count;
      o.menus.set(c.name, c);
   }
}
function FLgMenuBar_buildSubMenus(hSubMenu){
   var o = this;
   var ms = o.menus;
   for(var n=0; n<ms.count; n++){
      ms.value(n).buildSubMenus(hSubMenu);
   }
}
function FLgMoveBar(o){
   o = RClass.inherits(this, o, FControl);
   o.active          = null;
   o.layers          = null;
   o.activeLayer     = null;
   o.onActiveProcess = FLgMoveBar_onActiveProcess;
   o.onMouseOver     = FLgMoveBar_onMouseOver;
   o.construct       = FLgMoveBar_construct;
   o.push            = FLgMoveBar_push;
   o.update          = FLgMoveBar_update;
   return o;
}
function FLgMoveBar_onActiveProcess(){
   var o = this;
   var al = o.activeLayer;
   if(al){
      var c = o.layers.count;
      for(var n=0; n<c; n++){
         o.layers.get(n).onMoving();
      }
   }
}
function FLgMoveBar_onMouseOver(s, e){
   var o = this;
   var al = o.activeLayer;
   if(al != s){
      o.activeLayer = s;
      var idx = o.layers.indexOf(s);
      var c = o.layers.count;
      for(var n=0; n<c; n++){
         var l = o.layers.get(n);
         l.isReturn = (n >= idx);
         l.reset();
      }
      o.active.status = EActive.Active;
   }
}
function FLgMoveBar_construct(){
   var o = this;
   var a = o.active = new TActive(o, o.onActiveProcess);
   a.interval = 1;
   a.status = EActive.Sleep;
   o.layers = new TList();
   RConsole.find(FActiveConsole).push(a);
}
function FLgMoveBar_push(c){
   var o = this;
   o.layers.push(c);
   c.x = c.hPanel.style.pixelLeft;
   o.linkEvent(c, 'onMouseOver', c.hPanel);
}
function FLgMoveBar_update(){
   var o = this;
   var c = o.layers.count;
   var w = o.hPanel.offsetWidth;
   var lw = (w - o.layerWidth) / (c - 1)
   for(var n=0; n<c; n++){
      var l = o.layers.get(n);
      l.endX = parseInt(lw + lw*n -o.layerWidth + 2 - n - n);
   }
}
function FLgMoveLayer(o){
   o = RClass.inherits(this, o, FControl);
   o.x          = 0;
   o.endX       = 0;
   o.step       = 1;
   o.isReturn   = true;
   o.onMoving   = FLgMoveLayer_onMoving;
   o.reset      = FLgMoveLayer_reset;
   o.link       = FLgMoveLayer_link;
   return o;
}
function FLgMoveLayer_onMoving(){
   var o = this;
   if(o.isReturn){
      var x = o.hPanel.style.pixelLeft;
      if(x != o.x){
         var absx = Math.abs(x - o.x);
         var step = Math.min(parseInt(o.step*o.step), absx);
         o.hPanel.style.pixelLeft += (x - o.x > 0) ? step : +step;
         o.step += 0.2;
         o.hPanel.filters.Alpha.opacity = 140 - 100/Math.abs(o.endX - o.x)*absx;
      }
   }else{
      var x = o.hPanel.style.pixelLeft;
      if(x != o.endX){
         var absx = Math.abs(x - o.endX);
         var step = Math.min(parseInt(o.step*o.step), absx);
         o.hPanel.style.pixelLeft -= (x - o.endX > 0) ? step : +step;
         o.step += 0.2;
         o.hPanel.filters.Alpha.opacity = 100/Math.abs(o.endX - o.x)*absx + 40;
      }
   }
}
function FLgMoveLayer_reset(){
   var o = this;
   o.step = 1;
}
function FLgMoveLayer_link(hp, src){
   var o = this;
   o.hPanel = hp;
   var h = o.hImg = RBuilder.append(hp, 'IMG');
   h.src = src;
}
function FLgSpliter(o){
   o = RClass.inherits(this, o, FControl, MLsnClick);
   o.hLayer             = null;
   o.drag               = false;
   o.dragX              = 0;
   o.dragY              = 0;
   o.direction          = null;
   o.ohSplitDown        = RClass.register(o, new HMouseDown('onSplitDown'), FLgSpliter_ohSplitDown);
   o.ohSplitMove        = RClass.register(o, new HMouseMove('onSplitMove'), FLgSpliter_ohSplitMove);
   o.ohSplitUp          = RClass.register(o, new HMouseUp('onSplitUp'), FLgSpliter_ohSplitUp);
   o.ohSplitDoubleClick = RClass.register(o, new HDoubleClick('onSplitDoubleClick'), FLgSpliter_ohSplitDoubleClick);
   o.ohSplitButtonEnter = RClass.register(o, new HMouseEnter('onSplitButtonEnter'), FLgSpliter_ohSplitButtonEnter);
   o.ohSplitButtonLeave = RClass.register(o, new HMouseLeave('onSplitButtonLeave'), FLgSpliter_ohSplitButtonLeave);
   o.ohSplitButtonClick = RClass.register(o, new HMouseDown('onSplitButtonClick'), FLgSpliter_ohSplitButtonClick);
   o.construct          = FLgSpliter_construct;
   o.build              = FLgSpliter_build;
   o.link               = FLgSpliter_link;
   o.click              = FLgSpliter_click;
   o.dispose            = FLgSpliter_dispose;
   return o;
}
function FLgSpliter_ohSplitDown(e){
   var o = this;
   if(e.hSender != o.hDrag){
      return;
   }
   var hs = o.hSize;
   if(hs){
      if('none' == hs.style.display){
         return;
      }
   }
   var h = o.hLayer;
   var hd = o.hDrag;
   o.drag = true;
   o.dragX = event.offsetX;
   o.dragY = event.offsetY;
   var r = RHtml.rect(hd);
   h.style.display = 'block';
   h.style.pixelWidth = hd.offsetWidth;
   h.style.pixelHeight = hd.offsetHeight;
   h.style.pixelTop = r.top;
   h.style.pixelLeft = event.x - o.dragX;
   o.layerTop = h.style.pixelTop;
   o.layerLeft = h.style.pixelLeft;
   o.sizeHeight = o.hSize.offsetHeight;
   o.hDrag.setCapture();
}
function FLgSpliter_ohSplitMove(e){
   var o = this;
   if(!e){
      e = event;
   }
   if(o.drag){
      if(EDirection.Vertical == o.direction){
         var y = e.y - o.dragY;
         if(y > 40){
            o.hLayer.style.pixelTop = y;
         }
      }else if(EDirection.Horizontal == o.direction){
         var x = e.x - o.dragX;
         if(x > 40){
            o.hLayer.style.pixelLeft = x;
         }
      }
   }
}
function FLgSpliter_ohSplitUp(e){
   var o = this;
   if(o.drag){
      var hl = o.hLayer;
      if(EDirection.Vertical == o.direction){
         o.hSize.height = o.sizeHeight + (hl.style.pixelTop - o.layerTop);
      }else if(EDirection.Horizontal == o.direction){
         o.hSize.width = hl.style.pixelLeft;
      }
      hl.style.display = 'none';
      o.hDrag.releaseCapture();
      o.drag = false;
   }
}
function FLgSpliter_ohSplitDoubleClick(){
   this.click();
}
function FLgSpliter_ohSplitButtonEnter(){
}
function FLgSpliter_ohSplitButtonLeave(){
}
function FLgSpliter_ohSplitButtonClick(){
   this.click();
}
function FLgSpliter_construct(){
   this.direction = EDirection.Horizontal;
}
function FLgSpliter_build(){
   var o = this;
   var hf = o.hForm = RBuilder.appendTable(o.hDrag);
   hf.height = 36;
   hc = o.hButton = hf.insertRow().insertCell()
   hc.bgColor = o.dragBackgroundColor;
   hc.style.cursor = 'hand';
   o.hButtonIcon = RBuilder.appendIcon(hc, 'ctl.FSpliter_Left');
   o.attachEvent('onSplitButtonEnter', hc, o.ohSplitButtonEnter);
   o.attachEvent('onSplitButtonLeave', hc, o.ohSplitButtonLeave);
   o.attachEvent('onSplitButtonClick', hc, o.ohSplitButtonClick);
}
function FLgSpliter_link(hDrag, hSize){
   var o = this;
   var h = o.hDrag = hDrag;
   o.attachEvent('onSplitDown', h, o.ohSplitDown);
   o.attachEvent('onSplitMove', h, o.ohSplitMove);
   o.attachEvent('onSplitUp', h, o.ohSplitUp);
   o.attachEvent('onSplitDoubleClick', h, o.ohSplitDoubleClick);
   if(EDirection.Vertical == o.direction){
      h.style.cursor = 'N-resize'
   }else if(EDirection.Horizontal == o.direction){
      h.style.cursor = 'E-resize'
   }
   o.hSize = hSize;
   var h = o.hLayer = RBuilder.append(null, 'DIV');
   h.style.position = 'absolute';
   h.style.backgroundColor = '#a5eaea';
   h.style.border = '1 solid #70eaea';
   h.style.display = 'none';
   h.zIndex = 30000;
   RBuilder.appendEmpty(h, 1, 1);
}
function FLgSpliter_click(){
   var o = this;
   var hs = o.hSize;
   if(hs){
      if('none' == hs.style.display){
         hs.style.display = 'block';
         if(o.hButtonIcon){
            o.hButtonIcon.src = RRes.iconPath('ctl.FSpliter_Left');
         }
      }else{
         hs.style.display = 'none';
         if(o.hButtonIcon){
            o.hButtonIcon.src = RRes.iconPath('ctl.FSpliter_Right');
         }
      }
   }
}
function FLgSpliter_dispose(){
   var o = this;
   o.base.FControl.dispose.call(o);
   o.hDrag = null;
   o.hLayer = null;
   o.hSize = null;
   o.hForm = null;
   o.hButton = null;
   o.hButtonIcon = null;
}
function FLgSubMenu(o){
   o = RClass.inherits(this, o, FContainer);
   o.viewId         = RClass.register(o, new TPtyInt('viewId'));
   o.ouid           = RClass.register(o, new TPtyStr('ouid'));
   o.type           = RClass.register(o, new TPtyStr('type'));
   o.properties     = RClass.register(o, new TPtyStr('properties'));
   o.stButtonNormal = RClass.register(o, new TStyle('ButtonNormal'));
   o.stButtonHover  = RClass.register(o, new TStyle('ButtonHover'));
   o.stButtonSelect = RClass.register(o, new TStyle('ButtonSelect'));
   o.isSelected     = false;
   o.hasDrop        = false;
   o.lsnsClick      = new TListeners();
   o.oeBuild        = FLgSubMenu_oeBuild;
   o.onBuildPanel   = FLgSubMenu_onBuildPanel;
   o.onEnter        = FLgSubMenu_onEnter;
   o.onLeave        = FLgSubMenu_onLeave;
   o.onClick        = FLgSubMenu_onClick;
   o.select         = FLgSubMenu_select;
   o.push           = FLgSubMenu_push;
   return o;
}
function FLgSubMenu_oeBuild(e){
   var o = this;
   var r = o.base.FContainer.oeBuild.call(o, e);
   if(e.isAfter()){
      var hp = o.hPanel;
      hp.style.position = 'relative';
      var hf = o.hForm = RBuilder.appendTable(hp, o.style('ButtonNormal'));
      var hr = hf.insertRow();
      var hc = hr.insertCell();
      hc.innerText = o.label;
      hc.style.whiteSpace = 'nowrap';
      if(o.hasDrop){
         var hfp = o.hFloatPanel = RBuilder.appendDiv(hp);
         hfp.style.position = 'absolute';
         hfp.style.pixeLeft = 0;
         hfp.style.display = 'none';
         hfp.style.border = '1 solid #77AFEE';
         hfp.style.backgroundColor = '#FFFFFF';
         hfp.style.padding = 8;
         var hff = o.hFloatForm = RBuilder.appendTable(hfp);
         var hfr = hff.insertRow();
         var cs = o.components;
         if(cs){
            var ct = cs.count;
            for(var n = 0; n < ct; n++){
               var c = cs.value(n);
               if(RClass.isClass(c, FLgSubMenuGroup)){
                  c.lsnsClick.register(o, o.onClick);
                  var hc = hfr.insertCell()
                  c.setPanel(hc);
               }
            }
         }
      }
   }
   return r;
}
function FLgSubMenu_onBuildPanel(){
   this.hPanel = RBuilder.create(null, 'TD');
}
function FLgSubMenu_onEnter(){
   var o = this;
   if(!o.isSelected){
      o.hForm.className = o.style('ButtonHover');
   }
   if(o.hasDrop){
      var hfp = o.hFloatPanel;
      hfp.style.pixelTop = o.hPanel.offsetHeight;
      hfp.style.display = 'block';
   }
}
function FLgSubMenu_onLeave(){
   var o = this;
   if(!o.isSelected){
      o.hForm.className = o.style('ButtonNormal');
   }
   if(o.hasDrop){
      o.hFloatPanel.style.display = 'none';
   }
}
function FLgSubMenu_onClick(e, s){
   var o = this;
   if(RClass.isClass(s, FLgSubMenu)){
      if(o.hasDrop){
         o.hFloatPanel.style.display = 'none';
      }
   }
   o.lsnsClick.process(e, RObject.nvl(s, o));
}
function FLgSubMenu_select(v){
   var o = this;
   o.isSelected = v;
   o.hPanel.className = v ? o.style('ButtonSelect') : o.style('ButtonNormal');
}
function FLgSubMenu_push(c){
   var o = this;
   o.base.FContainer.push.call(o, c);
   if(RClass.isClass(c, FLgSubMenuGroup)){
      o.hasDrop = true;
   }
}
function FLgSubMenuGroup(o){
   o = RClass.inherits(this, o, FContainer);
   o.viewId         = RClass.register(o, new TPtyInt('viewId'));
   o.ouid           = RClass.register(o, new TPtyStr('ouid'));
   o.type           = RClass.register(o, new TPtyStr('type'));
   o.properties     = RClass.register(o, new TPtyStr('properties'));
   o.stButtonNormal = RClass.register(o, new TStyle('ButtonNormal'));
   o.stButtonHover  = RClass.register(o, new TStyle('ButtonHover'));
   o.stButtonSelect = RClass.register(o, new TStyle('ButtonSelect'));
   o.lsnsClick      = new TListeners();
   o.oeBuild        = FLgSubMenuGroup_oeBuild;
   o.onBuildPanel   = FLgSubMenuGroup_onBuildPanel;
   o.onEnter        = FLgSubMenuGroup_onEnter;
   o.onLeave        = FLgSubMenuGroup_onLeave;
   o.onClick        = FLgSubMenuGroup_onClick;
   return o;
}
function FLgSubMenuGroup_oeBuild(e){
   var o = this;
   o.base.FContainer.oeBuild.call(o, e);
   var hp = o.hPanel;
   if(e.isAfter()){
      var cs = o.components;
      for(var n=0; n<cs.count; n++){
         var c = cs.value(n);
         if(RClass.isClass(c, FLgSubMenuItem)){
            c.lsnsClick.register(o, o.onClick);
            var hc = hp.insertRow().insertCell();
            c.setPanel(hc);
         }
      }
   }
   return EEventStatus.Continue;
}
function FLgSubMenuGroup_onBuildPanel(){
   var o = this;
   var h = o.hPanel = RBuilder.create(null, 'Table');
}
function FLgSubMenuGroup_onEnter(){
   var o = this;
}
function FLgSubMenuGroup_onLeave(){
   var o = this;
}
function FLgSubMenuGroup_onClick(e, s){
   var o = this;
   o.lsnsClick.process(e, RObject.nvl(s, o));
}
function FLgSubMenuItem(o){
   o = RClass.inherits(this, o, FControl);
   o.page           = RClass.register(o, new TPtyStr('page'));
   o.properties     = RClass.register(o, new TPtyStr('properties'));
   o.stButtonNormal = RClass.register(o, new TStyle('ButtonNormal'));
   o.stButtonHover  = RClass.register(o, new TStyle('ButtonHover'));
   o.stButtonSelect = RClass.register(o, new TStyle('ButtonSelect'));
   o.lsnsClick      = new TListeners();
   o.oeBuild        = FLgSubMenuItem_oeBuild;
   o.onBuildPanel   = FLgSubMenuItem_onBuildPanel;
   o.onEnter        = FLgSubMenuItem_onEnter;
   o.onLeave        = FLgSubMenuItem_onLeave;
   o.onClick        = FLgSubMenuItem_onClick;
   return o;
}
function FLgSubMenuItem_oeBuild(event){
   var o = this;
   o.base.FControl.oeBuild.call(o, event);
   var hp = o.hPanel;
   var hf = o.hForm = RBuilder.appendTable(hp);
   hf.style.border = '1 solid #FFFFFF';
   hf.style.backgroundColor = '#FFFFFF';
   var hr = hf.insertRow();
   if(o.icon){
      var hip = o.hIconPanel = hr.insertCell();
      o.hIcon = RBuilder.appendIcon(hip, o.icon);
   }
   if(o.label){
      var ht = o.hText = hr.insertCell();
      ht.style.padding = '2 12';
      ht.style.cursor = 'hand';
      ht.noWrap = true;
      ht.innerText = o.label;
   }
   return EEventStatus.Stop;
}
function FLgSubMenuItem_onBuildPanel(){
   this.hPanel = RBuilder.create(null, 'TD');
}
function FLgSubMenuItem_onEnter(){
   var o = this;
   var hf = o.hForm;
   hf.style.border = '1 solid #ADDBEF';
   hf.style.backgroundColor = '#F0F7FD';
}
function FLgSubMenuItem_onLeave(){
   var o = this;
   var hf = o.hForm;
   hf.style.border = '1 solid #FFFFFF';
   hf.style.backgroundColor = '#FFFFFF';
}
function FLgSubMenuItem_onClick(e){
   var o = this;
   o.lsnsClick.process(e, o);
}
var RLogic = new function(){
   var o = this;
   o.showUser   = RLogic_showUser;
   o.makeFriend = RLogic_makeFriend;
   RMemory.register('RLogic', o);
   return o;
}
function RLogic_showUser(c){
   if(!RString.isEmpty(c)){
      var u = top.RContext.context('/psn/home/User.wa?code=' + c);
      var w = screen.width * 0.90;
      var h = screen.height * 0.90;
      var l = (screen.width - w)/2;
      var t = (screen.height - h)/2 - 20;
      var s = RString.format('left={0},top={1},width={2},height={3},toolbar=no,location=no,directories=no,status=no,menubar=no,resizable=yes,scrollbars=yes,dependent=yes', l, t, w, h);
      window.open(u, '_top', s);
   }
}
function RLogic_makeFriend(c){
   if(!RString.isEmpty(c)){
      var u = top.RContext.context('/psn/usr/friend/Friend.wa?do=insert&code=' + c);
      var w = 400;
      var h = 200;
      var l = (screen.width - w)/2;
      var t = (screen.height - h)/2 - 20;
      var s = RString.format('left={0},top={1},width={2},height={3},toolbar=no,location=no,directories=no,status=no,menubar=no,resizable=no,scrollbars=no,dependent=yes', l, t, w, h);
      window.open(u, '_blank', s);
   }
}
