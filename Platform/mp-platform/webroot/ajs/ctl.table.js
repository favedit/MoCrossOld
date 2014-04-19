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
         var he = r.hEdit = RBuilder.append(hep, '<INPUT type=radio name=' + c.dataName.toLowerCase() + '_' + RHtml.uid(hel) + " style='border:0;cursor:hand'>");
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
      RHtml.tableMoveRow(o.hColumnForm, r.hFixPanel.rowIndex, i + 2);
   }
   o.hRows.appendChild(r.hPanel);
   RHtml.tableMoveRow(o.hDataForm, r.hPanel.rowIndex, i + 2);
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
