<%@ include file="/inc/page/begin.inc" %>
<jh:define form='sys.doc.catalog' alias='catalog_form'/>
<jh:define item='&catalog_form.node_list' alias='node_list'/>

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
     frmConsole.action = '<jh:context/>/sys/doc/catalog/DeleteNodeSave.wa';
     frmConsole.submit();
   }
}
</SCRIPT>

<!-- Toolbar Initialize ----------------------------------->
<jc:toolbar name='top.frames.frmToolbar.oToolbar' inFrame='frmWork' inForm='frmConsole' target='frmWork' action='refresh'>
   <jc:tbButton caption='trs:sys|button.delete|Delete' action='actionDelete()' icon='sys.tvnd'/>
   <jc:tbButton caption='trs:sys|button.back|Back' pageAction='/sys/doc/catalog/Config.wa' icon='sys.back'/>
</jc:toolbar>

<!-- Body Initialize -------------------------------------->
<js:body styleClass='frmWork'>
<jh:form name='frmConsole'>
<TABLE width='100%' cellspacing='8' border='0'>
<TR><TD>

<jh:hasChild item='&node_list'>
<TABLE width='100%' border='0' cellspacing='1' cellpadding='2' class='table'>
<TR class='tableHead'>
   <TD nowrap width='20px' align='center'></TD>
   <TD nowrap width='80px' align='right'><jh:text value='trs:this|node_id|Node Id'/></TD>
   <TD nowrap width='160px'><jh:text value='trs:this|disp_name|Display Name'/></TD>
   <TD nowrap><jh:text value='trs:this|disp_label|Display Label'/></TD>
</TR>
<jh:loop item='&node_list' alias='node'>
<TR class='tableLine'>
	<TD align='center'><jh:check item='&node.pty_select'/></TD>
   <TD align='right' nowrap><jh:item item='&node.node_id'/></TD>
   <TD nowrap><jh:item item='&node.disp_name'/></TD>
   <TD nowrap><jh:item item='&node.disp_label'/></TD>
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