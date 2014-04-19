<%@ page contentType='text/html;charset=utf-8' %>
<%@ include file='/apl/public.inc' %>

<jh:define source='&page.environment' alias='environment'/>
<jh:define source='&page.formName' alias='form_name'/>
<jh:define source='&page.formValue' alias='form_value'/>

<HTML>
<HEAD>
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
//----------------------------------------------------------
function doSort(action){
	form.doAction('sortAction');
}
//----------------------------------------------------------
function _onloadAll(){
	MoJS.connect();
	// Build toolbar
	toolbar = RControl.fromXml(xToolBar, _id_toolbar);
	// Build form
	form = RControl.fromXml(xForm, _id_form, EMode.Design);
	form.psDesign(EDesign.Move, true);
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
<jh:write source='&environment' format='text'/>
</XML>
<!-- ToolBar ------------------------------------------------->
<XML ID="xToolBar">
<ToolBar width='100%' height='100%'>
	<ToolButton name='btnRefresh' label='刷新' icon='tool.refresh' icon_disable='tool.refreshd'/>
	<ToolButton name='btnBack' label='返回' icon='tool.back' icon_disable='tool.backd'/>
	<ToolButtonSplit/>
	<ToolButton name='saveButton' label='保存' icon='tool.save' icon_disable='tool.saved' action='doSort()' hotkey='F8'/>
</ToolBar>
</XML>
<!-- Form ---------------------------------------------------->
<XML ID="xForm"><jh:write source='&page.formConfig' format='text'/></XML>
<!-- Value --------------------------------------------------->
<XML ID="xValue">
<jh:write source='&form_value' format='text'/>
</XML>
<!-- Body begin ---------------------------------------------->
<jh:body style='bodyMain' scroll='no' onload='_onload()'>
<jh:form>
<TABLE border='0' cellpadding='0' cellspacing='0' width='100%' height='100%'>
<TR>
<TD height='1'>
<!-- Toolbar begin ------------------------------------------->
<DIV id='_id_toolbar'></DIV>
<!-- Toolbar end --------------------------------------------->
</TD>
</TR>
<TR><TD height='4'></TD></TR>
<TR><TD valign='top'>
<!-- Form begin ---------------------------------------------->
   <DIV id='_id_form' style='padding:8;width:100%;height:100%;overflow:auto'></DIV><br>
<!-- Form end ------------------------------------------------>
</TD></TR>
</TABLE>
</jh:form>
</jh:body>
<!-- Body end ------------------------------------------------>
</HTML>
