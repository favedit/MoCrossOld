<%@ page contentType='text/html;charset=utf-8' %>
<%@ include file='/apl/public.inc' %>

<jh:define source='&page.environment' alias='environment'/>
<jh:define source='&page.formName' alias='form_name'/>
<jh:define source='&page.formSearch' alias='form_search'/>
<jh:define source='&page.formOrder' alias='form_oder'/>
<jh:define source='&page.formValue' alias='form_value'/>

<HTML>
<HEAD>
<TITLE>Class Example</TITLE>
<jh:css src='/company/acs/control.css'/>
<jh:css src='/company/acs/lang_cn.css'/>
<jh:js/>
</HEAD>
<!-- Script -------------------------------------------------->
<SCRIPT>
var action = '<jh:write source="&page.action"/>';
var form = null;
var toolbar = null;
var searchBox = null;
function doRefresh(){
	fmMain.action = '';
	fmMain.submit();
}
function doSelectRow(row){
	fmMain.form_name.value = form.formName;
	fmMain.form_action.value = EAction.Update;
	fmMain.ouid.value = row.ouid();
	fmMain.over.value = row.over();
	fmMain.action = "<jh:context path='/apl/form/WebForm.wa?do=update'/>";
	fmMain.submit();
}
function doBack(){
	fmMain.action = "<jh:context path='/apl/form/WebForm.wa?do=back'/>";
	fmMain.submit();
}
function doFetch(){
	form.dsFetch();
	form.setAction(EAction.Update);
}
function doSearch(){
	if(!searchBox){
		searchBox = RControl.create(FSearchWindow);
		searchBox.linkDsControl(form);
	}
	searchBox.show();
}
function doLov(){
	RConsole.find(FDatasetConsole).listView();
}
function doInsert(){
	//debugger
	var ds = RConsole.find(FFocusConsole).findClass(MDataset);
	var ps = ds.topControl(FPageSheet);
	fmMain.form_name.value = ds.formName;
	fmMain.form_action.value = EAction.Insert;
	fmMain.action = "<jh:context path='/apl/form/WebForm.wa?do=insert'/>";
	if(ps && ps.formLink){
		var pds = ds.topControl(MDataset);
		var attrs = new TAttributes();
		pds.saveValue(attrs);
		var fl = RPack.unpackLink(ps.formLink, attrs);
		fmMain.form_parent.value = fl.pack();
		fmMain.submit();
	}else{
		fmMain.submit();
	}
}
function doTableInsert(sender, table){
	var ds = table.topControl(MDataset);
	var ps = table.topControl(FPageSheet);
	if(ds && ps && ps.formLink){
		var attrs = new TAttributes();
		ds.saveValue(attrs);
		var fl = RPack.unpackLink(ps.formLink, attrs);
		fmMain.form_name.value = table.formName;
		fmMain.form_action.value = EAction.Insert;
		fmMain.form_parent.value = fl.pack();
		fmMain.action = "<jh:context path='/apl/form/WebForm.wa?do=insert'/>";
		fmMain.submit();
	}
}
function doInsertSave(){
	var da = new FDataAction();
	da.service = 'insert@logic.dataset';
	da.invoke(form); 
}
function doUpdate(){
   var dc = RConsole.find(FFocusConsole).findClass(MDataset);
   dc.dsUpdate();
}
function doUpdateSave(){
	var da = new FDataAction();
	da.service = 'update@logic.dataset';
	da.invoke(form); 
}
function doDelete(){
	if(form.isSelected()){
		var rows = form.rows;
		for(var n=0; n<rows.count; n++){
			var row = rows.get(n);
			if(row.isSelect){
			   	fmMain.form_name.value = form.formName;
			   	fmMain.form_action.value = EAction.Delete;
			   	fmMain.ouid.value = row.ouid();
			   	fmMain.over.value = row.over();
			   	fmMain.action = "<jh:context path='/apl/form/WebForm.wa?do=delete'/>";
			   	fmMain.submit();
			   	break;
			}
		}
	}
}
function doDeleteSave(){
	var da = new FDataAction();
	da.service = 'delete@logic.dataset';
	da.invoke(form); 
}
function doMovePage(action){
   form.dsMovePage(action);
}
function _load(){
	RWindow.connect(window);
	// Build toolbar
	toolbar = RControl.fromXml(xToolBar, _id_toolbar);
	var dc = RConsole.find(FDatasetConsole);
	//dc.register(EDataAction.Lov, toolbar.button('btnLov'));
	// Build form
	form = RControl.fromXml(xForm, _id_form);
	if(RClass.isClass(form, FTable) || RClass.isClass(form, FForm)){
		if(RClass.isClass(form, FTable)){
			var formParent = fmMain.form_parent.value;
			var parentPack = new TAttributes();
			parentPack.unpack(formParent);
			// Fetch dataset
			form.lsnsRowDblClick.push(new TListener(form, doSelectRow));
		}
		form.setAction(REnum.encode(EAction, action));
		form.loadDatasets(RXml.makeNode(xValue));
	}
	//debugger
	form.focus();
	// Listen events
	var listenerConsole = RConsole.find(FListenerConsole);
	listenerConsole.register(FTable, ETableAction.GoInsert, form, doTableInsert);
}
</SCRIPT>
<!-- Environment --------------------------------------------->
<XML ID="xEnvironment">
<jh:write source='&environment' format='text'/>
</XML>
<!-- ToolBar ------------------------------------------------->
<XML ID="xToolBar">
<DsToolBar width='100%'>
<jh:notEquals source='&page.history' value='top'>
</jh:notEquals>
	<ToolButton name='btnBack' type='back' caption='返回' icon='tool.back' icon_disable='tool.fetchd' hotkey='F1' action='doBack()'/>
	<ToolButtonSplit/>
	<ToolButton name='btnFetch' type='fetch' caption='查询' icon='tool.fetch' icon_disable='tool.fetchd' hotkey='F2' action='doFetch()'/>
	<ToolButton name='btnSearch' type='search' caption='搜索' icon='tool.search' icon_disable='tool.searchd' hotkey='F3' action='doSearch()'/>
	<ToolButton name='btnLov' type='lov' caption='选取'  icon='tool.view' icon_disable='tool.viewd' hotkey='F4' action='doLov()'/>
	<ToolButtonSplit/>
	<ToolButton name='btnInsert' type='insert' caption='新建' icon='tool.insert' icon_disable='tool.insertd' action='doInsert()'/>
	<ToolButton name='btnUpdate' type='update' caption='更新' icon='tool.save' icon_disable='tool.saved' action='doUpdate()'/>
	<ToolButton name='btnDelete' type='delete' caption='删除' icon='tool.delete' icon_disable='tool.deleted' action='doDelete()'/>
	<ToolButtonSplit/>
	<ToolButton name='btnFirst' type='first' icon='tool.ds-first' action='doMovePage(EDataAction.First)'/>
	<ToolButton name='btnPrior' type='prior' icon='tool.ds-prior' action='doMovePage(EDataAction.Prior)'/>
	<ToolButton name='btnNext' type='next' icon='tool.ds-next' action='doMovePage(EDataAction.Next)'/>
	<ToolButton name='btnLast' type='last' icon='tool.ds-last' action='doMovePage(EDataAction.Last)'/>
	<ToolButtonSplit/>
	<ToolButtonMenu name='btnAction' type='action' caption='操作'  icon='tool.action' icon_disable='tool.actiond' page='build@#/system/persistence/Persistence.wa'/>
