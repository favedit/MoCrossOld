<%@ include file="/inc/page/begin.inc" %>
<jh:define form='wfc.database' alias='database_form'/>
<jh:define item='&database_form.field' alias='field'/>
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
   <jc:tbButton caption='trs:sys|button.save|Save' pageAction='/system/database/table/FieldUpdateSave.wa' icon='sys.save'/>
</jc:toolbar>

<!-- Body Initialize -------------------------------------->
<js:body styleClass='frmWork'>
<jh:form name='frmConsole'>
<TABLE width='100%' cellspacing='8' border='0'><TR><TD>

<TABLE border='0' cellspacing='0' cellpadding='2'>
<TR>
   <TD nowrap width='80'><jh:text value='trs:this|field.name|Name'/>:</TD>
   <TD nowrap colspan='3'><B><jh:item item='&field.field_name'/></B></TD>
</TR>
<TR>
   <TD nowrap width='80'><jh:text value='trs:this|field.label|Label'/>:</TD>
   <TD nowrap colspan='3'><jh:edit item='&field.label' size='40' maxlength='200'/></TD>
</TR>
<TR>
    <TD nowrap width='80'><jh:text value='trs:this|table.is_key|Is Key'/>:</TD>
    <TD nowrap width='120'><jh:check item='&field.is_key' displayOnly='Y' trueValue='Y' falseValue='N'/></TD>
    <TD nowrap width='80'><jh:text value='trs:this|table.is_null|Is Null'/>:</TD>
    <TD nowrap><jh:check item='&field.is_null' displayOnly='Y' trueValue='Y' falseValue='N'/></TD>
</TR>
<TR>
   <TD nowrap width='80'><jh:text value='trs:this|field.data_type|Data Type'/>:</TD>
   <TD nowrap width='120'><jh:item item='&field.data_type'/></TD>
   <TD nowrap width='80'><jh:text value='trs:this|field.data_length|Data Length'/>:</TD>
   <TD nowrap><jh:item item='&field.data_length'/></TD>
</TR>
<TR>
   <TD nowrap width='80'><jh:text value='trs:this|field.note|Note'/>:</TD>
   <TD nowrap colspan='3'><jh:memo item='&field.note' cols='40' rows='2'/></TD>
</TR>
</TABLE>

<BR><js:property type='&property.type' name='&property.name' value='&property.value'/>

</TD></TR></TABLE>
</jh:form>
</js:body>
</HTML>
<%@ include file="/inc/page/end.inc" %>
