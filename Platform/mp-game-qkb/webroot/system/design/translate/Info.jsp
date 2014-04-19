<%@ include file='/apl/public.inc' %>
<jh:define form='wfc.trans' alias='trans_form'/>

<HTML>

<!-- Html Header Initialize ------------------------------->
<HEAD>
<js:meta/>
<js:css/>
<js:js/>
<jh:title caption='trs:sys|title|Translate'/>
</HEAD>

<!-- Toolbar Initialize ----------------------------------->
<jc:toolbar name='top.frames.frmToolbar.oToolbar' inFrame='frmWork' inForm='frmConsole' target='frmWork' action='refresh'>
   <jc:tbButton caption='trs:sys|button.sync|Build All Translate' pageAction='/system/translate/BuildTranslate.wa' icon='sys.update'/>
</jc:toolbar>

<!-- Body Initialize -------------------------------------->
<js:body styleClass='frmWork'>
<jh:form name='frmConsole'>

<TABLE width='100%' cellspacing='8' border='0'>
<TR><TD>
      Translate
</TD></TR>
</TABLE>

</jh:form>
</js:body>
</HTML>
