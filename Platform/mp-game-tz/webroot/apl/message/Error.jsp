<%@ page contentType='text/html;charset=utf-8' %>
<%@ include file='/apl/public.inc' %>
<HTML>
<!-- Html Header <S> -------------------------------------->
<HEAD>
<je:css/>
<je:js type='runtime'/>
<jh:title caption='业务错误'/>
</HEAD>
<!-- Html Header <E> -------------------------------------->
<SCRIPT language='javascript'>
function _onOk(){
}
//----------------------------------------------------------
function _onloadAll(){
   MoJS.initialize();
   // 创建工具栏
   toolbar = RControl.fromXml(xToolBar, _id_toolbar);
}
//----------------------------------------------------------
function _onload(){
   RWindow.connect(window);
   RLoader.loadJs('mobj');
   RLoader.waitJs(new TInvoke(null, _onloadAll), 'mobj');
}
</SCRIPT>
<!-- Toolbar Initialize ----------------------------------->
<XML ID="xToolBar">
<ToolBar width='100%' >
   <ToolButton name='OkButton' type='insert' label='OK' disp_access='' icon='commu' action='_onOk()'/>
</ToolBar>
</XML>

<!-- Body Initialize -------------------------------------->
<jh:body scroll='no' onload='_onload()'>
<jh:form name='frmConsole'>

<TABLE width='100%' height='100%' border='0' cellspacing='0' cellpadding='0' bgcolor='#F0F0FF'>
<TR height='1'>
   <TD>
<!-- Toolbar <S> ------------------------------------------>
      <DIV id='_id_toolbar' style='width:100%; height:100%'></DIV>
<!-- Toolbar <E> ------------------------------------------>
   </TD>
<TR>
<TR height='80'>
   <TD>
<!-- Title <S> -------------------------------------------->
      <TABLE width='100%' cellpadding='4'>
      <TR>
         <TD align='center' valign='middle' >
            <jh:img src='/ars/icon/sys/msg/lerror.gif' align='absmiddle'/>
            <jh:img src='/ars/icon/n.gif' width='8'/>
            <FONT color='red'><B>逻辑错误</B></FONT>
            <BR><BR>
            <FONT color='red'>由于以下原因使得本次操作产生了错误，如果您对该错误存在疑问，请联系管理员。</FONT>
         </TD>
      </TR>
      </TABLE>
<!-- Title <E> -------------------------------------------->
   </TD>
<TR>
<TR height='60'>
   <TD style='padding:0 16'>
<!-- Description <S> -------------------------------------->
      <TABLE width='100%' height='100%' bgcolor='#94B6FF' border='0' cellspacing='1' cellpadding='0' style='table-layout:fixed'>
      <TR bgcolor='#FFFFFF'>
         <TD>
            <DIV style='width:100%;height:100%;overflow:auto;white-space:nowrap;padding:8;'>
               <TABLE width='100%' height='100%' border='0' cellspacing='0' cellpadding='0'>
               <TR>
                  <TD width='30' align='center'><jh:img src='/ars/icon/sys/msg/error.gif' align='absmiddle'/></TD>
                  <TD style='color:red'><jh:write source='&message.code'/></TD>
               </TR>
               </TABLE>
            </DIV>
         </TD>
      </TR>
      </TABLE>
<!-- Description <E> -------------------------------------->
   </TD>
</TR>
<TR>
   <TD style='padding:16 16'>
<!-- Detail <S> ------------------------------------------->
      <TABLE width='100%' height='100%' bgcolor='#94B6FF' border='0' cellspacing='1' cellpadding='0' style='table-layout:fixed'>
      <TR bgcolor='#FFFFFF'>
         <TD>
            <DIV style='width:100%;height:100%;overflow:auto;color:red;white-space:nowrap;padding:8'>
               <jh:write source='&message.description'/>
            </DIV>
         </TD>
      </TR>
      </TABLE>
<!-- Detail <E> ------------------------------------------->
   </TD>
</TR>
</TABLE>
</jh:form>
</jh:body>
</HTML>
