<%@ include file="/inc/page/begin.inc" %>

<HTML>

<!-- Html Header Initialize ------------------------------->
<HEAD>
<js:meta/>
<js:css/>
<js:js/>
<jh:title caption='FavInfo'/>
</HEAD>

<jh:default action='doBack()'/>
<jh:keymap type='onkeydown' key='backspace' action='doBack()'/>

<SCRIPT language='javascript'>
var m_bExecute = false;
function doBack(){
   if(!m_bExecute){
      m_bExecute = true;
      frmConsole.action = '<jc:history action='prior' level='2' params='fa=r'/>';
      frmConsole.submit();
   }
}
</SCRIPT>

<!-- Toolbar Initialize ----------------------------------->
<jc:toolbar name='top.frames.frmToolbar.oToolbar' inFrame='frmWork' inForm='frmConsole' target='frmWork' action='refresh'>
   <jc:tbButton caption='trs:sys|button.back|Back' action='doBack()' icon='sys.back'/>
</jc:toolbar>

<!-- Body Initialize -------------------------------------->
<js:body styleClass='frmWork'>
<js:form name='frmConsole'>
<TABLE width='100%' cellspacing='8' border='0'>
<TR><TD>
   <TABLE width='100%' border='0' cellspacing='1' cellpadding='4'>
      <TR><TD align='center' valign='middle'>
         <jh:img src='/res/img/sys/msg/lmsg.gif' align='absmiddle'/><jh:img src='/res/img/n.gif' width='8'/><jh:text value='trs:sys|title.message|Message'/>
      </TD></TR>
   </TABLE>
</TD></TR>
<TR><TD>
   <TABLE width='100%' bgcolor='#94B6FF' border='0' cellspacing='1' cellpadding='0'>
      <TR bgcolor='#FFFFFF'><TD>
         <TABLE width='100%' border='0' cellspacing='4' cellpadding='0'>
            <TR>
               <TD nowrap><jh:text value='trs:sys|info.saveok|Save Ok'/></TD>
            </TR>
         </TABLE>
      </TD></TR>
   </TABLE>
</TD></TR>
<TR><TD>
   <TABLE width='100%' border='0' cellspacing='0' cellpadding='4'>
      <TR>
         <TD align='center'>
            <jc:tbButton inFrame='frmWork' inForm='frmConsole' target='frmWork' caption='trs:sys|button.back|Back' action='doBack()' icon='/res/img/sys/back.gif'/>
         </TD>
   </TABLE>
</TD></TR>
</TABLE>
</js:form>
</js:body>
</HTML>
<%@ include file="/inc/page/end.inc" %>
