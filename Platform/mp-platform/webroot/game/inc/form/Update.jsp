<%@ include file='/apl/public.inc' %>
<jh:define item='&webform.formConfig' alias='form_config'/>
<jh:define item='&webform.formValue' alias='form_value'/>
<jh:define item='&webform.environment' alias='environment'/>

<HTML>
<HEAD>
<TITLE>Class Example</TITLE>
<jh:css/>
<jh:js/>
</HEAD>

<STYLE>
.lstType  { text-align:center; nowrap };
.lstLabel { nowrap };
.lstDate  { nowrap };
.lstUpd   { nowrap };
.lstChk   { nowrap };
</STYLE>

<SCRIPT>
var form = null;
var toolbar = null;
function doRefresh(){
	fmMain.action = '';
	fmMain.submit();
}
function doSave(){
	form.doAction('doSave');
}
function _load(){
	// Build toolbar
	toolbar = RControl.fromXml(xToolBar, _id_toolbar);
	if(toolbar){
		toolbar.component('btnRefresh').addClickListener(doRefresh);
		toolbar.component('btnSave').addClickListener(doSave);
	}
	// Build form
	form = RControl.fromXml(xForm, _id_form);
	if(form){
		form.environment = RXml.makeNode(xEnvironment);
		var value = RXml.makeNode(xValue);
		if(value){
			form.loadValue(RXml.makeNode(xValue))
		}
	}
}
</SCRIPT>

<!-- Environment --------------------------------------------->

<XML ID="xEnvironment">
<jh:write item='&environment' format='text'/>
</XML>
<!-- ToolBar ------------------------------------------------->
<XML ID="xToolBar">
<ToolBar>
	<ToolButton name='btnRefresh' caption='Refresh' icon='tool.refresh' icon_disable='tool.refreshd'/>
	<ToolButtonSplit/>
	<ToolButton name='btnSave'    caption='Save'  icon='tool.save' icon_disable='tool.saved'/>
</ToolBar>
</XML>
<!-- Form ---------------------------------------------------->
<XML ID="xForm">
<jh:write item='&form_config' format='text'/>
</XML>
<!-- Value --------------------------------------------------->
<XML ID="xValue">
<jh:write item='&form_value' format='text'/>
</XML>

<BODY style='margin:0; padding-right:6; padding-bottom:6' scroll='no' onload='_load()'>
<jh:form>

<TABLE border='0' cellpadding='0' cellspacing='0' width='100%' height='100%'>
   <TR>
      <TD height='1'>
			<DIV id='_id_toolbar'></DIV>
      </TD>
   </TR>
   <TR><TD height='4'></TD></TR>
   <TR>
      <TD style='border:1 solid #999999; padding:6' valign='top'>
			<DIV id='_id_form' style='width:100%;height:100%;overflow:auto'></DIV><br>
      </TD>
   </TR>
</TABLE>

</jh:form>
</BODY>

</HTML>
