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

<!-- Toolbar Initialize ----------------------------------->
<jc:toolbar name='top.frames.frmToolbar.oToolbar' inFrame='frmWork' inForm='frmConsole' target='frmWork' action='refresh'>
   <jc:tbButton caption='trs:this|button.save|Save' pageAction='/system/property/FieldUpdateSave.wa' icon='sys.save'/>
   <jc:tbButton caption='|'/>
   <jc:tbButton caption='trs:sys|button.delete|Delete' pageAction='/system/property/FieldDelete.wa' icon='sys.delete'/>
</jc:toolbar>

<SCRIPT>
function initPage(){
   selDataType();
   selEditType();
}
function selDataType(){
   var sValue = frmConsole.id_datatype.value.toLowerCase();
   for(var n=0; n<id_data.length; n++){
      id_data[n].style.display = IString.isEmpty(sValue) ? 'none' : 'block';
   }
}
function selEditType(){
   var sValue = frmConsole.id_edittype.value.toLowerCase();
   for(var n=0; n<id_edit.length; n++){
      if(!IString.isEmpty(sValue)){
         id_edit[n].style.display = (id_edit[n].type.indexOf(sValue) == -1) ? 'none' : 'block';
      }else{
         id_edit[n].style.display = 'none';
      }
   }
}
</SCRIPT>

<!-- Body Initialize -------------------------------------->
<js:body styleClass='frmWork' scroll='auto' onload='initPage()'>
<js:form name='frmConsole' dataForm='top.frmToolbar.frmConsole'>

