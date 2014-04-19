<%@ include file="/inc/page_begin.inc" %>

<jh:define item='[Control.OnlineBatchForm]|{&request.source}' alias='online_batch_form'/>
<jh:define item='&online_batch_form.window_node' alias='window_node'/>
<jh:define item='&online_batch_form.online_batch_list' alias='online_batch_list'/>

<HTML>
<!-- Html Header Initialize ------------------------------->
<HEAD>
<js:meta/>
<js:css/>
<js:js/>
<jh:title item='&window_node.disp_label'/>
<META http-equiv='refresh' content='10;URL=<jh:context/>/inc/Control.wa?source=<jh:item item='&window_node.full_name'/>&tv_action=init'>
</HEAD>

<SCRIPT language='javascript'>
function _onRefresh(){
   frmConsole.target = 'frmBody';
   frmConsole.wa = '<jh:context/>/inc/Control.wa?source=<jh:item item='&window_node.full_name'/>&tv_action=init';
   frmConsole.submit();
}
function _onExecute(sOnbName){
   frmConsole.target = 'frmBody';
   frmConsole.wa = '<jh:context/>/inc/Control.wa?source=<jh:item item='&window_node.full_name'/>&action=execute&onb_name=' + sOnbName;
   frmConsole.submit();
}
function _onReset(sOnbName){
   frmConsole.target = 'frmBody';
   frmConsole.wa = '<jh:context/>/inc/Control.wa?source=<jh:item item='&window_node.full_name'/>&action=reset&onb_name=' + sOnbName;
   frmConsole.submit();
}
</SCRIPT>

<!-- Toolbar Initialize ----------------------------------->
<jc:toolbar inFrame='frmBody' inForm='frmConsole' target='frmBody' name='top.frames.frmToolBar.oToolBar' action='refresh'>
   <jc:tbButton caption='txt|sys|button.refresh' action='_onRefresh()' icon='sys.refresh'/>
</jc:toolbar>

<!-- Body Initialize -------------------------------------->
<js:body styleClass='frmBody'>
<jh:form name='frmConsole'>
<TABLE width='100%' border='0' cellspacing='8' cellpadding='8'>
<TR><TD>

<jh:loop item='&online_batch_list' alias='online_batch'>
<TABLE width='100%' border='0' cellspacing='1' cellpadding='4' bgcolor='#FFFFFF'>
<TR height='1px' bgcolor='#999999'><TD colspan='4'></TD></TR>
<TR>
   <TD nowrap><jh:img src='/res/img/sys/ctl/ob.gif' border='0' align='absmiddle'/> <jh:item item='&online_batch.disp_label'/></TD>
   <TD nowrap width='40px'><jh:combo item='&online_batch.current_state' displayOnly='Y' source='code|sys|control.online.batch.state'/></TD>
   <TD nowrap width='80px'>
      <jh:equals item='&online_batch.current_state' value='F'>
         <jh:item item='&online_batch.effect_count'/> <jh:write item='txt|ctl|online_batch.effect_count'/>
      </jh:equals>
   </TD>
   <TD nowrap align='center' width='40px'>
      <jh:equals item='&online_batch.current_state' value='R'>
         <jh:write item='txt|sys|button.wait'/>
      </jh:equals>
      <jh:equals item='&online_batch.current_state' value='F'>
         <A href='javascript:_onExecute("<jh:item item='&online_batch.name'/>")'><jh:write item='txt|sys|button.execute'/></A>
      </jh:equals>
      <jh:equals item='&online_batch.current_state' value='E'>
         <A href='javascript:_onReset("<jh:item item='&online_batch.name'/>")'><jh:write item='txt|sys|button.reset'/></A>
      </jh:equals>
   </TD>
</TR>
<TR height='1px' bgcolor='#999999'><TD colspan='4'></TD></TR>
</TABLE><BR>
</jh:loop>

</TD></TR>
</TABLE>
</jh:form>
</js:body>
</HTML>
<%@ include file="/inc/page_end.inc" %>
