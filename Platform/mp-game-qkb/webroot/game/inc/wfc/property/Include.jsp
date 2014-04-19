<%@ include file='/apl/public.inc' %>
<jh:define form='wfc.inc.property' alias='property_form'/>
<jh:define item='&property_form.config' alias='config'/>
<jh:define item='&property_form.value' alias='value'/>

<SCRIPT language='javascript'>
function displayGroup(oGroup, oInfo){
   var bDisplay = (oInfo.style.display == 'none');
   oInfo.style.display = bDisplay ? 'block' : 'none';
   var oImg = oGroup.children[0].children[0].children[0].children[0].children[0];
   oImg.src = bDisplay ? ResourceManager.imgMinus() : ResourceManager.imgPlus();
}
</SCRIPT>

<jh:hasChild item='&config'><jh:loop item='&config' alias='group'>
<!-- Group start -->
<TABLE width='100%' border='0' cellspacing='2' cellpadding='0'>
   <TR><TD class='comPtyCtl_grp' onclick='displayGroup(this, id_group_<jh:item item='&group.name'/>)'>
      <TABLE border='0' cellspacing='0' cellpadding='0'>
         <TR>
            <TD width='20' align='center'><jh:img src='/res/img/sys/minus.gif' align='absmiddle'/></TD>
            <TD><jh:item item='&group.label'/></TD>
         </TR>
      </TABLE>
   <TR></TR><TR id='id_group_<jh:item item='&group.name'/>'><TD>
<jh:hasChild item='&config'>
<jh:loop item='&group' alias='field' loopAlias='flooper'>
<!-- Field -->
<jh:isPosition item='&flooper' position='first'>
<TABLE border='0' cellspacing='2' cellpadding='0'><TR><TD width='16'></TD>
</jh:isPosition>
<jh:isTrue item='&field.is_wrap'><jh:isPosition item='&flooper' position='after.first'>
</TR></TABLE>
<TABLE border='0' cellspacing='2' cellpadding='0'><TR><TD width='16'></TD>
</jh:isPosition></jh:isTrue>
   <TD width='<jh:item item='&field.width_label'/>'><jh:isTrue item='&field.disp_label'><jh:item item='&field.label'/>:</jh:isTrue></TD>
   <TD width='<jh:item item='&field.width_flag'/>' align='center'><jh:isTrue item='&field.is_required'>*</jh:isTrue></TD>
   <TD width='<jh:item item='&field.width_value'/>'><jc:editor item='&field' value='&value'/></TD>
</jh:loop>
</TR></TABLE>
</jh:hasChild>
</TD></TR></TABLE>
<!-- Group end -->
</jh:loop></jh:hasChild>

