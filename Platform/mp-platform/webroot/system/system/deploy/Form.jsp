<%@ include file='/apl/public.inc' %>
<jh:define source='&page.webform' alias='form'/>
<jh:define source='&page.webformfields' alias='fields'/>

<HTML>
<HEAD>
<TITLE>Welcome</TITLE>
<js:css/>
<js:js type='runtime.client'/>
</HEAD>
<!------------------------------------------------------------>
<STYLE>
.toolbarButton {color:#666666; background-color:#DDDDDD;
      padding-top:0; padding-left:16; padding-bottom:0; padding-right:16; }
</STYLE>
<SCRIPT language='javascript'>
function selConsole(name){
   fmMain.action = '?do=showConsole&name=' + name;
   fmMain.target = 'frmBody';
   fmMain.submit();
}
function _onloadAll(){
	MoJS.connect();
   var start = new Date().getTime();
	RWindow.connect(window);
	var xform = RXml.makeNode(xForm);
	var emode = REnum.encode(EMode, 'Update')
	// Build toolbar
	toolbar = RToolBar.fromNode(xform, _id_toolbar, true);
	if(!toolbar){
		toolbar = RControl.fromXml(xToolBar, _id_toolbar);
	}
	toolbar.psMode(emode);	
	// Build form
    form = RControl.fromNode(xform, _id_form);
  	form.psMode(emode);
	top.document.title = form.label;
	form.psRefresh();
	form.focus();
}
//----------------------------------------------------------
function _onload(){
   RWindow.connect(window);
   RLoader.loadJs('mobj');
   RLoader.waitJs(new TInvoke(null, _onloadAll), 'mobj');
}
</SCRIPT>
<!------------------------------------------------------------>
<jc:form name='xForm' source='&page.formName'/>
<!------------------------------------------------------------>
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
