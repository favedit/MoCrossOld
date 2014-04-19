<%@ page contentType='text/html;charset=utf-8' %>
<%@ include file='/apl/public.inc' %>

<HTML>
<HEAD>
<TITLE>资源: 10012 中华料理 - 讨论</TITLE>
<je:css/>
<je:js/>
<jh:js src='/ajs/4-ctl.window/FPageTab.js' />
<jh:js src='/ajs/4-ctl.window/FPageBar.js' />
<jh:js src='/ajs/logic/FLgSpliter.js' />
</HEAD>

<!-- Script -------------------------------------------------->
<SCRIPT>
var action = 'update';
var form = null;
var toolbar = null;
var pagebar = null;
function doRefresh() {
   fmMain.action = '';
   fmMain.submit();
}
function doInsert() {
   form.doAction('insertAction');
}
function doSave() {
   if (RWindow.inDisable) {
      return;
   }
   if ('insert' == action) {
      form.doAction('insertAction');
   } else if ('update' == action) {
      form.doAction('updateAction');
   } else {
      alert('Unknown action [' + action + ']');
   }
}
function doPage(sender){
	fmMain.action = sender.page;
	fmMain.submit();
}
function doDelete(){
   form.doAction('deleteAction');
}
function _load(){
	toolbar = RControl.fromXml(xToolBar, _id_toolbar);
}
</SCRIPT>
<!-- Form ---------------------------------------------------->
<XML ID="xToolBar">
<ToolBar width='100%'>
	<ToolButton name='btnUpdate' label='发表评论' icon='tool.update' icon_disable='tool.updated' action='doUpdate()'/>
	<ToolButtonSplit/>
	<ToolButton name='btnAction' label='关闭' icon='tool.action' icon_disable='tool.actiond'/>
</ToolBar>
</XML>
<BODY onload='_load()' scroll='no' style='margin:0;padding:0' bgcolor="#2cc8e7">
<jh:form>
<table width="100%" height='100%' border="0" cellpadding="0" cellspacing="0">
   <tr>
      <td height="1" bgcolor="#00CCFF" id='_id_toolbar'></td>
   </tr>
   <tr>
      <td bgcolor='#FFFFFF' style='border-left:1 solid #1f8ba1; border-right:1 solid #1f8ba1'>
			<table width="100%" height='100%' border="0" cellpadding="0" cellspacing="0">
         <tr>
            <td>Body</td>
         </tr>
      </table></td>
   </tr>
</table>
</jh:form>
</BODY>
</HTML>