<%@ include file='/apl/public.inc' %>
<jh:define form='sys.control' alias='control_form'/>
<jh:define item='&control_form.dataset' alias='dataset'/>
<jh:define item='&control_form.field_list' alias='field_list'/>

<HTML>
<!-- Html Header Initialize ------------------------------->
<HEAD>
<js:meta/>
<js:css/>
<js:js top='Y'/>
<jh:title caption='Dataset Fields'/>
</HEAD>

<SCRIPT>
function addControls(){
   var oWindow = dialogArguments['parent'];
   var sDsName = dialogArguments['dataset'];
   var arFldNames = frmConsole.fieldName;
   var arNames = frmConsole.controlName;
   var arTypes = frmConsole.controlType;
   if(arNames.length){
      for(var n=0; n<arNames.length; n++){
         var sType = arTypes[n].value;
         var sName = arNames[n].value;
         if(sType == 'edit'){
            sName = 'edt' + sName;
         }else if(sType == 'datepicker'){
            sName = 'dtp' + sName;
         }else if(sType == 'combobox'){
            sName = 'cbo' + sName;
         }else if(sType == 'selcombobox'){
            sName = 'scb' + sName;
         }else if(sType == 'column'){
            sName = 'col' + sName;
         }
         oWindow.insertDbControl(sDsName, sType, sName, arFldNames[n].value);
      }
   }
   window.close();
}
</SCRIPT>

<!-- Body Initialize -------------------------------------->
<js:body styleClass='frmWork' jstop='Y' scroll='auto'>
<js:form name='frmConsole'>

<!-- Page Control Initialize ------------------------------>
<TABLE width='100%' border='0' cellspacing='0' cellpadding='8'>
<TR><TD>

<jh:item item='&dataset.name'/>
<BR><BR>

<jh:hasChild item='&field_list'>
<TABLE width='100%' border='0' cellspacing='1' cellpadding='2' class='table'>
<TR class='tableHead'>
   <TD nowrap width='20px'><INPUT type='checkbox' name='fieldSelectall' class='selCheck' checked='true'></TD>
   <TD nowrap width='140px'><jh:text value='trs:this|field.name|Field Name'/></TD>
   <TD nowrap width='100px'><jh:text value='trs:this|control.name|Control Type'/></TD>
   <TD nowrap width='1px'><jh:text value='trs:this|control.name|Control Name'/></TD>
   <TD nowrap><jh:text value='trs:this|field.label|Label'/></TD>
</TR>
<jh:loop item='&field_list' alias='field'>
<TR class='tableLine'>
   <TD nowrap><INPUT type='checkbox' name='fieldSelect' class='selCheck' checked='true'></TD>
   <TD nowrap>
     <jh:img src='/res/img/sys/ctl/ctl.gif' align='absmiddle'/> <jh:item item='&field.name'/>
     <jh:edit type='hidden' name='fieldName' item='&field.control_date_name' size='20'/>
   </TD>
   <TD nowrap><jh:combo name='controlType' item='&field.control_type' source='sys|window.control'/></TD>
   <TD nowrap><jh:edit name='controlName' item='&field.control_name' size='20'/></TD>
   <TD nowrap><jh:item item='&field.label'/></TD>
</TR>
</jh:loop>
</TABLE>
<BR>
<BR>
<DIV><CENTER>
   <A href='#' onclick='addControls()'>Add Select Controls</A>
</CENTER></DIV>
</jh:hasChild>

</TD></TR>
</TABLE>

</js:form>
</js:body>
</HTML>

