<%@ include file="/inc/page/begin.inc" %>
<jh:define form='wfc.database' alias='database_form'/>
<jh:define item='&database_form.table' alias='table'/>
<jh:define item='&database_form.property' alias='property'/>

<HTML>

<!-- Html Header Initialize ------------------------------->
<HEAD>
<js:meta/>
<js:css/>
<js:js/>
<jh:title caption='Table'/>
</HEAD>

<!-- Toolbar Initialize ----------------------------------->
<jc:toolbar name='top.frames.frmToolbar.oToolbar' inFrame='frmWork' inForm='frmConsole' target='frmWork' action='refresh'>
   <jc:tbButton caption='trs:sys|button.save|Save' pageAction='/system/database/table/TableUpdateSave.wa' icon='sys.save'/>
   <jc:tbButton caption='|'/>
   <jc:tbButton caption='trs:this|build.property|Build Property' pageAction='/system/database/table/BuildProperty.wa' icon='sys.edit'/>
   <jc:tbButton caption='|'/>
   <jc:tbButton caption='trs:this|build.dataset|Build Dataset' pageAction='/system/database/table/BuildDataset.wa' icon='sys.edit'/>
</jc:toolbar>

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
   <TD nowrap colspan='3'><jh:edit item='&table.label' size='40' maxlength='200'/></TD>
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
   <TD nowrap colspan='3'><jh:memo item='&table.note' cols='40' rows='2'/></TD>
</TR>
</TABLE>

<BR><js:property type='&property.type' name='&property.name' value='&property.value'/>

</TD></TR></TABLE>
</jh:form>
</js:body>
</HTML>
<%@ include file="/inc/page/end.inc" %>
