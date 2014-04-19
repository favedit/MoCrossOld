<%@ include file="/inc/page_begin.inc" %>
<HTML>

<!-- Html Header Initialize ------------------------------->
<HEAD>
<js:meta/>
<js:css/>
<js:js/>
<jh:title caption='FavInfo'/>
</HEAD>

<jh:define item='[Sys.ReportForm]' alias='report_form'/>
<jh:define item='&report_form.database_info' alias='database_info'/>

<!-- Toolbar Initialize ----------------------------------->
<jc:toolbar inFrame='frmBody' inForm='frmConsole' target='frmBody' name='top.frames.frmToolBar.oToolBar' action='refresh'>
   <jc:tbButton caption='txt|sys|Toolbar.Refresh' pageAction='/sys/rpt/Database.wa' icon='sys.refresh'/>
</jc:toolbar>

<!-- Body Initialize -------------------------------------->
<js:body styleClass='frmBody'>
<jh:form name='frmConsole'>
<TABLE width='100%' cellspacing='8' border='0'><TR><TD>

<jh:notEmpty item='&database_info'>
<jh:loop item='&database_info' alias='database'>
<jh:define item='&database.connection_list' alias='connection_list'/>
<TABLE width='100%' border='0' cellspacing='1' cellpadding='4' class='table'>
<TR class='tableHead'>
    <TD nowrap width='40px'><jh:write item='txt|sys.rpt|database.active'/></TD>
    <TD nowrap><jh:write item='txt|sys.rpt|database.name'/></TD>
    <TD nowrap width='120px' align='center'><jh:write item='txt|sys.rpt|database.create_time'/></TD>
    <TD nowrap width='120px' align='center'><jh:write item='txt|sys.rpt|database.last_use_time'/></TD>
</TR>
<jh:loop item='&connection_list' alias='connection'>
<TR class='tableLine'>
    <TD nowrap><jh:item item='&connection.active'/></TD>
    <TD nowrap><jh:item item='&connection.name'/></TD>
    <TD nowrap align='center'><jh:item item='&connection.create_time'/></TD>
    <TD nowrap align='center'><jh:item item='&connection.last_use_time'/></TD>
</TR>
</jh:loop>
</TABLE><BR>
</jh:loop>
</jh:notEmpty>

</TD></TR></TABLE>
</jh:form>
</js:body>
</HTML>
<%@ include file="/inc/page_end.inc" %>
