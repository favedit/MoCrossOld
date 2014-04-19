<%@ include file="/inc/page/begin.inc" %>
<jh:define form='sys.doc.catalog' alias='catalog_form'/>
<jh:define item='&catalog_form.node' alias='node'/>

<HTML>

<!-- Html Header Initialize ------------------------------->
<HEAD>
<js:meta/>
<js:css/>
<js:js/>
<jh:title caption='trs:this|title|Insert Document Node'/>
</HEAD>

<!-- Toolbar Initialize ----------------------------------->
<jc:toolbar name='top.frames.frmToolbar.oToolbar' inFrame='frmWork' inForm='frmConsole' target='frmWork' action='refresh'>
   <jc:tbButton caption='trs:sys|button.insert|Insert' pageAction='/sys/doc/catalog/InsertNodeSave.wa' icon='sys.save'/>
   <jc:tbButton caption='trs:sys|button.back|Back' pageAction='/sys/doc/catalog/Config.wa' icon='sys.back'/>
</jc:toolbar>

<!-- Body Initialize -------------------------------------->
<js:body styleClass='frmWork'>
<js:form name='frmConsole'>
<TABLE width='100%' cellspacing='8' border='0'><TR><TD>

<TABLE width='100%' border='0' cellspacing='0' cellpadding='4'>
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
   <TD nowrap><jh:memo item='&node.description' cols='40' rows='4'/></TD>
</TR>
</TABLE>

</TD></TR></TABLE>
</js:form>
</js:body>
</HTML>
<%@ include file="/inc/page/end.inc" %>
