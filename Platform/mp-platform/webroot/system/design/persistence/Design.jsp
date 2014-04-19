<%@ page contentType='text/html;charset=utf-8' %>
<%@ include file='/apl/public.inc' %>

<jh:define item='&page.environment' alias='environment'/>
<jh:define item='&page.formName' alias='form_name'/>
<jh:define item='&page.formValue' alias='form_value'/>

<HTML>
<HEAD>
<TITLE>Class Example</TITLE>
<js:css/>
<jh:js/>
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
function _load(){
	RWindow.connect();
	// Build toolbar
	toolbar = RControl.fromXml(xToolBar, _id_toolbar);
	// Build form
	form = RControl.fromXml(xForm, _id_form);
	form.design(EDesign.Move, true);
}
</SCRIPT>
<!-- Environment --------------------------------------------->
<SCRIPT id="xEnvironment" type="application/xml"><jh:write source='&page.environment' format='text'/></SCRIPT>
<!-- ToolBar ------------------------------------------------->
<SCRIPT id="xToolBar" type="application/xml">
<ToolBar width='100%' height='100%'>
	<ToolButton name='btnRefresh' caption='刷新' icon='tool.refresh' icon_disable='tool.refreshd'/>
	<ToolButton name='btnBack' caption='返回' icon='tool.back' icon_disable='tool.backd'/>
	<ToolButtonSplit/>
	<ToolButton name='btnSave' caption='保存' icon='tool.save' icon_disable='tool.saved' action='doDesign()'/>
</ToolBar>
</SCRIPT>
<!-- Form ---------------------------------------------------->
<XML ID="xForm">
<WebForm name="frmOrder" width='100%' height='100%'>
	<DataAction name='daSaveOrder' service='saveAttributeOrder@design.persistence'/>
<jh:write item='&page.formConfig' format='text'/>
</WebForm>
</XML>
<!-- Value --------------------------------------------------->
<XML ID="xValue">
<jh:write item='&form_value' format='text'/>
</XML>
<!-- Body begin ---------------------------------------------->
<jh:body style='bodyMain' scroll='no' onload='_load()'>
<jh:form>
<jc:include uri='#/inc/template/MainBody.tpl'/>
</jh:form>
</jh:body>
<!-- Body end ------------------------------------------------>
</HTML>
