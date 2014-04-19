<%@ include file='/apl/public.inc' %>
<jh:define form='wfc.window' alias='window_form'/>
<jh:define item='&window_form.control' alias='control'/>

<HTML>
<!-- Html Header Initialize ------------------------------->
<HEAD>
<js:meta/>
<js:css/>
<js:js/>
<jh:js src='/js/mo_ext.js'/>
<jh:title caption='FavInfo'/>
</HEAD>

<!-- Toolbar Initialize ----------------------------------->
<jc:toolbar name='top.frames.frmToolbar.oToolbar' inFrame='frmWork' inForm='frmConsole' target='frmWork' action='refresh'>
   <jc:tbButton caption='trs:this|button.save|Save' pageAction='/system/window/ControlUpdateSave.wa' icon='sys.save'/>
   <jc:tbButton caption='|'/>
   <jc:tbButton caption='trs:sys|button.delete|Delete' pageAction='/system/window/ControlDelete.wa' icon='sys.delete'/>
</jc:toolbar>

<!-- Body Initialize -------------------------------------->
<js:body styleClass='frmWork' scroll='auto'>
<js:form name='frmConsole' dataForm='top.frmToolbar.frmConsole'>

<!-- Page Control Initialize ------------------------------>
<TABLE width='100%' border='0' cellspacing='0' cellpadding='8'>
<TR><TD>
   <TABLE width='100%' border='0' cellspacing='0' cellpadding='2'>
   <TR>
      <TD width='60'>Name:</TD>
      <TD width='200'><jh:edit item='&control.name' size='20' maxlength='40'/></TD>
      <TD><jh:check item='&control.is_valid' default='Y'/>Is Valid</TD>
   </TR>
   </TABLE>
   <TABLE width='100%' border='0' cellspacing='0' cellpadding='2'>
   <TR>
      <TD width='60'>Label:</TD>
      <TD><jh:edit item='&control.label' size='20' maxlength='80'/></TD>
   </TR>
   </TABLE>
   <TABLE width='100%' border='0' cellspacing='0' cellpadding='2'>
   <TR>
      <TD width='60'>Note:</TD>
      <TD colspan='4'><jh:memo item='&control.note' cols='60' rows='4'/></TD>
   </TR>
   </TABLE>
</TD></TR>
</TABLE>

</js:form>
</js:body>
</HTML>

