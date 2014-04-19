<SCRIPT language='javascript'>
function _onInsert(){
   var sAction = '<jh:context/>/inc/Control.action';
   sAction += '?source=<jh:item item='&table_node.ctl_unit'/>';
   sAction += '&action=insert';
   sAction += '&prepare_pack=<jh:item item='{&table_node.prepare_pack}'/>';
   sAction += '&prior=<jh:item item='{&table_node.package}.{&table_node.name}'/>';
   frmConsole.target = 'frmBody';
   frmConsole.action = sAction;
   frmConsole.submit();
}
</SCRIPT>
<TABLE width='100%' border='0' cellspacing='1' cellpadding='4' class='table'>
<TR class='tableHead'>
<jh:loop item='&column_nodes' alias='column_node'>
<jh:isTrue item='&column_node.is_select'>
   <TD nowrap<jh:notEmpty item='&column_node.disp_width'> width='<jh:item item='&column_node.disp_width'/>px'</jh:notEmpty>>
      <jh:item item='&column_node.disp_label'/>
   </TD>
</jh:isTrue>
</jh:loop>
   <TD nowrap width='70px' align='center'>
      <a href="javascript:_onInsert()"><jh:write item='txt|sys|button.insert'/></a>
   </TD>
</TR>
</TR>
<jh:loop item='&dataset' alias='unit'>
<TR class='tableLine'>
<jh:loop item='&column_nodes' alias='column_node'>
<jh:isTrue item='&column_node.is_select'>
   <TD nowrap><jh:item item='&unit.{&column_node.data_column}'/></TD>
</jh:isTrue>
</jh:loop>
   <TD nowrap align='center'>
      <a href="javascript:_onUpdate('<jh:item item='&unit.key_pack'/>')"><jh:write item='txt|sys|button.update'/></a> |
      <a href="javascript:_onDelete('<jh:item item='&unit.key_pack'/>')"><jh:write item='txt|sys|button.delete'/></a>
   </TD>
</TR>
</jh:loop>
</TABLE>
