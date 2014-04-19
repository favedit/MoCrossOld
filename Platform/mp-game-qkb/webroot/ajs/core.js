function EResultCommandFace(){
   var o = this;
   o.TreeReload        = 'tree.reload';
   o.TreeParentRefresh = 'tree.parent.refresh';
   o.TreeNodeRefresh   = 'tree.node.refresh';
   o.PageRedirect      = 'page.redirect';
   return o;
}
var EResultCommand = new EResultCommandFace();
function ERowActionFace(){
   var o = this;
   o.Insert = 'I';
   o.Update = 'U';
   o.Delete = 'D';
   return o;
}
var ERowAction = new ERowActionFace();
function FActionConsole(o){
   o = RClass.inherits(this, o, FConsole);
   o.scope         = EScope.Global;
   o.window        = null;
   o.find          = FActionConsole_find;
   return o;
}
function FActionConsole_find(){
   var o = this;
   if(!o.window){
      o.window = top.RControl.create('FActionWindow');
   }
   return o.window;
}
function FActiveConsole(o){
   o = RClass.inherits(this, o, FConsole);
   o.scope        = EScope.Page;
   o.active       = true;
   o.working      = false;
   o.interval     = 10;
   o.intervalId   = null;
   o.actives      = new TList();
   o.activeMemory = null;
   o.hWindow      = null;
   o.ohInterval   = FActiveConsole_ohInterval;
   o.onMemory     = FActiveConsole_onMemory;
   o.construct    = FActiveConsole_construct;
   o.push         = FActiveConsole_push;
   o.process      = FActiveConsole_process;
   o.processAll   = FActiveConsole_processAll;
   o.wait         = FActiveConsole_wait;
   o.release      = FActiveConsole_release;
   o.dispose      = FActiveConsole_dispose;
   return o;
}
var g_activeConsole = null;
function FActiveConsole_dispose(){
   var o = this;
   o.release();
   o.hWindow = null;
}
function FActiveConsole_onMemory(){
   RMemory.refresh();
}
function FActiveConsole_construct(){
   var o = this;
   o.base.FConsole.construct.call(o);
   o.hWindow = window;
   g_activeConsole = o;
   var a = o.activeMemory = new TActive(o, o.onMemory);
   a.interval = 10000;
   o.push(a);
   o.intervalId = o.hWindow.setInterval(o.ohInterval, o.interval);
}
function FActiveConsole_push(a){
   if(a){
      var o = this;
      if(!o.actives.contains(a)){
         a.id = o.actives.push(a);
         a.name = 'T:' + RString.lpad(a.id, 4, '0');
      }
   }
}
function FActiveConsole_process(active){
   if(active){
      switch(active.status){
         case EActive.Sleep:
            break;
         case EActive.Active:
            active.process(this.interval);
            break;
         case EActive.Cancel:
            this.actives.removeItem(active);
            break;
      }
   }
}
function FActiveConsole_processAll(){
   var o = this;
   if(o.active){
      o.working = true;
      var as = o.actives;
         for(var n=0; n<as.count; n++){
            o.process(as.get(n));
         }
      o.working = false;
   }
}
function FActiveConsole_ohInterval(){
   var ac = g_activeConsole;
   if(ac && !ac.working){
      ac.processAll();
   }
}
function FActiveConsole_wait(request){
}
function FActiveConsole_release(){
   if(this.hWindow && this.intervalId){
      this.hWindow.clearInterval(this.intervalId);
   }
}
function FButtonConsole(o){
   o = RClass.inherits(this, o, FConsole);
   o.scope         = EScope.Global;
   o.window        = null;
   o.find          = FButtonConsole_find;
   return o;
}
RConsole.register(new TConsole(EScope.Page, FKeyConsole, true));
function FButtonConsole_find(){
   var o = this;
   if(!o.window){
      o.window = top.RControl.create('FButtonWindow');
   }
   return o.window;
}
function FButtonFormConsole(o) {
   o = RClass.inherits(this, o, FConsole);
   o.scope = EScope.Global;
   o.window = null;
   o.find = FButtonFormConsole_find;
   return o;
}
RConsole.register(new TConsole(EScope.Page, FKeyConsole, true));
function FButtonFormConsole_find() {
   var o = this;
   if (!o.window) {
      o.window = top.RControl.create('FButtonFormWindow');
   }
   return o.window;
}
function FChartConsole(o){
   o = RClass.inherits(this, o, FConsole);
   o.scope          = EScope.Page;
   o.defines        = null;
   o.events         = null;
   o.lsnsLoaded     = null;
   o.formId         = 0;
   o.onLoaded       = FChartConsole_onLoaded;
   o.construct      = FChartConsole_construct;
   o.loadService    = FChartConsole_loadService;
   o.loadChart      = FChartConsole_loadChart;
   return o;
}
function FChartConsole_onLoaded(e){
   var o = this;
   e.argument.callback.invoke(e);
}
function FChartConsole_construct(){
   var o = this;
   o.defines = new TMap();
   o.defines.set(EForm.Form, new TMap());
   o.defines.set(EForm.Lov, new TMap());
   o.events = new TMap();
   o.lsnsLoaded = new TListeners();
}
function FChartConsole_loadService(g){
   var o = this;
   var doc = new TXmlDocument();
   var root = doc.root();
   root.set('action', 'chart');
   var f = root.create('Chart');
   f.set('name', g.name);
   var e = new TEvent(o, EXmlEvent.Send, o.onLoaded);
   e.url = RService.url('logic.webform.dataset');
   e.document = doc;
   e.argument = g;
   RConsole.find(FXmlConsole).process(e);
}
function FChartConsole_loadChart(g){
   var o = this;
   var doc = new TXmlDocument();
   var root = doc.root();
   root.set('action', 'chart');
   var f = root.create('Chart');
   f.set('name', g.name);
   var url = RService.url('logic.webform.dataset');
   var doc = RConsole.find(FXmlConsole).send(url, doc);
   return doc.xml().toString();
}
function FCheckWindowConsole(o){
   o = RClass.inherits(this, o, FConsole);
   o.scope         = EScope.Global;
   o.find          = FCheckWindowConsole_find;
   o.window        = null;
   return o;
}
function FCheckWindowConsole_find(){
   var o = this;
   var w = o.window;
   if(!w){
      w = o.window = RControl.create( FCheckWindow );
   }
   return w;
}
function FCodeListConsole(o){
   o = RClass.inherits(this, o, FConsole);
   o.scope        = EScope.Page;
   o.fetch        = FCodeListConsole_fetch;
   return o;
}
function FCodeListConsole_fetch(g){
   var o = this;
   var doc = new TXmlDocument();
   var root = doc.root();
   root.set('action', 'codelist');
   var f = root.create('CodeList');
   var nd = g.toNode();
   if(nd){
      f.push(nd);
   }
   f.set('name', g.name);
   var url = RService.url('logic.webform');
   var doc = RConsole.find(FXmlConsole).send(url, doc);
   var root = doc.root();
   var mc = RConsole.find(FMessageConsole);
   var r = mc.checkResult(root);
   if(r){
      return doc;
   }
}
function FCompleteConsole(o){
   o = RClass.inherits(this, o, FConsole);
   o.scope       = EScope.Page;
   o.serviceName = 'logic.dataset';
   o.onLoaded    = FCompleteConsole_onLoaded;
   o.search      = FCompleteConsole_search;
   return o;
}
function FCompleteConsole_onLoaded(e){
   var o = this;
   var xr = e.document.root();
   var r = RConsole.find(FMessageConsole).checkResult(xr);
   if(r){
      e.control.onComplete(xr);
   }
}
function FCompleteConsole_search(c){
   var o = this;
   o.activeControl = c;
   var doc = new TXmlDocument();
   var root = doc.root();
   root.set('action', EDataAction.Complete);
   var node = root.create('Control');
   node.set('form', c.formName);
   node.set('control', c.controlName);
   node.set('value', c.controlValue);
   var e = new TEvent(o, EXmlEvent.Send, o.onLoaded);
   e.url = RService.url(o.serviceName);
   e.document = doc;
   e.control = c;
   RConsole.find(FXmlConsole).process(e);
}
function FConsole(o){
   o = RClass.inherits(this, o, FObject);
   o.scope = EScope.Global;
   return o;
}
function FDatasetConsole(o){
   o = RClass.inherits(this, o, FConsole);
   o.scope            = EScope.Page;
   o.datasets         = null;
   o.lsnsLoaded       = null;
   o.lsnsUpdateBegin  = null;
   o.lsnsUpdateEnd    = null;
   o.onFetchLoaded    = FDatasetConsole_onFetchLoaded;
   o.onScalarLoaded   = FDatasetConsole_onScalarLoaded;
   o.onCompleteLoaded = FDatasetConsole_onCompleteLoaded;
   o.onLovLoaded      = FDatasetConsole_onLovLoadeded;
   o.onPrepareLoaded  = FDatasetConsole_onPrepareLoaded;
   o.onUpdateLoaded   = FDatasetConsole_onUpdateLoaded;
   o.onTreeLoaded     = FDatasetConsole_onTreeLoaded;
   o.onLoaded         = FDatasetConsole_onLoaded;
   o.construct        = FDatasetConsole_construct;
   o.loadDataset      = FDatasetConsole_loadDataset;
   o.loadDatasets     = FDatasetConsole_loadDatasets;
   o.get              = FDatasetConsole_get;
   o.getById          = FDatasetConsole_getById;
   o.getByPath        = FDatasetConsole_getByPath;
   o.fetch            = FDatasetConsole_fetch;
   o.scalar           = FDatasetConsole_scalar;
   o.complete         = FDatasetConsole_complete;
   o.lov              = FDatasetConsole_lov;
   o.prepare          = FDatasetConsole_prepare;
   o.update           = FDatasetConsole_update;
   o.onColumnFetch    = FDatasetConsole_onColumnFetch;
   o.columnNodeFetch  = FDatasetConsole_columnNodeFetch;
   o.treeUpdate       = FDatasetConsole_treeUpdate;
   return o;
}
function FDatasetConsole_onScalarLoaded(g, r){
   var o = this;
   if(r.hasNode()){
      var rc = g.resultConfig = r.find('Control');
      if(rc){
         g.result = rc.get('result');
      }
   }
   g.invoke();
}
function FDatasetConsole_scalar(g){
   var o = this;
   var doc = new TXmlDocument();
   var r = doc.root();
   r.set('action', EDataAction.Scalar);
   r.push(g.toNode());
   var e = new TEvent(o, EXmlEvent.Send, o.onLoaded);
   e.url = RService.url('logic.webform.dataset');
   e.action = EDataAction.Scalar;
   e.argument = g;
   e.document = doc;
   RConsole.find(FXmlConsole).process(e);
}
function FDatasetConsole_loadDataset(fd, x){
   var o = this;
   var dn = x.get('name') + '@' + fd;
   var ds = o.datasets.get(dn);
   if(!ds){
      ds = new TDataset();
      o.datasets.set(dn, ds);
      ds.id = dn;
   }
   ds.clear();
   ds.loadNode(x);
   return ds;
}
function FDatasetConsole_loadDatasets(dss, fd, x){
   var o = this;
   if(x && x.hasNode()){
      var xds = x.nodes;
      for(var n=0; n<xds.count; n++){
         var xd = xds.get(n);
         if(xd.isName('Dataset')){
            var ds = o.loadDataset(fd, xd);
            dss.set(ds.name, ds);
         }
      }
   }
}
function FDatasetConsole_onFetchLoaded(g, x){
   var o = this;
   var rds = g.resultDatasets;
   if(x.hasNode()){
      var xfs = x.nodes;
      var xfc = xfs.count;
      for(var n = 0; n < xfc; n ++){
         var xf = xfs.get(n);
         var fd = xf.get('id');
         if(!RString.isEmpty(fd)){
            o.loadDatasets(rds, fd, xf);
         }
      }
   }
   if(!rds.isEmpty()){
      var c = rds.count;
      for(var n=0; n<c; n++){
         var rd = rds.value(n);
         if('/' == rd.name){
            g.resultDataset = rd;
            g.resultRow = rd.row(0);
            break;
         }
      }
   }
   g.invoke();
}
function FDatasetConsole_onCompleteLoaded(g, root){
   var o = this;
   if(root.hasNode()){
      var nc = root.find('Control');
      if(nc){
         g.resultConfig = nc;
      }
   }
   g.invoke();
}
function FDatasetConsole_onLovLoadeded(arg, root){
   var o = this;
   arg.lovNode = root;
   arg.invoke();
}
function FDatasetConsole_onPrepareLoaded(g, x){
   var o = this;
   var rds = g.resultDatasets;
   if(x.hasNode()){
      var xfs = x.nodes;
      var xfc = xfs.count;
      for(var n = 0; n < xfc; n ++){
         var xf = xfs.get(n);
         var fd = xf.get('id');
         if(!RString.isEmpty(fd)){
            o.loadDatasets(rds, fd, xf);
         }
      }
   }
   if(!rds.isEmpty()){
      var c = rds.count;
      for(var n=0; n<c; n++){
         var rd = rds.value(n);
         if('/' == rd.name){
            g.resultRow = rd.row(0);
            break;
         }
      }
   }
   g.invoke();
}
function FDatasetConsole_onUpdateLoaded(g, x){
   var o = this;
   var xf = x.find('Form');
   if(!xf){
      return;
   }
   var fd = xf.get('id');
   var xd = xf.find('Dataset');
   if(!xd){
      return;
   }
   var ds = g.resultDataset = o.loadDataset(fd, xd);
   g.resultRow = ds.row(0);
   g.invoke();
   RWindow.setEnable(true);
}
function FDatasetConsole_onLoaded(e){
   var o = this;
   var r = e.document.root();
   var g = e.argument;
   if(!e.messageChecked){
      var m = new TMessageArg();
      m.argument = g;
      m.form = g.form;
      m.config = r;
      m.invokeCaller = new TInvoke(o, o.onLoaded);
      m.invokeParam = e;
      m.event = e;
      if(!RConsole.find(FMessageConsole).checkResult(m)){
         return;
      }
   }
   g.configResult = r;
   switch(e.action){
      case EDataAction.Fetch:
         o.onFetchLoaded(g, r);
         break;
      case EDataAction.Prepare:
         o.onPrepareLoaded(g, r);
         break;
      case EDataAction.Update:
         o.onUpdateLoaded(g, r);
         break;
      case EDataAction.Lov:
         o.onLovLoaded(g, r);
         break;
      case EDataAction.Scalar:
         o.onScalarLoaded(g, r);
         break;
      case EDataAction.Complete:
         o.onCompleteLoaded(g, r);
         break;
   }
   RConsole.find(FListenerConsole).process(MDataset, EAction.Changed, e, e)
}
function FDatasetConsole_construct(){
   var o = this;
   o.datasets = new TMap();
   o.lsnsLoaded      = new TListeners();
   o.lsnsUpdateBegin = new TListeners();
   o.lsnsUpdateEnd   = new TListeners();
}
function FDatasetConsole_fetch(g){
   var o = this;
   var doc = new TXmlDocument();
   var root = doc.root();
   root.set('action', 'fetch');
   root.push(g.toNode());
   var e = new TEvent(o, EXmlEvent.Send, o.onLoaded);
   e.url = RService.url('logic.webform.dataset');
   e.action = EDataAction.Fetch;
   e.argument = g;
   e.document = doc;
   RConsole.find(FXmlConsole).process(e);
}
function FDatasetConsole_complete(g){
   var o = this;
   var doc = new TXmlDocument();
   var root = doc.root();
   root.set('action', 'complete');
   root.push(g.toNode());
   var e = new TEvent(o, EXmlEvent.Send, o.onLoaded);
   e.url = RService.url('logic.webform.dataset');
   e.action = EDataAction.Complete;
   e.argument = g;
   e.document = doc;
   RConsole.find(FXmlConsole).process(e);
}
function FDatasetConsole_lov(g){
   var o = this;
   var doc = new TXmlDocument();
   var root = doc.root();
   root.set('action', 'lov');
   root.push(g.toNode());
   var e = new TEvent(o, EXmlEvent.Send, o.onLoaded);
   e.url = RService.url('logic.webform.dataset');
   e.action = EDataAction.Lov;
   e.argument = g;
   e.document = doc;
   RConsole.find(FXmlConsole).process(e);
}
function FDatasetConsole_prepare(g){
   var o = this;
   var doc = new TXmlDocument();
   var root = doc.root();
   root.set('action', 'prepare');
   root.push(g.toNode());
   var e = new TEvent(o, EXmlEvent.Send, o.onLoaded);
   e.url = RService.url('logic.webform.dataset');
   e.action = EDataAction.Prepare;
   e.argument = g;
   e.document = doc;
   RConsole.find(FXmlConsole).process(e);
}
function FDatasetConsole_update(g){
   var o = this;
   var doc = new TXmlDocument();
   var root = doc.root();
   root.set('action', 'update');
   if(g.checked){
      root.set('checked', g.checked);
   }
   root.push(g.toNode());
   var e = new TEvent(o, EXmlEvent.Send, o.onLoaded);
   e.url = RService.url('logic.webform.dataset');
   e.action = EDataAction.Update;
   e.argument = g;
   e.document = doc;
   RConsole.find(FXmlConsole).process(e);
}
function FDatasetConsole_get(id){
   var o = this;
   var ds = o.forms.get(id);
   return ds;
}
function FDatasetConsole_getById(id){
   var o = this;
   var d = o.datasets.get(id);
   return d;
}
function FDatasetConsole_getByPath(formId, path){
   var o = this;
   var ds = o.get(formId);
   return ds ? ds.get(path) : null;
}
function FDatasetConsole_onTreeLoaded(g){
   var o = this;
   alert(1);
}
function FDatasetConsole_onColumnFetch(e){
   var o = this;
   var root = e.document.root();
   var mc = RConsole.find(FMessageConsole);
   var r = mc.checkResult(root);
   if(r){
      var g = e.arg;
      if(root.hasNode()){
         var fs = root.nodes;
         var ct = fs.count;
         for(var k = 0; k < ct; k++){
            var f = fs.get(k);
            if(f.hasNode()){
               var ns = f.nodes;
               var nt = ns.count;
               for( n = 0; n < nt; n++){
                  var d = ns.get(n);
                  if(d.name == 'Data'){
                     g.resultConfig = d;
                     break;
                  }
               }
            }
         }
      }
      g.invoke();
   }
}
function FDatasetConsole_columnNodeFetch(g){
   var o = this;
   var doc = new TXmlDocument();
   var root = doc.root();
   root.set('action', g.action);
   var nd = g.toNode();
   root.push(nd);
   var url = RService.url(g.service);
   var e = new TEvent(o, EXmlEvent.Send, o.onColumnFetch);
   e.url = url;
   e.document = doc;
   e.arg = g;
   e.action = EDataAction.Fetch;
   RConsole.find(FXmlConsole).process(e);
}
function FDatasetConsole_treeUpdate(g){
   var o = this;
   var doc = new TXmlDocument();
   var root = doc.root();
   root.set('action', g.action);
   var nd = g.toNode();
   root.push(nd);
   var url = RService.url(g.service);
   var e = new TEvent(o, EXmlEvent.Send, o.onTreeLoaded);
   e.url = url;
   e.document = doc;
   e.arg = g;
   e.action = EDataAction.TreeUpdate;
   RConsole.find(FXmlConsole).process(e);
}
function FDateConsole(o){
   o = RClass.inherits(this, o, FConsole);
   o.scope       = EScope.Page;
   o.dateDiffer  = 0;
   o.serverDate  = null;
   o.currentDate = FDateConsole_currentDate;
   o.setDate     = FDateConsole_setDate;
   return o;
}
function FDateConsole_currentDate(){
   var o = this;
   var t = new TDate();
   t.addMseconds(o.dateDiffer);
   return t;
}
function FDateConsole_setDate(d){
   var o = this;
   o.serverDate = d;
}
function FDesignConsole(o){
   o = RClass.inherits(this, o, FConsole);
   o.scope          = EScope.Page;
   o.padWidth       = 2;
   o.padHeight      = 4;
   o.layerSplit     = 10000;
   o.layerControl   = 20000;
   o.onDragStart    = RClass.register(o, new HMouseDown('onDragStart'), FDesignConsole_onDragStart);
   o.onDrag         = RClass.register(o, new HMouseMove('onDrag'), FDesignConsole_onDrag);
   o.onDragStop     = RClass.register(o, new HMouseUp('onDragStop'), FDesignConsole_onDragStop);
   o._moveable      = false;
   o.mode           = null;
   o.active         = null;
   o.hoverControl   = null;
   o.hoverContainer = null;
   o.moveControl    = null;
   o.activeControl  = null;
   o.activeLnRects  = new TList();
   o.actLineCtls    = new TList();
   o.activeLines    = new TList();
   o.actRect        = null;
   o.activeRects    = new TList();
   o.posControl     = null;
   o.actControls    = new TList();
   o.actContainer   = null;
   o.clickPos       = new TPoint();
   o.movePos        = new TPoint();
   o.movePosOrg     = new TPoint();
   o.splitRect      = new TRect();
   o.parentPosPanel = null;
   o.parentPos      = new TPoint();
   o.controlPos     = new TPoint();
   o.focusConsole   = null;
   o.controls       = new Array();
   o.hSplit         = null;
   o.hSplitPanel    = null;
   o.onInterval     = FDesignConsole_onInterval;
   o.construct      = FDesignConsole_construct;
   o.createMove     = FDesignConsole_createMove;
   o.hover          = FDesignConsole_hover;
   o.leave          = FDesignConsole_leave;
   o.design         = FDesignConsole_design;
   o.startDrag      = FDesignConsole_startDrag;
   o.drag           = FDesignConsole_drag;
   o.stopDrag       = FDesignConsole_stopDrag;
   o.showSplit      = FDesignConsole_showSplit;
   o.setFlag        = FDesignConsole_setFlag;
   o.isDesign       = FDesignConsole_isDesign;
   return o;
}
function FDesignConsole_isDesign(){
   return this._moveable;
}
function FDesignConsole_onDragStart(s){
   if(!s.isDraging){
      this.startDrag(s);
   }
}
function FDesignConsole_onDrag(s, e){
   var p = this.movePos
   p.x = e.clientX;
   p.y = e.clientY;
}
function FDesignConsole_onDragStop(s, e){
   this.stopDrag(s, false, e.ctrlKey);
}
function FDesignConsole_onInterval(){
   var o = this;
   var c = o.moveControl;
   if(c && !o.movePosOrg.equals(o.movePos)){
      o.movePosOrg.assign(o.movePos);
      if(RClass.isClass(c, MHorizontal)){
         c.setBounds(o.controlPos.x, o.movePos.y - o.clickPos.y-o.parentPos.y);
      }else{
         c.setBounds(o.movePos.x-o.parentPos.x-2, o.movePos.y-o.parentPos.y-2);
      }
      o.showSplit(o.movePos.x, o.movePos.y);
      RHtml.setPixelBounds(o.hSplit, o.splitRect.left-o.parentPos.x, o.splitRect.top-o.parentPos.y, o.splitRect.width(), o.splitRect.height());
   }
}
function FDesignConsole_construct(){
   var o = this;
   o.base.FConsole.construct.call(o);
   RWindow.lsnsMouseDown.register(o, o.design);
   RWindow.lsnsMouseOver.register(o, o.hover);
   RWindow.lsnsKeyUp.register(o, o.leave);
   o.active = new TActive(o, o.onInterval);
   RLogger.debug(o.active, 'Push active object');
   o.active.interval = 10;
   o.active.status = EActive.Sleep;
   RConsole.find(FActiveConsole).push(o.active);
   o.hSplit = RBuilder.append(o.hSplitPanel, 'SPAN', 'FDesignConsole_Split');
   o.hSplit.zIndex = o.layerSplit;
   o.hSplit.style.display = 'none';
   RBuilder.appendEmpty(o.hSplit);
   o.focusConsole = RConsole.find(FFocusConsole);
}
function FDesignConsole_createMove(c){
   var o = this;
   var n = RClass.name(c) + c.name;
   var r = o.controls[n];
   if(!r){
      r = RClass.create(c.constructor);
      r.assign(c, EAssign.Property);
      r.psBuild();
      RWindow.appendControl(r);
      var h = r.panel(EPanel.Move);
      RControl.linkEvent(o, r, 'onDragStart', h);
      RControl.linkEvent(o, r, 'onDrag', h);
      RControl.linkEvent(o, r, 'onDragStop', h);
      o.controls[n] = r;
   }
   var hc = c.panel(EPanel.Move);
   var hr = r.panel(EPanel.Move);
   RHtml.setPixelRect(hr, RHtml.rect(hc));
   hr.className = r.style('DesignMove');
   r.show();
   return r;
}
function FDesignConsole_hover(){
   var o = this;
   if(!o.activeControl){
      var cnt = o.focusConsole.hoverContainer;
      var ctl = o.focusConsole.hoverControl;
   }
}
function FDesignConsole_leave(){
   var o = this;
   if(o.mode == EDesign.Move && !event.ctrlKey){
      RLog.debug(o, 'Design move leave (container={0}, control={1})', RClass.dump(o.hoverContainer), RClass.dump(o.activeControl));
      if(o.activeControl){
         o.stopDrag(o.activeControl, true);
      }else if(o.hoverControl){
         o.hoverControl.onDesignLeave();
         o.hoverControl = null;
      }
      if(o.hoverContainer){
         o.hoverContainer.psDesign(EDesign.Move, false);
         o.hoverContainer = null;
      }
   }
   if(o.mode == EDesign.Border && !event.ctrlKey && !event.shiftKey){
      if(o.hoverContainer){
         o.hoverContainer.psDesign(EDesign.Border, false);
         o.hoverContainer = null;
      }
   }
}
function FDesignConsole_design(){
   var o = this;
   if(o._moveable){
      var e = RWindow.event();
      var ctl = RControl.htmlControl(e.srcElement, MDesign);
      if(ctl){
         var cnt = ctl.topControl(FContainer);
         if(ctl && cnt){
            RLog.debug(o, 'Design control (container={0}, control={1})', RClass.dump(cnt), RClass.dump(ctl));
            o.activeControl = ctl;
            o.actContainer = cnt;
            o.startDrag(cnt, ctl);
         }
      }
   }
}
function FDesignConsole_startDrag(cnt, ctl){
   var o = this;
   var hp = o.actContainer.panel(EPanel.Container);
   if(o.hSplitPanel != hp){
      o.hSplitPanel = hp;
      o.hSplitPanel.appendChild(o.hSplit);
   }
   var mc = o.moveControl = o.createMove(ctl);
   var mp = mc.panel(EPanel.Move);
   var h = ctl.hPanel;
   o.parentPosPanel = RHtml.posParent(h);
   RHtml.toPoint(o.parentPos, o.parentPosPanel);
   RHtml.toPoint(o.controlPos, h, o.parentPosPanel);
   var cp = RWindow.clientPos();
   var mr = ctl.calcRect();
   h.zIndex = o.layerControl;
   h.width = mr.width();
   RHtml.setRect(h, mr);
   o.clickPos.set(cp.x - mr.left, cp.y - mr.top);
   o.movePos.assign(cp);
   o.actRect = o.actContainer.calcRect();
   RLogger.debug(o, 'StartDrag (rect={0}, control={1}, parent={2}:{3})', o.actRect.dump(), o.controlPos.dump(), o.parentPos.dump(), o.parentPosPanel.currentStyle.overflow);
   var cs = o.actContainer.controls;
   var ar = o.activeRects;
   ar.clear();
   for(var n=0; n<cs.count; n++){
      var c = cs.value(n);
      var r = RHtml.rect(c.panel(EPanel.Design));
      if(!RClass.isClass(c, MHorizontal)){
         var pr = RHtml.rect(c.hPanelLine);
         r.top = pr.top;
         r.bottom = pr.bottom;
      }
      r.control = c;
      ar.push(r);
   }
   var al = o.activeLines;
   al.clear();
   var lastLine = 0;
   for(var n=0; n<ar.count; n++){
      var r = ar.get(n);
      if(!al.contains(r.top)){
         al.push(r.top);
      }
      lastLine = Math.max(r.bottom, lastLine);
   }
   al.push(lastLine+3);
   o.hSplit.style.display = 'block';
   mp.setCapture();
   RLogger.debug(o, 'rect={0}, control={1}, parent={2}:{3}', o.movePos.dump(), o.splitRect.left-o.parentPos.x, o.splitRect.top-o.parentPos.y, o.splitRect.width(), o.splitRect.height());
   ctl.onDesignBegin();
   o.active.status = EActive.Active;
   return;
}
function FDesignConsole_drag(ctl){
}
function FDesignConsole_stopDrag(ctl, cancel, copy){
   var o = this;
   var h = ctl.hPanel;
   o.active.status = EActive.Sleep;
   ctl.onDesignEnd();
   o.hSplit.style.display = 'none';
   if(!cancel){
      o.actContainer.moveChild(o.activeControl, o.posControl, o.posAction, copy);
   }
   o.moveControl.panel(EPanel.Move).releaseCapture();
   o.moveControl.hide();
   o.moveControl = null;
   o.activeControl.onDesignEnd();
   o.activeControl = null;
   o.actContainer = null;
}
function FDesignConsole_showSplit(x, y){
   var o = this;
   var cs = o.actContainer.controls;
   if(cs){
      var sr = o.splitRect;
      var vpos = RClass.isClass(this.activeControl, MHorizontal);
      for(var n=0; n<o.activeLines.count; n++){
         var lc = o.activeLines.get(n);
         var ln = o.activeLines.get(n+1);
         if(y > lc-o.padHeight && y < lc+o.padHeight){
            sr.setBounds(o.actRect.left, lc-2, o.actRect.width(), 3);
            if(n == o.activeLines.count-1){
               o.posAction = EPosition.LineAfter;
               o.posControl = o.activeRects.last().control;
               return;
            }
            for(var i=0; i<o.activeRects.count; i++){
               var r = o.activeRects.get(i);
               if(r.top == lc){
                  o.posAction = EPosition.LineBefore;
                  o.posControl = r.control;
                  return;
               }
            }
            return;
         }else if(!vpos && y > lc+o.padHeight && y < ln-o.padHeight){
            var first = true;
            var alr = o.activeLnRects;
            alr.clear();
            var rcount = o.activeRects.count;
            for(var i=0; i<rcount; i++){
               var r = o.activeRects.get(i);
               if(r.top == lc && !RClass.isClass(r.control, MHorizontal)){
                  o.activeLnRects.push(r);
               }
            }
            var rcount = o.activeLnRects.count;
            for(var i=0; i<rcount; i++){
               var r = o.activeLnRects.get(i);
               if(x < r.right){
                  o.posAction = EPosition.Before;
                  o.posControl = r.control;
                  sr.setBounds(r.left-2, r.top-1, 3, r.height());
                  return;
               }else if(i == rcount-1){
                  o.posAction = EPosition.After;
                  o.posControl = r.control;
                  sr.setBounds(r.right-2, r.top-1, 3, r.height());
                  return;
               }
            }
            return;
         }
      }
   }
}
function FDesignConsole_setFlag(flag, value){
   if(EDesign.Move == flag){
      this._moveable = value;
   }
}
function FEditConsole(o){
   o = RClass.inherits(this, o, FConsole);
   o.scope        = EScope.Page;
   o.focusEditor  = null;
   o.editors      = new TMap();
   o.makeName     = FEditConsole_makeName;
   o.enter        = FEditConsole_enter;
   o.leave        = FEditConsole_leave;
   o.focus        = FEditConsole_focus;
   o.blur         = FEditConsole_blur;
   o.lost         = FEditConsole_lost;
   return o;
}
function FEditConsole_makeName(cls, name){
   return name ? name + '@' + RClass.name(cls) : RClass.name(cls);
}
function FEditConsole_enter(editable, cls){
   var name = RClass.name(cls);
   var editor = this.hoverEditors.get(name);
   if(!editor){
      editor = RClass.create(cls);
      editor.psBuild();
      this.hoverEditors.set(name, editor);
   }
   this.hoverEditor = editor;
   editor.editable = editable;
   editor.show();
   return editor;
}
function FEditConsole_leave(editor){
   var o = this;
   if(o.hoverEditor != o.focusEditor){
      editor = RObject.nvl(editor, o.hoverEditor);
      o.hoverEditor = null;
      RLog.debug(o, 'Leave {0}', RClass.dump(editor));
   }
}
function FEditConsole_focus(c, n, l){
   var o = this;
   var name = o.makeName(n, l);
   var e = o.editors.get(l);
   if(!e){
      e = RClass.create(n);
      e.psBuild();
      o.editors.set(l, e);
   }
   RLogger.debug(o, 'Focus editor {0} (editable={1}, name={2})', RClass.dump(e), RClass.dump(c), l);
   e.reset();
   if(RClass.isClass(e, FDropEditor)){
      e.linkControl(c);
      o.focusEditor = e;
   }
   return e;
}
function FEditConsole_blur(editor){
   var o = this;
   if(o.focusEditor){
      RLogger.debug(o, 'Blur {0}', RClass.dump(editor));
      editor = RObject.nvl(editor, o.focusEditor);
      if(editor){
         editor.onEditEnd();
      }
      o.focusEditor = null;
   }
}
function FEditConsole_lost(e){
   var o = this;
   o.leave(e);
   o.blur(e);
}
function FEnvConsole(o){
   o = RClass.inherits(this, o, FConsole);
   o.scope       = EScope.Page;
   o.environment = null;
   o.connect     = FEnvConsole_connect;
   o.build       = FEnvConsole_build;
   o.buildValue  = FEnvConsole_buildValue;
   o.xml         = FEnvConsole_xml;
   return o;
}
function FEnvConsole_connect(){
   var xData = RHtml.get('xEnvironment');
   if(xData){
      this.environment = RXml.makeNode(xData);
   }
}
function FEnvConsole_build(config){
   if(!this.environment){
      this.connect()
   }
   if(this.environment){
      var node = config.create('Environment');
      node.attributes().append(this.environment.attributes());
   }
}
function FEnvConsole_buildValue(){
   if(!this.environment){
      this.connect()
   }
   if(this.environment){
      var env = RHtml.get('_environment');
      if(env){
         env.value = this.environment.xml();
      }
   }
}
function FEnvConsole_xml(){
   if(!this.environment){
      this.connect()
   }
   if(this.environment){
      return this.environment.xml();
   }
   return null;
}
function FEventConsole(o){
   o = RClass.inherits(this, o, FConsole);
   o.scope      = EScope.Page;
   o.active     = null;
   o.allow      = true;
   o.allows     = new TMap();
   o.events     = new TList();
   o.listeners  = new TMap();
   o.construct  = FEventConsole_construct;
   o.register   = FEventConsole_register;
   o.process    = FEventConsole_process;
   o.add        = FEventConsole_add;
   o.push       = FEventConsole_push;
   o.allowEvent = FEventConsole_allowEvent;
   o.skipEvent  = FEventConsole_skipEvent;
   o.allowAll   = FEventConsole_allowAll;
   o.skipAll    = FEventConsole_skipAll;
   o.onlyCall   = FEventConsole_onlyCall;
   o.clear      = FEventConsole_clear;
   return o;
}
function FEventConsole_construct(){
   var o = this;
   o.base.FConsole.construct.call(o);
   o.active = new TActive(o, o.process);
   o.active.interval = 1;
   RConsole.find(FActiveConsole).push(o.active);
   RLogger.debug(o, 'Add event active (active={0})', RClass.dump(o.active));
}
function FEventConsole_register(owner, proc){
   this.events.push(new TEvent(owner, null, proc));
}
function FEventConsole_process(){
   var o = this;
   var es = o.events;
   if(es.count){
      var sp = new TSpeed(o, 'Process events (count={0})', es.count);
      while(true){
         var has = false;
         for(var n=0; n<es.count; n++){
            var e = es.get(n);
            if(e){
               has = true;
               e.process();
               var ls = o.listeners.get(RMethod.name(e));
               if(ls){
                  ls.process(e);
               }
               es.set(n, null)
            }
         }
         if(!has){
            break;
         }
      }
      sp.record();
      es.clear();
   }
}
function FEventConsole_add(owner, proc){
   this.events.push(new TEvent(owner, null, proc));
}
function FEventConsole_push(e){
   var o = this;
   var n = RClass.name(e)
   if(o.allow){
      var a = true;
      if(o.allows.contains(n)){
         a = RBool.isTrue(o.allows.get(n));
      }
      if(a){
         var c = o.events.count;
         for(var n=0; n<c; n++){
            if(o.events.get(n) == e){
               o.events.set(n, null);
            }
         }
         o.events.push(e);
      }
   }
}
function FEventConsole_allowEvent(c){
   this.allows.set(RMethod.name(c), EBool.True);
}
function FEventConsole_skipEvent(c){
   this.allows.set(RMethod.name(c), EBool.False);
}
function FEventConsole_allowAll(){
   this.allow = true;
}
function FEventConsole_skipAll(){
   this.allow = false;
}
function FEventConsole_onlyCall(c, m){
   var o = this;
   o.allow = false;
   m.call(c);
   o.allow = true;
}
function FEventConsole_clear(){
   this.events.clear();
}
function FEventEngineConsole(o){
   o = RClass.inherits(this, o, FConsole);
   o.scope      = EScope.Page;
   o.events     = new TMap();
   o.loadConfig = FEventEngineConsole_loadConfig;
   o.process    = FEventEngineConsole_process;
   return o;
}
function FEventEngineConsole_loadConfig(cfg){
   var o = this;
   if(!(cfg && cfg.nodes)){
      return;
   }
   var ns = cfg.nodes;
   var l = ns.count;
   for(var n = 0; n < l; n++){
      var x = ns.get(n);
      if(x.isName('Event')){
         var c = RClass.create(FEvent);
         c.loadConfig(x);
         if(RString.isEmpty(c.name) || RString.isEmpty(c.source) || RString.isEmpty(c.form)){
            RMessage.fatel(o, null, "Event property is invalid. (event={0})", x.xml());
         }
         var s = c.name + '@' + c.source + '@' + c.form;
         o.events.set(s, c);
      }
   }
}
function FEventEngineConsole_process(e){
   var o = this;
   var es = o.events;
   if(es.isEmpty()){
      return;
   }
   var se = e.source;
   if(RClass.isClass(se, FControl)){
      var p = se.topControl(MDataset);
      if(p){
         var s = e.name + '@' + se.name + '@' + p.name;
         var c = es.get(s);
         if(c && c.code){
            if(c.event){
               c.event.call(se);
            }else{
               c.event = new Function(c.code);
               c.event.call(se);
            }
         }
      }
   }
}
function FFocusConsole(o){
   o = RClass.inherits(this, o, FConsole);
   o.scope              = EScope.Page;
   o.__blurAble         = true;
   o.__focusAble        = true;
   o.__focusClasses     = null;
   o.__storeControl     = null;
   o.hoverContainer     = null;
   o.hoverControl       = null;
   o.focusControl       = null;
   o.blurControl        = null;
   o.activeControl      = null;
   o.lsnsFocus          = null;
   o.lsnsBlur           = null;
   o.lsnsFocusClass     = null;
   o.onWindowMouseDown  = FFocusConsole_onWindowMouseDown;
   o.onWindowMouseWheel = FFocusConsole_onWindowMouseWheel;
   o.construct          = FFocusConsole_construct;
   o.isFocus            = FFocusConsole_isFocus;
   o.enter              = FFocusConsole_enter;
   o.leave              = FFocusConsole_leave;
   o.focus              = FFocusConsole_focus;
   o.blur               = FFocusConsole_blur;
   o.findClass          = FFocusConsole_findClass;
   o.focusClass         = FFocusConsole_focusClass;
   o.focusHtml          = FFocusConsole_focusHtml;
   o.lockBlur           = FFocusConsole_lockBlur;
   o.unlockBlur         = FFocusConsole_unlockBlur;
   o.storeFocus         = FFocusConsole_storeFocus;
   o.restoreFocus       = FFocusConsole_restoreFocus;
   o.dispose            = FFocusConsole_dispose;
   return o;
}
function FFocusConsole_onWindowMouseDown(s, e){
   this.focusHtml(e);
}
function FFocusConsole_onWindowMouseWheel(s, e){
   var o = this;
   var fc = this.focusControl;
   if(RClass.isClass(fc, MMouseWheel)){
      fc.onMouseWheel(s, e);
   }
}
function FFocusConsole_construct(){
   var o = this;
   o.base.FConsole.construct.call(o);
   o.__focusClasses = new Object();
   o.lsnsFocus = new TListeners();
   o.lsnsBlur = new TListeners();
   o.lsnsFocusClass = new TListeners();
   RLogger.debug(o, 'Add listener for window mouse down and wheel');
   RWindow.lsnsMouseDown.register(o, o.onWindowMouseDown);
   RWindow.lsnsMouseWheel.register(o, o.onWindowMouseWheel);
}
function FFocusConsole_isFocus(c){
   return (this.focusControl == c);
}
function FFocusConsole_enter(c){
   var o = this;
   if(RClass.isClass(c, MContainer)){
      o.hoverContainer = c;
   }else{
      o.hoverControl = c;
   }
}
function FFocusConsole_leave(c){
   var o = this;
   if(o.hoverContainer == c){
      o.hoverContainer = null;
   }
   if(o.hoverControl == c){
      o.hoverControl = null;
   }
}
function FFocusConsole_focus(c, e){
   var o = this;
   if(!RClass.isClass(c, MFocus)){
      return;
   }
   var f = o.focusControl;
   if(f == c){
      return;
   }
   var bc = o.blurControl;
   if(bc != f){
      if(o.__blurAble && f && f.testBlur(c)){
         RLogger.debug(o, 'Blur focus control. (name={1}, instance={2})', f.name, RClass.dump(f));
         o.blurControl = f;
         f.doBlur(e);
         o.lsnsBlur.process(f);
      }
   }
   if(o.__focusAble){
      RLogger.debug(o, 'Focus control. (name={1}, instance={2})', c.name, RClass.dump(c));
      c.doFocus(e);
      o.focusControl = o.activeControl = c;
      o.lsnsFocus.process(c);
   }
}
function FFocusConsole_blur(c, e){
   var o = this;
   var fc = o.focusControl;
   var bc = o.blurControl;
   if(fc && c && !fc.testBlur(c)){
      return;
   }
   if(bc != c && RClass.isClass(c, MFocus)){
      RLogger.debug(o, 'Blur control. (name={1}, instance={2})', c.name, RClass.dump(c));
      o.blurControl = c;
      c.doBlur(e);
   }
   if(fc){
      RLogger.debug(o, 'Blur focus control. (name={1}, instance={2})', fc.name, RClass.dump(fc));
      fc.doBlur(e);
      o.focusControl = null;
   }
}
function FFocusConsole_findClass(c){
   var o = this;
   var n = RClass.name(c);
   if(o.__focusClasses[n]){
      return o.__focusClasses[n];
   }
   var p = o.activeControl;
   if(RClass.isClass(p, FEditor)){
      p = p.source;
   }
   if(p){
      return p.topControl(c);
   }
}
function FFocusConsole_focusClass(c, p){
   var o = this;
   var n = RClass.name(c);
   if(o.__focusClasses[n] != p){
      o.__focusClasses[n] = p;
      RLogger.debug(o, 'Focus class. (name={1}, class={2})', n, RClass.dump(p));
      o.lsnsFocusClass.process(p, c);
   }
}
function FFocusConsole_focusHtml(he){
   var o = this;
   var c = RControl.htmlControl(he.srcElement);
   RLogger.debug(o, 'Focus html control. (control={1},element={2})', RClass.dump(c), he.srcElement.tagName);
   if(c){
      if(o.focusControl != c){
         o.blur(c, he);
      }
   }else{
      o.blur(null, he);
   }
}
function FFocusConsole_lockBlur(){
   this.__blurAble = false;
}
function FFocusConsole_unlockBlur(){
   this.__blurAble = true;
}
function FFocusConsole_storeFocus(){
   var o = this;
   o.__storeControl = o.focusControl;
}
function FFocusConsole_restoreFocus(){
   var o = this;
   if(o.__storeControl){
      o.__storeControl.focus();
      o.__storeControl = null;
   }
}
function FFocusConsole_dispose(){
   var o = this;
   o.base.FConsole.dispose.call(o);
   o.__focusClasses = null;
}
function FFormConsole(o){
   o = RClass.inherits(this, o, FConsole);
   o.scope            = EScope.Page;
   o.forms            = null;
   o.freeForms        = null;
   o.formsLoaded      = null;
   o.formIds          = null;
   o.lsnsLoaded       = null;
   o.events           = null;
   o.onProcessLoaded  = FFormConsole_onProcessLoaded;
   o.construct        = FFormConsole_construct;
   o.createFromName   = FFormConsole_createFromName;
   o.get              = FFormConsole_get;
   o.find             = FFormConsole_find;
   o.hiddenAll        = FFormConsole_hiddenAll;
   o.process          = FFormConsole_process;
   o.loadEvents       = FFormConsole_loadEvents;
   o.processEvent     = FFormConsole_processEvent;
   o.free             = FFormConsole_free;
   o.dispose          = FFormConsole_dispose;
   return o;
}
function FFormConsole_construct(){
   var o = this;
   o.forms = new TMap();
   o.formIds = new TMap();
   o.formsLoaded = new TMap();
   o.lsnsLoaded = new TListeners();
   o.freeForms = new TList();
   o.events = new TMap();
}
function FFormConsole_createFromName(n, h, b, t){
   var o = this;
   var fs = o.freeForms;
   if(!fs.isEmpty()){
      var c = fs.count;
      for(var i=0; i<c; i++){
         if(fs.get(i).name == n){
            var f = fs.remove(i);
            f.setPanel(h);
            return f;
         }
      }
   }
   var fdc = RConsole.find(FFormDefineConsole);
   var fx = fdc.find(n, t);
   var fd = t + ':' + n;
   if(!o.formsLoaded.contains(fd)){
      var es = fdc.getEvents(n);
      if(es){
         o.loadEvents(es);
      }
      o.formsLoaded.set(fd, true);
   }
   var c = RClass.create('F' + fx.name);
   RControl.innerCreate(c, fx);
   c.psInitialize();
   if(!b){
      b = RWindow.builder();
   }
   c.psBuild(h, b);
   c.dsInitialize();
   c.setVisible(false);
   c.formId = fdc.nextFormId();
   o.formIds.set(c.formId, c);
   o.forms.set(n, c);
   return c;
}
function FFormConsole_get(id){
   return o.formIds.get(id);
}
function FFormConsole_find(n, h, b){
   var o = this;
   var f = o.forms.get(n);
   if(!f){
      f = o.createFromName(n, h, b);
   }
   return f;
}
function FFormConsole_hiddenAll(){
   var o = this;
   var fs = o.forms;
   var fc = fs.count;
   for(var n=0; n<fc; n++){
      fs.value(n).setVisible(false);
   }
}
function FFormConsole_onProcessLoaded(e){
   var o = this;
   var r = e.document.root();
   var g = e.argument;
   if(!e.messageChecked){
      var m = new TMessageArg();
      m.argument = g;
      m.form = g.form;
      m.config = r;
      m.invokeCaller = new TInvoke(o, o.onLoaded);
      m.invokeParam = e;
      m.event = e;
      if(!RConsole.find(FMessageConsole).checkResult(m)){
         return;
      }
   }
   var g = e.argument;
   var fn = r.find('Form');
   if(fn){
      var ds = RDataset.make(fn);
      g.resultDataset = ds;
      g.resultRow = ds.rows.get(0);
   }
   g.invoke();
}
function FFormConsole_process(g){
   var o = this;
   var doc = new TXmlDocument();
   var root = doc.root();
   root.set('action', 'process');
   if(g.checked){
      root.set('checked', g.checked);
   }
   root.push(g.toNode());
   var e = new TEvent(o, EXmlEvent.Send, o.onProcessLoaded);
   e.url = RService.url(RString.nvl(g.url, 'logic.webform'));
   e.action = EDataAction.Process;
   e.argument = g;
   e.document = doc;
   RConsole.find(FXmlConsole).process(e);
}
function FFormConsole_loadEvents(cfg){
   return;
   var o = this;
   if(!(cfg && cfg.nodes)){
      return;
   }
   var ns = cfg.nodes;
   var l = ns.count;
   for(var n = 0; n < l; n++){
      var x = ns.get(n);
      if(x.isName('Event')){
         var c = RClass.create(FEvent);
         c.loadConfig(x);
         if(RString.isEmpty(c.name) || RString.isEmpty(c.source) || RString.isEmpty(c.form)){
            RMessage.fatel(o, null, "Event property is invalid. (event={0})", x.xml());
         }
         var s = c.name + '@' + c.source + '@' + c.form;
         o.events.set(s, c);
      }
   }
}
function FFormConsole_processEvent(e){
   var o = this;
   var es = o.events;
   if(es.isEmpty()){
      return;
   }
   var se = e.source;
   if(RClass.isClass(se, FControl)){
      var p = se.topControl();
      if(p){
         var s = RString.nvl(e.name, e.handle) + '@' + se.name + '@' + p.name;
         var c = es.get(s);
         var eo = e.caller ? e.caller : se;
         if(c && c.code){
            if(c.event){
               c.event.call(eo, eo, e);
            }else{
               c.event = new Function('o', 'e', c.code);
                  c.event.call(eo, eo, e);
            }
         }
      }
   }
}
function FFormConsole_free(f){
   f.setVisible(false);
   this.freeForms.push(f);
}
function FFormConsole_dispose(){
   var o = this;
   RMemory.free(o.forms);
   RMemory.free(o.formIds);
   RMemory.free(o.formsLoaded);
   o.forms = null;
   o.formIds = null;
   o.formsLoaded = null;
}
function FFormDefineConsole(o){
   o = RClass.inherits(this, o, FConsole);
   o.scope          = EScope.Global;
   o.defines        = null;
   o.events         = null;
   o.lsnsLoaded     = null;
   o.formId         = 0;
   o.construct      = FFormDefineConsole_construct;
   o.createFromName = FFormDefineConsole_createFromName;
   o.loadNode       = FFormDefineConsole_loadNode;
   o.loadService    = FFormDefineConsole_loadService;
   o.nextFormId     = FFormDefineConsole_nextFormId;
   o.get            = FFormDefineConsole_get;
   o.find           = FFormDefineConsole_find;
   o.getLov         = FFormDefineConsole_getLov;
   o.findLov        = FFormDefineConsole_findLov;
   o.getEvents      = FFormDefineConsole_getEvents;
   return o;
}
function FFormDefineConsole_construct(){
   var o = this;
   o.defines = new TMap();
   o.defines.set(EForm.Form, new TMap());
   o.defines.set(EForm.Lov, new TMap());
   o.events = new TMap();
   o.lsnsLoaded = new TListeners();
}
function FFormDefineConsole_createFromName(name, type){
   var o = this;
   var doc = o.loadService(name, type);
   o.loadNode(doc);
   if(EForm.Lov == type){
      return o.getLov(name);
   }else{
      return o.get(name);
   }
}
function FFormDefineConsole_loadNode(x){
   var o = this;
   var nns = x.root();
   if(nns.hasNode()){
      var nodes = nns.nodes;
      var ct = nodes.count;
      for(var n = 0; n < ct; n++){
         var node = nodes.get(n);
         var fn = node.get('name');
         var tp = node.get('type');
         if(node.hasNode()){
            var nfds = node.nodes;
            for(var k = 0; k < nfds.count; k++){
               var dd = nfds.get(k);
               if(dd.isName('Define')){
                  if(dd.hasNode()){
                     var fds = dd.nodes;
                     for(var m = 0; m < fds.count; m++){
                        var nd = fds.get(m);
                        var mp = o.defines.get(tp);
                        mp.set(fn, nd);
                     }
                  }
               }else if(dd.isName('Events')){
                  o.events.set(fn, dd);
               }
            }
         }
      }
   }
}
function FFormDefineConsole_loadService(n, t){
   var o = this;
   if(!t){
      t = EForm.Form;
   }
   var doc = new TXmlDocument();
   var root = doc.root();
   root.set('action', 'loadDefine');
   var f = root.create('WebForm');
   f.set('name', n);
   f.set('type', t);
   var url = RService.url('logic.webform');
   var doc = RConsole.find(FXmlConsole).send(url, doc);
   var r = doc.root();
   if(!RConsole.find(FMessageConsole).checkResult(new TMessageArg(r))){
      return null;
   }
   return doc;
}
function FFormDefineConsole_nextFormId(){
   return ++this.formId;
}
function FFormDefineConsole_get(n){
   return this.defines.get(EForm.Form).get(n);
}
function FFormDefineConsole_find(n, t){
   var o = this;
   if(EForm.Lov == t){
      return o.findLov(n);
   }
   var fc = o.get(n);
   if(RClass.isMode(ERun.Debug)){
      RMemory.free(fc);
      fc = null;
      o.defines.get(EForm.Form).set(n, null);
   }
   if(!fc){
      fc = o.createFromName(n);
   }
   return fc;
}
function FFormDefineConsole_getLov(n){
   return this.defines.get(EForm.Lov).get(n);
}
function FFormDefineConsole_findLov(n){
   var o = this;
   var fc = o.getLov(n);
   if(!fc){
      fc = o.createFromName(n, EForm.Lov);
   }
   return fc;
}
function FFormDefineConsole_getEvents(n){
   return this.events.get(n);
}
function FHintConsole(o){
   o = RClass.inherits(this, o, FConsole);
   o.scope      = EScope.Page;
   o.hintWindow = null;
   o.construct  = FHintConsole_construct;
   o.find       = FHintConsole_find;
   return o;
}
function FHintConsole_construct(){
}
function FHintConsole_find(){
   var o = this;
   var w = o.hintWindow;
   if( !w ){
      w = o.hintWindow = RControl.create(FHintWindow);
   }
   return w;
}
function FHistoryConsole(o){
   o = RClass.inherits(this, o, FConsole);
   o.scope          = EScope.Page;
   o.historyIndex   = -1;
   o.historys       = null;
   o.construct      = FHistoryConsole_construct;
   o.next           = FHistoryConsole_next;
   o.popup          = FHistoryConsole_popup;
   o.get            = FHistoryConsole_get;
   o.find           = FHistoryConsole_find;
   return o;
}
function FHistoryConsole_construct(){
   var o = this;
   o.historys = new TList();
}
function FHistoryConsole_next(){
   var o = this;
   var n = o.historyIndex + 1;
   var h = o.historys.get(n);
   if(!h){
      h = new THistory();
      o.historys.push(h);
   }
   o.historyIndex++;
   return h;
}
function FHistoryConsole_popup(l){
   var n = o.historyIndex - l;
   if(n < 0){
      n = 0;
   }
   o.historyIndex = n;
   return o.historys.get(n);
}
function FHistoryConsole_get(n, h, b){
   var o = this;
   var f = o.forms.get(n);
   if(!f){
      f = o.createFromName(n, h, b);
   }
   return f;
}
function FHistoryConsole_find(){
}
function FIdleConsole(o){
   o = RClass.inherits(this, o, FConsole);
   o.scope            = EScope.Page;
   o.register         = FIdleConsole_register;
   return o;
}
function FIdleConsole_register(c, cFun){
   var o = this;
   o.active = new TActive(c, cFun);
   o.active.interval = 100;
   RConsole.find(FActiveConsole).push(o.active);
}
function FIdleConsole_construct(){
   var o = this;
}
function FKeyConsole(o){
   o = RClass.inherits(this, o, FConsole);
   o.scope         = EScope.Page;
   o.allow         = true;
   o.registerAble  = true;
   o.listeners     = new Object();
   o.disableKeys   = new Object();
   o.onKeyDown     = FKeyConsole_onKeyDown;
   o.construct     = FKeyConsole_construct;
   o.register      = FKeyConsole_register;
   o.allowRegister = FKeyConsole_allowRegister;
   o.skipRegister  = FKeyConsole_skipRegister;
   o.allowAll      = FKeyConsole_allowAll;
   o.skipAll       = FKeyConsole_skipAll;
   return o;
}
RConsole.register(new TConsole(EScope.Page, FKeyConsole, true));
function FKeyConsole_onKeyDown(s, e){
   var o = this;
   var k = REnum.tryDecode(EKey, e.keyCode);
   if(k && o.allow){
      var ls = o.listeners[k];
      if(ls){
         ls.process(o, e);
         e.keyCode = null;
         e.returnValue = false;
      }
   }
   if(k && o.disableKeys[k]){
      e.keyCode = null;
      e.returnValue = false;
   }
}
function FKeyConsole_construct(){
   var o = this;
   o.base.FConsole.construct.call(o);
   o.disableKeys[EKey.F1] = true;
   o.disableKeys[EKey.F5] = true;
   RWindow.lsnsKeyDown.register(o, o.onKeyDown);
}
function FKeyConsole_register(k, l){
   var o = this;
   if(o.registerAble){
      if(RInt.isInt(k)){
         k = REnum.decode(EKey, k);
      }
      var ks = o.listeners;
      var ls = ks[k];
      if(!ls){
         ls = ks[k] = new TListeners();
      }
      ls.push(l);
   }
}
function FKeyConsole_allowRegister(){
   this.registerAble = true;
}
function FKeyConsole_skipRegister(){
   this.registerAble = false;
}
function FKeyConsole_allowAll(){
   this.allow = true;
}
function FKeyConsole_skipAll(){
   this.allow = false;
}
function FListenerConsole(o){
   o = RClass.inherits(this, o, FConsole);
   o.scope     = EScope.Page;
   o.listeners = null;
   o.contains  = FListenerConsole_contains;
   o.register  = FListenerConsole_register;
   o.process   = FListenerConsole_process;
   return o;
}
function FListenerConsole_contains(clazz, action){
   var o = this;
   if(!o.listeners){
      return false;
   }
   var name = RClass.name(clazz) + '@' + action;
   var lsns = o.listeners[name];
   if(!lsns){
      return false;
   }
   return lsns.isEmpty();
}
function FListenerConsole_register(clazz, action, owner, method){
   var o = this;
   if(!o.listeners){
      o.listeners = new Array();
   }
   var l = new TListener(owner, method);
   var name = RClass.name(clazz) + '@' + action;
   var lsns = o.listeners[name];
   if(!lsns){
      lsns = o.listeners[name] = new TListeners();
   }
   lsns.push(l);
   return l;
}
function FListenerConsole_process(clazz, action, sender, params){
   var o = this;
   if(o.listeners){
      var name = RClass.name(clazz) + '@' + action;
      var lsns = o.listeners[name];
      if(lsns){
         lsns.process(sender, params);
      }
   }
}
function FLoggerConsole(o){
   o = RClass.inherits(this, o, FConsole);
   o.scope      = EScope.Page;
   o.iLogger    = null;
   o.onKeyDown  = FLoggerConsole_onKeyDown;
   o.construct  = FLoggerConsole_construct;
   o.connect    = FLoggerConsole_connect;
   o.disconnect = FLoggerConsole_disconnect;
   o.output     = FLoggerConsole_output;
   return o;
}
function FLoggerConsole_onKeyDown(e){
   if(e.shiftKey && e.ctrlKey && EKey.L == e.keyCode){
      this.connect();
   }
}
function FLoggerConsole_construct(){
   var o = this;
   o.base.FConsole.construct.call(o);
   RWindow.lsnsKeyDown.register(o, o.onKeyDown);
}
function FLoggerConsole_connect(){
   var o = this;
   if(!o.iLogger){
   }
}
function FLoggerConsole_disconnect(){
   this.iLogger = null;
}
function FLoggerConsole_output(level, obj, method, ms, msg, stack){
   var o = this;
   if(o.iLogger){
      var m = RClass.dump(obj);
      if(ms){
         m += ' (' + ms + 'ms)';
      }
      var s = level + ' [' + RString.rpad(m, 36) + '] ';
      if(stack){
         s += RString.rpad(msg, 120) + ' [' + stack + ']';
      }else{
         s += msg;
      }
      o.iLogger.Output(s);
   }
}
function FLoggerConsole_xml(){
   if(!this.environment){
      this.connect()
   }
   if(this.environment){
      return this.environment.xml();
   }
   return null;
}
function FMessageConsole(o){
   o = RClass.inherits(this, o, FConsole, MStyle);
   o.scope        = EScope.Global;
   o.result       = new Array();
   o.attributes   = new Array();
   o.messageBox   = null;
   o.messageWindow = null;
   o.parse        = FMessageConsole_parse;
   o.popupMessage = FMessageConsole_popupMessage;
   o.closeMessage = FMessageConsole_closeMessage;
   o.checkResult  = FMessageConsole_checkResult;
   return o;
}
function FMessageConsole_parse(config){
   var msgs = null;
   var msgsNode = config.find('Messages');
   if(msgsNode && msgsNode.nodes && msgsNode.nodes.count){
      msgs = new TMessages();
      for(var n=0; n<msgsNode.nodes.count; n++){
         var node = msgsNode.node(n);
         var msg = new TMessage();
         msg.loadConfig(msgsNode.node(n));
         msgs.push(msg);
      }
   }
   return msgs;
}
function FMessageConsole_popupMessage(g){
   var o = this;
   var w = o.messageWindow;
   if(!w){
      w = o.messageWindow = RControl.create('FMessageWindow');
   }
   w.loadMessages(g);
   w.show();
}
function FMessageConsole_closeMessage(){
   RWindow.setEnable(true);
}
function FMessageConsole_checkResult(g){
   var o = this;
   var ms = g.messages = o.parse(g.config);
   if(ms){
      var m = ms.message(EMessage.Fatal);
      if(m && m.attrType == "session.timeout"){
         var ss = RString.splitTwo(m.redirect, '@');
         var s = RContext.context(ss[1] + '?do='+ss[0]);
         fmMain.action = s;
         fmMain.target = '_self';
         fmMain.submit();
      }else{
         o.popupMessage(g);
      }
      return false;
   }
   return true;
}
function FMonitorConsole(o){
   o = RClass.inherits(this, o, FConsole);
   o.scope      = EScope.Global;
   o.working    = false;
   o.interval   = 10;
   o.intervalId = null;
   o.monitors   = new TList();
   o.hWindow    = null;
   o.doInterval = FMonitorConsole_doInterval;
   o.push       = FMonitorConsole_push;
   o.process    = FMonitorConsole_process;
   o.processAll = FMonitorConsole_processAll;
   o.startup    = FMonitorConsole_startup;
   o.wait       = FMonitorConsole_wait;
   o.release    = FMonitorConsole_release;
   return o;
}
function FMonitorConsole_push(monitor){
   this.startup();
   monitor.id = this.monitors.sync(monitor);
   monitor.name = 'T:' + RString.lpad(monitor.id, 4, '0');
   monitor.status = EMonitor.Active;
}
function FMonitorConsole_process(monitor){
   if(monitor){
      switch(monitor.status){
         case EMonitor.Sleep:
            break;
         case EMonitor.Active:
            monitor.process(this.interval);
            break;
         case EMonitor.Cancel:
            this.monitors.removeItem(monitor);
            break;
      }
   }
}
function FMonitorConsole_processAll(){
   this.working = true;
   var monitors = this.monitors;
   for(var n=0; n<monitors.count; n++){
      this.process(monitors.get(n));
   }
   this.working = false;
}
function FMonitorConsole_doInterval(){
   var con = RGlobal.get(FMonitorConsole);
   if(con && !con.working){
      con.processAll();
   }
}
function FMonitorConsole_startup(){
   if(!this.hWindow){
      this.hWindow = window;
      this.intervalId = this.hWindow.setInterval(this.doInterval, this.interval);
   }
}
function FMonitorConsole_wait(request){
}
function FMonitorConsole_release(){
   if(this.hWindow && this.intervalId){
      this.hWindow.clearInterval(this.intervalId);
   }
}
function FMoveConsole(o){
   o = RClass.inherits(this, o, FConsole);
   o.ResizeRange   = 4;
   o.ResizeBorder  = 3;
   o.WidthMin      = 200;
   o.HeightMin     = 100;
   o.LayerMax      = 20000;
   o.LayerEdit     = 30000;
   o.scope         = EScope.Page;
   o.active        = null;
   o.activeControl = null;
   o.activePanel   = null;
   o.origin        = new TPoint3(0, 0, 10000);
   o.clickPos      = new TPoint();
   o.movePos       = new TPoint();
   o.ohStartDrag   = FMoveConsole_ohStartDrag;
   o.ohDrag        = FMoveConsole_ohDrag;
   o.ohStopDrag    = FMoveConsole_ohStopDrag;
   o.onInterval    = FMoveConsole_onInterval;
   o.construct     = FMoveConsole_construct;
   o.nextPoint     = FMoveConsole_nextPoint;
   o.nextLayer     = FMoveConsole_nextLayer;
   o.registerDrag  = FMoveConsole_registerDrag;
   o.startDrag     = FMoveConsole_startDrag;
   o.drag          = FMoveConsole_drag;
   o.stopDrag      = FMoveConsole_stopDrag;
   return o;
}
function FMoveConsole_ohStartDrag(){
   var o = this.link;
   var mc = this.linkMc;
   if(mc && RClass.isClass(o, MMoveable)){
      if(o.canMove && !o.inMoving){
         o.startDrag(EDrag.Move);
         mc.startDrag(o);
         o.inMoving = true;
      }
   }
}
function FMoveConsole_ohDrag(){
   var o = this.link;
   var mc = this.linkMc;
   if(mc && mc.activeControl==o){
      mc.drag(o);
   }
}
function FMoveConsole_ohStopDrag(){
   var o = this.link;
   var mc = this.linkMc;
   if(mc && mc.activeControl==o){
      o.inMoving = false;
      o.stopDrag(EDrag.Move);
      mc.stopDrag(o);
   }
}
function FMoveConsole_onInterval(){
   var c = this.activeControl;
   if(c){
      c.setBounds(Math.max(this.movePos.x - this.clickPos.x, 0), Math.max(this.movePos.y - this.clickPos.y, 0));
   }
}
function FMoveConsole_construct(){
   var o = this;
   o.base.FConsole.construct.call(o);
   o.active = new TActive(o, o.onInterval);
   RLog.debug(o.active, 'Push active object');
   o.active.interval = 0;
   o.active.status = EActive.Sleep;
   RConsole.find(FActiveConsole).push(o.active);
}
function FMoveConsole_nextPoint(){
   var p3 = new TPoint3(this.origin.x, this.origin.y, this.origin.z);
   this.origin.resize(40, 40, 1);
   if(this.origin.x > 200 || this.origin.y > 200){
      this.origin.x = 0;
      this.origin.y = 0;
   }
   return p3;
}
function FMoveConsole_nextLayer(){
   return ++this.origin.z;
}
function FMoveConsole_registerDrag(ctl, h){
   var o = this;
   h.onmousedown = o.ohStartDrag;
   h.onmousemove = o.ohDrag;
   h.onmouseup = o.ohStopDrag;
   h.linkMc = o;
}
function FMoveConsole_startDrag(ctl){
   var o = this;
   var cp = RWindow.clientPos();
   o.activeControl = ctl;
   o.activePanel = ctl.panel(EPanel.Move);
   o.activeSize = ctl.panel(EPanel.Size);
   var mr = RHtml.rect(o.activeSize);
   o.clickPos.set(cp.x - mr.left, cp.y - mr.top);
   o.movePos.assign(cp);
   o.activePanel.setCapture();
   o.active.status = EActive.Active;
   RLog.debug(o, 'Start drag {1}', ctl);
}
function FMoveConsole_drag(ctl){
   RWindow.clientPos(this.movePos);
}
function FMoveConsole_stopDrag(ctl){
   var o = this;
   RLog.debug(o, 'Stop drag {1}', ctl);
   o.active.status = EActive.Sleep;
   o.activePanel.releaseCapture();
   o.activeControl = null;
}
function FPropertyManager(){
   this.className = 'FPropertyManager';
   this.m_oPropertyList = new FList();
   this.onLoadDefineAfter = mgr_pty_onLoadDefineAfter;
   this.onLoadValueAfter = mgr_pty_onLoadValueAfter;
   this.onSaveValueAfter = mgr_pty_onSaveValueAfter;
   this.loadDefine = mgr_pty_loadDefine;
   this.loadValue = mgr_pty_loadValue;
   this.saveValue = mgr_pty_saveValue;
   return this;
}
function mgr_pty_loadDefine(oPtyCtl, sName){
   var oPtyNode = this.m_oPropertyList.nameValue(sName);
   if(oPtyNode){
      oPtyCtl.onLoadDefineAfter(oPtyNode);
   }else{
      oPtyCtl.disableWindow();
      var oDoc = new FXMLDocument('Config');
      var oActNode = oDoc.rootNode.createNode('Action');
      oActNode.value = 'property.define';
      var oDefNode = oDoc.rootNode.createNode('Property');
      oDefNode.setAttribute('name', sName);
      var oConnect = new FXMLConnect(SystemManager.serviceURL('sys.pty'), oDoc);
      oConnect.propertyControl = oPtyCtl;
      oConnect.propertyName = sName;
      oConnect.propertyList = this.m_oPropertyList;
      oConnect.onload = this.onLoadDefineAfter;
      oConnect.send();
   }
}
function mgr_pty_onLoadDefineAfter(){
   var oPtyNode = null;
   var arNodes = this.rootNode.nodes;
   if(arNodes){
      for(var n=0; n<arNodes.length; n++){
         var oNode = arNodes[n];
         if(IString.equals(oNode.name, 'propertyconfig')){
            var sItemName = oNode.attribute('name');
            this.propertyList.setNameValue(sItemName, oNode);
            if(IString.equals(sItemName, this.propertyName)){
               oPtyNode = oNode;
            }
         }
      }
   }
   if(oPtyNode){
      this.propertyControl.onLoadDefineAfter(oPtyNode);
   }else{
      ILogger.fatal(this, 'loadDefine', 'Not find Property define: ' + sPtyName);
   }
   this.propertyControl.enableWindow();
}
function mgr_pty_loadValue(oPtyCtl, sService, sPtyType, sPtyId){
   var oDoc = new FXMLDocument('Config');
   var oActNode = oDoc.rootNode.createNode("Action");
   oActNode.value = 'property.value.load';
   var oPtyDefNode = oDoc.rootNode.createNode("PropertyValue");
   oPtyDefNode.setAttribute('pty_type', sPtyType);
   oPtyDefNode.setAttribute('pty_id', sPtyId);
   var oConnect = new FXMLConnect(sService, oDoc);
   oConnect.propertyControl = oPtyCtl;
   oConnect.propertyType = sPtyType;
   oConnect.propertyId = sPtyId;
   oConnect.onload = this.onLoadValueAfter;
   oConnect.send();
}
function mgr_pty_onLoadValueAfter(){
   var oValNode = null;
   var arNodes = this.rootNode.nodes;
   if(arNodes){
      for(var n=0; n<arNodes.length; n++){
         var oNode = arNodes[n];
         if(IString.equals(oNode.attribute('pty_type'), this.propertyType) &&
            IString.equals(oNode.attribute('pty_id'), this.propertyId)){
            oValNode = oNode;
         }
      }
   }
   if(oValNode){
      this.propertyControl.onLoadValueAfter(oValNode);
   }else{
      ILogger.fatal(this, 'loadValue', 'Not load ' + this.propertyType + ' value: ' + this.propertyId);
   }
}
function mgr_pty_saveValue(oPtyCtl, sService, oPtyNode){
   oPtyCtl.clientWindow.document.body.disabled = true;
   var oDoc = new FXMLDocument('Config');
   var oActNode = oDoc.rootNode.createNode("Action");
   oActNode.value = 'property.value.save';
   oDoc.rootNode.push(oPtyNode);
   var oConnect = new FXMLConnect(sService, oDoc);
   oConnect.ptyCtl = oPtyCtl;
   oConnect.clientWindow = oPtyCtl.clientWindow;
   oConnect.onload = this.onSaveValueAfter;
   oConnect.send();
}
function mgr_pty_onSaveValueAfter(){
   this.ptyCtl.enableWindow();
}
function FResultConsole(o){
   o = RClass.inherits(this, o, FConsole);
   o.scope          = EScope.Page;
   o.executeCommand = FResultConsole_executeCommand;
   o.checkService   = FResultConsole_checkService;
   return o;
}
function FResultConsole_executeCommand(command){
   var name = command.get('name');
   if(EResultCommand.TreeReload == name){
      var tv = RGlobal.get('catalog.tree');
      if(tv){
         tv.reload();
      }
   }else if(EResultCommand.TreeNodeRefresh == name){
      var tv = RGlobal.get('catalog.tree');
      if(tv){
         var uuid = command.get('uuid');
         if(uuid){
            var fn = tv.findByUuid(uuid);
            if(fn){
               tv.reloadNode(fn);
            }else{
               return alert("Can't find tree node. (uuid="+uuid+")");
            }
         }else{
            tv.reloadNode();
         }
      }
   }else if(EResultCommand.TreeParentRefresh == name){
      var tv = RGlobal.get('catalog.tree');
      if(tv){
         var fn = tv.focusNode;
         if(fn){
            tv.reloadNode(fn.parentNode);
         }
      }
   }else if(EResultCommand.PageRedirect == name){
      var action = command.get('action');
      var page = top.RContext.context(command.get('page'));
      if(action){
         page += '?do=' + action;
      }
      fmMain.action = page;
      fmMain.target = '';
      fmMain.submit();
   }
}
function FResultConsole_checkService(config){
   var o = this;
   if(config){
      if(!RConsole.find(FMessageConsole).checkResult(new TMessageArg(config))){
         return false;
      }
      var cmdsNode = config.find('Commands');
      if(cmdsNode && cmdsNode.nodes && cmdsNode.nodes.count){
         for(var n=0; n<cmdsNode.nodes.count; n++){
            var node = cmdsNode.node(n);
            if(node.isName('Command')){
               o.executeCommand(node);
            }
         }
      }
      RConsole.find(FFocusConsole).restoreFocus();
   }
   return true;
}
function FSizeConsole(o){
   o = RClass.inherits(this, o, FConsole);
   o.activeControl = null;
   o.cursor        = null;
   o.movePos       = new TPoint();
   o.priorRect     = new TRect();
   o.moveRect      = new TRect();
   o.scope         = EScope.Page;
   o.ohStartDrag   = FSizeConsole_ohStartDrag;
   o.ohDrag        = FSizeConsole_ohDrag;
   o.ohStopDrag    = FSizeConsole_ohStopDrag;
   o.onInterval    = FSizeConsole_onInterval;
   o.construct     = FSizeConsole_construct;
   o.registerDrag  = FSizeConsole_registerDrag;
   o.startDrag     = FSizeConsole_startDrag;
   o.drag          = FSizeConsole_drag;
   o.stopDrag      = FSizeConsole_stopDrag;
   return o;
}
function FSizeConsole_ohStartDrag(){
   var o = this.link;
   var sc = this.linkSc;
   if(sc && RClass.isClass(o, MSizeable)){
      if(!o.isDraging){
         if(sc.startDrag(o)){
            o.startDrag(EDrag.Size);
         }
      }
   }
}
function FSizeConsole_ohDrag(){
   var o = this.link;
   var sc = this.linkSc;
   if(sc && RClass.isClass(o, MSizeable)){
      sc.drag(o);
   }
}
function FSizeConsole_ohStopDrag(){
   var o = this.link;
   var sc = this.linkSc;
   if(sc && sc.activeControl==o){
      o.stopDrag(EDrag.Size);
      sc.stopDrag(o);
   }
}
function FSizeConsole_onInterval(){
   if(this.activeControl){
      var mr = this.moveRect;
      this.activeControl.setBounds(mr.left, mr.top, mr.right, mr.bottom);
   }
}
function FSizeConsole_construct(){
   var o = this;
   o.base.FConsole.construct.call(o);
   o.active = new TActive(o, o.onInterval);
   RLog.debug(o.active, 'Push active object');
   o.active.interval = 10;
   o.active.status = EActive.Sleep;
   RConsole.find(FActiveConsole).push(o.active);
}
function FSizeConsole_registerDrag(ctl, h){
   var o = this;
   h.onmousedown = o.ohStartDrag;
   h.onmousemove = o.ohDrag;
   h.onmouseup = o.ohStopDrag;
   h.linkSc = o;
}
function FSizeConsole_startDrag(ctl){
   var cur = ctl.cursor();
   if(cur == ECursor.Default){
      return false;
   }
   var o = this;
   var cp = RWindow.clientPos();
   this.activeControl = ctl;
   this.activePanel = ctl.panel(EPanel.Size);
   var mr = RHtml.rect(this.activePanel);
   this.moveRect.assign(mr);
   this.priorRect.set(mr.left-cp.x, mr.top-cp.y, mr.right-cp.x, mr.bottom-cp.y);
   this.cursor = cur;
   this.activePanel.setCapture();
   o.active.status = EActive.Active;
   return true;
}
function FSizeConsole_drag(ctl){
   if(this.activeControl){
      var pr = this.priorRect;
      var mr = this.moveRect;
      var cp = RWindow.clientPos();
      switch(this.cursor){
         case ECursor.NorthWest:
            mr.top = pr.top + cp.y;
            mr.left = pr.left + cp.x;
            break;
         case ECursor.SouthWest:
            mr.bottom = pr.bottom + cp.y;
            mr.left = pr.left + cp.x;
            break;
         case ECursor.SouthEast:
            mr.bottom = pr.bottom + cp.y;
            mr.right = pr.right + cp.x;
            break;
         case ECursor.NorthEast:
            mr.top = pr.top + cp.y;
            mr.right = pr.right + cp.x;
            break;
         case ECursor.West:
            mr.left = pr.left + cp.x;
            break;
         case ECursor.South:
            mr.bottom = pr.bottom + cp.y;
            break;
         case ECursor.East:
            mr.right = pr.right + cp.x;
            break;
         case ECursor.North:
            mr.top = pr.top + cp.y;
            break;
      }
   }else{
      ctl.setCursor();
   }
}
function FSizeConsole_stopDrag(ctl){
   var o = this;
   o.active.status = EActive.Sleep;
   this.activePanel.releaseCapture();
   this.activeControl = null;
   this.cursor = null;
}
function FTrackManager(){
   IObject.extendClass(this, FManager);
   this.className = 'FMoveManager';
   this.trackCount = 20;
   this.clientWindow = null;
   this.tracker = null;
   this.messageDate = new Array();
   this.messageRef = new Array();
   this.messageInfo = new Array();
   this.eventIntervalId = null;
   this.windowTrack = null;
   this.htmlTable = null;
   this.onInterval = mgr_trc_onInterval;
   this.onEventMousedown = mgr_trc_onEventMousedown;
   this.onEventRelease = mgr_trc_onEventRelease;
   this.initialize = mgr_trc_initialize;
   this.createWindow = mgr_trc_createWindow;
   this.createElement = mgr_trc_createElement;
   this.refresh = mgr_trc_refresh;
   this.push = mgr_trc_push;
   return this;
}
function mgr_trc_onInterval(){
   TrackManager.refresh();
}
function mgr_trc_onEventRelease(){
   if(this.clientWindow && this.eventIntervalId){
      this.clientWindow.clearInterval(this.eventIntervalId);
   }
   this.clientWindow = null;
   this.windowTrack = null;
}
function mgr_trc_createElement(sTag){
   return this.clientWindow ? this.clientWindow.createElement(sTag) : null ;
}
function mgr_trc_initialize(oClientWindow){
   this.clientWindow = oClientWindow;
}
function mgr_trc_createWindow(oClientWindow){
   oClientWindow = oClientWindow ? oClientWindow : this.clientWindow;
   this.windowTrack = new FWinTrackCtl()
   this.windowTrack.clientWindow = oClientWindow;
   this.windowTrack.show();
   this.windowTrack.setLeft(10);
   this.windowTrack.setTop(oClientWindow.document.body.offsetHeight - 150);
   this.windowTrack.setWidth(oClientWindow.document.body.offsetWidth-20);
   this.windowTrack.setHeight(140);
   this.windowTrack.focus();
   this.clientWindow = oClientWindow;
   this.htmlTable = this.windowTrack.htmlTable;
   this.eventIntervalId = this.clientWindow.setInterval(this.onInterval, 20);
}
function mgr_trc_push(oRef, sMessage){
   var sDate = IDate.format(new FDate(), 'YYYY/MM/DD HH24:MI:SS')
   var sRef = '[null]';
   if(oRef){
      if(oRef.className){
         if(oRef.name){
            sRef = '[' + oRef.className + '->' + oRef.name + ']';
         }else{
            sRef = '[' + oRef.className + ']';
         }
      }else if(oRef.tagName){
         sRef = '[' + oRef.tagName + ']';
      }else{
         sRef = '[' + oRef + ']';
      }
   }
   this.messageDate.push(sDate);
   this.messageRef.push(sRef);
   this.messageInfo.push(sMessage);
}
function mgr_trc_refresh(){
   if(this.htmlTable){
      var nLength = this.messageDate.length;
      if(nLength > 0){
         for(var n=0; n<nLength; n++){
            var oRow = this.htmlTable.insertRow(0);
            var oCell = oRow.insertCell();
            oCell.noWrap = true;
            oCell.innerText = this.messageDate[n];
            var oCell = oRow.insertCell();
            oCell.noWrap = true;
            oCell.innerText = this.messageRef[n];
            var oCell = oRow.insertCell();
            oCell.noWrap = true;
            oCell.innerText = this.messageInfo[n];
            var nRowLength = this.htmlTable.rows.length;
            if(nRowLength > this.trackCount){
               this.htmlTable.rows[nRowLength-1].removeNode(true);
            }
         }
         this.messageDate = this.messageDate.slice(nLength);
         this.messageRef = this.messageRef.slice(nLength);
         this.messageInfo = this.messageInfo.slice(nLength);
      }
   }
}
function mgr_trc_onEventMousedown(){
   if(this.clientWindow){
      var oEvent = this.clientWindow.event;
      if(oEvent && oEvent.altKey && oEvent.ctrlKey && oEvent.button == 2){
         if(!this.windowTrack){
            this.createWindow();
         }else{
            var nLength = this.htmlTable.rows.length;
            for(var n=nLength-1; n>=0; n--){
               this.htmlTable.rows[n].removeNode(true);
            }
            this.windowTrack.show();
         }
      }
   }
}
function FUploadConsole(o){
   o = RClass.inherits(this, o, FConsole);
   o.scope               = EScope.Global;
   o._build              = false;
   o.uploadWindow        = null;
   o.uploadFileName      = null;
   o.uploads             = null;
   o.lsnsFileSelected    = new TListeners();
   o.lsnsUploaded        = new TListeners();
   o.onFileSelected      = RClass.register(o, new HChange('onFileSelected'), FUploadConsole_onFileSelected);
   o.onFileUploaded      = FUploadConsole_onFileUploaded;
   o.onDeleteLoaded      = FUploadConsole_onDeleteLoaded;
   o.onPreviewSaveLoaded = FUploadConsole_onPreviewSaveLoaded;
   o.onDataFrameLoad     = RClass.register(o, new HReadyStateChange('onDataFrameLoad'), FUploadConsole_onDataFrameLoad);
   o.onUploadFrameLoad   = RClass.register(o, new HReadyStateChange('onUploadFrameLoad'), FUploadConsole_onUploadFrameLoad);
   o.construct           = FUploadConsole_construct;
   o.showDialog          = FUploadConsole_showDialog;
   o.upload              = FUploadConsole_upload;
   o.getWindow           = FUploadConsole_getWindow;
   o.findWindow          = FUploadConsole_findWindow;
   o.previewSave         = FUploadConsole_previewSave;
   o.deleteFile          = FUploadConsole_deleteFile;
   return o;
}
function FUploadConsole_upload(a, g){
   var o = this;
   if(a){
      o.action = a;
   }
   if(g){
      o.hType.value = RString.nvl(g.typeCode);
      o.hCode.value = RString.nvl(g.recordCode);
      o.hGuid.value = RString.nvl(g.recordGuid);
      o.hName.value = RString.nvl(g.recordName);
      o.hPath.value = RString.nvl(g.path);
   }
   o.hForm.action = o.uploadPage + '?do=' + a;
   o.hForm.submit();
}
function FUploadConsole_onFileSelected(e){
   var o = RHtml.findLink(this.parentElement, 'control');
   var fn = o.hUpload.value;
   if(fn && fn.length > 0){
      o.uploadFileName = fn;
      o.hWorkerId.value = RDate.format();
      o.uploadWindow.selectFile(fn);
      o.lsnsFileSelected.process(o);
   }
}
function FUploadConsole_onFileUploaded(s, g){
   this.uploadWindow.uploadedFile(g)
}
function FUploadConsole_onDataFrameLoad(e, a, b){
   var o = this;
   var hf = o.hDataFrame;
   if('complete' == hf.readyState){
      var hw = hf.contentWindow;
      var hd = hw.document;
      var hb = hd.body;
      var s = new TString();
      s.append("<FORM method='post' target='" + o.uploadFrameName + "' enctype='multipart/form-data'>");
      s.append("<INPUT name='type' type='text'>");
      s.append("<INPUT name='code' type='text'>");
      s.append("<INPUT name='guid' type='text'>");
      s.append("<INPUT name='name' type='text'>");
      s.append("<INPUT name='path' type='text'>");
      s.append("<DIV></DIV>");
      s.append("<INPUT name='worker_id' type='text'>");
      s.append("</FORM>");
      hb.innerHTML = s.toString();
      o.hForm = hd.forms[0];
      o.hType = o.hForm.children[0];
      o.hCode = o.hForm.children[1];
      o.hGuid = o.hForm.children[2];
      o.hName = o.hForm.children[3];
      o.hPath = o.hForm.children[4];
      var hup = o.hUploadPanel  = o.hForm.children[5];
      RHtml.link(hup, 'control', o);
      o.hWorkerId  = o.hForm.children[6];
   }
}
function FUploadConsole_onUploadFrameLoad(e, a, b){
   var o = this;
   var hf = o.hUploadFrame;
   if('complete' == hf.readyState){
   }
}
function FUploadConsole_construct(){
   var o = this;
   o.base.FConsole.construct.call(o);
   o.uploads = new TMap();
   o.uploadPage = top.RContext.context('/apl/logic/transfer/Upload.wa')
   var hd = o.hDataFramePanel = RBuilder.append(null, 'DIV');
   var ufn = o.uploadFrameName = 'frm' + RHtml.uid(hd);
   hd.style.display = 'none';
   hd.innerHTML = "<IFRAME></IFRAME>";
   var hf = o.hDataFrame = hd.children[0];
   RControl.attachEvent(o, 'onDataFrameLoad', hf);
   var hd = o.hUploadFramePanel = RBuilder.append(null, 'DIV');
   hd.style.display = 'none';
   hd.innerHTML = "<IFRAME name='" + ufn + "'></IFRAME>";
   o.onUploadFrameLoad = hd.children[0];
   RConsole.find(FListenerConsole).register(FUploadConsole, 'onFileUploaded', o, o.onFileUploaded);
}
function FUploadConsole_showDialog(){
   var o = this;
   var hup = o.hUploadPanel;
   if(o.hUpload){
      o.hUpload.onchange = null;
      hup.removeChild(o.hUpload);
   }
   hup.innerHTML = "<INPUT name='upload' type='file'>";
   var hu = o.hUpload  = hup.children[0];
   hu.onchange = o.onFileSelected;
   hu.click();
}
function FUploadConsole_getWindow(){
   var o = this;
   var w = o.uploadWindow;
   if(!w){
      w = o.uploadWindow = RControl.create('FUploadWindow');
   }
   return w;
}
function FUploadConsole_findWindow(){
   var o = this;
   var w = o.getWindow();
   w.lsnsFileSelected.clear();
   w.lsnsUploaded.clear();
   return w;
}
function FUploadConsole_onDeleteLoaded(e){
   var o = this;
   var g = e.argument;
   if(g.callback){
      g.callback.invoke(o, g, e);
   }
}
function FUploadConsole_onPreviewSaveLoaded(e){
   var o = this;
   var g = e.argument;
   var fn = e.document.root().find('File');
   if(fn){
      g.attributes = fn.attrs;
   }
   if(g.callback){
      g.callback.invoke(o, g, e);
   }
}
function FUploadConsole_previewSave(g){
   var o = this;
   var doc = new TXmlDocument();
   var root = doc.root();
   root.set('action', 'previewSave');
   var fileNode = root.create('File');
   fileNode.set('type_code', g.typeCode);
   fileNode.set('record_code', g.recordCode);
   fileNode.set('record_guid', g.recordGuid);
   fileNode.set('record_name', g.recordName);
   fileNode.set('guid', g.guid);
   fileNode.set('path', g.path);
   fileNode.set('adjust_width', g.adjustWidth);
   fileNode.set('adjust_height', g.adjustHeight);
   var e = new TEvent(o, EXmlEvent.Send, o.onPreviewSaveLoaded);
   e.url = RService.url('public.upload');
   e.document = doc;
   e.argument = g;
   RConsole.find(FXmlConsole).process(e);
}
function FUploadConsole_deleteFile(g){
   var o = this;
   var doc = new TXmlDocument();
   var root = doc.root();
   root.set('action', 'deleteFile');
   var fileNode = root.create('File');
   fileNode.set('guid', g.guid);
   var e = new TEvent(o, EXmlEvent.Send, o.onDeleteLoaded);
   e.url = RService.url('public.upload');
   e.document = doc;
   e.argument = g;
   RConsole.find(FXmlConsole).process(e);
}
function FValidConsole(o){
   o = RClass.inherits(this, o, FConsole);
   o.scope      = EScope.Page;
   o.validFocus = FValidConsole_validFocus;
   o.notEmpty   = FValidConsole_notEmpty;
   o.validDate  = FValidConsole_validDate;
   return o;
}
function FValidConsole_validFocus(){
   if(this.validControl && this.validControl.focus){
      this.validControl.focus(true);
   }
   this.validControl = null;
}
function FValidConsole_notEmpty(sValue, sLabel){
   if(IString.isEmpty(sValue)){
      ILogger.contextValid(this, 'notEmpty', this.CONTEXT_VALID_NULL, sLabel);
      return false;
   }
   return true;
}
function FValidConsole_validDate(sValue, sLabel){
   if(!IString.isEmpty(sValue)){
      if(!IDate.autoParse(sValue)){
         ILogger.contextValid(this, 'validDate', this.CONTEXT_VALID_DATE, sLabel);
         return false;
      }
   }
   return true;
}
function FWindowConsole(o, create){
   if(!create){return this;}
   o = IClass.inherits(this, o, FConsole);
   o.activeWindow = null;
   o.windows      = null;
   o.defines      = null;
   o.construct    = FWindowConsole_construct;
   o.create       = FWindowConsole_create;
   o.loadDefine   = FWindowConsole_loadDefine;
   return o;
   o.focusWinCtl = null;
   o.activeForm = null;
   o.activeControl = null;
   o.maxFlag = false;
   o.m_oDefinePool = new FList();
   o.clientWindow = null;
   o.EVENT_MAX = 101;
   o.EVENT_CLOSE = 102;
   o.EVENT_CLOSEALL = 103;
   this.onEventMousedown = FWindowConsole_onEventMousedown;
   this.onSaveDefineAfter = FWindowConsole_onSaveDefineAfter;
   this.onEventRelease = FWindowConsole_onEventRelease;
   this.initialize = FWindowConsole_initialize;
   this.hasWindow = FWindowConsole_hasWindow;
   this.focus = FWindowConsole_focus;
   this.saveDefine = FWindowConsole_saveDefine;
   this.findWindow = FWindowConsole_findWindow;
   this.releaseWindowName = FWindowConsole_releaseWindowName;
   this.releaseWindow = FWindowConsole_releaseWindow;
   this.doFrameAction = FWindowConsole_doFrameAction;
   this.setMaxWindow = FWindowConsole_setMaxWindow;
   this.restore = FWindowConsole_restore;
   this.doProperties = FWindowConsole_doProperties;
   this.clear = FWindowConsole_clear;
   this.hideAll = FWindowConsole_hideAll;
   this.dump = FWindowConsole_dump;
   return this;
}
function FWindowConsole_construct(){
   this.windows = new TList();
   this.defines = new TNameList();
}
function FWindowConsole_create(name, hWin){
   var config = this.loadDefine(name);
   var win = IClass.create(FWindow);
IDump.dump(_id1, win);
   win.linkHtml(window);
   win.build();
   return win;
   if(this.windowList.isEmpty()){
      MoveManager.resetPosition();
   }
   var oWindow = new FCfgWindowCtl();
   oWindow.name = sWinName;
   oWindow.clientWindow = oClientWindow;
   if(sWinName){
      oWindow.show();
      oWindow.focus();
      if(this.maxFlag){oWindow.max();}
      this.windowList.add(sWinName, oWindow);
   }
   return oWindow;
}
function FWindowConsole_loadDefine(name){
   if(name == null){
      return null;
   }
   var config = this.defines.find(name);
   if(config == null){
      var doc = new TXmlDocument();
      var root = doc.root();
      var action = root.create('Action');
      action.value = 'window.config.load';
      root.create('Window', 'name', name);
      var cnn = new TXmlCnn();
      var doc = cnn.syncSend('window.xml', doc);
      return doc.root();
      var oNode = null;
      var sNodeName = null;
      var arNodes = oConnect.rootNode.nodes;
      for(var n=0; n<arNodes.length; n++){
         var oNode = arNodes[n];
         sNodeName = oNode.name.toLowerCase();
         if(sNodeName == 'window'){
            var sFullName = oNode.attribute('name');
            this.m_oDefinePool.setNameValue(sFullName, oNode);
            if(sFullName == sWinName){
               oWinNode = oNode;
            }
         }else if(sNodeName == 'dataset'){
            DatasetManager.addDefine(oNode.attribute('name'), oNode);
         }else if(sNodeName == 'searchlist'){
            SearchManager.addDefine(oNode);
         }
      }
   }
   if(!config){
      return ILogger.fatal(this, 'loadDefine', 'Not find window define: ' + sWinName);
   }
   return config;
}
function FWindowConsole_dump(){
   var sDump = this.className;
   sDump += '\n\nDefine:\n' + this.m_oDefinePool.dump();
   sDump += '\n\nWindow:\n' + this.windowList.dump();
   return sDump;
}
function FWindowConsole_clear(){
   this.focusWinCtl = null;
   this.activeWindow = null;
   this.activeForm = null;
   this.activeControl = null;
   this.m_oDefinePool = new FList();
   var nSize = this.windowList.size();
   for(var n=nSize-1; n>=0; n--){
      this.windowList.value(n).release();
   }
   this.windowList = new FList();
   IEngine.process(this, this.EVENT_CLOSEALL);
}
function FWindowConsole_hideAll(oExpWin, bDisplay){
   var nSize = this.windowList.size();
   for(var n=nSize-1; n>=0; n--){
      var oWin = this.windowList.value(n);
      if(oWin != oExpWin){
         oWin.hide(bDisplay);
      }
   }
}
function FWindowConsole_setMaxWindow(oWin){
   this.maxFlag = true;
   this.hideAll(oWin);
}
function FWindowConsole_restore(){
   var nSize = this.windowList.size();
   this.hideAll(null, true);
   for(var n=0; n<nSize; n++){
      var oWin = this.windowList.value(n);
      if(oWin.maxFlag){
         this.windowList.value(n).restore();
      }
   }
   this.maxFlag = false;
}
function FWindowConsole_initialize(oCtWin){
   this.clientWindow = oCtWin;
}
function FWindowConsole_hasWindow(){
   return !this.windowList.isEmpty();
}
function FWindowConsole_focus(oWinCtl){
   this.focusWinCtl = oWinCtl;
   if(this.maxFlag){
      oWinCtl.show();
      this.hideAll(oWinCtl, true)
      oWinCtl.max();
   }
}
function FWindowConsole_saveDefine(oWinNode, oClientWindow){
   if(oClientWindow){this.clientWindow.document.body.disabled = true;}
   if(!oWinNode){
      return LoggerUtil.fatal(this, 'saveDefine', 'Window node is null.');
   }
   var sFullName = oWinNode.attribute('full_name');
   if(!sFullName){
      return ILogger.fatal(this, 'saveDefine', 'Window full name is null.');
   }
   var oDoc = new FXMLDocument('Config');
   var oActNode = oDoc.rootNode.createNode('Action');
   oActNode.setAttribute('name', 'define.save');
   oDoc.rootNode.push(oWinNode);
   var oConnect = new FXMLConnect(SystemManager.serviceURL('window'), oDoc);
   oConnect.clientWindow = oClientWindow;
   oConnect.onload = this.onSaveDefineAfter;
   oConnect.send();
}
function FWindowConsole_onEventMousedown(oCWin){
}
function FWindowConsole_onSaveDefineAfter(){
   ILogger.info(this, 'saveDefine', 'Save Ok.');
   if(this.clientWindow){this.clientWindow.document.body.disabled = false;}
}
function FWindowConsole_findWindow(sWinName){
   return this.windowList.nameValue(sWinName);
}
function FWindowConsole_releaseWindowName(sWinName){
   var oWin = this.windowList.removeName(sWinName);
   IEngine.process(this, this.EVENT_CLOSE, oWin);
}
function FWindowConsole_releaseWindow(oWin){
   this.windowList.removeValue(oWin);
   IEngine.process(this, this.EVENT_CLOSE, oWin);
}
function FWindowConsole_doFrameAction(sAction){
   if(!this.activeForm){
      return ILogger.fatal(this, 'doFrameAction', 'Not active form!');
   }
   this.activeForm.doAction(sAction);
}
function FWindowConsole_doProperties(){
   TrackManager.push(this, 'Do properties.');
   if(!WindowManager.focusWinCtl){return;}
   var arParams = new Array();
   arParams['WindowManager'] = WindowManager;
   window.showModalDialog(SystemManager.actionURL('window'), arParams, 'dialogWidth:500px;dialogHeight:360px;resizable:no;scroll:no;edge:sunken');
}
function FWindowConsole_onEventRelease(oCWin){
   if(oCWin){
      var oSubWin = null;
      var oRemoves = new Array();
      var nSize = this.windowList.size();
      for(var n=0; n<nSize; n++){
         oSubWin = this.windowList.value(n);
         if(oSubWin.clientWindow == oCWin){
            if(oSubWin == MoveManager.focusBorder){
               MoveManager.focus(null);
            }
            oRemoves.push(oSubWin);
         }
      }
      for(var n=0; n<oRemoves.length; n++){
         this.windowList.removeValue(oRemoves[n]);
      }
   }else{
      this.windowList.clear();
      MoveManager.focus(null);
   }
}
function FXmlConsole(o){
   o = RClass.inherits(this, o, FConsole);
   o.scope       = EScope.Page;
   o.connections = null;
   o.onLoad      = FXmlConsole_onLoad;
   o.construct   = FXmlConsole_construct;
   o.alloc       = FXmlConsole_alloc;
   o.process     = FXmlConsole_process;
   o.send        = FXmlConsole_send;
   return o;
}
function FXmlConsole_construct(){
   var o = this;
   o.connections = new TList();
}
function FXmlConsole_onLoad(){
   var o = this;
   var e = o.event;
   e.document = o.document;
   e.process();
   o.event = null;
   o.document = null;
   o.isFree = true;
}
function FXmlConsole_alloc(){
   var o = this;
   var a = null;
   var cs = o.connections;
   var l = cs.count;
   for(var n=0; n<l; n++){
      var c = cs.get(n);
      if(c && c.isFree){
         a = c;
         break;
      }
   }
   if(!a){
      a = new TXmlConnect();
      cs.push(a);
      a.onLoad = o.onLoad;
   }
   a.isFree = false;
   return a;
}
function FXmlConsole_process(e){
   var o = this;
   var c = o.alloc();
   c.event = e;
   switch(e.code){
      case EXmlEvent.Send:
         c.send(e.url, e.document);
         break;
      case EXmlEvent.Receive:
         c.receive(e.url, e.document);
         break;
      case EXmlEvent.SyncSend:
         return c.syncSend(e.url, e.document);
      case EXmlEvent.SyncReceive:
         return c.syncReceive(e.url, e.document);
   }
}
function FXmlConsole_send(u, d){
   var o = this;
   var c = o.alloc();
   var r = c.syncSend(u, d);
   c.isFree = true;
   return r;
}
function MRelease(o){
   o = RClass.inherits(this, o);
   o.release = RMethod.virtual(o, 'release')
   return o;
   }
