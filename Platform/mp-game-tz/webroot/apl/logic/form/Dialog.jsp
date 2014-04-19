<%@ page contentType='text/html;charset=utf-8' %>
<%@ include file='/apl/public.inc' %>

<jh:define source='&page.environment' alias='environment'/>
<jh:define source='&page.formConfig' alias='form_config'/>
<jh:define source='&page.formName' alias='form_name'/>
<jh:define source='&page.formSearch' alias='form_search'/>
<jh:define source='&page.formOrder' alias='form_oder'/>
<jh:define source='&page.formValue' alias='form_value'/>

<HTML>
<SCRIPT>
function clearSearch(s){
   if( RClass.isClass(s, FNavigatorItem) ){
      fmMain.form_search.value='';      
   }else if( RClass.isClass(s, FNavigatorButton) ){
   }
}
function onloadAll(){
   RFormSpace.lsnsTitleButtonClickBefore.register(null, clearSearch);
}
</SCRIPT>
<HEAD>
<TITLE>eUIS</TITLE>
<je:css/>
<je:js type='runtime'/>
</HEAD>
<!-- Script -------------------------------------------------->
<SCRIPT>
var go = false;
var action = 'update';
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
	var pack = new TAttributes();
	pack.set('ouid', row.ouid());
	pack.set('over', row.over());
	fmMain.form_name.value = row.table.formName;
	fmMain.form_action.value = EAction.Update;
	fmMain.form_search.value = pack.pack();
	fmMain.action = "<jh:context path='/apl/logic/form/WebForm.wa?do=update'/>";
	fmMain.submit();
}

function getInsideRow(xvalue){
   var dn = RDataset.make(xvalue.nodes.get(0));
   return dn.rows.get(0);
    
}
function doBack(){
	if(RClass.isClass(form, FForm)){
		fmMain.action = "<jh:context path='/apl/logic/form/WebForm.wa?do=back'/>";
		fmMain.submit();
	}
}
function doFetch(){
	RConsole.find(FFocusConsole).findClass(MDataset).dsFetch(true);
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
function doZoom(){
	var fc = RConsole.find(FFocusConsole).findClass(MZoom);
	if(fc){
		fc.doZoom();
	}
}
function doInsert(){
	var ds = RConsole.find(FFocusConsole).findClass(MDataset);
	var ps = ds.topControl(FPageSheet);
	fmMain.form_name.value = ds.getFormName();
	fmMain.form_action.value = EAction.Insert;
	fmMain.action = "<jh:context path='/apl/logic/form/WebForm.wa?do=insert'/>";
	if(ps && ps.formLink){
		var pds = ds.topControl(MDataset);
		var attrs = new TAttributes();
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
	var fs = new TAttributes();
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
		fmMain.action = "<jh:context path='/apl/logic/form/WebForm.wa?do=delete'/>";
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
function _resize(){
	if(form){
		RConsole.find(FEventConsole).add(form, form.psRefresh);
	}
}
function doDetail(){
	var ouid = null;
	var over = null;
	var dc = RConsole.find(FFocusConsole).findClass(MDataset);
	if(RClass.isClass(dc, FForm)){
		ouid = dc.dsGet('ouid');
		over = dc.dsGet('over');
	}else if(RClass.isClass(dc, FTable)){
		var rs = dc.findSelRows();
		if(!rs.isEmpty()){
			var r = rs.get(0);
			ouid = r.ouid();
			over = r.over();
		}
	}
	if(ouid && over){
		var uri = "<jh:context path='/apl/logic/resource/WebResource.wa'/>?form_name={0}&ouid={1}&over={2}";
		uri = RStr.format(uri, dc.name, ouid, over);
		RHtml.popup(uri, 900, 600);
	}
}
function _onloadAll(){
   // 初始化表单
   MoJS.connect();
   RWindow.connect(window);
   var formName = $('#form_name').value;
   var xvalue = RXml.makeNode(xValue);
   var emode = REnum.encode(EMode, action)
   // Build form
   var form = RConsole.find(FFormConsole).createFromName(formName, $('#_id_form'));
   if(EMode.Insert == emode){
      var valueNode  = RXml.makeNode(xValue);
      form.doPrepare(valueNode.attributes());
   }else{
      if(RClass.isClass(form, FForm)){
         var r = getInsideRow(xvalue);
         form.loadValue(r);
      }else if(RClass.isClass(form, FTable)){
         form.loadDatasets(xvalue)
      }
   }
   top.document.title = form.label;
   form.show();
   form.psRefresh();
   form.psMode(emode);
   form.focus();
	// Listen events
	//var lsnConsole = RConsole.find(FListenerConsole);
	//lsnConsole.register(MDataset, EDataAction.EndUpdate, form, doBack);
	//lsnConsole.register(FTable, ETableAction.RowClick, form, doSelectRow);
	//lsnConsole.register(FTable, ETableAction.GoInsert, form, doInsert);
	//window.document.body.onresize = _resize;
// 如果有用户自定义的函数则加载自定义内容
   try{
      onloadAll();
   }catch(e){}
}
//----------------------------------------------------------
function _onload(){
   _start = new Date().getTime();
   RWindow.connect(window);
   RLoader.loadJs('mobj', 'workspace');
   RLoader.waitJs(new TInvoke(null, _onloadAll), 'mobj', 'workspace');
}
</SCRIPT>
<!-- Environment --------------------------------------------->
<XML ID="xEnvironment">
<jh:write source='&environment' format='text'/>
</XML>
<!-- Form ---------------------------------------------------->
<XML ID="xSearch"><jh:write source='&form_search' format='text'/></XML>
<XML ID="xParent"><jh:write source='&form_parent' format='text'/></XML>
<XML ID="xOrder"><jh:write source='&form_order' format='text'/></XML>
<XML ID="xValue"><jh:write source='&form_value' format='text'/></XML>
<!-- Body begin ---------------------------------------------->
<jh:body style='bodyMain' scroll='no' onload='_onload()'>
<jh:form>
<!-- Hidden begin -------------------------------------------->
<jh:hidden name='form_name' source='&page.form_name'/>
<jh:hidden name='form_action' source='&page.form_action'/>
<jh:hidden name='form_parent' source='&page.form_parent'/>
<jh:hidden name='form_search' source='&page.form_search'/>
<jh:hidden name='form_order' source='&page.form_order'/>
<jh:hidden name='form_value' source='&page.form_value'/>
<jh:hidden name='ouid'/>
<jh:hidden name='over'/>
<!-- Hidden end ---------------------------------------------->
<TABLE border='0' cellpadding='0' cellspacing='0' width='100%' height='100%' style='layout:fix'>
<TR><TD height='1'>
<!-- Toolbar begin ------------------------------------------->
<DIV id='_id_toolbar'></DIV>
<!-- Toolbar end --------------------------------------------->
</TD></TR>
<!-- Form begin ---------------------------------------------->
<TR><TD><DIV id='_id_form' style='padding:8;width:100%;height:100%;overflow:auto;'></DIV></TD></TR>
<!-- Form end ------------------------------------------------>
</TABLE>
</jh:form>
</jh:body>
<!-- Body end ------------------------------------------------>
</HTML>
