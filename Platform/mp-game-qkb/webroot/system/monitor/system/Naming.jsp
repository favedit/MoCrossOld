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
<jh:define item='&report_form.naming_info' alias='naming_info'/>

<!-- Toolbar Initialize ----------------------------------->
<jc:toolbar inFrame='frmBody' inForm='frmConsole' target='frmBody' name='top.frames.frmToolBar.oToolBar' action='refresh'>
   <jc:tbButton caption='txt|sys|Toolbar.Refresh' pageAction='/sys/rpt/Page.wa' icon='sys.refresh'/>
</jc:toolbar>

<!-- Body Initialize -------------------------------------->
<js:body styleClass='frmBody'>
<jh:form name='frmConsole'>
<TABLE width='100%' cellspacing='8' border='0'><TR><TD>

<TABLE width='100%' border='0' cellspacing='1' cellpadding='4'>
<TR>
    <TD nowrap><jh:item item='&naming_info.page_count'/></TD>
</TR>
<TR>
    <TD nowrap><jh:item item='&naming_info.naming_html'/></TD>
</TR>
</TABLE>

</TD></TR></TABLE>
</jh:form>
</js:body>
</HTML>
<%@ include file="/inc/page_end.inc" %>
