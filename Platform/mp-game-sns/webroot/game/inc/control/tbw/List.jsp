<%@ include file="/inc/page_begin.inc" %>

<jh:define item='[Control.TableWindowForm]|{&request.source}' alias='table_window_form'/>
<jh:define item='&table_window_form.search_info' alias='search_info'/>
<jh:define item='&table_window_form.select_info' alias='select_info'/>
<jh:define item='&table_window_form.window_node' alias='window_node'/>
<jh:define item='&table_window_form.control_list' alias='control_list'/>
<jh:define item='&table_window_form.dataset' alias='dataset'/>

<jh:define item='&window_node.toolbar_name' alias='toolbar_name' default='top.frames.frmToolBar.oToolBar'/>
<jh:define item='&window_node.toolbar_target' alias='toolbar_target' default='frmBody'/>

<jh:keymap type='onkeydown' key='insert' action='_onInsert()'/>
<jh:keymap type='onkeydown' key='home' action='_onFirst()'/>
<jh:keymap type='onkeydown' key='page_up' action='_onPrior()'/>
<jh:keymap type='onkeydown' key='page_down' action='_onNext()'/>
<jh:keymap type='onkeydown' key='end' action='_onLast()'/>

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
function _goPage(nPage){
   if(!m_bExecute && nPage){
      m_bExecute = true;
      frmConsole.target = '';
      frmConsole.wa = "<jh:context/>/inc/ctl/tbw/List.wa?source=<jh:item item='{&window_node.package}.{&window_node.name}'/>&action=page";
      frmConsole.<jh:name item='&search_info.page_position'/>.value = nPage;
      frmConsole.submit();
   }
}
function _onPageSize(oCombo){
   if(!m_bExecute && oCombo){
      m_bExecute = true;
      frmConsole.target = '';
      frmConsole.wa = "<jh:context/>/inc/ctl/tbw/List.wa?source=<jh:item item='{&window_node.package}.{&window_node.name}'/>&action=page";
      frmConsole.submit();
   }
}
function _onPageChange(oCombo){
   if(!m_bExecute && oCombo){
      m_bExecute = true;
      frmConsole.target = '';
      frmConsole.wa = "<jh:context/>/inc/ctl/tbw/List.wa?source=<jh:item item='{&window_node.package}.{&window_node.name}'/>";
      frmConsole.submit();
   }
}
function _onSelect(sPack){
   if(!m_bExecute){
      m_bExecute = true;
      var oWindow = dialogArguments['window'];
      var sCallback = dialogArguments['callback'];
      var oList = obj.create('Lang.List');
      try{
         if(sCallback && sCallback.length > 0){
            eval('oWindow.'+sCallback+'("'+sPack+'")');
         }
      }catch(e){}
      top.window.close();
   }
}
function _onInsert(){
   if(!m_bExecute){
      m_bExecute = true;
      frmConsole.target = 'frmBody';
      frmConsole.wa = '<jh:context/>/inc/Control.wa?source=<jh:item item='&window_node.unit_window'/>&action=insert<jh:notEmpty item='&window_node.unit_window'>&prepare_pack=<jh:item item='&table_window_form.key_pack'/></jh:notEmpty>';
      frmConsole.submit();
   }
}
function _onUpdate(sPack){
   if(!event.ctrlKey && !m_bExecute){
      m_bExecute = true;
      frmConsole.target = 'frmBody';
      frmConsole.wa = '<jh:context/>/inc/Control.wa?source=<jh:item item='&window_node.unit_window'/>&action=update&key_pack=' + sPack;
      frmConsole.submit();
   }
}
function _onDelete(sPack){
   if(!m_bExecute){
      m_bExecute = true;
      frmConsole.target = 'frmBody';
      frmConsole.wa = '<jh:context/>/inc/Control.wa?source=<jh:item item='&window_node.unit_window'/>&action=delete&key_pack=' + sPack;
      frmConsole.submit();
   }
}
</SCRIPT>

<!-- Toolbar Initialize ----------------------------------->
<jh:isFalse item='&window_node.hidden_toolbar'>
<jc:toolbar inFrame='frmBody.frmSearch' inForm='frmConsole' target='&toolbar_target' name='&toolbar_name' action='refresh'>
   <jh:isTrue item='&window_node.can_search'>
      <jc:tbButton target='frmSearchInner' caption='txt|sys|button.search' action='_onSearch(true)' icon='wap.search'/>
      <jc:tbButton target='frmSearchInner' caption='txt|sys|button.search.cancel' action='_onSearch(false)' icon='wap.searchcl'/>
   </jh:isTrue>
   <jh:isTrue item='&window_node.can_insert'>
      <jh:isTrue item='&window_node.can_search'><jc:tbButton caption='|'/></jh:isTrue>
      <jc:tbButton inFrame='frmBody.frmList' caption='txt|sys|button.insert' action='_onInsert()' icon='wap.unitins'/>
   </jh:isTrue>
   <jh:define item='&table_window_form.toolbar_button_list' alias='toolbar_button_list'/>
   <%@ include file="/inc/ctl/com/Toolbar.inc" %>
   <jh:isTrue item='&window_node.catalog_flag'>
      <jh:notEmpty item='&window_node.catalog_unit_window'>
         <jh:isTrue item='&window_node.can_search'><jc:tbButton caption='|'/></jh:isTrue>
         <jc:tbButton caption='txt|sys|button.node.insert' pageAction='/inc/Control.wa?source={&window_node.catalog_unit_window}&action=insert' icon='sys.tvni'/>
         <jc:tbButton caption='txt|sys|button.node.update' pageAction='/inc/Control.wa?source={&window_node.catalog_unit_window}&action=update&key_pack={&table_window_form.key_pack}' icon='sys.tvnu'/>
         <jc:tbButton caption='txt|sys|button.node.delete' pageAction='/inc/Control.wa?source={&window_node.catalog_unit_window}&action=delete&key_pack={&table_window_form.key_pack}' icon='sys.tvnd'/>
      </jh:notEmpty>
   </jh:isTrue>
