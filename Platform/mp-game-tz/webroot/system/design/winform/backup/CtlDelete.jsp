<%@ include file="/inc/page/begin.inc" %>
<jh:define form='sys.control' alias='control_form'/>
<jh:define item='&control_form.controls' alias='controls'/>

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
function goActionDelete(){
   if(confirm("<jh:write item='txt|sys|info.deleteconfim' format='jmsg'/>")){
     frmConsole.wa = '<jh:context/>/sys/ctl/win/DeleteControlSave.wa';
     frmConsole.submit();
   }
}
</SCRIPT>

<!-- Toolbar Initialize ----------------------------------->
<jc:toolbar name='top.frames.frmToolbar.oToolbar' inFrame='frmWork' inForm='frmConsole' target='frmWork' action='refresh'>
   <jc:tbButton caption='txt|sys|button.nodes.delete' action='goActionDelete()' icon='sys.tvnd'/>
   <jc:tbButton caption='txt|sys|button.back' pageAction='/sys/ctl/win/WindowConfig.wa' icon='sys.back'/>
</jc:toolbar>

<!-- Body Initialize -------------------------------------->
<js:body styleClass='frmWork'>
<jh:form name='frmConsole'>
<TABLE width='100%' cellspacing='8' border='0'>
<TR><TD><jh:write item='&control_form.path'/></TD></TR>
<TR><TD>

<jh:hasChild item='&controls'>
<TABLE width='100%' border='0' cellspacing='1' cellpadding='2' class='table'>
<TR class='tableHead'>
   <TD nowrap width='20px' align='center'></TD>
   <TD nowrap width='160px'><jh:text item='name' label='Name'/></TD>
   <TD nowrap><jh:text item='type' label='Type'/></TD>
</TR>
<jh:loop item='&controls' alias='control'>
<TR class='tableLine'>
	<TD align='center'><jh:check item='&control.pty_select'/></TD>
   <TD nowrap>
     <jh:img src='/res/img/sys/ctl/ctl.gif' align='absmiddle'/> <jh:item item='&control.name'/>
   </TD>
   <TD nowrap><jh:item item='&control.disp_label'/></TD>
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