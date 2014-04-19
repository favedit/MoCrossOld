
<HTML>

<!-- Html Header Initialize ------------------------------->
<HEAD>
<META http-equiv="Content-Type" content="text/html; charset=SHIFT_JIS">
<jh:css css="/css/ml.css"/>
<js:js/>
<TITLE>FavInfo</TITLE>
</HEAD>

<!-- Toolbar Initialize ----------------------------------->
<jc:toolbar inFrame='frmBody' inForm='frmConsole' target='frmBody' name='top.frames.frmToolBar.oToolBar' action='refresh'>
    <jc:tbButton caption='db.res|Oracle.Toolbar.Refresh|Refresh' action='act:frmConsole:/db/oracle/info/Session.wa' icon='/res/img/sys/refresh.gif'/>
    <jc:tbButton caption='db.res|Oracle.Toolbar.Kill|Kill' action='act:frmConsole:/db/oracle/info/SessionKill.wa' icon='/res/img/sys/delete.gif'/>
</jc:toolbar>

<!-- Body Initialize -------------------------------------->
<BODY bgcolor='#FFFFFF' text='#000000' leftmargin='0' topmargin='0' marginwidth='0' marginheight='0'>
<jh:form name='frmConsole' link='jfa.app.sys.web.root.db.com.form.DatabaseForm'>


<TABLE width='100%' cellspacing='8' border='0'><TR><TD>

<jh:container source='data_set' position='first'>
<TABLE width='100%' border='0' cellspacing='1' cellpadding='2' bgcolor='#9B9BB3'><TR bgcolor='#FFFFFF'><TD>
<TABLE width='100%' border='0' cellspacing='1' cellpadding='2' bgcolor='#CCCCCC'>
<TR bgcolor='#E0E0FF'>
    <TD nowrap align='center' width='20px'>S</TD>
    <TD nowrap width='40px'>SID</TD>
    <TD width='40px'>SERIAL</TD>
    <TD>STATUS</TD>
    <TD>USERNAME</TD>
    <TD>SCHEMANAME</TD>
    <TD>OSUSER</TD>
    <TD>MACHINE</TD>
    <TD>PROGRAM</TD>
    <TD nowrap>COMMAND</TD>
</TR>
<jh:container link='#source' position='next'>
<jh:logicMod link='#parent.parent' property='position' modItem='2' equalsItem='0'>
<TR bgcolor='#FFFFFF'>
</jh:logicMod>
<jh:logicMod link='#parent.parent' property='position' modItem='2' equalsItem='1'>
<TR bgcolor='#FFFFF0'>
</jh:logicMod>
    <TD nowrap align='center'><jh:check source='selected' trueValue='Y' falseValue='N'/></TD>
    <TD nowrap><jh:source source='sid'/></TD>
    <TD nowrap><jh:source source='serial'/></TD>
    <TD nowrap><jh:source source='status'/></TD>
    <TD nowrap><jh:source source='username'/></TD>
    <TD nowrap><jh:source source='schemaname'/></TD>
    <TD nowrap><jh:source source='osuser'/></TD>
    <TD nowrap><jh:source source='machine'/></TD>
    <TD nowrap><jh:source source='program'/></TD>
    <TD nowrap><jh:source source='command'/></TD>
</TR>
</jh:container>
</TABLE>
</TD></TR></TABLE>
</jh:container>

</TD></TR></TABLE>

</jh:form>
</BODY>
</HTML>