</jc:toolbar>
</jh:isFalse>

<!-- Body Initialize -------------------------------------->
<js:body styleClass='frmBody' focus='true'>
<jh:form name='frmConsole'>
<jh:edit type='hidden' item='&select_info.object_id'/>

<TABLE width='100%' cellspacing='8' border='0'><TR><TD>

<jh:notEmpty item='&dataset'>
<TABLE width='100%' border='0' cellspacing='1' cellpadding='4'>
<TR>
   <TD><jc:dsNavigator item='&dataset' source='txt|control|navigator' format='txt|control|navigator.format.page'/></TD>
   <TD align='right'>
      <jc:dsNavigator item='&dataset' source='txt|control|navigator' format='txt|control|navigator.format.navigator' jsAction='_goPage'/>
      <jh:hasDefine item='&display_page_list'>
         <jh:combo item='&search_info.page_position' source='&display_page_list' onchange='_onPageChange(this)'/>
      </jh:hasDefine>
      <jh:combo item='&search_info.page_size' source='code|sys|display.page.size' onchange='_onPageSize(this)'/>
   </TD>
</TR>
</TABLE>
<TABLE width='100%' border='0' cellspacing='1' cellpadding='4' class='table'>
<TR class='tableHead'>
   <jh:isTrue item='&window_node.can_delete'>
      <TD nowrap width='16px' align='center' class='tableHeadCell'><jh:img src='/res/img/sys/tab/cmd.gif' border='0'/></TD>
   </jh:isTrue>
<jh:loop item='&control_list' alias='control_node'>
<jh:isTrue item='&control_node.disp_select'>
   <TD class='tableHeadCell' nowrap<jh:notEmpty item='&control_node.disp_width'> width='<jh:item item='&control_node.disp_width'/>px'</jh:notEmpty><jh:notEmpty item='&control_node.disp_align_h'> align='<jh:item item='&control_node.disp_align_h'/>'</jh:notEmpty>>
      <jh:item item='&control_node.disp_label'/>
   </TD>
</jh:isTrue>
</jh:loop>
</TR>
<jh:loop item='&dataset' alias='unit'>
<TR class='tableLine' <jh:isTrue item='&window_node.can_update'>onmouseover='obj.html.li(this)' onmouseout='obj.html.lo(this)' onclick='_onUpdate("<jh:item item='&unit.key_pack'/>")' style='cursor:hand'</jh:isTrue><jh:isTrue item='&window_node.can_select'>onmouseover='obj.html.li(this)' onmouseout='obj.html.lo(this)' onclick='_onSelect("<jh:item item='&unit.key_pack'/>")' style='cursor:hand'</jh:isTrue><jh:notEmpty item='&control_node.disp_align_v'> valign='<jh:item item='&control_node.disp_align_v'/>'</jh:notEmpty>>
   <jh:isTrue item='&window_node.can_delete'>
      <TD nowrap align='center' onclick='_onDelete("<jh:item item='&unit.key_pack'/>")'><jh:img src='/res/img/sys/delete.gif' border='0'/></TD>
   </jh:isTrue>
   <jh:loop item='&control_list' alias='control_node'>
   <jh:define item='&control_node.[data]' alias='data_node'/>
   <jh:define item='&control_node.[edit]' alias='edit_node'/>
   <jh:isTrue item='&control_node.disp_select'>
      <TD nowrap<jh:notEmpty item='&control_node.disp_align_h'> align='<jh:item item='&control_node.disp_align_h'/>'</jh:notEmpty>>
      <jh:isTrue item='&control_node.disp_select_edit'>
         <%@ include file="/inc/ctl/com/FieldEdit.inc" %>
      </jh:isTrue>
      <jh:isFalse item='&control_node.disp_select_edit'>
         <%@ include file="/inc/ctl/com/FieldSelect.inc" %>
      </jh:isFalse>
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
<SCRIPT language='javascript'>
function _onFirst(){
   _goPage(<jh:item item='&navigator_first_page'/>);
}
function _onPrior(){
   _goPage(<jh:item item='&navigator_prior_page'/>);
}
function _onNext(){
   _goPage(<jh:item item='&navigator_next_page'/>);
}
function _onLast(){
   _goPage(<jh:item item='&navigator_last_page'/>);
}
</SCRIPT>
<%@ include file="/inc/page_end.inc" %>
