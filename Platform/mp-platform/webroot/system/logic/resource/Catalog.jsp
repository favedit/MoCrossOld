<%@ page contentType='text/html;charset=utf-8' %>
<%@ include file='/apl/public.inc' %>

<HTML>
<HEAD>
<TITLE>Welcome</TITLE>
<jh:css />
<jh:js/>
<style type="text/css">
<!--
.STYLE1 {
	cursor:hand;
	color: #FFFFFF;
	font-weight: bold;
}
-->
</style>
</HEAD>
<!-- Script -------------------------------------------------->
<SCRIPT language='javascript'>
var tree = null;
var toolbar = null;
function onViewChange(o){
	fmMain.target = 'frmCatalog';
	fmMain.action = '';
	fmMain.submit();
}
function goViewList(){
	fmMain.action = "<jh:context path='#/apl/form/WebForm.wa?do=show&hs=top'/>";
	fmMain.form_name.value = 'logic.resource.catalog.ViewList';
	fmMain.form_parent.value = '';
	fmMain.target = 'frmMain';
	fmMain.submit();
}
function goCatalogList(){
	var attrs = new TAttrs();
	//attrs.set('VIEW_ID', fmMain.<jh:name item='&page.viewId'/>.value); 
	attrs.set('PARENT_ID', 0); 
	fmMain.form_name.value = 'logic.resource.catalog.CatalogList';
	fmMain.form_parent.value = attrs.pack();
	fmMain.action = "<jh:context path='#/apl/form/WebForm.wa?do=show&hs=top'/>";
	fmMain.target = 'frmMain';
	fmMain.submit();
}
function goModuleList(){
	fmMain.form_name.value = 'logic.system.module.ModuleList';
	fmMain.form_parent.value = '';
	fmMain.action = "<jh:context path='#/apl/form/WebForm.wa?do=show&hs=top'/>";
	fmMain.target = 'frmMain';
	fmMain.submit();
}
function refreshTree(){
   tree.reload();
}
function refreshNode(){
   tree.reloadNode();
}
function onNodeClick(sender, node){
	var type = node.type.typeName;
	var cmd = null;
	var formName = node.get('form_name');
	fmMain.target = 'frmMain';
	fmMain.form_name.value = formName;
	if('view' == type){
		fmMain.form_parent.value = '';
		cmd = '?do=show&hs=top';
	}else if('catalog' == type){
		var attrs = new TAttrs();
		//attrs.set('viewId', fmMain.<jh:name item='&page.viewId'/>.value); 
		attrs.set('parentId', 0); 
		fmMain.form_parent.value = attrs.pack();
		cmd = '?do=show&hs=top';
	}else if('catalogType' == type){
		var attrs = new TAttrs();
		//attrs.set('VIEW_ID', fmMain.<jh:name item='&page.viewId'/>.value); 
		fmMain.form_parent.value = attrs.pack();
	}else if('unitCtg' == type){
		var attrs = new TAttrs();
		//attrs.set('VIEW_ID', fmMain.<jh:name item='&page.viewId'/>.value); 
		fmMain.form_parent.value = attrs.pack();
		cmd = '?do=show&hs=top';
	}else if('unitCtgType' == type){
		var attrs = new TAttrs();
		//attrs.set('VIEW_ID', fmMain.<jh:name item='&page.viewId'/>.value); 
		fmMain.form_parent.value = attrs.pack();
		cmd = '?do=show&hs=top';
	}else if('resource' == type){
		var attrs = new TAttrs();
		//attrs.set('VIEW_ID', fmMain.<jh:name item='&page.viewId'/>.value); 
		fmMain.form_parent.value = attrs.pack();
		cmd = '?do=show&hs=top';
	}else if('unit' == type){
		var attrs = new TAttrs();
		//attrs.set('VIEW_ID', fmMain.<jh:name item='&page.viewId'/>.value); 
		fmMain.form_parent.value = attrs.pack();
		cmd = '?do=show&hs=top';
	}else if('resourceType' == type){
		var attrs = new TAttrs();
		//attrs.set('VIEW_ID', fmMain.<jh:name item='&page.viewId'/>.value); 
		fmMain.form_parent.value = attrs.pack();
		cmd = '?do=show&hs=top';
	}else if('unitType' == type){
		var attrs = new TAttrs();
		//attrs.set('VIEW_ID', fmMain.<jh:name item='&page.viewId'/>.value); 
		fmMain.form_parent.value = attrs.pack();
		cmd = '?do=show&hs=top';
	}else{
		var attrs = new TAttrs();
		attrs.set('ouid', node.get('ouid')); 
		attrs.set('over', node.get('over')); 
		fmMain.form_name.value = 'logic.resource.catalog.CatalogForm';
		fmMain.form_search.value = attrs.pack();
		cmd = '?do=update&hs=top';
	}
	if(cmd){
		fmMain.action = "<jh:context path='#/logic/webform/WebForm.wa'/>" + cmd;;
		fmMain.submit();
	}
}
function goPage(formName){
	fmMain.form_name.value = formName;
	fmMain.form_parent.value = '';
	//fmMain.action = "<jh:context path='#/apl/form/WebForm.wa?do=show&hs=top'/>";
	fmMain.target = 'frmMain';
	fmMain.submit();
}
function _onload(){
	top.fstBody.cols = '340,*'
	tree = RControl.fromXml(xTree, _id_tree);
	//tree.attributes.set('view_id', fmMain.<jh:name item='&page.viewId'/>.value);
	tree.lsnsClick.push(new TListener(tree, onNodeClick));
	//tree.connect();
}
</SCRIPT>
<!-- TreeView config ----------------------------------------->
   <jc:tree name='xTree' source='enterprise.develop.resource.catalog'/>
