<%@ include file='/apl/public.inc' %>

<HTML>

<!-- Html Header Initialize ------------------------------->
<HEAD>
<jh:css/>
<jh:js/>
<jh:title caption='Message'/>
</HEAD>

<SCRIPT FOR=document EVENT=onkeydown>
if(event.keyCode == 13){
   _onClickOk();
}
</SCRIPT>

<SCRIPT>
function _onClickOk(){
   dialogArguments['result'].dataRetryAction = true;
   window.close();
}
function _onClickCancel(){
   window.close();
}
var messageBox = null;
function _load(){
	// Build MessageBox
	messageBox = RControl.fromXml(xMessageBox, _id_msgbox);
	messageBox.loadMessages(dialogArguments['messages']);
	// Button
	var btnOk = RHtml.find('_id_button_ok');
	var btnCancel = RHtml.find('_id_button_cancel');
	var btnClose = RHtml.find('_id_button_close');
	if(EMessage.Valid == messageBox.type){
		btnOk.style.display = 'none';
		btnCancel.style.display = 'none';
		btnClose.style.display = 'block';
      btnClose.focus();
	}else if(EMessage.Fatal == messageBox.type || EMessage.Error == messageBox.type){
		btnOk.style.display = 'none';
		btnCancel.style.display = 'none';
		btnClose.style.display = 'block';
      btnClose.focus();
	}else if(EMessage.Warn == messageBox.type){
		btnOk.style.display = 'block';
		btnCancel.style.display = 'block';
		btnClose.style.display = 'none';
      btnOk.focus();
	}else if(EMessage.Info == messageBox.type){
		btnOk.style.display = 'block';
		btnCancel.style.display = 'none';
		btnClose.style.display = 'none';
		btnOk.focus();
	}
}
</SCRIPT>

<!-- MessageBox config --------------------------------------->
<XML ID="xMessageBox">
<MessageBox/>
</XML>
<!-- Body start ---------------------------------------------->
<jh:body styleclass='frmWork' scroll='no' onload='_load();'>
<jh:form name='frmConsole'>
<DIV id='_div'></DIV>
<TABLE width='100%' height='100%' border='0' cellspacing='0' cellpadding='4'>
<TR><TD colspan='2' height='20' style="filter:progid:DXImageTransform.Microsoft.Gradient(gradienttype=1, startcolorstr=red, endcolorstr=#FFFFFF);">
   <FONT color='#FFFFFF'><B>Message</B></FONT>
</TD></TR>
<TR valign='top'>
   <TD width='100%' id='_id_msgbox'></TD>
   <TD width='200px'>
      <TABLE width='100%' border='0' cellspacing='0' cellpadding='4'>
         <TR id='_id_button_ok'><TD><INPUT type='button' name='btnOk' value='Ok' style='width:100' onclick='_onClickOk()'></TD></TR>
         <TR id='_id_button_cancel'><TD><INPUT type='button' name='btnCancel' value='Cancel' style='width:100' onclick='_onClickCancel()'></TD></TR>
         <TR id='_id_button_close'><TD><INPUT type='button' name='btnClose' value='Close' style='width:100' onclick='window.close()'></TD></TR>
         <TR><TD><jh:img icon='n'/></TD></TR>
         <TR><TD><INPUT type='button' value='Copy' style='width:100' onclick='window.close()'></TD></TR>
      </TABLE>
   </TD>
<TR>
</TABLE>

</jh:form>
</jh:body>
<!-- Body end ------------------------------------------------>
</HTML>
