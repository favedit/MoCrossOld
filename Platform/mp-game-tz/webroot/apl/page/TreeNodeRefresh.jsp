<%@ include file="/inc/page_begin.inc" %>

<jh:define source='[MessageForm]' alias='message_form'/>

<HTML>
<HEAD>
<js:meta/>
<js:css/>
<js:js/>
<jh:title caption='FavInfo'/>
</HEAD>

<!-- Body Initialize -------------------------------------->
<js:body styleClass='frmBody'>
<jh:form name='frmConsole'>
<jc:tvReloadNode source='&message_form.tree_node_property' jsTreeView='top.frames.frmCatalog.frames.frmCatalogInner.oTreeView' action='reload.click'/>
</jh:form>
</js:body>
</HTML>
<%@ include file="/inc/page_end.inc" %>
