<jh:notEmpty item='&page_controls'>

<SCRIPT language='javascript'>
var m_bPcExecute = false;
function _onPcSelect(sPcName){
   frmConsole.target = 'frmBody';
   frmConsole.action = '<jh:context/>/inc/Control.action?source=<jh:item item='&window_node.full_name'/>&action=update&page_action=refresh&pc_name=' + sPcName;
   frmConsole.submit();
}
</SCRIPT>

<TABLE border='0' cellspacing='0' cellpadding='0'>
<TR height='1px' class='table'>
   <TD width='1px'></TD>
   <jh:loop item='&page_controls' alias='pc_unit'><TD></TD></jh:loop>
   <TD></TD>
</TR>
<TR height='22px'>
<TD width='1px' class='table'></TD>
<TD nowrap align='center' bgcolor='#EFF3FF'>
<jh:img src='/res/img/sys/block.gif'/>
<jh:loop item='&page_controls' alias='pc_unit'>
   <jh:equals item='&pc_unit.name' value='&page_control.name'>
      <jh:item item='&pc_unit.disp_label'/>
   </jh:equals>
   <jh:notEquals item='&pc_unit.name' value='&page_control.name'>
      <A href='javascript:_onPcSelect("<jh:item item='&pc_unit.name'/>")'><jh:item item='&pc_unit.disp_label'/></A>
   </jh:notEquals>
   <jh:img src='/res/img/sys/block.gif'/>
</jh:loop>
</TD>
<TD width='1px' class='table'></TD>
</TR>
</TABLE>

<%@ include file="/inc/ctl/com/PageControl.inc" %>
</jh:notEmpty>