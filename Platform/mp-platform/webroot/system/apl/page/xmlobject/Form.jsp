<%@ page contentType='text/html;charset=utf-8' %>
<%@ include file='/apl/public.inc' %>
<HTML>
<HEAD>
<META http-equiv="Content-Type" content="text/html; charset=utf-8" />
<SCRIPT>var pageStart = new Date().getTime();</SCRIPT>
<js:css/>
<js:js type='runtime.client'/>
</HEAD>
<!-- Script -------------------------------------------------->
<SCRIPT>
var mode = '<jh:write source="&page.pageAction"/>';
var form = null;
var toolbar = null;
//----------------------------------------------------------
function doRefresh(){
   fmMain.action = '';
   fmMain.submit();
}
//----------------------------------------------------------
function doInsert(){
   form.doAction('insertAction');
}
//----------------------------------------------------------
function doSave(){
   if(RWindow.inDisable){
      return;
   }
   var emode = REnum.encode(EMode, mode)
   if(EMode.Insert == emode){
      form.doAction('insertAction');
   }else if(EMode.Update == emode){
      form.doAction('updateAction');
   }else{
      alert('Unknown action [' + mode + ']');
   }
}
//----------------------------------------------------------
function doDelete(){
   form.doAction('deleteAction');
}
//----------------------------------------------------------
function _onloadAll(){
   MoJS.connect();
   //
   var start = new Date().getTime();
   RWindow.connect(window);
   // RConsole.find(FLoggerConsole).connect();
   var xform = RXml.makeNode(xForm);
   var xvalue = RXml.makeNode(xValue);
   var emode = REnum.encode(EMode, mode)
   // Build toolbar
   toolbar = RToolBar.fromNode(xform, _id_toolbar, true);
   if(!toolbar){
      toolbar = RControl.fromXml(xToolBar, _id_toolbar);
   }
   toolbar.psMode(emode);  
   // Build form
   form = RControl.fromNode(xform, _id_form);
   form.psMode(emode);
   if(EMode.Insert == emode){
      var valueNode  = RXml.makeNode(xValue);
      form.doPrepare(valueNode.attributes());
   }else{
      if(RClass.isClass(form, FForm)){
         form.loadValue(xvalue, EStore.DataNvl);
      }else if(RClass.isClass(form, FTable)){
         form.loadDatasets(xvalue)
      }
   }
   top.document.title = form.label;
   form.psRefresh();
   form.focus();
   // show
   var end = new Date().getTime();
   top.document.title = form.label + ' ( Show page in ' + (end - pageStart) + 'ms. ' + 
      'Build form: ' + (end - start) + 'ms )';
}
//----------------------------------------------------------
function _onload(){
   RWindow.connect(window);
   RLoader.loadJs('mobj');
   RLoader.waitJs(new TInvoke(null, _onloadAll), 'mobj');
}
</SCRIPT>
<!-- Environment --------------------------------------------->
<SCRIPT id="xEnvironment" type="application/xml"><jh:write source='&page.environment' format='text'/></SCRIPT>
<!-- Form ---------------------------------------------------->
<jc:form name='xForm' source='&page.formName'/>
<!-- Value --------------------------------------------------->
<SCRIPT id="xValue" type="application/xml"><jh:write source='&page.formValue' format='text'/></SCRIPT>
<!-- Body begin ---------------------------------------------->
<jh:body style='bodyMain' scroll='no' onload='_onload()'>
<jh:form>
<jh:hidden name='form_name' source='&page.formName'/>
<jh:hidden name='form_service' source='&page.formService'/>
<jh:hidden name='sel_type' source='&page.selectType'/>
<jh:hidden name='sel_collection' source='&page.selectCollection'/>
<jh:hidden name='sel_component' source='&page.selectComponent'/>
<!-- Data begin ---------------------------------------------->
<TABLE border='0' cellpadding='0' cellspacing='0' width='100%' height='100%' style='layout:fix'>
<TR><TD height='24'>
<!-- Toolbar begin ------------------------------------------->
<DIV id='_id_toolbar'></DIV>
<!-- Toolbar end --------------------------------------------->
</TD></TR>
<!-- Form begin ---------------------------------------------->
<TR><TD><DIV id='_id_form' style='padding:8;width:100%;height:100%;overflow:auto;'></DIV></TD></TR>
<!-- Form end ------------------------------------------------>
</TABLE>
<!-- Data end ------------------------------------------------>
</jh:form>
</jh:body>
<!-- Body end ------------------------------------------------>
</HTML>
