<%@ include file="/inc/page/begin.inc" %>
<jh:define form='sys.doc.catalog' alias='catalog_form'/>
<jh:define item='&catalog_form.node' alias='node'/>
<jh:define item='&catalog_form.node_list' alias='node_list'/>

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
   <jc:tbButton caption='trs:sys|button.update|Update' pageAction='/sys/doc/catalog/UpdateNodeSave.wa' icon='sys.save'/>
   <jc:tbButton caption='trs:sys|button.update.order|Update Order' pageAction='/sys/doc/catalog/UpdateOrderSave.wa' icon='sys.save'/>
   <jc:tbButton caption='|'/>
   <jc:tbButton caption='trs:sys|button.insert|Insert' pageAction='/sys/doc/catalog/InsertNode.wa' icon='sys.insert'/>
   <jc:tbButton caption='trs:sys|button.delete|Delete' pageAction='/sys/doc/catalog/DeleteNode.wa' icon='sys.delete'/>
   <jc:tbButton caption='|'/>
   <jc:tbButton caption='trs:sys|button.upload|Upload' pageAction='/sys/doc/catalog/UploadNode.wa' icon='sys.upload'/>
</jc:toolbar>

<!-- Body Initialize -------------------------------------->
<js:body styleClass='frmWork'>
<js:form name='frmConsole'>
<TABLE width='100%' cellspacing='8' border='0'><TR><TD>

<TABLE width='100%' border='0' cellspacing='0' cellpadding='4'>
<TR>
   <TD nowrap><jh:text value='trs:dbi|tables.doc_catalog_node_ds.node_id|Node Id'/></TD>
   <TD width='10px'>*</TD>
   <TD nowrap><jh:item item='&node.node_id'/></TD>
</TR>
<TR>
   <TD nowrap><jh:text value='trs:dbi|tables.doc_catalog_node_ds.node_type|Node Type'/></TD>
   <TD width='10px'>*</TD>
   <TD nowrap><jh:combo item='&node.node_type' source='doc|catalog.node.type'/></TD>
</TR>
<TR>
   <TD nowrap><jh:text value='trs:dbi|tables.doc_catalog_node_ds.disp_name|Display Name'/></TD>
   <TD width='10px'>*</TD>
   <TD nowrap><jh:edit item='&node.disp_name' size='40' maxlength='60'/></TD>
</TR>
<TR>
   <TD nowrap><jh:text value='trs:dbi|tables.doc_catalog_node_ds.disp_label|Display Label'/></TD>
   <TD></TD>
   <TD nowrap><jh:edit item='&node.disp_label' size='40' maxlength='60'/></TD>
</TR>
<TR>
   <TD nowrap><jh:text value='trs:dbi|tables.doc_catalog_node_ds.description|Description'/></TD>
   <TD></TD>
   <TD nowrap><jh:memo item='&node.description' cols='40' rows='6'/></TD>
</TR>
</TABLE><BR>

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
