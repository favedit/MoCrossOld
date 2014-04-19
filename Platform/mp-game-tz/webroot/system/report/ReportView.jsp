<%@ include file='/apl/public.inc' %>
<jh:define form='sys.control' alias='control_form'/>

<HTML>
<!-- Html Header Initialize ------------------------------->
<HEAD>
<js:meta/>
<js:css/>
<js:js/>
<jh:js src='/js/ctl_com.js'/>
<jh:js src='/js/xhtml.js'/>
<jh:title caption='FavInfo'/>
</HEAD>

<jc:toolbar name='top.frames.frmToolbar.oToolbar' inFrame='frmWork' inForm='frmConsole' target='frmWork' action='refresh'>
   <jc:tbButton caption='txt|sys|button.download' action='' icon='sys.text'/>
</jc:toolbar>

<!-- Body Initialize -------------------------------------->
<js:body styleClass='frmWork' onload='ptyCfgInitialize();' scroll='auto'>
<js:form name='frmConsole' dataForm='top.frmToolbar.frmConsole'>

<!-- Page Control Initialize ------------------------------>
<TABLE width='100%' border='0' cellspacing='0' cellpadding='8'>
<TR><TD>
<DIV id='id_pty_cfg'></DIV>
<SCRIPT>
</SCRIPT>
</TD></TR>
<TR><TD>

</TD></TR>
</TABLE>

</js:form>
</js:body>
</HTML>

