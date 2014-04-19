<%@ page contentType='text/html;charset=utf-8' %>
<%@ include file='/apl/public.inc' %>
<HTML>
<HEAD>
<TITLE>eUIS</TITLE>
<SCRIPT>var pageStart = new Date().getTime();</SCRIPT>
<je:css/>
<je:js type='runtime'/>
<STYLE>
.bottomPic {
   background: url('<jh:img type='path' src='/login/loginBg3.jpg'/>'); 
}
</STYLE>
</HEAD>
<!-- Script - begin --------------------------------------->
<SCRIPT>
// ---------------------------------------------------------
function _onloadAll(){
   MoJS.initialize();
   RWindow.setEnable(true);
}
// ---------------------------------------------------------
function _onload(){
   _start = new Date().getTime();
   RWindow.connect(window);
   RLoader.loadJs('mobj', 'workspace');
   RLoader.waitJs(new TInvoke(null, _onloadAll), 'mobj', 'workspace');
}
</SCRIPT>
<!-- Script - end ----------------------------------------->
<!-- Body - begin ----------------------------------------->
<BODY style='margin:0; padding:0' onload='_onload()' disable>
<TABLE width="100%" height='100%' border="0" cellpadding="0" cellspacing="0">
<TR><TD>
<!--------------------------------------------------------->
<TABLE width="100%" height='100%' border="0" cellpadding="0" cellspacing="0">
  <TR>
    <TD align='center' valign='top'>
      <TABLE width="100%" height="100%" border="0" cellpadding="0" cellspacing="0" bgcolor='#FFFFFF'>
      <TR>
         <TD width="100%" align='center'>
            <IMG src='MasterSuccess.jpg' width='80' height='80'>
            <BR><BR>
            执行处理成功。
         </TD>
         <TD width="20"><jh:img icon='n'/></TD>
      </TR>
      </TABLE>
    </TD>
  </TR>
</TABLE>
<!--------------------------------------------------------->
</TD></TR>
</TABLE>
</BODY>
<!-- Body - end ------------------------------------------->
</HTML>
