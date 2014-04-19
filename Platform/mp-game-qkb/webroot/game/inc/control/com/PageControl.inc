<jh:notEmpty item='&page_control'>
<jh:define item='&page_control.node' alias='pc_node'/>
<jh:define item='&page_control.table_node' alias='pc_window_node'/>
<jh:define item='&page_control.column_nodes' alias='pc_column_nodes'/>
<jh:define item='&page_control.dataset' alias='pc_dataset'/>

<SCRIPT language='javascript'>
var m_bPcExecute = false;
function _onPcInsert(){
   frmConsole.target = 'frmBody';
   frmConsole.action = '<jh:context/>/inc/Control.action?source=<jh:item item='&pc_window_node.unit_window'/>&action=insert&page_action=&prepare_pack=<jh:item item='{&pc_node.prepare_pack}'/>';
   frmConsole.submit();
}
function _onPcUpdate(sPack){
   if(!event.ctrlKey){
      if(!m_bPcExecute){
         frmConsole.target = 'frmBody';
         frmConsole.action = '<jh:context/>/inc/Control.action?source=<jh:item item='&pc_window_node.unit_window'/>&action=update&page_action=&key_pack=' + sPack;
         frmConsole.submit();
      }
      m_bPcExecute = true;
   }
}
function _onPcDelete(sPack){
   if(!m_bPcExecute){
      frmConsole.target = 'frmBody';
      frmConsole.action = '<jh:context/>/inc/Control.action?source=<jh:item item='&pc_window_node.unit_window'/>&action=delete&page_action=&key_pack=' + sPack;
      frmConsole.submit();
   }
   m_bPcExecute = true;
}
</SCRIPT>

<TABLE width='100%' border='0' cellspacing='1' cellpadding='4' class='table'>
<TR class='tableHead'>
   <jh:isTrue item='&pc_window_node.can_delete'><jh:notEquals item='&window_action' value='delete'>
      <TD class='tableHeadCell' nowrap width='32px' align='center'>
         <a href="javascript:_onPcInsert()"><jh:write item='txt|sys|button.insert'/></a>
      </TD>
   </jh:notEquals></jh:isTrue>
<jh:loop item='&pc_column_nodes' alias='pc_column_node'>
<jh:isTrue item='&pc_column_node.disp_select'>
   <TD class='tableHeadCell' nowrap<jh:notEmpty item='&pc_column_node.disp_width'> width='<jh:item item='&pc_column_node.disp_width'/>px'</jh:notEmpty><jh:notEmpty item='&pc_column_node.disp_align_h'> align='<jh:item item='&pc_column_node.disp_align_h'/>'</jh:notEmpty>>
      <jh:item item='&pc_column_node.disp_label'/>
   </TD>
</jh:isTrue>
</jh:loop>
</TR>
<jh:loop item='&pc_dataset' alias='unit'>
<TR class='tableLine' <jh:notEquals item='&window_action' value='delete'><jh:isTrue item='&pc_window_node.can_update'>onmouseover='obj.html.li(this)' onmouseout='obj.html.lo(this)' onclick='_onPcUpdate("<jh:item item='&unit.key_pack'/>")' style='cursor:hand'</jh:isTrue></jh:notEquals><jh:notEmpty item='&column_node.disp_align_v'> valign='<jh:item item='&column_node.disp_align_v'/>'</jh:notEmpty>>
   <jh:isTrue item='&pc_window_node.can_delete'><jh:notEquals item='&window_action' value='delete'>
      <TD nowrap align='center' onclick='_onPcDelete("<jh:item item='&unit.key_pack'/>")'><jh:img src='/res/img/sys/delete.gif' border='0'/></TD>
   </jh:notEquals></jh:isTrue>
   <jh:loop item='&pc_column_nodes' alias='column_node'>
   <jh:isTrue item='&column_node.disp_select'>
      <jh:define item='&column_node.[data]' alias='data_node'/>
      <jh:define item='&column_node.[edit]' alias='edit_node'/>
      <TD nowrap<jh:notEmpty item='&column_node.disp_align_h'> align='<jh:item item='&column_node.disp_align_h'/>'</jh:notEmpty>>
	   <jh:isTrue item='&column_node.disp_select_edit'>
		   <%@ include file="/inc/ctl/com/FieldEdit.inc" %>
	   </jh:isTrue>
	   <jh:isFalse item='&column_node.disp_select_edit'>
		   <%@ include file="/inc/ctl/com/FieldSelect.inc" %>
	   </jh:isFalse>
      </TD>
   </jh:isTrue>
   </jh:loop>
</TR>
</jh:loop>
</TABLE>

</jh:notEmpty>
