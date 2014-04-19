<%@ page contentType='text/html;charset=utf-8' %>
<%@ include file='/apl/public.inc' %>

<jh:define source='&webform.environment' alias='environment'/>
<jh:define source='&webform.formConfig' alias='form_config'/>
<jh:define source='&webform.formValue' alias='form_value'/>

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
function doInsert(){
	form.doAction('doInsert');
}
function doSave(){
	form.doAction('daSave');
}
function _onloadAll(){
	MoJS.connect();
	// Build toolbar
	toolbar = RControl.fromXml(xToolBar, _id_toolbar);
	// Build form
	form = RControl.fromXml(xForm, _id_form);
	if(RClass.isClass(FForm)){
		form.loadValue(RXml.makeNode(xValue))
	}else if(RClass.isClass(FTable)){
		form.dsFetch();
	}
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
	<ToolButton name='btnRefresh' caption='刷新' icon='tool.refresh' icon_disable='tool.refreshd'/>
	<ToolButtonSplit/>
	<ToolButton name='btnInsert' caption='新建数据集' icon='tool.insert' icon_disable='tool.insertd' page='insert@#/database/dataset/Dataset.wa'/>
	<ToolButtonSplit/>
	<ToolButtonMenu name='btnBuildAll' caption='全部编译' icon='tool.build' icon_disable='tool.buildd' page='buildAll@#/database/dataset/Dataset.wa?type=all'>
		<MenuItem name='miSqlTable' label='SQL - 数据表' icon='tool.build' icon_disable='tool.buildd' page='buildAll@#/database/dataset/Dataset.wa?type=sqlTable'/>
		<MenuItem name='miSqlView' label='SQL - 数据视图' icon='tool.build' icon_disable='tool.buildd' page='buildAll@#/database/dataset/Dataset.wa?type=sqlView'/>
		<MenuItem name='miSqlSequence' label='SQL - 数据视图' icon='tool.build' icon_disable='tool.buildd' page='buildAll@#/database/dataset/Dataset.wa?type=sqlSequence'/>
		<MenuItem name='miPackageHead' label='PLSQL - 包头' icon='tool.build' icon_disable='tool.buildd' page='buildAll@#/database/dataset/Dataset.wa?type=packageHead'/>
		<MenuItem name='miPackageBody' label='PLSQL - 包体' icon='tool.build' icon_disable='tool.buildd' page='buildAll@#/database/dataset/Dataset.wa?type=packageBody'/>
	</ToolButtonMenu>
	<ToolButtonMenu name='btnDataAll' caption='数据操作' icon='tool.build' icon_disable='tool.buildd'>
		<MenuItem name='miStore' label='数据 - 存储' icon='tool.store' icon_disable='tool.buildd' page='dataStore@#/database/dataset/Dataset.wa?type=all'/>
		<MenuItem name='miRestore' label='数据 - 恢复' icon='tool.restore' icon_disable='tool.buildd' page='dataRestore@#/database/dataset/Dataset.wa?type=all'/>
	</ToolButtonMenu>
</ToolBar>
</XML>
<!-- Form ---------------------------------------------------->
<jc:form name='xForm' source='database.dataset.DatasetList'/>
<!-- Value --------------------------------------------------->
<XML ID="xValue">
<jh:write source='&form_value' format='text'/>
</XML>
<!-- Body begin ---------------------------------------------->
<jh:body style='bodyMain' scroll='no' onload='_onload()'>
<jh:form>
<jc:include uri='#/inc/template/MainBody.tpl'/>
</jh:form>
</jh:body>
<!-- Body end ------------------------------------------------>
</HTML>
