function FActionWindow(o){
   o = RClass.inherits(this, o, FWindow);
   o.styleTablePanel      = RClass.register(o, new TStyle('TablePanel'));
   o.styleButtonPanel     = RClass.register(o, new TStyle('ButtonPanel'));
   o.buttons              = new TList();
   o.oeBuild              = FActionWindow_oeBuild;
   o.linkForm             = FActionWindow_linkForm;
   o.show                 = FActionWindow_show;
   o.hide                 = FActionWindow_hide;
   o.onClose              = FActionWindow_onClose;
   o.onProcessButtonClick = FActionWindow_onProcessButtonClick;
   o.onBuildButtons       = FActionWindow_onBuildButtons;
   o.processArg           = FActionWindow_processArg;
   o.onLoaded             = FActionWindow_onLoaded;
   o.dispose              = FActionWindow_dispose;
}
function FActionWindow_oeBuild(e){
   var o = this;
   var r = o.base.FWindow.oeBuild.call(o, e);
   if(e.isAfter()){
      o.setIcon('Icon');
      o.setCaption(' Action Window');
      var hTab = RBuilder.appendTable(o.hBodyPanel);
      o.hBodyPanel.style.height = '400px';
      hTab.width = '100%';
      hTab.height = '100%';
      var h = o.hButtonPanel = hTab.insertRow().insertCell();
      h.height = 1;
      h.className = o.style('ButtonPanel');
      h.style.borderBottomStyle = 'solid';
      h.style.borderBottomWidth  = 'thin';
      var h  = hTab.insertRow().insertCell();
      h.height = "200";
      var hDiv = RBuilder.appendDiv(h);
      hDiv.style.width = '100%';
      hDiv.style.height = '100%';
      hDiv.style.overflow = 'auto';
      o.hTablePanel = RBuilder.appendTable(hDiv);
      h.style.height = "100%";
      o.hTablePanel.style.width = '100%';
      h.vAlign = 'top';
      h.style.width = '100%';
      h.cellSpacing = 10;
      o.onBuildButtons();
      RConsole.find(FKeyConsole).register(EKey.Esc, new TListener(o, o.onClose));
   }
   return r;
}
function FActionWindow_onBuildButtons(){
   var o = this;
   var hBtnTab = RBuilder.appendTable(o.hButtonPanel, null, 0, 0, 6);
   var hRow = hBtnTab.insertRow();
   var b = o.btnClose = RClass.create(FButton);
   b.icon = 'tool.exit';
   b.label = RContext.get('FToolButton:close');
   b.width = '100%';
   b.lsnsClick.register(o, o.onClose);
   b.psBuild(hRow.insertCell());
}
function FActionWindow_linkForm(ds){
   var o = this;
   var h = o.hTablePanel;
   o.linkDs = ds;
   h.innerText = '';
   if(RClass.isClass(ds, MDataset)){
      var cs = ds.components;
      var ct = cs.count;
      for(var n = 0; n < ct; n++){
         var c = cs.value(n);
         if(RClass.isClass(c, FDataAction)){
            var htc = h.insertRow().insertCell();
            htc.style.width = '100%';
            var htb = RBuilder.appendTable(htc);
            htb.style.width = '100%';
            htb.style.border = '1px solid gray';
            var hr = htb.insertRow();
            var hc0 = hr.insertCell();
            hc0.width = 50;
            hc0.align = 'center';
            hc0.vAlign = 'middle';
            var hic = RBuilder.appendIcon(hc0);
            hic.src = RString.nvl(o.styleIcon(c.iconPath), o.styleIcon('Default'));
            RRes.iconPath(o.styleIcon(c.iconPath));
            var hc2 = hr.insertCell();
            hc2.innerText = c.label;
            var ht = RBuilder.appendTable(hc2);
            ht.style.width = '100%';
            var hr1 = ht.insertRow();
            var hr2 = ht.insertRow();
            var hrc1 = hr1.insertCell();
            var hrc2 = hr2.insertCell();
            hrc1.innerText = '   '+c.note;
            hrc1.style.cellPadding = 3;
            hrc2.align = 'right';
            var b = RClass.create(FButton);
            b.width = 1;
            b.icon = 'tool.exit';
            b.label = '操作';
            b.psBuild(hrc2);
            b.userService = c.service;
            b.actName = c.name;
            b.dsName = ds.name;
            b.invokeFun = c.invokeFunction;
            b.lsnsClick.register(o, o.onProcessButtonClick);
            var htc2 = h.insertRow().insertCell();
            htc2.innerHTML = '<br>';
         }
      }
   }
}
function FActionWindow_show(){
   var o = this;
   o.base.FWindow.show.call(o);
   RWindow.setEnable(false, true);
   RWindow.moveCenter(o.hPanel);
   o.psVisible(true);
   o.focus();
}
function FActionWindow_hide(){
   var o = this;
   o.base.FWindow.hide.call(o);
   RWindow.setEnable(true);
}
function FActionWindow_onClose(){
   var o = this;
   o.hide();
}
function FActionWindow_onProcessButtonClick(btn){
   var o = this;
   var ds = o.linkDs;
   var g = new TActionServiceArg();
   var t = new TAttributes();
   if(RClass.isClass(ds, FTable)){
      ds.findSelectRows();
      var sr = ds._selectRows.get(0);
      sr.toAttributes(t);
   }else if(RClass.isClass(ds, FForm)){
      ds.toAttributes(t);
   }
   g.values = t;
   var sv = RString.splitTwo(btn.userService, '@');
   g.sev = sv;
   g.actName = btn.actName;
   g.dsName = btn.dsName;
   g.invokeFun = btn.invokeFun;
   g.callback = new TInvoke(o, o.onLoaded);
   o.processArg(g);
}
function FActionWindow_processArg(g){
   var o = this;
   var doc = new TXmlDocument();
   var root = doc.root();
   root.set('action', g.sev[0]);
   var nd = g.toNode();
   root.push(nd);
   var url = RService.url(g.sev[1]);
   var e = new TEvent(o, EXmlEvent.Send, o.onLoaded);
   e.url = url;
   e.document = doc;
   e.arg = g;
   e.action = g.action;
   RConsole.find(FXmlConsole).process(e);
}
function FActionWindow_onLoaded(e){
   var o = this;
   var root = e.document.root();
   var mc = RConsole.find(FMessageConsole);
   var r = mc.checkResult(root, e.arg);
   if(r){
      o.hide();
      if(e.arg.invokeFun){
         eval(e.arg.invokeFun);
      }
   }
}
function FActionWindow_dispose(){
   var o = this;
   o.base.FWindow.dispose.call(o);
   RMemory.freeHtml(o.hBodyPanel);
   RMemory.freeHtml(o.hTablePanel);
   RMemory.freeHtml(o.hButtonPanel);
   o.hBodyPanel = null;
   o.hTablePanel = null;
   o.hButtonPanel = null;
}
function FAlertWindow(o, c){
   o = RClass.inherits(this, o, FWindow);
   o.type            = null;
   o.isDialog        = false;
   o.titleBlur       = false;
   o.content         = c;
   o.icon             = '#com.FAlertWindow_Warn';
   o.result          = false;
   o.stMsgPanel      = RClass.register(o, new TStyle('MsgPanel'));
   o.stButtonPanel   = RClass.register(o, new TStyle('ButtonPanel'));
   o.stItmeForm      = RClass.register(o, new TStyle('ItmeForm'));
   o.stItemTitle     = RClass.register(o, new TStyle('ItemTitle'));
   o.stItemBodyForm  = RClass.register(o, new TStyle('ItemBodyForm'));
   o.stRowItem       = RClass.register(o, new TStyle('RowItem'));
   o.stDescForm      = RClass.register(o, new TStyle('DescForm'));
   o.stDescTitle     = RClass.register(o, new TStyle('DescTitle'));
   o.stDescBody      = RClass.register(o, new TStyle('DescBody'));
   o.lsns            = new TListeners();
   o.hMessagePanel   = null;
   o.hMessages       = null;
   o.hDescription    = null;
   o.hButtonPanel    = null;
   o.oeBuild         = FAlertWindow_oeBuild;
   o.buildEdit       = FAlertWindow_buildEdit;
   o.onOk            = FAlertWindow_onOk;
   o.onCancel        = FAlertWindow_onCancel;
   o.onClose         = FAlertWindow_onClose;
   o.setText         = FAlertWindow_setText;
   o.show            = FAlertWindow_show;
   o.hide            = FAlertWindow_hide;
   o.dispose         = FAlertWindow_dispose;
   return o;
}
function FAlertWindow_oeBuild(e){
   var o = this;
   var r = o.base.FWindow.oeBuild.call(o, e);
   if(e.isAfter()){
     o.hPanel.style.width = '400px';
      o.buildEdit();
      RConsole.find(FKeyConsole).register(EKey.Esc, new TListener(o, o.onClose));
   }
   return r;
}
function FAlertWindow_onOk(){
   this.hide();
   this.result = true;
}
function FAlertWindow_onCancel(){
   this.hide();
   this.result = false;
   return true;
}
function FAlertWindow_onClose(){
   this.hide();
   this.result = false;
   return false
}
function FAlertWindow_setText(t){
   this.hText.innerText = ' ' + t;
}
function FAlertWindow_show(){
   var o = this;
   o.base.FWindow.show.call(o);
   o.panel().style.zIndex = RLayer.next(ELayer.Message);
   RWindow.moveCenter(o.panel());
   o.psMode(EMode.Update);
   RConsole.find(FFocusConsole).blur();
   RWindow.setEnable(false, true);
   o.focus();
}
function FAlertWindow_buildEdit(){
   var o = this;
   var h = o.hBodyPanel;
   o.hBodyPanel.style.height = 100;
   var hTab = RBuilder.appendTable(o.hBodyPanel, 0, 0, 0);
   hTab.style.height = '100%';
   hTab.style.width = '100%';
   var hRow1 = hTab.insertRow();
   var hcp = hRow1.insertCell();
   var htp = RBuilder.appendTable(hcp, 0, 0, 0);
   var hcr = htp.insertRow();
   var hcb = hcr.insertCell();
   hcb.width=10;
   var hcm = hcr.insertCell();
   hcm.width = 50;
   hcm.height = 50;
   hg = o.hImg = RBuilder.appendImage(hcm, 'FAlertWindow_Warn');
   var hc11 = o.hText = hcr.insertCell();
   hc11.vAlign = 'middle';
   hc11.align = 'center';
   var hRow3 = hTab.insertRow();
   var hc31 = hRow3.insertCell();
   hc31.align = 'right';
   var hBtb = RBuilder.appendTable(hc31);
   var hBr = hBtb.insertRow();
   var hBc1 = hBr.insertCell();
   var b = o.btnOk = RClass.create(FButton);
   b.icon = 'tool.ok';
   b.label = RContext.get('FToolButton:ok');
   b.lsnsClick.register(o, o.onOk);
   b.psBuild(hBc1);
   o.setIcon('TitleWarn');
   o.setCaption('错误警告');
}
function FAlertWindow_hide(){
   var o = this;
   o.base.FWindow.hide.call(o);
   RWindow.setEnable(true);
}
function FAlertWindow_dispose(){
   var o = this;
   o.base.FWindow.dispose.call(o);
   RMemory.freeHtml(o.hBodyPanel);
   RMemory.freeHtml(o.hText);
   o.hBodyPanel = null;
   o.hText = null;
}
function FButtonFormWindow(o){
   o = RClass.inherits(this, o, FWindow);
   o.buttons              = new TList();
   o.oeBuild              = FButtonFormWindow_oeBuild;
   o.linkForm             = FButtonFormWindow_linkForm;
   o.show                 = FButtonFormWindow_show;
   o.hide                 = FButtonFormWindow_hide;
   o.onClose              = FButtonFormWindow_onClose;
   o.dispose              = FButtonFormWindow_dispose;
   return o;
}
function FButtonFormWindow_oeBuild(e){
   var o = this;
   var r = o.base.FWindow.oeBuild.call(o, e);
   if(e.isAfter()){
      o.setIcon('Icon');
      o.setCaption(' Window');
      var hTab = RBuilder.appendTable(o.hBodyPanel);
      o.hPanel.style.width = '800px';
      o.hBodyPanel.style.height = '500px';
      hTab.style.width = '100%';
      hTab.style.height = '100%';
      var hc = o.hBox = hTab.insertRow().insertCell();
      hc.style.width = '100%';
      hc.style.height = '100%';
      hc.vAlign = 'top';
      RConsole.find(FKeyConsole).register(EKey.Esc, new TListener(o, o.onClose));
   }
   return r;
}
function FButtonFormWindow_linkForm(btn){
   var o = this;
   var h = o.hBox;
   h.innerHTML = '';
   o.sourceBtn = btn;
   btn.window = o;
   var fName = btn.editForm;
   var f = o.form = RConsole.find(FFormConsole).createFromName(fName, h, null, EForm.Form);
   f.psMode(EMode.Update);
   f.setVisible(true);
   f.psRefresh();
   f.focus();
   f.dsFetch(true, true);
   f.panel().style.height = '400';
   f.window = this;
}
function FButtonFormWindow_show(){
   var o = this;
   o.base.FWindow.show.call(o);
   RWindow.setEnable(false, true);
   RWindow.moveCenter(o.hPanel);
   o.psVisible(true);
   o.focus();
}
function FButtonFormWindow_hide(){
   var o = this;
   o.base.FWindow.hide.call(o);
   RWindow.setEnable(true);
}
function FButtonFormWindow_onClose(){
   var o = this;
   o.hide();
}
function FButtonFormWindow_dispose(){
   var o = this;
   o.base.FWindow.dispose.call(o);
   RMemory.freeHtml(o.hPanel);
   RMemory.freeHtml(o.hBodyPanel);
   o.hPanel = null;
   o.hBodyPanel = null;
}
function FButtonWindow(o){
   o = RClass.inherits(this, o, FWindow);
   o.buttons              = new TList();
   o.oeBuild              = FButtonWindow_oeBuild;
   o.linkUrl              = FButtonWindow_linkUrl;
   o.show                 = FButtonWindow_show;
   o.hide                 = FButtonWindow_hide;
   o.onClose              = FButtonWindow_onClose;
   o.dispose              = FButtonWindow_dispose;
   return o;
}
function FButtonWindow_oeBuild(e){
   var o = this;
   var r = o.base.FWindow.oeBuild.call(o, e);
   if(e.isAfter()){
      o.setIcon('Icon');
      o.setCaption(' Window');
      var hTab = RBuilder.appendTable(o.hBodyPanel);
      o.hPanel.style.width = '800px';
      o.hBodyPanel.style.height = '560px';
      hTab.style.width = '100%';
      hTab.style.height = '100%';
      var hc = hTab.insertRow().insertCell();
      hc.style.width = '100%';
      hc.style.height = '100%';
      o.hBox = RBuilder.appendDiv(hc);
      RConsole.find(FKeyConsole).register(EKey.Esc, new TListener(o, o.onClose));
   }
   return r;
}
function FButtonWindow_linkUrl(url){
   var o = this;
   var h = o.hBox;
   h.innerHTML = "<IFRAME ID=IFrame1 width=100% height=100% FRAMEBORDER=0 SCROLLING=auto SRC=" +url+"></IFRAME>";
}
function FButtonWindow_show(){
   var o = this;
   o.base.FWindow.show.call(o);
   RWindow.setEnable(false, true);
   RWindow.moveCenter(o.hPanel);
   o.psVisible(true);
   o.focus();
}
function FButtonWindow_hide(){
   var o = this;
   o.base.FWindow.hide.call(o);
   RWindow.setEnable(true);
}
function FButtonWindow_onClose(){
   var o = this;
   o.hide();
}
function FButtonWindow_dispose(){
   var o = this;
   o.base.FWindow.dispose.call(o);
   RMemory.freeHtml(o.hPanel);
   RMemory.freeHtml(o.hBodyPanel);
   RMemory.freeHtml(o.hBox);
   o.hPanel = null;
   o.hBodyPanel = null;
   o.hBox = null;
}
function FCheckWindow(o){
   o = RClass.inherits(this, o, FWindow);
   o.stTablePanel   = RClass.register(o, new TStyle('TablePanel'));
   o.stButtonPanel  = RClass.register(o, new TStyle('ButtonPanel'));
   o.onSelect       = RClass.register(o, new HClick('onSelect'), FCheckWindow_onSelect);
   o.onClose        = FCheckWindow_onClose;
   o.onBuildButtons = FCheckWindow_onBuildButtons;
   o.oeBuild        = FCheckWindow_oeBuild;
   o.set            = FCheckWindow_set;
   o.show           = FCheckWindow_show;
   o.hide           = FCheckWindow_hide;
   o.dispose        = FCheckWindow_dispose;
   return o;
}
function FCheckWindow_onSelect(e){
   var o = this;
   var n = e.hSource.parentNode.parentNode.rowIndex;
   var c = o.ctrs.get(n);
   o.hide();
}
function FCheckWindow_onClose(){
   this.hide();
}
function FCheckWindow_onBuildButtons(){
   var o = this;
   var hBtnTab = RBuilder.appendTable(o.hButtonPanel, null, 0, 0, 6);
   var hRow = hBtnTab.insertRow();
   var b = o.btnClose = RClass.create( FButton );
   b.icon = 'tool.exit';
   b.label = RContext.get('FToolButton:close');
   b.width = '100%';
   b.lsnsClick.register(o, o.onClose);
   b.psBuild(hRow.insertCell());
}
function FCheckWindow_oeBuild(e){
   var o = this;
   var r = o.base.FWindow.oeBuild.call(o, e);
   if(e.isAfter()){
      o.setIcon('Picker');
      o.setCaption(' 检查错误');
      var hb = o.hBodyPanel;
      hb.vAlign = 'top';
      var hTab = RBuilder.appendTable(o.hBodyPanel);
      o.hBodyPanel.style.height = '400';
      hTab.width = '100%';
      var h = o.hButtonPanel = hTab.insertRow().insertCell();
      h.height = 1;
      h.style.borderBottomStyle = 'solid';
      h.style.borderBottomWidth  = 'thin';
      var h = o.hTablePanel = hTab.insertRow().insertCell();
      h.style.border = '1px solid #A9C9E2';
      var hdv = o.hDivForm = RBuilder.appendDiv(h);
      var hHeadForm = RBuilder.appendTable(hdv);
      hHeadForm.style.width = '100%';
      var hc = hHeadForm.insertRow().insertCell();
      hc.style.height = '30';
      hc.style.backgroundColor = '#DBE5FA';
      hc.innerText =  RContext.get('FCheckWindow:Title');
      hc.style.padding = 10;
      hdv.scroll = 'auto';
      var hf = o.hCtrForm = RBuilder.appendTable(h);
      hf.style.padding = 10;
      hf.style.width = '100%';
      RConsole.find(FKeyConsole).register(EKey.Esc, new TListener(o, o.onClose));
   }
   return r;
}
function FCheckWindow_set(tl){
   var o = this;
   var h = o.hCtrForm;
   h.innerText = '';
   o.ctrs = tl;
   for(var n = 0; n < tl.count; n++){
      var c = tl.get(n);
      var hr = h.insertRow();
      if(n % 2 == 0){
         hr.style.backgroundColor = '#EEFAFF';
      }else{
         hr.style.backgroundColor = '#FAFCFD';
      }
      var hc1 = hr.insertCell();
      hc1.style.height = '10px';
      hc1.innerText = n + 1;
      hc1.width = '20';
      var hc2 = hr.insertCell();
      hc2.innerHTML = '<span>' + RString.trim(c.label) + '</span>';
      var htx = hc2.childNodes[0];
      htx.style.textDecoration = "underline";
      htx.style.cursor = 'hand';
      o.attachEvent('onSelect', htx);
      var hc3 = hr.insertCell();
      hc3.innerText = c._validText;
   }
}
function FCheckWindow_show(){
   var o = this;
   o.base.FWindow.show.call(o);
   RWindow.setEnable(false, true);
   RWindow.moveCenter(o.hPanel);
   o.psVisible(true);
   o.focus();
}
function FCheckWindow_hide(){
   var o = this;
   o.base.FWindow.hide.call(o);
   RWindow.setEnable(true);
}
function FCheckWindow_dispose(){
   var o = this;
   o.base.FWindow.dispose.call(o);
   o.hBodyPanel = null;
   o.hTablePanel = null;
   o.hCtrForm = null;
   o.hPanel = null;
}
function FConfirmWindow(o){
   o = RClass.inherits(this, o, FWindow);
   o.type            = null;
   o.isDialog        = false;
   o.titleBlur       = false;
   o.stMsgPanel      = RClass.register(o, new TStyle('MsgPanel'));
   o.stButtonPanel   = RClass.register(o, new TStyle('ButtonPanel'));
   o.stItmeForm      = RClass.register(o, new TStyle('ItmeForm'));
   o.stItemTitle     = RClass.register(o, new TStyle('ItemTitle'));
   o.stItemBodyForm  = RClass.register(o, new TStyle('ItemBodyForm'));
   o.stRowItem       = RClass.register(o, new TStyle('RowItem'));
   o.stDescForm      = RClass.register(o, new TStyle('DescForm'));
   o.stDescTitle     = RClass.register(o, new TStyle('DescTitle'));
   o.stDescBody      = RClass.register(o, new TStyle('DescBody'));
   o.hMessagePanel   = null;
   o.hMessages       = null;
   o.hDescription    = null;
   o.hButtonPanel    = null;
   o.lsns            = new TListeners();
   o.oeBuild         = FConfirmWindow_oeBuild;
   o.buildEdit       = FConfirmWindow_buildEdit;
   o.onOk            = FConfirmWindow_onOk;
   o.onCancel        = FConfirmWindow_onCancel;
   o.onClose         = FConfirmWindow_onClose;
   o.setText         = FConfirmWindow_setText;
   o.show            = FConfirmWindow_show;
   o.hide            = FConfirmWindow_hide;
   o.dispose         = FConfirmWindow_dispose;
   return o;
}
function FConfirmWindow_oeBuild(e){
   var o = this;
   var r = o.base.FWindow.oeBuild.call(o, e);
   if(e.isAfter()){
     o.hPanel.style.width = '380px';
      o.buildEdit();
      RConsole.find(FKeyConsole).register(EKey.Esc, new TListener(o, o.onClose));
   }
   return r;
}
function FConfirmWindow_onOk(){
   this.hide();
   this.lsns.process(true);
   return true;
}
function FConfirmWindow_onCancel(){
   this.hide();
   return true;
}
function FConfirmWindow_onClose(){
   this.hide();
   return false
}
function FConfirmWindow_setText(t){
   this.hText.innerText = ' ' + t;
}
function FConfirmWindow_show(){
   var o = this;
   o.base.FWindow.show.call(o);
   RWindow.setEnable(false, true);
   o.panel().style.zIndex = RLayer.next(ELayer.Message);
   top.RWindow.moveCenter(o.panel());
   o.psMode(EMode.Update);
   RConsole.find(FFocusConsole).blur();
   o.focus();
}
function FConfirmWindow_buildEdit(){
   var o = this;
   var h = o.hBodyPanel;
   o.hBodyPanel.style.height = 100;
   var hTab = RBuilder.appendTable(o.hBodyPanel, 0, 0, 0);
   hTab.style.height = '100%';
   hTab.style.width = '100%';
   var hRow1 = hTab.insertRow();
   var hRow1 = hTab.insertRow();
   var hcp = hRow1.insertCell();
   var htp = RBuilder.appendTable(hcp, 0, 0, 0);
   var hcr = htp.insertRow();
   var hcb = hcr.insertCell();
   hcb.width=10;
   var hcm = hcr.insertCell();
   hg = o.hImg = RBuilder.appendImage(hcm, 'FConfirmWindow_Confirm');
   hg.style.width = 32;
   hg.style.height = 33;
   var hc11 = o.hText = hcr.insertCell();
   hc11.vAlign = 'middle';
   hc11.align = 'center';
   var hRow3 = hTab.insertRow();
   var hc31 = hRow3.insertCell();
   hc31.align = 'right';
   var hBtb = RBuilder.appendTable(hc31);
   var hBr = hBtb.insertRow();
   var hBc1 = hBr.insertCell();
   var b = o.btnOk = RClass.create(FButton);
   b.icon = 'tool.ok';
   b.label = RContext.get('FToolButton:ok');
   b.lsnsClick.register(o, o.onOk);
   b.psBuild(hBc1);
   var hBc3 = hBr.insertCell();
   hBc3.width = 10;
   var hBc2 = hBr.insertCell();
   var b = o.btnCancel = RClass.create(FButton);
   b.icon = 'tool.cancel';
   b.label = RContext.get('FToolButton:cancel');
   b.lsnsClick.register(o, o.onCancel);
   b.psBuild(hBc2);
   o.setCaption('操作确认');
   o.setIcon('Confirm');
}
function FConfirmWindow_hide(){
   var o = this;
   o.base.FWindow.hide.call(o);
   RWindow.setEnable(true);
}
function FConfirmWindow_dispose(){
   var o = this;
   o.base.FWindow.dispose.call(o);
   RMemory.freeHtml(o.hBodyPanel);
   RMemory.freeHtml(o.hText);
   o.hBodyPanel = null;
   o.hText = null;
}
function FFormWindow(o){
   o = RClass.inherits(this, o, FWindow);
   o.styleTablePanel  = RClass.register(o, new TStyle('TablePanel'));
   o.styleButtonPanel = RClass.register(o, new TStyle('ButtonPanel'));
   o.table          = null;
   o.lovControl     = null;
   o.hForm          = null;
   o.hMessages      = null;
   o.oeBuild        = FFormWindow_oeBuild;
   o.onBuildButtons = FFormWindow_onBuildButtons;
   o.onSelect       = FFormWindow_onSelect;
   o.onClose        = FFormWindow_onClose;
   o.onSearch       = FFormWindow_onSearch;
   o.linkForm       = FFormWindow_linkForm;
   o.show           = FFormWindow_show;
   o.hide           = FFormWindow_hide;
   o.focus          = FFormWindow_focus;
   o.dispose        = FFormWindow_dispose;
   return o;
}
function  FFormWindow_onSearch(){
   var o = this;
   var lov_searchBox = RControl.create(FSearchWindow);
   lov_searchBox.linkDsControl(o.table);
   lov_searchBox.show();
}
function FFormWindow_oeBuild(e){
   var o = this;
   var r = o.base.FWindow.oeBuild.call(o, e);
   if(e.isAfter()){
      o.setIcon('Picker');
      var hTab = RBuilder.appendTable(o.hBodyPanel);
      o.hBodyPanel.style.height = '550px';
      hTab.width = '100%';
      hTab.height = '100%';
      var h = o.hTablePanel = hTab.insertRow().insertCell();
      h.vAlign = 'top';
      RConsole.find(FKeyConsole).register(EKey.Esc, new TListener(o, o.onClose));
   }
   return r;
}
function FFormWindow_onBuildButtons(){
   var o = this;
   var hBtnTab = RBuilder.appendTable(o.hButtonPanel, null, 0, 0, 6);
   var hRow = hBtnTab.insertRow();
   var b = o.btnClose = RClass.create(FButton);
   b.icon = 'tool.exit';
   b.label = RContext.get('FToolButton:close');
   b.width = '100%';
   b.lsnsClick.register(o, o.onClose);
   b.psBuild(hRow.insertCell());
}
function FFormWindow_onSelect(row){
   var o = this;
   var lov = o.lovControl;
   var pack = RPack.split(lov.lovFields, ',', '=');
   var ds = lov.topControl(MDataset);
   if(RClass.isClass(ds, FForm)){
      for(var n=0; n<pack.count; n++){
         var fn = pack.name(n);
         var fv = pack.value(n);
         if(RString.startsWith(fv, '${') && RString.endsWith(fv, '}')){
            fv = fv.substr(2, fv.length-3);
            fv = row.get(fv);
         }
         ds.set(fn, fv);
      }
   }else if(RClass.isClass(ds, FRow)){
   }
   if(RClass.isClass(lov, MFocus)){
      RConsole.find(FFocusConsole).focusClass(MDataset, ds);
      RConsole.find(FEventConsole).add(lov, lov.focus);
   }
   o.hide();
}
function FFormWindow_onClose(){
   this.hide();
}
function FFormWindow_buildField(c){
   var o = this;
   var hCell = o.hFieldsTab.insertRow().insertCell();
   hCell.innerText = c.label;
   o.fieldsPanel = RControl.create(FPanel);
   o.fieldsPanel.psBuild();
   o.fieldsPanel.setPanel(hCel);
}
function FFormWindow_linkForm(g){
   var o = this;
   var f = o.form = RConsole.find(FFormConsole).createFromName(g.formName, o.hTablePanel);
   f.hPanel.style.width = '100%';
   f.hPanel.style.height = '100%';
   f.psMode(EMode.Update);
   f.setVisible(true);
   f.dsValues = g.values;
   f.psRefresh();
   f.focus();
   f.dsFetch(true, true);
   if(RClass.isClass(f, FForm)){
      o.hIcon.src = RRes.iconPath('#tbr.formEdit');
   }else{
      o.hIcon.src = RRes.iconPath('#tbr.table');
   }
   o.setCaption(f.label);
}
function FFormWindow_show(){
   var o = this;
   o.base.FWindow.show.call(o);
   RWindow.moveCenter(o.hPanel);
   o.psVisible(true);
   RWindow.setEnable(false, true);
   o.focus();
}
function FFormWindow_hide(){
   var o = this;
   o.base.FWindow.hide.call(o);
   RWindow.setEnable(true);
}
function FFormWindow_focus(){
   var o = this;
   o.base.FWindow.focus.call(o);
   var t = o.table;
   if(t && t.hPage){
      t.hPage.focus();
   }
}
function FFormWindow_dispose(){
   var o = this;
   o.base.FWindow.dispose.call(o);
   RMemory.freeHtml(o.hBodyPanel);
   RMemory.freeHtml(o.hTablePanel);
   RMemory.freeHtml(o.hButtonPanel);
   o.hBodyPanel       = null;
   o.hTablePanel      = null;
   o.hButtonPanel     = null;
}
function FHintWindow(o){
   o = RClass.inherits(this, o, FControl);
   o.hPanel       = null;
   o.hForm        = null;
   o.hTitleRow    = null;
   o.hTitleTd     = null;
   o.hBoxRow      = null;
   o.hBoxTd       = null;
   o.linkObj      = null;
   o.oeBuild      = FHintWindow_oeBuild;
   o.onBuildPanel = FHintWindow_onBuildPanel;
   o.linkControl  = FHintWindow_linkControl;
   o.show         = FHintWindow_show;
   o.hide         = FHintWindow_hide;
   o.setPosition  = FHintWindow_setPosition;
   o.dispose      = FHintWindow_dispose;
   return o;
}
function FHintWindow_oeBuild(e){
   var o = this;
   o.base.FControl.oeBuild.call(o, e);
   if(e.isBefore()){
      var f = o.hForm  = RBuilder.appendTable(o.hPanel);
      var tRow = o.hTitleRow = f.insertRow();
      var tTd  = o.hTitleTd  = tRow.insertCell();
      tTd.width = '100';
      tTd.vAlign = 'center';
      tTd.height = '40';
      var bRow = o.hBoxRow   = f.insertRow();
      var bTd  = o.hBoxTd = bRow.insertCell();
   }
   return EEventStatus.Continue;
}
function FHintWindow_onBuildPanel(){
   var hp = this.hPanel = RBuilder.appendDiv();
   hp.style.position = 'absolute';
   hp.style.border = '1px solid #77AFEE';
   hp.style.backgroundColor = 'white';
   hp.style.padding = '6';
   hp.style.zindex = RLayer.next();
}
function FHintWindow_linkControl(c){
   var o = this;
   this.hide();
   o.linkObj = c;
   var r = RHtml.rect(c.hPanel);
   var tr = o.calcRect();
   r.bottom = r.bottom + 5;
   o.setBounds(r.left, r.bottom);
   if(c.hotkey){
      o.hTitleTd.innerHTML= "快捷按键:"+"<br><B>"+"<font size='4'>"+c.hotkey+"</font></B>";
      if(c.hint){
         o.hBoxTd.innerHTML = "功能简介:"+"<br>"+c.hint;
      }
   }
}
function FHintWindow_show(){
   var o = this;
   o.hPanel.style.display = 'block';
}
function FHintWindow_hide(){
   var o = this;
   o.hPanel.style.display = 'none';
}
function FHintWindow_setPosition(left,top){
   var o = this;
   o.hPanel.style.top  = left;
   o.hPanel.style.left = top;
}
function FHintWindow_dispose(){
   var o = this;
   o.base.FControl.dispose.call(o);
   RMemory.freeHtml(o.hTitleRow);
   RMemory.freeHtml(o.hTitleTd);
   RMemory.freeHtml(o.hBoxRow);
   RMemory.freeHtml(o.hBoxTd);
   o.hTitleRow = null;
   o.hTitleTd = null;
   o.hBoxRow = null;
   o.hBoxTd = null;
}
function FInfoWindow(o, c){
   o = RClass.inherits(this, o, FWindow);
   o.type            = null;
   o.isDialog        = false;
   o.titleBlur       = false;
   o.content         = c;
   o.icon             = '#com.FInfoWindow_Info';
   o.result          = false;
   o.stMsgPanel      = RClass.register(o, new TStyle('MsgPanel'));
   o.stButtonPanel   = RClass.register(o, new TStyle('ButtonPanel'));
   o.stItmeForm      = RClass.register(o, new TStyle('ItmeForm'));
   o.stItemTitle     = RClass.register(o, new TStyle('ItemTitle'));
   o.stItemBodyForm  = RClass.register(o, new TStyle('ItemBodyForm'));
   o.stRowItem       = RClass.register(o, new TStyle('RowItem'));
   o.stDescForm      = RClass.register(o, new TStyle('DescForm'));
   o.stDescTitle     = RClass.register(o, new TStyle('DescTitle'));
   o.stDescBody      = RClass.register(o, new TStyle('DescBody'));
   o.lsns            = new TListeners();
   o.hMessagePanel   = null;
   o.hMessages       = null;
   o.hDescription    = null;
   o.hButtonPanel    = null;
   o.oeBuild         = FInfoWindow_oeBuild;
   o.buildEdit       = FInfoWindow_buildEdit;
   o.onOk            = FInfoWindow_onOk;
   o.onCancel        = FInfoWindow_onCancel;
   o.onClose         = FInfoWindow_onClose;
   o.setText         = FInfoWindow_setText;
   o.show            = FInfoWindow_show;
   o.hide            = FInfoWindow_hide;
   o.dispose         = FInfoWindow_dispose;
   return o;
}
function FInfoWindow_oeBuild(e){
   var o = this;
   var r = o.base.FWindow.oeBuild.call(o, e);
   if(e.isAfter()){
     o.hPanel.style.width = '400px';
      o.buildEdit();
      RConsole.find(FKeyConsole).register(EKey.Esc, new TListener(o, o.onClose));
   }
   return r;
}
function FInfoWindow_onOk(){
   this.hide();
   this.result = true;
}
function FInfoWindow_onCancel(){
   this.hide();
   this.result = false;
   return true;
}
function FInfoWindow_onClose(){
   this.hide();
   this.result = false;
   return false
}
function FInfoWindow_setText(t){
   this.hText.innerText = ' ' + t;
}
function FInfoWindow_show(){
   var o = this;
   o.base.FWindow.show.call(o);
   o.panel().style.zIndex = RLayer.next(ELayer.Message);
   RWindow.moveCenter(o.panel());
   o.psMode(EMode.Update);
   RConsole.find(FFocusConsole).blur();
   top.RWindow.setEnable(false, true);
   o.focus();
}
function FInfoWindow_buildEdit(){
   var o = this;
   var h = o.hBodyPanel;
   o.hBodyPanel.style.height = 100;
   var hTab = RBuilder.appendTable(o.hBodyPanel, 0, 0, 0);
   hTab.style.height = '100%';
   hTab.style.width = '100%';
   var hRow1 = hTab.insertRow();
   var hcp = hRow1.insertCell();
   var htp = RBuilder.appendTable(hcp, 0, 0, 0);
   var hcr = htp.insertRow();
   var hc11 = o.hText = hcr.insertCell();
   hc11.style.width = '400px';
   hc11.style.height = '50px';
   hc11.vAlign = 'middle';
   hc11.align = 'center';
   var hRow3 = hTab.insertRow();
   var hc31 = hRow3.insertCell();
   hc31.align = 'right';
   var hBtb = RBuilder.appendTable(hc31);
   var hBr = hBtb.insertRow();
   var hBc1 = hBr.insertCell();
   var b = o.btnOk = RClass.create(FButton);
   b.icon = 'tool.ok';
   b.label = RContext.get('FToolButton:ok');
   b.lsnsClick.register(o, o.onOk);
   b.psBuild(hBc1);
   o.setIcon('TitleInfo');
   o.setCaption('操作提示');
}
function FInfoWindow_hide(){
   var o = this;
   o.base.FWindow.hide.call(o);
   top.RWindow.setEnable(true);
}
function FInfoWindow_dispose(){
   var o = this;
   o.base.FWindow.dispose.call(o);
   RMemory.freeHtml(o.hBodyPanel);
   RMemory.freeHtml(o.hText);
   o.hBodyPanel = null;
   o.hText = null;
}
function FInputWindow(o){
   o = RClass.inherits(this, o, FWindow);
   o.type            = null;
   o.isDialog        = false;
   o.titleBlur       = false;
   o.source          = null;
   o.lsns            = new TListeners();
   o.stMsgPanel      = RClass.register(o, new TStyle('MsgPanel'));
   o.stButtonPanel   = RClass.register(o, new TStyle('ButtonPanel'));
   o.stItmeForm      = RClass.register(o, new TStyle('ItmeForm'));
   o.stItemTitle     = RClass.register(o, new TStyle('ItemTitle'));
   o.stItemBodyForm  = RClass.register(o, new TStyle('ItemBodyForm'));
   o.stRowItem       = RClass.register(o, new TStyle('RowItem'));
   o.stDescForm      = RClass.register(o, new TStyle('DescForm'));
   o.stDescTitle     = RClass.register(o, new TStyle('DescTitle'));
   o.stDescBody      = RClass.register(o, new TStyle('DescBody'));
   o.hMessagePanel   = null;
   o.hMessages       = null;
   o.hDescription    = null;
   o.hButtonPanel    = null;
   o.oeBuild         = FInputWindow_oeBuild;
   o.buildEdit       = FInputWindow_buildEdit;
   o.onOk            = FInputWindow_onOk;
   o.onCancel        = FInputWindow_onCancel;
   o.onClose         = FInputWindow_onClose;
   o.show            = FInputWindow_show;
   o.hide            = FInputWindow_hide;
   o.dispose         = FInputWindow_dispose;
   return o;
}
function FInputWindow_oeBuild(e){
   var o = this;
   var r = o.base.FWindow.oeBuild.call(o, e);
   if(e.isAfter()){
     o.hPanel.style.width = '300px';
     o.setCaption('');
      o.buildEdit();
      RConsole.find(FKeyConsole).register(EKey.Esc, new TListener(o, o.onClose));
   }
   return r;
}
function FInputWindow_onOk(){
   var o = this;
   o.hide();
   o.lsns.process(o.hEdit.value);
}
function FInputWindow_onCancel(){
   var o = this;
   this.hide();
}
function FInputWindow_onClose(){
   this.hide();
}
function FInputWindow_show(){
   var o = this;
   o.base.FWindow.show.call(o);
   RWindow.setEnable(false, true);
   o.panel().style.zIndex = RLayer.next(ELayer.Message);
   top.RWindow.moveCenter(o.panel());
   o.psMode(EMode.Update);
   RConsole.find(FFocusConsole).blur();
   o.focus();
}
function FInputWindow_buildEdit(){
   var o = this;
   var h = o.hBodyPanel;
   h.style.backgroundColor='#CCCCCC';
   o.hBodyPanel.style.height = 100;
   var hTab = RBuilder.appendTable(o.hBodyPanel, 0, 0, 0);
   hTab.style.height = '100%';
   hTab.style.width = '100%';
   var hRow1 = hTab.insertRow();
   var hc11 = hRow1.insertCell();
   hc11.align = 'left';
   hc11.innerText = '  请输入文件夹名称：';
   var hRow2 = hTab.insertRow();
   var hc21 = hRow2.insertCell();
   var he = o.hEdit = RBuilder.append(hc21, 'INPUT');
   hc21.align = 'center';
   he.height = 25;
   he.width = 250;
   he.size = 40;
   he.style.fontSize = '14pt';
   he.style.fontStyle = 'italic';
   var hRow3 = hTab.insertRow();
   var hc31 = hRow3.insertCell();
   hc31.align = 'center';
   var hBtb = RBuilder.appendTable(hc31);
   hBtb.style.width = '200px';
   var hBr = hBtb.insertRow();
   var hBc1 = hBr.insertCell();
   var b = o.btnOk = RClass.create(FButton);
   b.icon = 'tool.ok';
   b.label = RContext.get('FToolButton:ok');
   b.lsnsClick.register(o, o.onOk);
   b.psBuild(hBc1);
   hBc1.width = 100;
   var hBc3 = hBr.insertCell();
   hBc3.width = 40;
   hBc3.innerText='         ';
   var hBc2 = hBr.insertCell();
   hBc2.width = 100;
   var b = o.btnCancel = RClass.create(FButton);
   b.icon = 'tool.cancel';
   b.label = RContext.get('FToolButton:cancel');
   b.lsnsClick.register(o, o.onCancel);
   b.psBuild(hBc2);
   o.setIcon('Icon');
}
function FInputWindow_hide(){
   var o = this;
   o.base.FWindow.hide.call(o);
   RWindow.setEnable(true);
}
function FInputWindow_dispose(){
   var o = this;
   o.base.FWindow.dispose.call(o);
   RMemory.freeHtml(o.hBodyPanel);
   RMemory.freeHtml(o.hEdit);
   o.hBodyPanel     = null;
   o.hEdit          = null;
}
function FListWindow(o){
   o = RClass.inherits(this, o, FWindow);
   o.stTablePanel   = RClass.register(o, new TStyle('TablePanel'));
   o.stButtonPanel  = RClass.register(o, new TStyle('ButtonPanel'));
   o._windowCd      = EWindow.ListView;
   o._form          = null;
   o.lovControl     = null;
   o.oeBuild        = FListWindow_oeBuild;
   o.onSelect       = FListWindow_onSelect;
   o.onClose        = FListWindow_onClose;
   o.onSearch       = FListWindow_onSearch;
   o.onBuildButtons = FListWindow_onBuildButtons;
   o.linkLovControl = FListWindow_linkLovControl;
   o.fetch          = FListWindow_fetch;
   o.show           = FListWindow_show;
   o.hide           = FListWindow_hide;
   o.focus          = FListWindow_focus;
   o.dispose        = FListWindow_dispose;
   return o;
}
function  FListWindow_onSearch(){
   var o = this;
   var lov_searchBox = RControl.create(FSearchWindow);
   lov_searchBox.linkDsControl(o._form);
   lov_searchBox.show();
}
function FListWindow_oeBuild(e){
   var o = this;
   var r = o.base.FWindow.oeBuild.call(o, e);
   if(e.isAfter()){
      o.setIcon('Picker');
      o.setCaption('List Of View');
      var hTab = RBuilder.appendTable(o.hBodyPanel);
      o.hBodyPanel.style.height = '500px';
      hTab.width = '100%';
      hTab.height = '100%';
      var h = o.hButtonPanel = hTab.insertRow().insertCell();
      h.height = 1;
      h.style.borderBottomStyle = 'solid';
      h.style.borderBottomWidth  = 'thin';
      var h = o.hTablePanel = hTab.insertRow().insertCell();
      h.className = o.style('TablePanel');
      RConsole.find(FKeyConsole).register(EKey.Esc, new TListener(o, o.onClose));
   }
   return r;
}
function FListWindow_onBuildButtons(){
   var o = this;
   var hBtnTab = RBuilder.appendTable(o.hButtonPanel, null, 0, 0, 6);
   var hRow = hBtnTab.insertRow();
   var b = o.btnClose = RClass.create(FButton);
   b.icon = 'tool.exit';
   b.label = RContext.get('FToolButton:close');
   b.width = '100%';
   b.lsnsClick.register(o, o.onClose);
   b.psBuild(hRow.insertCell());
}
function FListWindow_onSelect(row){
   var o = this;
   var lov = o.lovControl;
   var ds = lov.topControl(MDataset);
   var lr = null;
   if(RClass.isClass(ds, FForm)){
      lr = ds;
   }else if(RClass.isClass(ds, FGridControl)){
      lr = ds.__focusCell.row;
   }
   if(lr){
      var v = null;
      var pack = RPack.split(lov.lovFields, ',', '=');
      for(var n=0; n<pack.count; n++){
         var fn = pack.name(n);
         var fv = pack.value(n);
         if(RString.startsWith(fv, '${') && RString.endsWith(fv, '}')){
            fv = row.get(fv.substr(2, fv.length-3));
         }
         lr.set(fn, fv);
         if(RString.equals(fn, lov.dataName)){
            v = fv;
         }
      }
      lov.onListSelected(v);
   }
   if(RClass.isClass(lov, MFocus)){
      RConsole.find(FFocusConsole).focusClass(MDataset, ds);
      RConsole.find(FEventConsole).add(lov, lov.focus);
   }
   var e = o._eventRowSelect;
   if(!e){
      e = o._eventRowSelect = new TEvent();
      e.source = lov;
   }
   e.row = row;
   e.handle = 'onListAfter';
   o.linkConsole.find(FFormConsole).processEvent(e);
   o.hide();
}
function FListWindow_onClose(){
   this.hide();
}
function FListWindow_linkLovControl(lov){
   var o = this;
   o.lovControl = lov;
   var fc = RConsole.find(FFormConsole);
   if(o._form){
      fc.free(o._form);
   }
   var f = o._form = fc.createFromName(lov.lovRefer, o.hTablePanel, null, EForm.Lov);
   o.setCaption(RContext.get('FListWindow:caption') + ' - ' + f.label);
   f._window = o;
   f.lsnsRowClick.push(new TListener(o, o.onSelect));
   f.setVisible(true);
   f.hPanel.width = '100%';
   f.hPanel.height = '100%';
}
function FListWindow_fetch(cvs){
   var o = this;
   var t = o._form;
   t.dsGlobalSearchs.clear();
   var lov = o.lovControl;
   var ds = lov.topControl(MDataset);
   if(lov.lovWhere){
      var s = lov.lovWhere;
      var r = new TString();
      var f = '';
      var start = false;
      var cst = 0;
      var st = 0;
      var ed = 0;
      var count = 0;
      for(var n = 0; n < s.length; n++){
         var sst = s.charAt(n);
         if(sst == '$'){
            cst = n;
            start = true;
         }else if(sst == '{'){
            st = n;
            count++;
         }else if(sst == '}'){
            start = false;
            if(count != 0){
               ed = n;
               f = s.substring(st+1, ed);
               if(cvs){
             	  v = cvs.get(f);
             	  r.append(v);
               }else{
	               var cs = ds.components;
	               for(var m = 0; m < cs.count; m++){
	                  var c = cs.value(m);
	                  if(c && RClass.isClass(c, MValue)){
	                     if(c.dataName == f){
	                        var v = c.reget();
	                        r.append(v);
	                        break;
	                     }
	                  }
	               }
               }
               count--;
            }
            continue;
         }else{
            if(!start){
               r = r.append(sst);
            }
         }
      }
      var s = r.toString();
      var it = new TSearchItem('', s, ESearch.Source);
      t.dsGlobalSearchs.push(it);
   }
   var f = o._form;
   f.psResize();
   f.psRefresh();
   f.focus();
   t.dsFetch(true, true);
}
function FListWindow_show(){
   var o = this;
   o.base.FWindow.show.call(o);
   o.psVisible(true);
   RWindow.moveCenter(o.hPanel);
   RWindow.setEnable(false, true);
   o.focus();
}
function FListWindow_hide(){
   var o = this;
   o.base.FWindow.hide.call(o);
   RWindow.setEnable(true);
}
function FListWindow_focus(){
   var o = this;
   o.base.FWindow.focus.call(o);
   var t = o._form;
   if(t && t.hPage){
      t.hPage.focus();
   }
}
function FListWindow_dispose(){
   var o = this;
   o.base.FWindow.dispose.call(o);
   o.hBodyPanel = null;
   o.hTablePanel = null;
   o.hButtonPanel = null;
}
function FMessageBox(o){
   o = RClass.inherits(this, o, FContainer);
   o.items          = new TList();
   o.dataEmpty      = true;
   o.type           = null;
   o.hForm          = null;
   o.hMessages      = null;
   o.ohCloseClick   = FMessageBox_ohCloseClick;
   o.ohOkClick      = FMessageBox_ohOkClick;
   o.ohCancelClick  = FMessageBox_ohCancelClick;
   o.ohCopyClick    = FMessageBox_ohCopyClick;
   o.oeBuild        = FMessageBox_oeBuild;
   o.onBuildPanel   = FMessageBox_onBuildPanel;
   o.onBuildMessage = FMessageBox_onBuildMessage;
   o.onBuildButton  = FMessageBox_onBuildButton;
   o.loadConfig     = FMessageBox_loadConfig;
   o.saveConfig     = FMessageBox_saveConfig;
   o.loadMessages   = FMessageBox_loadMessages;
   o.dispose        = FMessageBox_dispose;
   return o;
}
function FMessageBox_ohCloseClick(){
   RConsole.find(FMessageConsole).closeMessage();
}
function FMessageBox_ohOkClick(){
}
function FMessageBox_ohCancelClick(){
}
function FMessageBox_ohCopyClick(){
}
function FMessageBox_oeBuild(event){
   var o = this;
   o.base.FContainer.oeBuild.call(o, event);
   var hTab = RBuilder.appendTable(this.hPanel);
   hTab.width = '100%';
   hTab.height = '100%';
   var hRow = hTab.insertRow();
   o.MessagePanel = hRow.insertCell();
   o.onBuildMessage();
   var h = o.ButtonPanel = hRow.insertCell();
   h.className = o.style('ButtonPanel');
   o.onBuildButton();
   return EEventStatus.Stop;
}
function FMessageBox_onBuildPanel(){
   this.hPanel = RBuilder.create(null, 'DIV');
}
function FMessageBox_onBuildMessage(){
   var o = this;
   var hTab = o.hForm = RBuilder.appendTable(o.MessagePanel);
   hTab.cellPadding = 2;
   hTab.className = this.style('Panel');
   var hRow = hTab.insertRow();
   var hCel = hRow.insertCell();
   hCel.className = this.style('Title');
   hCel.innerText = 'Message:';
   var hRow = hTab.insertRow();
   var hCel = hRow.insertCell();
   hCel.className = this.style('Message');
   o.hMessages = RBuilder.appendTable(hCel);
   o.hMessages.width = '100%';
   var hRow = hTab.insertRow();
   var hCel = hRow.insertCell();
   hCel.className = this.style('Title');
   hCel.innerText = 'Description:';
   var hRow = hTab.insertRow();
   var hCel = hRow.insertCell();
   hCel.className = this.style('Description');
}
function FMessageBox_onBuildButton(){
   var o = this;
   var hBtnTab = RBuilder.appendTable(o.ButtonPanel);
   var hRow = hBtnTab.insertRow();
   var hCel = hRow.insertCell();
   var hRow = hBtnTab.insertRow();
   var hCel = hRow.insertCell();
   hCel.align = 'center';
   var h = o.hOk = RBuilder.append(hCel, 'DIV', o.style('Button'));
   h.innerText = 'Close';
   h.onclick = o.ohCloseClick;
   var hRow = hBtnTab.insertRow();
   var hCel = hRow.insertCell();
   hCel.align = 'center';
   var h = o.hCancel = RBuilder.append(hCel, 'DIV', o.style('Button'));
   h.innerText = 'Cancel';
   h.onclick = o.ohCloseClick;
   var hRow = hBtnTab.insertRow();
   var hCel = hRow.insertCell();
   hCel.align = 'center';
   var h = o.hCopy = RBuilder.append(hCel, 'DIV', o.style('Button'));
   h.innerText = 'Copy';
   h.onclick = o.ohCloseClick;
   var hRow = hBtnTab.insertRow();
   var hCel = hRow.insertCell();
   hCel.innerHTML = '&nbsp;';
}
function FMessageBox_loadConfig(config){
   var o = this;
   o.base.FContainer.loadConfig.call(o, config);
   o.dataEmpty = RBool.isTrue(config.get('data_empty'));
   return EStatus.Stop;
}
function FMessageBox_saveConfig(config){
   this.base.FContainer.saveConfig.call(this, config)
   config.set('data_empty', RBool.toString(this.dataEmpty));
}
function FMessageBox_loadMessages(messages){
   var o = this;
   RHtml.clear(o.hMessages);
   if(messages){
      var msgs = messages.items;
      for(var n=0; n<msgs.count; n++){
         var msg = msgs.get(n);
         if(msg){
            var hRow = o.hMessages.insertRow();
            var hCell = hRow.insertCell();
            hCell.className = o.style('MessageIcon');
            RBuilder.appendIcon(hCell, msg.icon());
            var hCell = hRow.insertCell();
            hCell.className = o.style('MessageText');
            RBuilder.appendText(hCell, msg.message);
         }
      }
      o.type = messages.type();
   }
}
function FMessageBox_dispose(){
   var o = this;
   o.base.FWindow.dispose.call(o);
   RMemory.freeHtml(o.hItmeForm);
   RMemory.freeHtml(o.hDescBody);
   RMemory.freeHtml(o.hDescDiv);
   o.hForm           = null;
   o.hCopy           = null;
   o.hMessages       = null;
}
function FMessageWindow(o){
   o = RClass.inherits(this, o, FWindow);
   o.type            = null;
   o.isDialog        = false;
   o.titleBlur       = false;
   o.messageArg      = null;
   o.stMsgPanel      = RClass.register(o, new TStyle('MsgPanel'));
   o.stButtonPanel   = RClass.register(o, new TStyle('ButtonPanel'));
   o.stItmeForm      = RClass.register(o, new TStyle('ItmeForm'));
   o.stItemTitle     = RClass.register(o, new TStyle('ItemTitle'));
   o.stItemBodyForm  = RClass.register(o, new TStyle('ItemBodyForm'));
   o.stRowItem       = RClass.register(o, new TStyle('RowItem'));
   o.stDescForm      = RClass.register(o, new TStyle('DescForm'));
   o.stDescTitle     = RClass.register(o, new TStyle('DescTitle'));
   o.stDescBody      = RClass.register(o, new TStyle('DescBody'));
   o.onItemOver      = RClass.register(o, new HMouseOver('onItemOver'), FMessageWindow_onItemOver);
   o.onItemClick     = RClass.register(o, new HClick('onItemClick'), FMessageWindow_onItemClick);
   o.onDescClick     = RClass.register(o, new HClick('onDescClick'), FMessageWindow_onDescClick);
   o.hMessagePanel   = null;
   o.hMessages       = null;
   o.hDescription    = null;
   o.hButtonPanel    = null;
   o.hBlank          = null;
   o.oeBuild         = FMessageWindow_oeBuild;
   o.onBuildMessages = FMessageWindow_onBuildMessages;
   o.onBuildButtons  = FMessageWindow_onBuildButtons;
   o.onOk            = FMessageWindow_onOk;
   o.onCancel        = FMessageWindow_onCancel;
   o.onClose         = FMessageWindow_onClose;
   o.loadMessages    = FMessageWindow_loadMessages;
   o.show            = FMessageWindow_show;
   o.hide            = FMessageWindow_hide;
   o.dispose         = FMessageWindow_dispose;
   return o;
}
function FMessageWindow_onItemOver(e){
   var o = this;
   var hf = o.hItemBodyForm;
   var h = e.hSource;
   return;
   h.style.backgroundColor = "BLUE";
   h.style.cousor = "hand";
}
function FMessageWindow_onItemClick(e){
   var o = this;
   var hf = o.hItemBodyForm;
   for(var n = 0; n < hf.rows.count; n++){
   }
   var h = e.hSource;
   var idx = h.rowIndex;
}
function FMessageWindow_onDescClick(e){
   var o = this;
   return;
   var st = o.hDescBody.style.display;
   if('none' == st){
      o.hDescBody.style.display = 'block';
   }else{
      o.hDescBody.style.display = 'none';
   }
}
function FMessageWindow_oeBuild(e){
   var o = this;
   var r = o.base.FWindow.oeBuild.call(o, e);
   if(e.isAfter()){
      o.setIcon('Icon');
      var hTab = RBuilder.appendTable(o.hBodyPanel, 0, 0, 0);
      hTab.style.vAlign = "top";
      hTab.width = '100%';
      hTab.height = '100%';
      var h1 = o.hTitlePanel = hTab.insertRow().insertCell();
      h1.style.height = "100%";
      h1.style.vAlign = "top";
      var h2 = o.hMsgPanel = hTab.insertRow().insertCell();
      h2.style.height = "100%";
      o.onBuildMessages();
      var h0 = o.hButtonPanel = hTab.insertRow().insertCell();
      h0.style.align = 'right';
      o.onBuildButtons();
      h0.height = 20;
      RConsole.find(FKeyConsole).register(EKey.Esc, new TListener(o, o.onClose));
   }
   return r;
}
function FMessageWindow_onBuildMessages(){
   var o = this;
   if(!o.type){
      var hTab1 = o.hItmeForm = RBuilder.appendTable(o.hTitlePanel);
      hTab1.style.height = "100%";
      hTab1.style.width = "100%";
      hTab1.style.vAlign = "top";
      var hItemTitle = o.hItemTitle = hTab1.insertRow().insertCell();
      hItemTitle.height = 25;
      var h = RBuilder.appendTable(hItemTitle);
      h.height = '100%';
      h.width = '100%';
      h.style.backgroundColor = "#F5F5F5";
      var hr = h.insertRow();
      var hc1 = hr.insertCell();
      hc1.width = '20';
      var hTitleIcon = RBuilder.appendIcon(hc1, null, null, 16, 14);
      hTitleIcon.style.paddingLeft = 20;
      hTitleIcon.src = o.styleIconPath('TitleIcon');
      var hc2 = hr.insertCell();
      hc2.innerText = ' '+ RContext.get('FMessageWindow:MessageContext');
      var hItemBody  = o.hItemBody = hTab1.insertRow().insertCell();
      hItemBody.height = 100;
      o.hItemBody.style.borderBottom = '2 solid #F5F5F5';
      hItemBody.style.padding = '5';
      hItemBody.vAlign = "top";
      var hDiv = RBuilder.appendDiv(hItemBody);
      hDiv.style.height = '100px';
      hDiv.style.overflow = "auto";
      var hItemBodyForm = o.hItemBodyForm = RBuilder.appendTable(hDiv);
      hItemBodyForm.style.border = '2px solid #FFFFFF';
      hItemBodyForm.width = "100%";
      hItemBodyForm.style.vAlign = "top";
      var hTab2 = o.hDescForm = RBuilder.appendTable(o.hMsgPanel);
      hTab2.style.tableLayout = "fixed";
      hTab2.style.border='2px solid #EEEDED';
      hTab2.style.borderTopWidth = 0;
   }
   o.hItmeForm.style.display = 'none';
   o.hDescForm.style.display = 'none';
   o.hMsgPanel.style.height = '100%';
   if(EMessage.Fatal == o.type || EMessage.Error == o.type){
      o.hItmeForm.style.display = 'block';
      o.hDescForm.style.display = 'block';
   }else{
      o.hItmeForm.style.display = 'block';
   }
}
function FMessageWindow_onBuildButtons(t){
   var o = this;
   if(!o.type){
      var hBtnTab = RBuilder.appendTable(o.hButtonPanel, null, 0, 0, 2);
      var hRow = hBtnTab.insertRow();
      var hc = o.hBlank = hRow.insertCell();
      hc.width='72%';
      var b = o.btnOk = RClass.create(FButton);
      b.icon = 'tool.ok';
      b.label = RContext.get('FToolButton:ok');
      b.width = '100%';
      b.lsnsClick.register(o, o.onOk);
      var hoc = hRow.insertCell();
      hoc.style.align='right';
      hoc.width='15%';
      b.psBuild(hoc);
      var b = o.btnCancel = RClass.create(FButton);
      b.icon = 'tool.cancel';
      b.label = RContext.get('FToolButton:cancel');
      b.width = '100%';
      b.lsnsClick.register(o, o.onCancel);
      var hcc = hRow.insertCell();
      hcc.width='15%';
      b.psBuild(hcc);
   }
   o.btnOk.hPanel.style.display = "none";
   o.btnCancel.hPanel.style.display = "none";
   if(EMessage.Warn == o.type){
      o.btnOk.hPanel.style.display = "block";
      o.btnCancel.hPanel.style.display = "block";
      o.hBlank.width = '72%';
   }else{
      o.btnOk.hPanel.style.display = "block";
      o.hBlank.width = '87%';
   }
}
function FMessageWindow_onOk(){
   var o = this;
   var g = o.messageArg;
   var cg = g.argument;
   var type = o.msgs.get(0).type;
   if(EMessage.Warn == type){
      if(cg){
         cg.checked = EBoolean.True;
         if('process' == cg.actionType){
            RConsole.find(FFormConsole).process(cg);
         }else if('update' == cg.actionType){
            RConsole.find(FDatasetConsole).update(cg);
         }
      }
   }
   if(type == EMessage.Info){
      if(g.invokeCaller){
         g.invokeParam.messageChecked = true;
         g.invokeCaller.invoke(g.invokeParam);
      }
   }
   o.hide();
}
function FMessageWindow_onCancel(){
   this.hide();
}
function FMessageWindow_onClose(){
   this.hide();
}
function FMessageWindow_loadMessages(g){
   var o = this;
   o.messageArg = g;
   var ms = g.messages;
   o.type = ms.type();
   o.onBuildButtons();
   o.onBuildMessages();
   RHtml.clear(o.hItemBodyForm);
   RHtml.clear(o.hDescDiv);
   var first = true;
   var msgs = o.msgs = ms.items;
   var msgType = EMessage.Info;
   for(var n=0; n<msgs.count; n++){
      var msg = msgs.get(n);
      var m = msg.message;
      var d = msg.description;
      var t = msg.type;
      var hr = o.hItemBodyForm.insertRow();
      hr.height = 12;
      var hc1 = hr.insertCell();
      hc1.width = 20;
      var hIcon =  RBuilder.appendIcon(hc1, null, n, 16, 16);
      if(EMessage.Error == t){
    	 o.setIcon('TitleError');
         hIcon.src = o.styleIconPath('ItemError');
         msgType = EMessage.Error;
      }else if(EMessage.Warn == t){
    	 o.setIcon('TitleWarn');
         hIcon.src = o.styleIconPath('ItemWarn');
         msgType = EMessage.Warn;
      }else if(EMessage.Info == t){
    	 o.setIcon('TitleInfo');
         msgType = EMessage.Info;
         hIcon.src = o.styleIconPath('ItemInfo');
      }else if(EMessage.Fatal == t){
         msgType = EMessage.Fatal;
         hIcon.src = o.styleIconPath('ItemError');
      }
      var hc2 = hr.insertCell();
      hc2.style.textOverflow = 'ellipsis';
      hc2.style.overflow = 'hidden';
      hc2.innerText = ' ' + m;
      hc2.style.cursor = "hand";
      o.attachEvent('onItemClick', hr);
      o.attachEvent('onItemOver', hr);
      if(first){
         first = false;
      }
   }
   if(EMessage.Error == msgType){
      o.setCaption(' ' + RContext.get('FMessageWindow:Error'));
   }else if(EMessage.Warn == msgType){
      o.setCaption(' ' + RContext.get('FMessageWindow:Warn'));
   }else if(EMessage.Info == msgType){
      o.setCaption(' ' + RContext.get('FMessageWindow:Info'));
   }else if(EMessage.Fatal == msgType){
      o.setCaption(' ' + RContext.get('FMessageWindow:Fatal'));
   }
}
function FMessageWindow_show(){
   var o = this;
   o.base.FWindow.show.call(o);
   o.panel().style.zIndex = RLayer.next(ELayer.Message);
   RWindow.moveCenter(o.panel());
   o.psMode(EMode.Update);
   RConsole.find(FFocusConsole).blur();
   RWindow.setEnable(false, true);
   o.focus();
}
function FMessageWindow_hide(){
   var o = this;
   o.base.FWindow.hide.call(o);
   var f = o.messageArg.argument.form;
   if(RClass.isClass(f, MDataset)){
      f.psProgress(false);
   }
   RWindow.setEnable(true);
}
function FMessageWindow_dispose(){
   var o = this;
   o.base.FWindow.dispose.call(o);
   o.hItmeForm = null;
   o.hDescBody = null;
   o.hDescDiv = null;
   o.hDescTitle = null;
   o.hItemBodyForm = null;
   o.hButtonPanel = null;
}
function FPageBar(o){
   o = RClass.inherits(this, o, FContainer);
   o.tabs         = new TMap();
   o.selected     = null;
   o.hTop         = null;
   o.hLine        = null;
   o.hBottom      = null;
   o.hSheets      = null;
   o.oeBuild      = FPageBar_oeBuild;
   o.onBuildPanel = FPageBar_onBuildPanel;
   o.select       = FPageBar_select;
   o.tab          = FPageBar_tab;
   o.push         = FPageBar_push;
   o.dispose      = FPageBar_dispose;
   return o;
}
function FPageBar_oeBuild(e){
   var o = this;
   o.base.FContainer.oeBuild.call(o, e);
   if(e.isBefore()){
      o.hRows = RBuilder.append(o.hPanel, 'TBODY');
      o.hRow = RBuilder.append(o.hRows, 'TR');
   }else if(e.isAfter()){
      var ts = o.tabs;
      for(var n=0; n<ts.count; n++){
         o.hRow.appendChild(ts.value(n).hPanel);
      }
   }
}
function FPageBar_onBuildPanel(){
   this.hPanel = RBuilder.newTable();
}
function FPageBar_select(sheet){
   this.selected = sheet;
   for(var n=0; n<this.tabs.count; n++){
      var o = this.tabs.value(n);
      o.select(sheet == o);
   }
   sheet.psRefresh();
}
function FPageBar_tab(name){
   return this.sheets.get(name);
}
function FPageBar_push(c){
   var o = this;
   o.base.FContainer.push.call(o, c);
   if(RClass.isClass(c, FPageTab)){
      c.pageBar = o;
      c.index = o.tabs.count;
      o.tabs.set(c.name, c);
   }
}
function FPageBar_dispose(){
   var o = this;
   o.base.FContainer.dispose.call(o);
   RMemory.freeHtml(o.hPanel);
   RMemory.freeHtml(o.hRow);
   o.hRow = null;
   o.hPanel = null;
}
function FPageTab(o){
   o = RClass.inherits(this, o, FControl);
   o.page         = RClass.register(o, new TPtyStr('page', null));
   o.icon         = RClass.register(o, new TPtyStr('icon', null));
   o.action       = RClass.register(o, new TPtyStr('action', null));
   o.stPanel      = RClass.register(o, new TStyle('LabelPanel'));
   o.stButtonIcon = RClass.register(o, new TStyleIcon('Button'));
   o.pageBar      = null;
   o.index        = null;
   o.selected     = false;
   o.hasBuilded   = false;
   o.lsnsSelect   = new TListeners();
   o.hTopL        = null;
   o.hTop         = null;
   o.hTopR        = null;
   o.hLeft        = null;
   o.hButton      = null;
   o.hIcon        = null;
   o.hText        = null;
   o.hBottomL     = null;
   o.hBottom      = null;
   o.hBottomR     = null;
   o.hRight       = null;
   o.oeBuild      = FPageTab_oeBuild;
   o.onBuildPanel = FPageTab_onBuildPanel;
   o.onEnter      = FPageTab_onEnter;
   o.onLeave      = FPageTab_onLeave;
   o.onMouseDown  = FPageTab_onMouseDown;
   o.select       = FPageTab_select;
   o.dump         = FPageTab_dump;
   return o;
}
function FPageTab_oeBuild(e){
   var o = this;
   o.base.FControl.oeBuild.call(o, e);
   var b = o.border = RBorder.create(EBorder.Round, o.hPanel);
   var hb = b.hPanel;
   o.hPanel.width = '90'
   hb.align = 'center';
   hb.className = o.style('LabelPanel');
   hb.background = o.styleIconPath('Button');
   o.hPanel.appendChild(b.hForm);
   var label = o.label;
   if(o.icon){
      o.hIcon = RBuilder.appendIcon(hb, o.icon);
      label = ' ' + o.label;
   }
   RBuilder.appendText(hb, label);
   return EEventStatus.Stop;
}
function FPageTab_onBuildPanel(){
   this.hPanel = RBuilder.create(null, 'TD');
}
function FPageTab_onEnter(){
   if(!this.selected){
   }
}
function FPageTab_onLeave(){
   if(!this.selected){
   }
}
function FPageTab_onMouseDown(){
   var o = this;
   if(o.action){
      eval(o.action);
   }
}
function FPageTab_select(flag){
   var o = this;
   var b = o.pageBar;
   if(flag && !o.hasBuilded){
   }
   var first = (o.index == 0);
   var prior = (b.selected.index-1 == o.index);
   if(o.selected != flag){
      if(flag){
         o.lsnsSelect.process();
      }
      o.selected = flag;
   }
}
function FPageTab_dump(dump, space){
   dump = RString.nvlStr(dump);
   dump.append(space, RClass.name(this), ' [');
   dump.append('name=', this.name, ', ');
   dump.append('icon=', this.icon, ', ');
   dump.append('label=', this.label, ', ');
   dump.append('action=', this.action, ']');
   return dump;
}
function FPop(o){
   o = RClass.inherits(this, o, FContainer, MDisplayAble, MSizeable, MMoveable, MPopBorder);
   o.caption      = RClass.register(o, new TPtyStr('caption'));
   o.isDialog     = false;
   o.type         = null;
   o.oeBuild      = FPop_oeBuild;
   o.oeShow       = FPop_oeShow;
   o.onBuildPanel = FPop_onBuildPanel;
   o.panel        = FPop_panel;
   o.dump         = FPop_dump;
   return o;
}
function FPop_oeBuild(event){
   var o = this;
   o.base.FContainer.oeBuild.call(o, event);
   o.base.MPopBorder.oeBuild.call(o, event);
}
function FPop_oeShow(event){
   var o = this;
   o.base.FContainer.oeShow.call(o, event);
   if(event.isAfter()){
      o.hBorder.style.zIndex = RLayer.next(ELayer.Window);
   }
}
function FPop_onBuildPanel(){
   var o = this;
   o.base.MPopBorder.onBuildPanel.call(o);
}
function FPop_panel(t){
   var o = this;
   if(EPanel.Display == t || EPanel.Border == t || EPanel.Size == t){
      return o.hBorder;
   }
   else if(EPanel.Move == t){
      return o.hTitle;
   }
   return o.base.FContainer.panel.call(o, t);
}
function FPop_dump(oCtl, sLeft){
   var sDump = '';
   if(!oCtl){
      oCtl = this;
   }
   if(!sLeft){
      sLeft = '   ';
   }
   sDump += oCtl.className + '\n';
   if(oCtl.children){
      var arChildren = oCtl.children;
      for(var n=0; n<arChildren.length; n++){
         sDump += sLeft + this.dump(arChildren[n], sLeft + '   ');
      }
   }
   return sDump;
}
function FPop_pushAllControl(oCtl){
   if(!this.allControls){this.allControls = new Array();}
   this.allControls.push(oCtl);
}
function FPop_control(sName){
   if(this.allControls){
      for(var n=0; n<this.allControls.length; n++){
         if(this.allControls[n].name == sName){
            return this.allControls[n];
         }
      }
   }
   return null;
}
function FPop_restore(){
   this.max(true);
}
function FPop_processResize(){
   if(!SystemManager.runMode){
      var oRect = this.rect()
      this.width = oRect.width();
      this.height = oRect.height();
   }
   this.processEvent(this, IWindowEvent.RESIZE);
}
function FPop_fillAllControl(){
   var oControl = null;
   var nCount = this.controls.size();
   for(var n=0; n<nCount; n++){
      oControl = this.controls.value(n);
      if(oControl.fill){
         oControl.fill();
      }
   }
}
function FPop_refresh(bConfig){
   if(this.loadConfig){this.loadConfig();}
   this.setCaption(this.label);
   this.setWidth(this.width);
   this.setHeight(this.height);
   if(this.allControls){
      for(var n=0; n<this.allControls.length; n++){
         var oCtl = this.allControls[n];
         if(oCtl.refresh){
            if(bConfig && oCtl.reloadConfig){
               oCtl.reloadConfig();
            }
            oCtl.refresh();
         }
      }
   }
}
function FPop_initialize(){
   if(this.allControls){
      for(var n=0; n<this.allControls.length; n++){
         var oCtl = this.allControls[n];
         if(oCtl.initialize){oCtl.initialize();}
         if(oCtl.initializeControl){oCtl.initializeControl();}
      }
   }
}
function FPop_release(){
   if(this.allControls){
      for(var n=0; n<this.allControls.length; n++){
         var oCtl = this.allControls[n];
         if(oCtl.releaseControl){oCtl.releaseControl();}
         if(oCtl.release){oCtl.release();}
      }
   }
   this.htmlBorder.removeNode(true);
   DatasetManager.focus(null, true);
   WindowManager.releaseWindow(this);
}
function FPop_stopDropExecute(oSource){
   if(oSource.config && oSource.rect){
      var oRect = oSource.rect();
      oSource.config.setAttribute('left', oRect.left);
      oSource.config.setAttribute('top', oRect.top);
      oSource.config.setAttribute('width', oRect.width());
      oSource.config.setAttribute('height', oRect.height());
   }
   if(this.owner.onStopDrop){
      this.owner.onStopDrop(oSource);
   }
}
function FPop_selectDsExecute(oSource){
   if(oSource && oSource.constructor == FDatasetCtl){
      var bRefresh = (DatasetManager.activeDsCtl != oSource);
      DatasetManager.activeDsCtl = oSource;
      if(bRefresh){
         DatasetManager.refreshToolbar();
      }
   }
}
function FSearchEdit(o){
   o = RClass.inherits(this, o, FEdit, MDisplayAble, MSearch);
   o.labelType     = ELabel.All;
   o.searchBox     = null;
   o.oeBuild       = FSearchEdit_oeBuild;
   o.onDataKeyDown = FSearchEdit_onDataKeyDown;
   o.assign        = FSearchEdit_assign;
   o.setEditStyle  = FSearchEdit_setEditStyle;
   return o;
}
function FSearchEdit_oeBuild(e){
   var o = this;
   var r = o.base.FEdit.oeBuild.call(o, e);
   o.base.MSearch.oeBuild.call(o, e);
   return r;
}
function FSearchEdit_onDataKeyDown(s, e){
   var o = this;
   o.base.FEdit.onDataKeyDown.call(o, s, e);
   if(e.keyCode == EKey.Enter){
      o.blur();
      o.searchBox.onSearch();
   }
}
function FSearchEdit_assign(edit, type){
   var o = this;
   o.base.FEdit.assign.call(o, edit, type);
   o.base.MSearch.assign.call(o, edit, type);
}
function FSearchEdit_setEditStyle(style){
   var o = this;
   o.base.FEdit.setEditStyle.call(o, style);
   o.base.MSearch.setEditStyle.call(o, style);
}
function FSearchWindow(o){
   o = RClass.inherits(this, o, FWindow);
   o.type             = null;
   o.styleFieldPanel  = RClass.register(o, new TStyle('FieldPanel'));
   o.styleButtonPanel = RClass.register(o, new TStyle('ButtonPanel'));
   o.searchControls   = new TList();
   o.hForm            = null;
   o.hMessages        = null;
   o.oeBuild          = FSearchWindow_oeBuild;
   o.onBuildButtons   = FSearchWindow_onBuildButtons;
   o.onSearch         = FSearchWindow_onSearch;
   o.onClear          = FSearchWindow_onClear;
   o.onReset          = FSearchWindow_onReset;
   o.onClose          = FSearchWindow_onClose;
   o.buildField       = FSearchWindow_buildField;
   o.linkDsControl    = FSearchWindow_linkDsControl;
   o.show             = FSearchWindow_show;
   o.hide             = FSearchWindow_hide;
   o.dispose          = FSearchWindow_dispose;
   return o;
}
function FSearchWindow_oeBuild(e){
   var o = this;
   var r = o.base.FWindow.oeBuild.call(o, e);
   if(e.isAfter()){
      o.setIcon('Search');
      o.setCaption('Search');
      var ht = RBuilder.appendTable(o.hBodyPanel);
      ht.width = '100%';
      ht.height = '100%';
      var h1 = o.hButtonPanel = ht.insertRow().insertCell();
      h1.style.borderBottomStyle = 'solid';
      h1.style.borderBottomWidth  = 'thin';
      var h2 = o.hDataPanel = ht.insertRow().insertCell();
      h1.height = 1;
      o.onBuildButtons();
      RConsole.find(FKeyConsole).register(EKey.Esc, new TListener(o, o.onClose));
      var hTab = o.hDataForm = RBuilder.append(h2, "TABLE");
      hTab.style.tableLayout = "fixed";
      var h = o.FieldPanel = hTab.insertRow().insertCell();
      h.height = "400px";
      h.vAlign = 'top';
      var hDiv = o.hFieldForm = RBuilder.appendDiv(h);
      hDiv.style.height = '100%';
      hDiv.width = '100%';
      hDiv.style.paddingTop   = '20';
      hDiv.style.paddingLeft   = '20';
      hDiv.style.overflow = 'auto';
   }
   return r;
}
function FSearchWindow_onBuildButtons(){
   var o = this;
   var hBtnTab = RBuilder.appendTable(o.hButtonPanel, null, 0, 0, 6);
   var hRow = hBtnTab.insertRow();
   var hCel = hRow.insertCell();
   var b = o.btnSearch = RClass.create(FButton);
   b.icon = 'tool.search';
   b.label = RContext.get('FToolButton:search');
   b.width = '100%';
   b.lsnsClick.register(o, o.onSearch);
   b.psBuild(hRow.insertCell());
   var b = o.btnClose = RClass.create(FButton);
   b.icon = 'tool.exit';
   b.label = RContext.get('FToolButton:close');
   b.width = '100%';
   b.lsnsClick.register(o, o.onClose);
   b.psBuild(hRow.insertCell());
   var b = o.btnCancel = RClass.create(FButton);
   b.icon = 'tool.delete';
   b.label = RContext.get('FToolButton:clear');
   b.width = '100%';
   b.lsnsClick.register(o, o.onClear);
   b.psBuild(hRow.insertCell());
   var b = o.btnReset = RClass.create(FButton);
   b.icon = 'tool.refresh';
   b.label = RContext.get('FToolButton:reset');
   b.width = '100%';
   b.lsnsClick.register(o, o.onReset);
   b.psBuild(hRow.insertCell());
   var hCel = hRow.insertCell();
   hCel.innerHTML = '&nbsp;';
}
function FSearchWindow_onSearch(){
   var o = this;
   var dc = o.dsControl;
   dc.dsSearchs.clear();
   dc.dsOrders.clear();
   var cs = o.searchControls;
   if(cs){
      for(var n=0; n<cs.count; n++){
         var c = cs.get(n);
         if(!RString.isEmpty(c.text())){
            var si = new TSearchItem();
            var oi = new TOrderItem();
            si.set(c.dataName, c.text(), c.searchType.reget());
            oi.set(c.dataName, c.searchOrder.reget());
            dc.dsSearchs.push(si);
            dc.dsOrders.push(oi);
         }
      }
      dc.dsSearch(true, false);
   }
   o.hide();
}
function FSearchWindow_onClear(){
   var o = this;
   var cs = o.searchControls;
   if(cs){
      for(var n=0; n<cs.count; n++){
         cs.get(n).clearSearch();
      }
   }
}
function FSearchWindow_onReset(){
   var o = this;
   var cs = o.searchControls;
   if(cs){
      for(var n=0; n<cs.count; n++){
         cs.get(n).resetSearch();
      }
   }
}
function FSearchWindow_onClose(){
   this.hide();
}
function FSearchWindow_buildField(c){
   var o = this;
   var hCell = o.hFieldsTab.insertRow().insertCell();
   hCell.innerText = c.label;
   o.fieldsPanel = RControl.create(FPanel);
   o.fieldsPanel.build();
   o.fieldsPanel.setPanel(hCel);
}
function FSearchWindow_linkDsControl(dc){
   var o = this;
   o.dsControl = dc;
   var cs = dc.controls;
   if(cs){
      for(var n=0; n<cs.count; n++){
         var c = cs.value(n);
         if(c.dispSearch){
            var search = RClass.create(FSearchEdit);
            search.assign(c, EAssign.Property);
            search.labelType = ELabel.All;
            search.searchBox = o;
            search.psBuild(o.hFieldForm);
            search.psMode(EMode.Search);
            o.searchControls.push(search);
            o.push(search);
         }
      }
   }
}
function FSearchWindow_show(){
   var o = this;
   o.base.FWindow.show.call(o);
   RWindow.setEnable(false, true);
   RWindow.moveCenter(o.hPanel);
   o.psVisible(true);
   o.psRefresh();
   o.focus();
}
function FSearchWindow_hide(){
   var o = this;
   o.base.FWindow.hide.call(o);
   RWindow.setEnable(true);
}
function FSearchWindow_dispose(){
   var o = this;
   o.base.FWindow.dispose.call(o);
   RMemory.freeHtml(o.hFieldsTab);
   RMemory.freeHtml(o.hFieldForm);
   o.hFieldsTab = null;
   o.hFieldForm = null;
}
function FTabBar(o){
   o = RClass.inherits(this, o, FContainer);
   o.left             = 20;
   o.width            = '100%';
   o.sheets           = new TMap();
   o.selected         = null;
   o.hTop             = null;
   o.hLine            = null;
   o.hBottom          = null;
   o.oeBuild          = FTabBar_oeBuild;
   o.onBuildPanel     = FTabBar_onBuildPanel;
   o.appendChild      = FTabBar_appendChild;
   o.addClickListener = FTabBar_addClickListener;
   o.select           = FTabBar_select;
   o.push             = FTabBar_push;
   o.dispose          = FTabBar_dispose;
   return o;
}
function FTabBar_oeBuild(event){
   var o = this;
   o.base.FContainer.oeBuild.call(o, event);
   if(event.isBefore()){
      var h = o.hTop.insertCell();
      RBuilder.appendEmpty(h);
      h.width = 20;
      var h = o.hLine.insertCell();
      var h = RBuilder.append(o.hBottom, 'TD', this.style('Bottom'));
      RBuilder.appendEmpty(h);
   }else if(event.isAfter()){
      RBuilder.append(o.hTop, 'TD', this.style('Top'));
      h = RBuilder.append(o.hLine, 'TD');
      RBuilder.appendEmpty(h);
      RBuilder.append(o.hBottom, 'TD', this.style('Bottom'));
      if(o.sheets.count){
         o.select(o.sheets.value(0));
      }
   }
}
function FTabBar_appendChild(sheet){
   this.hTop.appendChild(sheet.hTopL);
   this.hTop.appendChild(sheet.hTop);
   this.hTop.appendChild(sheet.hTopR);
   this.hLine.appendChild(sheet.hLeft);
   this.hLine.appendChild(sheet.hPanel);
   this.hLine.appendChild(sheet.hRight);
   this.hBottom.appendChild(sheet.hBottomL);
   this.hBottom.appendChild(sheet.hBottom);
   this.hBottom.appendChild(sheet.hBottomR);
}
function FTabBar_onBuildPanel(){
   var o = this;
   var h = o.hPanel = RBuilder.append(o.hParent, 'TABLE');
   h.border = 0;
   h.frame = 'box';
   h.cellPadding = 0;
   h.cellSpacing = 0;
   o.hTop = h.insertRow();
   o.hLine = h.insertRow();
   o.hBottom = h.insertRow();
}
function FTabBar_addClickListener(){
   var btn = this.components.get(name);
   if(btn){
      btn.lsnsClick.push(new TListener(this, method));
   }
}
function FTabBar_select(sheet){
   this.selected = sheet;
   for(var n=0; n<this.sheets.count; n++){
      var o = this.sheets.value(n);
      o.select(sheet == o);
   }
}
function FTabBar_selectPage(idx, force){
   this.activeIndex = idx;
   var oPage = null;
   if(!force){
      for(var n=0; n<this.items.length; n++){
         if(this.items[n].name == this.activePageName){
            oPage = this.items[n];
            this.activePageName = oPage.name;
            this.activeIndex = n;
            break;
         }
      }
      if(!oPage){
         oPage = this.items[this.activeIndex];
         this.activePageName = oPage.name;
      }
   }
   if(oPage){
      this.activePage = oPage;
   }else{
      oPage = this.activePage;
   }
   if(oPage){
      this.clear();
      this.refresh();
      oPage = this.items[this.activeIndex];
      this.activePageName = oPage.name;
      if(this.onSheetClick){
         this.onSheetClick(oPage);
      }
   }
   return oPage;
}
function FTabBar_push(o){
   this.base.FContainer.push.call(this, o);
   if(RClass.isClass(o, FTabButton)){
      o.tabBar = this;
      o.index = this.sheets.count;
      this.sheets.set(o.name, o);
   }
}
function FTabBar_dispose(){
   var o = this;
   o.base.FContainer.dispose.call();
   RMemory.freeHtml(o.hTop);
   RMemory.freeHtml(o.hLine);
   RMemory.freeHtml(o.hBottom);
   o.hTop = null;
   o.hLine = null;
   o.hBottom = null;
}
function FTabButton(o){
   o = RClass.inherits(this, o, FControl, MLsnClick);
   o.icon         = null;
   o.caption      = null;
   o.action       = null;
   o.hint         = null;
   o.pageBar      = null;
   o.index        = null;
   o.selected     = false;
   o.oeBuild      = FTabButton_oeBuild;
   o.onBuildPanel = FTabButton_onBuildPanel;
   o.onEnter      = FTabButton_onEnter;
   o.onLeave      = FTabButton_onLeave;
   o.onMouseDown  = FTabButton_onMouseDown;
   o.onMouseUp    = FTabButton_onMouseUp;
   o.loadConfig   = FTabButton_loadConfig;
   o.saveConfig   = FTabButton_saveConfig;
   o.select       = FTabButton_select;
   o.dump         = FTabButton_dump;
   o.dispose      = FTabButton_dispose;
   return o;
}
function FTabButton_oeBuild(event){
   var o = this;
   var b = o.tabBar;
   o.hTopL = RBuilder.create(null, 'TD', b.style('Top'));
   o.hTop  = RBuilder.create(null, 'TD', b.style('Top'));
   o.hTopR = RBuilder.create(null, 'TD', b.style('Top'));
   o.hLeft = RBuilder.create(null, 'TD', b.style('Left'));
   RBuilder.appendEmpty(o.hLeft);
   o.hBottomL = RBuilder.create(null, 'TD', b.style('Bottom'));
   o.hBottom  = RBuilder.create(null, 'TD', b.style('Bottom'));
   o.hBottomR = RBuilder.create(null, 'TD', b.style('Bottom'));
   o.hRight = RBuilder.create(null, 'TD', b.style('Right'));
   RBuilder.appendEmpty(o.hRight);
   o.base.FControl.oeBuild.call(o, event);
   var tb = o.parent;
   var h = this.hPanel;
   o.hButton = RBuilder.append(this.hPanel, 'DIV', this.style('Button'));
   if(o.icon){
      o.hIcon = RBuilder.appendIcon(o.hButton, this.icon);
   }
   if(o.caption){
      o.hText = RBuilder.append(o.hButton, 'SPAN');
      o.hText.innerText = ' ' + this.caption;
   }
   return EEventStatus.Stop;
}
function FTabButton_onBuildPanel(){
   this.hPanel = RBuilder.create(null, 'TD', this.style('Panel'));
}
function FTabButton_onEnter(){
   if(!this.selected){
      this.hButton.className = this.style('Hover');
   }
}
function FTabButton_onLeave(){
   if(!this.selected){
      this.hButton.className = this.style('Button');
   }
}
function FTabButton_onMouseDown(){
   this.parent.select(this);
}
function FTabButton_onMouseUp(){
   this.processClick();
}
function FTabButton_loadConfig(config){
   var o = this;
   o.base.FControl.loadConfig.call(o, config);
   o.icon    = config.get('icon');
   o.caption = config.get('caption');
   o.action  = config.get('action');
   o.hint    = config.get('hint');
}
function FTabButton_saveConfig(config){
   var o = this;
   o.base.FControl.saveConfig.call(o, config);
   config.set('icon',    o.icon);
   config.set('caption', o.caption);
   config.set('action',  o.action);
   config.set('hint',    o.hint);
}
function FTabButton_select(flag){
   var o = this;
   o.selected = flag;
   var b = this.tabBar;
   var first = (o.index == 0);
   var prior = true;
   if(b.selected){
      prior = (b.selected.index-1 == o.index);
   }
   o.hButton.className = flag ? o.style('Select') : o.style('Button');
   o.hTop.className = flag ? b.style('TopSel') : b.style('Top');
   o.hLeft.className = flag ? b.style('LeftSel') : (first ? b.style('Right') : b.style('Left'));
   o.hBottomL.className = flag ? b.style('BottomSel') : b.style('Bottom');
   o.hBottom.className = flag ? b.style('BottomSel') : b.style('Bottom');
   o.hBottomR.className = flag ? b.style('BottomSel') : b.style('Bottom');
   o.hRight.className = flag ? b.style('RightSel') : (prior ? b.style('RightP') : b.style('Right'));
}
function FTabButton_dump(dump, space){
   dump = RString.nvlStr(dump);
   dump.append(space, RClass.name(this), ' [');
   dump.append('name=', this.name, ', ');
   dump.append('icon=', this.icon, ', ');
   dump.append('label=', this.label, ', ');
   dump.append('action=', this.action, ']');
   return dump;
}
function FTabButton_dispose(){
   var o = this;
   o.base.FControl.dispose.call(o);
   RMemory.freeHtml(o.hButton);
   RMemory.freeHtml(o.hTop);
   RMemory.freeHtml(o.hLeft);
   RMemory.freeHtml(o.hBottomL);
   RMemory.freeHtml(o.hBottom);
   RMemory.freeHtml(o.hBottomR);
   RMemory.freeHtml(o.hRight);
   o.hButton = null;
   o.hTop = null;
   o.hLeft = null;
   o.hBottomL = null;
   o.hBottom = null;
   o.hBottomR = null;
   o.hRight = null;
}
function FTimer(o){
   o = RClass.inherits(this, o, FControl, MDesign);
   o.path         = '/ars/icon/ctl/FTimer/';
   o.numberImgs   = new Array('0.png','1.png','2.png','3.png','4.png','5.png','6.png','7.png','8.png','9.png');
   o.lastDate     = null;
   o.lastTime     = null;
   o.hPanel       = null;
   o.hForm        = null;
   o.oeBuild      = FTimer_oeBuild;
   o.onBuildPanel = FTimer_onBuildPanel;
   o.onCalculate  = FTimer_onCalculate;
   o.construct    = FTimer_construct;
   o.dispose      = FTimer_dispose;
   return o;
}
function FTimer_oeBuild(e){
   var o = this;
   var r = o.base.FControl.oeBuild.call(o, e);
   var pf = o.hPanel
   var hip = o.hInfoPanel = pf.insertRow().insertCell();
   var hif = o.hInfoForm = RBuilder.appendTable(hip);
   hif.width = '100%';
   var hdr = hif.insertRow();
   var hcp = o.hCountryPanel = hdr.insertCell();
   hcp.innerText = RContext.get('FTimer:Country');
   hcp.style.fontWeight = 'bold';
   var hdp = o.hDatePanel = hdr.insertCell();
   hdp.style.fontWeight = 'bold';
   hdp.align = 'right';
   var htp = o.hTimePanel = pf.insertRow().insertCell();
   htp.style.paddingTop = 6;
   var htf = o.hTimeForm = RBuilder.appendTable(htp);
   var htr = htf.insertRow();
   var hc = htr.insertCell();
   hc.align = 'left';
   hc.width = 23;
   o.hHourLeftImg = RBuilder.append(hc, 'IMG');
   var hc = htr.insertCell();
   hc.align = 'left';
   hc.width = 23;
   o.hHourRightImg = RBuilder.append(hc, 'IMG');
   var hc = htr.insertCell();
   hc.innerText = ':';
   hc.width = 10;
   hc.align = 'center';
   hc.style.fontSize = '16';
   var hc = htr.insertCell();
   hc.align = 'right';
   hc.width = 23;
   o.hMinuteLeftImg = RBuilder.append(hc, 'IMG');
   var hc = htr.insertCell();
   hc.align = 'right';
   hc.width = 23;
   o.hMinuteRightImg = RBuilder.append(hc, 'IMG');
   o.isBuilded = true;
   return r;
}
function FTimer_onBuildPanel(){
   this.hPanel = RBuilder.newTable();
}
function FTimer_construct(){
   var o = this;
   o.serverDate = RConsole.find(FDateConsole).currentDate();
   o.active = new TActive(o, o.onCalculate);
   o.active.interval = 1000;
   o.contextPath = RContext.context(o.path);
   RConsole.find(FActiveConsole).push(o.active);
}
function FTimer_onCalculate(){
   var o = this;
   if(!o.isBuilded){
      return;
   }
   var t = RConsole.find(FDateConsole).currentDate();
   var ld = RDate.formatDate(t, 'YYYY.MM.DD');
   if(o.lastDate != ld){
      o.hDatePanel.innerText = ld;
      o.lastDate = ld;
   }
   var lt = RDate.formatDate(t, 'HH24MI');
   if(o.lastTime != lt){
      o.hHourLeftImg.src = o.contextPath + lt.charAt(0) + '.png';
      o.hHourRightImg.src = o.contextPath + lt.charAt(1) + '.png';
      o.hMinuteLeftImg.src = o.contextPath + lt.charAt(2) + '.png';
      o.hMinuteRightImg.src = o.contextPath + lt.charAt(3) + '.png';
   }
}
function FTimer_dispose(){
   var o = this;
   o.base.FControl.dispose.call(o);
   o.hInfoPanel = null;
   o.hInfoForm = null;
   o.hCountryPanel = null;
   o.hDatePanel = null;
   o.hTimePanel = null;
   o.hTimeForm = null;
   o.hHourLeftImg = null;
   o.hHourRightImg = null;
   o.hMinuteLeftImg = null;
   o.hMinuteRightImg = null;
   o.hButtonPanel = null;
}
function FUploadWindow(o){
   o = RClass.inherits(this, o, FWindow);
   o.fileEdit          = false;
   o.recordCode        = null;
   o.recordGuid        = null;
   o.uploadWorker      = null;
   o.attributes        = null;
   o.lsnsFileSelected  = null;
   o.lsnsUploaded      = null;
   o.onPreviewUploaded = FUploadWindow_onPreviewUploaded;
   o.onBrowserClick    = FUploadWindow_onBrowserClick;
   o.onUploadClick     = FUploadWindow_onUploadClick;
   o.oeBuild           = FUploadWindow_oeBuild;
   o.construct         = FUploadWindow_construct;
   o.selectFile        = FUploadWindow_selectFile;
   o.uploadedFile      = FUploadWindow_uploadedFile;
   o.show              = FUploadWindow_show;
   o.hide              = FUploadWindow_hide;
   return o;
}
function FUploadWindow_onPreviewUploaded(s, g){
   var o = this;
   o.hide();
   o.lsnsUploaded.process(o, g);
}
function FUploadWindow_onBrowserClick(){
   RConsole.find(FUploadConsole).showDialog();
}
function FUploadWindow_onUploadClick(){
   var o = this;
   if('P' == o.mode){
      var g = new TUploadArg();
      g.typeCode = o.typeCode;
      g.recordCode = o.recordCode;
      g.recordGuid = o.recordGuid;
      g.recordName = o.recordName;
      g.guid = o.attributes.get('guid');
      g.path = o.attributes.get('path');
      g.adjustWidth = o.adjustWidth;
      g.adjustHeight = o.adjustHeight;
      g.callback = new TInvoke(o, o.onPreviewUploaded);
      RConsole.find(FUploadConsole).previewSave(g);
   }else{
      o.mode = 'U';
      o.hInfoCell.innerText = '文件正在上传中，请稍候...'
      o.browserButton.psEnable(false);
      o.uploadButton.psEnable(false);
      o.closeButton.psEnable(false);
      var g = new TUploadArg();
      g.typeCode = o.typeCode;
      g.recordCode = o.recordCode;
      g.recordGuid = o.recordGuid;
      g.recordName = o.recordName;
      g.path = o.uploadFileName;
      RConsole.find(FUploadConsole).upload('upload', g);
   }
}
function FUploadWindow_oeBuild(e){
   var o = this;
   var r = o.base.FWindow.oeBuild.call(o, e);
   if(e.isAfter()){
      o.setIcon('Icon');
      o.setCaption(' ' + RContext.get('FUploadWindow:Caption'));
      o.hPanel.style.width = 620;
      o.hBodyPanel.style.height = 400;
      var hbf = o.hBodyForm = RBuilder.appendTable(o.hBodyPanel);
      hbf.style.width = '100%';
      hbf.style.height = '100%';
      var htp = o.hToolbarPanel = hbf.insertRow().insertCell();
      htp.height = 1;
      var tb = RClass.create(FToolBar);
      tb.width = '100%';
      var tbn = o.browserButton = RClass.create(FToolButton);
      tbn.icon = 'ctl.FUploadWindow_Browser';
      tbn.label = RContext.get('FUploadWindow:Browser');
      tbn.lsnsClick.register(o, o.onBrowserClick);
      tb.push(tbn);
      var tbn = o.uploadButton = RClass.create(FToolButton);
      tbn.icon = 'ctl.FUploadWindow_Upload';
      tbn.label = '上传文件';
      tbn.lsnsClick.register(o, o.onUploadClick);
      tb.push(tbn);
      var tbn = RClass.create(FToolButtonSplit);
      tb.push(tbn);
      var tbn = o.closeButton = RClass.create(FToolButton);
      tbn.icon = 'ctl.FUploadWindow_Close';
      tbn.label = RContext.get('FUploadWindow:Close');
      tbn.lsnsClick.register(o, o.onCloseClick);
      tb.push(tbn);
      tb.psBuild(htp);
      var hpp = o.hPreviewPanel = hbf.insertRow().insertCell();
      hpp.style.paddingTop = 6;
      var hpf = o.hPreviewForm = RBuilder.appendTable(hpp, null, 0, 1);
      hpf.width = '100%';
      hpf.height = '100%';
      hpf.bgColor = '#CCCCCC';
      var hpr = hpf.insertRow();
      var hc = o.hIconPanel = hpr.insertCell();
      hc.width = 330;
      hc.align = 'center';
      hc.bgColor = '#FFFFFF';
      o.hIcon = RBuilder.appendImage(hc);
      o.hIcon.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod = image)";
      var hc = o.hInfoPanel = hpr.insertCell();
      hc.bgColor = '#FFFFFF';
      hc.align = 'center';
      var hif = o.hInfoForm = RBuilder.appendTable(hc, null, 0, 4);
      o.hInfoCell = hif.insertRow().insertCell();
      hif.insertRow().insertCell();
      o.hFileCell = hif.insertRow().insertCell();
      o.hLine1 = hif.insertRow().insertCell();
      o.hLine1.style.display = 'none';
      o.hLine2 = hif.insertRow().insertCell();
      o.hLine2.style.display = 'none';
      o.hLine3 = hif.insertRow().insertCell();
      o.hLine3.style.display = 'none';
   }
   return r;
}
function FUploadWindow_construct(){
   var o = this;
   o.base.FWindow.construct.call(o);
   o.attributes = new TAttributes();
   o.lsnsFileSelected = new TListeners();
   o.lsnsUploaded = new TListeners();
}
function FUploadWindow_selectFile(fn){
   var o = this;
   o.uploadFileName = fn;
   if(RFile.isPicture(fn)){
      o.mode = 'P';
      o.hInfoCell.innerText = '图片预览生成中，请稍候。';
      o.browserButton.psEnable(false);
      o.uploadButton.psEnable(false);
      o.closeButton.psEnable(false);
      RConsole.find(FUploadConsole).upload('preview');
   }else{
      o.hIcon.style.display = 'block';
      var sn = RFile.isKnown(fn) ? RFile.extend(fn) : 'unknown';
      o.hIcon.src = top.RContext.context('/ars/img/mime/' + RString.toLower(sn) + '.gif');
      o.hInfoCell.innerText = '';
      o.hFileCell.innerText = fn;
      o.uploadButton.psEnable(true);
   }
}
function FUploadWindow_uploadedFile(g){
   var o = this;
   if('E' == g.resultCode){
      alert('文件上传错误。请检查后重新上传。\n------------------------------\n' + g.result);
      o.browserButton.psEnable(true);
      o.uploadButton.psEnable(false);
      o.closeButton.psEnable(true);
      o.hInfoCell.innerText = '请选择要上传的文件...';
      return;
   }
   if('P' == o.mode){
      var as = o.attributes;
      as.clear();
      as.unpack(g.attributes);
      o.browserButton.psEnable(true);
      o.uploadButton.psEnable(true);
      o.closeButton.psEnable(true);
      o.hIcon.style.display = 'block';
      o.hIcon.src = RContext.context('/svr/tmp/' + g.attachment + '.preview');
      o.hInfoCell.innerText = '图片预览生成完毕。';
      o.hFileCell.innerText = '文件：' + o.uploadFileName;
      var v = as.contains('width');
      o.hLine1.style.display = v ? 'block' : 'none';
      if(v){
         o.hLine1.innerText = '宽度：' + as.nvl('width');
      }
      var v = as.contains('height');
      o.hLine2.style.display = v ? 'block' : 'none';
      if(v){
         o.hLine2.innerText = '高度：' + as.nvl('height');
      }
      var v = as.contains('deep');
      o.hLine3.style.display = v ? 'block' : 'none';
      if(v){
         o.hLine3.innerText = '色深：' + as.nvl('deep');
      }
   }else{
      o.hide();
      var as = new TAttributes();
      as.unpack(g.attributes);
      g.attributes = as;
      o.lsnsUploaded.process(o, g);
   }
}
function FUploadWindow_show(){
   var o = this;
   o.base.FWindow.show.call(o);
   RWindow.moveCenter(o.hPanel);
   RWindow.setEnable(false, true);
   o.psVisible(true);
   o.focus();
   o.browserButton.psEnable(true);
   o.uploadButton.psEnable(false);
   o.closeButton.psEnable(true);
   o.mode = null;
   if(o.uploadWorker){
      o.uploadWorker.release();
      o.uploadWorker = null;
   }
   o.hInfoCell.innerText = '请选择要上传的文件...';
   o.hFileCell.innerText = '';
   o.hLine1.style.display = 'none';
   o.hLine2.style.display = 'none';
   o.hLine3.style.display = 'none';
   o.uploadButton.psEnable(false);
   o.hIcon.style.display = 'none';
}
function FUploadWindow_hide(){
   var o = this;
   o.base.FWindow.hide.call(o);
   RWindow.setEnable(true);
}
function FWindow(o){
   o = RClass.inherits(this, o, FContainer, MFocus, MDisplayAble, MSizeable, MMoveable, MWinBorder);
   o.caption      = RClass.register(o, new TPtyStr('caption'));
   o.isDialog     = false;
   o.type         = null;
   o.border       = null;
   o.oeBuild      = FWindow_oeBuild;
   o.oeVisible    = FWindow_oeVisible;
   o.onBuildPanel = FWindow_onBuildPanel;
   o.panel        = FWindow_panel;
   o.dump         = FWindow_dump;
   o.doFocus      = FWindow_doFocus;
   o.dispose      = FWindow_dispose;
   return o;
}
function FWindow_oeBuild(e){
   var o = this;
   o.base.FContainer.oeBuild.call(o, e);
   if(e.isBefore()){
      var fb = o.borderFloat = RBorder.createFloat(o.hPanel);
      var b = o.border = RBorder.create(EBorder.RoundTitle, fb.hPanel);
      b.hForm.width = '100%';
      var sc = RConsole.find(FSizeConsole);
      var hp = b.hForm;
      hp.link = o;
      hp._sizeable = true;
      sc.registerDrag(o, hp);
      var hbf = o.hBorderForm = b.hForm;
      o.buildBorder();
   }
}
function FWindow_onBuildPanel(){
   var o = this;
   var h = o.hPanel = RBuilder.appendDiv();
   h.style.display = 'none';
}
function FWindow_doFocus(){
   var o = this;
   if(o.searchControls && o.searchControls.count > 0){
      var cs = o.searchControls;
      for(var n = 0; n < cs.count; n++){
         var c = o.searchControls.get(0)
         if(RClass.isClass(c, MEditValue)){
            c.focus();
         }
      }
   }
}
function FWindow_oeVisible(e){
   var o = this;
   o.base.FContainer.oeVisible.call(o, e);
   if(e.isAfter()){
      o.hPanel.style.zIndex = RLayer.next(ELayer.Window);
      o.hPanel.style.display = 'block';
   }
}
function FWindow_panel(t){
   var o = this;
   if(EPanel.Display == t || EPanel.Border == t || EPanel.Size == t){
      return o.hPanel;
   }else if(EPanel.Move == t){
      return o.hTitleForm;
   }
   return o.base.FContainer.panel.call(o, t);
}
function FWindow_dump(oCtl, sLeft){
   var sDump = '';
   if(!oCtl){
      oCtl = this;
   }
   if(!sLeft){
      sLeft = '   ';
   }
   sDump += oCtl.className + '\n';
   if(oCtl.children){
      var arChildren = oCtl.children;
      for(var n=0; n<arChildren.length; n++){
         sDump += sLeft + this.dump(arChildren[n], sLeft + '   ');
      }
   }
   return sDump;
}
function FWindow_pushAllControl(oCtl){
   if(!this.allControls){this.allControls = new Array();}
   this.allControls.push(oCtl);
}
function FWindow_control(sName){
   if(this.allControls){
      for(var n=0; n<this.allControls.length; n++){
         if(this.allControls[n].name == sName){
            return this.allControls[n];
         }
      }
   }
   return null;
}
function FWindow_restore(){
   this.max(true);
}
function FWindow_processResize(){
   if(!SystemManager.runMode){
      var oRect = this.rect()
      this.width = oRect.width();
      this.height = oRect.height();
   }
   this.processEvent(this, IWindowEvent.RESIZE);
}
function FWindow_fillAllControl(){
   var oControl = null;
   var nCount = this.controls.size();
   for(var n=0; n<nCount; n++){
      oControl = this.controls.value(n);
      if(oControl.fill){
         oControl.fill();
      }
   }
}
function FWindow_refresh(bConfig){
   if(this.loadConfig){this.loadConfig();}
   this.setCaption(this.label);
   this.setWidth(this.width);
   this.setHeight(this.height);
   if(this.allControls){
      for(var n=0; n<this.allControls.length; n++){
         var oCtl = this.allControls[n];
         if(oCtl.refresh){
            if(bConfig && oCtl.reloadConfig){
               oCtl.reloadConfig();
            }
            oCtl.refresh();
         }
      }
   }
}
function FWindow_initialize(){
   if(this.allControls){
      for(var n=0; n<this.allControls.length; n++){
         var oCtl = this.allControls[n];
         if(oCtl.initialize){oCtl.initialize();}
         if(oCtl.initializeControl){oCtl.initializeControl();}
      }
   }
}
function FWindow_release(){
   if(this.allControls){
      for(var n=0; n<this.allControls.length; n++){
         var oCtl = this.allControls[n];
         if(oCtl.releaseControl){oCtl.releaseControl();}
         if(oCtl.release){oCtl.release();}
      }
   }
   this.htmlBorder.removeNode(true);
   DatasetManager.focus(null, true);
   WindowManager.releaseWindow(this);
}
function FWindow_stopDropExecute(oSource){
   if(oSource.config && oSource.rect){
      var oRect = oSource.rect();
      oSource.config.setAttribute('left', oRect.left);
      oSource.config.setAttribute('top', oRect.top);
      oSource.config.setAttribute('width', oRect.width());
      oSource.config.setAttribute('height', oRect.height());
   }
   if(this.owner.onStopDrop){
      this.owner.onStopDrop(oSource);
   }
}
function FWindow_selectDsExecute(oSource){
   if(oSource && oSource.constructor == FDatasetCtl){
      var bRefresh = (DatasetManager.activeDsCtl != oSource);
      DatasetManager.activeDsCtl = oSource;
      if(bRefresh){
         DatasetManager.refreshToolbar();
      }
   }
}
function FWindow_dispose(){
   var o = this;
   o.base.FContainer.dispose.call(o);
   o.base.MWinBorder.dispose.call(o);
   o.hBorderForm = null;
}
function MPopBorder(o){
   o = RClass.inherits(this, o);
   o.isDialog        = false;
   o.titleBlur       = true;
   o.hTitlePanel     = null;
   o.hBody           = null;
   o.hBodyPanel      = null;
   o.hBorder         = null;
   o.hBorderTb       = null;
   o.hCaption        = null;
   o.hClose          = null;
   o.hIcon           = null;
   o.hMax            = null;
   o.hMin            = null;
   o.hTitle          = null;
   o.ohMin           = MPopBorder_ohMin;
   o.ohMax           = MPopBorder_ohMax;
   o.ohClose         = MPopBorder_ohClose;
   o.oeBuild         = MPopBorder_oeBuild;
   o.onBuildPanel    = MPopBorder_onBuildPanel;
   o.onSize          = MPopBorder_onSize;
   o.onFocus         = MPopBorder_onFocus;
   o.onBlur          = MPopBorder_onBlur;
   o.onMin           = MPopBorder_onMin;
   o.onMax           = MPopBorder_onMax;
   o.onClose         = MPopBorder_onClose;
   o.min             = MPopBorder_min;
   o.max             = MPopBorder_max;
   o.close           = MPopBorder_close;
   o.buildBorderCell = MPopBorder_buildBorderCell;
   o.startDrag       = MPopBorder_startDrag;
   o.stopDrag        = MPopBorder_stopDrag;
   o.inSizeRange     = MPopBorder_inSizeRange;
   o.setIcon         = MPopBorder_setIcon;
   o.setCaption      = MPopBorder_setCaption;
   o.bringToFront    = MPopBorder_bringToFront;
   o.borderStyle     = MPopBorder_borderStyle;
   return o;
}
function MPopBorder_ohMin(){
   var o = this.link;
   if(RClass.isClass(o, MPopBorder)){
      o.onMin();
   }
}
function MPopBorder_ohMax(){
   var o = this.link;
   if(RClass.isClass(o, MPopBorder)){
      o.onMax();
   }
}
function MPopBorder_ohClose(){
   var o = this.link;
   if(RClass.isClass(o, MPopBorder)){
      o.onClose();
   }
}
function MPopBorder_oeBuild(event){
   var o = this;
   if(event.isBefore()){
      var mc = RConsole.find(FMoveConsole);
      var sc = RConsole.find(FSizeConsole);
      var hTab = o.hBorder = o.hPanel;
      hTab.link = o;
      hTab.className = o.borderStyle('Panel');
      hTab._sizeable = true;
      sc.registerDrag(o, hTab);
      var hRow = hTab.insertRow();
      hRow.height = EMoveSize.InnerBorder;
      o.buildBorderCell(hRow, true);
      o.buildBorderCell(hRow);
      o.buildBorderCell(hRow);
      var hRow = hTab.insertRow();
      hRow.height = 1;
      o.buildBorderCell(hRow);
      o.hTitlePanel = hRow.insertCell();
      o.buildBorderCell(hRow);
      var hRow = hTab.insertRow();
      o.buildBorderCell(hRow);
      o.hBodyPanel = hRow.insertCell();
      o.buildBorderCell(hRow);
      var hRow = hTab.insertRow();
      hRow.height = EMoveSize.InnerBorder;
      o.buildBorderCell(hRow);
      o.buildBorderCell(hRow);
      o.buildBorderCell(hRow, true);
      var h = o.hTitle = RBuilder.appendTable(o.hTitlePanel, o.borderStyle('Title'), 0, 2, 1);
      h.link = o;
      h.ondblclick = o.ohMin;
      mc.registerDrag(o, h);
      var hRow = o.hTitle.insertRow();
      hRow.valign = 'bottom';
      var hIconCell = hRow.insertCell();
      hIconCell.align = 'center';
      hIconCell.width = 20;
      var hObj = o.hIcon = RBuilder.appendIcon(hIconCell, 'ctl.form-table');
      hObj.link = o;
      var h = o.hCaption = hRow.insertCell();
      h.link = o;
      h.className = o.borderStyle('Caption');
      h.innerText = RString.nvl(o.caption, 'Empty - Window');
      if(!o.isDialog){
         h = o.hMin = hRow.insertCell();
         h.link = o;
         h.className = o.borderStyle('Button');
         h.innerText = '0';
         h.onclick = o.ohMin;
         h = o.hMax = hRow.insertCell();
         h.link = o;
         h.className = o.borderStyle('Button');
         h.innerText = '1';
         h.onclick = o.ohMax;
      }
      h = o.hClose = hRow.insertCell();
      h.link = o;
      h.className = o.borderStyle('Button');
      h.innerText = 'r';
      h.onclick = o.ohClose;
      h = o.hPanel = o.hBody = RBuilder.append(o.hBodyPanel, 'DIV', 'MPopBorder_Body');
      h.link = o;
      h.isInnerBody = true;
   }
}
function MPopBorder_onBuildPanel(){
   this.hPanel = RBuilder.appendTable();
   this.hPanel.style.display = 'none';
}
function MPopBorder_onFocus(){
   var mc = IConsole.find(IMoveConsole);
   this.hBorder.style.zIndex = mc.nextLayer();
   this.hTitle.className = 'MPopBorder_titleHover';
}
function MPopBorder_onBlur(){
   var o = this;
   if(o.titleBlur){
      o.hTitle.className = o.borderStyle('Title');
   }
}
function MPopBorder_onMin(){
   alert('Min');
}
function MPopBorder_onMax(){
   alert('Max');
}
function MPopBorder_onClose(){
   alert('Close');
}
function MPopBorder_inSizeRange(hObj){
   return hObj._sizeable;
}
function MPopBorder_min(){
   this.onMin();
}
function MPopBorder_max(){
   this.onMax();
}
function MPopBorder_close(){
   this.onClose();
}
function MPopBorder_onSize(){
}
function MPopBorder_buildBorderCell(hRow, img, height){
   var o = this;
   var h = hRow.insertCell();
   h.className = o.borderStyle('Inner');
   h._sizeable = true;
   h.width = 1;
   if(img){
      var hImg = RBuilder.appendEmpty(h, EMoveSize.InnerBorder, EMoveSize.InnerBorder);
      hImg._sizeable = true;
   }
   return h;
}
function MPopBorder_startDrag(type){
   var o = this;
   if(EDrag.Move == type){
      o.onBlur();
      o.hBody.className = o.borderStyle('BodyHover');
      o.hBorder.style.zIndex = RLayer.next();
   }
}
function MPopBorder_stopDrag(type){
   if(EDrag.Move == type){
      var o = this;
      o.hBody.className = o.borderStyle('Body');
      o.hBorder.style.zIndex = RLayer.next();
   }
}
function MPopBorder_setIcon(s){
   this.hIcon.src = RRes.iconPath(s);
}
function MPopBorder_setCaption(s){
   this.hCaption.innerText = s;
}
function MPopBorder_bringToFront(){
   this.hBorder.style.zIndex = RLayer.next();
}
function MPopBorder_borderStyle(n){
   return RClass.find(MPopBorder).style(n);
}
function MSearch(o){
   o = RClass.inherits(this, o);
   o.SearchType      = 'search.type';
   o.SearchOrder     = 'search.order'
   o.searchType      = null;
   o.searchOrder     = null;
   o.hType           = null;
   o.hOrder          = null;
   o.oeBuild         = MSearch_oeBuild;
   o.onBuildType     = MSearch_onBuildType;
   o.onBuildOrder    = MSearch_onBuildOrder;
   o.onTypeKeyPress  = MSearch_onTypeKeyPress;
   o.onOrderKeyPress = MSearch_onOrderKeyPress;
   o.assign          = MSearch_assign;
   o.saveSearch      = MSearch_saveSearch;
   o.clearSearch     = MSearch_clearSearch;
   o.resetSearch     = MSearch_resetSearch;
   o.setEditStyle    = MSearch_setEditStyle;
   return o;
}
function MSearch_oeBuild(event){
   var o = this;
   var h = o.hTypePanel = o.hFormRow.insertCell();
   h.height = 25;
   h.className = RCss.style(FSearchWindow, 'TypePanel');
   o.onBuildType();
   h = o.hOrderPanel = o.hFormRow.insertCell();
   h.className = RCss.style(FSearchWindow, 'OrderPanel');
   o.onBuildOrder();
}
function MSearch_onBuildType(){
   var o = this;
   var items = RNaming.get(TItems, o.SearchType);
   if(!items){
      items = new TItems();
      items.create(ESearch.Equals, RContext.get('MSearch:search.equals'));
      items.create(ESearch.Begin, RContext.get('MSearch:search.begin'));
      items.create(ESearch.End, RContext.get('MSearch:search.end'));
      items.create(ESearch.Like, RContext.get('MSearch:search.like'));
      RNaming.set(items, o.SearchType);
   }
   var sel = o.searchType = RClass.create(FSelect);
   sel.searchBox = o.searchBox;
   sel.dataValue = 'E';
   sel.labelVisible = false;
   sel.editWidth = 80;
   sel.editRefer = 'search.type'
   sel.items = items;
   sel.onKeyPress = o.onTypeKeyPress;
   sel.psBuild(o.hTypePanel);
   sel.psMode(EMode.Search);
}
function MSearch_onBuildOrder(){
   var o = this;
   var items = RNaming.get(TItems, o.SearchOrder);
   if(!items){
      items = new TItems();
      items.create(EOrder.None, RContext.get('MSearch:order.none'));
      items.create(EOrder.Asc, RContext.get('MSearch:order.asc'));
      items.create(EOrder.Desc, RContext.get('MSearch:order.desc'));
      RNaming.set(items, o.SearchOrder);
   }
   var sel = o.searchOrder = RClass.create(FSelect);
   sel.searchBox = o.searchBox;
   sel.dataValue = 'N';
   sel.labelVisible = false;
   sel.editWidth = 40;
   sel.editRefer = 'search.order'
   sel.items = items;
   sel.onKeyPress = o.onOrderKeyPress;
   sel.psBuild(o.hOrderPanel);
   sel.psMode(EMode.Search);
}
function MSearch_onTypeKeyPress(e){
   var o = this;
   if(e.keyCode == EKey.Enter){
      o.blur();
      o.searchBox.doSearch();
   }
}
function MSearch_onOrderKeyPress(e){
   var o = this;
   if(e.keyCode == EKey.Enter){
      o.blur();
      o.searchBox.doSearch();
   }
}
function MSearch_assign(c, t){
   var o = this;
   o.nowrap = false;
   o.width = '100%';
   o.dataValue = null;
   o.labelWidth = 80;
   o.labelPosition = EPosition.Left;
   o.editWidth = 140;
}
function MSearch_saveSearch(x){
   var o = this;
   var v = o.text();
   var st = o.searchType.get();
   var so = o.searchOrder.get();
   if(v){
      var xs = x.create('Item');
      xs.set('name', o.name);
      xs.set('data_name', o.dataName);
      xs.set('data_value', v);
      xs.set('search_type', st);
      xs.set('search_order', so);
   }
}
function MSearch_clearSearch(){
   var o = this;
   o.set();
   o.searchType.set();
   o.searchOrder.set();
}
function MSearch_setEditStyle(style){
   var o = this;
   return;
   o.searchType.setEditStyle(style);
   o.searchOrder.setEditStyle(style);
}
function MSearch_resetSearch(){
   var o = this;
   o.set();
   o.searchType.set();
   o.searchOrder.set();
}
function MWinBorder(o){
   o = RClass.inherits(this, o);
   o.stBorderForm    = RClass.register(o, new TStyle('BorderForm'));
   o.stInner         = RClass.register(o, new TStyle('Inner'));
   o.stTitlePanel    = RClass.register(o, new TStyle('TitlePanel'));
   o.stTitleForm     = RClass.register(o, new TStyle('TitleForm'));
   o.stBodyPanel     = RClass.register(o, new TStyle('BodyPanel'));
   o.stCaption       = RClass.register(o, new TStyle('Caption'));
   o.stButton        = RClass.register(o, new TStyle('Button'));
   o.stBodyHover     = RClass.register(o, new TStyle('BodyHover'));
   o.isDialog        = false;
   o.titleBlur       = true;
   o.hBody           = null;
   o.hBodyPanel      = null;
   o.hBorder         = null;
   o.hBorderTb       = null;
   o.hIcon           = null;
   o.hTitle          = null;
   o.hTitlePanel     = null;
   o.hCaption        = null;
   o.hMinPanel       = null;
   o.hMinIcon        = null;
   o.hMaxPanel       = null;
   o.hMaxIcon        = null;
   o.hClosePanel     = null;
   o.hCloseIcon      = null;
   o.onBuildPanel    = MWinBorder_onBuildPanel;
   o.onMinClick      = RClass.register(o, new HClick('onMinClick'), MWinBorder_onMinClick);
   o.onMaxClick      = RClass.register(o, new HClick('onMaxClick'), MWinBorder_onMaxClick);
   o.onCloseClick    = RClass.register(o, new HClick('onCloseClick'), MWinBorder_onCloseClick);
   o.onCloseHover    = RClass.register(o, new HMouseEnter('onCloseHover'), MWinBorder_onCloseHover);
   o.onCloseLeave    = RClass.register(o, new HMouseLeave('onCloseLeave'), MWinBorder_onCloseLeave);
   o.onSize          = MWinBorder_onSize;
   o.onFocus         = MWinBorder_onFocus;
   o.onBlur          = MWinBorder_onBlur;
   o.buildBorder     = MWinBorder_buildBorder;
   o.min             = MWinBorder_min;
   o.max             = MWinBorder_max;
   o.close           = MWinBorder_close;
   o.buildBorderCell = MWinBorder_buildBorderCell;
   o.startDrag       = MWinBorder_startDrag;
   o.stopDrag        = MWinBorder_stopDrag;
   o.inSizeRange     = MWinBorder_inSizeRange;
   o.setIcon         = MWinBorder_setIcon;
   o.setCaption      = MWinBorder_setCaption;
   o.bringToFront    = MWinBorder_bringToFront;
   o.dispose         = MWinBorder_dispose;
   return o;
}
function MWinBorder_buildBorder(){
   var o = this;
   var mc = RConsole.find(FMoveConsole);
   var sc = RConsole.find(FSizeConsole);
   var hp = o.hPanel;
   var b = o.border;
   var htp = o.hTitlePanel = b.hTitle;
   htp.style.paddingLeft  = 1;
   htp.style.paddingTop  = 0;
   htp.style.paddingRight  = 1;
   var hbp = o.hBodyPanel = b.hPanel;
   hbp.className = o.style('BodyPanel');
   var htf = o.hTitleForm = RBuilder.appendTable(htp, null, 0, 0, 0);
   htf.height = "25px";
   htf.width = "100%";
   htf.link = o;
   htf.style.cursor = 'move';
   mc.registerDrag(o, htf);
   var hr = htf.insertRow();
   var hc1 = hr.insertCell();
   var s = o.styleIconPath('Title_Bg', FWindow);
   hc1.style.backgroundImage = 'url('+s+')';
   var hTab1 = RBuilder.appendTable(hc1, null, 0, 0, 0);
   var htr = hTab1.insertRow();
   htr.valign = 'bottom';
      var hic = htr.insertCell();
      hic.align = 'center';
      hic.width = 20;
      var hi = o.hIcon = RBuilder.appendIcon(hic, 'ctl.form-table');
      hi.link = o;
      var hcc = o.hCaption = htr.insertCell();
      hcc.link = o;
      hcc.innerText = RString.nvl(o.caption, 'Empty - Window');
      hcc.style.color = '#FFFFFF';
   var hc2 = hr.insertCell();
   var s = o.styleIconPath('Close_Bg', FWindow);
   hc2.style.backgroundImage = 'url('+s+')';
   hc2.width = "200px";
   var hTab2 = RBuilder.appendTable(hc2, null, 0, 0, 2);
   hTab2.style.cursor = 'auto';
   hTab2.vAlign = "center";
   hTab2.align = "right";
   var r2 = hTab2.insertRow();
   if(o.isDialog){
      var hmp = o.hMinPanel = r2.insertCell();
      var hmi = o.hMinIcon = RBuilder.appendImage(hmp);
      hmi.src =  o.styleIconPath('Min_Icon', FWindow);
      o.attachEvent('onMinClick', hmi);
      var hmp = o.hMaxPanel = r2.insertCell();
      var hmi = o.hMaxIcon = RBuilder.appendImage(hmp);
      hmi.src =  o.styleIconPath('Max_Icon', FWindow);
      o.attachEvent('onMaxClick', hmi);
   }
   var hcp = o.hClosePanel = r2.insertCell();
   hcp.style.vAlign='top';
   var hci = o.hCloseIcon = RBuilder.appendImage(hcp);
   hci.src =  o.styleIconPath('Close_Icon', FWindow);
   o.attachEvent('onCloseClick', hci);
   o.attachEvent('onCloseHover', hci);
   o.attachEvent('onCloseLeave', hci);
}
function MWinBorder_onBuildPanel(){
   var o = this;
   o.hPanel = RBuilder.appendTable();
   o.hPanel.style.display = 'none';
}
function MWinBorder_onCloseHover(){
   var o = this;
   o.hCloseIcon.style.cursor='hand';
   o.hCloseIcon.src = o.styleIconPath('Close_IconPress', FWindow);
}
function MWinBorder_onCloseLeave(){
   var o = this;
   o.hCloseIcon.src = o.styleIconPath('Close_Icon', FWindow);
}
function MWinBorder_onFocus(){
   var o = this;
   var mc = IConsole.find(IMoveConsole);
   o.hBorder.style.zIndex = mc.nextLayer();
}
function MWinBorder_onBlur(){
   var o = this;
   if(o.titleBlur){
   }
}
function MWinBorder_onMinClick(){
}
function MWinBorder_onMaxClick(){
}
function MWinBorder_onCloseClick(){
   this.hide();
}
function MWinBorder_inSizeRange(hObj){
   return hObj._sizeable;
}
function MWinBorder_min(){
}
function MWinBorder_max(){
}
function MWinBorder_close(){
}
function MWinBorder_onSize(){
}
function MWinBorder_buildBorderCell(hr, img, height){
   var o = this;
   var h = hr.insertCell();
   h.className = o.style('Inner');
   h._sizeable = true;
   h.width = 1;
   if(img){
      var hi = RBuilder.appendEmpty(h, EMoveSize.InnerBorder, EMoveSize.InnerBorder);
      hi._sizeable = true;
   }
   return h;
}
function MWinBorder_startDrag(type){
   var o = this;
   if(EDrag.Move == type){
      o.onBlur();
      o.hPanel.style.zIndex = RLayer.next();
   }
}
function MWinBorder_stopDrag(type){
   var o = this;
   if(EDrag.Move == type){
      o.hBodyPanel.className = o.style('BodyPanel');
      o.hPanel.style.zIndex = RLayer.next();
   }
}
function MWinBorder_setIcon(s){
   this.hIcon.src = this.styleIconPath(s);
}
function MWinBorder_setCaption(s){
   this.hCaption.innerText = s;
}
function MWinBorder_bringToFront(){
   this.hBorder.style.zIndex = RLayer.next();
}
function MWinBorder_dispose(){
   var o = this;
   o.hTitlePanel = null;
   o.hBodyPanel = null;
   o.hBorder = null;
   o.hTitleForm = null;
   o.hIcon = null;
   o.hCaption = null;
}
function TFormWindowArg(){
   var o = this;
   o.formName     = null;
   o.values       = null;
   return o;
}
