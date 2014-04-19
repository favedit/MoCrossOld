var EForm = new function(){
   var o = this;
   o.Form  = 'F';
   o.Lov   = 'L'
   return o;
}
function EUploadStatusFace(){
   var o = this;
   o.Local     = 'L';
   o.Uploading = 'U';
   o.Uploaded  = 'D';
   return o;
}
var EUploadStatus = new EUploadStatusFace();
function FAttachment(o){
   o = RClass.inherits(this, o, FEditControl, MEditBorder);
   o.attributes       = null;
   o.files            = null;
   o.cfgBt            = null;
   o.uploadAble       = null;
   o.onBuildEdit      = FAttachment_onBuildEdit;
   o.construct        = FAttachment_construct;
   o.clearValue       = FAttachment_clearValue;
   o.clearItems       = FAttachment_clearItems;
   o.setText          = FAttachment_setText;
   o.editWidth        = '100%';
   o.editHeight       = '100%';
   o.borderStyle      = EBorder.Round;
   o.toolBar          = null;
   o.type             = null;
   o.perpareItem      = null;
   o.items            = new TList();
   o._selectItem      = null;
   o.newNode          = null;
   o.fileTree         = null;
   o.configUrl        = RClass.register(o, new TPtyStr('configUrl'));
   o.configDisp       = RClass.register(o, new TPtyStr('configDisp'));
   o.hItemsPanel      = null;
   o.onFileSelected   = FAttachment_onFileSelected;
   o.onUploaded       = FAttachment_onUploaded;
   o.onDeleteAfter    = FAttachment_onDeleteAfter;
   o.onClickUpload    = FAttachment_onClickUpload;
   o.onClickDownload  = FAttachment_onClickDownload;
   o.onClickDelete    = FAttachment_onClickDelete;
   o.onClickConfig    = FAttachment_onClickConfig;
   o.makeMimePath     = FAttachment_makeMimePath;
   o.makeIconPath     = FAttachment_makeIconPath;
   o.makeDownloadPath = FAttachment_makeDownloadPath;
   o.refreshButtons   = FAttachment_refreshButtons;
   o.appendItem       = FAttachment_appendItem;
   o.syncItem         = FAttachment_syncItem;
   o.deleteItem       = FAttachment_deleteItem;
   o.selectItem       = FAttachment_selectItem;
   o.downloadItem     = FAttachment_downloadItem;
   o.text             = RMethod.empty;
   o.setEditable      = FAttachment_setEditable;
   o.dispose          = FAttachment_dispose;
   return o;
}
function FAttachment_onBuildEdit(b){
   var o = this;
   var hf = o.hFrom = RBuilder.appendTable(b.hPanel);
   hf.width = '100%';
   hf.height = '100%';
   var htr = o.hTitleBar = hf.insertRow();
   var h = o.hTitlePanel = htr.insertCell();
   h.height = 22;
   var tb = o.toolBar = RClass.create(FToolBar);
   tb.width = '100%';
   var tbn = o.uploadButton = RClass.create(FToolButton);
   tbn.icon = 'ctl.FAttachment_Upload';
   tbn.label = RContext.get('FAttachment:Upload')
   tbn.lsnsClick.register(o, o.onClickUpload);
   tb.push(tbn);
   var tbn = RClass.create(FToolButtonSplit);
   tb.push(tbn);
   var tbn = o.downloadButton = RClass.create(FToolButton);
   tbn.icon = 'ctl.FAttachment_Download';
   tbn.label = RContext.get('FAttachment:Download');
   tbn.lsnsClick.register(o, o.onClickDownload);
   tb.push(tbn);
   var tbn = o.deleteButton = RClass.create(FToolButton);
   tbn.icon = 'ctl.FAttachment_Delete';
   tbn.label = RContext.get('FAttachment:Delete');
   tbn.lsnsClick.register(o, o.onClickDelete);
   tb.push(tbn);
   var tbn = o.configButton = RClass.create(FToolButton);
   tbn.icon = 'ctl.ctl';
   tbn.label = RContext.get('FAttachment:Config');
   tbn.lsnsClick.register(o, o.onClickConfig);
   tb.push(tbn);
   tb.psBuild(h);
   tb.hPanel.style.borderLeftColor = '#E4EFFD';
   tb.hPanel.style.borderTopColor = '#E4EFFD';
   var hip = o.hItemsPanel = hf.insertRow().insertCell();
   var hi = o.hItems = RBuilder.append(hip, 'DIV')
   hi.style.padding = 4;
   hi.style.width = '100%';
   hi.style.height = '100%';
   hi.style.overflowY = 'auto';
}
function FAttachment_construct(){
   var o = this;
   o.base.FEditControl.construct.call(o);
   o.attributes = new TAttributes();
   o.files = new TStrings();
}
function FAttachment_clearValue(){
   this.clearItems();
}
function FAttachment_clearItems(p){
   var o = this;
   var s = o.items;
   var c = s.count;
   for(var n=RInteger.nvl(p); n<c; n++){
      s.get(n).setVisible(false);
   }
}
function FAttachment_setText(v){
   var o = this;
   var as = o.attributes;
   as.clear();
   as.unpack(v);
   o.recordCode = as.get('code');
   o.recordGuid = as.get('guid');
   if('Y' == o.configDisp) {
	  o.cfgBt = as.get('CB');
	  o.uploadAble = as.get('UB');
   }
   var fs = o.files;
   fs.clear();
   fs.unpack(as.get('files'));
   if(!fs.isEmpty()){
      c = fs.count;
      for(var n=0; n<c; n++){
         o.syncItem(n).set(fs.get(n));
      }
   }
   o.clearItems(fs.count);
   o._selectItem = null;
   o.refreshButtons();
}
function FAttachment_onFileSelected(ui){
   var o = this;
   var ui = o.perpareItem;
   o.perpareItem = null;
   o.pushItem(ui);
}
function FAttachment_onClickDownload(){
   var o = this;
   var si = o._selectItem;
   if(si){
      o.downloadItem(si);
   }
}
function FAttachment_onClickDelete(){
   var o = this;
   var si = o._selectItem;
   if(si){
      o.deleteItem(si);
   }
}
function FAttachment_onClickConfig(){
   var o = this;
   if(o.configUrl){
      var f = RFormSpace.nextForm(o.configUrl);
      f.dsValues = o.topControl().toDeepAttributes();
      f.dsUpdate();
   }
}
function FAttachment_onClickUpload(){
   var o = this;
   var uw = RConsole.find(FUploadConsole).findWindow();
   var tc = o.topControl();
   uw.fileEdit = true;
   uw.typeCode = 'A';
   uw.recordCode = o.recordCode;
   uw.recordGuid = o.recordGuid;
   uw.recordName = null;
   uw.lsnsUploaded.register(o, o.onUploaded);
   uw.show();
}
function FAttachment_onUploaded(s, g){
   var o = this;
   var c = o.appendItem();
   c.link(g.attributes);
}
function FAttachment_refreshButtons(){
   var o = this;
   var si = o._selectItem;
   if ('Y' == o.configDisp) {
	 o.configButton.setVisible(RBoolean.isTrue(o.cfgBt));
	 if ('I' == o._emode) {
		o.uploadButton.setVisible(true);
		o.downloadButton.setVisible(true);
	    o.deleteButton.setVisible(true);
	    o.downloadButton.psEnable(si);
	    o.deleteButton.psEnable(si);
	    return;
	  } else {
	    o.uploadButton.setVisible(RBoolean.isTrue(o.uploadAble));
	  }
	  if(si) {
	     var at = si.attributes;
	     o.downloadButton.setVisible(si.downloadAble);
	     o.deleteButton.setVisible(si.deleteAble);
	  } else {
		  o.downloadButton.setVisible(false);
	      o.deleteButton.setVisible(false);
	  }
   } else{
	  o.configButton.setVisible(false);
   }
   o.downloadButton.psEnable(si);
   o.deleteButton.psEnable(si);
}
function FAttachment_appendItem(){
   var o = this;
   return o.syncItem(o.items.count);
}
function FAttachment_onDeleteAfter(s, g){
   var o = this;
   var bi = g.item;
   o.items.extract(bi);
   o.items.append(bi);
   bi.hide();
   o._selectItem = null;
   o.refreshButtons();
}
function FAttachment_makeMimePath(m){
   var o = this;
   var p = 'unknown'
   if(RFile.isKnown(m)){
      p = RString.toLower(m);
   }
   return top.RContext.context('/ars/img/mime/' + p + '.gif');
}
function FAttachment_makeIconPath(n, g, m){
   var o = this;
   var s = '/svr/' + n + '/sys/' + o.recordCode + '/' + o.recordGuid + '/' + g + '.icon.' + m;
   return top.RContext.context(RString.toLower(s))
}
function FAttachment_makeDownloadPath(n, g, m){
   var o = this;
   var s = '/svr/' + n + '/sys/' + o.recordCode + '/' + o.recordGuid + '/' + g + '.' + m;
   var p = 'Download'
   if(RFile.inPicture(m)){
      p = 'DownloadPicture'
   }
   return top.RContext.context('/apl/logic/transfer/' + p + '.wa?r=') + RString.toLower(s);
}
function FAttachment_deleteItem(bi){
   var o = this;
   if(bi){
      if(confirm(RContext.get('FAttachment:delete.confim', bi.path))){
         var g = new TUploadArg();
         g.item = bi;
         g.guid = bi.guid;
         g.callback = new TInvoke(o, o.onDeleteAfter);
         RConsole.find(FUploadConsole).deleteFile(g);
      }
   }
}
function FAttachment_syncItem(n){
   var o = this;
   var is = o.items;
   var c = is.get(n);
   if(!c){
      for(var i=o.items.count; i<=n; i++){
         c = RControl.create(FBrowserItem, o.hItems);
         c.browser = o;
         o.items.push(c);
      }
   }
   c.setVisible(true);
   return c;
}
function FAttachment_selectItem(bi){
   var o = this;
   var si = o._selectItem;
   if(si){
      si.select(false);
   }
   bi.select(true);
   o._selectItem = bi;
   o.refreshButtons();
}
function FAttachment_downloadItem(bi){
   var o = this;
   var fm = RHtml.form(o.hPanel);
   var t = fm.target;
   fm.target = '_blank';
   fm.action = o.makeDownloadPath(bi.network, bi.guid, bi.mime);
   fm.submit();
   fm.target = t;
}
function FAttachment_setEditable(v){
   var o = this;
   o.hTitleBar.style.display = v ? 'block' : 'none';
   return o.base.FEditControl.setEditable.call(o, v);
}
function FAttachment_dispose(){
   var o = this;
   o.base.FEditControl.dispose.call(o);
   o.toolBar.dispose();
   o.toolBar = null;
   o.hItemsPanel = null;
}
function FBrowser(o){
   o = RClass.inherits(this, o, FEditControl, MEditBorder);
   o.attributes       = null;
   o.files            = null;
   o.construct        = FBrowser_construct;
   o.clearValue       = FBrowser_clearValue;
   o.clearItems       = FBrowser_clearItems;
   o.setText          = FBrowser_setText;
   o.editWidth        = '100%';
   o.editHeight       = '100%';
   o.borderStyle      = EBorder.Round;
   o.toolBar          = null;
   o.type             = null;
   o.perpareItem      = null;
   o.items            = new TList();
   o._selectItem      = null;
   o.newNode          = null;
   o.fileTree         = null;
   o.hItemsPanel      = null;
   o.onBuildEdit      = FBrowser_onBuildEdit;
   o.onFileSelected   = FBrowser_onFileSelected;
   o.onUploaded       = FBrowser_onUploaded;
   o.onDeleteAfter    = FBrowser_onDeleteAfter;
   o.onClickUpload    = FBrowser_onClickUpload;
   o.onClickDownload  = FBrowser_onClickDownload;
   o.onClickDelete    = FBrowser_onClickDelete;
   o.makeMimePath     = FBrowser_makeMimePath;
   o.makeIconPath     = FBrowser_makeIconPath;
   o.makeDownloadPath = FBrowser_makeDownloadPath;
   o.refreshButtons   = FBrowser_refreshButtons;
   o.appendItem       = FBrowser_appendItem;
   o.syncItem         = FBrowser_syncItem;
   o.deleteItem       = FBrowser_deleteItem;
   o.selectItem       = FBrowser_selectItem;
   o.downloadItem     = FBrowser_downloadItem;
   o.text             = RMethod.empty;
   o.setEditable      = FBrowser_setEditable;
   o.dispose          = FBrowser_dispose;
   return o;
}
function FBrowser_construct(){
   var o = this;
   o.base.FEditControl.construct.call(o);
   o.attributes = new TAttributes();
   o.files = new TStrings();
}
function FBrowser_clearValue(){
   this.clearItems();
}
function FBrowser_clearItems(p){
   var o = this;
   var s = o.items;
   var c = s.count;
   for(var n=RInteger.nvl(p); n<c; n++){
      s.get(n).setVisible(false);
   }
}
function FBrowser_setText(v){
   var o = this;
   var as = o.attributes;
   as.clear();
   as.unpack(v);
   o.recordCode = as.get('code');
   o.recordGuid = as.get('guid');
   var fs = o.files;
   fs.clear();
   fs.unpack(as.get('files'));
   if(!fs.isEmpty()){
      c = fs.count;
      for(var n=0; n<c; n++){
         o.syncItem(n).set(fs.get(n));
      }
   }
   o.clearItems(fs.count);
   o._selectItem = null;
   o.refreshButtons();
}
function FBrowser_onBuildEdit(b){
   var o = this;
   var hf = o.hFrom = RBuilder.appendTable(b.hPanel);
   hf.width = '100%';
   hf.height = '100%';
   var htr = o.hTitleBar = hf.insertRow();
   var h = o.hTitlePanel = htr.insertCell();
   h.height = 22;
   var tb = o.toolBar = RClass.create(FToolBar);
   tb.width = '100%';
   var tbn = o.uploadButton = RClass.create(FToolButton);
   tbn.icon = 'ctl.FBrowser_Upload';
   tbn.label = RContext.get('FBrowser:Upload')
   tbn.lsnsClick.register(o, o.onClickUpload);
   tb.push(tbn);
   var tbn = RClass.create(FToolButtonSplit);
   tb.push(tbn);
   var tbn = o.downloadButton = RClass.create(FToolButton);
   tbn.icon = 'ctl.FBrowser_Download';
   tbn.label = RContext.get('FBrowser:Download');
   tbn.lsnsClick.register(o, o.onClickDownload);
   tb.push(tbn);
   var tbn = o.deleteButton = RClass.create(FToolButton);
   tbn.icon = 'ctl.FBrowser_Delete';
   tbn.label = RContext.get('FBrowser:Delete');
   tbn.lsnsClick.register(o, o.onClickDelete);
   tb.push(tbn);
   tb.psBuild(h);
   tb.hPanel.style.borderLeftColor = '#E4EFFD';
   tb.hPanel.style.borderTopColor = '#E4EFFD';
   var hip = o.hItemsPanel = hf.insertRow().insertCell();
   var hi = o.hItems = RBuilder.append(hip, 'DIV')
   hi.style.padding = 4;
   hi.style.width = '100%';
   hi.style.height = '100%';
   hi.style.overflowY = 'auto';
}
function FBrowser_onFileSelected(ui){
   var o = this;
   var ui = o.perpareItem;
   o.perpareItem = null;
   o.pushItem(ui);
}
function FBrowser_onClickDownload(){
   var o = this;
   var si = o._selectItem;
   if(si){
      o.downloadItem(si);
   }
}
function FBrowser_onClickDelete(){
   var o = this;
   var si = o._selectItem;
   if(si){
      o.deleteItem(si);
   }
}
function FBrowser_onClickUpload(){
   var o = this;
   var uw = RConsole.find(FUploadConsole).findWindow();
   var tc = o.topControl();
   uw.recordCode = o.recordCode;
   uw.recordGuid = o.recordGuid;
   uw.lsnsUploaded.register(o, o.onUploaded);
   uw.show();
}
function FBrowser_onUploaded(s, g){
   var o = this;
   var c = o.appendItem();
   c.link(g.attributes);
}
function FBrowser_refreshButtons(){
   var o = this;
   var si = o._selectItem;
   o.downloadButton.psEnable(si);
   o.deleteButton.psEnable(si);
}
function FBrowser_appendItem(){
   var o = this;
   return o.syncItem(o.items.count);
}
function FBrowser_onDeleteAfter(s, g){
   var o = this;
   var bi = g.item;
   o.items.extract(bi);
   o.items.append(bi);
   bi.hide();
   o._selectItem = null;
   o.refreshButtons();
}
function FBrowser_makeMimePath(m){
   var o = this;
   var p = 'unknown'
   if(RFile.isKnown(m)){
      p = RString.toLower(m);
   }
   return top.RContext.context('/ars/img/mime/' + p + '.gif');
}
function FBrowser_makeIconPath(n, g, m){
   var o = this;
   var s = '/svr/' + n + '/sys/' + o.recordCode + '/' + o.recordGuid + '/' + g + '.icon.' + m;
   return top.RContext.context(RString.toLower(s))
}
function FBrowser_makeDownloadPath(n, g, m){
   var o = this;
   var s = '/svr/' + n + '/sys/' + o.recordCode + '/' + o.recordGuid + '/' + g + '.' + m;
   return top.RContext.context('/apl/logic/transfer/Download.wa?r=') + RString.toLower(s);
}
function FBrowser_deleteItem(bi){
   var o = this;
   if(bi){
      if(confirm('确认要删除文件[' + bi.path + ']么？')){
         var g = new TUploadArg();
         g.item = bi;
         g.guid = bi.guid;
         g.callback = new TInvoke(o, o.onDeleteAfter);
         RConsole.find(FUploadConsole).deleteFile(g);
      }
   }
}
function FBrowser_syncItem(n){
   var o = this;
   var is = o.items;
   var c = is.get(n);
   if(!c){
      for(var i=o.items.count; i<=n; i++){
         c = RControl.create(FBrowserItem, o.hItems);
         c.browser = o;
         o.items.push(c);
      }
   }
   c.setVisible(true);
   return c;
}
function FBrowser_selectItem(bi){
   var o = this;
   var si = o._selectItem;
   if(si){
      si.select(false);
   }
   bi.select(true);
   o._selectItem = bi;
   o.refreshButtons();
}
function FBrowser_downloadItem(bi){
   var o = this;
   var fm = RHtml.form(o.hPanel)
   fm.target = '_blank';
   fm.action = o.makeDownloadPath(bi.network, bi.guid, bi.mime);
   fm.submit();
}
function FBrowser_setEditable(v){
   var o = this;
   o.hTitleBar.style.display = v ? 'block' : 'none';
   return o.base.FEditControl.setEditable.call(o, v);
}
function FBrowser_dispose(){
   var o = this;
   o.base.FEditControl.dispose.call(o);
   o.toolBar.dispose();
   o.toolBar = null;
   o.hItemsPanel = null;
}
function FBrowserItem(o){
   o = RClass.inherits(this, o, FControl);
   o.selected          = false;
   o.hForm             = null;
   o.newNode           = null;
   o.downloadAble      = true;
   o.deleteAble        = true;
   o.fileTree          = null;
   o.hMessages         = null;
   o.attributes        = null;
   o.oeBuild           = FBrowserItem_oeBuild;
   o.onBuildPanel      = RBuilder.onBuildDivPanel;
   o.onEnter           = FBrowserItem_onEnter;
   o.onLeave           = FBrowserItem_onLeave;
   o.onClick           = FBrowserItem_onClick;
   o.onDoubleClick     = FBrowserItem_onDoubleClick;
   o.construct         = FBrowserItem_construct;
   o.setLabel          = FBrowserItem_setLabel;
   o.set               = FBrowserItem_set;
   o.link              = FBrowserItem_link;
   o.select            = FBrowserItem_select;
   return o;
}
function FBrowserItem_oeBuild(event){
   var o = this;
   o.base.FControl.oeBuild.call(o, event);
   var hp = o.hPanel;
   hp.style.width = 80;
   hp.style.height = 90;
   hp.style.float = 'left';
   var b = o.border = new TRoundBorder();
   b.build(hp);
   b.hPanel.style.cursor = 'hand';
   b.setColor('#FFFFFF');
   b.hPanel.style.filter = 'progid:DXImageTransform.Microsoft.Gradient(gradienttype=0, startcolorstr=#FFFFFF, endcolorstr=#FFFFFF)';
   var bp = b.hPanel;
   bp.style.padding = 4;
   var hf = o.hForm = RBuilder.appendTable(bp);
   hf.width = '100%';
   hf.height = '100%';
   var hip = o.hIconPanel = hf.insertRow().insertCell();
   hip.align = 'center';
   hip.width = 48;
   hip.height = 48;
   o.hIcon = RBuilder.append(hip, 'IMG');
   var htp = o.hText = hf.insertRow().insertCell();
   htp.align = 'center';
   htp.style.wordBreak = 'break-all';
   return EEventStatus.Stop;
}
function FBrowserItem_onEnter(){
   var o = this;
   if(!o.selected){
      o.border.setColor('#24C2DB');
      o.border.hPanel.filters['DXImageTransform.Microsoft.Gradient'].endcolorstr = '#E0F2FD';
   }
}
function FBrowserItem_onLeave(){
   var o = this;
   if(!o.selected){
      if(o.browser._editable){
         o.border.setColor(EColor.Edit);
         o.border.hPanel.filters['DXImageTransform.Microsoft.Gradient'].endcolorstr = EColor.Edit;
      }else{
         o.border.setColor(EColor.Readonly);
         o.border.hPanel.filters['DXImageTransform.Microsoft.Gradient'].endcolorstr = EColor.Readonly;
      }
   }
}
function FBrowserItem_onClick(){
   this.browser.selectItem(this);
}
function FBrowserItem_onDoubleClick(){
   if(this.downloadAble) {
	 this.browser.downloadItem(this);
   }
}
function FBrowserItem_set(v){
   var o = this;
   var as = o.attributes;
   as.clear();
   as.unpack(v);
   o.link(as);
   var db = as.get('DB');
   if (db) {
	  o.downloadAble = RBoolean.isTrue(db);
	  o.deleteAble = RBoolean.isTrue(as.get('TB'));
   }
}
function FBrowserItem_link(as){
   var o = this;
   o.network = RString.nvl(as.get('n'), as.get('network_code'));
   o.guid = RString.nvl(as.get('g'), as.get('guid'));
   o.mime = RString.nvl(as.get('m'), as.get('mime'));
   o.path = RString.nvl(as.get('p'), as.get('path'));
   o.hText.innerText = o.path;
   if(RFile.isPicture(o.path)){
      o.hIcon.src = o.browser.makeIconPath(o.network, o.guid, o.mime);
   }else{
      o.hIcon.src = o.browser.makeMimePath(o.mime);
   }
}
function FBrowserItem_construct(){
   var o = this;
   o.base.FControl.construct.call(o);
   o.attributes = new TAttributes();
}
function FBrowserItem_setLabel(v){
   this.hText.innerText = v;
}
function FBrowserItem_select(v){
   var o = this;
   o.selected = v;
   var f = o.border.hPanel.filters['DXImageTransform.Microsoft.Gradient'];
   if(v){
      o.border.setColor('#24C2DB');
      f.endcolorstr = '#B4E1FD';
   }else{
      o.border.setColor('#FFFFFF');
      f.endcolorstr = '#FFFFFF';
   }
}
function FButton(o){
   o = RClass.inherits(this, o, FControl, MDisplay, MDesign);
   o.labelPosition      = RClass.register(o, new TPtyStr('labelPosition', EPosition.Left));
   o.icon               = RClass.register(o, new TPtyStr('icon'));
   o.type               = RClass.register(o, new TPtyStr('type'));
   o.action             = RClass.register(o, new TPtyStr('action'));
   o.dataAction         = RClass.register(o, new TPtyStr('dataAction'));
   o.service            = RClass.register(o, new TPtyStr('service'));
   o.target             = RClass.register(o, new TPtyStr('target'));
   o.page               = RClass.register(o, new TPtyStr('page'));
   o.method             = RClass.register(o, new TPtyStr('method'));
   o.iconDisable        = RClass.register(o, new TPtyStr('iconDisable'));
   o.attributes         = RClass.register(o, new TPtyStr('attributes'));
   o.editUrl            = RClass.register(o, new TPtyStr('editUrl'));
   o.editForm           = RClass.register(o, new TPtyStr('editForm'));
   o.stIcon             = RClass.register(o, new TStyle('Icon'));
   o.stLabel            = RClass.register(o, new TStyle('Label'));
   o.stForm             = RClass.register(o, new TStyle('Form'));
   o.stIconPanel        = RClass.register(o, new TStyleIcon('Panel'));
   o.__process          = false;
   o.lsnsClick          = new TListeners();
   o.hForm              = null;
   o.hLeftButton        = null;
   o.hMiddleButton      = null;
   o.hRightButton       = null;
   o.hLabelPanel        = null;
   o.hLabel             = null;
   o.onButtonEnter      = RClass.register(o, new HMouseEnter('onButtonEnter'), FButton_onButtonEnter);
   o.onButtonLeave      = RClass.register(o, new HMouseLeave('onButtonLeave'), FButton_onButtonLeave);
   o.onButtonDown       = RClass.register(o, new HMouseDown('onButtonDown'), FButton_onButtonDown);
   o.onButtonUp         = RClass.register(o, new HMouseUp('onButtonUp'), FButton_onButtonUp);
   o.onButtonClickDelay = FButton_onButtonClickDelay;
   o.onClick            = FButton_onClick;
   o.onButtonClick      = RClass.register(o, new HClick('onButtonClick'), FButton_onButtonClick);
   o.oeBuild            = FButton_oeBuild;
   o.oeMode             = FButton_oeMode;
   o.setLabel           = FButton_setLabel;
   o.setLabelColor      = FButton_setLabelColor;
   o.setLabelStyle      = FButton_setLabelStyle;
   o.doClick            = FButton_doClick;
   o.dispose            = FButton_dispose;
   return o;
}
function FButton_onButtonEnter(e){
   var o = this;
   if(!o._disabled){
	  o.hLeftButton.background = o.styleIconPath('HoverLeft');
	  o.hMiddleButton.background = o.styleIconPath('HoverMiddle');
	  o.hRightButton.background = o.styleIconPath('HoverRight');
   }
}
function FButton_onButtonLeave(e){
   var o = this;
   if(!o._disabled){
	  o.hLeftButton.background = o.styleIconPath('ButtonLeft');
	  o.hMiddleButton.background = o.styleIconPath('Button');
	  o.hRightButton.background = o.styleIconPath('ButtonRight');
   }
}
function FButton_onButtonDown(e){
   var o = this;
   if(!o._disabled){
	  o.hLeftButton.background = o.styleIconPath('PressLeft');
	  o.hMiddleButton.background = o.styleIconPath('PressMiddle');
	  o.hRightButton.background = o.styleIconPath('PressRight');
   }
}
function FButton_onButtonUp(e){
   var o = this;
   if(!o._disabled){
	  o.hLeftButton.background = o.styleIconPath('ButtonLeft');
	  o.hMiddleButton.background = o.styleIconPath('Button');
	  o.hRightButton.background = o.styleIconPath('ButtonRight');
   }
}
function FButton_onButtonClickDelay(e){
   var o = this;
   o.__process = false;
   o.clickActive.status = EActive.Sleep;
}
function FButton_onClick(e){
   this.doClick();
}
function FButton_onButtonClick(e){
   this.doClick();
}
function FButton_oeBuild(e){
   var o = this;
   o.base.FControl.oeBuild.call(o, e);
   var hp = o.hPanel;
   hp.style.paddingTop = o.padTop ? o.padTop : 10;
   hp.style.pixelHeight = 26;
   var hf = o.hForm = RBuilder.appendTable(hp);
   var hr = hf.insertRow();
   hr.height = 22;
   var hl = o.hLeftButton = hr.insertCell();
   hl.width = 3;
   hl.background = o.styleIconPath('ButtonLeft');
   var hm = o.hMiddleButton = hr.insertCell();
   hm.background = o.styleIconPath('Button');
   var hrb = o.hRightButton = hr.insertCell();
   hrb.width = 3;
   hrb.background = o.styleIconPath('ButtonRight');
   hf.style.cursor = 'hand';
   hf.style.border = 0;
   o.attachEvent('onButtonEnter', hf, o.onButtonEnter);
   o.attachEvent('onButtonLeave', hf, o.onButtonLeave);
   o.attachEvent('onButtonDown', hf, o.onButtonDown);
   o.attachEvent('onButtonUp', hf, o.onButtonUp);
   o.attachEvent('onButtonClick', hf);
   var hTb = RBuilder.appendTable(hm);
   var hr  = hTb.insertRow();
   var hc = hr.insertCell();
   hc.width = 10;
   if(o.icon){
      var hc = hr.insertCell();
      hc.width = 16;
      o.hIcon = RBuilder.appendIcon(hc, o.icon);
      hcc = hr.insertCell();
      hcc.width = 4;
   }
   if(o.label){
      var hc = hr.insertCell();
      hc.align = 'center';
      hc.noWrap = true;
      o.hLabel = RBuilder.appendText(hc, o.label);
      o.hLabel.style.font = 'icon';
   }
   var hc = o.hFormEnd = hr.insertCell();
   hc.width = 10;
   o.__process = false;
   var ca = o.clickActive = new TActive(o, o.onButtonClickDelay);
   ca.interval = 500;
   ca.status = EActive.Sleep;
   RConsole.find(FActiveConsole).push(ca);
   return EEventStatus.Stop;
}
function FButton_oeMode(e){
   var o = this;
   o.base.FControl.oeMode.call(o, e);
   o.base.MDisplay.oeMode.call(o, e);
   return EEventStatus.Stop;
}
function FButton_setLabel(v){
   var o = this;
   o.label = v;
   o.hLabel.innerText = v;
   o.hLabel.noWrap = true;
}
function FButton_setLabelColor(c){
   var o = this;
   o.hLabel.style.color = '#FF0000';
}
function FButton_setLabelStyle(c, w, s){
   var o = this;
   o.hLabel.style.color = '#FF0000';
   o.hLabel.style.fontWeight = 'bold';
   o.hLabel.style.fontSize = '12';
}
function FButton_doClick(){
   var o = this;
   if(o.__process){
      return;
   }
   o.__process = true;
   o.clickActive.status = EActive.Active;
   o.lsnsClick.process(this);
   if(o.action){
      eval(o.action);
   }
   if(o.page){
      var form = RHtml.form(o.hButton);
      var p = RPage.parse(o.page);
      if(o.method){
         p.action = o.method;
      }
      p.split(o.attributes);
      var f = o.topControl(MDataset);
      if(f){
         var as = new TAttributes();
         f.saveValue(as);
         if(form && form.form_pack){
            form.form_pack.value = as.pack();
         }
      }
      p.post(form, RString.nvl(o.target, '_self'));
   }
   if(o.editUrl){
      var w = RConsole.find(FButtonConsole).find();
      w.linkUrl(o.editUrl);
      w.show();
   }
   if(o.editForm){
      var w = RConsole.find(FButtonFormConsole).find();
      w.linkForm(o);
      w.show();
   }
}
function FButton_dispose(){
   var o = this;
   o.base.FControl.dispose.call(o);
   o.hForm = null;
   o.hFormEnd = null;
   o.hLabel = null;
}
function FCalendar(o){
   o = RClass.inherits(this, o, FEditControl, MEditBorder, MDropable, MDescCalendar);
   o.editFormat  = RDate.DisplayFormat;
   o.editHour     = RClass.register(o, new TPtyBoolSet('editHour', 'editDate', EDateTimeMode.Hour));
   o.editMinute   = RClass.register(o, new TPtyBoolSet('editMinute', 'editDate', EDateTimeMode.Minute));
   o.editSecond   = RClass.register(o, new TPtyBoolSet('editSecond', 'editDate', EDateTimeMode.Second));
   o.borderStyle = EBorder.RoundDrop;
   o.date        = null;
   o.lsnEditEnd  = null;
   o.hForm       = null;
   o.hDrop       = null;
   o.hForm       = null;
   o.onKeyPress  = FCalendar_onKeyPress;
   o.onDataClick   = FCalendar_onDataClick;
   o.refreshStyle  = FCalendar_refreshStyle;
   o.onEditEnd   = FCalendar_onEditEnd;
   o.onBuildEdit = FCalendar_onBuildEdit;
   o.construct   = FCalendar_construct;
   o.formatValue = FCalendar_formatValue;
   o.formatText  = FCalendar_formatText;
   o.drop        = FCalendar_drop;
   o.doBlur      = FCalendar_doBlur;
   return o;
}
function FCalendar_onDataClick(){
   var o = this;
   if(!o.editCheck){
      o.drop();
   }
}
function FCalendar_onBuildEdit(b){
   var o = this;
   var htb = RBuilder.appendTable(b.hPanel);
    htb.style.tableLayout = 'fixed';
    var hr = o.hEdit = htb.insertRow();
   o.onBuildChange(hr.insertCell())
   var hc = hr.insertCell();
   var h = o.hEdit = RBuilder.appendEdit(hc, o.style('Edit'));
   h.style.disabled = 'true';
   if(o.editLength){
      h.maxLength = o.editLength;
   }
}
function FCalendar_onEditEnd(e){
   var o = this;
   if(e){
      o.set(e.get());
      o._invalidText = o.validText(o.text());
      o.refreshStyle();
   }
   o.onDataEditEnd(o);
}
function FCalendar_onKeyPress(e){
   if(!RString.inChars(String.fromCharCode(e.keyCode), RDate.Chars)){
      RKey.eventClear(e);
   }
}
function FCalendar_construct(){
   var o = this;
   o.base.FEditControl.construct.call(o);
   o.date = new TDate();
   o.lsnEditEnd = new TListener(o, o.onEditEnd);
}
function FCalendar_formatValue(t){
   if(t){
      var o = this;
      if(t.toLowerCase() == '@now'){
         o.date.now();
         return RDate.formatDate(o.date);
      }else{
         RDate.autoParse(o.date, t);
         return RDate.formatDate(o.date);
      }
   }
   return RString.nvl(t);
}
function FCalendar_formatText(value){
   if(value){
      var o = this;
      RDate.autoParse(o.date, value);
      return RDate.formatDate(o.date, o.editFormat);
   }
   return RString.nvl(value);
}
function FCalendar_refreshStyle(){
   var o = this;
   o.base.FEditControl.refreshStyle.call(o);
   if(!o.editCheck){
      o.hEdit.readOnly = 'true';
   }
}
function FCalendar_drop(){
   var o = this;
   if(o.canDrop() && o._editable){
      var e = o.editor = RConsole.find(FEditConsole).focus(o, FCalendarEditor, o.name);
      e.set(o.reget(), o.editFormat);
      e.setHourEditable(o.editHour);
      e.setMinuteEditable(o.editMinute);
      e.setSecondEditable(o.editSecond);
      e.lsnEditEnd = o.lsnEditEnd;
      e.show();
   }
}
function FCalendar_doBlur(){
   var o = this;
   o.base.FEditControl.doBlur.call(o);
   if(o.editor){
      o.editor.hide();
   }
}
function FCalendarEditor(o){
   o = RClass.inherits(this, o, FDropEditor, MFocusLooper);
   o.editFormat       = null;
   o.dataValue        = null;
   o.date             = new TDate();
   o.hTitlePanel      = null;
   o.hYearPrior       = null;
   o.hYear            = null;
   o.hYearNext        = null;
   o.hMonthPrior      = null;
   o.hMonth           = null;
   o.hMonthNext       = null;
   o.hDaysPanel       = null;
   o.hTimePanel       = null;
   o.hTime            = null;
   o.hNow             = null;
   o.hOk              = null;
   o.hCancel          = null;
   o.hHour            = null;
   o.hMinute          = null;
   o.hSecond          = null;
   o.hSelect          = null;
   o.editFormat       = RDate.DisplayFormat;
   o.dateOrg          = new TDate();
   o.dateOrgValue     = null;
   o.dayCells         = new TList();
   o.focusObject      = null;
   o.skipBlur         = false;
   o.styleYearMonth   = RClass.register(o, new TStyle('YearMonth'));
   o.styleButton      = RClass.register(o, new TStyle('Button'));
   o.styleButtonHover = RClass.register(o, new TStyle('ButtonHover'));
   o.styleDay         = RClass.register(o, new TStyle('Day'));
   o.styleDaySel      = RClass.register(o, new TStyle('DaySel'));
   o.styleDayHover    = RClass.register(o, new TStyle('DayHover'));
   o.styleDayFree     = RClass.register(o, new TStyle('DayFree'));
   o.styleDayNone     = RClass.register(o, new TStyle('DayNone'));
   o.styleTitlePanel  = RClass.register(o, new TStyle('TitlePanel'));
   o.styleDaysPanel   = RClass.register(o, new TStyle('DaysPanel'));
   o.styleTimePanel   = RClass.register(o, new TStyle('TimePanel'));
   o.styleMonth       = RClass.register(o, new TStyle('Year'));
   o.styleMonth       = RClass.register(o, new TStyle('Month'));
   o.styleWeek        = RClass.register(o, new TStyle('Week'));
   o.styleTime        = RClass.register(o, new TStyle('Time'));
   o.styleHour        = RClass.register(o, new TStyle('Hour'));
   o.styleSplit       = RClass.register(o, new TStyle('Split'));
   o.styleMinute      = RClass.register(o, new TStyle('Minute'));
   o.styleSecond      = RClass.register(o, new TStyle('Second'));
   o.styleNow         = RClass.register(o, new TStyle('Now'));
   o.styleOk          = RClass.register(o, new TStyle('Ok'));
   o.onDaySelect      = RClass.register(o, new HMouseDown('onDaySelect'), FCalendarEditor_onDaySelect);
   o.onButtonNow      = RClass.register(o, new HMouseDown('onButtonNow'), FCalendarEditor_onButtonNow);
   o.onDateKeyDown    = RClass.register(o, new HKeyDown('onDateKeyDown'), FCalendarEditor_onDateKeyDown);
   o.onDateBlur       = RClass.register(o, new HBlur('onDateBlur'), FCalendarEditor_onDateBlur);
   o.onTimeBlur       = RClass.register(o, new HBlur('onTimeBlur'), FCalendarEditor_onTimeBlur);
   o.onTimeClick      = RClass.register(o, new HClick('onTimeClick'), FCalendarEditor_onTimeClick);
   o.onDayDbClick     = RClass.register(o, new HDoubleClick('onDayDbClick'), FCalendarEditor_onDayDbClick);
   o.onDayEnter       = RClass.register(o, new HMouseEnter('onDayEnter'),    FCalendarEditor_onDayEnter);
   o.onDayOut         = RClass.register(o, new HMouseOut('onDayOut'),        FCalendarEditor_onDayOut);
   o.onButtonOk       = RClass.register(o, new HMouseDown('onButtonOk'),     FCalendarEditor_onButtonOk);
   o.onButtonCancel   = RClass.register(o, new HMouseDown('onButtonCancel'), FCalendarEditor_onButtonCancel);
   o.onButtonOver     = RClass.register(o, new HMouseEnter('onButtonOver'),  FCalendarEditor_onButtonOver);
   o.onButtonOut      = RClass.register(o, new HMouseOut('onButtonOut'),     FCalendarEditor_onButtonOut);
   o.onMdown          = RClass.register(o, new HMouseDown('onMdown'),        FCalendarEditor_onMdown);
   o.onMup            = RClass.register(o, new HMouseUp('onMup'),            FCalendarEditor_onMup);
   o.onBuildDrop      = FCalendarEditor_onBuildDrop;
   o.show             = FCalendarEditor_show;
   o.setMinuteEditable = FCalendarEditor_setMinuteEditable;
   o.setHourEditable   = FCalendarEditor_setHourEditable;
   o.setSecondEditable = FCalendarEditor_setSecondEditable;
   o.buildTitle       = FCalendarEditor_buildTitle;
   o.buildDays        = FCalendarEditor_buildDays;
   o.buildTime        = FCalendarEditor_buildTime;
   o.testBlur         = FCalendarEditor_testBlur;
   o.get              = FCalendarEditor_get;
   o.set              = FCalendarEditor_set;
   o.setDate          = FCalendarEditor_setDate;
   o.storeChange      = FCalendarEditor_storeChange;
   o.daySelectLsns    = new TListeners();
   o.onBuildButton    = FCalendarEditor_onBuildButton;
   o.ohKdown          = FCalendarEditor_ohKdown;
   o.ohDaysChange     = FCalendarEditor_ohDaysChange;
   o.ohKeyCheck       = FCalendarEditor_ohKeyCheck;
   o.onDateAction     = FCalendarEditor_onDateAction;
   o.panel            = FCalendarEditor_panel;
   o.dispose          = FCalendarEditor_dispose;
   return o;
}
function FCalendarEditor_onTimeClick(e){
   var o = this;
   var h = e.hSource;
   if(h.editAble){
      h.select();
   }
}
function FCalendarEditor_onTimeBlur(e){
	var o = this;
    var h = e.hSource;
    if(h == o.hHour){
       h.value = Math.min(RInteger.parse(h.value), 23);
    }else if(h == o.hMinute){
       h.value = Math.min(RInteger.parse(h.value), 59);
    }else if(h == o.hSecond){
       h.value = Math.min(RInteger.parse(h.value), 59);
    }
    o.storeChange();
    o.setDate(o.date);
}
function FCalendarEditor_onDayDbClick(e){
   var o = e.source
   if(RClass.isClass(o, FCalendarEditor) && 0 != RInteger.parse(e.hSource.innerText)){
      o.date.setDay(e.hSource.innerText);
      o.dataValue = RDate.formatDate(o.date);
      o.editEnd();
   }
}
function FCalendarEditor_onDaySelect(e){
   var o = this;
   if(RClass.isClass(o, FCalendarEditor) && 0 != RInteger.parse(e.hSource.innerText)){
	  var h = e.hSource;
	  if(o.hSelect){
		  o.hSelect.style.border = '1 solid #FFFFFF';
	  };
	  o.hSelect = h;
	  h.style.border = '1 solid #2BD6F0';
      o.date.setDay(h.innerText);
   }
}
function FCalendarEditor_onButtonNow(e){
   var o = e.source;
   if(RClass.isClass(o, FCalendarEditor)){
      o.dataValue = RDate.format();
      o.editEnd();
   }
}
function FCalendarEditor_onDateKeyDown(e, he){
   var o = this;
   var h = e.hSource;
   var v = h.value;
   if(EKey.Enter == e.keyCode){
      o.storeChange();
      o.setDate(o.date);
   }else if(EKey.Up == e.keyCode){
      if(h == o.hYear){
         o.hYear.value = RInteger.parse(o.hYear.value) + 1;
      }else if(h == o.hMonth){
         o.hMonth.value = RInteger.parse(o.hMonth.value) + 1;
      }else if(h == o.hHour){
    	  if(o.hHour.editAble){
		     if(v < 23){
			    h.value = RInteger.parse(h.value) + 1;
			 }
    	  }
	  }else if(h == o.hMinute){
		 if(o.hMinute.editAble){
		    if(v < 59){
			   h.value = RInteger.parse(h.value) + 1;
			}
	     }
	  }else{
		  if(o.hSecond.editAble){
		     if(v < 59){
			    h.value = RInteger.parse(h.value) + 1;
		     }
	      }
	  }
      o.storeChange();
      o.setDate(o.date);
   }else if(EKey.Down == e.keyCode){
      if(h == o.hYear){
         o.hYear.value = RInteger.parse(o.hYear.value) - 1;
      }else if(h == o.hMonth){
         o.hMonth.value = RInteger.parse(o.hMonth.value) - 1;
      }else if(h == o.hHour){
    	 if(o.hHour.editAble){
            if(v > 0){
	           h.value = RInteger.parse(h.value) - 1;
	        }
    	 }
	  }else if(h == o.hMinute){
		  if(o.hMinute.editAble){
		     if(v > 0){
	            h.value = RInteger.parse(h.value) - 1;
	         }
		  }
	  }else{
		  if(o.hSecond.editAble){
		     if(v > 0){
		        h.value = RInteger.parse(h.value) - 1;
		     }
		  }
	  }
      o.storeChange();
      o.setDate(o.date);
      h.select();
   }else{
	  if(h == o.hHour || h == o.hMinute || h == o.hSecond){
	     if(h.editAble){
	        RKey.fixChars(he, RDate.Chars);
	     }else{
	        he.keyCode = 0;
	        he.returnValue = false;
	     }
	  }else{
		  RKey.fixChars(he, RDate.Chars);
	  }
   }
}
function FCalendarEditor_onDateBlur(){
   var o = this;
   o.storeChange();
   o.setDate(o.date);
}
function FCalendarEditor_onBuildDrop(){
   var o = this;
   o.hDatePanel = RBuilder.appendTable(o.hDropPanel);
   o.hDropPanel.align = 'center';
   o.hDatePanel.width = '100%';
   var hRow = o.hDatePanel.insertRow();
   var hCell = o.hTitlePanel = hRow.insertCell();
   hCell.colSpan = 2;
   hCell.className = o.style('TitlePanel');
   o.buildTitle();
   var hRow = o.hDatePanel.insertRow();
   var hCell = o.hDaysPanel = hRow.insertCell();
   hCell.colSpan = 2;
   hCell.className = o.style('DaysPanel');
   o.buildDays();
   var hRow = o.hDatePanel.insertRow();
   var hCell = o.hTimePanel = hRow.insertCell();
   o.buildTime();
   o.pushFocus(o.hYear);
   o.pushFocus(o.hMonth);
}
function FCalendarEditor_show(v){
   var o = this;
   o.base.FDropEditor.show.call(o, v);
   var hp = o.hPanel;
   var hbf = o.hBorderForm;
   var s = o.source;
   var r = s.getEditRange();
   hp.style.pixelLeft = r.x;
   hp.style.pixelTop = r.y + r.height;
   hp.style.pixelWidth = 273;
   o.base.MShadow.show.call(o);
}
function FCalendarEditor_buildTitle(){
   var o = this;
   var hTab = RBuilder.appendTable(o.hTitlePanel, null, 0, 5, 1);
   hTab.align = 'center';
   hTab.width = '100%';
   hTab.style.filter = "progid:DXImageTransform.Microsoft.Gradient(startColorStr='#E5FAFE', endColorStr='#FFFFFF', gradientType='0')";
   var hRow = hTab.insertRow();
   var hCel = hRow.insertCell();
   var h = o.hYearPrior = RBuilder.append(hCel, 'SPAN', o.style('Button'));
   h.link = o;
   h.linkAction = o.onDateAction;
   h.innerText = '3';
   o.attachEvent("onButtonOver",h);
   o.attachEvent("onButtonOut",h);
   o.attachEvent("onMdown",h);
   o.attachEvent("onMup",h);
   var hCel = hRow.insertCell();
   var h = o.hYear = RBuilder.append(hCel, 'INPUT', o.style('Year'));
   h.maxLength = '4';
   o.attachEvent('onDateBlur', h, o.onDateBlur);
   o.attachEvent('onDateKeyDown', h, o.onDateKeyDown);
   var hCel = hRow.insertCell();
   hCel.innerText = RContext.get('FCalendarEditor:year');
   hCel.className = o.style('YearMonth');
   var hCel = hRow.insertCell();
   var h = o.hYearNext = RBuilder.append(hCel, 'SPAN', o.style('Button'));
   h.link = o;
   h.linkAction = o.onDateAction;
   h.innerText = '4';
   o.attachEvent("onButtonOver",h);
   o.attachEvent("onButtonOut",h);
   o.attachEvent("onMdown",h);
   o.attachEvent("onMup",h);
   var hCell = hRow.insertCell();
   hCell.width='10';
   var hCel = hRow.insertCell();
   var h = o.hMonthPrior = RBuilder.append(hCel, 'SPAN', o.style('Button'));
   h.link = o;
   h.linkAction = o.onDateAction;
   h.innerText = '3';
   o.attachEvent("onButtonOver",h);
   o.attachEvent("onButtonOut",h);
   o.attachEvent("onMdown",h);
   o.attachEvent("onMup",h);
   var hCel = hRow.insertCell();
   var h = o.hMonth = RBuilder.append(hCel, 'INPUT', o.style('Month'));
   h.maxLength = '2';
   o.attachEvent('onDateBlur', h, o.onDateBlur);
   o.attachEvent('onDateKeyDown', h, o.onDateKeyDown);
   var hCel = hRow.insertCell();
   hCel.innerText = RContext.get('FCalendarEditor:month');
   hCel.className = o.style('YearMonth');
   var hCel = hRow.insertCell();
   var h = o.hMonthNext = RBuilder.append(hCel, 'SPAN', o.style('Button'));
   h.link = o;
   h.linkAction = o.onDateAction;
   h.innerText = '4';
   o.attachEvent("onButtonOver",h);
   o.attachEvent("onButtonOut",h);
   o.attachEvent("onMdown", h);
   o.attachEvent("onMup", h);
}
function FCalendarEditor_buildDays(){
   var o = this;
   var hTab = RBuilder.appendTable(o.hDaysPanel, null, 0, 0, 1);
   hTab.width = '100%';
   var weekDays = RContext.get('FCalendarEditor:weekdays').split(',');
   var count = weekDays.length;
   var hWeekRow = hTab.insertRow();
   for(var n=0; n<count; n++){
      var h = hWeekRow.insertCell();
      h.className = o.style('Week');
      h.align = 'center';
      h.innerText = weekDays[n];
   }
   for(var n=0; n<6; n++){
      var hRow = hTab.insertRow();
      for(var i=0; i<count; i++){
         var h = hRow.insertCell();
         h.link = o;
         h.className = o.style('DayNone');
         o.attachEvent("onDayEnter", h);
         o.attachEvent("onDayOut", h);
         o.attachEvent("onDaySelect", h);
         o.attachEvent("onDayDbClick", h);
         h.innerText = '.';
         o.dayCells.push(h);
      }
   }
}
function FCalendarEditor_buildTime(){
   var o = this;
   var hTab = RBuilder.appendTable(o.hTimePanel, null, 0, 1, 1);
   var ht = o.hTimePanel;
   ht.style.filter = "progid:DXImageTransform.Microsoft.Gradient(startColorStr='#FFFFFF', endColorStr='#E5FAFE', gradientType='0')";
   var hRow = hTab.insertRow();
   var hb1 = hRow.insertCell();
   hb1.width = 5;
   var hl = hRow.insertCell();
   hl.width = 50;
   hl.style.color = '#1F8FB7';
   hl.style.fontWeight = 'BOLD';
   hl.innerText='时间:';
   var hc = hRow.insertCell();
   var hb = RBuilder.appendTable(hc, null, 0, 0, 0);
   hc.style.border = '1 solid #2BD6F0';
   hc.style.backgroundColor = '#FFFFFF';
   var hr = hb.insertRow();
   var hh =hr.insertCell();
   var hHour = o.hHour = RBuilder.appendEdit(hh, o.style('Hour'));
   hHour.maxLength = 2;
   o.attachEvent("onTimeClick", hHour);
   o.attachEvent("onDateKeyDown", hHour, o.onDateKeyDown);
   o.attachEvent("onTimeBlur", hHour, o.onTimeBlur);
   var hs1 = hr.insertCell();
   hs1.innerText = ':';
   var hm = hr.insertCell();
   var hMinute = o.hMinute = RBuilder.appendEdit(hm, o.style('Minute'));
   hMinute.maxLength = 2;
   o.attachEvent("onTimeClick", hMinute);
   o.attachEvent("onDateKeyDown", hMinute, o.onDateKeyDown);
   o.attachEvent("onTimeBlur", hMinute, o.onTimeBlur);
   var hs2 = hr.insertCell();
   hs2.innerText = ':';
   var hs = hr.insertCell();
   var hSecond = o.hSecond = RBuilder.appendEdit(hs, o.style('Second'));
   hSecond.maxLength = 2;
   o.attachEvent("onTimeClick", hSecond);
   o.attachEvent("onDateKeyDown", hSecond, o.onDateKeyDown);
   o.attachEvent("onTimeBlur", hSecond, o.onTimeBlur);
   var hb2 = hRow.insertCell();
   hb2.width = 50;
   var hn = hRow.insertCell();
   hn.style.display = 'none';
   var hNow = o.hNow = RBuilder.append(hn, 'SPAN', o.style('Now'));
   hNow.style.width = 50;
   hn.style.border='1 solid #2BD6F0';
   hNow.innerText = RContext.get('FCalendarEditor:now');
   hNow.style.display = 'none';
   hNow.link = o;
   o.attachEvent("onButtonNow", hNow);
   var hc = hRow.insertCell();
   var hCl = o.hCancel = RBuilder.append(hc, 'SPAN', o.style('Ok'));
   hCl.style.width = 50;
   hc.style.border='1 solid #2BD6F0';
   hCl.link = o;
   o.attachEvent("onButtonCancel", hCl);
   hCl.innerText = RContext.get('FCalendarEditor:cancel');
   var ho = hRow.insertCell();
   var hOk = o.hOk = RBuilder.append(ho, 'SPAN', o.style('Ok'));
   hOk.style.width = 50;
   ho.style.border='1 solid #2BD6F0';
   hOk.link = o;
   o.attachEvent("onButtonOk", hOk);
   hOk.innerText = RContext.get('FCalendarEditor:ok');
}
function FCalendarEditor_testBlur(c){
   return this.source != c;
}
function FCalendarEditor_get(){
   return this.dataValue;
}
function FCalendarEditor_set(value, format){
   var o = this;
   o.changed = false;
   o.skipBlur = 0;
   o.dataValue = value;
   o.dateOrgValue = value;
   o.editFormat = format;
   RDate.parse(o.date, value);
   RDate.parse(o.dateOrg, value);
   if(!value){
      o.date.now();
      RDate.parse(o.date, value);
      RDate.parse(o.dateOrg, value);
   }
   o.setDate(o.date);
}
function FCalendarEditor_setDate(date){
   var o = this;
   o.hYear.value = date.year;
   o.hMonth.value = date.month;
   o.hHour.value = RString.lpad(date.hour, 2, '0');
   o.hMinute.value = RString.lpad(date.minute, 2, '0');
   o.hSecond.value = RString.lpad(date.second, 2,'0');
   var selDay = date.day;
   if(!(o.dateOrg.year == date.year && o.dateOrg.month == date.month)){
      selDay = -1;
   }
   if(o.hSelect){
	   o.hSelect.style.border='1 solid #FFFFFF';
   }
   var monthWeekDay = this.date.monthWeekDay();
   var monthDays = this.date.monthDays();
   var weekDay = monthWeekDay;
   for(var n=0; n<o.dayCells.count; n++){
      var h = o.dayCells.get(n);
      if(n<monthWeekDay){
         h.className = o.style('DayNone');
         h.innerText = '.'
      }else if(n < monthDays+monthWeekDay){
         if(weekDay == 7){
            weekDay = 0;
         }
         var day = n-monthWeekDay+1;
         if(day == selDay){
            h.className = o.style('DaySel');
            h.isCurrent = true;
            o.hSelect = h;
            h.style.border = '1 solid #2BD6F0';
         }else{
            h.isFree = (weekDay==0 || weekDay==6);
            h.className = h.isFree ? o.style('DayFree') : o.style('Day');
            h.isCurrent = false;
         }
         h.innerText = day;
         weekDay++;
      }else{
         h.className = o.style('DayNone');
         h.innerText = '.'
      }
   }
}
function FCalendarEditor_setHourEditable(v){
   var o = this;
   if(!v){
	   o.hHour.value = '00';
	   o.hHour.style.cursor='default';
	   o.hHour.style.color='gray';
	   o.hHour.editAble = false;
   }else{
	   o.hHour.editAble = true;
   }
}
function FCalendarEditor_setMinuteEditable(v){
   var o = this;
   if(!v){
	   o.hMinute.value = '00';
	   o.hMinute.style.cursor='default';
	   o.hMinute.style.color='gray';
	   o.hMinute.editAble = false;
   }else{
	   o.hMinute.editAble = true;
   }
}
function FCalendarEditor_setSecondEditable(v){
   var o = this;
   if(!v){
	   o.hSecond.value = '00';
	   o.hSecond.style.cursor='default';
	   o.hSecond.style.color='gray';
	   o.hSecond.editAble = false;
   }else{
	   o.hSecond.editAble = true;
   }
}
function FCalendarEditor_storeChange(){
   var o = this;
   o.date.setYear(o.hYear.value);
   o.date.setMonth(o.hMonth.value);
   o.date.setHour(Math.min(RInteger.parse(o.hHour.value), 23));
   o.date.setMinute(Math.min(RInteger.parse(o.hMinute.value), 59));
   o.date.setSecond(Math.min(RInteger.parse(o.hSecond.value), 59));
}
function FCalendarEditor_onBuildButton(){
   var o = this;
   return;
   o.base.FDropEditor.onBuildButton.call(o);
   var h = o.hNow = RBuilder.append(o.hButtonPanel, 'SPAN', o.style('Now'));
   var hp = o.hButtonPanel;
   hp.style.filter = "progid:DXImageTransform.Microsoft.Gradient(startColorStr='#FFFFFF', endColorStr='#E5FAFE', gradientType='0')";
   hp.height = 20;
   h.innerText = RContext.get('FCalendarEditor:now');
   o.attachEvent("onButtonNow",h);
}
function FCalendarEditor_onMdown(e){
   var o = e.source;
   if(RClass.isClass(o, FCalendarEditor)){
      o.isSkipBlur = true;
      if(e.hSource.linkAction){
         e.hSource.linkAction.call(o, e.hSource);
      }
   }
}
function FCalendarEditor_onMup(e){
   var o = e.source;
   if(RClass.isClass(o, FCalendarEditor)){
      var f = o.focusObject;
      if(f && f.focus && f.select){
         f.focus();
         f.select();
      }
   }
}
function FCalendarEditor_ohKdown(){
   var o = this.link;
   if(RClass.isClass(o, FCalendarEditor)){
      var e = RWindow.event(this);
      if(EKey.Esc == e.keyCode){
         o.dataValue = o.dateOrgValue;
         o.editStatus = EEditStatus.Cancel;
         o.endEdit();
      }else if(event.ctrlKey && EKey.Enter == e.keyCode){
         o.storeChange();
         o.editStatus = EEditStatus.Ok;
         o.endEdit();
      }else if(EKey.Enter == e.keyCode){
         o.storeChange();
         o.setDate(o.date);
      }else if(EKey.Tab == e.keyCode){
         o.isSkipBlur = true;
         if(e.shiftKey){
            o.focusPrior();
         }else{
            o.focusNext();
         }
         e.returnValue = 0;
      }
   }
}
function FCalendarEditor_onButtonOver(e){
   var o = e.source;
   if(RClass.isClass(o, FCalendarEditor)){
      e.hSource.className = o.style('ButtonHover');
   }
}
function FCalendarEditor_onButtonOut(e){
   var o = e.source;
   if(RClass.isClass(o, FCalendarEditor)){
      e.hSource.className = o.style('Button');
   }
}
function FCalendarEditor_onButtonOk(e){
   var o = e.source;
   if(RClass.isClass(o, FCalendarEditor)){
      o.editStatus = EEditStatus.Ok;
      o.dataValue = RDate.formatDate(o.date);
      o.editEnd();
   }
}
function FCalendarEditor_onButtonCancel(e) {
   var o = e.source;
   if(RClass.isClass(o, FCalendarEditor)){
	 o.editStatus = EEditStatus.Cancel;
     o.dataValue = '';
     o.editEnd();
   }
}
function FCalendarEditor_ohDaysChange(){
   var o = this.link;
   if(RClass.isClass(o, FCalendarEditor)){
      o.date.setYear(o.hYear.value);
      o.date.setMonth(o.hMonth.value);
      o.setDate(o.date);
   }
}
function FCalendarEditor_ohKeyCheck(){
   var e = RWindow.event(this)
   if(!RString.inChars(String.fromCharCode(e.keyCode), RDate.Chars)){
      e.keyCode = 0;
   }
}
function FCalendarEditor_onDayEnter(e){
   var o = e.source;
   if(RClass.isClass(o, FCalendarEditor) && e.hSource.innerText != '.'){
      if(!e.hSource.isCurrent){
         e.hSource.className = o.style('DayHover');
      }
   }
}
function FCalendarEditor_onDayOut(e){
   var o = e.source;
   if(RClass.isClass(o, FCalendarEditor) && e.hSource.innerText != '.'){
      if(!e.hSource.isCurrent){
         e.hSource.className = e.hSource.isFree ? o.style('DayFree') : o.style('Day');
      }
   }
}
function FCalendarEditor_onDateAction(h){
   var o = this;
   if(o.hYearPrior == h){
      o.date.addYear(-1);
      o.setDate(o.date);
      if(o.focusObject != this.hYear){
         o.focusObject = this.hYear;
         o.hYear.focus();
         o.hYear.select();
      }
   }else if(o.hYearNext == h){
      o.date.addYear(1);
      o.setDate(o.date);
      if(o.focusObject != this.hYear){
         o.focusObject = this.hYear;
         o.hYear.focus();
         o.hYear.select();
      }
   }else if(o.hMonthPrior == h){
      this.date.addMonth(-1);
      o.setDate(o.date);
      if(o.focusObject != this.hMonth){
         o.focusObject = this.hMonth;
         o.hMonth.focus();
      }
   }else if(o.hMonthNext == h){
      this.date.addMonth(1);
      o.setDate(o.date);
      if(o.focusObject != this.hMonth){
         o.focusObject = this.hMonth;
         o.hMonth.focus();
      }
   }
}
function FCalendarEditor_panel(type){
   var o = this;
   if(EPanel.Shadow == type){
      return o.hPanel;
   }
   return o.base.FDropEditor.panel.call(o, type);
}
function FCalendarEditor_dispose(){
   var o = this;
   o.base.FDropEditor.dispose.call(o);
   o.hDatePanel = null;
   o.hDropPanel = null;
   o.hTitlePanel = null;
   o.hOk = null;
   o.hNow = null;
   o.hButtonPanel = null;
   o.hMonthNext = null;
   o.hYear = null;
   o.hMonth = null;
   o.hTime = null;
   o.hTimePanel = null;
}
function FChart(o){
   o = RClass.inherits(this, o, FEditControl, MEditBorder);
   o.borderStyle    = EBorder.Round;
   o.dataService    = RClass.register(o, new TPtyStr('dataService'));
   o.onBuildEdit    = FChart_onBuildEdit;
   o.onLoaded       = FChart_onLoaded;
   o.onLoadConfig   = FChart_onLoadConfig;
   o.get            = RMethod.empty;
   o.set            = FChart_set;
   return o;
}
function FChart_onBuildEdit(b){
   var o = this;
   o.hBorderPanel = b.hPanel;
   o.hBorderPanel.align = 'center';
   o.hBorderPanel.vAlign = 'middle';
   b.hForm.width = '100%'
   b.hForm.height = '100%'
   var hcb = o.hChartPanel = RBuilder.appendDiv(window.document.body);
   var w = o.flashWidth = RString.nvl(o.width, 600) - 20;
   var h = o.flashHeight = RString.nvl(o.height, 400) - 20;
   var name = 'id_flash_' + o.name;
   var src = top.RContext.context('/ars/chart/MoChart.swf');
   var s = new TString();
   s.append("<OBJECT id='" + name + "' classid='clsid:D27CDB6E-AE6D-11cf-96B8-444553540000'");
   s.append(" codebase='http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=9,0,28,0'");
   s.append(" width='" + w + "' height='" + h + "' border='0' align='absmiddle'>");
   s.append("<PARAM name='movie' value='" + src + "'/>");
   s.append("<PARAM name='quality' value='high'/>");
   s.append("<PARAM name='play' value='true'/>");
   s.append("<PARAM name='scale' value='showall'/>");
   s.append("<EMBED src='" + src + "'");
   s.append(" quality='high' pluginspage='http://www.adobe.com/shockwave/download/download.cgi?P1_Prod_Version=ShockwaveFlash'");
   s.append(" type='application/x-shockwave-flash' width='" + w + "' height='" + h + "' wmode='transparent'></EMBED>");
   s.append("</OBJECT>");
   hcb.innerHTML = s.toString();
   o.hChart = hcb.children[0];
}
function FChart_onLoadConfig(){
   var o = this;
   var g = new TChartArg(o.dataService);
   g.control = o;
   g.callback = new TInvoke(o, o.onLoaded);
   RConsole.find(FChartConsole).loadService(g);
}
function FChart_onLoaded(e){
   var o = this;
   var r = e.document.root();
   r.set('stage_width', o.flashWidth);
   r.set('stage_height', o.flashHeight);
   var s = e.document.xml().toString();
   o.hChart.loadConfig(s);
   o.hBorderPanel.appendChild(o.hChart);
}
function FChart_set(v){
   var o = this;
   if(!o.chartLoaded){
      o.chartLoaded = true;
      RFlash.push(new TInvoke(o, o.onLoadConfig));
   }
}
function FCheck(o){
   o = RClass.inherits(this, o, FEditControl, MDescCheck);
   o.__recordValue = EBoolean.False;
   o.borderStyle   = EBorder.None;
   o.onClick       = RMethod.emptyCall;
   o.onDataClick   = RMethod.emptyCall;
   o.onBuildEdit   = FCheck_onBuildEdit;
   o.oeSaveValue   = FCheck_oeSaveValue;
   o.testFocus     = RMethod.emptyFalse;
   o.clearValue    = MDescCheck_clearValue;
   o.resetValue    = MDescCheck_resetValue;
   o.text          = MDescCheck_text;
   o.setText       = MDescCheck_setText
   o.validText     = RMethod.empty;
   o.refreshStyle  = FCheck_refreshStyle;
   return o;
}
function FCheck_onBuildEdit(h){
   var o = this;
   var he = o.hEdit = RBuilder.appendCheck(h, o.style('Edit'));
   he.style.cursor = 'hand';
}
function FCheck_oeSaveValue(e){
   var o = this;
   if(EStore.Prepare == e.store){
      if(RBoolean.isTrue(o.reget())){
         e.values.set(o.dataName, EBoolean.True);
      }
      return EEventStatus.Stop;
   }
   return o.base.FEditControl.oeSaveValue.call(o, e);
}
function FCheck_refreshStyle(){
   var o = this;
   var h = o.panel(EPanel.Edit);
   h.disabled = !o._editable;
   if(!o._editable){
      o.hEdit.style.cursor = 'normal';
   }
}
function FCheckPicker(o){
   o = RClass.inherits(this, o, FEditControl, MEditBorder, MDescCheckPicker, MDropable);
   o.stIconDropSelect = RClass.register(o, new TStyleIcon('DropSelect'));
   o.items            = new TItems();
   o.borderStyle      = EBorder.RoundDrop;
   o.onBuildEdit      = FCheckPicker_onBuildEdit;
   o.onEditEnd        = FCheckPicker_onEditEnd;
   o.onDataKeyDown    = FCheckPicker_onDataKeyDown;
   o.loadConfig       = FCheckPicker_loadConfig;
   o.formatValue      = FCheckPicker_formatValue;
   o.validText        = FCheckPicker_validText;
   o.formatText       = FCheckPicker_formatText;
   o.refreshStyle     = FCheckPicker_refreshStyle;
   o.drop             = FCheckPicker_drop;
   o.dispose          = FCheckPicker_dispose;
   return o;
}
function FCheckPicker_onBuildEdit(b){
   var o = this;
   var h = o.hEdit = RBuilder.appendEdit(b.hPanel, o.style('Edit'));
   if(o.editLength){
      h.maxLength = o.editLength;
   }
}
function FCheckPicker_onEditEnd(editor){
   var o = this;
   RLog.debug(o, 'Begin (editor={1}:{2} value={3})', editor, editor?editor.value():'', o.dataValue);
   if(editor){
      o.set(editor.values);
   }
   o.onDataEditEnd(o);
   RLog.debug(o, 'End (editor={1} value={2})', editor, o.dataValue);
}
function FCheckPicker_loadConfig(c){
   var o = this;
   o.base.FEditControl.loadConfig.call(o, c);
   if(o.dataEmpty){
      o.items.create();
   }
   o.items.loadConfig(c);
   return EStatus.Stop;
}
function FCheckPicker_text(){
   return this.hEdit.value;
}
function FCheckPicker_setText(text){
   this.hEdit.value = text;
}
function FCheckPicker_formatValue(text){
   var o = this;
   if(!RString.isEmpty(text)){
      ta = RString.split(text, ',');
      var vs = new Array();
      var item = o.items.items;
      for(var n = 0; n < ta.length; n++){
         for(var m = 0; m < item.count; m++){
            var c = item.value(m);
            if(c.label == ta[n]){
               vs.push(c.value);
            }
         }
      }
      return RString.toUpper(vs.join());
   }else{
      return '';
   }
}
function FCheckPicker_validText(text){
   var o = this;
   if(RString.isEmpty(text)){
      return true;
   }
   return !RString.isEmpty(o.formatValue(text));
}
function FCheckPicker_formatText(v){
   var o = this;
   if(!RString.isEmpty(v)){
      va = RString.split(v, ',');
      var vs = new Array();
      var item = o.items.items;
      for(var n = 0; n < va.length; n++){
         var t = item.values[item.indexOf(va[n])];
         if(t){
            vs.push(t.label);
         }
      }
      return RString.toUpper(vs.join());
   }else{
      return '';
   }
}
function FCheckPicker_refreshStyle(){
   var o = this;
   o.base.FEditControl.refreshStyle.call(o);
   o.hDrop.src = o.styleIconPath(o._hover ? 'DropSelect' : 'Drop');
}
function FCheckPicker_drop(){
   var o = this;
   if(o.canDrop() && o.canEdit && o.items.count() > 0){
      var ed = o.editor = RConsole.find(FEditConsole).focus(o, FCheckPickerEditor, o.editRefer);
      if(ed.linkControl(o)){
         ed.setItems(o.items);
         ed.set(o.reget());
      }
      ed.show();
   }
}
function FCheckPicker_onDataKeyDown(s, e){
   var o = this;
   o.base.FEditControl.onDataKeyDown.call(o, s, e);
   if(o.items.count()){
      if(o.editor && o.editor.source == o){
         o.editor.onEditKeyDown(s, e);
      }
   }
}
function FCheckPicker_dispose(){
   var o = this;
   o.base.FEditControl.dispose.call(o);
   RMemory.freeHtml(o.hEdit);
   o.hEdit = null;
}
function FCheckPickerEditor(o){
   o = RClass.inherits(this, o, FDropEditor, MShadow);
   o.MinWidth         = 120;
   o.onEditFocus      = RClass.register(o, new HFocus('onEditFocus'));
   o.onEditBlur       = RClass.register(o, new HBlur('onEditBlur'));
   o.stIconDropSelect = RClass.register(o, new TStyleIcon('DropSelect'));
   o.stFlag           = RClass.register(o, new TStyle('Flag'));
   o.stEditForm       = RClass.register(o, new TStyle('EditForm'));
   o.pattern          = null;
   o.originItem       = null;
   o.selectItem       = null;
   o.items            = null;
   o.itemClickListener = null;
   o.values           = new Array();
   o.hBtnTextSpan     = null;
   o.onBuildDrop      = FCheckPickerEditor_onBuildDrop;
   o.onBuildButton    = FCheckPickerEditor_onBuildButton;
   o.onItemClick      = FCheckPickerEditor_onItemClick;
   o.onEditKeyDown    = FCheckPickerEditor_onEditKeyDown;
   o.construct        = FCheckPickerEditor_construct;
   o.set              = FCheckPickerEditor_set;
   o.setItems         = FCheckPickerEditor_setItems;
   o.select           = FCheckPickerEditor_select;
   o.linkControl      = FCheckPickerEditor_linkControl;
   o.show             = FCheckPickerEditor_show;
   o.hide             = FCheckPickerEditor_hide;
   o.dispose          = FCheckPickerEditor_dispose;
   return o;
}
function FCheckPickerEditor_construct(){
   var o = this;
   o.itemClickListener = new TListener(o, o.onItemClick);
}
function FCheckPickerEditor_onBuildDrop(){
   var o = this;
   o.hItemsForm = RBuilder.appendTable(o.hDropPanel);
   o.hItemsForm.width = '100%';
   o.hItemsPanel = RBuilder.append(o.hItemsForm, 'TBODY');
   o.onBuildButton();
}
function FCheckPickerEditor_onBuildButton(){
   var o = this;
   o.base.FDropEditor.onBuildButton.call(o);
   var h = o.hBtnTextSpan = RBuilder.newSpan(o.hButtonPanel, null);
   h.innerText = 'colse';
}
function FCheckPickerEditor_onItemClick(s){
   var o = this;
   s.setChecked(!s.checked);
   var ts = o.items.items;
   var cs = o.components;
   var vs = new Array();
   for(var n = 0; n < ts.count; n++){
      var c = cs.value(n);
      if(c.checked){
         vs.push(c.value);
      }
   }
   var e = o.source;
   e.set(vs.join());
}
function FCheckPickerEditor_select(p){
   var o = this;
   var cs = o.components;
   p = Math.min(Math.max(0, p), cs.count-1)
   for(var n=0; n<cs.count; n++){
      o.components.value(n).setChecked(n == p);
   }
   o.position = p;
}
function FCheckPickerEditor_onEditKeyDown(s, e){
   var o = this;
   return;
}
function FCheckPickerEditor_set(v){
   var o = this;
   var cs = o.components;
   var cl = cs.count;
   for(var n = 0;n < cl;n++){
      cs.value(n).setChecked(false);
   }
   if(!RString.isEmpty(v)){
      o.values = v;
      va = RString.split(v, ',');
      for(var n = 0; n < va.length; n++){
         var c = cs.get(va[n]);
         if(c){
            c.setChecked(true);
         }
      }
   }
}
function FCheckPickerEditor_setItems(items){
   var o = this;
   if(o.components){
      return;
   }
   var hip = o.hItemsPanel;
   o.items = items;
   var count = items.count();
   for(var n=0; n<count; n++){
      if(n > 0){
         var hr = RBuilder.append(hip, 'TR');
         hr.height = 1;
         var hd = RBuilder.append(hr, 'TD');
         hd.colSpan = 3;
         hd.style.borderTop = '1 dashed #24c2db';
         RBuilder.appendEmpty(hd);
      }
      var t = items.get(n);
      var c = RControl.create(FSelectItem);
      c.name = t.value;
      c.lsnsClick.push(o.itemClickListener);
      c.set(t.icon, t.label, t.value);
      c.setPanel(hip);
      o.push(c);
   }
   o.position = 0;
}
function FCheckPickerEditor_linkControl(c){
   var o = this;
   if(o.source == c){
      return false;
   }
   o.source = c;
   RLog.debug(o, 'link Panel (panel={0}, edit={1})', RClass.dump(c.hEditCell), RClass.dump(c.hEdit));
   RHtml.toRect(o.rect, c.hEditCell);
   RHtml.setPixelRect(o.hPanel, o.rect);
   o.hPanel.style.pixelTop = o.rect.bottom;
   var hbf = o.border.hForm;
   hbf.style.pixelWidth = c.editBorder.hForm.width;
   hbf.style.pixelHeight = c.editBorder.hForm.height;
   return true;
}
function FCheckPickerEditor_show(v){
   var o = this;
   o.base.FDropEditor.show.call(o, v);
   RConsole.find(FFocusConsole).focus(o);
   if(o.border.hForm.offsetWidth < o.MinWidth){
      o.border.hForm.style.pixelWidth = o.MinWidth;
   }
   o.base.MShadow.show.call(o, v);
   o.isSkipBlur = false;
}
function FCheckPickerEditor_hide(){
   var o = this;
   o.source = null;
   o.base.FDropEditor.hide.call(o);
   o.base.MShadow.hide.call(o);
}
function FCheckPickerEditor_dispose(){
   var o = this;
   o.base.FDropEditor.dispose.call(o);
   RMemory.freeHtml(o.hPanel);
   RMemory.freeHtml(o.hItemsForm);
   RMemory.freeHtml(o.hItemsPanel);
   RMemory.freeHtml(o.hBtnTextSpan);
   RMemory.freeHtml(o.hDropPanel);
   RMemory.freeHtml(o.hButtonPanel);
   o.hPanel = null;
   o.hItemsForm = null;
   o.hItemsPanel = null;
   o.hBtnTextSpan = null;
   o.hDropPanel = null;
   o.hButtonPanel = null;
}
function FColorPicker(o){
   o = RClass.inherits(this, o, FEditControl, MEditBorder, MDescColor, MDropable);
   o.borderStyle = EBorder.RoundDrop;
   o.onBuildEdit = FColorPicker_onBuildEdit;
   o.onEditEnd   = FColorPicker_onEditEnd;
   o.onDataKeyDown   = FColorPicker_onDataKeyDown;
   o.checkColor = FColorPicker_checkColor;
   o.setText     = FColorPicker_setText;
   o.drop        = FColorPicker_drop;
   o.dispose     = FColorPicker_dispose;
   return o;
}
function FColorPicker_onBuildEdit(b){
   var o = this;
   var h = o.hEdit = RBuilder.appendEdit(b.hPanel, o.style('Edit'));
   h.maxLength = 20;
}
function FColorPicker_onEditEnd(editor){
   var o = this;
   RLog.debug(o, 'Begin (editor={0}:{1} value={2})', editor, editor?editor.color:'', o.dataValue);
   if(editor){
      o.set(editor.color);
      o.hDrop.style.backgroundColor = editor.color;
   }
   o.onDataEditEnd(o);
   RLog.debug(o, 'End (editor={0} value={1})', editor, o.dataValue);
}
function FColorPicker_setText(t){
   var o = this;
   o.base.FEditControl.setText.call(o, RString.toUpper(t));
   o.hDrop.style.backgroundColor = t;
}
function FColorPicker_checkColor(c)
{
   var oSpan = document.createElement("<span style='color:"+c+";'></span>");
   if(oSpan.style.color != ""){
      return true;
   }else{
      return false;
   }
   oSpan = null;
}
function FColorPicker_onDataKeyDown(e){
      var o = this;
      o.base.FEditControl.onDataKeyDown.call(o, o, e);
      if(o.checkColor(o.text())){
         o.hDrop.style.backgroundColor = o.text();
      }else{
         o.hDrop.style.backgroundColor = '';
      }
}
function FColorPicker_drop(){
   var o = this;
   if(o.canDrop() && o.canEdit){
      var ed = o.editor = RConsole.find(FEditConsole).focus(o, FColorPickerEditor, o.name);
      if(ed.linkControl(o)){
         ed.set(o.reget());
      }
      ed.show();
   }
}
function FColorPicker_dispose(){
   var o = this;
   o.base.FEditControl.dispose.call(o);
   RMemory.freeHtml(o.hEdit);
   RMemory.freeHtml(o.hDrop);
   o.hEdit = null;
   o.hDrop = null;
}
function FColorPickerEditor(o){
   o = RClass.inherits(this, o, FDropEditor, MShadow);
   o.MinWidth     = 240;
   o.ColorHex     = new Array('00', '33', '66', '99', 'CC', 'FF');
   o.SpColorHex   = new Array('FF0000', '00FF00', '0000FF', 'FFFF00', '00FFFF','FF00FF');
   o.onCellEnter  = RClass.register(o, new HMouseOver('onCellEnter'),  FColorPickerEditor_onCellEnter);
   o.onCellSelect = RClass.register(o, new HMouseDown('onCellSelect'), FColorPickerEditor_onCellSelect);
   o.color        = null;
   o.hTable       = null;
   o.cellWidth    = 16;
   o.cellHeight   = 10;
   o.onBuildDrop  = FColorPickerEditor_onBuildDrop;
   o.onKeyDown    = FColorPickerEditor_onKeyDown;
   o.onCellSelect = FColorPickerEditor_onCellSelect;
    o.onEditEnd = FColorPickerEditor_onEditEnd;
   o.makeCell     = FColorPickerEditor_makeCell;
   o.set          = FColorPickerEditor_set;
   o.show         = FColorPickerEditor_show;
   o.hide         = FColorPickerEditor_hide;
   o.linkControl  = FColorPickerEditor_linkControl;
   o.dispose      = FColorPickerEditor_dispose;
   return o;
}
function FColorPickerEditor_onBuildDrop(){
   var o = this;
   o.hTable = RBuilder.appendTable(o.hDropPanel);
   for(var i = 0; i < 2; i++){
      for(var j = 0; j < 6; j++){
         var hRow = o.hTable.insertRow();
         o.makeCell(hRow, "#000000");
         if (i == 0){
            o.makeCell(hRow, '#'+o.ColorHex[j] + o.ColorHex[j] + o.ColorHex[j]);
         }else {
            o.makeCell(hRow, '#'+o.SpColorHex[j]);
         }
         o.makeCell(hRow, "#000000");
         for (k = 0; k < 3; k++) {
            for (l = 0; l < 6; l++) {
               o.makeCell(hRow, '#'+o.ColorHex[k + i * 3] + o.ColorHex[l] + o.ColorHex[j]);
            }
         }
      }
   }
}
function FColorPickerEditor_linkControl(c){
   var o = this;
   if(o.source == c){
      return false;
   }
   o.source = c;
   RLog.debug(o, 'link Panel (panel={0}, edit={1})', RClass.dump(c.hEditCell), RClass.dump(c.hEdit));
   RHtml.toRect(o.rect, c.hEditCell);
   RHtml.setPixelRect(o.hPanel, o.rect);
   o.hPanel.style.pixelTop = o.rect.bottom;
   var hbf = o.border.hForm;
   hbf.style.pixelWidth = c.editBorder.hForm.width;
   hbf.style.pixelHeight = c.editBorder.hForm.height;
   return true;
}
function FColorPickerEditor_onCellEnter(e){
   var o = this;
   o.editable.hDrop.style.backgroundColor = e.hSource.style.backgroundColor;
}
function FColorPickerEditor_onCellSelect(e){
   var o = this;
   o.color = e.srcElement.style.backgroundColor;
   o.editStatus = EEditStatus.Ok
   o.blur();
}
function FColorPickerEditor_makeCell(hRow, color) {
   var o = this;
   var h = hRow.insertCell();
   h.link = o;
   h.width = o.cellWidth;
   h.height = o.cellHeight;
   h.style.backgroundColor = color;
   o.attachEvent('onCellEnter', h);
   o.attachEvent('onCellSelect', h);
   return h;
}
function FColorPickerEditor_onKeyDown(e){
   alert(FColorPickerEditor_onKeyDown);
   var o = this;
   var kc = e.keyCode;
   if(EKey.Up == kc){
      o.select(o.selectIndex-1);
   }else if(EKey.Down == kc){
      o.select(o.selectIndex+1);
   }else if(EKey.Esc == kc){
      o.editStatus = EEditStatus.Cancel;
      o.selectIndex = o.originIndex;
      RKey.eventClear(e);
      o.inEdit = false;
      o.hEdit.blur();
   }else if(EKey.Enter == kc){
      o.editStatus = EEditStatus.Ok;
      RKey.eventClear(e);
      o.inEdit = false;
      o.hEdit.blur();
   }
}
function FColorPickerEditor_set(v){
   var o = this;
   o.color = v;
}
function FColorPickerEditor_show(v){
   var o = this;
   o.base.FDropEditor.show.call(o, v);
   RConsole.find(FFocusConsole).focus(o);
   if(o.border.hForm.offsetWidth < o.MinWidth){
      o.border.hForm.style.pixelWidth = o.MinWidth;
   }
   o.base.MShadow.show.call(o, v);
   o.isSkipBlur = false;
}
function FColorPickerEditor_onEditEnd(){
   var o = this;
   var t = o.editable;
   RLog.debug(o, 'Edit end (editable={0}, status={1})', RClass.dump(t), REnum.decode(EEditStatus, o.editStatus));
   if(t){
      t.hDrop.style.backgroundColor = o.color;
      var ec = RConsole.find(FEventConsole);
      if(EEditStatus.Cancel == o.editStatus){
         ec.add(t, t.focus);
      }else if(EEditStatus.Ok == o.editStatus){
         t.onEditEnd(o);
         ec.add(t, t.focus);
      }
   }
   o.editable = null;
   o.inEdit = false;
}
function FColorPickerEditor_hide(){
   var o = this;
   o.source = null;
   o.base.FDropEditor.hide.call(o);
   o.base.MShadow.hide.call(o);
}
function FColorPickerEditor_dispose(){
   var o = this;
   o.base.FDropEditor.dispose.call(o);
   RMemory.freeHtml(o.hTable);
   RMemory.freeHtml(o.hDropPanel);
   RMemory.freeHtml(o.hEdit);
   o.hTable = null;
   o.hDropPanel = null;
   o.hEdit = null;
}
function FConfigAction(o){
   o = RClass.inherits(this, o, FComponent, MInvoke);
   o.service   = RClass.register(o, new TPtyStr('service'));
   o.isLoading = false;
   o.service   = null;
   o.valuable  = null;
   o.onLoaded  = FConfigAction_onLoaded;
   o.invoke    = FConfigAction_invoke;
   return o;
}
function FConfigAction_onLoaded(e){
   var o = this;
   var r = RConsole.find(FResultConsole).checkService(e.document.root());
   if(r){
      RWindow.setEnable(true);
      var v = o.valuable;
      if(RClass.isClass(v, MFocus)){
         v.focus();
      }
   }
   o.isLoading = false;
}
function FConfigAction_invoke(vo){
   var o = this;
   RClass.checkClass(vo, MConfig);
   var svc = RService.parse(this.service);
   if(!svc){
      return alert('Unknown service');
   }
   RWindow.setEnable(false);
   var doc = new TXmlDocument();
   var root = doc.root();
   root.set('action', svc.action);
   RConsole.find(FEnvConsole).build(root);
   var config = root.create('Data');
   if(RClass.isClass(vo, FContainer)){
      vo.storeConfig(config);
   }else{
      vo.saveConfig(config);
   }
   RLog.debug(this, doc.dump());
   o.valuable = vo;
   o.isLoading = true;
   var e = new TEvent(o, EXmlEvent.Send, o.onLoaded);
   e.url = svc.url;
   e.document = doc;
   RConsole.find(FXmlConsole).process(e);
}
function FDataAction(o){
   o = RClass.inherits(this, o, FComponent, MInvoke);
   o.service           = RClass.register(o, new TPtyStr('service'));
   o.invokeFunction    = RClass.register(o, new TPtyStr('invokeFunction'));
   o.isLoading = false;
   o.service   = null;
   o.valuable  = null;
   o.onLoaded  = FDataAction_onLoaded;
   o.invoke    = FDataAction_invoke;
   return o;
}
function FDataAction_onLoaded(e){
   var o = this;
   var r = RConsole.find(FResultConsole).checkService(e.document.root());
   if(r){
      RWindow.setEnable(true);
      var v = o.valuable;
      if(RClass.isClass(v, MFocus)){
         v.focus();
      }
   }
   o.isLoading = false;
}
function FDataAction_invoke(vo){
   var o = this;
   RClass.checkClass(vo, MValue);
   var svc = RService.parse(this.service);
   if(!svc){
      return alert('Unknown service');
   }
   RWindow.setEnable(false);
   var doc = new TXmlDocument();
   var root = doc.root();
   root.set('action', svc.action);
   RConsole.find(FEnvConsole).build(root);
   var config = root.create('Data');
   vo.saveValue(config);
   RLog.debug(this, doc.dump());
   o.valuable = vo;
   o.isLoading = true;
   var e = new TEvent(o, EXmlEvent.Send, o.onLoaded);
   e.url = svc.url;
   e.document = doc;
   RConsole.find(FXmlConsole).process(e);
}
function FDate(o){
   o = RClass.inherits(this, o, FEditControl, MEditBorder, MDropable);
   o.editDispMode = RClass.register(o, new TPtyBoolSet('editDisplay', 'editDate', EDateTimeMode.Display));
   o.editYear     = RClass.register(o, new TPtyBoolSet('editYear', 'editDate', EDateTimeMode.Year));
   o.editMonth    = RClass.register(o, new TPtyBoolSet('editMonth', 'editDate', EDateTimeMode.Month));
   o.editDay      = RClass.register(o, new TPtyBoolSet('editDay', 'editDate', EDateTimeMode.Day));
   o._date        = null;
   o.borderStyle  = EBorder.RoundDrop;
   o.lsnEditEnd   = null;
   o.hYearPanel   = null;
   o.hYear        = null;
   o.hMonthPanel  = null;
   o.hMonth       = null;
   o.hDayPanel    = null;
   o.hDay         = null;
   o.onKeyPress   = FDate_onKeyPress;
   o.onEditEnd    = FDate_onEditEnd;
   o.onBuildEdit  = FDate_onBuildEdit;
   o.oeSaveValue  = FDate_oeSaveValue;
   o.construct    = FDate_construct;
   o.formatValue  = FDate_formatValue;
   o.text         = FDate_text;
   o.setText      = FDate_setText;
   o.validText    = FDate_validText;
   o.setEditable  = FDate_setEditable;
   o.refreshStyle = FDate_refreshStyle;
   o.drop         = FDate_drop;
   o.dispose      = FDate_dispose;
   return o;
}
function FDate_onKeyPress(e){
   if(!RString.inChars(String.fromCharCode(e.keyCode), RDate.Chars)){
      RKey.eventClear(e);
   }
}
function FDate_onEditEnd(e){
   var o = this;
   if(e){
      o.set(e.get());
   }
   o.onDataEditEnd(o);
}
function FDate_onBuildEdit(b){
   var o = this;
   var htb = RBuilder.appendTable(b.hPanel);
   htb.width = '100%';
   htb.style.tableLayout = 'fixed';
   var hr = o.hEdit = htb.insertRow();
   o.onBuildChange(hr.insertCell())
   var hc = oonDateDoubleClickPanel = hr.insertCell();
   hc.width = '40%';
   hc.style.padding = '0 1';
   var he = o.hYear = RBuilder.appendEdit(hc);
   he.maxLength = 4;
   he.style.border = 0;
   he.style.width = '100%';
   he.style.textAlign = 'right';
   var hc = o.hYearSplit = hr.insertCell();
   hc.width = 5;
   hc.innerText = '-';
   o.hYear.style.display = o.editYear?'block':'none'
   o.hYearSplit.style.display = o.editYear?'block':'none'
   var hc = o.hMonthPanel = hr.insertCell();
   hc.width = '20%';
   hc.style.padding = '0 1';
   var he = o.hMonth = RBuilder.appendEdit(hc);
   he.maxLength = 2;
   he.style.border = 0;
   he.style.width = '100%';
   he.style.textAlign = 'right';
   var hc = o.hMonthSplit = hr.insertCell();
   hc.width = 5;
   hc.innerText = '-';
   o.hMonth.style.display = o.editMonth?'block':'none';
   o.hMonthSplit.style.display = o.editDay?'block':'none';
   var hc = o.hDayPanel = hr.insertCell();
   hc.width = '20%';
   hc.style.padding = '0 1'
   var he = o.hDay = RBuilder.appendEdit(hc);
   he.maxLength = 2;
   he.style.border = 0;
   he.style.width = '100%';
   he.style.textAlign = 'right';
   o.hDay.style.display = o.editDay?'block':'none';
}
function FDate_oeSaveValue(e){
   var o = this;
   var dn = RString.nvl(o.dataCode, o.dataName);
   if(!RString.isEmpty(dn)){
      var vs = e.values;
      var v = vs.get(dn);
      if(v){
         vs.set(dn, o.reget().substring(0, 8) + v.substring(8));
      }else{
         vs.set(dn, o.reget());
      }
   }
   return EEventStatus.Stop;
}
function FDate_construct(){
   var o = this;
   o.base.FEditControl.construct.call(o);
   o._date = new TDate();
   o.lsnEditEnd = new TListener(o, o.onEditEnd);
}
function FDate_formatValue(t){
   if(t){
      var o = this;
      if(t.toLowerCase() == '@now'){
         o._date.now();
         return RDate.formatDate(o._date);
      }else{
         RDate.autoParse(o._date, t);
         return RDate.formatDate(o._date);
      }
   }
   return RString.nvl(t);
}
function FDate_text(){
   var o = this;
   o._date.setYear(o._date.year);
   o._date.setMonth(o._date.month);
   o._date.setDay(o._date.day);
   return RDate.formatDate(o._date);
}
function FDate_setText(t){
   var o = this;
   if(t){
      RDate.autoParse(o._date, t);
      o.hYear.value = RInteger.format(o._date.year, 4);
      o.hMonth.value = RInteger.format(o._date.month, 2);
      o.hDay.value = RInteger.format(o._date.day, 2);
   }else{
      o.hYear.value = '';
      o.hMonth.value = '';
      o.hDay.value = '';
   }
}
function FDate_validText(t){
   return null;
}
function FDate_setEditable(v){
   var o = this;
   o.base.FEditControl.setEditable.call(o, v);
   o.hYear.readOnly = !v;
   o.hMonth.readOnly = !v;
   o.hDay.readOnly = !v;
}
function FDate_refreshStyle(){
   var o = this;
   o.base.FEditControl.refreshStyle.call(o);
   o.hYear.style.color = o._textColor;
   o.hYear.style.backgroundColor = o._backColor;
   o.hMonth.style.color = o._textColor;
   o.hMonth.style.backgroundColor = o._backColor;
   o.hDay.style.color = o._textColor;
   o.hDay.style.backgroundColor = o._backColor;
}
function FDate_drop(){
   var o = this;
   if(o.canDrop() && o._editable){
      var e = o.editor = RConsole.find(FEditConsole).focus(o, FDateEditor, o.editRefer);
      e.set(RDate.formatDate(o._date));
      e.setYearVisible(o.editYear);
      e.setMonthVisible(o.editMonth);
      e.setDayVisible(o.editDay);
      e.lsnEditEnd = o.lsnEditEnd;
      e.show();
   }
}
function FDate_dispose(){
   var o = this;
   o.base.FEditControl.dispose.call(o);
   o._date = null;
}
function FDateEditor(o){
   o = RClass.inherits(this, o, FDropEditor);
   o.date              = null;
   o.years             = null;
   o.months            = null;
   o.days              = null;
   o.hPanelDay         = null;
   o.hPanelMonth       = null;
   o.hPanelYear        = null;
   o.hTitleDay         = null;
   o.hTitleMonth       = null;
   o.hTitleYear        = null;
   o.onButtonEnter     = RClass.register(o, new HMouseEnter('onButtonEnter'), FDateEditor_onButtonEnter);
   o.onButtonLeave     = RClass.register(o, new HMouseLeave('onButtonLeave'), FDateEditor_onButtonLeave);
   o.onYearClick       = RClass.register(o, new HMouseDown('onYearClick'), FDateEditor_onYearClick);
   o.onMonthClick      = RClass.register(o, new HMouseDown('onMonthClick'), FDateEditor_onMonthClick);
   o.onDayClick        = RClass.register(o, new HMouseDown('onDayClick'), FDateEditor_onDayClick);
   o.onDateDoubleClick = RClass.register(o, new HDoubleClick('onDateDoubleClick'), FDateEditor_onDateDoubleClick);
   o.onNowClick        = RClass.register(o, new HMouseDown('onNowClick'), FDateEditor_onNowClick);
   o.onConfirmClick    = RClass.register(o, new HMouseDown('onConfirmClick'), FDateEditor_onConfirmClick);
   o.onBuildDrop       = FDateEditor_onBuildDrop;
   o.onBuildButton     = FDateEditor_onBuildButton;
   o.construct         = FDateEditor_construct;
   o.buildTitle        = FDateEditor_buildTitle;
   o.get               = FDateEditor_get;
   o.set               = FDateEditor_set;
   o.resetDay          = FDateEditor_resetDay;
   o.setYearVisible    = FDateEditor_setYearVisible;
   o.setMonthVisible    = FDateEditor_setMonthVisible;
   o.setDayVisible    = FDateEditor_setDayVisible;
   o.selectCell        = FDateEditor_selectCell;
   o.restore           = FDateEditor_restore;
   o.show              = FDateEditor_show;
   o.dispose           = FDateEditor_dispose;
   return o;
}
function FDateEditor_onButtonEnter(e){
   if(!e.hSource.isSelect){
	  if(RString.isEmpty(e.hSource.innerText)){
         e.hSource.style.backgroundColor = '#CCCCFF';
	  }
   }
}
function FDateEditor_onButtonLeave(e){
   if(!e.hSource.isSelect){
      e.hSource.style.backgroundColor = '#FFFFFF';
   }
}
function FDateEditor_onYearClick(e){
   var o = this;
   o.date.setYear(e.hSource.innerText);
   o.restore();
   o.resetDay();
}
function FDateEditor_onMonthClick(e){
   var o = this;
   o.date.setMonth(e.hSource.innerText);
   o.restore();
   o.resetDay();
}
function FDateEditor_onDayClick(e){
   var o = this;
   if(!RString.equals(e.hSource.innerText, '.')){
      o.date.setDay(e.hSource.innerText);
      o.restore();
   }
}
function FDateEditor_onDateDoubleClick(){
   this.onConfirmClick();
}
function FDateEditor_onNowClick(){
   var o = this;
   o.date = new TDate();
   o.editEnd();
}
function FDateEditor_onConfirmClick(){
   var o = this;
   o.date.setYear(o.hYear.value);
   o.date.setMonth(o.hMonth.value);
   o.date.setDay(o.hDay.value);
   o.editEnd();
}
function FDateEditor_onBuildDrop(){
   var o = this;
   var hdp = o.hDropPanel;
   hdp.width = 220;
   o.attachEvent('onDateDoubleClick', hdp);
   o.hTitleYear = o.buildTitle('Year', 4);
   var hp = o.hPanelYear = o.hSelectPanel = RBuilder.appendTable(hdp);
   hp.width = '100%';
   for(var m=0; m<4; m++){
      var hr = hp.insertRow();
      for(var n=0; n<4; n++){
         var hc = hr.insertCell();
         hc.innerText = RInteger.format(2000 + 4*m+n, 2);
         hc.align = 'center';
         hc.style.padding = '1 6';
         hc.style.cursor = 'hand';
         hc.style.borderBottom = '1 solid #EEEEEE';
         if(n < 5){
            hc.style.borderRight = '1 solid #EEEEEE';
         }
         o.attachEvent('onButtonEnter', hc);
         o.attachEvent('onButtonLeave', hc);
         o.attachEvent('onYearClick', hc);
         o.years.push(hc);
      }
   }
   o.hTitleMonth = o.buildTitle('Month', 2);
   var hp = o.hPanelMonth = o.hSelectPanel = RBuilder.appendTable(hdp);
   hp.width = '100%';
   for(var m=0; m<2; m++){
      hr = hp.insertRow();
      for(var n=0; n<6; n++){
         var hc = hr.insertCell();
         hc.innerText = RInteger.format(6*m+n+1, 2);
         hc.align = 'center';
         hc.style.cursor = 'hand';
         hc.style.borderBottom = '1 solid #EEEEEE';
         if(n < 5){
            hc.style.borderRight = '1 solid #EEEEEE';
         }
         o.attachEvent('onButtonEnter', hc);
         o.attachEvent('onButtonLeave', hc);
         o.attachEvent('onMonthClick', hc);
         o.months.push(hc);
      }
   }
   o.hTitleDay = o.buildTitle('Day', 2);
   var hp = o.hPanelDay = o.hSelectPanel = RBuilder.appendTable(hdp);
   hp.width = '100%';
   for(var m=0; m<5; m++){
      hr = hp.insertRow();
      for(var n=0; n<7; n++){
         var day = 7*m+n+1;
         if(day > 31){
            continue;
         }
         var hc = hr.insertCell();
         hc.innerText = RInteger.format(day, 2);
         hc.align = 'center';
         hc.style.borderBottom = '1 solid #EEEEEE';
         hc.style.cursor = 'hand';
         if(n < 5){
            hc.style.borderRight = '1 solid #EEEEEE';
         }
         o.attachEvent('onButtonEnter', hc);
         o.attachEvent('onButtonLeave', hc);
         o.attachEvent('onDayClick', hc);
         o.days.push(hc);
      }
   }
}
function FDateEditor_onBuildButton(){
   var o = this;
   o.base.FDropEditor.onBuildButton.call(o);
   var hf = RBuilder.appendTable(o.hButtonPanel);
   hf.width = '100%';
   hf.height = 20;
   hf.style.filter = "progid:DXImageTransform.Microsoft.Gradient(startColorStr='#EEEEEE', endColorStr='#FFFFFF', gradientType='0')";
   var hr = hf.insertRow();
   var hc = hr.insertCell();
   hc.style.padding = '0 6';
   var h = o.hNow = RBuilder.append(hc, 'SPAN');
   h.style.cursor = 'hand';
   o.attachEvent('onNowClick', h);
   h.innerText = RContext.get('FDate:Now');
   var hc = hr.insertCell();
   hc.style.padding = '0 6';
   hc.align = 'right';
   var h = o.hNow = RBuilder.append(hc, 'SPAN');
   h.style.cursor = 'hand';
   o.attachEvent('onConfirmClick', h);
   h.innerText = RContext.get('FDate:Confirm');
}
function FDateEditor_construct(){
   var o = this;
   o.base.FDropEditor.construct.call(o);
   o.date = new TDate();
   o.years = new TList();
   o.months = new TList();
   o.days = new TList();
}
function FDateEditor_buildTitle(n, ml){
   var o = this;
   var hf = RBuilder.appendTable(o.hDropPanel);
   hf.width = '100%';
   hf.style.borderBottom = '1 solid #999999';
   hf.style.filter = "progid:DXImageTransform.Microsoft.Gradient(startColorStr='#FFFFFF', endColorStr='#E5FAFE', gradientType='0')";
   hf.style.backgroundColor = '#F8F8F8';
   hf.style.padding = '2 6';
   var hr = hf.insertRow();
   var hc = hr.insertCell();
   hc.width = 60;
   var he = o['h' + n] = RBuilder.appendEdit(hc);
   he.style.width = '100%';
   he.style.textAlign = 'right';
   he.style.border = '1 solid #CCCCCC';
   he.maxLength = ml;
   var hc = hr.insertCell();
   hc.innerText = RContext.get('FDate:' + n);
   return hf;
}
function FDateEditor_get(){
   return RDate.formatDate(this.date);
}
function FDateEditor_set(v){
   var o = this;
   RDate.autoParse(o.date, v);
   o.restore();
}
function FDateEditor_setYearVisible(v){
   var o = this;
   o.hPanelYear.style.display = v? 'block':'none';
   o.hTitleYear.style.display = v? 'block':'none';
}
function FDateEditor_setMonthVisible(v){
   var o = this;
   o.hPanelMonth.style.display = v? 'block':'none';
   o.hTitleMonth.style.display = v? 'block':'none';
}
function FDateEditor_setDayVisible(v){
   var o = this;
   o.hPanelDay.style.display = v? 'block':'none';
   o.hTitleDay.style.display = v? 'block':'none';
}
function FDateEditor_show(v){
   var o = this;
   o.base.FDropEditor.show.call(o, v);
   var hp = o.hPanel;
   var hbf = o.hBorderForm;
   var s = o.source;
   var r = s.getEditRange();
   hp.style.pixelLeft = r.x;
   hp.style.pixelTop = r.y + r.height;
   hp.style.pixelWidth = 220;
   o.base.MShadow.show.call(o);
}
function FDateEditor_resetDay(){
   var o = this;
   var monthDays = this.date.monthDays();
   for(var n=0; n<o.days.count; n++){
      var hd = o.days.get(n);
      if(n >= monthDays){
         hd.innerText = '.';
      }else{
    	 hd.innerText = RInteger.format(n+1, 2);
      }
   }
}
function FDateEditor_selectCell(ls, v){
   var c = ls.count;
   for(var n=0; n<c; n++){
      var h = ls.get(n);
      if(h.innerText == v){
         h.style.color = '#FFFFFF';
         h.style.backgroundColor = '#9999EE';
         h.isSelect = true;
      }else{
         h.style.color = '#000000';
         h.style.backgroundColor = '#FFFFFF';
         h.isSelect = false;
      }
   }
}
function FDateEditor_restore(){
   var o = this;
   o.hYear.value = o.date.year;
   o.hMonth.value = o.date.month;
   o.hDay.value = o.date.day;
   o.selectCell(o.years, o.date.year);
   o.selectCell(o.months, o.date.month);
   o.selectCell(o.days, o.date.day);
}
function FDateEditor_dispose(){
   var o = this;
   o.base.FDropEditor.dispose.call(o);
   o.hPanel = null;
}
function FDialog(o){
   o = RClass.inherits(this, o, FForm);
   return o;
}
function FEdit(o){
   o = RClass.inherits(this, o, FEditControl, MDescEdit, MEditBorder);
   o.stUnit        = RClass.register(o, new TStyle('Unit'));
   o.borderStyle   = EBorder.Round;
   o.hUnit         = null;
   o.onDataKeyDown = FEdit_onDataKeyDown;
   o.onBuildEdit   = FEdit_onBuildEdit;
   o.formatValue   = FEdit_formatValue;
   o.set           = FEdit_set;
   o.setText       = FEdit_setText;
   o.validText     = FEdit_validText;
   o.findEditor    = FEdit_findEditor;
   o.drop          = FEdit_drop;
   o.link          = FEdit_link;
   o.clone         = FEdit_clone;
   return o;
}
function FEdit_onDataKeyDown(s, e){
   var o = this;
   o.base.FEditControl.onDataKeyDown.call(o, s, e);
   if(o.editCase){
      RKey.fixCase(e, o.editCase);
   }
   if(o._editable){
      return;
      if(o.editComplete){
         if( 16 != e.keyCode && 17 != e.keyCode && 18 != e.keyCode && 20 != e.keyCode ){
            var ed = o.findEditor();
            if(ed){
               ed.onEditKeyDown(s, e);
            }
         }
      }
   }
}
function FEdit_onBuildEdit(b){
   var o = this;
   var htb = RBuilder.appendTable(b.hPanel);
   htb.style.tableLayout = 'fixed';
   var hr = o.hEdit = htb.insertRow();
   o.onBuildChange(hr.insertCell());
   var hep = hr.insertCell();
   var he = o.hEdit = RBuilder.appendEdit(hep, o.style('Edit'));
   he._pname = 'hEdit';
   if(o.editLength){
      he.maxLength = o.editLength;
   }
}
function FEdit_formatValue(v){
   var o = this;
   var r = RString.nvl(v);
   if(ECase.Upper == o.editCase){
      r = RString.toUpper(r);
   }else if(ECase.Lower == o.editCase){
      r = RString.toLower(r);
   }
   return r;
}
function FEdit_set(v){
   var o = this;
   o.base.FEditControl.set.call(o, v);
   o.finded = v;
   if(o.hChangeIcon){
      o.hChangeIcon.style.display = 'none';
   }
}
function FEdit_setText(t){
   var o = this;
   if(!o.hEdit){
      return;
   }
   if('U'== o.editCase){
      o.hEdit.value = RString.toUpper(t);
   }else if('L'== o.editCase){
         o.hEdit.value = RString.toLower(t);
   }else{
      o.hEdit.value = t;
   }
   if('right' == o.editAlign ){
      o.hEdit.style.textAlign = 'right';
   }else if('left' == o.editAlign ){
      o.hEdit.style.textAlign = 'left';
   }else{
      o.hEdit.style.textAlign = 'center';
   }
}
function FEdit_validText(t){
   var o = this;
   var r = o.base.FEditControl.validText.call(o, t);
   if(!r){
      if(o.validLenmin){
         if(o.validLenmin > t.length){
            return RContext.get('MDescEdit:ValidMinLength', o.validLenmin);
         }
      }
      if(o.validLenmax){
         if(o.validLenmax < t.length){
            return RContext.get('MDescEdit:ValidMaxLength', o.validLenmax);
         }
      }
   }
   return r;
}
function FEdit_findEditor(){
   var o = this;
   if(o.editComplete){
      var de = o.editor;
      if(!de){
         o.dsControl = o.topControl(MDataset);
         if(o.dsControl){
            de = o.editor = RConsole.find(FEditConsole).focus(o, FEditEditor);
         }
      }
      if(de){
         de.linkControl(o);
      }
      return o.editor;
   }
}
function FEdit_drop(){
   var o = this;
   var de = o.findEditor();
   if(de){
      var t = o.reget();
      if(t.length > 0){
         if(o.finded != t){
            if(de.source != o){
               de.linkControl(o);
            }
            de.search(t);
         }
         o.finded = t;
      }
   }
}
function FEdit_clone(){
   var o = this;
   var r = o._class.newInstance();
   GHtml_clone(r, o.hPanel);
   return r;
}
function FEdit_link(){
   var o = this;
}
function FEdit11(o){
   o = RClass.inherits(this, o, FEdit1Control, MDescEdit, MEditBorder);
   o.stUnit        = RClass.register(o, new TStyle('Unit'));
   o.borderStyle   = EBorder.Round;
   o.hUnit         = null;
   o.onDataKeyDown = FEdit1_onDataKeyDown;
   o.onBuildEdit   = FEdit1_onBuildEdit;
   o.formatValue   = FEdit1_formatValue;
   o.set           = FEdit1_set;
   o.setText       = FEdit1_setText;
   o.validText     = FEdit1_validText;
   o.findEditor    = FEdit1_findEditor;
   o.drop          = FEdit1_drop;
   o.link          = FEdit1_link;
   o.innerClone    = FEdit1_innerClone;
   o.eventClone    = FEdit1_eventClone;
   o.clone         = FEdit1_clone;
   return o;
}
function FEdit1_onDataKeyDown(s, e){
   var o = this;
   o.base.FEdit1Control.onDataKeyDown.call(o, s, e);
   if(o.editCase){
      RKey.fixCase(e, o.editCase);
   }
   if(o._editable){
      return;
      if(o.editComplete){
         if( 16 != e.keyCode && 17 != e.keyCode && 18 != e.keyCode && 20 != e.keyCode ){
            var ed = o.findEditor();
            if(ed){
               ed.onEditKeyDown(s, e);
            }
         }
      }
   }
}
function FEdit1_onBuildEdit(b){
   var o = this;
   var htb = RBuilder.appendTable(b.hPanel);
   htb.style.tableLayout = 'fixed';
   var hr = o.hEdit = htb.insertRow();
   o.onBuildChange(hr.insertCell());
   var hep = hr.insertCell();
   var he = o.hEdit = RBuilder.appendEdit(hep, o.style('Edit'));
   he._ptyName = 'hEdit';
   if(o.editLength){
      he.maxLength = o.editLength;
   }
}
function FEdit1_formatValue(v){
   var o = this;
   var r = RString.nvl(v);
   if(ECase.Upper == o.editCase){
      r = RString.toUpper(r);
   }else if(ECase.Lower == o.editCase){
      r = RString.toLower(r);
   }
   return r;
}
function FEdit1_set(v){
   var o = this;
   o.base.FEdit1Control.set.call(o, v);
   o.finded = v;
   if(o.hChangeIcon){
      o.hChangeIcon.style.display = 'none';
   }
}
function FEdit1_setText(t){
   var o = this;
   if(!o.hEdit){
      return;
   }
   if('U'== o.editCase){
      o.hEdit.value = RString.toUpper(t);
   }else if('L'== o.editCase){
         o.hEdit.value = RString.toLower(t);
   }else{
      o.hEdit.value = t;
   }
   if('right' == o.editAlign ){
      o.hEdit.style.textAlign = 'right';
   }else if('left' == o.editAlign ){
      o.hEdit.style.textAlign = 'left';
   }else{
      o.hEdit.style.textAlign = 'center';
   }
}
function FEdit1_validText(t){
   var o = this;
   var r = o.base.FEdit1Control.validText.call(o, t);
   if(!r){
      if(o.validLenmin){
         if(o.validLenmin > t.length){
            return RContext.get('MDescEdit:ValidMinLength', o.validLenmin);
         }
      }
      if(o.validLenmax){
         if(o.validLenmax < t.length){
            return RContext.get('MDescEdit:ValidMaxLength', o.validLenmax);
         }
      }
   }
   return r;
}
function FEdit1_findEditor(){
   var o = this;
   if(o.editComplete){
      var de = o.editor;
      if(!de){
         o.dsControl = o.topControl(MDataset);
         if(o.dsControl){
            de = o.editor = RConsole.find(FEdit1Console).focus(o, FEdit1Editor);
         }
      }
      if(de){
         de.linkControl(o);
      }
      return o.editor;
   }
}
function FEdit1_drop(){
   var o = this;
   var de = o.findEditor();
   if(de){
      var t = o.reget();
      if(t.length > 0){
         if(o.finded != t){
            if(de.source != o){
               de.linkControl(o);
            }
            de.search(t);
         }
         o.finded = t;
      }
   }
}
function FEdit1_innerClone(r, s, t){
   this.eventClone(s, t);
   var sc = s.childNodes;
   var tc = t.childNodes;
   var cc = sc.length;
   for(var n = 0; n < cc; n++){
      var sh = sc[n];
      var th = tc[n];
      if(sh){
    	 if(sh._ptyName){
            r[sh._ptyName] = sh;
    	 }
    	 this.innerClone(r, sh, th);
      }
   }
}
function FEdit1_eventClone(s, t){
   t.onclick = s.onclick;
   t.onkeydown = s.onkeydown;
   t.onblur = s.onblur;
   t.onmouseenter = s.onmouseenter;
}
function FEdit1_clone(){
   var o = this;
   var r = o._class.newInstance();
   var t = r.hPanel = o.hPanel.cloneNode(true);
   var s = o.hPanel;
   o.innerClone(r, s, t);
   return r;
}
function FEdit1_link(){
   var o = this;
}
function FEditControl(o){
   o = RClass.inherits(this, o, FControl, MEditDescriptor, MEditValue, MDesign, MFocus, MDisplay, MProgress);
   o.labelType         = RClass.register(o, new TPtyStr('labelType', ELabel.All));
   o.labelPosition     = RClass.register(o, new TPtyStr('labelPosition', EPosition.Left));
   o.labelWidth        = RClass.register(o, new TPtyStr('labelWidth'));
   o.labelHeight       = RClass.register(o, new TPtyStr('labelHeight'));
   o.editWidth         = RClass.register(o, new TPtyStr('editWidth'));
   o.editHeight        = RClass.register(o, new TPtyStr('editHeight'));
   o.dataType          = RClass.register(o, new TPtyStr('dataType'));
   o.typeAble          = RClass.register(o, new TPtyStr('typeAble'), EBool.False);
   o.stForm            = RClass.register(o, new TStyle('Form'));
   o.stLabelForm       = RClass.register(o, new TStyle('LabelForm'));
   o.stEditForm        = RClass.register(o, new TStyle('EditForm'));
   o.stControlForm     = RClass.register(o, new TStyle('ControlForm'));
   o.stEdit            = RClass.register(o, new TStyle('Edit'));
   o.stEditUnit        = RClass.register(o, new TStyle('EditUnit'));
   o.stDropPanel       = RClass.register(o, new TStyle('DropPanel'));
   o._textColor        = null;
   o._foreColor        = null;
   o._backColor        = null;
   o._progress         = false;
   o.border            = null;
   o.borderStyle       = EBorder.None;
   o.hForm             = null;
   o.hFormRow          = null;
   o.hLabelForm        = null;
   o.hLabelRow         = null;
   o.hChangeIcon       = null;
   o.hIcon             = null;
   o.hLabel            = null;
   o.hControlForm      = null;
   o.hControlRow       = null;
   o.hHintPanel        = null;
   o.hHintIcon         = null;
   o.onChangeEnter     = RClass.register(o, new HMouseEnter('onChangeEnter'), FEditControl_onChangeEnter);
   o.onChangeClick     = RClass.register(o, new HClick('onChangeClick'), FEditControl_onChangeClick);
   o.onDataDoubleClick = FEditControl_onDataDoubleClick;
   o.onDataKeyDown     = FEditControl_onDataKeyDown;
   o.onDesignBegin     = FEditControl_onDesignBegin;
   o.onDesignEnd       = FEditControl_onDesignEnd;
   o.onBuildChange     = FEditControl_onBuildChange;
   o.onBuildLabel      = FEditControl_onBuildLabel;
   o.onBuildEdit       = RMethod.virtual(o, 'onBuildEdit');
   o.onBuildControl    = FEditControl_onBuildControl;
   o.onBuildPanel      = FEditControl_onBuildPanel;
   o.oeBuild           = FEditControl_oeBuild;
   o.oeDesign          = FEditControl_oeDesign;
   o.oeMode            = FEditControl_oeMode;
   o.oeProgress        = FEditControl_oeProgress;
   o.oeLoadValue       = FEditControl_oeLoadValue;
   o.scalar            = FEditControl_scalar;
   o.onScalar          = FEditControl_onScalar;
   o.doFocus           = FEditControl_doFocus;
   o.doBlur            = FEditControl_doBlur;
   o.testFocus         = FEditControl_testFocus;
   o.getEditRange      = FEditControl_getEditRange;
   o.text              = FEditControl_text;
   o.setText           = FEditControl_setText;
   o.panel             = FEditControl_panel;
   o.setLabel          = FEditControl_setLabel;
   o.setEditable       = FEditControl_setEditable;
   o.setVisible        = FEditControl_setVisible;
   o.focus             = FEditControl_focus;
   o.refreshStyle      = FEditControl_refreshStyle;
   o.dispose           = FEditControl_dispose;
   return o;
}
function FEditControl_onChangeEnter(e){
   var o = this;
   var t = null;
   if(RString.isEmpty(o.dataValue)){
      t = RContext.get('FEditControl:change.empty');
   }else{
      t = RContext.get('FEditControl:change.restore', o.dataValue);
   }
   o.hChangeIcon.title = t;
}
function FEditControl_onChangeClick(e){
   this.set(this.dataValue);
}
function FEditControl_onScalar(g){
   var o = this;
   o.set(g.result);
}
function FEditControl_scalar(a){
   var o = this;
   var g = new TDatasetScalarArg(o, null, a);
   g.callback = new TInvoke(o, o.onScalar);
   RConsole.find(FDatasetConsole).scalar(g);
}
function FEditControl_onDataDoubleClick(){
   var o = this;
   if(RClass.isClass(o, MDropable)){
      o.onDropDoubleClick();
   }
   if(RClass.isClass(o, MListView)){
      o.onListClick();
   }
}
function FEditControl_onDataKeyDown(s, e){
   var o = this;
   o.base.MEditDescriptor.onDataKeyDown.call(o, s, e);
   var hci = o.hChangeIcon;
   if(hci){
      hci.style.display = o.isDataChanged() ? 'block' : 'none';
   }
   if(RClass.isClass(o, MDropable) && EKey.Down==e.keyCode){
      o.drop();
   }else if(e.ctrlKey && (EKey.Enter==e.keyCode) && o.editSearch){
      var dc = o.dsControl;
      if(dc){
         if(!o.isValid){
            var sn = new TNode('Search');
            var n = sn.create('Item');
            n.set('name', o.name);
            n.set('data_name', o.dataName);
            n.set('data_value', o.dataValue);
            n.set('search_type', ESearch.Equals);
            n.set('search_order', EOrder.None);
            RConsole.find(FDatasetConsole).fetch(dc, sn);
         }
      }
   }
}
function FEditControl_onDesignBegin(){
   var o = this;
   o.base.MDesign.onDesignBegin.call(o);
   o._disbaled = true;
   o.hEdit.disbaled = true;
}
function FEditControl_onDesignEnd(){
   var o = this;
   o.base.MDesign.onDesignEnd.call(o);
   o._disbaled = false;
   o.hEdit.disbaled = false;
}
function FEditControl_onBuildChange(hc){
   var o = this;
   hc.vAlign = 'top';
   hc.width = 5;
   var hi = o.hChangeIcon = RBuilder.appendIcon(hc, 'ctl.chgflag');
   hi._pname = 'hChangeIcon';
   hi.style.display = 'none';
   hi.style.cursor = 'hand';
   o.attachEvent('onChangeEnter', hi, o.onChangeEnter);
   o.attachEvent('onChangeClick', hi, o.onChangeClick);
}
function FEditControl_onBuildLabel(){
   var o = this;
   var h = o.hLabelForm = RBuilder.newTable(null, o.style('LabelForm'));
   h._pname = 'hLabelForm';
   var hr = o.hLabelRow = h.insertRow();
   hr._pname = 'hLabelRow';
   var hc = hr.insertCell();
   hc.width = 20;
   if(o.labelIcon){
      o.hIcon = RBuilder.appendIcon(hc, o.labelIcon);
      o.hIcon._pname = 'hIcon';
   }
   var hc = o.hLabel = hr.insertCell();
   hc._pname = 'hLabel';
   hc.noWrap = true;
   o.setLabel(o.label);
   var hl = o.hLabel;
   if(hl){
      if(o.validRequire){
         hl.style.color = EColor.Require;
      }
      if(RClass.isClass(o, MListView)){
         o.setLabelStyle(hl);
      }
   }
}
function FEditControl_onBuildControl(){
   var o = this;
   var h = o.hControlForm = RBuilder.newTable(null, o.style('ControlForm'));
   h._pname = 'hControlForm';
   h.width = '100%';
   h.height = '100%';
   var hcr = o.hControlRow = h.insertRow();
   hcr._pname = 'hControlRow';
   hcr._pname = 'hControlRow';
   var hc = o.hEditCell = o.hControlRow.insertCell();
   hc._pname = 'hEditCell';
   if(o.editWidth){
      hc.width = o.editWidth;
   }
   if(o.base.MEditBorder){
      o.onBuildEditBorder(o.hEditCell);
      o.onBuildEdit(o.editBorder);
      if(o.base.MDropable){
         o.onBuildDrop();
         o.editBorder.hDrop.appendChild(o.hDrop);
      }
   }else{
      o.onBuildEdit(hc);
   }
   var he = o.hEdit;
   if(he){
      if(o.editAlign){
         he.style.textAlign = o.editAlign;
      }
      o.linkEvent(o, 'onFocus', he);
      o.linkEvent(o, 'onBlur', he);
      o.linkEvent(o, 'onDataClick', he);
      o.linkEvent(o, 'onDataDoubleClick', he);
      o.linkEvent(o, 'onDataKeyDown', he);
      o.linkEvent(o, 'onDataChange', he);
   }
   if(o.hint){
      var hp = o.hHintPanel = hcr.insertCell();
      hp.width = 13;
      hp.align = 'right';
      hp.vAlign = 'top';
      var hi = o.hHintIcon = RBuilder.appendIcon(hp, 'ctl.hint');
      hi._pname = 'hHintIcon';
      hi.title = o.hint;
   }
   if(o.editUnit){
      var h = o.hUnit = o.hControlRow.insertCell();
      h.className = o.style('EditUnit');
      h._pname = 'hUnit';
      h.innerHTML = '&nbsp;'+o.editUnit;
   }
}
function FEditControl_onBuildPanel(){
   var o = this;
   o.hPanel = o.hForm = RBuilder.newTable(o.hPanel, o.style('Form'));
   o.hPanel._ptyName = 'hPanel';
   o.hForm._pname = 'hForm';
}
function FEditControl_oeBuild(e){
   var o = this;
   if(o.labelWidth && o.editWidth && o.width){
      if(RInteger.parse(o.width) < RInteger.parse(o.labelWidth) + RInteger.parse(o.editWidth)){
         o.width = null;
      }
   }
   o.base.FControl.oeBuild.call(o, e);
   var hl = null;
   var hc = null;
   var ht = o.hForm;
   if(ELabel.Label == o.labelType){
      hl = o.hFormRow = ht.insertRow().insertCell();
      hl._pname = 'hFormRow';
   }else if(ELabel.Hidden == o.labelType){
      hc = o.hFormRow = ht.insertRow().insertCell();
      hc._pname = 'hFormRow';
   }else{
      if(EPosition.Top == o.labelPosition){
         hl = ht.insertRow().insertCell();
         hc = ht.insertRow().insertCell();
      }else if(EPosition.Right == o.labelPosition){
         var hRow = ht.insertRow();
         hc = hRow.insertCell();
         hl = hRow.insertCell();
      }else if(EPosition.Bottom == o.labelPosition){
         hc = ht.insertRow().insertCell();
         hl = ht.insertRow().insertCell();
      }else{
         var hRow = o.hFormRow = ht.insertRow();
         hRow._pname = 'hFormRow';
         hl = hRow.insertCell();
         hc = hRow.insertCell();
      }
   }
   if(hl){
      o.onBuildLabel();
      if(o.labelWidth){
         hl.style.width = o.labelWidth;
      }
      if(o.labelHeight){
         hl.style.height = o.labelHeight;
      }
      if(o.labelAlign){
         hl.align = o.labelAlign;
      }
      if(o.labelColor){
         o.hLabel.style.color = o.labelColor;
      }
      hl.appendChild(o.hLabelForm);
   }
   if(hc){
      o.onBuildControl();
      hc.appendChild(o.hControlForm);
   }
   return EEventStatus.Stop;
}
function FEditControl_oeDesign(e){
   var o = this;
   o.base.MDesign.oeDesign.call(o, e);
   var hlf = o.hLabelForm;
   var hef = o.hEditForm;
   switch(e.mode){
      case EDesign.Move:
         if(e.flag){
            o.hForm.border = 1;
            if(hlf){
               hlf.cellPadding = 1;
            }
            if(hef){
            }
            if(o.hEdit){
               o.hEdit.disabled = true;
            }
         }else{
            o.hForm.border = 0;
            if(hlf){
               hlf.border = 0;
               hlf.cellPadding = 0;
            }
            if(hef){
            }
            if(o.hEdit){
               o.hEdit.disabled = false;
            }
         }
         break;
      case EDesign.Border:
         if(e.flag){
            o.hForm.border = 1;
            if(hef){
               hef.border = 1;
            }
         }else{
            o.hForm.border = 0;
            if(hef){
               hef.border = 0;
            }
         }
         break;
   }
   return EEventStatus.Stop;
}
function FEditControl_oeMode(e){
   var o = this;
   o.base.FControl.oeMode.call(o, e);
   o.base.MDisplay.oeMode.call(o, e);
   o._editable = o.canEdit(e.mode);
   o._validable = o.canValid(e.mode);
   if(!o._progress){
      o.setEditable(o._editable);
   }
   return EEventStatus.Stop;
}
function FEditControl_oeProgress(e){
   var o = this;
   if(o._progress && e.enable){
      return EEventStatus.Stop;
   }
   o._progress = e.enable;
   if(e.enable){
      var ea = o._editable;
      o.setEditable(false);
      o._editable = ea;
   }else{
      o.setEditable(o._editable);
   }
   return EEventStatus.Stop;
}
function FEditControl_oeLoadValue(e){
   var o = this;
   var r = o.base.MEditValue.oeLoadValue.call(o, e);
   var hci = o.hChangeIcon;
   if(hci){
      hci.style.display = 'none';
   }
   return r;
}
function FEditControl_doFocus(e){
   var o = this;
   o.base.MFocus.doFocus.call(o, e);
   o.base.MEditValue.doFocus.call(o, e);
}
function FEditControl_doBlur(e){
   var o = this;
   o.base.MFocus.doBlur.call(o, e);
   o.base.MEditValue.doBlur.call(o, e);
}
function FEditControl_testFocus(){
   return this._visible && this._editable && !this._disbaled;
}
function FEditControl_getEditRange(){
   var o = this;
   var hc = o.hEditCell;
   var p = RHtml.offsetPosition(hc);
   var w = hc.offsetWidth;
   var h = hc.offsetHeight;
   return new TRange(p.x, p.y, w, h);
}
function FEditControl_text(){
   return this.hEdit ? this.hEdit.value : '';
}
function FEditControl_setText(t){
   this.hEdit.value = t;
}
function FEditControl_panel(t){
   var o = this;
   if(EPanel.Edit == t){
      return o.hEdit;
   }else if(EPanel.Focus == t){
      return o.hEdit;
   }
   return o.base.FControl.panel.call(o, t);
}
function FEditControl_setLabel(s){
   var o = this;
   o.label = s;
   o.hLabel.innerHTML = RString.nvl(s);
}
function FEditControl_setEditable(v){
   var o = this;
   o.base.MEditValue.setEditable.call(o, v);
   if(o.hEdit){
      o.hEdit.readOnly = !v;
   }
   var hl = o.hLabel;
   if(hl){
      if(o.validRequire){
         o.hLabel.style.color = v ? EColor.Require : EColor.Text;
      }
      if(RClass.isClass(o, MListView) && o.canListView()){
         hl.style.cursor = v ? 'hand' : 'normal';
         hl.className = v ? 'RLine_Underline' : '';
      }
   }
}
function FEditControl_setVisible(v){
   var o = this;
   o.base.FControl.setVisible.call(o, v);
   o.refreshStyle();
}
function FEditControl_focus(){
   var o = this;
   o.base.MFocus.focus.call(o);
   if(o.hEdit){
      try{
         o.hEdit.focus();
      }catch(e){
      }
   }
}
function FEditControl_refreshStyle(){
   var o = this;
   if(!o._visible){
      return;
   }
   var tc = EColor.TextReadonly;
   var bc = EColor.Readonly;
   var cr = 'normal';
   if(o._editable){
      tc = EColor.TextEdit;
      bc = EColor.Edit;
      cr = 'hand';
      if(!RString.isEmpty(o.editTip) && o.hEdit.innerText == o.editTip){
         tc = '#CCCCCC';
      }
   }
   if(o._invalidText){
      if(!RString.isEmpty(o.text())){
         tc = EColor.TextInvalid;
         bc = EColor.Invalid;
      }
   }
   o._textColor = tc;
   o._backColor = bc;
   var he = o.hEdit;
   var hd = o.hDrop;
   if(he){
      he.style.color = tc;
      he.style.backgroundColor = bc;
   }
   if(hd){
	   if(he){
	      he.style.cursor = cr;
	   }
	   hd.style.cursor = cr;
	}
   if(o.editBorder){
      var bs = EBorderStyle.Readonly;
      if(o._editable){
         bs = EBorderStyle.Edit;
      }
      if(o._hover){
         bs = EBorderStyle.Hover;
      }
      o.setEditBorderStyle(bs, bc);
   }
}
function FEditControl_dispose(){
   var o = this;
   o.base.FControl.dispose.call(o);
   o.hForm = null;
   delete o.hForm;
   o.hFormRow = null;
   delete hFormRow;
   o.hLabelForm = null;
   delete hLabelForm;
   o.hChangeIcon = null;
   delete o.hChangeIcon;
   o.hIcon = null;
   delete o.hIcon;
   o.hLabel = null;
   delete o.hLabel;
   o.hControlForm = null;
   delete o.hControlForm;
   o.hControlRow = null;
   delete o.hControlRow;
   o.hControl = null;
   delete o.hControl;
   o.hEdit = null;
   delete o.hEdit;
   o.hHintPanel = null;
   delete o.hHintPanel;
   o.hHintIcon = null;
   delete o.hHintIcon;
}
function FEditEditor(o){
   o = RClass.inherits(this, o, FDropEditor, MShadow);
   o.MinWidth          = 120;
   o.MaxCount          = 20;
   o.stIconDropSelect  = RClass.register(o, new TStyleIcon('DropSelect'));
   o.stFlag            = RClass.register(o, new TStyle('Flag'));
   o.stRow             = RClass.register(o, new TStyle('Row'));
   o.stRowHover        = RClass.register(o, new TStyle('RowHover'));
   o.stRowSelect       = RClass.register(o, new TStyle('RowSelect'));
   o.stEditForm        = RClass.register(o, new TStyle('EditForm'));
   o.stItemsForm       = RClass.register(o, new TStyle('ItemsForm'));
   o.pattern           = null;
   o.originItem        = null;
   o.selectItem        = null;
   o.items             = null;
   o.inSearch          = false;
   o.position          = null;
   o.itemClickListener = null;
   o.onBuildDrop       = FEditEditor_onBuildDrop;
   o.onItemClick       = FEditEditor_onItemClick;
   o.invoke            = FEditEditor_invoke;
   o.onComplete        = FEditEditor_onComplete;
   o.onEditKeyDown     = FEditEditor_onEditKeyDown;
   o.construct         = FEditEditor_construct;
   o.get               = RMethod.empty;
   o.set               = RMethod.empty;
   o.search            = FEditEditor_search;
   o.select            = FEditEditor_select;
   o.linkControl       = FEditEditor_linkControl;
   o.show              = FEditEditor_show;
   o.hide              = FEditEditor_hide;
   o.dispose           = FEditEditor_dispose;
   return o;
}
function FEditEditor_onBuildDrop(){
   var o = this;
   o.hItemsForm = RBuilder.appendTable(o.hDropPanel, o.style('ItemsForm'));
   var hip = o.hItemsPanel = RBuilder.append(o.hItemsForm, 'TBODY');
   for(var n=0; n<o.MaxCount; n++){
      var hr = null;
      if(n > 0){
         hr = RBuilder.append(hip, 'TR');
         hr.height = 1;
         hr.style.display = 'none';
         var hd = RBuilder.append(hr, 'TD');
         hd.colSpan = 3;
         hd.style.borderTop = '1 dashed #24C2DB';
         RBuilder.appendEmpty(hd);
      }
      var c = RControl.create(FSelectItem);
      c.setPanel(hip);
      c.name = null;
      c.setVisible(false);
      c.hSplitRow = hr;
      c.lsnsClick.push(o.itemClickListener);
      o.push(c);
   }
}
function FEditEditor_onItemClick(s){
   var o = this;
   var e = o.source;
   e.set(s.label);
   o.source = null;
   o.hide();
}
function FEditEditor_invoke(g){
   var o = this;
   o.onComplete(g.resultConfig);
}
function FEditEditor_onComplete(xr){
   var o = this;
   o.inSearch = false;
   var nc = xr.nodes ? xr.nodes.count : 0;
   if(0 == nc){
      return o.hide();
   }
   var t = o.source.reget();
   o.position = 0;
   o.count = nc;
   for(var n=0; n<o.MaxCount; n++){
      var x = xr.nodes ? xr.nodes.get(n) : null;
      var c  = o.components.value(n);
      var hr = c.hSplitRow;
      if(n < nc){
         if(hr){
            hr.style.display = 'block';
         }
         var xd = x.get('data');
         if(t == xd){
            o.position = n;
         }
         c.set(null, xd, null, '[' + x.get('count') + ']');
         c.setChecked(t == xd);
      }else{
         if(hr){
            hr.style.display = 'none';
         }
      }
      c.setVisible(n < nc);
   }
   o.show();
}
function FEditEditor_onEditKeyDown(s, e){
   var o = this;
   var kc = e.keyCode;
   if(EKey.Up == kc){
      o.select(o.position - 1);
   }else if(EKey.Down == kc){
      o.select(o.position + 1);
   }else if(EKey.Enter == kc){
      var c = o.components.value(o.position);
      if(c){
         o.source.set(c.label);
      }
      o.blur();
   }else if(EKey.Esc == kc){
      o.blur();
   }else if(EKey.Left == kc || EKey.Right == kc || EKey.Home == kc || EKey.End == kc){
      return;
   }else{
      o.search(o.source.reget());
   }
}
function FEditEditor_construct(){
   var o = this;
   o.itemClickListener = new TListener(o, o.onItemClick);
}
function FEditEditor_search(t){
   var o = this;
   if(RString.isEmpty(t)){
      return o.hide();
   }
   if(!o.inSearch){
      o.formName = o.source.dsControl.name;
      o.controlName = o.source.name;
      o.controlValue = t;
      o.inSearch = true;
      var arg = new TDatasetCompleteArg(o.formName, o.controlName, o.controlValue);
      arg.callback = o;
      var fdc = RConsole.find(FDatasetConsole);
      fdc.complete(arg);
   }
}
function FEditEditor_select(p){
   var o = this;
   p = Math.min(Math.max(0, p), o.count-1)
   for(var n=0; n<o.count; n++){
      o.components.value(n).setChecked(n == p);
   }
   o.position = p;
}
function FEditEditor_linkControl(c){
   var o = this;
   if(o.source == c){
      return false;
   }
   o.source = c;
   RLogger.debug(o, 'link Panel (panel={0}, edit={1})', RClass.dump(c.hEditCell), RClass.dump(c.hEdit));
   RHtml.toRect(o.rect, c.hEditCell);
   RHtml.setPixelRect(o.hPanel, o.rect);
   o.hPanel.style.pixelTop = o.rect.bottom;
   var hbf = o.border.hForm;
   hbf.style.pixelWidth = c.editBorder.hForm.width;
   hbf.style.pixelHeight = c.editBorder.hForm.height;
   return true;
}
function FEditEditor_show(v){
   var o = this;
   o.base.FDropEditor.show.call(o, v);
   RConsole.find(FFocusConsole).focus(o);
   var hbf = o.border.hForm;
   if(hbf.offsetWidth < o.MinWidth){
      hbf.style.pixelWidth = o.MinWidth;
   }
   o.base.MShadow.show.call(o, v);
   o.isSkipBlur = false;
}
function FEditEditor_hide(){
   var o = this;
   o.base.FDropEditor.hide.call(o);
   o.base.MShadow.hide.call(o);
}
function FEditEditor_dispose(){
   var o = this;
   o.base.FDropEditor.dispose.call(o);
   RMemory.freeHtml(o.hItemsForm);
   RMemory.freeHtml(o.hEdit);
   o.hChgIic     = null;
   o.hEdit       = null;
}
function FFileBrowser(o){
   o = RClass.inherits(this, o, FContainer, MValue, MEditDescriptor);
   o.type            = null;
   o.perpareItem     = null;
   o.items           = new TList();
   o.dsName          = null;
   o.id              = null;
   o.hForm           = null;
   o.newNode         = null;
   o.fileTree        = null;
   o.hMessages       = null;
   o.oeBuild         = FFileBrowser_oeBuild;
   o.onBuildPanel    = FFileBrowser_onBuildPanel;
   o.onClickUpload   = FFileBrowser_onClickUpload;
   o.onClickDownload = FFileBrowser_onClickDownload;
   o.onClickDelete   = FFileBrowser_onClickDelete;
   o.onClickNew      = FFileBrowser_onClickNew;
   o.onBuildTree     = FFileBrowser_onBuildTree;
   o.onFileSelected  = FFileBrowser_onFileSelected;
   o.saveValue       = FFileBrowser_saveValue;
   o.loadValue       = FFileBrowser_loadValue;
   o.newFolder       = FFileBrowser_newFolder;
   o.parseToNode     = FFileBrowser_parseToNode;
   o.onLoaded        = FFileBrowser_onLoaded;
   o.refresh         = FFileBrowser_refresh;
   o.resetValue      = FFileBrowser_resetValue;
   o.loadDefault     = FFileBrowser_loadDefault;
   o.reget           = FFileBrowser_reget;
   o.get             = FFileBrowser_get;
   o.set             = FFileBrowser_set;
   return o;
}
function FFileBrowser_oeBuild(event){
   var o = this;
   o.base.FContainer.oeBuild.call(o, event);
   var hTab = RBuilder.appendTable(o.hPanel);
   hTab.style.border = '1px solid #00CCCC';
   hTab.width = '100%';
   hTab.height = '100%';
   var hRow = hTab.insertRow();
   var h = o.hTitlePanel = hRow.insertCell();
   h.height = 24;
   var tb = RClass.create(FToolBar);
   tb.width = '100%';
   var tbn = RClass.create(FToolButton);
   tbn.icon = 'ctl.FUploadView_Insert';
   tbn.label = RContext.get('FFileBrowser:Upload')
   tbn.lsnsClick.register(o, o.onClickUpload);
   tb.push(tbn);
   var tbn = RClass.create(FToolButton);
   tbn.icon = 'ctl.FUploadView_Upload';
   tbn.label = RContext.get('FFileBrowser:Download');
   tbn.lsnsClick.register(o, o.onClickDownload);
   tb.push(tbn);
   var tbn = RClass.create(FToolButton);
   tbn.icon = 'ctl.FUploadView_Upload';
   tbn.label = RContext.get('FFileBrowser:Delete');
   tbn.lsnsClick.register(o, o.onClickDelete);
   tb.push(tbn);
   tb.psBuild(h);
   o.hFieldsPanel = hTab.insertRow().insertCell();
   o.hFieldsPanel.vAlign = 'top';
   o.onBuildTree();
   return EEventStatus.Stop;
}
function FFileBrowser_onBuildPanel(){
   var o = this;
   o.hPanel = RBuilder.append(null, 'DIV');
   o.hPanel.style.zIndex = 0;
}
function FFileBrowser_onFileSelected(ui){
   var o = this;
   var ui = o.perpareItem;
   o.perpareItem = null;
   o.pushItem(ui);
}
function FFileBrowser_onClickDownload(){
   var o = this;
   document.execCommand("saveAs");
}
function FFileBrowser_onClickNew(){
   var o = this;
   var iw = top.RControl.create(FInputWindow);
   iw.source = true;
   iw.lsns.register(o, o.newFolder);
   iw.show();
}
function FFileBrowser_newFolder(name){
   var o = this;
   var n = RControl.create(FTreeNode);
   n.name = name;
   n.label = name;
   n.tree = o.fileTree;
   n.parent = RObject.nvl(o.fileTree.focusNode, o.fileTree.rootNode);
   n.attributes.set('FILE_TYPE', '文件夹');
   n.icon = 'ctl.FFileBrowser_Folder';
   n.build(0);
   n.parent.push(n);
   o.fileTree.allNodes.push(n);
}
function FFileBrowser_onClickDelete(){
   var o = this;
   if(confirm('Are you sure about it ?')){
      var fn = o.fileTree.focusNode
      o.fileTree.removeNodes(fn);
      var doc = new TXmlDocument();
      var root = doc.root();
      root.set('action', 'remove');
      var nd = new TNode('Node',fn.attributes);
      nd.set("path", fn.getFullPath());
      root.push(nd);
      var url = RService.url('logic.webform.store');
      var e = new TEvent(o, EXmlEvent.SyncSend);
      e.url = url;
      e.document = doc;
      e.action = EDataAction.Update;
      RConsole.find(FXmlConsole).process(e);
   }
}
function FFileBrowser_onClickUpload(){
   var o = this;
   var uw = RConsole.find(FUploadConsole).findWindow();
   var tc = o.topControl();
   uw.recordType = tc.dsName;
   uw.recordId = tc.component('OUID').reget();
   uw.show();
}
function FFileBrowser_onBuildTree(){
   var o = this;
   var ht = o.fileTree = RControl.create(FTreeView, o.hFieldsPanel);
   var col1 = RControl.create(FTreeColumn);
   col1.hPanel.innerText='Folder';
   ht.tempAppendChild(col1);
   col1.tree = ht;
   col1.dataName = 'NAME'
   ht.columns.set(col1.dataName, col1);
   var col2 = RControl.create(FTreeColumn);
   col2.hPanel.innerText='Type';
   ht.tempAppendChild(col2);
   col2.tree = ht;
   col2.dataName = 'FILE_TYPE'
   ht.columns.set(col2.dataName, col2);
   var col4 = RControl.create(FTreeColumn);
   col4.hPanel.innerText='Size';
   ht.tempAppendChild(col4);
   col4.tree = ht;
   col4.dataName = 'DATA_SIZE';
    ht.columns.set(col4.dataName, col4);
   var col5 = RControl.create(FTreeColumn);
   col5.hPanel.innerText='Date';
   ht.tempAppendChild(col5);
   col5.tree = ht;
   col5.dataName = 'DATA_DATE';
    ht.columns.set(col5.dataName, col5);
}
function FFileBrowser_set(v){
   var o = this;
   var fs = new TStrings();
   fs.unpack(v);
   var xTree = o.parseToNode(fs);
   if(o.fileTree.rootNode.hasChild()){
      o.fileTree.clearNodes(o.fileTree.rootNode);
   }
   o.fileTree.tempAppendNodes(null, xTree);
}
function FFileBrowser_parseToNode(fs){
   var o = this;
   var root = new TNode("Config");
   for(var m = 0; m < fs.count; m++){
     var pNode = root;
     var s = fs.get(m);
      var ts = new TAttributes();
      ts.unpack(s);
      var name = ts.get('path');
      var ns = RString.split(name, '/');
      for(var n = 0; n < ns.length; n++){
        var ss = ns[n];
        if(null == root.findNode('label', ss)){
            var nd = new TNode('TreeNode');
            nd.set('label', ss);
            nd.set('name', ss);
            if(n != ns.length -1){
              nd.set('FILE_TYPE', 'Folder');
              nd.set('child', 'Y');
            }else{
               nd.set('OUID', ts.get('OUID'));
               nd.set('FILE_TYPE', ts.get('MIME_TYPE'));
              nd.set('DATA_SIZE', RInt.parse(ts.get('DATA_SIZE'))/1000+'KB');
              nd.set('DATA_DATE', RDate.formatDate(RDate.autoParse(null, ts.get('DATA_DATE')),'yyyy-mm-dd'));
              nd.set('child', 'N');
            }
            pNode.push(nd);
            pNode = nd;
        }else{
           pNode = root.findNode('label', ss);
         }
      }
   }
   return root;
}
function FFileBrowser_loadValue(c, t){
   var o = this;
   var d = o.descriptor();
   if(EStore.Name == t){
      o.set(c.get(d.name));
   }else if(EStore.DataNvl == t){
      if(c.contains(d.dataName)){
        o.set(c.get(d.dataName));
      }
   }else if(EStore.Reset == t){
      o.set(RString.EMPTY);
   }else{
      o.set(c.get(d.dataName));
   }
}
function FFileBrowser_saveValue(c, t){
   var o = this;
   var d = o.descriptor();
   if(EStore.Name == t){
      c.set(d.name, o.reget());
   }else{
      c.set(d.dataName, o.reget());
   }
}
function FFileBrowser_refresh(){
   var m = this.topControl(MDataset);
   m.dsFetch(true);
}
function FFileBrowser_onLoaded(e){
   alert();
}
function FFileBrowser_resetValue(){
   return;
}
function FFileBrowser_loadDefault(){
   return;
}
function FFileBrowser_get(){
   return null;
}
function FFileBrowser_reget(){
   return null;
}
function FForm(o){
   o = RClass.inherits(this, o, FLayout, MFocus, MForm, MDisplayAble, MValue, MDataset, MAction);
   o.__status           = ERowStatus.Update;
   o.__clearEvent       = null;
   o.__resetEvent       = null;
   o.__loadEvent        = null;
   o.__saveEvent        = null;
   o.__recordEvent      = null;
   o.__codeEvent        = null;
   o.__dataComponents   = null;
   o.lsnsLoaded         = null;
   o.lsnsClick          = null;
   o.onMouseDown        = FForm_onMouseDown;
   o.onLoadDataset      = FForm_onLoadDataset;
   o.onLoadDatasetEnd   = FForm_onLoadDatasetEnd;
   o.construct          = FForm_construct;
   o.isDataChanged      = FForm_isDataChanged;
   o.getFormLink        = FForm_getFormLink;
   o.allDataComponents  = FForm_allDataComponents;
   o.get                = FForm_get;
   o.reget              = FForm_reget;
   o.set                = FForm_set;
   o.getDataCodes       = FForm_getDataCodes;
   o.getCurrentRow      = FForm_getCurrentRow;
   o.getSelectedRows    = FForm_getSelectedRows;
   o.getCurrentRows     = FForm_getCurrentRows;
   o.getChangedRows     = FForm_getChangedRows;
   o.getRows            = FForm_getRows;
   o.clearValue         = FForm_clearValue;
   o.resetValue         = FForm_resetValue;
   o.loadValue          = FForm_loadValue;
   o.saveValue          = FForm_saveValue;
   o.recordValue        = FForm_recordValue;
   o.toAttributes       = FForm_toAttributes;
   o.focus              = FForm_focus;
   o.dsUpdate           = FForm_dsUpdate;
   o.doPrepare          = FForm_doPrepare;
   o.doUpdate           = FForm_doUpdate;
   o.doDelete           = FForm_doDelete;
   o.dispose            = FForm_dispose;
   o._nameComponents    = null;
   o.allNameComponents  = FForm_allNameComponents;
   o.isLoading          = false;
   o.onLoaded           = FForm_onLoaded;
   o.onDsFetchEnd       = FForm_onDsFetchEnd;
   o.onDsUpdateBegin    = FForm_onDsUpdateBegin;
   o.onDsUpdateEnd      = FForm_onDsUpdateEnd;
   o.onLoadValue        = RMethod.empty;
   o.onSaveValue        = RMethod.empty;
   o.connect            = FForm_connect;
   o.loadDocument       = FForm_loadDocument;
   o.testStatus         = FForm_testStatus;
   o.hasAction          = FForm_hasAction;
   o.setEditable        = FForm_setEditable;
   return o;
}
function FForm_onMouseDown(e, he){
   var o = this;
   var fc = RConsole.find(FFocusConsole);
   fc.focusClass(MDataset, o);
   fc.focusHtml(he);
   if(!RConsole.find(FDesignConsole).isDesign()){
      he.cancelBubble = true;
   }
}
function FForm_onLoadDataset(ds){
   var o = this;
   o.doUpdate(o.dsViewer.current());
}
function FForm_onLoadDatasetEnd(){
   var o = this;
   o.topControl().topResize();
   o.psProgress(false);
}
function FForm_construct(){
   var o = this;
   o.base.FLayout.construct.call(o);
   o.base.MDataset.construct.call(o);
   o.lsnsLoaded = new TListeners();
   o.lsnsClick = new TListeners();
   o.__clearEvent = new TEventProcess(o, 'oeClearValue', MEditValue);
   o.__resetEvent = new TEventProcess(o, 'oeResetValue', MEditValue);
   o.__loadEvent = new TEventProcess(o, 'oeLoadValue', MEditValue);
   o.__saveEvent = new TEventProcess(o, 'oeSaveValue', MEditValue);
   o.__recordEvent = new TEventProcess(o, 'oeRecordValue', MEditValue);
   o.__codeEvent = new TEventProcess(o, 'oeSaveCode', MEditDescriptor);
   o.__dataComponents = new TMap();
}
function FForm_isDataChanged(){
   var o = this;
   var ps = o.allDataComponents();
   if(!ps.isEmpty()){
      var pc = ps.count;
      for(var n=0; n<pc; n++){
         var p = ps.value(n);
         if(p.isDataChanged()){
            return true;
         }
      }
   }
}
function FForm_getFormLink(t){
   var o = this;
   if(EFormLink.Form == t){
      return o.name;
   }else if(EFormLink.Table == t){
      return o.formName;
   }
   RMessage.fatal(o, null, 'Form link is invalid. (type={0})', t);
}
function FForm_allDataComponents(p, m){
   var o = this;
   if(!p){
      p = o;
   }
   if(!m){
      m = o.__dataComponents;
   }
   var cs = p.components;
   if(cs){
      var cc = cs.count;
      for(var n = 0; n<cc; n++){
         var c = cs.value(n);
         if(!RClass.isClass(c, MDataset)){
            if(RClass.isClass(c, MValue)){
               m.set(c.dataName, c);
            }
            o.allDataComponents(c, m);
         }
      }
   }
   return m;
}
function FForm_get(n){
   var ps = this.allDataComponents();
   if(ps){
      var p = ps.get(n);
      if(p){
         return p.get();
      }
   }
}
function FForm_reget(n){
   var ps = this.allDataComponents();
   if(ps){
      var p = ps.get(n);
      if(p){
         return p.reget();
      }
   }
}
function FForm_set(n, v){
   var ps = this.allDataComponents();
   if(ps){
      var p = ps.get(n);
      if(p){
         p.set(v);
      }
   }
}
function FForm_getDataCodes(){
   var o = this;
   var e = o.__codeEvent;
   e.values = new TAttributes();
   o.process(e);
   return e.values;
}
function FForm_getCurrentRow(){
   return this.saveValue();
}
function FForm_getSelectedRows(){
   var ls = new TList();
   ls.push(this.saveValue());
   return ls;
}
function FForm_getCurrentRows(){
   var o = this;
   var ls = new TList();
   var r = new TRow();
   o.toDeepAttributes(r);
   o.saveValue(r);
   ls.push(r);
   return ls;
}
function FForm_getChangedRows(){
   var o = this;
   var ls = new TList();
   if(o.isDataChanged()){
      var r = new TRow();
      o.toDeepAttributes(r);
      o.saveValue(r);
      ls.push(r);
   }
   return ls;
}
function FForm_getRows(){
   var ls = new TList();
   ls.push(this.saveValue());
   return ls;
}
function FForm_clearValue(){
   this.process(this.__clearEvent);
}
function FForm_resetValue(){
   this.process(this.__resetEvent);
}
function FForm_loadValue(r, m){
   if(r){
      var o = this;
      var e = o.__loadEvent;
      e.viewer = o.dsViewer;
      e.store = m;
      e.values = r;
      o.process(e);
   }
}
function FForm_saveValue(r, m){
   var o = this;
   if(!r){
      r = new TRow();
   }
   var e = o.__saveEvent;
   e.viewer = o.dsViewer;
   e.store = m;
   e.values = r;
   o.process(e);
   r.set('_status', o.__status);
   return r;
}
function FForm_recordValue(){
   this.process(this.__recordEvent);
}
function FForm_toAttributes(r, m){
   return this.saveValue(r, m);
}
function FForm_focus(){
   var o = this;
   o.base.MFocus.focus.call(o);
   o.focusControl();
   RConsole.find(FFocusConsole).focusClass(MDataset, o);
}
function FForm_dsUpdate(u, v){
   var o = this;
   if(u){
      o.psProgress(true);
      o.psMode(EMode.Update);
      var g = new TDatasetFetchArg(o.name, o.formId, o.dsPageSize, 0);
      g.form = o;
      g.reset = true;
      o.dsSearchs.clear();
      if(u){
         o.dsSearchs.push(new TSearchItem('OUID', u));
      }
      if(v){
         o.dsSearchs.push(new TSearchItem('OVER', v));
      }
      g.searchs = o.dsSearchs;
      g.values.append(o.dsValues);
      g.callback = new TInvoke(o, o.onDsUpdate);
      if(o.onDsUpdateCheck(g)){
         RConsole.find(FDatasetConsole).fetch(g);
      }
      return;
   }
   return o.base.MDataset.dsUpdate.call(o, u, v)
}
function FForm_setEditable(v){
   var ps = this.allDataComponents();
   if(ps){
	   var pc = ps.count;
	   for(var n = 0; n < pc; n++){
	      var p = ps.value(n);
	      p.setEditable(v);
	   }
   }
}
function FForm_doPrepare(v){
   var o = this;
   o.__status = ERowStatus.Insert;
   o.resetValue();
   o.loadValue(v);
   o.recordValue();
   o.dsLoaded();
}
function FForm_doUpdate(v){
   var o = this;
   o.__status = ERowStatus.Update;
   o.clearValue();
   o.loadValue(v);
   o.recordValue();
   o.dsLoaded();
}
function FForm_doDelete(v){
   var o = this;
   o.__status = ERowStatus.Delete;
   o.clearValue();
   o.loadValue(v);
   o.recordValue();
   o.dsLoaded();
}
function FForm_dispose(){
   var o = this;
   o.base.FLayout.dispose.call(o);
   RMemory.freeHtml(o.hEdit);
   RMemory.freeHtml(o.hDrop);
   o.hEdit = null;
   o.hDrop = null;
}
function FForm_allNameComponents(f, p, m){
   var o = this;
   var vs = o._nameComponents;
   if(!f && vs){
      return vs;
   }
   if(!vs){
      vs = o._nameComponents = new TMap();
   }
   if(f){
      vs.clear();
   }
   if(!p){
      p = this;
   }
   if(!m){
      m = vs;
   }
   var cs = p.components;
   if(cs){
      var cc = cs.count;
      for(var n = 0; n<cc; n++){
         var c = cs.value(n);
         if(!RClass.isClass(c, MDataset)){
            if(RClass.isClass(c, MValue)){
               m.set(c.name, c);
            }
            o.allNameComponents(false, c, m);
         }
      }
   }
   return vs;
}
function FForm_onLoaded(){
   var o = this.form;
   var doc = this.document;
   if(o && doc){
      RControl.build(o, doc.root());
      o.isLoading = false;
      o.lsnsLoaded.process(o);
   }
}
function FForm_onDsFetchEnd(){
   var o = this;
   var v = o.dsCurrent();
   if(v){
      o.loadValue(v);
   }
}
function FForm_onDsUpdateBegin(){
   var o = this;
   var v = o.dsCurrent();
   if(v){
      o.saveValue(v);
   }
}
function FForm_onDsUpdateEnd(){
   var o = this;
   var v = o.dsCurrent();
   if(v){
      o.loadValue(v);
   }
}
function FForm_connect(service, type, action, attrs){
   var doc = new TXmlDocument();
   var root = doc.root();
   root.set('type', type);
   root.set('name', this.name);
   root.set('action', action);
   root.create('Attributes').value = attrs;
   var event = new TEvent(this, EXmlEvent.Send);
   event.url = service;
   event.document = doc;
   event.form = this;
   event.onLoad = this.onLoaded;
   RConsole.find(FXmlConsole).process(event);
}
function FForm_loadDocument(doc){
   if(doc){
      var root = doc.root();
      if(root.isName('Table')){
         var o = this;
         o.loadConfig(root);
         o.buildColumns(root);
         o.buildRows(root);
      }
   }
}
function FForm_testStatus(t){
   var o = this;
   var r = o.base.MDataset.testStatus.call(o, t);
   if(EDataAction.Fetch == t){
      return true;
   }else if(EDataAction.Fetch == t){
      return true;
   }else if(EDataAction.Search== t){
      return true;
   }else if(EDataAction.First == t){
      return false;
   }else if(EDataAction.Prior == t){
      return false;
   }else if(EDataAction.Next == t){
      return false;
   }else if(EDataAction.Last == t){
      return false;
   }else if(EDataAction.Action == t){
      return true;
   }
   return r;
}
function FForm_hasAction(){
   var o = this;
   var cs = o.components;
   var ct = cs.count;
   for(var n = 0; n < ct; n++){
      var c = cs.value(n);
      if(RClass.isClass(c, FDataAction)){
         return true;
      }
   }
   return false;
}
function FFormInformationBar(o){
   o = RClass.inherits(this, o, FEditControl, MHorizontal);
   o._esize      = ESize.Horizontal;
   o.onBuildEdit = RMethod.empty;
   o.oeBuild     = FFormInformationBar_oeBuild;
   o.set         = FFormInformationBar_set;
   o.get         = RMethod.empty;
   o.dispose     = FFormInformationBar_dispose;
   return o;
}
function FFormInformationBar_oeBuild(e){
   var o = this;
   var hp = o.hForm = o.hPanel = RBuilder.appendTable();
   hp.style.marginBottom = 8;
   hp.style.borderLeft = '1 solid #666666';
   hp.style.borderTop = '1 solid #666666';
   hp.style.borderRight = '1 solid #DDDDDD';
   hp.style.borderBottom = '1 solid #DDDDDD';
   o.setBounds(o.left, o.top, o.right, o.bottom, true);
   o.setSize(o.width, o.height);
   o.setPads(o.padLeft, o.padTop, o.padRight, o.padBottom, true);
   if(RClass.isClass(o.parent, MContainer)){
      o.parent.appendChild(o);
   }
   var he = o.hEdit = hp.insertRow().insertCell();
   he.noWrap = true;
   he.style.paddingLeft = 10;
   hp.width = '100%';
   hp.height = '26';
   return EEventStatus.Stop;
}
function FFormInformationBar_set(v){
   var o = this;
   var t = new TString();
   if(v){
      var vs = RString.split(v, '|');
      var d = new TDate();
      RDate.autoParse(d, vs[3]);
      var st =  RDate.formatDate(d, RDate.DisplayFormat);
      t.append('<FONT color=#6868EE>');
      t.append(o.topControl().label);
      t.append('</FONT>  编号(<FONT color=green>');
      t.append(vs[0]);
      t.append('</FONT>) 状态(<FONT color=green>');
      if(RBool.isTrue(vs[1])){
         t.append('有效');
      }else{
         t.append('无效');
      }
      t.append('</FONT>) 上次操作(<FONT color=green>');
      if('I' == vs[2]){
         t.append('新建');
      }else if('U' == vs[2]){
         t.append('更新');
      }else if('D' == vs[2]){
         t.append('删除');
      }
      t.append('</FONT>) 上次修改时间(<FONT color=green>');
      t.append(st);
      t.append('</FONT>)');
      if(vs[5]){
         t.append(' 修改者(<FONT color=green>');
         t.append(vs[5]);
         t.append('</FONT>)');
      }
   }
   o.hEdit.innerHTML = t.toString();
}
function FFormInformationBar_dispose(){
   var o = this;
   o.base.FEditControl.dispose.call(o);
   RMemory.freeHtml(o.hEdit);
   o.hEdit = null;
}
function FGroup(o){
   o = RClass.inherits(this, o, FEditControl, MDescEdit, MEditBorder);
   o.stUnit        = RClass.register(o, new TStyle('Unit'));
   o.borderStyle        = EBorder.Round;
   o.itemType           = RClass.register(o, new TPtyStr('itemType'));
   o.itemLabelWidth     = RClass.register(o, new TPtyStr('itemLabelWidth'));
   o.itemControlWidth   = RClass.register(o, new TPtyStr('itemControlWidth'));
   o.rowItemCount       = RClass.register(o, new TPtyStr('rowItemCount'));
   o.controls           = new TList();
   o.onBuildEdit          = FGroup_onBuildEdit;
   o.onControlClick       = RClass.register(o, new HClick('onControlClick'), FGroup_onControlClick);
   o.set                  = FGroup_set;
   o.get                  = FGroup_get;
   o.reget                = FGroup_reget;
   o.resetValue           = FGroup_resetValue;
   o.resetHeight          = FGroup_resetHeight;
   o.setText              = FGroup_setText;
   o.oeValid              = FGroup_oeValid;
   o.setGroupPack         = FGroup_setGroupPack;
   o.refreshStyle         = FGroup_refreshStyle;
   o.buildGroupControl    = FGroup_buildGroupControl;
   return o;
}
function FGroup_onBuildEdit(b){
   var o = this;
   var htb = RBuilder.appendTable(b.hPanel);
   htb.style.tableLayout = 'fixed';
   var hr = o.hEdit = htb.insertRow();
   var hec = hr.insertCell();
   var hep = RBuilder.appendTable(hec);
   var rc = o.rowItemCount;
   for(n = 0; n<20; n++){
	  if(n%rc == 0){
	     her = hep.insertRow();
	  }
	  var hc = her.insertCell();
	  hc.style.height=20;
	  hcp = RBuilder.appendTable(hc);
	  hcpr = hcp.insertRow();
	  hcc = hcpr.insertCell();
	  hcc.style.width = o.itemControlWidth;
	  hcl = hcpr.insertCell();
	  hcl.style.width = o.itemLabelWidth;
	  if(o.itemType == 'Check'){
	     var hce = RBuilder.appendCheck(hcc);
	  }else if(o.itemType == 'Radio'){
	     var hce = RBuilder.append(hcc, '<input type="radio" name=' + o.dataName + '/>');
	  }
	  hce.style.border = 0;
	  hce.style.display = 'none';
	  hce.disabled = !o._editable;
	  o.attachEvent('onControlClick', hce, o.onControlClick);
	  var h = new Object();
	  h.control = hce;
	  h.labelPanel = hcl;
	  o.controls.push(h);
   }
}
function FGroup_resetHeight(c, n){
   var o = this;
   var rc = 0;
   if(c%n != 0){
      rc = Math.floor(c/n) + 1;
   }else{
      rc = c/n;
   }
   o.hEdit.style.height = rc * 20;
}
function FGroup_oeValid(e){
   var o = this;
   var r = EEventStatus.Stop;
   var t = o.reget();
   if(o.validRequire && !RValidator.validRequire(o, t)){
      e.controls.push(o);
      return r;
   }
}
function FGroup_reget(){
	var o = this;
	o.resetValue();
    return o.dataValue;
}
function FGroup_get(){
	var o = this;
	o.resetValue();
    return o.dataValue;
}
function FGroup_resetValue(b){
   var o = this;
   var cs = o.controls;
   var cc = cs.count;
   o.dataValue = '';
   for(var n = 0; n < cc; n++){
      var c = cs.get(n).control;
      if(c.checked){
    	 if(RString.isEmpty(o.dataValue)){
            o.dataValue = o.dataValue + c.value;
    	 }else{
    		o.dataValue =o.dataValue + ';' + c.value;
    	 }
      }
   }
}
function FGroup_onControlClick(e){
   var o = this;
   var he = e.hSender;
   if(he.type == 'checkbox'){
	  o.resetValue();
   }else if(he.type == 'radio'){
	  o.dataValue = he.value;
   }
}
function FGroup_buildGroupControl(f){
	var o = this;
	var its = f.items;
	var ic = its.count;
	var oc = o.controls;
	var occ = oc.count;
	for(n = 0; n<occ; n++){
	   var c = oc.get(n).control;
	   var l = oc.get(n).labelPanel;
       if(n < ic){
	      var it = its.get(n);
	      c.value = it.value;
	      c.name = o.dataName;
	      c.checked = RBoolean.isTrue(it.select);
	      l.innerText = it.label;
	      c.style.display = 'block';
          l.style.display = 'block';
       }
	}
	o.resetHeight(ic, o.rowItemCount);
}
function FGroup_setGroupPack(v){
   var o = this;
   var ig = new TGroupControl();
   ig.unpack(v);
   o.buildGroupControl(ig);
}
function FGroup_set(v){
	var o = this;
   var cs = o.controls;
   var cc = cs.count;
   for(var n = 0; n < cc; n++){
      c = cs.get(n).control;
      if(v == c.value){
         c.checked = true;
      }
   }
}
function FGroup_setText(t){
   var o = this;
   if(!o.hEdit){
      return;
   }
   if('U'== o.editCase){
      o.hEdit.value = RString.toUpper(t);
   }else if('L'== o.editCase){
         o.hEdit.value = RString.toLower(t);
   }else{
      o.hEdit.value = t;
   }
   if('right' == o.editAlign ){
      o.hEdit.style.textAlign = 'right';
   }else if('left' == o.editAlign ){
      o.hEdit.style.textAlign = 'left';
   }else{
      o.hEdit.style.textAlign = 'center';
   }
}
function FGroup_refreshStyle(){
	var o = this;
	o.base.FEditControl.refreshStyle.call(o);
	var cs = o.controls;
	for(var n = 0; n< cs.count; n++){
		var h = cs.get(n).control;
		h.disabled = !o._editable;
		if(!o._editable){
		   o.hEdit.style.cursor = 'normal';
		}
	}
}
function FHtmlEditor(o){
   o = RClass.inherits(this, o, FEditControl, MValidator, MEditBorder);
   o.editComplete   = RClass.register(o, new TPtyStr('editComplete'));
   o.editCase       = RClass.register(o, new TPtyStr('editCase'));
   o.editPattern    = RClass.register(o, new TPtyStr('editPattern'));
   o.editLength     = RClass.register(o, new TPtyInt('editLength'));
   o.validLenmin    = RClass.register(o, new TPtyStr('validLenmin'));
   o.validLenmax    = RClass.register(o, new TPtyStr('validLenmax'));
   o.editOverflow   = RClass.register(o, new TPtyStr('editOverflow'));
   o.onEditClick    = RClass.register(o, new HClick('onEditClick'), FHtmlEditor_onEditClick);
   o.onCodeClick    = RClass.register(o, new HClick('onCodeClick'), FHtmlEditor_onCodeClick);
   o.onDispClick    = RClass.register(o, new HClick('onDispClick'), FHtmlEditor_onDispClick);
   o.onEditBlur     = RClass.register(o, new HBlur('onEditBlur'), FHtmlEditor_onEditBlur);
   o.hUnit          = null;
   o.borderStyle    = EBorder.Round;
   o.onBuildEdit    = FHtmlEditor_onBuildEdit;
   o.onEditKeyPress = FHtmlEditor_onEditKeyPress;
   o.onBuildControl = FHtmlEditor_onBuildControl;
   o.oeResize       = FHtmlEditor_oeResize;
   o.oeValid        = FHtmlEditor_oeValid;
   o.text           = FHtmlEditor_text;
   o.set            = FHtmlEditor_set;
   o.setText        = FHtmlEditor_setText;
   o.setVisible     = FHtmlEditor_setVisible;
   o.dispose        = FHtmlEditor_dispose;
   o.doResizeEdit   = FHtmlEditor_doResizeEdit;
   o.refreshStyle   = FHtmlEditor_refreshStyle;
   return o;
}
function FHtmlEditor_doResizeEdit(){
   var o = this;
   var hp = o.hEditDataCell;
   if(hp.offsetWidth){
      o.hEdit.style.pixelWidth = hp.offsetWidth;
      o.hEdit.style.pixelHeight = hp.offsetHeight - 2;
   }
}
function FHtmlEditor_oeResize(e){
   var o = this;
   var r = o.base.FEditControl.oeResize.call(o, e);
   o.doResizeEdit();
   return r;
}
function FHtmlEditor_oeValid(e){
   var o = this;
   var r = EEventStatus.Stop;
   if(o._visible){
      var t = o.text();
      if(o.validRequire && !RValidator.validRequire(o, t)){
         e.controls.push(o);
         return r;
      }
      if(!RValidator.validTextLength(o, t, o.validLenmax)){
    	  e.controls.push(o);
          return r;
      }
   }
   return r;
}
function FHtmlEditor_onBuildEdit(b){
   var o = this;
   var htb = RBuilder.appendTable(b.hPanel);
   htb.width = '100%';
   htb.height = '100%';
   htb.style.tableLayout = 'fixed';
   var hr = o.hEdit = htb.insertRow();
   var hc = o.hEditDataCell = hr.insertCell();
   var h = o.hEdit = RBuilder.append(hc, 'TEXTAREA', o.style('Edit'));
   h.style.display = "none";
   o.attachEvent('onEditClick', o.hEdit, o.onEditClick);
   o.attachEvent('onEditBlur', o.hEdit, o.onEditBlur);
   b.hPanel.style.border = '1 solid #FFFFFF';
   h.style.overflowY = 'auto';
   if(RBoolean.isTrue(o.editOverflow)){
      h.wrap = 'off';
      h.style.overflowX = 'auto';
   }else{
      h.wrap = 'soft';
      h.style.overflowX = 'hidden';
   }
   var ht = o.hHtml = RBuilder.append(hc, 'SPAN', o.style('Edit'));
   ht.style.wordWrap='break-word';
   ht.style.wordBreak='break-all';
   ht.style.height = o.editHeight;
   ht.style.display = "block";
   var hbr = htb.insertRow();
   var hbf = hbr.insertCell();
   var hbt = o.hButtonForm = RBuilder.appendTable(hbf);
   hbt.width = '100%';
   hbt.height = 20;
   hbt.style.backgroundColor = '#E4EFFD';
   hbt.style.borderTop='1 solid #24C2DB';
   var hbrr = hbt.insertRow();
   var hbrc = o.hDispButton = hbrr.insertCell();
   hbrc.width="10%";
   hbrc.style.textAlign = 'center';
   hbrc.style.cursor = 'hand';
   hbrc.innerText = '预览';
   hbrc.style.borderLeft = '1 solid #eeeeee';
   hbrc.style.borderTop = '1 solid #eeeeee';
   hbrc.style.borderBottom = '1 solid #808080';
   hbrc.style.borderRight = '1 solid #808080';
   hbrc.style.backgroundColor = '#eeeeee';
   var hbrc = o.hEditButton = hbrr.insertCell();
   hbrc.width="10%";
   hbrc.style.cursor = 'hand';
   hbrc.style.textAlign = 'center';
   hbrc.innerText = '编辑';
   hbrc.style.borderLeft = '1 solid #eeeeee';
   hbrc.style.borderTop = '1 solid #eeeeee';
   hbrc.style.borderBottom = '1 solid #808080';
   hbrc.style.borderRight = '1 solid #808080';
   hbrc.style.backgroundColor = '#eeeeee';
   var hbrc = hbrr.insertCell();
   hbrc.width="80%";
   o.attachEvent('onCodeClick', o.hEditButton, o.onCodeClick);
   o.attachEvent('onDispClick', o.hDispButton, o.onDispClick);
}
function FHtmlEditor_onEditClick(e){
   var o = this;
   if (o.hEdit.innerText == o.editTip) {
	   o.hEdit.innerText = '';
	   o.hEdit.style.color = EColor.TextEdit;
   }
}
function FHtmlEditor_onCodeClick(e){
   var o = this;
   o.hEditButton.style.backgroundColor = '#ccc';
   o.hDispButton.style.backgroundColor = '#eee';
   o.hEdit.style.display = 'block';
   o.hEdit.focus();
   o.hHtml.style.display = 'none';
}
function FHtmlEditor_onDispClick(e){
   var o = this;
   o.hEditButton.style.backgroundColor = '#eee';
   o.hDispButton.style.backgroundColor = '#ccc';
   o.hEdit.style.display = 'none';
   o.hHtml.style.display = 'block';
   o.hHtml.innerHTML = "<pre style='font-family: Tahoma;font-size:8pt;'>" + o.hEdit.value + "</pre>";
}
function FHtmlEditor_onEditBlur(e) {
   var o = this;
   if ('' == o.hEdit.innerText && o.editTip) {
	   o.hEdit.innerText = o.editTip;
	   o.hEdit.style.color = '#ccc';
   }
}
function FHtmlEditor_onBuildControl(){
   var o = this;
   o.base.FEditControl.onBuildControl.call(o);
   if(o.editUnit){
      var h = o.hUnit = o.hControlRow.insertCell();
      h.className = o.style('Unit');
      h.innerText = o.editUnit;
   }
}
function FHtmlEditor_onEditKeyPress(e){
   var o = this;
}
function FHtmlEditor_text(){
   var o = this;
   if(this.hEdit.value == o.editTip) {
      return null;
   }
   return this.hEdit.value;
}
function FHtmlEditor_setText(text){
   var o = this;
   if(o.editTip && '' == text) {
      o.hEdit.innerText = o.editTip;
      o.hEdit.style.color = '#ccc';
   }else {
	   this.hEdit.value = text;
   }
}
function FHtmlEditor_set(v){
   var o = this;
   o.hEdit.innerText=v;
   if ('I' == o._emode) {
	   o.hHtml.innerHTML=v;
	   o.hEditButton.style.backgroundColor = '#ccc';
	   o.hDispButton.style.backgroundColor = '#eee';
	   o.hEdit.style.display = 'block';
	   o.hHtml.style.display = 'none';
   } else {
	   o.hHtml.innerHTML="<pre style='font-family: Tahoma;font-size:8pt;'>" + v +"</pre>";
	   o.hDispButton.style.backgroundColor = '#ccc';
	   o.hEditButton.style.backgroundColor = '#eee';
	   o.hEdit.style.display = 'none';
	   o.hHtml.style.display = 'block';
   }
}
function FHtmlEditor_refreshStyle(){
   var o = this;
   if(!o._editable){
	  o.hHtml.style.backgroundColor = EColor.Readonly;
	  o.hButtonForm.style.display = 'none';
   } else {
	   o.hHtml.style.backgroundColor = EColor.Edit;
	   o.hButtonForm.style.display = 'block';
   }
}
function FHtmlEditor_setVisible(v){
   var o = this;
   var r = o.base.FEditControl.setVisible.call(o, v);
   if(v){
      o.doResizeEdit();
   }
   return r;
}
function FHtmlEditor_dispose(){
   var o = this;
   o.base.FEditControl.dispose.call(o);
   o.hEdit = null;
}
function FHtmlMemo(o){
   o = RClass.inherits(this, o, FEditControl);
   o.editOverflow = RClass.register(o, new TPtyStr('editOverflow'));
   o.onBuildEdit  = FHtmlMemo_onBuildEdit;
   o.oeClearValue = RMethod.empty;
   o.oeResetValue = RMethod.empty;
   o.setText      = FHtmlMemo_setText;
   o.dispose      = FHtmlMemo_dispose;
   return o;
}
function FHtmlMemo_onBuildEdit(hc){
   var o = this;
   var h = o.hEdit = RBuilder.appendDiv(hc, o.style('Edit'));
   if(o.width){
      h.style.width = o.width;
   }
   h.style.overflowY = 'auto';
   if(RString.equals(o.editOverflow,'N')){
      h.wrap ='off';
      h.style.overflowX = 'auto';
   }
   if(RString.equals(o.editOverflow,'Y')){
      h.style.overflowX = 'auto';
   }
   if(o.dataDefault){
      this.hEdit.innerHTML = o.dataDefault;
   }
}
function FHtmlMemo_setText(t){
   this.hEdit.innerHTML = t;
}
function FHtmlMemo_dispose(text){
   var o = this;
   o.base.FEditControl.dispose.call(o);
   o.hEdit = null;
}
function FIconPicker(o){
   o = RClass.inherits(this, o, FEditControl, MEditBorder, MListView);
   o.iconDefault    = RClass.register(o, new TPtyStr('iconDefault'));
   o.stIconDefault  = RClass.register(o, new TStyleIcon('Default'));
   o.hEditIcon      = null;
   o.borderStyle    = EBorder.RoundIcon;
   o.onEditKeyDown  = FIconPicker_onEditKeyDown;
   o.onEditKeyPress = FIconPicker_onEditKeyPress;
   o.onBuildEdit    = FIconPicker_onBuildEdit;
   o.setText        = FIconPicker_setText;
   o.dispose        = FIconPicker_dispose;
   return o;
}
function FIconPicker_onEditKeyDown(e){
   var o = this;
   o.base.FEditControl.onEditKeyDown.call(o,e);
   o.hEditIcon.src = RRes.iconPath(RString.nvl(o.text(), o.styleIcon("Default")));
}
function FIconPicker_onEditKeyPress(e){
   var o = this;
   o.base.FEditControl.onEditKeyPress.call(o, e);
   if(o.editCase){
      RKey.fixCase(e, o.editCase);
   }
}
function FIconPicker_onBuildEdit(b){
   var o = this;
   var h = b.hPanel;
   b.hIcon.width = 1;
   h.align = 'center';
   h.noWrap = 'true';
   var hi = RString.nvl(o.iconDefault, o.styleIcon("Default"));
   o.hEditIcon = RBuilder.appendIcon(h, hi);
   var h = o.hEdit = RBuilder.appendEdit(h, o.style('Edit'));
   h.autocomplete = RBool.isTrue(o.editComplete) ? 'on' : 'off';
   if(o.editLength){
      h.maxLength = o.editLength;
   }
}
function FIconPicker_setText(t){
   var o = this;
   o.base.FEditControl.setText.call(o, t);
   o.hEditIcon.src = RResource.iconPath(RString.nvl(o.text(), o.styleIcon("Default")));
}
function FIconPicker_dispose(){
   var o = this;
   o.base.FEditControl.dispose.call(o);
   o.hEditIcon = null;
   o.hEdit = null;
}
function FLabel(o){
   o = RClass.inherits(this, o, FEditControl);
   o.onBuildEdit  = FLabel_onBuildEdit;
   o.text         = FLabel_text;
   o.setText      = FLabel_setText;
   o.refreshStyle = RMethod.empty;
   return o;
}
function FLabel_onBuildEdit(){
   var o = this;
   o.hEdit = o.hEditCell;
   if(o.dataDefault){
      o.hEdit.innerHTML = RString.nvl(o.dataDefault);
   }
}
function FLabel_text(){
   return this.hEdit.innerText;
}
function FLabel_setText(t){
   this.hEdit.innerHTML = RString.nvl(t);
}
function FLayout(o){
   o = RClass.inherits(this, o, FContainer);
   o.hContainer     = null;
   o.hPanelTable    = null;
   o.hPanelLine     = null;
   o.__lastSplit    = null;
   o.oeDesign       = FLayout_oeDesign;
   o.oeRefresh      = FLayout_oeRefresh;
   o.oeResize       = FLayout_oeResize;
   o.onDesignBegin  = FLayout_onDesignBegin;
   o.onDesignEnd    = FLayout_onDesignEnd;
   o.onBuildPanel   = FLayout_onBuildPanel;
   o.doResize       = FLayout_doResize;
   o.insertPosition = FLayout_insertPosition;
   o.appendLine     = FLayout_appendLine;
   o.appendChild    = FLayout_appendChild;
   o.moveChild      = FLayout_moveChild;
   o.moveChild      = FLayout_moveChild;
   o.panelExtend    = FLayout_panelExtend;
   o.dispose        = FLayout_dispose;
   return o;
}
function FLayout_onDesignBegin(){
   var o = this;
   o.base.MDesign.onDesignBegin.call(o);
}
function FLayout_onDesignEnd(){
   var o = this;
   o.base.MDesign.onDesignEnd.call(o);
}
function FLayout_doResize(){
   var o = this;
   var cs = o.components;
   if(cs){
      var ha = false;
      var c = cs.count;
      for(var n=0; n<c; n++){
         var p = o.components.value(n);
         if(RClass.isClass(p, FTable) || RClass.isClass(p, FPageControl)){
            ha = true;
            break;
         }
      }
      o.setSize('100%', ha ? '100%' : 1);
   }
}
function FLayout_oeDesign(event){
   var o = this;
   o.base.FContainer.oeDesign.call(o, event);
   if(event.isAfter()){
      switch(event.mode){
         case EDesign.Move:
            break;
         case EDesign.Border:
            if(event.flag){
               o.hPanel.border = 1;
               o.hPanel.style.border = '1 solid red';
            }else{
               o.hPanel.border = 0;
               o.hPanel.style.border = null;
            }
            break;
      }
   }
}
function FLayout_oeRefresh(e){
   var o = this;
   o.base.FContainer.oeDesign.call(o, event);
   if(e.isAfter()){
      o.doResize();
   }
}
function FLayout_oeResize(e){
   var o = this;
   o.base.FContainer.oeResize.call(o, event);
   if(e.isAfter()){
      o.doResize();
   }
}
function FLayout_onBuildPanel(){
   var o = this;
   var h = o.hPanel = o.hPanelForm = RBuilder.newTable();
   h.width = '100%';
   if(EMode.Design == o._emode){
      o.hContainer = h.insertRow().insertCell();
   }
}
function FLayout_appendLine(){
   var o = this;
   var h = null;
   if(EMode.Design == o._emode){
      h = o.hPanelTable = RBuilder.appendTable(o.hContainer);
      h.style.paddingBottom = 6;
      o.hPanelLine = h.insertRow();
   }else{
      o.hPanelTable = null;
      o.hPanelLine = null;
   }
   return h;
}
function FLayout_appendChild(ctl){
   var o = this;
   if(EMode.Design == o._emode){
      if(!o.hPanelLine){
         o.appendLine();
      }
      if(RClass.isClass(ctl, MHorizontal)){
         if(o.hPanelTable.rows[0].cells.length == 0){
            o.hContainer.insertBefore(ctl.hPanel, o.hPanelTable);
         }else{
            o.hContainer.appendChild(ctl.hPanel);
            o.appendLine();
         }
         return;
      }
      var hCell = o.hPanelLine.insertCell();
      if(!RClass.isClass(ctl, FLayout)){
         ctl.hPanelLine = o.hPanelTable;
      }
      hCell.appendChild(ctl.hPanel);
      ctl.hLayoutCell = hCell;
      if(!ctl.nowrap && (o.controls.last() != ctl)){
         o.appendLine();
      }
   }else{
      ctl.hPanel.style.paddingTop = 2;
      ctl.hPanel.style.paddingBottom = 2;
      if(RSet.contains(ctl._esize, ESize.Horizontal) || '100%' == ctl.width){
         if(RClass.isClass(ctl, FSplit)){
            o.__lastSplit = ctl;
         }
         var hr = o.hPanelForm.insertRow();
         var hc = hr.insertCell();
         hc.vAlign = 'top';
         hc.appendChild(ctl.hPanel);
         ctl.hLayoutRow = hr;
         o.hPanelLast = hc;
         if(!RSet.contains(ctl._esize, ESize.Vertical)){
            hc.height = 1;
         }else if(ctl.height){
            hc.height = ctl.height;
         }
         o.hPanelLine = null;
      }else{
         if(!o.hPanelLine){
            var hr = o.hPanelForm.insertRow();
            hr.height = 1;
            if(o.__lastSplit){
               o.__lastSplit.pushLine(hr);
            }
            var hc = hr.insertCell();
            hc.vAlign = 'top';
            var ht = o.hPanelTable = RBuilder.appendTable(hc);
            o.hPanelLine = ht.insertRow();
         }
         var hc = o.hPanelLine.insertCell()
         ctl.hLayoutRow = o.hPanelLine;
         o.hPanelLast = hc;
         hc.appendChild(ctl.hPanel);
         ctl.hLayoutCell = hc;
         if(!ctl.nowrap){
            o.hPanelLine = null;
         }
      }
   }
}
function FLayout_insertPosition(cf, ct, idx, copy){
   var o = this;
   var ms = o.components;
   var cs = o.controls;
   ms.removeValue(cf);
   cs.removeValue(cf);
   if(ct){
      var index = ms.indexOfValue(ct);
      ms.insert(index+idx, cf.name, cf);
      var index = cs.indexOfValue(ct);
      cs.insert(index+idx, cf.name, cf);
   }else{
      ms.set(cf.name, cf);
      cs.set(cf.name, cf);
   }
}
function FLayout_moveChild(cf, ct, pos, copy){
   if(!(cf && ct && pos) || (cf == ct)){
      return;
   }
   var o = this;
   var hPanel = o.hPanel;
   var moved = false;
   var cfh = RClass.isClass(cf, MHorizontal);
   var hCfTd = RHtml.parent(cf.hPanel, 'TD');
   var hCfTab = RHtml.parent(cf.hPanel, 'TABLE');
   var cth = RClass.isClass(ct, MHorizontal);
   var hTd = RHtml.parent(ct.hPanel, 'TD');
   var hTable = RHtml.parent(hTd, 'TABLE');
   switch(pos){
      case EPosition.Before:
         var hRow = hTable.rows[0];
         for(var n=0; n<hRow.cells.length; n++){
            if(hRow.cells[n] == hTd){
               var hCell = hRow.insertCell(hTd.cellIndex);
               hCell.appendChild(cf.hPanel);
               o.insertPosition(cf, ct, 0, copy);
               cf.nowrap = true;
               cf.hPanelLine = hTable;
               moved = true;
               break;
            }
         }
         break;
      case EPosition.After:
         var hRow = hTable.rows[0];
         for(var n=0; n<hRow.cells.length; n++){
            if(hRow.cells[n] == hTd){
               var hCfTd = RHtml.parent(cf.hPanel, 'TD');
               var hCell = hRow.insertCell(hTd.cellIndex+1);
               hCell.appendChild(cf.hPanel);
               o.insertPosition(cf, ct, 1, copy);
               cf.nowrap = false;
               cf.hPanelLine = hTable;
               ct.nowrap = true;
               moved = true;
               break;
            }
         }
         break;
      case EPosition.LineBefore:
         if(cth){
            if(cfh){
               o.hContainer.insertBefore(cf.hPanel, ct.hPanel);
            }else{
               var hNewTab = o.appendLine();
               o.hContainer.insertBefore(hNewTab, ct.hPanel);
               var hCell = o.hPanelLine.insertCell();
               hCell.appendChild(cf.hPanel);
               cf.hPanelLine = hNewTab;
            }
            o.insertPosition(cf, ct, 0, copy);
         }else{
            var count = o.hContainer.children.length;
            for(var n=0; n<count; n++){
               if(o.hContainer.children[n] == hTable){
                  if(cfh){
                     o.hContainer.insertBefore(cf.hPanel, hTable);
                  }else{
                     var hNewTab = o.appendLine();
                     o.hContainer.insertBefore(hNewTab, hTable);
                     var hCell = o.hPanelLine.insertCell();
                     hCell.appendChild(cf.hPanel);
                     cf.hPanelLine = hNewTab;
                     moved = true;
                  }
                  o.insertPosition(cf, ct, 0, copy);
                  cf.nowrap = false;
                  break;
               }
            }
         }
         break;
      case EPosition.LineAfter:
         if(cfh){
            o.hContainer.appendChild(cf.hPanel);
         }else{
            var hNewTab = o.appendLine();
            var hCell = o.hPanelLine.insertCell();
            hCell.appendChild(cf.hPanel);
            hCell.appendChild(cf.hPanel);
            moved = true;
         }
         o.insertPosition(cf, null, 0, copy);
         ct.nowrap = false;
         cf.nowrap = false;
         break;
   }
   if(moved){
      hCfTd.removeNode(true);
      if(hCfTab.rows[0].cells.length == 0){
         hCfTab.removeNode(true);
      }
   }
}
function FLayout_panelExtend(v){
   var o = this;
   if(o.hLastLine){
      o.hPanelLast.height = v ? '1' : '100%';
   }
}
function FLayout_dispose(){
   var o = this;
   o.base.FContainer.dispose.call(o);
   o.hPanelCurrent = null;
   o.hPanelTable = null;
   o.hPanel = null;
   o.hContainer = null;
}
function FListBox(o){
   o = RClass.inherits(this, o, FPanel, MHorizontal);
   o.lsnsClick  = new TListeners();
   o.appendLine = FListBox_appendLine;
   o.dispose    = FListBox_dispose;
   return o;
}
function FListBox_appendLine(){
   var o = this;
   var h = null;
   if(EMode.Design == o._emode){
      h = this.hPanelTable = RBuilder.appendTable(this.hContainer, null, 10, 10, 10);
      h.style.border = '1 solid red';
      this.hPanelLine = this.hPanelTable.insertRow();
   }else{
      o.hPanelTable = null;
      o.hPanelLine = null;
   }
   return h;
}
function FListBox_dispose(){
   var o = this;
   o.base.FPanel.dispose.call(o);
   RMemory.freeHtml(o.hPanelTable);
   RMemory.freeHtml(o.hPanelLine);
   o.hPanelTable = null;
   o.hPanelLine = null;
}
function FListItem(o){
   o = RClass.inherits(this, o, FControl, MDesign, MHorizontal);
   o.styleForm    = RClass.register(o, new TStyle('Form'));
   o.styleIcon    = RClass.register(o, new TStyle('Icon'));
   o.styleLabel   = RClass.register(o, new TStyle('Label'));
   o.oeBuild      = FListItem_oeBuild;
   o.onBuildPanel = FListItem_onBuildPanel;
   o.formatValue  = FListItem_formatValue;
   o.text         = FListItem_text;
   o.setText      = FListItem_setText;
   o.dispose      = FListItem_dispose;
   return o;
}
function FListItem_oeBuild(e){
   var o = this;
   o.base.FControl.oeBuild.call(o, e);
   if(e.isBefore()){
      var hf = o.hForm = RBuilder.appendTable(o.hPanel, o.style('Form'));
      var hRow = hf.insertRow();
      var hc = hRow.insertCell();
      hc.className = o.style('Icon');
      hc.width = 20;
      o.hIcon = RBuilder.appendIcon(hc, 'arrow');
      var hc = hRow.insertCell();
      var h = o.hLabel = RBuilder.append(hc, 'SPAN', o.style('Label'));
      h.innerText = o.label;
   }
}
function FListItem_onBuildPanel(){
   this.hPanel = RBuilder.create(null, 'DIV');
}
function FListItem_formatValue(s){
   return RString.nvl(s);
}
function FListItem_text(){
   return this.hEdit.value;
}
function FListItem_setText(text){
   this.hEdit.value = text;
}
function FListItem_dispose(){
   var o = this;
   o.base.FControl.dispose.call(o);
   o.hForm = null;
   o.hIcon = null;
   o.hLabel = null;
   o.hPanel = null;
   o.hEdit = null;
}
function FListView(o){
   o = RClass.inherits(this, o, FContainer, MShadow);
   o.type           = null;
   o.lovControl     = null;
   o.listView       = null;
   o.hForm          = null;
   o.hMessages      = null;
   o.ohClearClick   = FListView_ohClearClick;
   o.ohCloseClick   = FListView_ohCloseClick;
   o.ohResetClick   = FListView_ohResetClick;
   o.ohLoaded       = FListView_ohLoaded;
   o.oeBuild        = FListView_oeBuild;
   o.onBuildPanel   = FListView_onBuildPanel;
   o.onBuildFields  = FListView_onBuildFields;
   o.onBuildButton  = FListView_onBuildButton;
   o.onBuildData    = FListView_onBuildData;
   o.onKeyDown      = FListView_onKeyDown;
   o.buildField     = FListView_buildField;
   o.linkLovControl = FListView_linkLovControl;
   o.isBuilded      = FListView_isBuilded;
   o.show           = FListView_show;
   o.hide           = FListView_hide;
   o.doSearch       = FListView_doSearch;
   o.selectRow      = FListView_selectRow;
   o.dispose        = FListView_dispose;
   return o;
}
function FListView_ohCloseClick(){
   this.hide();
}
function FListView_ohClearClick(){
   var o = this;
   var cs = o.fieldsPanel.components;
   if(cs){
      for(var n=0; n<cs.count; n++){
         cs.value(n).clearSearch();
      }
   }
}
function FListView_ohResetClick(){
}
function FListView_ohLoaded(){
   this.lovControl.onBuildData(this.document.root());
}
function FListView_oeBuild(event){
   var o = this;
   o.base.FContainer.oeBuild.call(o, event);
   var hTab = RBuilder.appendTable(o.hPanel);
   hTab.width = '100%';
   hTab.height = '100%';
   var hRow = hTab.insertRow();
   var h = o.hTitlePanel = hRow.insertCell();
   h.className = o.style('TitlePanel');
   RBuilder.appendIcon(h, 'tool.search');
   RBuilder.appendText(h, '&nbsp;List of View');
   h.colSpan = 2;
   hRow = hTab.insertRow();
   var h = o.hFieldsPanel = hRow.insertCell();
   h.className = o.style('FieldsPanel');
   var h = o.hButtonPanel = hRow.insertCell();
   h.className = o.style('ButtonPanel');
   o.onBuildButton();
   return EEventStatus.Stop;
}
function FListView_onBuildPanel(){
   var o = this;
   o.hPanel = RBuilder.append(null, 'DIV');
   o.hPanel.style.zIndex = ELayer.Message;
}
function FListView_onBuildFields(){
   return;
   var o = this;
   var hTab = o.hFieldsTab = RBuilder.appendTable(o.hFieldsPanel, null, 10, 10);
   hTab.width = '100%';
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
function FListView_onBuildButton(){
   var o = this;
   var hBtnTab = RBuilder.appendTable(o.hButtonPanel, null, 0, 0, 6);
   var hRow = hBtnTab.insertRow();
   var hCel = hRow.insertCell();
   var b = o.btnSelect = RClass.create(FButton);
   b.label = 'Select'
   b.width = '100%';
   b.addClickListener(o, o.selectRow);
   b.build(hBtnTab.insertRow().insertCell());
   var b = o.btnClose = RClass.create(FButton);
   b.label = 'Close';
   b.width = '100%';
   b.addClickListener(o, o.ohCloseClick);
   b.build(hBtnTab.insertRow().insertCell());
   var b = o.btnRefresh = RClass.create(FButton);
   b.label = 'Refresh';
   b.width = '100%';
   b.addClickListener(o, o.ohClearClick);
   b.build(hBtnTab.insertRow().insertCell());
   var hRow = hBtnTab.insertRow();
   var hCel = hRow.insertCell();
   hCel.innerHTML = '&nbsp;';
}
function FListView_buildField(c){
   var o = this;
   var hCell = o.hFieldsTab.insertRow().insertCell();
   hCell.innerText = c.label;
   o.fieldsPanel = RControl.create(FPanel);
   o.fieldsPanel.build();
   o.fieldsPanel.setPanel(hCel);
}
function FListView_linkLovControl(ctl){
   var o = this;
   o.lovControl = ctl;
   o.lovRefer = ctl.lovRefer;
   var doc = new TXmlDocument();
   var root = doc.root();
   root.set('action', 'dsPicker');
   RConsole.find('FEnvConsole').build(root);
   var dn = root.create('Control');
   dn.set('lov_refer', ctl.lovRefer);
   dn.set('lov_where', ctl.lovWhere);
   dn.set('lov_order', ctl.lovOrder);
   RLog.info(o, 'Send lov request (service={1},node={2})', ctl.lovRefer, root.dump());
   var e = new TEvent(o, EXmlEvent.Send);
   e.url = RService.url(ctl.lovService);
   e.document = doc;
   e.lovControl = o;
   e.onLoad = o.ohLoaded;
   RConsole.find(FXmlConsole).process(e);
}
function FListView_onBuildData(config){
   var o = this;
   var v = o.listView = RControl.fromNode(config, o.hFieldsPanel);
   v.hPanel.height = '100%';
   v.resize();
   v.addDblClickListener(o, o.selectRow);
   v.addSelectListener(o, o.selectRow);
   v.addKeyDownListener(o, o.onKeyDown);
   o.show();
}
function FListView_onKeyDown(sender, e){
   if(EKey.Esc == e.keyCode){
      this.hide();
   }
}
function FListView_show(){
   var o = this;
   if(!o.isVisible()){
      o.base.FContainer.show.call(o);
      RWindow.setEnable(false);
      RWindow.moveCenter(o.hPanel);
      o.base.MShadow.show.call(o, true);
      o.focus();
      o.listView.focus();
   }
}
function FListView_hide(){
   var o = this;
   if(o.isVisible()){
      o.base.FContainer.hide.call(o);
      o.base.MShadow.hide.call(o);
      RWindow.setEnable(true);
      o.lovControl.focus();
   }
}
function FListView_doSearch(){
   var o = this;
   var cs = o.fieldsPanel.components;
   if(cs){
      var sn = new TNode('Search');
      for(var n=0; n<cs.count; n++){
         cs.value(n).saveSearch(sn);
      }
      RLog.debug(o, 'Search value {1}', sn.dump());
   }
   o.hide();
}
function FListView_selectRow(table, row){
   var o = this;
   var fields = o.lovControl.lovFields;
   var dsCtl = o.lovControl.topControl(MDataset);
   if(dsCtl && fields){
      if(!row){
         row = o.listView.selectRow;
      }
      if(row){
         var flds = RString.splitTwo(fields, ',');
         for(var n=0; n<flds.length; n++){
            var v = RString.splitTwo(flds[n], ' ');
            dsCtl.dsSet(RString.nvl(v[1], v[0]), row.get(v[0]));
         }
         dsCtl.loadValue(dsCtl.dsCurrent());
      }
   }
   o.hide();
}
function FListView_isBuilded(){
   return (null != this.listView);
}
function FListView_dispose(){
   var o = this;
   o.base.FContainer.dispose.call(o);
   RMemory.freeHtml(o.hEdit);
   RMemory.freeHtml(o.hButton);
   RMemory.freeHtml(o.hText);
   RMemory.freeHtml(o.userSrc);
   RMemory.freeHtml(o.femaleSrc);
   RMemory.freeHtml(o.errorSrc);
   RMemory.freeHtml(o.orgSrc);
   RMemory.freeHtml(o.dutySrc);
   RMemory.freeHtml(o.roleSrc);
   RMemory.freeHtml(o.userUk);
   o.hEdit = null;
   o.hButton = null;
   o.hText = null;
   o.userSrc = null;
   o.femaleSrc = null;
   o.errorSrc = null;
   o.orgSrc = null;
   o.dutySrc = null;
   o.roleSrc = null;
   o.userUk = null;
}
function FLogicUserPicker(o){
   o = RClass.inherits(this, o, FEditControl, MEditBorder);
   o.service        = RClass.register(o, new TPtyStr('service', 'logic.webform.weblogic'));
   o.editStyle      = RClass.register(o, new TPtyBool('editStyle', false));
   o.onButtonClick  = RClass.register(o, new HClick('onButtonClick'), FLogicUserPicker_onButtonClick);
   o.onButtonEnter  = RClass.register(o, new HMouseEnter('onButtonEnter'), FLogicUserPicker_onButtonEnter);
   o.onButtonLeave  = RClass.register(o, new HMouseLeave('onButtonLeave'), FLogicUserPicker_onButtonLeave);
   o.onEditClick    = RClass.register(o, new HClick('onEditClick'), FLogicUserPicker_onEditClick);
   o.onEditBlur     = RClass.register(o, new HBlur('onEditBlur'), FLogicUserPicker_onEditBlur);
   o.hUnit          = null;
   o.lable          = null;
   o.borderStyle    = EBorder.Round;
   o.onBuildEdit    = FLogicUserPicker_onBuildEdit;
   o.onEditKeyPress = FLogicUserPicker_onEditKeyPress;
   o.onDataLoaded   = FLogicUserPicker_onDataLoaded;
   o.formatValue    = FLogicUserPicker_formatValue;
   o.text           = FLogicUserPicker_text;
   o.setText        = FLogicUserPicker_setText;
   o.appendItem     = FLogicUserPicker_appendItem;
   o.validText      = RMethod.empty;
   o.set            = FLogicUserPicker_set;
   o.get            = FLogicUserPicker_get;
   o.reget          = FLogicUserPicker_reget;
   o.refreshStyle   = FLogicUserPicker_refreshStyle;
   o.dispose        = FLogicUserPicker_dispose;
   return o;
}
function FLogicUserPicker_onButtonEnter(e){
   var o = this;
   if(!o._disabled){
	   o.hButton.style.cursor='hand';
	   o.hButton.title ='click here to translate the user information';
   }
}
function FLogicUserPicker_onButtonLeave(e){
   var o = this;
   if(!o._disabled){
	   o.hButton.style.cursor='default';
   }
}
function FLogicUserPicker_onBuildEdit(b){
   var o = this;
   var h  = RBuilder.appendTable(b.hPanel);
   h.width = '100%';
   h.height = '100%';
   var hr0 = h.insertRow();
   var hc0 = hr0.insertCell();
   var htb = RBuilder.appendTable(hc0);
   htb.style.tableLayout = 'fixed';
   htb.width = '100%';
   htb.height = '100%';
   var hrr = htb.insertRow();
   hrr.height = 36;
   hrr.bgColor = EColor.Readonly;
   var hc01 =  hrr.insertCell();
   hc01.width = '100%';
   var hdv = o.hText = RBuilder.appendDiv(hc01);
   hdv.style.overflow = 'auto';
   hdv.style.width = '100%';
   hdv.style.height = '100%';
   var hc02 = hrr.insertCell();
   hc02.width = 36;
   hc02.align = 'center';
   hc02.vAlign = 'middle';
   var ig = o.hButton = RBuilder.appendIcon(hc02, o.styleIcon('check'), null, 24, 24);
   o.linkEvent(o, 'onButtonClick', ig);
   o.linkEvent(o, 'onButtonEnter', ig);
   o.linkEvent(o, 'onButtonLeave', ig);
   var hc = h.insertRow().insertCell();
   hc.height = 1;
   hc.bgColor = '#24C2DB';
   var hr2 = h.insertRow();
   var hc2 = hr2.insertCell();
   if(!o.editStyle){
      hc2.height = 36;
      var hx2 = o.hEdit = RBuilder.append(hc2, 'TEXTAREA', o.style('Edit'));
      hx2.wrap = 'soft';
      hx2.style.overflowX = 'hidden';
   }else{
      var hx2 = o.hEdit = RBuilder.append(hc2, 'INPUT', o.style('Edit'));
      hx2.wrap = 'soft';
      hx2.style.overflowX = 'hidden';
   }
   o.attachEvent('onEditClick', o.hEdit, o.onEditClick);
   o.attachEvent('onEditBlur', o.hEdit, o.onEditBlur);
   o.userSrc = o.styleIconPath('user', FLogicUserPicker);
   o.femaleSrc = o.styleIconPath('userFemale', FLogicUserPicker);
   o.errorSrc = o.styleIconPath('unknown', FLogicUserPicker);
   o.orgSrc = o.styleIconPath('organization', FLogicUserPicker);
   o.dutySrc = o.styleIconPath('duty', FLogicUserPicker);
   o.roleSrc = o.styleIconPath('role', FLogicUserPicker);
   o.userUk = o.styleIconPath('userUk', FLogicUserPicker);
}
function FLogicUserPicker_onEditClick(e){
   var o = this;
   if (o.hEdit.innerText == o.editTip) {
	   o.hEdit.innerText = '';
	   o.hEdit.style.color = EColor.TextEdit;
   }
}
function FLogicUserPicker_onEditBlur(e) {
   var o = this;
   if ('' == o.hEdit.innerText && o.editTip) {
	   o.hEdit.innerText = o.editTip;
	   o.hEdit.style.color = '#ccc';
   }
}
function FLogicUserPicker_onEditKeyPress(e){
   var o = this;
   o.base.FEditControl.onEditKeyPress.call(o, e);
}
function FLogicUserPicker_formatValue(s){
   return RString.nvl(s);
}
function FLogicUserPicker_text(){
   return this.hEdit.value;
}
function FLogicUserPicker_setText(text){
   this.hEdit.value = text;
}
function FLogicUserPicker_set(v){
   var o = this;
   o.hText.innerHTML = '';
   if( !RString.isEmpty(v) ){
      if( !RString.contains(v, '|') ){
         o.hEdit.innerText = v;
         return;
      }
      var vs = RString.splitTwo(v, '|');
      var strs = new TStrings();
      strs.unpack(vs[1]);
      var ss = RString.split(vs[1], ';');
      if( !ss || !ss.length ){
         return;
      }
      var ssl = ss.length;
      for(var n=0; n<ssl; n++){
         var s = ss[n];
         var t = new TAttributes();
         t.unpack(s);
         var spp = RBuilder.append(o.hText, 'SPAN');
         spp.noWrap = false;
         spp.title = t.get('label')+'('+t.get('workNumber')+')';
         var ig = RBuilder.append(spp, 'IMG');
         ig.align = 'absmiddle';
         var sp = RBuilder.append(spp, 'SPAN');
         var s = t.get('label');
         var m = t.get('gender');
         sp.innerText = ' ' + s + (n<ssl-1 ? '; ' : '');
         o.hText.appendChild(spp);
         switch(t.get('type')){
            case 'U':
               if(m == 'M'){
                  ig.src = o.userSrc;
               }else if(m == 'F'){
                  ig.src = o.femaleSrc;
               }else{
                  ig.src = o.userUk;
               }
               break;
            case 'O':
               ig.src = o.orgSrc;
               break;
            case 'D':
               ig.src = o.dutySrc;
               break;
            case 'R':
               ig.src = o.roleSrc;
               break;
         }
      }
      o.hEdit.innerText = vs[0];
   }else{
      if(o.editTip){
    	  o.hEdit.innerText = o.editTip;
      }else {
    	   o.hEdit.innerText = "";
      }
   }
}
function FLogicUserPicker_get(){
   var o = this;
   if(this.hEdit.value == o.editTip) {
	  return null;
   }
   var s = RString.nvl(this.hEdit.value);
   s = RString.trim(s);
   s = RString.removeChars(s, '\n');
   return s.replace(/；/g, ';');
}
function FLogicUserPicker_reget(){
   return this.get();
}
function FLogicUserPicker_onButtonClick(e){
   var o = this;
   var text = o.get();
   if(!RString.isEmpty(text)){
      var doc = new TXmlDocument();
      var root = doc.root();
      root.set('action', 'userPicker');
      var d = root.create('Data');
      d.value = text;
      var url = RService.url(o.service);
      var e = new TEvent(o, EXmlEvent.Send, o.onDataLoaded);
      e.url = url;
      e.document = doc;
      e.action = 'userPicker';
      RConsole.find(FXmlConsole).process(e);
   }else{
      o.hText.innerHTML = '';
   }
}
function FLogicUserPicker_onDataLoaded(e){
   var o = this;
   o.hText.innerHTML = '';
   var root = e.document.root();
   if(!RConsole.find(FMessageConsole).checkResult(new TMessageArg(root))){
      return;
   }
   if(root.hasNode()){
      var nds = root.nodes;
      var ct = nds.count;
      for(var n = 0; n < ct; n++){
         var nd = nds.get(n);
         if(nd.hasNode()){
            var nns = nd.nodes;
            var nnt = nns.count;
            var h = o.hText;
            for(var k=0; k<nnt; k++){
               o.appendItem(nns.get(k), k<nnt-1);
            }
         }
      }
   }
}
function FLogicUserPicker_appendItem(nd, isLast){
   var o = this;
   var h  = o.hText;
   var name = null;
   var spp = RBuilder.append(h, 'SPAN');
   spp.title = nd.get('label')+'('+nd.get('workNumber')+')';
   spp.noWrap = false;
   var ig = RBuilder.append(spp, 'IMG');
   ig.align = 'absmiddle';
   var sp = RBuilder.append(spp, 'SPAN');
   var s = RString.nvl(nd.get('label'), nd.get('message'));
   sp.innerText = ' ' + s + (isLast ? '; ' : '');
   switch(nd.name){
      case 'User':
         var sm = nd.get("gender");
         if(sm == 'M'){
            ig.src = o.userSrc;
         }else if(sm == 'F'){
            ig.src = o.femaleSrc;
         }else{
            ig.src = o.userUk;
         }
         break;
      case 'Unknown':
         ig.src = o.errorSrc;
         spp.title = '记录不存在';
         break;
      case 'Organization':
         ig.src = o.orgSrc;
         break;
      case 'Duty':
         ig.src = o.dutySrc;
         break;
      case 'Role':
         ig.src = o.roleSrc;
         break;
   }
}
function FLogicUserPicker_refreshStyle(){
   var o = this;
   o.base.FEditControl.refreshStyle.call(o);
   var t = o.hEdit.innerText;
   if(!RString.isEmpty(t)){
      if(t == o.editTip){
    	  o.hEdit.style.color = '#CCCCCC';
      }
   }
   o.hButton.style.display = o._editable ? "block":"none";
}
function FLogicUserPicker_dispose(){
   var o = this;
   o.base.FEditControl.dispose.call(o);
   o.hEdit = null;
   o.hButton = null;
   o.hText = null;
   o.userSrc = null;
   o.femaleSrc = null;
   o.errorSrc = null;
   o.orgSrc = null;
   o.dutySrc = null;
   o.roleSrc = null;
   o.userUk = null;
}
function FMemo(o){
   o = RClass.inherits(this, o, FEditControl, MValidator, MEditBorder);
   o.editComplete   = RClass.register(o, new TPtyStr('editComplete'));
   o.editCase       = RClass.register(o, new TPtyStr('editCase'));
   o.editPattern    = RClass.register(o, new TPtyStr('editPattern'));
   o.editLength     = RClass.register(o, new TPtyInt('editLength'));
   o.validLenmin    = RClass.register(o, new TPtyStr('validLenmin'));
   o.validLenmax    = RClass.register(o, new TPtyStr('validLenmax'));
   o.editOverflow   = RClass.register(o, new TPtyStr('editOverflow'));
   o.onEditClick    = RClass.register(o, new HClick('onEditClick'), FMemo_onEditClick);
   o.onEditBlur     = RClass.register(o, new HBlur('onEditBlur'), FMemo_onEditBlur);
   o.hUnit          = null;
   o.borderStyle    = EBorder.Round;
   o.onBuildEdit    = FMemo_onBuildEdit;
   o.onEditKeyPress = FMemo_onEditKeyPress;
   o.onBuildControl = FMemo_onBuildControl;
   o.oeResize       = FMemo_oeResize;
   o.oeValid        = FMemo_oeValid;
   o.text           = FMemo_text;
   o.setText        = FMemo_setText;
   o.setVisible     = FMemo_setVisible;
   o.dispose        = FMemo_dispose;
   o.doResizeEdit   = FMemo_doResizeEdit;
   return o;
}
function FMemo_doResizeEdit(){
   var o = this;
   var hp = o.hEditDataCell;
   if(hp.offsetWidth){
      o.hEdit.style.pixelWidth = hp.offsetWidth;
      o.hEdit.style.pixelHeight = hp.offsetHeight - 2;
   }
}
function FMemo_oeResize(e){
   var o = this;
   var r = o.base.FEditControl.oeResize.call(o, e);
   o.doResizeEdit();
   return r;
}
function FMemo_onBuildEdit(b){
   var o = this;
   var htb = RBuilder.appendTable(b.hPanel);
   htb.width = '100%';
   htb.height = '100%';
   htb.style.tableLayout = 'fixed';
   var hr = o.hEdit = htb.insertRow();
   o.onBuildChange(hr.insertCell());
   var hc = o.hEditDataCell = hr.insertCell();
   var h = o.hEdit = RBuilder.append(hc, 'TEXTAREA', o.style('Edit'));
   o.attachEvent('onEditClick', o.hEdit, o.onEditClick);
   o.attachEvent('onEditBlur', o.hEdit, o.onEditBlur);
   b.hPanel.style.border = '1 solid #FFFFFF';
   h.style.overflowY = 'auto';
   if(RBoolean.isTrue(o.editOverflow)){
      h.wrap = 'off';
      h.style.overflowX = 'auto';
   }else{
      h.wrap = 'soft';
      h.style.overflowX = 'hidden';
   }
}
function FMemo_onEditClick(e){
   var o = this;
   if (o.hEdit.innerText == o.editTip) {
	   o.hEdit.innerText = '';
	   o.hEdit.style.color = EColor.TextEdit;
   }
}
function FMemo_onEditBlur(e) {
   var o = this;
   if ('' == o.hEdit.innerText && o.editTip) {
	   o.hEdit.innerText = o.editTip;
	   o.hEdit.style.color = '#ccc';
   }
}
function FMemo_onBuildControl(){
   var o = this;
   o.base.FEditControl.onBuildControl.call(o);
   if(o.editUnit){
      var h = o.hUnit = o.hControlRow.insertCell();
      h.className = o.style('Unit');
      h.innerText = o.editUnit;
   }
}
function FMemo_onEditKeyPress(e){
   var o = this;
}
function FMemo_oeValid(e){
   var o = this;
   var r = EEventStatus.Stop;
   if(o._visible){
      var t = o.text();
      if(o.validRequire && !RValidator.validRequire(o, t)){
         e.controls.push(o);
         return r;
      }
      if(!RValidator.validTextLength(o, t, o.validLenmax)){
    	  e.controls.push(o);
          return r;
      }
   }
   return r;
}
function FMemo_text(){
   var o = this;
   if(this.hEdit.value == o.editTip) {
      return null;
   }
   return this.hEdit.value;
}
function FMemo_setText(text){
   var o = this;
   if(o.editTip && '' == text) {
      o.hEdit.innerText = o.editTip;
      o.hEdit.style.color = '#ccc';
   }else {
	   this.hEdit.value = text;
   }
}
function FMemo_setVisible(v){
   var o = this;
   var r = o.base.FEditControl.setVisible.call(o, v);
   if(v){
      o.doResizeEdit();
   }
   return r;
}
function FMemo_dispose(){
   var o = this;
   o.base.FEditControl.dispose.call(o);
   o.hEdit = null;
}
function FMemoDrop(o){
   o = RClass.inherits(this, o, FDataEditControl, MDropable);
   o.dataMemo    = '';
   o.hForm       = null;
   o.hDrop       = null;
   o.hForm       = null;
   o.onBuildEdit = FMemoDrop_onBuildEdit;
   o.onEditEnd   = FMemoDrop_onEditEnd;
   o.loadConfig  = FMemoDrop_loadConfig;
   o.saveConfig  = FMemoDrop_saveConfig;
   o.formatValue = FMemoDrop_formatValue;
   o.formatText  = FMemoDrop_formatText;
   o.text        = FMemoDrop_text;
   o.setText     = FMemoDrop_setText;
   o.drop        = FMemoDrop_drop;
   return o;
}
function FMemoDrop_onBuildEdit(){
   var o = this;
   o.hEdit = RBuilder.create(null, 'TEXTAREA');
   o.hDrop = RBuilder.newIcon(null, 'ctl.memo');
}
function FMemoDrop_onEditEnd(editor){
   var o = this;
   RLog.debug(o, 'Begin (editor={1}:{2} value={3})', editor, editor?editor.value():'', o.dataValue);
   if(editor){
      var v = editor.value();
      var f = RString.firstLine(v);
      o.setText(f);
      o.dataValue = v;
      o.dataMemo = v.substr(f.length);
   }
   o.base.FDataEditControl.onEditEnd.call(o);
   RLog.debug(o, 'End (editor={1} value={2})', editor, o.dataValue);
}
function FMemoDrop_onKeyDown(){
   if(EKey.Down == event.keyCode){
      this.drop();
   }
}
function FMemoDrop_onDoubleClick(){
   this.drop();
}
function FMemoDrop_loadConfig(config){
   var o = this;
   o.base.FDataEditControl.loadConfig.call(o, config);
}
function FMemoDrop_saveConfig(config){
   this.base.FDataEditControl.saveConfig.call(this, config)
}
function FMemoDrop_formatValue(s){
   return s + this.dataMemo;
}
function FMemoDrop_formatText(s){
   return RString.firstLine(s);
}
function FMemoDrop_text(){
   return this.hEdit.value;
}
function FMemoDrop_setText(text){
   this.hEdit.value = text;
}
function FMemoDrop_drop(){
   var o = this;
   if(o.canEdit){
      o.dataValue = o.hEdit.value + o.dataMemo;
      var editor = o.editConsole.focus(o, FMemoDropEditor, o.name);
      editor.linkPanel(o.hControlPanel, o.hEdit);
      editor.setValue(o.dataValue);
      editor.show();
   }
}
function FMemoDrop_focus(){
   this.hEdit.focus();
}
function FMemoDropEditor(o){
   o = RClass.inherits(this, o, FDropEditor);
   o.MinWidth     = 240;
   o.onBuildEdit  = FMemoDropEditor_onBuildEdit;
   o.onKeyDown    = FMemoDropEditor_onKeyDown;
   o.oeShow       = FMemoDropEditor_oeShow;
   o.focus        = FMemoDropEditor_focus;
   o.value        = FMemoDropEditor_value;
   o.setValue     = FMemoDropEditor_setValue;
   return o;
}
function FMemoDropEditor_onBuildEdit(){
   var o = this;
   var hFormTab = o.hForm = RBuilder.appendTable(o.hPanel);
   var hTltCel = hFormTab.insertRow().insertCell();
   var hTab = RBuilder.appendTable(hTltCel);
   var hRow = hTab.insertRow();
   var hCel = hRow.insertCell();
   o.hEditCel = hCel;
   o.hEdit = RBuilder.append(hCel, 'INPUT', o.style('Edit'));
   var hCel = hRow.insertCell()
   o.hDrop = RBuilder.appendIcon(hCel, 'ctl.memo', o.style('Drop'));
   var hDrpCel = hFormTab.insertRow().insertCell();
   o.hDropPanel = RBuilder.append(hDrpCel, 'DIV', o.style('DropPanel'));
   o.hEditor = RBuilder.append(o.hDropPanel, 'TEXTAREA', o.style('Memo'));
   o.linkEvent(o.hEditor);
}
function FMemoDropEditor_onKeyDown(){
   var o = this;
   var kc = event.keyCode;
   if(EKey.Esc == kc){
      this.hEditor.value = o.dataValue;;
      o.editStatus = EEditStatus.Cancel;
      o.onBlur();
   }else if(event.ctrlKey && EKey.Enter == kc){
      o.editStatus = EEditStatus.Ok;
      o.onBlur();
   }
}
function FMemoDropEditor_oeShow(event){
   var o = this;
   o.base.FDropEditor.oeShow.call(o, event);
   if(event.isAfter()){
      RHtml.toRect(o.rect, o.hDropPanel);
      RWindow.showShadow(true, o.rect);
   }
}
function FMemoDropEditor_focus(){
   this.hEditor.focus();
}
function FMemoDropEditor_value(){
   return this.hEditor.value;
}
function FMemoDropEditor_setValue(value){
   var o = this;
   value = RString.nvl(value);
   o.changed = false;
   o.dataValue = value;
   o.dataValue = value;
   o.hEdit.value = RString.firstLine(value);
   o.hEditor.value = value;
}
function FNumber(o){
   o = RClass.inherits(this, o, FEditControl, MDescNumber, MEditBorder, MListView, MZoom, MMouseWheel);
   o.editAlign         = EAlign.Right;
   o.borderStyle       = EBorder.RoundDrop;
   o.onEditFocus       = RClass.register(o, new HFocus('onEditFocus'), FNumber_onEditFocus);
   o.onEditBlur        = RClass.register(o, new HBlur('onEditBlur'), FNumber_onEditBlur);
   o.onEditKeyPress    = RClass.register(o, new HKeyPress('onEditKeyPress'), FNumber_onEditKeyPress);
   o.onBuildEdit       = FNumber_onBuildEdit;
   o.formatValue       = MDescNumber_formatValue;
   o.formatText        = MDescNumber_formatText;
   o.onMouseWheel      = MDescNumber_onMouseWheel;
   o.onDataKeyDown     = FNumber_onDataKeyDown;
   o.ohEditKeyUp       = FNumber_ohEditKeyUp;
   o.onEditKeyUp       = FNumber_onEditKeyUp;
   o.onEditDoubleClick = FNumber_onEditDoubleClick;
   o.validPattern      = FNumber_validPattern;
   o.refreshStyle      = FNumber_refreshStyle;
   o.splitValue        = FNumber_splitValue;
   o.removeSplit       = FNumber_removeSplit;
   o.precisionValue    = FNumber_precisionValue;
   o.dispose           = FNumber_dispose;
   o.setUnitIcon       = FNumber_setUnitIcon;
   return o;
}
function FNumber_onEditFocus(e){
   var o = this;
   o.setText(o.formatValue(o.text()));
}
function FNumber_onEditBlur(e){
   var o = this;
   o.setText(o.formatText(o.text()));
}
function FNumber_onEditKeyPress(e, he){
   var o = this;
   var kc = he.keyCode;
   if(he.shiftKey && 53 == kc){
      return;
   }
   if(!EKey.floatCodes[kc]){
      RKey.eventClear(he);
   }
}
function FNumber_onBuildEdit(b){
   var o = this;
   var htb = RBuilder.appendTable(b.hPanel);
   htb.style.tableLayout = 'fixed';
   var hr = o.hEdit = htb.insertRow();
   o.onBuildChange(hr.insertCell());
   if(o.canZoom()){
      var hc = hr.insertCell();
      o.hZoom = RBuilder.appendIcon(hc, 'ctl.zooms');
      hc.width = 16;
   }
   var hc = hr.insertCell();
   hc.style.width = '100%';
   var he = o.hEdit = RBuilder.appendEdit(hc, o.style('Edit'));
   o.attachEvent('onEditFocus', he, o.onEditFocus);
   o.attachEvent('onEditKeyPress', he, o.onEditKeyPress);
   o.attachEvent('onEditBlur', he, o.onEditBlur);
   o.attachEvent('onDataKeyUp', he, o.ohEditKeyUp);
   if(o.editLength){
      he.maxLength = o.editLength;
   }
   o.buildAdjustForm(b.hDrop);
}
function FNumber_setUnitIcon(i){
   var o = this;
   var hui = o.hUnit;
   hui.innerHTML = '<IMG src='+i+'>';
}
function FNumber_onDataKeyDown(s, e){
   var o = this;
   if(o.canEdit){
      if(EKey.Up == e.keyCode){
         o.adjustValue(true);
      }else if(EKey.Down == e.keyCode){
         o.adjustValue(false);
      }
   }
   o.base.FEditControl.onDataKeyDown.call(o, s, e);
}
function FNumber_ohEditKeyUp(s, e){
   var o = this;
   if(EKey.Up == e.keyCode && o.canEdit){
      o.hUpIcon.src = o.styleIconPath('UpSelect');
   }else if(EKey.Down == e.keyCode && o.canEdit){
      o.hDownIcon.src = o.styleIconPath('DownSelect');
   }
}
function FNumber_onEditKeyDown(e) {
   var o = this;
   if(o.canEdit){
      if (EKey.Up == e.keyCode) {
         e.source.hUpIcon.src = o.styleIconPath('up');
         o.changeValue(e, 'Y');
      }else if (EKey.Down == e.keyCode){
         e.source.hDownIcon.src = o.styleIconPath('down');
         o.changeValue(e, 'N');
      }
   }
}
function FNumber_onEditKeyUp(e) {
   var o = this;
   if(o.canEdit){
      if (EKey.Up == e.keyCode){
         e.source.hUpIcon.src = o.styleIconPath('upSelect');
      }else if (EKey.Down == e.keyCode){
         e.source.hDownIcon.src = o.styleIconPath('downSelect');
      }
   }
}
function FNumber_onEditDoubleClick(){
   var o = this;
   this.onListClick();
}
function FNumber_validPattern(s) {
   var o = this;
   var flag = true;
   var s = RString.nvl(s);
   if(!RRegExp.test(ERegExp.NUMBER,s)){
      return false;
   }
   var r = null;
   if (o.dataType) {
      for (n in ERegExp) {
         if (RString.equals(n, o.dataType)) {
            r = ERegExp[n];
            break;
         }
      }
      if (RString.equals(RClass.name(r), "RegExp")) {
         flag = RRegExp.test(r, s) ? flag & true : flag & false;
      }
   }
   if (o.editMaxvalue) {
      flag = parseFloat(s) <= parseFloat(o.editMaxvalue) ? flag & true : flag & false;
   }
   if (o.editMinvalue) {
      flag = parseFloat(s) >= parseFloat(o.editMinvalue) ? flag & true : flag & false;
   }
   return flag;
}
function FNumber_refreshStyle(){
   var o = this;
   o.base.FEditControl.refreshStyle.call(o);
   o.hUpIcon.src = o.styleIconPath(o._hover ? 'UpSelect' : 'Up');
   o.hDownIcon.src = o.styleIconPath(o._hover ? 'DownSelect' : 'Down');
}
function FNumber_splitValue(v){
   var o = this;
   var s = RString.nvl(v.toString());
   var j = RString.findChars(s,"-");
   var b = RString.findChars(s,"%");
   s = RString.removeChars(s, "'");
   s = RString.removeChars(s, " ");
   s = RString.removeChars(s, "%");
   s = RString.removeChars(s, "-");
   if (!RString.isEmpty(s)) {
      var sc = '';
      var c = '';
      var n = 0;
      for(var i = s.length; i > -1; i--){
         if(i != 0 && n != 0 && n % 3 == 0){
            sc = "'" + s.charAt(i) + sc;
         }else{
            sc = s.charAt(i) + sc;
         }
         n++;
      }
      if(-1 != j){
          sc = "-" + sc ;
       }
      if(-1 != b){
         sc = sc +"%";
      }
      return sc;
   }
   return s;
}
function FNumber_removeSplit(s){
   var o = this;
   var s = RString.nvl(s);
   s = RString.removeChars(s,"'");
   s = RString.removeChars(s,"%");
   return s;
}
function FNumber_precisionValue(v){
   var o = this;
   if(RString.isEmpty(v)){
      return v;
   }
   var l1,l2;
   var p = RString.nvl(o.editPrecision);
   v = RString.nvl(v);
   if(RString.contains(p,'.')){
      var sp = p.split('.')
      l2 = sp[1].length;
   }else{
     l1 = p.length;
   }
   if(RString.contains(v, '.')){
      var vs = v.split('.');
      if(l2){
         if(l2 > vs[1].length){
            vs[1] = RString.rpad(vs[1],l2 - vs[1].length,'0');
         }else if(l2 <= vs[1].length){
            vs[1] = vs[1].substring(0, l2);
         }
      }
      if(l1){
         if(l1 > vs[0].length){
            alert(l1);
         }else if(l1 < vs[0].length){
            vs[0] = vs[0].substring(0, vs[0].length - l1);
            vs[0] = RString.rpad(vs[0],l1,'0');
         }
         vs[1] = null;
      }
      if(vs[1]){
         v = vs[0] + '.' + RString.nvl(vs[1]);
      }else{
         v = vs[0];
      }
   }else{
      if(l1){
         if(l1 <= v.length){
            v = v.substring(0, v.length - l1 + 1);
            for(var n = 0; n < l1 - 1;n++){
               v = v.concat('0');
            }
         }
         else if(l1 > v.length){
            v = 0;
         }
      }
      if(l2){
         v = v + '.';
         for(var n = 0; n < l2;n++){
            v = v.concat('0');
         }
      }
   }
   return v;
}
function FNumber_dispose(){
   var o = this;
   o.base.FEditControl.dispose.call(o);
   o.hLabel = null;
   o.hUpIcon = null;
   o.hDownIcon = null;
   o.hChgIic = null;
}
function FPageControl(o){
   o = RClass.inherits(this, o, FContainer, MDesign, MHorizontal);
   o.styleForm        = RClass.register(o, new TStyle('Form'));
   o.styleTitlePanel  = RClass.register(o, new TStyle('TitlePanel'));
   o.styleTitleForm   = RClass.register(o, new TStyle('TitleForm'));
   o.styleDataPanel   = RClass.register(o, new TStyle('DataPanel'));
   o.styleDataForm    = RClass.register(o, new TStyle('DataForm'));
   o.sheets           = new TMap();
   o.selected         = null;
   o._esize           = ESize.Both;
   o.hTop             = null;
   o.hLine            = null;
   o.hBottom          = null;
   o.hSheets          = null;
   o.oeBuild          = FPageControl_oeBuild;
   o.oeRefresh        = FPageControl_oeRefresh;
   o.onBuildPanel     = FPageControl_onBuildPanel;
   o.appendChild      = FPageControl_appendChild;
   o.select           = FPageControl_select;
   o.selectByIndex    = FPageControl_selectByIndex;
   o.sheet            = FPageControl_sheet;
   o.push             = FPageControl_push;
   o.dispose          = FPageControl_dispose;
   return o;
}
function FPageControl_oeBuild(event){
   var o = this;
   o.base.FContainer.oeBuild.call(o, event);
   var hp = o.hPanel;
   if(event.isBefore()){
      hp.width = '100%';
      hp.height = '100%';
      var hc = hp.insertRow().insertCell();
      hc.height = 1;
      var htf = o.hTitleForm = RBuilder.appendTable(hc, o.style('TitleForm'));
      o.hTop = htf.insertRow();
      o.hLine = htf.insertRow();
      o.hBottom = htf.insertRow();
      var hc = o.hTop.insertCell();
      hc.width = 20;
      RBuilder.appendEmpty(hc);
      o.hLine.insertCell();
      var hbc = o.hBottom.insertCell();
      hbc.className = o.style('Bottom', FPageSheet);
      var hc = hp.insertRow().insertCell();
      hc.height = 4;
   }else if(event.isAfter()){
      var hc = o.hTop.insertCell();
      hc.className = o.style('Top', FPageSheet);
      RBuilder.appendEmpty(hc);
      o.hLine.insertCell();
      var hc = o.hBottom.insertCell();
      hc.className = o.style('Bottom', FPageSheet);
      o.selectByIndex(0);
   }
}
function FPageControl_oeRefresh(e){
   var o = this;
   var r = o.base.FContainer.oeRefresh.call(o, e);
   if(e.isBefore()){
      if(o.sheets.count){
         if(o.selected){
            o.selected.oeRefresh(e);
         }else{
            var s = o.selected = o.sheets.value(0);
            if(s){
               s.innerSelect(true);
            }
         }
      }
   }
   return r;
}
function FPageControl_onBuildPanel(){
   this.hPanel = RBuilder.newTable();
   this.hPanel.backgroundColor='red';
}
function FPageControl_appendChild(p){
   if(RClass.isClass(p, FPageSheet)){
      var o = this;
      var hc = p.hTopL = o.hTop.insertCell();
      hc.className = p.style('Top');
      var hc = p.hTop = o.hTop.insertCell();
      hc.className = p.style('Top');
      var hc = p.hTopR = o.hTop.insertCell();
      hc.className = p.style('Top');
      var hc = p.hLeft = o.hLine.insertCell();
      hc.className = p.style('Left');
      RBuilder.appendEmpty(hc);
      var hc = p.hButtonPanel = o.hLine.insertCell();
      p.attachEvent('onHeadMouseDown', hc);
      hc.width = 1;
      var hb = p.hButton = RBuilder.append(hc, 'DIV', p.style('Button'));
      if(p.icon){
         p.hIcon = RBuilder.appendIcon(hb, p.icon);
      }
      if(p.label){
         p.hText = RBuilder.append(hb, 'SPAN', p.style('ButtonText'));
         p.hText.innerText = ' ' + p.label;
      }
      var hc = p.hRight = o.hLine.insertCell();
      hc.className = p.style('Right')
      RBuilder.appendEmpty(hc);
      var hc = p.hBottomL = o.hBottom.insertCell();
      hc.className = p.style('Bottom');
      var hc = p.hBottom = o.hBottom.insertCell();
      hc.className = p.style('Bottom');
      var hc = p.hBottomR = o.hBottom.insertCell();
      hc.className = p.style('Bottom');
      var hr = o.hPanel.insertRow();
      if(p.index){
         hr.style.display = 'none';
      }
      var hc = hr.insertCell();
      p.hForm = hr;
      hc.style.verticalAlign = 'top';
      hc.appendChild(p.hPanel);
   }
}
function FPageControl_select(p){
   var o = this;
   o.selected = p;
   for(var n=0; n<o.sheets.count; n++){
      var c = o.sheets.value(n);
      if(c != p){
         c.select(false);
      }
   }
   p.select(true);
}
function FPageControl_selectByIndex(n){
   var o = this;
   var p = o.sheets.value(n);
   if(p){
      o.select(p);
   }
}
function FPageControl_sheet(name){
   return this.sheets.get(name);
}
function FPageControl_push(p){
   var o = this;
   if(RClass.isClass(p, FPageSheet)){
      p.pages = o;
      p.index = o.sheets.count;
      o.sheets.set(p.name, p);
   }
   o.base.FContainer.push.call(o, p);
}
function FPageControl_dispose(){
   var o = this;
   o.base.FContainer.dispose.call(o);
}
function FPageSheet(o){
   o = RClass.inherits(this, o, FPanel, MDisplayAble);
   o.icon            = RClass.register(o, new TPtyStr('icon', null));
   o.formName        = RClass.register(o, new TPtyStr('formName', null));
   o.formLink        = RClass.register(o, new TPtyStr('formLink', null));
   o.formWhere       = RClass.register(o, new TPtyStr('formWhere', null));
   o.formOrder       = RClass.register(o, new TPtyStr('formOrder', null));
   o.top             = 0;
   o.onHeadMouseDown = RClass.register(o, new HMouseDown('onHeadMouseDown'));
   o.stTop           = RClass.register(o, new TStyle('Top'));
   o.stTopSelect     = RClass.register(o, new TStyle('TopSelect'));
   o.stLeft          = RClass.register(o, new TStyle('Left'));
   o.stLeftSelect    = RClass.register(o, new TStyle('LeftSelect'));
   o.stRight         = RClass.register(o, new TStyle('Right'));
   o.stRightSelect   = RClass.register(o, new TStyle('RightSelect'));
   o.stRightPrior    = RClass.register(o, new TStyle('RightPrior'));
   o.stButtom        = RClass.register(o, new TStyle('Bottom'));
   o.stBottomSelect  = RClass.register(o, new TStyle('BottomSelect'));
   o.stButtonText    = RClass.register(o, new TStyle('ButtonText'));
   o.stButton        = RClass.register(o, new TStyle('Button'));
   o.stButtonHover   = RClass.register(o, new TStyle('ButtonHover'));
   o.stButtonSelect  = RClass.register(o, new TStyle('ButtonSelect'));
   o.stDataPanel     = RClass.register(o, new TStyle('DataPanel'));
   o.pages           = null;
   o.index           = null;
   o.selected        = false;
   o.hasBuilded      = false;
   o.lsnsSelect      = new TListeners();
   o.hTopL           = null;
   o.hTop            = null;
   o.hTopR           = null;
   o.hLeft           = null;
   o.hButton         = null;
   o.hIcon           = null;
   o.hText           = null;
   o.hBottomL        = null;
   o.hBottom         = null;
   o.hBottomR        = null;
   o.hRight          = null;
   o.onHeadMouseDown = FPageSheet_onHeadMouseDown;
   o.onBuildPanel    = FPageSheet_onBuildPanel;
   o.onEnter         = FPageSheet_onEnter;
   o.onLeave         = FPageSheet_onLeave;
   o.innerSelect     = FPageSheet_innerSelect;
   o.select          = FPageSheet_select;
   o.setVisible      = FPageSheet_setVisible;
   o.dump            = FPageSheet_dump;
   o.dispose         = FPageSheet_dispose
   return o;
}
function FPageSheet_onBuildPanel(){
   var o = this;
   var hp = o.hContainer = o.hPanel = RBuilder.create(null, 'DIV');
   hp.width = '100%';
   hp.height = '100%';
   var hf = o.hPanelForm = RBuilder.appendTable(hp);
   hf.width = '100%';
   hf.height = '100%';
}
function FPageSheet_onEnter(){
   var o = this;
   if(!o.selected){
      o.hButton.className = o.style('ButtonHover');
   }
}
function FPageSheet_onLeave(){
   var o = this;
   if(!o.selected){
      o.hButton.className = o.style('Button');
   }
}
function FPageSheet_onHeadMouseDown(){
   this.pages.select(this);
}
function FPageSheet_innerSelect(flag){
   var o = this;
   var b = o.pages;
   if(flag && !o.hasBuilded){
      o.hasBuilded = true;
   }
   var first = (o.index == 0);
   var prior = (b.selected.index-1 == o.index);
   if(o.selected != flag){
      if(flag){
         o.lsnsSelect.process();
      }
      o.selected = flag;
   }
   o.hButton.className = flag ? o.style('ButtonSelect') : o.style('Button');
   o.hTop.className = flag ? o.style('TopSelect') : o.style('Top');
   o.hLeft.className = flag ? o.style('LeftSelect') : (first ? o.style('Right') : o.style('Left'));
   o.hBottomL.className = flag ? o.style('BottomSelect') : o.style('Bottom');
   o.hBottom.className = flag ? o.style('BottomSelect') : o.style('Bottom');
   o.hBottomR.className = flag ? o.style('BottomSelect') : o.style('Bottom');
   o.hRight.className = flag ? o.style('RightSelect') : (prior ? o.style('RightPrior') : o.style('Right'));
   o.hForm.style.display = flag ? 'block' : 'none';
}
function FPageSheet_select(flag){
   var o = this;
   o.innerSelect(flag);
   if(flag){
      o.psRefresh();
      o.psResize();
   }
}
function FPageSheet_setVisible(v){
   var o = this;
   o.hForm.style.display = v ? 'block' : 'none';
}
function FPageSheet_dump(dump, space){
   dump = RString.nvlStr(dump);
   dump.append(space, RClass.name(this), ' [');
   dump.append('name=', this.name, ', ');
   dump.append('icon=', this.icon, ', ');
   dump.append('label=', this.label, ', ');
   dump.append('action=', this.action, ']');
   return dump;
}
function FPageSheet_dispose(){
   var o = this;
   o.base.FPanel.dispose.call(o);
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
function FPanel(o){
   o = RClass.inherits(this, o, FLayout, MDesign, MFocus);
   return o;
}
function FPicker(o){
   o = RClass.inherits(this, o, FDataEditControl, MDropable);
   o.hForm         = null;
   o.hDrop         = null;
   o.hForm         = null;
   o.onBuildEdit   = FPicker_onBuildEdit;
   o.onEditEnd     = FPicker_onEditEnd;
   o.text          = FPicker_text;
   o.setText       = FPicker_setText;
   o.drop          = FPicker_drop;
   o.dispose       = FPicker_dispose;
   return o;
}
function FPicker_onBuildEdit(){
   var o = this;
   o.hEdit = RBuilder.newEdit();
   o.hDrop = RBuilder.newIcon(null, 'ctl.ds-ds');
}
function FPicker_onEditEnd(editor){
   var o = this;
   if(editor){
      var dsCtl = o.topControl(MDataset);
      if(dsCtl){
         dsCtl.dsMovePosition(editor.position());
      }
   }
   o.base.FDataEditControl.onEditEnd.call(o);
   RLog.debug(o, 'Edit end (editor={1} value={2})', editor, o.dataValue);
}
function FPicker_text(){
   return this.hEdit.value;
}
function FPicker_setText(text){
   this.hEdit.value = text;
}
function FPicker_drop(){
   var o = this;
   if(o.canDrop() && o.canEdit){
      var dsCtl = o.topControl(MDataset);
      if(dsCtl){
         var editor = RConsole.find(FEditConsole).focus(o, FPickerEditor, o.name);
         editor.linkPanel(o.hControlPanel, o.hEdit, o.hEditForm);
         editor.setDsControl(dsCtl);
         editor.setValue(o.hEdit.value);
         editor.show();
      }
   }
}
function FPicker_dispose(){
   var o = this;
   o.base.FDataEditControl.dispose.call(o);
   RMemory.freeHtml(o.hEdit);
   o.hEdit = null;
}
function FPickerEditor(o){
   o = RClass.inherits(this, o, FDropEditor, MShadow);
   o.MaxHeight    = 240;
   o.originIndex  = null;
   o.selectIndex  = null;
   o.dsControl    = null;
   o.dataset      = null;
   o.columns      = new TList();
   o.ohDropMdown  = FPickerEditor_ohDropMdown;
   o.ohDropMup    = FPickerEditor_ohDropMup;
   o.ohRowEnter   = FPickerEditor_ohRowEnter;
   o.ohRowLeave   = FPickerEditor_ohRowLeave;
   o.ohRowMdown   = FPickerEditor_ohRowMdown;
   o.onBuildEdit  = FPickerEditor_onBuildEdit;
   o.onKeyDown    = FPickerEditor_onKeyDown;
   o.isColumn     = FPickerEditor_isColumn;
   o.panel        = FPickerEditor_panel;
   o.position     = FPickerEditor_position;
   o.setPosition  = FPickerEditor_setPosition;
   o.buildFlag    = FPickerEditor_buildFlag;
   o.setDsControl = FPickerEditor_setDsControl;
   o.select       = FPickerEditor_select;
   o.focus        = FPickerEditor_focus;
   o.show         = FPickerEditor_show;
   o.hide         = FPickerEditor_hide;
   o.setValue     = FPickerEditor_setValue;
   return o;
}
function FPickerEditor_ohDropMdown(){
   var o = this.link;
   if(o.inEdit && RClass.isClass(o, FPickerEditor)){
      RLog.debug(o, 'FPickerEditor.ohDropMdown', 'Drop mouse down');
      o.isSkipBlur = true;
   }
}
function FPickerEditor_ohDropMup(){
   var o = this.link;
   if(o.inEdit && RClass.isClass(o, FPickerEditor)){
      RLog.debug(o, 'FPickerEditor.ohDropMup', 'Drop mouse up');
      o.hEdit.focus();
   }
}
function FPickerEditor_ohRowEnter(){
   var o = this.link;
   if(RClass.isClass(o, FPickerEditor)){
      if(!this.ptySelect){
         this.className = o.style('RowHover');
      }
   }
}
function FPickerEditor_ohRowLeave(){
   var o = this.link;
   if(RClass.isClass(o, FPickerEditor)){
      if(!this.ptySelect){
         this.className = o.style('Row');
      }
   }
}
function FPickerEditor_ohRowMdown(){
   var o = this.link;
   if(RClass.isClass(o, FPickerEditor)){
      o.selectIndex = this.ptyIndex;
      o.editStatus = EEditStatus.Ok;
      o.inEdit = false;
      o.hEditor.blur();
   }
}
function FPickerEditor_onBuildEdit(){
   var o = this;
   var hFormTab = o.hForm = RBuilder.appendTable(o.hPanel);
   var hTltCel = hFormTab.insertRow().insertCell();
   var hTab = RBuilder.appendTable(hTltCel);
   var hRow = hTab.insertRow();
   var hCel = hRow.insertCell();
   o.hEditCel = hCel;
   o.hEditor = RBuilder.append(hCel, 'INPUT', o.style('Edit'));
   o.hEdit = o.hEditor;
   var hCel = hRow.insertCell()
   o.hDrop = RBuilder.appendIcon(hCel, 'ctl.ds-ds', o.style('Drop'));
   var hDrpCel = hFormTab.insertRow().insertCell();
   var h = o.hDropPanel = RBuilder.append(hDrpCel, 'DIV', o.style('DropPanel'));
   h.link = o;
   h.onmousedown = o.ohDropMdown;
   h.onmouseup = o.ohDropMup;
   var h = o.hRowsPanel = RBuilder.appendTable(o.hDropPanel, null, 0, 1, 1);
   h.width = '100%';
   o.linkKeyEvent(o.hEdit);
}
function FPickerEditor_onKeyDown(event){
   var o = this;
   var kc = event.keyCode;
   if(EKey.Up == kc){
      o.select(o.selectIndex-1);
   }else if(EKey.Down == kc){
      o.select(o.selectIndex+1);
   }else if(EKey.Esc == kc){
      o.selectIndex = o.originIndex;
      o.editStatus = EEditStatus.Cancel;
      o.inEdit = false;
      o.hEditor.blur();
   }else if(EKey.Enter == kc){
      o.editStatus = EEditStatus.Ok;
      o.inEdit = false;
      o.hEditor.blur();
   }
}
function FPickerEditor_isColumn(c){
   if(c.dispPicker){
      return true;
   }
   return false;
}
function FPickerEditor_panel(type){
   var o = this;
   if(EPanel.Shadow == type){
      return o.hDropPanel;
   }
   return o.base.FDropEditor.panel.call(o, type);
}
function FPickerEditor_position(){
   return this.selectIndex;
}
function FPickerEditor_setPosition(p){
   var o = this;
   o.originIndex = p;
   o.selectIndex = p;
}
function FPickerEditor_buildFlag(hRow){
   var h = hRow.insertCell();
   h.className = this.style('Flag');
   return h;
}
function FPickerEditor_setDsControl(dsCtl, force){
   var o = this;
   if(o.dataset == dsCtl.dsControl){
      if(!force && o.hRowsPanel.rows.length){
         return;
      }
   }
   o.hPanel.style.display = 'block';
   var dc = o.dsControl = dsCtl;
   var ds = o.dataset = dsCtl.dsControl;
   o.originIndex = ds.position;
   o.selectIndex = ds.position;
   var count = ds.count();
   var cols = o.columns;
   cols.clear();
   var cs = dsCtl.components;
   for(var i=0; i<cs.count; i++){
      var c = cs.value(i);
      if(o.isColumn(c)){
         o.columns.push(c);
      }
   }
   RHtml.clear(o.hRowsPanel);
   var hRow = o.hRowsPanel.insertRow();
   o.buildFlag(hRow);
   for(var i=0; i<cols.count; i++){
      var c = cols.get(i);
      var hCel = hRow.insertCell();
      hCel.className = o.style('Title');
      if(c.dispAlign){
         hCel.align = c.dispAlign;
      }
      hCel.innerText = RString.nvl(c.label);
   }
   for(var n=0; n<count; n++){
      var row = ds.row(n);
      var hRow = o.hRowsPanel.insertRow();
      hRow.link = o;
      hRow.ptyIndex = n;
      hRow.className = o.style('Row');
      hRow.onmouseenter = o.ohRowEnter;
      hRow.onmouseleave = o.ohRowLeave;
      hRow.onmousedown = o.ohRowMdown;
      o.buildFlag(hRow);
      for(var i=0; i<cols.count; i++){
         var c = cols.get(i);
         var hCel = hRow.insertCell();
         if(c.dispAlign){
            hCel.align = c.dispAlign;
         }
         hCel.innerText = RString.nvl(row.get(c.dataName));
      }
   }
   var hRow = o.hRowsPanel.insertRow();
   RBuilder.appendEmpty(o.buildFlag(hRow), 3);
   for(var i=0; i<cols.count; i++){
      var c = cols.get(i);
      var hCel = hRow.insertCell();
      if(c.pickerWidth){
         RBuilder.appendEmpty(hCel, c.pickerWidth);
      }
   }
   o.hDropPanel.style.height = o.hRowsPanel.offsetHeight+2;
}
function FPickerEditor_select(n){
   var o = this;
   o.selectIndex = RInt.toRange(n, 0, o.dataset.count()-1);
   var rows = o.hRowsPanel.rows;
   for(var n=0; n<rows.length; n++){
      var row = rows[n];
      if(row.ptyIndex == o.selectIndex){
         row.className = o.style('RowSelect');
         row.ptySelect = true;
         row.scrollIntoView(false);
      }else{
         row.className = o.style('Row');
         row.ptySelect = false;
      }
   }
}
function FPickerEditor_focus(){
   this.hEdit.focus();
}
function FPickerEditor_show(v){
   var o = this;
   o.base.FDropEditor.show.call(o, v);
   o.select(o.selectIndex);
   var height = o.hDropPanel.offsetHeight;
   o.hDropPanel.style.height = Math.min(height, o.MaxHeight);
   o.base.MShadow.show.call(o, v);
   o.isSkipBlur = false;
}
function FPickerEditor_hide(){
   var o = this;
   o.base.FDropEditor.hide.call(o);
   o.base.MShadow.hide.call(o);
}
function FPickerEditor_setValue(value){
   var o = this;
   o.changed = false;
   o.dataValue = value;
   o.hEdit.value = value;
   o.select(o.selectIndex);
}
function FPicture(o){
   o = RClass.inherits(this, o, FEditControl, MEditBorder, MDescEdit);
   o.storeType         = RClass.register(o, new TPtyStr('storeType'));
   o.storeCode         = RClass.register(o, new TPtyStr('storeCode'));
   o.storeName         = RClass.register(o, new TPtyStr('storeName'));
   o.editAdjust        = RClass.register(o, new TPtyInt('editAdjust'));
   o.editMaxWidth      = RClass.register(o, new TPtyInt('editMaxWidth'));
   o.editMaxHeight     = RClass.register(o, new TPtyInt('editMaxHeight'));
   o.__seed            = 0;
   o.attributes        = null;
   o.border            = null;
   o.borderStyle       = EBorder.Round;
   o.onUploadMouseDown = RClass.register(o, new HMouseDown('onUploadMouseDown'), FPicture_onUploadMouseDown);
   o.onFileUploaded    = FPicture_onFileUploaded;
   o.onBuildEdit       = FPicture_onBuildEdit;
   o.construct         = FPicture_construct;
   o.makeIconPath      = FPicture_makeIconPath;
   o.setText           = FPicture_setText;
   o.setEditable       = FPicture_setEditable;
   o.dispose           = FPicture_dispose;
   return o;
}
function FPicture_onUploadMouseDown(e){
   var o = this;
   if(o._editable && !o._disbaled){
      var uw = RConsole.find(FUploadConsole).findWindow();
      uw.lsnsUploaded.register(o, o.onFileUploaded);
      uw.typeCode = 'P';
      uw.fileEdit = false;
      uw.recordCode = o.recordCode;
      uw.recordGuid = o.recordGuid;
      uw.recordName = o.recordName;
      uw.guid = o.guid;
      uw.adjustWidth = o.editWidth;
      uw.adjustHeight = o.editHeight;
      uw.show();
   }
}
function FPicture_onFileUploaded(s, g){
   var o = this;
   var as = g.attributes;
   o.guid = as.get('GUID');
   o.mime = as.get('MIME');
   o.networkCode = as.get('NETWORK_CODE')
   o.hImage.src = o.makeIconPath(o.guid, o.mime, o.networkCode) + '?' + RDate.format() + (++o.__seed);
   o.hImage.style.display = 'block';
}
function FPicture_onBuildEdit(b){
   var o = this;
   var hif = o.hImageForm = o.hEdit = RBuilder.appendTable(b.hPanel);
   hif.width = '100%';
   hif.border = 1;
   hif.height = '100%';
   var hc = o.hImagePanel = hif.insertRow().insertCell();
   hc.align = 'center';
   hc.style.cursor = 'hand';
   o.attachEvent('onUploadMouseDown', o.hImagePanel);
   var h = o.hImage = RBuilder.append(hc, 'IMAGE');
   h.style.border = '1 solid #CCCCCC';
   h.style.display = 'none';
   if(o.left>0 && o.top>0){
      o.hPanel.style.position = 'absolute';
   }
}
function FPicture_construct(){
   var o = this;
   o.base.FEditControl.construct.call(o);
   o.attributes = new TAttributes();
}
function FPicture_makeIconPath(g, m, sc){
   var o = this;
   var s = o.recordCode + '/' + o.recordGuid + '/' + g + '.icon.' + m;
   return top.RContext.context('/svr/' + sc.toLowerCase() + '/sys/' + RString.toLower(s));
}
function FPicture_setText(t){
   var o = this;
   var as = o.attributes;
   as.clear();
   var v = false;
   if(!RString.isEmpty(t)){
      as.unpack(t);
      o.networkCode = as.get('nc');
      o.recordCode = as.get('code');
      o.recordGuid = as.get('guid');
      o.recordName = as.get('name');
      o.guid = as.get('ogid');
      o.mime = as.get('mime');
      if(o.guid && o.mime){
         v = true;
         o.hImage.src = o.makeIconPath(o.guid, o.mime, o.networkCode);
      }
   }
   o.hImage.style.display = v ? 'block' : 'none';
}
function FPicture_setEditable(v){
   var o = this;
   o.base.FEditControl.setEditable.call(o, v);
   if(v){
      o.hImagePanel.style.cursor = 'hand';
   }else{
      o.hImagePanel.style.cursor = 'normal';
   }
}
function FPicture_dispose(){
   var o = this;
   o.base.FEditControl.dispose.call(o);
   o.hImage = null;
}
function FPrepareAction(o){
   o = RClass.inherits(this, o, FDataAction);
   o.onLoaded      = FPrepareAction_onLoaded;
   return o;
}
function FPrepareAction_onLoaded(doc){
   var controls = this.parent.controls;
   var node = doc.root().find('Dataset');
   if(node){
      this.valuable.loadValue(node);
   }
}
function FProgress(o){
   o = RClass.inherits(this, o, FDataEditControl, MDropable);
   o.hForm       = null;
   o.hDrop       = null;
   o.hForm       = null;
   o.onBuildEdit = FProgress_onBuildEdit;
   o.onEditEnd   = FProgress_onEditEnd;
   o.text        = FProgress_text;
   o.setText     = FProgress_setText;
   o.drop        = FProgress_drop;
   return o;
}
function FProgress_onBuildEdit(){
   var o = this;
   o.hEdit = RBuilder.newEdit();
   o.hDrop = RBuilder.newIcon(null, 'ctl.clrdrop');
}
function FProgress_onEditEnd(editor){
   var o = this;
   RLog.debug(o, 'Begin (editor={0}:{1} value={2})', editor, editor?editor.color:'', o.dataValue);
   if(editor){
      o.setValue(editor.color);
   }
   o.base.FDataEditControl.onEditEnd.call(o);
   RLog.debug(o, 'End (editor={0} value={1})', editor, o.dataValue);
}
function FProgress_text(){
   return this.hEdit.value;
}
function FProgress_setText(text){
   var o = this;
   o.hEdit.value = text.toUpperCase();
   o.hDropPanel.style.backgroundColor = text;
}
function FProgress_drop(){
   var o = this;
   if(o.canDrop() && o.canEdit){
      var editor = RConsole.find(FEditConsole).focus(o, FProgressEditor);
      editor.linkPanel(o.hControlPanel, o.hEdit, o.hEditForm);
      editor.setColor(o.value());
      editor.show();
   }
}
function FProgressBar(o){
   o = RClass.inherits(this, o, FEditControl, MEditBorder);
   o.hUnit          = null;
   o.borderStyle    = EBorder.Round;
   o.onBuildEdit    = FProgressBar_onBuildEdit;
   o.onBuildControl = FProgressBar_onBuildControl;
   o.formatValue    = FProgressBar_formatValue;
   o.text           = FProgressBar_text;
   o.set            = FProgressBar_set;
   o.get            = RMethod.Empty;
   o.dispose        = FProgressBar_dispose;
   return o;
}
function FProgressBar_onBuildEdit(b){
   var o = this;
   var htb = o.hEdit = o.hPanelForm = RBuilder.appendTable(b.hPanel);
   htb.style.tableLayout  = 'fixed';
   htb.height = 10;
   var hr = htb.insertRow();
   var value = '0%';
   var v = RFloat.parse(RString.nvl(value));
   v = v * 100;
   v = v + "%";
   var hc1 = hr.insertCell();
   hc1.style.width = v;
   hc1.style.backgroundColor = '#29BAD5';
   var hc2 = hr.insertCell();
   htb.title  = v;
}
function FProgressBar_onBuildControl(e){
   var o = this;
   o.base.FEditControl.onBuildControl.call(o);
   if(o.editUnit){
      var h = o.hUnit = o.hControlRow.insertCell();
      h.className = o.style('Unit');
      h.innerText = o.editUnit;
   }
}
function FProgressBar_formatValue(text){
   this.hEdit.value = text;
}
function FProgressBar_text(t){
   var o = this;
   if(RString.isEmpty(t)){
      if(o.validRequire){
         return false;
      }
   }
   return true;
}
function FProgressBar_set(value){
   var o = this;
   var htb = o.hPanelForm;
   if(!RString.isEmpty(value)){
      htb.innerText = '';
      htb.style.tableLayout  = 'fixed';
      htb.height = 10;
      var hr = htb.insertRow();
      var v = RFloat.parse(RString.nvl(value));
      v = v * 100;
      v = v + "%";
      var hc1 = hr.insertCell();
      hc1.style.width = v;
      hc1.style.backgroundColor = '#29BAD5';
      var hc2 = hr.insertCell();
      htb.title  = v;
   }
}
function FProgressBar_dispose(){
   var o = this;
   o.base.FEditControl.dispose.call(o);
   o.hEdit = null;
   o.hPanelForm = null;
   o.hControlRow = null;
}
function FRadio(o){
   o = RClass.inherits(this, o, FEditControl);
   o.groupName    = RClass.register(o, new TPtyStr('groupName'));
   o.editChecked  = RClass.register(o, new TPtyBool('editChecked', false));
   o.onClick      = RMethod.emptyCall;
   o.onDataClick  = RMethod.emptyCall;
   o.onBuildEdit  = FRadio_onBuildEdit;
   o.clearValue   = FRadio_clearValue;
   o.resetValue   = FRadio_resetValue;
   o.saveValue    = FRadio_saveValue;
   o.text         = FRadio_text;
   o.setText      = FRadio_setText;
   o.refreshStyle = FRadio_refreshStyle;
   return o;
}
function FRadio_onBuildEdit(h){
   var o = this;
   var he = o.hEdit = RBuilder.append(h, '<INPUT type=radio name=' + o.dataName + '>');
   he.style.border = 0;
   he.style.cursor = 'hand';
}
function FRadio_clearValue(){
   this.hEdit.checked = false;
}
function FRadio_resetValue(){
   this.hEdit.checked = this.editChecked;
}
function FRadio_saveValue(vs){
   var o = this;
   if(o.hEdit.checked){
      vs.set(o.dataName, o.dataDefault);
   }
}
function FRadio_text(){
   return this.hEdit.checked ? this.dataDefault : '';
}
function FRadio_setText(t){
   this.hEdit.checked = (this.dataDefault == t);
}
function FRadio_refreshStyle(){
   var o = this;
   var h = o.panel(EPanel.Edit);
   h.disabled = !o._editable;
   h.style.cursor = o._editable? 'hand':'normal';
}
function FReference(o){
   o = RClass.inherits(this, o, FEditControl, MDescEdit, MEditBorder);
   o.pickerWidth    = RClass.register(o, new TPtyInt('pickerWidth'), 80);
   o.__ouid         = null;
   o.__code         = null;
   o.__value        = null;
   o.onClearClick   = RClass.register(o, new HClick('onClearClick'), FReference_onClearClick);
   o.onListSelected = FReference_onListSelected;
   o.onListScalar   = FReference_onListScalar;
   o.onDataBlur     = FReference_onDataBlur;
   o.onDataKeyDown  = FReference_onDataKeyDown;
   o.onBuildEdit    = FReference_onBuildEdit;
   o.resetValue     = FReference_resetValue;
   o.setInfo        = FReference_setInfo;
   o.get            = FReference_get;
   o.getCode        = FReference_getCode;
   o.reget          = FReference_reget;
   o.type           = 'Radio';
   o.border         = null;
   o.hClearCell   = null;
   o.borderStyle    = EBorder.Round;
   o.formatValue    = FReference_formatValue;
   o.refreshStyle   = FReference_refreshStyle;
   o.formatText     = FReference_formatText;
   o.findEditor     = FReference_findEditor;
   o.onDataClick    = FReference_onDataClick;
   o.__onListScalar = new TInvoke();
   o.isTextChanged  = FReference_isTextChanged;
   o.set            = RMethod.empty;
   o.setCode        = FReference_setCode;
   o.setText        = FReference_setText;
   o.drop           = FReference_drop;
   o.dispose        = FReference_dispose;
   return o;
}
function FReference_onDataClick(){
   var o = this;
   if ('N' == o.typeAble) {
      o.onListClick();
   }
}
function FReference_onListSelected(v){
   var o = this;
   o.__ouid = v;
   o.onDataEditEnd(o);
   var g = new TDatasetScalarArg(o, v);
   g.callback = new TInvoke(o, o.onListScalar);
   RConsole.find(FDatasetConsole).scalar(g);
}
function FReference_onListScalar(g){
   var o = this;
   o.setInfoPack(g.result);
   o.callEvent('onListScalar', o, o.__onListScarar);
}
function FReference_onDataBlur(s, e){
   var o = this;
   var f = o.topControl();
   var v = o.hEdit.value;
   if(!RString.isEmpty(v) && o.__code != v){
      var a = new TAttributes();
      var p = f.component('params');
      a.set('code', o.hEdit.value);
      if(p){
         a.set('params', p.reget());
      }
      var g = new TDatasetScalarArg(o, null, a);
      g.callback = new TInvoke(o, o.onListScalar);
      RConsole.find(FDatasetConsole).scalar(g);
   }
}
function FReference_onDataKeyDown(s, e){
   var o = this;
   o.base.FEditControl.onDataKeyDown.call(o, s, e);
   if(!o.canEdit){
      return;
   }
   if(o.editCase){
      RKey.fixCase(e, o.editCase);
   }
   if(o.editComplete){
      if( 16 != e.keyCode && 17 != e.keyCode && 18 != e.keyCode && 20 != e.keyCode ){
         var editor = o.findEditor();
         if(editor){
            editor.onEditKeyDown(s, e);
         }
      }
   }
   if(EKey.Enter == e.keyCode){
      o.onDataBlur(s, e);
   }
}
function FReference_onBuildEdit(b){
   var o = this;
   var htb = RBuilder.appendTable(b.hPanel);
   htb.style.tableLayout = 'fixed';
   var hr = o.hEdit = htb.insertRow();
   o.onBuildChange(hr.insertCell());
   var hea = o.hEditArea = hr.insertCell();
   hea.width = o.pickerWidth;
   hea.style.borderRight = '1 solid #999999';
   var he = o.hEdit = RBuilder.appendEdit(hea, o.style('Edit'));
   if ('N' == o.typeAble) {
      he.readOnly = 'true';
   }
   he.style.cursor = 'hand';
   he.style.textAlign = 'right';
   if(o.editLength){
      he.maxLength = o.editLength;
   }
   o.attachEvent('onDataBlur', he);
   var hnc = o.hNoteCell = hr.insertCell();
   hnc.style.paddingLeft = 4;
   hnc.style.borderLeft = '1 solid #FFFFFF';
   hnc.style.backgroundColor = EColor.Readonly;
   var hnc = o.hClearCell = hr.insertCell();
   hnc.style.display = 'none';
   hnc.style.width = 40;
   var t = RBuilder.appendTable(hnc);
   var tr = t.insertRow();
   var tc = tr.insertCell();
   tc.width = 35;
   tc.bgColor = '#F4F4F4';
   tc.style.borderLeft  = '1 solid #AAAAAA';
   tc.style.borderTop  = '1 solid #AAAAAA';
   tc.style.borderRight  = '1 solid #000000';
   tc.style.borderBottom = '1 solid #000000';
   tc.innerText = '清空';
   tc.align = 'center';
   hnc.style.paddingLeft = 2;
   hnc.title = '清除内容';
   hnc.style.borderLeft = '1 solid #999999';
   hnc.style.backgroundColor = EColor.Readonly;
   hnc.style.cursor = 'hand';
   o.attachEvent('onClearClick', hnc);
}
function FReference_onClearClick(){
   var o = this;
   o.__ouid = null;
   o.__code = null;
   o.hEdit.value = '';
   o.hNoteCell.innerHTML = '';
   o.dataValue = '';
}
function FReference_resetValue(){
   var o = this;
   o.__ouid = null;
   o.__code = null;
   o.hEdit.value = '';
   o.hNoteCell.innerHTML = '';
   o.dataValue = '';
}
function FReference_setInfo(f){
   var o = this;
   if(f){
	   o.__ouid = f.ouid;
	   o.hEdit.value = f.code;
	   o.hNoteCell.innerHTML = f.value;
	   o.__code = f.code;
   }
}
function FReference_setCode(t){
	var o = this;
	o.hEdit.value = t;
	var a = new TAttributes();
	var p = o.topControl().component('params');
    a.set('code', o.hEdit.value);
    if(p){
       a.set('params', p.reget());
    }
    a.set('code', o.hEdit.value);
    var g = new TDatasetScalarArg(o, null, a);
    g.callback = new TInvoke(o, o.onListScalar);
    RConsole.find(FDatasetConsole).scalar(g);
}
function FReference_set(t){
	var o = this;
	o.hEdit.value = t;
}
function FReference_get(){
   return RString.nvl(this.__ouid);
}
function FReference_getCode(){
   return RString.nvl(this.__code);
}
function FReference_reget(){
   var o = this;
   if(RString.isEmpty(o.hEdit.value)){
      o.__ouid = '';
   }
   return RString.nvl(o.__ouid);
}
function FReference_refreshStyle(){
	   var o = this;
	   o.base.FEditControl.refreshStyle.call(o);
       if(!o._editable){
	      o.hEdit.style.cursor = 'normal';
	      o.hClearCell.style.display = 'none';
       }else{
    	   o.hEdit.style.cursor = 'hand';
    	   o.hClearCell.style.display = 'block';
       }
       if ('N' == o.typeAble) {
	      o.hEdit.readOnly = 'true';
       }
	}
function FReference_formatValue(s){
   var o = this;
   if(!RString.isEmpty(s) && o.editPattern && RString.equals('yyyy-mm-dd hh24:mi:ss', o.editPattern.toLowerCase())){
      return RString.nvl(o.dataValue);
   }
   return RString.nvl(s);
}
function FReference_findEditor(){
   var o = this;
   if(o.editComplete){
      var de = o.editor;
      if(!de){
         o.dsControl = o.topControl(MDataset);
         if(o.dsControl){
            de = o.editor = RConsole.find(FReferenceConsole).focus(o, FReferenceEditor);
         }
      }
      if(de){
         de.linkControl(o);
      }
      return o.editor;
   }
}
function FReference_drop(){
   var o = this;
   var de = o.findEditor();
   if(de){
      var t = o.reget();
      if(t.length > 0){
         if(o.finded != t){
            if(de.source != o){
               de.linkControl(o);
            }
            de.search(t);
         }
         o.finded = t;
      }
   }
}
function FReference_setText(t){
   var o = this;
   if(!RString.isEmpty(t)){
      var as = new TAttributes();
      as.unpack(t);
      o.hEdit.value = as.get('ouid');
      o.hNoteCell.innerHTML = as.get('note');
   }
}
function FReference_isTextChanged(){
   return RString.nvl(this.text()) != this.__recordText;
}
function FReference_formatText(v){
   var o = this;
   var hasG = false;
   v = RString.nvl(v);
   o.dataValue = v;
   if(!RString.isEmpty(v) && o.editPattern && RString.equals('yyyy-mm-dd hh24:mi:ss', o.editPattern.toLowerCase())){
      if(RString.contains(v, '-')){
         hasG = true;
      }
      v = RString.removeChars(v, '-');
      var sv = new Array(6);
      sv[0] = v.substring(0,4);
      sv[1] = v.substring(4,6);
      sv[2] = v.substring(6,8);
      sv[3] = v.substring(8,10);
      sv[4] = v.substring(10,12);
      sv[5] = v.substring(12);
      var s = '';
      if( RInt.parse(sv[0])!=0 ){
         s += sv[0]+'年';
      }
      if( RInt.parse(sv[1])!=0 ){
         s += sv[1]+'月';
      }
      if( RInt.parse(sv[2])!=0 ){
         s += sv[2]+'天';
      }
      if( RInt.parse(sv[3])!=0 ){
         s += sv[3]+'小时';
      }
      if( RInt.parse(sv[4])!=0 ){
         s += sv[4]+'分钟';
      }
      if( RInt.parse(sv[5])!=0 ){
         s += sv[5]+'秒';
      }
      if(RString.isEmpty(s)){
         return '0';
      }
      if(hasG){
         s = '-'+s;
      }
      return s;
   }else{
      return v;
   }
}
function FReference_dispose(){
   var o = this;
   o.base.FEditControl.dispose.call(o);
   RMemory.freeHtml(o.hChgIic);
   RMemory.freeHtml(o.hEdit);
   o.hChgIic     = null;
   o.hClearCell  = null;
   o.hEdit       = null;
}
function FRemoteAction(o){
   o = RClass.inherits(this, o, FComponent);
   o.isLoading     = false;
   o.service       = null;
   o.valuable      = null;
   o.onLoaded      = FRemoteAction_onLoaded;
   o.loadConfig    = FRemoteAction_loadConfig;
   o.saveConfig    = FRemoteAction_saveConfig;
   o.execute       = FRemoteAction_execute;
   return o;
}
function FRemoteAction_onLoaded(e){
   var o = this;
   var doc = e.document;
   var rs = RConsole.find(FResultConsole).checkService(doc.root());
   if(rs){
      RWindow.setEnable(true);
   }
   o.isLoading = false;
}
function FRemoteAction_loadConfig(config){
   var o = this;
   o.base.FComponent.loadConfig.call(o, config);
   o.service = config.get('service');
}
function FRemoteAction_saveConfig(config){
   var o = this;
   o.base.FComponent.saveConfig.call(o, config)
   config.set('service',  this.service);
}
function FRemoteAction_execute(service, config){
   var o = this;
   var svc = RService.parse(RString.nvl(service, this.service));
   if(!svc){
      return alert('Unknown service');
   }
   RWindow.setEnable(false);
   var doc = new TXmlDocument();
   var root = doc.root();
   root.set('action', svc.action);
   RConsole.find(FEnvConsole).build(root);
   root.push(config);
   RLog.debug(this, 'Execute (service={1})\n{2}', svc.url, doc.dump());
   o.isLoading = true;
   var e = new TEvent(o, EXmlEvent.Send, o.onLoaded);
   e.url = svc.url;
   e.document = doc;
   e.action = this;
   RConsole.find(FXmlConsole).process(e);
}
function FSelect(o){
   o = RClass.inherits(this, o, FEditControl, MEditBorder, MDescSelect, MDropable);
   o.borderStyle   = EBorder.RoundDrop;
   o.items         = null;
   o.lsnEditEnd    = null;
   o.onDataKeyDown = FSelect_onDataKeyDown;
   o.onDataClick   = FSelect_onDataClick;
   o.onEditEnd     = FSelect_onEditEnd;
   o.onBuildEdit   = FSelect_onBuildEdit;
   o.construct     = FSelect_construct;
   o.loadConfig    = FSelect_loadConfig;
   o.formatValue   = FSelect_formatValue;
   o.formatText    = FSelect_formatText;
   o.refreshStyle  = FSelect_refreshStyle;
   o.drop          = FSelect_drop;
   o.doBlur        = FSelect_doBlur;
   o.dispose       = FSelect_dispose;
   return o;
}
function FSelect_onDataClick(){
   var o = this;
   if(!o.editCheck){
      o.drop();
   }
}
function FSelect_onDataKeyDown(s, e){
   var o = this;
   var ed = o.editor;
   var ef = ed && ed.inEdit;
   o.base.FEditControl.onDataKeyDown.call(o, s, e);
   if(ef && ed.source == o){
      ed.onEditKeyDown(s, e);
   }
}
function FSelect_onEditEnd(e){
   var o = this;
   if(e){
      o.set(e.get());
      o._invalidText = o.validText(o.text());
      o.refreshStyle();
   }
   o.onDataEditEnd(o);
}
function FSelect_onBuildEdit(b){
   var o = this;
   var hf = RBuilder.appendTable(b.hPanel);
   hf.style.tableLayout = 'fixed';
   var hr = hf.insertRow();
   o.onBuildChange(hr.insertCell())
   var hc = hr.insertCell();
   var he = o.hEdit = RBuilder.appendEdit(hc, o.style('Edit'));
   if(o.editLength){
      he.maxLength = o.editLength;
   }
}
function FSelect_construct(){
   var o = this;
   o.base.FEditControl.construct.call(o);
   o.items = new TItems();
   o.lsnEditEnd = new TListener(o, o.onEditEnd);
}
function FSelect_loadConfig(c){
   var o = this;
   o.base.FEditControl.loadConfig.call(o, c);
   if(o.dataEmpty){
      o.items.create();
   }
   if(!o.editCheck){
      o.items.create('', '');
   }
   o.items.loadConfig(c);
   var ns = c.nodes;
   if(ns){
   var nc = ns.count;
	   for(var n = 0; n < nc; n++){
		  var p = ns.get(n);
	      if(p.isName('Event')){
	    	  var e = RClass.create(FEvent);
	          e.loadConfig(p);
	          o.push(e);
	      }
	   }
   }
   return EStatus.Stop;
}
function FSelect_formatValue(t){
   var o = this;
   if(RBoolean.isTrue(o.editCheck)){
      var v = o.items.value(t);
      if(v){
         return v;
      }else{
         return RString.nvl(t);
      }
   }
   return o.items.value(t);
}
function FSelect_formatText(v){
   var o = this;
   if(RBoolean.isTrue(o.editCheck) && RString.isEmpty(o.items.label(v))){
      return v;
   }
   return o.items.label(v);
}
function FSelect_refreshStyle(){
   var o = this;
   o.base.FEditControl.refreshStyle.call(o);
   if(!o.editCheck){
      o.hEdit.readOnly = 'true';
   }
}
function FSelect_drop(){
   var o = this;
   if(o.canDrop() && o.canEdit && o.items.count() > 0 && o._editable){
      if(!o.editRefer){
         return RMessage.fatal(o, null, 'Edit refer is null.');
      }
      var e = o.editor = RConsole.find(FEditConsole).focus(o, FSelectEditor, o.editRefer);
      if(o.editDynamic){
         return RMessage.fatal(o, null, 'Unsupport.');
      }else{
    	 e.__source = o;
         e.setItems(o.items);
         e.set(o.reget());
      }
      e.lsnEditEnd = o.lsnEditEnd;
      e.show();
   }
}
function FSelect_doBlur(){
   var o = this;
   o.base.FEditControl.doBlur.call(o);
   if(o.editor){
      o.editor.hide();
   }
}
function FSelect_dispose(){
   var o = this;
   o.base.FEditControl.dispose.call(o);
}
function FSelectEditor(o){
   o = RClass.inherits(this, o, FDropEditor);
   o.__minHeight   = 300;
   o.__minWidth    = 160;
   o.items         = null;
   o.position      = null;
   o.lsnItemClick  = null;
   o.hDropLayout   = null;
   o.hItemsForm    = null;
   o.onItemClick   = FSelectEditor_onItemClick;
   o.onEditKeyDown = FSelectEditor_onEditKeyDown;
   o.onBuildDrop   = FSelectEditor_onBuildDrop;
   o.construct     = FSelectEditor_construct;
   o.testBlur      = FSelectEditor_testBlur;
   o.setItems      = FSelectEditor_setItems;
   o.get           = FSelectEditor_get;
   o.set           = FSelectEditor_set;
   o.select        = FSelectEditor_select;
   o.fetch         = FSelectEditor_fetch;
   o.show          = FSelectEditor_show;
   o.dispose       = FSelectEditor_dispose;
   o.__mouseDownEvent  = new TEvent();
   return o;
}
function FSelectEditor_onItemClick(s){
   var o = this;
   var t = o.__source;
   o.position = o.items.indexOf(s);
   o.editEnd();
   if(t){
      t.callEvent('onItemClick', t, o.__mouseDownEvent);
   }
}
function FSelectEditor_onEditKeyDown(s, e){
   var o = this;
   switch(e.keyCode){
      case EKey.Up:
         o.select(o.position - 1);
         break;
      case EKey.Down:
         o.select(o.position + 1);
         break;
      case EKey.Enter:
         o.editEnd();
         break;
      case EKey.Esc:
         o.editCancel();
         break;
   }
}
function FSelectEditor_onBuildDrop(){
   var o = this;
   var hdl = o.hDropLayout = RBuilder.append(o.hDropPanel, 'DIV')
   var hif = o.hItemsForm = RBuilder.appendTable(hdl);
   o.hItemsPanel = RBuilder.append(hif, 'TBODY');
}
function FSelectEditor_construct(){
   var o = this;
   o.lsnItemClick = new TListener(o, o.onItemClick);
}
function FSelectEditor_testBlur(c){
   var o = this;
   if(o.source == c){
      return false;
   }
   return !this.items.contains(c);
}
function FSelectEditor_setItems(items){
   var o = this;
   if(o.items){
      return;
   }
   var is = o.items = new TList();
   var hip = o.hItemsPanel;
   var count = items.count();
   for(var n=0; n<count; n++){
      if(n > 0){
         var hr = RBuilder.append(hip, 'TR');
         hr.height = 1;
         var hd = RBuilder.append(hr, 'TD');
         hd.colSpan = 3;
         hd.style.borderTop = '1 dashed #24C2DB';
         RBuilder.appendEmpty(hd);
      }
      var t = items.get(n);
      var c = RControl.create(FSelectItem);
      c.name = t.value;
      c.lsnsClick.push(o.lsnItemClick);
      c.set(t.icon, t.label, t.value);
      c.setPanel(hip);
      is.push(c);
      o.push(c);
   }
   o.position = 0;
}
function FSelectEditor_get(){
   var o = this;
   return o.items.get(o.position).value;
}
function FSelectEditor_set(v){
   var o = this;
   o.position = -1;
   var ps = o.items;
   var pc = ps.count;
   for(var n=0; n<pc; n++){
      var p = ps.get(n);
      if(RString.equals(p.value, v)){
         o.position = n;
         p.setChecked(true);
      }else{
         p.setChecked(false);
      }
   }
}
function FSelectEditor_select(p){
   var o = this;
   var is = o.items;
   var ic = is.count;
   p = Math.min(Math.max(0, p), ic-1)
   for(var n=0; n<ic; n++){
      is.get(n).setChecked(n == p);
   }
   o.position = p;
}
function FSelectEditor_fetch(){
   var o = this;
   if(!o.hasFetched){
      var g = new TCodeListServiceArg();
      var f = o.source.topControl(MDataset);
      g.values = f.getCurrentRows();
      g.name = o.source.editRefer;
      var doc = RConsole.find(FCodeListConsole).fetch(g);
      if(doc){
         var edt = o.source;
         edt.items.clear();
         edt.items.loadConfig(doc.root().nodes.get(0));
      }
      o.hasFetched = true;
   }
}
function FSelectEditor_show(v){
   var o = this;
   o.base.FDropEditor.show.call(o, v);
   var hp = o.hPanel;
   var hif = o.hItemsForm;
   var hbf = o.hBorderForm;
   var s = o.source;
   var r = s.getEditRange();
   hif.width = null;
   var iw = hif.offsetWidth;
   hp.style.pixelLeft = r.x;
   hp.style.pixelTop = r.y + r.height;
   hp.style.pixelWidth = Math.max(iw, r.width);
   hif.width = '100%';
   if(hif.offsetHeight > o.__minHeight){
      o.hDropLayout.style.overflowY = 'scroll';
      o.hDropLayout.style.pixelHeight = o.__minHeight;
   }
   o.base.MShadow.show.call(o);
}
function FSelectEditor_dispose(){
   var o = this;
   o.base.FDropEditor.dispose.call(o);
   o.hDropLayout = null;
   o.hItemsForm = null;
}
function FSelectItem(o){
   o = RClass.inherits(this, o, FControl);
   o.icon              = RClass.register(o, new TPtyStr('icon'));
   o.note              = RClass.register(o, new TPtyStr('note'));
   o.stHover           = RClass.register(o, new TStyle('Hover'));
   o.stSelect          = RClass.register(o, new TStyle('Select'));
   o.stIconChecked     = RClass.register(o, new TStyle('Icon'));
   o.stLabel           = RClass.register(o, new TStyle('Label'));
   o.stNote            = RClass.register(o, new TStyle('Note'));
   o.hIcon             = null;
   o.hIconPanel        = null;
   o.hLabelPanel       = null;
   o.hNotePanel        = null;
   o.checked           = false;
   o.lsnsClick         = new TListeners();
   o.oeBuild           = FSelectItem_oeBuild;
   o.onBuildPanel      = FSelectItem_onBuildPanel;
   o.onMouseOver       = FSelectItem_onMouseOver;
   o.onMouseOut        = FSelectItem_onMouseOut;
   o.onMouseDown       = FSelectItem_onMouseDown;
   o.set               = FSelectItem_set;
   o.setChecked        = FSelectItem_setChecked;
   o.dispose           = FSelectItem_dispose;
   return o;
}
function FSelectItem_oeBuild(e){
   var o = this;
   o.base.FControl.oeBuild.call(o,e);
   var h = o.hPanel;
   o.hIconPanel = RBuilder.append(h, 'TD', o.style("Icon"));
   o.hLabelPanel = RBuilder.append(h, 'TD', o.style("Label"));
   o.hNotePanel = RBuilder.append(h, 'TD', o.style("Note"));
   return EEventStatus.Stop;
}
function FSelectItem_onBuildPanel(){
   this.hPanel = RBuilder.create(null, 'TR', this.style("Panel"));
}
function FSelectItem_onMouseOver(){
   this.hPanel.className = RBool.isTrue(this.checked) ? this.style('Select') : this.style('Hover');
}
function FSelectItem_onMouseOut(){
   this.hPanel.className = RBool.isTrue(this.checked) ? this.style('Select') : this.style('Panel');
}
function FSelectItem_onMouseDown(){
   this.lsnsClick.process(this);
}
function FSelectItem_set(icon, label, value, note){
   var o = this;
   o.icon = RString.nvl(icon);
   if(!RString.isEmpty(o.icon)){
      o.hIcon = RBuilder.appendIcon(o.hIconPanel, o.styleIcon(o.icon));
   }
   o.label = RString.nvl(label);
   o.value = RString.nvl(value);
   o.note = RString.nvl(note);
   o.hLabelPanel.innerText = o.label;
   o.hNotePanel.innerText = o.note;
}
function FSelectItem_setChecked(f){
   var o = this;
   o.checked = f;
   if(o.hIcon){
      o.hIcon.style.display = f ? 'block' : 'none';
   }else{
      o.hIconPanel.innerText = f ? 'ü' : '';
   }
   o.hPanel.className = f ? o.style('Select') : o.style('Panel');
}
function FSelectItem_dispose(){
   var o = this;
   o.base.FControl.dispose.call(o);
   RMemory.freeHtml(o.hEdit);
   o.hEdit = null;
}
function FSplit(o){
   o = RClass.inherits(this, o, FControl, MDesign, MDisplay, MHorizontal);
   o.dispStyle         = RClass.register(o, new TPtyStr('dispStyle', ESplitStyle.Normal));
   o.icon              = RClass.register(o, new TPtyStr('icon'));
   o.editExtend        = RClass.register(o, new TPtyBool('editExtend'), true);
   o.stTitle           = RClass.register(o, new TStyle('Title'));
   o.iconMinus         = 'ctl.collapse_nor';
   o.iconPlus          = 'ctl.expand_nor';
   o.__lines           = null;
   o._esize            = ESize.Horizontal;
   o.extended          = true;
   o.hImage            = null;
   o.hIcon             = null;
   o.hText             = null;
   o.onSplitMouseEnter = RClass.register(o, new HMouseEnter('onSplitMouseEnter'), FSplit_onSplitMouseEnter);
   o.onSplitMouseLeave = RClass.register(o, new HMouseLeave('onSplitMouseLeave'), FSplit_onSplitMouseLeave);
   o.onMouseDown       = FSplit_onMouseDown;
   o.onBuildPanel      = FSplit_onBuildPanel;
   o.oeBuild           = FSplit_oeBuild;
   o.oeMode            = FSplit_oeMode;
   o.construct         = FSplit_construct;
   o.extend            = FSplit_extend;
   o.pushLine          = FSplit_pushLine;
   o.dispose           = FSplit_dispose;
   return o;
}
function FSplit_onSplitMouseEnter(e){
   var o = this;
   if(o.hImage){
      o.hImage.src = RRes.iconPath(o.extended ? 'ctl.collapse_hvr' : 'ctl.expand_hvr');
   }
}
function FSplit_onSplitMouseLeave(e){
   var o = this;
   if(o.hImage){
      o.hImage.src = RRes.iconPath(o.extended ? 'ctl.collapse_nor' : 'ctl.expand_nor');
   }
}
function FSplit_onMouseDown(){
   var o = this;
   if(ESplitStyle.Normal == o.dispStyle){
      o.extend(!o.extended);
   }
}
function FSplit_onBuildPanel(){
   var o = this;
   o.hPanel = RBuilder.create(null, 'DIV');
   o.hForm = RBuilder.appendTable(o.hPanel);
   o.hForm.width = '100%';
}
function FSplit_oeBuild(e){
   var o = this;
   o.base.FControl.oeBuild.call(o, e);
   o.height = 2;
   if(RString.equals(o.dispStyle, ESplitStyle.Normal)){
      var hf = o.hForm;
      var hr = hf.insertRow()
      o.attachEvent('onSplitMouseEnter', hf);
      o.attachEvent('onSplitMouseLeave', hf);
      var hc = hr.insertCell();
      hc.width = '100%';
      hc.height = 25;
      hc.style.padding = '0 0';
      hc.style.background = 'url(' + RRes.iconPath('ctl.FSplit_Panel') + ')';
      RBuilder.appendEmpty(hc, 4);
      o.hImage = RBuilder.appendIcon(hc, o.iconMinus);
      if(o.icon){
         o.hIcon = RBuilder.appendIcon(hc, o.icon);
      }
      o.hText = RBuilder.appendText(hc, '&nbsp;&nbsp;' + o.label);
      o.hText.style.fontWeight='BOLD';
   }else if(RString.equals(o.dispStyle, ESplitStyle.BulgeLine)){
      var h = this.hForm.insertRow().insertCell();
      h.style.borderBottom  = '1px solid #666666';
      h.style.borderTop  = '1px solid #DDDDDD';
      h.height = 2;
   }else if(RString.equals(o.dispStyle, ESplitStyle.HollowLine)){
      var h = this.hForm.insertRow().insertCell();
      h.style.borderBottom  = '1px solid #DDDDDD';
      h.style.borderTop  = '1px solid #666666';
      h.height = 2;
   }
   return EEventStatus.Stop;
}
function FSplit_oeMode(e){
   var o = this;
   var r = o.base.FControl.oeMode.call(o, e);
   o.base.MDisplay.oeMode.call(o, e);
   o.extend(o.editExtend);
   return r;
}
function FSplit_construct(){
   var o = this;
   o.__lines = new TList();
}
function FSplit_extend(v){
   var o = this;
   if(EMode.Design == o._emode){
      return;
   }
   if(o.extended == v){
      return;
   }
   o.extended = v;
   if(o.hImage){
      o.hImage.src = v ? RResource.iconPath(o.iconMinus) : RRes.iconPath(o.iconPlus);
   }
   var c = o.__lines.count;
   for(var n=0; n<c; n++){
      o.__lines.get(n).style.display = v ? 'block' : 'none';
   }
   o.topControl().topResize(o);
}
function FSplit_pushLine(hr){
   this.__lines.push(hr);
}
function FSplit_dispose(){
   var o = this;
   o.base.FControl.dispose.call(o);
   if(o.__lines){
      o.__lines.release();
      o.__lines = null;
   }
   o.hForm = null;
   o.hText = null;
   o.hIcon = null;
   o.hImage = null;
}
function FTemplate(o){
   o = RClass.inherits(this, o, FControl, MDisplayAble, MDesign, MHorizontal);
   o.icon         = RClass.register(o, new TPtyStr('icon'));
   o.styleTitle   = RClass.register(o, new TStyle('Title'));
   o.extended     = true;
   o.hImage       = null;
   o.hIcon        = null;
   o.hText        = null;
   o.oeBuild      = FTemplate_oeBuild;
   o.onBuildPanel = FTemplate_onBuildPanel;
   return o;
}
function FTemplate_oeBuild(event){
   var o = this;
   o.base.FControl.oeBuild.call(o, event);
   var h = o.hForm.insertRow().insertCell();
   h.className = o.style('Title');
   if(o.icon){
      o.hIcon = RBuilder.appendIcon(h, o.icon);
   }
   o.hText = RBuilder.appendText(h, '&nbsp;' + this.label);
   return EEventStatus.Stop;
}
function FTemplate_onBuildPanel(){
   var o = this;
   o.hPanel = RBuilder.create(null, 'DIV');
   o.hForm = RBuilder.appendTable(this.hPanel);
   o.hForm.width = '100%';
}
function FTime(o){
   o = RClass.inherits(this, o, FEditControl, MEditBorder, MDropable);
   o.editHour     = RClass.register(o, new TPtyBoolSet('editHour', 'editDate', EDateTimeMode.Hour));
   o.editMinute   = RClass.register(o, new TPtyBoolSet('editMinute', 'editDate', EDateTimeMode.Minute));
   o.editSecond   = RClass.register(o, new TPtyBoolSet('editSecond', 'editDate', EDateTimeMode.Second));
   o._date        = null;
   o.borderStyle  = EBorder.RoundDrop;
   o.lsnEditEnd   = null;
   o.hHourPanel   = null;
   o.hHour        = null;
   o.hMinutePanel = null;
   o.hMinute      = null;
   o.hSecondPanel = null;
   o.hSecond      = null;
   o.onKeyPress   = FTime_onKeyPress;
   o.onEditEnd    = FTime_onEditEnd;
   o.onBuildEdit  = FTime_onBuildEdit;
   o.oeSaveValue  = FTime_oeSaveValue;
   o.construct    = FTime_construct;
   o.formatValue  = FTime_formatValue;
   o.validText    = FTime_validText;
   o.text         = FTime_text;
   o.setText      = FTime_setText;
   o.setEditable  = FTime_setEditable;
   o.refreshStyle = FTime_refreshStyle;
   o.drop         = FTime_drop;
   o.dispose      = FTime_dispose;
   return o;
}
function FTime_onKeyPress(e){
   if(!RString.inChars(String.fromCharCode(e.keyCode), RDate.Chars)){
      RKey.eventClear(e);
   }
}
function FTime_onEditEnd(e){
   var o = this;
   if(e){
      o.set(e.get());
   }
   o.onDataEditEnd(o);
}
function FTime_onBuildEdit(b){
   var o = this;
   var htb = RBuilder.appendTable(b.hPanel);
   htb.width = '100%';
   htb.style.tableLayout = 'fixed';
   var hr = o.hEdit = htb.insertRow();
   o.onBuildChange(hr.insertCell())
   var hc = o.hHourPanel = hr.insertCell();
   hc.style.padding = '0 1'
   var he = o.hHour = RBuilder.appendEdit(hc);
   he.maxLength = 2;
   he.style.border = 0;
   he.style.width = '100%';
   he.style.textAlign = 'right';
   var hc = o.hHourSplit = hr.insertCell();
   hc.width = 5;
   hc.innerText = ':';
   o.hHour.style.display = o.editHour?'block':'none';
   o.hHourSplit.style.display = o.editHour?'block':'none';
   var hc = o.hMinutePanel = hr.insertCell();
   hc.style.padding = '0 1'
   var he = o.hMinute = RBuilder.appendEdit(hc);
   he.maxLength = 2;
   he.style.border = 0;
   he.style.width = '100%';
   he.style.textAlign = 'right';
   var hc = o.hSecondSplit = hr.insertCell();
   hc.width = 5;
   hc.innerText = ':';
   o.hMinute.style.display = o.editMinute?'block':'none';
   o.hSecondSplit.style.display = o.editSecond?'block':'none';
   var hc = o.hSecondPanel = hr.insertCell();
   hc.style.padding = '0 1'
   var he = o.hSecond = RBuilder.appendEdit(hc);
   he.maxLength = 2;
   he.style.border = 0;
   he.style.width = '100%';
   he.style.textAlign = 'right';
   o.hSecond.style.display = o.editSecond?'block':'none';
}
function FTime_oeSaveValue(e){
   var o = this;
   var dn = RString.nvl(o.dataCode, o.dataName);
   if(!RString.isEmpty(dn)){
      var vs = e.values;
      var v = vs.get(dn);
      if(v){
         vs.set(dn, v.substring(0, 8) + o.reget().substring(8));
      }else{
         vs.set(dn, o.reget());
      }
   }
   return EEventStatus.Stop;
}
function FTime_construct(){
   var o = this;
   o.base.FEditControl.construct.call(o);
   o._date = new TDate();
   o.lsnEditEnd = new TListener(o, o.onEditEnd);
}
function FTime_formatValue(t){
   if(t){
      var o = this;
      if(t.toLowerCase() == '@now'){
         o._date.now();
         return RDate.formatDate(o._date);
      }else{
         RDate.autoParse(o._date, t);
         return RDate.formatDate(o._date);
      }
   }
   return RString.nvl(t);
}
function FTime_text(){
   var o = this;
   o._date.setHour(o._date.hour);
   o._date.setMinute(o._date.minute);
   o._date.setSecond(o._date.second);
   return RDate.formatDate(o._date);
}
function FTime_setText(t){
   var o = this;
   if(t){
      RDate.autoParse(o._date, t);
      o.hHour.value = RInteger.format(o._date.hour, 2);
      o.hMinute.value = RInteger.format(o._date.minute, 2);
      o.hSecond.value = RInteger.format(o._date.second, 2);
   }else{
      o.hHour.value = '';
      o.hMinute.value = '';
      o.hSecond.value = '';
   }
}
function FTime_validText(v){
   return null;
}
function FTime_setEditable(v){
   var o = this;
   o.base.FEditControl.setEditable.call(o, v);
   o.hHour.readOnly = !v;
   o.hMinute.readOnly = !v;
   o.hSecond.readOnly = !v;
}
function FTime_refreshStyle(){
   var o = this;
   o.base.FEditControl.refreshStyle.call(o);
   o.hHour.style.color = o._textColor;
   o.hHour.style.backgroundColor = o._backColor;
   o.hMinute.style.color = o._textColor;
   o.hMinute.style.backgroundColor = o._backColor;
   o.hSecond.style.color = o._textColor;
   o.hSecond.style.backgroundColor = o._backColor;
}
function FTime_drop(){
   var o = this;
   if(o.canDrop() && o.canEdit){
      var e = o.editor = RConsole.find(FEditConsole).focus(o, FTimeEditor, o.editRefer);
      e.set(RDate.formatDate(o._date));
      e.setHourVisible(o.editHour);
      e.setMinuteVisible(o.editMinute);
      e.setSecondVisible(o.editSecond);
      e.lsnEditEnd = o.lsnEditEnd;
      e.show();
   }
}
function FTime_dispose(){
   var o = this;
   o.base.FEditControl.dispose.call(o);
   o._date = null;
}
function FTimeEditor(o){
   o = RClass.inherits(this, o, FDropEditor);
   o.date              = null;
   o.hours             = null;
   o.minutes           = null;
   o.seconds           = null;
   o.hPanelHour        = null;
   o.hPanelMinute      = null;
   o.hPanelSecond      = null;
   o.hTitleHour        = null;
   o.hTitleMinute      = null;
   o.hTitleSecond      = null;
   o.onButtonEnter     = RClass.register(o, new HMouseEnter('onButtonEnter'), FTimeEditor_onButtonEnter);
   o.onButtonLeave     = RClass.register(o, new HMouseLeave('onButtonLeave'), FTimeEditor_onButtonLeave);
   o.onHourClick       = RClass.register(o, new HMouseDown('onHourClick'), FTimeEditor_onHourClick);
   o.onMinuteClick     = RClass.register(o, new HMouseDown('onMinuteClick'), FTimeEditor_onMinuteClick);
   o.onSecondClick     = RClass.register(o, new HMouseDown('onSecondClick'), FTimeEditor_onSecondClick);
   o.onTimeDoubleClick = RClass.register(o, new HDoubleClick('onTimeDoubleClick'), FTimeEditor_onTimeDoubleClick);
   o.onNowClick        = RClass.register(o, new HMouseDown('onNowClick'), FTimeEditor_onNowClick);
   o.onConfirmClick    = RClass.register(o, new HMouseDown('onConfirmClick'), FTimeEditor_onConfirmClick);
   o.onBuildDrop       = FTimeEditor_onBuildDrop;
   o.onBuildButton     = FTimeEditor_onBuildButton;
   o.construct         = FTimeEditor_construct;
   o.buildTitle        = FTimeEditor_buildTitle;
   o.get               = FTimeEditor_get;
   o.set               = FTimeEditor_set;
   o.setHourVisible    = FDateEditor_setHourVisible;
   o.setMinuteVisible    = FDateEditor_setMinuteVisible;
   o.setSecondVisible    = FDateEditor_setSecondVisible;
   o.selectCell        = FTimeEditor_selectCell;
   o.restore           = FTimeEditor_restore;
   o.show              = FTimeEditor_show;
   o.dispose           = FTimeEditor_dispose;
   return o;
}
function FTimeEditor_onButtonEnter(e){
   if(!e.hSource.isSelect){
      e.hSource.style.backgroundColor = '#CCCCFF';
   }
}
function FTimeEditor_onButtonLeave(e){
   if(!e.hSource.isSelect){
      e.hSource.style.backgroundColor = '#FFFFFF';
   }
}
function FTimeEditor_onHourClick(e){
   var o = this;
   o.date.setHour(e.hSource.innerText);
   o.restore();
}
function FTimeEditor_onMinuteClick(e){
   var o = this;
   o.date.setMinute(e.hSource.innerText);
   o.restore();
}
function FTimeEditor_onSecondClick(e){
   var o = this;
   o.date.setSecond(e.hSource.innerText);
   o.restore();
}
function FTimeEditor_onTimeDoubleClick(){
   this.onConfirmClick();
}
function FTimeEditor_onNowClick(){
   var o = this;
   o.date = new TDate();
   o.editEnd();
}
function FTimeEditor_onConfirmClick(){
   var o = this;
   o.date.setHour(o.hHour.value);
   o.date.setMinute(o.hMinute.value);
   o.date.setSecond(o.hSecond.value);
   o.editEnd();
}
function FTimeEditor_onBuildDrop(){
   var o = this;
   var hdp = o.hDropPanel;
   hdp.width = 220;
   o.attachEvent('onTimeDoubleClick', hdp);
   o.hTitleHour = o.buildTitle('Hour', 4);
   var hp = o.hPanelHour = o.hSelectPanel = RBuilder.appendTable(hdp);
   hp.width = '100%';
   var hr = hp.insertRow();
   var hc = hr.insertCell();
   hc.innerText = 'AM';
   hc.style.padding = '0 14';
   hc.style.borderRight = '1 solid #EEEEEE';
   hc.style.borderBottom = '1 solid #EEEEEE';
   hc.rowSpan = 2;
   for(var m=0; m<2; m++){
      if(m){
         hr = hp.insertRow();
      }
      for(var n=0; n<6; n++){
         var hc = hr.insertCell();
         hc.innerText = RInteger.format(6*m+n, 2);
         hc.align = 'center';
         hc.style.padding = '1 6';
         hc.style.cursor = 'hand';
         hc.style.borderBottom = '1 solid #EEEEEE';
         if(n < 5){
            hc.style.borderRight = '1 solid #EEEEEE';
         }
         o.attachEvent('onButtonEnter', hc);
         o.attachEvent('onButtonLeave', hc);
         o.attachEvent('onHourClick', hc);
         o.hours.push(hc);
      }
   }
   var hr = hp.insertRow();
   var hc = hr.insertCell();
   hc.innerText = 'PM';
   hc.style.padding = '0 14';
   hc.style.borderRight = '1 solid #EEEEEE';
   hc.style.borderBottom = '1 solid #EEEEEE';
   hc.rowSpan = 2;
   for(var m=0; m<2; m++){
      if(m){
         hr = hp.insertRow();
      }
      for(var n=0; n<6; n++){
         var hc = hr.insertCell();
         hc.innerText = 6*m + n + 12;
         hc.align = 'center';
         hc.style.padding = '1 6';
         hc.style.cursor = 'hand';
         hc.style.borderBottom = '1 solid #EEEEEE';
         if(n < 5){
            hc.style.borderRight = '1 solid #EEEEEE';
         }
         o.attachEvent('onButtonEnter', hc);
         o.attachEvent('onButtonLeave', hc);
         o.attachEvent('onHourClick', hc);
         o.hours.push(hc);
      }
   }
   o.hTitleMinute = o.buildTitle('Minute', 2);
   var hp = o.hPanelMinute = o.hSelectPanel = RBuilder.appendTable(hdp);
   hp.width = '100%';
   var hr = hp.insertRow();
   for(var m=0; m<2; m++){
      if(m){
         hr = hp.insertRow();
      }
      for(var n=0; n<6; n++){
         var hc = hr.insertCell();
         hc.innerText = RInteger.format(30*m+5*n, 2);
         hc.align = 'center';
         hc.style.cursor = 'hand';
         hc.style.borderBottom = '1 solid #EEEEEE';
         if(n < 5){
            hc.style.borderRight = '1 solid #EEEEEE';
         }
         o.attachEvent('onButtonEnter', hc);
         o.attachEvent('onButtonLeave', hc);
         o.attachEvent('onMinuteClick', hc);
         o.minutes.push(hc);
      }
   }
   o.hTitleSecond = o.buildTitle('Second', 2);
   var hp = o.hPanelSecond = o.hSelectPanel = RBuilder.appendTable(hdp);
   hp.width = '100%';
   var hr = hp.insertRow();
   for(var m=0; m<2; m++){
      if(m){
         hr = hp.insertRow();
      }
      for(var n=0; n<6; n++){
         var hc = hr.insertCell();
         hc.innerText = RInteger.format(30*m+5*n, 2);
         hc.align = 'center';
         hc.style.borderBottom = '1 solid #EEEEEE';
         hc.style.cursor = 'hand';
         if(n < 5){
            hc.style.borderRight = '1 solid #EEEEEE';
         }
         o.attachEvent('onButtonEnter', hc);
         o.attachEvent('onButtonLeave', hc);
         o.attachEvent('onSecondClick', hc);
         o.seconds.push(hc);
      }
   }
}
function FTimeEditor_onBuildButton(){
   var o = this;
   o.base.FDropEditor.onBuildButton.call(o);
   var hf = RBuilder.appendTable(o.hButtonPanel);
   hf.width = '100%';
   hf.height = 20;
   hf.style.filter = "progid:DXImageTransform.Microsoft.Gradient(startColorStr='#EEEEEE', endColorStr='#FFFFFF', gradientType='0')";
   var hr = hf.insertRow();
   var hc = hr.insertCell();
   hc.style.padding = '0 6';
   var h = o.hNow = RBuilder.append(hc, 'SPAN');
   h.style.cursor = 'hand';
   o.attachEvent('onNowClick', h);
   h.innerText = RContext.get('FTime:Now');
   var hc = hr.insertCell();
   hc.style.padding = '0 6';
   hc.align = 'right';
   var h = o.hNow = RBuilder.append(hc, 'SPAN');
   h.style.cursor = 'hand';
   o.attachEvent('onConfirmClick', h);
   h.innerText = RContext.get('FTime:Confirm');
}
function FTimeEditor_construct(){
   var o = this;
   o.base.FDropEditor.construct.call(o);
   o.date = new TDate();
   o.hours = new TList();
   o.minutes = new TList();
   o.seconds = new TList();
}
function FTimeEditor_buildTitle(n, ml){
   var o = this;
   var hf = RBuilder.appendTable(o.hDropPanel);
   hf.width = '100%';
   hf.style.borderBottom = '1 solid #999999';
   hf.style.filter = "progid:DXImageTransform.Microsoft.Gradient(startColorStr='#FFFFFF', endColorStr='#E5FAFE', gradientType='0')";
   hf.style.backgroundColor = '#F8F8F8';
   hf.style.padding = '2 6';
   var hr = hf.insertRow();
   var hc = hr.insertCell();
   hc.width = 60;
   var he = o['h' + n] = RBuilder.appendEdit(hc);
   he.style.width = '100%';
   he.style.textAlign = 'right';
   he.style.border = '1 solid #CCCCCC';
   he.maxLength = ml;
   var hc = hr.insertCell();
   hc.innerText = RContext.get('FTime:' + n);
   return hf;
}
function FTimeEditor_get(){
   return RDate.formatDate(this.date);
}
function FTimeEditor_set(v){
   var o = this;
   RDate.autoParse(o.date, v);
   o.restore();
}
function FDateEditor_setHourVisible(v){
   var o = this;
   o.hPanelHour.style.display = v? 'block':'none';
   o.hTitleHour.style.display = v? 'block':'none';
}
function FDateEditor_setMinuteVisible(v){
   var o = this;
   o.hPanelMinute.style.display = v? 'block':'none';
   o.hTitleMinute.style.display = v? 'block':'none';
}
function FDateEditor_setSecondVisible(v){
   var o = this;
   o.hPanelSecond.style.display = v? 'block':'none';
   o.hTitleSecond.style.display = v? 'block':'none';
}
function FTimeEditor_show(v){
   var o = this;
   o.base.FDropEditor.show.call(o, v);
   var hp = o.hPanel;
   var hbf = o.hBorderForm;
   var s = o.source;
   var r = s.getEditRange();
   hp.style.pixelLeft = r.x;
   hp.style.pixelTop = r.y + r.height;
   hp.style.pixelWidth = 220;
   o.base.MShadow.show.call(o);
}
function FTimeEditor_selectCell(ls, v){
   var c = ls.count;
   for(var n=0; n<c; n++){
      var h = ls.get(n);
      if(h.innerText == v){
         h.style.color = '#FFFFFF';
         h.style.backgroundColor = '#9999EE';
         h.isSelect = true;
      }else{
         h.style.color = '#000000';
         h.style.backgroundColor = '#FFFFFF';
         h.isSelect = false;
      }
   }
}
function FTimeEditor_restore(){
   var o = this;
   o.hHour.value = o.date.hour;
   o.hMinute.value = o.date.minute;
   o.hSecond.value = o.date.second;
   o.selectCell(o.hours, o.date.hour);
   o.selectCell(o.minutes, o.date.minute);
   o.selectCell(o.seconds, o.date.second);
}
function FTimeEditor_dispose(){
   var o = this;
   o.base.FDropEditor.dispose.call(o);
   o.hPanel = null;
}
function FTimeSpanPicker(o){
   o = RClass.inherits(this, o, FEditControl, MEditBorder);
   o.editComplete    = RClass.register(o, new TPtyStr('editComplete'));
   o.editCase        = RClass.register(o, new TPtyStr('editCase'));
   o.editPattern     = RClass.register(o, new TPtyStr('editPattern'));
   o.editLength      = RClass.register(o, new TPtyInt('editLength'));
   o.validLenmin     = RClass.register(o, new TPtyStr('validLenmin'));
   o.validLenmax     = RClass.register(o, new TPtyStr('validLenmax'));
   o.editOverflow    = RClass.register(o, new TPtyStr('editOverflow'));
   o.rows            = new TList();
   o.checks          = new TList();
   o.borderStyle     = EBorder.Round;
   o.amSelectAll     = null;
   o.pmSelectAll     = null;
   o.hAm             = null;
   o.hPm             = null;
   o.onBuildEdit     = FTimeSpanPicker_onBuildEdit;
   o.onClick         = FTimeSpanPicker_onClick;
   o.onMouseOver     = FTimeSpanPicker_onMouseOver;
   o.onMouseOut      = FTimeSpanPicker_onMouseOut;
   o.onBuildControl  = FTimeSpanPicker_onBuildControl;
   o.formatValue     = FTimeSpanPicker_formatValue;
   o.clearValue      = FTimeSpanPicker_clearValue;
   o.resetValue      = FTimeSpanPicker_resetValue;
   o.text            = FTimeSpanPicker_text;
   o.setText         = FTimeSpanPicker_setText;
   o.dispose         = FTimeSpanPicker_dispose;
   return o;
}
function FTimeSpanPicker_onBuildEdit(b){
   var o = this;
   var h  = RBuilder.appendTable(b.hPanel);
   h.width = '100%';
   h.style.backgroundColor = 'white';
   h.cellPadding = 2;
   var hr1 = h.insertRow();
   var h1 = hr1.insertCell();
   h1.style.filter = "progid:DXImageTransform.Microsoft.Gradient(startColorStr='#E5FAFE', endColorStr='#FFFFFF', gradientType='0')";
   h1.height = 20;
   h1.innerHTML = '<b>上午</b>';
   h1.align = 'center';
   var h2 = hr1.insertCell();
   h2.innerHTML = '<b>下午</b>';
   h2.style.filter = "progid:DXImageTransform.Microsoft.Gradient(startColorStr='#E5FAFE', endColorStr='#FFFFFF', gradientType='0')";
   h2.align = 'center';
   var hr2 = h.insertRow();
   for(var n = 0; n < 2; n++){
      var hc00 = hr2.insertCell();
      hc00.style.backgroundColor = '#C9F5FE';
      hc00.style.borderTop = '1 solid #62E4FE';
      hc00.style.borderBottom = '1 solid #62E4FE';
      var hr2tb = RBuilder.appendTable(hc00);
      hr2tb.width = '100%';
      hr2tb.border = "0px";
      var hr2r = hr2tb.insertRow();
      var hc0 = hr2r.insertCell();
      hc0.align = 'center';
      hc0.width = 25;
      if (0 == n) {
         o.amSelectAll = RBuilder.appendCheck(hc0);
         o.amSelectAll.style.cursor = 'hand';
         o.amSelectAll.style.border = '0px solid red';;
      }else if(1 == n) {
    	 o.pmSelectAll = RBuilder.appendCheck(hc0);
    	 o.pmSelectAll.style.cursor = 'hand';
    	 o.pmSelectAll.style.border = '0px solid red';;
      }
      var hc1 = hr2r.insertCell();
      hc1.width = 25;
      hc1.align = 'center';
      hc1.innerText = '时间';
      var hc2 = hr2r.insertCell();
      hc2.width = 25;
      hc2.innerText = '15';
      hc2.align = 'center';
      var hc3 = hr2r.insertCell();
      hc3.width = 25;
      hc3.innerText = '30';
      hc3.align = 'center';
      var hc4 = hr2r.insertCell();
      hc4.innerText = '45';
      hc4.width = 25;
      hc4.align = 'center';
      var hc5 = hr2r.insertCell();
      hc5.innerText = '60';
      hc5.width = 25;
      hc5.align = 'center';
   }
   var hr3 = h.insertRow();
   var hcc1 = hr3.insertCell();
   hcc1.vAlign = 'top';
   var hcc2 = hr3.insertCell();
   var hTb1 = o.hAm = RBuilder.appendTable(hcc1);
   hTb1.height = '100%'
   hTb1.border = 1;
   hTb1.style.borderCollapse = 'collapse';
   hTb1.borderColorDark ="#ECE9D8";
   hTb1.width = '100%'
   hTb1.style.cellspacing = "10";
   var hTb2 = o.hPm =RBuilder.appendTable(hcc2);
   hTb2.width = '100%'
   hTb2.border = 1;
   hTb2.style.borderCollapse = 'collapse';
   hTb2.borderColorDark ="#ECE9D8";
   var htt = hTb1;
   for(var n = 0; n < 12; n++){
      if(n > 5){
         htt = hTb2;
      }
      var htr1 = htt.insertRow();
      htc1 = htr1.insertCell();
      htc1.height = 20;
      htc1.width = 20;
      htc1.align = 'center';
      htc2 = htr1.insertCell();
      htc2.width = '20';
      htc2.align = 'center';
      htc2.vAlign = 'middle';
      if( n < 4 ){
         var itt = document.createElement("INPUT");
         itt.type = 'checkbox';
         itt.style.height = '16';
         itt.style.border = '0px solid red';
         itt.style.cursor = 'hand';
         htc1.appendChild(itt);
         htc2.innerText = (n+9).toString()+"点";
         o.rows.push(htr1);
      }else if( n > 5){
         var itt = document.createElement("INPUT");
         itt.type = 'checkbox';
         itt.style.height = '16';
         itt.style.border = '0px solid red';
         itt.style.cursor = 'hand';
         htc1.appendChild(itt);
         o.rows.push(htr1);
         htc2.innerText = (n+7).toString()+"点";
      }
      for(var m = 0; m < 4; m++){
         var htc3 = htr1.insertCell();
         htc3.width = 20;
         htc3.align = 'center';
         if(!(n == 11 && m >1) && !(n == 4 || n == 5)){
            var ipt = document.createElement("INPUT");
            ipt.type = 'checkbox';
            ipt.style.border = '0px solid red';
            ipt.style.height = '16';
            htc3.appendChild(ipt);
            htc3.vAlign = 'middle';
            ipt.style.cursor = 'hand';
            o.checks.push(ipt);
         }
      }
   }
   o.imgSrc = o.styleIconPath('Checked', FTimeSpanPicker);
   var hc = h.insertRow().insertCell();
   hc.colSpan = 2;
   hc.style.filter = "progid:DXImageTransform.Microsoft.Gradient(startColorStr='#FFFFFF', endColorStr='#E5FAFE', gradientType='0')";
   o.hHint = RBuilder.append(hc, 'textarea');
   o.hHint.style.border = '0px solid white';
   o.hHint.style.backgroundColor = 'transparent';
   o.hHint.style.height = '30';
   o.hHint.style.width = '100%';
   o.hHint.readOnly = true;
   o.hEdit = document.createElement('Img');
   o.hEdit.style.display = 'none';
}
function FTimeSpanPicker_onClick(e){
   var o = this;
   var h = e.srcElement;
   if(h == o.amSelectAll || h == o.pmSelectAll){
	  if (h == o.amSelectAll) {
		  var hpTb = o.hAm.childNodes[0];
	  } else if(h == o.pmSelectAll) {
		  var hpTb = o.hPm.childNodes[0];
	  }
	  for(var i = 0; i < hpTb.childNodes.length; i++){
		  var hpTr = hpTb.childNodes[i];
    	  for(var n = 0; n < hpTr.childNodes.length; n++){
		      var htd = hpTr.childNodes[n];
		      if(htd.childNodes.length > 0){
		         var hCk = htd.childNodes[0];
		         if (hCk.nodeName == 'INPUT') {
		            hCk.checked = h.checked;
		         }
		      }
		   }
      }
   }else {
   	  if(h.nodeName == 'INPUT'){
	      var hpTd = h.parentNode;
	      var hpTr = hpTd.parentNode;
	      if(hpTd.cellIndex == 0){
	         for(var n = 2; n < hpTr.childNodes.length; n++){
	            var htd = hpTr.childNodes[n];
	            if(htd.childNodes.length > 0){
	               var hCk = htd.childNodes[0];
	               hCk.checked = h.checked;
	            }
	         }
	      }else{
	         var flag = true;
	         for(var n = 2; n < hpTr.childNodes.length; n++){
	            var hpTd = hpTr.childNodes[n];
	            if(hpTd.childNodes.length > 0){
	               var hCk = hpTr.childNodes[n].childNodes[0];
	               if(hCk.style.display != 'none'){
	                  if(!hCk.checked){
	                     flag = false;
	                     break;
	                  }
	               }
	            }
	         }
	         hpTr.childNodes[0].childNodes[0].checked = flag;
	      }
   	  }
   }
   var s = o.text();
   var count = s.length;
   var hint = "";
   var hour = 9;
   var hourStr = '';
   var start = false;
   var min = 0;
   var minStr = '';
   for(var n = 0; n < count; n++){
     min =  n%4 * 15;
     minStr = RString.lpad(min,2,'0');
      if(n%4 == 0 && n != 0){
       hour++;
      }
      hourStr = RString.lpad(hour,2,'0');
      if(!start && s.charAt(n) == 0){
         continue;
      }else if(!start && s.charAt(n) == 1){
        start = true;
        hint = hint + hourStr+":"+ minStr;
      }else if(start && s.charAt(n) == 0){
         start = false;
         hint = hint + " - "+hourStr+":"+ minStr+"      ";
      }
      if(n == count -1&&s.charAt(n) == 1){
        hint = hint + " - "+hourStr+":"+ (min+15)%60;
      }
   }
   o.hHint.value = hint;
}
function FTimeSpanPicker_onMouseOver(e){
   var o = this;
}
function FTimeSpanPicker_onMouseOut(e){
   var o = this;
}
function FTimeSpanPicker_onBuildControl(){
   var o = this;
   o.base.FEditControl.onBuildControl.call(o);
}
function FTimeSpanPicker_formatValue(s){
   return this.text();
}
function FTimeSpanPicker_clearValue(){
   var o = this;
   var cs = o.checks;
   for(var n=cs.count-1; n>=0; n--){
      cs.get(n).checked = false;
   }
}
function FTimeSpanPicker_resetValue(){
   var o = this;
   var cs = o.checks;
   for(var n=cs.count-1; n>=0; n--){
      cs.get(n).checked = false;
   }
}
function FTimeSpanPicker_text(){
   var o = this;
   var s = '';
   var cs = o.checks;
   for(var n = 0; n < cs.count; n++){
      var c = cs.get(n);
      if('none' == c.style.display){
         s = s.concat('0');
      }else if('block' == c.style.display){
         var ch = c.checked ?  '1' : '0';
         s = s.concat(ch);
      }
   }
   return s;
}
function FTimeSpanPicker_setText(text){
   var o = this;
   o.hHint.value = '';
   if(o.checks.count != text.length){
      alert(o.checks.count +"!="+ text.length + '[' + text + ']');
   }
   var cs = o.checks;
   var ct = cs.count;
   for(var n = 0; n < ct; n++){
      var flag = RString.equals(text.charAt(n), '1');
      var c = cs.get(n);
      if(flag){
         c.style.display = 'none';
         var hImg = c.nextSibling;
         if(!hImg){
            hImg = document.createElement('Img');
            hImg.src = o.imgSrc;
            c.parentNode.appendChild(hImg);
         }
         hImg.style.display = 'block';
      }else{
         c.style.display = 'block';
         c.checked = false;
         var hImg = c.nextSibling;
         if(!hImg){
            hImg = document.createElement('Img');
            hImg.src = o.imgSrc;
            c.parentNode.appendChild(hImg);
         }
         hImg.style.display = 'none';
      }
   }
   var cs = o.rows;
   var ct = cs.count;
   for(var n = 0; n < ct; n++){
      var hpTr = cs.get(n);
      var flag = true;
      var len = hpTr.childNodes.length;
      for(var m = 2; m < len; m++){
         if(hpTr.childNodes.length > 0){
            var htd = hpTr.childNodes[m];
            if(htd.childNodes.length > 0){
               var hCk = htd.childNodes[0];
               if('block' == hCk.style.display){
                  flag = false;
                  break;
               }
            }
         }
      }
      var ds = flag ?'none':'block';
      var hkk = hpTr.childNodes[0].childNodes[0];
      hkk.style.display = ds;
      hkk.checked = false;
   }
}
function FTimeSpanPicker_dispose(){
   var o = this;
   o.base.FEditControl.dispose.call(o);
   RMemory.freeHtml(o.hHint);
   RMemory.freeHtml(o.hEdit);
   o.hHint = null;
   o.hEdit = null;
}
function FUploadItem(o){
   o = RClass.inherits(this, o, FControl);
   o.onFrameLoad      = RClass.register(o, new HReadyStateChange('onFrameLoad'), FUploadItem_onFrameLoad);
   o.page             = null;
   o.type             = null;
   o.viewer           = null;
   o.fileName         = null;
   o.uploadStatus     = EUploadStatus.Local;
   o.uploaded         = null;
   o.hForm            = null;
   o.hFile            = null;
   o.path             = null;
   o.id               = null;
   o.dsName           = null;
   o.lsnsFileSelected = new TListeners();
   o.lsnsUploaded     = new TListeners();
   o.oeBuild          = FUploadItem_oeBuild;
   o.ohFileSelected   = FUploadItem_ohFileSelected;
   o.onFrameLoad      = FUploadItem_onFrameLoad;
   o.onFileUploaded   = FUploadItem_onFileUploaded;
   o.buildDialog      = FUploadItem_buildDialog;
   o.showDialog       = FUploadItem_showDialog;
   o.upload           = FUploadItem_upload;
   return o;
}
function FUploadItem_oeBuild(e){
   var o = this;
   var b = e.builder;
   var hp = o.hPanel = b.create('TR');
   o.hFileName = b.append(hp, 'TD');
   o.hFileName.innerText = o.fileName;
   o.hStatus = b.append(hp, 'TD');
   o.hStatus.innerText = '未传输';
   o.hCommand = b.append(hp, 'TD');
   o.hCommand.innerText = '删除';
   return EEventStatus.Stop;
}
function FUploadItem_ohFileSelected(){
   var o = RHtml.findLink(this, 'control');
   var fn = o.hFile.value;
   o.fileName = o.hFile.value;
   if(fn && fn.length > 0){
      o.lsnsFileSelected.process(o);
   }
}
function FUploadItem_onFrameLoad(e, a, b){
   var o = this;
   var hf = o.hFrame;
   if((EUploadStatus.Local == o.uploadStatus) && ('complete' == hf.readyState) && !o.hFile){
      var hw = hf.contentWindow;
      var hd = hw.document;
      var hb = hd.body;
      var s = new TString();
      s.append("<FORM method='post' enctype='multipart/form-data'>");
      s.append("<INPUT name='store_type' type='text'>");
      s.append("<INPUT name='store_code' type='text'>");
      s.append("<INPUT name='store_name' type='text'>");
      s.append("<INPUT name='store_id' type='text'>");
      s.append("<INPUT name='path' type='text'>");
      s.append("<INPUT name='upload' type='file'>");
      s.append("</FORM>");
      hb.innerHTML = s.toString();
      o.hForm = hd.forms[0];
      o.hForm.action = o.page;
      o.hStoreType = o.hForm.children[0];
      o.hStoreCode = o.hForm.children[1];
      o.hStoreName = o.hForm.children[2];
      o.hStoreId = o.hForm.children[3];
      o.hPath = o.hForm.children[4];
      o.hUpload  = o.hForm.children[5];
      o.hUpload.onchange = o.ohFileSelected;
      RHtml.link(o.hUoload, 'control', o);
      RConsole.find(FEventConsole).register(o, o.showDialog);
   }
}
function FUploadItem_buildDialog(){
   var o = this;
   var hd = document.createElement('DIV');
   hd.style.display = 'none';
   RWindow.appendElement(hd);
   hd.innerHTML = "<IFRAME></IFRAME>";
   var hf = o.hFrame = hd.children[0];
   o.attachEvent('onFrameLoad', hf);
}
function FUploadItem_showDialog(){
   var o = this;
   if(o.hUpload){
      o.hUpload.click();
   }else{
      o.buildDialog();
   }
}
function FUploadItem_upload(){
   var o = this;
   if(EUploadStatus.Local != o.uploadStatus){
      return;
   }
   o.hStoreType.value = o.storeType;
   o.hStoreCode.value = o.storeCode;
   o.hStoreName.value = o.storeName;
   o.hStoreId.value = o.storeId;
   o.hPath.value = o.path;
   o.hForm.submit();
}
function FUploadItem_onFileUploaded(){
   var o = this;
   o.lsnsUploaded.process();
}
function FUploadView(o){
   o = RClass.inherits(this, o, FContainer);
   o.type           = null;
   o.page           = 'http://localhost:88/eUIS/apl/logic/transfer/Upload.wa';
   o.path           = null;
   o.id             = null;
   o.dsName         = null;
   o.perpareItem    = null;
   o.items          = new TList();
   o.hForm          = null;
   o.hMessages      = null;
   o.oeBuild        = FUploadView_oeBuild;
   o.onBuildPanel   = FUploadView_onBuildPanel;
   o.onFileSelected = FUploadView_onFileSelected;
   o.onFileUploaded = FUploadView_onFileUploaded;
   o.onClickAddFile = FUploadView_onClickAddFile;
   o.onClickUpload  = FUploadView_onClickUpload;
   o.isBuilded      = FUploadView_isBuilded;
   o.doSearch       = FUploadView_doSearch;
   o.selectRow      = FUploadView_selectRow;
   o.pushItem       = FUploadView_pushItem;
   return o;
}
function FUploadView_oeBuild(event){
   var o = this;
   o.base.FContainer.oeBuild.call(o, event);
   var hTab = RBuilder.appendTable(o.hPanel);
   hTab.width = '100%';
   hTab.height = '100%';
   var hRow = hTab.insertRow();
   var h = o.hTitlePanel = hRow.insertCell();
   h.height = 24;
   var tb = RClass.create(FToolBar);
   tb.width = '100%';
   var tbn = RClass.create(FToolButton);
   tbn.icon = 'ctl.FUploadView_Insert';
   tbn.label = '添加文件...'
   tbn.lsnsClick.register(o, o.onClickAddFile);
   tb.push(tbn);
   var tbn = RClass.create(FToolButtonSplit);
   tb.push(tbn);
   var tbn = RClass.create(FToolButton);
   tbn.icon = 'ctl.FUploadView_Upload';
   tbn.label = '上传文件'
   tbn.lsnsClick.register(o, o.onClickUpload);
   tb.push(tbn);
   tb.psBuild(h);
   o.hFieldsPanel = hTab.insertRow().insertCell();
   o.hFieldsPanel.vAlign = 'top';
   var ht = o.hItemsForm = RBuilder.appendTable(o.hFieldsPanel, null, 1, 1);
   ht.width = '100%';
   var hb = o.hItemsBody  = RBuilder.append(ht, 'TBODY');
   var hh = o.hItemsHead  = RBuilder.append(hb, 'TR');
   var hc = RBuilder.append(hh, 'TD');
   hc.innerText = '文件名称';
   hc.style.width='200px';
   var hc = RBuilder.append(hh, 'TD');
   hc.innerText = '传输状态';
   var hc = RBuilder.append(hh, 'TD');
   hc.innerText = '命令';
   return EEventStatus.Stop;
}
function FUploadView_onBuildPanel(){
   var o = this;
   o.hPanel = RBuilder.append(null, 'DIV');
   o.hPanel.style.zIndex = ELayer.Message;
}
function FUploadView_onFileSelected(ui){
   var o = this;
   var ui = o.perpareItem;
   o.perpareItem = null;
   o.pushItem(ui);
}
function FUploadView_onFileUploaded(s, uid){
   var o = this;
   for(var n=0; n<o.items.count; n++){
      var ui = o.items.get(n);
      if(ui.uploadUid == uid){
         ui.onFileUploaded();
      }
   }
}
function FUploadView_onClickAddFile(){
   var o = this;
   var ui = o.perpareItem;
   if(!o.perpareItem){
      ui = o.perpareItem = RClass.create(FUploadItem);
      ui.page = o.page;
      ui.path = o.path;
      ui.id = o.id;
      ui.dsName = o.dsName;
      ui.lsnsFileSelected.register(o, o.onFileSelected);
   }
   ui.showDialog();
}
function FUploadView_onClickUpload(){
   var o = this;
   var lc = top.RConsole.find(FListenerConsole);
   if(!lc.contains(FUploadView, 'onFileUploaded')){
      lc.register(FUploadView, 'onFileUploaded', o, o.onFileUploaded);
   }
   for(var n=0; n<o.items.count; n++){
      var ui = o.items.get(n);
      if(EUploadStatus.Local == ui.uploadStatus){
         ui.upload();
      }
   }
}
function FUploadView_doSearch(){
   var o = this;
   var cs = o.fieldsPanel.components;
   if(cs){
      var sn = new TNode('Search');
      for(var n=0; n<cs.count; n++){
         cs.value(n).saveSearch(sn);
      }
      RLog.debug(o, 'Search value {1}', sn.dump());
   }
   o.hide();
}
function FUploadView_selectRow(table, row){
   var o = this;
   var fields = o.lovControl.lovFields;
   var dsCtl = o.lovControl.topControl(MDataset);
   if(dsCtl && fields){
      if(!row){
         row = o.listView.selectRow;
      }
      if(row){
         var flds = RString.splitTwo(fields, ',');
         for(var n=0; n<flds.length; n++){
            var v = RString.splitTwo(flds[n], ' ');
            dsCtl.dsSet(RString.nvl(v[1], v[0]), row.get(v[0]));
         }
         dsCtl.loadValue(dsCtl.dsCurrent());
      }
   }
   o.hide();
}
function FUploadView_isBuilded(){
   return (null != this.listView);
}
function FUploadView_pushItem(ui){
   var o = this;
   o.items.push(ui);
   ui.psBuild();
   o.hItemsBody.appendChild(ui.hPanel);
}
function FWebDialog(o){
   o = RClass.inherits(this, o, FDialog, MTop);
   return o;
}
function FWebForm(o){
   o = RClass.inherits(this, o, FForm, MTop);
   o.onBuildPanel = FWebForm_onBuildPanel;
   return o;
}
function FWebForm_onBuildPanel(){
   var o = this;
   var hp = o.hPanel = RBuilder.newDiv();
   hp.width = '100%';
   hp.height = '100%';
   hp.style.padding = 8;
   var hf = o.hPanelForm = RBuilder.appendTable(hp);
   hf.width = '100%';
   hf.height = '100%';
   if(EMode.Design == o._emode){
      o.hContainer = hf.insertRow().insertCell();
   }
}
function FWebStyle(o){
   o = RClass.inherits(this, o, FForm, MTop);
   return o;
}
function FWebTemplate(o){
   o = RClass.inherits(this, o, FForm);
   return o;
}
function MEditBorder(o){
   o = RClass.inherits(this, o);
   o.editBorder         = null;
   o.borderStyle        = EBorder.None;
   o.onBuildEditBorder  = MEditBorder_onBuildEditBorder;
   o.setEditBorderStyle = MEditBorder_setEditBorderStyle;
   o.dispose            = MEditBorder_dispose;
   return o;
}
function MEditBorder_onBuildEditBorder(hp){
   var o = this;
   var b = o.editBorder = new TBorder(o.borderStyle);
   b.hParent = hp;
   RBorder.build(b);
   if(b.hDrop){
      b.hDrop.className = o.style('DropPanel');
   }
   o.hEditPanel  = b.hPanel;
   var h = o.hEditForm = b.hForm;
   h.className = o.style('EditForm');
   o.linkEvent(o, 'onDataEnter', h);
   o.linkEvent(o, 'onDataLeave', h);
   if(o.editWidth){
      h.width = o.editWidth;
   }
   if(o.editHeight){
      h.height = o.editHeight;
   }
}
function MEditBorder_setEditBorderStyle(s, c){
   var o = this;
   var b = o.editBorder;
   switch(b.style){
      case EBorder.Round:
      case EBorder.RoundDrop:
         if(EBorderStyle.Readonly == s){
            b.hTopLine.className = 'RBorder_TopReadony';
            b.hBeforeLine.className = 'RBorder_BeforeReadony';
            b.hFormLine.className = 'RBorder_PanelReadony';
            b.hAfterLine.className = 'RBorder_AfterReadony';
            b.hBottomLine.className = 'RBorder_BottomReadony';
            b.hPanel.style.backgroundColor = EColor.Readonly;
         }else{
            b.hTopLine.className = 'RBorder_Top';
            b.hBeforeLine.className = 'RBorder_Before';
            b.hFormLine.className = 'RBorder_Panel';
            b.hAfterLine.className = 'RBorder_After';
            b.hBottomLine.className = 'RBorder_Bottom';
            b.hPanel.style.backgroundColor = EColor.Edit;
         }
         break;
      case EBorder.RoundIcon:
         b.hIcon.style.backgroundColor = c;
         b.hPanel.style.backgroundColor = c;
         break;
   }
}
function MEditBorder_dispose(){
   var o = this;
   RMemory.freeHtml(o.hEditForm);
   o.hEditForm = null;
}
function EColumnModeFace(o){
   var o = this;
   o.None = 0;
   o.Size = 1;
   o.Drag = 2;
   return o;
}
var EColumnMode = new EColumnModeFace();
function EEditFormatFace(){
   var o = this;
   o.Html  = 'H';
   o.Text  = 'T';
   o.Upper = 'U';
   o.Lower = 'L';
   o.Word  = 'W';
   return o;
}
var EEditFormat = new EEditFormatFace();
function EGridActionFace(){
   var o = this;
   o.Insert      = 'I';
   o.Update      = 'U';
   o.Delete      = 'D';
   o.GoInsert    = 'GI';
   o.GoUpdate    = 'GU';
   o.GoDelete    = 'GD';
   o.RowClick    = 'RC';
   o.RowDblClick = 'RD';
   return o;
}
var EGridAction= new EGridActionFace();
function EGridDisplayFace(){
   var o = this;
   o.Title     = 'T';
   o.Head      = 'H';
   o.Search    = 'S';
   o.Total     = 'A';
   o.Navigator = 'N';
   return o;
}
var EGridDisplay = new EGridDisplayFace();
function ETableActionFace(){
   var o = this;
   o.Insert      = 'I';
   o.Update      = 'U';
   o.Delete      = 'D';
   o.GoInsert    = 'GI';
   o.GoUpdate    = 'GU';
   o.GoDelete    = 'GD';
   o.RowClick    = 'RC';
   o.RowDblClick = 'RD';
   return o;
}
var ETableAction= new ETableActionFace();
function ETableDisplayFace(){
   var o = this;
   o.Title     = 'T';
   o.Head      = 'H';
   o.Search    = 'S';
   o.Total     = 'A';
   o.Navigator = 'N';
   return o;
}
var ETableDisplay = new ETableDisplayFace();
function FBrowserControl(o) {
   o = RClass.inherits(this, o, FGridControl);
   o.fieldName         = RClass.register(o, new TPtyStr('field_name', 'name'));
   o.fieldLabel        = RClass.register(o, new TPtyStr('field_label', 'label'));
   o.fieldIcon         = RClass.register(o, new TPtyStr('field_icon', 'icon'));
   o.fieldGroup        = RClass.register(o, new TPtyStr('field_group', 'group'));
   o.fieldGroup        = 'list_label';
   o.dsPageSize        = 100;
   o.groupList         = new TList();
   o.groups            = new TMap();
   o.nodes             = new TList();
   o.lsnsRowClick      = new TListeners();
   o.lsnsRowDblClick   = new TListeners();
   o.onResizeAfter     = RMethod.empty;
   o.onBuildData       = RMethod.empty;
   o.onNodeClick       = RClass.register(o, new HClick('onNodeClick'), FBrowserControl_onNodeClick);
   o.onNodeDoubleClick = RClass.register(o, new HClick('onNodeDoubleClick'), FBrowserControl_onNodeDoubleClick);
   o.onLoadDataset     = FBrowserControl_onLoadDataset;
   o.oeBuild           = FBrowserControl_oeBuild;
   o.groupClear        = FBrowserControl_groupClear;
   o.groupSync         = FBrowserControl_groupSync;
   o.nodeSync          = FBrowserControl_nodeSync;
   o.pushColumn        = FBrowserControl_pushColumn;
   return o;
}
function FBrowserControl_onNodeClick(s, e){
}
function FBrowserControl_onNodeDoubleClick(s, e){
   var o = this;
   RConsole.find(FListenerConsole).process(FGridControl, EGridAction.RowClick, s, s);
   var e = o._eventRowClick;
   if(!e){
      e = o._eventRowClick = new TEvent();
      e.source = o;
   }
   e.caller = s;
   e.handle = 'onTableRowClick';
   RConsole.find(FFormConsole).processEvent(e);
}
function FBrowserControl_onLoadDataset(ds, da){
   var o = this;
   var c = ds.count;
   o.groupClear();
   if(o.fieldGroup){
      for(var n=0; n<c; n++){
         var r = ds.rows.get(n);
         var gn = r.get(o.fieldGroup);
         var g = o.groupSync(gn);
         g.setLabel(gn);
         g.nodeNext().loadValue(r);
      }
   }else{
      for(var n=0; n<c; n++){
         var r = ds.rows.get(n);
         var bn = o.nodeSync(n);
         bn.loadValue(r);
      }
   }
   return;
}
function FBrowserControl_oeBuild(e){
   var o = this;
   if(e.isBefore()){
      if(!o.height || o.height < 160){
         o.height = '100%';
      }
   }
   var r = o.base.FContainer.oeBuild.call(o, e);
   if(e.isBefore()){
      var hp = o.hPanel;
      var hpl = o.hPanel.insertRow();
      var b = o.border = new TBorder(EBorder.Round);
      b.hParent = hpl.insertCell();
      RBorder.build(b);
      var hbf = b.hForm;
      hbf.width = '100%';
      hbf.height = '100%';
      var hc = hpl.insertCell();
      hc.width = 1;
      var hd = o.hFixHeight = RBuilder.appendDiv(hc);
      hd.style.width = 1;
      hd.style.height = o._minHeight;
      var hbp = o.hBorderPanel = b.hPanel;
      hbp.className = o.style('BorderPanel');
      hbp.vAlign = 'top';
      hbp.style.overflow = 'auto';
      var hdp = o.hDataPanel = RBuilder.appendDiv(o.hBorderPanel);
      hdp.style.width = '100%';
      hdp.style.height = '100%';
      hdp.style.overflow = 'auto';
      hdp.style.backgroundColor = '#FFFFFF';
      var hnp = o.hNavigator = o.hPanel.insertRow().insertCell();
      hnp.height = 1;
      o.hHintForm = RBuilder.appendTable(hnp, o.style('HintForm'));
      o.onBuildHint(e);
   }
   return r;
}
function FBrowserControl_groupClear(){
   var o = this;
   var gs = o.groups;
   for(var n=gs.count-1; n>=0; n--){
      var g = gs.value(n);
      g.nodeClear();
      g.hide();
   }
   gs.clear();
}
function FBrowserControl_groupSync(n){
   var o = this;
   var gs = o.groups;
   var g = gs.get(n);
   if(!g){
      g = o.groupList.get(gs.count);
      if(!g){
         g = RControl.create(FBrowserGroup, o.hDataPanel);
         g.browser = o;
         o.groupList.push(g)
      }
      g.name = n;
      gs.set(n, g);
   }
   g.setVisible(true);
   return g;
}
function FBrowserControl_nodeSync(n){
   var o = this;
   var ps = o.nodes;
   var p = ps.get(n);
   if(!p){
      for(var i=ps.count; i<=n; i++){
         p = RControl.create(FBrowserNode, o.hDataPanel);
         p.browser = o;
         ps.push(p);
      }
   }
   p.setVisible(true);
   return p;
}
function FBrowserControl_pushColumn(c){
   var o = this;
   if(c.dispFixed){
      o.hFixHead.appendChild(c.hPanel);
      o.hFixSearch.appendChild(c.hSearchPanel);
      o.hFixTotal.appendChild(c.hTotalPanel);
      o.hFixRowLine.appendChild(c.hFixPanel);
   }else{
      o.hHead.appendChild(c.hPanel);
      o.hSearch.appendChild(c.hSearchPanel);
      o.hTotal.appendChild(c.hTotalPanel);
      o.hRowLine.appendChild(c.hFixPanel);
   }
   o.push(c);
}
function FBrowserGroup(o){
   o = RClass.inherits(this, o, FControl);
   o.nodeCount     = 0;
   o.nodes         = new TList();
   o.onClick       = FBrowserGroup_onClick;
   o.onDoubleClick = FBrowserGroup_onDoubleClick;
   o.onBuildPanel  = RBuilder.onBuildTablePanel;
   o.oeBuild       = FBrowserGroup_oeBuild;
   o.setLabel      = FBrowserGroup_setLabel;
   o.nodeSync      = FBrowserGroup_nodeSync;
   o.nodeNext      = FBrowserGroup_nodeNext;
   o.nodeClear     = FBrowserGroup_nodeClear;
   return o;
}
function FBrowserGroup_onClick(){
}
function FBrowserGroup_onDoubleClick(){
}
function FBrowserGroup_oeBuild(event){
   var o = this;
   o.base.FControl.oeBuild.call(o, event);
   var hp = o.hPanel;
   hp.style.width = '100%';
   var hc = hp.insertRow().insertCell();
   var hf = o.hCaptionForm = RBuilder.appendTable(hc);
   hf.width = '100%';
   var hr = hf.insertRow();
   var hc = hr.insertCell();
   hc.width = 1;
   hc.style.padding = '0 8';
   o.hText = RBuilder.appendText(hc);
   o.hText.style.whiteSpace = 'nowrap';
   o.hText.style.font = 'icon';
   var hc = hr.insertCell();
   hc.innerHTML = "<HR style='height:1;background-color:#666666'>";
   var hc = hp.insertRow().insertCell();
   var hdp = o.hDataPanel = RBuilder.appendDiv(hc);
   hdp.style.width = '100%';
   hdp.style.height = '100%';
   hdp.style.overflow = 'auto';
   hdp.style.backgroundColor = '#FFFFFF';
   return EEventStatus.Stop;
}
function FBrowserGroup_setLabel(v){
   this.hText.innerText = v;
}
function FBrowserGroup_nodeSync(n){
   var o = this;
   var ps = o.nodes;
   var p = ps.get(n);
   if(!p){
      for(var i=ps.count; i<=n; i++){
         p = RControl.create(FBrowserNode, o.hDataPanel);
         p.browser = o.browser;
         p.group = o;
         ps.push(p);
      }
   }
   p.setVisible(true);
   return p;
}
function FBrowserGroup_nodeNext(){
   return this.nodeSync(this.nodeCount++);
}
function FBrowserGroup_nodeClear(){
   var o = this;
   o.nodeCount = 0;
   for(var n=o.nodes.count-1; n>=0; n--){
      o.nodes.get(n).hide();
   }
}
function FBrowserNode(o){
   o = RClass.inherits(this, o, FControl);
   o.selected          = false;
   o.attributes        = null;
   o.onEnter           = FBrowserNode_onEnter;
   o.onLeave           = FBrowserNode_onLeave;
   o.onClick           = FBrowserNode_onClick;
   o.onDoubleClick     = FBrowserNode_onDoubleClick;
   o.onBuildPanel      = RBuilder.onBuildDivPanel;
   o.oeBuild           = FBrowserNode_oeBuild;
   o.setLabel          = FBrowserNode_setLabel;
   o.getId             = FBrowserNode_getId;
   o.loadValue         = FBrowserNode_loadValue;
   o.select            = FBrowserNode_select;
   return o;
}
function FBrowserNode_onEnter(){
   var o = this;
   if(!o.selected){
      o.border.setColor('#24C2DB');
      o.border.hPanel.filters['DXImageTransform.Microsoft.Gradient'].endcolorstr = '#E0F2FD';
   }
}
function FBrowserNode_onLeave(){
   var o = this;
   if(!o.selected){
      o.border.setColor('#FFFFFF');
      o.border.hPanel.filters['DXImageTransform.Microsoft.Gradient'].endcolorstr = '#FFFFFF';
   }
}
function FBrowserNode_onClick(e){
   var o = this;
   o.browser.onNodeClick(o, e);
}
function FBrowserNode_onDoubleClick(e){
   var o = this;
   o.browser.onNodeDoubleClick(o, e);
}
function FBrowserNode_oeBuild(event){
   var o = this;
   o.base.FControl.oeBuild.call(o, event);
   var hp = o.hPanel;
   hp.style.width = 80;
   hp.style.height = 90;
   hp.style.float = 'left';
   var b = o.border = new TRoundBorder();
   b.build(hp);
   b.hPanel.style.cursor = 'hand';
   b.setColor('#FFFFFF');
   b.hPanel.style.filter = 'progid:DXImageTransform.Microsoft.Gradient(gradienttype=0, startcolorstr=#FFFFFF, endcolorstr=#FFFFFF)';
   var bp = b.hPanel;
   bp.style.padding = 4;
   var hf = o.hForm = RBuilder.appendTable(bp);
   hf.width = '100%';
   hf.height = '100%';
   var hip = o.hIconPanel = hf.insertRow().insertCell();
   hip.align = 'center';
   hip.width = 48;
   hip.height = 48;
   o.hIcon = RBuilder.append(hip, 'IMG');
   var htp = o.hText = hf.insertRow().insertCell();
   htp.align = 'center';
   htp.style.font = 'icon';
   htp.style.wordBreak = 'break-all';
   return EEventStatus.Stop;
}
function FBrowserNode_setLabel(v){
   this.hText.innerText = v;
}
function FBrowserNode_getId(){
   return this.attributes.get('ouid');
}
function FBrowserNode_loadValue(a){
   var o = this;
   o.attributes = a;
   o.label = a.nvl('label');
   o.icon = a.nvl('icon', 'com.unknownBig');
   o.hText.innerText = o.label;
   o.hIcon.src = RResource.iconPath(o.icon);
}
function FBrowserNode_select(v){
   var o = this;
   o.selected = v;
   var f = o.border.hPanel.filters['DXImageTransform.Microsoft.Gradient'];
   if(v){
      o.border.setColor('#24C2DB');
      f.endcolorstr = '#B4E1FD';
   }else{
      o.border.setColor('#FFFFFF');
      f.endcolorstr = '#FFFFFF';
   }
}
function FCell(o){
   o = RClass.inherits(this, o, FControl, MEditValue);
   o.stEdit       = RClass.register(o, new TStyle('Edit'));
   o.table        = null;
   o.column       = null;
   o.row          = null;
   o.hPanel       = null;
   o.hForm        = null;
   o.hFormLine    = null;
   o.hIconPanel   = null;
   o.hIcon        = null;
   o.hEditPanel   = null;
   o.hEdit        = null;
   o.hDropPanel   = null;
   o.hDrop        = null;
   o.buildIcon    = FCell_buildIcon;
   o.buildEdit    = FCell_buildEdit;
   o.buildDrop    = RMethod.empty;
   o.buildForm    = FCell_buildForm;
   o.build        = FCell_build;
   o.doFocus      = FCell_doFocus;
   o.doBlur       = FCell_doBlur;
   o.descriptor   = FCell_descriptor;
   o.text         = FCell_text;
   o.setText      = FCell_setText;
   o.focus        = FCell_focus;
   o.setVisible   = FCell_setVisible;
   o.setEditStyle = RMethod.empty;
   o.refreshStyle = FCell_refreshStyle;
   o.dispose      = FCell_dispose;
   o.dump         = FCell_dump;
   return o;
}
function FCell_buildIcon(){
   var o = this;
   o.hIcon = RBuilder.append(o.hIconPanel, 'IMG');
}
function FCell_buildEdit(){
   var o = this;
   var c = o.column;
   var he = o.hEdit = RBuilder.append(o.hEditPanel, 'INPUT', o.style('Edit'));
   he.style.width = '100%';
   c.linkEvent(o, 'onCellMouseDown', he, c.onCellMouseDown);
   c.linkEvent(o, 'onCellKeyDown', he, c.onCellKeyDown);
   c.linkEvent(o, 'onCellClick', he, c.onCellClick);
   c.linkEvent(o, 'onCellDoubleClick', he, c.onCellDoubleClick);
   if(o.table.isLov){
      o.hEdit.style.cursor = 'hand';
   }
   if(!RString.isEmpty(c.editAlign)){
      he.style.textAlign = c.editAlign;
   }
}
function FCell_buildForm(){
   var o = this;
   var c = o.column;
   if(c.hasIconArea || c.hasDropArea){
      var hf = o.hForm = RBuilder.appendTable(o.hPanel);
      hf.width = '100%';
      var hr = o.hFormLine = hf.insertRow();
      if(c.hasIconArea){
         o.hIconPanel = hr.insertCell();
         o.hIconPanel.width = 18;
         o.buildIcon();
      }
      o.hEditPanel = hr.insertCell();
      o.buildEdit();
      if(c.hasDropArea){
         o.hDropPanel = hr.insertCell();
         o.hDropPanel.width = 8;
         o.buildDrop();
      }
   }else{
      var hep = o.hEditPanel = o.hPanel;
      hep.align = c.editAlign;
      o.buildEdit();
   }
}
function FCell_build(){
   var o = this;
   var c = o.column;
   var h = o.hPanel = RBuilder.create(null, 'TD', o.style('Panel'));
   h.style.borderRight = '1px solid #F0F0F0';
   h.style.borderBottom = '1px dotted #CCCCCC';
   RHtml.link(h, 'control', o);
   c.linkEvent(o, 'onCellMouseEnter', h, c.onCellMouseEnter);
   c.linkEvent(o, 'onCellMouseLeave', h, c.onCellMouseLeave);
   if(c.editColor){
      h.style.color = c.editColor;
   }
   if(c.editBgcolor){
      h.style.backgroundColor = c.editBgcolor;
   }
   if(EEditFormat.Html != c.editFormat){
      o.buildForm();
   }
}
function FCell_doFocus(){
   var o = this;
   o.table.__focusCell = o;
   if(o.column.isEditAble(o)){
      var hs = o.hPanel.style;
      hs.borderLeft = '1px solid #666666';
      hs.borderTop = '1px solid #666666';
      hs.borderRight = '1px solid #CCCCCC';
      hs.borderBottom = '1px solid #CCCCCC';
      o.__focus = true;
      o.refreshStyle();
   }
}
function FCell_doBlur(){
   var o = this;
   if(o.column.isEditAble(o)){
      var hs = o.hPanel.style;
      hs.borderLeft = '0px solid #666666';
      hs.borderTop = '0px solid #666666';
      hs.borderRight = '1px solid #F0F0F0';
      hs.borderBottom = '1px dotted #CCCCCC';
      o.__focus = false;
      o.refreshStyle();
   }
}
function FCell_descriptor(){
   return this.column;
}
function FCell_text(){
   var o = this;
   var c = o.column;
   if(EEditFormat.Html == c.editFormat){
      return o.hPanel.innerHTML;
   }else if(c._absEdit && o.hEdit){
      return o.hEdit.value;
   }else if(o.hEditPanel){
      return o.hEditPanel.innerText;
   }
   return '';
}
function FCell_setText(t){
   var o = this;
   var c = o.column;
   if(EEditFormat.Html == c.editFormat){
      o.hPanel.innerHTML = t;
   }else if(c._absEdit && o.hEdit){
      o.hEdit.value = t;
   }else if(o.hEditPanel){
      o.hEditPanel.innerText = t;
   }
}
function FCell_focus(s){
   var o = this;
   var h = o.hEdit;
   if(h){
      o.column.table.selectRow(o.row, true, true);
      h.focus();
      if(s){
         h.select();
      }
   }
}
function FCell_setVisible(v){
   this.hPanel.style.display = v ? 'block' : 'none';
}
function FCell_refreshStyle(){
   var o = this;
   var t = o.table;
   var r = o.row;
   var s = r.isSelect;
   var he = o.hEdit;
   if(he){
      he.readOnly = true;
      he.style.color = EColor.TextReadonly;
      he.style.backgroundColor = bc;
   }
   var bc = null;
   if(s){
      bc = EColor.RowSelect;
   }else{
      var ih = (t.__hoverRow == r);
      if(ih){
         bc = EColor.RowHover;
      }else{
         bc = EColor.Rows[r.index % EColor.Rows.length];
      }
   }
   if(o.__focus){
      bc = EColor.RowEditHover;
   }
   o.hPanel.style.backgroundColor = bc;
}
function FCell_dispose(){
   var o = this;
   o.base.FControl.dispose.call(o);
   RMemory.freeHtml(o.hPanel);
   o.hPanel = null;
   o.hForm = null;
   o.hFormLine = null;
   o.hIconPanel = null;
   o.hIcon = null;
   o.hEditPanel = null;
   o.hEdit = null;
   o.hDropPanel = null;
   o.hDrop = null;
}
function FCell_dump(s){
   var o = this;
   s = RString.nvlStr(s);
   s.append(RClass.dump(o), '[');
   s.append(o.value);
   s.append(']');
   return s;
}
function FCellBar(o){
   o = RClass.inherits(this, o, FCell);
   o.hEdit              = null;
   o.hLine              = null;
   o.buttons            = new TMap();
   o.stButton           = RClass.register(o, new TStyle('Button'));
   o.stHover            = RClass.register(o, new TStyle('Hover'));
   o.stPress            = RClass.register(o, new TStyle('Press'));
   o.build              = FCellBar_build;
   o.set                = FCellBar_set;
   o.get               = FCellBar_get;
   return o;
}
function FCellBar_build(){
   var o = this;
   var c = o.column;
   var h = o.hPanel = o.base.FCell.build.call(o);
   var hb = o.hButton = RBuilder.appendTable(h);
   var hLine = o.hLine = o.hButton.insertRow();
   for(var n = 0; n < o.column.controls.count; n++){
   if(n != 0){
     hCelSplit = o.hLine.insertCell();
     hCelSplit.innerHTML = '|';
   }
    var button = c.controls.value(n);
    o.buttons.push(button.name, button);
    buttonPanel = button.build();
    var hTd = RBuilder.create(null, 'TD');
    hCel = o.hLine.insertCell();
    hCel.appendChild(buttonPanel);
   }
   return h;
}
function FCellBar_set(v){
   var o = this;
   var d = o.descriptor();
   var c = o.column;
   o.dataValue = RString.nvl(v);
   var s = o.dataValue;
   var bs = new TStrings();
   bm = new TMap();
   bs.unpack(v);
   for(var n = 0; n < o.buttons.count; n++){
      bn = o.buttons.name(n);
      b = o.buttons.value(n);
      bb = bs.get(bn);
      if(!bm.isEmpty()){
         bm.clear();
      }
      bm.unpack(bb);
      if(!bm.get('visiable')){
         b.setVisiable(false);
      }
   }
}
function FCellBar_get(){
   return this.dataValue;
}
function FCellButton(o){
   o = RClass.inherits(this, o, FCell);
   o.buttons           = null;
   o.attributes        = null;
   o.onButtonEnter     = RClass.register(o, new HMouseEnter('onButtonEnter'), FCellButton_onButtonEnter);
   o.onButtonLeave     = RClass.register(o, new HMouseLeave('onButtonLeave'), FCellButton_onButtonLeave);
   o.onCellLeave       = RClass.register(o, new HMouseLeave('onCellLeave'), FCellButton_onCellLeave);
   o.onHintEnter       = RClass.register(o, new HMouseEnter('onHintEnter'), FCellButton_onHintEnter);
   o.onHintLeave       = RClass.register(o, new HMouseLeave('onHintLeave'), FCellButton_onHintLeave);
   o.onButtonClick     = RClass.register(o, new HClick('onButtonClick'), FCellButton_onButtonClick);
   o.construct         = FCellButton_construct;
   o.isDataChanged     = RMethod.emptyFalse;
   o.findButtonByPanel = FCellButton_findButtonByPanel;
   o.buildForm         = FCellButton_buildForm;
   o.set               = FCellButton_set;
   o.modifyButton      = FCellButton_modifyButton;
   o.refreshStyle      = FCellButton_refreshStyle;
   return o;
}
function FCellButton_onButtonEnter(e){
   var o = this;
   var b = o.findButtonByPanel(e.hSource);
   if(b){
      var hs = b.hPanel.style;
      hs.color = 'black';
      hs.cursor = 'hand';
      if(b.hintBox){
  		b.hintBox.style.display = "block";
  	  }
   }
   if (o.hHintPanel) {
	   o.hHintPanel.style.display = '';
   }
}
function FCellButton_onButtonLeave(e){
   var o = this;
   var b = o.findButtonByPanel(e.hSource);
   if(b){
      var hs = b.hPanel.style;
      hs.color = '#0661B0';
      hs.cursor = 'normal';
   }
}
function FCellButton_onHintEnter(e){
   var o = this;
   e.hSource.style.backgroundColor = "#eeeeee";
}
function FCellButton_onCellLeave(e){
	var bs = this.buttons;
	var c = bs.count;
	for(var n = 0; n<c; n++){
	   var b = bs.value(n);
	   if(b.hintBox){
	      b.hintBox.style.display='none';
	   }
	}
}
function FCellButton_onHintLeave(e){
	e.hSource.style.backgroundColor = "#ffffff";
    e.hSource.style.display = "none";
}
function FCellButton_onButtonClick(e){
   var o = this;
   var t = o.table;
   t.clickCell(o);
   var b = o.findButtonByPanel(e.hSource);
   if(b){
      b.button.callEvent('onClick', o, e);
   }
}
function FCellButton_construct(){
   var o = this;
   o.base.FCell.construct.call(o);
   o.attributes = new TAttributes();
}
function FCellButton_findButtonByPanel(h){
   var o = this;
   var bs = o.buttons;
   for(var n=0; n<bs.count; n++){
      var b = bs.value(n);
      if(b.hPanel == h){
         return b;
      }
   }
}
function FCellButton_buildForm(){
   var o = this;
   var c = o.column;
   var hp = o.hPanel;
   RControl.attachEvent(o, 'onCellLeave', hp, o.onCellLeave);
   hp.align = 'left';
   hp.padding = 1;
   var hf = o.hForm = RBuilder.appendTable(o.hPanel);
   var hr = o.hFormLine = hf.insertRow();
   var bs = c.components;
   if(bs){
      o.buttons = new TMap();
      for(var n=0; n<bs.count; n++){
         var b = bs.value(n);
         var hc = hr.insertCell();
         hc.align = 'center';
         hc.style.padding = '0 3';
         var hbp = RBuilder.append(hc, 'DIV');
         var hi = null;
         if(b.icon){
            hi = RBuilder.appendIcon(hbp, b.icon);
         }else{
            hbp.style.padding = '2 6';
            hbp.style.color = '#0661B0';
            hbp.style.textDecoration = 'underline';
         }
         o.attachEvent('onButtonEnter', hbp, o.onButtonEnter);
         o.attachEvent('onButtonLeave', hbp, o.onButtonLeave);
         o.attachEvent('onButtonClick', hbp, o.onButtonClick);
         var ht = null;
         if(b.label){
            if(b.icon){
               hi.title = b.label;
            }else{
               ht = RBuilder.appendText(hbp, b.label);
            }
         }
         var cb = new TCellButton();
         cb.button = b;
         cb.hLayout = hc;
         cb.hPanel = hbp;
         cb.hIcon = hi;
         cb.hText = ht;
         o.buttons.set(b.name, cb);
      }
      var hfp = o.hHintPanel = o.hForm.insertRow().insertCell();
      hfp.height = 1;
      hfp.style.position = 'relative';
   }
}
function FCellButton_set(v){
   var o = this;
   if(!RString.isEmpty(v)){
      var pbs = new TAttributes();
      pbs.unpack(v);
      for(var n=0; n<pbs.count; n++){
         var b = o.buttons.get(pbs.name(n));
         var pk = pbs.value(n);
         if(b && !RString.isEmpty(pk)){
            var as = o.attributes;
            as.clear();
            as.unpack(pk);
            o.modifyButton(b, as);
         }
      }
   }
}
function FCellButton_modifyButton(b, as){
   var o = this;
   var bv = true;
   if(as.contains('visible')){
      bv = RBoolean.isTrue(as.get('visible'));
   }
   b.hLayout.style.display = bv ? 'block' : 'none';
   var pd = as.get('disabled');
   if(pd){
      if(RBoolean.isTrue(pd)){
         hc.style.padding = 3;
         hc.style.border = 0;
      }else{
         hc.style.padding = 2;
         hc.style.borderLeft = '1 solid #DDDDDD';
         hc.style.borderTop = '1 solid #DDDDDD';
         hc.style.borderRight = '1 solid #999999';
         hc.style.borderBottom = '1 solid #999999';
         hc.style.backgroundColor = '#FFFFFF';
      }
   }
   var pl = as.get('label');
   if(pl){
      if(b.icon){
         b.hIcon.title = pl;
      }else{
         b.hText.innerText = pl;
      }
   }
   if(as.contains('hint')){
      hfd = o.hFloatDrop = RBuilder.append(o.hHintPanel, 'DIV');
      hfd.style.borderLeft = '1 solid #CCCCCC';
      hfd.style.borderTop = '1 solid #CCCCCC';
      hfd.style.borderRight = '1 solid #666666';
      hfd.style.borderBottom = '1 solid #666666';
      hfd.style.zIndex = 40000;
      hfd.style.backgroundColor = '#FFFFFF';
      hfd.style.display = 'none';
      hfd.style.position = 'absolute'
      hfd.style.padding = '4 8';
      hfd.style.width = '300px';
      hfd.style.pixelTop = b.offsetHeight + 1;
      hfd.style.pixelLeft = b.hPanel.offsetWidth + 20;
      hfd.innerHTML = as.get('hint');
      o.attachEvent('onHintEnter', hfd, o.onHintEnter);
      o.attachEvent('onHintLeave', hfd, o.onHintLeave);
      b.hintBox = hfd;
   }
}
function FCellButton_refreshStyle(){
   var o = this;
   var r = o.row;
   var bc = null;
   if(r.isSelect){
      bc = EColor.RowSelect;
   }else{
      var ih = (o.column.table.__hoverRow == r);
      if(ih){
         bc = EColor.RowHover;
      }else{
         bc = EColor.Rows[r.index % EColor.Rows.length];
      }
   }
   o.hPanel.style.backgroundColor = bc;
}
function FCellCalendar(o){
   o = RClass.inherits(this, o, FCellEditControl, MFocus, MCellDropable);
   o.stForm           = RClass.register(o, new TStyle('Form'));
   o.stEditForm       = RClass.register(o, new TStyle('EditForm'));
   o.stDrop           = RClass.register(o, new TStyle('Drop'));
   o.stPanelInvalid   = RClass.register(o, new TStyle('PanelInvalid'));
   o.stEditInvalid    = RClass.register(o, new TStyle('EditInvalid'));
   o.stIconDrop       = RClass.register(o, new TStyleIcon('Drop'));
   o.lsnCellEditEnd   = null;
   o.lsnSearchEditEnd = null;
   o.hForm            = null;
   o.hEditForm        = null;
   o.hEdit            = null;
   o.hDrop            = null;
   o.onDataEditBegin  = RMethod.emptyCall;
   o.onDataEditEnd    = RMethod.emptyCall;
   o.buildDrop        = FCellCalendar_buildDrop;
   o.buildEdit        = FCellCalendar_buildEdit;
   o.validText        = FCellCalendar_validText;
   o.drop             = FCellCalendar_drop;
   o.dispose          = FCellCalendar_dispose;
   return o;
}
function FCellCalendar_buildDrop(){
   var o = this;
   var c = o.column;
   o.hDropPanel.width = 1;
   var hi = o.hDrop = RBuilder.appendIcon(o.hDropPanel, o.styleIcon('Drop'), o.style('Drop'));
   c.linkEvent(o, 'onCellDropClick', hi);
}
function FCellCalendar_buildEdit(){
	   var o = this;
	   var c = o.column;
	   if(c._absEdit){
	      o.base.FCellEditControl.buildEdit.call(o);
	   }else{
	      var he = o.hEditPanel;
	      c.linkEvent(o, 'onCellMouseDown', he, c.onCellMouseDown);
	      c.linkEvent(o, 'onCellClick', he, c.onCellClick);
	      c.linkEvent(o, 'onCellDoubleClick', he, c.onCellDoubleClick);
	   }
	}
function FCellCalendar_validText(t){
   return true;
}
function FCellCalendar_drop(){
   var o = this;
   var d = o.descriptor();
   var c = o.column;
   if(!d.isEditAble(o.row)){
      return;
   }
   var e = RConsole.find(FEditConsole).focus(o, FCalendarEditor, d.name);
   e.set(o.dataValue, d.editFormat);
   e.lsnEditEnd = d.lsnCellEditEnd;
   e.setHourEditable(c.editHour);
   e.setMinuteEditable(c.editMinute);
   e.setSecondEditable(c.editSecond);
   e.show();
}
function FCellCalendar_dispose(){
   var o = this;
   o.base.FCellEditControl.dispose.call(o);
   o.hDropPanel = null;
   o.hFormLine = null;
   o.hEdit = null;
}
function FCellCheck(o){
   o = RClass.inherits(this, o, FCellEditControl, MDescCheck);
   o.__recordValue = EBoolean.False;
   o.__editUpdate  = true;
   o.buildEdit     = FCellCheck_buildEdit;
   o.testFocus     = RMethod.emptyFalse;
   o.clearValue    = MDescCheck_clearValue;
   o.resetValue    = MDescCheck_resetValue;
   o.text          = MDescCheck_text;
   o.setText       = MDescCheck_setText;
   o.setInfo       = FCellCheck_setInfo;
   o.validText     = RMethod.empty;
   o.refreshStyle  = FCellCheck_refreshStyle;
   return o;
}
function FCellCheck_buildEdit(){
   var o = this;
   var hep = o.hEditPanel;
   hep.align = 'center';
   var he = o.hEdit = RBuilder.appendCheck(hep, o.style('Edit'));
   he.style.cursor = 'hand';
}
function FCellCheck_refreshStyle(){
   var o = this;
   o.base.FCellEditControl.refreshStyle.call(o);
   var e = o.column.isEditAble(o.row);
   if(o.hEdit){
	  if(e){
         o.hEdit.disabled = !o.__editUpdate;
	  }else{
		 o.hEdit.style.cursor = 'normal';
		 o.hEdit.disabled = true;
	  }
   }
}
function FCellCheck_setInfo(f){
	var o = this;
	o.__editUpdate = RBoolean.isTrue(f.enable);
	o.hEdit.disabled = !RBoolean.isTrue(f.enable);
	o.hEdit.style.display = RBoolean.isTrue(f.visible) ? 'block' : 'none';
    return;
}
function FCellColorPicker(o){
   o = RClass.inherits(this, o, FCellEditControl);
   o.styleForm        = RClass.register(o, new TStyle('Form'));
   o.styleEditForm    = RClass.register(o, new TStyle('EditForm'));
   o.styleDropForm    = RClass.register(o, new TStyle('DropForm'));
   o.styleDrop        = RClass.register(o, new TStyle('Drop'));
   o.hForm        = null;
   o.hEditForm    = null;
   o.hDropForm    = null;
   o.hEdit        = null;
   o.hDrop        = null;
   o.build        = FCellColorPicker_build;
   o.text         = FCellColorPicker_text;
   o.setText      = FCellColorPicker_setText;
   return o;
}
function FCellColorPicker_build(){
   var o = this;
   var c = o.column;
   var h = o.base.FCellEditControl.build.call(o);
   var hTb = o.hForm = RBuilder.appendTable(h, o.style('Form'));
   var hR  = hTb.insertRow();
   var hE  = o.hEditForm  = hR.insertCell();
   hE.className = o.style('EditForm');
   o.hEdit = RBuilder.appendEdit(hE,o.style('Edit'));
   var hD = hR.insertCell();
   var hT = o.hDropForm = RBuilder.appendTable(hD,o.style('DropForm'));
   var hDtd = hT.insertRow().insertCell();
   o.hDrop = RBuilder.appendIcon(hDtd,'#colorpicker',o.style('Drop'),13,13);
   return h;
}
function FCellColorPicker_text(){
   return this.hEdit.value;
}
function FCellColorPicker_setText(v){
   this.hEdit.value = v;
}
function FCellEdit(o){
   o = RClass.inherits(this, o, FCellEditControl, MFocus);
   o.buildDrop = FCellEdit_buildDrop;
   o.buildEdit = FCellEdit_buildEdit;
   o.setInfo   = FCellEdit_setInfo;
   o.text      = FCellEdit_text;
   o.setText   = FCellEdit_setText;
   return o;
}
function FCellEdit_buildDrop(){
   var o = this;
   var c = o.column;
   if(!RString.isEmpty(c.lovRefer)){
      var hdp = o.hDropPanel;
      hdp.align = 'right';
      hdp.style.paddingRight = 2;
      var hli = o.hLovImage = RBuilder.appendIcon(hdp, 'ctl.FCellEdit_Lov', null, 16, 16);
      hli.style.borderLeft='1 solid #CCCCCC';
      hli.style.cursor = 'hand';
      c.linkEvent(o, 'onListClick', hli);
   }
}
function FCellEdit_buildEdit(){
   var o = this;
   var c = o.column;
   if(c.canZoom()){
      var hep = o.hEditPanel;
      c.linkEvent(o, 'onCellDoubleClick', hep, c.onCellDoubleClick);
      var he = o.hEdit = RBuilder.append(hep, 'SPAN');
      he.style.color = 'blue';
      he.style.textDecoration = 'underline';
      he.style.cursor = 'hand';
      he.style.paddingBottom = 1;
      c.linkEvent(o, 'onZoomClick', he, c.onZoomClick);
      c.linkEvent(o, 'onZoomHover', he, c.onZoomHover);
      c.linkEvent(o, 'onZoomLeave', he, c.onZoomLeave);
      if(!RString.isEmpty(c.editAlign)){
         he.style.textAlign = c.editAlign;
      }
   }else{
      if(c._absEdit){
         o.base.FCellEditControl.buildEdit.call(o);
      }else{
         var he = o.hEditPanel;
         c.linkEvent(o, 'onCellMouseDown', he, c.onCellMouseDown);
         c.linkEvent(o, 'onCellClick', he, c.onCellClick);
         c.linkEvent(o, 'onCellDoubleClick', he, c.onCellDoubleClick);
      }
   }
}
function FCellEdit_setInfo(f){
   var o = this;
   o.base.FCellEditControl.setInfo.call(o, f);
   var d = o.column;
   var m = d.iconMap;
   var hi = o.hIcon;
   if(m && m.get(f.icon)){
      hi.style.display = 'block';
      hi.title = f.iconHint;
      hi.src = RResource.iconPath(m.get(f.icon));
   }else{
      if(hi){
         hi.style.display = 'none';
      }
   }
}
function FCellEdit_text(){
   var o = this;
   var c = o.column;
   if(c.canZoom()){
      return o.hEdit.innerText;
   }
   if(c._absEdit){
      return o.hEdit.value;
   }
   return o.hEditPanel.innerText;
}
function FCellEdit_setText(t){
   var o = this;
   var c = o.column;
   if(c.canZoom()){
      o.hEdit.innerText = t;
   }else{
      if(c._absEdit){
         o.hEdit.value = t;
      }else{
         o.hEditPanel.innerText = t;
      }
   }
}
function FCellEditControl(o){
   o = RClass.inherits(this, o, FCell);
   o.stEditable   = RClass.register(o, new TStyle('Readonly'));
   o.stPanel      = RClass.register(o, new TStyle('PanelSelect'));
   o.stEditable   = RClass.register(o, new TStyle('ReadonlySelect'));
   o.stEditSelect = RClass.register(o, new TStyle('EditSelect'));
   o.stEdit       = RClass.register(o, new TStyle('EditReadonly'));
   o.stEditSelect = RClass.register(o, new TStyle('EditReadonlySelect'));
   o.buildEdit    = FCellEditControl_buildEdit;
   o.getEditRange = FCellEditControl_getEditRange;
   o.select       = FCellEditControl_select;
   o.setVisible   = FCellEditControl_setVisible;
   o.refreshStyle = FCellEditControl_refreshStyle;
   return o;
}
function FCellEditControl_buildEdit(){
   var o = this;
   o.base.FCell.buildEdit.call(o);
   var c = o.column;
   var he = o.hEdit;
   he.style.color = EColor.TextEdit;
   c.linkEvent(o, 'onDataChange', he);
   if(o.table.isLov){
      o.hEdit.style.cursor = 'hand';
   }
}
function FCellEditControl_getEditRange(){
   var o = this;
   var hc = o.hPanel;
   var p = RHtml.offsetPosition(hc);
   var w = hc.offsetWidth;
   var h = hc.offsetHeight;
   return new TRange(p.x, p.y, w, h);
}
function FCellEditControl_select(v){
   var o = this;
   var a = o.descriptor().isEditAble(o.row);
   if(v){
      if(!RClass.isClass(o, FCellCalendar)){
         o.setEditStyle(a ? EStyle.Select : EStyle.ReadonlySelect);
      }else{
         o.setEditStyle(EStyle.ReadonlySelect);
         o.column.disable();
      }
   }else{
      if(!RClass.isClass(o, FCellCalendar)){
         o.setEditStyle(a ? EStyle.Normal : EStyle.Readonly);
      }else{
         o.setEditStyle(EStyle.Readonly);
         o.column.disable();
      }
   }
}
function FCellEditControl_setVisible(v){
   var o = this;
   o.hPanel.style.display = v ? 'block' : 'none';
   if(v){
      if(!RClass.isClass(o, FCellCalendar)){
         var a = o.descriptor().isEditAble(o.row);
         o.setEditStyle(a ? EStyle.Normal : EStyle.Readonly);
     }else{
       o.setEditStyle(EStyle.Readonly);
       o.column.disable();
     }
   }
}
function FCellEditControl_refreshStyle(){
   var o = this;
   var t = o.table;
   var c = o.column;
   var r = o.row;
   var hep = o.hEditPanel;
   var he = o.hEdit;
   var hd = o.hDrop;
   var e = c.isEditAble(r);
   var s = r.isSelect;
   var ce = e ? EColor.TextEdit : EColor.TextReadonly;
   if(he){
      he.readOnly = !e;
      if(!c.zoomRefer){
         he.style.color = ce;
      }
      if(hd){
         he.style.cursor = e? 'hand':'normal';
         hd.style.cursor = e? 'hand':'normal';
      }
   }
   if(hep){
      hep.style.color = ce;
   }
   var bc = null;
   if(s){
      bc = EColor.RowSelect;
   }else{
      var ih = (t.__hoverRow == r);
      if(ih){
         bc = EColor.RowHover;
      }else{
         bc = EColor.Rows[r.index % EColor.Rows.length];
      }
   }
   if(o.__focus){
      bc = EColor.RowEditHover;
   }
   if(he){
      he.style.backgroundColor = bc;
   }
   o.hPanel.style.backgroundColor = bc;
}
function FCellIcon(o){
   o = RClass.inherits(this, o, FCellEditControl);
   o.hEdit  = null;
   o.build  = FCellIcon_build;
   o.get    = FCellIcon_get;
   o.set    = FCellIcon_set;
   o.select = FCellIcon_select;
   return o;
}
function FCellIcon_build(){
   var o = this;
   var c = o.column;
   var h = o.base.FCellIconable.build.call(o);
   var he = o.hEdit = RBuilder.append(h, 'INPUT', o.style('Editor'));
   he.link = this;
   he.onfocus = c.ohEditFocus;
   he.onblur = c.ohEditBlur;
   he.onkeydown = c.ohEditKdown;
   he.onkeypress = c.ohEditKpress;
   he.onkeyup = c.ohEditKup;
   he.onchange = c.ohEditChanged;
   if(c.width){
      o.hEdit.style.width = c.width-2;
   }
   return h;
}
function FCellIcon_get(){
   return this.hEdit.value;
}
function FCellIcon_set(v){
   this.hEdit.value = v;
}
function FCellIcon_select(v){
   var o = this;
   var h = o.base.FCellIconable.select.call(o, v);
   o.hEdit.className = v ? o.style('EditorSelect') : o.style('Editor');
}
function FCellNumber(o){
   o = RClass.inherits(this, o, FCellEditControl, MDescNumber, MFocus);
   o.editAlign   = EAlign.Right;
   o.buildDrop   = FCellNumber_buildDrop;
   o.buildEdit   = FCellNumber_buildEdit;
   o.formatValue = MDescNumber_formatValue;
   o.formatText  = MDescNumber_formatText;
   o.setInfo     = FCellNumber_setInfo;
   o.dispose     = FCellNumber_dispose;
   return o;
}
function FCellNumber_buildDrop(){
   this.buildAdjustForm(this.hDropPanel);
}
function FCellNumber_buildEdit(){
   var o = this;
   var c = o.column;
   if(c._absEdit){
      o.base.FCellEditControl.buildEdit.call(o);
   }else{
      var he = o.hEditPanel;
      c.linkEvent(o, 'onCellMouseDown', he, c.onCellMouseDown);
      c.linkEvent(o, 'onCellClick', he, c.onCellClick);
      c.linkEvent(o, 'onCellDoubleClick', he, c.onCellDoubleClick);
   }
}
function FCellNumber_setInfo(f){
   var o = this;
   o.base.FCellEditControl.setInfo.call(o, f);
   var d = o.column;
   var m = d.iconMap;
   var hi = o.hIcon;
   if(m && m.get(f.icon)){
      hi.style.display = 'block';
      hi.title = f.iconHint;
      hi.src = RResource.iconPath(m.get(f.icon));
   }else{
      if(hi){
         hi.style.display = 'none';
      }
   }
}
function FCellNumber_dispose(){
   var o = this;
   o.base.FCellEditControl.dispose.call(o);
   o.hArrowForm = null;
   o.hUpIcon = null;
   o.hDownIcon = null;
}
function FCellPicture(o){
   o = RClass.inherits(this, o, FCellEditControl);
   o.hEdit        = null;
   o.buildForm    = FCellPicture_buildForm;
   o.text         = FCellPicture_text;
   o.setText      = FCellPicture_setText;
   o.refreshStyle = FCellPicture_refreshStyle;
   return o;
}
function FCellPicture_buildForm(){
   var o = this;
   var c = o.column;
   var he = o.hEdit = o.hImage = RBuilder.append(o.hPanel, 'IMAGE');
}
function FCellPicture_text(){
   return '';
}
function FCellPicture_setText(t){
   var o = this;
   if(!RString.isEmpty(t)){
      o.hImage.style.display = 'block';
      var as = new TAttributes();
      as.unpack(t);
      var recordId = as.get('RD');
      var name = as.get('OG') + '_' + as.get('OD') + '.' + as.get('MT').toLowerCase();
      o.storeCode = 'person.user'
      o.hImage.src = top.RContext.context('/ars/sys/') + o.storeCode.toLowerCase() + '/' + recordId + '/' + name;
      o.hImage.height = 16;
   }else{
      o.hImage.style.display = 'none';
   }
}
function FCellPicture_refreshStyle(){
   var o = this;
   o.base.FCellEditControl.refreshStyle.call(o);
   if(o.hPanel.offsetWidth){
   }
}
function FCellProcessStatus(o){
   o = RClass.inherits(this, o, FCellEditControl);
   o.styleForm     = RClass.register(o, new TStyle('Form'));
   o.styleProcess  = RClass.register(o, new TStyle('Process'));
   o.isDataChanged = RMethod.emptyFalse;
   o.buildForm     = FCellProcessStatus_buildForm;
   o.text          = FCellProcessStatus_text;
   o.setText       = FCellProcessStatus_setText;
   return o;
}
function FCellProcessStatus_buildForm(){
   var o = this;
   var c = o.column;
   var hf = o.hForm = RBuilder.appendTable(o.hPanel);
   hf.width = '100%';
   hf.height = '100%';
   hf.style.tableLayout = 'fixed';
   c.linkEvent(o, 'onCellDoubleClick', hf);
}
function FCellProcessStatus_text(){
   return this.dataValue;
}
function FCellProcessStatus_setText(v){
   var o = this;
   if(!v){
      return;
   }
   RHtml.clear(o.hForm);
   o.hPanel.vAlign = 'middle';
   var hUp = o.hForm.insertRow();
   var hr = o.hForm.insertRow();
   var hDown = o.hForm.insertRow();
   var s = RString.nvl(v);
   var sl = s.length;
   var i = RInt.parse(o.column.stepStart);
   var l = RInt.nvl(RInt.parse(o.column.stepCount),sl);
   var bcs = RString.split(o.column.levelColors,',');
   var bis = RString.split(o.column.levelImages,',');
   for(var m = 0;m < sl; m++){
      var h1 = hUp.insertCell();
      h1.height = 4;
      var hd = hr.insertCell();
      hd.height = 8;
      hd.className = o.style('Process');
      var h2 = hDown.insertCell();
      h2.height = 4;
      if(m+1 == 13 || m+1 == 26){
         var a = hUp.insertCell();
         var b = hr.insertCell();
         var c = hDown.insertCell();
         a.width = 1;
         a.style.backgroundColor = '#BACBF4';
         b.style.backgroundColor = '#BACBF4';
         c.style.backgroundColor = '#BACBF4';
      }
      var idx = RInt.parse(s.charAt(m));
      if(bcs && bcs[idx]){
         hd.style.backgroundColor = bcs[idx];
      }
      if(bis && bis[idx]){
         hd.style.backgroundImage = RRes.imagePath(bis[idx]);
      }
   }
}
function FCellProgress(o){
   o = RClass.inherits(this, o, FCellEditControl);
   o.hEdit        = null;
   o.build        = FCellProgress_build;
   o.text         = FCellProgress_text;
   o.setText      = FCellProgress_setText;
   return o;
}
function FCellProgress_build(){
   var o = this;
   var c = o.column;
   var h = o.base.FCellEditControl.build.call(o);
   var he = o.hEdit = RBuilder.append(h, 'INPUT', o.style('Edit'));
   return h;
}
function FCellProgress_text(){
   return this.hEdit.value;
}
function FCellProgress_setText(v){
   this.hEdit.value = v;
}
function FCellProgressBar(o){
   o = RClass.inherits(this, o, FCell);
   o.stForm        = RClass.register(o, new TStyle('Form'));
   o.hForm         = null;
   o.hProgressForm = null;
   o.hProgress     = null;
   o.hText         = null;
   o.buildForm     = FCellProgressBar_buildForm;
   o.isDataChanged = RMethod.emptyFalse;
   o.get           = RMethod.empty;
   o.reget         = RMethod.empty;
   o.set           = FCellProgressBar_set;
   o.dispose       = FCellProgressBar_dispose;
   return o;
}
function FCellProgressBar_buildForm(){
   var o = this;
   var hp = o.hPanel;
   hp.style.paddingTop = 4;
   var hf = o.hForm = RBuilder.appendTable(hp);
   hf.style.width = '100%';
   hf.style.height = '100%';
   var hfr = hf.insertRow();
   var hpf = o.hProgressForm = RBuilder.appendTable(hfr.insertCell());
   hpf.width = '100%';
   hpf.height = '10';
   hpf.style.tableLayout = 'fixed';
   hpf.style.backgroundColor = '#F0EFEF';
   var hr = hpf.insertRow();
   o.hProgress = hr.insertCell();
   hr.insertCell();
   var ht = o.hText = hfr.insertCell();
   ht.width = 40;
   ht.align = 'right';
   ht.noWrap = true;
   ht.style.color = EColor.TextReadonly;
   ht.style.paddingRight = 4;
}
function FCellProgressBar_set(v){
   var o = this;
   var c = o.column;
   var arv = RString.split(v, '|');
   var vd = arv ? (arv.length > 0 ? arv[0] : 0) : 0;
   var vt = arv ? (arv.length > 1 ? arv[1] : 0) : 0;
   var vc =  '#FF8040';
   vt = (vt == 0 ? 1:vt);
   o.hProgress.width = vt;
   if(vc){
	   o.hProgress.style.backgroundColor = vc;
	   o.hProgress.style.border = '1 solid #CCCCCC';
	   o.hProgressForm.style.border
   }
   o.hProgressForm.title = vt;
   o.hText.innerText = vd + '(' + vt + ')';
}
function FCellProgressBar_dispose(){
   var o = this;
   o.base.FCell.dispose.call(o);
   o.hForm = null;
   o.hProgressForm = null;
   o.hProgress = null;
   o.hText = null;
}
function FCellRadio(o){
   o = RClass.inherits(this, o, FCell);
   o.get       = FCellRadio_get;
   o.reget     = FCellRadio_reget;
   o.set       = FCellRadio_set;
   o.validText = FCellRadio_validText;
   return o;
}
function FCellRadio_get(){
   return this.dataValue;
}
function FCellRadio_reget(){
   return this.descriptor().formatValue(this.text());
}
function FCellRadio_set(v){
   var o = this;
   var d = o.descriptor();
   var mvp = d.iconMap;
   if(mvp && mvp.get(v)){
      o.hImg.style.display = 'block';
      o.hImg.src = RRes.iconPath(mvp.get(v));
   }else{
      if(o.hImg){
         o.hImg.style.display = 'none';
      }
   }
   o.dataValue = RString.nvl(v);
   var t = d.formatText(v);
   o.setText(t);
}
function FCellRadio_validText(t){
   return true;
}
function FCellRadioGroup(o){
   o = RClass.inherits(this, o, FCellEditControl);
   o.radios       = null;
   o.metaPack     = null;
   o.meta         = null;
   o.onRadioClick = RClass.register(o, new HClick('onRadioClick'), FCellRadioGroup_onRadioClick);
   o.construct    = FCellRadioGroup_construct;
   o.buildForm    = FCellRadioGroup_buildForm;
   o.syncRadio    = FCellRadioGroup_syncRadio;
   o.text         = FCellRadioGroup_text;
   o.setText      = FCellRadioGroup_setText;
   o.validText    = RMethod.empty;
   return o;
}
function FCellRadioGroup_onRadioClick(e){
   var o = this;
   for(var i=o.radios.count-1; i>=0; i--){
      var r = o.radios.get(i);
      if(r.hEdit == e.hSource){
         o.dataValue = r.value;
         break;
      }
   }
}
function FCellRadioGroup_construct(){
   var o = this;
   o.radios = new TList();
   o.metaPack = new TAttributes();
   o.meta = new TAttributes();
}
function FCellRadioGroup_buildForm(){
   var o = this;
   o.hEditForm = RBuilder.appendTable(o.hPanel);
   o.hEditLine = o.hEditForm.insertRow();
}
function FCellRadioGroup_syncRadio(n){
   var o = this;
   var c = o.column;
   var r = o.radios.get(n);
   if(!r){
      var hel = o.hEditLine;
      for(var i=o.radios.count; i<=n; i++){
         r = new TCellRadio();
         var hep = r.hEditPanel = hel.insertCell();
         var he = r.hEdit = RBuilder.append(hep, '<INPUT type=radio name=' + c.dataName.toLowerCase() + '_' + hel.uniqueNumber + " style='border:0;cursor:hand'>");
         o.attachEvent('onRadioClick', he);
         r.hLabel = o.hEditLine.insertCell();
         r.hLabel.style.paddingRight = 4;
         o.radios.push(r);
      }
   }
   r.hEditPanel.style.display = 'block';
   r.hLabel.style.display = 'block';
   return r;
}
function FCellRadioGroup_text(){
   var o = this;
   return o.dataValue;
}
function FCellRadioGroup_setText(v){
   var o = this;
   var mp = o.metaPack;
   mp.clear();
   mp.unpack(v);
   var mt = o.meta;
   mt.clear();
   mt.unpack(mp.get('meta'));
   var dv = o.dataValue = mp.get('value');
   for(var i=0; i<mt.count; i++){
      var r = o.syncRadio(i);
      var rv = r.value = mt.name(i);
      var rl = r.label = mt.value(i);
      r.hEdit.checked = (rv == dv);
      r.hLabel.innerText = rl;
   }
}
function FCellReference(o){
   o = RClass.inherits(this, o, FCell);
   o.get       = FCellReference_get;
   o.reget     = FCellReference_reget;
   o.set       = FCellReference_set;
   o.validText = FCellReference_validText;
   return o;
}
function FCellReference_get(){
   return this.dataValue;
}
function FCellReference_reget(){
   return this.descriptor().formatValue(this.text());
}
function FCellReference_set(v){
   var o = this;
   var d = o.descriptor();
   var mvp = d.iconMap;
   if(mvp && mvp.get(v)){
      o.hImg.style.display = 'block';
      o.hImg.src = RRes.iconPath(mvp.get(v));
   }else{
      if(o.hImg){
         o.hImg.style.display = 'none';
      }
   }
   o.dataValue = RString.nvl(v);
   var t = d.formatText(v);
   o.setText(t);
}
function FCellReference_validText(t){
   return true;
}
function FCellSelect(o){
   o = RClass.inherits(this, o, FCellEditControl, MFocus, MCellDropable);
   o.stForm          = RClass.register(o, new TStyle('Form'));
   o.stEditForm      = RClass.register(o, new TStyle('EditForm'));
   o.stDropForm      = RClass.register(o, new TStyle('DropForm'));
   o.stDrop          = RClass.register(o, new TStyle('Drop'));
   o.stIconDrop      = RClass.register(o, new TStyleIcon('Drop'));
   o.onDataEditBegin = RMethod.emptyCall;
   o.buildDrop       = FCellSelect_buildDrop;
   o.buildEdit       = FCellSelect_buildEdit;
   o.set             = FCellSelect_set;
   o.drop            = FCellSelect_drop;
   return o;
}
function FCellSelect_buildDrop(){
   var o = this;
   var c = o.column;
   var hd = o.hDropPanel;
   hd.width = 1;
   var hi = o.hDrop = RBuilder.appendIcon(hd, o.styleIcon('Drop'), o.style('Drop'));
   c.linkEvent(o, 'onCellDropClick', hi);
}
function FCellSelect_buildEdit(){
   var o = this;
   var c = o.column;
   if(c._absEdit){
      o.base.FCellEditControl.buildEdit.call(o);
   }else{
      var he = o.hEditPanel;
      c.linkEvent(o, 'onCellMouseDown', he, c.onCellMouseDown);
      c.linkEvent(o, 'onCellClick', he, c.onCellClick);
      c.linkEvent(o, 'onCellDoubleClick', he, c.onCellDoubleClick);
   }
}
function FCellSelect_set(v){
   var o = this;
   var d = o.descriptor();
   if(d.viewIcons && d.iconMap){
      o.hIcon.src = RRes.iconPath(d.iconMap.get(v));
   }
   o.dataValue = RString.nvl(v);
   var t = d.formatText(v);
   o.setText(t);
}
function FCellSelect_drop(){
   var o = this;
   var d = o.descriptor();
   if(!d.isEditAble(o.row) || !d.editRefer){
      return;
   }
   var e = RConsole.find(FEditConsole).focus(o, FSelectEditor, d.editRefer);
   if(d.editDynamic){
      return RMessage.fatal(o, null, 'Unsupport.');
   }else{
	  e.__source = o;
      e.setItems(d.items);
      e.set(d.formatValue(o.text()));
   }
   e.lsnEditEnd = d.lsnCellEditEnd;
   e.show();
}
function FCellSelected(o){
   o = RClass.inherits(this, o, FCellEditControl);
   o.dataName      = '_select';
   o.hSelected       = null;
   o.buildForm     = FCellSelected_buildForm;
   o.onSelected    = FCellSelected_onSelected;
   o.refreshStyle  = FCellSelected_refreshStyle;
   o.isDataChanged = RMethod.emptyFalse;
   o.get           = RMethod.empty;
   o.reget         = RMethod.empty;
   o.set           = RMethod.empty;
   o.dispose       = FCellSelected_dispose;
   return o;
}
function FCellSelected_buildForm(){
   var o = this;
   var c = o.column;
   var hp = o.hPanel;
   hp.align = 'center';
   o.hSelected = RBuilder.appendCheck(hp, o.style('Edit'));
   o.hSelected.parent = o;
   o.hSelected.onclick = o.onSelected;
}
function FCellSelected_refreshStyle(){
   var o = this;
   var r = o.row;
   var t = r.table;
   var p = null;
   if(t.dispSelected){
	   o.hPanel.style.display = 'block';
	   if(r.isSelect){
	      o.hSelected.checked = true;
	      o.hPanel.style.backgroundColor = '#CEE7FF';
	   }else{
		   o.hSelected.checked = false;
		   o.hPanel.style.backgroundColor = '#FFFFFF';
	   }
   }else{
	   o.hPanel.style.display = 'none';
   }
}
function FCellSelected_onSelected(){
   var r = this.parent.row;
   var t = this.parent.table;
   if(this.checked){
      t.selectRow(r, false, true);
   }else{
	  t.clearSelectRow(r);
   }
}
function FCellSelected_dispose(){
   var o = this;
   o.base.FCellEditControl.dispose.call(o);
   o.hSelected = null;
}
function FCellStatus(o){
   o = RClass.inherits(this, o, FCellEditControl);
   o.dataName      = '_status';
   o.hStatus       = null;
   o.buildForm     = FCellStatus_buildForm;
   o.isDataChanged = RMethod.emptyFalse;
   o.get           = RMethod.empty;
   o.reget         = RMethod.empty;
   o.set           = RMethod.empty;
   o.setIcon       = FCellStatus_setIcon;
   o.refreshStyle  = FCellStatus_refreshStyle;
   o.dispose       = FCellStatus_dispose;
   return o;
}
function FCellStatus_onStatusEnter(){
   this.row.table.getRowBar().linkCell(this);
}
function FCellStatus_buildForm(){
   var o = this;
   var c = o.column;
   var hp = o.hPanel;
   hp.align = 'center';
   hp.style.paddingTop = 2;
   hp.style.paddingBottom = 2;
   hp.style.cursor='normal';
   c.linkEvent(o, 'onCellClick', hp, c.onCellClick);
   o.hStatus = RBuilder.appendIcon(hp, o.column.styleIcon(c.table.isFormLinked() ? 'Normal' : 'Normal'));
   if(c.table.dispRowbar){
   }
}
function FCellStatus_setIcon(s){
   this.hStatus.src = s;
}
function FCellStatus_refreshStyle(){
   var o = this;
   var r = o.row;
   var t = r.table;
   var p = null;
   if(r.isDataChanged()){
      p = 'Changed';
   }else{
      p = t.isFormLinked() ? 'Normal' : 'Normal';
   }
   o.setIcon(o.column.styleIconPath(p));
}
function FCellStatus_dispose(){
   var o = this;
   o.base.FCellEditControl.dispose.call(o);
   o.hStatus = null;
}
function FCellTree(o){
   o = RClass.inherits(this, o, FCell);
   o.hEdit        = null;
   o.label        = RClass.register(o, new TPtyStr('label'));
   o.build        = FCellTree_build;
   o.text         = FCellTree_text;
   o.setText      = FCellTree_setText;
   o.set          = FCellTree_set;
   return o;
}
function FCellTree_build(){
   var o = this;
   var t = o.column.table;
   var h = o.base.FCell.build.call(o);
   h.noWrap = true;
   var hs = RBuilder.append(h, 'SPAN');
   var s = '';
   var p = o.row.parentRow;
   while(p){
      s += '&nbsp;&nbsp;';
      p = p.parentRow;
   }
   hs.innerHTML = s;
   var hImg = o.hImg = RBuilder.append(h, 'IMG');
   hImg.align = 'absmiddle'
   var hTxt = o.hLabel = RBuilder.append(h, 'SPAN');
   t.linkEvent(o, 'onColumnTreeClick', hImg);
   return h;
}
function FCellTree_text(){
   return this.hEdit.value;
}
function FCellTree_setText(v){
   this.hEdit.value = v;
}
function FCellTree_set(){
   var o = this;
   var r = o.row;
   var ochd = r.get('ochd');
   if(ochd == 'Y'){
      o.hImg.src = o.styleIconPath('Expend', FColumnTree);
   }else if(ochd == 'N'){
      o.hImg.src = o.styleIconPath('Node', FColumnTree);
   }
   var lbs = r.row.get('label');
   o.hLabel.innerText = RString.nvl(lbs);
}
function FColumn(o) {
   o = RClass.inherits(this, o, FControl, MEditDescriptor, MDisplay);
   o.dispList          = RClass.register(o, new TPtyBoolSet('dispList', 'dispConfig', EDisplayConfig.List));
   o.dispFixed         = RClass.register(o, new TPtyBoolSet('dispFixed', 'dispConfig', EDisplayConfig.Fixed));
   o.dispAuto          = RClass.register(o, new TPtyBoolSet('dispAuto', 'dispConfig', EDisplayConfig.Auto));
   o.dispSize          = RClass.register(o, new TPtyBoolSet('dispSize', 'dispConfig', EDisplayConfig.Size));
   o.dispDrag          = RClass.register(o, new TPtyBoolSet('dispDrag', 'dispConfig', EDisplayConfig.Drag));
   o.dataType          = RClass.register(o, new TPtyStr('dataType'));
   o.editColor         = RClass.register(o, new TPtyStr('editColor'));
   o.editBgcolor       = RClass.register(o, new TPtyStr('editBgcolor'));
   o.orderAble         = RClass.register(o, new TPtyBool('orderAble'));
   o.editAlign         = EAlign.Left;
   o.viewIcons         = RClass.register(o, new TPtyStr('viewIcons'));
   o.stHead            = RClass.register(o, new TStyle('Head'));
   o.stHeadLabel       = RClass.register(o, new TStyle('HeadLabel'));
   o.stSearchPanel     = RClass.register(o, new TStyle('SearchPanel'));
   o.stSearchEdit      = RClass.register(o, new TStyle('SearchEdit'));
   o.stIconSortUp      = RClass.register(o, new TStyleIcon('SortUp'));
   o.stIconSortDown    = RClass.register(o, new TStyleIcon('SortDown'));
   o.__cellClass       = FCell;
   o.hasIconArea       = false;
   o.hasDropArea       = false;
   o.table             = null;
   o.index             = null;
   o.iconMap           = null;
   o.sortType          = true;
   o.isDisplay         = true;
   o.searchHint        = "搜索...";
   o.hForm             = null;
   o.hFormLine         = null;
   o.hIconPanel        = null;
   o.hIcon             = null;
   o.hHeadPanel        = null;
   o.hLabel            = null;
   o.hSortPanel        = null;
   o.hSortUp           = null;
   o.hSortDown         = null;
   o.hSearchPanel      = null;
   o.hSearchForm       = null;
   o.hSearchFormLine   = null;
   o.hSearchIconPanel  = null;
   o.hSearchIcon       = null;
   o.hSearchEditPanel  = null;
   o.hSearchEdit       = null;
   o.hSearchDropPanel  = null;
   o.hSearchDrop       = null;
   o.hFixPanel         = null;
   o.onSearchEnter     = RClass.register(o, new HMouseEnter('onSearchEnter'));
   o.onSearchClick     = RClass.register(o, new HClick('onSearchClick'));
   o.onSearchLeave     = RClass.register(o, new HMouseLeave('onSearchLeave'));
   o.onSearchKeyDown   = RClass.register(o, new HKeyDown('onSearchKeyDown'));
   o.onCellMouseEnter  = RClass.register(o, new HMouseEnter('onCellMouseEnter'), FColumn_onCellMouseEnter);
   o.onCellMouseLeave  = RClass.register(o, new HMouseLeave('onCellMouseLeave'), FColumn_onCellMouseLeave);
   o.onCellMouseDown   = RClass.register(o, new HMouseDown('onCellMouseDown'), FColumn_onCellMouseDown);
   o.onCellClick       = RClass.register(o, new HClick('onCellClick'), FColumn_onCellClick);
   o.onCellDoubleClick = RClass.register(o, new HDoubleClick('onCellDoubleClick'), FColumn_onCellDoubleClick);
   o.onCellKeyDown     = RClass.register(o, new HKeyDown('onCellKeyDown'), FColumn_onCellKeyDown);
   o.onDataKeyDown     = FColumn_onDataKeyDown;
   o.onDataChanged     = FColumn_onDataChanged;
   o.onEditBegin       = FColumn_onEditBegin;
   o.onEditEnd         = FColumn_onEditEnd;
   o.onEditChanged     = FColumn_onEditChanged;
   o.onHeadMouseDown   = RClass.register(o, new HMouseDown('onHeadMouseDown'), FColumn_onHeadMouseDown);
   o.onBuildLabel      = FColumn_onBuildLabel;
   o.onBuildSearchIcon = RMethod.empty;
   o.onBuildSearchEdit = FColumn_onBuildSearchEdit;
   o.onBuildSearchDrop = RMethod.empty;
   o.onBuildSearchForm = FColumn_onBuildSearchForm;
   o.onBuildSearch     = FColumn_onBuildSearch;
   o.onBuildTotal      = FColumn_onBuildTotal;
   o.onBuildPanel      = FColumn_onBuildPanel;
   o.oeBuild           = FColumn_oeBuild;
   o.oeMode            = FColumn_oeMode;
   o.oeRefresh         = FColumn_oeRefresh;
   o.createCell        = FColumn_createCell;
   o.createMoveable    = FColumn_createMoveable;
   o.searchValue       = FColumn_searchValue;
   o.setStyleStatus    = FColumn_setStyleStatus;
   o.cell              = FColumn_cell;
   o.equalsValue       = FColumn_equalsValue;
   o.setWidth          = FColumn_setWidth;
   o.setVisible        = FColumn_setVisible;
   o.moveCellFocus     = FColumn_moveCellFocus;
   o.getEditRange      = FColumn_getEditRange;
   o.dispose           = FColumn_dispose;
   o.dump              = FColumn_dump;
   return o;
}
function FColumn_onCellMouseEnter(s, e){
   this.table.hoverRow(s.row, true);
}
function FColumn_onCellMouseLeave(s, e){
   this.table.hoverRow(s.row, false);
}
function FColumn_onCellMouseDown(s, e){
   var o = this;
   var t = s.table;
   var r = s.row;
   t.__focusCell = s;
   t.selectRow(r, !e.ctrlKey, true);
   var fc = RConsole.find(FFocusConsole);
   var c = fc.focusControl;
   if(RClass.isClass(c, FDropEditor)){
      if(c.source == s){
         return;
      }
   }
   RConsole.find(FFocusConsole).focus(s);
}
function FColumn_onCellClick(s, e){
   this.table.clickRow(s.row);
}
function FColumn_onCellDoubleClick(s, e){
   var o = this;
   var r = s.row;
   if(!o.isEditAble(r)){
      o.table.doubleClickRow(r);
   }
}
function FColumn_onCellKeyDown(s, e, he){
   var o = this;
   if(he){
      o.table.onCellKeyDown(s, e, he);
   }
}
function FColumn_oeBuild(e) {
   var o = this;
   var t = o.table;
   o._absEdit = o.editInsert || o.editUpdate || o.editDelete;
   if(!o._absEdit){
	   if(!RString.isEmpty(o.lovRefer)){
		   o.hasDropArea = true;
	   }else{
         o.hasDropArea = false;
	   }
   }
   if (!RString.isEmpty(o.viewIcons)) {
      var im = o.iconMap = new TAttributes();
      im.split(o.viewIcons.replace(/\n/g, ';'), '=', ';');
      o.hasIconArea = im.count > 0;
   }
   o.base.FControl.oeBuild.call(o, e);
   var hp = o.hPanel;
   hp.style.backgroundImage = 'url(' + RResource.iconPath('ctl.FColumn_Head') + ')';
   hp.style.padding = 4;
   var hf = o.hForm = RBuilder.appendTable(hp);
   if (!o.orderAble) {
     hf.style.cursor = 'hand';
     o.attachEvent('onHeadMouseDown', hf);
   }
   var hr = o.hFormLine = o.hForm.insertRow();
   o.onBuildLabel();
   o.onBuildSearch();
   o.onBuildTotal();
   var h = o.hFixPanel = RBuilder.create(null, 'TD');
   h.height = 1;
   h.bgColor = '#FFFFFF'
   if(!o.width){
      o.width = 60;
   }
   o.hPanel.style.pixelWidth = o.width;
   o.hFixPanel.style.pixelWidth = o.width;
   return EEventStatus.Stop;
}
function FColumn_oeMode(e){
   var o = this;
   if(e.isAfter()){
      var d = false;
      if(EAction.Design == e.mode){
         d = o.dispDesign;
      }else{
         d = o.dispList;
      }
      o.inModeDisplay = d;
      o.setVisible(d);
   }
   return EEventStatus.Continue;
}
function FColumn_oeRefresh(e) {
   var o = this;
   if(e.isBefore()){
      o.setVisible(o.dispList);
   }
}
function FColumn_onBuildLabel(){
   var o = this;
   var hr = o.hFormLine;
   if (o.icon) {
      var hip = o.hIconPanel = hr.insertCell();
      o.hIcon = RBuilder.appendIcon(hip, o.icon);
   }
   if (o.label) {
      var hl = o.hLabel = hr.insertCell();
      hl.noWrap = true;
      hl.style.fontSize = '12';
      hl.style.fontWeight = 'bolder';
      hl.style.color = o.editUpdate ? EColor.TextEdit : EColor.TextReadonly;
      if(o.editUpdate && o.validRequire){
         hl.style.color = EColor.Require;
      }
      hl.align = o.labelAlign;
      hl.innerText = o.label;
   }
   var hsp = o.hSortPanel = hr.insertCell();
   var hsu = o.hSortUp = RBuilder.appendIcon(hsp, o.styleIcon('SortUp', FColumn));
   hsu.style.display = 'none';
   var hsu = o.hSortDown = RBuilder.appendIcon(hsp, o.styleIcon('SortDown', FColumn));
   hsu.style.display = 'none';
}
function FColumn_onBuildSearchEdit(){
   var o = this;
   var hc = o.hSearchEditPanel = o.hSearchFormLine.insertCell();
   var he = o.hSearchEdit = RBuilder.append(hc, 'INPUT', o.style('SearchEdit'));
   o.table.linkEvent(o, 'onColumnSearchKeyDown', he);
   o.attachEvent('onSearchClick', he);
   he.style.backgroundColor = "#FFFFFF";
   hc.style.backgroundColor = "#FFFFFF";
   if(!RString.isEmpty(o.editAlign)){
      he.style.textAlign = o.editAlign;
   }
}
function FColumn_onBuildSearchForm(){
   var o = this;
   var hf = o.hSearchForm = RBuilder.appendTable(o.hSearchPanel);
   hf.width = '100%';
   hf.style.backgroundColor = '#FFFFFF';
   var hfl = o.hSearchFormLine = hf.insertRow();
   if(RClass.isClass(o, FColumnButton)){
	   o.hSearchPanel.style.backgroundColor='#EEEFF1';
	   o.hSearchPanel.style.borderLeft='1 solid #808080';
	   o.hSearchPanel.style.borderTop='1 solid #808080';
	   o.hSearchPanel.style.borderBottom = '1 solid #9EC4EB';
	   return;
   }
   o.onBuildSearchIcon();
   o.onBuildSearchEdit();
   o.onBuildSearchDrop();
}
function FColumn_onBuildSearch(){
   var o = this;
   var h = o.hSearchPanel = RBuilder.create(null, 'TD', o.style('SearchPanel'));
   h.style.backgroundColor = "#FFFFFF";
   h.style.borderBottom = '1 solid #9EC4EB';
   RHtml.link(h, 'control', o);
  o.attachEvent('onSearchEnter', h);
  o.attachEvent('onSearchLeave', h);
  o.onBuildSearchForm();
}
function FColumn_onBuildTotal(){
   var o = this;
   var h = o.hTotalPanel = RBuilder.create(null, 'TD');
   RHtml.link(h, 'control', o);
   h.align = 'right';
   h.style.color = '#686860';
   h.style.backgroundColor = '#F8F8F0';
   h.style.borderBottom = '1 solid #B8B8B0';
   h.innerText = ' ';
}
function FColumn_onBuildPanel() {
   this.hPanel = RBuilder.create(null, 'TD');
}
function FColumn_onDataKeyDown(s, e) {
   var o = this;
   o.base.MEditDescriptor.onDataKeyDown.call(o, s, e);
}
function FColumn_onDataChanged(s, e) {
   var o = this;
   o.table.setDataStatus(s.row, EDataStatus.Update);
}
function FColumn_onEditBegin(editor) {
   var o = this;
   var row = editor.row;
   o.editor = editor;
   o.table.editRow = row;
   o.table.editColumn = o;
   o.table.select(row, true);
   RLogger.debug(o, 'Edit begin (column={1} row={2} editor={3})', o.name, RClass.dump(row), RClass.dump(editor));
}
function FColumn_onEditEnd(e) {
   var o = this;
   var row = editor.row;
   var text = editor.text();
   o.setValue(row, o.formatValue(text));
   o.setText(row, text);
   o.table.setDataStatus(row, row.isChanged() ? EDataStatus.Update : EDataStatus.Unknown)
   o.editor = null;
   RLogger.debug(o, '{1}={2}\n{3}\n{4}', RClass.dump(editor), o.formatValue(text), o.dump(), row.dump());
}
function FColumn_onEditChanged(cell) {
   cell.row.refresh();
}
function FColumn_onHeadMouseDown(e) {
   var o = this;
   var tbl = o.table;
   var ct = tbl.dsViewer.count;
   var x = e.x;
   if(!RClass.isClass(o, FColumnButton)){
	   var l = o.hPanel.offsetWidth;
	   var r = l - 6;
	   if (x > 0 && x < r) {
	      if (ct > 0 && !RClass.isClass(e.source, FColumnStatus)) {
	         var cs = tbl.columns;
	         var len = cs.count;
	         for ( var n = 0; n < len; n++) {
	            var c = cs.value(n);
	            c.hSortUp.style.display = 'none';
	            c.hSortDown.style.display = 'none';
	         }
	         tbl.dsOrders.clear();
	         var oi = new TOrderItem();
	         var n = o.dataName;
	         if (o.sortType) {
	            oi.set(n, EOrder.Desc);
	            o.hSortUp.style.display = 'none';
	            o.hSortDown.style.display = 'block';
	         } else {
	            o.hSortUp.style.display = 'block';
	            o.hSortDown.style.display = 'none';
	            oi.set(n, EOrder.Asc);
	         }
	         o.sortType = !o.sortType;
	         tbl.dsOrders.push(oi);
	         tbl.dsSearch();
	      }
   }
   }
}
function FColumn_onRowClick(s, e){
   RConsole.find(FListenerConsole).process(FGridControl, EGridAction.RowClick, s.row, s.row);
}
function FColumn_createCell() {
   var o = this;
   var c = RClass.create(o.__cellClass);
   c.name = o.name;
   c.table = o.table;
   c.column = o;
   c.build();
   c.setVisible(o.dispList);
   return c;
}
function FColumn_createMoveable(p) {
   var o = this;
   var r = o.cloneMove;
   if (!r) {
      r = RClass.create(o.constructor);
      r.buildMode = EColumnMode.Drag;
      r.assign(o, EAssign.Property);
      r.build();
      o.cloneMove = r;
   }
   var hc = o.panel(EPanel.Move);
   var hr = r.panel(EPanel.Move);
   RHtml.setPixelRect(hr, RHtml.rect(hc));
   hr.className = r.style('DesignMove');
   hr.style.pixelLeft = hc.offsetLeft;
   r.show();
   return r;
}
function FColumn_searchValue() {
   var o = this;
   if(o.hSearchEdit){
      return o.hSearchEdit.value;
   }
}
function FColumn_setStyleStatus(row, status) {
   var o = this;
   var h = o.cell(row);
   if (h) {
      var s = h.style;
      switch (status) {
      case EStyle.Normal:
         if (row.isDelete()) {
            s.backgroundColor = EColor.Delete;
         } else {
            if (o.isEditAble(row)) {
               s.backgroundColor = EColor.Edit;
            } else {
               s.backgroundColor = EColor.Readonly;
            }
         }
         break;
      case EStyle.Select:
         if (row.isDelete()) {
            s.backgroundColor = EColor.Select;
         } else {
            s.textDecoration = 'none';
            if (o.isEditAble(row)) {
               s.backgroundColor = EColor.RowEditSelect;
            } else {
               s.backgroundColor = EColor.Select;
            }
         }
         break;
      case EStyle.Delete:
         s.textDecoration = 'line-through';
         s.backgroundColor = EColor.Select;
         break;
      }
   }
}
function FColumn_cell(r){
   return r.cell(this.index);
}
function FColumn_equalsValue(s, t) {
   return RString.nvl(s).replace(/\n/g, '\\n').replace(/\r/g, '\\r') == RString.nvl(t).replace(/\n/g, '\\n').replace(/\r/g, '\\r');
}
function FColumn_setWidth(w){
   var o = this;
   o.hPanel.style.pixelWidth = w;
   o.hFixPanel.style.pixelWidth = w;
}
function FColumn_setVisible(v){
   var o = this;
   o.isDisplay = v;
   var s = v ? 'block' : 'none';
   o.hPanel.style.display = s;
   o.hSearchPanel.style.display = s;
   o.hTotalPanel.style.display = s;
   o.hFixPanel.style.display = s;
}
function FColumn_moveCellFocus(row, p) {
   var o = this;
   var t = o.table;
   var mt = null;
   var mr = null;
   var mc = null;
   if(EPosition.Top == p){
      mt = o;
      mr = t.rows.get(t.rows.indexOf(row) - 1);
      if(mr){
         mc = mr.cell(mt.index);
      }
   }else if(EPosition.Bottom == p){
      mt = o;
      mr = t.rows.get(t.rows.indexOf(row) + 1);
      if(mr){
         mc = mr.cell(mt.index);
      }
   }else if (EPosition.Before == p){
      var fi = o.index - 1;
      var ri = t.rows.indexOf(row);
      for(var n = ri; n >= 0; n--){
         var fr = t.rows.get(n);
         for( var i = fi; i >= 0; i--){
            var ft = t.columns.value(i);
            if(RClass.isClass(ft, FColumn) && ft.dispList){
               mt = ft;
               mr = fr;
               mc = mr.cell(mt.index);
               break;
            }
         }
         if(mt){
            break;
         }
         fi = t.columns.count - 1;
      }
   }else if(EPosition.After == p){
      var fi = o.index + 1;
      var ri = t.rows.indexOf(row);
      var cc = t.columns.count;
      var rc = t.rows.count;
      for(var n = ri; n < rc; n++){
         var fr = t.rows.get(n);
         for(var i = fi; i < cc; i++){
            var ft = t.columns.value(i);
            if(RClass.isClass(ft, FColumn) && ft.dispList){
               mt = ft;
               mr = fr;
               mc = mr.cell(mt.index);
               break;
            }
         }
         if(mt){
            break;
         }
         fi = 0;
      }
   }
   if(mt && mr && mc){
      mc.focus(true);
      RConsole.find(FFocusConsole).focus(mc);
   }
}
function FColumn_getEditRange(){
   var o = this;
   var hc = o.hSearchPanel;
   var p = RHtml.offsetPosition(hc);
   var w = hc.offsetWidth;
   var h = hc.offsetHeight;
   return new TRange(p.x, p.y, w, h);
}
function FColumn_dispose(){
   var o = this;
   o.base.FControl.dispose.call(o);
   RMemory.freeHtml(o.hSearchPanel);
   RMemory.freeHtml(o.hFixPanel);
   o.hForm = null;
   o.hFormLine = null;
   o.hIconPanel = null;
   o.hIcon = null;
   o.hHeadPanel = null;
   o.hLabel = null;
   o.hSortPanel = null;
   o.hSortUp = null;
   o.hSortDown = null;
   o.hSearchPanel = null;
   o.hSearchForm = null;
   o.hSearchFormLine = null;
   o.hSearchIconPanel = null;
   o.hSearchIcon = null;
   o.hSearchEditPanel = null;
   o.hSearchEdit = null;
   o.hSearchDropPanel = null;
   o.hSearchDrop = null;
   o.hFixPanel = null;
}
function FColumn_dump(s) {
   var o = this;
   s = RString.nvlStr(s);
   s.append(RClass.dump(o), '[');
   s.append('name=', o.name);
   s.appendIf(o.icon, ',icon=', o.icon);
   s.appendIf(o.label, ',label=', o.label);
   s.appendIf(o.align, ',align=', o.align);
   s.appendIf(o.valign, ',valign=', o.valign);
   s.appendIf(o.dataName, ',dataName=', o.dataName);
   s.appendIf(o.dataDefault, ',dataDefault=', o.dataDefault);
   s.appendIf(o.index, ',index=', o.index);
   s.append(']');
   s.append(' [editAccess=');
   s.append(o.editInsert ? 'I' : '_');
   s.append(o.editUpdate ? 'U' : '_');
   s.append(']');
   return s;
}
function FColumnBar(o){
   o = RClass.inherits(this, o, FColumn);
   o.__cellClass = FCellBar;
   o.icon        = RClass.register(o, new TPtyStr('icon'));
   return o;
}
function FColumnButton(o){
   o = RClass.inherits(this, o, FColumn);
   o.__cellClass = FCellButton;
   return o;
}
function FColumnCalendar(o){
   o = RClass.inherits(this, o, FColumnEditControl, MDescCalendar);
   o.editFormat        = RDate.DisplayFormat;
   o.__cellClass       = FCellCalendar;
   o.__searchValue     = null;
   o.hasDropArea       = true;
   o.date              = null;
   o.onBuildSearchEdit = FColumnCalendar_onBuildSearchEdit;
   o.onBuildSearchDrop = FColumnCalendar_onBuildSearchDrop;
   o.onSearchEnter     = FColumnCalendar_onSearchEnter;
   o.onSearchLeave     = FColumnCalendar_onSearcLeave;
   o.onSearchDblClick  = RClass.register(o, new HDoubleClick('onSearchDblClick'), FColumnCalendar_onSearchDblClick);
   o.onSearchDropClick = RClass.register(o, new HMouseDown('onSearchDropClick'), FColumnCalendar_onSearchDropClick);
   o.onCellDropClick   = RClass.register(o, new HMouseDown('onCellDropClick'), FColumnCalendar_onCellDropClick);
   o.editHour     = RClass.register(o, new TPtyBoolSet('editHour', 'editDate', EDateTimeMode.Hour));
   o.editMinute   = RClass.register(o, new TPtyBoolSet('editMinute', 'editDate', EDateTimeMode.Minute));
   o.editSecond   = RClass.register(o, new TPtyBoolSet('editSecond', 'editDate', EDateTimeMode.Second));
   o.onCellEditEnd     = FColumnCalendar_onCellEditEnd;
   o.construct         = FColumnCalendar_construct;
   o.buildSearchEdit   = FColumnCalendar_buildSearchEdit;
   o.formatValue       = MDescCalendar_formatValue;
   o.formatText        = MDescCalendar_formatText;
   o.searchValue       = FColumnCalendar_searchValue;
   o.searchDrop        = FColumnCalendar_searchDrop;
   o.focus             = FColumnCalendar_focus;
   o.dispose           = FColumnCalendar_dispose;
   return o;
}
function FColumnCalendar_onCellEditEnd(e){
   var o = this;
   var c = e.source;
   c.set(e.get());
   c.onDataEditEnd(c);
   c.focus();
}
function FColumnCalendar_construct(){
   var o = this;
   o.base.FColumnEditControl.construct.call(o);
   o.lsnCellEditEnd = new TListener(o, o.onCellEditEnd);
   o.__searchValue = new Object();
   o.date = new TDate();
}
function FColumnCalendar_buildSearchEdit(p, hr){
   var o = this;
   var hc = hr.insertCell();
   hc.style.padding = '0 2';
   var he = o['h' + p[0]] = RBuilder.append(hc, 'INPUT');
   he.size = p[1] - 1;
   he.maxLength = p[1];
   he.style.textAlign = 'right';
   he.style.backgroundColor = '#FFFFFF';
   he.style.borderLeft = '1 solid #CCCCCC';
   he.style.borderTop = '1 solid #CCCCCC';
   he.style.borderRight = '1 solid #EEEEEE';
   he.style.borderBottom = '1 solid #EEEEEE';
   o.table.linkEvent(o, 'onColumnSearchKeyDown', he);
}
function FColumnCalendar_onBuildSearchEdit(){
   var o = this;
   var t = o.table;
   var hp = o.hSearchEditPanel = o.hSearchFormLine.insertCell();
   var hf = RBuilder.appendTable(hp)
   var hr = hf.insertRow();
   var ps = o.__parts = RString.splitPattern(o.editFormat, RDate.Parts);
   for(var n=0; n<ps.length; n++){
      var p = ps[n];
      if(RString.inRange(p, RDate.Parts)){
         o.buildSearchEdit(RDate.PartsDefine[p], hr);
      }else{
         var hc = hr.insertCell();
         hc.innerText = p;
      }
   }
}
function FColumnCalendar_onBuildSearchDrop(){
   var o = this;
}
function FColumnCalendar_onSearchEnter(){
   var o = this;
}
function FColumnCalendar_onSearcLeave(){
   var o = this;
}
function FColumnCalendar_onSearchDblClick(){
   this.searchDrop();
}
function FColumnCalendar_onSearchDropClick(e){
   this.searchDrop();
}
function FColumnCalendar_onCellDropClick(s, e){
   var o = this;
   o.onCellMouseDown(s, e);
   s.drop();
}
function FColumnCalendar_searchValue(){
   var o = this;
   var ps = o.__parts;
   var v = '';
   var f = '';
   for(var n=0; n<ps.length; n++){
      var p = ps[n];
      if(RString.inRange(p, RDate.Parts)){
         var pd = RDate.PartsDefine[p];
         var s = o['h' + pd[0]].value;
         if(!RString.isEmpty(s)){
            var pd = RDate.PartsDefine[p];
            v += RInteger.format(s, pd[1]);
            f += p;
         }
      }
   }
   var sv = o.__searchValue;
   sv.value = v;
   sv.format = f;
   return RString.isEmpty(v) ? null : sv;
}
function FColumnCalendar_searchDrop(){
   var o = this;
   var ed = o.editor;
   if(!ed){
      ed = o.editor = RClass.create(FCalendarEditor);
      ed.psBuild();
   }
   ed.source = o;
   ed.editable = o;
   RHtml.toRect(ed.rect, o.hSearchPanel);
   RHtml.setPixelRect(ed.hPanel, ed.rect);
   ed.hPanel.style.pixelTop = ed.rect.bottom + 3;
   var hbf = ed.border.hForm;
   hbf.style.pixelWidth = o.hSearchPanel.width;
   ed.hPanel.style.width = 273;
   ed.setValue(o.hSearchEdit.value);
   ed.show();
}
function FColumnCalendar_focus(){
   this.hSearchEdit.focus();
}
function FColumnCalendar_dispose(){
   var o = this;
   o.base.FColumnEditControl.dispose.call(o);
   o.hSearchPanel = null;
   var ps = o.__parts;
   if(ps){
      for(var n=0; n<ps.length; n++){
         var p = ps[n];
         if(RString.inRange(p, RDate.Parts)){
            o['h' + RDate.PartsDefine[p][0]] = null;
         }
      }
   }
}
function FColumnCheck(o){
   o = RClass.inherits(this, o, FColumnEditControl, MDescCheck);
   o.iconCheck   = 'ctl.radios'
   o.iconUncheck = 'ctl.radiou';
   o.__cellClass = FCellCheck;
   o.equalsValue = FColumnCheck_equalsValue;
   return o;
}
function FColumnCheck_equalsValue(rv, cv){
   return RBool.isTrue(rv) == RBool.isTrue(cv);
}
function FColumnColorPicker(o){
   o = RClass.inherits(this, o, FColumnEditControl, MDescColor);
   o.__cellClass = FCellColorPicker;
   return o;
}
function FColumnDate(o){
   o = RClass.inherits(this, o, FColumnEditControl, MDescCalendar);
   o.editFormat        = RDate.DisplayFormat;
   o.__cellClass       = FCellCalendar;
   o.__searchValue     = null;
   o.hasDropArea       = true;
   o.date              = null;
   o.onBuildSearchEdit = FColumnDate_onBuildSearchEdit;
   o.onBuildSearchDrop = FColumnDate_onBuildSearchDrop;
   o.onSearchEnter     = FColumnDate_onSearchEnter;
   o.onSearchLeave     = FColumnDate_onSearcLeave;
   o.onSearchDblClick  = RClass.register(o, new HDoubleClick('onSearchDblClick'), FColumnDate_onSearchDblClick);
   o.onSearchDropClick = RClass.register(o, new HMouseDown('onSearchDropClick'), FColumnDate_onSearchDropClick);
   o.onCellDropClick   = RClass.register(o, new HMouseDown('onCellDropClick'), FColumnDate_onCellDropClick);
   o.construct         = FColumnDate_construct;
   o.buildSearchEdit   = FColumnDate_buildSearchEdit;
   o.validText         = MDescCalendar_validText;
   o.formatValue       = MDescCalendar_formatValue;
   o.formatText        = MDescCalendar_formatText;
   o.searchValue       = FColumnDate_searchValue;
   o.searchDrop        = FColumnDate_searchDrop;
   o.focus             = FColumnDate_focus;
   o.dispose           = FColumnDate_dispose;
   return o;
}
function FColumnDate_construct(){
   var o = this;
   o.base.FColumnEditControl.construct.call(o);
   o.__searchValue = new Object();
   o.date = new TDate();
}
function FColumnDate_buildSearchEdit(p, hr){
   var o = this;
   var hc = hr.insertCell();
   hc.style.padding = '0 2';
   var he = o['h' + p[0]] = RBuilder.append(hc, 'INPUT');
   he.size = p[1] - 1;
   he.maxLength = p[1];
   he.style.textAlign = 'right';
   he.style.backgroundColor = '#eff6ff';
   he.style.borderLeft = '1 solid #CCCCCC';
   he.style.borderTop = '1 solid #CCCCCC';
   he.style.borderRight = '1 solid #EEEEEE';
   he.style.borderBottom = '1 solid #EEEEEE';
   o.table.linkEvent(o, 'onColumnSearchKeyDown', he);
}
function FColumnDate_onBuildSearchEdit(){
   var o = this;
   var t = o.table;
   var hp = o.hSearchEditPanel = o.hSearchFormLine.insertCell();
   var hf = RBuilder.appendTable(hp)
   var hr = hf.insertRow();
   var ps = o.__parts = RString.splitPattern(o.editFormat, RDate.Parts);
   for(var n=0; n<ps.length; n++){
      var p = ps[n];
      if(RString.inRange(p, RDate.Parts)){
         o.buildSearchEdit(RDate.PartsDefine[p], hr);
      }else{
         var hc = hr.insertCell();
         hc.innerText = p;
      }
   }
}
function FColumnDate_onBuildSearchDrop(){
   var o = this;
}
function FColumnDate_onSearchEnter(){
   var o = this;
}
function FColumnDate_onSearcLeave(){
   var o = this;
}
function FColumnDate_onSearchDblClick(){
   this.searchDrop();
}
function FColumnDate_onSearchDropClick(e){
   this.searchDrop();
}
function FColumnDate_onCellDropClick(s, e){
   if(!this.disabled){
      s.drop();
   }
}
function FColumnDate_searchValue(){
   var o = this;
   var ps = o.__parts;
   var v = '';
   var f = '';
   for(var n=0; n<ps.length; n++){
      var p = ps[n];
      if(RString.inRange(p, RDate.Parts)){
         var pd = RDate.PartsDefine[p];
         var s = o['h' + pd[0]].value;
         if(!RString.isEmpty(s)){
            var pd = RDate.PartsDefine[p];
            v += RInteger.format(s, pd[1]);
            f += p;
         }
      }
   }
   var sv = o.__searchValue;
   sv.value = v;
   sv.format = f;
   return RString.isEmpty(v) ? null : sv;
}
function FColumnDate_searchDrop(){
   var o = this;
   var ed = o.editor;
   if(!ed){
      ed = o.editor = RClass.create(FCalendarEditor);
      ed.psBuild();
   }
   ed.source = o;
   ed.editable = o;
   RHtml.toRect(ed.rect, o.hSearchPanel);
   RHtml.setPixelRect(ed.hPanel, ed.rect);
   ed.hPanel.style.pixelTop = ed.rect.bottom + 3;
   var hbf = ed.border.hForm;
   hbf.style.pixelWidth = o.hSearchPanel.width;
   ed.hPanel.style.width = 273;
   ed.setValue(o.hSearchEdit.value);
   ed.show();
}
function FColumnDate_focus(){
   this.hSearchEdit.focus();
}
function FColumnDate_dispose(){
   var o = this;
   o.base.FColumnEditControl.dispose.call(o);
   o.hSearchPanel = null;
   var ps = o.__parts;
   for(var n=0; n<ps.length; n++){
      var p = ps[n];
      if(RString.inRange(p, RDate.Parts)){
         o['h' + RDate.PartsDefine[p][0]] = null;
      }
   }
}
function FColumnEdit(o){
   o = RClass.inherits(this, o, FColumnEditControl, MDescEdit);
   o.__cellClass    = FCellEdit;
   o.hasDropArea    = true;
   o.onCellMouseEnter = FColumnEdit_onCellMouseEnter;
   o.onCellMouseLeave = FColumnEdit_onCellMouseLeave;
   o.onListClick      = FColumnEdit_onListClick;
   o.onZoomClick      = RClass.register(o, new HClick('onZoomClick'), FColumnEdit_onZoomClick);
   o.onZoomHover      = RClass.register(o, new HMouseEnter('onZoomHover'), FColumnEdit_onZoomHover);
   o.onZoomLeave      = RClass.register(o, new HMouseLeave('onZoomLeave'), FColumnEdit_onZoomLeave);
   return o;
}
function FColumnEdit_onCellMouseEnter(s, e){
   if(s.hLovImage){
   }
}
function FColumnEdit_onCellMouseLeave(s, e){
   if(s.hLovImage){
   }
}
function FColumnEdit_onListClick(s, e){
   var o = this;
   o.table.__focusCell = s;
   var cvs = s.row.saveRow().toAttributes();
   o.doListView(cvs);
}
function FColumnEdit_onZoomHover(s, e){
   s.hEdit.style.color='black';
}
function FColumnEdit_onZoomLeave(s, e){
   s.hEdit.style.color='blue';
}
function FColumnEdit_onZoomClick(s, e){
   var o = this;
   o.table.clickRow(s.row);
   var r = s.row.saveRow();
   var v = r.get(o.zoomField)
   if(!RString.isEmpty(v)){
      o.doZoom(v);
   }
}
function FColumnEditControl(o){
   o = RClass.inherits(this, o, FColumn);
   o.isEditAble = FColumnEditControl_isEditAble;
   return o;
}
function FColumnEditControl_isEditAble(r){
   var o = this;
   if(r){
      return (ERowStatus.Insert == r.status) ? o.editInsert : o.editUpdate;
   }
}
function FColumnEditor(o){
   o = RClass.inherits(this, o, FHoverEditor, MEventKey);
   o.pattern      = null;
   o.table        = null;
   o.column       = null;
   o.row          = null;
   o.storeNodes   = new Object();
   o.hStore       = null;
   o.onKeyDown    = FColumnEditor_onKeyDown;
   o.onKeyUp      = FColumnEditor_onKeyUp;
   o.onBuildEdit  = FColumnEditor_onBuildEdit;
   o.text         = FColumnEditor_text;
   o.setText      = FColumnEditor_setText;
   o.focus        = RMethod.empty;
   o.value        = FColumnEditor_value;
   o.setValue     = FColumnEditor_setValue;
   o.linkPanel    = FColumnEditor_linkPanel;
   return o;
}
function FColumnEditor_onKeyDown(e){
   var o = this;
   var k = e.keyCode;
   if(EKey.Up == k){
      o.column.moveFocus(o.row, EPosition.Top);
      e.returnValue = false;
   }else if(EKey.Down == k){
      o.column.moveFocus(o.row, EPosition.Bottom);
      e.returnValue = false;
   }else if(e.shiftKey && EKey.Tab == k){
      o.column.moveFocus(o.row, EPosition.Before);
      e.returnValue = false;
   }else if(EKey.Tab == k){
      o.column.moveFocus(o.row, EPosition.After);
      e.returnValue = false;
   }
}
function FColumnEditor_onKeyUp(){
   var o = this.link;
   if(RClass.isClass(o, FColumnEditor)){
      o.onEditChanged();
   }
}
function FColumnEditor_onBuildEdit(event){
   var o = this;
   o.hForm = RBuilder.newTable();
   var hRow = o.hForm.insertRow();
   var hCel = hRow.insertCell();
   o.hEdit = RBuilder.append(hCel, 'INPUT', o.style('Edit'));
   o.linkEvent(o.hEdit);
   return EEventStatus.Stop;
}
function FColumnEditor_text(){
   return this.hEdit.value;
}
function FColumnEditor_setText(text){
   this.changed = false;
   this.storedText = text;
   this.hEdit.value = text;
}
function FColumnEditor_value(){
}
function FColumnEditor_setValue(){
}
function FColumnEditor_linkPanel(t, c, r){
   var o = this;
   RLog.debug(o, 'Link panel (table={1},editable={2})\ncolumn={3}\nrow={4}', RClass.dump(t), RClass.dump(o.editable), c.dump(), r.dump())
   var hp = t.hRowsPanel;
   var he = c.cell(r);
   o.table = t;
   o.column = c;
   o.row = r;
   RHtml.toRect(o.rect, he, hp);
   RHtml.setRect(o.hPanel, o.rect);
   var s = o.hEdit.style;
   s.pixelWidth = he.clientWidth;
   s.pixelHeight = he.offsetHeight-2;
   s.paddingLeft = parseInt(he.currentStyle.paddingLeft);
   s.paddingTop = parseInt(he.currentStyle.paddingTop)-1;
   if(r.isSelect){
      s.backgroundColor = EColor.RowEditSelect;
   }else{
      s.backgroundColor = EColor.Edit;
   }
   o.setText(r.get(c.dataName));
   o.base.FHoverEditor.linkPanel.call(o, hp, he);
}
function FColumnEmpty(o){
   o = RClass.inherits(this, o, FColumn);
   o.dispList          = true;
   o.onBuildSearchForm = RMethod.empty;
   return o;
}
function FColumnIcon(o){
   o = RClass.inherits(this, o, FColumn);
   o.__cellClass = FCellIcon;
   return o;
}
function FColumnNumber(o){
   o = RClass.inherits(this, o, FColumnEditControl, MDescNumber, MListView);
   o.__cellClass               = FCellNumber;
   o.hasDropArea               = true;
   o.editAlign                 = EAlign.Right;
   o.onBuildSearch             = FColumnNumber_onBuildSearch;
   o.onUpIconMouseDown         = RClass.register(o, new HMouseDown('onUpIconMouseDown'), FColumnNumber_onUpIconMouseDown);
   o.onUpIconMouseUp           = RClass.register(o, new HMouseUp('onUpIconMouseUp'), FColumnNumber_onUpIconMouseUp);
   o.onSearchEditFocus         = RClass.register(o, new HFocus('onSearchEditFocus'), FColumnNumber_onSearchEditFocus);
   o.onSearchEditBlur          = RClass.register(o, new HBlur('onSearchEditBlur'), FColumnNumber_onSearchEditBlur);
   o.onSearchEditDown          = RClass.register(o, new HKeyDown('onSearchEditDown'), FColumnNumber_onSearchEditDown);
   o.onSearchEditUpIconDown    = RClass.register(o, new HMouseDown('onSearchEditUpIconDown'), FColumnNumber_onSearchEditUpIconDown);
   o.onSearchEditDownIconDown  = RClass.register(o, new HMouseDown('onSearchEditDownIconDown'), FColumnNumber_onSearchEditDownIconDown);
   o.onSearchEditUpIconUp      = RClass.register(o, new HMouseUp('onSearchEditUpIconUp'), FColumnNumber_onSearchEditUpIconUp);
   o.onSearchEditDownIconUp    = RClass.register(o, new HMouseUp('onSearchEditDownIconUp'), FColumnNumber_onSearchEditDownIconUp);
   o.onCellDoubleClick         = FColumnNumber_onCellDoubleClick;
   o.ohEditKeyDown             = FColumnNumber_ohEditKeyDown;
   o.onListSelected            = FColumnNumber_onListSelected;
   o.changeText                = FColumnNumber_changeText;
   o.dispose                   = FColumnNumber_dispose;
   return o;
}
function FColumnNumber_onListSelected(v){
   this.table.__focusCell.row.refresh();
}
function FColumnNumber_onCellDoubleClick(s, e){
   var o = this;
   var cvs = s.row.saveRow().toAttributes();
   if(o.lovRefer){
      return o.doListView(cvs);
   }
   return o.base.FColumnEditControl.onCellDoubleClick.call(o, s, e);
}
function FColumnNumber_onBuildSearch(){
   var o = this;
   var h = o.hSearchPanel = RBuilder.create(null, 'TD', o.style('SearchPanel'));
   var hp = RBuilder.appendTable(h);
   hp.style.width = '100%';
   hp.align = 'right';
   hp.style.border = '0px solid blue';
   var hr = hp.insertRow();
   var hc1 = hr.insertCell();
   var he = o.hSearchEdit = RBuilder.append(hc1, 'INPUT');
   o.attachEvent('onSearchEditDown', he, o.ohEditKeyDown);
   o.attachEvent('onSearchEditBlur', he);
   o.attachEvent('onSearchEditFocus', he);
   he.align = 'right';
   he.style.width = '100%';
   he.style.textAlign = 'right';
   he.style.border = '0px solid blue';
   he.style.backgroundColor = '#FFFFFF';
   var hc2 = hr.insertCell();
   hc2.width = 1;
   var htb = RBuilder.appendTable(hc2);
   var hr1 = htb.insertRow();
   var hr2 = htb.insertRow();
   var hrc1 = hr1.insertCell();
   var hrc2 = hr2.insertCell();
   var hImg1 = o.hImg1 = RBuilder.append(hrc1, 'IMG');
   var hImg2 = o.hImg2 = RBuilder.append(hrc2, 'IMG');
   o.attachEvent('onSearchEditUpIconUp', hImg1);
   o.attachEvent('onSearchEditDownIconUp', hImg2);
   o.attachEvent('onSearchEditUpIconDown', hImg1);
   o.attachEvent('onSearchEditDownIconDown', hImg2);
   hImg1.src = o.styleIconPath( 'Up', FCellNumber);
   hImg2.src = o.styleIconPath( 'Down', FCellNumber);
   if(o.table){
      o.table.linkEvent(o, 'onColumnSearchKeyDown', o.hSearchEdit);
   }
}
function FColumnNumber_ohEditKeyDown(e, he){
   var o = this;
   var kc = he.keyCode;
   if(o.hSearchEdit){
	   hs = o.hSearchEdit;
   }else{
      hs = he.srcElement;
   }
   if(EKey.Up == kc){
      hs.value = RInt.parse(RInt.nvl(hs.value)) + 1;
   }else if(EKey.Down == kc){
      hs.value = RInt.parse(RInt.nvl(hs.value)) - 1;
   }
   if(RKey.isNumKey(kc)){
	   kc = kc - 48;
   }
   if(!EKey.floatCodes[kc]){
      RKey.eventClear(he);
   }
}
function FColumnNumber_onSearchEditDown(e){
   var o = this;
}
function FColumnNumber_onSearchEditUpIconDown(e){
   var he = this.hSearchEdit;
   he.value = RInt.parse(RInt.nvl(he.value)) + 1;
   e.hSource.src = this.styleIconPath('UpSelect', FCellNumber);
}
function FColumnNumber_onSearchEditDownIconDown(e){
   var he = this.hSearchEdit;
   he.value = RInt.parse(RInt.nvl(he.value)) - 1;
   e.hSource.src = this.styleIconPath('DownSelect', FCellNumber);
}
function FColumnNumber_onSearchEditUpIconUp(e){
   e.hSource.src = this.styleIconPath('Up', FCellNumber);
}
function FColumnNumber_onSearchEditDownIconUp(e){
   e.hSource.src = this.styleIconPath('Down', FCellNumber);
}
function FColumnNumber_onSearchEditBlur(s, e){
   var o = this;
}
function FColumnNumber_onSearchEditFocus(s, e){
   var o = this;
}
function FColumnNumber_onUpIconMouseUp(s, e){
   var o = this;
   var t = e.sender;
   if(o.isEditAble()){
      e.hSource.src = e.sender.styleIconPath('Up');
   }
}
function FColumnNumber_onUpIconMouseDown(s, e){
   var o = this;
   var t = e.sender;
   if(o.isEditAble()){
      e.hSource.src = t.styleIconPath('UpSelect');
      o.changeText(t, true);
   }
}
function FColumnNumber_onDownIconMouseDown(s, e){
   var o = this;
   var t = e.sender;
   if(o.isEditAble()){
      e.hSource.src = e.sender.styleIconPath('DownSelect');
      o.changeText(t, false);
   }
}
function FColumnNumber_onDownIconMouseUp(s, e){
   var o = this;
   var t = e.sender;
   if(o.isEditAble()){
      e.hSource.src = e.sender.styleIconPath('Down');
   }
}
function FColumnNumber_changeText(c, f){
   var o = this;
   var v = RFloat.parse(o.formatValue(c.text()));
   if(f){
      v += RFloat.parse(o.editIncreate);
   }else{
      v -= RFloat.parse(o.editIncreate);
   }
   c.setText(o.formatText(v.toString()));
}
function FColumnNumber_dispose(){
   var o = this;
   o.base.FColumnEditControl.dispose.call(o);
   o.hSearchPanel = null;
   o.hImg1 = null;
   o.hImg2 = null;
}
function FColumnPicture(o){
   o = RClass.inherits(this, o, FColumnEditControl, MDescPicture);
   o.__cellClass = FCellPicture;
   return o;
}
function FColumnProcessStatus(o){
   o = RClass.inherits(this, o, FColumnEditControl, MDescProcessStatus);
   o.__cellClass = FCellProcessStatus;
   return o;
}
function FColumnProgress(o){
   o = RClass.inherits(this, o, FColumnEditControl, MDescProgress);
   o.cellClass = FCellProgress;
   return o;
}
function FColumnProgressBar(o){
   o = RClass.inherits(this, o, FColumn);
   o.dispStyle   = RClass.register(o, new TPtyStr('dispStyle'));
   o.__cellClass = FCellProgressBar;
   return o;
}
function FColumnRadio(o){
   o = RClass.inherits(this, o, FColumn, MDescCheck);
   o.cellClass   = FCellCheck;
   o.equalsValue = FColumnRadio_equalsValue;
   return o;
}
function FColumnRadio_equalsValue(rv, cv){
   return RBool.isTrue(rv) == RBool.isTrue(cv);
}
function FColumnRadioGroup(o){
   o = RClass.inherits(this, o, FColumnEditControl);
   o.__cellClass = FCellRadioGroup;
   return o;
}
function FColumnReference(o){
   o = RClass.inherits(this, o, FColumn);
   o.cellClass = FCellReference;
   return o;
}
function FColumnSelect(o){
   o = RClass.inherits(this, o, FColumnEditControl, MDescSelect);
   o.__cellClass         = FCellSelect;
   o.hasDropArea         = true;
   o.items               = new TItems();
   o.lsnCellEditEnd      = null;
   o.lsnSearchEditEnd    = null;
   o.onBuildSearchEdit   = FColumnSelect_onBuildSearchEdit;
   o.onBuildSearchDrop   = FColumnSelect_onBuildSearchDrop;
   o.onSearchEnter       = FColumnSelect_onSearchEnter;
   o.onSearchLeave       = FColumnSelect_onSearchLeave;
   o.onSearchKeyDown     = FColumnSelect_onSearchKeyDown;
   o.onSearchDblClick    = RClass.register(o, new HDoubleClick('onSearchDblClick'), FColumnSelect_onSearchDblClick);
   o.onSearchDropClick   = RClass.register(o, new HMouseDown('onSearchDropClick'), FColumnSelect_onSearchDropClick);
   o.onCellDropClick     = RClass.register(o, new HMouseDown('onCellDropClick'), FColumnSelect_onCellDropClick);
   o.onCellDoubleClick   = FColumnSelect_onCellDoubleClick;
   o.onCellEditEnd       = FColumnSelect_onCellEditEnd;
   o.onSearchEditEnd     = FColumnSelect_onSearchEditEnd;
   o.construct           = FColumnSelect_construct;
   o.loadConfig          = FColumnSelect_loadConfig;
   o.formatValue         = FColumnSelect_formatValue;
   o.formatText          = FColumnSelect_formatText;
   o.searchValue         = FColumnSelect_searchValue;
   o.set                 = FColumnSelect_setSearchValue;
   o.searchDrop          = FColumnSelect_searchDrop;
   return o;
}
function FColumnSelect_onCellEditEnd(e){
   var o = this;
   var c = e.source;
   c.set(e.get());
   c.focus();
}
function FColumnSelect_onSearchEditEnd(e){
   var o = this;
   o.hSearchEdit.value = o.items.label(e.get());
}
function FColumnSelect_construct(){
   var o = this;
   o.base.FColumnEditControl.construct.call(o);
   o.lsnCellEditEnd = new TListener(o, o.onCellEditEnd);
   o.lsnSearchEditEnd = new TListener(o, o.onSearchEditEnd);
}
function FColumnSelect_onBuildSearchEdit(){
   var o = this;
   o.base.FColumnEditControl.onBuildSearchEdit.call(o);
   var he = o.hSearchEdit;
   o.attachEvent('onSearchKeyDown', he);
   o.attachEvent('onSearchDblClick', he);
}
function FColumnSelect_onBuildSearchDrop(){
   var o = this;
   var hdp = o.hSearchDropPanel = o.hSearchFormLine.insertCell();
   hdp.width = 1;
   var hd = o.hSearchDrop = RBuilder.appendIcon(hdp, o.styleIcon('SearchDrop', FColumn));
   o.attachEvent('onSearchDropClick', hd);
}
function FColumnSelect_onSearchEnter(){
   var o = this;
   o.hSearchDrop.src = o.styleIconPath('SearchDropHover', FColumn);
}
function FColumnSelect_onSearchLeave(){
   var o = this;
   o.hSearchDrop.src = o.styleIconPath('SearchDrop', FColumn);
}
function FColumnSelect_onSearchKeyDown(e){
   var o = this;
   if(EKey.Down == e.keyCode){
      o.searchDrop();
   }
}
function FColumnSelect_onSearchDblClick(){
   var o = this;
   o.searchDrop();
}
function FColumnSelect_onSearchDropClick(e){
   var o = this;
   o.searchDrop();
}
function FColumnSelect_onCellDropClick(s, e){
   var o = this;
   if(!o.disabled){
      o.onCellMouseDown(s, e);
      s.drop();
    }
}
function FColumnSelect_onCellDoubleClick(s, e){
   var o = this;
   if(o.isEditAble(s)){
      return o.onCellDropClick(s, e);
   }
   return o.base.FColumnEditControl.onCellDoubleClick.call(o, s, e);
}
function FColumnSelect_loadConfig(c){
   var o = this;
   o.base.FColumnEditControl.loadConfig.call(o, c);
   if(o.dataEmpty){
      o.items.create();
   }
   o.items.loadConfig(c);
   return EStatus.Stop;
}
function FColumnSelect_formatValue(t){
   var o = this;
   if(RBool.isTrue(o.editCheck)){
      var v = this.items.value(t);
      if(v){
         return v;
      }else{
         return RString.nvl(t);
      }
   }
   return this.items.value(t);
}
function FColumnSelect_formatText(v){
   var o = this;
   if(RBool.isTrue(o.editCheck) && RString.isEmpty(o.items.label(v))){
      return v;
   }
   return o.items.label(v);
}
function FColumnSelect_searchValue(){
   var o = this;
   if(o.hSearchEdit){
      var s = o.hSearchEdit.value;
      return RString.nvl(o.items.value(s));
   }
}
function FColumnSelect_setSearchValue(s){
   var o = this;
   o.hSearchEdit.value = s;
}
function FColumnSelect_searchDrop(){
   var o = this;
   if(o.items){
      var e = RConsole.find(FEditConsole).focus(o, FSelectEditor, o.name);
      e.lsnEditEnd = o.lsnSearchEditEnd;
      e.setItems(o.items);
      e.set(o.hSearchEdit.value);
      e.show();
   }
}
function FColumnSelected(o){
   o = RClass.inherits(this, o, FColumnEditControl);
   o.onCellClick      = FColumnSelected_onCellClick;
   o.onSelectedClick  = FColumnSelected_onSelectedClick;
   o.oeBuild          = FColumnSelected_oeBuild;
   o.dispList            = true;
   o.dispFixed           = true;
   o.dataName            = '_select';
   o.styleAlign          = 'left';
   o.hSelected           = null;
   o.__cellClass         = FCellSelected;
   o.width               = 20;
   o.dispSize            = false;
   o.dispDrag            = false;
   o.stEdit              = RClass.register(o, new TStyle('Edit'));
   o.setVisible          = FColumnSelected_setVisible;
   o.lsnsHeadClick       = new TListeners();
   o.lsnsRowDblClick     = new TListeners();
   o.onBuildSearchForm   = FColumnSelected_onBuildSearchForm;
   o.createCell          = FColumnSelected_createCell;
   o.dispose             = FColumnSelected_dispose;
   return o;
}
function FColumnSelected_setVisible(){
	var o = this;
	var v = o.table.dispSelected ? 'block' : 'none';
	o.hPanel.style.display = v
	o.hSelected.style.display = v;
	o.hSearchPanel.style.display = v;
	o.hTotalPanel.style.display = v;
	o.hFixPanel.style.display = v;
}
function FColumnSelected_onCellClick(s, e){
	return;
}
function FColumnSelected_onSelectedClick(s, e){
	var o = this;
	var c = o.column;
	var rs = c.table.rows;
    var rc = rs.count;
    for(var n = 0; n<rc; n++){
       var r = rs.get(n);
       if(r.selectAble){
	       if(o.checked){
	          c.table.selectRow(r, false, true);
	       }else{
	    	   c.table.clearSelectRow(r);
	       }
       }
    }
}
function FColumnSelected_oeBuild(e){
   var o = this;
   var r = o.base.FColumnEditControl.oeBuild.call(o, e);
   var h = o.hPanel;
   h.align = 'center';
   RBuilder.appendEmpty(o.hPanel, 12, 12);
   return r;
}
function FColumnSelected_onBuildSearchForm(){
   var o = this;
   var hf = o.hSearchForm = RBuilder.appendTable(o.hSearchPanel);
   hf.width = '100%';
   var hfl = o.hSearchFormLine = hf.insertRow();
   var hc = hfl.insertCell();
   hc.align = 'center';
   o.hSelected = RBuilder.appendCheck(hc, o.style('Edit'));
   o.hSelected.column = o;
   o.hSelected.onclick = o.onSelectedClick;
}
function FColumnSelected_createCell(row){
   var o = this;
   var c = o.base.FColumnEditControl.createCell.call(o, row);
   if(row){
      row.cellSelect = c;
   }
   c.hPanel.className = c.style('Panel');
   return c;
}
function FColumnSelected_dispose(){
   var o = this;
   o.base.FColumnEditControl.dispose.call(o);
   o.hSelect = null;
}
function FColumnStatus(o){
   o = RClass.inherits(this, o, FColumnEditControl);
   o.stIconNormal     = RClass.register(o, new TStyleIcon('Normal'));
   o.stIconNormal     = RClass.register(o, new TStyleIcon('NormalEnter'));
   o.stIconInsert     = RClass.register(o, new TStyleIcon('Insert'));
   o.stIconChanged    = RClass.register(o, new TStyleIcon('Changed'));
   o.stIconDelete     = RClass.register(o, new TStyleIcon('Delete'));
   o.stIconInvalid    = RClass.register(o, new TStyleIcon('Invalid'));
   o.stIconLock       = RClass.register(o, new TStyleIcon('Lock'));
   o.onCellClick      = FColumnStatus_onCellClick;
   o.oeBuild          = FColumnStatus_oeBuild;
   o.setDataStatus    = FColumnStatus_setDataStatus;
   o.dispList            = true;
   o.dispFixed           = true;
   o.dataName            = '_status';
   o.styleAlign          = 'left';
   o.hSelect             = null;
   o.iconNormal          = 'tool.normal';
   o.iconInsert          = 'tool.insert';
   o.iconUpdate          = 'tool.update';
   o.iconDelete          = 'tool.delete';
   o.__cellClass         = FCellStatus;
   o.width               = 20;
   o.dispSize            = false;
   o.dispDrag            = false;
   o.lsnsHeadClick       = new TListeners();
   o.lsnsRowDblClick     = new TListeners();
   o.ohCellMdclk         = FColumnStatus_ohCellMdclk;
   o.onBuildSearchForm   = FColumnStatus_onBuildSearchForm;
   o.createCell          = FColumnStatus_createCell;
   o.dispose             = FColumnStatus_dispose;
   return o;
}
function FColumnStatus_onCellClick(s, e){
	return;
   if(this.table.callEvent('onTableRowDoubleClick', s.row)){
      return;
   }
   RConsole.find(FListenerConsole).process(FGridControl, EGridAction.RowClick, s.row, s.row);
}
function FColumnStatus_oeBuild(e){
   var o = this;
   var r = o.base.FColumnEditControl.oeBuild.call(o, e);
   var h = o.hPanel;
   h.align = 'center';
   RBuilder.appendEmpty(o.hPanel, 12, 12);
   return r;
}
function FColumnStatus_setDataStatus(r, s){
   var o = this;
   var t = o.table;
   var c = r.getStatus();
   var p = null;
   switch(s){
      case EDataStatus.Insert:
         p = 'Insert';
         break;
      case EDataStatus.Delete:
         p = 'Delete';
         break;
      default:
         if(r.isDataChanged()){
            p = 'Changed';
         }else{
            p = t.isFormLinked() ? 'NormalEnter' : 'Normal';
         }
         break;
   }
   c.setIcon(o.styleIconPath(p));
}
function FColumnStatus_ohCellMdclk(){
   var tab = this.lnkCol.table;
   tab.insertRow(this.lnkRow.rowIndex());
}
function FColumnStatus_onBuildSearchForm(){
   var o = this;
   var hf = o.hSearchForm = RBuilder.appendTable(o.hSearchPanel);
   hf.height = 18;
   hf.width = '100%';
   var hfl = o.hSearchFormLine = hf.insertRow();
   var hc = hfl.insertCell();
   hc.align = 'center';
   o.hSearchIcon = RBuilder.appendIcon(hc, o.styleIcon('Search'));
   o.hSearchIcon.title = RContext.get('FColumnStatus:Search');
}
function FColumnStatus_createCell(row){
   var o = this;
   var c = o.base.FColumnEditControl.createCell.call(o, row);
   if(row){
      row.cellStatus = c;
   }
   c.hPanel.className = c.style('Panel');
   return c;
}
function FColumnStatus_dispose(){
   var o = this;
   o.base.FColumnEditControl.dispose.call(o);
   o.hSelect = null;
}
function FColumnTime(o){
   o = RClass.inherits(this, o, FColumnEditControl, MDescCalendar);
   o.editFormat        = RDate.DisplayFormat;
   o.__cellClass       = FCellCalendar;
   o.__searchValue     = null;
   o.hasDropArea       = true;
   o.date              = null;
   o.onBuildSearchEdit = FColumnTime_onBuildSearchEdit;
   o.onBuildSearchDrop = FColumnTime_onBuildSearchDrop;
   o.onSearchEnter     = FColumnTime_onSearchEnter;
   o.onSearchLeave     = FColumnTime_onSearcLeave;
   o.onSearchDblClick  = RClass.register(o, new HDoubleClick('onSearchDblClick'), FColumnTime_onSearchDblClick);
   o.onSearchDropClick = RClass.register(o, new HMouseDown('onSearchDropClick'), FColumnTime_onSearchDropClick);
   o.onCellDropClick   = RClass.register(o, new HMouseDown('onCellDropClick'), FColumnTime_onCellDropClick);
   o.construct         = FColumnTime_construct;
   o.buildSearchEdit   = FColumnTime_buildSearchEdit;
   o.validText         = MDescCalendar_validText;
   o.formatValue       = MDescCalendar_formatValue;
   o.formatText        = MDescCalendar_formatText;
   o.searchValue       = FColumnTime_searchValue;
   o.searchDrop        = FColumnTime_searchDrop;
   o.focus             = FColumnTime_focus;
   o.dispose           = FColumnTime_dispose;
   return o;
}
function FColumnTime_construct(){
   var o = this;
   o.base.FColumnEditControl.construct.call(o);
   o.__searchValue = new Object();
   o.date = new TDate();
}
function FColumnTime_buildSearchEdit(p, hr){
   var o = this;
   var hc = hr.insertCell();
   hc.style.padding = '0 2';
   var he = o['h' + p[0]] = RBuilder.append(hc, 'INPUT');
   he.size = p[1] - 1;
   he.maxLength = p[1];
   he.style.textAlign = 'right';
   he.style.backgroundColor = '#eff6ff';
   he.style.borderLeft = '1 solid #CCCCCC';
   he.style.borderTop = '1 solid #CCCCCC';
   he.style.borderRight = '1 solid #EEEEEE';
   he.style.borderBottom = '1 solid #EEEEEE';
   o.table.linkEvent(o, 'onColumnSearchKeyDown', he);
}
function FColumnTime_onBuildSearchEdit(){
   var o = this;
   var t = o.table;
   var hp = o.hSearchEditPanel = o.hSearchFormLine.insertCell();
   var hf = RBuilder.appendTable(hp)
   var hr = hf.insertRow();
   var ps = o.__parts = RString.splitPattern(o.editFormat, RDate.Parts);
   for(var n=0; n<ps.length; n++){
      var p = ps[n];
      if(RString.inRange(p, RDate.Parts)){
         o.buildSearchEdit(RDate.PartsDefine[p], hr);
      }else{
         var hc = hr.insertCell();
         hc.innerText = p;
      }
   }
}
function FColumnTime_onBuildSearchDrop(){
   var o = this;
}
function FColumnTime_onSearchEnter(){
   var o = this;
}
function FColumnTime_onSearcLeave(){
   var o = this;
}
function FColumnTime_onSearchDblClick(){
   this.searchDrop();
}
function FColumnTime_onSearchDropClick(e){
   this.searchDrop();
}
function FColumnTime_onCellDropClick(s, e){
   if(!this.disabled){
      s.drop();
   }
}
function FColumnTime_searchValue(){
   var o = this;
   var ps = o.__parts;
   var v = '';
   var f = '';
   for(var n=0; n<ps.length; n++){
      var p = ps[n];
      if(RString.inRange(p, RDate.Parts)){
         var pd = RDate.PartsDefine[p];
         var s = o['h' + pd[0]].value;
         if(!RString.isEmpty(s)){
            var pd = RDate.PartsDefine[p];
            v += RInteger.format(s, pd[1]);
            f += p;
         }
      }
   }
   var sv = o.__searchValue;
   sv.value = v;
   sv.format = f;
   return RString.isEmpty(v) ? null : sv;
}
function FColumnTime_searchDrop(){
   var o = this;
   var ed = o.editor;
   if(!ed){
      ed = o.editor = RClass.create(FCalendarEditor);
      ed.psBuild();
   }
   ed.source = o;
   ed.editable = o;
   RHtml.toRect(ed.rect, o.hSearchPanel);
   RHtml.setPixelRect(ed.hPanel, ed.rect);
   ed.hPanel.style.pixelTop = ed.rect.bottom + 3;
   var hbf = ed.border.hForm;
   hbf.style.pixelWidth = o.hSearchPanel.width;
   ed.hPanel.style.width = 273;
   ed.setValue(o.hSearchEdit.value);
   ed.show();
}
function FColumnTime_focus(){
   this.hSearchEdit.focus();
}
function FColumnTime_dispose(){
   var o = this;
   o.base.FColumnEditControl.dispose.call(o);
   o.hSearchPanel = null;
   var ps = o.__parts;
   for(var n=0; n<ps.length; n++){
      var p = ps[n];
      if(RString.inRange(p, RDate.Parts)){
         o['h' + RDate.PartsDefine[p][0]] = null;
      }
   }
}
function FColumnTree(o){
   o = RClass.inherits(this, o, FColumn);
   o.cellClass = FCellTree;
   return o;
}
function FDiscussion(o){
   o = RClass.inherits(this, o, FTable, MTop);
   o.style = FDiscussion_style;
   return o;
}
function FDiscussion_style(n){
   return RClass.name(FTable) + '_' + n;
}
function FFixGrid(o) {
   o = RClass.inherits(this, o, FGridControl);
   o.__rowClass    = FFixRow;
   o._esize        = ESize.Horizontal;
   o.onResizeAfter = RMethod.emptyCall;
   o.onBuildData   = FFixGrid_onBuildData;
   o.oeResize      = RMethod.emptyCall;
   o.oeRefresh     = FFixGrid_oeRefresh;
   o.pushColumn    = FFixGrid_pushColumn;
   return o;
}
function FFixGrid_onBuildData(){
   var o = this;
   var hdf = o.hDataForm = RBuilder.appendTable(o.hBorderPanel, null, 1);
   hdf.frame = 'rhs';
   hdf.style.tableLayout = 'fixed';
   hdf.style.wordBreak = 'break-all';
   hdf.borderColorLight = '#D0D0D0';
   hdf.borderColorDark = '#EEEEEE';
   var hrs = o.hRows = RBuilder.append(hdf, 'TBODY');
   o.hHeadLine = RBuilder.append(hrs, 'TR');
   o.hSearchLine = RBuilder.append(hrs, 'TR');
   o.hFixHeight.style.display = 'none';
   o.panelNavigator = false;
}
function FFixGrid_oeRefresh(e){
   var o = this;
   o.base.FGridControl.oeRefresh.call(o, e);
   if(e.isAfter()){
      var ow = o.hBorderPanel.offsetWidth;
      var ca = null;
      var aw = ow;
      var cs = o.columns;
      for(var n=0; n<cs.count; n++){
         var c = cs.value(n);
         if(c.isDisplay){
            if(c.dispAuto){
               if(ca){
                  return RMessage.fatal(o, null, 'Too many auto column! (name1={0},name2={1})', ca.name, c.name);
               }
               ca = c;
            }else{
               aw -= c.hPanel.offsetWidth;
            }
         }
      }
      if(ca){
         ca.setWidth(Math.max(aw - 2, ca.width ? ca.width : 120));
      }
      o.hTitleForm.style.display = o.panelTitle ? 'block' : 'none';
      o.hHeadLine.style.display = o.panelHead ? 'block' : 'none';
      o.hSearchLine.style.display = o.panelSearch ? 'block' : 'none';
   }
}
function FFixGrid_pushColumn(c){
   var o = this;
   o.hHeadLine.appendChild(c.hPanel);
   o.hSearchLine.appendChild(c.hSearchPanel);
   o.push(c);
}
function FFixRow(o){
   o = RClass.inherits(this, o, FRowControl);
   o.push  = FFixRow_push;
   return o;
}
function FFixRow_push(c){
   var o = this;
   o.base.FRowControl.push.call(o, c);
   o.hPanel.appendChild(c.hPanel);
}
function FFixTable(o) {
   o = RClass.inherits(this, o, FGridControl);
   o.__rowClass     = FFixRow;
   o.onResizeAfter = FFixTable_onResizeAfter;
   o.onBuildData   = FFixTable_onBuildData;
   o.oeResize      = FFixTable_oeResize;
   o.oeRefresh     = FFixTable_oeRefresh;
   o.pushColumn    = FFixTable_pushColumn;
   return o;
}
function FFixTable_onResizeAfter(){
   var o = this;
   var hdp = o.hDataPanel;
   o.hHeadPanel.style.pixelWidth = hdp.offsetWidth - RHtml.scrollWidth(hdp);
}
function FFixTable_onBuildData(){
   var o = this;
   var hhp = o.hHeadPanel = RBuilder.appendDiv(o.hDataPanel);
   hhp.style.zIndex = 1;
   hhp.style.position = 'absolute';
   hhp.style.overflowX = 'hidden';
   hhp.style.width = 1;
   var hhf = o.hHeadForm = RBuilder.appendTable(hhp, null, 1);
   hhf.frame = 'rhs';
   hhf.style.tableLayout = 'fixed';
   hhf.borderColorLight = '#29BAD5';
   hhf.borderColorDark = '#EEEEEE';
   o.hHead = hhf.insertRow();
   o.hSearch = hhf.insertRow();
   var hdp = o.hDataPanel = RBuilder.appendDiv(hbp, o.style('DataPanel'));
   var hdf = o.hDataForm = RBuilder.appendTable(hdp, o.style('DataForm'), 0, 0, 1);
   o.hRows = RBuilder.append(hdf, 'TBODY');
   o.hRowLine = RBuilder.append(o.hRows, 'TR');
   o.attachEvent('onHeadMouseDown', o.hHeadForm, o.onHeadMouseDown);
   o.attachEvent('onHeadMouseMove', o.hHeadForm, o.onHeadMouseMove);
   o.attachEvent('onHeadMouseUp', o.hHeadForm, o.onHeadMouseUp);
   o.attachEvent('onDataScroll', o.hDataPanel, o.onDataScroll);
}
function FFixTable_oeResize(e){
   var o = this;
   var h = o.hPanel;
   if(!h.offsetWidth || !h.offsetHeight){
      return;
   }
   var hp = o.border.hPanel;
   var hcf = o.hTitleForm;
   var hhp = o.hHeadPanel;
   var hdp = o.hDataPanel;
   hdp.style.display = 'none';
   var ow = o.hBorderPanel.offsetWidth;
   var oh = o.hBorderPanel.offsetHeight;
   hdp.style.display = 'block';
   hhp.style.pixelWidth = ow;
   hdp.style.pixelWidth = ow;
   hdp.style.pixelHeight = oh - hcf.offsetHeight;
   if(o.dpScrollLeft){
      hdp.scrollLeft = o.dpScrollLeft;
      o.dpScrollLeft = null;
   }
   RConsole.find(FEventConsole).push(o.eventResizeAfter);
   return EEventStatus.Stop;
}
function FFixTable_oeRefresh(e){
   var o = this;
   o.base.FGridControl.oeRefresh.call(o, e);
   if(e.isAfter()){
      o.refreshStatus();
      var hcf = o.hTitleForm;
      var hhp = o.hHeadPanel;
      var hdp = o.hDataPanel;
      var hcfh = hcf.offsetHeight;
      var hhph = hhp.offsetHeight;
      hdp.style.display = 'none';
      var ow = o.hBorderPanel.offsetWidth;
      var oh = o.hBorderPanel.offsetHeight;
      hdp.style.display = 'block';
      hhp.style.pixelTop = hcfh;
      hhp.style.pixelLeft = 0;
      hhp.style.pixelWidth = ow;
      hdp.style.paddingLeft = 0;
      hdp.style.paddingTop = hhph;
      hdp.style.pixelWidth = ow;
      hdp.style.pixelHeight = oh - hcfh;
      var ca = null;
      var aw = ow;
      var cs = o.columns;
      for(var n=0; n<cs.count; n++){
         var c = cs.value(n);
         if(c.isDisplay){
            if(c.dispAuto){
               if(ca){
                  return RMessage.fatal(o, null, 'Too many auto column! (name1={0},name2={1})', ca.name, c.name);
               }
               ca = c;
            }else{
               aw -= c.hPanel.offsetWidth;
            }
         }
      }
      if(ca){
         ca.setWidth(Math.max(aw - 2, ca.width ? ca.width : 120));
      }
   }
}
function FFixTable_pushColumn(c){
   var o = this;
   o.hHead.appendChild(c.hPanel);
   o.hSearch.appendChild(c.hSearchPanel);
   o.hRowLine.appendChild(c.hFixPanel);
   o.push(c);
}
function FGrid(o) {
   o = RClass.inherits(this, o, FGridControl);
   o.onResizeAfter = FGrid_onResizeAfter;
   o.onBuildData   = FGrid_onBuildData;
   o.oeResize      = FGrid_oeResize;
   o.oeRefresh     = FGrid_oeRefresh;
   o.pushColumn    = FGrid_pushColumn;
   return o;
}
function FGrid_onResizeAfter(){
   var o = this;
   var hdp = o.hDataPanel;
   var hfp = o.hFixPanel;
   var sw = RHtml.scrollWidth(hdp);
   var sh = RHtml.scrollHeight(hdp);
   o.hHeadPanel.style.pixelWidth = hdp.offsetWidth - hfp.offsetWidth - sw;
   o.hColumnPanel.style.pixelHeight = hdp.offsetHeight - hfp.offsetHeight - sh + 1;
}
function FGrid_onBuildData(){
   var hfp = o.hFixPanel = RBuilder.appendDiv(hbp);
   hfp.style.zIndex = 2;
   hfp.style.position = 'absolute';
   var hff = o.hFixForm = RBuilder.appendTable(hfp, null, 1);
   var hffb = RBuilder.append(hff, 'TBODY');
   hff.style.tableLayout = 'fixed';
   hff.frame = 'rhs';
   hff.borderColorLight = '#29BAD5';
   hff.borderColorDark = '#EEEEEE';
   o.hFixHead = RBuilder.append(hffb, 'TR');
   o.hFixSearch = RBuilder.append(hffb, 'TR');
   var hhp = o.hHeadPanel = RBuilder.appendDiv(hbp);
   hhp.style.zIndex = 1;
   hhp.style.position = 'absolute';
   hhp.style.overflowX = 'hidden';
   hhp.style.width = 1;
   var hhf = o.hHeadForm = RBuilder.appendTable(hhp, null, 1);
   hhf.frame = 'rhs';
   hhf.style.tableLayout = 'fixed';
   hhf.borderColorLight = '#29BAD5';
   hhf.borderColorDark = '#EEEEEE';
   o.hHead = hhf.insertRow();
   o.hSearch = hhf.insertRow();
   var hcp = o.hColumnPanel = RBuilder.appendDiv(hbp, o.style('DataPanel'));
   hcp.style.zIndex = 1;
   hcp.style.position = 'absolute';
   hcp.style.overflowY = 'hidden';
   var hcf = o.hColumnForm = RBuilder.appendTable(hcp, o.style('DataForm'), 0, 0, 1);
   o.hFixRows = RBuilder.append(hcf, 'TBODY');
   o.hFixRowLine = RBuilder.append(o.hFixRows, 'TR');
   var hdp = o.hDataPanel = RBuilder.appendDiv(hbp, o.style('DataPanel'));
   var hdf = o.hDataForm = RBuilder.appendTable(hdp, o.style('DataForm'), 0, 0, 1);
   o.hRows = RBuilder.append(hdf, 'TBODY');
   o.hRowLine = RBuilder.append(o.hRows, 'TR');
   o.attachEvent('onHeadMouseDown', o.hHeadForm, o.onHeadMouseDown);
   o.attachEvent('onHeadMouseMove', o.hHeadForm, o.onHeadMouseMove);
   o.attachEvent('onHeadMouseUp', o.hHeadForm, o.onHeadMouseUp);
   o.attachEvent('onDataScroll', o.hDataPanel, o.onDataScroll);
}
function FGrid_oeResize(e){
   var o = this;
   var h = o.hPanel;
   if(!h.offsetWidth || !h.offsetHeight){
      return;
   }
   var hp = o.border.hPanel;
   var hcf = o.hTitleForm;
   var hfp = o.hFixPanel;
   var hhp = o.hHeadPanel;
   var hcp = o.hColumnPanel;
   var hdp = o.hDataPanel;
   hhp.style.display = hcp.style.display = hdp.style.display = 'none';
   var ow = o.hBorderPanel.offsetWidth;
   var oh = o.hBorderPanel.offsetHeight;
   hhp.style.display = hcp.style.display = hdp.style.display = 'block';
   hhp.style.pixelWidth = ow - hfp.offsetWidth;
   hcp.style.pixelHeight = oh - hfp.offsetHeight - 1 - hcf.offsetHeight;
   hdp.style.pixelWidth = ow;
   hdp.style.pixelHeight = oh - hcf.offsetHeight;
   if(o.dpScrollLeft){
      hdp.scrollLeft = o.dpScrollLeft;
      o.dpScrollLeft = null;
   }
   RConsole.find(FEventConsole).push(o.eventResizeAfter);
   return EEventStatus.Stop;
}
function FGrid_oeRefresh(e){
   var o = this;
   o.base.FGridControl.oeRefresh.call(o, e);
   if(e.isAfter()){
      var hcf = o.hTitleForm;
      var hfp = o.hFixPanel;
      var hhp = o.hHeadPanel;
      var hcp = o.hColumnPanel;
      var hdp = o.hDataPanel;
      var hcfh = hcf.offsetHeight;
      var hfpw = hfp.offsetWidth;
      var hfph = hfp.offsetHeight;
      hcp.style.display = hdp.style.display = 'none';
      var ow = o.hBorderPanel.offsetWidth;
      var oh = o.hBorderPanel.offsetHeight;
      hcp.style.display = hdp.style.display = 'block';
      hfp.style.pixelTop = hcfh;
      hhp.style.pixelTop = hcfh;
      hhp.style.pixelLeft = hfpw;
      hhp.style.pixelWidth = ow - hfpw;
      hhp.style.pixelHeight = hfph;
      o.hHead.style.pixelHeight = o.hFixHead.offsetHeight;
      o.hSearch.style.pixelHeight = o.hFixSearch.offsetHeight;
      hcp.style.pixelTop = hcfh + hfph;
      hcp.style.pixelHeight = oh - hcfh - hfph;
      hdp.style.paddingLeft = hfpw;
      hdp.style.paddingTop = hfph;
      hdp.style.pixelWidth = ow;
      hdp.style.pixelHeight = oh - hcfh;
      var ca = null;
      var aw = ow;
      var cs = o.columns;
      for(var n=0; n<cs.count; n++){
         var c = cs.value(n);
         if(c.isDisplay){
            if(c.dispAuto){
               if(ca){
                  return RMessage.fatal(o, null, 'Too many auto column! (name1={0},name2={1})', ca.name, c.name);
               }
               ca = c;
            }else{
               aw -= c.hPanel.offsetWidth;
            }
         }
      }
      if(ca){
         ca.setWidth(Math.max(aw - 2, ca.width ? ca.width : 120));
      }
   }
}
function FGrid_pushColumn(c){
   var o = this;
   if(c.dispFixed){
      o.hFixHead.appendChild(c.hPanel);
      o.hFixSearch.appendChild(c.hSearchPanel);
      o.hFixRowLine.appendChild(c.hFixPanel);
   }else{
      o.hHead.appendChild(c.hPanel);
      o.hSearch.appendChild(c.hSearchPanel);
      o.hRowLine.appendChild(c.hFixPanel);
   }
   o.push(c);
}
function FGridControl(o) {
   o = RClass.inherits(this, o, FContainer, MValue, MDataset, MDisplay, MFocus, MForm, MProgress, MHorizontal, MLsnLoaded, MLsnSelect, MLsnClick, MLsnKey);
   o.formName               = RClass.register(o, new TPtyStr('formName', null));
   o.formCustom             = RClass.register(o, new TPtyBool('formCustom', false));
   o.formParameter          = RClass.register(o, new TPtyStr('formParameter', null));
   o.formLinked             = RClass.register(o, new TPtyBool('formLinked', false));
   o.dispRowbar             = RClass.register(o, new TPtyBool('dispRowbar', false));
   o.dispSelected           = RClass.register(o, new TPtyBool('dispSelected', false));
   o.dispCount              = RClass.register(o, new TPtyInt('dispCount'), 20);
   o.rowHeight              = RClass.register(o, new TPtyInt('rowHeight'), 0);
   o.panelTitle             = RClass.register(o, new TPtyBoolSet('panelTitle', 'panelAccess', EGridDisplay.Title, false));
   o.panelHead              = RClass.register(o, new TPtyBoolSet('panelHead', 'panelAccess', EGridDisplay.Head, false));
   o.panelSearch            = RClass.register(o, new TPtyBoolSet('panelSearch', 'panelAccess', EGridDisplay.Search, false));
   o.panelTotal             = RClass.register(o, new TPtyBoolSet('panelTotal', 'panelAccess', EGridDisplay.Total, false));
   o.panelNavigator         = RClass.register(o, new TPtyBoolSet('panelNavigator', 'panelAccess', EGridDisplay.Navigator, false));
   o.stBorderPanel          = RClass.register(o, new TStyle('BorderPanel'));
   o.stHeadPanel            = RClass.register(o, new TStyle('HeadPanel'));
   o.stHeadForm             = RClass.register(o, new TStyle('HeadForm'));
   o.stHeadLine             = RClass.register(o, new TStyle('HeadLine'));
   o.stSearchLine           = RClass.register(o, new TStyle('SearchLine'));
   o.stDataPanel            = RClass.register(o, new TStyle('DataPanel'));
   o.stDataForm             = RClass.register(o, new TStyle('DataForm'));
   o.stHintForm             = RClass.register(o, new TStyle('HintForm'));
   o.stHint                 = RClass.register(o, new TStyle('Hint'));
   o.stButton               = RClass.register(o, new TStyle('Button'));
   o.siButton               = RClass.register(o, new TStyleIcon('Button'));
   o.__rowClass             = FRow;
   o.__dataset              = null;
   o.__focusCell            = null;
   o.__focusRow             = null;
   o.__hoverRow             = null;
   o.__clickRowEvent        = null;
   o.__doubleClickRowEvent  = null;
   o.__loadActive           = null;
   o._statusColumn          = null;
   o._loadFinish            = false;
   o.__isSearching          = false;
   o._esize                 = ESize.Both;
   o._minHeight             = 70;
   o.border                 = null;
   o.columns                = null;
   o.buttons                = null;
   o.rows                   = null;
   o.hPanel                 = null;
   o.hCaption               = null;
   o.hBorderPanel           = null;
   o.hFixPanel              = null;
   o.hFixForm               = null;
   o.hFixHead               = null;
   o.hFixSearchLine         = null;
   o.hHeadPanel             = null;
   o.hHeadForm              = null;
   o.hHead                  = null;
   o.hSearch                = null;
   o.hColumnPanel           = null;
   o.hColumnForm            = null;
   o.hDataPanel             = null;
   o.hDataForm              = null;
   o.hFixRowLine            = null;
   o.hFixRows               = null;
   o.hRows                  = null;
   o.hRowLine               = null;
   o.hDelayPanel            = null;
   o.hDelayText             = null;
   o.hNavigator             = null;
   o.hFottor                = null;
   o.hButtons               = null;
   o.lsnsRowClick           = null;
   o.lsnsRowDblClick        = null;
   o.onMouseDown            = FGridControl_onMouseDown;
   o.onHeadMouseDown        = RClass.register(o, new HMouseDown('onHeadMouseDown'), FGridControl_onHeadMouseDown);
   o.onHeadMouseMove        = RClass.register(o, new HMouseMove('onHeadMouseMove'), FGridControl_onHeadMouseMove);
   o.onHeadMouseUp          = RClass.register(o, new HMouseUp('onHeadMouseUp'), FGridControl_onHeadMouseUp);
   o.onDataScroll           = RClass.register(o, new HScroll('onDataScroll'), FGridControl_onDataScroll);
   o.onCellKeyDown          = RClass.register(o, new HKeyDown('onCellKeyDown'), FGridControl_onCellKeyDown);
   o.onRowMouseEnter        = RClass.register(o, new HMouseEnter('onRowMouseEnter'), FGridControl_onRowMouseEnter);
   o.onRowMouseLeave        = RClass.register(o, new HMouseLeave('onRowMouseLeave'), FGridControl_onRowMouseLeave);
   o.onRowClick             = RClass.register(o, new HClick('onRowClick'), FGridControl_onRowClick);
   o.onColumnSearchKeyDown  = RClass.register(o, new HKeyDown('onColumnSearchKeyDown'), FGridControl_onColumnSearchKeyDown);
   o.onButtonMouseDown      = RClass.register(o, new HMouseDown('onButtonMouseDown'), FGridControl_onButtonMouseDown);
   o.onPageCountDown        = RClass.register(o, new HKeyDown('onPageCountDown'), FGridControl_onPageCountDown);
   o.onInsertButtonClick    = FGridControl_onInsertButtonClick;
   o.onExtendButtonClick    = FGridControl_onExtendButtonClick;
   o.onDsPrepare            = RMethod.empty;
   o.onResizeAfter          = RMethod.virtual(o, 'onResizeAfter');
   o.onLoadDatasetDelay     = FGridControl_onLoadDatasetDelay;
   o.onLoadDataset          = FGridControl_onLoadDataset;
   o.clearSelectAll         = FGridControl_clearSelectAll;
   o.onLoadDatasetEnd       = RMethod.empty;
   o.onBuildTitle           = FGridControl_onBuildTitle;
   o.onBuildData            = RMethod.virtual(o, 'onBuildData');
   o.onBuildHint            = FGridControl_onBuildHint;
   o.onBuildPanel           = RBuilder.onBuildTablePanel;
   o.oeBuild                = FGridControl_oeBuild;
   o.oeMode                 = FGridControl_oeMode;
   o.oeProgress             = FGridControl_oeProgress;
   o.construct              = FGridControl_construct;
   o.buildNavigatorButton   = FGridControl_buildNavigatorButton;
   o.isFormLinked           = FGridControl_isFormLinked;
   o.isDataSelected         = FGridControl_isDataSelected;
   o.isDataChanged          = FGridControl_isDataChanged;
   o.hasAction              = FGridControl_hasAction;
   o.loadValue              = RMethod.empty;
   o.saveValue              = RMethod.empty;
   o.getFormLink            = FGridControl_getFormLink;
   o.getHeadMode            = FGridControl_getHeadMode;
   o.getRowBar              = FGridControl_getRowBar;
   o.calculateDataSize      = FGridControl_calculateDataSize;
   o.createRow              = FGridControl_createRow;
   o.insertRow              = FGridControl_insertRow;
   o.syncRow                = FGridControl_syncRow;
   o.getDataCodes           = RMethod.empty;
   o.getCurrentRow          = FGridControl_getCurrentRow;
   o.getSelectedRow         = FGridControl_getSelectedRow;
   o.getSelectedRows        = FGridControl_getSelectedRows;
   o.getCurrentRows         = FGridControl_getChangedRows;
   o.getChangedRows         = FGridControl_getChangedRows;
   o.getRows                = FGridControl_getRows;
   o.refreshHint            = FGridControl_refreshHint;
   o.refreshSelected        = FGridControl_refreshSelected;
   o.hoverRow               = FGridControl_hoverRow;
   o.selectRow              = FGridControl_selectRow;
   o.clearSelectRow         = FGridControl_clearSelectRow;
   o.clearSelectRows        = FGridControl_clearSelectRows;
   o.clickCell              = FGridControl_clickCell;
   o.clickRow               = FGridControl_clickRow;
   o.doubleClickRow         = FGridControl_doubleClickRow;
   o.setDataStatus          = FGridControl_setDataStatus;
   o.dsInsert               = FGridControl_dsInsert;
   o.dsUpdate               = FGridControl_dsUpdate;
   o.dsDelete               = FGridControl_dsDelete;
   o.doPrepare              = RMethod.empty;
   o.doDelete               = RMethod.empty;
   o.doSearch               = FGridControl_doSearch;
   o.push                   = FGridControl_push;
   o.pushColumn             = RMethod.virtual(o, 'pushColumn');
   o.pushButton             = FGridControl_pushButton;
   o.focus                  = FGridControl_focus;
   o.pack                   = FGridControl_pack;
   o.setVisible             = FGridControl_setVisible;
   o.setButtonVisible       = FGridControl_setButtonVisible;
   o.hideRows               = FGridControl_hideRows;
   o.hasVisibleRow          = FGridControl_hasVisibleRow
   o.refreshStyle           = FGridControl_refreshStyle;
   o.dispose                = FGridControl_dispose;
   o.dump                   = FGridControl_dump;
   o.onColumnTreeClick      = RClass.register(o, new HClick('onColumnTreeClick'), FGridControl_onColumnTreeClick);
   o.onColumnTreeService    = FGridControl_onColumnTreeService;
   o.hoverMode              = EColumnMode.None;
   o.__searchKeyDownEvent   = new TEvent();
   o.createChild            = FGridControl_createChild;
   o.buildRow               = FGridControl_buildRow;
   o.buildRows              = FGridControl_buildRows;
   o.appendRow              = FGridControl_appendRow;
   o.deleteRow              = FGridControl_deleteRow;
   o.clearRows              = FGridControl_clearRows;
   o.getRowType             = FGridControl_getRowType;
   o.setStyleStatus         = FGridControl_setStyleStatus;
   return o;
}
function FGridControl_pushButton(b){
   var o = this;
   var hc  = o.hButtons.insertCell();
   hc.style.border = '0 solid #C6D7FF';
   hc.appendChild(b.hPanel);
   o.push(b);
}
function FGridControl_onMouseDown(e, he){
   var o = this;
   var fc = RConsole.find(FFocusConsole);
   fc.focusClass(MDataset, o);
   fc.focusHtml(he);
   if(!RConsole.find(FDesignConsole).isDesign()){
      he.cancelBubble = true;
   }
}
function FGridControl_onHeadMouseDown(e){
   var o = this;
   var m = o.getHeadMode(e);
   if(EColumnMode.Size == m){
      o.hoverMode = EColumnMode.Size;
      e.srcElement.status = EColumnMode.Size;
      o.hoverX = e.srcElement.offsetLeft + e.x;
      o.hoverDataCell = null;
      if(o.hDataForm.rows.length){
         o.hoverDataCell = o.hDataForm.rows[0].cells[o.hoverHead.index];
      }
      o.hHeadForm.setCapture();
   }
}
function FGridControl_onHeadMouseMove(e){
   var o = this;
   if(EColumnMode.Size == o.hoverMode){
      var bl = o.hoverCellLength;
      var mx = e.srcElement.offsetLeft + e.x;
      var w =  mx - o.hoverX + bl;
      if(w > 0){
         o.hoverHead.hPanel.style.pixelWidth = w;
         o.hoverHead.hFixPanel.style.pixelWidth = w;
      }
   }else if(EColumnMode.None == o.hoverMode){
      var m = o.getHeadMode(e);
      var c = 'default';
      if(EColumnMode.Size == m){
         c = 'e-resize';
      }else if(EColumnMode.Drag == m){
         c = 'hand';
      }
      o.hHeadForm.style.cursor = c;
   }
}
function FGridControl_onHeadMouseUp(e){
   var o = this;
   if(EColumnMode.Size == o.hoverMode){
      o.hHeadForm.releaseCapture();
   }
   o.hoverMode = EColumnMode.None;
}
function FGridControl_onDataScroll(){
   var o = this;
   o.hHeadPanel.scrollLeft = o.hDataPanel.scrollLeft;
   o.hColumnPanel.scrollTop = o.hDataPanel.scrollTop;
}
function FGridControl_onCellKeyDown(c, e, he){
   var o = this;
   var k = e.keyCode;
   var l = c.column;
   var r = c.row;
   if(EKey.Up == k) {
      l.moveCellFocus(r, EPosition.Top);
      RKey.eventClear(he);
   }else if(EKey.Down == k) {
      l.moveCellFocus(r, EPosition.Bottom);
      RKey.eventClear(he);
   }else if(EKey.Tab == k && e.shiftKey){
      l.moveCellFocus(r, EPosition.Before);
      RKey.eventClear(he);
   }else if(EKey.Tab == k){
      l.moveCellFocus(r, EPosition.After);
      RKey.eventClear(he);
   }
}
function FGridControl_onRowMouseEnter(s, e){
   this.hoverRow(s, true);
}
function FGridControl_onRowMouseLeave(s, e){
   this.hoverRow(s, false);
}
function FGridControl_onRowClick(s, e){
   var o = this;
   o.selectRow(s, !e.ctrlKey, true);
   o.lsnsRowClick.process(s);
   var e = o._eventRowClick;
   if(!e){
      e = o._eventRowClick = new TEvent();
      e.source = o;
   }
   e.caller = s;
   e.handle = 'onTableRowClick';
   RConsole.find(FFormConsole).processEvent(e);
}
function FGridControl_onColumnSearchKeyDown(s, e){
   var o = this;
   if(EKey.Enter == e.keyCode){
      if(!o._isSearching || !o.table._isSearching){
         o._isSearching = true;
         if(o.table){
        	 o.table.doSearch();
             o.table.dpScrollLeft = o.table.hDataPanel.scrollLeft;
             o.table.callEvent('onSearchKeyDown', o, o.__searchKeyDownEvent);
         }else{
            o.doSearch();
            o.dpScrollLeft = o.hDataPanel.scrollLeft;
            o.callEvent('onSearchKeyDown', o, o.__searchKeyDownEvent);
         }
      }
   }
}
function FGridControl_onButtonMouseDown(e){
   var o = this;
   var ds = o.dsViewer;
   if(!ds || 0 == ds.dataset.pageCount){
      return;
   }
   var h = e.hSource;
   if(o.hInsertButton == h){
      o.onInsertButtonClick();
   }else if(o.hExtendButton == h){
      o.onExtendButtonClick();
   }else if (o.hNavFirst == h && ds.pageIndex != 0){
      o.dsMovePage(EDataAction.First);
   } else if (o.hNavPrior == h && ds.pageIndex != 0){
      o.dsMovePage(EDataAction.Prior);
   } else if (o.hNavNext == h && ds.pageIndex != ds.pageCount - 1){
      o.dsMovePage(EDataAction.Next);
   } else if (o.hNavLast == h && ds.pageIndex != ds.pageCount - 1){
      o.dsMovePage(EDataAction.Last);
   }
}
function FGridControl_onPageCountDown(e){
   var o = this;
   var ds = o.dsViewer;
   if(RString.isEmpty(o.hPage.value) || !ds || 0 == ds.dataset.pageCount){
      return;
   }
   var n = RInt.parse(o.hPage.value);
   if(EKey.Enter == e.keyCode && n != ds.pageIndex + 1){
      if(n < 1){
         n = 1;
      }
      if(n > ds.pageCount){
         n = ds.pageCount;
      }
      o.dsMovePage(n - 1);
   }
}
function FGridControl_onInsertButtonClick(){
   RFormSpace.doPrepare(this);
}
function FGridControl_onExtendButtonClick(){
   var o = this;
   if(400 == o.dsPageSize){
      o.dsPageSize = o.dsPageSizeStore;
      o.hExtendText.innerText = ' 展开';
   }else{
      o.dsPageSizeStore = o.dsPageSize;
      o.dsPageSize = 400;
      o.hExtendText.innerText = ' 收缩';
   }
   o.dsSearch();
}
function FGridControl_onLoadDatasetDelay(a){
   var o = this;
   o.psProgress(true);
   var v = o.dsViewer;
   var c = o.dispCount;
   var h = o.rowHeight;
   var idx = a.index;
   var m = idx + a.acceleration;
   if( m > v.count - 1){
      m = v.count - 1;
   }
   if(o.hHeadPanel){
      o.hHeadPanel.scrollLeft = 0;
   }
   if(o.hColumnPanel){
      o.hColumnPanel.scrollTop = 0;
   }
   o.syncRow(m);
   for(var n = idx; n <= m; n++){
      var r = o.syncRow(n);
      if(h>0) {
     	 r.hFixPanel.height = h;
      }
      if(v.next()){
         r.loadRow(v.current());
         r.recordValue();
         r.setVisible(true);
         r.refreshStyle();
      }else{
         r.setVisible(false);
      }
   }
   if(m == v.count-1){
      m = v.count-1;
      a.status = EActive.Sleep;
      o.hDelayPanel.style.display = 'none';
      var rs = o.rows;
      for(var n=m+1; n<rs.count; n++){
         rs.get(n).setVisible(false);
      }
      o.topControl().topResize();
      o._isSearching = false;
      RConsole.find(FListenerConsole).process(MDataset, EAction.Changed, o, o);
   }
   if((m+1) != v.count){
      o.hDelayPanel.filters[0].opacity = 100 - (100/v.count)// (m+1);
   }
   a.acceleration++;
   a.index += a.acceleration;
   o._loadFinish = true;
   o._isSearching = false;
   o.dsLoaded();
   o.psProgress(false);
}
function FGridControl_onLoadDataset(ds, da){
   var o = this;
   o.__dataset = ds;
   if(o.hColumnPanel){
      o.hColumnPanel.scrollTop = 0;
      o.hColumnPanel.scrollLeft = 0;
   }
   if(o.hDataPanel){
	  o.hDataPanel.scrollTop = 0;
	  o.hDataPanel.scrollLeft = 0;
   }
   var v = o.dsViewer;
   if(v.isEmpty()){
      o.hideRows();
      o.topControl().topResize();
      o._isSearching = false;
      o._loadFinish = true;
      o.dsLoaded();
      o.psProgress(false);
      return;
   }
   ds.saveViewer(v);
   var a = o.__loadActive;
   a.interval = 0;
   a.index = 0;
   a.acceleration = 100;
   a.dataAction = da;
   a.status = EActive.Active;
   v.reset();
   o.psProgress(true);
   o.psRefresh();
   if(o.hHint){
      o.refreshHint();
   }
   o.refreshSelected();
   if(o.hPage){
      o.hPage.value = ds.pageIndex + 1;
   }
}
function FGridControl_onBuildTitle(e){
   var o = this;
   var hcf = o.hTitleForm = RBuilder.appendTable(o.hBorderPanel);
   hcf.width = '100%';
   hcf.height = '20';
   hcf.style.borderBottom = '1 solid #999999';
   var hcr = o.hCaptionLine = hcf.insertRow();
   var hcc = hcr.insertCell();
   hcc.style.backgroundImage = 'url(' + RResource.iconPath('ctl.FGridControl_Head') + ')';
   hcc.height = '20';
   hcc.align = 'center';
   hcc.innerText = o.label;
   hcc.style.fontWeight = 'bold';
   hcc.style.color = '#176877';
   hcc.style.display = o.panelTitle ? 'block' : 'none';
   hbc = hcf.insertRow();
   hdc = hbc.insertCell();
   hdc.style.backgroundColor='#CAE9FE';
   hdc.style.borderTop='1 solid #95C6FE';
   hbf = o.hButtonForm = RBuilder.appendTable(hdc);
   hb = o.hButtons = hbf.insertRow();
   hdc.style.display = o.panelTitle ? 'block' : 'none';
}
function FGridControl_onBuildHint(e) {
   var o = this;
   var hr = o.hHintForm.insertRow();
   if(o.editInsert && o.formName){
      var hc = hr.insertCell();
      hc.width = 60;
      o.hInsertButton = o.buildNavigatorButton(hc, 'ctl.FGridControl_insert', '&nbsp;新建', null, 'hInsert');
   }
   var hc = hr.insertCell();
   hc.width = 10;
   var hc = hr.insertCell();
   hc.noWrap = true;
   o.hHint = RBuilder.appendText(hc, '', o.style('Hint'))
   var hc = hr.insertCell();
   hc.noWrap = true;
   hc.align = 'right';
   o.hNavFirst = o.buildNavigatorButton(hc, 'ctl.FGridControl_first', '&nbsp;'+RContext.get('FGridControl:First'));
   o.hNavPrior = o.buildNavigatorButton(hc, 'ctl.FGridControl_prior', '&nbsp;'+RContext.get('FGridControl:Prior'));
   o.hNavPrior.style.paddingRight = '20';
   o.hPage = RBuilder.appendEdit(hc)
   o.hPage.style.width = 40;
   o.attachEvent('onPageCountDown', o.hPage);
   o.hNavNext = o.buildNavigatorButton(hc, null, RContext.get('FGridControl:Next')+'&nbsp;', 'ctl.FGridControl_next');
   o.hNavLast = o.buildNavigatorButton(hc, null, RContext.get('FGridControl:Last')+'&nbsp;', 'ctl.FGridControl_last');
}
function FGridControl_oeBuild(e){
   var o = this;
   if(e.isBefore()){
      if(!o.height || o.height < 160){
         o.height = '100%';
      }
   }
   var r = o.base.FContainer.oeBuild.call(o, e);
   if(e.isBefore()){
      var hpl = o.hPanel.insertRow();
      var b = o.border = new TBorder(EBorder.Round);
      b.build(hpl.insertCell());
      var hbf = b.hForm;
      hbf.width = '100%';
      hbf.height = '100%';
      var hc = hpl.insertCell();
      hc.width = 1;
      var hd = o.hFixHeight = RBuilder.appendDiv(hc);
      hd.style.width = 1;
      hd.style.height = o._minHeight;
      var hbp = o.hBorderPanel = b.hPanel;
      hbp.className = o.style('BorderPanel');
      hbp.vAlign = 'top';
      hbp.style.position = 'relative';
      hbp.style.overflow = 'hidden';
      o.onBuildTitle(e);
      o.onBuildData(e);
      if(o.panelNavigator){
         var hnp = o.hNavigator = o.hPanel.insertRow().insertCell();
         hnp.height = 1;
         o.hHintForm = RBuilder.appendTable(hnp, o.style('HintForm'));
         o.onBuildHint(e);
      }
   }else if (e.isAfter()) {
	  o.border.setBorderColor('#9EC4EB');
      var cs = o.columns;
      var cc = cs.count;
      for(var n=0; n<cc; n++){
         o.pushColumn(cs.value(n));
      }
      for(var n=0; n<cc; n++){
         var c = o.columns.value(n);
         c.index = n;
      }
      var cnt = o.rows.count;
      for(var n=0; n<cnt; n++){
         o.buildRow(o.rows.get(n));
      }
      var bs = o.buttons;
      for(var n=0; n<bs.count; n++){
    	  o.pushButton(bs.value(n));
      }
      o.dsPageSize = o.dispCount;
   }
   return r;
}
function FGridControl_oeMode(e){
   var o = this;
   o.dispUpdate = true;
   o.dispDelete = true;
   o.base.FContainer.oeMode.call(o, e);
   o.base.MDisplay.oeMode.call(o, e);
   o._editable = o.canEdit(e.mode);
   return EEventStatus.Stop;
}
function FGridControl_oeProgress(e){
   var o = this;
   if('none' == o.hPanel.currentStyle.display){
      return;
   }
   var hdp = o.hDelayPanel;
   if(!hdp){
      hdp = o.hDelayPanel = RBuilder.appendDiv(o.hBorderPanel);
      var st = hdp.style;
      st.position = 'absolute';
      st.zIndex = RLayer.next();
      st.filter = 'progid:DXImageTransform.Microsoft.Alpha(opacity=100)';
      st.backgroundColor = '#FFFFFF';
      st.top = 0;
      st.width = '100%';
      st.height = '100%';
      st.display = 'none';
      var hdf = o.hDelayForm = RBuilder.appendTable(hdp);
      hdf.style.width = '100%';
      hdf.style.height = '100%';
      var hc = hdf.insertRow().insertCell();
      hc.align = 'center';
      hc.vAlign = 'middle';
      RBuilder.appendIcon(hc, 'ctl.FGridControl_Loading')
      var t = o.hDelayText = RBuilder.append(hc, 'SPAN');
      t.innerHTML = "<BR><BR><FONT color='red'><B>" + RContext.get('FGridControl:Loading') + "</B></FONT>";
   }
   if(e.enable){
      RHtml.setRect(hdp, o.calculateDataSize());
      hdp.filters[0].opacity = 100;
      hdp.style.display = 'block';
   }else{
	   if(o._loadFinish){
         hdp.style.display = 'none';
	   }
   }
   o.refreshHint();
   return EEventStatus.Stop;
}
function FGridControl_construct() {
   var o = this;
   o.base.FContainer.construct.call(o);
   o.base.MDataset.construct.call(o);
   o.columns = new TMap();
   o.buttons = new TMap();
   o.rows = new TList();
   if(o.dispCount < 0){
      o.dsPageSize = 400;
   }
   o.lsnsRowClick = new TListeners();
   o.lsnsRowDblClick = new TListeners();
   o.__clickRowEvent = new TEvent();
   o.__doubleClickRowEvent = new TEvent();
   var col = o._statusColumn = RControl.create(FColumnStatus);
   col.table = this;
   col.name = '_s';
   o.columns.set(col.name, col);
   var cols = o._selectColumn = RControl.create(FColumnSelected);
   cols.table = this;
   cols.name = '_select';
   o.columns.set(cols.name, cols);
   var a = o.__loadActive = new TActive(o, o.onLoadDatasetDelay);
   a.status = EActive.Sleep;
   RConsole.find(FActiveConsole).push(a);
   o.eventResizeAfter = new TEvent(o, 'ResizeAfter', o.onResizeAfter);
}
function FGridControl_buildNavigatorButton(hParent, iconBf, text, iconAf, name) {
   var o = this;
   var h = RBuilder.append(hParent, 'SPAN', o.style('Button'));
   h.style.cursor = 'hand';
   h.style.paddingLeft = '10';
   o.attachEvent('onButtonMouseDown', h);
   if (iconBf) {
      RBuilder.appendIcon(h, iconBf);
   }
   if(text){
      if(name){
         o[name + 'Text'] = RBuilder.appendText(h, text);
      }else{
         RBuilder.appendText(h, text);
      }
   }
   if(iconAf){
      RBuilder.appendIcon(h, iconAf);
   }
   return h;
}
function FGridControl_isFormLinked(){
   return this.formLinked || this.formName;
}
function FGridControl_isDataSelected(){
   var rs = this.rows;
   for(var n=rs.count-1; n>=0; n--){
      if(rs.get(n).isSelect){
         return true;
      }
   }
}
function FGridControl_isDataChanged(){
   var rs = this.rows;
   for(var n=rs.count-1; n>=0; n--){
      if(rs.get(n).isDataChanged()){
         return true;
      }
   }
}
function FGridControl_hasAction(){
   var o = this;
   var cs = o.components;
   var ct = cs.count;
   for(var n = 0; n < ct; n++){
      var c = cs.value(n);
      if(RClass.isClass(c, FDataAction)){
         return o.isDataSelected();
      }
   }
}
function FGridControl_getFormLink(t){
   var o = this;
   if(EFormLink.Form == t){
      return this.formName;
   }else if(EFormLink.Table == t){
      return this.name;
   }
   RMessage.fatal(o, null, 'Form link is invalid. (type={0})', t);
}
function FGridControl_getHeadMode(e){
   var o = this;
   var p = RHtml.point(o.hHeadForm);
   var x = e.srcElement.offsetLeft + e.x - p.x;
   var cs = o.columns;
   for(var n = 0; n<cs.count; n++){
      var c = cs.value(n);
      if(c.dispSize){
         var l = c.hPanel.offsetLeft + c.hPanel.offsetWidth - p.x;
         o.hoverCellLength = c.hPanel.offsetWidth;
         if(l - 6 <= x && x<=l){
            o.hoverHead = c;
            return EColumnMode.Size;
         }
      }
   }
   return EColumnMode.None;
}
function FGridControl_getRowBar(){
   var o = this;
   var rb = o._rowBar;
   if(!rb){
      rb = o._rowBar = RClass.create(FRowBar);
      rb.table = o;
      rb.psBuild(o.hBorderPanel);
   }
   return rb;
}
function FGridControl_calculateDataSize(){
   var o = this;
   var r = o.__dataRect;
   if(!r){
      r = o.__dataRect = new TRect();
   }
   var hcfh = o.hTitleForm ? o.hTitleForm.offsetHeight : 0;
   var hfph = o.hFixPanel ? o.hFixPanel.offsetHeight : 0;
   r.left = 0;
   r.top = hfph + hcfh;;
   r.setWidth(o.hBorderPanel.offsetWidth);
   r.setHeight(o.hBorderPanel.offsetHeight - hcfh - hfph);
   return r;
}
function FGridControl_createRow() {
   var o = this;
   var r = RClass.create(o.__rowClass);
   r.table = r.parent = o;
   return r;
}
function FGridControl_hasVisibleRow() {
   var o = this;
   var rs = o.rows;
   for(var n = 0; n<rs.count; n++){
	   var rt = rs.get(n);
	   if(rt.__visible){
	      return true;
	   }
   }
   return false;
}
function FGridControl_insertRow(i, r){
   var o = this;
   r.index = i;
   r.build();
   if(r.hFixPanel){
      o.hFixRows.appendChild(r.hFixPanel);
      o.hColumnForm.moveRow(r.hFixPanel.rowIndex, i+2);
   }
   o.hRows.appendChild(r.hPanel);
   o.hDataForm.moveRow(r.hPanel.rowIndex, i+2);
   r.refreshStyle();
   o.rows.insert(i, r);
}
function FGridControl_syncRow(i){
   var o = this;
   var rs = o.rows;
   var r = rs.get(i);
   if(!r){
      for(var n = rs.count; n <= i; n++){
         r = o.createRow();
         r.index = n;
         r.build();
         if(r.hFixPanel){
            o.hFixRows.appendChild(r.hFixPanel);
         }
         o.hRows.appendChild(r.hPanel);
         rs.push(r);
      }
   }
   r.extended = false;
   if(r.childRows){
      r.hideChild();
      r.childRows.clear();
   }
   return r;
}
function FGridControl_getCurrentRow(){
   var c = this.__focusCell;
   if(c){
      return c.row.saveRow();
   }
}
function FGridControl_getSelectedRow(){
   var rs = this.rows;
   var c = rs.count;
   for(var n=0; n<c; n++){
      var r = rs.get(n);
      if(r.isSelect){
         return r;
      }
   }
}
function FGridControl_getSelectedRows(){
   var ls = new TList();
   var rs = this.rows;
   var c = rs.count;
   for(var n=0; n<c; n++){
      var r = rs.get(n);
      if(r.isSelect && r.isVisible()){
         ls.push(r.saveRow());
      }
   }
   return ls;
}
function FGridControl_getChangedRows(){
   var ls = new TList();
   var rs = this.rows;
   var c = rs.count;
   for(var n=0; n<c; n++){
      var r = rs.get(n);
      if(r.isVisible()){
	      if(r.isDataChanged()){
	         ls.push(r.saveRow());
	      }
      }
   }
   return ls;
}
function FGridControl_getRows(){
   var ls = new TList();
   var rs = this.rows;
   var c = rs.count;
   for(var n=0; n<c; n++){
	  var r = rs.get(n);
	  if(r.isVisible()){
         ls.push(r.saveRow());
	  }
   }
   return ls;
}
function FGridControl_refreshHint(){
   var o = this;
   var h = o.hHint;
   var ds = o.__dataset;
   if(ds && h){
      var ci = 0;
      var r = o.getSelectedRow();
      if(r){
         ci = o.rows.indexOf(r)+1;
      }
      h.innerHTML ='共' +"<FONT color='red' style='font-weight:BOLD '>"+ds.pageCount +"</FONT>" + '页' + "<FONT color='red' style='font-weight:BOLD '>"+ds.total +"</FONT>" + '条记录，' + '当前选中第'+"<FONT color='red' style='font-weight:BOLD '>"+(ds.pageIndex + 1)+"</FONT>" +'页第'+ "<FONT color='red' style='font-weight:BOLD '>"+ci+"</FONT>" + '条记录';
      o.hPage.value = ds.pageIndex + 1;
   }
}
function FGridControl_refreshSelected(){
	var o = this;
	var cs = o.columns;
	var sc = cs.get('_select');
	sc.hSelected.checked = false;
	var rs = o.rows;
	var rc = rs.count;
	for(var n = 0; n < rc; n++){
	   var r = rs.get(n);
	   r.isSelect = false;
	}
}
function FGridControl_hoverRow(r, f){
   var o = this;
   if(f){
      o.__hoverRow = r;
      r.refreshStyle();
   }else{
      if(o.__hoverRow == r){
         o.__hoverRow = null;
      }
      r.refreshStyle();
   }
}
function FGridControl_selectRow(row, reset, force) {
   var o = this;
   var has = false;
   if(reset){
      var rs = o.rows;
      var c = rs.count;
      for(var n=0; n<c; n++){
         var r = rs.get(n);
         if(r != row && r.isSelect){
            r.select(false);
            has = true;
         }
      }
   }
   row.select(has || !row.isSelect || force);
   o.refreshHint();
}
function FGridControl_clearSelectRow(row) {
   var o = this;
   row.select(false);
   o.refreshHint();
}
function FGridControl_clearSelectRows() {
    var o = this;
    var rs = o.rows;
    for(var n = 0; n < rs.count; n++){
       rs.get(n).isSelect = false;
    }
    o.refreshHint();
}
function FGridControl_clickCell(c){
   this.__focusCell = c;
}
function FGridControl_clickRow(r){
   var o = this;
   o.lsnsRowClick.process(r);
   o.__focusRow = r;
   if(o.callEvent('onTableRowClick', r)){
	   return;
   }
   var e = o.__clickRowEvent;
   e.source = o;
   e.caller = r;
   e.handle = 'onTableRowClick';
   RConsole.find(FFormConsole).processEvent(e);
   if(o.isLov){
      o.doubleClickRow(r);
   }
}
function FGridControl_doubleClickRow(r){
   var o = this;
   o.lsnsRowDblClick.process(r);
   if(o.callEvent('onTableRowDoubleClick', r)){
      return;
   }
   var e = o.__doubleClickRowEvent;
   e.source = o;
   e.caller = r;
   e.handle = 'onTableRowDoubleClick';
   RConsole.find(FFormConsole).processEvent(e);
   RConsole.find(FListenerConsole).process(FGridControl, EGridAction.RowDblClick, r, r)
}
function FGridControl_setDataStatus(r, s) {
   var o = this;
   r.dataStatus = s;
   o._statusColumn.setDataStatus(r, s);
}
function FGridControl_dsInsert() {
}
function FGridControl_dsUpdate(r){
   var o = this;
   o.psMode(EMode.Update);
   o.dsFetch(true);
}
function FGridControl_dsDelete() {
}
function FGridControl_doSearch(){
   var o = this;
   o.dsSearchs.clear();
   var cs = o.columns;
   for(var n=0; n<cs.count; n++){
      var c = cs.value(n);
      var v = c.searchValue();
      if(RClass.isClass(c, FColumnCalendar)){
         if(v){
            var si = new TSearchItem();
            si.set(c.dataName, v.value, ESearch.Date, v.format);
            o.dsSearchs.push(si);
         }
      }else{
         if(!RString.isEmpty(v)){
            var si = new TSearchItem();
            si.set(c.dataName, v, ESearch.Like);
            o.dsSearchs.push(si);
         }
      }
   }
   o.dsValues = o.toDeepAttributes();
   o.dsSearch();
}
function FGridControl_push(c){
   var o = this;
   o.base.FContainer.push.call(o, c);
   if(RClass.isClass(c, FColumn)){
      c.table = o;
      o.columns.set(c.name, c);
   }else if(RClass.isClass(c, FTableButton)){
      c.table = o;
      o.buttons.set(c.name, c);
   }
}
function FGridControl_focus(){
   var o = this;
   RConsole.find(FFocusConsole).focusClass(MDataset, o);
}
function FGridControl_pack(){
   var o = this;
   var rfs = o.rows;
   var ct = rfs.count;
   var root = new TNode('Dataset');
   for(var n = 0; n < ct; n++){
      var r = rfs.get(n);
      if(r.isDataChanged()){
         var atts = r.toAttrs();
         var nd = new TNode('Row', atts)
         root.push(nd);
      }
   }
   return root;
}
function FGridControl_setVisible(v){
   var o = this;
   o.base.FContainer.setVisible.call(o, v);
   o.base.MHorizontal.setVisible.call(o, v);
}
function FGridControl_setButtonVisible(n, v){
   var o = this;
   var b = o.buttons.get(n);
   if(b){
      b.setVisible(v);
   }
}
function FGridControl_hideRows(){
   var o = this;
   var rs = o.rows;
   for(var n = rs.count-1; n >= 0 ; n--){
      rs.get(n).setVisible(false);
   }
}
function FGridControl_refreshStyle(){
   var o = this;
   var rs = o.rows;
   var c = rs.count;
   for(var n=0; n<c; n++){
      rs.get(n).refreshStyle();
   }
}
function FGridControl_dispose(){
   var o = this;
   o.base.FContainer.dispose.call(o);
   o.hBorderPanel = null;
   o.hDelayPanel = null;
   o.hDelayForm = null;
   o.hFixPanel = null;
   o.hFixForm = null;
   o.hFixHead = null;
   o.hFixSearch = null;
   o.hHeadPanel = null;
   o.hHeadForm = null;
   o.hHead = null;
   o.hSearch = null;
   o.hColumnPanel = null;
   o.hColumnForm = null;
   o.hFixRows = null;
   o.hFixRowLine = null;
   o.hDataPanel = null;
   o.hDataForm = null;
   o.hRows = null;
   o.hRowLine = null;
   o.hHintForm = null;
   o.hInsertButton = null;
   o.hExtendButton = null;
   o.hExtendText = null;
}
function FGridControl_dump(s) {
   var o = this;
   s = RString.nvlStr(s);
   s.appendLine(RClass.name(o));
   var rs = o.rows;
   for(var n = 0; n < rs.count; n++) {
      s.appendLine(rs.get(n).dump());
   }
   return s;
}
function FGridControl_storeValues(a){
   var o = this;
   if(!a){
      a = new TAttributes();
   }
   var s = o.getSelectRows();
   if(s.count){
      if(1 != s.count){
         RMessage.fatal(o, 'Invalid selected rows. (count={0})', s.count);
      }
      s.get(0).toAttributes(a);
   }
   return a;
}
function FGridControl_buildRows(){
   return;
   var o = this;
   var rs = o.rows;
   if(!rs.count){
      var c = o.dispCount;
      for(var n = 0; n < c; n++){
         var r = RClass.create(FRow);
         r.table = this;
         r.build();
         o.hRows.appendChild(r.hPanel);
         rs.push(r);
      }
   }
}
function FGridControl_createChild(config) {
   var o = this;
   var c = o.base.FContainer.createChild.call(o, config);
   if(RClass.isClass(c, FRow)){
      c.table = o;
      c.row = o.dsLoadRowNode(config);
      o.rows.push(c);
      return null;
   }else if(RClass.isClass(c, FColumnEditControl)){
      c.table = o;
   }
   return c;
}
function FGridControl_setStyleStatus(row, status) {
   var hRow = row.hPanel;
   if (hRow) {
      switch (status) {
         case EStyle.Normal:
            row.select(false);
            break;
         case EStyle.Select:
            row.select(true);
            break;
      }
   }
}
function FGridControl_buildRow(row) {
   var o = this;
   var cs = o.columns;
   for ( var n = 0; n < cs.count; n++) {
      var c = cs.value(n);
      var cell = c.createCell(row);
      if(c.dataName){
         cell.set(RString.nvl(row.get(c.dataName), c.dataDefault));
      }
      row.push(cell);
   }
   return row;
}
function FGridControl_clearSelectAll() {
   var o = this;
   var cs = o.columns;
   var sc = cs.get('_select');
   sc.hSelected.checked = false;
}
function FGridControl_appendRow(row) {
   this.hRows.appendChild(row.hRow);
   this.rows.push(row);
}
function FGridControl_deleteRow(r) {
   var o = this;
   r = RObject.nvl(r, o.selectedRow);
   if (!r) {
      return alert('Please select row.');
   }
   if (r.isExist()) {
      if (r.isDelete()) {
         r.doNormal();
         o.setDataStatus(r, EDataStatus.Unknown);
         o.setStyleStatus(r, EStyle.Select);
      } else {
         r.doDelete();
         o.setDataStatus(r, EDataStatus.Delete);
         o.setStyleStatus(r, EStyle.Delete);
      }
   } else {
      r.release();
   }
}
function FGridControl_clearRows() {
   var o = this;
   var c = o.rows.count;
   for(var n=0; n<c; n++){
      var r = o.rows.get(n);
      if(r){
         r.dispose();
      }
   }
   o.rows.clear();
   RHtml.clear(o.hRows);
}
function FGridControl_onColumnTreeService(g){
   var o = this;
   var d = g.resultDatasets.get(g.path);
   var rs = d.rows;
   if(rs && rs.count > 0){
      var pr = o.focusRow;
      pr.extdStatus = true;
      pr.psResize();
      var idx = pr.hPanel.rowIndex + 1;
      for(var n = 0; n < rs.count; n++){
         var r = RClass.create(FRow);
         r.table = o;
         pr.childRows.push(r);
         r.parentRow = pr;
         r.buildChild(o.hFixRows, o.hRows, idx + n);
         r.loadRow(rs.get(n));
      }
   }
}
function FGridControl_getRowType(){
   var o = this;
   var cs = o.components;
   var ct = cs.count;
   for(var n = 0; n < ct; n++){
      var c = cs.value(n);
      if(RClass.isClass(c, FRowType)){
         return c;
      }
   }
}
function FGridControl_onColumnTreeClick(s, e){
   var o = this;
   var c = o.getRowType();
   if(!c){
      return;
   }
   var r = s.row;
   if(r.childRows && r.childRows.count > 0){
      if(r.extended){
         r.hideChild();
      }else{
         r.showChild();
      }
      r.extended = !r.extended;
      if(r.extended){
         s.hImg.src = s.styleIconPath('Fold', FColumnTree);
      }else{
         s.hImg.src = s.styleIconPath('Expend', FColumnTree);
      }
   }else{
      o.focusRow = s.row;
      if(o.focusRow.row.get('ochd') == 'Y'){
         s.row.extended = true;
         s.hImg.src = s.styleIconPath('Fold', FColumnTree);
         var name = s.row.get('otyp');
         var tb = s.row.table;
         var rt = tb.component(name);
         var ds = o.topControl(MDataset);
         var g = new TDatasetFetchArg(ds.name, ds.formId, ds.dsPageSize, ds.dsPageIndex, null, null, o.fullPath(), rt.formResearch);
         ds.dsSearchs.clear();
         if(rt && rt.formWhere){
            var si = new TSearchItem();
            si.set(rt.dataName, rt.formWhere, ESearch.Source);
            ds.dsSearchs.push(si);
         }
         g.force = true;
         g.reset = true;
         g.searchs = ds.dsSearchs;
         var ats = new TAttributes();
         s.row.toDeepAttributes(ats);
         g.values = ats;
         g.callback = new TInvoke(o, o.onColumnTreeService);
         RConsole.find(FDatasetConsole).fetch(g);
      }
   }
}
function FRow(o){
   o = RClass.inherits(this, o, FRowControl);
   o.hFixPanel    = null;
   o.build        = FRow_build;
   o.select       = FRow_select;
   o.setVisible   = FRow_setVisible;
   o.push         = FRow_push;
   o.refreshSize  = FRow_refreshSize;
   o.refreshStyle = FRow_refreshStyle;
   o.dispose      = FRow_dispose;
   return o;
}
function FRow_build(){
   var o = this;
   var t = o.table;
   o.hFixPanel = RBuilder.create(null, 'TR', o.style('Panel'));
   o.base.FRowControl.build.call(o);
}
function FRow_select(v){
   var o = this;
   o.isSelect = v;
   var c = v ? EColor.RowSelect : EColor.Row;
   o.hFixPanel.style.backgroundColor = c;
   o.hPanel.style.backgroundColor = c;
   o.refreshStyle();
}
function FRow_setVisible(f){
   var o = this;
   o.__visible = f;
   var s = f ? 'block' : 'none';
   o.hFixPanel.style.display = s;
   o.hPanel.style.display = s;
}
function FRow_push(c){
   var o = this;
   o.base.FRowControl.push.call(o, c);
   if(c.column.dispFixed){
      o.hFixPanel.appendChild(c.hPanel);
   }else{
      o.hPanel.appendChild(c.hPanel);
   }
}
function FRow_refreshSize(){
   this.hPanel.style.pixelHeight = this.hFixPanel.offsetHeight;
}
function FRow_refreshStyle(){
   var o = this;
   if(o.hPanel.offsetHeight > o.hFixPanel.offsetHeight){
      o.hFixPanel.style.pixelHeight = o.hPanel.offsetHeight;
   }else{
      o.hPanel.style.pixelHeight = o.hFixPanel.offsetHeight;
   }
   if(o.table.isLov){
      o.hFixPanel.style.cursor = 'hand';
   }
   o.base.FRowControl.refreshStyle.call(o);
}
function FRow_dispose(){
   var o = this;
   o.base.FRowControl.dispose.call(o);
   RMemory.freeHtml(o.hFixPanel);
   o.hFixPanel = null;
}
function FRowBar(o){
   o = RClass.inherits(this, o, FControl);
   o.table         = null;
   o.row           = null;
   o.onBarLeave    = RClass.register(o, new HMouseLeave('onBarLeave'), FRowBar_onBarLeave);
   o.onRowClick    = RClass.register(o, new HClick('onRowClick'), FRowBar_onRowClick);
   o.onButtonEnter = RClass.register(o, new HMouseEnter('onButtonEnter'), FRowBar_onButtonEnter);
   o.onButtonLeave = RClass.register(o, new HMouseLeave('onButtonLeave'), FRowBar_onButtonLeave);
   o.onInsertClick = RClass.register(o, new HClick('onInsertClick'), FRowBar_onInsertClick);
   o.onCopyClick   = RClass.register(o, new HClick('onCopyClick'), FRowBar_onCopyClick);
   o.oeBuild       = FRowBar_oeBuild;
   o.buildButton   = FRowBar_buildButton;
   o.linkCell      = FRowBar_linkCell;
   return o;
}
function FRowBar_onRowClick(){
   var o = this;
   o.hide();
   RConsole.find(FListenerConsole).process(FGridControl, EGridAction.RowClick, o.row, o.row);
}
function FRowBar_onInsertClick(){
   var o = this;
   var t = o.table;
   var i = t.rows.indexOf(o.row);
   var r = t.createRow();
   t.insertRow(i, r);
   r.doInsert();
   r.refreshStyle();
   o.hide();
}
function FRowBar_onCopyClick(){
   var o = this;
   var t = o.table;
   var i = t.rows.indexOf(o.row);
   var r = t.createRow();
   t.insertRow(i, r);
   r.doInsert();
   var cc = o.row.cells.count;
   for(var n=0; n<cc; n++){
      var c = r.cells.value(n);
      if(c.column.editCopy){
         c.set(o.row.cells.value(n).reget());
      }
   }
   r.refreshStyle();
   o.hide();
}
function FRowBar_onButtonEnter(e){
   var o = this;
   var hs = e.hSource.style;
   hs.margin = 0;
   hs.borderLeft = '1 solid #666666';
   hs.borderTop = '1 solid #666666';
   hs.borderRight = '1 solid #CCCCCC';
   hs.borderBottom = '1 solid #CCCCCC';
}
function FRowBar_onButtonLeave(e){
   var hs = e.hSource.style;
   hs.margin = 1;
   hs.border = '0';
}
function FRowBar_onBarLeave(){
   this.hide();
}
function FRowBar_oeBuild(e){
   var o = this;
   o.base.FControl.oeBuild.call(o, e);
   var hp = o.hPanel;
   hp.style.width = 180;
   hp.style.position = 'absolute';
   hp.style.zIndex = 4;
   o.attachEvent('onBarLeave', hp);
   var hf = o.hTitleForm = RBuilder.appendTable(o.hPanel, null, 0, 2);
   hf.width = '100%';
   hf.style.backgroundColor = '#999999';
   hf.style.borderTop = '1 solid #CCCCCC'
   hf.style.borderLeft = '1 solid #CCCCCC'
   hf.style.borderRight = '1 solid #666666'
   hf.style.borderBottom = '1 solid #666666'
   var hr = hf.insertRow();
   var hc = o.hSelect = hr.insertCell();
   hc.style.cursor = 'hand';
   o.attachEvent('onRowClick', hc);
   RBuilder.appendIcon(hc, 'ctl.FColumnStatus_NormalEnter');
   o.buildButton(hr.insertCell(), 'Insert');
   o.buildButton(hr.insertCell(), 'Copy');
   var hc = hr.insertCell();
   hc.innerHTML = '&nbsp;';
   var hc = o.hTools = hr.insertCell();
   var hf = o.hToolForm = RBuilder.appendTable(o.hPanel);
   hf.cellPadding = 4;
   hf.width = '100%';
   hf.style.color = '#666666';
   hf.style.borderLeft = '1 solid #CCCCCC';
   hf.style.borderRight = '1 solid #CCCCCC';
   hf.style.borderBottom = '1 solid #CCCCCC';
   hf.style.backgroundColor = '#F8F8C0'
   var hr = hf.insertRow();
   var hc = o.hStatus = hr.insertCell();
   return EEventStatus.Stop;
}
function FRowBar_buildButton(hp, n){
   var o = this;
   var nl = n.toLowerCase();
   hp.width = 1;
   var hf = o['h' + n + 'ButtonForm'] = RBuilder.appendTable(hp, null, 0, 2);
   hf.width = 1;
   hf.style.margin = 1;
   hf.style.cursor = 'hand';
   o.attachEvent('onButtonEnter', hf);
   o.attachEvent('onButtonLeave', hf);
   o.attachEvent('on' + n + 'Click', hf);
   var hbr = hf.insertRow();
   RBuilder.appendIcon(hbr.insertCell(), 'tool.' + nl);
   var hc = hbr.insertCell();
   hc.noWrap = true;
   hc.style.padding = '0 4';
   hc.style.color = '#FFFFFF';
   RBuilder.appendText(hc, RContext.get('FRowBar:' + n));
}
function FRowBar_linkCell(c){
   var o = this;
   var hp = o.hPanel;
   var hs = hp.style;
   hs.display = 'none';
   var r = o.row = c.row;
   var t = r.table;
   var dr = t.calculateDataSize();
   var cw = c.hPanel.offsetWidth;
   hs.display = 'block';
   hs.pixelTop = c.hPanel.offsetTop + dr.top - 2;
   hs.pixelLeft = 0;
   o.hSelect.width = cw - 2;
   o.hToolForm.style.marginLeft = cw - 1;
   o.hToolForm.style.pixelWidth = o.hTitleForm.offsetWidth - cw + 1;
   var ouid = r.cells.get('ouid')
   var id = ouid ? ouid.get() : '';
   var s = '';
   if(id){
      if(t._formLinked){
         s = RContext.get('FCellStatus:Detail', id);
      }else{
         s = RContext.get('FCellStatus:Code', id);
      }
   }
   o.hStatus.innerText = s;
}
function FRowControl(o){
   o = RClass.inherits(this, o, FContainer);
   o.stHover          = RClass.register(o, new TStyle('Hover'));
   o.stSelect         = RClass.register(o, new TStyle('Select'));
   o.__statusCell     = null;
   o.__clearProcess   = null;
   o.__resetProcess   = null;
   o.__loadProcess    = null;
   o.__saveProcess    = null;
   o.__recordProcess  = null;
   o.__visible        = true;
   o.table            = null;
   o.cells            = null;
   o.rows             = null;
   o.attributes       = null;
   o.selectAble       = true;
   o.status           = ERowStatus.Update;
   o.isSelect         = false;
   o.level            = 0;
   o.existed          = false;
   o.extended         = false;
   o.loaded           = false;
   o.builded          = false;
   o.construct        = FRowControl_construct;
   o.build            = FRowControl_build;
   o.buildChildren    = FRowControl_buildChildren;
   o.isDataChanged    = FRowControl_isDataChanged;
   o.isVisible        = FRowControl_isVisible;
   o.getIndex         = FRowControl_getIndex;
   o.getId            = FRowControl_getId;
   o.getVersion       = FRowControl_getVersion;
   o.getStatus        = FRowControl_getStatus;
   o.cell             = FRowControl_cell;
   o.get              = FRowControl_get;
   o.reget            = FRowControl_reget;
   o.set              = FRowControl_set;
   o.loadRow          = FRowControl_loadRow;
   o.saveRow          = FRowControl_saveRow;
   o.loadValue        = FRowControl_loadValue;
   o.saveValue        = FRowControl_saveValue;
   o.recordValue      = FRowControl_recordValue;
   o.toAttributes     = FRowControl_toAttributes;
   o.toDeepAttributes = FRowControl_toDeepAttributes;
   o.select           = FRowControl_select;
   o.setVisible       = FRowControl_setVisible;
   o.extend           = FRowControl_extend;
   o.doInsert         = FRowControl_doInsert;
   o.doDelete         = FRowControl_doDelete;
   o.push             = FRowControl_push;
   o.refresh          = FRowControl_refresh;
   o.refreshSize      = RMethod.empty;
   o.refreshStyle     = FRowControl_refreshStyle;
   o.dump             = FRowControl_dump;
   return o;
}
function FRowControl_construct(){
   var o = this;
   o.base.FContainer.construct.call(o);
   o.cells = new TMap();
   o.rows = new TList();
   o.__clearProcess = new TEventProcess(o, 'oeClearValue', MEditValue);
   o.__resetProcess = new TEventProcess(o, 'oeResetValue', MEditValue);
   o.__loadProcess = new TEventProcess(o, 'oeLoadValue', MEditValue);
   o.__saveProcess = new TEventProcess(o, 'oeSaveValue', MEditValue);
   o.__recordProcess = new TEventProcess(o, 'oeRecordValue', MEditValue);
}
function FRowControl_build(){
   var o = this;
   var t = o.table;
   var hp = o.hPanel = RBuilder.create(null, 'TR', o.style('Panel'));
   t.linkEvent(o, 'onRowMouseEnter', hp, t.onRowMouseEnter);
   t.linkEvent(o, 'onRowMouseLeave', hp, t.onRowMouseLeave);
   if(o.table.isLov){
      o.hPanel.style.cursor = 'hand';
   }
   var cs = o.table.columns;
   var cc = cs.count;
   for(var n=0; n<cc; n++){
      o.push(cs.value(n).createCell());
   }
}
function FRowControl_buildChildren(){
   var o = this;
   var t = o.table;
   var hfr = o.hFixPanel = hfp.insertRow(idx);
   hfr.className = o.style('Panel');
   var hr = o.hPanel = hp.insertRow(idx);
   hr.className = o.style('Panel');
   var cs = o.table.columns;
   var cc = cs.count;
   for(var n=0; n<cc; n++){
      var c = cs.value(n);
      var cl = c.createCell(o);
      if(c.dispFixed){
         hfr.appendChild(cl.hPanel);
      }else{
         hr.appendChild(cl.hPanel);
      }
      o.cells.set(c.dataName, cl);
   }
   o.doRefresh()
}
function FRowControl_isDataChanged(){
   var o = this;
   var cs = o.cells;
   for(var n=cs.count-1; n>=0; n--){
      if(cs.value(n).isDataChanged()){
         return true;
      }
   }
   return false;
}
function FRowControl_isVisible(){
	var o = this;
	return o.__visible;
}
function FRowControl_getIndex(){
   return this.hPanel.rowIndex;
}
function FRowControl_getId(){
   var c = this.cells.get('ouid');
   return c ? c.reget() : '';
}
function FRowControl_getVersion(){
   var c = this.cells.get('over');
   return c ? c.reget() : '';
}
function FRowControl_getStatus(){
   return this.__statusCell;
}
function FRowControl_cell(n){
   return this.cells.value(n);
}
function FRowControl_get(n){
   return this.cells.get(n).get();
}
function FRowControl_reget(n){
   return this.cells.get(n).reget();
}
function FRowControl_set(n, v){
   this.cells.get(n).set(v);
}
function FRowControl_loadRow(r){
   var o = this;
   o.process(o.__clearProcess);
   var e = o.__loadProcess;
   e.values = r;
   o.process(e);
}
function FRowControl_saveRow(r){
   var o = this;
   if(!r){
      r = new TRow();
   }
   var e = o.__saveProcess;
   e.values = r;
   o.process(e);
   r.set('_status', o.status);
   return r;
}
function FRowControl_loadValue(v){
   this.loadRow(v);
}
function FRowControl_saveValue(v){
   this.saveRow(v);
}
function FRowControl_recordValue(){
   this.process(this.__recordProcess);
}
function FRowControl_toAttributes(v){
   this.saveRow(v);
}
function FRowControl_toDeepAttributes(r){
   var o = this;
   var ts = new TList();
   var p = o.table;
   while(p){
      if(p != o.table && RClass.isClass(p, MDataset)){
         ts.push(p);
      }
      if(!p.parent){
         break;
      }
      p = p.topControl(MDataset);
   }
   for(var n=ts.count-1; n>=0; n--){
      var m = ts.get(n);
      if(RClass.isClass(m, FForm)){
         m.toAttributes(r);
      }else if(RClass.isClass(m, FTable)){
         var rs = m.getSelectRows();
         if(1 != rs.count){
            return RMessage.fatal(o, 'Invalid selected rows. (count={0})', rs.count);
         }
         rs.get(0).toAttributes(r);
      }
   }
   o.toAttributes(r);
}
function FRowControl_select(v){
   var o = this;
   o.isSelect = v;
   o.hPanel.style.backgroundColor = v ? EColor.RowSelect : EColor.Row;
   o.refreshStyle();
}
function FRowControl_setVisible(f){
   var o = this;
   o.__visible = f;
   o.hPanel.style.display = f ? 'block' : 'none';
}
function FRowControl_extend(v){
   var o = this;
   var rs = o.rows;
   if(rs && rs.count){
      var rc = rs.count;
      for(var n=0; n<rc; n++){
         var r = rs.get(n);
         if(v){
            r.setVisible(true);
            r.extend(r.extended);
         }else{
            r.setVisible(false);
         }
         r.refresh();
      }
   }
   o.extended = v;
}
function FRowControl_doInsert(){
   var o = this;
   if(!o.row){
      o.row = new TRow();
   }
   o.status = ERowStatus.Insert;
   o.table.setDataStatus(o, ERowStatus.Insert);
}
function FRowControl_doDelete(){
   var o = this;
   o.status = ERowStatus.Delete;
   o.table.setDataStatus(o, ERowStatus.Delete);
}
function FRowControl_push(c){
   var o = this;
   o.base.FContainer.push.call(o, c);
   c.row = o;
   o.cells.set(c.column.dataName, c);
   if(RClass.isClass(c, FCellStatus)){
      o.__statusCell = c;
   }
}
function FRowControl_refresh(){
   var o = this;
   o.table.setDataStatus(o, o.isDataChanged() ? ERowStatus.Changed : ERowStatus.Normal);
}
function FRowControl_refreshStyle(){
   var o = this;
   var cs = o.cells;
   if(cs){
      for(var n=cs.count-1; n>=0; n--){
         cs.value(n).refreshStyle();
      }
   }
}
function FRowControl_dump(s){
   var o = this;
   s = RString.nvlStr(s);
   s.append(RClass.dump(o), '[');
   s.append(o.isSelect ? 'S' : '_');
   s.append(']');
   s.append(o.saveRow().dump());
   return s;
}
function FRowType(o){
   o = RClass.inherits(this, o, FComponent);
   o.service         = RClass.register(o, new TPtyStr('service'));
   o.action          = RClass.register(o, new TPtyStr('action'));
   o.formName        = RClass.register(o, new TPtyStr('formName'));
   o.formWhere       = RClass.register(o, new TPtyStr('formWhere'));
   o.formOrder       = RClass.register(o, new TPtyStr('formOrder'));
   o.formResearch    = RClass.register(o, new TPtyStr('formResearch'));
   return o;
}
function FTable(o) {
   o = RClass.inherits(this, o, FGridControl);
   o.onResizeAfter = FTable_onResizeAfter;
   o.onBuildData   = FTable_onBuildData;
   o.oeResize      = FTable_oeResize;
   o.oeRefresh     = FTable_oeRefresh;
   o.pushColumn    = FTable_pushColumn;
   return o;
}
function FTable_onResizeAfter(){
   var o = this;
   var hdp = o.hDataPanel;
   var hfp = o.hFixPanel;
   var sw = RHtml.scrollWidth(hdp);
   var sh = RHtml.scrollHeight(hdp);
   o.hHeadPanel.style.pixelWidth = hdp.offsetWidth - hfp.offsetWidth - sw;
   o.hColumnPanel.style.pixelHeight = hdp.offsetHeight - hfp.offsetHeight - sh + 1;
}
function FTable_onBuildData(){
   var o = this;
   var hbp = o.hBorderPanel;
   var hfp = o.hFixPanel = RBuilder.appendDiv(hbp);
   hfp.style.zIndex = 2;
   hfp.style.position = 'absolute';
   var hff = o.hFixForm = RBuilder.appendTable(hfp, null, 1);
   var hffb = RBuilder.append(hff, 'TBODY');
   hff.style.tableLayout = 'fixed';
   hff.frame = 'rhs';
   hff.borderColorLight = '#D0D0D0';
   hff.borderColorDark = '#EEEEEE';
   o.hFixHead =  RBuilder.append(hffb, 'TR');
   o.hFixSearch = RBuilder.append(hffb, 'TR');
   o.hFixTotal = RBuilder.append(hffb, 'TR');
   o.hFixTotal.style.display = 'none';
   var hhp = o.hHeadPanel = RBuilder.appendDiv(hbp);
   hhp.style.zIndex = 1;
   hhp.style.position = 'absolute';
   hhp.style.overflowX = 'hidden';
   hhp.style.width = 1;
   var hhf = o.hHeadForm = RBuilder.appendTable(hhp, null, 1);
   hhf.frame = 'rhs';
   hhf.style.tableLayout = 'fixed';
   hhf.borderColorLight = '#D0D0D0';
   hhf.borderColorDark = '#EEEEEE';
   o.hHead = hhf.insertRow();
   o.hSearch = hhf.insertRow();
   o.hTotal = hhf.insertRow();
   o.hTotal.style.display = 'none';
   var hcp = o.hColumnPanel = RBuilder.appendDiv(hbp, o.style('DataPanel'));
   hcp.style.zIndex = 1;
   hcp.style.position = 'absolute';
   hcp.style.overflowY = 'hidden';
   var hcf = o.hColumnForm = RBuilder.appendTable(hcp, o.style('DataForm'), 0, 0, 1);
   o.hFixRows = RBuilder.append(hcf, 'TBODY');
   o.hFixRowLine = RBuilder.append(o.hFixRows, 'TR');
   var hdp = o.hDataPanel = RBuilder.appendDiv(hbp, o.style('DataPanel'));
   hdp.width = '100%';
   hdp.height = '100%';
   var hdf = o.hDataForm = RBuilder.appendTable(hdp, o.style('DataForm'), 0, 0, 1);
   o.hRows = RBuilder.append(hdf, 'TBODY');
   o.hRowLine = RBuilder.append(o.hRows, 'TR');
   o.attachEvent('onHeadMouseDown', o.hHeadForm, o.onHeadMouseDown);
   o.attachEvent('onHeadMouseMove', o.hHeadForm, o.onHeadMouseMove);
   o.attachEvent('onHeadMouseUp', o.hHeadForm, o.onHeadMouseUp);
   o.attachEvent('onDataScroll', o.hDataPanel, o.onDataScroll);
   o.panelNavigator = true;
}
function FTable_oeResize(e){
   var o = this;
   var h = o.hPanel;
   if(!h.offsetWidth || !h.offsetHeight){
      return;
   }
   var hp = o.border.hPanel;
   var hcf = o.hTitleForm;
   var hfp = o.hFixPanel;
   var hhp = o.hHeadPanel;
   var hcp = o.hColumnPanel;
   var hdp = o.hDataPanel;
   hhp.style.display = hcp.style.display = hdp.style.display = 'none';
   var ow = o.hBorderPanel.offsetWidth;
   var oh = o.hBorderPanel.offsetHeight;
   hhp.style.display = hcp.style.display = hdp.style.display = 'block';
   hhp.style.pixelWidth = ow - hfp.offsetWidth;
   hcp.style.pixelHeight = oh - hfp.offsetHeight - 1 - hcf.offsetHeight;
   hdp.style.pixelWidth = ow;
   hdp.style.pixelHeight = oh - hcf.offsetHeight;
   var c = o.rows.count;
   for(var n=0; n<c; n++){
      o.rows.get(n).refreshSize();
   }
   if(o.dpScrollLeft){
      hdp.scrollLeft = o.dpScrollLeft;
      o.dpScrollLeft = null;
   }
   RConsole.find(FEventConsole).push(o.eventResizeAfter);
   return EEventStatus.Stop;
}
function FTable_oeRefresh(e){
   var o = this;
   o.base.FGridControl.oeRefresh.call(o, e);
   if(e.isAfter()){
      var hcf = o.hTitleForm;
      var hfp = o.hFixPanel;
      var hhp = o.hHeadPanel;
      var hcp = o.hColumnPanel;
      var hdp = o.hDataPanel;
      var hcfh = hcf.offsetHeight;
      var hfpw = hfp.offsetWidth;
      var hfph = hfp.offsetHeight;
      hcp.style.display = hdp.style.display = 'none';
      var ow = o.hBorderPanel.offsetWidth;
      var oh = o.hBorderPanel.offsetHeight;
      hcp.style.display = hdp.style.display = 'block';
      hfp.style.pixelTop = hcfh;
      hhp.style.pixelTop = hcfh;
      hhp.style.pixelLeft = hfpw;
      hhp.style.pixelWidth = ow - hfpw;
      hhp.style.pixelHeight = hfph;
      o.hHead.style.pixelHeight = o.hFixHead.offsetHeight;
      o.hSearch.style.pixelHeight = o.hFixSearch.offsetHeight;
      hcp.style.pixelTop = hcfh + hfph;
      hcp.style.pixelHeight = oh - hcfh - hfph;
      hdp.style.paddingLeft = hfpw;
      hdp.style.paddingTop = hfph;
      hdp.style.pixelWidth = ow;
      hdp.style.pixelHeight = oh - hcfh;
      var ca = null;
      var aw = ow;
      var cs = o.columns;
      for(var n=0; n<cs.count; n++){
         var c = cs.value(n);
         if(c.isDisplay){
            if(c.dispAuto){
               if(ca){
                  return RMessage.fatal(o, null, 'Too many auto column! (name1={0},name2={1})', ca.name, c.name);
               }
               ca = c;
            }else{
               aw -= c.hPanel.offsetWidth;
            }
         }
      }
      if(ca){
         ca.setWidth(Math.max(aw - 1, ca.width ? ca.width : 120));
      }
   }
}
function FTable_pushColumn(c){
   var o = this;
   if(c.dispFixed){
      o.hFixHead.appendChild(c.hPanel);
      o.hFixSearch.appendChild(c.hSearchPanel);
      o.hFixTotal.appendChild(c.hTotalPanel);
      o.hFixRowLine.appendChild(c.hFixPanel);
   }else{
      o.hHead.appendChild(c.hPanel);
      o.hSearch.appendChild(c.hSearchPanel);
      o.hTotal.appendChild(c.hTotalPanel);
      o.hRowLine.appendChild(c.hFixPanel);
   }
   o.push(c);
}
function FTableButton(o){
   o = RClass.inherits(this, o, FControl, MDisplay, MDesign);
   o.labelPosition      = RClass.register(o, new TPtyStr('labelPosition', EPosition.Left));
   o.icon               = RClass.register(o, new TPtyStr('icon'));
   o.type               = RClass.register(o, new TPtyStr('type'));
   o.action             = RClass.register(o, new TPtyStr('action'));
   o.dataAction         = RClass.register(o, new TPtyStr('dataAction'));
   o.service            = RClass.register(o, new TPtyStr('service'));
   o.target             = RClass.register(o, new TPtyStr('target'));
   o.page               = RClass.register(o, new TPtyStr('page'));
   o.method             = RClass.register(o, new TPtyStr('method'));
   o.iconDisable        = RClass.register(o, new TPtyStr('iconDisable'));
   o.attributes         = RClass.register(o, new TPtyStr('attributes'));
   o.editUrl            = RClass.register(o, new TPtyStr('editUrl'));
   o.editForm           = RClass.register(o, new TPtyStr('editForm'));
   o.stIcon             = RClass.register(o, new TStyle('Icon'));
   o.stLabel            = RClass.register(o, new TStyle('Label'));
   o.stForm             = RClass.register(o, new TStyle('Form'));
   o.stIconPanel        = RClass.register(o, new TStyleIcon('Panel'));
   o.__process          = false;
   o.lsnsClick          = new TListeners();
   o.hForm              = null;
   o.hLeftButton        = null;
   o.hMiddleButton      = null;
   o.hRightButton       = null;
   o.hLabelPanel        = null;
   o.hLabel             = null;
   o.onButtonEnter      = RClass.register(o, new HMouseEnter('onButtonEnter'), FTableButton_onButtonEnter);
   o.onButtonLeave      = RClass.register(o, new HMouseLeave('onButtonLeave'), FTableButton_onButtonLeave);
   o.onButtonDown       = RClass.register(o, new HMouseDown('onButtonDown'), FTableButton_onButtonDown);
   o.onButtonUp         = RClass.register(o, new HMouseUp('onButtonUp'), FTableButton_onButtonUp);
   o.onButtonClickDelay = FTableButton_onButtonClickDelay;
   o.onClick            = FTableButton_onClick;
   o.onButtonClick      = RClass.register(o, new HClick('onButtonClick'), FTableButton_onButtonClick);
   o.oeBuild            = FTableButton_oeBuild;
   o.oeMode             = FTableButton_oeMode;
   o.setLabel           = FTableButton_setLabel;
   o.doClick            = FTableButton_doClick;
   o.dispose            = FTableButton_dispose;
   return o;
}
function FTableButton_onButtonEnter(e){
   var o = this;
   if(!o._disabled){
	  o.hLeftButton.background = o.styleIconPath('HoverLeft');
	  o.hMiddleButton.background = o.styleIconPath('HoverMiddle');
	  o.hRightButton.background = o.styleIconPath('HoverRight');
   }
}
function FTableButton_onButtonLeave(e){
   var o = this;
   if(!o._disabled){
	  o.hLeftButton.background = o.styleIconPath('ButtonLeft');
	  o.hMiddleButton.background = o.styleIconPath('Button');
	  o.hRightButton.background = o.styleIconPath('ButtonRight');
   }
}
function FTableButton_onButtonDown(e){
   var o = this;
   if(!o._disabled){
	  o.hLeftButton.background = o.styleIconPath('PressLeft');
	  o.hMiddleButton.background = o.styleIconPath('PressMiddle');
	  o.hRightButton.background = o.styleIconPath('PressRight');
   }
}
function FTableButton_onButtonUp(e){
   var o = this;
   if(!o._disabled){
	  o.hLeftButton.background = o.styleIconPath('ButtonLeft');
	  o.hMiddleButton.background = o.styleIconPath('Button');
	  o.hRightButton.background = o.styleIconPath('ButtonRight');
   }
}
function FTableButton_onButtonClickDelay(e){
   var o = this;
   o.__process = false;
   o.clickActive.status = EActive.Sleep;
}
function FTableButton_onClick(e){
   this.doClick();
}
function FTableButton_onButtonClick(e){
   this.doClick();
}
function FTableButton_oeBuild(e){
   var o = this;
   o.base.FControl.oeBuild.call(o, e);
   var hp = o.hPanel;
   hp.style.paddingTop = 2;
   hp.style.pixelHeight = 26;
   var hf = o.hForm = RBuilder.appendTable(hp);
   var hr = hf.insertRow();
   hr.height = 22;
   var hl = o.hLeftButton = hr.insertCell();
   hl.width = 3;
   hl.background = o.styleIconPath('ButtonLeft');
   var hm = o.hMiddleButton = hr.insertCell();
   hm.background = o.styleIconPath('Button');
   var hrb = o.hRightButton = hr.insertCell();
   hrb.width = 3;
   hrb.background = o.styleIconPath('ButtonRight');
   hf.style.cursor = 'hand';
   hf.style.borderLeft = '0 solid #FFFFFF';
   hf.style.borderTop = '0 solid #FFFFFF';
   hf.style.borderRight = '0 solid #FFFFFF';
   hf.style.borderBottom = '0 solid #FFFFFF';
   o.attachEvent('onButtonEnter', hf, o.onButtonEnter);
   o.attachEvent('onButtonLeave', hf, o.onButtonLeave);
   o.attachEvent('onButtonDown', hf, o.onButtonDown);
   o.attachEvent('onButtonUp', hf, o.onButtonUp);
   o.attachEvent('onButtonClick', hf);
   var hTb = RBuilder.appendTable(hm);
   var hr  = hTb.insertRow();
   var hc = hr.insertCell();
   hc.width = 10;
   if(o.icon){
      var hc = hr.insertCell();
      hc.width = 16;
      o.hIcon = RBuilder.appendIcon(hc, o.icon);
      hcc = hr.insertCell();
      hcc.width = 4;
   }
   if(o.label){
      var hc = hr.insertCell();
      hc.align = 'center';
      hc.noWrap = true;
      o.hLabel = RBuilder.appendText(hc, o.label);
      o.hLabel.style.font = 'icon';
   }
   var hc = o.hFormEnd = hr.insertCell();
   hc.width = 10;
   o.__process = false;
   var ca = o.clickActive = new TActive(o, o.onButtonClickDelay);
   ca.interval = 500;
   ca.status = EActive.Sleep;
   RConsole.find(FActiveConsole).push(ca);
   return EEventStatus.Stop;
}
function FTableButton_oeMode(e){
   var o = this;
   o.base.FControl.oeMode.call(o, e);
   o.base.MDisplay.oeMode.call(o, e);
   return EEventStatus.Stop;
}
function FTableButton_setLabel(v){
   var o = this;
   o.label = v;
   o.hLabel.innerText = v;
   o.hLabel.noWrap = true;
}
function FTableButton_doClick(){
   var o = this;
   if(o.__process){
      return;
   }
   o.__process = true;
   o.clickActive.status = EActive.Active;
   o.lsnsClick.process(this);
   if(o.action){
      eval(o.action);
   }
   if(o.page){
      var form = RHtml.form(o.hButton);
      var p = RPage.parse(o.page);
      if(o.method){
         p.action = o.method;
      }
      p.split(o.attributes);
      var f = o.topControl(MDataset);
      if(f){
         var as = new TAttributes();
         f.saveValue(as);
         if(form && form.form_pack){
            form.form_pack.value = as.pack();
         }
      }
      p.post(form, RString.nvl(o.target, '_self'));
   }
   if(o.editUrl){
      var w = RConsole.find(FTableButtonConsole).find();
      w.linkUrl(o.editUrl);
      w.show();
   }
   if(o.editForm){
      var w = RConsole.find(FTableButtonFormConsole).find();
      w.linkForm(o);
      w.show();
   }
}
function FTableButton_dispose(){
   var o = this;
   o.base.FControl.dispose.call(o);
   o.hForm = null;
   o.hFormEnd = null;
   o.hLabel = null;
}
function FWebBrowser(o){
   o = RClass.inherits(this, o, FBrowserControl, MTop);
   return o;
}
function FWebFixGrid(o){
   o = RClass.inherits(this, o, FFixGrid, MTop);
   return o;
}
function FWebFixTable(o){
   o = RClass.inherits(this, o, FFixTable, MTop);
   return o;
}
function FWebGrid(o){
   o = RClass.inherits(this, o, FGrid, MTop);
   return o;
}
function FWebPicker(o){
   o = RClass.inherits(this, o, FTable, MTop);
   return o;
}
function FWebTable(o){
   o = RClass.inherits(this, o, FTable, MTop);
   o.oeBuild = FWebTable_oeBuild;
   return o;
}
function FWebTable_oeBuild(e){
   var o = this;
   var r = o.base.FTable.oeBuild.call(o, e);
   if(e.isAfter()){
   }
   return r;
}
function MCellDropable(o){
   o = RClass.inherits(this, o);
   o.testBlur = MCellDropable_testBlur;
   return o;
}
function MCellDropable_testBlur(c){
   var o = this;
   if(o.editor && RClass.isClass(c, FEditor)){
      return o.editor != c;
   }
   return true;
}
function TCellButton(){
   var o = this;
   o.button = null;
   o.hPanel = null;
   return o;
}
function TCellRadio(){
   var o = this;
   return o;
}
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
function FToolButton_oeBuild(event){
   var o = this;
   o.base.FControl.oeBuild.call(o, event);
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
function FTreeColumn(o){
   o = RClass.inherits(this, o, FControl);
   o.icon         = RClass.register(o, new TPtyStr('icon'));
   o.dataName     = RClass.register(o, new TPtyStr('dataName'));
   o.display      = RClass.register(o, new TPtyBool('display', EBool.False));
   o.config       = RClass.register(o, new TPtyCfg('config'));
   o.oeBuild      = FTreeColumn_oeBuild;
   o.onBuildPanel = FTreeColumn_onBuildPanel;
   return o;
}
function FTreeColumn_oeBuild(event){
   var o = this;
   var r = o.base.FControl.oeBuild.call(o, event);
   var h = o.hPanel;
   h.innerText = RString.nvl(o.label);
   h.noWrap = true;
   if(!o.display){
      h.style.display = 'block';
   }
   if(o.width){
      h.width = o.width;
   }
   return EEventStatus.Stop;
}
function FTreeColumn_onBuildPanel(){
   this.hPanel = RBuilder.create(null, 'TD');
}
function FTreeLevel(o){
   o = RClass.inherits(this, o, FControl);
   o.id           = RClass.register(o, new TPtyStr('id'));
   o.color        = RClass.register(o, new TPtyStr('color'));
   o.bgColor      = RClass.register(o, new TPtyStr('bgColor'));
   return o;
}
function FTreeNode(o){
   o = RClass.inherits(this, o, FContainer);
   o.type             = RClass.register(o, new TPtyStr('type'));
   o.uuid             = RClass.register(o, new TPtyStr('uuid'));
   o.isValid          = RClass.register(o, new TPtyBool('isValid'), true);
   o.icon             = RClass.register(o, new TPtyStr('icon'));
   o.tag              = RClass.register(o, new TPtyStr('tag'));
   o.note             = RClass.register(o, new TPtyStr('note'));
   o.child            = RClass.register(o, new TPtyBool('child'));
   o.checked          = RClass.register(o, new TPtyBool('checked'), false);
   o.extended         = RClass.register(o, new TPtyBool('extended'), false);
   o.stHover          = RClass.register(o, new TStyle('Hover'));
   o.stSelect         = RClass.register(o, new TStyle('Select'));
   o.stNodePanel      = RClass.register(o, new TStyle('NodePanel'));
   o.stNodeHover      = RClass.register(o, new TStyle('NodeHover'));
   o.stNodeSelect     = RClass.register(o, new TStyle('NodeSelect'));
   o.stImage          = RClass.register(o, new TStyle('Image'));
   o.stIcon           = RClass.register(o, new TStyle('Icon'));
   o.stIconDisable    = RClass.register(o, new TStyle('IconDisable'));
   o.stCell           = RClass.register(o, new TStyle('Cell'));
   o.__linked         = false;
   o.__display        = true;
   o.__delete         = false;
   o._hover           = false;
   o._extended        = false;
   o._selected        = false;
   o.tree             = null;
   o.parentNode       = null;
   o.loaded           = false;
   o.level            = 0;
   o.attributes       = null;
   o.nodes            = null;
   o.hNodePanel       = null;
   o.hImage           = null;
   o.hIcon            = null;
   o.hLabel           = null;
   o.onNodeEnter      = RClass.register(o, new HMouseEnter('onNodeEnter'), FTreeNode_onNodeEnter);
   o.onNodeLeave      = RClass.register(o, new HMouseLeave('onNodeLeave'), FTreeNode_onNodeLeave);
   o.onNodeClick      = RClass.register(o, new HClick('onNodeClick'), FTreeNode_onNodeClick);
   o.onBuildPanel     = RBuilder.onBuildTrPanel;
   o.oeBuild          = FTreeNode_oeBuild;
   o.construct        = FTreeNode_construct;
   o.hasChild         = FTreeNode_hasChild;
   o.topNode          = FTreeNode_topNode;
   o.topNodeByType    = FTreeNode_topNodeByType;
   o.get              = FTreeNode_get;
   o.set              = FTreeNode_set;
   o.check            = FTreeNode_check;
   o.setCheck         = FTreeNode_setCheck;
   o.createChild      = FTreeNode_createChild;
   o.loadConfig       = FTreeNode_loadConfig;
   o.saveConfig       = FTreeNode_saveConfig;
   o.loadNode         = FTreeNode_loadNode;
   o.show             = FTreeNode_show;
   o.hide             = FTreeNode_hide;
   o.extend           = FTreeNode_extend;
   o.select           = FTreeNode_select;
   o.setLevel         = FTreeNode_setLevel;
   o.push             = FTreeNode_push;
   o.refreshStyle     = FTreeNode_refreshStyle;
   o.reload           = FTreeNode_reload;
   o.reloadParent     = FTreeNode_reloadParent;
   o.loadQuery        = FTreeNode_loadQuery;
   o.remove           = FTreeNode_remove;
   o.removeChildren   = FTreeNode_removeChildren;
   o.click            = FTreeNode_click;
   o.isFolder         = FTreeNode_isFolder;
   o.dispose          = FTreeNode_dispose;
   o.innerDump        = FTreeNode_innerDump;
   o.extendAll        = FTreeNode_extendAll;
   o.findByName       = FTreeNode_findByName;
   o.findByUuid       = FTreeNode_findByUuid;
   o.checkChanged     = FTreeNode_checkChanged;
   o.pushChanged      = FTreeNode_pushChanged;
   o.getFullPath      = FTreeNode_getFullPath;
   return o;
}
function FTreeNode_onNodeEnter(e){
   var o = this;
   var t = o.tree;
   if(!t.focusNode || (t.focusNode && (t.focusNode != o))){
	   if(!o.isFolder()){
	      o._hover = true;
	      o.refreshStyle();
	   }
      t.lsnsEnter.process(t, o);
   }
}
function FTreeNode_onNodeLeave(e){
   var o = this;
   var t = o.tree;
   if(!t.focusNode || (t.focusNode && (t.focusNode != o))){
      o._hover = false;
      o.refreshStyle();
      t.lsnsLeave.process(t, o);
   }
}
function FTreeNode_onNodeClick(e){
   var o = this;
   var t = o.tree;
   var esn = e.hSender.tagName;
   if('INPUT' == esn){
      return;
   }
   var isImg = false;
   if('IMG' == esn){
      isImg = ('image' == e.hSender._linkType);
   }
   var isParent = false;
   var find = t.focusNode;
   while(find){
      if(find == o){
         isParent = true;
         break;
      }
      find = find.parent;
   }
   if(!isImg || (isImg && (isParent || !o.child))){
      t.selectNode(o, true);
   }
   if(!o.loaded && o.child){
      o.extend(true);
      if(!isImg){
         t.lsnsClick.process(t, o);
      }
   }else{
      if(o.child){
    	 if(o.isFolder()){
    		 o.extend(!o._extended);
    	 }else{
	         if(isImg){
	            o.extend(!o._extended);
	         }else{
	            o.extend(true);
	         }
    	 }
      }
      if((isImg && isParent) || (isImg && !o.child) || !isImg){
         t.lsnsClick.process(t, o);
      }
   }
}
function FTreeNode_oeBuild(e){
   var o = this;
   var t = o.tree;
   var r = o.base.FContainer.oeBuild.call(o, e);
   if(e.isBefore()){
      var hp = o.hPanel;
      hp.style.border = '1 solid red';
      o.attachEvent('onNodeEnter', hp, o.onNodeEnter);
      o.attachEvent('onNodeLeave', hp, o.onNodeLeave);
      o.attachEvent('onNodeClick', hp);
      var hnp = o.hNodePanel = RBuilder.appendCell(hp, o.style('NodePanel'));
      hnp.noWrap = true;
      var ni = o.child ? t.iconPlus : t.iconNode;
      var hi = o.hImage = RBuilder.appendIcon(hnp, ni, o.style('Image'), 16, 16);
      hi._linkType = 'image';
      var ni = RString.nvl(o.icon, o.type ? o.type.icon : null);
      if(ni){
         var hi = o.hIcon = RBuilder.appendIcon(hnp, ni, o.isValid ? o.style('Icon') : o.style('IconDisable'), 16, 16);
      }else{
    	 var hi = o.hIcon = RBuilder.appendIcon(hnp, t.iconEmpty, o.isValid ? o.style('Icon') : o.style('IconDisable'), 1, 1);
      }
      hi._linkType = 'icon';
      if(t.dispChecked){
         var hc = o.hCheck = RBuilder.appendCheck(hnp);
         hc.width = 13;
         hc.height = 13;
         hc.style.borderWidth = 0;
         o.setCheck(o.checked);
         t.linkEvent(o, 'onNodeCheckClick', hc);
      }
      var text = '&nbsp;' + o.label;
      if(o.tag){
         text += '&nbsp;<FONT color=blue>(' + o.tag + ')</FONT>';
      }
      if(o.note){
         text += '&nbsp;<FONT color=green>[ ' + o.note + ' ]</FONT>';
      }
      var hl = o.hLabel = RBuilder.appendText(hnp, text);
      hl.style.font = 'icon';
      var cs = t.columns;
      if(cs){
         for(var n=1; n<cs.count; n++){
            var c = cs.value(n);
            var hc = RBuilder.appendCell(hp, o.style('Cell'));
            hc.align='center';
            hc.noWrap = true;
            hc.innerText = RString.nvl(o.get(c.dataName));
            hc.style.display = c.display ? 'block' : 'none';
         }
      }
   }
   return r;
}
function FTreeNode_construct(){
   var o = this;
   o.base.FContainer.construct.call(o);
   o.attributes = new TAttributes();
}
function FTreeNode_hasChild(){
   var o = this;
   if(o.child){
      return o.nodes && o.nodes.count > 0;
   }
}
function FTreeNode_topNode(){
   var f = this;
   while(f.parentNode){
      f = f.parentNode;
   }
   return f;
}
function FTreeNode_topNodeByType(t){
   var f = this;
   while(f){
      if(f.type.type == t){
         return f;
      }
      f = f.parentNode;
   }
}
function FTreeNode_get(n){
   return this.attributes.get(n);
}
function FTreeNode_set(n, v){
   this.attributes.set(n, v);
}
function FTreeNode_check(){
   return this.hCheck.checked;
}
function FTreeNode_setCheck(v){
   this.hCheck.checked = v;
   this.checked = v;
}
function FTreeNode_createChild(x){
   var r = null;
   if(x.isName('Node') || x.isName('TreeNode')){
      r = RClass.create(FTreeNode);
      r.tree = this.tree;
   }
   return r;
}
function FTreeNode_loadConfig(x){
   var o = this;
   o.base.FContainer.loadConfig.call(o, x);
   o.type = RObject.nvl(this.tree.types.get(x.get('type')), this.tree.type);
   o.attributes.append(x.attrs);
   var attrs = x.get('attributes')
   if(attrs){
      o.attributes.unpack(attrs);
   }
}
function FTreeNode_saveConfig(x){
   var o = this;
   o.base.FContainer.saveConfig.call(o, x);
   var t = o.type;
   x.set('type', t.name);
   x.set('type_type', t.type);
   x.set('attributes', o.attributes.pack());
}
function FTreeNode_loadNode(x){
   var o = this;
   var t = o.tree;
   o.type = null;
   o.uuid = null;
   o.isValid = true;
   o.icon = null;
   o.tag = null;
   o.note = null;
   o.child = false;
   o.checked = false;
   o.extended = true;
   o.loadConfig(x);
   o.__linked = false;
   o.__display = true;
   o.__delete = false;
   o._hover = false;
   o._extended = false;
   o._selected = false;
   o.loaded = false;
   o.level = 0;
   var ni = o.child ? t.iconPlus : t.iconNode;
   o.hImage.src = RResource.iconPath(ni);
   var ni = RString.nvl(o.icon, o.type ? o.type.icon : null);
   o.hIcon.className = o.isValid ? o.style('Icon') : o.style('IconDisable');
   if(ni){
	  o.hIcon.style.width = 16;
	  o.hIcon.style.height = 16;
      o.hIcon.src = RResource.iconPath(ni);
   }else{
      o.hIcon.style.width = 1;
      o.hIcon.style.height = 1
   }
   if(!RString.isEmpty(o.attributes.get('checked'))){
	  o.checked = RBoolean.isTrue(o.attributes.get('checked'));
	  if(o.hCheck){
         o.hCheck.checked = o.checked;
	  }
   }
   var text = '&nbsp;' + o.label;
   if(o.tag){
      text += '&nbsp;<FONT color=blue>(' + o.tag + ')</FONT>';
   }
   if(o.note){
      text += '&nbsp;<FONT color=green>[ ' + o.note + ' ]</FONT>';
   }
   o.hLabel.innerHTML = text;
}
function FTreeNode_show(){
   var o = this;
   var t = o.tree;
   o.hPanel.style.display = 'block';
   var ns = o.nodes;
   if(ns && ns.count){
      var nc = ns.count;
      for(var i=0; i<nc; i++){
         var n = ns.get(i);
         if(!n.__linked){
            t.appendNode(n, o);
         }
         if(n.__display){
            n.hPanel.style.display = 'block';
            if(n._extended){
               n.show();
            }
         }
      }
   }
}
function FTreeNode_hide(){
   var o = this;
   var t = o.tree;
   if(o.hPanel){
      o.hPanel.style.display = 'none';
   }
   if(o.components){
      var count = o.components.count;
      for(var n=0; n<count; n++){
         var child = o.components.value(n);
         if(child){
            child.hide();
         }
      }
   }
}
function FTreeNode_extend(flag){
   var o = this;
   var t = o.tree;
   if(!o.loaded && o.child){
      if(t.__loading){
    	  return;
      }
      t.loadNode(o);
   }else{
      if(o.hImage && !o.hasChild()){
         o.hImage.src = RResource.iconPath(t.iconNode);
         return false;
      }
      o._extended = flag;
      if(o.child && o.hImage){
         o.hImage.src = RResource.iconPath(flag ? t.iconMinus : t.iconPlus);
      }
      if(flag){
         o.show();
      }else if(o.nodes){
         for(var n=o.nodes.count-1; n>=0; n--){
            o.nodes.get(n).hide();
         }
      }
   }
   t.resetTreeHeight()
}
function FTreeNode_select(v){
   var o = this;
   o._selected = v;
   if(v){
      o._hover = false;
   }
   o.refreshStyle();
}
function FTreeNode_setLevel(l){
   var o = this;
   var t = o.tree;
   o.level = l;
   o.hImage.style.marginLeft = t.indent * l;
}
function FTreeNode_push(c){
   var o = this;
   var t = o.tree;
   o.base.FContainer.push.call(o, c);
   if(RClass.isClass(c, FTreeNode)){
      o.child = true;
      o.loaded = true;
      var ns = o.nodes;
      if(!ns){
         ns = o.nodes = new TList();
      }
      c.tree = t;
      c.parentNode = o;
      ns.push(c);
      t.allNodes.pushUnique(c);
   }
}
function FTreeNode_refreshStyle(){
   var o = this;
   var cs = o.hPanel.cells;
   if(o._selected){
      for(var n=0; n<cs.length; n++){
         cs[n].className = o.style('NodeSelect');
      }
   }else{
      if(o._hover){
         for(var n=0; n<cs.length; n++){
            cs[n].className = o.style('NodeHover');
         }
      }else{
         for(var n=0; n<cs.length; n++){
            cs[n].className = o.style('NodePanel');
         }
      }
   }
}
function FTreeNode_reload(t){
   var o = this;
   if(t){
      o.tree.reload();
   }else{
      o.tree.reloadNode(o);
   }
}
function FTreeNode_reloadParent(){
   var o = this;
   if(o.parentNode){
      o.tree.reloadNode(o.parentNode);
   }else{
      o.tree.reload();
   }
}
function FTreeNode_loadQuery(x){
   var o = this;
   var sl = RString.nvl(x.get('label'), o.label);
   var sn = RString.nvl(x.get('note'), o.note);
   var text = '&nbsp;' + sl;
   if(!RString.isEmpty(sn)){
      text += '&nbsp;<FONT color=green>[ ' + sn + ' ]</FONT>';
   }
   o.hLabel.innerHTML = text;
   if(x.contains('visible')){
      o.__display = RBool.isTrue(x.get('visible'));
      o.setVisible(o.__display);
   }
}
function FTreeNode_remove(){
   var o = this;
   if(o.__linked){
      if(o.nodes){
         o.removeChildren();
      }
      o.tree.freeNode(o);
   }
}
function FTreeNode_removeChildren(){
   var ns = this.nodes;
   if(ns){
      for(var i=ns.count-1; i>=0; i--){
         var n = ns.get(i);
         if(n){
            n.remove();
         }
      }
      ns.release();
   }
}
function FTreeNode_click(){
   var o = this;
   var t = o.tree;
   t.selectNode(o, true);
   t.lsnsClick.process(t, o);
}
function FTreeNode_dispose(){
   var o = this;
   o.base.FContainer.dispose.call(o);
   o.hNodePanel = null;
   o.hImage = null;
   o.hIcon = null;
   o.hCheck = null;
   o.hLabel = null;
}
function FTreeNode_innerDump(s){
   var o = this;
   s.append(RClass.typeOf(o));
   s.append('[level=',  o.level);
   if(o.type){
      s.append(' type=',  o.type.name);
   }
   s.append(', icon=',  o.icon);
   s.append(', caption=', o.label);
   s.append(', child=', o.child);
   s.append(']');
}
function FTreeNode_extendAll(){
   var o = this;
   o.extend(true);
   var cs = o.components;
   if(cs){
      var c = cs.count;
      for(var n=0; n<c; n++){
         cs.values[n].extendAll();
      }
   }
}
function FTreeNode_findByName(n){
   var o = this;
   if(o.name == n){
      return o;
   }
   var cs = o.components;
   if(cs){
      var cc = cs.count;
      for(var i=0; i<cc; i++){
         var c = cs.value(i);
         if(c){
            if(c.name == n){
               return c;
            }
            if(c.components){
               var f = c.findByName(n);
               if(f){
                  return f;
               }
            }
         }
      }
   }
   return null;
}
function FTreeNode_findByUuid(u){
   var o = this;
   if(o.uuid == u){
      return o;
   }
   var cs = o.components;
   if(cs){
      for(var n=0; n<cs.count; n++){
         var c = cs.value(n);
         if(c){
            if(c.uuid == u){
               return c;
            }
            if(c.components){
               var f = c.findByUuid(u);
               if(f){
                  return f;
               }
            }
         }
      }
   }
   return null;
}
function FTreeNode_pushChanged(trd){
   var o = this;
    var d = new TNode();
    d.attrs = o.attributes;
    if(d.attrs){
         d.attrs.set('checked', RBoolean.toString(o.check()));
    }
    trd.push(d);
   if(o.components && o.components.count > 0){
      var cc = o.components.count;
      for(var n = 0; n < cc; n++){
         var c = o.components.value(n);
         if(RClass.isClass(c, FTreeNode)){
            c.pushChanged(trd);
         }
      }
   }
}
function FTreeNode_checkChanged(){
   var o = this;
   if(o.checked != o.check()){
      return true;
   }
   return false;
}
function FTreeNode_getFullPath(){
   var o = this;
   var path = '';
   if(o.label){
       path = o.label;
   }
    if(o.parent){
       var s = o.parent.getFullPath();
       if(!RString.isEmpty(s)){
           path = s + "/" + path;
       }
    }
    return path;
}
function FTreeNode_isFolder(){
	if(this.type){
       return (this.type.typeName == 'collections') ? true : false;
	}
}
function FTreeNodeType(o){
   o = RClass.inherits(this, o, FComponent);
   o.type       = RClass.register(o, new TPtyStr('type'));
   o.typeName   = RClass.register(o, new TPtyStr('typeName'));
   o.icon       = RClass.register(o, new TPtyStr('icon'));
   o.service    = RClass.register(o, new TPtyStr('service'));
   o.action     = RClass.register(o, new TPtyStr('action'));
   o.config     = RClass.register(o, new TPtyCfg('config'));
   o.get        = FTreeNodeType_get;
   o.set        = FTreeNodeType_set;
   o.innerDump  = FTreeNodeType_innerDump;
   return o;
}
function FTreeNodeType_get(n){
   var o = this;
   return o.config ? o.config.get(n) : null;
}
function FTreeNodeType_set(n, v){
   var o = this;
   if(o.config){
      o.config.set(n, v)
   }
}
function FTreeNodeType_innerDump(dump){
   var o = this;
   dump.append(RClass.typeOf(o));
   dump.append('[icon=',  o.icon);
   dump.append(', service=', o.service);
   dump.append(', action=', o.action);
   dump.append(']');
}
function FTreeView(o){
   o = RClass.inherits(this, o, FContainer);
   o.dispChecked      = RClass.register(o, new TPtyBool('dispChecked'), false);
   o.service          = RClass.register(o, new TPtyStr('service'));
   o.queryService     = RClass.register(o, new TPtyStr('queryService'));
   o.indent           = RClass.register(o, new TPtyInt('indent', 16));
   o.stNodePanel      = RClass.register(o, new TStyle('NodePanel'));
   o.stNodeForm       = RClass.register(o, new TStyle('NodeForm'));
   o.iconNode         = 'ctl.tv-node';
   o.iconPlus         = 'ctl.tv-plus';
   o.iconMinus        = 'ctl.tv-minus';
   o.__loading        = false;
   o.__loadingNode    = null;
   o.focusNode        = null;
   o.type             = null;
   o.allNodes         = null;
   o.dispNodeCount    = null;
   o.nodes            = null;
   o.freeNodes        = null;
   o.attributes       = null;
   o.types            = null;
   o.columns          = null;
   o.levels           = null;
   o.hNodePanel       = null;
   o.hNodeForm        = null;
   o.hHeadLine        = null;
   o.lsnsEnter        = new TListeners();
   o.lsnsLeave        = new TListeners();
   o.lsnsLoad         = new TListeners();
   o.lsnsLoaded       = new TListeners();
   o.lsnsClick        = new TListeners();
   o.onNodeCheckClick = RClass.register(o, new HClick('onNodeCheckClick'), FTreeView_onNodeCheckClick);
   o.onLoaded         = FTreeView_onLoaded;
   o.onQueryLoaded    = FTreeView_onQueryLoaded;
   o.onBuildPanel     = RBuilder.onBuildTablePanel;
   o.oeBuild          = FTreeView_oeBuild;
   o.construct        = FTreeView_construct;
   o.connect          = FTreeView_connect;
   o.findByName       = FTreeView_findByName;
   o.findByUuid       = FTreeView_findByUuid;
   o.selectNode       = FTreeView_selectNode;
   o.extendAuto       = FTreeView_extendAuto;
   o.extendAll        = FTreeView_extendAll;
   o.createChild      = FTreeView_createChild;
   o.createNode       = FTreeView_createNode;
   o.appendNode       = FTreeView_appendNode;
   o.loadNode         = FTreeView_loadNode;
   o.freeNode         = FTreeView_freeNode;
   o.push             = FTreeView_push;
   o.reload           = FTreeView_reload;
   o.reloadNode       = FTreeView_reloadNode;
   o.doQuery          = FTreeView_doQuery;
   o.clear            = FTreeView_clear;
   o.dispose          = FTreeView_dispose;
   o.getTreeHeight    = FTreeView_getTreeHeight;
   o.resetTreeHeight  = FTreeView_resetTreeHeight;
   o.filterNode       = FTreeView_filterNode;
   o.removeNode       = FTreeView_removeNode;
   o.clearNodes       = FTreeView_clearNodes;
   o.haveNodes        = FTreeView_haveNodes;
   o.release          = FTreeView_release;
   o.getChangedChecks = FTreeView_getChangedChecks;
   o.fetchExtendsAll  = FTreeView_fetchExtendsAll;
   o.tempAppendNodes  = FTreeView_tempAppendNodes;
   o.removeNodes      = FTreeView_removeNodes;
   o.tempAppendChild  = FTreeView_tempAppendChild;
   return o;
}
function FTreeView_onNodeCheckClick(s, e){
   var o = this;
   if(s && RClass.isClass(s, FTreeNode)){
      var f = s.check();
      var cs = s.controls;
      if(cs){
         for(var n = 0; n < cs.count; n++){
            var nd = cs.value(n);
            if(nd && RClass.isClass(nd, FTreeNode)){
               nd.setCheck(f);
            }
         }
      }
      var p = s.parentNode;
      while(p){
	      if(f){
	         p.setCheck(f);
	         p = p.parentNode;
	      }else{
	         var pcs = p.controls;
	         var pcc = pcs.count;
	         for(var n=0; n<pcc; n++){
	        	var pnd = pcs.value(n);
	            if(pnd && RClass.isClass(pnd, FTreeNode)){
	               if(pnd.check()){
	            	   return;
	               }
	            }
	         }
	         p.setCheck(false);
	         p = p.parentNode;
	      }
	   }
   }
}
function FTreeView_onLoaded(e){
   var o = this;
   var xd = e.document;
   if(xd){
      var ne = e.node;
      o.__loadingNode.hide();
      o.__loading = false;
      var xr = xd.root();
      var xns = xr.nodes;
      if(xns){
         var xnc = xns.count;
         for(var i=0; i<xnc; i++){
            var xn = xns.get(i);
            if(xn.isName('TreeNode')){
               var n = o.createNode();
               n.loadNode(xn);
               if(ne){
                  ne.push(n);
               }else{
                  o.push(n);
               }
               o.appendNode(n, ne);
            }
         }
      }
      o.lsnsLoaded.process(o, e.node);
      if(o.extendsAll){
          o.extendAll();
      }
   }
}
function FTreeView_onQueryLoaded(e){
   var o = this;
   var doc = e.document;
   if(doc){
      var tvn = doc.root().find('TreeView');
      if(tvn && tvn.nodes){
         var nc = tvn.nodes.count;
         for(var n=0; n<nc; n++){
            var nd = tvn.nodes.get(n);
            if(nd.isName('TreeNode')){
               var nm = nd.get('name');
               var fd = o.findByName(nm);
               if(fd){
                  fd.loadQuery(nd);
               }
            }
         }
      }
   }
}
function FTreeView_oeBuild(e){
   var o = this;
   var r = o.base.FContainer.oeBuild.call(o, e);
   if(e.isBefore()){
      var hc = o.hPanel.insertRow().insertCell();
      var hnp = o.hNodePanel = RBuilder.appendDiv(hc, o.style('NodePanel'));
      var hnf = o.hNodeForm = RBuilder.appendTable(hnp, o.style('NodeForm'));
      o.hHeadLine = hnf.insertRow();
      o.hNodeRows = hnf.children[0];
      var ln = o.__loadingNode = RClass.create(FTreeNode);
      ln.tree = o;
      ln.label = RContext.get('FTreeView:loading');
      ln.icon = 'ctl.tv-load';
      ln.psBuild();
      o.appendNode(ln);
      ln.hide();
   }else if(e.isAfter()){
      var ns = o.nodes;
      if(!ns.isEmpty()){
         var nc = ns.count;
         for(var i=0; i<nc; i++){
            o.appendNode(ns.get(i));
         }
      }
      o.extendAuto();
   }
   return r;
}
function FTreeView_construct(){
   var o = this;
   o.base.FContainer.construct.call(o);
   o.nodes = new TList();
   o.allNodes = new TList();
   o.freeNodes = new TList();
   o.attributes = new TAttributes();
   o.types = new TMap();
   o.columns = new TMap();
   o.levels = new TMap();
   o.type = RClass.create(FTreeNodeType);
}
function FTreeView_connect(service, attrs){
   var o = this;
   var svc = RService.parse(RString.nvl(service, this.service));
   if(!svc){
      return alert('Unknown service');
   }
   attrs = RObject.nvl(attrs, o.attributes);
   var xd = new TXmlDocument();
   var xr = xd.root();
   xr.set('action', svc.action);
   RConsole.find(FEnvConsole).build(xr);
   if(!attrs.isEmpty()){
      if(RClass.isClass(attrs, TNode)){
         xr.push(attrs);
      }if(RClass.isClass(attrs, TAttributes)){
         xr.create('Tree').attrs = attrs;
         xr.create('Attributes').attrs = attrs;
      }else{
         xr.create('Tree').value = attrs;
         xr.create('Attributes').value = attrs;
      }
   }
   var ln = o.__loadingNode;
   o.hNodeForm.moveRow(ln.hPanel.rowIndex, 0);
   ln.setLevel(0);
   ln.show();
   var e = new TEvent(o, EXmlEvent.Send, o.onLoaded);
   e.url = svc.url;
   e.document = xd;
   RConsole.find(FXmlConsole).process(e);
}
function FTreeView_findByName(n){
   var o = this;
   var ns = o.allNodes;
   var nc = ns.count;
   if(nc){
      for(var i=0; i<nc; i++){
         var fn = ns.get(i);
         if(fn.name == n){
            return fn;
         }
      }
   }
}
function FTreeView_findByUuid(u){
   var o = this;
   var ns = o.allNodes;
   var nc = ns.count;
   if(nc){
      for(var i=0; i<nc; i++){
         var fn = ns.get(i);
         if(fn.uuid == u){
            return fn;
         }
      }
   }
}
function FTreeView_selectNode(n, s){
   var o = this;
   var fn = o.focusNode;
   if(s){
      if(n){
         if(fn){
            if(fn == n){
               return;
            }
            if(n.isFolder()){
               fn.select(true);
            }else{
               fn.select(false);
            }
         }
         if(!n.isFolder()){
            n.select(true);
            o.focusNode = n;
         }
      }
   }else{
      if(n){
         n.select(false);
      }
      if(fn){
         fn.select(false);
      }
   }
}
function FTreeView_extendAuto(n){
   var o = this;
   var ns = n ? n.nodes : o.nodes;
   if(ns){
      var nc = ns.count;
      if(nc){
         for(var i=0; i<nc; i++){
            var fn = ns.get(i);
            fn.extend(fn.extended);
            if(fn.extended){
               o.extendAuto(fn);
            }
         }
      }
   }
}
function FTreeView_extendAll(n){
   var o = this;
   var ns = n ? n.nodes : o.nodes;
   if(ns){
      var nc = ns.count;
      if(nc){
         for(var i=0; i<nc; i++){
            var fn = ns.get(i);
            fn.extend(true);
            o.extendAll(fn);
         }
      }
   }
}
function FTreeView_createChild(x){
   var o = this;
   var r = null;
   if(x.isName('Column') || x.isName('TreeColumn')){
      r = RClass.create(FTreeColumn);
   }else if(x.isName('Level') || x.isName('TreeLevel')){
      r = RClass.create(FTreeLevel);
   }else if(x.isName('Type') || x.isName('TreeNodeType')){
      r = RClass.create(FTreeNodeType);
   }else if(x.isName('Node') || x.isName('TreeNode')){
      r = RClass.create(FTreeNode);
   }else{
      RMessage.fatal(o, null, 'Unknown child type (config={0})', x.xml());
   }
   r.tree = o;
   return r;
}
function FTreeView_createNode(){
   var o = this;
   var n = o.freeNodes.pop();
   if(!n){
      var n = RClass.create(FTreeNode);
      n.tree = o;
      n.psBuild();
   }
   n.hPanel.style.display = 'block';
   o.allNodes.pushUnique(n);
   return n;
}
function FTreeView_appendNode(n, p){
   var o = this;
   if(!n.__linked){
      if(p){
         var nr = p.hPanel.rowIndex;
         var ns = p.nodes;
         for(var i=ns.count-1; i>=0; i--){
            var pn = ns.get(i)
            if(pn.__linked){
               nr = pn.hPanel.rowIndex;
               break;
            }
         }
         if(n.hPanel.parentElement){
            if(n.hPanel.rowIndex > nr){
               nr++;
            }
            o.hNodeForm.moveRow(n.hPanel.rowIndex, nr);
         }else{
            o.hNodeRows.appendChild(n.hPanel);
            o.hNodeForm.moveRow(n.hPanel.rowIndex, nr+1);
         }
         n.setLevel(p.level + 1);
      }else{
         o.hNodeRows.appendChild(n.hPanel);
         n.setLevel(0);
      }
      n.__linked = true;
   }
}
function FTreeView_loadNode(node, refresh){
   var o = this;
   o.__loading = true;
   var type = null;
   var fn = node;
   while(fn){
      type = fn.type;
      if(type && type.service){
         break;
      }
      fn = fn.parentNode;
   }
   var svc = RService.parse(RString.nvl(type.service, o.service));
   if(!svc){
      return alert('Unknown service');
   }
   var fn = node;
   while(fn){
      type = fn.type;
      if(type && type.action){
         break;
      }
      fn = fn.parentNode;
   }
   var act = RString.nvl(type.action, svc.action);
   if(!act){
      return alert('Unknown action');
   }
   o.lsnsLoad.process(o, node);
   var doc = new TXmlDocument();
   var root = doc.root();
   root.set('type', type.name);
   root.set('action', act);
   root.create('Tree', o.attributes);
   root.create('Attributes', o.attributes);
   var fn = node;
   var xnode = root;
   while(fn){
      var xnode = xnode.create('Node');
      fn.saveConfig(xnode);
      fn = fn.parentNode;
   }
   node._extended = true;
   if(node.child && node.hImage){
      node.hImage.src = RResource.iconPath(o.iconMinus);
   }
   var ln = o.__loadingNode;
   var nr = node.hPanel.rowIndex;
   if(ln.hPanel.rowIndex > nr){
      nr++;
   }
   o.hNodeForm.moveRow(ln.hPanel.rowIndex, nr);
   ln.setLevel(node.level + 1);
   ln.show();
   var e = new TEvent(o, EXmlEvent.Send, o.onLoaded);
   e.node = node;
   e.url = svc.url;
   e.document = doc;
   RConsole.find(FXmlConsole).process(e);
}
function FTreeView_freeNode(n){
   var o = this;
   if(n.__linked){
      n.__linked = false;
      n.hPanel.style.display = 'none';
      o.allNodes.extract(n);
      o.freeNodes.push(n);
   }
}
function FTreeView_push(c){
   var o = this;
   o.base.FContainer.push.call(o, c);
   c.tree = o;
   if(RClass.isClass(c, FTreeColumn)){
      o.columns.set(c.name, c);
   }else if(RClass.isClass(c, FTreeLevel)){
      o.levels.set(c.id.toString(), c);
   }else if(RClass.isClass(c, FTreeNodeType)){
      o.types.set(c.typeName, c);
   }else if(RClass.isClass(c, FTreeNode)){
      o.nodes.push(c);
      o.allNodes.pushUnique(c);
   }
}
function FTreeView_reload(){
   var o = this;
   o.clear();
   o.connect();
}
function FTreeView_reloadNode(n){
   var o = this;
   n = RObject.nvl(n, o.focusNode);
   if(!n){
      return o.reload();
   }
   n.removeChildren();
   o.loadNode(n);
}
function FTreeView_doQuery(){
   var o = this;
   var svc = RService.parse(o.queryService);
   if(!svc){
      return alert('Unknown query service');
   }
   var doc = new TXmlDocument();
   var root = doc.root();
   root.set('action', svc.action);
   root.create('Attributes').attrs = o.attributes;
   var e = new TEvent(o, EXmlEvent.Send, o.onQueryLoaded);
   e.url = svc.url;
   e.document = doc;
   RConsole.find(FXmlConsole).process(e);
}
function FTreeView_clear(){
   var o = this;
   var ns = o.nodes;
   for(var i=ns.count-1; i>=0; i--){
      ns.get(i).remove();
   }
   ns.release();
   o.allNodes.release();
}
function FTreeView_dispose(){
   var o = this;
   o.base.FContainer.dispose.call(o);
   o.hNodePanel = null;
   o.hNodeForm = null;
   o.hHeadLine = null;
}
function FTreeView_getTreeHeight(){
   var o = this;
   var ns = o.allNodes;
   var c = 0;
   for(var n = 0; n<ns.count; n++){
      var cn = ns.get(n);
      if(cn.hPanel.style.display == 'block'||cn.hPanel.style.display == ''){
         c++;
      }
   }
   return c * 29;
}
function FTreeView_resetTreeHeight(){
   var o = this;
   if(o.parentObj){
	   var h = o.getTreeHeight();
	   o.parentObj.style.height = h;
   }
}
function FTreeView_filterNode(sCaption, sAttr){
   var oNode = null;
   var nCount = this.allNodes.length;
   var sNodeCaption = null;
   var sNodeAttr = null;
   if(!sCaption){
      for(var n=0; n<nCount; n++){
         oNode = this.allNodes[n];
         if(!oNode.isDelete){
            oNode.show(true);
         }
      }
   }else{
      sCaption = sCaption.toLowerCase();
      var arAttr = null;
      var nAttrCount = 0;
      if(sAttr){
         sAttr = sAttr.toLowerCase();
         arAttr = sAttr.split("|");
         nAttrCount = arAttr.length;
      }
      for(var n=0; n<nCount; n++){
         oNode = this.allNodes[n];
         if(!oNode.isDelete){
            sNodeCaption = oNode.label.toLowerCase();
            if(arAttr){
               sNodeAttr = oNode.linkAttr.toLowerCase();
               for(var s=0; s<nAttrCount; s++){
                  if(sNodeAttr.indexOf(arAttr[s]) != -1){
                     oNode.show((sNodeCaption.indexOf(sCaption) != -1));
                     break;
                  }
               }
            }else{
               oNode.show((sNodeCaption.indexOf(sCaption) != -1));
            }
         }
      }
   }
   return true;
}
function FTreeView_removeNode(oNode){
   if(oNode){
      var nodes = new Array();
      var oLoopNode = null;
      var nCount = this.allNodes.length;
      for(var n=0; n<nCount; n++){
         oLoopNode = this.allNodes[n];
         if(oLoopNode != oNode){
            nodes[nodes.length] = oLoopNode;
         }
      }
      this.allNodes = nodes;
      var oParent = oNode.parent;
      if(oParent){
         nodes = new Array();
         nCount = oParent.nodes.length;
         for(var n=0; n<nCount; n++){
            oLoopNode = oParent.nodes[n];
            if(oLoopNode != oNode){
               nodes[nodes.length] = oLoopNode;
            }
         }
         oParent.nodes = nodes;
         oNode.parent.childrenHTML.removeChild(oNode.ownerHTML);
      }
      if(oParent.nodes.length == 0){
         oParent.imageHTML.src = this.imgEmpty;
      }
      return true;
   }
   return false;
}
function FTreeView_haveNodes(){
   return this.rootNode.hasChild();
}
function FTreeView_clearNodes(node){
   if(node){
      node.removeChildren();
   }
   return true;
   var nodes = new Array();
   var oLoopNode = null;
   var nCount = this.allNodes.length;
   for(var n=0; n<nCount; n++){
      oLoopNode = this.allNodes[n];
      if(oLoopNode.parent != oNode){
         nodes[nodes.length] = oLoopNode;
      }else{
      oNode.childrenHTML.removeChild(oLoopNode.ownerHTML);
      }
   }
   oNode.imageHTML.src = this.imgEmpty ;
   this.allNodes = nodes;
   return true;
}
function FTreeView_release(){
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
function FTreeView_fetchExtendsAll(s){
   var o = this;
   if(s && RClass.isClass(s, FTreeNode)){
      fmMain.target = 'frmMain';
      fmMain.form_search.value = '';
      fmMain.form_order.value = '';
      fmMain.form_values.value = '';
      var type = node.type.typeName;
      if('table' == type || 'form' == type){
         fmMain.form_name.value = node.get('form');
         fmMain.action = top.RContext.context('/ent/apl/logic/form/InnerForm.wa?do=update');
         fmMain.submit();
      }else if('frameTree' == type){
         fmMain.action = top.RContext.context(node.get('redirect'));
         fmMain.submit();
      }
   }else{
   }
}
function FTreeView_getChangedChecks(){
   var o = this;
   var treeView = new TNode('TreeView');
   treeView.set('name', o.name);
   var rnd = RObject.nvl(o.rootNode, o);
   var cs = rnd.controls;
   for(var n = 0; n < cs.count; n++){
      var c = cs.value(n);
      c.pushChanged(treeView);
   }
   return treeView;
}
function FTreeView_tempAppendNodes(parent, config){
   parent = RObject.nvl(parent, this.workNode, this.rootNode);
   if(config && config.nodes){
      var count = config.nodes.count;
      if(count > 0){
         parent.child = true;
         parent.loaded = true;
         for(var n=0; n<count; n++){
            var nc = config.nodes.get(n);
            if(nc && (nc.isName('Node') || nc.isName('TreeNode'))){
               var tn = RClass.create(FTreeNode);
               tn.parent = parent;
               tn.tree = this;
               tn.loadConfig(nc);
               if(nc.nodes){
                  tn.icon = 'ctl.FBrowser_Folder';
               }else{
                  tn.icon = 'ctl.FBrowser_Txt';
               }
               tn.build(0);
               tn.hide();
               if(nc.nodes){
                  this.tempAppendNodes(tn, nc);
               }
               parent.push(tn);
               this.allNodes.push(tn);
            }
         }
      }
   }
   this.rootNode.extend(true);
}
function FTreeView_removeNodes(node){
   node = RObject.nvl(node, this.workNode, this.rootNode);
   if(node.hasChild()){
      node.removeChildren();
   }
   node.remove();
}
function FTreeView_tempAppendChild(child){
   var o = this;
   var hc = o.hHeadLine.insertCell();
   hc.height = '100%';
   if(RClass.isClass(child, FTreeColumn)){
      hc.appendChild(child.hPanel);
   }
}
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
