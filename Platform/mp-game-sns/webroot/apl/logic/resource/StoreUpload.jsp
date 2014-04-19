<%@ page contentType='text/html;charset=utf-8' %>
<%@ include file='/apl/public.inc' %>

<HTML>
<HEAD>
<TITLE>资源: 10012 文件上传</TITLE>
<je:css/>
<je:js/>
<jh:js src='/ajs/4-ctl.window/FPageTab.js' />
<jh:js src='/ajs/4-ctl.window/FPageBar.js' />
<jh:js src='/ajs/logic/FLgSpliter.js' />
</HEAD>

<!-- Script -------------------------------------------------->
<SCRIPT>
var form = null;
var toolbar = null;
var pagebar = null;
function doUpload(){
	fmMain.action = '<jh:context/>/apl/logic/resource/WebStore.wa?do=uploadSave';
	fmMain.submit();
}
function doClose(){
   this.close();
}
function _load(){
   toolbar = RControl.fromXml(xToolBar, _id_toolbar);
}
</SCRIPT>
<!-- Form ---------------------------------------------------->
<XML ID="xToolBar">
<ToolBar width='100%'>
	<ToolButton name='btnInsert' label='开始上传' icon='tool.insert' icon_disable='tool.insertd' action='doUpload()'/>
	<ToolButtonSplit/>
	<ToolButton name='btnAction' label='关闭' icon='tool.action' icon_disable='tool.actiond' action='doClose()'/>
</ToolBar>
</XML>
<BODY onload='_load()' scroll='no' style='margin:0;padding:0' bgcolor="#2cc8e7">
<jh:form multipart='true'>
<table width="100%" height='100%' border="0" cellpadding="0" cellspacing="0">
<tr>
	<td height="22" id='_id_toolbar'></td>
</tr>
<tr valign='top'>
	<td bgcolor='#FFFFFF' style='padding:10;'>
		<table width="100%" border="0" cellpadding="8" cellspacing="0">
		<tr>
			<td width='50'>文件 1:</td><td><INPUT type='file' name='file1' style='width:300px;'></td>
		</tr>
		<tr>
			<td>文件 2:</td><td><INPUT type='file' name='file2' style='width:300px;'></td>
		</tr>
		<tr>
			<td>文件 3:</td><td><INPUT type='file' name='file3' style='width:300px;'></td>
		</tr>
		<tr>
			<td>文件 4:</td><td><INPUT type='file' name='file4' style='width:300px;'></td>
		</tr>
		<tr>
			<td>文件 5:</td><td><INPUT type='file' name='file5' style='width:300px;'></td>
		</tr>
		</table>
	</td>
</tr>
</table>
</jh:form>
</BODY>
</HTML>