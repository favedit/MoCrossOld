<%@ page contentType='text/html;charset=utf-8' %>
<%@ include file='/apl/public.inc' %>

<jh:define item='&page.environment' alias='environment'/>
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
function doDesign(){
	var config = RControl.store(form);
	var ra = RClass.create(FRemoteAction);
	ra.execute('saveOrder@design.webform', config);
}
function _onloadAll(){
	MoJS.connect();
	RWindow.connect();
	// Build toolbar
	toolbar = RControl.fromXml(xToolBar, _id_toolbar);
	// Build form
	RConsole.find(FKeyConsole).skipRegister();
	form = RControl.fromXml(xForm, _id_form);
	form.design(EDesign.Move, true);
	RConsole.find(FKeyConsole).allowRegister();
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
	<ToolButton name='btnBack' caption='返回' icon='tool.back' icon_disable='tool.backd'/>
	<ToolButton name='btnRefresh' caption='刷新' icon='tool.refresh' icon_disable='tool.refreshd'/>
	<ToolButtonSplit/>
	<ToolButton name='btnSave' caption='保存' icon='tool.save' icon_disable='tool.saved' action='doDesign()'/>
</ToolBar>
</XML>
<!-- Form ---------------------------------------------------->
<jc:form name='xForm' source='&page.selectForm'/>
<!-- Value --------------------------------------------------->
<XML ID="xValue">
<Config/>
</XML>
<!-- Body begin ---------------------------------------------->
<jh:body style='bodyMain' scroll='no' onload='_onload()'>
<jh:form>
<jc:include uri='#/inc/template/MainBody.tpl'/>
<DIV id='_log'></DIV>
</jh:form>
</jh:body>
<!-- Body end ------------------------------------------------>
</HTML>
