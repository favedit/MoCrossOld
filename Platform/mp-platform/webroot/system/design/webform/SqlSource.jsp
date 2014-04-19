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
function doClipboard(){
   window.clipboardData.setData('Text', fmMain.source.innerText);
   alert('当前内容已经复制到剪贴板!');
}
function doExecute(){
	fmMain.action = '<jh:context/>/design/webform/WebForm.wa?do=sqlSourceSave';
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
	<ToolButton name='btnClipboard' label='复制到剪贴板' icon='tool.copy' icon_disable='tool.copyd' action='doClipboard()'/>
	<ToolButtonSplit/>
	<ToolButton name='btnUpdate' label='保存' icon='#tools.save' action='doExecute()'/>
</ToolBar>
</XML>
<!-- Body begin ---------------------------------------------->
<jh:body style='bodyMain' scroll='no' onload='_onload()'>
<jh:form>
<jh:hidden name='form_name' source='&page.formName'/>
<jh:hidden name='form_service' source='&page.formService'/>
<jh:hidden name='source_type' source='&#parameter.type'/>
<jh:hidden name='sel_type' source='&page.selectType'/>
<jh:hidden name='sel_collection' source='&page.selectCollection'/>
<jh:hidden name='sel_component' source='&page.selectComponent'/>
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
<TEXTAREA name='sqlSource' wrap='off' style='border:1 solid #008800; width:100%;height:100%;'><jh:write source='&page.sqlSource' format='text'/></TEXTAREA>
<!-- Form end ------------------------------------------------>
</TD></TR>
</TABLE>
</jh:form>
</jh:body>
<!-- Body end ------------------------------------------------>
</HTML>
