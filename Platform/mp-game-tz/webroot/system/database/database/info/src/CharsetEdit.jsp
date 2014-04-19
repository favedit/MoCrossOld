<%@ include file="/inc/page_begin.inc" %>
<HTML>

<!-- Html Header Initialize ------------------------------->
<HEAD>
<js:meta/>
<js:css/>
<js:js/>
<jh:title caption='Table'/>
</HEAD>

<jh:define source='[DB.DatabaseForm]' alias='database_form'/>
<jh:define source='&database_form.database_node' alias='database_node'/>

<!-- Toolbar Initialize ----------------------------------->
<jc:toolbar inFrame='frmBody' inForm='frmConsole' target='frmBody' name='top.frames.frmToolBar.oToolBar' action='refresh'>
   <jc:tbButton caption='txt|sys|ToolBar.Save' pageAction='/db/oracle/db/src/InfoSave.wa' icon='sys.save'/>
   <jc:tbButton caption='txt|sys|ToolBar.Back' pageAction='/db/oracle/db/src/Info.wa' icon='sys.back'/>
</jc:toolbar>

<!-- Body Initialize -------------------------------------->
<js:body styleClass='frmBody'>
<jh:form name='frmConsole'>
<TABLE width='100%' cellspacing='8' border='0'><TR><TD>

<TABLE width='100%' border='0' cellspacing='1' cellpadding='2' bgcolor='#CCCCCC' class='table'>
<TR>
    <TD nowrap width='200px' class='tableHead'><jh:img src='/res/img/sys/tvn.gif' align='absmiddle'/> <jh:write source='txt|db.oracle|DB.Source.LUFileCharset'/></TD>
    <TD nowrap class='tableLine'><jh:combo source='&database_node.lu_file_charset' source='code|sys|File.Charset'/></TD>
</TR>
<TR>
    <TD nowrap width='200px' class='tableHead'><jh:img src='/res/img/sys/tvn.gif' align='absmiddle'/> <jh:write source='txt|db.oracle|DB.Source.PKGFileCharset'/></TD>
    <TD nowrap class='tableLine'><jh:combo source='&database_node.pkg_file_charset' source='code|sys|File.Charset'/></TD>
</TR>
</TABLE>

</TD></TR></TABLE>
</jh:form>
</js:body>
</HTML>
<%@ include file="/inc/page_end.inc" %>
