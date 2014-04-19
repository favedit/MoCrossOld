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
   h.style.border = '0px';
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
   var hr = hf.insertRow(-1);
   o.onBuildChange(hr.insertCell(-1))
   var hc = hr.insertCell(-1);
   var se = o.style('Edit')
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
