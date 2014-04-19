<%@ page contentType='text/html;charset=utf-8' %>
<%@ include file='/apl/public.inc' %>

<jh:define source='&page.environment' alias='environment'/>
<jh:define source='&page.formValue' alias='form_value'/>

<HTML>
<HEAD>
<TITLE>Class Example</TITLE>
<js:css/>
<js:js type='runtime.client'/>
</HEAD>
<!-- Script -------------------------------------------------->
<SCRIPT>
var action = '<jh:write source="&#parameter.do"/>';
var form = null;
var toolbar = null;
function doRefresh(){
	fmMain.action = '';
	fmMain.submit();
}
function doDesign(){
	var config = RControl.store(form);
	var ra = RClass.create(FRemoteAction);
	ra.execute('design@<jh:write source='&page.formService'/>', config);
}
//----------------------------------------------------------
function _onloadAll(){
	MoJS.connect();
	// Build toolbar
	toolbar = RControl.fromXml(xToolBar, _id_toolbar);
	// Build form
	RConsole.find(FKeyConsole).skipRegister();
	form = RControl.fromXml(xForm, _id_form, EMode.Design);
	form.psDesign(EDesign.Move, true);
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
<SCRIPT id="xEnvironment" type="application/xml"><jh:write source='&page.environment' format='text'/></SCRIPT>
<!-- ToolBar ------------------------------------------------->
<SCRIPT id="xToolBar" type="application/xml">
<ToolBar width='100%' height='100%'>
	<ToolButton name='btnBack' label='返回' icon='tool.back' icon_disable='tool.backd'/>
	<ToolButton name='btnRefresh' label='刷新' icon='tool.refresh' icon_disable='tool.refreshd'/>
	<ToolButtonSplit/>
	<ToolButton name='saveButton' label='保存' icon='tool.save' icon_disable='tool.saved' action='doDesign()' hotkey='F8'/>
</ToolBar>
</SCRIPT>
<!-- Form ---------------------------------------------------->
<XML ID="xForm"><jh:write source='&page.formConfig' format='false'/></XML>
<!-- Value --------------------------------------------------->
<XML ID="xValue">
<Config/>
</XML>
<!-- Body begin ---------------------------------------------->
<jh:body style='bodyMain' scroll='no' onload='_onload()'>
<jh:form>
<TABLE border='0' cellpadding='0' cellspacing='0' width='100%' height='100%'>
<!-- Toolbar begin ------------------------------------------->
<TR><TD height='1'><DIV id='_id_toolbar'></DIV></TD></TR>
<!-- Toolbar end --------------------------------------------->
<!-- Form begin ---------------------------------------------->
<TR><TD>
<DIV style='padding:12;width:100%;height:100%;overflow:auto'>
   <DIV id='_id_form' style='width:100%'></DIV>
</DIV>
</TD></TR>
<!-- Form end ------------------------------------------------>
</TABLE>
</jh:form>
</jh:body>
<!-- Body end ------------------------------------------------>
</HTML>
