<%@ page contentType='text/html;charset=utf-8' %>
<%@ include file='/apl/public.inc' %>

<jh:define item='&page.environment' alias='environment'/>
<jh:define item='&page.formConfig' alias='form_config'/>
<jh:define item='&page.formName' alias='form_name'/>
<jh:define item='&page.formSearch' alias='form_search'/>
<jh:define item='&page.formOrder' alias='form_oder'/>
<jh:define item='&page.formValue' alias='form_value'/>

<HTML>
<HEAD>
<TITLE>Class Example</TITLE>
<jh:css src='#/acs/control.css'/>
<jh:css src='#/acs/lang_cn.css'/>
<js:js type='runtime.client'/>

</HEAD>
<!-- Script -------------------------------------------------->
<SCRIPT>
var go = false;
var action = '<jh:write item="&page.action"/>';
var form = null;
var toolbar = null;
var searchBox = null;
function doRefresh(){
	fmMain.action = '';
	fmMain.submit();
}
function doSelectRow(row){
	var ds = row.table;
	if(ds && ds.isLov){return;}
	if(go){return;}else{go = true};
	var pack = new TAttrs();
	pack.set('ouid', row.ouid());
	pack.set('over', row.over());
	fmMain.form_name.value = row.table.formName;
	fmMain.form_action.value = EAction.Update;
	fmMain.form_search.value = pack.pack();
	fmMain.action = "<jh:context path='#/logic/webform/WebForm.wa?do=update'/>";
	fmMain.submit();
}
function doBack(){
	fmMain.action = "<jh:context path='#/logic/webform/WebForm.wa?do=back'/>";
	fmMain.submit();
}
function doFetch(){
	RConsole.find(FFocusConsole).findClass(MDataset).dsFetch();
}
function doSearch(){
	RConsole.find(FFocusConsole).findClass(MDataset).doSearch();
}
function doLov(){
	var fc = RConsole.find(FFocusConsole).findClass(MListView);
	if(fc){
		fc.doListView();
	}
}
function doInsert(){
	var ds = RConsole.find(FFocusConsole).findClass(MDataset);
	var ps = ds.topControl(FPageSheet);
	fmMain.form_name.value = ds.getFormName();
	fmMain.form_action.value = EAction.Insert;
	fmMain.action = "<jh:context path='#/logic/webform/WebForm.wa?do=insert'/>";
	if(ps && ps.formLink){
		var pds = ds.topControl(MDataset);
		var attrs = new TAttrs();
		pds.saveValue(attrs, EStore.Name);
		var fl = RPack.unpackLink(ps.formLink, attrs);
		fmMain.form_parent.value = fl.pack();
		fmMain.submit();
	}else{
		fmMain.submit();
	}
}
function doUpdate(){
   var dc = RConsole.find(FFocusConsole).findClass(MDataset);
   if(RClass.isClass(dc, FForm)){
      dc.dsUpdate();
   }else if(RClass.isClass(dc, FTable)){
      if(dc.dsIsChanged()){
         dc.dsUpdate();
      }else{
         doSelectRow();
      }
   }
}
function doDelete(){
	var fs = new TAttrs();
	var dc = RConsole.find(FFocusConsole).findClass(MDataset);
	if(RClass.isClass(dc, FForm)){
		fs.set('ouid', dc.dsGet('ouid'));
		fs.set('over', dc.dsGet('over'));
	}else if(RClass.isClass(dc, FTable)){
		var rs = dc.findSelRows();
		if(!rs.isEmpty()){
			var r = rs.get(0);
			fs.set('ouid', r.ouid());
			fs.set('over', r.over());
		}
	}
	if(!fs.isEmpty()){
		fmMain.form_name.value = dc.getFormName();
		fmMain.form_action.value = EAction.Delete;
		fmMain.form_search.value = fs.pack();
		fmMain.action = "<jh:context path='#/logic/webform/WebForm.wa?do=delete'/>";
		fmMain.submit();
	}
}
function doInsertSave(){
	var da = new FDataAction();
	da.service = 'insert@logic.dataset';
	da.invoke(form); 
}
function doUpdateSave(){
	var da = new FDataAction();
	da.service = 'update@logic.dataset';
	da.invoke(form); 
}
function doDeleteSave(){
	var da = new FDataAction();
	da.service = 'delete@logic.dataset';
	da.invoke(form); 
}
function doMovePage(action){
	var dc = RConsole.find(FFocusConsole).findClass(MDataset);
	if(RClass.isClass(dc, FTable)){
		form.dsMovePage(action);
	}
}
function doPrintPdf(){
	var dc = RConsole.find(FFocusConsole).findClass(MDataset);
	if(RClass.isClass(dc, MForm)){
		var uri = "<jh:context path='/public.pdf.wv'/>?do=build&form_name=" + dc.getFormName();
		RHtml.popup(uri, 800, 600);
	}
}
function _onloadAll(){
	MoJS.connect();
	RWindow.connect(window);
	// Build toolbar
	toolbar = RControl.fromXml(xToolBar, _id_toolbar);
	var dc = RConsole.find(FDatasetConsole);
	// Build form
	form = RControl.fromXml(xForm, _id_form);
	if(RClass.isClass(form, FTable) || RClass.isClass(form, FForm)){
		if(RClass.isClass(form, FTable)){
			var formParent = fmMain.form_parent.value;
			var parentPack = new TAttrs();
			parentPack.unpack(formParent);
		}
		form.setAction(REnum.encode(EAction, action));
		form.loadDatasets(RXml.makeNode(xValue));
	}
	top.document.title = form.label;
	form.focus();
	// Listen events
	var lsnConsole = RConsole.find(FListenerConsole);
	lsnConsole.register(MDataset, EDataAction.EndUpdate, form, doBack);
	lsnConsole.register(FTable, ETableAction.RowClick, form, doSelectRow);
	lsnConsole.register(FTable, ETableAction.RowDblClick, form, doSelectRow);
	lsnConsole.register(FTable, ETableAction.GoInsert, form, doInsert);
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
<jh:write item='&environment' format='text'/>
</XML>
<!-- ToolBar ------------------------------------------------->
<XML ID="xToolBar">
<DsToolBar width='100%'>
<jh:notEquals item='&page.history' value='top'>
	<ToolButton name='btnBack' type='back' label='返回' icon='tool.back' icon_disable='tool.fetchd' action='doBack()'/>
	<ToolButtonSplit/>
</jh:notEquals>
	<ToolButton name='btnFetch' type='fetch' label='查询' icon='tool.fetch' icon_disable='tool.fetchd' hotkey='F2' action='doFetch()'/>
	<ToolButton name='btnSearch' type='search' label='搜索' icon='tool.search' icon_disable='tool.searchd' hotkey='F3' action='doSearch()'/>
	<ToolButton name='btnLov' type='lov' label='选取'  icon='tool.view' icon_disable='tool.viewd' hotkey='F4' action='doLov()'/>
	<ToolButtonSplit/>
	<ToolButton name='btnInsert' type='insert' label='新建' icon='tool.insert' icon_disable='tool.insertd' hotkey='F5' action='doInsert()'/>
	<ToolButton name='btnUpdate' type='update' label='更新' icon='tool.save' icon_disable='tool.saved' hotkey='F8' action='doUpdate()'/>
	<ToolButton name='btnDelete' type='delete' label='删除' icon='tool.delete' icon_disable='tool.deleted' hotkey='F6' action='doDelete()'/>
	<ToolButtonSplit/>
	<ToolButton name='btnFirst' type='first' icon='tool.ds-first' action='doMovePage(EDataAction.First)'/>
	<ToolButton name='btnPrior' type='prior' icon='tool.ds-prior' action='doMovePage(EDataAction.Prior)'/>
	<ToolButton name='btnNext' type='next' icon='tool.ds-next' action='doMovePage(EDataAction.Next)'/>
	<ToolButton name='btnLast' type='last' icon='tool.ds-last' action='doMovePage(EDataAction.Last)'/>
	<ToolButtonSplit/>
	<ToolButtonMenu name='btnAction' type='action' label='操作'  icon='tool.action' icon_disable='tool.actiond' hotkey='F12'>
		<MenuButton name='miPrintPdf' type='first' label='打印-PDF' icon='file.pdf' action='doPrintPdf()'/>
	</ToolButtonMenu>
</DsToolBar>
</XML>
<!-- Form ---------------------------------------------------->
<XML ID="xForm"><jh:write item='&form_config' format='text'/></XML>
<XML ID="xSearch"><jh:write item='&form_search' format='text'/></XML>
<XML ID="xParent"><jh:write item='&form_parent' format='text'/></XML>
<XML ID="xOrder"><jh:write item='&form_order' format='text'/></XML>
<XML ID="xValue"><jh:write item='&form_value' format='text'/></XML>
<!-- Body begin ---------------------------------------------->
<jh:body style='bodyLogic' scroll='no' onload='_onload()'>
<jh:form>
<!-- Hidden begin -------------------------------------------->
<jh:hidden name='form_name' item='&page.form_name'/>
<jh:hidden name='form_action' item='&page.form_action'/>
<jh:hidden name='form_parent' item='&page.form_parent'/>
<jh:hidden name='form_search' item='&page.form_search'/>
<jh:hidden name='form_order' item='&page.form_order'/>
<jh:hidden name='ouid'/>
<jh:hidden name='over'/>
<!-- Hidden end ---------------------------------------------->
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
