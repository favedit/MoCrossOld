<%@ include file='/apl/public.inc' %>
<jh:define form='wfc.frame' alias='frame_form'/>
<jh:define item='&frame_form.frame|{&frame_form.name}' alias='frame'/>
<jh:define item='&frame_form.dataset|{&frame_form.name}' alias='dataset'/>
<jh:define item='&frame_form.page|{&frame_form.name}' alias='page'/>

<HTML>
<!-- Html Header Initialize ------------------------------->
<HEAD>
<js:meta/>
<js:css/>
<js:js/>
<jh:title caption='FavInfo'/>
</HEAD>

<SCRIPT>
var m_bExecute = false;
function _li(oTr){
   oTr.style.cursor = 'hand';
   oTr.style.backgroundColor = '#EEEEEE';
}
function _lo(oTr){
   oTr.style.cursor = 'normal';
   oTr.style.backgroundColor = '#FFFFFF';
}
// Record click
function _rc(oTr){
   var oElement = event.srcElement;
   var oCheck = oTr.cells[0].children[1];
   if(oElement == oCheck){
   }else if(oElement == oTr.cells[0]){
      oCheck.checked = !oCheck.checked;
   }else if(!m_bExecute){
      m_bExecute = true;
<jh:notEmpty item='&frame.form'>
      frmConsole.action = '<jh:context/>/inc/wfc/frame/form/Update.wa?fn=<jh:item item='&frame_form.form'/>&kp=' + oTr.keys_;
      frmConsole.submit();
</jh:notEmpty>
   }
}
// Record double click
function _rd(oTr){
   var oCheck = oTr.cells[0].children[0];
   oCheck.checked = true;
   doUpdate();
}
// Select record
function _sr(oThis){
   var arSelect = frmConsole.sel_;
   var arSelects = frmConsole.sels_;
   if(arSelect.length > 1){
      for(var n=0; n<arSelect.length; n++){
         if(arSelect[n] != oThis){
            arSelect[n].checked = oThis.checked;
            arSelects[n].value = oThis.checked?'Y':'N';
         }
      }
   }
}
function doFetch(){
}
function doSearch(){
}
function doEdit(){
   frmConsole.action = '<jh:context/>/inc/wfc/frame/table/ListEdit.wa'
   frmConsole.submit();
}
function doInsert(){
   frmConsole.action = '<jh:context/>/inc/wfc/frame/form/Insert.wa?fn=<jh:item item='&frame_form.form'/>';
   frmConsole.submit();
}
function doDelete(){
   var bSelect = false;
   var arSelect = frmConsole.sel_;
   if(arSelect.length > 1){
      for(var n=1; n<arSelect.length; n++){
         if(arSelect[n].checked){
            bSelect = true;
            break;
         }
      }
   }
   if(bSelect){
      frmConsole.action = '<jh:context/>/inc/wfc/frame/form/Delete.wa?fr=<jh:item item='&frame_form.name'/>&fn=<jh:item item='&frame_form.form'/>'
      frmConsole.submit();
   }else{
      alert('Please select less than one record.');
   }
}
function doPage(nPage){
   frmConsole.<jh:name item='&page.page'/>.value = nPage;
   frmConsole.action = '<jh:context/>/inc/wfc/frame/table/List.wa?fa=p'
   frmConsole.submit();
}
function doPageSize(nPage){
   frmConsole.action = '<jh:context/>/inc/wfc/frame/table/List.wa?fa=p'
   frmConsole.submit();
}
</SCRIPT>

<jc:toolbar name='top.frames.frmToolbar.oToolbar' inFrame='frmWork' inForm='frmConsole' target='frmWork' action='refresh'>
   <jc:tbButton caption='trs:sys|button.fetch|Fetch' action='doFetch()' icon='sys.query'/>
   <jc:tbButton caption='trs:sys|button.search|Search' action='doSearch()' icon='sys.search'/>
   <jc:tbButton caption='|'/>
   <jc:tbButton caption='trs:sys|button.edit|Edit' action='doEdit()' icon='sys.edit'/>
   <jh:notEmpty item='&frame.form'>
      <jc:tbButton caption='|'/>
      <jc:tbButton caption='trs:sys|button.insert|Insert' action='doInsert()' icon='sys.insert'/>
      <jc:tbButton caption='trs:sys|button.delete|Delete' action='doDelete()' icon='sys.delete'/>
   </jh:notEmpty>
</jc:toolbar>

<!-- Body Initialize -------------------------------------->
<js:body styleClass='frmWork' scroll='auto'>
<js:form name='frmConsole' dataForm='top.frmToolbar.frmConsole'>
<jh:edit type='hidden' item='&page.page'/>

<!-- Page Control Initialize ------------------------------>
<TABLE width='100%' border='0' cellspacing='0' cellpadding='8'>
<TR><TD>

<TABLE width='100%' border='0' cellspacing='0' cellpadding='0'>
<TR>
<TD align='right'>
<jc:navigator item='&dataset' action='doPage'
   first='trs:sys|navigator.first|First'
   prior='trs:sys|navigator.prior|Prior'
   next='trs:sys|navigator.next|Next'
   last='trs:sys|navigator.last|Last'
   format='{page}/{pages}   {first} | {prior} | {next} | {last}'/>
</TD>
<TD width='8'></TD>
<TD width='40'>
<jh:combo item='&page.size' source='lst:com.frame.page.size' onchange='doPageSize()'/>
</TD>
</TR>
</TABLE>
<js:wfctable action='view'/>

</TD></TR>
</TABLE>

</js:form>
</js:body>
</HTML>

