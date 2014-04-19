<%@ page contentType='text/html;charset=utf-8' %>
<%@ include file='/apl/public.inc' %>
<HTML>
<!-- Html Header <S> -------------------------------------->
<HEAD>
<je:css/>
<je:js type='runtime'/>
<jh:title caption='Error'/>
</HEAD>
<!-- Html Header <E> -------------------------------------->
<SCRIPT language='javascript'>
function _onBack(){
   history.back();
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
   <ToolButton name='backButton' type='back' label='返回' disp_access='' icon='commu' action='_onBack()'/>
</ToolBar>
</XML>

<!-- Body Initialize -------------------------------------->
<jh:body scroll='no' onload='_onload()'>
<jh:form name='frmConsole'>

<TABLE width='100%' height='100%' border='0' cellspacing='0' cellpadding='0'>
<TR height='1'>
   <TD>
<!-- Toolbar <S> ------------------------------------------>
      <DIV id='_id_toolbar' style='width:100%; height:100%'></DIV>
<!-- Toolbar <E> ------------------------------------------>
   </TD>
<TR>
<TR height='1'>
   <TD>
<!-- Title <S> -------------------------------------------->
      <TABLE width='100%'>
      <TR>
         <TD align='center' valign='middle' >
            <jh:img src='/ars/icon/sys/msg/logicError.jpg' align='absmiddle'/>
            <jh:img src='/ars/icon/sys/msg/logicError_Caption.jpg'/>
            <BR><BR><BR>
            <FONT color='red'>
               由于以下原因使本次操作产生了错误，<BR><BR>
               如果您对该错误存在疑问，请联系系统管理员。
            </FONT>
            <BR>
         </TD>
      </TR>
      </TABLE>
<!-- Title <E> -------------------------------------------->
   </TD>
<TR>
<TR height='1'>
   <TD style='padding:0 16'>
<!-- Description <S> -------------------------------------->
      <TABLE width='100%' height='100%' bgcolor='#94B6FF' border='0' cellspacing='1' cellpadding='0' style='table-layout:fixed'>
      <TR bgcolor='#FFFFFF'>
         <TD style='padding:8 8'>
            <TABLE width='100%' height='100%' border='0' cellspacing='0' cellpadding='0'>
            <TR>
               <TD width='30' align='center'><jh:img src='/ars/icon/sys/msg/error.gif' align='absmiddle'/></TD>
               <TD><FONT color='red'><jh:write source='&message.message'/></FONT></TD>
            </TR>
            </TABLE>
         </TD>
      </TR>
      </TABLE>
<!-- Description <E> -------------------------------------->
   </TD>
</TR>
<TR><TD>&nbsp;</TD></TR>
</TABLE>
<!-- Error message <S> ------------------------------------>
<DIV style='display:none'>
<jh:write source='&message.description' format='text'/>
</DIV>
<!-- Error message <E> ------------------------------------>
</jh:form>
</jh:body>
</HTML>
