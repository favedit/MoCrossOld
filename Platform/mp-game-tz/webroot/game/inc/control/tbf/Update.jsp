<%@ include file="/inc/page_begin.inc" %>

<jh:define item='[Control.FormWindowForm]|{&request.source}' alias='form_window_form'/>
<jh:define item='&form_window_form.wa' alias='window_action'/>
<jh:define item='&form_window_form.window_node' alias='window_node'/>
<jh:define item='&form_window_form.control_list' alias='control_list'/>
<jh:define item='&table_window_form.toolbar_button_list' alias='toolbar_button_list'/>
<jh:define item='&form_window_form.unit' alias='unit'/>
<jh:default action='_onUpdate()'/>
<jh:keymap type='onkeydown' key='backspace' action='_onBack()' ignore='input,textarea'/>

<HTML>
<!-- Html Header Initialize ------------------------------->
<HEAD>
<js:meta/>
<js:css/>
<js:js/>
<jh:title item='&window_node.disp_label'/>
</HEAD>

<SCRIPT language='javascript'>
var m_bExecute = false;
function _onRefresh(){
   if(!m_bExecute){
      m_bExecute = true;
      frmConsole.target = '';
      frmConsole.wa = '<jh:context/>/inc/Control.wa?source=<jh:item item='&form_window_form.source'/>&action=update&page_action=refresh';
      frmConsole.submit();
   }
}
function _onUpdate(){
   if(!m_bExecute){
      m_bExecute = true;
      frmConsole.wa = '<jh:context/>/inc/ctl/tbf/UpdateSave.wa?source=<jh:item item='{&window_node.full_name}'/>';
      frmConsole.submit();
   }
}
function _onBack(){
   if(!m_bExecute){
      m_bExecute = true;
      frmConsole.wa = '<jh:context/><jh:item item='&history.prior' format='false'/>';
      frmConsole.submit();
   }
}
</SCRIPT>

<!-- Toolbar Initialize ----------------------------------->
<jc:toolbar inFrame='frmBody' inForm='frmConsole' target='frmBody' name='top.frames.frmToolBar.oToolBar' action='refresh'>
   <jh:isTrue item='&window_node.can_update'>
      <jc:tbButton caption='txt|sys|button.update' action='_onUpdate()' icon='sys.save'/>
   </jh:isTrue>
   <jh:notEmpty item='&history.prior'>
      <jc:tbButton caption='txt|sys|button.back' action='_onBack()' icon='sys.back'/>
   </jh:notEmpty>
   <jh:define item='&form_window_form.toolbar_button_list' alias='toolbar_button_list'/>
   <%@ include file="/inc/ctl/com/Toolbar.inc" %>
</jc:toolbar>

<!-- Body Initialize -------------------------------------->
<js:body styleClass='frmBody' focus='true'>
<jh:form name='frmConsole' multipart='&window_node.multi_part'>
<TABLE width='100%' cellspacing='8' border='0'><TR><TD>

<jh:notEmpty item='&control_list'>
<TABLE width='100%' border='0' cellspacing='0' cellpadding='4'><TR>
<jh:loop item='&control_list' alias='control_node'>
<jh:define item='&control_node.[data]' alias='data_node'/>
<jh:define item='&control_node.[edit]' alias='edit_node'/>
<jh:isTrue item='&control_node.disp_update'>
   <TD nowrap width='<jh:item item='&control_node.disp_label_width' default='60'/>px'><jh:item item='&control_node.disp_label'/></TD>
   <TD width='8px'><jh:isFalse item='&data_node.data_is_null'>*</jh:isFalse></TD>
   <TD nowrap width='<jh:item item='&control_node.disp_width'/>px'>
   <jh:isTrue item='&control_node.disp_is_blob'><B></jh:isTrue>
   <jh:isTrue item='&control_node.disp_is_italic'><I></jh:isTrue>
   <jh:isTrue item='{&window_node.can_update}&{&control_node.disp_update_edit}'>
      <%@ include file="/inc/ctl/com/FieldEdit.inc" %>
   </jh:isTrue>
   <jh:isFalse item='{&window_node.can_update}&{&control_node.disp_update_edit}'>
      <%@ include file="/inc/ctl/com/FieldSelect.inc" %><jh:edit type='hidden' item='&unit.{&data_node.data_name}'/>
   </jh:isFalse>
   <jh:isTrue item='&control_node.disp_is_italic'></I></jh:isTrue>
   <jh:isTrue item='&control_node.disp_is_blob'></B></jh:isTrue>
   </TD>
<jh:isFalse item='&control_node.disp_nowrap'>
</TR></TABLE>
<TABLE width='100%' border='0' cellspacing='0' cellpadding='4'><TR>
</jh:isFalse>
</jh:isTrue>
</jh:loop>
</TR></TABLE><BR>
</jh:notEmpty>

<jh:define item='&form_window_form.page_controls' alias='page_controls'/>
<jh:define item='&form_window_form.page_control' alias='page_control'/>
<%@ include file="/inc/ctl/com/PageControls.inc" %>

<jh:define item='&form_window_form.logic_action' alias='logic_action'/>
<%@ include file="/inc/ctl/com/LogicAction.inc" %>

</TD></TR></TABLE>
</jh:form>
</js:body>
</HTML>
<%@ include file="/inc/page_end.inc" %>
