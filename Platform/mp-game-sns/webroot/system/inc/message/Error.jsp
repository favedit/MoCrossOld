<%@ include file="/inc/page/begin.inc" %>
<jh:define form='[message]' alias='message_form'/>
<jh:default action='_onBack()'/>

<HTML>

<!-- Html Header Initialize ------------------------------->
<HEAD>
<js:meta/>
<js:css/>
<js:js/>
<jh:title caption='Error'/>
</HEAD>

<SCRIPT language='javascript'>
var m_bExecute = false;
function _onBack(){
   if(!m_bExecute){
      frmConsole.wa = '<jh:context/><jh:item item='&message_form.uri_prior' format='false'/>';
      frmConsole.submit();
   }
   m_bExecute = true;
}
function displayDetails(){
   id_details.style.display = (id_details.style.display == 'none') ? 'block' : 'none' ;
   id_details_head.style.display = (id_details.style.display == 'none') ? 'block' : 'none' ;
}
</SCRIPT>

<jc:toolbar name='top.frames.frmToolbar.oToolbar' inFrame='frmWork' inForm='frmConsole' target='frmWork' action='refresh'/>

<!-- Body Initialize -------------------------------------->
<js:body styleClass='frmWork'>
<jh:form name='frmConsole'>

<TABLE width='100%' cellspacing='8' border='0'>
<TR><TD>
   <TABLE width='100%' border='0' cellspacing='1' cellpadding='4'>
      <TR><TD align='center' valign='middle'>
         <jh:img src='/res/img/sys/msg/lerror.gif' align='absmiddle'/><jh:img src='/res/img/n.gif' width='8'/><jh:text value='trs:sys|message.error.title|Error'/>
         <BR><BR>
         <FONT color='red'><B><jh:text value='trs:sys|message.error.info|Information'/></B></FONT>
      </TD></TR>
   </TABLE>
</TD></TR>

<TR><TD>

<TABLE width='100%' bgcolor='#94B6FF' border='0' cellspacing='1' cellpadding='0'>
<TR bgcolor='#FFFFFF'><TD>
   <TABLE width='100%' border='0' cellspacing='4' cellpadding='0'>
   <TR>
      <TD width='20px'><jh:img src='/res/img/sys/msg/error.gif' align='absmiddle'/></TD>
      <TD><FONT color='red'><jh:item item='&message_form.message'/></FONT></TD>
   </TR>
   </TABLE>
</TD></TR>
</TABLE><BR>

<TABLE width='100%' bgcolor='#94B6FF' border='0' cellspacing='1' cellpadding='0'>
<TR bgcolor='#FFFFFF'><TD>
   <TABLE width='100%' border='0' cellspacing='4' cellpadding='0'>
   <TR>
      <TD width='20px' valign='top'><jh:img src='/res/img/sys/msg/info.gif' align='absmiddle' style='cursor:hand'/></TD>
      <TD nowrap onclick='displayDetails()' style='cursor:hand'>
         <FONT color='red' id='id_details_head' style='display:block'><jh:write item='txt|sys|message.error.details'/></FONT>
         <FONT color='red' id='id_details' style='display:none'><jh:item item='&message_form.description'/></FONT>
      </TD>
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
               <jc:tbButton inFrame='frmBody' inForm='frmConsole' target='frmBody' caption='trs:sys|button.back|Back' action='_onBack()' icon='/res/img/sys/back.gif'/>
            </jh:notEmpty>
         </TD>
   </TABLE>
</TD></TR>

</TABLE>

</jh:form>
</js:body>
</HTML>
<%@ include file="/inc/page/end.inc" %>
