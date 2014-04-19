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
      <jc:tbButton caption='trs:this|button.save|Save' pageAction='/system/property/CatalogUpdateSave.wa' icon='sys.save'/>
      <jc:tbButton caption='trs:sys|button.delete|Delete' pageAction='/system/property/CatalogDelete.wa' icon='sys.delete'/>
      <jc:tbButton caption='|'/>
   </jh:notEmpty>
   <jc:tbButton caption='trs:sys|button.insert|Insert Catalog' pageAction='/system/property/CatalogInsert.wa' icon='sys.insert'/>
   <jh:empty item='&property_form.catalog_id'>
      <jc:tbButton caption='|'/>
      <jc:tbButton caption='trs:sys|button.build.source|Build Source' pageAction='/system/property/BuildSource.wa' icon='sys.cfg'/>
   </jh:empty>
   <jh:notEmpty item='&property_form.catalog_id'>
      <jc:tbButton caption='trs:sys|button.insert.property|Insert Property' pageAction='/system/property/PropertyInsert.wa' icon='sys.insert'/>
   </jh:notEmpty>
</jc:toolbar>

<!-- Body Initialize -------------------------------------->
<js:body styleClass='frmWork' scroll='auto'>
<js:form name='frmConsole' dataForm='top.frmToolbar.frmConsole'>

<!-- Page Control Initialize ------------------------------>
<TABLE width='100%' border='0' cellspacing='0' cellpadding='8'>
<TR><TD>
   <jh:notEmpty item='&property_form.catalog_id'>
   <TABLE width='100%' border='0' cellspacing='0' cellpadding='2'>
   <TR>
      <TD>Name:</TD>
      <TD><jh:edit item='&catalog.name' size='40'/></TD>
   </TR>
   <TR>
      <TD>Label:</TD>
      <TD><jh:edit item='&catalog.label' size='60'/></TD>
   </TR>
   <TR>
      <TD>Note:</TD>
      <TD><jh:memo item='&catalog.note' cols='60' rows='4'/></TD>
   </TR>
   </TABLE>
   </jh:notEmpty>
</TD></TR>
</TABLE>

</js:form>
</js:body>
</HTML>

