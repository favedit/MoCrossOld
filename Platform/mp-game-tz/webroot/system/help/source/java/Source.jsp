<%@ include file="/inc/page/begin.inc" %>
<jh:define form='sys.source' alias='source_form'/>

<HTML>

<!-- Html Header Initialize ------------------------------->
<HEAD>
<js:meta/>
<js:css/>
<js:js/>
<jh:title caption='Class Source'/>
</HEAD>

<!-- Javascript Initialize ----------------------------------->
<SCRIPT language='javascript'>
function doClipboard(){
   window.clipboardData.setData('Text', idSource.innerText);
   alert('<jh:write item='txt|sys|info.copytoclipboardok'/>');
}
</SCRIPT>

<!-- Toolbar Initialize ----------------------------------->
<jc:toolbar name='top.frames.frmToolbar.oToolbar' inFrame='frmWork' inForm='frmConsole' target='frmWork' action='refresh'>
   <jc:tbButton caption='trs:sys|button.copytoclipboard|Copy To Clipboard' action='doClipboard()' icon='sys.paste'/>
</jc:toolbar>

<!-- Body Initialize -------------------------------------->
<js:body styleClass='frmWork'>
<jh:form name='frmConsole'>

<TABLE width='100%' cellspacing='8' border='0'>
<TR><TD>
   <TABLE width='100%' bgcolor='#94B6FF' border='0' cellspacing='1' cellpadding='4'>
      <TR bgcolor='#EFF3FF'><TD>
         <jh:item item='&source_form.class_inherit_html' format='false'/>
      </TD></TR>
   </TABLE>
</TD></TR>
<TR><TD>
   <TABLE width='100%' bgcolor='#94B6FF' border='0' cellspacing='1' cellpadding='4'>
      <TR bgcolor='#FFFFF4'><TD id='idSource'>
         <jh:item item='&source_form.source_html' format='false'/>
      </TD></TR>
   </TABLE>
</TD></TR>
</TABLE>

</jh:form>
</js:body>
</HTML>
<%@ include file="/inc/page/end.inc" %>
