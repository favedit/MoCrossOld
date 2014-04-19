<%@ include file='/apl/public.inc' %>

<HTML>
<!-- Html Header Initialize ------------------------------->
<HEAD>
<js:meta/>
<js:css/>
<js:js/>
<jh:title caption='FavInfo'/>
</HEAD>

<jc:toolbar name='top.frames.frmToolbar.oToolbar' inFrame='frmWork' inForm='frmConsole' target='frmWork' action='refresh'/>

<SCRIPT>
function initialize(){
   var oTvNode = top.frmCatalog.oTreeView.focusNode;
   if(oTvNode){
      var sWinName = oTvNode.config.attribute('window');
      WindowManager.createWindow(window, sWinName);
   }
}
</SCRIPT>

<!-- Body Initialize -------------------------------------->
<js:body onload='initialize();' scroll='auto' styleClass='frmMDI'>
<js:form name='frmConsole' dataForm='top.frmToolbar.frmConsole'>
<!-- Control Initialize ------------------------------>
</js:form>
</js:body>
</HTML>

