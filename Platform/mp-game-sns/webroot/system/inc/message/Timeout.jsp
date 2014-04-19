<%@ include file="/inc/page/begin.inc" %>
<HTML>

<!-- Html Header Initialize ------------------------------->
<HEAD>
<js:meta/>
<js:css/>
<js:js/>
<jh:title caption='FavInfo'/>
</HEAD>

<SCRIPT language='javascript'>
var m_bExecute = false;
function relogin(){
   frmConsole.action = '<jh:context/>/home/Login.wa';
   frmConsole.target = '_top'
   frmConsole.submit();
}
</SCRIPT>


<!-- Toolbar Initialize ----------------------------------->
<jc:toolbar name='top.frames.frmToolbar.oToolbar' inFrame='frmWork' inForm='frmConsole' target='frmWork' action='refresh'>
   <jc:tbButton caption='trs:sys|toolbar.relogin|Relogin' action='relogin()' icon='sys.back'/>
</jc:toolbar>

<!-- Body Initialize -------------------------------------->
<js:body styleClass='frmWork'>
<jh:form name='frmConsole'>

<TABLE width='100%' cellspacing='8' border='0'>
<TR><TD>
   <TABLE width='100%' border='0' cellspacing='1' cellpadding='4'>
      <TR><TD align='center' valign='middle'>
         <jh:img src='/res/img/sys/msg/lerror.gif' align='absmiddle'/>
         <jh:img src='/res/img/n.gif' width='8'/>
         <jh:text value='trs:sys|message.timeout|Session timeout'/>
      </TD></TR>
   </TABLE>
</TD></TR>

<TR><TD>
   <TABLE width='100%' border='0' cellspacing='0' cellpadding='4'>
      <TR>
         <TD align='center'>
            <jc:tbButton caption='trs:sys|toolbar.relogin|Relogin' action='relogin()' icon='/res/img/sys/back.gif'/>
         </TD>
   </TABLE>
</TD></TR>

</TABLE>

</jh:form>
</js:body>
</HTML>
<%@ include file="/inc/page/end.inc" %>
