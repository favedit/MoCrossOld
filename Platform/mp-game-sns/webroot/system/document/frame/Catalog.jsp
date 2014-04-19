<%@ include file='/apl/public.inc' %>
<HTML>

<HEAD>
<META http-equiv='content-type' content='text/html; charset=UTF-8'>
<jh:css css='/css/jp.css'/>
<SCRIPT>top.document.title="FavInfo"</SCRIPT>
</HEAD>
<js:js>
<jh:js src='/js/mo_ctl_tree.js'/>
</js:js>

<SCRIPT language='javascript'>
var oTreeView = null;
function __onNodeClick(oNode){
   var sType = oNode.xmlNode.getAttribute('type');
   if(sType == 'sys.doc.itm'){
      var sNodeId = oNode.xmlNode.getAttribute('node_id');
      var sVersionId = oNode.xmlNode.getAttribute('version_id');
      top.frmWork.navigate(SystemManager.contextPath + '/doc/' + sNodeId + '-' + sVersionId + '/Index.html');
   }else{
      var oPageCtl = parent.frmToolbar.oPageCtl;
      oPageCtl.loadSheets(oNode);
   }
}
function treeInitialize(){
   oTreeView = new FTreeView(this);
   oTreeView.document = document;
   oTreeView.linkHTMLObject = id_oTreeView;
   oTreeView.treeURL = '<jh:context/>/dev.doc.catalog.wss';
   //oTreeView.treeURL = '<jh:context/>/dev.doc.tree.wss';
   oTreeView.onNodeClick = __onNodeClick;
   oTreeView.loadNode();
}

</SCRIPT>

<js:body styleClass='frmCatalog' onload='treeInitialize();'>
<FORM name='frmConsole' method='POST'>
<INPUT type='hidden' name='node_xml' value=''>
<DIV id='id_oTreeView'></DIV>
</FORM>
</js:body>

</HTML>

