<%@ include file="/inc/page/begin.inc" %>

<HTML>

<!-- Html Header Initialize ------------------------------->
<HEAD>
<js:meta/>
<js:css/>
<js:js/>
<jh:title caption='FavInfo'/>
</HEAD>

<jh:define source='[DB.DatabaseForm]' alias='database_form'/>
<jh:define source='[DB.ToolbarForm]' alias='toolbar_form'/>
<jh:define source='&database_form.data_set' alias='data_set'/>

<!-- Toolbar Initialize ----------------------------------->
<jc:toolbar inFrame='frmBody' inForm='frmConsole' target='frmBody' name='top.frames.frmToolBar.oToolBar' action='refresh'>
   <jh:equals source='&toolbar_form.support_sysstat' value='true'>
      <jc:tbButton caption='txt|db|Toolbar.Information.Parameter' pageAction='/db/oracle/info/Parameter.wa' icon='sys.edit'/>
   </jh:equals>
   <jh:equals source='&toolbar_form.support_parameter' value='true'>
      <jc:tbButton caption='txt|db|Toolbar.Information.SysStat' pageAction='/db/oracle/info/SysStat.wa' icon='sys.edit'/>
   </jh:equals>
</jc:toolbar>

<!-- Body Initialize -------------------------------------->
<js:body styleClass='frmBody'>
<jh:form name='frmConsole'>
<TABLE width='100%' cellspacing='8' border='0'>
<TR><TD>

<jh:notEmpty source='&data_set'>
<TABLE width='100%' border='0' cellspacing='1' cellpadding='2' bgcolor='#CCCCCC'>
<jh:loop source='&data_set' alias='unit'>
<TR bgcolor='#FFFFFF'>
   <TD nowrap><jh:img src='/res/img/sys/help.gif' align='absmiddle'/> <jh:source source='&unit.information'/></TD>
</TR>
</jh:loop>
</TABLE>
</jh:notEmpty>

</TD></TR></TABLE>
</jh:form>
</js:body>
</HTML>
<%@ include file="/inc/page/end.inc" %>
