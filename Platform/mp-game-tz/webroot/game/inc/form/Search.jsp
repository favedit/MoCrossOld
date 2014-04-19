<%@ include file='/apl/public.inc' %>
<HTML>

<HEAD>
<js:meta/>
<js:css/>
<js:js/>
<jh:js src='/js/mo_ctl_tree.js'/>
<jh:title caption='Catalog'/>
</HEAD>

<BODY bgcolor='#FFFFFF' text='#000000' leftmargin='0' topmargin='0' marginwidth='0' marginheight='0' scroll='no'>
<FORM name='frmConsole' method='POST'>
<TABLE width='100%' border='0' cellspacing='0' cellpadding='4' bgcolor='#F0F0F0'><TR><TD>
   <TABLE width='100%' border='0' cellspacing='0' cellpadding='0'>
   <TR>
      <TD valign='center'><INPUT type='text' name='search' value='' style='width:100%'></TD>
      <TD width='4'></TD>
      <TD width='60px' align='center' valign='center'><INPUT type='button' name='btnSearch' value='Search' style='width:100%;background-color:#FFFFFF'></TD>
   </TR>
   </TABLE>
   <TABLE width='100%' border='0' cellspacing='0' cellpadding='0'>
   <TR>
      <TD>
         <A href='javascript:top.frmCatalog.oTreeView.extendNode(true)'><jh:img src='/res/img/sys/tvni.gif' border='0' align='absmiddle'/> Extend</A>
         <A href='javascript:top.frmCatalog.oTreeView.extendNode(false)'><jh:img src='/res/img/sys/tvnd.gif' border='0' align='absmiddle'/> Colapse</A>
         <A href='javascript:top.frmCatalog.oTreeView.refreshNode()'><jh:img src='/res/img/sys/refresh.gif' border='0' align='absmiddle'/> Refresh</A>
      </TD>
   </TR>
   </TABLE>
</TD></TR></TABLE>
<TABLE width='100%' bgcolor='#666666' border='0' cellspacing='0' cellpadding='0' height='1'><TR><TD></TD></TR></TABLE>
</FORM>
</BODY>

</HTML>
