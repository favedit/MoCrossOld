<%@ include file='/apl/public.inc' %>
<jh:define source='&page.field' alias='field'/>

<HTML>
<HEAD>
<TITLE>Welcome</TITLE>
<js:css />
</HEAD>
<!------------------------------------------------------------>
<STYLE>
.toolbarButton {color:#666666; background-color:#DDDDDD;
      padding-top:0; padding-left:16; padding-bottom:0; padding-right:16; }
</STYLE>
<SCRIPT language='javascript'>
function selConsole(name){
}
</SCRIPT>
<!------------------------------------------------------------>
<BODY style='margin:0; padding-right:6; padding-bottom:6' scroll='no'>

<TABLE border='0' cellpadding='0' cellspacing='0' width='100%' height='100%'>
   <TR>
      <TD style='border:1 solid #999999' valign='top'>

<TABLE border='0' cellpadding="4" cellspacing='0'>
   <TR><TD>Name</TD><TD><jh:source source='&field.name'/></TD></TR>
   <TR><TD>driver</TD><TD><jh:source source='&field.type'/></TD></TR>
   <TR><TD>url</TD><TD><jh:source source='&console.url'/></TD></TR>
   <TR><TD>user</TD><TD><jh:source source='&console.user'/></TD></TR>
   <TR><TD>active_time_limit</TD><TD><jh:source source='&console.active_time_limit'/></TD></TR>
</TABLE>

      </TD>
   </TR>
</TABLE>

</BODY>

</HTML>

