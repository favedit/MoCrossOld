var EResultPage = new function(){
   var o = this;
   o.MasterSuccess = '/apl/page/Master.wa?do=success';
   o.InnerSuccess = '/apl/page/Inner.wa?do=success';
   return o;
}
function FFormSheet(o){
   o = RClass.inherits(this, o, FContainer, MDisplay);
   o.target            = RClass.register(o, new TPtyStr('target'));
   o.icon              = RClass.register(o, new TPtyStr('icon'));
   o.closeAble         = RClass.register(o, new TPtyBool('closeAble'), true);
   o.stButton          = RClass.register(o, new TStyle('Button'));
   o._manager          = null;
   o._sheets           = null;
   o._spaceIndex       = -1;
   o._selected         = false;
   o.activeSpace       = null;
   o.spaces            = null;
   o.lsnsButtonClick   = null;
   o.hButtonPanel      = null;
   o.hButton           = null;
   o.hIcon             = null;
   o.hText             = null;
   o.hHistoryForm      = null;
   o.hHistoryLine      = null;
   o.hHistoryText      = null;
   o.onEnter           = FFormSheet_onEnter;
   o.onLeave           = FFormSheet_onLeave;
   o.onSheetClick      = RClass.register(o, new HClick('onSheetClick'), FFormSheet_onSheetClick);
   o.onSheetCloseClick = RClass.register(o, new HClick('onSheetCloseClick'), FFormSheet_onSheetCloseClick);
   o.onBuildPanel      = FFormSheet_onBuildPanel;
   o.oeBuild           = FFormSheet_oeBuild;
   o.construct         = FFormSheet_construct;
   o.setLabel          = FFormSheet_setLabel;
   o.setIcon           = FFormSheet_setIcon;
   o.setText           = FFormSheet_setText;
   o.setHint           = FFormSheet_setHint;
   o.currentSpace      = FFormSheet_currentSpace;
   o.createSpace       = FFormSheet_createSpace;
   o.firstSpace        = FFormSheet_firstSpace;
   o.space             = FFormSheet_space;
   o.nextSpace         = FFormSheet_nextSpace;
   o.syncSpace         = FFormSheet_syncSpace;
   o.clickSpace        = FFormSheet_clickSpace;
   o.innerSelect       = FFormSheet_innerSelect;
   o.select            = FFormSheet_select;
   o.selectSpace       = FFormSheet_selectSpace;
   o.selectByIndex     = FFormSheet_selectByIndex;
   o.popup             = FFormSheet_popup;
   o.clear             = FFormSheet_clear;
   o.dispose           = FFormSheet_dispose;
   return o;
}
function FFormSheet_onEnter(e){
   var o = this;
   if(!o._selected){
      o.hButtonPanel.style.background = RResource.iconUrlPath('ctl.FFormSheet_ButtonHover', 'png');
      o.hRight.style.background = RResource.iconUrlPath('ctl.FFormSheet_ButtonHoverR', 'png');
      if(o.closeAble){
         o.hCloseIcon.src = RResource.iconPath('ctl.FFormSheet_CloseHover', 'png');
         o.hCloseIcon.style.display = 'block';
      }
   }
}
function FFormSheet_onLeave(e){
   var o = this;
   if(!o._selected){
      o.hButtonPanel.style.background = '';
      o.hRight.style.background = '';
      if(o.closeAble){
         o.hCloseIcon.style.display = 'none';
      }
   }
}
function FFormSheet_onSheetClick(e){
   var o = this;
   if(o._visible){
      o._sheets.select(o);
   }
}
function FFormSheet_onSheetCloseClick(e){
   var o = this;
   o.hide();
   if(o._selected){
      o._sheets.selectByIndex(0);
   }
}
function FFormSheet_onBuildPanel(){
   this.hPanel = RBuilder.newTable();
}
function FFormSheet_oeBuild(e){
   var o = this;
   var r = o.base.FContainer.oeBuild.call(o, e);
   var b = e.builder;
   if(e.isBefore()){
      var hpr = o.hPanel.insertRow();
      var hbp = o.hButtonPanel = hpr.insertCell();
      var hb = o.hButton = b.appendTable(hbp);
      hb.style.cursor = 'hand';
      o.attachEvent('onSheetClick', hb);
      var hr = o.hButton.insertRow();
      var hc = hr.insertCell();
      hc.style.paddingTop = 4;
      hc.style.paddingLeft = 8;
      hc.style.paddingRight = 4;
      hc.style.paddingBottom = 2;
      o.hIcon = b.appendIcon(hc, o.icon);
      o.hText = b.append(hc, 'SPAN');
      o.hText.style.whiteSpace = 'nowrap';
      o.hText.style.color = '#CCCCCC';
      o.hText.style.padding = '0 4';
      o.hText.innerText = o.label;
      var hc = hr.insertCell();
      hc.width = 10;
      if(o.closeAble){
         var hi = o.hCloseIcon = b.appendIcon(hc);
         hi.style.display = 'none';
         o.attachEvent('onSheetCloseClick', hi);
      }
      var hc = o.hRight = hpr.insertCell();
      hc.width = 5;
      var hf = o.hHistoryForm = RBuilder.appendTable(RFormSpace.hHistoryPanel);
      var hr = o.hHistoryLine = hf.insertRow();
      var hc = o.hHistoryText = hr.insertCell();
      hc.innerText = RContext.get('FFormSheet:history');
   }else if(e.isAfter()){
      if(EAlign.Right != o.align){
         var hTd = b.create('TD');
         b.appendEmpty(hTd);
         o.hHistoryLine.appendChild(hTd);
      }
   }
   return r;
}
function FFormSheet_construct(){
   var o = this;
   o.base.FContainer.construct.call(o);
   o.spaces = new TList();
   o.lsnsButtonClick = new TListeners();
}
function FFormSheet_setLabel(t){
   this.hText.innerHTML = t;
}
function FFormSheet_setIcon(c){
   this.hIcon.src = RResource.iconPath(c);
}
function FFormSheet_setText(t){
   this.hText.innerText = t;
}
function FFormSheet_setHint(s){
   this.hText.title = s;
}
function FFormSheet_currentSpace(){
   return this.activeSpace;
}
function FFormSheet_createSpace(){
   var o = this;
   var hs = null;
   var hl = o.hHistoryLine;
   if(o.spaces.count){
      hs = hl.insertCell();
      hs.width = '16';
      RBuilder.appendIcon(hs, 'ctl.FHistoryBar_Spliter');
   }
   var s = RControl.create(FFormSpace, hl.insertCell());
   s._manager = o._manager;
   s._sheet = o;
   s.index = o.spaces.count;
   s.hSplit = hs;
   o.spaces.push(s);
   RFormSpace.spaces.push(s);
   return s;
}
function FFormSheet_firstSpace(fn){
   var o = this;
   var ss = o.spaces;
   var s = ss.get(0);
   if(!s){
      s = o.createSpace();
   }
   o.select(s);
   o._spaceIndex = s.index;
   return s;
}
function FFormSheet_space(n){
   return this.spaces.get(n);
}
function FFormSheet_nextSpace(){
   var o = this;
   var sc = o.spaces.get(o._spaceIndex);
   if(sc){
      sc.innerSelect(false);
   }
   var n = ++o._spaceIndex;
   var sn = o.syncSpace(n);
   o.activeSpace = sn;
   sn.innerSelect(true);
   sn.setVisible(true);
   var ss = o.spaces;
   for(var i=n+1; i<ss.count; i++){
      ss.get(i).setVisible(false);
   }
   return sn;
}
function FFormSheet_syncSpace(n){
   var o = this;
   var ss = o.spaces;
   var s = ss.get(n);
   if(!s){
      for(var i=ss.count; i<=n; i++){
         s = o.createSpace();
      }
   }
   return s;
}
function FFormSheet_clickSpace(s){
   var o = this;
   o._spaceIndex = o.spaces.indexOf(s);
   o.lsnsButtonClick.process(s);
   o.select(s);
}
function FFormSheet_innerSelect(v){
   var o = this;
   o._selected = v;
   var ht = o.hText;
   if(v){
      ht.style.fontWeight = 'bold';
      ht.style.color = '#333333';
      o.hButtonPanel.style.background = RResource.iconUrlPath('ctl.FFormSheet_Button', 'png');
      o.hRight.style.background = RResource.iconUrlPath('ctl.FFormSheet_ButtonR', 'png');
      if(o.closeAble){
         o.hCloseIcon.style.display = 'block';
         o.hCloseIcon.src = RResource.iconPath('ctl.FFormSheet_Close', 'png');
      }
   }else{
      ht.style.fontWeight = 'normal';
      ht.style.color = '#CCCCCC';
      o.hButtonPanel.style.background = '';
      o.hRight.style.background = '';
      if(o.closeAble){
         o.hCloseIcon.style.display = 'none';
      }
   }
   if(v){
      if('frame' == o.target){
         RFormSpace.hSpaceFrame.style.display = 'block';
         RFormSpace.hSpaceForm.style.display = 'none';
      }else{
         RFormSpace.hSpaceFrame.style.display = 'none';
         RFormSpace.hSpaceForm.style.display = 'block';
      }
   }
   o.hHistoryForm.style.display = v ? 'block' : 'none';
   if(v){
      var s = o.activeSpace;
      if(s){
         s.setVisible(true);
         if(v){
            s.refresh();
         }
      }
   }else{
      var ss = o.spaces;
      for(var i=0; i<ss.count; i++){
         ss.get(i).innerSelect(false);
      }
   }
}
function FFormSheet_select(s){
   var o = this;
   o._sheets.select(o);
   if(s){
      var ss = o.spaces;
      for(var i=0; i<ss.count; i++){
         var f = ss.get(i);
         if(f != s){
            f.innerSelect(false);
         }
      }
      o.activeSpace = s;
      s.innerSelect(true);
   }
}
function FFormSheet_selectSpace(s){
   var o = this;
   o._sheets.select(o);
   if(s){
      var ss = o.spaces;
      for(var i=0; i<ss.count; i++){
         var f = ss.get(i);
         if(f != s){
            f.innerSelect(false);
         }
      }
      o.activeSpace = s;
      s.innerSelect(true);
   }
}
function FFormSheet_selectByIndex(n){
   this.select(ss.get(n));
}
function FFormSheet_popup(l){
   var o = this;
   var n = Math.max(o._spaceIndex - moNvl(l, 1), 0);
   var s = o.spaces.get(n);
   o.select(s);
   o._spaceIndex = n;
   var bc = o.spaces.count;
   for(++n; n<bc; n++){
      o.spaces.get(n).setVisible(false);
   }
   return s;
}
function FFormSheet_clear(){
   var o = this;
   var ss = o.spaces;
   var c = ss.count;
   for(var n=0; n<c; n++){
      ss.get(n).setVisible(false);
   }
}
function FFormSheet_dispose(){
   var o = this;
   o.base.FContainer.dispose.call(o);
   o.hButtonPanel = null;
   o.hButton = null;
   o.hIcon = null;
   o.hText = null;
   o.hHistoryForm = null;
   o.hHistoryLine = null;
   o.hHistoryText = null;
}
function FFormSheets(o){
   o = RClass.inherits(this, o, FContainer);
   o._manager        = null;
   o.activeSheet     = null;
   o.sheets          = new TMap();
   o.hLine           = null;
   o.onBuildPanel    = FFormSheets_onBuildPanel;
   o.oeBuild         = FFormSheets_oeBuild;
   o.sheet           = FFormSheets_sheet;
   o.syncSheet       = FFormSheets_syncSheet;
   o.pushSheet       = FFormSheets_pushSheet;
   o.select          = FFormSheets_select;
   o.selectByIndex   = FFormSheets_selectByIndex;
   o.clear           = FFormSheets_clear;
   o.dispose         = FFormSheets_dispose;
   return o;
}
function FFormSheets_onBuildPanel(){
   this.hPanel = RBuilder.newTable();
}
function FFormSheets_oeBuild(e){
   var o = this;
   o.base.FContainer.oeBuild.call(o, e);
   var hp = o.hPanel;
   var b = e.builder;
   if(e.isBefore()){
      hp.width = '100%';
      var hr = o.hTop = hp.insertRow();
      hr.insertCell();
      var hr = hp.insertRow();
      var hc = o.hLinePanel = hr.insertCell();
      hc.height = 1;
      var hlf = o.hLineForm = RBuilder.appendTable(hc);
      o.hLine = hlf.insertRow();
      o.hCaption = o.hLine.insertCell();
      o.hCaption.innerText = ' ';
      var hr = o.hBottom = hp.insertRow();
      var hc = hr.insertCell();
      hc.height = '3';
      hc.bgColor = '#CCCCCC'
   }else if(e.isAfter()){
      var cs = o.components;
      for(var n=0; n<cs.count; n++){
         var c = cs.value(n);
         if(RClass.isClass(c, FFormSheet)){
            o.pushSheet(c);
         }
      }
      if(EAlign.Right != o.align){
         var hTd = b.create('TD');
         b.appendEmpty(hTd);
      }
   }
}
function FFormSheets_select(s){
   var o = this;
   var ss = o.sheets;
   for(var i=0; i<ss.count; i++){
      var f = ss.value(i);
      if(f != s){
         f.innerSelect(false);
      }
   }
   o.activeSheet = s;
   s.innerSelect(true);
}
function FFormSheets_selectByIndex(n){
   var o = this;
   var s = o.sheets.value(n);
   if(s){
      o.select(s);
   }
}
function FFormSheets_sheet(n){
   return this.sheets.value(n);
}
function FFormSheets_syncSheet(n){
   var o = this;
   var s = o.sheets.get(n);
   if(!s){
      s = RControl.create(FFormSheet);
      s.name = n;
      o.pushSheet(s);
   }
   return s;
}
function FFormSheets_pushSheet(s){
   var o = this;
   s._manager = o._manager;
   s._sheets = o;
   o.sheets.set(s.name, s);
   var hc = s.hParent = o.hLine.insertCell();
   hc.appendChild(s.hPanel);
}
function FFormSheets_clear(){
   var o = this;
   var ss = o.sheets;
   for(var n=ss.count-1; n>=0; n--){
      ss.value(n).setVisible(n);
   }
}
function FFormSheets_dispose(){
   var o = this;
   o.base.FContainer.dispose.call(o);
   o.hLine = null;
   o.hCaption = null;
   o.hParent = null;
}
function FFormSpace(o){
   o = RClass.inherits(this, o, FControl);
   o.stButton      = RClass.register(o, new TStyle('Button'));
   o.stLabel       = RClass.register(o, new TStyle('Label'));
   o.stHover       = RClass.register(o, new TStyle('Hover'));
   o.stSelect      = RClass.register(o, new TStyle('Select'));
   o._manager      = null;
   o._sheet        = null;
   o._selected     = false;
   o.formName      = null;
   o.form          = null;
   o.hButton       = null;
   o.hButtonLine   = null;
   o.hButtonPanel  = null;
   o.hIcon         = null;
   o.hText         = null;
   o.onEnter       = FFormSpace_onEnter;
   o.onLeave       = FFormSpace_onLeave;
   o.onMouseDown   = FFormSpace_onMouseDown;
   o.onMouseUp     = FFormSpace_onMouseUp;
   o.onButtonClick = RClass.register(o, new HClick('onButtonClick'), FFormSpace_onButtonClick);
   o.onUpdateEnd   = FFormSpace_onUpdateEnd;
   o.onBuildPanel  = FFormSpace_onBuildPanel;
   o.oeBuild       = FFormSpace_oeBuild;
   o.setIcon       = FFormSpace_setIcon;
   o.setText       = FFormSpace_setText;
   o.setHint       = FFormSpace_setHint;
   o.setCaption    = FFormSpace_setCaption;
   o.findForm      = FFormSpace_findForm;
   o.innerSelect   = FFormSpace_innerSelect;
   o.select        = FFormSpace_select;
   o.setVisible    = FFormSpace_setVisible;
   o.setFormIcon   = FFormSpace_setFormIcon;
   o.doFetch       = FFormSpace_doFetch;
   o.doSearch      = FFormSpace_doSearch;
   o.doLov         = FFormSpace_doLov;
   o.doCopy        = FFormSpace_doCopy;
   o.doZoom        = FFormSpace_doZoom;
   o.doDelete      = FFormSpace_doDelete;
   o.doMovePage    = FFormSpace_doMovePage;
   o.doDetail      = FFormSpace_doDetail;
   o.doPrintPdf    = FFormSpace_doPrintPdf;
   o.refresh       = FFormSpace_refresh;
   o.dispose       = FFormSpace_dispose;
   return o;
}
function FFormSpace_onEnter(e){
   var o = this;
   if(!o._selected){
      o.hPanel.className = o.style('Hover');
   }
}
function FFormSpace_onLeave(e){
   var o = this;
   if(!o._selected){
      o.hPanel.className = o.style('Button');
   }
}
function FFormSpace_onMouseDown(e){
   if(!this.disabled){
   }
}
function FFormSpace_onMouseUp(e){
   var o = this;
   if(!o.disabled){
      o.hPanel.className = o.style('Hover');
   }
}
function FFormSpace_onButtonClick(e){
   this._sheet.clickSpace(this);
}
function FFormSpace_onUpdateEnd(g){
   var o = this;
   var f = g.form;
}
function FFormSpace_onBuildPanel(){
   this.hPanel = RBuilder.create(null, 'TD', this.style('Button'));
}
function FFormSpace_oeBuild(e){
   var o = this;
   o.base.FControl.oeBuild.call(o, e);
   var b = e.builder;
   var h = o.hPanel;
   var hb = o.hButton = b.appendTable(o.hPanel, o.style('Panel'));
   var hl = o.hButtonLine = o.hButton.insertRow();
   var hc = o.hButtonPanel = hl.insertCell();
   o.attachEvent('onButtonClick', hc);
   o.hIcon = b.appendIcon(hc, 'ctl.form-table');
   o.hText = b.append(hc, 'SPAN');
   o.hText.style.whiteSpace = 'nowrap';
   return EEventStatus.Stop;
}
function FFormSpace_setIcon(c){
   this.hIcon.src = RResource.iconPath(c);
}
function FFormSpace_setText(t){
   this.hText.innerText = t;
}
function FFormSpace_setHint(s){
   this.hText.title = s;
}
function FFormSpace_setCaption(t){
   var o = this;
   var f = o.form;
   var r = f.label;
   if(t){
      r += ' (' + t + ')';
   }
   o.hText.innerText = ' ' + r;
}
function FFormSpace_findForm(n){
   var o = this;
   var m = o._manager;
   var f = o.form;
   if(f){
      if(f.name == n){
         return f;
      }
      f.setVisible(false);
      RConsole.find(FFormConsole).free(f);
   }
   o.formName = n;
   f = o.form = RConsole.find(FFormConsole).createFromName(n, RFormSpace.hFormPanel, RWindow.builder());
   f.lsnsUpdateEnd.register(o, o.onUpdateEnd);
   f.setSize('100%', '100%');
   f.setVisible(true);
   f.dsShow();
   f.psResize();
   o.setCaption();
   return f;
}
function FFormSpace_innerSelect(v){
   var o = this;
   o._selected = v;
   o.hPanel.className = v ? o.style('Select') : o.style('Button');
   var m = o._manager;
   var f = o.form;
   if(f){
      m.hToolbarPanel.height = f.dispToolbar ? 24 : 1;
      if(v){
         m.selectForm(f);
         f.setVisible(true);
         m.lsnsFormShowBefore.process(o, f);
         f.psRefreshFirst();
         f.psResize();
         m.lsnsFormShow.process(o, f);
         f.dsShow();
         f.focus();
      }else{
         f.setVisible(false);
      }
   }
}
function FFormSpace_select(){
   this._sheet.select(this);
}
function FFormSpace_setVisible(v){
   var o = this;
   if(o.hSplit){
      o.hSplit.style.display = v ? 'block' : 'none';
   }
   o.hPanel.style.display = v ? 'block' : 'none';
   var f = o.form;
   if(f){
      f.setVisible(v);
   }
}
function FFormSpace_setFormIcon(c){
   var o = this;
   var f = o.form;
   if(RString.isEmpty(c)){
      if(RClass.isClass(f, FForm)){
         switch(f._emode){
            case EMode.Insert:
               c = '#tbr.formInsert';
               break;
            case EMode.Update:
               c = '#tbr.formUpdate';
               break;
            case EMode.Delete:
               c = '#tbr.formDelete';
               break;
         }
      }else{
         c = '#tbr.table';
      }
   }
   o.setIcon(c);
}
function FFormSpace_doFetch(){
   var f = RConsole.find(FFocusConsole).findClass(MDataset);
   f.dsSearchs.clear();
   f.dsOrders.clear();
   f.dsFetch(true, true);
}
function FFormSpace_doSearch(){
   RConsole.find(FFocusConsole).findClass(MDataset).doSearch();
}
function FFormSpace_doCopy(){
   var f= RConsole.find(FFocusConsole).findClass(MDataset);
   if(f){
      f.dsCopy();
   }
}
function FFormSpace_doLov(){
   var fc = RConsole.find(FFocusConsole).findClass(MListView);
   if(fc){
      fc.doListView();
   }
}
function FFormSpace_doZoom(){
   var fc = RConsole.find(FFocusConsole).findClass(MZoom);
   if(fc){
      fc.doZoom();
   }
}
function FFormSpace_doDelete(){
}
function FFormSpace_doMovePage(a){
   var dc = RConsole.find(FFocusConsole).findClass(MDataset);
   if(RClass.isClass(dc, FTable)){
      dc.dsMovePage(a);
   }
}
function FFormSpace_doDetail(){
   var ouid = null;
   var over = null;
   var dc = RConsole.find(FFocusConsole).findClass(MDataset);
   if(RClass.isClass(dc, FForm)){
      ouid = dc.dsGet('ouid');
      over = dc.dsGet('over');
   }else if(RClass.isClass(dc, FTable)){
      var rs = dc.findSelectRows();
      if(!rs.isEmpty()){
         var r = rs.get(0);
         ouid = r.ouid();
         over = r.over();
      }
   }
   if(ouid && over){
      var uri = top.RContext.context('/apl/logic/resource/WebResource.wa?form_name={0}&ouid={1}&over={2}');
      uri = RString.format(uri, dc.name, ouid, over);
      RHtml.popup(uri, 900, 600);
   }
}
function FFormSpace_doPrintPdf(){
   var dc = RConsole.find(FFocusConsole).findClass(MDataset);
   if(RClass.isClass(dc, MForm)){
      var formName = dc.getFormLink(EFormLink.Form);
      var uri = top.RContext.context('/public.pdf.wv?do=build&form_name=' + formName);
      RHtml.popup(uri, 800, 600);
   }
}
function FFormSpace_refresh(){
   var o = this;
   var f = o.from;
   if(f){
      f.psResize();
      f.psRefresh();
   }
}
function FFormSpace_dispose(){
   var o = this;
   o.base.FControl.dispose.call(o);
   o.hLine = null;
   o.hIcon = null;
   o.hText = null;
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
   var hb = o.hButton = b.appendTable(o.hPanel, o.style('Panel'));
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
   }
}
function FHistoryButton_onLeave(e){
   var o = this;
   if(!o.isSelected){
      o.hPanel.className = o.style('Button');
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
   o.hPanel.className = v ? o.style('Select') : o.style('Button');
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
var go = false;
var searchBox = null;
function doInsertSave(){
   var da = new FDataAction();
   da.service = 'insert@logic.dataset';
   da.invoke(form);
}
function doUpdateSave(){
   var da = new FDataAction();
   da.service = 'update@logic.dataset';
   da.invoke(form);
}
function doDeleteSave(){
   var da = new FDataAction();
   da.service = 'delete@logic.dataset';
   da.invoke(form);
}
function _resize(){
   if(form){
      RConsole.find(FEventConsole).add(form, form.psRefresh);
   }
}
function initializeForm(){
   var start = new Date().getTime();
   window.isLoaded = false;
   var emode = REnum.encode(EMode, fmMain.page_action.value);
   var xform = RXml.makeNode(xForm);
   var xdstoolbar = RXml.makeNode(xToolBar);
   var xtoolbar = RToolBar.mergeNode(xdstoolbar, xform);
   toolbar = RControl.fromNode(xtoolbar, _id_toolbar);
   toolbar.psMode(emode);
   var dc = RConsole.find(FDatasetConsole);
   form = RControl.fromXml(xForm, _id_form);
   if(RClass.isClass(form, MForm)){
      if(RClass.isClass(form, FTable)){
         var formParent = fmMain.form_parent.value;
         var parentPack = new TAttrs();
         parentPack.unpack(formParent);
      }
      if(RClass.isClass(form, FForm)){
         form.psMode(emode);
      }
      form.loadDatasets(RXml.makeNode(xValue));
   }
   top.document.title = form.label;
   _id_title.innerText = form.label;
   form.focus();
   var lsnConsole = RConsole.find(FListenerConsole);
   lsnConsole.register(MDataset, EDataAction.EndUpdate, form, doBack);
   lsnConsole.register(FTable, ETableAction.RowClick, form, doSelectRow);
   lsnConsole.register(FTable, ETableAction.GoInsert, form, doInsert);
   window.document.body.onresize = _resize;
   RConsole.find(FEventEngineConsole).loadConfig(RXml.makeNode(xEvent));
   var end = new Date().getTime();
   top.document.title = form.label + ' ( Show page in ' + (end - pageStart) + 'ms. ' + 'Build form: ' + (end - start) + 'ms )';
   window.isLoaded = true;
}
var RFormSpace = new function(){
   var o = this;
   o.groupBar                   = null;
   o.titleBar                   = null;
   o.historyBar                 = null;
   o.toolBar                    = null;
   o.sheets                     = null;
   o.activeGroup                = null;
   o.activeSpace                = null;
   o.spaces                     = new TList();
   o.hTitlePanel                = null;
   o.hHistoryPanel              = null;
   o.hToolPanel                 = null;
   o.hFormPanel                 = null;
   o.lsnsTitleButtonClickBefore = new TListeners();
   o.lsnsTitleButtonClick       = new TListeners();
   o.lsnsFormShowBefore         = new TListeners();
   o.lsnsFormShow               = new TListeners();
   o.lsnsUpdateEnd              = new TListeners();
   o.onFocusChanged             = RFormSpace_onFocusChanged;
   o.onTitleButtonClickBefore   = RFormSpace_onTitleButtonClickBefore
   o.onTitleButtonClick         = RFormSpace_onTitleButtonClick;
   o.onHistoryClick             = RFormSpace_onHistoryClick;
   o.onSpaceResize              = RFormSpace_onSpaceResize;
   o.onFormShow                 = RFormSpace_onFormShow;
   o.onFormResize               = RFormSpace_onFormResize;
   o.onTableSelectRow           = RFormSpace_onTableSelectRow;
   o.onUpdateEnd                = RFormSpace_onUpdateEnd;
   o.initialize                 = RFormSpace_initialize;
   o.createSheetBar             = RFormSpace_createSheetBar;
   o.createTitleBar             = RFormSpace_createTitleBar;
   o.createHistoryBar           = RFormSpace_createHistoryBar;
   o.createToolBar              = RFormSpace_createToolBar;
   o.createSpace                = RFormSpace_createSpace;
   o.setFormPanel               = RFormSpace_setFormPanel;
   o.focusControl               = RFormSpace_focusControl;
   o.syncSheet                  = RFormSpace_syncSheet;
   o.hasSpace                   = RFormSpace_hasSpace;
   o.firstSpace                 = RFormSpace_firstSpace;
   o.currentSheet               = RFormSpace_currentSheet;
   o.currentSpace               = RFormSpace_currentSpace;
   o.nextSpace                  = RFormSpace_nextSpace;
   o.popupSpace                 = RFormSpace_popupSpace;
   o.selectSpace                = RFormSpace_selectSpace;
   o.nextForm                   = RFormSpace_nextForm;
   o.selectForm                 = RFormSpace_selectForm;
   o.doFetch                    = RFormSpace_doFetch;
   o.doSearch                   = RFormSpace_doSearch;
   o.doLov                      = RFormSpace_doLov;
   o.doCopy                     = RFormSpace_doCopy;
   o.doZoom                     = RFormSpace_doZoom;
   o.doPrepare                  = RFormSpace_doPrepare;
   o.doUpdate                   = RFormSpace_doUpdate;
   o.doDelete                   = RFormSpace_doDelete;
   o.doMovePage                 = RFormSpace_doMovePage;
   o.doDetail                   = RFormSpace_doDetail;
   o.doPrintPdf                 = RFormSpace_doPrintPdf;
   o.doOperateAction            = RFormSpace_doOperateAction;
   o.goPage                     = RFormSpace_goPage;
   o.refresh                    = RFormSpace_refresh;
   o.release                    = RFormSpace_release;
   RMemory.register('RFormSpace', o);
   return o;
}
function RFormSpace_onFocusChanged(c){
   this.focusControl(c);
}
function RFormSpace_onTitleButtonClickBefore(s){
   this.lsnsTitleButtonClickBefore.process(s);
}
function RFormSpace_onTitleButtonClick(s){
   this.lsnsTitleButtonClick.process(s);
}
function RFormSpace_onHistoryClick(b){
   var o = this;
   o.selectSpace(b.store.space);
   o.historyBar.historyIndex = o.historyBar.buttons.indexOf(b);
}
function RFormSpace_onSpaceResize(s){
   var o = this;
   if(!o.hasSpace()){
      return;
   }
   var ts = o.currentSpace();
   if(ts){
      var f = ts.form;
      if(f){
         f.psResize();
      }
   }
}
function RFormSpace_onFormShow(){
   this.lsnsFormShow.process();
}
function RFormSpace_onFormResize(){
   this.lsnsFormShow.process();
}
function RFormSpace_onTableSelectRow(r){
   var o = this;
   if(!o.hasSpace()){
      return;
   }
   var dc = r.table;
   if(dc && dc.isLov){
      return;
   }
   var sh = o.currentSheet();
   var sc = sh.currentSpace();
   var fn = dc.getFormLink(EFormLink.Form);
   if(!RString.isEmpty(fn)){
      var sn = sh.nextSpace();
      var f = sn.findForm(fn);
      f.dsValues.clear();
      r.toDeepAttributes(f.dsValues)
      sn.innerSelect(true);
      f.dsUpdate(r.getId());
      sc.setFormIcon();
   }
}
function RFormSpace_onUpdateEnd(g){
   this.lsnsUpdateEnd.process(g);
}
function RFormSpace_initialize(){
   var o = this;
   var fc = RConsole.find(FFocusConsole);
   fc.lsnsFocus.register(o, o.onFocusChanged);
   fc.lsnsFocusClass.register(o, o.onFocusChanged);
   var lc = RConsole.find(FListenerConsole);
   lc.register(FGridControl, EGridAction.RowClick, o, o.onTableSelectRow);
   lc.register(FGridControl, EGridAction.RowDblClick, o, o.onTableSelectRow);
   lc.register(MTop, ETopAction.Resize, o, o.onFormResize);
   RWindow.lsnsResize.register(o, o.onSpaceResize);
   RFlash.start();
}
function RFormSpace_createSheetBar(h, hx){
   var o = this;
   var fss = o.sheets = RControl.fromXml(hx, h);
   fss._manager = o;
   fss.selectByIndex(0);
}
function RFormSpace_createTitleBar(h, hx){
   var o = this;
   var t = null;
   if(hx){
      t = RControl.fromXml(hx, h)
   }else{
      t = RControl.create(FTitleBar, h);
   }
   t.lsnsNavButtonClickBefore.register(o, o.onTitleButtonClickBefore);
   t.lsnsNavButtonClick.register(o, o.onTitleButtonClick);
   o.titleBar = t;
}
function RFormSpace_createHistoryBar(h){
   var o = this;
}
function RFormSpace_createToolBar(h, hx){
   var o = this;
   o.hToolPanel = h;
   o.toolBar = RControl.fromNode(RXml.makeNode(hx), h);
   o.focusControl();
}
function RFormSpace_createSpace(){
   var o = this;
   var s = RControl.create(FFormSpace);
   s._manager = o;
   s.setHistoryIcon();
   s.historyButton.store.space = s;
   o.spaces.push(s);
   return s;
}
function RFormSpace_setFormPanel(h){
   var o = this;
   o.hFormPanel = h;
}
function RFormSpace_focusControl(){
   var o = this;
   var fc = RConsole.find(FFocusConsole);
   var c = fc.activeControl;
   var dc = fc.findClass(MDataset);
   var bs = null;
   if(!dc){
      bs = {
         'fetchButton' : false,
         'searchButton': false,
         'lovButton'   : false,
         'zoomButton'  : false,
         'insertButton': false,
         'copyButton'  : false,
         'updateButton': false,
         'deleteButton': false,
         'splitButton2': false,
         'firstButton' : false,
         'priorButton' : false,
         'nextButton'  : false,
         'lastButton'  : false,
         'actionButton': false};
   }
   if(RClass.isClass(dc, FForm)){
      var bl = RClass.isClass(c, MListView) && c.canListView();
      var bz = RClass.isClass(c, MZoom) && c.canZoom();
      switch(dc._emode){
         case EMode.Insert:
            bs = {
               'fetchButton' : false,
               'searchButton': false,
               'lovButton'   : bl,
               'zoomButton'  : bz,
               'insertButton': false,
               'copyButton'  : false,
               'updateButton': true,
               'deleteButton': false,
               'splitButton2': false,
               'firstButton' : false,
               'priorButton' : false,
               'nextButton'  : false,
               'lastButton'  : false,
               'actionButton': dc.hasAction()};
            break;
         case EMode.Update:
            bs = {
               'fetchButton' : dc.editFetch,
               'searchButton': dc.editSearch,
               'lovButton'   : bl,
               'zoomButton'  : bz,
               'insertButton': false,
               'copyButton'  : false,
               'updateButton': dc.editUpdate,
               'deleteButton': false,
               'splitButton2': false,
               'firstButton' : false,
               'priorButton' : false,
               'nextButton'  : false,
               'lastButton'  : false,
               'actionButton': dc.hasAction()};
            break;
         case EMode.Delete:
            bs = {
               'fetchButton' : false,
               'searchButton': false,
               'lovButton'   : bl,
               'zoomButton'  : bz,
               'insertButton': false,
               'copyButton'  : false,
               'updateButton': true,
               'deleteButton': false,
               'splitButton2': false,
               'firstButton' : false,
               'priorButton' : false,
               'nextButton'  : false,
               'lastButton'  : false,
               'actionButton': dc.hasAction()};
            break;
      }
   }
   if(RClass.isClass(dc, FGridControl)){
      var s = dc.isDataSelected();
      bs = {
         'fetchButton' : true,
         'searchButton': true,
         'lovButton'   : bl,
         'zoomButton'  : bz,
         'splitButton1': true,
         'insertButton': dc.editInsert,
         'copyButton'  : dc.editInsert && s,
         'updateButton': dc.editUpdate,
         'deleteButton': dc.editDelete && s,
         'splitButton2': true,
         'firstButton' : true,
         'priorButton' : true,
         'nextButton'  : true,
         'lastButton'  : true,
         'actionButton': dc.hasAction()};
   }
   o.toolBar.setEnables(bs);
}
function RFormSpace_syncSheet(n, l){
   var o = this;
   var s = o.sheets.syncSheet(n);
   s.setLabel(l);
   s.show();
   o.sheets.select(s);
   return s;
}
function RFormSpace_hasSpace(){
   return !this.spaces.isEmpty();
}
function RFormSpace_firstSpace(f){
   var o = this;
   var hb = o.historyBar;
   if(f){
      var c = hb.buttons.count;
      for(var n=0; n<c; n++){
         var b = hb.buttons.get(n);
         if(b.store.space){
            b.store.space.select(false);
            b.setVisible(false);
         }
      }
   }
   var b = hb.buttons.get(0);
   if(b){
      hb.selectButton(b);
      return b.store.space;
   }
   return o.createSpace();
}
function RFormSpace_currentSheet(){
   var o = this;
   return o.sheets.activeSheet;
}
function RFormSpace_currentSpace(){
   var o = this;
   var s = o.currentSheet();
   if(s){
      return s.currentSpace();
   }
}
function RFormSpace_nextSpace(c){
   var o = this;
   var s = o.currentSheet();
   if(s){
      return s.nextSpace();
   }
}
function RFormSpace_popupSpace(n){
   var o = this;
   var s = o.currentSheet();
   var p = s.popup(n);
   if(p){
      o.lsnsFormShow.process(p);
   }
   return p;
}
function RFormSpace_selectSpace(s){
   var o = this;
}
function RFormSpace_nextForm(fn){
   var o = this;
   var r = null;
   var sh = o.currentSheet();
   var sc = sh.currentSpace();
   if(sc.form){
      r = sc.form.getCurrentRow();
   }
   var sn = sh.nextSpace();
   var f = sn.findForm(fn);
   f.dsValues.append(r);
   sc.innerSelect(false);
   sn.innerSelect(true);
   o.focusControl(f);
   return f;
}
function RFormSpace_selectForm(f){
   var o = this;
   var t = o.titleBar;
   if(RClass.isClass(f, FForm)){
      t.setIcon('logic.common.form');
   }else if(RClass.isClass(f, FTable)){
      t.setIcon('logic.common.table');
   }
   if(RClass.isMode(ERun.Debug)){
      t.setCaption(f.label + " <FONT color='green'>( form=" + f.name + ", mode=" + REnum.tryDecode(EMode, f.inMode) + " )</FONT>");
   }else{
      t.setCaption(f.label);
   }
   o.focusControl(f);
   o.lsnsFormShow.process(o, f);
}
function RFormSpace_doFetch(){
   if(!this.hasSpace()){
      return;
   }
   this.currentSpace().doFetch();
}
function RFormSpace_doSearch(){
   if(!this.hasSpace()){
      return;
   }
   this.currentSpace().doSearch();
}
function RFormSpace_doCopy(){
   var o = this;
   if(!this.hasSpace()){
      return;
   }
   var ds = RConsole.find(FFocusConsole).findClass(MDataset);
   if( RClass.isClass(ds, FTable) ){
      var frows = ds.findSelectRows();
      if(frows && frows.count == 1){
         var sc = o.currentSpace();
         var sf = sc.form;
         var s = o.nextSpace();
         var fn = ds.getFormLink(EFormLink.Form);
         var f = s.findForm(fn);
         f.dsCopy(frows.get(0));
         f.setVisible(true);
         f.focus();
         s.setHistoryIcon();
         o.selectSpace(s);
      }
   }
}
function RFormSpace_doLov(){
   if(!this.hasSpace()){
      return;
   }
   this.currentSpace().doLov();
}
function RFormSpace_doZoom(){
   var o = this;
   if(!this.hasSpace()){
      return;
   }
   var focusControl = o.focusControl;
   var ouid = focusControl.reget();
   var sc = o.currentSpace();
   var sf = sc.form;
   var c = '#tbr.formZoom';
   var s = o.nextSpace();
   s.setHistoryIcon(c);
   var fn = focusControl.zoomRefer;
   var f = s.findForm(fn);
   f.dsValues.clear();
   f.dsUpdate(ouid);
   f.setVisible(true);
   f.focus();
   o.selectSpace(s);
}
function RFormSpace_doPrepare(sf){
   var o = this;
   if(!this.hasSpace()){
      return;
   }
   if(sf){
      var s = o.nextSpace();
      var fn = sf.getFormLink(EFormLink.Form);
      var f = s.findForm(fn);
   }else{
      var sc = o.currentSpace();
      sf = sc.form;
      var s = o.nextSpace();
      var ds = RConsole.find(FFocusConsole).findClass(MDataset);
      var fn = ds.getFormLink(EFormLink.Form);
      var f = s.findForm(fn);
   }
   f.dsValues.clear();
   if(sf){
      if(RClass.isClass(sf.parent, MDataset)){
         sf.parent.toDeepAttributes(s.form.dsValues);
      }
      f.dsValues.append(sf.dsValues);
   }
   f.setVisible(true);
   f.focus();
   s.setFormIcon();
   o.selectSpace(s);
   f.dsPrepare();
}
function RFormSpace_doUpdate(){
   var o = this;
   if(!o.hasSpace()){
      return;
   }
   var ds = RConsole.find(FFocusConsole).findClass(MDataset);
   if(ds){
      ds.dsDoUpdate();
   }else{
      RMessage.fatal("Cant't find focus dataset control.");
   }
}
function RFormSpace_doDelete(){
   var o = this;
   if(!this.hasSpace()){
      return;
   }
   var sc = o.currentSpace();
   var s = o.nextSpace();
   var ds = RConsole.find(FFocusConsole).findClass(MDataset);
   var r = ds.getCurrentRow();
   if(!r && RClass.isClass(ds, FTable)){
      return RMessage.fatal(this, null, 'Please select row first.');
   }
   var fn = ds.getFormLink(EFormLink.Form);
   var f = s.findForm(fn);
   f.dsDelete(r.get('ouid'));
   f.setVisible(true);
   f.focus();
   s.setFormIcon();
   o.selectSpace(s);
}
function RFormSpace_doMovePage(a){
   if(!this.hasSpace()){
      return;
   }
   this.currentSpace().doMovePage(a);
}
function RFormSpace_doDetail(){
   if(!this.hasSpace()){
      return;
   }
   this.currentSpace().doDetail();
}
function RFormSpace_doPrintPdf(){
   var dc = RConsole.find(FFocusConsole).findClass(MDataset);
   if(RClass.isClass(dc, MForm)){
      var formName = dc.getFormLink(EFormLink.Form);
      var uri = top.RContext.context('/public.pdf.wv?do=build&form_name=' + formName);
      RHtml.popup(uri, 800, 600);
   }
}
function RFormSpace_doOperateAction(){
   var o = this;
   if(!this.hasSpace()){
      return;
   }
   var w = RConsole.find(FActionConsole).find();
   var ds = RFormSpace.currentSpace().form;
   w.linkForm(ds);
   w.show();
}
function RFormSpace_goPage(rp){
   fmMain.action = top.RContext.context(rp);
   fmMain.submit();
}
function RFormSpace_refresh(){
   this.lsnsFormShow.process();
}
function RFormSpace_release(){
   var o = this;
   RConsole.find(FFormConsole).dispose();
}
