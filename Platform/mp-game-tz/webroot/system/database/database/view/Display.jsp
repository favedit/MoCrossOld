
<HTML>

<!-- Html Header Initialize ------------------------------->
<HEAD>
<META http-equiv="Content-Type" content="text/html; charset=SHIFT_JIS">
<jh:css css="/css/ml.css"/>
<jh:js src="/js/lang.js"/>
<jh:js src="/js/public.js"/>
<jh:js src="/js/system.js"/>
<jh:js src="/js/lh_tree.js"/>
<TITLE>FavInfo</TITLE>
</HEAD>

<!-- Action Initialize ----------------------------------->
<SCRIPT language='javascript'>
function goActionEdit(){
    frmConsole.wa = '<jh:context/>/db/table/info/Edit.wa';
    frmConsole.submit();
}
</SCRIPT>

<!-- Toolbar Initialize ----------------------------------->
<jc:toolbar name='app_toolbar' action='refresh'>
    <jc:tbButton caption='Edit' action='goActionEdit()' icon='/res/img/sys/edit.gif'/>
</jc:toolbar>

<!-- Body Initialize -------------------------------------->
<BODY bgcolor='#FFFFFF' text='#000000' leftmargin='0' topmargin='0' marginwidth='0' marginheight='0'>
<jh:form name='frmConsole' link='jfa.app.sys.web.root.db.table.TableForm'>


<TABLE width='100%' cellspacing='8' border='0'><TR><TD>

<jh:container item='TableUnit'>
<TABLE width='100%' border='0' cellspacing='1' cellpadding='2' bgcolor='#9B9BB3'><TR bgcolor='#FFFFFF'><TD>
<TABLE width='100%' border='0' cellspacing='1' cellpadding='2' bgcolor='#CCCCCC'>
<TR bgcolor='#E0E0FF'>
    <TD nowrap width='150px'><jh:img src='/res/img/db/table.gif' align='absmiddle'/> Table Name</TD>
    <TD nowrap><B><jh:item item='table_name'/></B></TD>
</TR>
<TR bgcolor='#FFFFFF'>
    <TD nowrap width='150px'><jh:img src='/res/img/sys/block.gif' align='absmiddle'/> Table Code</TD>
    <TD nowrap><jh:item item='table_code'/></TD>
</TR>
<TR bgcolor='#FFFFFF'>
    <TD nowrap width='150px'><jh:img src='/res/img/sys/block.gif' align='absmiddle'/> Display Label</TD>
    <TD nowrap><jh:item item='display_label'/></TD>
</TR>
</TABLE>
</TD></TR></TABLE>
</jh:container>

<BR>

<jh:container item='DataSet' position='first'>
<TABLE width='100%' border='0' cellspacing='1' cellpadding='2' bgcolor='#9B9BB3'><TR bgcolor='#FFFFFF'><TD>
<TABLE width='100%' border='0' cellspacing='1' cellpadding='2' bgcolor='#CCCCCC'>
<TR bgcolor='#E0E0FF'>
    <TD nowrap width='80px'>Column Name</TD>
    <TD nowrap width='20px' align='center'>K</TD>
    <TD nowrap width='20px' align='center'>N</TD>
    <TD nowrap width='20px' align='center'>P</TD>
    <TD nowrap width='80px'>Display Label</TD>
    <TD nowrap width='80px'>Date Type</TD>
    <TD nowrap width='80px'>Date Size</TD>
    <TD nowrap>Date Default</TD>
</TR>
<jh:container link='#item' position='next'>
<jh:logicMod link='#parent.parent' property='position' modValue='2' value='0'>
<TR bgcolor='#FFFFFF'>
</jh:logicMod>
<jh:logicMod link='#parent.parent' property='position' modValue='2' value='1'>
<TR bgcolor='#FFFFF0'>
</jh:logicMod>
    <TD nowrap><jh:img src='/res/img/db/col.gif' align='absmiddle'/> <jh:item item='column_name'/></TD>
    <TD align='center'><jh:check displayOnly='Y' item='is_key'/></TD>
    <TD align='center'><jh:check displayOnly='Y' item='is_null'/></TD>
    <TD align='center'><jh:check displayOnly='Y' item='is_public'/></TD>
    <TD><jh:edit displayOnly='Y' item='display_label' size='25'/></TD>
    <TD><jh:combo displayOnly='Y' item='data_type' enumClass='jfa.com.db.FDBType'/></TD>
    <TD><jh:edit displayOnly='Y' item='data_size' size='6'/></TD>
    <TD><jh:edit displayOnly='Y' item='data_default' size='25'/></TD>
</TR>
</jh:container>
</TABLE>
</TD></TR></TABLE>
</jh:container>

</TD></TR></TABLE>

</jh:form>
</BODY>
</HTML>
