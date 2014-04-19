<%@ include file="/inc/page/begin.inc" %>

<HTML>
<!-- Html Header Initialize ------------------------------->
<HEAD>
<js:meta/>
<js:css/>
<js:js/>
<jh:js src='/js/mo_ext.js'/>
</HEAD>

<SCRIPT>
frmWinView = parent.frmWinView;
frmWinCtlLst = parent.frmWinCtlLst;
frmWinCtlPty = parent.frmWinCtlPty;

var oPtyCtl = null;
function ptyCfgInitialize(){
   oPtyCtl = new FPropertyConfig();
   oPtyCtl.autoValue = false;
   oPtyCtl.clientWindow = window;
   oPtyCtl.htmlBody = id_pty_cfg;
   oPtyCtl.onValueChange = function(oOwner){
      frmWinView.oWindow.refresh(true);
   }
}
</SCRIPT>

<!-- Body Initialize -------------------------------------->
<js:body styleClass='frmWork' scroll='auto' onload='ptyCfgInitialize();'>
<FORM name="frmConsole" method='POST'>
<TABLE width='100%' border='0' cellspacing='0' cellpadding='8'>
<TR><TD>
<DIV id='id_pty_cfg'></DIV>
</TD></TR>
</TABLE>
</FORM>
</js:body>
</HTML>
<%@ include file="/inc/page/end.inc" %>

