<%@ include file="/inc/page/begin.inc" %>
<jh:define form='control.window' alias='window_form'/>
<jh:define item='&window_form.file' alias='file'/>

<HTML>

<!-- Html Header Initialize ------------------------------->
<HEAD>
<js:meta/>
<js:css/>
<js:js/>
<jh:title caption='trs:this|title|Upload Document Node'/>
</HEAD>

<!-- Toolbar Initialize ----------------------------------->
<jc:toolbar name='top.frames.frmToolbar.oToolbar' inFrame='frmWork' inForm='frmConsole' target='frmWork' action='refresh'>
   <jc:tbButton caption='trs:sys|button.upload|Upload' pageAction='/sys/ctl/win/UploadSave.wa' icon='sys.upload'/>
   <jc:tbButton caption='trs:sys|button.back|Back' pageAction='/sys/ctl/win/ControlList.wa' icon='sys.back'/>
</jc:toolbar>

<!-- Body Initialize -------------------------------------->
<js:body styleClass='frmWork'>
<js:form name='frmConsole' multipart='Y'>
<TABLE width='100%' cellspacing='8' border='0'><TR><TD>

<TABLE width='100%' border='0' cellspacing='0' cellpadding='4'>
<TR>
   <TD nowrap><jh:text value='trs:dbi|document.upload|Upload'/></TD>
   <TD width='10px'>*</TD>
   <TD nowrap><jh:file item='&file.upload' size='40'/></TD>
</TR>
</TABLE>

</TD></TR></TABLE>
</js:form>
</js:body>
</HTML>
<%@ include file="/inc/page/end.inc" %>
