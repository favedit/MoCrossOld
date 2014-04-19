<%@ include file='/apl/public.inc' %>
<jh:define form='wfc.property' alias='property_form'/>
<jh:define item='&property_form.group' alias='group'/>
<jh:define item='&property_form.fields' alias='fields'/>

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
   <jc:tbButton caption='trs:this|button.save|Save' pageAction='/system/property/GroupUpdateSave.wa' icon='sys.save'/>
   <jc:tbButton caption='trs:this|button.save.order|Save Order' pageAction='/system/property/GroupUpdateOrder.wa' icon='sys.save'/>
   <jc:tbButton caption='trs:sys|button.delete|Delete' pageAction='/system/property/GroupDelete.wa' icon='sys.delete'/>
   <jc:tbButton caption='|'/>
   <jc:tbButton caption='trs:sys|button.insert.field|Insert Field' pageAction='/system/property/FieldInsert.wa' icon='sys.insert'/>
</jc:toolbar>

<!-- Body Initialize -------------------------------------->
<js:body styleClass='frmWork' scroll='auto'>
<js:form name='frmConsole' dataForm='top.frmToolbar.frmConsole'>

<!-- Page Control Initialize ------------------------------>
<TABLE width='100%' border='0' cellspacing='0' cellpadding='8'>
<TR><TD>
   <TABLE width='100%' border='0' cellspacing='0' cellpadding='2'>
   <TR>
      <TD>Name:</TD>
      <TD><jh:edit item='&group.name' size='40'/></TD>
   </TR>
   <TR>
      <TD>Label:</TD>
      <TD><jh:edit item='&group.label' size='60'/></TD>
   </TR>
   <TR>
      <TD>Note:</TD>
      <TD><jh:memo item='&group.note' cols='60' rows='4'/></TD>
   </TR>
   </TABLE>

   <jh:hasChild item='&fields'>
   <BR>
   <TABLE width='100%' border='0' cellspacing='1' cellpadding='2' class='table'>
      <TR class='tableHead'>
         <TD width='100'><jh:text value='trs:this|field.name|Name'/></TD>
         <TD width='100'><jh:text value='trs:this|field.label|Label'/></TD>
         <TD><jh:text value='trs:this|field.disporder|Display Order'/></TD>
      </TR>
      <jh:loop item='&fields' alias='field'>
      <TR class='tableLine'>
         <TD width='100'><jh:item item='&field.name'/></TD>
         <TD width='100'><jh:item item='&field.label'/></TD>
         <TD><jh:number item='&field.disp_order' size='2' maxlength='4'/></TD>
      </TR>
      </jh:loop>
   </TABLE>
   </jh:hasChild>
</TD></TR>
</TABLE>

</js:form>
</js:body>
</HTML>

