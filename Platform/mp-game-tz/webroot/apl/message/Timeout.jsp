<%@ page contentType='text/html;charset=utf-8' %>
<%@ include file='/apl/public.inc' %>

<HTML>
<HEAD>
<!------------------------------------------------------------>
<SCRIPT language='javascript'>
function _onload(){
   fmLogin.target = '_top';
   fmLogin.action = "<jh:context path='/apl/message/TimeoutShow.wp'/>";
   fmLogin.submit();
}
</SCRIPT>
<!------------------------------------------------------------>
<BODY onload='_onload()'>
<jh:form name='fmLogin' method='post'/>
</BODY>

</HTML>
