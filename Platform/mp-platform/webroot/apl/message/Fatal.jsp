<%@ page contentType='text/html;charset=utf-8' %>
<%@ include file='/apl/public.inc' %>

<HTML>

<!-- Html Header Initialize ------------------------------->
<HEAD>
<je:css/>
<je:js type='runtime'/>
<jh:title caption='系统错误'/>
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
//----------------------------------------------------------
function displayDetails(){
   id_details.style.display = (id_details.style.display == 'none') ? 'block' : 'none' ;
   id_details_head.style.display = (id_details.style.display == 'none') ? 'block' : 'none' ;
}
//----------------------------------------------------------
function _onloadAll(){
   MoJS.initialize();
}
//----------------------------------------------------------
function _onload(){
   RWindow.connect(window);
   RLoader.loadJs('mobj');
   RLoader.waitJs(new TInvoke(null, _onloadAll), 'mobj');
}
</SCRIPT>

<!-- Body Initialize -------------------------------------->
<jh:body scroll='no' onload='_onload()'>
<jh:form name='frmConsole'>

<TABLE width='100%' cellspacing='8' border='0'>
<TR><TD>
   <TABLE width='100%' border='0' cellspacing='1' cellpadding='4'>
      <TR><TD align='center' valign='middle'>
         <jh:img src='/ars/icon/sys/msg/lerror.gif' align='absmiddle'/>
         <jh:img src='/ars/icon/n.gif' width='8'/>
         <FONT color='red'><B>系统错误</B></FONT>
         <BR><BR>
         <FONT color='red'>由于以下原因使得本次操作产生了错误，如果您对该错误存在疑问，请联系管理员。</FONT>
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