var RCharset = new function(o){
   o = moNvl(o, this);
   o.ansiMinCode = RCharset_ansiMinCode;
   o.ansiMaxCode = RCharset_ansiMaxCode;
   o.dump        = RCharset_dump;
   RMemory.register('RCharset', o);
   return this;
}
function RCharset_ansiMinCode(){
   if(SystemManager.language == "ja"){return 65383;}
   return -1;
}
function RCharset_ansiMaxCode(){
   if(SystemManager.language == "ja"){return 65439;}
   return -1;
}
function RCharset_dump(){
   return this.className + ' ' + SystemManager.language + " [" + this.ansiMinCode() + " - " + this.ansiMaxCode() + "]";
}
var RCookie = new function(o){
   o = moNvl(o, this);
   o.ConnectId  = 'MOCK';
   o.storeDays  = 30;
   o.cookies    = new TAttributes();
   o.get        = RCookie_get;
   o.set        = RCookie_set;
   o.connect    = RCookie_connect;
   o.disconnect = RCookie_disconnect;
   o.release    = RCookie_release;
   RMemory.register('RCookie', o);
   return o;
}
function RCookie_get(n){
   return RString.nvl(this.cookies.get(n));
}
function RCookie_set(n, v){
   this.cookies.set(n, v);
}
function RCookie_connect(){
   var o = this;
   var cs = o.cookies;
   var ac = document.cookie.split('; ');
   for(var n=0; n<ac.length; n++){
      var s = ac[n];
      var i = s.indexOf('=');
      if(-1 != i){
         var n = s.substring(0, i);
         if(o.ConnectId == n){
            var ts = new TAttributes()
            ts.unpack(s.substring(i + 1));
            for(var n=0; n<ts.count; n++){
               var cv = ts.value(n);
               if(!RString.isEmpty(cv)){
                  cs.set(ts.name(n), unescape(ts.value(n)));
               }
            }
            break;
         }
      }
   }
}
function RCookie_disconnect(){
   var o = this;
   var d = new Date();
   d.setTime(d.getTime() + (RDate.DaySeconds * o.storeDays));
   var cs = o.cookies;
   var ts = new TAttributes();
   var c = cs.count;
   for(var n=0; n<c; n++){
      var v = cs.value(n);
      if(!RString.isEmpty(v)){
         ts.set(cs.name(n), escape(v));
      }
   }
   document.cookie = o.ConnectId + "=" + ts.pack() + ";expires=" + d.toGMTString();
}
function RCookie_release(){
   var o = this;
   var d = new Date();
   d.setTime(d.getTime() -1 );
   document.cookie = o.ConnectId + "=null;expires=" + d.toGMTString();
}
var RGlobalPage = new function(){
   var o = this;
   o.uri          = null;
   o.uriTag       = null;
   o.resourcePath = null;
   RMemory.register('RGlobalPage', o);
   return o;
}
var RKey = new function(){
   var o = this;
   o.isCtlKey      = RKey_isCtlKey;
   o.isNumKey      = RKey_isNumKey;
   o.isCtlKeyPress = RKey_isCtlKeyPress;
   o.eventClear    = RKey_eventClear;
   o.fixCase       = RKey_fixCase;
   o.fixPattern    = RKey_fixPattern;
   o.fixChars      = RKey_fixChars;
   RMemory.register('RKey', o);
   return o;
}
function RKey_isCtlKey(c){
   var ks = EKey.ControlKeys;
   for(var n=0; n<ks.length; n++){
      if(ks[n] == c){
         return true;
      }
   }
   return false;
}
function RKey_isNumKey(c){
   var ks = EKey.ControlKeys;
   if(c >= 96 && c <= 105){
      return true;
   }
   return false;
}
function RKey_isCtlKeyPress(c){
   for(var n in EKey.ControlKeys){
      if(EKey.ControlKeys[n] == c){
         return true;
      }
   }
   return false;
}
function RKey_eventClear(e){
   e.returnValue = false;
}
function RKey_fixCase(e, c){
   if(e && c){
      var k = e.keyCode;
      if(ECase.Upper == c){
         k = String.fromCharCode(k).toUpperCase().charCodeAt(0)
      }else if(ECase.Lower == c){
         k = String.fromCharCode(k).toLowerCase().charCodeAt(0)
      }
      e.keyCode = k;
   }
}
function RKey_fixPattern(e, p){
   if(p){
      var k = e.keyCode;
      if(!this.isCtlKeyPress(k)){
         if(!RString.isPattern(String.fromCharCode(k), p)){
            e.keyCode = 0;
            return false;
         }
      }
   }
   return true;
}
function RKey_fixChars(e, p){
   if(p){
      var k = e.keyCode;
      if(this.isNumKey(k)){
    	  k = e.keyCode = e.keyCode - 48;
      }
      if(!this.isCtlKeyPress(k)){
         if(!RString.inChars(String.fromCharCode(k), p)){
            e.keyCode = 0;
            e.returnValue = false;
            return false;
         }
      }
   }
   return true;
}
var RLayer = new function(o){
   if(!o){o=this;}
   o.layers = new Array();
   o.next   = RLayer_next;
   o.free   = RLayer_free;
   RMemory.register('RLayer', o);
   return o;
}
function RLayer_next(type){
   var o = this;
   var n = RInt.nvl(type, ELayer.Default);
   var c = RInt.nvl(o.layers[n], n);
   o.layers[n] = ++c;
   return c;
}
function RLayer_free(type, layer){
   var o = this;
   var n = RInt.nvl(type, ELayer.Default);
   var c = RInt.nvl(o.layers[n], n);
   --c;
   if(c > n){
      o.layers[n] = c;
   }
   return c;
}
var RNaming = new function(){
   var o = this;
   o.pool = new Array();
   o.get  = RNaming_get;
   o.set  = RNaming_set;
   RMemory.register('RNaming', o);
   return o;
}
function RNaming_get(c, n){
   if(c){
      n = RString.nvl(n) + '@' + RClass.name(c);
      return this.pool[n.toLowerCase()];
   }
   return null;
}
function RNaming_set(o, n){
   if(o){
      n = RString.nvl(n) + '@' + RClass.name(o);
      this.pool[n.toLowerCase()] = o;
   }
}
var RPage = new function(){
   var o = this;
   o.url      = RPage_url;
   o.parse    = RPage_parse;
   o.redirect = RPage_redirect;
   RMemory.register('RPage', o);
   return o;
}
function RPage_url(name){
   if(RString.startsWith(name, 'http://')){
      return name;
   }
   return top.RContext.context(name);
}
function RPage_parse(page){
   if(!page){
      return null;
   }
   var ps = '';
   var n = page.indexOf('?');
   if(-1 != n){
      ps = page.substring(n);
      page = page.substring(0, n);
   }
   var p = null;
   var items = page.split('@');
   if(1 == items.length && items[0]){
      p = new TPage();
      p.action = null;
      p.page = items[0] + ps;
      p.url = this.url(p.page);
   }else if(2 == items.length && items[0] && items[1]){
      p = new TPage();
      p.action = items[0];
      p.page = items[1] + ps;
      p.url = this.url(p.page);
   }
   return p;
}
function RPage_redirect(f, t, u, m){
   RConsole.find(FEnvConsole).buildValue();
   u = RString.nvl(u);
   if(m){
      u += '?do=' + m;
   }
   f.action = u;
   if(t){
      f.target = t;
   }
   f.method = 'POST';
   f.submit();
}
var RService = new function(){
   var o = this;
   o.url   = RService_url;
   o.parse = RService_parse;
   RMemory.register('RService', o);
   return o;
}
function RService_url(name){
   if(RString.startsWith(name, 'http://')){
      return name;
   }
   if(RString.startsWith(name, '#')){
      return name.substr(1);
   }
   if(!RString.startsWith(name, '/')){
      name = '/' + name;
   }
   return top.RContext.context(name + '.ws');
}
function RService_parse(service){
   var o = null;
   if(service){
      var items = service.split('@');
      if(items.length == 1){
         if(items[0]){
            o = new TService();
            o.service = items[0];
            o.action = null;
            o.url = this.url(o.service);
         }
      }else if(items.length == 2){
         if(items[0] && items[1]){
            o = new TService();
            o.service = items[1];
            o.action = items[0];
            o.url = this.url(o.service);
         }
      }
   }
   return o;
}
var RTag = new function(){
   var o = this;
   o.findParent = RTag_findParent;
   o.findChild  = RTag_findChild;
   o.cbSync     = RTag_cbSync;
   o.goPage     = RTag_goPage;
   RMemory.register('RTag', o);
   return o;
}
function RTag_findParent(oHtml, sType){
   if(!oHtml)
      return false;
   if(oHtml.tagName == sType.toUpperCase())
      return oHtml;
   if(!oHtml.parentElement)
      return false;
   return this.GetParent(oHtml.parentElement, sType);
}
function RTag_findChild(oHtml, sType, nPosition){
   if(!nPosition)
      nPosition = 0;
   if(!oHtml)
      return false;
   var nCount = oHtml.children.length;
   for(var i=0; i<nCount; i++){
      var oChild = oHtml.children[i];
      if(oChild.tagName == sType.toUpperCase())
         return oChild;
   }
   return false;
}
function RTag_cbSync(self, sync, trueValue, falseValue){
   if(self && sync){
      sync.value = self.checked ? trueValue : falseValue;
   }
}
function RTag_goPage(f, t, u, m){
   f = RHtml.form(f);
   if(f){
      u = RString.nvl(u);
      if(m){
         u += '?do=' + m;
      }
      f.action = u;
      if(t){
         f.target = t;
      }
      f.method = 'POST';
      f.submit();
   }
}
function TActionServiceArg(fn, da){
   var o = this;
   o.formName   = fn;
   o.dataAction = da;
   o.service    = null;
   o.action     = null;
   o.rows       = new TList();
   o.actionType = 'process';
   o.values     = null;
   o.resultRows = null;
   o.callback   = null;
   o.toNode     = TActionServiceArg_toNode;
   o.invoke     = TActionServiceArg_invoke;
   return o;
}
function TActionServiceArg_toNode(){
   var o = this;
   var nf = new TNode('Form');
   nf.set('name', o.dsName);
   nf.set('data_action', o.actName);
   var rs = o.rows;
   if(o.values && o.values.count > 0){
      var nd = new TNode('Row', o.values);
      nf.push(nd);
   }
   return nf;
}
function TActionServiceArg_invoke(){
   var o = this;
   if(o.callback){
      o.callback.invoke(o);
   }
}
function TActive(owner, run){
   var o = this;
   o.count    = 0;
   o.owner    = owner;
   o.status   = EActive.Active;
   o.active   = true;
   o.interval = 100;
   o.run      = run;
   o.process  = TActive_process;
   return o;
}
function TActive_process(n){
   var o = this;
   o.count -= n;
   if(o.count < 0){
      if(o.run){
         if(o.owner){
            o.run.call(o.owner, o);
         }else{
            o.run(o);
         }
      }
      o.count = o.interval;
   }
}
function TChartArg(n, c, cn){
   var o = this;
   o.callback        = null;
   o.name            = n;
   o.chart           = c;
   o.control         = cn;
   o.attributes      = new TAttributes();
   o.set             = TChartArg_set;
   o.push            = TChartArg_push;
   o.toNode          = TChartArg_toNode;
   o.invoke          = TChartArg_invoke;
   o.invokeSuccess   = TChartArg_invokeSuccess;
   return o;
}
function TChartArg_set(n, i, dn){
   var o = this;
   o.name = n;
   o.id   = i;
   o.dsName = dn;
}
function TChartArg_push(r){
   var o = this;
   var nd = new TNode('Row');
   r.saveNode(nd);
   var rd = o.dataset.createRow();
   rd.loadNode(nd);
}
function TChartArg_toNode(){
   var o = this;
   var root = new TNode("Form");
   root.set("name", o.name);
   root.set("id", o.id);
   root.set("path", o.path);
   var ds = new TNode("Dataset");
   ds.set("name", o.dsName);
   root.push(ds);
   var rs = o.dataset.rows;
   var rc = rs.count;
   for(var n=0; n<rc; n++){
      var r = rs.get(n);
      var rn = new TNode('Row');
      r.saveNode(rn);
      ds.push(rn);
   }
   return root;
}
function TChartArg_invoke(){
   var o = this;
   if(o.callback){
      o.callback.invoke(o);
   }
}
function TChartArg_invokeSuccess(){
   var o = this;
   if(o.callbackSuccess){
      return o.callbackSuccess.invoke(o);
   }
   return false;
}
function TCodeListServiceArg(n){
   var o = this;
   o.callback     = null;
   o.name         = n;
   o.resultConfig = null;
   o.values       = new TList();
   o.toNode       = TCodeListServiceArg_toNode;
   o.invoke       = TCodeListServiceArg_invoke;
   return o;
}
function TCodeListServiceArg_toNode(){
   var o = this;
   if(o.values && o.values.count > 0){
      var nd = new TNode('Values', o.values.get(0).toAttributes());
      return nd;
   }
}
function TCodeListServiceArg_invoke(){
   var o = this;
   if(o.callback){
      o.callback.invoke(o);
   }
}
function TDatasetCompleteArg(fn, c, v){
   var o = this;
   o.callback     = null;
   o.formName     = fn;
   o.control      = c;
   o.value        = v;
   o.resultConfig = null;
   o.set          = TDatasetCompleteArg_set;
   o.toNode       = TDatasetCompleteArg_toNode;
   o.invoke       = TDatasetCompleteArg_invoke;
   return o;
}
function TDatasetCompleteArg_set(n, c, v){
   var o = this;
   o.name     = n;
   o.control  = c;
   o.value    = v;
}
function TDatasetCompleteArg_toNode(){
   var o = this;
   var n = new TNode('Control');
   n.set('form', o.formName);
   n.set('control', o.control);
   n.set('value', o.value);
   return n;
}
function TDatasetCompleteArg_invoke(){
   var o = this;
   if(o.callback){
      o.callback.invoke(o);
   }
}
function TDatasetFetchArg(n, id, ps, p, r, f, pt, re){
   var o = this;
   o.formName = n;
   o.formId   = id;
   o.pageSize = ps;
   o.page     = p;
   o.reset    = r;
   o.force    = f;
   o.path     = pt;
   o.research = re;
   o.searchs  = new TSearchItems();
   o.orders   = new TOrderItems();
   o.values   = new TAttributes();
   o.callback = null;
   o.resultDatasets = new TMap();
   o.push     = TDatasetFetchArg_push;
   o.toNode   = TDatasetFetchArg_toNode;
   o.invoke   = TDatasetFetchArg_invoke
   return o;
}
function TDatasetFetchArg_push(v){
   var o = this;
   if(RClass.isClass(v, TSearchItem)){
      o.searchs.push(v);
   }else if(RClass.isClass(v, TOrderItem)){
      o.orders.push(v);
   }
}
function TDatasetFetchArg_toNode(){
   var o = this;
   var n = new TNode('Form');
   n.set('name', o.formName);
   n.set('id', o.formId);
   n.set('page', o.page);
   n.set('page_size', o.pageSize);
   n.set('path', o.path);
   if(o.research){
      n.set('research', o.research);
   }
   var ss = o.searchs;
   if(ss.count > 0){
      var ns = n.create('Search');
      var sl = ss.count;
      for(var m=0; m<sl; m++){
         ns.push(ss.get(m).toNode());
      }
   }
   var so = o.orders;
   if(so.count > 0){
      var no = n.create('Order');
      var ol = so.count;
      for(var m=0; m<ol; m++){
         no.push(so.get(m).toNode());
      }
   }
   if(o.values && o.values.count > 0){
      var nd = new TNode('Values', o.values);
      n.push(nd);
   }
   return n;
}
function TDatasetFetchArg_invoke(){
   var o = this;
   if(o.callback){
      o.callback.invoke(o);
   }
}
function TDatasetLovArg(n, cp){
   var o = this;
   o.callback    = null;
   o.name        = n;
   o.controlPath = cp;
   o.dataset     = new TDataset();
   o.lovNode     = null;
   o.set         = TDatasetLovArg_set;
   o.push        = TDatasetLovArg_push;
   o.toNode      = TDatasetLovArg_toNode;
   o.invoke      = TDatasetLovArg_invoke;
   return o;
}
function TDatasetLovArg_set(n, cp){
   var o = this;
   o.name = n;
   o.controPath = cp;
}
function TDatasetLovArg_push(r){
   var o = this;
   var nd = new TNode('Row');
   r.saveNode(nd);
   var rd = o.dataset.createRow();
   rd.loadNode(nd);
}
function TDatasetLovArg_toNode(){
   var o = this;
   var root = new TNode("Form");
   root.set("name", o.name);
   root.set("control", o.controlPath);
   var dsn = new TNode("Dataset");
   dsn.set("name", o.dsName);
   root.push(dsn);
   var ds = o.dataset;
   var len = ds.total;
   for(var n = 0;  n<len; n++){
      var r = ds.rows.get(n);
      var nd = new TNode('Row');
      r.loadNode(nd);
      dsn.push(nd);
   }
   return root;
}
function TDatasetLovArg_invoke(){
   var o = this;
   if(o.callback){
      o.callback.invoke(o);
   }
}
function TDatasetPrepareArg(n, i){
   var o = this;
   o.callback       = null;
   o.callbackSuccess = null;
   o.name           = n;
   o.id             = i;
   o.dataset        = new TDataset();
   o.resultDataset  = new TDataset();
   o.resultDatasets = new TMap();
   o.values         = new TAttributes();
   o.set            = TDatasetPrepareArg_set;
   o.toNode         = TDatasetPrepareArg_toNode;
   o.push           = TDatasetPrepareArg_push;
   o.invoke         = TDatasetPrepareArg_invoke;
   o.invokeSuccess  = TDatasetPrepareArg_invokeSuccess;
   return o;
}
function TDatasetPrepareArg_set(n, i, dn){
   var o = this;
   o.name = n;
   o.id   = i;
   o.dsName    = dn;
}
function TDatasetPrepareArg_push(r){
   var o = this;
   var nd = new TNode('Row');
   r.saveNode(nd);
   var rd = o.dataset.createRow();
   rd.loadNode(nd);
}
function TDatasetPrepareArg_toNode(){
   var o = this;
   var root = new TNode("Form");
   root.set("name", o.name);
   root.set("id", o.id);
   var dsn = new TNode("Dataset");
   dsn.set("name", o.dsName);
   var ds = o.dataset;
   var len = ds.total;
   for(var n = 0; n < len; n++){
      var r = ds.rows.get(n);
      var nd = new TNode('Row');
      r.saveNode(nd);
      dsn.push(nd);
   }
   if(o.values && o.values.count > 0){
      var td = new TNode('Values', o.values);
      dsn.push(td);
   }
   root.push(dsn);
   return root;
}
function TDatasetPrepareArg_invoke(){
   var o = this;
   if(o.callback){
      o.callback.invoke(o);
   }
}
function TDatasetPrepareArg_invokeSuccess(){
   var o = this;
   if(o.callbackSuccess){
      return o.callbackSuccess.invoke(o);
   }
   return false;
}
function TDatasetScalarArg(c, u, p){
   var o = this;
   o.callback     = null;
   o.control      = c;
   o.ouid         = u;
   o.parameters   = p;
   o.resultConfig = null;
   o.result       = null;
   o.set          = TDatasetScalarArg_set;
   o.toNode       = TDatasetScalarArg_toNode;
   o.invoke       = TDatasetScalarArg_invoke;
   return o;
}
function TDatasetScalarArg_set(n, c, v){
   var o = this;
   o.name     = n;
   o.control  = c;
   o.value    = v;
}
function TDatasetScalarArg_toNode(){
   var o = this;
   var n = new TNode('Control');
   n.set('form', o.control.topControl().name);
   n.set('control', o.control.fullPath());
   n.set('ouid', o.ouid);
   if(o.parameters){
      n.set('parameters', o.parameters.pack());
   }
   return n;
}
function TDatasetScalarArg_invoke(){
   var o = this;
   if(o.callback){
      o.callback.invoke(o);
   }
}
function TDatasetServiceArg(fn, da){
   var o = this;
   o.formName    = fn;
   o.controlName = null;
   o.dataAction  = da;
   o.service     = null;
   o.action      = null;
   o.attributes  = new TAttributes();
   o.codes       = null;
   o.rows        = null;
   o.actionType  = 'process';
   o.values      = null;
   o.resultRows  = null;
   o.callback    = null;
   o.hasData     = TDatasetServiceArg_hasData;
   o.toNode      = TDatasetServiceArg_toNode;
   o.invoke      = TDatasetServiceArg_invoke;
   o.push        = TDatasetServiceArg_push;
   return o;
}
function TDatasetServiceArg_hasData(){
   var o = this;
   if(o.rows){
      return !o.rows.isEmpty();
   }
   return false;
}
function TDatasetServiceArg_toNode(){
   var o = this;
   var nf = new TNode('Form');
   nf.set('name', o.formName);
   nf.set('control_name', o.controlName);
   nf.set('data_action', o.dataAction);
   nf.push(new TNode('Dataset', o.attributes));
   nf.push(new TNode('Code', o.codes));
   var rs = o.rows;
   if(rs){
      var c = rs.count;
      for(var n=0; n<c; n++){
         nf.push(new TNode('Row', rs.get(n)));
      }
   }
   if(o.values && o.values.count > 0){
      var nd = new TNode('Values', o.values);
      nf.push(nd);
   }
   return nf;
}
function TDatasetServiceArg_invoke(){
   var o = this;
   if(o.callback){
      o.callback.invoke(o);
   }
}
function TDatasetServiceArg_push(r){
   var o = this;
   if(r){
      var rs = o.rows;
      if(!rs){
         rs = o.rows = new TList();
      }
      rs.push(r);
   }
}
function TDatasetTreeServiceArg(fn, id, pt, ac, sv){
   var o = this;
   o.callback     = null;
   o.formName     = fn;
   o.formId       = id;
   o.path         = pt;
   o.action       = ac;
   o.service      = sv;
   o.resultConfig = null;
   o.rows         = new TList();
   o.toNode       = TDatasetTreeServiceArg_toNode;
   o.invoke       = TDatasetTreeServiceArg_invoke;
   return o;
}
function TDatasetTreeServiceArg_toNode(){
   var o = this;
   var n = new TNode('Form');
   n.set('name', o.formName);
   n.set('id', o.formId);
   n.set('path', o.path);
   var rp = null;
   if(o.rows && o.rows.count > 0){
      rp = new TNode("RowPath");
      var ct = o.rows.count;
      for(var k = 0; k < ct; k++){
         rp.push(new TNode('Row', o.rows.get(k)));
      }
   }
   if(rp){
      n.push(rp);
   }
   return n;
}
function TDatasetTreeServiceArg_invoke(){
   var o = this;
   if(o.callback){
      o.callback.invoke(o);
   }
}
function TDatasetTreeViewArg(n, i){
   var o = this;
   g.action         = 'update';
   o.callback       = null;
   o.name           = n;
   o.id             = i;
   o.treeNode       = null;
   o.resultDataset  = new TDataset();
   o.values         = null;
   o.set            = TDatasetTreeViewArg_set;
   o.toNode         = TDatasetTreeViewArg_push;
   o.push           = TDatasetTreeViewArg_toNode;
   o.invoke         = TDatasetTreeViewArg_invoke;
   return o;
}
function TDatasetTreeViewArg_set(n, i, dn){
   var o = this;
   o.name = n;
   o.id   = i;
   o.dsName = dn;
}
function TDatasetTreeViewArg_push(r){
   var o = this;
   var nd = new TNode('Row');
   r.saveNode(nd);
   var rd = o.dataset.createRow();
   rd.loadNode(nd);
}
function TDatasetTreeViewArg_toNode(){
   var o = this;
}
function TDatasetTreeViewArg_invoke(){
   var o = this;
   if(o.callback){
      o.callback.invoke(o);
   }
}
function TDatasetUpdateArg(n, i, dn, pt){
   var o = this;
   o.callback        = null;
   o.callbackSuccess = null;
   o.name            = n;
   o.id              = i;
   o.dsName          = dn;
   o.path            = pt
   o.dataset         = new TDataset();
   o.resultDataset   = new TDataset();
   o.checked         = null;
   o.actionType      = 'update';
   o.processFinish   = false;
   o.hasData         = TDatasetUpdateArg_hasData;
   o.set             = TDatasetUpdateArg_set;
   o.push            = TDatasetUpdateArg_push;
   o.toNode          = TDatasetUpdateArg_toNode;
   o.invoke          = TDatasetUpdateArg_invoke;
   o.invokeSuccess   = TDatasetUpdateArg_invokeSuccess;
   return o;
}
function TDatasetUpdateArg_hasData(){
   var o = this;
   if(o.dataset){
      return !o.dataset.rows.isEmpty();
   }
   return false;
}
function TDatasetUpdateArg_set(n, i, dn){
   var o = this;
   o.name = n;
   o.id   = i;
   o.dsName = dn;
}
function TDatasetUpdateArg_push(r){
   var o = this;
   var nd = new TNode('Row');
   r.saveNode(nd);
   var rd = o.dataset.createRow();
   rd.loadNode(nd);
}
function TDatasetUpdateArg_toNode(){
   var o = this;
   var root = new TNode("Form");
   root.set("name", o.name);
   root.set("id", o.id);
   root.set("path", o.path);
   var ds = new TNode("Dataset");
   ds.set("name", o.dsName);
   root.push(ds);
   var rs = o.dataset.rows;
   var rc = rs.count;
   for(var n=0; n<rc; n++){
      var r = rs.get(n);
      var rn = new TNode('Row');
      r.saveNode(rn);
      ds.push(rn);
   }
   return root;
}
function TDatasetUpdateArg_invoke(){
   var o = this;
   if(o.callback){
      o.callback.invoke(o);
   }
}
function TDatasetUpdateArg_invokeSuccess(){
   var o = this;
   if(o.callbackSuccess){
      return o.callbackSuccess.invoke(o);
   }
   return false;
}
function TEvent(owner, code, proc){
   var o = this;
   o.owner     = owner;
   o.code      = code;
   o.type      = null;
   o.onProcess = proc;
   o.isBefore  = TEvent_isBefore;
   o.isAfter   = TEvent_isAfter;
   o.process   = TEvent_process;
   o.dump      = TEvent_dump;
   return o;
}
function TEvent_isBefore(){
   return (EEventType.Before == this.type);
}
function TEvent_isAfter(){
   return (EEventType.After == this.type);
}
function TEvent_process(){
   var o = this;
   if(!o.onProcess){
      return RMessage.fatal(o, null, 'Process event is null. (owner={0})', RClass.dump(o.owner));
   }
   var sp = new TSpeed(o, 'Process event (owner={0}, process={1})', o.owner, RMethod.name(o.onProcess));
   if(o.owner){
      o.onProcess.call(o.owner, o);
   }else{
      o.onProcess();
   }
   sp.record();
}
function TEvent_dump(){
   return RClass.typeOf(this) + ' [' + this.owner + ',' + this.type + '-' + this.code + ']';
}
function THistory(){
   var o = this;
   o.action = null;
   o.form   = null;
   return o;
}
function TMessageArg(c, f){
   var o = this;
   o.config = c;
   o.form   = f;
   return o;
}
function TMonitor(){
   var o = this;
   o._count   = 0;
   o.status   = EMonitor.Active;
   o.interval = 100;
   o.run      = null;
   o.process  = TMonitor_process;
   return o;
}
function TMonitor_process(n){
   this._count -= n;
   if(this._count < 0){
      if(this.run){
         this.run();
      }
      this._count = this.interval;
   }
}
function TPage(){
   var o = this;
   o.page       = null;
   o.action     = null;
   o.url        = null;
   o.attributes = null;
   o.attrs      = TPage_attrs;
   o.split      = TPage_split;
   o.post       = TPage_post;
   o.get        = TPage_get;
   return o;
}
function TPage_split(s){
   if(s){
      this.attrs().split(s, '=', '\n');
   }
}
function TPage_attrs(){
   var o = this;
   if(!o.attributes){
      o.attributes = new TAttributes();
   }
   return o.attributes;
}
function UrlEncode(str){
  var ret="";
  var strSpecial="!\"#$%&'()*+,/:;<=>?[]^`{|}~%";
  for(var i=0;i<str.length;i++){
   var chr = str.charAt(i);
    var c=str2asc(chr);
    tt += chr+":"+c+"n";
    if(parseInt("0x"+c) > 0x7f){
      ret+="%"+c.slice(0,2)+"%"+c.slice(-2);
    }else{
      if(chr==" ")
        ret+="+";
      else if(strSpecial.indexOf(chr)!=-1)
        ret+="%"+c.toString(16);
      else
        ret+=chr;
    }
  }
  return ret;
}
function TPage_post(form, target){
   var o = this;
   RConsole.find(FEnvConsole).buildValue();
   var url = RString.nvl(o.url);
   if(o.action){
      if(-1 == url.indexOf('?')){
         url += '?do=' + o.action;
      }else{
         url += '&do=' + o.action;
      }
   }
   var as = o.attributes;
   if(as){
      var s = as.join('=', '&');
      if(-1 == url.indexOf('?')){
         url += '?' + s;
      }else{
         url += '&' + s;
      }
   }
   form.action = encodeURI(url);
   if(target){
      form.target = target;
   }
   form.method = 'POST';
   form.submit();
}
function TPage_get(){
}
function TService(){
   var o = this;
   o.service = null;
   o.action  = null;
   o.url     = null;
   return o;
}
function TUploadArg(){
   var o = this;
   o.type          = null;
   o.url           = null;
   o.fileName      = null;
   o.lsnsStart     = new TListeners();
   o.lsnsUploading = new TListeners();
   o.lsnsFinish    = new TListeners();
   return o;
}