</DsToolBar>
</XML>
<!-- Form ---------------------------------------------------->
<jc:form name='xForm' source='&form_name'/>
<!-- Value --------------------------------------------------->
<XML ID="xSearch"><jh:write source='&form_search' format='text'/></XML>
<XML ID="xParent"><jh:write source='&form_parent' format='text'/></XML>
<XML ID="xOrder"><jh:write source='&form_order' format='text'/></XML>
<XML ID="xValue"><jh:write source='&form_value' format='text'/></XML>
<!-- Body begin ---------------------------------------------->
<jh:body style='bodyMain' scroll='no' onload='_load()'>
<jh:form>
<!-- Hidden begin -------------------------------------------->
<jh:hidden name='form_name' source='&page.form_name'/>
<jh:hidden name='form_action' source='&page.form_action'/>
<jh:hidden name='form_parent' source='&page.form_parent'/>
<jh:hidden name='form_search' source='&page.form_search'/>
<jh:hidden name='form_order' source='&page.form_order'/>
<jh:hidden name='ouid'/>
<jh:hidden name='over'/>
<!-- Hidden end ---------------------------------------------->
<TABLE border='0' cellpadding='0' cellspacing='0' width='100%'>
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
<DIV id='_id_form' style='width:100%;height:100%;overflow:visible'></DIV><br>
<!-- Form end ------------------------------------------------>
</TD></TR>
</TABLE>
</jh:form>
</jh:body>
<!-- Body end ------------------------------------------------>
</HTML>
