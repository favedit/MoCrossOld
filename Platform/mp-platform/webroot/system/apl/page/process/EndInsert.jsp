<%@ page contentType='text/html;charset=utf-8' %>
<%@ include file='/apl/public.inc' %>

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
//----------------------------------------------------------
function doRefresh(){
	fmMain.action = '';
	fmMain.submit();
}
//----------------------------------------------------------
function _onloadAll(){
   MoJS.connect();
	// Build toolbar
	toolbar = RControl.fromXml(xToolBar, _id_toolbar);
	// Check result
	RConsole.find(FResultConsole).checkService(RXml.makeNode(xResult));
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
	<ToolButton name='btnBack' label='返回' icon='tool.back' icon_disable='tool.backd'/>
</ToolBar>
</XML>
<!-- Value --------------------------------------------------->
<XML ID="xValue">
<jh:write source='&form_value' format='text'/>
</XML>
<!-- Result --------------------------------------------------->
<XML ID="xResult">
<jh:write source='&page.resultXml' format='text'/>
</XML>
<!-- Body begin ---------------------------------------------->
<jh:body style='bodyMain' scroll='no' onload='_onload()'>
<jh:form>
<TABLE border='0' cellpadding='0' cellspacing='0' width='100%' height='100%'>
<TR>
<TD height='24'>
<!-- Toolbar begin ------------------------------------------->
<DIV id='_id_toolbar'></DIV>
<!-- Toolbar end --------------------------------------------->
</TD>
</TR>
<TR><TD height='4'></TD></TR>
<TR><TD style='padding:8' valign='top'>
<!-- Form begin ---------------------------------------------->
	<TABLE width='100%' cellspacing='8' border='0'>
	<TR><TD>
		<TABLE width='100%' border='0' cellspacing='1' cellpadding='4'>
			<TR><TD align='center' valign='middle'>
			<js:icon source='page.lmsg'/><jh:img icon='n' width='8'/>执行成功
			</TD></TR>
		</TABLE>
	</TD></TR>
	<TR><TD>
		<TABLE width='100%' bgcolor='#94B6FF' border='0' cellspacing='1' cellpadding='0'>
		<TR bgcolor='#FFFFFF'><TD>
		<TABLE width='100%' border='0' cellspacing='4' cellpadding='0'>
			<TR>
			<TD nowrap>新建操作执行成功.</TD>
			</TR>
		</TABLE>
	</TD></TR>
		</TABLE>
		</TD></TR>
		<TR><TD>
		<TABLE width='100%' border='0' cellspacing='0' cellpadding='4'>
		<TR>
		<TD align='center'>
		</TD>
		</TABLE>
	</TD></TR>
	</TABLE>
<!-- Form end ------------------------------------------------>
</TD></TR>
</TABLE>
</jh:form>
</jh:body>
<!-- Body end ------------------------------------------------>
</HTML>
