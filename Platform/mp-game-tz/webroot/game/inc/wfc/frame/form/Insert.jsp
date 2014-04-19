<%@ include file='/apl/public.inc' %>
<jh:define form='wfc.frame' alias='frame_form'/>
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
function doBack(){
   frmConsole.action = '<jc:history action='prior' params='fa=b'/>';
   frmConsole.submit();
}
var m_sFields = null;
function doLovBack(oParams){
   var sFix = '<jh:name item='&unit'/>';
   var arFields = m_sFields.split(',');
   var sField = null;
   var sFrom = null;;
   for(var n=0; n<arFields.length; n++){
      var nIndex = arFields[n].indexOf(' ');
      if(nIndex == -1){
         sField = sFrom = arFields[n];
      }else{
         sFrom = arFields[n].substring(0, nIndex);
         sField = arFields[n].substring(nIndex+1);
      }
      var sValue = oParams.nameValue(sFrom);
      try{
         eval('frmConsole.'+sFix+sField.toLowerCase()).value = sValue;
      }catch(e){
         try{
            eval(sFix+sField.toLowerCase()).innerText = sValue;
         }catch(e){}
      }
   }
}
function doLov(sFrame, sFields){
   m_sFields = sFields;
   var sUri = '<jh:context/>/inc/wfc/frame/List.wa?fn='+sFrame;
   IHTML.modal('doLovBack', sUri, 600, 400);
}
function doSave(){
   frmConsole.action = '<jh:context/>/inc/wfc/frame/form/InsertSave.wa?fn=<jh:item item='&frame_form.name'/>';
   frmConsole.submit();
}
</SCRIPT>

<jc:toolbar name='top.frames.frmToolbar.oToolbar' inFrame='frmWork' inForm='frmConsole' target='frmWork' action='refresh'>
   <jc:tbButton caption='trs:sys|button.save|Save' action='doSave()' icon='sys.update'/>
   <jc:tbButton caption='trs:sys|button.back|Back' action='doBack()' icon='sys.back'/>
</jc:toolbar>

<!-- Body Initialize -------------------------------------->
<js:body styleClass='frmWork' scroll='auto'>
<js:form name='frmConsole' dataForm='top.frmToolbar.frmConsole'>

<!-- Page Control Initialize ------------------------------>
<TABLE width='100%' border='0' cellspacing='0' cellpadding='8'>
<TR><TD>
<js:wfcform action='insert' item='&unit'/>
</TD></TR>
</TABLE>

</js:form>
</js:body>
</HTML>

