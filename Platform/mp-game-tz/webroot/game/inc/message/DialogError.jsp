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
<jh:define item='&message_form.error_message_info' alias='error_message_info'/>
<jh:default action='_onBack()'/>

<SCRIPT language='javascript'>
function _onBack(){
   frmConsole.wa = "<jh:item item='&message_form.uri_prior'/>";
   frmConsole.submit();
}
</SCRIPT>

<!-- Body Initialize -------------------------------------->
<js:body styleClass='frmBody'>
<jh:form name='frmConsole'>
<jh:edit type='hidden' item='&message_form.check_flag' value='CHECKED@[MessageConstants]'/>

<TABLE width='100%' cellspacing='8' border='0'>
<TR><TD>
   <TABLE width='100%' border='0' cellspacing='1' cellpadding='4'>
      <TR><TD align='center' valign='middle'>
         <jh:img src='/res/img/sys/msg/lerror.gif' align='absmiddle'/><jh:img src='/res/img/n.gif' width='8'/><jh:write item='txt|sys|Message.Error.Title'/>
      </TD></TR>
   </TABLE>
</TD></TR>

<TR><TD>
   <TABLE width='100%' bgcolor='#94B6FF' border='0' cellspacing='1' cellpadding='0'>
      <TR bgcolor='#FFFFFF'><TD>
<TABLE width='100%' border='0' cellspacing='4' cellpadding='0'>

<TR>
    <TD width='20px'><jh:img src='/res/img/sys/msg/error.gif' align='absmiddle'/></TD>
    <TD><FONT color='red'><jh:item item='&error_message_info.message'/></FONT></TD>
</TR>

</TABLE>
      </TD></TR>
   </TABLE>
</TD></TR>

<TR><TD>
   <TABLE width='100%' border='0' cellspacing='0' cellpadding='4'>
      <TR>
         <TD align='center'>
            <jh:notEmpty item='&message_form.uri_prior'>
               <jc:tbButton inFrame='frmBody' inForm='frmConsole' target='frmBody' caption='txt|sys|toolbar.back' action='_onBack()' icon='/res/img/sys/back.gif'/>
            </jh:notEmpty>
         </TD>
   </TABLE>
</TD></TR>

</TABLE>

</jh:form>
</js:body>
</HTML>
<%@ include file="/inc/page_end.inc" %>
