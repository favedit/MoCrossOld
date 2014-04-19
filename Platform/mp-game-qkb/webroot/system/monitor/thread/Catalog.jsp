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
	fmMain.target = 'frmMain';
	var type = node.type.typeName;
	var isField = RBool.isTrue(node.type.get('is_field'));
	if('config' == type){
		fmMain.sel_type.value = 'config';
		fmMain.sel_collection.value = node.label;
		fmMain.sel_component.value = '';
		fmMain.action = '<jh:context/>/system/config/Config.wa?do=update';
		fmMain.submit();
	}else if('component' == type){
        fmMain.sel_type.value = 'component';
        fmMain.sel_collection.value = node.topNode().label;
   		fmMain.sel_component.value = node.uuid;
        fmMain.action = '<jh:context/>/system/config/Component.wa?do=update';
        fmMain.submit();
	}else if(isField){
		fmMain.sel_type.value = 'component';
		fmMain.sel_collection.value = node.parent.label;
		fmMain.sel_component.value = node.uuid;
		fmMain.action = '<jh:context/>/system/config/Property.wa?do=update';
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
	<ToolButtonText name='btnForm' caption='配置列表' icon='sys.pst.mgr' target='frmMain' page='#/system/config/Config.wa?do=list'/>
	<ToolButton name='btnRefresh' icon='tool.refresh' action='refreshNode()'/>
</ToolBar>
</XML>
<!-- TreeView config ----------------------------------------->
<XML ID="xTree">
<TreeView service='catalog@monitor.thread'>
	<Column name='tcName' label='名称' data_name='name' icon='ctl.ds-dataset' />
	<Column name='tcLabel' label='内容' data_name='description' icon='ctl.ds-dataset'/>
	<Level id='1' color='black'/>
	<Level id='2' color='green'/>
	<Type type_name='config' icon='#sys-cfg.cfg' action='listComponent'/>
    <Type type_name='component' icon='#sys-cfg.com' action='listComponent'/>
    <Type type_name='property' icon='ctl.ds-datasetd' is_field='Y'/>
</TreeView>
</XML>
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
<DIV id='_id_toolbar' style='width:100%; height:100%;'><DIV>
<!-- Toolbar end --------------------------------------------->
</TD></TR>
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
