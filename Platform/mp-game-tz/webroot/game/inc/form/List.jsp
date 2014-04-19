<%@ include file='/apl/public.inc' %>
<jh:define item='&webform.formConfig' alias='form_config'/>
<jh:define item='&webform.formValue' alias='form_value'/>

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
function doAction(n){
	if(n == 0){
		form.doInsert();
	}
	if(n == 1){
		form.doUpdate();
	}
	if(n == 2){
		form.doDelete();
	}
}
function _load(){
	toolbar = RControl.fromXml(xToolBar, _id_toolbar);
	form = RControl.fromXml(xForm, _id_form);
	if(form){
		form.loadValue(RXml.makeNode(xValue))
	}
}
</SCRIPT>

<XML ID="xToolBar">
	<ToolBar>
		<ToolButton name='btnRefresh' caption='Refresh' icon='refresh'/>
		<ToolButton name='btnBack'    caption='Back'    icon='back'/>
		<ToolButtonSplit/>
		<ToolButton name='btnInsert'  caption='Insert'  icon='insert' icon_disable='tool.insertd'/>
		<ToolButton name='btnUpdate'  caption='Update'  icon='edit' icon_disable='tool.updated'/>
		<ToolButton name='btnDelete'  caption='Delete'  icon='delete' icon_disable='tool.deleted'/>
		<ToolButtonSplit/>
		<ToolButtonDrop name='btnActions'  caption='Action'  icon='action' icon_disable='tool.insertd'/>
	</ToolBar>
</XML>
<XML ID="xForm"><jh:write item='&form_config' format='text'/></XML>
<XML ID="xValue"><jh:write item='&form_value' format='text'/></XML>

<BODY style='margin:0; padding-right:6; padding-bottom:6' scroll='no' onload='_load()'>
<TABLE border='0' cellpadding='0' cellspacing='0' width='100%' height='100%'>
   <TR>
      <TD height='1'>
			<DIV id='_id_toolbar'></DIV>
      </TD>
   </TR>
   <TR><TD height='4'></TD></TR>
   <TR>
      <TD style='border:1 solid #999999; padding:6' valign='top'>
			<DIV id='_id_form'></DIV><br>
      </TD>
   </TR>
</TABLE>
</BODY>

</HTML>
