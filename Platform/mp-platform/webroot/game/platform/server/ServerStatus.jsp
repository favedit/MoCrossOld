<%@ page contentType='text/html;charset=utf-8' %>
<%@ include file='/apl/public.inc' %>
<jh:define source='&page.systemInfo' alias='systemInfo'/>
<jh:define source='&page.processesInfo' alias='processesInfo'/>
<jh:define source='&page.disksInfo' alias='disksInfo'/>
<jh:define source='&page.cpusInfo' alias='cpusInfo'/>
<HTML>
<HEAD>
<TITLE>Welcome</TITLE>
<js:css/>
<js:js type='runtime.client'/>
</HEAD>
<!-- Script -------------------------------------------------->
<SCRIPT language='javascript'>
var tree = null;
var toolbar = null;
//----------------------------------------------------------
function doStart(code){
   fmMain.process_code.value = code;
   //alert(code);
	fmMain.action = '<jh:context/>/platform/server/Server.wa?do=startProcess';
	fmMain.submit();
}
//----------------------------------------------------------
function doStop(code){
   fmMain.process_code.value = code;
	fmMain.action = '<jh:context/>/platform/server/Server.wa?do=stopProcess';
	fmMain.submit();
}

//----------------------------------------------------------

function doCommand(code){
	fmMain.action = '<jh:context/>/platform/server/Server.wa?do=' + code;
	fmMain.submit();
}

//----------------------------------------------------------
function _onload(){
    
}

