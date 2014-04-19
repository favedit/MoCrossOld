<%@ include file='/apl/public.inc' %>
<jh:define form='wfc.property' alias='property_form'/>
<jh:define item='&property_form.field' alias='field'/>

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
   <jc:tbButton caption='trs:sys|button.update|Update' pageAction='/system/property/FieldUpdate.wa' icon='sys.update'/>
   <jc:tbButton caption='trs:sys|button.delete|Delete' pageAction='/system/property/FieldDelete.wa' icon='sys.delete'/>
</jc:toolbar>

<!-- Body Initialize -------------------------------------->
<js:body styleClass='frmWork' scroll='auto'>
<js:form name='frmConsole' dataForm='top.frmToolbar.frmConsole'>

<!-- Page Control Initialize ------------------------------>
<TABLE width='100%' border='0' cellspacing='0' cellpadding='8'>
<TR><TD>
   FIELD<BR><BR>
   <jh:item item='&field.field_id'/><BR>
   <jh:item item='&field.label'/>
</TD></TR>
</TABLE>

</js:form>
</js:body>
</HTML>

