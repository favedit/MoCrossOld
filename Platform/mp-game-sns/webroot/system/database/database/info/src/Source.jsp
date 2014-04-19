<%@ include file="/inc/page_begin.inc" %>
<HTML>

<!-- Html Header Initialize ------------------------------->
<HEAD>
<js:meta/>
<js:css/>
<js:js/>
<jh:title caption='FavInfo'/>
</HEAD>

<!-- Javascript Initialize ----------------------------------->
<SCRIPT language='javascript'>
function executeActionCopyToClipboard(){
   window.clipboardData.setData('Text', idSource.innerText);
   alert('<jh:write source='txt|sys|Information.CopyToClipboardOk'/>');
}
function executeActionRefresh(){
   frmConsole.submit();
}
</SCRIPT>

<jh:define source='[DB.DatabaseForm]' alias='database_form'/>

<!-- Toolbar Initialize ----------------------------------->
<jc:toolbar inFrame='frmBody' inForm='frmConsole' target='frmBody' name='top.frames.frmToolBar.oToolBar' action='refresh'>
    <jc:tbButton caption='txt|sys|Toolbar.Back' pageAction='/db/oracle/db/src/Info.wa' icon='sys.back'/>
    <jc:tbButton caption='txt|sys|Toolbar.CopyToClipboard' action='executeActionCopyToClipboard()' icon='sys.copy'/>
    <jc:tbButton caption='txt|sys|Toolbar.Refresh' action='executeActionRefresh()' icon='sys.refresh'/>
</jc:toolbar>

<!-- Body Initialize -------------------------------------->
<js:body styleClass='frmBody'>
<jh:form name='frmConsole'>
<TABLE width='100%' cellspacing='8' border='0'>
<TR><TD>

<TABLE width='100%' bgcolor='#94B6FF' border='0' cellspacing='1' cellpadding='4'>
   <TR bgcolor='#FFFFFF'><TD id='idSource' class='javaSource'>
      <jh:source source='&database_form.source_html'/>
   </TD></TR>
</TABLE>

</TD></TR>
</TABLE>
</jh:form>
</js:body>
</HTML>
<%@ include file="/inc/page_end.inc" %>
