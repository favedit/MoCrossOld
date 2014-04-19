<%@ include file="/inc/page/begin.inc" %>
<jh:define form='sys.control' alias='control_form'/>
<jh:define item='&control_form.windows' alias='windows'/>

<HTML>

<!-- Html Header Initialize ------------------------------->
<HEAD>
<js:meta/>
<js:css/>
<js:js/>
<jh:title caption='FavInfo'/>
</HEAD>

<!-- Action Initialize ----------------------------------->
<SCRIPT language='javascript'>
function actionDelete(){
   if(confirm("<jh:text value='trs:sys|info.confim.delete|Delete Confim' format='jmsg'/>")){
     frmConsole.action = '<jh:context/>/system/window/WinDeleteSave.wa';
     frmConsole.submit();
   }
}
</SCRIPT>

<!-- Toolbar Initialize ----------------------------------->
<jc:toolbar name='top.frames.frmToolbar.oToolbar' inFrame='frmWork' inForm='frmConsole' target='frmWork' action='refresh'>
   <jc:tbButton caption='trs:sys|button.node.delete|Delete' action='actionDelete()' icon='sys.delete'/>
   <jc:tbButton caption='trs:sys|button.back|Back' pageAction='/system/window/LstInfo.wa' icon='sys.back'/>
</jc:toolbar>

<!-- Body Initialize -------------------------------------->
<js:body styleClass='frmWork'>
<jh:form name='frmConsole'>
<TABLE width='100%' cellspacing='8' border='0'>
<TR><TD><jh:write item='&control_form.path'/></TD></TR>
<TR><TD>

<jh:hasChild item='&windows'>
<TABLE width='100%' border='0' cellspacing='1' cellpadding='2' class='table'>
<TR class='tableHead'>
   <TD nowrap width='20px' align='center'></TD>
   <TD nowrap width='160px'><jh:text value='trs:this|window.name|Name'/></TD>
   <TD nowrap><jh:text value='trs:this|window.label|Label'/></TD>
</TR>
<jh:loop item='&windows' alias='window'>
<TR class='tableLine'>
	<TD align='center'><jh:check item='&window.pty_select'/></TD>
   <TD nowrap>
     <jh:img src='/res/img/sys/ctl/ctl.gif' align='absmiddle'/> <jh:item item='&window.name'/>
   </TD>
   <TD nowrap><jh:item item='&window.label'/></TD>
</TR>
</jh:loop>
</TABLE>
</jh:hasChild>

</TD></TR>
</TABLE>
</jh:form>
</js:body>
</HTML>
<%@ include file="/inc/page/end.inc" %>