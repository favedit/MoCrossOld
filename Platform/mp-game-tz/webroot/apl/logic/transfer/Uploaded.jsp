<%@ page contentType='text/html;charset=utf-8' %>
<%@ include file='/apl/public.inc' %>

<HTML>
<HEAD>
<je:css/>
<je:js type='runtime'/>
<!--------------------------------------------------------->
<SCRIPT language='javascript'>
function _onload(){
   var lc = top.RConsole.find('FListenerConsole');
   var arg = new Object();
   arg.resultCode = "<jh:write source='&page.resultCode' format='text'/>";
   arg.result = "<jh:write source='&page.result' format='text'/>";
   arg.workerId = "<jh:write source='&page.workerId' format='text'/>";
   arg.attachment = "<jh:write source='&page.attachment' format='text'/>";
   arg.attachmentId = "<jh:write source='&page.attachmentId' format='text'/>";
   arg.attachmentGuid = "<jh:write source='&page.attachmentGuid' format='text'/>";
   arg.attachmentExtension = "<jh:write source='&page.attachmentExtension' format='text'/>";
   arg.attributes = "<jh:write source='&page.attributes' format='script.string'/>";
   lc.process(top.FUploadConsole, 'onFileUploaded', null, arg);
}
</SCRIPT>
<!--------------------------------------------------------->
<BODY style='margin:0; padding:0' onload='_onload()'></BODY>
</HTML>
