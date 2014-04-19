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
function onNodeEnter(tree, node){
   _id_item.innerText = node.get('oname');
   _id_item.style.pixelTop = window.event.y + node.hCell.style.pixelHeight + 2;
   //_id_item.style.display = 'block';
}
function onNodeLeave(tree, node){
   //_id_item.style.display = 'none';
}
function _onloadAll(){
	// Global
	RGlobal.set('catalog.tree', tree);
	MoJS.connect();
   // TreeView
   tree = RControl.fromXml(xTree, _id_tree);
   //tree.lsnsClick.push(new TListener(tree, onNodeClick));
   tree.connect();
}
//----------------------------------------------------------
function _onload(){
   RWindow.connect(window);
   RLoader.loadJs('mobj');
   RLoader.waitJs(new TInvoke(null, _onloadAll), 'mobj');
}
</SCRIPT>
<!------------------------------------------------------------>
<XML ID="xTree">
<TreeView service='system.monitor.objects'>
   <Column name='node' label='Object info' display='Y' width='200px' />
   <Column name='value' label='Value' display='Y' width='160px' />
   <Column name='detail' label='Detail' display='Y'/>
   <Column name='oid' label='Object ID' display='N'/>
   <Column name='oname' label='Object Name' display='N'/>
   
   <Type type_name='annotation'       icon='class.annotation'       action='process'/>
   <Type type_name='field'            icon='class.field'            action='process'/>
   <Type type_name='field-protected'  icon='class.field-protected'  action='process'/>
   <Type type_name='field-private'    icon='class.field-private'    action='process'/>
   <Type type_name='method'           icon='class.method'           action='process'/>
   <Type type_name='method-protected' icon='class.method-protected' action='process'/>
   <Type type_name='method-private'   icon='class.method-private'   action='process'/>
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
