<%@ page contentType='text/html;charset=utf-8' %>
<%@ include file='/apl/public.inc' %>

<jh:define item='&webform.environment' alias='environment'/>
<jh:define item='&webform.formConfig' alias='form_config'/>
<jh:define item='&webform.formValue' alias='form_value'/>

<HTML>
<HEAD>
<TITLE>Class Example</TITLE>
<js:css/>
<js:js type='runtime.client'/>
</HEAD>
<!-- Script -------------------------------------------------->
<SCRIPT>
var form = null;
var toolbar = null;
function doRefresh(){
	fmMain.action = '';
	fmMain.submit();
}
function doInsert(){
	form.doAction('insertAction');
}
function doSave(){
	form.doAction('saveAction');
}
function _onloadAll(){
	MoJS.connect();
	// Build toolbar
	toolbar = RControl.fromXml(xToolBar, _id_toolbar);
	// Build form
	form = RControl.fromXml(xForm, _id_form);
	form.loadValue(RXml.makeNode(xValue))
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
	<ToolButton name='btnRefresh' label='刷新' icon='tool.refresh' icon_disable='tool.refreshd'/>
	<ToolButtonSplit/>
	<ToolButton name='btnInsert' label='新建目录树' icon='#webtree.tree' icon_disable='tool.insertd' page='insert@#/design/webtree/WebTree.wa'/>
</ToolBar>
</XML>
<!-- Form ---------------------------------------------------->
<jc:form name='xForm' source='design.webtree.TreeViewList'/>
<!-- Value --------------------------------------------------->
<XML ID="xValue">
<jh:write item='&form_value' format='text'/>
</XML>
<!-- Body begin ---------------------------------------------->
<jh:body style='bodyMain' scroll='no' onload='_onload()'>
<jh:form>
<jc:include uri='#/inc/template/MainBody.tpl'/>
</jh:form>
</jh:body>
<!-- Body end ------------------------------------------------>
</HTML>
