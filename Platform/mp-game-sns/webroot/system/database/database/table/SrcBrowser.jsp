<%@ include file="/inc/page/begin.inc" %>
<jh:define form='db.source' alias='source_form'/>

<HTML>

<!-- Html Header Initialize ------------------------------->
<HEAD>
<js:meta/>
<js:css/>
<js:js/>
<jh:title caption='FavInfo'/>
</HEAD>

<!-- Javascript Initialize ----------------------------------->
<SCRIPT language='javascript'>
function executeActionCopyToClipboard(){
   window.clipboardData.setData('Text', idSource.innerText);
   alert('<jh:write item='txt|sys|info.copytoclipboardok'/>');
}
function executeActionRefresh(){
   frmConsole.submit();
}
function _executeUpdate(){
   frmConsole.action = '';
   frmConsole.target = '_self';
   frmConsole.Action.value = 'update';
   frmConsole.submit();
}
function _executeDownload(){
   frmConsole.action = '<jh:context/>/wfc.sys.database.table.source.wb?type=<jh:item item='&source_form.type'/>&name=<jh:item item='&source_form.name'/>';
   frmConsole.target = '_blank';
   frmConsole.target = '_self';
   frmConsole.Action.value = 'update';
   frmConsole.submit();
}
</SCRIPT>

<!-- Toolbar Initialize ----------------------------------->
<jc:toolbar name='top.frames.frmToolbar.oToolbar' inFrame='frmWork' inForm='frmConsole' target='frmWork' action='refresh'>
    <jc:tbButton caption='trs:sys|button.back|Back' pageAction='/system/database/table/SrcInfo.wa' icon='sys.back'/>
    <jc:tbButton caption='trs:sys|button.refresh|Refresh' action='executeActionRefresh()' icon='sys.refresh'/>
    <jc:tbButton caption='|'/>
    <jc:tbButton caption='trs:sys|button.download|Download' action='_executeDownload()' icon='sys.download'/>
    <jc:tbButton caption='|'/>
    <jc:tbButton caption='trs:sys|button.copytoclipboard|Copy To Clipboard' action='executeActionCopyToClipboard()' icon='sys.copy'/>
    <jc:tbButton caption='trs:sys|button.execute|Execute' action='_executeUpdate()' icon='sys.update'/>
</jc:toolbar>

<!-- Body Initialize -------------------------------------->
<js:body styleClass='frmWork'>
<jh:form name='frmConsole'>
<jh:edit type='hidden' name='Action'/>
<TABLE width='100%' cellspacing='8' border='0'>
<TR><TD>

<TABLE width='100%' bgcolor='#94B6FF' border='0' cellspacing='1' cellpadding='4'>
   <TR bgcolor='#FFFFFF'><TD id='idSource' class='sqlSource'>
      <jh:item item='&source_form.source_html' format='no'/>
   </TD></TR>
</TABLE>

</TD></TR>
</TABLE>
</jh:form>
</js:body>
</HTML>
<%@ include file="/inc/page/end.inc" %>
