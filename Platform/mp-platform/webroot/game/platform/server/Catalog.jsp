<%@ page contentType='text/html;charset=utf-8' %>
<%@ include file='/apl/public.inc' %>

<HTML>
<HEAD>
<TITLE>Welcome</TITLE>
<js:css/>
<js:js type='runtime.client'/>
</HEAD>
<!-- Script -------------------------------------------------->
<SCRIPT language='javascript'>
var tree = null;
var toolbar = null;
function refreshNode(){
	tree.reloadNode();
}
function onNodeClick(sender, node){
	var type = node.type.get('type');
   var typeName = node.get('type');
	fmMain.target = 'frmMain';
	fmMain.form_name.value = node.type.get('form_name');
	fmMain.sel_type.value = type;
	if('collection' == type){
		fmMain.sel_collection.value = node.label;
		fmMain.sel_component.value = '';
   	fmMain.action = '<jh:context/>/platform/server/Server.wa?do=show' + typeName;
   	fmMain.submit();
	}else if('component' == type){
		fmMain.sel_collection.value = node.topNode().label;
		fmMain.sel_component.value = node.uuid;
	}else{
		return alert('Unknown type ' + type);
	}
}
function _onloadAll(){
	MoJS.connect();
	// Toolbar
	toolbar = RControl.fromXml(xToolBar, _id_toolbar);
	// TreeView
	tree = RControl.fromXml(xTree, _id_tree);
	tree.lsnsClick.push(new TListener(tree, onNodeClick));
	tree.connect();
	// Global
	RGlobal.set('catalog.tree', tree);
}
//----------------------------------------------------------
function _onload(){
   RWindow.connect(window);
   RLoader.loadJs('mobj');
   RLoader.waitJs(new TInvoke(null, _onloadAll), 'mobj');
}
//----------------------------------------------------------
function onSearchKeyDown(){
   if(13 == event.keyCode){
      onSearch();
      event.returnValue = false;
   }
}
function onSearch(){
   var s = fmMain.search_value.value;
   if(s){
      if(s.lenth < 3){
         return alert('搜索长度必须大于2个字符');
      }
      tree.service = '';
      tree.attributes.set('search', s);
      tree.clear();
      tree.connect();
   }
}
</SCRIPT>
<!-- Toolbar config ------------------------------------------>
<SCRIPT id='xToolBar' type='application/xml'>
<ToolBar width='100%' height='100%' align='right'>
	<ToolButtonText name='btnForm' label='服务器列表' icon='sys.pst.mgr'/>
	<ToolButton name='btnRefresh' icon='tool.refresh' action='refreshNode()'/>
</ToolBar>
</SCRIPT>
<!-- TreeView config目录定义 输出 ---------------------------->
<jc:tree name='xTree' source='system.platform.server'/>
<!-- Body begin ---------------------------------------------->
<jh:body style='bodyCatalog' onload='_onload()' scroll='no'>
<jh:form name='fmMain' method='post'>
<!-- Hidden -------------------------------------------------->
<jh:hidden name='form_name'/>
<jh:hidden name='sel_type'/>
<jh:hidden name='sel_collection'/>
<jh:hidden name='sel_component'/>

<!-- Catalog ------------------------------------------------->
<TABLE width='100%' height='100%' border='0' cellpadding='0' cellspacing='0'>
<TR><TD height='24'>
<!-- Toolbar begin ------------------------------------------->
<DIV id='_id_toolbar' style='width:100%; height:100%;'></DIV>
<!-- Toolbar end --------------------------------------------->
</TD></TR>
<!-- Toolbar end --------------------------------------------->
<TR>
  <TD>
     <TABLE width='100%' border='0' cellpadding='0' cellspacing='0'>
         <TR>
            <TD><INPUT name='search_value' style='width:100%; height:18;' onkeydown='onSearchKeyDown()'></TD>
            <TD width='4'></TD>
            <TD width='1'><INPUT type='button' style='width:80; height:18;' style='cursor:hand' onclick='onSearch()' value='搜索'></TD>
         </TR>
      </TABLE>
  <TD>
</TR>
<TR><TD>
<!-- Tree begin ---------------------------------------------->
<DIV id='_id_tree' style='width:100%; height:100%;'></DIV>
<!-- Tree end ------------------------------------------------>
</TD></TR>
</TABLE>
</jh:form>
</jh:body>
<!-- Body end ------------------------------------------------>
</HTML>
