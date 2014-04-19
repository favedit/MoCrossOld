<%@ include file='/apl/public.inc' %>
<jh:define form='sys.control' alias='control_form'/>
<jh:define item='&control_form.window' alias='window'/>
<jh:define item='&control_form.control_list' alias='control_list'/>

<HTML>
<!-- Html Header Initialize ------------------------------->
<HEAD>
<js:meta/>
<js:css/>
<js:js/>
<jh:title caption='FavInfo'/>
</HEAD>

<SCRIPT language='javascript'>
function doSource(){
   var oTvNode = top.frmCatalog.oTreeView.focusNode;
   if(oTvNode){
      var sWindow = oTvNode.config.attribute('window');
      var sUri = SystemManager.contextPath + '/sys/ctl/win/Source.wa?window=' + sWindow;
      window.open(sUri, '_blank', 'width=600,height=480,location=no,menubar=no,scrollbars=yes,titlebar=no,toolbar=no,status=no,resizable=yes');
   }
}
function doDownload(sType){
   var oTvNode = top.frmCatalog.oTreeView.focusNode;
   if(oTvNode){
      frmConsole.method = 'post';
      frmConsole.action = SystemManager.contextPath + '/sys/window.download.ws';
      frmConsole.submit();
   }
}
</SCRIPT>

<jc:toolbar name='top.frames.frmToolbar.oToolbar' inFrame='frmWork' inForm='frmConsole' target='frmWork' action='refresh'>
   <jc:tbButton caption='trs:sys|button.upload|Upload' pageAction='/sys/ctl/win/Upload.wa' icon='sys.upload'/>
   <jc:tbButton caption='trs:sys|button.download|Download' action='doDownload()' icon='sys.download'/>
</jc:toolbar>

<!-- Body Initialize -------------------------------------->
<js:body styleClass='frmWork' scroll='auto'>
<js:form name='frmConsole' dataForm='top.frmToolbar.frmConsole'>

<!-- Page Control Initialize ------------------------------>
<TABLE width='100%' border='0' cellspacing='0' cellpadding='8'>
<TR><TD>

<jh:item item='&window.name'/>
<BR><BR>

<jh:hasChild item='&control_list'>
<TABLE width='100%' border='0' cellspacing='1' cellpadding='2' class='table'>
<TR class='tableHead'>
   <TD nowrap width='160px'><jh:text value='trs:this|window.control.name|Name'/></TD>
   <TD nowrap><jh:text value='trs:this|window.control.name|Type'/></TD>
</TR>
<jh:loop item='&control_list' alias='control'>
<TR class='tableLine'>
   <TD nowrap>
     <jh:img src='/res/img/sys/ctl/ctl.gif' align='absmiddle'/> <jh:item item='&control.name'/>
   </TD>
   <TD nowrap><jh:item item='&control.type'/></TD>
</TR>
</jh:loop>
</TABLE>
</jh:hasChild>

</TD></TR>
</TABLE>

</js:form>
</js:body>
</HTML>

