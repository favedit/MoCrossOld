<%@ page contentType='text/html;charset=utf-8' %>
<%@ include file='/apl/public.inc' %>

<jh:define source='&page.environment' alias='environment'/>
<jh:define source='&page.formConfig' alias='form_config'/>
<jh:define source='&page.formValue' alias='form_value'/>

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
function doExecute(){
	var type = fmMain.source_type.value;
	fmMain.action = '<jh:context/>/database/procedure/Package.wa?do=execute&type=' + type;
	fmMain.submit();
}
function _onloadAll(){
	MoJS.connect();
	toolbar = RControl.fromXml(xToolBar, _id_toolbar);
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
	<ToolButton name='btnRefresh' label='刷新' icon='tool.refresh' icon_disable='tool.refreshd' action='doRefresh()'/>
	<ToolButtonSplit/>
	<ToolButton name='btnExecute' label='执行' icon='tool.execute' icon_disable='tool.executed' action='doExecute()'/>
</ToolBar>
</XML>
<!-- Body begin ---------------------------------------------->
<jh:body style='bodyMain' scroll='no' onload='_onload()'>
<jh:form>
<jh:hidden name='source_type' source='&#parameter.type'/>
<jh:hidden name='select_package' source='&page.selectPackage'/>
<TABLE border='0' cellpadding='0' cellspacing='0' width='100%' height='100%'>
<TR>
<TD height='24'>
<!-- Toolbar begin ------------------------------------------->
<DIV id='_id_toolbar'></DIV>
<!-- Toolbar end --------------------------------------------->
</TD>
</TR>
<TR><TD height='2'></TD></TR>
<TR><TD style='padding:4' valign='top'>
<!-- Form begin ---------------------------------------------->
<DIV style='width:100%; height:100%; border:1 solid #008800; padding:6; background-color:#FFFFFF; overflow:auto;'>
<TABLE><TR><TD nowrap>
<jh:write source='&page.source' format='text'/>
</TD></TR></TABLE>
</DIV>
<!-- Form end ------------------------------------------------>
</TD></TR>
</TABLE>
</jh:form>
</jh:body>
<!-- Body end ------------------------------------------------>
</HTML>