<!-- Body begin ---------------------------------------------->
<jh:body style='bodyCatalog' scroll='no' onload='_onload()'>
<jh:form name='fmMain' method='post'>
<!-- Hidden -------------------------------------------------->
<jh:hidden name='form_name'/>
<jh:hidden name='form_parent'/>
<jh:hidden name='form_search'/>
<jh:hidden name='form_order'/>
<jh:hidden name='form_value'/>
<!-- Catalog ------------------------------------------------->
<table width="100%" height='100%'border="0" cellspacing="0" cellpadding="4">
  <tr>
    <td height="1"><table width="100%" border="0" cellpadding="2" cellspacing="0">
      <tr>
        <td bgcolor="29bad7" style='padding-left:8px'>
			<A herf='#' class="STYLE1" onclick='goViewList()'>目录视角：</A>
			<jh:select item='&page.viewId' onchange='onViewChange(this)' style='font-size:9pt;' enum='logic.resource.catalog.ViewList'/>
		</td>
        <td width="30" bgcolor="55cfe6">&nbsp;</td>
        <td width="12" align="center" valign="middle" bgcolor="a4eaea" style='cursor:hand' onclick='goCatalogList()'>
			<img src="../../../ars/pic/search.gif" width="5" height="9">
		</td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td height="1">
	<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="29bad5">
		<tr>
        <td bgcolor="#FFFFFF">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
		        <td>
					<input name='Put' style='border:0'>
				</td>
		        <td width='100' align="right" valign='bottom'>
					<img src="../../../ars/pic/search.jpg" width="31" height="18" style='cursor:hand' onclick='goModuleList()'>
				</td>
		        <td align="right" valign='bottom'>
					<img src="../../../ars/icon/sys/tree.gif" style='cursor:hand' onclick='refreshTree()'>
					<img src="../../../ars/icon/tool/refresh.gif" style='cursor:hand' onclick='refreshNode()'>
				</td>
		      </tr>
		    </table>
		</td>
      	</tr>
    </table>
	</td>
  </tr>
  <tr>
    <td valign="top"><DIV id='_id_tree' style='width:100%; height:100%; overflow:auto'><DIV></td>
  </tr>
</table>
</jh:form>
</jh:body>
<!-- Body end ------------------------------------------------>
</HTML>
