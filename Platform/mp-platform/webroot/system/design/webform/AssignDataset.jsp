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
		form.doAction('daInsert');
	}else{
		form.doAction('daSave');
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
	<ToolButton name='btnRefresh' caption='Refresh' icon='tool.refresh' icon_disable='tool.refreshd'/>
	<ToolButtonSplit/>
	<ToolButton name='btnInsert'  caption='Insert'  icon='tool.insert' icon_disable='tool.saved' disp_access='U' page='insert@#/design/webform/WebControl.wa'/>
	<ToolButton name='btnSave'    caption='Save'  icon='tool.save' icon_disable='tool.saved' action='doSave()'/>
	<ToolButton name='btnDelete' caption='Delete'  icon='tool.delete' icon_disable='tool.deleted' disp_access='U' page='delete@#/design/webform/WebForm.wa'/>
	<ToolButtonSplit/>
	<ToolButton name='btnDesign'  caption='Design'  icon='tool.design' icon_disable='tool.designd' page='design@#/design/webform/WebForm.wa'/>
</ToolBar>
</XML>
<!-- Form ---------------------------------------------------->
<jc:form name='xForm' source='&form_name'/>
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
