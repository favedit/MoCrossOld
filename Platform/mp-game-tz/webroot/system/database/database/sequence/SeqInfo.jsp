<%@ include file="/inc/page_begin.inc" %>
<HTML>

<!-- Html Header Initialize ------------------------------->
<HEAD>
<js:meta/>
<js:css/>
<js:js/>
<jh:title caption='Table'/>
</HEAD>

<jh:define item='[DB.DatabaseForm]' alias='database_form'/>
<jh:define item='&database_form.sequence_unit' alias='sequence_unit'/>

<!-- Toolbar Initialize ----------------------------------->
<jc:toolbar inFrame='frmBody' inForm='frmConsole' target='frmBody' name='top.frames.frmToolBar.oToolBar' action='refresh'>
   <jc:tbButton caption='txt|sys|Toolbar.Edit' pageAction='/db/oracle/tab/InfoEdit.wa' icon='sys.edit'/>
</jc:toolbar>

<!-- Body Initialize -------------------------------------->
<js:body styleClass='frmBody'>
<jh:form name='frmConsole'>
<TABLE width='100%' cellspacing='8' border='0'><TR><TD>

<TABLE width='100%' border='0' cellspacing='1' cellpadding='2' class='table'>
<TR class='tableHead'>
   <TD nowrap width='150px'><jh:img src='/res/img/db/tab.gif' align='absmiddle'/> <jh:write item='txt|db|sequence.name'/></TD>
   <TD nowrap><B><jh:item item='&sequence_unit.sequence_name'/></B></TD>
</TR>
<TR class='tableLine'>
   <TD nowrap width='150px'><jh:img src='/res/img/sys/block.gif' align='absmiddle'/> <jh:write item='txt|db|sequence.is_use'/></TD>
   <TD nowrap><jh:check displayOnly='Y' item='&sequence_unit.is_use' trueValue='Y' falseValue='N'/></TD>
</TR>
<TR class='tableLine'>
   <TD nowrap width='150px'><jh:img src='/res/img/sys/block.gif' align='absmiddle'/> <jh:write item='txt|db|sequence.display_label'/></TD>
   <TD nowrap><jh:item item='&sequence_unit.display_label'/></TD>
</TR>
<TR class='tableLine'>
   <TD nowrap width='150px'><jh:img src='/res/img/sys/block.gif' align='absmiddle'/> <jh:write item='txt|db|sequence.description'/></TD>
   <TD nowrap><jh:item item='&sequence_unit.description'/></TD>
</TR>
</TABLE>

</TD></TR></TABLE>
</jh:form>
</js:body>
</HTML>
<%@ include file="/inc/page_end.inc" %>
