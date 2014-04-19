<%@ include file='/apl/public.inc' %>
<jh:define form='sys.control' alias='control_form'/>

<HTML>
<!-- Html Header Initialize ------------------------------->
<HEAD>
<js:meta/>
<js:css/>
<js:js/>
<jh:js src='/js/ctl_com.js'/>
<jh:js src='/js/ctl_html.js'/>
<jh:js src='/js/ctl_frame.js'/>
<jh:js src='/js/ctl_pty.js'/>
<jh:title caption='FavInfo'/>
</HEAD>

<jc:toolbar name='top.frames.frmToolbar.oToolbar' inFrame='frmWork' inForm='frmConsole' target='frmWork' action='refresh'>
   <jc:tbButton caption='trs:sys|button.update|Update' action='oPtyCtl.saveResult()' icon='sys.update'/>
   <jc:tbButton caption='|'/>
   <jc:tbButton caption='trs:sys|button.import|Import' pageAction='/sys/ctl/win/ImportField.wa' icon='sys.tvni'/>
   <jc:tbButton caption='|'/>
   <jc:tbButton caption='trs:sys|button.node.insert|Node Insert' pageAction='/sys/ctl/win/InsertField.wa' icon='sys.tvni'/>
   <jc:tbButton caption='trs:sys|button.node.delete|Node Delete' pageAction='/sys/ctl/win/DeleteField.wa' icon='sys.tvnd'/>
</jc:toolbar>

<!-- Body Initialize -------------------------------------->
<js:body styleClass='frmWork' onload='ptyCfgInitialize();' scroll='auto'>
<js:form name='frmConsole' dataForm='top.frmToolbar.frmConsole'>

<!-- Page Control Initialize ------------------------------>
<TABLE width='100%' border='0' cellspacing='0' cellpadding='8'>
<TR><TD>
<DIV id='id_pty_cfg'></DIV>
<SCRIPT>
var oPtyCtl = null;
function ptyCfgInitialize(){
   var oNode = IXML.makeNode(frmConsole.node_xml.value);
   oPtyCtl = newInstance('Ctl.Property');
   oPtyCtl.bodyHTML = id_pty_cfg;
   oPtyCtl.propertyConfig = 'control|window';
   oPtyCtl.propertyType = 'window';
   oPtyCtl.propertyValue = oNode.getAttribute('window');
   oPtyCtl.valueURL = '<jh:context/>/wfc.sys.window.ws';
   oPtyCtl.load();
}
</SCRIPT>
</TD></TR>
</TABLE>

</js:form>
</js:body>
</HTML>

