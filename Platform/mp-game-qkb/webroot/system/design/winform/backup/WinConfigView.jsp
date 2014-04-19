<%@ include file="/inc/page/begin.inc" %>

<HTML>
<!-- Html Header Initialize ------------------------------->
<HEAD>
<js:meta/>
<js:css/>
<js:js/>
</HEAD>

<SCRIPT language='javascript'>
function frame(sFrame){
   return eval('parent.' + sFrame);
}
function updateControl(){
   var oCatalog = frame('frmWinCtlLst').oCatalog;
   oCatalog.defineNode.setAttribute('width', oWindow.width);
   oCatalog.defineNode.setAttribute('height', oWindow.height);
   WindowManager.saveDefine(oCatalog.defineNode);
}
function deleteControl(){
   var METHOD = 'deleteControl';
   var oCatalog = frame('frmWinCtlLst').oCatalog;
   if(!oCatalog.focusNode){
      return ILogger.error(this, METHOD, 'Control tree view not focus node.\nPlease select control on treeview.');
   }
   oCatalog.removeNode();
}
function insertControl(sType, sPreFix){
   var METHOD = 'insertControl';
   var oCatalog = frame('frmWinCtlLst').oCatalog;
   if(!oCatalog.focusNode){
      return ILogger.error(this, METHOD, 'Control tree view not focus node.\nPlease select control on treeview.');
   }
   var sText = window.prompt('Please enter new control name.', sPreFix);
   if(!sText){return;}
   if(!IString.startWiths(sText, sPreFix)){
      return ILogger.error(this, METHOD, 'Do not change control pre fix.');
   }
   var oNode = oCatalog.focusNode.config.createNode('Control')
   oNode.setAttribute('name', sText);
   oNode.setAttribute('type', sType);
   oCatalog.build(oCatalog.focusNode, oNode);
}

var oWindow = null;
function initWindow(){
   SystemManager.runMode = false;
   createWindow();
}
function createWindow(){
   oWindow = WindowManager.createWindow(window);
   oWindow.defineNode = parent.oWindowNode;
   oWindow.onDesignDblClick = function(oCtl){
      var oTvNode = frame('frmWinCtlLst').oCatalog.findNode(oCtl.config);
      if(oTvNode){
         frame('frmWinCtlLst').oCatalog.clickNode(oTvNode);
      }
   }
   oWindow.onDesignStopDrop = function(oSource){
      frame('frmWinCtlPty').oPtyCtl.refresh();
   }
   oWindow.show();
   oWindow.processResize();
   oWindow.focus();
}
function insertControls(){
   var oCatalog = top.frmWork.frmWinCtlLst.oCatalog;
   var oTvNode = oCatalog.focusNode;
   if(!oTvNode){
      return alert('No select tree node!');
   }
   var sType = oTvNode.config.attribute('type');
   if(!(sType == 'form' || sType == 'table')){
      return alert('Tree node type is wrong.');
   }
   var sDataset = oTvNode.config.attribute('dataset');
   if(!sDataset){
      return alert('No dataset!');
   }
   var oDsNode = oCatalog.findNodeByName(sDataset);
   if(!oDsNode){
      return alert('Unknown dataset!');
   }
   var sDsName = oDsNode.config.attribute('dataset_name');
   if(!sDsName){
      return alert('No dataset!');
   }
   var sUri = SystemManager.contextPath + '/sys/ctl/win/AddControls.wa?type=' + sType + '&dataset=' + sDsName;
   var oDlgPms = new Array();
   oDlgPms['parent'] = this;
   oDlgPms['dataset'] = sDsName;
   window.showModalDialog(sUri, oDlgPms, 'dialogWidth:600px;dialogHeight:480px;resizable:no;scroll:no;edge:sunken');
}
function insertDbControl(sDsName, sType, sName, sFieldName){
   var oCatalog = top.frmWork.frmWinCtlLst.oCatalog;
   var oDsNode = DatasetManager.loadDefine(sDsName);
   if(oDsNode){
      var oFldNode = oDsNode.findNode('Field', 'name', sFieldName);
      if(oFldNode){
         var oNode = oCatalog.focusNode.config.createNode('Control')
         oNode.attributes = IArray.copy(oFldNode.attributes);
         oNode.setAttribute('name', sName);
         oNode.setAttribute('type', sType);
         oCatalog.build(oCatalog.focusNode, oNode);
      }
   }
}
function setDefault(){
   var oCatalog = top.frmWork.frmWinCtlLst.oCatalog;
   var oTvNode = oCatalog.focusNode;
   if(!oTvNode){
      return alert('No select tree node!');
   }
   var sDataName = oTvNode.config.attribute('data_name');
   if(!sDataName){
      return alert('No data name!');
   }
   // Find dataset node
   var oDsNode = oTvNode;
   while(oDsNode){
      var sDataset = oDsNode.config.attribute('dataset');
      if(sDataset){
         break;
      }
      oDsNode = oTvNode.parent;
   }
   if(!sDataset){
      return alert('No dataset node!');
   }
   // Find dataset control
   var oDsNode = oCatalog.findNodeByName(sDataset);
   if(!oDsNode){
      return alert('Unknown dataset!');
   }
   var sDsName = oDsNode.config.attribute('dataset_name');
   if(!sDsName){
      return alert('No dataset!');
   }
   var oDsNode = DatasetManager.loadDefine(sDsName);
   if(!oDsNode){
      return alert('No server dataset!');
   }
   var oFldNode = oDsNode.findNode('Field', 'name', sDataName);
   if(!oFldNode){
      return alert('No field node in dataset!');
   }
   var oCfgNode = oTvNode.config;
   var sName = oCfgNode.attribute('name');
   var sType = oCfgNode.attribute('type');
   IArray.append(oCfgNode.attributes, oFldNode.attributes, true);
   oCfgNode.setAttribute('name', sName);
   oCfgNode.setAttribute('type', sType);
   // Refresh Node
   oCatalog.clickNode(oTvNode, true);
}
function refreshWindow(){
   if(oWindow){
      oWindow.release();
   }
   createWindow();
}
</SCRIPT>

