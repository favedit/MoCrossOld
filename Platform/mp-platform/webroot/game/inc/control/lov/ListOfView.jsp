<%@ include file="/inc/page_begin.inc" %>

<jh:define item='[Control.ListOfViewForm]|{&request.source}' alias='lov_form'/>
<jh:define item='&lov_form.search_info' alias='search_info'/>
<jh:define item='&lov_form.select_info' alias='select_info'/>
<jh:define item='&lov_form.window_node' alias='window_node'/>
<jh:define item='&lov_form.control_list' alias='control_list'/>
<jh:define item='&lov_form.dataset' alias='dataset'/>

<HTML>
<!-- Html Header Initialize ------------------------------->
<HEAD>
<js:meta/>
<js:css/>
<js:js/>
<jh:title item='&window_node.disp_label'/>
</HEAD>

<SCRIPT language='javascript'>
function _onSelect(sPack){
   var oWindow = dialogArguments['window'];
   var sItemName = dialogArguments['item_name'];
   var oList = obj.create('Lang.List');
   oList.unpack(sPack);
   var sKey = null;
   var sValue = null;
   var nCount = oList.getCount();
   for(var n=0; n<nCount; n++){
      sKey = oList.getKey(n).toLowerCase();
      sValue = oList.getItem(n);
      try{
         eval('oWindow.frmConsole.' + sItemName + sKey + '.value="' + sValue + '"');
      }catch(e){}
      try{
         eval('oWindow.frmConsole.' + sItemName + sKey + '__.value="' + sValue + '"');
      }catch(e){}
   }
   close();
}
</SCRIPT>

<!-- Toolbar Initialize ----------------------------------->
<jc:toolbar inForm='frmConsole' inFrame='frmBody.frmSearchHead' target='frmBody' name='top.frames.frmToolBar.oToolBar' />

<!-- Body Initialize -------------------------------------->
<js:body styleClass='frmBody'>
<jh:form name='frmConsole'>
<jh:edit type='hidden' item='&select_info.object_id'/>
<jh:edit type='hidden' item='&search_info.page_position'/>

<TABLE width='100%' cellspacing='8' border='0'><TR><TD>

<jh:notEmpty item='&dataset'>
<TABLE width='100%' border='0' cellspacing='1' cellpadding='4' class='table'>
<TR class='tableHead'>
<jh:loop item='&control_list' alias='control_node'>
<jh:isTrue item='&control_node.disp_select'>
   <TD nowrap<jh:notEmpty item='&control_node.disp_width'> width='<jh:item item='&control_node.disp_width'/>px'</jh:notEmpty><jh:notEmpty item='&control_node.disp_align_h'> align='<jh:item item='&control_node.disp_align_h'/>'</jh:notEmpty>>
      <jh:item item='&control_node.disp_label'/>
   </TD>
</jh:isTrue>
</jh:loop>
</TR>
<jh:loop item='&dataset' alias='unit'>
   <TR class='tableLine' onmouseover='obj.html.li(this)' onmouseout='obj.html.lo(this)' onclick='_onSelect("<jh:item item='&unit.key_pack'/>")' style='cursor:hand'<jh:notEmpty item='&control_node.disp_align_v'> valign='<jh:item item='&control_node.disp_align_v'/>'</jh:notEmpty>>
<jh:loop item='&control_list' alias='control_node'>
   <jh:define item='&control_node' alias='column_node'/>
   <jh:define item='&control_node.[data]' alias='data_node'/>
   <jh:define item='&control_node.[edit]' alias='edit_node'/>
   <jh:isTrue item='&control_node.disp_select'>
      <TD nowrap<jh:notEmpty item='&control_node.disp_align_h'> align='<jh:item item='&control_node.disp_align_h'/>'</jh:notEmpty>>
         <%@ include file="/inc/ctl/com/FieldSelect.inc" %>
      </TD>
   </jh:isTrue>
</jh:loop>
</TR>
</jh:loop>
</TABLE>
</jh:notEmpty>

</TD></TR></TABLE>
</jh:form>
</js:body>
</HTML>
<%@ include file="/inc/page_end.inc" %>
