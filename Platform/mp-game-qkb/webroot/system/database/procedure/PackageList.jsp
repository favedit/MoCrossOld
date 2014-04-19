<%@ page contentType='text/html;charset=utf-8' %>
<%@ include file='/apl/public.inc' %>
<jh:define source='&page.environment' alias='environment'/>
<jh:define source='&page.formName' alias='form_name'/>
<jh:define source='&page.formValue' alias='form_value'/>

<HTML>
<HEAD>
<js:css/>
<js:js type='runtime.client'/>
<jh:title caption='系统管理 - 数据库 - 数据包'/>
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
function doSave(){
	if(action == 'insert'){
		form.doAction('daInsert');
	}else{
		form.doAction('daSave');
	}
}
function _onloadAll(){
	MoJS.connect();
	RWindow.connect(window);
	// Build toolbar
	toolbar = RControl.fromXml(xToolBar, _id_toolbar);
	// Build form
	form = RControl.fromXml(xForm, _id_form);
	form.loadValue(RXml.makeNode(xValue))
	form.psMode(EMode.Update);
}
//----------------------------------------------------------
function _onload(){
   RWindow.connect(window);
   RLoader.loadJs('mobj');
   RLoader.waitJs(new TInvoke(null, _onloadAll), 'mobj');
}
</SCRIPT>
<!-- Environment --------------------------------------------->
<XML id="xEnvironment">
<jh:write source='&environment' format='text'/>
</XML>
<!-- ToolBar ------------------------------------------------->
<XML id="xToolBar">
<ToolBar width='100%' height='100%'>
   <ToolButton name='btnBack' type='back' label='返回' icon='tool.back' action='doBack()'/>
   <ToolButtonSplit/>
   <ToolButtonMenu label_width="80" name="sourceMenu" label="代码生成" disp_access="SPLIUD" icon="#tools.build">
      <MenuButton label_width="80" name="buildJavaFace" label="建立JAVA接口" disp_access="LIUD" icon="#tools.build" page="buildAll@#/database/procedure/Package.wa" attributes="type=JavaPackageFace"/>
      <MenuButton label_width="80" name="buildJavaClass" label="建立JAVA类对象" disp_access="LIUD" icon="#tools.build" page="buildAll@#/database/procedure/Package.wa" attributes="type=JavaPackage"/>
   </ToolButtonMenu>
</ToolBar>
</XML>
<!-- Form ---------------------------------------------------->
<jc:form name='xForm' source='database.procedure.PackageForm'/>
<!-- Value --------------------------------------------------->
<XML id="xValue">
<jh:write source='&form_value' format='text'/>
</XML>
<!-- Body begin ---------------------------------------------->
<jh:body style='bodyMain' scroll='no' onload='_onload()'>
<jh:form>
<jh:hidden name='select_package' source='&page.selectPackage'/>
<TABLE border='0' cellpadding='0' cellspacing='0' width='100%' height='100%'>
<TR>
<TD height='24'>
<!-- Toolbar begin ------------------------------------------->
<DIV id='_id_toolbar'></DIV>
<!-- Toolbar end --------------------------------------------->
</TD>
</TR>
<TR><TD style='padding:0; overflow:auto'>
<!-- Form begin ---------------------------------------------->
<DIV style='padding:8;width:100%;height:100%;overflow:auto'>
	<DIV id='_id_form'></DIV>
</DIV>
<!-- Form end ------------------------------------------------>
</TD></TR>
</TABLE>
</jh:form>
</jh:body>
<!-- Body end ------------------------------------------------>
</HTML>
