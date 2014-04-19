
<HTML>

<!-- Html Header Initialize ------------------------------->
<HEAD>
<js:meta/>
<js:css/>
<js:js/>
<jh:title caption='FavInfo'/>
</HEAD>

<!-- Toolbar Initialize ----------------------------------->
<jc:toolbar inFrame='frmBody' inForm='frmConsole' target='frmBody' name='top.frames.frmToolBar.oToolBar' action='refresh'>
    <jc:tbButton caption='db.res|Oracle.Toolbar.Back|Back' action='act:frmConsole:/db/oracle/info/Info.wa' icon='/res/img/sys/back.gif'/>
    <jc:tbButton caption='db.res|Oracle.Toolbar.Refresh|Refresh' action='act:frmConsole:/db/oracle/info/SysStat.wa' icon='/res/img/sys/refresh.gif'/>
</jc:toolbar>

<!-- Body Initialize -------------------------------------->
<js:body styleClass='frmBody'>
<jh:form name='frmConsole' link='[DB.DatabaseForm]'>


<TABLE width='100%' cellspacing='8' border='0'><TR><TD>

<jh:container source='data_set' position='first'>
<TABLE width='100%' border='0' cellspacing='1' cellpadding='2' bgcolor='#CCCCCC' class='table'>
<TR class='tableHead'>
    <TD width='70px'>STATISTIC</TD>
    <TD>NAME</TD>
    <TD>CLASS</TD>
    <TD>VALUE</TD>
</TR>
<jh:container link='#source' position='next'>
<TR class='tableLine'>
    <TD nowrap><jh:img src='/res/img/db/col.gif' align='absmiddle'/> <jh:source source='statistic'/></TD>
    <TD nowrap><jh:source source='name'/></TD>
    <TD nowrap><jh:source source='class'/></TD>
    <TD><jh:source source='value'/></TD>
</TR>
</jh:container>
</TABLE>
</TD></TR></TABLE>
</jh:container>

</TD></TR></TABLE>

</jh:form>
</js:body>
</HTML>
