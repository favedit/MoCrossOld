<%@ include file="/inc/page/begin.inc" %>
<jh:define form='sys.control' alias='control_form'/>
<jh:define item='&control_form.window' alias='window'/>

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
   <jc:tbButton caption='trs:sys|button.insert|Insert' pageAction='/system/window/WinInsertSave.wa' icon='sys.save'/>
   <jc:tbButton caption='trs:sys|button.back|Back' pageAction='/system/window/LstInfo.wa' icon='sys.back'/>
</jc:toolbar>

<!-- Body Initialize -------------------------------------->
<js:body styleClass='frmWork'>
<js:form name='frmConsole'>
<TABLE width='100%' cellspacing='8' border='0'><TR><TD>

<TABLE width='100%' border='0' cellspacing='0' cellpadding='4'>
<TR>
   <TD width='80px' nowrap><jh:text value='trs:this|window.path|Path'/></TD>
   <TD width='10px'></TD>
   <TD nowrap><jh:item item='&window.path'/></TD>
</TR>
<TR>
   <TD nowrap><jh:text value='trs:this|window.name|Name'/></TD>
   <TD width='10px'>*</TD>
   <TD nowrap><jh:edit item='&window.name' size='40' maxlength='40'/></TD>
</TR>
<TR>
   <TD nowrap><jh:text value='trs:this|window.label|Label'/></TD>
   <TD width='10px'></TD>
   <TD nowrap><jh:edit item='&window.label' size='40' maxlength='40'/></TD>
</TR>
</TABLE>

</TD></TR></TABLE>
</js:form>
</js:body>
</HTML>
<%@ include file="/inc/page/end.inc" %>
