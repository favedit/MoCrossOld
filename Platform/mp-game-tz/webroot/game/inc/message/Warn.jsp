<%@ include file="/inc/page/begin.inc" %>
<jh:define form='[message]' alias='message_form'/>
<jh:define item='&message_form.warn_message' alias='warnings'/>
<jh:define item='&message_form.info_message' alias='informations'/>
<jh:default action='_onNext()'/>
<jh:keymap type='onkeydown' key='backspace' action='_onBack()' ignore='input,textarea'/>

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
function _onBack(){
   if(!m_bExecute){
      frmConsole.action = '<jh:context/><jh:item item='&message_form.uri_prior' format='false'/>';
      frmConsole.submit();
   }
   m_bExecute = true;
}
function _onNext(){
   if(!m_bExecute){
      //frmConsole.action = '<jh:context/><jh:item item='&message_form.uri_next' format='false'/>';
      frmConsole.action = '';
      frmConsole.submit();
   }
   m_bExecute = true;
}
</SCRIPT>


<!-- Toolbar Initialize ----------------------------------->
<jc:toolbar name='top.frames.frmToolbar.oToolbar' inFrame='frmWork' inForm='frmConsole' target='frmWork' action='refresh'>
   <jc:tbButton caption='trs:sys|toolbar.back|Back' action='_onBack()' icon='sys.back'/>
   <jc:tbButton caption='trs:sys|toolbar.next|Next' action='_onNext()' icon='sys.next'/>
</jc:toolbar>

<SCRIPT language='javascript'>
function goUrl(sUrl){
   frmConsole.action = sUrl;
   frmConsole.submit();
}
</SCRIPT>

<!-- Body Initialize -------------------------------------->
<js:body styleClass='frmWork'>
<jh:form name='frmConsole'>
<jh:edit type='hidden' item='&message_form.check_flag' value='Y'/>

<TABLE width='100%' cellspacing='8' border='0'>
<TR><TD>
   <TABLE width='100%' border='0' cellspacing='1' cellpadding='4'>
      <TR><TD align='center' valign='middle'>
         <jh:img src='/res/img/sys/msg/lwarn.gif' align='absmiddle'/><jh:img src='/res/img/n.gif' width='8'/><jh:write item='txt|sys|Message.Warning.Title'/>
      </TD></TR>
   </TABLE>
</TD></TR>

<TR><TD>
   <TABLE width='100%' bgcolor='#94B6FF' border='0' cellspacing='1' cellpadding='0'>
      <TR bgcolor='#FFFFFF'><TD>

<TABLE width='100%' border='0' cellspacing='4' cellpadding='0'>
<jh:loop item='&warnings' alias='warning'>
<TR>
    <TD width='20px'><jh:img src='/res/img/sys/msg/warn.gif' align='absmiddle'/></TD>
    <TD nowrap><FONT color='blue'><jh:item item='&warning.message'/></FONT></TD>
</TR>
</jh:loop>

<jh:loop item='&informations' alias='information'>
<TR>
    <TD width='20px'><jh:img src='/res/img/sys/msg/info.gif' align='absmiddle'/></TD>
    <TD nowrap><jh:item item='&information.message'/></TD>
</TR>
</jh:loop>
</TABLE>

      </TD></TR>
   </TABLE>
</TD></TR>

<TR><TD>
   <TABLE width='100%' border='0' cellspacing='0' cellpadding='4'>
      <TR>
         <TD width='120px'>
            <jh:notEmpty item='&message_form.uri_next'>
               <jc:tbButton inFrame='frmBody' inForm='frmConsole' target='frmBody' caption='txt|sys|toolbar.back' action='_onBack()' icon='/res/img/sys/back.gif'/>
            </jh:notEmpty>
         </TD>
         <TD>
         </TD>
         <TD width='120px' align='right'>
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
<%@ include file="/inc/page/end.inc" %>
