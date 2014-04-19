
<HTML>

<!-- Html Header Initialize ------------------------------->
<HEAD>
<META http-equiv='Content-Type' content='text/html; charset=UTF-8'>
<TITLE>FavInfo</TITLE>
<jh:css css="/css/ml.css"/>
<js:js/>
</HEAD>

<!-- Toolbar Initialize ----------------------------------->
<jc:toolbar inFrame='frmBody' inForm='frmConsole' target='frmBody' name='top.frames.frmToolBar.oToolBar' action='refresh'>
    <jc:tbButton caption='db|Oracle.Toolbar.Table.Save' action='act:frmConsole:/db/oracle/tab/Save.wa' icon='/res/img/sys/edit.gif'/>
    <jc:tbButton caption='db|Oracle.Toolbar.Back' action='act:frmConsole:/db/oracle/tab/Information.wa' icon='/res/img/sys/back.gif'/>
</jc:toolbar>

<!-- Body Initialize -------------------------------------->
<BODY bgcolor='#FFFFFF' text='#000000' leftmargin='0' topmargin='0' marginwidth='0' marginheight='0'>
<jh:form name='frmConsole' link='jfa.app.sys.web.root.db.com.form.TableForm'>


<TABLE width='100%' cellspacing='8' border='0'><TR><TD>

<jh:container item='TableUnit'>
<TABLE width='100%' border='0' cellspacing='1' cellpadding='2' bgcolor='#9B9BB3'><TR bgcolor='#FFFFFF'><TD>
<TABLE width='100%' border='0' cellspacing='1' cellpadding='2' bgcolor='#CCCCCC'>
<TR bgcolor='#E0E0FF'>
    <TD nowrap width='150px'><jh:img src='/res/img/db/tab.gif' align='absmiddle'/> <jh:write item='txt|db.res' item='Oracle.Table.TableName' value='Table Name'/></TD>
    <TD nowrap><B><jh:item item='table_name'/></B></TD>
</TR>
<TR bgcolor='#FFFFFF'>
    <TD nowrap width='150px'><jh:img src='/res/img/sys/block.gif' align='absmiddle'/> <jh:write item='txt|db.res' item='Oracle.Table.Status' value='Status'/></TD>
    <TD nowrap><jh:check displayOnly='Y' trueValue='Y' falseValue='N' item='status'/></TD>
</TR>
<TR bgcolor='#FFFFFF'>
    <TD nowrap width='150px'><jh:img src='/res/img/sys/block.gif' align='absmiddle'/> <jh:write item='txt|db.res' item='Oracle.Table.DisplayLabel' value='Display Label'/></TD>
    <TD nowrap><jh:edit item='display_label' size='40'/></TD>
</TR>
<TR bgcolor='#FFFFFF'>
    <TD nowrap width='150px'><jh:img src='/res/img/sys/block.gif' align='absmiddle'/> <jh:write item='txt|db.res' item='Oracle.Table.Created' value='Created'/></TD>
    <TD nowrap><jh:item item='created'/></TD>
</TR>
</TABLE>
</TD></TR></TABLE>
</jh:container><BR>

<jh:container item='DataSet' position='first'>
<TABLE width='100%' border='0' cellspacing='1' cellpadding='2' bgcolor='#9B9BB3'><TR bgcolor='#FFFFFF'><TD>
<TABLE width='100%' border='0' cellspacing='1' cellpadding='2' bgcolor='#CCCCCC'>
<TR bgcolor='#E0E0FF'>
    <TD nowrap width='80px'><jh:write item='txt|db.res' item='Oracle.Table.Column.ColumnName' value='Column Name'/></TD>
    <TD align='right' nowrap width='30px'><jh:write item='txt|db.res' item='Oracle.Table.Column.ID' value='ID'/></TD>
    <TD nowrap width='30px' align='center'><jh:write item='txt|db.res' item='Oracle.Table.Column.IsKey' value='K'/></TD>
    <TD nowrap width='30px' align='center'><jh:write item='txt|db.res' item='Oracle.Table.Column.IsNull' value='N'/></TD>
    <TD nowrap width='30px' align='center'><jh:write item='txt|db.res' item='Oracle.Table.Column.IsPublic' value='P'/></TD>
    <TD nowrap width='80px'><jh:write item='txt|db.res' item='Oracle.Table.Column.DisplayLabel' value='Display Label'/></TD>
    <TD nowrap width='80px'><jh:write item='txt|db.res' item='Oracle.Table.Column.DataType' value='Data Type'/></TD>
    <TD nowrap width='80px'><jh:write item='txt|db.res' item='Oracle.Table.Column.DataSize' value='Data Size'/></TD>
    <TD nowrap><jh:write item='txt|db.res' item='Oracle.Table.Column.DataDefault' value='Data Default'/></TD>
</TR>
<jh:container link='#item' position='next'>
<jh:logicMod link='#parent.parent' property='position' modItem='2' equalsItem='0'>
<TR bgcolor='#FFFFFF'>
</jh:logicMod>
<jh:logicMod link='#parent.parent' property='position' modItem='2' equalsItem='1'>
<TR bgcolor='#FFFFF0'>
</jh:logicMod>
    <TD nowrap><jh:img src='/res/img/db/col.gif' align='absmiddle'/> <jh:item item='column_name'/></TD>
    <TD align='right' nowrap><jh:item item='column_id'/></TD>
    <TD align='center'><jh:check displayOnly='Y' trueValue='Y' falseValue='N' item='is_key'/></TD>
    <TD align='center'><jh:check displayOnly='Y' trueValue='Y' falseValue='N' item='is_null'/></TD>
    <TD align='center'><jh:check displayOnly='Y' trueValue='Y' falseValue='N' item='is_public'/></TD>
    <TD><jh:edit item='display_label' size='25'/></TD>
    <TD><jh:item item='data_type'/></TD>
    <TD><jh:item item='data_length'/></TD>
    <TD><jh:item item='data_default'/></TD>
</TR>
</jh:container>
</TABLE>
</TD></TR></TABLE>
</jh:container>

</TD></TR></TABLE>

</jh:form>
</BODY>
</HTML>
