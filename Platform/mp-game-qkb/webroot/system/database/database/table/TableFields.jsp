<%@ include file="/inc/page/begin.inc" %>
<jh:define form='wfc.database' alias='database_form'/>
<jh:define item='&database_form.table' alias='table'/>
<jh:define item='&database_form.fields' alias='fields'/>

<HTML>

<!-- Html Header Initialize ------------------------------->
<HEAD>
<js:meta/>
<js:css/>
<js:js/>
<jh:title caption='Table'/>
</HEAD>

<!-- Toolbar Initialize ----------------------------------->
<jc:toolbar name='top.frames.frmToolbar.oToolbar' inFrame='frmWork' inForm='frmConsole' target='frmWork' action='refresh'/>

<!-- Body Initialize -------------------------------------->
<js:body styleClass='frmWork'>
<jh:form name='frmConsole'>
<TABLE width='100%' cellspacing='8' border='0'><TR><TD>

<TABLE width='100%' border='0' cellspacing='0' cellpadding='2'>
<TR>
   <TD nowrap width='80'><jh:text value='trs:this|table.name|Name'/>:</TD>
   <TD nowrap colspan='3'><B><jh:item item='&table.table_name'/></B></TD>
</TR>
<TR>
   <TD nowrap width='80'><jh:text value='trs:this|table.label|Label'/>:</TD>
   <TD nowrap colspan='3'><jh:item item='&table.label'/></TD>
</TR>
<TR>
   <TD nowrap width='80'><jh:text value='trs:this|table.logic.delete|Logic Delete'/>:</TD>
   <TD nowrap width='120'><jh:check item='&table.logic_delete' displayOnly='Y' trueValue='Y' falseValue='N'/></TD>
   <TD nowrap width='80'><jh:text value='trs:this|table.created|Created'/>:</TD>
   <TD nowrap><jh:date item='&table.created' displayOnly='Y' format='YYYY-MM-DD HH24:MI:SS'/></TD>
</TR>
<TR>
    <TD nowrap width='80'><jh:text value='trs:this|table.is_use|Is Use'/>:</TD>
    <TD nowrap width='120'><jh:check item='&table.is_use' displayOnly='Y' trueValue='Y' falseValue='N'/></TD>
    <TD nowrap width='80'><jh:text value='trs:this|table.status|Status'/>:</TD>
    <TD nowrap><jh:check item='&table.status' displayOnly='Y' trueValue='Y' falseValue='N'/></TD>
</TR>
<TR>
   <TD nowrap width='80'><jh:text value='trs:this|table.note|Note'/>:</TD>
   <TD nowrap colspan='3'><jh:item item='&table.note'/></TD>
</TR>
</TABLE><BR>

<jh:hasChild item='&fields'>
<TABLE width='100%' border='0' cellspacing='1' cellpadding='2' class='table'>
<TR class='tableHead'>
   <TD nowrap width='180px'><jh:text value='trs:this|table.column.field_name|Field Name'/></TD>
   <TD align='right' nowrap width='30px'><jh:text value='trs:this|table.column.id|Id'/></TD>
   <TD nowrap width='30px' align='center'><jh:text value='trs:this|table.column.is_key|Key'/></TD>
   <TD nowrap width='30px' align='center'><jh:text value='trs:this|table.column.is_null|Null'/></TD>
   <TD nowrap width='30px' align='center'><jh:text value='trs:this|table.column.is_public|Public'/></TD>
   <TD nowrap><jh:text value='trs:this|table.column.label|Label'/></TD>
   <TD nowrap width='80px'><jh:text value='trs:this|table.column.type|Type'/></TD>
   <TD nowrap width='80px'><jh:text value='trs:this|vable.column.size|Size'/></TD>
</TR>
<jh:loop item='&fields' alias='field'>
<TR class='tableLine'>
   <TD nowrap><jh:img src='/res/img/db/col.gif' align='absmiddle'/> <jh:item item='&field.field_name'/></TD>
   <TD nowrap align='right'><jh:item item='&field.field_id'/></TD>
   <TD nowrap align='center'><jh:check item='&field.is_key' displayOnly='Y' trueValue='Y' falseValue='N'/></TD>
   <TD nowrap align='center'><jh:check item='&field.is_null' displayOnly='Y' trueValue='Y' falseValue='N'/></TD>
   <TD nowrap align='center'><jh:check item='&field.is_public' displayOnly='Y' trueValue='Y' falseValue='N'/></TD>
   <TD nowrap><jh:item item='&field.label'/></TD>
   <TD nowrap><jh:item item='&field.data_type'/></TD>
   <TD nowrap><jh:item item='&field.data_length'/></TD>
</TR>
</jh:loop>
</TABLE>
</jh:hasChild>

</TD></TR></TABLE>
</jh:form>
</js:body>
</HTML>
<%@ include file="/inc/page/end.inc" %>
