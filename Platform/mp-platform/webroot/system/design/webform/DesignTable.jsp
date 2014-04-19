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
function doDesign(action){
	form.doAction('daSaveOrder');
}
function _onloadAll(){
	MoJS.connect();
	RWindow.connect();
	// Build toolbar
	toolbar = RControl.fromXml(xToolBar, _id_toolbar);
	// Build form
	form = RControl.fromXml(xForm, _id_form);
	form.design(EDesign.Move, true);
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
	<ToolButton name='btnBack' caption='返回' icon='tool.back' icon_disable='tool.backd'/>
	<ToolButtonSplit/>
	<ToolButton name='btnSave' caption='保存' icon='tool.save' icon_disable='tool.saved' action='doDesign()'/>
</ToolBar>
</XML>
<!-- Form ---------------------------------------------------->
<XML ID="xForm">
<WebForm name="frmOrder" width='100%' height='100%'>
	<DataAction name='daSaveOrder' service='saveControlOrder@design.webform'/>
<jh:write item='&page.formConfig' format='text'/>
</WebForm>
</XML>
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
