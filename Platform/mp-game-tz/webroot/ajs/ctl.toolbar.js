function FDsToolBar(o){
   o = RClass.inherits(this, o, FToolBar);
   o.btnFetch    = null;
   o.btnSearch   = null;
   o.btnLov      = null;
   o.btnZoom     = null;
   o.btnInsert   = null;
   o.btnUpdate   = null;
   o.btnDelete   = null;
   o.btnFirst    = null;
   o.btnPrior    = null;
   o.btnNext     = null;
   o.btnLast     = null;
   o.btnAction   = null;
   o.dsControl   = null;
   o.oeBuild     = FDsToolBar_oeBuild;
   o.onDsChanged = FDsToolBar_onDsChanged;
   o.register    = FDsToolBar_register;
   o.setStatus   = FDsToolBar_setStatus;
   o.dispose     = FDsToolBar_dispose;
   return o;
}
function FDsToolBar_oeBuild(event){
   var o = this;
   o.base.FToolBar.oeBuild.call(o, event);
   if(event.isAfter()){
      var count = o.controls.count;
      for(var n=0; n<count; n++){
         var c = o.controls.value(n);
         if(RClass.isClass(c, FToolButton)){
            o.register(c.type, c);
         }
      }
   }
}
function FDsToolBar_onDsChanged(a, b, c, d){
   var dc = RConsole.find(FFocusConsole).findClass(MDataset);
   if(dc){
      var o = this;
      o.setStatus(dc, o.btnFetch, EDataAction.Fetch);
      o.setStatus(dc, o.btnSearch, EDataAction.Search);
      o.setStatus(dc, o.btnLov, EDataAction.Lov);
      o.setStatus(dc, o.btnZoom, EDataAction.Zoom);
      o.setStatus(dc, o.btnInsert, EDataAction.Insert);
      o.setStatus(dc, o.btnUpdate, EDataAction.Update);
      o.setStatus(dc, o.btnDelete, EDataAction.Delete);
      o.setStatus(dc, o.btnFirst, EDataAction.First);
      o.setStatus(dc, o.btnPrior, EDataAction.Prior);
      o.setStatus(dc, o.btnNext, EDataAction.Next);
      o.setStatus(dc, o.btnLast, EDataAction.Last);
      o.setStatus(dc, o.btnAction, EDataAction.Action);
   }
}
function FDsToolBar_register(t, b){
   var o = this;
   if(EDataAction.Fetch == t){
      o.btnFetch = b;
   }else if(EDataAction.Search == t){
      o.btnSearch = b;
   }else if(EDataAction.Lov == t){
      o.btnLov = b;
   }else if(EDataAction.Zoom == t){
      o.btnZoom = b;
   }else if(EDataAction.Insert == t){
      o.btnInsert = b;
   }else if(EDataAction.Update == t){
      o.btnUpdate = b;
   }else if(EDataAction.Delete == t){
      o.btnDelete = b;
   }else if(EDataAction.First == t){
      o.btnFirst = b;
   }else if(EDataAction.Prior == t){
      o.btnPrior = b;
   }else if(EDataAction.Next == t){
      o.btnNext = b;
   }else if(EDataAction.Last == t){
      o.btnLast = b;
   }else if(EDataAction.Action == t){
      o.btnAction = b;
   }
}
function FDsToolBar_setStatus(d, b, a){
   if(b && b.modeDisplay){
      var v = d.testStatus(a, ETest.Display);
      b.psVisible(v);
      if(v){
         b.psEnable(d.testStatus(a, ETest.Enable));
      }
   }
}
function FDsToolBar_dispose(){
   var o = this;
   o.base.FToolBar.dispose.call(o);
}
function FHistoryBar(o){
   o = RClass.inherits(this, o, FContainer, MDisplayAble, MTop);
   o.stButton        = RClass.register(o, new TStyle('Button'));
   o.historyIndex    = -1;
   o.buttons         = new TList();
   o.lsnsButtonClick = new TListeners();
   o.hLine           = null;
   o.oeBuild         = FHistoryBar_oeBuild;
   o.onBuildPanel    = FHistoryBar_onBuildPanel;
   o.currentButton   = FHistoryBar_currentButton;
   o.button          = FHistoryBar_button;
   o.syncButton      = FHistoryBar_syncButton;
   o.nextButton      = FHistoryBar_nextButton;
   o.clickButton     = FHistoryBar_clickButton;
   o.selectButton    = FHistoryBar_selectButton;
   o.popup           = FHistoryBar_popup;
   o.clear           = FHistoryBar_clear;
   o.dispose         = FHistoryBar_dispose;
   return o;
}
function FHistoryBar_oeBuild(e){
   var o = this;
   o.base.FContainer.oeBuild.call(o, e);
   var b = e.builder;
   if(e.isBefore()){
      var hc = o.hCaption = o.hLine.insertCell();
      hc.width = 80;
      hc.align = 'center';
      hc.style.fontWeight="BOLD";
      hc.innerText = RContext.get('FHistoryBar:caption');
   }else if(e.isAfter()){
      if(EAlign.Right != o.align){
         var hTd = o.hLastCell = b.create('TD');
         b.appendEmpty(hTd);
         o.hLine.appendChild(hTd);
      }
   }
}
function FHistoryBar_onBuildPanel(){
   var o = this;
   var hp = o.hPanel = RBuilder.newTable(o.hParent);
   hp.width = '100%';
   o.hLine = hp.insertRow();
}
function FHistoryBar_currentButton(){
   var o = this;
   return o.buttons.get(o.historyIndex);
}
function FHistoryBar_button(name){
   return this.buttons.get(name);
}
function FHistoryBar_syncButton(p){
   var o = this;
   var bs = o.buttons;
   var b = bs.get(p);
   if(!b){
      var c = bs.count;
      var hl = o.hLine;
      for(var n=c; n<=p; n++){
         var hs = null;
         if(c > 0){
            hs = hl.insertCell(o.hLastCell.cellIndex);
            hs.width = '16';
            RBuilder.appendIcon(hs, 'ctl.FHistoryBar_Spliter');
         }
         var hc = hl.insertCell(o.hLastCell.cellIndex);
         hc.width = 1;
         b = RControl.create(FHistoryButton, hc);
         b.bar = o;
         b.index = bs.count;
         b.hSplit = hs;
         bs.push(b);
      }
   }
   return b;
}
function FHistoryBar_nextButton(){
   var o = this;
   var n = ++o.historyIndex;
   var b = o.syncButton(n);
   o.selectButton(b);
   var c = o.buttons.count;
   for(var i=n+1; i<c; i++){
      o.buttons.get(i).setVisible(false);
   }
   return b;
}
function FHistoryBar_clickButton(b){
   var o = this;
   o.lsnsButtonClick.process(b);
   o.selectButton(b);
}
function FHistoryBar_selectButton(b){
   var o = this;
   b.select(true);
   b.setVisible(true);
   o.historyIndex = o.buttons.indexOf(b);
   var bs = o.buttons;
   var c = bs.count;
   for(var n=0; n<c; n++){
      var s = bs.get(n);
      if(s != b && s.isSelected){
         s.select(false);
      }
   }
}
function FHistoryBar_popup(l){
   var o = this;
   var n = Math.max(o.historyIndex - moNvl(l, 1), 0);
   var b = o.buttons.get(n);
   o.clickButton(b);
   var bc = o.buttons.count;
   for(++n; n<bc; n++){
      o.buttons.get(n).setVisible(false);
   }
   return b;
}
function FHistoryBar_clear(){
   var o = this;
   var bs = o.buttons;
   var c = bs.count;
   for(var n=0; n<c; n++){
      bs.get(n).setVisible(n);
   }
}
function FHistoryBar_dispose(){
   var o = this;
   o.base.FContainer.dispose.call(o);
   o.hLine = null;
   o.hCaption = null;
   o.hParent = null;
}
function FHistoryButton(o){
   o = RClass.inherits(this, o, FControl, MLsnClick);
   o.onButtonClick   = RClass.register(o, new HClick('onButtonClick'), FHistoryButton_onButtonClick);
   o.stButton        = RClass.register(o, new TStyle('Button'));
   o.stLabel         = RClass.register(o, new TStyle('Label'));
   o.stHover         = RClass.register(o, new TStyle('Hover'));
   o.stSelect        = RClass.register(o, new TStyle('Select'));
   o.icon            = 'ctl.form-table';
   o.store           = new Object();
   o.isSelected      = false;
   o.hButton         = null;
   o.hButtonLine     = null;
   o.hButtonPanel    = null;
   o.hIcon           = null;
   o.hText           = null;
   o.oeBuild         = FHistoryButton_oeBuild;
   o.onBuildPanel    = FHistoryButton_onBuildPanel;
   o.onEnter         = FHistoryButton_onEnter;
   o.onLeave         = FHistoryButton_onLeave;
   o.onMouseDown     = FHistoryButton_onMouseDown;
   o.onMouseUp       = FHistoryButton_onMouseUp;
   o.setIcon         = FHistoryButton_setIcon;
   o.setText         = FHistoryButton_setText;
   o.setHint         = FHistoryButton_setHint;
   o.select          = FHistoryButton_select;
   o.setVisible      = FHistoryButton_setVisible;
   o.dispose         = FHistoryButton_dispose;
   return o;
}
function FHistoryButton_oeBuild(e){
   var o = this;
   o.base.FControl.oeBuild.call(o, e);
   var b = e.builder;
   var h = o.hPanel;
   var hb = o.hButton = b.appendTable(o.hPanel);
   var hl = o.hButtonLine = o.hButton.insertRow();
   var hc = o.hButtonPanel = hl.insertCell();
   o.attachEvent('onButtonClick', hc);
   o.hIcon = b.appendIcon(hc, o.icon);
   o.hText = b.append(hc, 'SPAN');
   o.hText.style.whiteSpace = 'nowrap';
   return EEventStatus.Stop;
}
function FHistoryButton_onBuildPanel(){
   this.hPanel = RBuilder.create(null, 'TD', this.style('Button'));
}
function FHistoryButton_onEnter(e){
   var o = this;
   if(!o.isSelected){
      o.hPanel.className = o.style('Hover');
      o.hText.style.fontWeight="bold";
      o.hText.style.color="#376490";
      o.hPanel.title = "点击切换";
      o.hText.style.textDecoration = 'underline';
   }
}
function FHistoryButton_onLeave(e){
   var o = this;
   o.hPanel.title = "";
   if(!o.isSelected){
      o.hPanel.className = o.style('Button');
      o.hText.style.fontWeight="bold";
      o.hText.style.color="#000000";
      o.hText.style.textDecoration = 'none';
   }
}
function FHistoryButton_onMouseDown(e){
   if(!this.disabled){
   }
}
function FHistoryButton_onMouseUp(e){
   var o = this;
   if(!o.disabled){
      o.hPanel.className = o.style('Hover');
   }
}
function FHistoryButton_onButtonClick(e){
   this.bar.clickButton(this);
}
function FHistoryButton_click(){
   this.onClick();
}
function FHistoryButton_setIcon(c){
   this.hIcon.src = RRes.iconPath(c);
}
function FHistoryButton_setText(t){
   this.hText.innerText = t;
}
function FHistoryButton_setHint(s){
   this.hText.title = s;
}
function FHistoryButton_select(v){
   var o = this;
   o.isSelected = v;
   if(v){
	  o.hPanel.className = o.style('Select');
      o.hText.style.fontWeight="bold";
      o.hText.style.color="#376490";
      o.hText.style.textDecoration = 'none';
   }else{
	   o.hPanel.className = o.style('Button');
	   o.hText.style.fontWeight="bold";
	   o.hText.style.color="#000000";
   }
}
function FHistoryButton_setVisible(v){
   var o = this;
   if(o.hSplit){
      o.hSplit.style.display = v ? 'block' : 'none';
   }
   o.hParent.style.display = v ? 'block' : 'none';
}
function FHistoryButton_dispose(){
   var o = this;
   o.base.FControl.dispose.call(o);
   RMemory.freeHtml(o.hSplit);
   o.hLine = null;
   o.hIcon = null;
   o.hText = null;
}
function FMenuBar(o, create){
   if(!create){return this;}
   o = IClass.inherits(this, o, FControl, MDocument);
   o.service         = 'menu.xml';
   o.focusNode       = null;
   o.isLoading       = false;
   o.indent          = 16;
   o.nodes           = new TList();
   o.allNodes        = new TList();
   o.types           = new TMap();
   o.onNodeLoaded    = null;
   o.onNodeClick     = null;
   o.onNodeDblClick  = null;
   o.onBuild         = FMenuBar_onBuild;
   o.onLoaded        = FMenuBar_onLoaded;
   o.connect         = FMenuBar_connect;
   o.release         = FMenuBar_release;
   o.dispose         = FMenuBar_dispose;
   return this;
}
function FMenuBar_onBuild(builder){
   var doc = builder.document;
   this.hBody = doc.createDiv();
   this.hBody.className = 'menu_panel';
   this.hParent.insertBefore(this.hBody);
   builder.hParent = this.hBody;
}
function FMenuBar_onLoaded(cnn){
   var doc = cnn.document;
   if(doc && doc.node){
      IControl.load(this, doc.node);
      this.build();
   }
}
function FMenuBar_dispose(){
   var o = this;
   o.base.FControl.dispose.call(o);
   RMemory.freeHtml(o.hBody);
   RMemory.freeHtml(o.hParent);
   o.hBody = null;
   o.hParent = null;
}
function FMenuBar_connect(type, action, attrs){
   var doc = new TXmlDocument();
   var root = doc.root();
   root.set('type', type);
   root.set('action', action);
   root.create('Attributes').value = attrs;
   var self = this;
   var cnn = new TXmlCnn();
   cnn.onLoad = function(){self.onLoaded(cnn)};
   cnn.send(this.service, doc);
}
function FMenuBar_release(){
   var nodes = this.allNodes;
   for(var n=0; n<nodes.length; n++){
      var node = nodes[n];
      node.release();
   }
   this.allNodes = null;
   this.allNodesUuid = null;
   this.allNodesProperty = null;
   this.allNodesPropertyExtend = null;
   this.nodes = null;
   return true;
}
function FMenuButton(o){
   o = RClass.inherits(this, o, FControl, MMenuButton);
   o.action        = RClass.register(o, new TPtyStr('action', null));
   o.target        = RClass.register(o, new TPtyStr('target', null));
   o.page          = RClass.register(o, new TPtyStr('page'));
   o.hotkey        = RClass.register(o, new TPtyStr('hotkey'));
   o.method        = RClass.register(o, new TPtyStr('method'));
   o.icon          = RClass.register(o, new TPtyStr('icon', null));
   o.iconDisable   = RClass.register(o, new TPtyStr('iconDisable', null));
   o.attributes    = RClass.register(o, new TPtyStr('attributes'));
   o.styleButton   = RClass.register(o, new TStyle('Button'));
   o.styleLabel    = RClass.register(o, new TStyle('Label'));
   o.styleDisable  = RClass.register(o, new TStyle('Disable'));
   o.styleHover    = RClass.register(o, new TStyle('Hover'));
   o.stylePress    = RClass.register(o, new TStyle('Press'));
   o.disabled      = false;
   o.hButton       = null;
   o.hButtonLine   = null;
   o.hButtonPanel  = null;
   o.hIcon         = null;
   o.hText         = null;
   o.oeBuild       = FMenuButton_oeBuild;
   o.oeEnable      = FMenuButton_oeEnable;
   o.oeDisable     = FMenuButton_oeDisable;
   o.onBuildPanel  = FMenuButton_onBuildPanel;
   o.onEnter       = FMenuButton_onEnter;
   o.onLeave       = FMenuButton_onLeave;
   o.onMouseDown   = FMenuButton_onMouseDown;
   o.onMouseUp     = FMenuButton_onMouseUp;
   o.click         = FMenuButton_click;
   o.dispose       = FMenuButton_dispose;
   return o;
}
function FMenuButton_oeBuild(e){
   var o = this;
   o.base.FControl.oeBuild.call(o, e);
   var h = o.hPanel;
   o.hButton = RBuilder.appendTable(o.hPanel, o.style('Button'));
   var hLine = o.hButtonLine = o.hButton.insertRow();
   var hCel = hLine.insertCell();
   hCel.noWrap = 'true';
   if(o.icon){
      o.hIcon = RBuilder.appendIcon(hCel, o.icon);
   }
   if(o.label){
      o.hLabel = RBuilder.appendText(hCel, (o.hIcon ? '&nbsp;' : '') + o.label);
      o.hLabel.className = o.style('Label');
   }
   return EEventStatus.Stop;
}
function FMenuButton_onBuildPanel(){
   this.hPanel = RBuilder.create(null, 'DIV');
}
function FMenuButton_oeEnable(e){
   var o = this;
   o.base.FControl.oeEnable.call(o, e);
   o.hPanel.className = o.style('Button');
   if(o.iconDisable && o.icon){
      o.hIcon.src = RRes.iconPath(o.icon);
   }
   return EEventStatus.Stop;
}
function FMenuButton_oeDisable(e){
   var o = this;
   o.base.FControl.oeDisable.call(o, e);
   o.hPanel.className = o.style('Disable');
   if(o.iconDisable){
      o.hIcon.src = RRes.iconPath(o.iconDisable);
   }
   return EEventStatus.Stop;
}
function FMenuButton_onEnter(){
   var o = this;
   if(!o.disabled){
      o.hPanel.className = o.style('Hover');
   }
}
function FMenuButton_onLeave(){
   var o = this;
   if(!o.disabled){
      o.hPanel.className = o.style('Panel');
   }
}
function FMenuButton_onMouseDown(){
   var o = this;
   if(!o.disabled){
      o.hPanel.className = o.style('Press');
      o.click();
   }
}
function FMenuButton_onMouseUp(){
   var o = this;
   if(!o.disabled){
      o.hPanel.className = o.style('Hover');
   }
}
function FMenuButton_click(){
   var o = this;
   if(!o.disabled){
      RConsole.find(FFocusConsole).blur();
      if(o.action){
         eval(o.action);
      }
      if(o.page || o.method){
         var form = RHtml.form(o.hButton);
         var p = RPage.parse(o.page);
         if(o.method){
            p.action = o.method;
         }
         p.split(o.attributes);
         p.post(form, o.target);
      }
   }
}
function FMenuButton_dispose(){
   var o = this;
   o.base.FControl.dispose.call(o);
   RMemory.freeHtml(o.hPanel);
   RMemory.freeHtml(o.hIcon);
   RMemory.freeHtml(o.hLabel);
   o.hPanel = null;
   o.hIcon = null;
   o.hLabel = null;
}
function FMenuButtonMenu(o){
   o = RClass.inherits(this, o, FControl, MEventFocus, MEventClick, MLsnClick);
   o.action       = RClass.register(o, new TPtyStr('action', null));
   o.target       = RClass.register(o, new TPtyStr('target', null));
   o.page         = RClass.register(o, new TPtyStr('page'));
   o.hotkey       = RClass.register(o, new TPtyStr('hotkey'));
   o.method       = RClass.register(o, new TPtyStr('method'));
   o.icon         = RClass.register(o, new TPtyStr('icon', null));
   o.iconDisable  = RClass.register(o, new TPtyStr('iconDisable', null));
   o.attributes   = RClass.register(o, new TPtyStr('attributes'));
   o.disabled     = false;
   o.hButton      = null;
   o.hButtonLine  = null;
   o.hButtonPanel = null;
   o.hIcon        = null;
   o.hText        = null;
   o.oeBuild      = FMenuButtonMenu_oeBuild;
   o.oeEnable     = FMenuButtonMenu_oeEnable;
   o.oeDisable    = FMenuButtonMenu_oeDisable;
   o.onBuildPanel = FMenuButtonMenu_onBuildPanel;
   o.onEnter      = FMenuButtonMenu_onEnter;
   o.onLeave      = FMenuButtonMenu_onLeave;
   o.onMouseDown  = FMenuButtonMenu_onMouseDown;
   o.onMouseUp    = FMenuButtonMenu_onMouseUp;
   o.onClick      = FMenuButtonMenu_onClick;
   o.construct    = FMenuButtonMenu_construct;
   o.dispose      = FMenuButtonMenu_dispose;
   return o;
}
function FMenuButtonMenu_oeBuild(event){
   var o = this;
   o.base.FControl.oeBuild.call(o, event);
   var h = o.hPanel;
   o.hButton = RBuilder.appendTable(o.hPanel, o.style('Button'));
   o.linkClickEvent(o.hButton);
   var hLine = o.hButtonLine = o.hButton.insertRow();
   var hCel = hLine.insertCell();
   if(o.icon){
      o.hIcon = RBuilder.appendIcon(hCel, o.icon);
   }
   if(o.label){
      o.hLabel = RBuilder.appendText(hCel, (o.hIcon ? '&nbsp;' : '') + o.label);
      o.hLabel.className = o.style('Label');
   }
   return EEventStatus.Stop;
}
function FMenuButtonMenu_onBuildPanel(){
   this.hPanel = RBuilder.create(null, 'DIV');
}
function FMenuButtonMenu_oeEnable(event){
   var o = this;
   o.base.FControl.oeEnable.call(o, event);
   o.hPanel.className = o.style('Button');
   if(o.iconDisable && o.icon){
      o.hIcon.src = RRes.iconPath(o.icon);
   }
   return EEventStatus.Stop;
}
function FMenuButtonMenu_oeDisable(event){
   var o = this;
   o.base.FControl.oeDisable.call(o, event);
   o.hPanel.className = o.style('Disable');
   if(o.iconDisable){
      o.hIcon.src = RRes.iconPath(o.iconDisable);
   }
   return EEventStatus.Stop;
}
function FMenuButtonMenu_onEnter(){
   var o = this;
   if(!o.disabled){
      o.hPanel.className = o.style('Hover');
   }
}
function FMenuButtonMenu_onLeave(){
   var o = this;
   if(!o.disabled){
      o.hPanel.className = o.style('Panel');
   }
}
function FMenuButtonMenu_onMouseDown(){
   var o = this;
   if(!o.disabled){
      o.hPanel.className = o.style('Press');
   }
}
function FMenuButtonMenu_onMouseUp(){
   var o = this;
   if(!o.disabled){
      o.hPanel.className = o.style('Hover');
   }
}
function FMenuButtonMenu_onClick(){
   var o = this;
   if(!o.disabled){
      RConsole.find(FFocusConsole).focus(o);
      if(o.action){
         eval(o.action);
      }
      if(o.page || o.method){
         var form = RHtml.form(o.hButton);
         var p = RPage.parse(o.page);
         if(o.method){
            p.action = o.method;
         }
         p.split(o.attributes);
         p.post(form, o.target);
      }
      o.processClick();
   }
}
function FMenuButtonMenu_construct(){
   var o = this;
   o.base.FControl.construct.call(o);
}
function FMenuButtonMenu_dispose(){
   var o = this;
   o.base.FControl.dispose.call(o);
   RMemory.freeHtml(o.hPanel);
   RMemory.freeHtml(o.hButton);
   o.hPanel = null;
   o.hIcon = null;
   o.hButton = null;
   o.hButtonLine = null;
   o.hLabel = null;
}
function FMenuButtonSplit(o){
   o = RClass.inherits(this, o, FControl, MMenuButton);
   o.styleUp      = RClass.register(o, new TStyle('Up'));
   o.styleDown    = RClass.register(o, new TStyle('Down'));
   o.disabled     = false;
   o.oeBuild      = FMenuButtonSplit_oeBuild;
   o.onBuildPanel = FMenuButtonSplit_onBuildPanel;
   o.dispose      = FMenuButtonSplit_dispose;
   return o;
}
function FMenuButtonSplit_oeBuild(e){
   var o = this;
   o.base.FControl.oeBuild.call(o, e);
   var h = o.hPanel;
   var hc = h.insertRow().insertCell();
   hc.className = o.style('Up');
   var hc = h.insertRow().insertCell();
   hc.className = o.style('Down');
   return EEventStatus.Stop;
}
function FMenuButtonSplit_onBuildPanel(){
   this.hPanel = RBuilder.newTable();
}
function FMenuButtonSplit_dispose(){
   var o = this;
   o.base.FControl.dispose.call(o);
   RMemory.freeHtml(o.hPanel);
   o.hPanel = null;
}
function FNavigatorBar(o){
   o = RClass.inherits(this, o, FControl, MContainer);
   o.lsnsButtonClickBefore = new TListeners();
   o.lsnsButtonClick       = new TListeners();
   o.onButtonClickBefore = FNavigatorBar_onButtonClickBefore;
   o.onButtonClick       = FNavigatorBar_onButtonClick;
   o.oeBuild             = FNavigatorBar_oeBuild;
   o.onBuildPanel        = FNavigatorBar_onBuildPanel;
   o.appendButton        = FNavigatorBar_appendButton;
   o.dispose             = FNavigatorBar_dispose;
   return o;
}
function FNavigatorBar_onButtonClickBefore(s){
   this.lsnsButtonClickBefore.process(s);
}
function FNavigatorBar_onButtonClick(s){
   this.lsnsButtonClick.process(s);
}
function FNavigatorBar_oeBuild(e){
   var o = this;
   o.base.FControl.oeBuild.call(o, e);
   var cs = o.components;
   if(e.isAfter() && cs){
      for(var n = 0; n < cs.count; n++){
         o.appendButton(cs.value(n));
      }
   }
   return EEventStatus.Continue;
}
function FNavigatorBar_onBuildPanel(){
   var o = this;
   o.hPanel = RBuilder.appendTable();
   o.hPanel.style.border = '0px solid #FFFFFF';
   o.hRow = o.hPanel.insertRow();
}
function FNavigatorBar_appendButton(c){
   var o = this;
   if(RClass.isClass(c, FNavigatorButton)){
      c.lsnsButtonClickBefore.register(o, o.onButtonClickBefore);
      c.lsnsButtonClick.register(o, o.onButtonClick);
      var hc = o.hRow.insertCell();
      hc.appendChild(c.hPanel);
   }
}
function FNavigatorBar_dispose(){
   var o = this;
   o.base.FControl.dispose.call(o);
   RMemory.freeHtml(o.hPanel);
   RMemory.freeHtml(o.hRow);
   o.hPanel = null;
   o.hRow = null;
}
function FNavigatorButton(o){
   o = RClass.inherits(this, o, FControl, MContainer, MFocus);
   o.icon            = RClass.register(o, new TPtyStr('icon'));
   o.page            = RClass.register(o, new TPtyStr('page'));
   o.action          = RClass.register(o, new TPtyStr('action'));
   o.lsnsButtonClickBefore = new TListeners();
   o.lsnsButtonClick       = new TListeners();
   o.oeBuild         = FNavigatorButton_oeBuild;
   o.onBuildPanel    = FNavigatorButton_onBuildPanel;
   o.drop            = FNavigatorButton_drop;
   o.onMouseOver     = FNavigatorButton_onMouseOver;
   o.onMouseDown     = FNavigatorButton_onMouseDown;
   o.onMouseOut      = FNavigatorButton_onMouseOut;
   o.dispose         = FNavigatorButton_dispose;
   return o;
}
function FNavigatorButton_oeBuild(e){
   var o = this;
   o.base.FControl.oeBuild.call(o, e);
   if(e.isAfter()){
      var hr = o.hPanel.insertRow();
      if(o.icon){
         var hc = hr.insertCell();
         hc.width = 20;
         o.hIcon = RBuilder.appendIcon(hc, o.icon);
      }
      var hlf = o.hLabelForm = hr.insertCell();
      hlf.innerText = o.label;
      if(o.components){
         o.hDropForm = hr.insertCell();
         o.hDropForm.innerText = '6';
         o.hDropForm.style.fontFamily = 'Webdings';
      }
   }
   return EEventStatus.Continue;
}
function FNavigatorButton_onBuildPanel(){
   this.hPanel = RBuilder.appendTable();
}
function FNavigatorButton_drop(){
   var o = this;
   var cs = o.components;
   if(cs){
      var p = o.pop;
      if(!p){
         p = RControl.create(FNavigatorEditor);
         o.pop = p;
         p.editable = o;
         p.setGroups(cs);
      }
      o.pop.setShow(true);
   }
}
function FNavigatorButton_onMouseOver(e){
   var o = this;
   o.hPanel.style.cursor = 'hand';
   o.focus();
   o.drop();
}
function FNavigatorButton_onMouseOut(e){
   var o = this;
   if(o.pop){
      o.pop.setShow(false);
   }
}
function FNavigatorButton_dispose(){
   var o = this;
   o.base.FControl.dispose.call(o);
   RMemory.freeHtml(o.hDropForm);
   RMemory.freeHtml(o.hLabelForm);
   o.hDropForm = null;
   o.hLabelForm = null;
}
function FNavigatorButton_onMouseDown(e){
   var o = this;
   o.lsnsButtonClickBefore.process(o);
   if(o.page){
      var url = o.page.replace(/@/g, '&');
      url = top.RContext.context(url);
      window.location.href = url;
   }else{
      o.focus();
      o.drop();
   }
   o.lsnsButtonClick.process(o);
}
function FNavigatorEditor(o){
   o = RClass.inherits(this, o, FControl, MFocus);
   o.construct         = FNavigatorEditor_construct;
   o.oeBuild           = FNavigatorEditor_oeBuild;
   o.onBuildPanel      = FNavigatorEditor_onBuildPanel;
   o.setGroups         = FNavigatorEditor_setGroups;
   o.setShow           = FNavigatorEditor_setShow;
   o.onMouseOut        = FNavigatorEditor_onMouseOut;
   o.onMouseOver       = FNavigatorEditor_onMouseOver;
   o.onItemClick       = FNavigatorEditor_onItemClick;
   o.dispose           = FNavigatorEditor_dispose;
   o.rect              = new TRect();
   return o;
}
function FNavigatorEditor_construct(){
   var o = this;
}
function FNavigatorEditor_oeBuild(e){
   var o = this;
   o.base.FControl.oeBuild.call(o, e);
   if(e.isBefore()){
      o.hForm = RBuilder.appendTable(o.hPanel);
      o.hForm.style.backgroundColor = 'white';
      o.hForm.style.fontSize = '9pt';
      o.hForm.style.cursor = 'default';
      o.hPanel.style.display = 'none';
   }
   return EEventStatus.Continue;
}
function FNavigatorEditor_onBuildPanel(){
   var o = this;
   var hp = o.hPanel = RBuilder.appendDiv();
   hp.style.position = 'absolute';
   hp.style.border = '1px solid #77AFEE';
   hp.style.backgroundColor = 'white';
   hp.style.padding = '10';
   hp.style.zindex = RLayer.next();
}
function FNavigatorEditor_setGroups(gs){
   var o = this;
   if(gs){
      var hr = o.hForm.insertRow();
      for(var n = 0; n < gs.count; n++){
         var g = gs.value(n);
         g.hPanel.style.display='block';
         if(RClass.isClass(g, FNavigatorGroup)){
            g.editor = o;
            var cs = g.components;
            g.setItems(cs);
         }
         var hc = hr.insertCell();
         hc.vAlign = 'top';
         hc.appendChild(g.hPanel);
      }
   }
}
function FNavigatorEditor_onMouseOver(e){
   var o = this;
   o.setShow(true);
}
function FNavigatorEditor_onMouseOut(e){
   var o = this;
   o.setShow(false);
}
function FNavigatorEditor_setShow(f){
   var o = this;
   var p = o.editable;
   if(f){
      o.hPanel.style.display = 'block';
      var r = RHtml.rect(p.hPanel);
      var tr = this.calcRect();
      r.left = r.right - tr.width() + 1;
      o.setBounds(r.left, r.bottom);
      o.hPanel.style.zIndex = RLayer.next();
   }else{
      o.hPanel.style.display = 'none';
   }
}
function FNavigatorEditor_onItemClick(c){
   var o = this;
}
function FNavigatorEditor_dispose(c){
   var o = this;
   o.base.FControl.dispose.call(o);
   RMemory.freeHtml(o.hForm);
   o.hPanel = null;
   o.hForm = null;
}
function FNavigatorGroup(o){
   o = RClass.inherits(this, o, FControl, MContainer);
   o.oeBuild         = FNavigatorGroup_oeBuild;
   o.onBuildPanel    = FNavigatorGroup_onBuildPanel;
   o.setItems        = FNavigatorGroup_setItems;
   o.dispose         = FNavigatorGroup_dispose;
   return o;
}
function FNavigatorGroup_oeBuild(e){
   var o = this;
   o.base.FControl.oeBuild.call(o, e);
   if(e.isBefore()){
      o.hTitle = o.hPanel.insertRow().insertCell();
      o.hTitle.innerText = o.label;
      o.hTitle.style.fontColor = 'blue';
      o.hTitle.style.fontWeight ="bolder";
      var hc = o.hPanel.insertRow().insertCell();
      o.hForm = RBuilder.appendTable(hc);
      o.hForm.style.padding = '2';
      o.hPanel.style.display = 'none';
   }
   return EEventStatus.Continue;
}
function FNavigatorGroup_onBuildPanel(){
   var hp = this.hPanel = RBuilder.appendTable();
   hp.cellPadding = '2';
}
function FNavigatorGroup_setItems(cs){
   var o = this;
   for(var n = 0; n < cs.count; n++){
      var c = cs.value(n);
      if(RClass.isClass(c, FNavigatorItem)){
         var t = o.editor;
         c.hPanel.style.display='block';
         var tBar = t.editable.parent;
         c.lsnsButtonClickBefore.register(tBar, tBar.onButtonClickBefore);
         c.lsnsButtonClick.register(tBar, tBar.onButtonClick);
         var hc = o.hForm.insertRow().insertCell();
         hc.appendChild(c.hPanel);
      }
   }
}
function FNavigatorGroup_dispose(){
   var o = this;
   o.base.FControl.dispose.call(o);
   RMemory.freeHtml(o.hTitle);
   RMemory.freeHtml(o.hForm);
   o.hTitle = null;
   o.hForm = null;
}
function FNavigatorItem(o){
   o = RClass.inherits(this, o, FControl);
   o.action          = RClass.register(o, new TPtyStr('action'));
   o.page            = RClass.register(o, new TPtyStr('page'));
   o.lsnsButtonClickBefore = new TListeners();
   o.lsnsButtonClick       = new TListeners();
   o.onBuildPanel    = FNavigatorItem_onBuildPanel;
   o.onMouseOut      = FNavigatorItem_onMouseOut;
   o.onMouseOver     = FNavigatorItem_onMouseOver;
   o.onMouseDown     = FNavigatorItem_onMouseDown;
   o.dispose         = FNavigatorItem_dispose;
   return o;
}
function FNavigatorItem_onBuildPanel(){
   var o = this;
   var hp = o.hPanel = RBuilder.appendTable();
   hp.style.width = '100%';
   hp.height = '20px';
   o.hPanel.style.border = '1px solid #FFFFFF';
   hp.style.cursor = 'hand';
   var hf = o.hForm = hp.insertRow().insertCell();
   hf.width = '100px';
   hf.noWrap = 'true';
   hf.innerText = o.label;
   hp.style.display = 'none';
}
function FNavigatorItem_onMouseOut(e){
   var o = this;
   o.hPanel.style.backgroundColor = '#FFFFFF';
   o.hPanel.style.border = '1px solid #FFFFFF';
}
function FNavigatorItem_onMouseOver(e){
   var o = this;
   o.hPanel.style.border = '1px solid #ADDBEF';
   o.hPanel.style.backgroundColor = '#F0F7FD';
}
function FNavigatorItem_onMouseDown(e){
   var o = this;
   o.lsnsButtonClickBefore.process(o);
   if(o.page){
      var fmMain = RHtml.form();
      var url = o.page.replace(/@/g, '&');
      fmMain.action = top.RContext.context(url);
      fmMain.submit();
   }
   o.lsnsButtonClick.process(o);
}
function FNavigatorItem_dispose(){
   var o = this;
   o.base.FControl.dispose.call(o);
   RMemory.freeHtml(o.hPanel);
   RMemory.freeHtml(o.hForm);
   o.hPanel = null;
   o.hForm = null;
}
function FPopupMenu(o){
   o = RClass.inherits(this, o, FContainer, MFocus, MShadow);
   o.styleLabel   = RClass.register(o, new TStyle('Label'));
   o.styleButton  = RClass.register(o, new TStyle('Button'));
   o.opener       = null;
   o.hContainer   = null;
   o.hLabel       = null;
   o.hButtonPanel = null;
   o.hIcon        = null;
   o.hText        = null;
   o.oeBuild      = FPopupMenu_oeBuild;
   o.onBuildPanel = FPopupMenu_onBuildPanel;
   o.doBlur       = FPopupMenu_doBlur;
   o.appendChild  = FPopupMenu_appendChild;
   o.show         = FPopupMenu_show;
   o.setVisible   = FPopupMenu_setVisible;
   o.testInRange  = FPopupMenu_testInRange;
   o.dispose      = FPopupMenu_dispose;
   return o;
}
function FPopupMenu_oeBuild(e){
   var o = this;
   o.base.FContainer.oeBuild.call(o, e);
   if(e.isBefore()){
      var t = o.parent;
      var h = o.hPanel;
      var hlf = o.hLayout = RBuilder.appendTable(o.hPanel);
      hlf.style.tableLayout = 'fixed';
      hlf.border = 1;
      var hc = hlf.insertRow().insertCell();
      var hd = o.hLayoutPanel = RBuilder.append(hc, 'DIV')
      hd.style.width = '100%';
      hd.style.height = '100%';
      o.hContainer = RBuilder.appendTable(hd);
      var h = o.hLabel = o.hContainer.insertRow().insertCell();
      h.className = o.style('Label');
      RBuilder.appendEmpty(h);
   }else if(e.isAfter()){
      o.hLastRow = o.hContainer.insertRow();
      var h = o.hLastRow.insertCell();
      RBuilder.appendEmpty(h, 1, 4);
      o.setVisible(false);
   }
   return EEventStatus.Continue;
}
function FPopupMenu_onBuildPanel(){
   this.hPanel = RBuilder.append(null, 'SPAN');
}
function FPopupMenu_doBlur(){
   var o = this;
   if(o.opener){
      o.opener.onBlur();
   }else{
      o.hide();
   }
}
function FPopupMenu_appendChild(ctl){
   var o = this;
   var h = o.hLabel = o.hContainer.insertRow().insertCell();
   h.className = o.style('Button');
   h.appendChild(ctl.hPanel);
}
function FPopupMenu_show(h, pos, v){
   var o = this;
   o.setVisible(true);
   var cw = o.hContainer.offsetWidth;
   var ch = o.hContainer.offsetHeight;
   var hls = o.hLayout.style;
   if(ch > 300){
      o.hLayoutPanel.style.overflowY = 'scroll';
      hls.pixelHeight = 300;
      hls.pixelWidth = cw + 20;
   }else{
      hls.pixelHeight = ch;
      hls.pixelWidth = cw + 4;
   }
   var r = RHtml.rect(h);
   if(EAlign.BottomRight == pos){
      var tr = this.calcRect();
      r.left = r.right - tr.width() + 1;
      r.bottom += 1;
   }
   o.setBounds(r.left, r.bottom);
   o.hPanel.style.zIndex = RLayer.next();
   o.focus();
}
function FPopupMenu_setVisible(v){
   var o = this;
   o.base.FContainer.setVisible.call(o, v);
   o.base.MShadow.setVisible.call(o, v);
}
function FPopupMenu_testInRange(e){
   return this == RControl.htmlControl(e.srcElement, FPopupMenu);
}
function FPopupMenu_dispose(e){
   var o = this;
   o.base.FContainer.dispose.call(o);
   RMemory.freeHtml(o.hContainer);
   RMemory.freeHtml(o.hPanel);
   RMemory.freeHtml(o.hLabel);
   RMemory.freeHtml(o.hLastRow);
   o.hContainer = null;
   o.hPanel = null;
   o.hLabel = null;
   o.hLastRow = null;
}
function FSideBar(o){
   o = RClass.inherits(this, o, FContainer);
   o.hPanel                = null;
   o.hForm                 = null;
   o.buttonClickListener   = null;
   o.oeBuild               = FSideBar_oeBuild;
   o.onBuildPanel          = FSideBar_onBuildPanel;
   o.onBuildControls       = FSideBar_onBuildControls;
   o.selectByIndex         = FSideBar_selectByIndex;
   o.selectByName          = FSideBar_selectByName;
   o.select                = FSideBar_select;
   o.onBuildControls       = FSideBar_onBuildControls;
   o.hideNoDataButton      = FSideBar_hideNoDataButton;
   o.dispose               = FSideBar_dispose;
   return o;
}
function FSideBar_oeBuild(event){
   var o = this;
   var r = o.base.FContainer.oeBuild.call(o, event);
   if(event.isAfter()){
      o.onBuildControls();
      o.selectByIndex(0);
   }
   return r;
}
function FSideBar_onBuildPanel(){
   var o = this;
   o.hPanel = RBuilder.newTable(null, null, 0, 0, 0);
   o.hPanel.width = '100%';
   o.hPanel.height = '100%';
   o.hPanel.style.border = '1px solid #8ce1f6';
}
function FSideBar_onBuildControls(){
   var o = this;
   var hp = o.hPanel;
   var hc1 = hp.insertRow().insertCell();
   hc1.innerText = o.label;
   hc1.style.paddingLeft = 8;
   hc1.style.fontWeight = 'bolder';
   hc1.style.color = '#15428B';
   hc1.height = '23px';
   hc1.style.backgroundImage='url(../../../ats/00/rs/icon/ctl/FSideBar_Caption.gif)';
   var hc2 = hp.insertRow().insertCell();
   hc2.vAlign = 'top';
   var h = o.hDataForm = RBuilder.appendTable(hc2);
   h.width = '100%';
   h.height = '100%';
   var cs = o.controls;
   if(cs){
      for(var n = 0; n < cs.count; n++){
         var c = cs.value(n);
         if(RClass.isClass(c, FSideButton)){
            c.parent = o;
            var hp = c.hPanel;
            var hc = h.insertRow().insertCell();
            hc.height = '1';
            c.hParent = hc;
            hc.appendChild(hp);
            c.build();
         }
      }
   }
}
function FSideBar_selectByIndex(n){
   var o = this;
   var ct = o.controls.count;
   if(ct < n+1){
      n = 0;
   }
   this.select(this.components.value(n));
}
function FSideBar_selectByName(n){
   this.select(this.components.get(n));
}
function FSideBar_select(b){
   var o = this;
   var cs = o.controls;
   for(var n=0; n<cs.count; n++){
      var c = cs.value(n);
      c.select(c == b);
   }
}
function FSideBar_dispose(b){
   var o = this;
   RMemory.freeHtml(o.hPanel);
   o.hPanel = null;
   o.hDatePanel = null;
}
function FSideBar_hideNoDataButton(){
   var o = this;
   var cs = o.components;
   var ct = cs.count;
   for(var n = 0; n < ct; n++){
      var c = cs.value(n);
      if(c && RClass.checkClass(c, FSideButton)){
         if(RString.isEmpty(c.hDataPanel.innerText)){
            c.hPanel.style.display = 'none';
         }else{
            c.hPanel.style.display = 'block';
         }
      }
   }
}
function FSideButton(o){
   o = RClass.inherits(this, o, FControl);
   o.action           = RClass.register(o, new TPtyStr('action'));
   o.onButtonClick    = RClass.register(o, new HClick('onButtonClick'), FSideButton_onButtonClick);
   o.onButtonHover    = RClass.register(o, new HMouseOver('onButtonHover'), FSideButton_onButtonHover);
   o.onButtonOut      = RClass.register(o, new HMouseOut('onButtonOut'), FSideButton_onButtonOut);
   o.icon             = RClass.register(o, new TPtyBool('icon'));
   o.stTitleForm      = RClass.register(o, new TStyle('TitleForm'));
   o.hPanel           = null;
   o.hDataPanel       = null;
   o.hDataDiv         = null;
   o.hTitlePanel      = null;
   o.hTitleForm       = null;
   o.oeBuild          = FSideButton_oeBuild;
   o.build            = FSideButton_build;
   o.onBuildPanel     = FSideButton_onBuildPanel;
   o.onDataDisplay    = FSideButton_onDataDisplay;
   o.appendBody       = FSideButton_appendBody;
   o.select           = FSideButton_select;
   o.show             = FSideButton_show;
   o.hide             = FSideButton_hide;
   o.dispose          = FSideButton_dispose;
   return o;
}
function FSideButton_oeBuild(event){
   var o = this;
   var r = o.base.FControl.oeBuild.call(o, event);
   return r;
}
function FSideButton_build(){
   var o = this;
   var hp = o.hPanel;
   var hc1 = o.hTitelPanel = hp.insertRow().insertCell();
   hc1.height = '1';
   var hc2 = o.hDataPanel = hp.insertRow().insertCell();
   hc2.vAlign = 'top'
   var htf = o.hTitleForm = RBuilder.appendTable(hc1);
   htf.width = '100%';
   htf.height = '100%';
   htf.className = o.style('TitleForm');
   htf.style.cursor = 'hand';
   htf.style.border = '1px solid #8ce1f5';
   htf.height = '20'
   htf.vAlign = 'top';
   hc2.innerText = '';
   var htr = htf.insertRow();
   var htc1 = htr.insertCell();
   htc1.style.padding = '0, 4';
   var htc2 = htr.insertCell();
   var htc3 = htr.insertCell();
   htc1.width = '20';
   o.hIcon = RBuilder.appendIcon(htc1, null, null, 16, 16);
   if(o.icon){
      o.hIcon.src = o.styleIconPath(o.icon);
   }else{
      o.hIcon.src = '../../../ats/00/rs/icon/ctl/FSideButton_Default.gif';
   }
   htc2.style.fontSize = 13;
   htc2.innerText = o.label;
   htc2.width = '100%'
   htc3.width = '20';
   o.hExt = RBuilder.appendIcon(htc3, null, null, 16, 16);
   o.hExt.align = 'right';
   o.hExt.src = '../../../ats/00/rs/icon/ctl/FSideButton_extend2.gif';
   o.attachEvent('onButtonClick', o.hTitleForm);
   o.attachEvent('onButtonHover', o.hTitleForm);
   o.attachEvent('onButtonOut', o.hTitleForm);
}
function FSideButton_onBuildPanel(){
   var o = this;
   var h = o.hPanel = RBuilder.newTable();
   h.width = '100%';
   h.height = '100%';
}
function FSideButton_onButtonClick(e){
   var o = this;
   o.parent.select(o);
   if(o.action){
      eval(o.action);
   }
}
function FSideButton_onDataDisplay(){
   var o = this;
   var t = o.activeDisplay;
   o.hDataPanel.style.height = t.count;
}
function FSideButton_onButtonHover(e){
   var o = this;
   o.hTitleForm.style.backgroundColor='#dcf7ff';
}
function FSideButton_onButtonOut(e){
   var o = this;
   o.hTitleForm.style.backgroundColor='#dcf7ff';
}
function FSideButton_appendBody(h){
   this.hDataPanel.appendChild(h);
}
function FSideButton_select(s){
   var o = this;
   if(s){
      o.hParent.height = null;
      o.hDataPanel.style.display = 'block';
      o.hExt.src = '../../../ats/00/rs/icon/ctl/FSideButton_extend1.gif';
   }else{
      o.hDataPanel.style.display = 'none';
      o.hExt.src = '../../../ats/00/rs/icon/ctl/FSideButton_extend2.gif';
      o.hParent.height = 1;
   }
}
function FSideButton_show(){
   var o = this;
   o.hPanel.style.display = 'block';
}
function FSideButton_hide(){
   var o = this;
   o.hPanel.style.display = 'none';
}
function FSideButton_dispose(){
   var o = this;
   o.base.FControl.dispose.call(o);
   RMemory.freeHtml(o.hTitelPanel);
   RMemory.freeHtml(o.hTitleForm);
   RMemory.freeHtml(o.hDataPanel);
   RMemory.freeHtml(o.hIcon);
   o.hTitelPanel = null;
   o.hTitleForm = null;
   o.hDataPanel = null;
   o.hIcon = null;
}
function FTitleBar(o){
   o = RClass.inherits(this, o, FContainer, MDisplayAble, MTop);
   o.historyIndex    = -1;
   o.buttons         = new TList();
   o.lsnsButtonClick = new TListeners();
   o.lsnsNavaRowMdown           = new TListeners();
   o.lsnsNavButtonClickBefore = new TListeners();
   o.lsnsNavButtonClick       = new TListeners();
   o.onNavButtonClickBefore = FTitleBar_onNavButtonClickBefore;
   o.onNavButtonClick       = FTitleBar_onNavButtonClick;
   o.hLine           = null;
   o.hCaption        = null;
   o.oeBuild         = FTitleBar_oeBuild;
   o.onBuildPanel    = FTitleBar_onBuildPanel;
   o.setIcon         = FTitleBar_setIcon;
   o.setCaption      = FTitleBar_setCaption;
   o.button          = FTitleBar_button;
   o.nextButton      = FTitleBar_nextButton;
   o.clickButton     = FTitleBar_clickButton;
   o.selectButton    = FTitleBar_selectButton;
   o.clear           = FTitleBar_clear;
   o.dispose         = FTitleBar_dispose;
   return o;
}
function FTitleBar_onNavButtonClickBefore(s){
   this.lsnsNavButtonClickBefore.process(s);
}
function FTitleBar_onNavButtonClick(s){
   this.lsnsNavButtonClick.process(s);
}
function FTitleBar_oeBuild(e){
   var o = this;
   var r = o.base.FContainer.oeBuild.call(o, e);
   if(e.isAfter()){
      var b = e.builder;
      var hl = o.hLine;
      var cs = o.components;
      var cl = cs ? cs.count : 0;
      var hasEnd = false;
      for(var n = 0; n < cl; n++){
         var c = cs.value(n);
         if(RClass.isClass(c, FTitleButton)){
            var hc = hl.insertCell();
            hc.align = 'right';
            hc.width = 1;
            hc.appendChild(c.hPanel);
            if(!hasEnd && !c.page){
               hasEnd = true;
            }
         }
      }
      o.hIconPanel = hl.insertCell();
      o.hIconPanel.width = 20;
      o.hCaption = o.hLine.insertCell();
      o.hIcon = b.appendIcon(o.hIconPanel, '#com.form');
      o.hCaption.style.fontWeight = 'bold';
      if(hasEnd){
         o.hIcon.style.display = 'none';
      }
      for(var n = 0; n < cl; n++){
         var c = cs.value(n);
         if(RClass.isClass(c, FNavigatorBar)){
            c.lsnsButtonClickBefore.register(o, o.onNavButtonClickBefore);
            c.lsnsButtonClick.register(o, o.onNavButtonClick);
            var hc = hl.insertCell();
            hc.align = 'right';
            hc.width = '100px';
            hc.appendChild(c.hPanel);
         }
      }
   }
   return r;
}
function FTitleBar_onBuildPanel(){
   var o = this;
   var h = o.hPanel = RBuilder.newTable(o.hParent);
   h.width = '100%';
   o.hLine = h.insertRow();
}
function FTitleBar_setIcon(i){
   var o = this;
   o.hIcon.style.display = 'block';
   o.hIcon.src = RRes.iconPath(i);
}
function FTitleBar_setCaption(t){
   var o = this;
   o.hCaption.style.display = 'block';
   o.hCaption.innerHTML = t;
}
function FTitleBar_button(name){
   return this.buttons.get(name);
}
function FTitleBar_nextButton(){
   var o = this;
   var n = o.historyIndex + 1;
   var b = o.buttons.get(n);
   if(!b){
      b = RControl.create(FHistoryButton);
      if(!o.buttons.isEmpty()){
         var h = b.hSplit = o.hLine.insertCell();
         h.innerText = '>';
      }
      var h = b.hParent = o.hLine.insertCell();
      h.appendChild(b.hPanel);
      b.index = o.buttons.count;
      b.bar = o;
      o.buttons.push(b);
   }
   o.historyIndex++;
   o.selectButton(b);
   var bc = o.buttons.count;
   for(var n=o.historyIndex+1; n<bc; n++){
      o.buttons.get(n).setVisible(false);
   }
   return b;
}
function FTitleBar_clickButton(b){
   var o = this;
   o.historyIndex = o.buttons.indexOf(b);
   o.lsnsButtonClick.process(b);
   o.selectButton(b);
}
function FTitleBar_selectButton(s){
   var o = this;
   s.select(true);
   var bs = o.buttons;
   var c = bs.count;
   for(var n=0; n<c; n++){
      var b = bs.get(n);
      if(b != s && b.isSelected){
         b.select(false);
      }
   }
}
function FTitleBar_clear(){
   var o = this;
   var bs = o.buttons;
   var c = bs.count;
   for(var n=0; n<c; n++){
      bs.get(n).setVisible(n);
   }
}
function FTitleBar_dispose(){
   var o = this;
   o.base.FContainer.dispose.call(o);
   RMemory.freeHtml(o.hIconPanel);
   RMemory.freeHtml(o.hCaption);
   o.hIconPanel = null;
   o.hCaption = null;
}
function FTitleButton(o){
   o = RClass.inherits(this, o, FControl, MLsnClick);
   o.icon            = RClass.register(o, new TPtyStr('icon'));
   o.action          = RClass.register(o, new TPtyStr('action'));
   o.page            = RClass.register(o, new TPtyStr('page'));
   o.method          = RClass.register(o, new TPtyStr('method'));
   o.stButton        = RClass.register(o, new TStyle('Button'));
   o.stIcon          = RClass.register(o, new TStyle('Icon'));
   o.stLabel         = RClass.register(o, new TStyle('Label'));
   o.stHover         = RClass.register(o, new TStyle('Hover'));
   o.stPress         = RClass.register(o, new TStyle('Press'));
   o.lsnsClick       = new TListeners();
   o.disabled        = false;
   o.hButton         = null;
   o.hButtonLine     = null;
   o.hButtonPanel    = null;
   o.hIcon           = null;
   o.hText           = null;
   o.oeBuild         = FTitleButton_oeBuild;
   o.onButtonClick   = RClass.register(o, new HClick('onButtonClick'), FTitleButton_onButtonClick);
   o.onBuildPanel    = FTitleButton_onBuildPanel;
   o.onEnter         = FTitleButton_onEnter;
   o.onLeave         = FTitleButton_onLeave;
   o.onMouseDown     = FTitleButton_onMouseDown;
   o.onMouseUp       = FTitleButton_onMouseUp;
   o.onShowHint      = FTitleButton_onShowHint;
   o.setLabel        = FTitleButton_setLabel;
   o.dispose         = FTitleButton_dispose;
   return o;
}
function FTitleButton_oeBuild(event){
   var o = this;
   o.base.FControl.oeBuild.call(o, event);
   var t = o.parent;
   var hp = o.hPanel;
   o.attachEvent('onButtonClick', hp);
   var hl = o.hButtonLine = hp.insertRow();
   if(o.icon){
      var hc = hl.insertCell();
      hc.width = 20;
      o.hIcon = RBuilder.appendIcon(hc, o.icon);
   }
   if(o.label){
      var hc = o.hText = hl.insertCell();
      hc.className = o.style('Label');
      if(o.page){
         hc.style.color = '#0000FF';
      }
      hc.style.fontWeight = 'bold';
      hc.innerHTML = o.label;
   }
   return EEventStatus.Stop;
}
function FTitleButton_onBuildPanel(){
   var o = this;
   o.hPanel = RBuilder.newTable(null, o.style('Button'));
}
function FTitleButton_onEnter(e){
   var o = this;
   if(o.hotkey || o.hint){
      if(!o.hintBox){
         o.hintBox = RConsole.find(FHintConsole).find();
      }
      o.hintBox.linkControl(o);
      o.active = new TActive(o, o.onShowHint);
      o.active.count = 300;
      RConsole.find(FActiveConsole).push(o.active);
   }
   if(!o.disabled){
      o.hPanel.className = o.style('Hover');
   }
}
function FTitleButton_onLeave(e){
   var o = this;
   if(o.hintBox){
      o.hintBox.hide();
      o.hintBox = null;
   }
   if(!o.disabled){
      o.hPanel.className = o.style('Button');
   }
}
function FTitleButton_onMouseDown(){
   var o = this;
   if(o.hintBox){
      o.hintBox.hide();
   }
   if(!this.disabled){
   }
}
function FTitleButton_onMouseUp(h){
   var o = this;
   if(!o.disabled){
      o.hPanel.className = o.style('Hover');
   }
}
function FTitleButton_onButtonClick(h){
   var o = this;
   if(o.isVisible() && !o.disabled && (EAction.Design != o.inAction)){
      var fc = RConsole.find(FFocusConsole);
      fc.storeFocus();
      fc.blur();
      o.lsnsClick.process(o);
      if(o.action){
         eval(o.action);
      }
      if(o.page || o.method){
         var form = RHtml.form(o.hButton);
         form.target = '';
         var p = RPage.parse(o.page);
         if(o.method){
            p.action = o.method;
         }
         p.split(o.attributes);
         var f = RConsole.find(FFocusConsole).findClass(MDataset);
         if(f){
            var as = new TAttributes();
            f.saveValue(as);
            if(form && form.form_pack){
               form.form_pack.value = as.pack();
            }
         }
         p.post(form, o.target);
      }
      o.processClick();
   }
}
function FTitleButton_click(){
   this.onClick();
}
function FTitleButton_onShowHint(a){
   var o = this;
   a.status = EActive.Finish;
   if(o.hintBox){
      o.hintBox.show();
   }
}
function FTitleButton_setLabel(s){
   this.hText.innerText = s;
}
function FTitleButton_dispose(){
   var o = this;
   o.base.FControl.dispose.call(o);
   o.hButton = null;
   o.hButtonLine = null;
   o.hText = null;
   o.hIcon = null;
   o.hButtonPanel = null;
}
function FToolBar(o){
   o = RClass.inherits(this, o, FContainer, MDisplayAble, MTop);
   o.align            = RClass.register(o, new TPtyStr('align'));
   o.isMerge          = RClass.register(o, new TPtyBool('isMerge'));
   o.styleButton      = RClass.register(o, new TStyle('Button'));
   o.target           = null;
   o.hLine            = null;
   o.oeBuild          = FToolBar_oeBuild;
   o.onEnter          = RMethod.empty;
   o.onLeave          = RMethod.empty;
   o.onBuildPanel     = FToolBar_onBuildPanel;
   o.appendChild      = FToolBar_appendChild;
   o.addClickListener = FToolBar_addClickListener;
   o.button           = FToolBar_button;
   o.setEnables       = FToolBar_setEnables;
   o.setVisibles      = FToolBar_setVisibles;
   o.clear            = FToolBar_clear;
   o.dispose          = FToolBar_dispose;
   return o;
}
function FToolBar_oeBuild(e){
   var o = this;
   o.base.FContainer.oeBuild.call(o, e);
   if(e.isAfter()){
      if(EAlign.Right != o.align){
         var hTd = RBuilder.create(null, 'TD');
         RBuilder.appendEmpty(hTd);
         o.hLine.appendChild(hTd);
      }
   }
}
function FToolBar_onBuildPanel(){
   var o = this;
   var h = o.hPanel = RBuilder.newTable(o.hParent);
   o.hLine = h.insertRow();
}
function FToolBar_appendChild(button){
   this.hLine.appendChild(button.hPanel);
}
function FToolBar_addClickListener(name, method){
   var btn = this.component(name);
   if(btn){
      btn.addClickListener(new TListener(this, method));
   }
}
function FToolBar_button(name){
   return this.components.get(name);
}
function FToolBar_setVisibles(vs){
   var o = this;
   for(var n in vs){
      o.button(n).setVisible(vs[n]);
   }
}
function FToolBar_setEnables(vs){
   var o = this;
   for(var n in vs){
      o.button(n).psEnable(vs[n]);
   }
}
function FToolBar_clear(){
   if(this.hTable && this.hLine){
      this.hLine.removeNode(true);
      this.hLine = this.hTable.insertRow();
   }
   this.buttons = new Array();
}
function FToolBar_dispose(){
   var o = this;
   o.base.FContainer.dispose.call(o);
   RMemory.freeHtml(o.hTable);
   RMemory.freeHtml(o.hLine);
   RMemory.freeHtml(o.hParent);
   o.hTable = null;
   o.hLine = null;
   o.hParent = null;
}
function FToolButton(o){
   o = RClass.inherits(this, o, FControl, MLsnClick);
   o.type            = RClass.register(o, new TPtyStr('type'));
   o.action          = RClass.register(o, new TPtyStr('action'));
   o.dataAction      = RClass.register(o, new TPtyStr('dataAction'));
   o.service         = RClass.register(o, new TPtyStr('service'));
   o.target          = RClass.register(o, new TPtyStr('target'));
   o.page            = RClass.register(o, new TPtyStr('page'));
   o.hotkey          = RClass.register(o, new TPtyStr('hotkey'));
   o.method          = RClass.register(o, new TPtyStr('method'));
   o.icon            = RClass.register(o, new TPtyStr('icon'));
   o.iconDisable     = RClass.register(o, new TPtyStr('iconDisable'));
   o.attributes      = RClass.register(o, new TPtyStr('attributes'));
   o.onButtonClick   = RClass.register(o, new HClick('onButtonClick'), FToolButton_onButtonClick);
   o.stButton        = RClass.register(o, new TStyle('Button'));
   o.stDisable       = RClass.register(o, new TStyle('Disable'));
   o.stIcon          = RClass.register(o, new TStyle('Icon'));
   o.stIconDisable   = RClass.register(o, new TStyle('IconDisable'));
   o.stLabel         = RClass.register(o, new TStyle('Label'));
   o.stHover         = RClass.register(o, new TStyle('Hover'));
   o.stPress         = RClass.register(o, new TStyle('Press'));
   o.stButton        = RClass.register(o, new TStyleIcon('Button'));
   o.stButtonDisable = RClass.register(o, new TStyleIcon('ButtonDisable'));
   o.stButtonHover   = RClass.register(o, new TStyleIcon('ButtonHover'));
   o.lsnsClick       = new TListeners();
   o.disabled        = false;
   o.hButton         = null;
   o.hButtonLine     = null;
   o.hButtonPanel    = null;
   o.hIcon           = null;
   o.hText           = null;
   o.onBuildPanel    = FToolButton_onBuildPanel;
   o.onEnter         = FToolButton_onEnter;
   o.onLeave         = FToolButton_onLeave;
   o.onMouseDown     = FToolButton_onMouseDown;
   o.onMouseUp       = FToolButton_onMouseUp;
   o.onShowHint      = FToolButton_onShowHint;
   o.oeBuild         = FToolButton_oeBuild;
   o.oeEnable        = FToolButton_oeEnable;
   o.setLabel        = FToolButton_setLabel;
   o.dispose         = FToolButton_dispose;
   return o;
}
function FToolButton_oeBuild(e){
   var o = this;
   o.base.FControl.oeBuild.call(o, e);
   var t = o.parent;
   var h = o.hPanel;
   var hb = o.hButton = RBuilder.appendTable(o.hPanel, o.style('Panel'));
   hb.background = o.styleIconPath('Button', FToolButton);
   var hLine = o.hButtonLine = o.hButton.insertRow();
   var hCel = o.hButtonPanel = hLine.insertCell();
   o.attachEvent('onButtonClick', o.hButtonPanel);
   hCel.className = t.style('Button');
   if(o.icon){
      o.hIcon = RBuilder.appendIcon(hCel, o.icon);
   }
   if(o.label){
      o.hText = RBuilder.append(hCel, 'SPAN');
      o.hText.innerHTML = '&nbsp;' + o.label;
   }
   if(o.hotkey){
      RConsole.find(FKeyConsole).register(o.hotkey, new TListener(o, o.onButtonClick));
   }
   return EEventStatus.Stop;
}
function FToolButton_oeEnable(e){
   var o = this;
   o.base.FControl.oeEnable.call(o, e);
   o.disabled = !e.enable;
   if(e.enable && o.icon){
      var is = RRes.iconPath(o.icon);
      if(o.hIcon.src != is){
         o.hIcon.src = is;
      }
   }else if(!e.enable && o.iconDisable){
      var is = RRes.iconPath(o.iconDisable);
      if(o.hIcon.src != is){
         o.hIcon.src = is;
      }
   }
   var css = o.style(e.enable ? 'Icon' : 'IconDisable');
   if(o.hIcon.className != css){
      o.hIcon.className = css;
   }
   var css = o.style(e.enable ? 'Button' : 'Disable');
   if(o.hPanel.className != css){
      o.hPanel.className = css;
   }
   var ci = o.styleIconPath(e.enable ? 'Button' : 'ButtonDisable');
   if(o.hButton.background != ci){
      o.hButton.background = ci;
   }
   return EEventStatus.Stop;
}
function FToolButton_onBuildPanel(){
   this.hPanel = RBuilder.create(null, 'TD', this.style('Button'));
}
function FToolButton_onEnter(e){
   var o = this;
   if(o.hotkey || o.hint){
      if(!o.hintBox){
         o.hintBox = RConsole.find(FHintConsole).find();
      }
      o.hintBox.linkControl(o);
      o.active = new TActive(o, o.onShowHint);
      o.active.count = 300;
      RConsole.find(FActiveConsole).push(o.active);
   }
   if(!o.disabled){
      o.hPanel.className = o.style('Hover');
      o.hButton.background = o.styleIconPath('ButtonHover', FToolButton);
   }
}
function FToolButton_onLeave(e){
   var o = this;
   if(o.hintBox){
      o.hintBox.hide();
      o.hintBox = null;
   }
   if(!o.disabled){
      o.hPanel.className = o.style('Button');
      o.hButton.background = o.styleIconPath('Button', FToolButton);
   }
}
function FToolButton_onMouseDown(){
   var o = this;
   if(o.hintBox){
      o.hintBox.hide();
   }
   if(!o.disabled){
   }
}
function FToolButton_onMouseUp(h){
   var o = this;
   if(!o.disabled){
      o.hPanel.className = o.style('Hover');
   }
}
function FToolButton_onButtonClick(h){
   var o = this;
   RLogger.debug(o, '[D] onButtonClick = ' + o.name);
   if(o.isVisible() && !o.disabled && (EAction.Design != o.inAction)){
      var fc = RConsole.find(FFocusConsole);
      fc.storeFocus();
      fc.blur();
      o.lsnsClick.process(o);
      if(o.action){
         eval(o.action);
      }
      if(o.service){
         var servs = RString.splitTwo(o.service, '@');
         var f = RConsole.find(FFocusConsole).findClass(MDataset);
         var arg = new TDatasetServiceArg(f.name, o.dataAction);
         arg.callback = new TInvoke(f, f.onDsProcess);
         arg.rows = f.getCurrentRows();
         RConsole.find(FFormConsole).process(arg);
      }
      if(o.page || o.method){
         var form = RHtml.form(o.hButton);
         var p = RPage.parse(o.page);
         if(o.method){
            p.action = o.method;
         }
         p.split(o.attributes);
         var f = RConsole.find(FFocusConsole).findClass(MDataset);
         if(f){
            var as = new TAttributes();
            f.saveValue(as);
            if(form && form.form_pack){
               form.form_pack.value = as.pack();
            }
         }
         p.post(form, o.target);
      }
      o.processClick();
   }
}
function FToolButton_click(){
   this.onClick();
}
function FToolButton_onShowHint(a){
   var o = this;
   a.status = EActive.Finish;
   if(o.hintBox){
      o.hintBox.show();
   }
}
function FToolButton_setLabel(s){
   this.hText.innerText = s;
}
function FToolButton_dispose(){
   var o = this;
   o.base.FControl.dispose.call(o);
   RMemory.freeHtml(o.hButtonLine);
   RMemory.freeHtml(o.hButton);
   RMemory.freeHtml(o.hText);
   RMemory.freeHtml(o.hButtonPanel);
   RMemory.freeHtml(o.hIcon);
   o.hButton = null;
   o.hButtonLine = null;
   o.hText = null;
   o.hIcon = null;
   o.hButtonPanel = null;
}
function FToolButtonCheck(o){
   o = RClass.inherits(this, o, FToolButton);
   o.down         = RClass.register(o, new TPtyBool('down', false));
   o.onEnter      = FToolButtonCheck_onEnter;
   o.onLeave      = FToolButtonCheck_onLeave;
   o.onMouseDown  = FToolButtonCheck_onMouseDown;
   o.onMouseUp    = FToolButtonCheck_onMouseUp;
   o.setDown      = FToolButtonCheck_setDown;
   o.dispose      = FToolButtonCheck_dispose;
   return o;
}
function FToolButtonCheck_onEnter(){
   if(!this.down){
      this.hPanel.className = this.style('Hover');
   }
}
function FToolButtonCheck_onLeave(){
   if(!this.down){
      this.hPanel.className = this.style('Button');
   }
}
function FToolButtonCheck_onMouseDown(){
   this.hPanel.className = this.style('Press');
}
function FToolButtonCheck_onMouseUp(){
   var o = this;
   o.hPanel.className = o.style('Hover');
   o.setDown(!o.down)
   if(o.action){
      eval(o.action);
   }
   o.processClick(o, o.down);
}
function FToolButtonCheck_setDown(down){
   var o = this;
   if(o.down != down){
      o.down = down;
      if(down){
         o.hPanel.className = o.style('Down');
      }else{
         o.hPanel.className = o.style('Button');
      }
   }
}
function FToolButtonCheck_dispose(){
   var o = this;
   o.base.FToolButton.dispose.call(o);
   RMemory.freeHtml(o.hPanel);
   o.hPanel = null;
}
function FToolButtonMenu(o){
   o = RClass.inherits(this, o, FToolButton, MContainer, MDropable, MFocus);
   o.popup         = null;
   o.hDropPanel    = null;
   o.siDropHover   = RClass.register(o, new TStyleIcon('DropHover'));
   o.onEnter       = FToolButtonMenu_onEnter;
   o.onLeave       = FToolButtonMenu_onLeave;
   o.onBlur        = FToolButtonMenu_onBlur;
   o.onButtonClick = FToolButtonMenu_onButtonClick;
   o.onDropClick   = FToolButtonMenu_onDropClick;
   o.oeBuild       = FToolButtonMenu_oeBuild;
   o.construct     = FToolButtonMenu_construct;
   o.push          = FToolButtonMenu_push;
   o.drop          = FToolButtonMenu_drop;
   o.dispose       = FToolButtonMenu_dispose;
   return o;
}
function FToolButtonMenu_onEnter(e){
   var o = this;
   o.base.FToolButton.onEnter.call(o, e);
   if(!o.disabled){
      o.hDropIcon.src = o.styleIconPath('DropHover');
   }
}
function FToolButtonMenu_onLeave(e){
   var o = this;
   if(!o.popup.isVisible()){
      o.base.FToolButton.onLeave.call(o, e);
      if(!o.disabled){
         o.hDropIcon.src = o.styleIconPath('Drop');
      }
   }
}
function FToolButtonMenu_onBlur(e){
   var o = this;
   if(e){
      if(o.popup.testInRange(e)){
         return false;
      }
   }
   o.hPanel.className = o.style('Button');
   o.popup.hide();
}
function FToolButtonMenu_onButtonClick(){
   var o = this;
   if(!o.disabled){
      o.base.FToolButton.onButtonClick.call(o);
      if(!(o.action || o.page)){
         o.drop();
      }else if(o.action){
         eval(o.action);
      }
   }
}
function FToolButtonMenu_onDropClick(e){
   this.drop();
}
function FToolButtonMenu_oeBuild(e){
   var o = this;
   if(e.isBefore()){
      o.base.FToolButton.oeBuild.call(o, e);
      var h = o.hDropPanel = o.hButtonLine.insertCell();
      h.className = o.style('Drop')
      o.hDropIcon = RBuilder.appendIcon(h, o.styleIcon('Drop'));
      o.attachEvent('onDropClick', h);
   }
   if(e.isAfter()){
      o.popup.psBuild();
   }
   return EEventStatus.Continue;
}
function FToolButtonMenu_construct(){
   var o = this;
   o.popup = RClass.create(FPopupMenu);
   o.popup.opener = o;
}
function FToolButtonMenu_push(c){
   var o = this;
   if(RClass.isClass(c, MMenuButton)){
      return o.popup.push(c);
   }
   o.base.FToolButton.push.call(o, c);
}
function FToolButtonMenu_drop(){
   var o = this;
   if(!o.disabled){
      o.popup.show(this.hDropPanel, EAlign.BottomRight);
   }
}
function FToolButtonMenu_dispose(){
   var o = this;
   o.base.FControl.dispose.call(o);
   o.hDropIcon = null;
   o.hDropPanel = null;
}
function FToolButtonSplit(o){
   o = RClass.inherits(this, o, FControl);
   o.styleButton  = RClass.register(o, new TStyle('Button'));
   o.hButton      = null;
   o.oeBuild      = FToolButtonSplit_oeBuild;
   o.onBuildPanel = FToolButtonSplit_onBuildPanel;
   o.dispose      = FToolButtonSplit_dispose;
   return o;
}
function FToolButtonSplit_oeBuild(event){
   var o = this;
   o.base.FControl.oeBuild.call(o, event);
   o.hButton = RBuilder.append(this.hPanel, 'DIV', o.style('Button'));
   return EEventStatus.Stop;
}
function FToolButtonSplit_onBuildPanel(){
   this.hPanel = RBuilder.create(null, 'TD', this.style('Panel'));
}
function FToolButtonSplit_dispose(){
   var o = this;
   o.base.FControl.dispose.call(o);
   RMemory.freeHtml(o.hPanel);
   RMemory.freeHtml(o.hButton);
   o.hPanel = null;
   o.hButton = null;
}
function FToolButtonText(o){
   o = RClass.inherits(this, o, FToolButton);
   return o;
}
function MMenuButton(o){
   o = RClass.inherits(this, o);
   return o;
}
var RToolBar = new function(){
   var o = this;
   o.mergeNode    = RToolBar_mergeNode;
   o.fromNode     = RToolBar_fromNode;
   RMemory.register('RToolBar', o);
   return o;
}
function RToolBar_mergeNode(xtb, xNode, r){
   var ns = xNode.nodes;
   for(var j=0; j<ns.count; j++){
      var n = ns.get(j);
      if('ToolBar' == n.name){
         if(n.nodes){
            for(var i=0; i<n.nodes.count; i++){
               xtb.push(n.nodes.get(i));
            }
         }
      }
   }
   if(r){
      for(var j=ns.count-1; j>=0; j--){
         var n = ns.get(j);
         if('ToolBar' == n.name){
            ns.removeItem(n);
         }
      }
   }
   return xtb;
}
function RToolBar_fromNode(c, p, r){
   if(c && c.nodes){
      var xtb = null;
      var ns = c.nodes;
      for(var j=0; j<ns.count; j++){
         var n = ns.get(j);
         if('ToolBar' == n.name){
            if(!xtb){
               xtb = n;
            }else if(n.nodes){
               for(var i=0; i<n.nodes.count; i++){
                  xtb.push(n.nodes.get(i));
               }
            }
         }
      }
      if(r){
         for(var j=ns.count-1; j>=0; j--){
            var n = ns.get(j);
            if('ToolBar' == n.name){
               ns.extract(n);
            }
         }
      }
      if(xtb){
         return RControl.fromNode(xtb, p);
      }
   }
}
