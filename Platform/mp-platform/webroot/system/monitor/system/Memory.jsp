<%@ page contentType='text/html;charset=utf-8' %>
<%@ include file='/apl/public.inc' %>

<HTML>

<!-- Html Header Initialize ------------------------------->
<HEAD>
<js:css/>
<jh:js/>
<META http-equiv='refresh' content='10;URL='>
</HEAD>

<!-- Toolbar Initialize ----------------------------------->

<!-- Body Initialize -------------------------------------->
<jh:body style='bodyMain' scroll='no'>
<jh:form name='fmMain'>
<TABLE width='100%' cellspacing='8' border='0'><TR><TD>

<TABLE width='100%' border='0' cellspacing='1' cellpadding='4'>
<TR>
    <TD nowrap width='120px'>Max Memory'/</TD>
    <TD nowrap><jh:write item='&page.maxMemoryM'/>M ( <jh:write item='&page.maxMemory'/> )</TD>
</TR>
<TR>
    <TD nowrap>Total Memory</TD>
    <TD nowrap><jh:write item='&page.totalMemoryM'/>M ( <jh:write item='&page.totalMemory'/> )</TD>
</TR>
<TR>
    <TD nowrap>Free Memory</TD>
    <TD nowrap><jh:write item='&page.freeMemoryM'/>M ( <jh:write item='&page.freeMemory'/> )</TD>
</TR>
<TR>
    <TD nowrap>Use Percent</TD>
    <TD nowrap><jh:write item='&page.usePercent'/>%</TD>
</TR>
<TR>
    <TD nowrap>Available Processors</TD>
    <TD nowrap><jh:write item='&page.availableProcessors'/></TD>
</TR>
</TABLE>

</TD></TR></TABLE>
</jh:form>
</jh:body>
</HTML>
