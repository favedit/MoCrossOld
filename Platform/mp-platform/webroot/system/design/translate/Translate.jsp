<%@ page contentType='text/html;charset=utf-8' %>
<%@ include file='/apl/public.inc' %>

<jh:define item='&page.environment' alias='environment'/>
<jh:define item='&page.formName' alias='form_name'/>
<jh:define item='&page.formValue' alias='form_value'/>

<HTML>
<HEAD>
<TITLE>Class Example</TITLE>
<js:css/>
<js:js type='runtime.client'/>
</HEAD>
<!-- Script -------------------------------------------------->
<SCRIPT>
var action = '<jh:write item="&#parameter.do"/>';
var form = null;
var toolbar = null;
function doRefresh(){
	fmMain.action = '';
	fmMain.submit();
}
function doSave(){
	if(action == 'insert'){
		form.doAction('insertAction');
	}else{
		form.doAction('saveAction');
	}
}
function _onloadAll(){
	MoJS.connect();
	// Build toolbar
	toolbar = RControl.fromXml(xToolBar, _id_toolbar);
	// Build form
	form = RControl.fromXml(xForm, _id_form);
	form.loadValue(RXml.makeNode(xValue))
	// Action
	RControl.setAction(REnum.encode(EAction, action));
}
//----------------------------------------------------------
function _onload(){
   RWindow.connect(window);
   RLoader.loadJs('mobj');
   RLoader.waitJs(new TInvoke(null, _onloadAll), 'mobj');
}
</SCRIPT>
<!-- Environment --------------------------------------------->
<XML ID="xEnvironment">
<jh:write item='&environment' format='text'/>
</XML>
<!-- ToolBar ------------------------------------------------->
<XML ID="xToolBar">
<ToolBar width='100%' height='100%'>
	<ToolButton name='btnRefresh' caption='刷新' icon='tool.refresh' icon_disable='tool.refreshd'/>
	<ToolButtonSplit/>
	<ToolButton name='btnSave' caption='保存' icon='tool.save' icon_disable='tool.saved' hotkey='F8' action='doSave()'/>
	<ToolButton name='btnDelete' caption='删除' icon='tool.delete' icon_disable='tool.deleted' disp_access='U' page='delete@#/design/webtree/WebTree.wa'/>
	<ToolButtonSplit/>
	<ToolButton name='btnInsertColumn' caption='新建列' icon='#webtree.column' icon_disable='tool.insertd' disp_access='U' page='insert@#/design/webtree/WebControl.wa?form_name=design.webtree.TreeColumnForm'/>
	<ToolButton name='btnInsertType' caption='新建类型' icon='#webtree.type' icon_disable='tool.insertd' disp_access='U' page='insert@#/design/webtree/WebControl.wa?form_name=design.webtree.TreeNodeTypeForm'/>
	<ToolButton name='btnInsertLevel' caption='新建层次' icon='#webtree.level' icon_disable='tool.insertd' disp_access='U' page='insert@#/design/webtree/WebControl.wa?form_name=design.webtree.TreeLevelForm'/>
	<ToolButton name='btnInsertNode' caption='新建节点' icon='#webtree.node' icon_disable='tool.insertd' disp_access='U' page='insert@#/design/webtree/WebControl.wa?form_name=design.webtree.TreeNodeForm'/>
</ToolBar>
</XML>
<!-- Form ---------------------------------------------------->
<jc:form name='xForm' source='design.webtree.TreeViewForm'/>
<!-- Value --------------------------------------------------->
<XML ID="xValue">
<jh:write item='&form_value' format='text'/>
</XML>
<!-- Body begin ---------------------------------------------->
<jh:body style='bodyMain' scroll='no' onload='_onload()'>
<jh:form>
<jh:hidden name='&page.sel_tree'/>
<jc:include uri='#/inc/template/MainBody.tpl'/>
</jh:form>
</jh:body>
<!-- Body end ------------------------------------------------>
</HTML>
