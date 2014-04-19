<%@ include file="/inc/page/begin.inc" %>

<HTML>
<!-- Html Header Initialize ------------------------------->
<HEAD>
<js:meta/>
<js:css/>
<js:js/>
<jh:js src='/js/mo_ext.js'/>
</HEAD>

<SCRIPT language='javascript'>
function frame(sFrame){
   return eval('parent.' + sFrame);
}
var oCatalog = null;
function initCatalog(){
   oCatalog = new FCatalogCtl();
   oCatalog.clientWindow = window;
   oCatalog.htmlBody = id_html_catalog;
   oCatalog.defineNode = parent.oWindowNode;
   // Event
   oCatalog.onCreateNode = function(oNode, oUnit){
      oNode.caption = oUnit.attribute('name');
      oNode.icon = IString.equals(oUnit.name, 'Window') ? 'ctl.window' : 'ctl.' + oUnit.attribute('type');
   }
   oCatalog.onNodeClick = function(oNode){
      var oFrame = parent.frmWinCtlLst;
      if(window.event){
         if(oFrame.event.ctrlKey && oCatalog.focusNode){
            if(oCatalog.focusNode.parent == oNode.parent){
               // Swap xml node
               var oParent = oNode.parent;
               oParent.config.swapNode(oCatalog.focusNode.config, oNode.config);
               // Swap Html Node
               IHTML.removeChildren(oParent.htmlChildren);
               for(var n=0; n<oParent.config.nodes.length; n++){
                  oCatalog.build(oParent, oParent.config.nodes[n]);
               }
            }
         }
      }
      var oCfgNode = oNode.config;
      var oPtyCtl = frame('frmWinCtlPty').oPtyCtl;
      if(IString.equals(oCfgNode.name, 'Window')){
         oPtyCtl.propertyConfig = 'control|window';
      }else{
         oPtyCtl.propertyConfig = 'control|window.' + oCfgNode.attribute('type');
      }
      oPtyCtl.valueNode = oCfgNode;
      oPtyCtl.onLoadDefine = oPtyCtl.refresh;
      oPtyCtl.load();
   }
   oCatalog.build();
}</SCRIPT>

<!-- Body Initialize -------------------------------------->
<js:body styleClass='frmWork' scroll='auto' onload='initCatalog();'>
<TABLE border='0' cellpadding='0' cellspacing='2'>
<TR valign='top'>
   <TD><DIV id='id_html_catalog'></DIV></TD>
</TR>
<TABLE>
</js:body>
</HTML>

<%@ include file="/inc/page/end.inc" %>
