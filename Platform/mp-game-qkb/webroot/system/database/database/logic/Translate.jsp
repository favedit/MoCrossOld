<%@ include file="/inc/page/begin.inc" %>
<jh:define form='sys.trans' alias='trans_form'/>
<jh:define item='&trans_form.trans_ds' alias='trans_ds'/>

<HTML>

<!-- Html Header Initialize ------------------------------->
<HEAD>
<js:meta/>
<js:css/>
<js:js/>
<jh:title caption='Class View'/>
</HEAD>

<!-- Toolbar Initialize ----------------------------------->
<jc:toolbar name='top.frames.frmToolbar.oToolbar' inFrame='frmWork' inForm='frmConsole' target='frmWork' action='refresh'>
   <jc:tbButton caption='trs:sys|button.update|Update' pageAction='/sys/db/oracle/pkg/TranslateSave.wa' icon='sys.update'/>
   <jc:tbButton caption='trs:sys|button.sync|Sync' pageAction='/sys/db/oracle/pkg/TranslateSync.wa' icon='sys.update'/>
</jc:toolbar>

<!-- Body Initialize -------------------------------------->
<js:body styleClass='frmWork'>
<jh:form name='frmConsole'>

<TABLE width='100%' cellspacing='8' border='0'>
<TR><TD>
   <TABLE width='100%' bgcolor='#94B6FF' border='0' cellspacing='1' cellpadding='4'>
      <TR bgcolor='#EFF3FF'><TD><jh:item item='&trans_form.trans_id'/></TD></TR>
   </TABLE>
</TD></TR>
<TR><TD>
   <TABLE width='100%' bgcolor='#94B6FF' border='0' cellspacing='1' cellpadding='0'>
      <jh:loop item='&trans_ds' alias='item'>
         <TR bgcolor='#FFFFF4'>
            <TD width='60px' nowrap><jh:item item='&item.id'/></TD>
            <TD width='120px'><jh:item item='&item.label'/></TD>
            <TD><jh:edit item='&item.content' size='80' maxlength='200' style='border:0'/></TD>
         </TR>
      </jh:loop>
   </TABLE>
</TD></TR>
</TABLE>

</jh:form>
</js:body>
</HTML>
<%@ include file="/inc/page/end.inc" %>
