<%@ page contentType='text/html;charset=utf-8' %>
<%@ include file="/inc/page_begin.inc" %>
<HTML>

<!-- Html Header Initialize ------------------------------->
<HEAD>
<js:meta/>
<js:css/>
<jh:js/>
<jh:title caption='FavInfo'/>
</HEAD>

<!-- Toolbar Initialize ----------------------------------->
<jc:toolbar inFrame='frmBody' inForm='frmConsole' target='frmBody' name='top.frames.frmToolBar.oToolBar' action='refresh'>
   <jc:tbButton caption='sys|Toolbar.Close' action='window.close()' icon='sys.exit'/>
</jc:toolbar>

<!-- Body Initialize -------------------------------------->
<js:body styleClass='frmBody'>
<jh:form name='frmConsole' link='[MessageForm]'>


<TABLE width='100%' cellspacing='8' border='0'>
<TR><TD>
   <TABLE width='100%' border='0' cellspacing='1' cellpadding='4'>
      <TR><TD align='center' valign='middle'>
         <jh:img src='/res/img/sys/msg/lmsg.gif' align='absmiddle'/><jh:img src='/res/img/n.gif' width='8'/><jh:write item='txt|sys|Message.Title'/>
      </TD></TR>
   </TABLE>
</TD></TR>

<TR><TD>
   <TABLE width='100%' bgcolor='#94B6FF' border='0' cellspacing='1' cellpadding='0'>
      <TR bgcolor='#FFFFFF'><TD>
         <TABLE width='100%' border='0' cellspacing='4' cellpadding='0'>
            <TR>
               <TD nowrap><jh:write item='txt|sys|Information.SaveOk'/></TD>
            </TR>
         </TABLE>
      </TD></TR>
   </TABLE>
</TD></TR>

<TR><TD>
   <TABLE width='100%' border='0' cellspacing='0' cellpadding='4'>
      <TR>
         <TD align='center' id='id_asd'>
            <jh:notEmpty item='uri_prior'>
               <jc:tbButton jsForm='frmConsole' caption='sys|Toolbar.Close' action='window.close()' icon='/res/img/sys/exit.gif'/>
            </jh:notEmpty>
         </TD>
   </TABLE>
</TD></TR>

</TABLE>


</jh:form>
</js:body>
</HTML>
<%@ include file="/inc/page_end.inc" %>