<jc:toolbar name='top.frames.frmToolbar.oToolbar' inFrame='frmWork' inForm='frmConsole' target='frmWork' action='refresh'/>

<!-- Body Initialize -------------------------------------->
<js:body scroll='auto' onload='initWindow();' styleClass='frmWork'>
<TABLE width='100%' height='100%' border='0' cellpadding='0' cellspacing='0'>
<TR><TD></TD></TR>
<TR><TD height='40px'>
   <DIV style='margin-left:6px;position:absolute;z-index:30000'>
      <jh:img styleClass='comImageButton' src='/res/img/sys/save.gif' align='absmiddle' border='0' onclick='updateControl()' title='trs:sys|button.control.action.save|Save\n- Save the window config to server.'/>
      <jh:img styleClass='comImageButton' src='/res/img/sys/refresh.gif' align='absmiddle' border='0' onclick='refreshWindow()' title='trs:sys|button.control.action.refresh|Refresh\n- Refresh window.'/>
      <jh:img styleClass='comImageButton' src='/res/img/sys/delete.gif' align='absmiddle' border='0' onclick='deleteControl()' title='trs:sys|button.control.action.delete|Delete\n- Delete current selected node.'/>
      <jh:img styleClass='comImageButton' src='/res/img/sys/tvni.gif' align='absmiddle' border='0' onclick='insertControls()' title='trs:sys|button.control.action.insert|Insert\n- Insert new control to current tree node.'/>
      <jh:img styleClass='comImageButton' src='/res/img/sys/search.gif' align='absmiddle' border='0' onclick='setDefault()' title='trs:sys|button.control.action.default|Default\n- Auto set control attribute from dataset config.'/>
      <jh:img styleClass='comImageButton' src='/res/img/sys/block.gif' align='absmiddle' border='0'/>

      <jh:img styleClass='comImageButton' src='/res/img/ctl/dataset.gif' align='absmiddle' border='0' onclick='insertControl("dataset", "ds")' title='trs:sys|button.control.dataset|Dataset Control'/>
      <jh:img styleClass='comImageButton' src='/res/img/ctl/action.gif' align='absmiddle' border='0' onclick='insertControl("action", "act")' title='trs:sys|button.control.action|Dataset Action Control'/>
      <jh:img styleClass='comImageButton' src='/res/img/sys/block.gif' align='absmiddle' border='0'/>

      <jh:img styleClass='comImageButton' src='/res/img/ctl/form.gif' align='absmiddle' border='0' onclick='insertControl("form", "frm")' title='trs:sys|button.control.form|Form Control'/>
      <jh:img styleClass='comImageButton' src='/res/img/ctl/table.gif' align='absmiddle' border='0' onclick='insertControl("table", "tbl")' title='trs:sys|button.control.tab|Table Control'/>
      <jh:img styleClass='comImageButton' src='/res/img/ctl/column.gif' align='absmiddle' border='0' onclick='insertControl("column", "col")' title='trs:sys|button.control.tabcol|Table Column Control'/>
      <jh:img styleClass='comImageButton' src='/res/img/sys/block.gif' align='absmiddle' border='0'/>

      <jh:img styleClass='comImageButton' src='/res/img/ctl/tabcontrol.gif' align='absmiddle' border='0' onclick='insertControl("tabcontrol", "tbc")' title='trs:sys|button.control.tabpage|Tab Page Control'/>
      <jh:img styleClass='comImageButton' src='/res/img/ctl/tabsheet.gif' align='absmiddle' border='0' onclick='insertControl("tabsheet", "tbs")' title='trs:sys|button.control.tabsheet|Tab Sheet Control'/>
      <jh:img styleClass='comImageButton' src='/res/img/sys/block.gif' align='absmiddle' border='0'/>

      <jh:img styleClass='comImageButton' src='/res/img/ctl/selcombobox.gif' align='absmiddle' border='0' onclick='insertControl("selcombobox", "scb")' title='trs:sys|button.control.selcombo|Select ComboBox Control'/>
      <jh:img styleClass='comImageButton' src='/res/img/ctl/label.gif' align='absmiddle' border='0' onclick='insertControl("label", "lbl")' title='trs:sys|button.control.label|Label Control'/>
      <jh:img styleClass='comImageButton' src='/res/img/ctl/checkbox.gif' align='absmiddle' border='0' onclick='insertControl("checkbox", "chk")' title='trs:sys|button.control.checkbox|CheckBox Control'/>
      <jh:img styleClass='comImageButton' src='/res/img/ctl/edit.gif' align='absmiddle' border='0' onclick='insertControl("edit", "edt")' title='trs:sys|button.control.edit|Edit Control'/>
      <jh:img styleClass='comImageButton' src='/res/img/ctl/datepicker.gif' align='absmiddle' border='0' onclick='insertControl("datepicker", "dtp")' title='trs:sys|button.control.datepicker|Date Picker Control'/>
      <jh:img styleClass='comImageButton' src='/res/img/ctl/radiobutton.gif' align='absmiddle' border='0' onclick='insertControl("radiobutton", "rdo")' title='trs:sys|button.control.radio|RadioBox Control'/>
      <jh:img styleClass='comImageButton' src='/res/img/ctl/combobox.gif' align='absmiddle' border='0' onclick='insertControl("combobox", "cbo")' title='trs:sys|button.control.combo|ComboBox Control'/>
      <jh:img styleClass='comImageButton' src='/res/img/ctl/memo.gif' align='absmiddle' border='0' onclick='insertControl("memo", "memo")' title='trs:sys|button.control.memo|Memo Control'/>
      <jh:img styleClass='comImageButton' src='/res/img/ctl/button.gif' align='absmiddle' border='0' onclick='insertControl("button", "btn")' title='trs:sys|button.control.button|Button Control'/>
   <DIV>
</TD></TR>
<TABLE>
</js:body>
</HTML>

<%@ include file="/inc/page/end.inc" %>
