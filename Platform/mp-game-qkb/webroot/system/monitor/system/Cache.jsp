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
<jh:define item='&report_form.cache_info' alias='cache_info'/>
<jh:define item='&report_form.cache_memory_ds' alias='cache_memory_ds'/>
<jh:define item='&report_form.cache_file_ds' alias='cache_file_ds'/>

<!-- Toolbar Initialize ----------------------------------->
<jc:toolbar inFrame='frmBody' inForm='frmConsole' target='frmBody' name='top.frames.frmToolBar.oToolBar' action='refresh'>
   <jc:tbButton caption='txt|sys|Toolbar.Refresh' pageAction='/sys/rpt/Cache.wa' icon='sys.refresh'/>
</jc:toolbar>

<!-- Body Initialize -------------------------------------->
<js:body styleClass='frmBody'>
<jh:form name='frmConsole'>
<TABLE width='100%' cellspacing='8' border='0'><TR><TD>

<TABLE width='100%' border='0' cellspacing='1' cellpadding='4' class='table'>
<TR class='tableLine'>
    <TD nowrap width='80px' class='tableHead'><jh:write item='txt|sys.rpt|cache.shoort_count'/></TD>
    <TD><jh:item item='&cache_info.shoort_count'/></TD>
</TR>
<TR class='tableLine'>
    <TD nowrap width='80px' class='tableHead'><jh:write item='txt|sys.rpt|cache.require_count'/></TD>
    <TD><jh:item item='&cache_info.require_count'/></TD>
</TR>
<TR class='tableLine'>
    <TD nowrap width='80px' class='tableHead'><jh:write item='txt|sys.rpt|cache.shoort_percent'/></TD>
    <TD><jh:item item='&cache_info.shoort_percent'/></TD>
</TR>
</TABLE><BR>

<jh:write item='txt|sys.rpt|cache.memory_cache'/>: <jh:item item='&cache_info.memory_count'/> (<jh:item item='&cache_info.memory_cache_size'/>byte)
<TABLE width='100%' border='0' cellspacing='1' cellpadding='4' class='table'>
<TR class='tableHead'>
    <TD nowrap width='140px'><jh:write item='txt|sys.rpt|cache.name'/></TD>
    <TD nowrap width='60px'><jh:write item='txt|sys.rpt|cache.length'/></TD>
    <TD nowrap width='90px' align='center'><jh:write item='txt|sys.rpt|cache.start_time'/></TD>
    <TD nowrap width='90px' align='center'><jh:write item='txt|sys.rpt|cache.end_time'/></TD>
    <TD nowrap width='60px' align='center'><jh:write item='txt|sys.rpt|cache.persist_time'/></TD>
    <TD nowrap><jh:write item='txt|sys.rpt|cache.attribute'/></TD>
</TR>
<jh:loop item='&cache_memory_ds' alias='cache_memory'>
<TR class='tableLine'>
    <TD nowrap><jh:item item='&cache_memory.cache_name'/></TD>
    <TD nowrap align='right'><jh:item item='&cache_memory.cache_length'/></TD>
    <TD nowrap align='center'><jh:item item='&cache_memory.start_time'/></TD>
    <TD nowrap align='center'><jh:item item='&cache_memory.end_time'/></TD>
    <TD nowrap align='right'><jh:item item='&cache_memory.persist_time'/></TD>
    <TD nowrap><jh:item item='&cache_memory.cache_attribute'/></TD>
</TR>
</jh:loop>
</TABLE><BR>

<BR>

<jh:write item='txt|sys.rpt|cache.file_cache'/>: <jh:item item='&cache_info.file_count'/>
<TABLE width='100%' border='0' cellspacing='1' cellpadding='4' class='table'>
<TR class='tableHead'>
    <TD nowrap><jh:write item='txt|sys.rpt|cache.name'/></TD>
</TR>
<jh:loop item='&cache_file_ds' alias='cache_file'>
<TR class='tableLine'>
    <TD nowrap><jh:item item='&cache_file.cache_name'/></TD>
</TR>
</jh:loop>
</TABLE><BR>

</TD></TR></TABLE>
</jh:form>
</js:body>
</HTML>
<%@ include file="/inc/page_end.inc" %>
