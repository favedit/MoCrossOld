<%@ include file="/inc/page/begin.inc" %>

<HTML>

<!-- Html Header Initialize ------------------------------->
<HEAD>
<js:meta/>
<js:css/>
<js:js top='Y'/>
<jh:title caption='Message'/>
</HEAD>

<SCRIPT FOR=document EVENT=onkeydown>
if(event.keyCode == 13){
   oMsgCtl.cmdOk();
}
</SCRIPT>

<js:body styleClass='frmWork' jstop='true' scroll='no' onload='messageLoad();'>
<js:form name='frmConsole'>
<TABLE width='100%' height='100%' border='0' cellspacing='0' cellpadding='4'>
<TR><TD colspan='2' height='20' style="filter:progid:DXImageTransform.Microsoft.Gradient(gradienttype=1, startcolorstr=red, endcolorstr=#FFFFFF);">
   <FONT color='#FFFFFF'><B>Message</B></FONT>
</TD></TR>
<TR valign='top'>
   <TD width='100%'>
      <TABLE width='100%' height='100%' border='0' cellspacing='0' cellpadding='2'>
         <TR><TD height='20px' valign='bottom'>Message:</TD></TR>
         <TR><TD>
            <TABLE width='100%' height='100%' border='0' cellspacing='1' cellpadding='4' bgcolor='#666666'>
               <TR valign='top' bgcolor='#FFFFFF'><TD>
                  <DIV id='id_message'></DIV>
               </TD></TR>
            </TABLE>
         </TD></TR>
         <TR><TD height='20px' valign='bottom'>Description:</TD></TR>
         <TR><TD height='80px'>
            <TABLE width='100%' height='100%' border='0' cellspacing='1' cellpadding='4' bgcolor='#666666'>
               <TR valign='top' bgcolor='#FFFFFF'><TD>
                  <DIV id='id_description'></DIV>
               </TD></TR>
            </TABLE>
         </TD></TR>
      </TABLE>
   </TD>
   <TD width='200px'>
      <TABLE width='100%' border='0' cellspacing='0' cellpadding='4'>
         <TR id='btn_ok' style='display:none'><TD><INPUT type='button' name='btnOk' value='Ok' style='width:100' onclick='oMsgCtl.cmdOk()'></TD></TR>
         <TR id='btn_cancel' style='display:none'><TD><INPUT type='button' name='btnCancel' value='Cancel' style='width:100' onclick='oMsgCtl.cmdCancel()'></TD></TR>
         <TR id='btn_close' style='display:none'><TD><INPUT type='button' name='btnClose' value='Close' style='width:100' onclick='window.close()'></TD></TR>
         <TR><TD><jh:img src='/res/img/n.gif'/></TD></TR>
         <TR><TD><INPUT type='button' value='Copy' style='width:100' onclick='window.close()'></TD></TR>
      </TABLE>
   </TD>
<TR>
</TABLE>

</js:form>
</js:body>
</HTML>

<SCRIPT language='javascript'>
function FXMLMessage(){
   this.className = 'FXMLMessage';
   this.bodyHTML = null;
   this.messagesNode = null;
   this.cmdOk = lx_msg_cmdOk;
   this.cmdCancel = lx_msg_cmdCancel;
   this.refresh = lx_msg_refresh;
   return this;
}
function lx_msg_cmdOk(){
   dialogArguments['control'].dataRetryAction = true;
   window.close();
}
function lx_msg_cmdCancel(){
   window.close();
}
function lx_msg_refresh(){
   document.body.disabled = true;
   var oTableHTML = document.createElement('TABLE');
   oTableHTML.width = '100%';
   oTableHTML.className = 'msgTable';
   this.bodyHTML.insertBefore(oTableHTML);
   var oMsgNode = null;
   var arMsgNodes = this.messagesNode.nodes;
   var bValid = false;
   var bInfo = false;
   var bWarn = false;
   var bError = false;
   var sMessage = null;
   for(var n=0; n<arMsgNodes.length; n++){
      oMsgNode = arMsgNodes[n];
      if(IString.equals(oMsgNode.name, 'message')){
         var oRowHTML = oTableHTML.insertRow();
         oRowHTML.className = 'msgTableLine';
         var sIcon = null;
         var sType = IString.nvl(oMsgNode.attribute('type')).toLowerCase();
         if(sType == 'v'){
            bValid = true;
            sIcon = 'sys.msg.valid';
         }else if(sType == 'i'){
            bInfo = true;
            sIcon = 'sys.msg.info';
         }else if(sType == 'w'){
            bWarn = true;
            sIcon = 'sys.msg.warn';
         }else if(sType == 'e'){
            bError = true;
            sIcon = 'sys.msg.error';
         }else if(sType == 'f'){
            bError = true;
            sIcon = 'sys.msg.error';
         }
         var oIconCellHTML = oRowHTML.insertCell();
         oIconCellHTML.width = '24px';
         var oIconHTML = document.createElement('IMG');
         oIconHTML.src = ResourceManager.path(sIcon);
         oIconCellHTML.insertBefore(oIconHTML);
         var oMsgHTML = oRowHTML.insertCell();
         sMessage = oMsgNode.attribute('message');
         oMsgHTML.innerText = sMessage.replace(/\\n/g, '\n');
      }
   }
   if(bValid){
      btn_ok.style.display = 'none';
      btn_cancel.style.display = 'none';
      btn_close.style.display = 'block';
   }else if(bError){
      btn_ok.style.display = 'none';
      btn_cancel.style.display = 'none';
      btn_close.style.display = 'block';
   }else if(bWarn){
      btn_ok.style.display = 'block';
      btn_cancel.style.display = 'block';
      btn_close.style.display = 'none';
   }else if(bInfo){
      btn_ok.style.display = 'block';
      btn_cancel.style.display = 'none';
      btn_close.style.display = 'none';
   }
   document.body.disabled = false;
   if(bValid){
      frmConsole.btnClose.focus();
   }else if(bError){
      frmConsole.btnClose.focus();
   }else if(bWarn){
      frmConsole.btnOk.focus();
   }else if(bInfo){
      frmConsole.btnOk.focus();
   }
}
var oMsgCtl = null;
function messageLoad(){
   oMsgCtl = new FXMLMessage();
   oMsgCtl.bodyHTML = id_message;
   oMsgCtl.messagesNode = dialogArguments['message'];
   if(!oMsgCtl){
      window.close();
   }
   oMsgCtl.refresh();
}
</SCRIPT>

<%@ include file="/inc/page/end.inc" %>
