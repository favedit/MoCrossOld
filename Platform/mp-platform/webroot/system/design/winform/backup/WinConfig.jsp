<%@ include file="/inc/page/begin.inc" %>
<HTML>
<HEAD>
<js:meta/>
<js:css/>
<js:js/>
<SCRIPT>top.document.title='Manager'</SCRIPT>
</HEAD>

<SCRIPT>
var oWindowNode = null;
function frameInitialize(){
   // Initialize
   IEngine.clientInitialize();
   SystemManager.runMode = false;
   var oTvNode = top.frmCatalog.oTreeView.focusNode;
   if(oTvNode){
      var sWinName = oTvNode.config.attribute('window');
      oWindowNode = WindowManager.loadDefine(sWinName);
   }
   // Frame
   frmWinView.open(SystemManager.contextPath + '/system/window/WinConfigView.wa', '_self');
   frmWinCtlLst.open(SystemManager.contextPath + '/system/window/WinConfigTree.wa', '_self');
   frmWinCtlPty.open(SystemManager.contextPath + '/system/window/WinConfigProperty.wa', '_self');
}
function frameRealease(){
   SystemManager.runMode = true;
}
</SCRIPT>

<FRAMESET name='fstWindow' rows='*,160' border='0' frameborder='no' framespacing='2' onload='frameInitialize()' onunload='frameRealease()'>
   <jh:frame name='frmWinView' scrolling='auto' frameborder='no' style='border-bottom:1px gray solid'/>
   <FRAMESET name='fstWinCtl' cols='200,*' border='0' frameborder='no' framespacing='2' style='border-top:1px gray solid'>
      <jh:frame name='frmWinCtlLst' scrolling='auto' frameborder='no' style='border-right:1px gray solid'/>
      <jh:frame name='frmWinCtlPty' scrolling='auto' frameborder='no' style='border-left:1px gray solid'/>
   </FRAMESET>
</FRAMESET>

<NOFRAMES><BODY></BODY></NOFRAMES>
</HTML>
<%@ include file="/inc/page/end.inc" %>
