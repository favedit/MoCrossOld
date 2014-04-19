<%@ include file="/inc/page_begin.inc" %>
<HTML>

<!-- Html Header Initialize ------------------------------->
<HEAD>
<js:meta/>
<js:css/>
<js:js/>
<jh:title caption='FavInfo'/>
</HEAD>

<jh:define item='[DB.ToolbarForm]' alias='toolbar_form'/>
<jh:define item='[DB.DatabaseForm]' alias='database_form'/>
<jh:define item='&database_form.data_set' alias='dataset'/>

<!-- Toolbar Initialize ----------------------------------->
<jc:toolbar inFrame='frmBody' inForm='frmConsole' target='frmBody' name='top.frames.frmToolBar.oToolBar' action='refresh'>
   <jc:tbButton caption='txt|sys|button.update' pageAction='/db/oracle/info/Parameter.wa' icon='sys.edit'/>
</jc:toolbar>

<!-- Body Initialize -------------------------------------->
<js:body styleClass='frmBody'>
<jh:form name='frmConsole'>
<TABLE width='100%' cellspacing='8' border='0'><TR><TD>

<jh:notEmpty item='&dataset'>
<TABLE width='100%' border='0' cellspacing='1' cellpadding='2' bgcolor='#CCCCCC' class='table'>
<TR bgcolor='#E0E0FF'>
   <TD nowrap width='240px'>Table Name</TD>
   <TD align='right' nowrap width='60px'>Object Id</TD>
   <TD nowrap width='20px' align='center'>S</TD>
   <TD nowrap>Display Label</TD>
   <TD nowrap width='80px'>Created</TD>
</TR>
<jh:loop item='&dataset' alias='unit'>
   <jh:mod property='&dataset.position' modItem='2' equalsItem='0'><TR class='tableLine'></jh:mod>
   <jh:mod property='&dataset.position' modItem='2' equalsItem='1'><TR class='tableBody'></jh:mod>
   <TD nowrap><jh:img src='/res/img/db/tab.gif' align='absmiddle'/> <jh:item item='&unit.table_name'/></TD>
   <TD nowrap><jh:item item='&unit.object_id'/></TD>
   <TD align='center'><jh:check item='&unit.status' displayOnly='Y' trueValue='Y' falseValue='N'/></TD>
   <TD nowrap><jh:item item='&unit.display_label'/></TD>
   <TD nowrap><jh:item item='&unit.created'/></TD>
</TR>
</jh:loop>
</TABLE>
</jh:notEmpty>

</TD></TR></TABLE>
</jh:form>
</js:body>
</HTML>
<%@ include file="/inc/page_end.inc" %>
