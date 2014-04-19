var EResultPage = new function(){
   var o = this;
   o.MasterSuccess = '/apl/page/Master.wa?do=success';
   o.InnerSuccess = '/apl/page/Inner.wa?do=success';
   o.SuccessInfo = '/apl/page/Inner.wa?do=successInfo';
   return o;
}
function FFrameSpace(){
   var o = this;
   o.manager           = null;
   o.formName          = null;
   o.form              = null;
   o.historyButton     = null;
   o.onHistoryClick    = FFrameSpace_onHistoryClick;
   o.onTableSelectRow  = FFrameSpace_onTableSelectRow;
   o.onUpdateEnd       = FFrameSpace_onUpdateEnd;
   o.onFormResize      = FFrameSpace_onFormResize;
   o.doFetch           = FFrameSpace_doFetch;
   o.doSearch          = FFrameSpace_doSearch;
   o.doLov             = FFrameSpace_doLov;
   o.doCopy            = FFrameSpace_doCopy;
   o.doZoom            = FFrameSpace_doZoom;
   o.doUpdate          = FFrameSpace_doUpdate;
   o.doDelete          = FFrameSpace_doDelete;
   o.doMovePage        = FFrameSpace_doMovePage;
   o.doDetail          = FFrameSpace_doDetail;
   o.doPrintPdf        = FFrameSpace_doPrintPdf;
   o.initialize        = FFrameSpace_initialize;
   o.findForm          = FFrameSpace_findForm;
   o.setHistoryCaption = FFrameSpace_setHistoryCaption;
   o.select            = FFrameSpace_select;
   o.refresh           = FFrameSpace_refresh;
   o.setHistoryIcon    = FFrameSpace_setHistoryIcon;
   return o;
}
function FFrameSpace_onFormResize(){
   var o = this;
   var f = o.form;
   var m = o.manager;
   m.lsnsFormShowBefore.process(o, f);
   m.lsnsFormShow.process(o, f);
}
function FFrameSpace_onHistoryClick(b){
   var o = this;
   o.manager.selectSpace(o);
}
function FFrameSpace_onTableSelectRow(r){
   var o = this;
   var ds = r.table;
   if(ds && ds.isLov){
      return;
   }
   o.form.setVisible(false);
   var formName = ds.getFormLink(EFormLink.Form);
   var form = o.findForm(formName);
   form.dsUpdate(r.ouid(), r.over());
   form.setVisible(true);
   RConsole.find(FFocusConsole).focusClass(MDataset, form)
   o.titleBar.setCaption(form.label);
   o.historyBar.nextButton().linkForm(form);
}
function FFrameSpace_onUpdateEnd(g){
   var o = this;
   var f = g.form;
   var hb = o.historyBar;
   var m = o.manager;
   var b = m.historyBar.popup();
   if(b){
      var s = b.store.space;
      if(s){
         s.refresh();
         s.form.focus();
      }
   }else{
     if(RClass.isClass(o.form, FTable)){
         o.refresh();
     }
      f.focus();
   }
   m.lsnsUpdateEnd.process(o);
}
function FFrameSpace_initialize(){
   var o = this;
   var lc = RConsole.find(FListenerConsole);
   lc.register(FTable, ETableAction.RowClick, o, o.onTableSelectRow);
   RConsole.find(FEventEngineConsole).loadConfig(RXml.makeNode(xEvent));
}
function FFrameSpace_createTitleBar(h){
   var o = this;
   var b = o.titleBar = RControl.create(FTitleBar, h);
}
function FFrameSpace_findForm(n){
   var o = this;
   var m = o.manager;
   var f = o.form;
   if(f && f.name == n){
      return f;
   }
   o.formName = n;
   f = o.form = RConsole.find(FFormConsole).createFromName(n, o.manager.hFormPanel, RWindow.builder());
   f.setSize('100%', '100%');
   f.lsnsUpdateEnd.register(o, o.onUpdateEnd);
   f.lsnsResize.register(o, o.onFormResize);
   f.dsShow();
   o.setHistoryCaption();
   return f;
}
function FFrameSpace_select(v){
   var o = this;
   var f = o.form;
   var m = o.manager;
   if(f){
      m.toolBar.setVisible(f.dispToolbar);
      if(v){
         m.selectForm(f);
         m.lsnsFormShowBefore.process(o, f);
         f.setVisible(true);
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
function FFrameSpace_doFetch(){
   var f = RConsole.find(FFocusConsole).findClass(MDataset);
   f.dsSearchs.clear();
   f.dsOrders.clear();
   f.dsFetch(true);
}
function FFrameSpace_doSearch(){
   RConsole.find(FFocusConsole).findClass(MDataset).doSearch();
}
function FFrameSpace_doCopy(){
   var f= RConsole.find(FFocusConsole).findClass(MDataset);
   if(f){
      f.dsCopy();
   }
}
function FFrameSpace_doLov(){
   var fc = RConsole.find(FFocusConsole).findClass(MListView);
   if(fc){
      fc.doListView();
   }
}
function FFrameSpace_doZoom(){
   var fc = RConsole.find(FFocusConsole).findClass(MZoom);
   if(fc){
      fc.doZoom();
   }
}
function FFrameSpace_doUpdate(){
}
function FFrameSpace_doDelete(){
}
function FFrameSpace_doMovePage(a){
   var dc = RConsole.find(FFocusConsole).findClass(MDataset);
   if(RClass.isClass(dc, FTable)){
      dc.dsMovePage(a);
   }
}
function FFrameSpace_doDetail(){
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
function FFrameSpace_doPrintPdf(){
   var dc = RConsole.find(FFocusConsole).findClass(MDataset);
   if(RClass.isClass(dc, MForm)){
      var formName = dc.getFormLink(EFormLink.Form);
      var uri = top.RContext.context('/public.pdf.wv?do=build&form_name=' + formName);
      RHtml.popup(uri, 800, 600);
   }
}
function FFrameSpace_setHistoryIcon(c){
   var o = this;
   var f = o.form;
   if(RString.isEmpty(c)){
      if(RClass.isClass(f, FForm)){
         if(EMode.Insert == f.inMode){
            var c = '#tbr.formInsert';
         }else if(EMode.Update == f.inMode){
            var c = '#tbr.formEdit';
         }else if(EMode.Delete == f.inMode){
            var c = '#tbr.formDelete';
         }
      }else{
         var c = '#tbr.table';
      }
   }
   o.historyButton.setIcon(c);
}
function FFrameSpace_setHistoryCaption(t){
   var o = this;
   var f = o.form;
   var r = f.label;
   if(t){
      r += ' (' + t + ')';
   }
   o.historyButton.setText(' ' + r);
}
function FFrameSpace_refresh(){
   var o = this;
   var f = o.form;
   if(f){
      f.dsFetch(true);
   }
}
function FFrameSpaceBox(){
   var o = this;
   o.focusControl               = null;
   o.titleBar                   = null;
   o.historyBar                 = null;
   o.toolBar                    = null;
   o.spaces                     = new TList();
   o.lsnsFormShowBefore         = new TListeners();
   o.lsnsFormShow               = new TListeners();
   o.lsnsUpdateEnd              = new TListeners();
   o.lsnsTitleButtonClickBefore = new TListeners();
   o.lsnsTitleButtonClick       = new TListeners();
   o.hTitlePanel                = null;
   o.hHistoryPanel              = null;
   o.hToolPanel                 = null;
   o.hFormPanel                 = null;
   o.onTitleButtonClickBefore   = FFrameSpaceBox_onTitleButtonClickBefore
   o.onTitleButtonClick         = FFrameSpaceBox_onTitleButtonClick;
   o.onHistoryClick             = FFrameSpaceBox_onHistoryClick;
   o.onToolBarRefresh           = FFrameSpaceBox_onToolBarRefresh;
   o.onTableSelectRow           = FFrameSpaceBox_onTableSelectRow;
   o.onSpaceResize              = FFrameSpaceBox_onSpaceResize;
   o.onFormShow                 = FFrameSpaceBox_onFormShow;
   o.doFetch                    = FFrameSpaceBox_doFetch;
   o.doSearch                   = FFrameSpaceBox_doSearch;
   o.doLov                      = FFrameSpaceBox_doLov;
   o.doCopy                     = FFrameSpaceBox_doCopy;
   o.doZoom                     = FFrameSpaceBox_doZoom;
   o.doPrepare                  = FFrameSpaceBox_doPrepare;
   o.doUpdate                   = FFrameSpaceBox_doUpdate;
   o.doDelete                   = FFrameSpaceBox_doDelete;
   o.doMovePage                 = FFrameSpaceBox_doMovePage;
   o.doDetail                   = FFrameSpaceBox_doDetail;
   o.doPrintPdf                 = FFrameSpaceBox_doPrintPdf;
   o.doOperateAction            = FFrameSpaceBox_doOperateAction;
   o.initialize                 = FFrameSpaceBox_initialize;
   o.setFormPanel               = FFrameSpaceBox_setFormPanel;
   o.createSpace                = FFrameSpaceBox_createSpace;
   o.createTitleBar             = FFrameSpaceBox_createTitleBar;
   o.createHistoryBar           = FFrameSpaceBox_createHistoryBar;
   o.createToolBar              = FFrameSpaceBox_createToolBar;
   o.currentSpace               = FFrameSpaceBox_currentSpace;
   o.nextSpace                  = FFrameSpaceBox_nextSpace;
   o.popupSpace                 = FFrameSpaceBox_popupSpace;
   o.selectSpace                = FFrameSpaceBox_selectSpace;
   o.selectForm                 = FFrameSpaceBox_selectForm;
   o.release                    = FFrameSpaceBox_release;
   o.goPage                     = FFrameSpaceBox_goPage;
   RMemory.register('FFrameSpaceBox', o);
   return o;
}
function FFrameSpaceBox_onFormShow(){
   this.lsnsFormShow.process();
}
function FFrameSpaceBox_onTitleButtonClickBefore(s){
   this.lsnsTitleButtonClickBefore.process(s);
}
function FFrameSpaceBox_onTitleButtonClick(s){
   this.lsnsTitleButtonClick.process(s);
}
function FFrameSpaceBox_onHistoryClick(b){
   var o = this;
   o.selectSpace(b.store.space);
   o.historyBar.historyIndex = o.historyBar.buttons.indexOf(b);
}
function FFrameSpaceBox_onSpaceResize(s){
   var o = this;
   var ts = FFrameSpaceBox.currentSpace();
   var f = ts.form;
   if(f){
      f.psResize();
   }
}
function FFrameSpaceBox_onTableSelectRow(r){
   var o = this;
   top.RWindow.setEnable(false);
   var ds = r.table;
   if(ds && ds.isLov){
      return;
   }
   var sc = o.currentSpace();
   var fn = ds.getFormLink(EFormLink.Form);
   if(!RString.isEmpty(fn)){
      sc.select(false);
      var s = o.nextSpace();
      var f = s.findForm(fn);
      f.dsValues.clear();
      r.toDeepAttributes(f.dsValues)
      f.dsUpdate(r.ouid());
      s.setHistoryIcon();
      o.selectSpace(s);
      top.RWindow.setEnable(true);
   }
   top.RWindow.setEnable(true);
}
function FFrameSpaceBox_onToolBarRefresh(){
   var o = this;
   var bs = null;
   var f  = o.currentSpace().form;
   var c = RConsole.find(FFocusConsole).focusControl;
   var dc = RConsole.find(FFocusConsole).findClass(MDataset);
   if(dc){
   }
   var bl = false;
   var bz = false;
   if(c && RClass.isClass(f, FForm)){
      bl = RClass.isClass(c, MListView) && c.canListView();
      bz = RClass.isClass(c, MZoom) && c.canZoom();
   }
   if(RClass.isClass(f, FForm)){
      switch(f.inMode){
         case EMode.Insert:
            bs = {
               'fetchButton':false, 'searchButton':false, 'lovButton':bl, 'zoomButton':bz,
               'insertButton':false, 'copyButton':false, 'updateButton':true, 'deleteButton':false,
               'splitButton2':false, 'firstButton':false, 'priorButton':false, 'nextButton':false, 'lastButton':false,
               'actionButton':f.editAction};
               bs["actionButton"] = f.hasAction();
            break;
         case EMode.Update:
               bs = {
               'fetchButton':f.editFetch, 'searchButton':f.editSearch, 'lovButton':bl, 'zoomButton':bz,
               'insertButton':false, 'copyButton':false, 'updateButton':f.editUpdate, 'deleteButton':false,
               'splitButton2':false, 'firstButton':false, 'priorButton':false, 'nextButton':false, 'lastButton':false,
               'actionButton':f.editAction};
               bs["actionButton"] = f.hasAction();
            break;
         case EMode.Delete:
            bs = {
               'fetchButton':false, 'searchButton':false, 'lovButton':bl, 'zoomButton':bz,
               'insertButton':false, 'copyButton':false, 'updateButton':true, 'deleteButton':false,
               'splitButton2':false, 'firstButton':false, 'priorButton':false, 'nextButton':false, 'lastButton':false,
               'actionButton':f.editAction};
               bs["actionButton"] = f.hasAction();
            break;
      }
   }else if(RClass.isClass(f, FTable)){
      bs = {
         'fetchButton':true, 'searchButton':true, 'lovButton':bl, 'zoomButton':bz,
         'insertButton':f.editInsert, 'copyButton':false, 'updateButton':f.editUpdate, 'deleteButton':f.editDelete,
         'splitButton2':true, 'firstButton':true, 'priorButton':true, 'nextButton':true, 'lastButton':true,
         'actionButton':f.editAction};
      var ft = f.editFetch ? f.editFetch : false;
      bs["fetchButton"] = bs["searchButton"] = ft ;
      ft = f.editInsert ? f.editInsert : false;
      bs["insertButton"] = ft ;
      bs["deleteButton"] = f.isDataSelected()&&f.editDelete;
      bs["actionButton"] = f.hasAction();
   }
   o.toolBar.setEnables(bs);
   o.focusControl = c;
}
function FFrameSpaceBox_initialize(){
   var o = this;
   var lc = RConsole.find(FListenerConsole);
   lc.register(FTable, ETableAction.RowClick, o, o.onTableSelectRow);
   lc.register(FTable, ETableAction.RowDblClick, o, o.onTableSelectRow);
   RConsole.find(FIdleConsole).register(o, o.onToolBarRefresh);
   RWindow.lsnsResize.register(o, o.onSpaceResize);
   RFlash.start();
}
function FFrameSpaceBox_setFormPanel(h){
   var o = this;
   o.hFormPanel = h;
   RWindow.hContainer = h;
}
function FFrameSpaceBox_createSpace(b){
   var o = this;
   var s = new TFormSpace();
   s.manager = o;
   s.historyButton = b ? b : o.historyBar.nextButton();
   s.setHistoryIcon();
   s.historyButton.store.space = s;
   o.spaces.push(s);
   return s;
}
function FFrameSpaceBox_createTitleBar(h, hx){
   var o = this;
   if(hx){
      o.titleBar = RControl.fromXml(hx, h)
   }else{
      o.titleBar = RControl.create(FTitleBar, h);
   }
   o.titleBar.lsnsNavButtonClickBefore.register(o, o.onTitleButtonClickBefore);
   o.titleBar.lsnsNavButtonClick.register(o, o.onTitleButtonClick);
}
function FFrameSpaceBox_createHistoryBar(h){
   var o = this;
   var b = o.historyBar = RControl.create(FHistoryBar, h);
   b.lsnsButtonClick.register(o, o.onHistoryClick);
}
function FFrameSpaceBox_createToolBar(h, hx){
   var o = this;
   var xtoolbar = RXml.makeNode(hx);
   o.hToolPanel = h;
   o.toolBar = RControl.fromNode(xtoolbar, h);
}
function FFrameSpaceBox_currentSpace(){
   var o = this;
   var b = o.historyBar.currentButton();
   return b.store.space;
}
function FFrameSpaceBox_nextSpace(c){
   var o = this;
   var b = o.historyBar.nextButton(c);
   if(b.store.space){
      return b.store.space;
   }
   return o.createSpace(b);
}
function FFrameSpaceBox_popupSpace(n){
   var o = this;
   var b = o.historyBar.popup(n);
   var s = b.store.space;
   if(s){
      o.selectSpace(s);
   }
   return s;
}
function FFrameSpaceBox_doFetch(){
   this.currentSpace().doFetch();
}
function FFrameSpaceBox_doSearch(){
   this.currentSpace().doSearch();
}
function FFrameSpaceBox_doCopy(){
   var o = this;
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
function FFrameSpaceBox_doLov(){
   this.currentSpace().doLov();
}
function FFrameSpaceBox_doZoom(){
   var o = this;
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
function FFrameSpaceBox_doPrepare(sf){
   var o = this;
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
      sf.toDeepAttributes(s.form.dsValues);
      f.dsValues.append(sf.dsValues);
   }
   f.dsPrepare();
   f.setVisible(true);
   f.focus();
   s.setHistoryIcon();
   o.selectSpace(s);
}
function FFrameSpaceBox_doUpdate(){
   var o = this;
   var ds = RConsole.find(FFocusConsole).findClass(MDataset);
   if(RClass.isClass(ds, MForm)){
         ds.dsDoUpdate();
   }
}
function FFrameSpaceBox_doDelete(){
   var o = this;
   var sc = o.currentSpace();
   var s = o.nextSpace();
   var ds = RConsole.find(FFocusConsole).findClass(MDataset);
   var r = ds.dsViewer.current();
   var ouid = null;
   if(RClass.isClass(ds, FForm)){
      ouid = r.ouid();
   }else if(RClass.isClass(ds, FTable)){
      var rs = ds.findSelectRows();
      if(rs.isEmpty()){
         return RMessage.fatal(this, null, 'Please select row.');
      }
      var r = rs.get(0);
      ouid = r.ouid();
   }
   var fn = ds.getFormLink(EFormLink.Form);
   var f = s.findForm(fn);
   f.dsDelete(ouid);
   f.setVisible(true);
   f.focus();
   s.setHistoryIcon();
   o.selectSpace(s);
}
function FFrameSpaceBox_doMovePage(a){
   this.currentSpace().doMovePage(a);
}
function FFrameSpaceBox_doDetail(){
   this.currentSpace().doDetail();
}
function FFrameSpaceBox_doPrintPdf(){
   var dc = RConsole.find(FFocusConsole).findClass(MDataset);
   if(RClass.isClass(dc, MForm)){
      var formName = dc.getFormLink(EFormLink.Form);
      var uri = top.RContext.context('/public.pdf.wv?do=build&form_name=' + formName);
      RHtml.popup(uri, 800, 600);
   }
}
function FFrameSpaceBox_selectSpace(s){
   var o = this;
   var ss = o.spaces;
   for(var n=0; n<ss.count; n++){
      var c = ss.get(n);
      if(c != s){
         c.select(false);
      }
   }
   s.select(true);
}
function FFrameSpaceBox_selectForm(f){
   var o = this;
   var t = o.titleBar;
   if(RClass.isClass(f, FForm)){
      t.setIcon('#com.form');
   }else if(RClass.isClass(f, FTable)){
      t.setIcon('#com.table');
   }
   if(RClass.isMode(ERun.Debug)){
      t.setCaption(f.label + " <FONT color='green'>( form=" + f.name + ", mode=" + REnum.tryDecode(EMode, f.inMode) + " )</FONT>");
   }else{
      t.setCaption(f.label);
   }
}
function FFrameSpaceBox_release(){
   var o = this;
   var cl = RConsole.find(FFormConsole);
   cl.dispose();
}
function FFrameSpaceBox_doOperateAction(){
   var o = this;
   var w = RConsole.find(FActionConsole).find();
   var ds = FFrameSpaceBox.currentSpace().form;
   w.linkForm(ds);
   w.show();
}
function FFrameSpaceBox_goPage(rp){
   fmMain.action = top.RContext.context(rp);
   fmMain.submit();
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
   o.titleBar                   = null;
   o.historyBar                 = null;
   o.toolBar                    = null;
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
   o.createTitleBar             = RFormSpace_createTitleBar;
   o.createHistoryBar           = RFormSpace_createHistoryBar;
   o.createToolBar              = RFormSpace_createToolBar;
   o.createSpace                = RFormSpace_createSpace;
   o.setFormPanel               = RFormSpace_setFormPanel;
   o.focusControl               = RFormSpace_focusControl;
   o.hasSpace                   = RFormSpace_hasSpace;
   o.firstSpace                 = RFormSpace_firstSpace;
   o.currentSpace               = RFormSpace_currentSpace;
   o.nextSpace                  = RFormSpace_nextSpace;
   o.lastSpace                  = RFormSpace_lastSpace;
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
   o.goBlankPage                = RFormSpace_goBlankPage;
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
   var f = ts.form;
   if(f){
      f.psResize();
   }
}
function RFormSpace_onFormShow(){
   this.lsnsFormShow.process(this, f);
}
function RFormSpace_onFormResize(){
   var f = RFormSpace.currentSpace().form;
   f.psResize();
   this.lsnsFormShow.process(this, f);
}
function RFormSpace_onTableSelectRow(r){
   var o = this;
   if(!o.hasSpace()){
      return;
   }
   var dc = r.table;
   if(RClass.isClass(r, FBrowserNode)){
      dc = r.browser;
   }
   if(!dc){
      return;
   }else if(dc.isLov){
      return;
   }
   var sc = o.currentSpace();
   var fn = dc.getFormLink(EFormLink.Form);
   if(!RString.isEmpty(fn)){
      sc.select(false);
      var s = o.nextSpace();
      var f = s.findForm(fn);
      f.dsValues.clear();
      if(RClass.isClass(r, FBrowserNode)){
         f.dsValues.append(r.attributes);
      }else{
         r.toDeepAttributes(f.dsValues)
      }
      o.selectSpace(s);
      f.dsUpdate(r.getId());
      s.setHistoryIcon();
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
   var b = o.historyBar = RControl.create(FHistoryBar, h);
   b.lsnsButtonClick.register(o, o.onHistoryClick);
}
function RFormSpace_createToolBar(h, hx){
   var o = this;
   o.hToolPanel = h;
   o.toolBar = RControl.fromNode(RXml.makeNode(hx), h);
   o.focusControl();
}
function RFormSpace_createSpace(b){
   var o = this;
   var s = new TFormSpace();
   s.manager = s.parent = o;
   s.historyButton = b ? b : o.historyBar.nextButton();
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
         default:
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
         'copyButton'  : false,
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
function RFormSpace_hasSpace(){
   return this.historyBar && (null != this.historyBar.currentButton());
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
function RFormSpace_lastSpace(n){
   var o = this;
   var hb = o.historyBar;
   n = n ? n : 1;
   var b = hb.buttons.get(Math.max((hb.historyIndex - 1 - n),0));
   return b.store.space;
}
function RFormSpace_currentSpace(){
   var o = this;
   var b = o.historyBar.currentButton();
   return b.store.space;
}
function RFormSpace_nextSpace(c){
   var o = this;
   var b = o.historyBar.nextButton(c);
   if(b.store.space){
      return b.store.space;
   }
   return o.createSpace(b);
}
function RFormSpace_popupSpace(n){
   var o = this;
   var b = o.historyBar.popup(n);
   var s = b.store.space;
   if(s){
      o.selectSpace(s);
      o.lsnsFormShow.process(s);
   }
   return s;
}
function RFormSpace_selectSpace(s){
   var o = this;
   var ss = o.spaces;
   for(var n=0; n<ss.count; n++){
      var c = ss.get(n);
      if(c != s){
         c.select(false);
      }
   }
   s.select(true);
}
function RFormSpace_nextForm(fn, m){
   var o = this;
   var r = null;
   var cs = o.currentSpace();
   if(cs.form){
      r = cs.form.getCurrentRow();
   }
   var s = o.nextSpace();
   var f = s.findForm(fn);
   o.selectSpace(s);
   if(!m){
      f.dsValues.append(r);
   }
   return f;
}
function RFormSpace_selectForm(f){
   var o = this;
   var t = o.titleBar;
   if(t){
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
function RFormSpace_doZoom(z, v){
   var o = this;
   if(!o.hasSpace()){
      return;
   }
   var sc = o.currentSpace();
   if(RClass.dump(z, MZoom) && z.canZoom()){
      sc.select(false);
      var s = o.nextSpace();
      var f = s.findForm(z.zoomRefer);
      f.dsValues.clear();
      o.selectSpace(s);
      s.setHistoryIcon();
      f.dsUpdate(v);
   }
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
   s.setHistoryIcon();
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
   s.setHistoryIcon();
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
function   ForceWindow   ()
{
    this.r   =   document.documentElement;
    this.f   =   document.createElement("FORM");
    this.f.target   =   "_blank";
    this.f.method   =   "post";
    this.r.insertBefore(this.f,   this.r.childNodes[0]);
}
ForceWindow.prototype.pop   =   function   (sUrl)
{
    this.f.action   =   sUrl;
    this.f.submit();
}
function RFormSpace_goPage(rp){
   fmMain.action = top.RContext.context(rp);
   fmMain.submit();
}
function RFormSpace_goBlankPage(rp){
   fmMain.target = '_blank';
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
function TFormSpace(){
   var o = this;
   o.manager           = null;
   o.onUpdateEnd       = TFormSpace_onUpdateEnd;
   o.findForm          = TFormSpace_findForm;
   o.formName          = null;
   o.form              = null;
   o.historyButton     = null;
   o.onHistoryClick    = TFormSpace_onHistoryClick;
   o.onTableSelectRow  = TFormSpace_onTableSelectRow;
   o.onFormResize      = TFormSpace_onFormResize;
   o.doFetch           = TFormSpace_doFetch;
   o.doSearch          = TFormSpace_doSearch;
   o.doLov             = TFormSpace_doLov;
   o.doCopy            = TFormSpace_doCopy;
   o.doZoom            = TFormSpace_doZoom;
   o.doDelete          = TFormSpace_doDelete;
   o.doMovePage        = TFormSpace_doMovePage;
   o.doDetail          = TFormSpace_doDetail;
   o.doPrintPdf        = TFormSpace_doPrintPdf;
   o.initialize        = TFormSpace_initialize;
   o.setHistoryCaption = TFormSpace_setHistoryCaption;
   o.select            = TFormSpace_select;
   o.refresh           = TFormSpace_refresh;
   o.setHistoryIcon    = TFormSpace_setHistoryIcon;
   return o;
}
function TFormSpace_onUpdateEnd(g){
   var o = this;
   var f = g.form;
   var hb = o.historyBar;
   var m = o.manager;
   var b = m.historyBar.popup();
   if(b){
      var s = b.store.space;
      if(s){
         s.refresh();
         s.form.focus();
      }
   }else{
     if(RClass.isClass(o.form, FTable)){
         o.refresh();
     }
      f.focus();
   }
   m.onUpdateEnd(g);
}
function TFormSpace_findForm(n){
   var o = this;
   var m = o.manager;
   var f = o.form;
   if(f){
      if(f.name == n){
         return f;
      }else{
         RConsole.find(FFormConsole).free(f);
      }
   }
   o.formName = n;
   f = o.form = RConsole.find(FFormConsole).createFromName(n, o.manager.hFormPanel, RWindow.builder());
   f.setSize('100%', '100%');
   f.lsnsUpdateEnd.register(o, o.onUpdateEnd);
   f.dsShow();
   o.setHistoryCaption();
   return f;
}
function TFormSpace_onFormResize(){
   var o = this;
   var f = o.form;
   var m = o.manager;
   m.lsnsFormShowBefore.process(o, f);
   m.lsnsFormShow.process(o, f);
}
function TFormSpace_onHistoryClick(b){
   var o = this;
   o.manager.selectSpace(o);
}
function TFormSpace_onTableSelectRow(r){
   var o = this;
   var ds = r.table;
   if(ds && ds.isLov){
      return;
   }
   o.form.setVisible(false);
   var formName = ds.getFormLink(EFormLink.Form);
   var form = o.findForm(formName);
   form.dsUpdate(r.ouid(), r.over());
   form.setVisible(true);
   RConsole.find(FFocusConsole).focusClass(MDataset, form)
   o.titleBar.setCaption(form.label);
   o.historyBar.nextButton().linkForm(form);
}
function TFormSpace_initialize(){
   var o = this;
   var lc = RConsole.find(FListenerConsole);
   lc.register(FTable, ETableAction.RowClick, o, o.onTableSelectRow);
   RConsole.find(FEventEngineConsole).loadConfig(RXml.makeNode(xEvent));
}
function TFormSpace_createTitleBar(h){
   var o = this;
   var b = o.titleBar = RControl.create(FTitleBar, h);
}
function TFormSpace_select(v){
   var o = this;
   var f = o.form;
   var m = o.manager;
   if(f){
      m.toolBar.setVisible(f.dispToolbar);
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
function TFormSpace_doFetch(){
   var f = RConsole.find(FFocusConsole).findClass(MDataset);
   f.dsSearchs.clear();
   f.dsOrders.clear();
   f.dsFetch(true, true);
}
function TFormSpace_doSearch(){
   RConsole.find(FFocusConsole).findClass(MDataset).doSearch();
}
function TFormSpace_doCopy(){
   var f= RConsole.find(FFocusConsole).findClass(MDataset);
   if(f){
      f.dsCopy();
   }
}
function TFormSpace_doLov(){
   var fc = RConsole.find(FFocusConsole).findClass(MListView);
   if(fc){
      fc.doListView();
   }
}
function TFormSpace_doZoom(){
   var fc = RConsole.find(FFocusConsole).findClass(MZoom);
   if(fc){
      fc.doZoom();
   }
}
function TFormSpace_doDelete(){
}
function TFormSpace_doMovePage(a){
   var dc = RConsole.find(FFocusConsole).findClass(MDataset);
   if(RClass.isClass(dc, FTable)){
      dc.dsMovePage(a);
   }
}
function TFormSpace_doDetail(){
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
function TFormSpace_doPrintPdf(){
   var dc = RConsole.find(FFocusConsole).findClass(MDataset);
   if(RClass.isClass(dc, MForm)){
      var formName = dc.getFormLink(EFormLink.Form);
      var uri = top.RContext.context('/public.pdf.wv?do=build&form_name=' + formName);
      RHtml.popup(uri, 800, 600);
   }
}
function TFormSpace_setHistoryIcon(c){
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
   o.historyButton.setIcon(c);
}
function TFormSpace_setHistoryCaption(t){
   var o = this;
   var f = o.form;
   var r = f.label;
   if(t){
      r += ' (' + t + ')';
   }
   o.historyButton.setText(' ' + r);
}
function TFormSpace_refresh(){
   var o = this;
   var f = o.form;
   if(f){
      f.dsFetch(true, true);
   }
}
