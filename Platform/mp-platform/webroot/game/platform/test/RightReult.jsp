<%@ page contentType='text/html;charset=utf-8' %>
<%@ include file='/apl/public.inc' %>
<jh:define source='&page.systemInfo' alias='systemInfo'/>
<jh:define source='&page.processesInfo' alias='processesInfo'/>
<HTML>
<HEAD>
<TITLE>Welcome</TITLE>
<js:css/>
<js:js type='runtime.client'/>
<meta http-equid="refresh" content="5"/>
</HEAD>
<!-- Script   rightReult-------------------------------------------------->
<SCRIPT language='javascript'>
var tree = null;
var toolbar = null;
//----------------------------------------------------------
function doStart(code){
   //此处将process_code传到action中去。
   fmMain.process_code.value = code;
   alert(code);
	fmMain.action = '<jh:context/>/platform/server/Server.wa?do=startProcess';
	fmMain.submit();
}
//----------------------------------------------------------
function refresh(){
   window.location.reload();
}
//----------------------------------------------------------
function doStop(code){
   fmMain.process_code.value = code;
	fmMain.action = '<jh:context/>/platform/server/Server.wa?do=stopProcess';
	fmMain.submit();
}
//----------------------------------------------------------
function doCommand(code){
   alert(code);
	fmMain.action = '<jh:context/>/platform/server/Server.wa?do=' + code;
	fmMain.submit();
}
//----------------------------------------------------------
function _onload(){
}
</SCRIPT>
<!-- Toolbar config ------------------------------------------>
<XML ID="xToolBar">
<ToolBar width='100%' height='100%' align='right'>
	<ToolButtonText name='btnForm' label='服务器列表' icon='sys.pst.mgr'/>
	<ToolButton name='btnRefresh' icon='tool.refresh' action='refreshNode()'/>
</ToolBar>
</XML>
<!-- Body begin ---------------------------------------------->
<jh:body style='bodyCatalog' onload='_onload()' scroll='no'>
<jh:form name='fmMain' method='post'>
<!-- Hidden -------------------------------------------------->
<jh:hidden name='form_name'/>
<jh:hidden name='server_name' source='&page.server_name'/>
<jh:hidden name='process_code'/>
<jh:hidden name='sel_type'/>
<jh:hidden name='sel_collection'/>
<jh:hidden name='sel_component'/>
<!-- Catalog ------------------------------------------------->
<TABLE width='100%' border='0' cellpadding='0' cellspacing='0'>
<!-- System begin -------------------------------------------->
<TR><TD style='padding:12' bgcolor='#fcfcef'>
<FONT color='red'><B><jh:write source='&systemInfo.label'/>（<jh:write source='&systemInfo.host'/>）</B></FONT><BR><BR>
<TABLE>
   <TR><TD><B>物理内存：</B></TD>
      <TD>总共</TD><TD align='right'><jh:write source='&systemInfo.memory_total_label'/></TD>
      <TD>使用</TD><TD align='right'><jh:write source='&systemInfo.memory_use_label'/></TD>
      <TD>剩余</TD><TD align='right'><jh:write source='&systemInfo.memory_free_label'/></TD>
   </TR>
   <TR><TD><B>交换内存：</B></TD>
      <TD>总共</TD><TD align='right'><jh:write source='&systemInfo.swap_total_label'/></TD>
      <TD>使用</TD><TD align='right'><jh:write source='&systemInfo.swap_use_label'/></TD>
      <TD>剩余</TD><TD align='right'><jh:write source='&systemInfo.swap_free_label'/></TD>
   </TR>
</TABLE>
</TD></TR>
<!-- System end ---------------------------------------------->
<!-- System begin -------------------------------------------->
<TR><TD height='1' bgcolor='#666666'></TD></TR>
<!-- System end ---------------------------------------------->
<!-- Process list begin -------------------------------------->
<TR><TD style='padding:12'>
<TABLE width='100%' border='0' cellpadding='4' cellspacing='1' style='border:1px solid #FFFFFF;'>
<TR>
   <TD width='60'>进程状态：</TD>
   <TD>
      <jh:empty source='&processesInfo.start_codes'>全部启动</jh:empty>
      <jh:notEmpty source='&processesInfo.start_codes'><A href="javascript:doStart('<jh:write source='&processesInfo.start_codes'/>')">全部启动</A></jh:notEmpty> |
      <jh:empty source='&processesInfo.stop_codes'>全部停止</jh:empty>
      <jh:notEmpty source='&processesInfo.stop_codes'><A href="javascript:doStop('<jh:write source='&processesInfo.stop_codes'/>')">全部停止</A></jh:notEmpty>  |
      <A href="javascript:refresh()" >刷新</A>
   </TD>
