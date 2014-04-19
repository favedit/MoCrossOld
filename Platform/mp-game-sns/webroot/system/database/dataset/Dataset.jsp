<%@ page contentType='text/html;charset=utf-8' %>
<%@ include file='/apl/public.inc' %>

<jh:define source='&page.environment' alias='environment'/>
<jh:define source='&page.formName' alias='form_name'/>
<jh:define source='&page.formValue' alias='form_value'/>

<HTML>
<HEAD>
<js:css/>
<js:js type='runtime.client'/>
<jh:title caption='系统管理 - 数据库 - 数据集'/>
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
	// Action
	RControl.setAction(REnum.encode(EAction, action));
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
	<ToolButtonSplit/>
	<ToolButton name='btnInsert' label='新建字段' icon='tool.insert' icon_disable='tool.saved' disp_access='U' hotkey='F5' page='insert@#/database/dataset/Field.wa'/>
	<ToolButton name='btnSave' label='保存' icon='tool.save' icon_disable='tool.saved' hotkey='F8' action='doSave()'/>
	<ToolButton name='btnDelete' label='删除' icon='tool.delete' icon_disable='tool.deleted' disp_access='U' page='delete@#/database/dataset/Dataset.wa'/>
	<ToolButtonSplit/>
	<ToolButtonMenu name='btnSql' label='数据' icon='tool.action' icon_disable='tool.actiond' page='buildSource@#/database/dataset/Dataset.wa'>
		<MenuItem name='miSqlTable' label='SQL - 数据表' icon='tool.build' icon_disable='tool.buildd' page='build@#/database/dataset/Dataset.wa?type=sqlTable'/>
		<MenuItem name='miSqlView' label='SQL - 数据视图' icon='tool.build' icon_disable='tool.buildd' page='build@#/database/dataset/Dataset.wa?type=sqlView'/>
		<MenuItem name='miSqlSeq' label='SQL - 序列' icon='tool.build' icon_disable='tool.buildd' page='build@#/database/dataset/Dataset.wa?type=sqlSequence'/>
		<MenuItem name='miPackageHead' label='PLSQL - 包头' icon='tool.build' icon_disable='tool.buildd' page='build@#/database/dataset/Dataset.wa?type=packageHead'/>
		<MenuItem name='miPackageBody' label='PLSQL - 包体' icon='tool.build' icon_disable='tool.buildd' page='build@#/database/dataset/Dataset.wa?type=packageBody'/>
		<MenuItem name='miStore' label='数据 - 存储' icon='tool.store' icon_disable='tool.buildd' page='dataStore@#/database/dataset/Dataset.wa'/>
		<MenuItem name='miRestore' label='数据 - 恢复' icon='tool.restore' icon_disable='tool.buildd' page='dataRestore@#/database/dataset/Dataset.wa'/>
	</ToolButtonMenu>
	<ToolButtonMenu name='btnBuildAll' label='编译' icon='tool.build' icon_disable='tool.buildd' page='build@#/database/dataset/Dataset.wa?type=all'>
		<MenuItem name='miEntity' label='Entity' icon='tool.build' icon_disable='tool.buildd' page='build@#/database/dataset/Dataset.wa?type=entity'/>
		<MenuItem name='miRow' label='Row' icon='tool.build' icon_disable='tool.buildd' page='build@#/database/dataset/Dataset.wa?type=row'/>
		<MenuItem name='miIBase' label='Data Access Object Face' icon='tool.build' icon_disable='tool.buildd' page='build@#/database/dataset/Dataset.wa?type=ibase'/>
		<MenuItem name='miDao' label='Data Access Object' icon='tool.build' icon_disable='tool.buildd' page='build@#/database/dataset/Dataset.wa?type=dao'/>
		<MenuItem name='miILogic' label='Logic Face' icon='tool.build' icon_disable='tool.buildd' page='build@#/database/dataset/Dataset.wa?type=ilogic'/>
		<MenuItem name='miLogic' label='Logic' icon='tool.build' icon_disable='tool.buildd' page='build@#/database/dataset/Dataset.wa?type=logic'/>
	</ToolButtonMenu>
	<ToolButtonSplit/>
	<ToolButton name='btnDesign' label='设计' icon='tool.design' icon_disable='tool.designd' page='design@#/database/dataset/Dataset.wa'/>
</ToolBar>
</XML>
<!-- Form ---------------------------------------------------->
<jc:form name='xForm' source='database.dataset.DatasetForm'/>
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
