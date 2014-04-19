<%@ include file='/apl/public.inc' %>

[<jh:write source='&tag.hello'/>]
<BR>

<TABLE border='1'>
<jh:loop source='&tag.config' alias='face'>
<TR>
<TD>
<jh:write source='&face.name'/> = <jh:write source='&face.label'/><BR>
</TD>
</TR>
</jh:loop>
</TABLE>

