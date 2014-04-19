<%@ include file="/inc/page_begin.inc" %>
<HTML>

<!-- Html Header Initialize ------------------------------->
<HEAD>
<js:meta/>
<js:css/>
<js:js/>
<jh:title caption='FavInfo'/>
<META http-equiv='refresh' content='10;URL='>
</HEAD>

<jh:define item='[Sys.ReportForm]' alias='report_form'/>
<jh:define item='&report_form.session_info' alias='session_info'/>
<jh:define item='&report_form.session_ds' alias='session_ds'/>

<!-- Toolbar Initialize ----------------------------------->
<jc:toolbar inFrame='frmBody' inForm='frmConsole' target='frmBody' name='top.frames.frmToolBar.oToolBar' action='refresh'>
   <jc:tbButton caption='txt|sys|button.refresh' pageAction='/sys/rpt/Memory.wa' icon='sys.refresh'/>
   <jc:tbButton caption='txt|sys|button.memory.clear' pageAction='/sys/rpt/ClearMemory.wa' icon='sys.refresh'/>
</jc:toolbar>

<!-- Body Initialize -------------------------------------->
<js:body styleClass='frmBody'>
<jh:form name='frmConsole'>
<TABLE width='100%' cellspacing='8' border='0'><TR><TD>

<TABLE width='100%' border='0' cellspacing='1' cellpadding='4'>
<TR>
   <TD nowrap width='100px'><jh:write item='txt|sys.rpt|session.session_count'/></TD>
   <TD nowrap><jh:item item='&session_info.session_count'/></TD>
</TR>
</TABLE>

<TABLE width='100%' border='0' cellspacing='1' cellpadding='4' class='table'>
<TR class='tableHead'>
    <TD nowrap width='200px'><jh:write item='txt|sys.rpt|session.session_id'/></TD>
    <TD nowrap align='center' width='120px'><jh:write item='txt|sys.rpt|session.create_time'/></TD>
    <TD nowrap><jh:write item='txt|sys.rpt|session.current_time'/></TD>
</TR>
<jh:loop item='&session_ds' alias='session_info'>
<TR class='tableLine'>
    <TD nowrap><jh:item item='&session_info.session_id'/></TD>
    <TD nowrap align='center'><jh:item item='&session_info.create_time'/></TD>
    <TD nowrap align='center'><jh:item item='&session_info.current_time'/></TD>
</TR>
</jh:loop>
</TABLE><BR>

</TD></TR></TABLE>
</jh:form>
</js:body>
</HTML>
<%@ include file="/inc/page_end.inc" %>
