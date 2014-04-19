<%@ include file='/apl/public.inc' %>
<jh:define item='&page.ConnectionConsoles' alias='consoles'/>

<HTML>
<HEAD>
<TITLE>Welcome</TITLE>
<jh:css/>
<jh:js type='client'/>
</HEAD>
<!------------------------------------------------------------>
<STYLE>
.toolbarButton {color:#666666; background-color:#DDDDDD;
      padding-top:0; padding-left:16; padding-bottom:0; padding-right:16; }
</STYLE>
<SCRIPT language='javascript'>
function selConsole(name){
   fmMain.action = '?do=showConsole&name=' + name;
   fmMain.target = 'frmBody';
   fmMain.submit();
}
</SCRIPT>
<!------------------------------------------------------------>
<BODY style='margin:0; padding:0' scroll='no'>
<FORM name='fmMain' method='post'>

<TABLE border='0' cellpadding='0' cellspacing='0' width='100%' height='100%'>
   <TR>
      <TD width='8'></TD>
      <TD width='200' valign='top' style='border:1 solid #999999'>

<TABLE border='0' cellpadding="4" cellspacing='0'>
   <TR><TD><B><js:img icon='class.thread'/>Connection Console</B></TD></TR>
   <jh:loop item='&page.ConnectionConsoles' alias='console'>
   <TR><TD><js:img icon='class.thread'/> <A href='javascript:selConsole("<jh:item source='&console.name'/>")'><jh:item source='&console.name'/></A></TD></TR>
   </jh:loop>
</TABLE>

      </TD>
   </TR>
</TABLE>

</FORM>
</BODY>

</HTML>

