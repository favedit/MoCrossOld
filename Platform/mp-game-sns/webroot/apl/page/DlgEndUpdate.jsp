<%@ include file="/inc/page_begin.inc" %>
<HTML>

<!-- Html Header Initialize ------------------------------->
<HEAD>
<js:meta/>
<js:css/>
<js:js/>
<jh:title caption='FavInfo'/>
</HEAD>

<jh:default action='_onClose()'/>

<SCRIPT language='javascript'>
function _onClose(){
   window.close();
}
</SCRIPT>

<!-- Body Initialize -------------------------------------->
<js:body styleClass='frmBody' scroll='no'>
<jh:form name='frmConsole'>
<TABLE width='100%' cellspacing='8' border='0'>
<TR><TD>
   <TABLE width='100%' border='0' cellspacing='1' cellpadding='4'>
      <TR><TD align='center' valign='middle'>
         <jh:img src='/res/img/sys/msg/lmsg.gif' align='absmiddle'/><jh:img src='/res/img/n.gif' width='8'/><jh:write source='txt|sys|message.title'/>
      </TD></TR>
   </TABLE>
</TD></TR>
<TR><TD>
   <TABLE width='100%' bgcolor='#94B6FF' border='0' cellspacing='1' cellpadding='0'>
      <TR bgcolor='#FFFFFF'><TD>
         <TABLE width='100%' border='0' cellspacing='4' cellpadding='0'>
            <TR>
               <TD nowrap><jh:write source='txt|sys|information.saveok'/></TD>
            </TR>
         </TABLE>
      </TD></TR>
   </TABLE>
</TD></TR>
<TR><TD>
   <TABLE width='100%' border='0' cellspacing='0' cellpadding='4'>
      <TR>
         <TD align='center'>
            <jc:tbButton caption='txt|sys|toolbar.close' action='_onClose()' icon='/res/img/sys/exit.gif'/>
         </TD>
   </TABLE>
</TD></TR>
</TABLE>
</jh:form>
</js:body>
</HTML>
<%@ include file="/inc/page_end.inc" %>
