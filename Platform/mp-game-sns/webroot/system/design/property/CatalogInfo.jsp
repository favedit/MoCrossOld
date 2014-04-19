<%@ include file='/apl/public.inc' %>
<jh:define form='wfc.property' alias='property_form'/>
<jh:define item='&property_form.catalog' alias='catalog'/>

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
   <jh:notEmpty item='&property_form.catalog_id'>
      <jc:tbButton caption='trs:sys|button.insert.property|Insert Property' pageAction='/system/property/PropertyInsert.wa' icon='sys.insert'/>
      <jc:tbButton caption='|'/>
   </jh:notEmpty>
   <jc:tbButton caption='trs:sys|button.insert|Insert' pageAction='/system/property/CatalogInsert.wa' icon='sys.insert'/>
   <jc:tbButton caption='trs:sys|button.update|Update' pageAction='/system/property/CatalogUpdate.wa' icon='sys.update'/>
   <jc:tbButton caption='trs:sys|button.delete|Delete' pageAction='/system/property/CatalogDelete.wa' icon='sys.delete'/>
</jc:toolbar>

<!-- Body Initialize -------------------------------------->
<js:body styleClass='frmWork' scroll='auto'>
<js:form name='frmConsole' dataForm='top.frmToolbar.frmConsole'>

<!-- Page Control Initialize ------------------------------>
<TABLE width='100%' border='0' cellspacing='0' cellpadding='8'>
<TR><TD>
   CATALOG<BR><BR>
   <jh:item item='&catalog.catalog_id'/><BR>
   <jh:item item='&catalog.label'/>
</TD></TR>
</TABLE>

</js:form>
</js:body>
</HTML>