<!-- Page Control Initialize ------------------------------>
<TABLE width='100%' border='0' cellspacing='0' cellpadding='8'>
<TR><TD>
   <TABLE width='100%' border='0' cellspacing='0' cellpadding='2'>
   <TR>
      <TD width='60'>Name:</TD>
      <TD width='200'><jh:edit item='&field.name' size='20' maxlength='40'/></TD>
      <TD><jh:check item='&field.is_valid' default='Y'/>Is Valid</TD>
   </TR>
   </TABLE>
   <TABLE width='100%' border='0' cellspacing='0' cellpadding='2'>
   <TR>
      <TD width='60'>Label:</TD>
      <TD width='200'><jh:edit item='&field.label' size='30' maxlength='80'/></TD>
      <TD width='60'>Widths:</TD>
      <TD><jh:edit item='&field.disp_widths' size='10' maxlength='80'/></TD>
   </TR>
   </TABLE>
   <TABLE width='100%' border='0' cellspacing='0' cellpadding='2'>
   <TR>
      <TD width='60'></TD>
      <TD width='80'><jh:check item='&field.disp_label' default='Y'/>Label</TD>
      <TD width='80'><jh:check item='&field.is_readonly' default='N'/>Readonly</TD>
      <TD width='80'><jh:check item='&field.is_null' default='Y'/>Null</TD>
      <TD width='80'><jh:check item='&field.is_required' default='N'/>Required</TD>
      <TD><jh:check item='&field.is_wrap' default='Y'/>Wrap</TD>
   </TR>
   </TABLE>
   <HR style='height:1;'>
   <TABLE width='100%' border='0' cellspacing='0' cellpadding='2'>
   <TR>
      <TD width='60'>Data Type:</TD>
      <TD width='140'><jh:combo id='id_datatype' item='&field.data_type' source='lst:com.lang.data.type' empty='Y' onchange='selDataType(this)'/></TD>
      <TD id='id_data' width='60' style='display:none'>Size:</TD>
      <TD id='id_data' style='display:none'><jh:number item='&field.data_size' size='4' maxlength='8'/></TD>
      <TD></TD>
   </TR>
   </TABLE>
   <TABLE id='id_data' width='100%' border='0' cellspacing='0' cellpadding='2' style='display:none'>
   <TR>
      <TD width='60'></TD>
      <TD width='60'>Default:</TD>
      <TD><jh:edit item='&field.data_default' size='40' maxlength='80'/></TD>
   </TR>
   </TABLE>
   <HR style='height:1;'>
   <TABLE width='100%' border='0' cellspacing='0' cellpadding='2'>
   <TR>
      <TD width='60'>Edit Type:</TD>
      <TD><jh:combo id='id_edittype' item='&field.edit_type' source='lst:com.lang.edit.type' empty='Y' onchange='selEditType(this)'/></TD>
   </TR>
   </TABLE>
   <!-- CheckBox -->
   <TABLE id='id_edit' type='check' width='100%' border='0' cellspacing='0' cellpadding='2' style='display:none'>
   <TR>
      <TD width='60'>Check Box:</TD>
      <TD width='60'>True:</TD>
      <TD width='140'><jh:edit item='&field.edit_true' size='10' maxlength='80' default='Y'/></TD>
      <TD width='60'>False:</TD>
      <TD><jh:edit item='&field.edit_false' size='10' maxlength='80' default='N'/></TD>
   </TR>
   </TABLE>
   <!-- Number Field / Text Field -->
   <TABLE id='id_edit' type='number,edit' width='100%' border='0' cellspacing='0' cellpadding='2' style='display:none'>
   <TR>
      <TD width='60'>Edit:</TD>
      <TD width='60'>Size:</TD>
      <TD><jh:number item='&field.edit_size' size='4' maxlength='8'/></TD>
   </TR>
   </TABLE>
   <TABLE id='id_edit' type='number' width='100%' border='0' cellspacing='0' cellpadding='2' style='display:none'>
   <TR>
      <TD width='60'>Valid:</TD>
      <TD width='60'>Min Value:</TD>
      <TD width='140'><jh:number item='&field.valid_minvalue' size='4' maxlength='8'/></TD>
      <TD width='60'>Max Value:</TD>
      <TD><jh:number item='&field.valid_maxval' size='4' maxlength='8'/></TD>
   </TR>
   </TABLE>
   <TABLE id='id_edit' type='number,edit' width='100%' border='0' cellspacing='0' cellpadding='2' style='display:none'>
   <TR>
      <TD width='60'></TD>
      <TD width='60'>Min Length:</TD>
      <TD width='140'><jh:number item='&field.valid_minlen' size='4' maxlength='8'/></TD>
      <TD width='60'>Max Length:</TD>
      <TD><jh:number item='&field.valid_maxlen' size='4' maxlength='8'/></TD>
   </TR>
   </TABLE>
   <!-- ComboBox -->
   <TABLE id='id_edit' type='combo' width='100%' border='0' cellspacing='0' cellpadding='2' style='display:none'>
   <TR>
      <TD width='60'>Combo Box:</TD>
      <TD width='60'>Source:</TD>
      <TD width='200'><jh:edit item='&field.edit_source' size='40' maxlength='80'/></TD>
      <TD><jh:check item='&field.edit_empty'/>Empty</TD>
   </TR>
   </TABLE>
   <!-- Memo -->
   <TABLE id='id_edit' type='memo' width='100%' border='0' cellspacing='0' cellpadding='2' style='display:none'>
   <TR>
      <TD width='60'>Memo:</TD>
      <TD width='60'>Cols:</TD>
      <TD width='140'><jh:number item='&field.edit_cols' size='4' maxlength='8'/></TD>
      <TD width='60'>Rows:</TD>
      <TD><jh:edit item='&field.edit_rows' size='4' maxlength='8'/></TD>
   </TR>
   </TABLE>
   <HR style='height:1;'>
   <TABLE width='100%' border='0' cellspacing='0' cellpadding='2'>
   <TR>
      <TD width='60'>Note:</TD>
      <TD colspan='4'><jh:memo item='&field.note' cols='60' rows='2'/></TD>
   </TR>
   </TABLE>
</TD></TR>
</TABLE>

</js:form>
</js:body>
</HTML>

