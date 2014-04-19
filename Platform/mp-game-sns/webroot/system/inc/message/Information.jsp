<%@ include file="/inc/page_begin.inc" %>
<HTML>

<!-- Html Header Initialize ------------------------------->
<HEAD>
<js:meta/>
<js:css/>
<js:js/>
<jh:title caption='FavInfo'/>
</HEAD>

<jh:define item='[MessageForm]' alias='message_form'/>
<jh:define item='&message_form.information_message' alias='information_message'/>
<jh:default action='_onNext()'/>
<jh:keymap type='onkeydown' key='backspace' action='_onBack()' ignore='input,textarea'/>

<SCRIPT language='javascript'>
var m_bExecute = false;
function _onBack(){
   if(!m_bExecute){
      frmConsole.wa = '<jh:context/><jh:item item='&message_form.uri_prior' format='false'/>';
      frmConsole.submit();
   }
   m_bExecute = true;
}
function _onNext(){
   if(!m_bExecute){
      frmConsole.wa = '<jh:context/><jh:item item='&message_form.uri_next' format='false'/>';
      frmConsole.submit();
   }
   m_bExecute = true;
}
</SCRIPT>

<!-- Toolbar Initialize ----------------------------------->
<jc:toolbar inFrame='frmBody' inForm='frmConsole' target='frmBody' name='top.frames.frmToolBar.oToolBar' action='refresh'>
   <jc:tbButton caption='txt|sys|toolbar.next' action='_onNext()' icon='sys.next'/>
</jc:toolbar>

<!-- Body Initialize -------------------------------------->
<js:body styleClass='frmBody'>
<jh:form name='frmConsole'>
<jh:edit type='hidden' item='&message_form.check_flag' value='CHECKED@[MessageConstants]'/>

<TABLE width='100%' cellspacing='8' border='0'>
<TR><TD>
   <TABLE width='100%' border='0' cellspacing='1' cellpadding='4'>
      <TR><TD align='center' valign='middle'>
         <jh:img src='/res/img/sys/msg/linfo.gif' align='absmiddle'/><jh:img src='/res/img/n.gif' width='8'/><jh:write item='txt|sys|Message.Information.Title'/>
      </TD></TR>
   </TABLE>
</TD></TR>

<TR><TD>
   <TABLE width='100%' bgcolor='#94B6FF' border='0' cellspacing='1' cellpadding='0'>
      <TR bgcolor='#FFFFFF'><TD>
<TABLE width='100%' border='0' cellspacing='4' cellpadding='0'>

<jh:notEmpty item='&information_message'>
<jh:loop item='&information_message' alias='unit'>
<TR>
    <TD width='20px'><jh:img src='/res/img/sys/msg/info.gif' align='absmiddle'/></TD>
    <TD nowrap><jh:item item='&unit.message'/></TD>
</TR>
</jh:loop>
</jh:notEmpty>

</TABLE>
      </TD></TR>
   </TABLE>
</TD></TR>

<TR><TD>
   <TABLE width='100%' border='0' cellspacing='0' cellpadding='4'>
      <TR>
         <TD align='center'>
            <jh:notEmpty item='&message_form.uri_next'>
               <jc:tbButton inFrame='frmBody' inForm='frmConsole' target='frmBody' caption='txt|sys|toolbar.next' action='_onNext()' icon='/res/img/sys/next.gif'/>
            </jh:notEmpty>
         </TD>
   </TABLE>
</TD></TR>

</TABLE>

</jh:form>
</js:body>
</HTML>
<%@ include file="/inc/page_end.inc" %>
