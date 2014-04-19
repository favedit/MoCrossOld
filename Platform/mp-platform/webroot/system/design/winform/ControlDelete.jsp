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

<jc:toolbar name='top.frames.frmToolbar.oToolbar' inFrame='frmWork' inForm='frmConsole' target='frmWork' action='refresh'>
   <jc:tbButton caption='trs:this|button.save|Save' pageAction='/system/window/ControlDeleteSave.wa' icon='sys.save'/>
</jc:toolbar>

<!-- Body Initialize -------------------------------------->
<js:body styleClass='frmWork' scroll='auto'>
<js:form name='frmConsole' dataForm='top.frmToolbar.frmConsole'>

<!-- Page Control Initialize ------------------------------>
<TABLE width='100%' border='0' cellspacing='0' cellpadding='8'>
<TR><TD>
   Field:<BR>
   <TABLE width='100%' border='0' cellspacing='0' cellpadding='2'>
   <TR>
      <TD width='60'>Name:</TD>
      <TD><jh:item item='&control.name'/></TD>
   </TR>
   <TR>
      <TD>Label:</TD>
      <TD><jh:item item='&control.label'/></TD>
   </TR>
   <TR>
      <TD>Note:</TD>
      <TD><jh:item item='&control.note'/></TD>
   </TR>
</TD></TR>
</TABLE>

</js:form>
</js:body>
</HTML>

