<%@ include file='/apl/public.inc' %>
<jh:define form='wfc.frame' alias='frame_form'/>
<jh:define item='&frame_form.sheet' alias='sheet'/>
<jh:define item='&frame_form.sheet_frame' alias='sheet_frame'/>
<jh:define item='&frame_form.unit|{&frame_form.name}' alias='unit'/>

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

<SCRIPT language='javascript'>
var m_bExecute = false;
function _li(oTr){
   oTr.style.cursor = 'hand';
   oTr.style.backgroundColor = '#EEEEEE';
}
function _lo(oTr){
   oTr.style.cursor = 'normal';
   oTr.style.backgroundColor = '#FFFFFF';
}
function _rc(oTr){
   if(!m_bExecute){
      m_bExecute = true;
      frmConsole.action = '<jh:context/>/inc/wfc/frame/form/Update.wa?fn=<jh:item item='&sheet_frame.form'/>&kp=' + oTr.keys_;
      frmConsole.submit();
   }
}
function doBack(){
   frmConsole.action = '<jc:history action='prior' params='fa=b'/>';
   frmConsole.submit();
}
function doSheet(sSheet){
   frmConsole.action = '';
   frmConsole.ps.value = sSheet;
   frmConsole.submit();
}
function doInsert(){
   frmConsole.action = '<jh:context/>/inc/wfc/frame/form/Insert.wa?fn=<jh:item item='&sheet_frame.form'/>&pi=<jh:item item='&frame_form.sheet_insert'/>';
   frmConsole.submit();
}
</SCRIPT>

<jc:toolbar name='top.frames.frmToolbar.oToolbar' inFrame='frmWork' inForm='frmConsole' target='frmWork' action='refresh'>
   <jc:tbButton caption='trs:sys|button.save|Save' pageAction='/inc/wfc/frame/form/UpdateSave.wa' icon='sys.save'/>
   <jc:tbButton caption='trs:sys|button.back|Back' action='doBack()' icon='sys.back'/>
   <jh:isTrue item='&frame_form.has_sheet'>
      <jc:tbButton caption='|'/>
      <jc:tbButton caption='trs:sys|button.insert|Insert' action='doInsert()' icon='sys.insert'/>
   </jh:isTrue>
</jc:toolbar>

<!-- Body Initialize -------------------------------------->
<js:body styleClass='frmWork' scroll='auto'>
<js:form name='frmConsole' dataForm='top.frmToolbar.frmConsole'>
<jh:edit type='hidden' name='ps'/>

<!-- Page Control Initialize ------------------------------>
<TABLE width='100%' border='0' cellspacing='0' cellpadding='8'>
<TR><TD>
<js:wfcform action='update' item='&unit'/>
</TD></TR>
</TABLE>

</js:form>
</js:body>
</HTML>

