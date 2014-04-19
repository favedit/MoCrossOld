<%@ include file='/apl/public.inc' %>

<HTML>
<!-- Html Header Initialize ------------------------------->
<HEAD>
<js:meta/>
<js:css/>
<js:js/>
<jh:js src='/js/mo_ext.js'/>
</HEAD>

<jc:toolbar name='top.frames.frmToolbar.oToolbar' inFrame='frmWork' inForm='frmConsole' target='frmWork' action='refresh'>
   <jc:tbButton caption='trs:sys|button.update|Update' action='oPtyCtl.saveResult()' icon='sys.update'/>
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
   var oTvNode = top.frmCatalog.oTreeView.focusNode;
   if(oTvNode){
      oPtyCtl = new FPropertyConfig();
      oPtyCtl.clientWindow = window;
      oPtyCtl.htmlBody = id_pty_cfg;
      oPtyCtl.propertyConfig = 'database|table.field';
      oPtyCtl.propertyType = 'table.field';
      oPtyCtl.propertyValue = oTvNode.config.attribute('table_name') + '|' + oTvNode.config.attribute('field_name');
      oPtyCtl.valueURL = '<jh:context/>/wfc.sys.database.ws';
      oPtyCtl.load();
   }
}
</SCRIPT>
</TD></TR>
</TABLE>

</js:form>
</js:body>
</HTML>

