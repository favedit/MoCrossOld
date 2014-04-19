<%@ page contentType='text/html;charset=utf-8' %>
<%@ include file="/inc/page/begin.inc" %>
<jh:define form='[catalog]' alias='catalog_form'/>
<jh:define form='[message]' alias='message_form'/>

<HTML>

<!-- Html Header Initialize ------------------------------->
<HEAD>
<js:meta/>
<js:css/>
<jh:js/>
<jh:title caption='FavInfo'/>
</HEAD>

<jh:default action='_onBack()'/>
<jh:keymap type='onkeydown' key='backspace' action='_onBack()'/>

<SCRIPT language='javascript'>
var m_bExecute = false;
function _onBack(){
   if(!m_bExecute){
      m_bExecute = true;
      frmConsole.action = '<jh:uri item='&message_form.uri_prior'/>';
      frmConsole.submit();
   }
}
</SCRIPT>

<!-- Toolbar Initialize ----------------------------------->
<jc:toolbar name='top.frames.frmToolbar.oToolbar' inFrame='frmWork' inForm='frmConsole' target='frmWork' action='refresh'>
   <jh:notEmpty item='&message_form.uri_prior'>
      <jc:tbButton caption='txt|sys|button.back' action='_onBack()' icon='sys.back'/>
   </jh:notEmpty>
</jc:toolbar>

<jh:isTrue item='&catalog_form.caption_refresh'>
<jh:notEmpty item='&catalog_form.node_caption'>
<SCRIPT>
var oNode = top.frames.frmCatalog.oTreeView.focusNode;
if(oNode){oNode.setCaption("<jh:item item='&catalog_form.node_caption' format='N'/>");}
</SCRIPT>
<jh:reset item='&catalog_form.caption_refresh'/>
<jh:reset item='&catalog_form.node_caption'/>
</jh:notEmpty>
</jh:isTrue>

<jh:isTrue item='&catalog_form.node_refresh'>
<SCRIPT>top.frames.frmCatalog.oTreeView.refreshNode(null, false);</SCRIPT>
<jh:reset item='&catalog_form.node_refresh'/>
</jh:isTrue>

<jh:isTrue item='&catalog_form.parent_refresh'>
<SCRIPT>top.frames.frmCatalog.oTreeView.refreshNode(null, true);</SCRIPT>
<jh:reset item='&catalog_form.parent_refresh'/>
</jh:isTrue>

<!-- Body Initialize -------------------------------------->
<js:body styleClass='frmWork'>
<js:form name='frmConsole'>
<TABLE width='100%' cellspacing='8' border='0'>
<TR><TD>
   <TABLE width='100%' border='0' cellspacing='1' cellpadding='4'>
      <TR><TD align='center' valign='middle'>
         <jh:img src='/res/img/sys/msg/lmsg.gif' align='absmiddle'/><jh:img src='/res/img/n.gif' width='8'/><jh:text value='trs:sys|title.message|Message'/>
      </TD></TR>
   </TABLE>
</TD></TR>
<TR><TD>
   <TABLE width='100%' bgcolor='#94B6FF' border='0' cellspacing='1' cellpadding='0'>
      <TR bgcolor='#FFFFFF'><TD>
         <TABLE width='100%' border='0' cellspacing='4' cellpadding='0'>
            <TR>
               <TD nowrap><jh:text value='trs:sys|info.saveok|Save Ok'/></TD>
            </TR>
         </TABLE>
      </TD></TR>
   </TABLE>
</TD></TR>
<TR><TD>
   <TABLE width='100%' border='0' cellspacing='0' cellpadding='4'>
      <TR>
         <TD align='center'>
            <jh:notEmpty item='&message_form.uri_prior'>
               <jc:tbButton inFrame='frmWork' inForm='frmConsole' target='frmWork' caption='txt|sys|button.back' action='_onBack()' icon='/res/img/sys/back.gif'/>
            </jh:notEmpty>
         </TD>
   </TABLE>
</TD></TR>
</TABLE>
</js:form>
</js:body>
</HTML>
<%@ include file="/inc/page/end.inc" %>
