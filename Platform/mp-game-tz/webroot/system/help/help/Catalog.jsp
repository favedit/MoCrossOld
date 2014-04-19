<%@ include file='/apl/public.inc' %>
<HTML>

<HEAD>
<META http-equiv='content-type' content='text/html; charset=UTF-8'>
<jh:css css='/css/jp.css'/>
<SCRIPT>top.document.title="FavInfo"</SCRIPT>
</HEAD>

<jh:js src='/js/lang.js'/>
<jh:js src='/js/ctl_win.js'/>
<jh:js src='/js/ctl_tree.js'/>
<jh:js src='/js/frame.js'/>
<jh:js src='/js/system.js'/>
<SCRIPT language='javascript'>
IResource.init("<jh:context/>/res");
</SCRIPT>

<BODY bgcolor='#FFFFFF' text='#000000' leftmargin='0' topmargin='0' marginwidth='0' marginheight='0' scroll='auto'>
<FORM name='frmConsole' method='POST'>
<INPUT type='hidden' name='node_action' value=''>
<INPUT type='hidden' name='node_uuid' value=''>
<INPUT type='hidden' name='node_attribute' value=''>
<INPUT type='hidden' name='node_xml' value=''>
<DIV id='id_oTreeView'></DIV>
</FORM>
</BODY>

<SCRIPT language='javascript'>
var oTreeView = null;
function __onNodeClick(oNode){
   frmConsole.wa = '<jh:context/>/mgr/db/Toolbar.wa';
   frmConsole.target = 'frmToolbar';
   frmConsole.node_xml.value = oNode.xmlNode.xml;
   frmConsole.submit();
}
function tvPageInitialize(){
   oTreeView = newInstance('LH.Tree');
   oTreeView.document = document;
   oTreeView.linkHTMLObject = id_oTreeView;
   oTreeView.remoteURL = '<jh:context/>/mgr.db.catalog.wss';
   oTreeView.onNodeClick = __onNodeClick;
   oTreeView.loadNode();
}
tvPageInitialize();
</SCRIPT>

</HTML>

