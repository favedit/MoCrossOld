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
<jh:define item='&report_form.page_info' alias='page_info'/>

<!-- Toolbar Initialize ----------------------------------->
<jc:toolbar inFrame='frmBody' inForm='frmConsole' target='frmBody' name='top.frames.frmToolBar.oToolBar' action='refresh'>
   <jc:tbButton caption='txt|sys|button.refresh' pageAction='/sys/rpt/Page.wa' icon='sys.refresh'/>
   <jc:tbButton caption='txt|sys|button.reset' pageAction='/sys/rpt/Page.wa?action=reset' icon='sys.refresh'/>
</jc:toolbar>

<!-- Body Initialize -------------------------------------->
<js:body styleClass='frmBody'>
<jh:form name='frmConsole'>
<TABLE width='100%' cellspacing='8' border='0'><TR><TD>

<TABLE width='100%' border='0' cellspacing='1' cellpadding='4'>
<TR>
    <TD nowrap width='100px'><jh:write item='txt|sys.rpt|page.page_count'/></TD>
    <TD nowrap><jh:item item='&page_info.page_count'/></TD>
</TR>
<TR>
    <TD nowrap><jh:write item='txt|sys.rpt|page.page_html'/></TD>
    <TD nowrap><jh:item item='&page_info.page_html'/></TD>
</TR>
</TABLE>

</TD></TR></TABLE>
</jh:form>
</js:body>
</HTML>
<%@ include file="/inc/page_end.inc" %>
