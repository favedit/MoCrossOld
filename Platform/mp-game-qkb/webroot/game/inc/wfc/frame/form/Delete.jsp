<%@ include file='/apl/public.inc' %>
<jh:define form='wfc.frame' alias='frame_form'/>
<jh:define item='&frame_form.dataset|{&frame_form.name}' alias='dataset'/>

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
function doBack(){
   if(!m_bExecute){
      m_bExecute = true;
      frmConsole.action = '<jc:history action='prior' params='fa=b'/>';
      frmConsole.submit();
   }
}
</SCRIPT>

<jc:toolbar name='top.frames.frmToolbar.oToolbar' inFrame='frmWork' inForm='frmConsole' target='frmWork' action='refresh'>
   <jc:tbButton caption='trs:sys|button.delete|Delete' pageAction='/inc/wfc/frame/form/DeleteSave.wa' icon='sys.delete'/>
   <jc:tbButton caption='trs:sys|button.back|Back' action='doBack()' icon='sys.back'/>
</jc:toolbar>

<!-- Body Initialize -------------------------------------->
<js:body styleClass='frmWork' scroll='auto'>
<js:form name='frmConsole' dataForm='top.frmToolbar.frmConsole'>

<!-- Page Control Initialize ------------------------------>
<TABLE width='100%' border='0' cellspacing='0' cellpadding='8'>
<TR><TD>
<jh:loop item='&dataset' alias='unit' loopAlias='looper'>
<js:wfcform action='delete' item='&unit'/>
<jh:isPosition item='&looper' position='before.last'><HR style='height:1'></jh:isPosition>
</jh:loop>
</TD></TR>
</TABLE>

</js:form>
</js:body>
</HTML>

