<%@ include file="/inc/page/begin.inc" %>
<jh:define form='sys.source' alias='source_form'/>

<HTML>

<!-- Html Header Initialize ------------------------------->
<HEAD>
<js:meta/>
<js:css/>
<js:js/>
<jh:title caption='Class View'/>
</HEAD>

<!-- Toolbar Initialize ----------------------------------->
<jc:toolbar name='top.frames.frmToolbar.oToolbar' inFrame='frmWork' inForm='frmConsole' target='frmWork' action='refresh'/>

<!-- Body Initialize -------------------------------------->
<js:body styleClass='frmWork'>
<js:form name='frmConsole'>

<TABLE width='100%' cellspacing='8' border='0'>
<TR><TD>
   <TABLE width='100%' bgcolor='#94B6FF' border='0' cellspacing='1' cellpadding='4'>
      <TR bgcolor='#EFF3FF'><TD>
         <jh:item item='&source_form.class_inherit_html' format='false'/>
      </TD></TR>
   </TABLE>
</TD></TR>
<TR><TD>
   <jh:item item='&source_form.class_html' format='off'/>
</TD></TR>
</TABLE>

</js:form>
</js:body>
</HTML>
<%@ include file="/inc/page/end.inc" %>
