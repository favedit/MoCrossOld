
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
    <jc:tbButton caption='db|Toolbar.Back' action='act:frmConsole:/db/oracle/info/Info.wa' icon='/res/img/sys/back.gif'/>
    <jc:tbButton caption='db|Toolbar.Refresh' action='act:frmConsole:/db/oracle/info/Parameter.wa' icon='/res/img/sys/refresh.gif'/>
</jc:toolbar>

<!-- Body Initialize -------------------------------------->
<js:body styleClass='frmBody'>
<jh:form name='frmConsole' link='[DB.DatabaseForm]'>


<TABLE width='100%' cellspacing='8' border='0'><TR><TD>

<jh:container source='data_set' position='first'>
<TABLE width='100%' border='0' cellspacing='1' cellpadding='2' bgcolor='#CCCCCC' class='table'>
<TR class='tableHead'>
    <TD width='70px'>NUM</TD>
    <TD>NAME</TD>
    <TD>TYPE</TD>
    <TD align='center'>DEF</TD>
    <TD align='center'>SES<BR>MOD</TD>
    <TD align='center'>SYS<BR>MOD</TD>
    <TD align='center'>MOD</TD>
    <TD align='center'>ADJ</TD>
    <TD>DESCRIPTION</TD>
    <TD>VALUE</TD>
    <TD nowrap>UPDATE_COMMENT</TD>
</TR>
<jh:container link='#source' position='next'>
<TR class='tableLine'>
    <TD nowrap><jh:img src='/res/img/db/col.gif' align='absmiddle'/> <jh:source source='num'/></TD>
    <TD nowrap><jh:source source='name'/></TD>
    <TD nowrap><jh:source source='type'/></TD>
    <TD align='center'><jh:check displayOnly='Y' source='isdefault' trueValue='TRUE' falseValue='FALSE'/></TD>
    <TD align='center'><jh:check displayOnly='Y' source='isses_modifiable' trueValue='TRUE' falseValue='FALSE'/></TD>
    <TD align='center'><jh:check displayOnly='Y' source='issys_modifiable' trueValue='TRUE' falseValue='FALSE'/></TD>
    <TD align='center'><jh:check displayOnly='Y' source='ismodified' trueValue='TRUE' falseValue='FALSE'/></TD>
    <TD align='center'><jh:check displayOnly='Y' source='isadjusted' trueValue='TRUE' falseValue='FALSE'/></TD>
    <TD nowrap><jh:source source='description'/></TD>
    <TD><jh:source source='value'/></TD>
    <TD><jh:source source='update_comment'/></TD>
</TR>
</jh:container>
</TABLE>
</jh:container>

</TD></TR></TABLE>

</jh:form>
</js:body>
</HTML>
