<%@ page contentType='text/html;charset=utf-8' %>
<%@ include file='/apl/public.inc' %>

<HTML>
<HEAD>
<TITLE>Welcome</TITLE>
<js:css/>
<js:js type='runtime.client'/>
</HEAD>
<!-- Script  lefttree -------------------------------------------------->
<SCRIPT language='javascript'>
var tree = null;
var toolbar = null;
function refreshNode(){
	tree.reloadNode();
}
function onNodeClick(sender, node){
	var type = node.type.get('type');
   //alert(type);
   var typeName = node.get('type');
	fmMain.target = 'frmMain';
   // typeName = server;
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
	// Toolbar将xToolBar模板加载至_id_toolbar的div上面。
	toolbar = RControl.fromXml(xToolBar, _id_toolbar);
	// TreeView xTree1定义tree,之后又加载到Id_tree1的div上面。
	tree = RControl.fromXml(xTree1, _id_tree1);
   //给tree的节点上加单击事件
	tree.lsnsClick.push(new TListener(tree, onNodeClick));
   //执行连接
	tree.connect();
	// Global，删了树也可以出来，不知道是什么意思。
	RGlobal.set('LeftTree.tree', tree);
}
//----------------------------------------------------------
function _onload(){
   RWindow.connect(window);
   RLoader.loadJs('mobj');
   //获取tree信息，如何获取应该在mobj.js文件中
   RLoader.waitJs(new TInvoke(null, _onloadAll), 'mobj');
}
</SCRIPT>
<!-- Toolbar config ------------------------------------------>
<SCRIPT id='xToolBar' type='application/xml'>
<ToolBar width='100%' height='100%' align='right'>
	<ToolButtonText name='btnForm' label='服务器列表' icon='sys.pst.mgr'/>
	<ToolButton name='btnRefresh' icon='tool.refresh' action='refreshNode()'/>
</ToolBar>
</SCRIPT>
<!-- TreeView config目录定义，从程序中加载-------------------->
<jc:tree name='xTree1' source='system.platform.server'/>
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
<DIV id='_id_toolbar' style='width:100%; height:100%;'>
</DIV>
<!-- Toolbar end --------------------------------------------->
</TD></TR>
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
<TR>
<TD>

<!-- Tree begin ---------------------------------------------->

<DIV id='_id_tree1' style='width:100%; height:100%;'>

</DIV>
<!-- Tree end ------------------------------------------------>
</TD></TR>
</TABLE>
</jh:form>
</jh:body>
<!-- Body end ------------------------------------------------>
</HTML>
