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
function onNodeClick(tree, node){
	fmMain.target = 'frmMain';
	var type = node.type.name;
	if(type == 'table'){
		fmMain.form_name.value = 'dataset.table.Table';
		fmMain.sel_type.value = 'dataset';
		fmMain.sel_table.value = node.label;
		fmMain.action = '<jh:context/>/database/table/Table.wa?do=update';
		fmMain.submit();
	}else if(type == 'field'){
		//可以再建一个form用来单独显示field的内容
		fmMain.form_name.value = 'dataset.table.Table';
		fmMain.sel_type.value = 'field';
		fmMain.sel_table.value = node.parent.label;
		fmMain.sel_field.value = node.label;
		fmMain.action = '<jh:context/>/database/table/Field.wa?do=update';
      fmMain.submit();
   }
	return;
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

</SCRIPT>
<!-- Toolbar config ------------------------------------------>
<XML ID="xToolBar">
<ToolBar width='100%' height='100%' align='right'>
	<ToolButtonText name='btnForm' label='数据表列表' icon='sys.pst.mgr' target='frmMain' page='#/database/dataset/Dataset.wa'  method='list'/>
	<ToolButton name='btnRefresh' icon='tool.refresh' action='refreshNode()'/>
</ToolBar>
</XML>
<!-- TreeView config ----------------------------------------->
<XML ID="xTree">
<TreeView service='catalog@database.table'>
	<Type name='table' type_name='table' icon='db.tab' action='listField'/>
	<Type name='field' type_name='field' icon='db.col'/>
</TreeView>
</XML>
<!-- Body begin ---------------------------------------------->
<jh:body style='bodyCatalog' scroll='no' onload='_onload()'>
<jh:form name='fmMain' method='post'>
<!-- Hidden -------------------------------------------------->
<jh:hidden name='form_name'/>
<jh:hidden name='sel_type'/>
<jh:hidden name='sel_table'/>
<jh:hidden name='sel_field'/>
<!-- Catalog ------------------------------------------------->
<jc:include uri='#/inc/template/CatalogBody.tpl'/>
</jh:form>
</jh:body>
<!-- Body end ------------------------------------------------>
</HTML>
