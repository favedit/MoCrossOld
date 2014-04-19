<%@ include file='/apl/public.inc' %>
<HTML>

<HEAD>
<js:meta/>
<js:css/>
<js:js/>
<jh:js src='/js/mo_ext.js'/>
</HEAD>

<SCRIPT language='javascript'>
var oTreeView = null;
function __onNodeClick(oNode){
   var oPageCtl = parent.frmToolbar.oPageCtl;
   oPageCtl.loadSheets(oNode);
}
function treeInitialize(){ 
   oTreeView = new FTreeView(this);
   oTreeView.document = document;
   oTreeView.linkHTMLObject = id_treeview;
   oTreeView.treeURL = '<jh:context/>/wfc.sys.tree.ws';
   oTreeView.onNodeClick = __onNodeClick;
   oTreeView.loadNode();
}
</SCRIPT>

<js:body styleClass='frmCatalog' onload='treeInitialize();'>
<FORM name='frmConsole' method='POST'>
<INPUT type='hidden' name='node_xml' value=''>
<DIV id='id_treeview'></DIV>
</FORM>
</js:body>

</HTML>
