<%@ include file="/inc/page/begin.inc" %>
<jh:define form='sys.document' alias='document_form'/>

<HTML>

<!-- Html Header Initialize ------------------------------->
<HEAD>
<js:meta/>
<js:css/>
<js:js/>
<jh:title caption='FavInfo'/>
</HEAD>

<!-- Toolbar Initialize ----------------------------------->
<jc:toolbar name='top.frames.frmToolbar.oToolbar' inFrame='frmWork' inForm='frmConsole' target='frmWork' action='refresh'>
   <jc:tbButton caption='txt|sys|button.back' pageAction='/sys/ctl/win/WindowConfig.wa' icon='sys.back'/>
</jc:toolbar>

<!-- Body Initialize -------------------------------------->
<js:body styleClass='frmWork' scroll='yes'>
<js:form name='frmConsole'>

<TABLE width='100%' height='100%' cellspacing='0' cellpadding='0' bgcolor='FFFFFF'>
<TR><TD height='1px'>
   <TABLE width='100%' border='0' cellspacing='0' cellpadding='4'>
   <TR><TD bgcolor='FFFFFF'>
      <FONT color='blue'>Navigator:<jh:item item='&document_form.navigator_html' format='N'/></FONT>
   </TD></TR>
   </TABLE>
</TD></TR>
<TR><TD height='1px' style="filter:progid:DXImageTransform.Microsoft.Gradient(startColorStr='#FFFFFF', endColorStr='#666666', gradientType='1')"></TD></TR>
<TR><TD valign='top'>
   <TABLE width='100%' height='100%' border='0' cellspacing='0' cellpadding='0'>
   <TR valign='top'>
      <TD style='padding:8px'>
         <jh:item item='&document_form.document_html' format='N'/>
      </TD>
      <TD width='1px' bgcolor='#888888'><jh:img src='/res/img/n.gif' width='1px'/></TD>
      <TD width='200px' style='padding:8px'>
         Catalog Html
         <jh:item item='&document_form.catalog_html' format='N'/>
      </TD>
   </TR>
   </TABLE>
</TD></TR>
<TR><TD height='1px' style="filter:progid:DXImageTransform.Microsoft.Gradient(startColorStr='#FFFFFF', endColorStr='#666666', gradientType='1')"></TD></TR>
<TR><TD height='1px'>
   <TABLE width='100%' border='0' cellspacing='0' cellpadding='4'>
   <TR><TD align='center' style="filter:progid:DXImageTransform.Microsoft.Gradient(startColorStr='#E0E0E0', endColorStr='#FFFFFF', gradientType='1')">
      <FONT color='blue'>Copy Right Java Frame Studio</FONT>
   </TD></TR>
   </TABLE>
</TD></TR>
</TABLE>
</js:form>
</js:body>
</HTML>
<%@ include file="/inc/page/end.inc" %>
