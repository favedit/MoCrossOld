<%@ include file='/apl/public.inc' %>
<jh:define item='&webform.formConfig' alias='form_config'/>
<jh:define item='&webform.formValue' alias='form_value'/>
<jh:define item='&webform.formDataset' alias='form_dataset'/>
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
function doUpdate(){
	form.dsUpdate('logic.webform.dataset');
}
function doDelete(){
	form.deleteRow();
}
function _load(){
	// Build toolbar
	toolbar = RControl.fromXml(xToolBar, _id_toolbar);
	if(toolbar){
		toolbar.component('btnRefresh').addClickListener(doRefresh);
		toolbar.component('btnUpdate').addClickListener(doUpdate);
		toolbar.component('btnDelete').addClickListener(doDelete);
	}
	// Build form
	form = RControl.fromXml(xForm, _id_form);
	//if(form){
		//form.environment = RXml.makeNode(xEnvironment);
		//form.loadValue(RXml.makeNode(xValue))
	//}
	//if(RClass.isClass(form, FTable)){
	//	form.loadDataset(RXml.makeNode(xDataset))
	//}
	form.dsConnect('logic.webform.dataset')
}
</SCRIPT>

<XML ID="xEnvironment"><jh:write item='&environment' format='text'/></XML>
<XML ID="xToolBar">
	<ToolBar>
		<ToolButton name='btnRefresh' caption='Refresh' icon='tool.refresh' icon_disable='tool.refreshd'/>
		<ToolButtonSplit/>
		<ToolButton name='btnInsert' caption='Insert' icon='tool.insert' icon_disable='tool.insertd'/>
		<ToolButton name='btnUpdate' caption='Update' icon='tool.update' icon_disable='tool.updated'/>
		<ToolButton name='btnDelete' caption='Delete' icon='tool.delete' icon_disable='tool.deleted'/>
		<ToolButtonSplit/>
		<ToolButton name='btnAction' caption='Action' icon='tool.action' icon_disable='tool.actiond'/>
	</ToolBar>
</XML>
<XML ID="xForm"><jh:write item='&form_config' format='text'/></XML>
<XML ID="xValue"><jh:write item='&form_value' format='text'/></XML>
<XML ID="xDataset"><jh:write item='&form_dataset' format='text'/></XML>

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
