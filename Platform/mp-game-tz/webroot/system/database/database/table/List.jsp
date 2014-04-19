<%@ include file="/inc/page/begin.inc" %>
<jh:define form='wfc.database' alias='database_form'/>
<jh:define item='&database_form.tables' alias='tables'/>

<HTML>

<!-- Html Header Initialize ------------------------------->
<HEAD>
<js:meta/>
<js:css/>
<js:js/>
<jh:title caption='FavInfo'/>
</HEAD>

<!-- Toolbar Initialize ----------------------------------->
<jc:toolbar name='top.frames.frmToolbar.oToolbar' inFrame='frmWork' inForm='frmConsole' target='frmWork' action='refresh'>
   <jc:tbButton caption='trs:this|build.property|Build Property' pageAction='/system/database/table/BuildAllProperty.wa' icon='sys.edit'/>
   <jc:tbButton caption='trs:this|build.source|Build Source' pageAction='/system/database/table/BuildAllSource.wa' icon='sys.edit'/>
   <jc:tbButton caption='trs:this|build.translate|Build Translate' pageAction='/system/database/table/BuildAllTranslate.wa' icon='sys.edit'/>
</jc:toolbar>

<!-- Body Initialize -------------------------------------->
<js:body styleClass='frmWork'>
<jh:form name='frmConsole'>
<TABLE width='100%' cellspacing='8' border='0'><TR><TD>

<jh:hasChild item='&tables'>
<TABLE width='100%' border='0' cellspacing='1' cellpadding='2' bgcolor='#CCCCCC' class='table'>
<TR class='tableHead'>
   <TD nowrap width='240px'><jh:text value='trs:this|table.name|Table Name'/></TD>
   <TD nowrap width='30px' align='center'><jh:text value='trs:this|table.status|Status'/></TD>
   <TD nowrap width='30px' align='center'><jh:text value='trs:this|table.is_use|Is Use'/></TD>
   <TD nowrap><jh:text value='trs:this|table.label|Label'/></TD>
   <TD nowrap width='80px' align='center'><jh:text value='trs:this|table.created|Created Date'/></TD>
</TR>
<jh:loop item='&tables' alias='table' loopAlias='looper'>
   <jh:isPosition item='&looper' mod='2' position='0'><TR class='tableLine'></jh:isPosition>
   <jh:isPosition item='&looper' mod='2' position='1'><TR class='tableBody'></jh:isPosition>
   <TD nowrap><jh:img src='/res/img/db/tab.gif' align='absmiddle'/> <jh:item item='&table.table_name'/></TD>
   <TD align='center'><jh:check item='&table.status' displayOnly='Y' trueValue='Y' falseValue='N'/></TD>
   <TD align='center'><jh:check item='&table.is_use' displayOnly='Y' trueValue='Y' falseValue='N'/></TD>
   <TD nowrap><jh:item item='&table.label'/></TD>
   <TD nowrap align='center'><jh:date item='&table.created' displayOnly='Y' format='YYYY-MM-DD'/></TD>
</TR>
</jh:loop>
</TABLE>
</jh:hasChild>

</TD></TR></TABLE>
</jh:form>
</js:body>
</HTML>
<%@ include file="/inc/page/end.inc" %>
