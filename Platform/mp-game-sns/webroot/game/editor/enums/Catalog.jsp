<%@ page contentType='text/html;charset=utf-8' %>
<%@ include file='/apl/public.inc' %>

<HTML>
<HEAD>
<TITLE>Welcome</TITLE>
<jg:css/>
<jg:js type='runtime.client'/>
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
	fmMain.target = 'frmMain';
	fmMain.form_name.value = node.type.get('form_name');
	fmMain.sel_type.value = type;
	if('collection' == type){
      var label = node.label;
      var find = label.indexOf(' - ');
      if(-1 != find){
         label = label.substring(find + 3);
      }
		fmMain.sel_collection.value = label;
		fmMain.sel_component.value = '';
	}else if('component' == type){
		fmMain.sel_collection.value = node.topNode().get('linker_name');
		fmMain.sel_component.value = node.uuid;
	}else{
		return alert('Unknown type ' + type);
	}
	fmMain.action = '<jh:context/>/editor/enums/Enum.wa?do=update';
	fmMain.submit();
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
      tree.service = 'search@game.editor.enum';
      tree.attributes.set('search', s);
      tree.clear();
      tree.connect();
   }
}
//----------------------------------------------------------
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
</SCRIPT>
<!-- Toolbar config ------------------------------------------>
<XML ID="xToolBar">
<ToolBar width='100%' height='100%' align='right'>
	<ToolButtonText name='btnForm' label='枚举列表' icon='sys.pst.mgr' target='frmMain' page='#/editor/enums/Enum.wa?do=list&amp;form_name=game.editor.enum.GroupList'/>
	<ToolButton name='btnRefresh' icon='tool.refresh' action='refreshNode()'/>
</ToolBar>
</XML>
<!-- TreeView config ----------------------------------------->
<jc:tree name='xTree' source='game.editor.enum'/>
<!-- Body begin ---------------------------------------------->
<jh:body style='bodyCatalog' scroll='no' onload='_onload()'>
<jh:form name='fmMain' method='post'>
<!-- Hidden -------------------------------------------------->
<jh:hidden name='form_name'/>
<jh:hidden name='sel_type'/>
<jh:hidden name='sel_collection'/>
<jh:hidden name='sel_component'/>
<!-- Catalog ------------------------------------------------->
<TABLE border='0' cellpadding='0' cellspacing='0' style='width:100%; height:100%;'>
<TR>
<TD height='24'>
<!-- Toolbar begin ------------------------------------------->
<DIV id='_id_toolbar' style='width:100%; height:100%; overflow:auto;'><DIV>
<!-- Toolbar end --------------------------------------------->
</TD></TR>
<TR>
   <TD height='24' style='padding:2;' bgcolor='#F0F0FC'>
      <TABLE width='100%' border='0' cellpadding='0' cellspacing='0'>
         <TR>
            <TD><INPUT name='search_value' style='width:100%; height:18;' onkeydown='onSearchKeyDown()'></TD>
            <TD width='4'></TD>
            <TD width='1'><INPUT type='button' style='width:80; height:18;' style='cursor:hand' onclick='onSearch()' value='搜索'></TD>
         </TR>
      </TABLE>
   </TD>
</TR>
<TR><TD>
<!-- Tree begin ---------------------------------------------->
<DIV id='_id_tree' style='width:100%; height:100%; overflow:auto'><DIV>
<!-- Tree end ------------------------------------------------>
</TD></TR>
</TABLE>
</jh:form>
</jh:body>
<!-- Body end ------------------------------------------------>
</HTML>
