<%@ include file='/apl/public.inc' %>
<jh:define form='wfc.property' alias='property_form'/>
<jh:define item='&property_form.property' alias='property'/>
<jh:define item='&property_form.groups' alias='groups'/>

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
   <jc:tbButton caption='trs:this|button.save|Save' pageAction='/system/property/PropertyUpdateSave.wa' icon='sys.save'/>
   <jc:tbButton caption='trs:this|button.save.order|Save Order' pageAction='/system/property/PropertyUpdateOrder.wa' icon='sys.save'/>
   <jc:tbButton caption='trs:sys|button.delete|Delete' pageAction='/system/property/PropertyDelete.wa' icon='sys.delete'/>
   <jc:tbButton caption='|'/>
   <jc:tbButton caption='trs:sys|button.build.source|Build Source' pageAction='/system/property/PropertyBuildSource.wa' icon='sys.cfg'/>
   <jc:tbButton caption='|'/>
   <jc:tbButton caption='trs:sys|button.insert.group|Insert Group' pageAction='/system/property/GroupInsert.wa' icon='sys.insert'/>
</jc:toolbar>

<!-- Body Initialize -------------------------------------->
<js:body styleClass='frmWork' scroll='auto'>
<js:form name='frmConsole' dataForm='top.frmToolbar.frmConsole'>

<!-- Page Control Initialize ------------------------------>
<TABLE width='100%' border='0' cellspacing='0' cellpadding='8'>
<TR><TD>
   Property:<BR>
   <TABLE width='100%' border='0' cellspacing='0' cellpadding='2'>
   <TR>
      <TD>Name:</TD>
      <TD><jh:edit item='&property.name' size='40'/></TD>
   </TR>
   <TR>
      <TD>Label:</TD>
      <TD><jh:edit item='&property.label' size='60'/></TD>
   </TR>
   <TR>
      <TD>Widths:</TD>
      <TD><jh:edit item='&property.disp_widths' size='40'/></TD>
   </TR>
   <TR>
      <TD>Note:</TD>
      <TD><jh:memo item='&property.note' cols='60' rows='2'/></TD>
   </TR>
   </TABLE>

   <jh:hasChild item='&groups'>
   <BR>
   <TABLE width='100%' border='0' cellspacing='1' cellpadding='2' class='table'>
      <TR class='tableHead'>
         <TD width='100'><jh:text value='trs:this|group.name|Name'/></TD>
         <TD width='100'><jh:text value='trs:this|group.label|Label'/></TD>
         <TD><jh:text value='trs:this|group.disporder|Display Order'/></TD>
      </TR>
      <jh:loop item='&groups' alias='group'>
      <TR class='tableLine'>
         <TD width='100'><jh:item item='&group.name'/></TD>
         <TD width='100'><jh:item item='&group.label'/></TD>
         <TD><jh:number item='&group.disp_order' size='2' maxlength='4'/></TD>
      </TR>
      </jh:loop>
   </TABLE>
   </jh:hasChild>

</TD></TR>
</TABLE>


</js:form>
</js:body>
</HTML>

