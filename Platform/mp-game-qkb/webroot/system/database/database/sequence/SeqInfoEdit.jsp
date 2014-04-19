<%@ include file="/inc/page_begin.inc" %>
<HTML>

<!-- Html Header Initialize ------------------------------->
<HEAD>
<js:meta/>
<js:css/>
<js:js/>
<jh:title caption='Table'/>
</HEAD>

<jh:define item='[DB.ToolbarForm]' alias='toolbar_form'/>
<jh:define item='[DB.TableForm]' alias='table_form'/>
<jh:define item='&table_form' item='table_unit' alias='table_unit'/>
<jh:define item='&table_form' item='dataset' alias='dataset'/>

<!-- Toolbar Initialize ----------------------------------->
<jc:toolbar inFrame='frmBody' inForm='frmConsole' target='frmBody' name='top.frames.frmToolBar.oToolBar' action='refresh'>
   <jc:tbButton caption='txt|sys|Toolbar.Save' pageAction='/db/oracle/tab/InfoSave.wa' icon='sys.edit'/>
   <jc:tbButton caption='txt|sys|Toolbar.Back' pageAction='/db/oracle/tab/Info.wa' icon='sys.back'/>
</jc:toolbar>

<!-- Body Initialize -------------------------------------->
<js:body styleClass='frmBody'>
<jh:form name='frmConsole' link='[DB.TableForm]'>


<TABLE width='100%' cellspacing='8' border='0'><TR><TD>

<TABLE width='100%' border='0' cellspacing='1' cellpadding='2' class='table'>
<TR class='tableHead'>
   <TD nowrap width='150px'><jh:img src='/res/img/db/tab.gif' align='absmiddle'/> <jh:write item='txt|db|Table.Name'/></TD>
   <TD nowrap colspan='3'><B><jh:item link='&table_unit' item='table_name'/></B></TD>
</TR>
<TR class='tableLine'>
   <TD nowrap width='150px'><jh:img src='/res/img/sys/block.gif' align='absmiddle'/> <jh:write item='txt|db|Table.Status'/></TD>
   <TD nowrap colspan='3'><jh:check link='&table_unit' displayOnly='Y' trueValue='Y' falseValue='N' item='status'/></TD>
</TR>
<TR class='tableLine'>
   <TD nowrap width='150px'><jh:img src='/res/img/sys/block.gif' align='absmiddle'/> <jh:write item='txt|db|Table.DisplayLabel'/></TD>
   <TD nowrap colspan='3'><jh:edit link='&table_unit' item='display_label' size='40'/></TD>
</TR>
<TR class='tableLine'>
   <TD nowrap width='150px'><jh:img src='/res/img/sys/block.gif' align='absmiddle'/> <jh:write item='txt|db|Table.Description'/></TD>
   <TD nowrap colspan='3'><jh:edit link='&table_unit' item='description' size='40'/></TD>
</TR>
<TR class='tableLine'>
   <TD nowrap width='150px'><jh:img src='/res/img/sys/block.gif' align='absmiddle'/> <jh:write item='txt|db|Table.Created'/></TD>
   <TD nowrap colspan='3'><jh:datetime link='&table_unit' item='created' format='YYYY-MM-DD HH24:MI:SS'/></TD>
</TR>
<TR class='tableLine'>
    <TD nowrap width='150px'><jh:img src='/res/img/sys/block.gif' align='absmiddle'/> <jh:write item='txt|db|Table.IsUse'/></TD>
    <TD nowrap width='150px'><jh:check link='&table_unit' trueValue='Y' falseValue='N' item='is_use'/></TD>
    <TD nowrap width='150px'><jh:img src='/res/img/sys/block.gif' align='absmiddle'/> <jh:write item='txt|db|Table.Status'/></TD>
    <TD nowrap><jh:check link='&table_unit' displayOnly='Y' trueValue='Y' falseValue='N' item='status'/></TD>
</TR>
</TABLE><BR>

<jh:notEmpty item='&dataset'>
<TABLE width='100%' border='0' cellspacing='1' cellpadding='2' class='table'>
<TR class='tableHead'>
   <TD nowrap width='80px'><jh:write item='txt|db|Table.Column.Name'/></TD>
   <TD align='right' nowrap width='30px'><jh:write item='txt|db|Table.Column.ID'/></TD>
   <TD nowrap width='30px' align='center'><jh:write item='txt|db|Table.Column.IsKey'/></TD>
   <TD nowrap width='30px' align='center'><jh:write item='txt|db|Table.Column.IsNull'/></TD>
   <TD nowrap width='30px' align='center'><jh:write item='txt|db|Table.Column.IsPublic'/></TD>
   <TD nowrap width='80px'><jh:write item='txt|db|Table.Column.DisplayLabel'/></TD>
   <TD nowrap width='80px'><jh:write item='txt|db|Table.Column.DataType'/></TD>
   <TD nowrap width='80px'><jh:write item='txt|db|Table.Column.DataSize'/></TD>
   <TD nowrap><jh:write item='txt|db|Table.Column.DataDefault'/></TD>
</TR>
<jh:loop item='&dataset' alias='unit'>
   <jh:mod link="&dataset" property='position' modItem='2' equalsItem='0'><TR class='tableLine'></jh:mod>
   <jh:mod link="&dataset" property='position' modItem='2' equalsItem='1'><TR class='tableBody'></jh:mod>
   <TD nowrap><jh:img src='/res/img/db/col.gif' align='absmiddle'/> <jh:item item='&unit.column_name'/></TD>
   <TD align='right' nowrap><jh:item item='&unit.column_id'/></TD>
   <TD align='center'><jh:check link='&unit' displayOnly='Y' trueValue='Y' falseValue='N' item='is_key'/></TD>
   <TD align='center'><jh:check link='&unit' displayOnly='Y' trueValue='Y' falseValue='N' item='is_null'/></TD>
   <TD align='center'><jh:check link='&unit' displayOnly='Y' trueValue='Y' falseValue='N' item='is_public'/></TD>
   <TD><jh:edit item='&unit.display_label' size='25'/></TD>
   <TD><jh:item item='&unit.data_type'/></TD>
   <TD><jh:item item='&unit.data_length'/></TD>
   <TD><jh:edit item='&unit.data_default' size='25'/></TD>
</TR>
</jh:loop>
</TABLE>
</jh:notEmpty>

</TD></TR></TABLE>

</jh:form>
</js:body>
</HTML>
<%@ include file="/inc/page_end.inc" %>
