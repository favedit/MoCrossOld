<%@ include file="/inc/page/begin.inc" %>
<jh:define form='sys.ctl.uml' alias='uml_form'/>
<jh:define item='&uml_form.node_list' alias='node_list'/>

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
   <jc:tbButton caption='trs:sys|button.upload|Upload' pageAction='/sys/ctl/rpt/Upload.wa' icon='sys.upload'/>
</jc:toolbar>

<!-- Body Initialize -------------------------------------->
<js:body styleClass='frmWork'>
<js:form name='frmConsole'>
<TABLE width='100%' cellspacing='8' border='0'><TR><TD>

<jh:hasChild item='&node_list'>
<TABLE width='100%' border='0' cellspacing='1' cellpadding='2' class='table'>
<TR class='tableHead'>
   <TD nowrap width='20px' align='right'><jh:text value='trs:this|node_id|Node Id'/></TD>
   <TD nowrap width='80px' align='right'><jh:text value='trs:this|node_type|Node Type'/></TD>
   <TD nowrap width='160px'><jh:text value='trs:this|disp_name|Display Name'/></TD>
   <TD nowrap><jh:text value='trs:this|disp_label|Display Label'/></TD>
   <TD nowrap><jh:text value='trs:this|disp_order|Display Order'/></TD>
</TR>
<jh:loop item='&node_list' alias='node'>
<TR class='tableLine'>
   <TD align='right' nowrap><jh:item item='&node.node_id'/></TD>
   <TD align='right' nowrap><jh:combo displayOnly='Y' item='&node.node_type' source='doc|catalog.node.type'/></TD>
   <TD nowrap><jh:item item='&node.disp_name'/></TD>
   <TD nowrap><jh:item item='&node.disp_label'/></TD>
   <TD nowrap><jh:number item='&node.disp_order' size='8'/></TD>
</TR>
</jh:loop>
</TABLE>
</jh:hasChild>

</TD></TR></TABLE>
</js:form>
</js:body>
</HTML>
<%@ include file="/inc/page/end.inc" %>