</TR>
</TABLE>
<TABLE width='100%' border='0' bgcolor='#CCCCCC' cellpadding='4' cellspacing='1'>
<TR bgcolor='#F8F8F8'>
   <TD width='60' align='center'><A href="javascript:doCommand('compiler')">自动编译</A><FONT color='#888888'></TD>
   <TD>
      <A href="javascript:doCommand('compilerUpdate')">更新版本</A><FONT color='#888888'>：从版本管理器上更新内容。</FONT></BR>
      <A href="javascript:doCommand('compilerCode')">代码生成</A><FONT color='#888888'>：根据配置生成枚举，实体，消息的代码。</FONT></BR>
      <A href="javascript:doCommand('compilerClear')">清除编译</A><FONT color='#888888'>：清除所有编译项目。</FONT></BR>
      <A href="javascript:doCommand('compilerBuild')">重新编译</A><FONT color='#888888'>：重新编译所有项目。</FONT></BR>
   </TD>
</TR>
<TR bgcolor='#F8F8F8'>
   <TD width='60' align='center'><A href="javascript:doCommand('publish')">自动发布</A><FONT color='#888888'></TD>
   <TD>
      <A href="javascript:doCommand('pack')">打包版本</A><FONT color='#888888'>：生成打包的版本。</FONT></BR>
      <A href="javascript:doCommand('unpack')">解包版本</A><FONT color='#888888'>：安装打包的版本。</FONT></BR>
   </TD>
</TR>
</TABLE>
</DIV>
<BR>
<TABLE width='100%' border='0' bgcolor='#666666' cellpadding='4' cellspacing='1'>
<TR bgcolor='#F0F0F0'>
   <TD width='40' align='right'>编号</TD>
   <TD width='60'>用户</TD>
   <TD width='160'>名称</TD>
   <TD style='white-space:nowrap'>标签</TD>
   <TD width='40' align='center'>CPU</TD>
   <TD width='40' align='center'>MEM</TD>
   <TD width='100' align='right'>虚拟内存</TD>
   <TD width='100' align='right'>全部内存</TD>
   <TD width='40' align='center'>等待</TD>
   <TD width='80' align='center'>状态</TD>
   <TD width='100'>命令</TD>
</TR>
<jh:loop source='&processesInfo' alias='processInfo'>
<jh:equals source='&processInfo.status' value='run'><TR bgcolor='#DDFAD5'></jh:equals>
<jh:equals source='&processInfo.status' value='stop'><TR bgcolor='#FFFFFF'></jh:equals>
   <TD style='white-space:nowrap' align='right'><jh:write source='&processInfo.id'/></TD>
   <TD style='white-space:nowrap'><jh:write source='&processInfo.user'/></TD>
   <TD style='white-space:nowrap'><jh:write source='&processInfo.name'/></TD>
   <TD style='white-space:nowrap'><jh:write source='&processInfo.label'/></TD>
   <TD style='white-space:nowrap' align='center'><jh:write source='&processInfo.cpu_rate'/></TD>
   <TD style='white-space:nowrap' align='center'><jh:write source='&processInfo.memory_rate'/></TD>
   <TD style='white-space:nowrap' align='right'><jh:write source='&processInfo.memory_virtual'/></TD>
   <TD style='white-space:nowrap' align='right'><jh:write source='&processInfo.memory_total'/></TD>
   <TD style='white-space:nowrap' align='center'><jh:write source='&processInfo.option_wait'/></TD>
   <TD style='white-space:nowrap' align='center'><jh:write source='&processInfo.status'/></TD>
   <TD>
      <jh:equals source='&processInfo.status' value='run'>
      <A href="javascript:doStop('<jh:write source='&processInfo.process_code'/>')">停止</A>
      </jh:equals>
      <jh:equals source='&processInfo.status' value='stop'>
      <A href="javascript:doStart('<jh:write source='&processInfo.name'/>')">启动</A>
      </jh:equals>
   </TD>
</TR>
</jh:loop>
</TABLE>
<BR>　　总计信息：
<BR>　　　　物理内存： <jh:write source='&processesInfo.memory_total'/>
<BR>　　　　虚拟内存： <jh:write source='&processesInfo.memory_virtual'/>
</TD></TR>
<!-- Process list end ---------------------------------------->
</TABLE>
</jh:form>
</jh:body>
<!-- Body end ------------------------------------------------>
</HTML>
