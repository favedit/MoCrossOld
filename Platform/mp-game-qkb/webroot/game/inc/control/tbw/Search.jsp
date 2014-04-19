<%@ include file="/inc/page_begin.inc" %>

<jh:define item='[Control.TableWindowForm]|{&request.source}' alias='table_window_form'/>
<jh:define item='&table_window_form.search_info' alias='search_info'/>
<jh:define item='&table_window_form.search_info' alias='unit'/>
<jh:define item='&table_window_form.window_node' alias='window_node'/>
<jh:define item='&table_window_form.control_list' alias='control_list'/>
<jh:default action='_onSearch(true)'/>

<HTML>
<HEAD>
<js:meta/>
<js:css/>
<js:js/>
<jh:title item='&window_node.disp_label'/>
</HEAD>

<SCRIPT language='javascript'>
function _onSearch(bSearch){
   frmConsole.target = 'frmList';
   frmConsole.wa = '<jh:context/>/inc/Control.wa?source=<jh:item item='{&window_node.full_name}'/>&action=list';
   frmConsole.<jh:name item='&search_info.search_cancel'/>.value = bSearch ? 'N' : 'Y';
   frmConsole.submit();
}
</SCRIPT>

<!-- Body Initialize -------------------------------------->
<js:body styleClass='frmBody'>
<jh:form name='frmConsole'>
<jh:edit type='hidden' item='&search_info.begin_search' value='Y'/>
<jh:edit type='hidden' item='&search_info.search_cancel' value='N'/>
<TABLE width='100%' cellspacing='4' border='0'><TR><TD>

<TABLE width='100%' border='0' cellspacing='0' cellpadding='2'>
<jh:loop item='&control_list' alias='control_node'>
<jh:define item='&control_node.[data]' alias='data_node'/>
<jh:define item='&control_node.[edit]' alias='edit_node'/>
<jh:isTrue item='&control_node.disp_search'>
<TR>
   <TD nowrap width='80px'><jh:item item='&control_node.disp_label'/></TD>
   <TD nowrap><%@ include file="/inc/ctl/com/FieldEdit.inc" %></TD>
</TR>
</jh:isTrue>
</jh:loop>
</TABLE>

</TD></TR></TABLE>
</jh:form>
</js:body>
</HTML>
<%@ include file="/inc/page_end.inc" %>
