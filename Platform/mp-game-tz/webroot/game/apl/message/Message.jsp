<%@ page contentType='text/html;charset=utf-8' %>
<%@ include file='/apl/public.inc' %>

<HTML>

<!-- Html Header Initialize ------------------------------->
<HEAD>
<js:css/>
<jh:js/>
<jh:title caption='Error'/>
</HEAD>

<SCRIPT language='javascript'>
var m_bExecute = false;
function _onBack(){
   if(!m_bExecute){
      frmConsole.wa = '';
      frmConsole.submit();
   }
   m_bExecute = true;
}
function displayDetails(){
   id_details.style.display = (id_details.style.display == 'none') ? 'block' : 'none' ;
   id_details_head.style.display = (id_details.style.display == 'none') ? 'block' : 'none' ;
}
</SCRIPT>

<!-- Body Initialize -------------------------------------->
<jh:body style='bodyMain'>
<jh:form name='frmConsole'>

<TABLE width='100%' cellspacing='8' border='0'>
<TR><TD>
   <TABLE width='100%' border='0' cellspacing='1' cellpadding='4'>
      <TR><TD align='center' valign='middle'>
         <jh:img src='/ars/icon/sys/msg/lerror.gif' align='absmiddle'/>
         <jh:img src='/ars/icon/n.gif' width='8'/>
         <jh:write source='trs:sys|message.error.title|Error'/>
         <BR><BR>
         <FONT color='red'><B><jh:write source='trs:sys|message.error.info|Information'/></B></FONT>
      </TD></TR>
   </TABLE>
</TD></TR>

<TR><TD>

<TABLE width='100%' bgcolor='#94B6FF' border='0' cellspacing='1' cellpadding='0'>
<TR bgcolor='#FFFFFF'><TD>
   <TABLE width='100%' border='0' cellspacing='4' cellpadding='0'>
   <TR>
      <TD width='20px'><jh:img src='/ars/icon/sys/msg/error.gif' align='absmiddle'/></TD>
      <TD><FONT color='red'><jh:write source='&message.code'/></FONT></TD>
   </TR>
   </TABLE>
</TD></TR>
</TABLE><BR>

<TABLE width='100%' bgcolor='#94B6FF' border='0' cellspacing='1' cellpadding='0'>
<TR bgcolor='#FFFFFF'><TD>
   <TABLE width='100%' border='0' cellspacing='4' cellpadding='0'>
   <TR>
      <TD width='20px' valign='top'><jh:img src='/ars/icon/sys/msg/info.gif' align='absmiddle' style='cursor:hand'/></TD>
      <TD nowrap>
         <FONT color='red'><jh:write source='&message.description'/></FONT>
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
         </TD>
   </TABLE>
</TD></TR>

</TABLE>

</jh:form>
</jh:body>
</HTML>
