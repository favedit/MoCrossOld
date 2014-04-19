<%@ include file="/inc/page_begin.inc" %>
<%@ page import="jfa.lib.app.web.FWebContext" %>
<%@ page import="jfa.lib.app.web.IWebGlobals" %>
<%@ page import="jfa.console.com.form.MessageForm" %>
<%@ page import="jfa.lib.app.console.FMessageConsole" %>
<%@ page import="jfa.lib.app.msg.FValidatorMessage" %>
<%@ page import="jfa.lib.app.web.util.FWebHTML" %>
<%@ page import="jfa.lib.com.lang.FString" %>
<%@ page import="jfa.lib.com.container.FList" %>
<HTML>

<!-- Html Header Initialize ------------------------------->
<HEAD>
<js:meta/>
<js:css/>
<js:js/>
<jh:title caption='txt|sys|Message.Validator.WinTitle'/>
</HEAD>

<jh:default action='window.close();'/>

<!-- Body Initialize -------------------------------------->
<jh:body styleClass='frmValidator' scroll='no'>

<FORM name='__frmError'>
<TABLE border='0' cellspacing='0' cellpadding='0' width='300' height='0'>
<TR>
<TD width='20' valign='top'><TABLE width='20' border='0' cellspacing='0'><TR><TD>&nbsp;</TD></TR></TABLE></TD>
<TD align='center' valign='top'>
<TABLE border='0' cellspacing='0' cellpadding='0' width='100%'>
<TR><TD height='15'>&nbsp;</TD></TR></TABLE>
<TABLE border='0' cellspacing='0' cellpadding='0' width='100%'>
<%
String sDump = "";
FWebContext oContext = (FWebContext)request.getAttribute(IWebGlobals.INSTANCE_WEB_CONTEXT);
if(oContext != null){
   MessageForm oMessageForm = (MessageForm) oContext.getForm(MessageForm.class);
   FList oValidatorList = oMessageForm.getValidateMessage();
   if(oValidatorList != null){
      FValidatorMessage oValidatorMessage = null;
      int nCount = oValidatorList.getCount();
      for(int m = 0; m < nCount; m++){
         oValidatorMessage = (FValidatorMessage)oValidatorList.getItem(m);
         out.print("<tr><td nowrap>&nbsp;&nbsp;");
         if(!FString.isEmpty(oValidatorMessage.getMessage())){
            String[] arMsgLine = FString.split(oValidatorMessage.getDescription(), 50);
            int nMsgLineCount = arMsgLine.length;
            for(int n = 0; n < nMsgLineCount; n++){
               out.print(FWebHTML.toHtml(arMsgLine[n]) + "<BR>");
            }
         }
         out.print("</td></tr>");
      }
   }
   oMessageForm.resetValidateMessage();
   oMessageForm.commit();
}
%>
</TABLE>
<TABLE border='0' cellspacing='0' cellpadding='0' width='100%'>
<TR align='center'>
<TD height='20'>
<HR style='height:1;color:#999999;'>
<A id='btnClose' onclick='window.close();' style='cursor:hand'><jh:img src='/res/img/sys/delete.gif' align='absmiddle'/> <jh:write item='txt|sys|button.close'/></A>
</TD>
</TR>
<TR><TD height='15'>&nbsp;</TD></TR>
</TABLE>
</TD>
<TD width='20'><TABLE width='20' border='0' cellspacing='0'><TR><TD>&nbsp;</TD></TR></TABLE></TD>
</TR>
</TABLE>

</jh:body>
</HTML>
<%@ include file="/inc/page_end.inc" %>
