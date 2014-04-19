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
      var label = node.topNode().label;
      var find = label.indexOf(' - ');
      if(-1 != find){
         label = label.substring(find + 3);
      }
		fmMain.sel_collection.value = label;
		fmMain.sel_component.value = node.uuid;
	}else{
		return alert('Unknown type ' + type);
	}
	fmMain.action = '<jh:context/>/editor/logic/Logic.wa?do=update';
	fmMain.submit();
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
	<ToolButtonText name='btnForm' label='逻辑列表' icon='sys.pst.mgr' target='frmMain' page='#/editor/logic/Logic.wa?do=list&amp;form_name=game.editor.logic.LogicList'/>
	<ToolButton name='btnRefresh' icon='tool.refresh' action='refreshNode()'/>
</ToolBar>
</XML>
<!-- TreeView config ----------------------------------------->
<jc:tree name='xTree' source='game.editor.logic'/>
<!-- Body begin ---------------------------------------------->
<jh:body style='bodyCatalog' scroll='no' onload='_onload()'>
<jh:form name='fmMain' method='post'>
<!-- Hidden -------------------------------------------------->
<jh:hidden name='form_name'/>
<jh:hidden name='sel_type'/>
<jh:hidden name='sel_collection'/>
<jh:hidden name='sel_component'/>
<!-- Catalog ------------------------------------------------->
<jc:include uri='#/inc/template/CatalogBody.tpl'/>
</jh:form>
</jh:body>
<!-- Body end ------------------------------------------------>
</HTML>
