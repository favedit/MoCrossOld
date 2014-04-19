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
function _onload(){
   _start = new Date().getTime();
   RWindow.connect(window);
   RLoader.loadJs('mobj');
}
</SCRIPT>
<!-- Body - begin ----------------------------------------->
<BODY style='margin:0; padding:0' onload='_onload()' disable style='filter:progid:DXImageTransform.Microsoft.Gradient(gradienttype=0, startcolorstr=#caeeee, endcolorstr=#1d8bb5);'>
<TABLE width="100%" height='100%' border='0' cellpadding='0' cellspacing='0'>
<TR>
<TD width='50%'><DIV></DIV></TD>
<TD width="1" bgcolor="#1C89B3"><jh:img icon='n'/></TD>
<TD>
<TABLE width="100%" height='100%' border='0' cellpadding='0' cellspacing='0' background="<jh:img type='path' src='/login/loginBg2.jpg'/>">
<TR><TD>
<!--------------------------------------------------------->
<TABLE width="933" height='100%' border='0' cellpadding='0' cellspacing='0'>
   <TR>
      <TD height='96' align='right' valign='top'>
         <TABLE width="100%" border="0" cellpadding='0' cellspacing='0'>
            <TR>
               <TD align='right' style='padding-top:4;padding-right:4;'><A href='<jh:context/>/apl/login/Login.wa'><IMG src='home.png' width='32' height='32' style='border:0'></A></TD>
            </TR>
         </TABLE>
      </TD>
   </TR>
   <TR>
      <TD align='center' valign='top'>
         <TABLE width="100%" height="100%" border='0' cellpadding='0' cellspacing='0' bgcolor='#FFFFFF'>
         <TR>
            <TD width="20"><jh:img icon='n'/></TD>
            <TD width="100%" valign='top'>
<!--------------------------------------------------------->
<A href='<jh:context/>/ars/res/WindowsXP-KB932823-v3-x86-CHS.zip'>WindowsXP-KB932823-v3-x86-CHS.zip</A>


<!--------------------------------------------------------->
            </TD>
            <TD width="20"><jh:img icon='n'/></TD>
         </TR>
         </TABLE>
      </TD>
   </TR>
</TABLE>
<!--------------------------------------------------------->
</TD></TR>
<TR><TD bgcolor='#DCEDF5' align='center'></TD></TR>
<TR><TD height='20' class='bottomPic'></TD></TR>
</TABLE>
</TD>
<TD width="1" bgcolor="#1C89B3"><jh:img icon='n'/></TD>
<TD width='50%'><DIV></DIV></TD>
</TR>
</TABLE>
</BODY>
<!-- Body - end ------------------------------------------->
</HTML>
