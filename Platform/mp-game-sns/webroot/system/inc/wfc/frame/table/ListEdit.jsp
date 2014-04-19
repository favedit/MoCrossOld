<%@ include file='/apl/public.inc' %>
<jh:define form='wfc.frame' alias='frame_form'/>
<jh:define item='&frame_form.frame' alias='frame'/>
<jh:define item='&frame_form.dataset' alias='dataset'/>

<HTML>
<!-- Html Header Initialize ------------------------------->
<HEAD>
<js:meta/>
<js:css/>
<js:js/>
<jh:title caption='FavInfo'/>
</HEAD>

<SCRIPT>
function _li(oTr){}
function _lo(oTr){}
function _rc(oTr){}
function _rd(oTr){}
function _sd(oSelect){}
function doUpdate(){
   frmConsole.action = '<jh:context/>/inc/wfc/frame/table/ListEditSave.wa'
   frmConsole.submit();
}
function doBack(){
   frmConsole.action = '<jh:context/>/inc/wfc/frame/table/List.wa?fa=b'
   frmConsole.submit();
}
</SCRIPT>

<jc:toolbar name='top.frames.frmToolbar.oToolbar' inFrame='frmWork' inForm='frmConsole' target='frmWork' action='refresh'>
   <jc:tbButton caption='trs:sys|button.update|Update' action='doUpdate()' icon='sys.update'/>
   <jc:tbButton caption='trs:sys|button.back|Back' action='doBack()' icon='sys.back'/>
</jc:toolbar>

<!-- Body Initialize -------------------------------------->
<js:body styleClass='frmWork' scroll='auto'>
<js:form name='frmConsole' dataForm='top.frmToolbar.frmConsole'>

<!-- Page Control Initialize ------------------------------>
<TABLE width='100%' border='0' cellspacing='0' cellpadding='8'>
<TR><TD>
<js:wfctable action='update'/>
</TD></TR>
</TABLE>

</js:form>
</js:body>
</HTML>

