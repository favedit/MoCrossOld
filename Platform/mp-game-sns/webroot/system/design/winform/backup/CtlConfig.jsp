<%@ include file='/apl/public.inc' %>

<HTML>
<!-- Html Header Initialize ------------------------------->
<HEAD>
<js:meta/>
<js:css/>
<js:js/>
<jh:js src='/js/ctl_com.js'/>
<jh:js src='/js/xhtml.js'/>
<jh:title caption='FavInfo'/>
</HEAD>

<jc:toolbar name='top.frames.frmToolbar.oToolbar' inFrame='frmWork' inForm='frmConsole' target='frmWork' action='refresh'>
   <jc:tbButton caption='txt|sys|button.update' action='oPtyCfgCtl.saveResult()' icon='sys.update'/>
</jc:toolbar>

<!-- Body Initialize -------------------------------------->
<js:body styleClass='frmWork' onload='ptyCfgInitialize();' scroll='auto'>
<js:form name='frmConsole' dataForm='top.frmToolbar.frmConsole'>

<!-- Page Control Initialize ------------------------------>
<TABLE width='100%' border='0' cellspacing='0' cellpadding='8'>
<TR><TD>
<DIV id='id_pty_cfg'></DIV>
<SCRIPT>
var oPtyCfgCtl = null;
function ptyCfgInitialize(){
   var oNode = IXML.makeNode(frmConsole.node_xml.value);
   oPtyCfgCtl = newInstance('LH.Property.Control');
   oPtyCfgCtl.propertyAction = 'Control';
   oPtyCfgCtl.propertyId = oNode.getAttribute('window') + '|' + oNode.getAttribute('control');
   oPtyCfgCtl.linkHTML = id_pty_cfg;
   oPtyCfgCtl.configURL = '<jh:context/>/sys.pty.cfg.wss';
   oPtyCfgCtl.valueURL = '<jh:context/>/sys.ctl.window.value.wss';
   oPtyCfgCtl.saveValueURL = '<jh:context/>/sys.ctl.window.value.wss';
   oPtyCfgCtl.load('control|window.control');
}
</SCRIPT>
</TD></TR>
</TABLE>

</js:form>
</js:body>
</HTML>

