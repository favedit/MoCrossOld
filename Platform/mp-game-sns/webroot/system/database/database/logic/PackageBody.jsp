<%@ include file="/inc/page_begin.inc" %>

<jh:define item='[DB.PackageForm]' alias='package_form'/>
<jh:define item='&package_form.package_unit' alias='package_unit'/>

<HTML>
<HEAD>
<js:meta/>
<js:css/>
<js:js/>
<jh:title caption='Class View'/>
</HEAD>

<!-- Javascript Initialize ----------------------------------->
<SCRIPT language='javascript'>
function executeActionCopyToClipboard(){
   window.clipboardData.setData('Text', idSource.innerText);
   alert('Copy To Clipboard Success!');
}
</SCRIPT>

<!-- Toolbar Initialize ----------------------------------->
<jc:toolbar inFrame='frmBody' inForm='frmConsole' target='frmBody' name='top.frames.frmToolBar.oToolBar' action='refresh'>
   <jc:tbButton caption='txt|sys|Toolbar.Back' pageAction='/db/oracle/pkg/Info.wa' icon='sys.back'/>
   <jc:tbButton caption='txt|sys|Toolbar.Refresh' pageAction='/db/oracle/pkg/PackageHeader.wa' icon='sys.refresh'/>
   <jc:tbButton caption='txt|sys|Toolbar.CopyToClipboard' action='executeActionCopyToClipboard()' icon='sys.copy'/>
</jc:toolbar>

<!-- Body Initialize -------------------------------------->
<js:body styleClass='frmBody'>
<jh:form name='frmConsole'>

<TABLE width='100%' cellspacing='8' border='0'>
<TR><TD>
   <TABLE width='100%' bgcolor='#94B6FF' border='0' cellspacing='1' cellpadding='4'>
     <TR bgcolor='#EFF3FF'><TD>
       <jh:img src='/res/img/db/pkg.gif' align='absmiddle'/> <jh:item item='&package_unit.package_name'/>
     </TD></TR>
   </TABLE>
</TD></TR>
<TR><TD>
   <TABLE width='100%' bgcolor='#94B6FF' border='0' cellspacing='1' cellpadding='4'>
     <TR bgcolor='#FFFFF4'>
       <TD id='idSource' nowrap class='javaSource'><jh:item item='&package_form.source_html' format='off'/></TD>
     </TR>
   </TABLE>
</TD></TR>
</TABLE>

</jh:form>
</js:body>
</HTML>
<%@ include file="/inc/page_end.inc" %>
