<%@ page contentType='text/html;charset=utf-8' %>
<%@ include file='/apl/public.inc' %>

<HTML>
<HEAD>
<TITLE>资源: <jh:write source='&page.resourceId'/> <jh:write source='&page.resourceLabel'/></TITLE>
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

var _formName = "<jh:write source='&page.formName'/>";
var _ouid = "<jh:write source='&page.ouid'/>";
var _over = "<jh:write source='&page.over'/>";

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
	fmMain.target = 'fmFrame';
	if(sender.name == 'information'){
		var uri = "<jh:context path='/apl/logic/form/WebForm.wa'/>?do=update&form_name={0}&form_search={1}";
		var fs = new TAttributes();
		fs.set('ouid', _ouid);
		fs.set('over', _over);
		uri = RStr.format(uri, _formName, fs.pack());
		fmMain.action = uri;
		fmMain.submit();
		return;
	}
	if(sender.name == 'discussion'){
		uri = "<jh:context path='/apl/logic/form/WebForm.wa'/>?do=show&form_name=logic.resource.DiscussionList";
		fmMain.action = uri;
		fmMain.submit();
		return;
	}
	if(sender.name == 'note'){
		uri = "<jh:context path='/apl/logic/form/WebForm.wa'/>?do=show&form_name=logic.resource.NoteList";
		fmMain.action = uri;
		fmMain.submit();
		return;
	}
	var uri = "<jh:context path='/apl/logic/resource/'/>" + sender.page;
	fmMain.action = uri;
	fmMain.submit();
}
function doDelete(){
   form.doAction('deleteAction');
}
function _load(){
	pagebar = RControl.fromXml(xPageBar, _id_pagebar);
	//
	var uri = "<jh:context path='/apl/logic/form/WebForm.wa'/>?do=update&form_name={0}&form_search={1}";
	var fs = new TAttributes();
	fs.set('ouid', _ouid);
	fs.set('over', _over);
	uri = RStr.format(uri, _formName, fs.pack());
	fmMain.action = uri;
	fmMain.target = 'fmFrame';
	fmMain.submit();
}
</SCRIPT>
<!-- Form ---------------------------------------------------->
<XML id='xPageBar'>
<PageBar>
	<PageTab name='information' page='WebInformation.wa' icon="#sys.information" label="基本信息" action='doPage(this)'/>
	<PageTab name='store' page='WebStore.wa' icon="#sys.store" label="文件目录" action='doPage(this)'/>
	<PageTab name='discussion' page='WebDiscussion.wa' icon="#sys.discussion" label="评论信息" action='doPage(this)'/>
	<PageTab name='note' page='WebNote.wa' icon="#sys.note" label="记事本" action='doPage(this)'/>
	<PageTab name='subscribte' page='WebSubscribte.wa' icon="#sys.subscribte" label="订阅管理" action='doPage(this)'/>
	<PageTab name='calendar' page='WebCalendar.wa' icon="#sys.calendar" label="工作日历" action='doPage(this)'/>
	<PageTab name='history' page='WebHistory.wa' icon="#sys.history" label="历史信息" action='doPage(this)'/>
	<PageTab name='help' page='WebHelp.wa' icon="#sys.help" label="帮助信息" action='doPage(this)'/>
</PageBar>
</XML>

<BODY onload='_load()' scroll='no' style='margin:0;padding:0' bgcolor="#2cc8e7">
<jh:form>

<TABLE width="100%" height='100%' border="0" cellpadding="0" cellspacing="0">
<!-- 标题栏定义 ------------------------------------------->
<TR>
	<TD height="22" bgcolor="#0082a3" style='padding-left:16;color:#FFFFFF;filter:progid:DXImageTransform.Microsoft.Gradient(gradienttype=1, startcolorstr=#0082a3, endcolorstr=#8b41d0);'>
		基本信息 ： <jh:write source='&page.resourceId'/> <jh:write source='&page.resourceLabel'/>
	</TD>
</TR>
<!-- 内容显示开始 ----------------------------------------->
<TR><TD>
<!-- 分栏显示 --------------------------------------------->
<TABLE width="100%" height='100%' border="0" cellpadding="0" cellspacing="0" style='table-layer:fix'>
<TR>
	<TD bgcolor='#FFFFFF' style='border-left:1 solid #1f8ba1; border-right:1 solid #1f8ba1'>
		<IFRAME name='fmFrame' width='100%' height='100%' frameborder='0'></IFRAME>
	</TD>
</TR>
<TR>
<TD height="34" id='_id_pagebar' style='padding-left:10'></TD>
</TR>
</TABLE>
<!-- 内容显示结束 ----------------------------------------->
</TD></TR>
</TABLE>
</jh:form>
</BODY>
</HTML>