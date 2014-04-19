<%@ include file="/inc/page/begin.inc" %>
<jh:define form='sys.control' alias='control_form'/>
<jh:define item='&control_form.control' alias='control'/>

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
   <jc:tbButton caption='txt|sys|button.insert' pageAction='/sys/ctl/win/InsertControlSave.wa' icon='sys.save'/>
   <jc:tbButton caption='txt|sys|button.back' pageAction='/sys/ctl/win/WindowConfig.wa' icon='sys.back'/>
</jc:toolbar>

<!-- Body Initialize -------------------------------------->
<js:body styleClass='frmWork'>
<js:form name='frmConsole'>
<TABLE width='100%' cellspacing='8' border='0'><TR><TD>

<TABLE width='100%' border='0' cellspacing='0' cellpadding='4'>
<TR>
   <TD nowrap width='60px'><jh:text item='type' label='Type'/></TD>
   <TD>*</TD>
   <TD nowrap><jh:combo item='&control.ctype' source='sys|control.type'/></TD>
</TR>
<TR>
   <TD nowrap><jh:text item='name' label='Name'/></TD>
   <TD width='10px'>*</TD>
   <TD nowrap><jh:edit item='&control.name' size='40' maxlength='40'/></TD>
</TR>
<TR>
   <TD nowrap><jh:text item='label' label='Label'/></TD>
   <TD width='10px'>*</TD>
   <TD nowrap><jh:edit item='&control.disp_label' size='40' maxlength='40'/></TD>
</TR>
</TABLE>

</TD></TR></TABLE>
</js:form>
</js:body>
</HTML>
<%@ include file="/inc/page/end.inc" %>
