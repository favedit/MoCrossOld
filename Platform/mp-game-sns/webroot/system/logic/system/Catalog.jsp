<%@ include file='/apl/public.inc' %>

<HTML>
<HEAD>
<TITLE>Welcome</TITLE>
<jh:css />
<<js:js type='runtime.client'/>
</HEAD>
<!------------------------------------------------------------>
<STYLE>
</STYLE>
<SCRIPT language='javascript'>
var tree = null;
var toolbar = null;
function refreshNode(){
	tree.reloadNode();
}
function onNodeClick(tree, node){
   var type = node.type.name;
   if(type == 'form.form'){
		fmMain.form_name.value = 'system.form.frmForm';
      fmMain.action = '<jh:context/>/logic/webform/WebForm.wa?do=update';
      fmMain.target = 'frmBody';
      fmMain.submit();
   }else if(type == 'table'){
		fmMain.form_name.value = 'logic.basic.tblCountry';
      fmMain.action = '<jh:context/>/logic/webform/WebForm.wa?do=show';
		fmMain.target = 'frmMain';
      fmMain.submit();
   }
}
function _onloadAll(){
	MoJS.connect();
	top.fstBody.cols = '200,*'
	// Toolbar
	toolbar = RControl.fromXml(xToolBar, _id_toolbar);
	// TreeView
	tree = RControl.fromXml(xTree, _id_tree);
	tree.lsnsClick.push(new TListener(null, onNodeClick));
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
	<ToolButtonText name='btnBasic' caption='Basic Logic' icon='sys.pst.mgr' target='frmMain' page='#/system/persistence/Persistence.wa' method='list'/>
	<ToolButton name='btnRefresh' icon='tool.refresh' action='refreshNode()'/>
</ToolBar>
</XML>
<!-- TreeView config ----------------------------------------->
<XML ID="xTree">
   <TreeView>
   	<Type name='table' icon='db.tab'/>
   	<Type name='floder' icon='sys.floder'/>
		<Node type='table' caption='Country' form='logic.base.frmCountry'/>
		<Node type='table' caption='Country - List' form='logic.base.tblCountry'/>
		<Node type='table' caption='Area'/>
		<Node type='table' caption='Province'/>
		<Node type='table' caption='Occupation'/>
		<Node type='floder' caption='Mobil'>
			<Node type='table' caption='Factory'/>
			<Node type='table' caption='Vendor'/>
			<Node type='table' caption='Mobil'/>
		</Node>
   </TreeView>
</XML>
<!-- Body begin ---------------------------------------------->
<jh:body style='bodyCatalog' scroll='no' onload='_onload()'>
<jh:form name='fmMain' method='post'>
<!-- Hidden -------------------------------------------------->
<jh:hidden name='form_name'/>
<!-- Catalog ------------------------------------------------->
<jc:include uri='#/inc/template/CatalogBody.tpl'/>
</jh:form>
</jh:body>
<!-- Body end ------------------------------------------------>
</HTML>
