<%@ page contentType='text/html;charset=utf-8' %>
<%@ include file='/apl/public.inc' %>
<HTML>
<HEAD>
<SCRIPT>var pageStart = new Date().getTime();</SCRIPT>
<js:css/>
</HEAD>
<!-- Script -------------------------------------------------->
<SCRIPT>
var mode = '<jh:write source="&page.action"/>';
//------------------------------------------------------------
function doDetail(form, pack){
   fmMain.action = '/apl/form/Show.wa';
   fmMain.form.value = form;
   fmMain.pack.value = pack;
   fmMain.page.value = 1;
   fmMain.submit();
}
function doPage(pageIndex){
   fmMain.page.value = pageIndex;
   fmMain.submit();
}
//------------------------------------------------------------
function _onload(){
}
</SCRIPT>
<!-- Body begin ---------------------------------------------->
<jh:body style='bodyMain' scroll='no' onload='_onload()'>
<jh:form>
<jh:hidden name='form' source='&page.form'/>
<jh:hidden name='database' source='&page.database'/>
<jh:hidden name='page' source='&page.page'/>
<jh:hidden name='pack' source='&page.pack'/>
<!-- Data begin ---------------------------------------------->

<TABLE border='0' cellpadding='0' cellspacing='0' width='100%' height='100%' style='layout:fix'>
<TR><TD height='24'>
<!-- Toolbar begin ------------------------------------------->
<DIV id='_id_toolbar'>Toolbar</DIV>
<!-- Toolbar end --------------------------------------------->
</TD></TR>
<!-- Form begin ---------------------------------------------->
<TR><TD><DIV id='_id_form' style='padding:8;width:100%;height:100%;overflow:auto;'>
   <jh:write source='&page.html' format='text'/>
</DIV></TD></TR>
<!-- Form end ------------------------------------------------>
</TABLE>
<!-- Data end ------------------------------------------------>
</jh:form>
</jh:body>
<!-- Body end ------------------------------------------------>
</HTML>
