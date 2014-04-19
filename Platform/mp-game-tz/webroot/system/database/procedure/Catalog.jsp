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
	if(type == 'package'){
		fmMain.form_name.value = 'dataset.procedure.PackageForm';
		fmMain.sel_type.value = 'package';
		fmMain.sel_Package.value = node.label;
		fmMain.action = '<jh:context/>/database/procedure/Package.wa?do=update';
		fmMain.submit();
	}else if(type == 'function'){
		fmMain.form_name.value = 'dataset.procedure.FunctionForm';
		fmMain.sel_type.value = 'function';
		fmMain.sel_Package.value = node.parent.label;
		fmMain.sel_Function.value = node.label;
		fmMain.sel_Procedure.value = node.label;
		fmMain.action = '<jh:context/>/database/procedure/Function.wa?do=update';
      fmMain.submit();
    }else if(type == 'procedure'){
		fmMain.form_name.value = 'dataset.procedure.ProcedureForm';
		fmMain.sel_type.value = 'procedure';
		fmMain.sel_Package.value = node.parent.label;
		fmMain.sel_Function.value = node.label;
		fmMain.sel_Procedure.value = node.label;
		fmMain.action = '<jh:context/>/database/procedure/Procedure.wa?do=update';
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
	<ToolButtonText name='btnForm' label='数据包列表' icon='sys.pst.mgr' target='frmMain' page='list@#/database/procedure/Package.wa?form_name=database.procedure.PackageList'/>
	<ToolButton name='btnRefresh' icon='tool.refresh' action='refreshNode()'/>
</ToolBar>
</XML>
<!-- TreeView config ----------------------------------------->
<XML ID="xTree">
<TreeView service='catalog@database.procedure'>
	<Type name='package' type_name='package' icon='db.pkg' action='listField'/>
	<Type name='function' type_name='function' icon='db.func'/>
	<Type name='procedure' type_name='procedure' icon='db.proc'/>
</TreeView>
</XML>
<!-- Body begin ---------------------------------------------->
<jh:body style='bodyCatalog' scroll='no' onload='_onload()'>
<jh:form name='fmMain' method='post'>
<!-- Hidden -------------------------------------------------->
<jh:hidden name='form_name'/>
<jh:hidden name='sel_type'/>
<jh:hidden name='sel_Package'/>
<jh:hidden name='sel_Function'/>
<jh:hidden name='sel_Procedure'/>
<!-- Catalog ------------------------------------------------->
<jc:include uri='#/inc/template/CatalogBody.tpl'/>
</jh:form>
</jh:body>
<!-- Body end ------------------------------------------------>
</HTML>
