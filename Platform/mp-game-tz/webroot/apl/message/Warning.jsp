<%@ page contentType='text/html;charset=utf-8' %>
<%@ include file='/apl/public.inc' %>
<LINK rel='stylesheet' href='/eUIS/acs/control.css' type='text/css'>
<LINK rel='stylesheet' href='/eUIS/ats/00/cs/control.css' type='text/css'>
<LINK rel='stylesheet' href='/eUIS/ats/00/cs/lang_cn.css' type='text/css'>
<LINK rel='stylesheet' href='/eUIS/ats/00/cs/site_cn.css' type='text/css'>
<SCRIPT src="/eUIS/ajs/client.js"></SCRIPT>
<SCRIPT>RGlobal.initialize('/eUIS','','cn','00','/ats/00/rs/icon','/ats/00/rs/pic');</SCRIPT>
<SCRIPT src="/eUIS/ajs/2-base/FEditor.sc.js"></SCRIPT>
<SCRIPT src="/eUIS/ajs/2-base/FDropEditor.sc.js"></SCRIPT>
<SCRIPT src="/eUIS/ajs/3-core/FCompleteConsole.sc.js"></SCRIPT>
<SCRIPT src="/eUIS/ajs/4-ctl.form/FEdit.sc.js"></SCRIPT>
<SCRIPT src="/eUIS/ajs/4-ctl.form/FEditEditor.sc.js"></SCRIPT>
<SCRIPT src="/eUIS/ajs/4-ctl.form/FSelect.sc.js"></SCRIPT>
<SCRIPT src="/eUIS/ajs/4-ctl.form/FSelectEditor.sc.js"></SCRIPT>
<SCRIPT src="/eUIS/ajs/4-ctl.form/FSelectItem.sc.js"></SCRIPT>
<SCRIPT src="/eUIS/ajs/4-ctl.table/FCellStatus.sc.js"></SCRIPT>
<SCRIPT src="/eUIS/ajs/4-ctl.table/FCellProcessStatus.sc.js"></SCRIPT>
<SCRIPT src="/eUIS/ajs/4-ctl.table/FColumnStatus.sc.js"></SCRIPT>
<SCRIPT src="/eUIS/ajs/4-ctl.table/FRow.sc.js"></SCRIPT>
<SCRIPT src="/eUIS/ajs/4-ctl.table/FTable.sc.js"></SCRIPT>

<HTML>

<!-- Html Header Initialize ------------------------------->
<HEAD>
<js:css/>
<jh:js/>
<jh:title caption='Error'/>
</HEAD>

<SCRIPT language='javascript'>
var m_bExecute = false;
function _onOk(){
	   if(!m_bExecute){
		      frmConsole.wa = '';
		      frmConsole.submit();
		   }
		   m_bExecute = true;
}
function _onCancel(){
}

function _onload(){
      // TreeView
      var toolbar = RControl.fromXml(xToolBar, _id_toolbar);
}
</SCRIPT>

<!-- Toolbar Initialize ----------------------------------->
<XML ID="xToolBar">
<ToolBar width='100%'>
   <ToolButton name='OkButton' type='insert' label='OK' disp_access='' icon='commu' action='_onOk()'/>
   <ToolButton name='Cancelutton' type='insert' label='Cancel' disp_access='' icon='commu' action='_onCancel()'/>
</ToolBar>
</XML>

<!-- Body Initialize -------------------------------------->
<jh:body style='bodyCatalog' scroll='no' onload='_onload()'>
<jh:form name='frmConsole'>

<TABLE width='100%' cellspacing = '0' cellpadding='0'>
   <TR>
      <TD><DIV id='_id_toolbar' style='width:100%; height:100%'><DIV></TD>
   </TR>
	<TR>
      <TD>
	      <TABLE width='100%' border='0' cellpadding='4'>
	         <TR>
               <TD align='center' valign='middle'>
			         <jh:img src='/ars/icon/sys/msg/lwarn.gif' align='absmiddle'/>
			         <jh:img src='/ars/icon/n.gif' width='8'/>
			         <jh:write source='警告'/>
	               <BR><BR>
	               <FONT color='#FFFFFF'><B><jh:write source='您的操作可能产生不可预知的错误，请您慎重选择'/></B></FONT>
	            </TD>
            </TR>
	      </TABLE>
	    </TD>
   </TR>
   <TR>
      <TD>
         <TABLE width='100%' bgcolor='#94B6FF' border='0' cellspacing='1' cellpadding='0'>
            <TR bgcolor='#FFFFFF'><TD>
               <TABLE width='100%' border='0' cellspacing='4' cellpadding='0'>
                  <TR>
                     <TD>
	                     <jh:loop source = '&message.messages' alias='msg'>
                        <TR>
                           <TD width='20px'><jh:img src='/ars/icon/sys/msg/warn.gif' align='absmiddle'/></TD>
                           <TD><FONT color='#000000'><jh:write source='&msg.message'/></FONT><br></TD>
                        </TR>
                        </jh:loop>
                     </TD>
                  </TR>
	            </TABLE> 
            </TD></TR>
         </TABLE>
      </TD>
   </TR>
</TABLE>
<BR>

<TABLE width='100%' bgcolor='#94B6FF' border='0' cellspacing='1' cellpadding='0'>
<TR bgcolor='#FFFFFF'><TD>
   <TABLE width='100%' border='0' cellspacing='4' cellpadding='0'>
   <TR>
      <TD valign='top'><jh:img src='/ars/icon/sys/msg/info.gif' align='absmiddle' style='cursor:hand'/> 详细信息：</TD>
      <TD nowrap>
         <jh:loop source = '&message.messages' alias = "msg">
            <FONT color='#000000'><jh:write source='&msg.description'/></FONT>
         </jh:loop>
      </TD>
   </TR>
   </TABLE>
</TD></TR>
</TABLE>

</TD></TR>

<TR><TD>
   <TABLE width='100%' border='0' cellspacing='0' cellpadding='4'>
      <TR>
         <TD align='center'>
         </TD>
   </TABLE>
</TD></TR>
</TABLE>

</jh:form>
</jh:body>
</HTML>