function refresh(servername){
   fmMain.sel_collection.value = servername;
   fmMain.action = '<jh:context/>/platform/server/Server.wa?do=showServer';
	fmMain.submit();
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
<jh:hidden name='server_files' source='&processesInfo.server_files'/>
<!-- Catalog ------------------------------------------------->
<TABLE width='100%' border='0' cellpadding='0' cellspacing='0'>
<!-- System begin -------------------------------------------->
<TR><TD style='padding:12' bgcolor='#fcfcef'>
<FONT color='red'><B><jh:write source='&systemInfo.label'/>（<jh:write source='&systemInfo.host'/>）</B></FONT><BR><BR>
<TABLE border="1" cellpadding="1" cellspacing="1" style="text-align:center;float:left;border:1px solid black;border-collapse:collapse;">
   <TR style="height:20px"><TD><B>物理内存:</B></TD>
      <TD>使用率</TD><TD align='right'style="color:green"><jh:write source='&systemInfo.memory_totle_percent_label'/>%</TD>
      <TD>总共</TD><TD align='right'><jh:write source='&systemInfo.memory_total_label'/></TD>
      <TD>使用</TD><TD align='right'><jh:write source='&systemInfo.memory_use_label'/></TD>
      <TD>剩余</TD><TD align='right'><jh:write source='&systemInfo.memory_free_label'/></TD>
   
   </TR>
   <TR style="height:20px"><TD><B>交换内存:</B></TD>
      <TD>使用率</TD><TD align='right'style="color:green"><jh:write source='&systemInfo.swap_totle_percent_label'/>%</TD>
      <TD>总共</TD><TD align='right'><jh:write source='&systemInfo.swap_total_label'/></TD>
      <TD>使用</TD><TD align='right'><jh:write source='&systemInfo.swap_use_label'/></TD>
      <TD>剩余</TD><TD align='right'><jh:write source='&systemInfo.swap_free_label'/></TD>
     
   </TR>
   <TR style="height:20px">
      <TD>&nbsp;</TD>
      <TD><B>使用率</B></TD>
      <TD><B>文件系统</B></TD>
      <TD><B>总共</B></TD>
      <TD><B>使用</B></TD>
      <TD><B>剩余</B></TD>
      <TD colspan="3"><B>挂载点</B></TD>
   </TR>
   <jh:loop source='&disksInfo' alias='diskinfo'>
    <TR style="height:20px"><TD><B>&nbsp;&nbsp;&nbsp;&nbsp;硬盘:</B></TD>
      <TD align='right'style="color:green"><jh:write source='&diskinfo.disk_userate'/>%</TD>
      <TD align='right'><jh:write source='&diskinfo.file_system'/></TD>
      <TD align='right'><jh:write source='&diskinfo.disk_size'/></TD>
      <TD align='right'><jh:write source='&diskinfo.disk_used'/></TD>
      <TD align='right'><jh:write source='&diskinfo.disk_avail'/></TD>
      <TD align='right'colspan="3"><jh:write source='&diskinfo.mounted_on'/></TD>
   </TR>
   </jh:loop>
</TABLE>

<table border="1" cellpadding="1" cellspacing="1" style="border:1px solid black;border-collapse:collapse;text-align:right;">
    <TR style="height:20px">
      <TD>&nbsp;</TD>
      <jh:loop source='&cpusInfo' alias='cpuinfo'>
        <TD><B>&nbsp;cpu</TD>
      </jh:loop>
   </TR>
   <TR style="height:20px">
      <TD><B>编号</B></TD>
      <jh:loop source='&cpusInfo' alias='cpuinfo'>
        <TD align='right'><jh:write source='&cpuinfo.cpu_id'/></TD>
      </jh:loop>
   </TR>
   <TR style="height:20px">
      <TD><B>使用率</B></TD>
       <jh:loop source='&cpusInfo' alias='cpuinfo'>
          <TD align='right'style="color:green"><jh:write source='&cpuinfo.cpu_used'/>%</TD>
      </jh:loop>  
   </TR>
   <TR style="height:20px">
      <TD><B>剩余</B></TD>
      <jh:loop source='&cpusInfo' alias='cpuinfo'>
         <TD align='right'><jh:write source='&cpuinfo.cpu_avail'/>%</TD>
      </jh:loop>   
   </TR>
   
</table>



</TD></TR>
<!-- System end ---------------------------------------------->
<!-- System begin -------------------------------------------->
<TR><TD height='1' bgcolor='#666666'></TD></TR>
<!-- System end ---------------------------------------------->
<!-- Process list begin -------------------------------------->
<TR><TD style='padding:12'>
<TABLE width='100%' border='0' cellpadding='4' cellspacing='1' style='border:1px solid #FFFFFF;'>
<TR>
   <TD width='80'>进程状态：</TD>
   <TD>
      <jh:empty source='&processesInfo.start_codes'>全部启动</jh:empty>
      <jh:notEmpty source='&processesInfo.start_codes'><A href="javascript:doStart('<jh:write source='&processesInfo.start_codes'/>')">全部启动</A></jh:notEmpty> |
      <jh:empty source='&processesInfo.stop_codes'>全部停止</jh:empty>
      <jh:notEmpty source='&processesInfo.stop_codes'><A href="javascript:doStop('<jh:write source='&processesInfo.stop_codes'/>')">全部停止</A></jh:notEmpty> |
      <A href="javascript:refresh('<jh:write source='&page.server_name'/>')">刷新</A> |
      <A href="javascript:test()">停止监控</A>
      
   </TD>
</TR>
</TABLE>
<TABLE width='100%' border='0' bgcolor='#CCCCCC' cellpadding='4' cellspacing='1'>
<TR bgcolor='#F8F8F8'>
   <TD width='60' align='center'><A href="javascript:doCommand('serverDeployment')">自动发布</A><FONT color='#888888'></TD>
   <TD style="text-align:right;width:80px">
      <A href="javascript:doCommand('ddd')">    打包版本</A></BR>
      <A href="javascript:doCommand('createUser')">    创建用户</A></BR>
      <A href="javascript:doCommand('extractingVersion')">    解包版本</A></BR>
      <A href="javascript:doCommand('changeConfig')">修改配置文件</A></BR>
      <A href="javascript:doCommand('executeSqlDispose')"> 创建sql数据</A></BR>
   </TD>
   <TD>
      <FONT color='#888888'>：生成打包版本。</FONT></BR>
      <FONT color='#888888'>：创建用户，新建目录。</FONT></BR>
      <FONT color='#888888'>：安装打包的版本,更改权限。</FONT></BR>
      <FONT color='#888888'>：修改的文件包括（数据库连接、内部端口、外部端口、数据库配置文件）。</FONT></BR>
      <FONT color='#888888'>：创建数据库和执行sql脚本。</FONT></BR>
   </TD>
</TR>
<TR bgcolor='#F8F8F8'>
   <TD width='60' align='center'>更新<FONT color='#888888'></TD>
   <TD style="text-align:right;">
      <A href="javascript:doCommand('updateVersion')">更新版本</A></BR>
      <A href="javascript:doCommand('cacheClear')">清除缓存</A></BR>
      <A href="javascript:doCommand('executeSqlPatch')">升级数据库</A></BR>
   </TD>
   <TD>
      <FONT color='#888888'>：从版本管理器上更新内容。</FONT></BR>
      <FONT color='#888888'>：清除服务器缓存服务。</FONT></BR>
      <FONT color='#888888'>：更新sql补丁。</FONT></BR>
   </TD>
</TR>
<TR bgcolor='#F8F8F8'>
   <TD width='60' align='center'><A href="javascript:doCommand('compiler')">自动编译</A><FONT color='#888888'></TD>
   <TD style="text-align:right;">
      <A href="javascript:doCommand('compilerCode">代码生成</A></BR>
      <A href="javascript:doCommand('compilerClear')">清除编译</A></BR>
      <A href="javascript:doCommand('compilerBuild')">重新编译</A></BR>
   </TD>
   <TD>
      <FONT color='#888888'>：根据配置生成枚举，实体，消息的代码。</FONT></BR>
      <FONT color='#888888'>：清除所有编译项目。</FONT></BR>
      <FONT color='#888888'>：重新编译所有项目。</FONT></BR>
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
