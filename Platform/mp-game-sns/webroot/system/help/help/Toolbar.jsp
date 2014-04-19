<%@ include file='/apl/public.inc' %>
<HTML>
<HEAD>
<META http-equiv='content-type' content='text/html; charset=UTF-8'>
<jh:css css='/css/jp.css'/>
<jh:js src='/js/lang.js'/>
<jh:js src='/js/ctl_com.js'/>
<jh:js src='/js/frame.js'/>
<jh:js src='/js/system.js'/>
<jh:title caption='txt|title.toolbar'/>
</HEAD>

<SCRIPT language='javascript'>
IResource.init("<jh:context/>/res");
</SCRIPT>

<!-- Body Initialize [ Begin ] ---------------------------->
<BODY class="frmToolbar">
<FORM name="frmConsole" method='POST'>
<SCRIPT language='javascript'>
function dispatchAction(sAction){
   frmConsole.wa = sAction;
   frmConsole.submit();
}
</SCRIPT>

<!-- Page Control Initialize ------------------------------>
<jh:define form='test' alias='test_form'/>
<jh:write item='&test_form.user'/>
<jh:write item='&test_form.user2'/>

<TABLE width='100%' border='0' cellspacing='0' cellpadding='0' class='comPageCtlTab'>
<TR><TD height='2'></TD></TR>
<TR background='/ods/res/img/mark/h1.gif' height='24px'><TD>
<DIV id='id_oPageCtl'></DIV>
<SCRIPT>
var oPageCtl=null;
function _onTabSheetClick(oPage){
   top.frames.frmCatalog.frames.frmCatalogInner.frmConsole.page_index.value = oPage.name;
}
</SCRIPT>
</TD></TR>
</TABLE>

<!-- Toolbar Initialize ----------------------------------->
<TABLE width='100%' bgcolor='#666666' border='0' cellspacing='0' cellpadding='0' height='1'><TR><TD></TD></TR></TABLE>
<TABLE border='0' height='22px' width='100%' cellpadding='0' cellspacing='0'>
   <TR valign='middle'>
      <TD bgcolor='#FFFFFF' width='4px'>
         <TABLE border='0' width='2' cellspacing='0' cellpadding='1'>
            <TR><TD><IMG style='border:1px inset' height=1 width=1></TD></TR>
            <TR><TD><IMG style='border:1px inset' height=1 width=1></TD></TR>
            <TR><TD><IMG style='border:1px inset' height=1 width=1></TD></TR>
            <TR><TD><IMG style='border:1px inset' height=1 width=1></TD></TR>
         </TABLE>
      </TD>
      <TD width='100%' style='filter:progid:DXImageTransform.Microsoft.Gradient(gradienttype=1 , startcolorstr=#FFFFFF, endcolorstr=#999999)'>
         <DIV id='id_oToolBar'></DIV><SCRIPT language='javascript'>var oToolBar=null;</SCRIPT>
      </TD>
   </TR>
</TABLE>
<TABLE width='100%' bgcolor='#666666' border='0' cellspacing='0' cellpadding='0' height='1'><TR><TD></TD></TR></TABLE>
</FORM>
<SCRIPT language='javascript'>
//------------------------------------------------------------
oToolBar = newInstance('LH.ToolBar');
oToolBar.linkHTML = id_oToolBar;
oToolBar.refresh();
//------------------------------------------------------------
oPageCtl = newInstance('LH.PageCtl');
oPageCtl.linkHTML = id_oPageCtl;
oPageCtl.targetForm = "frmBody";
oPageCtl.targetJSP = "";
oPageCtl.newSheet("DSA","ASD","wap.pcinfo","/wap/Info.wa","Hint");
oPageCtl.newSheet("DSA2","ASD2","wap.pcinfo","/wap/Info.wa","Hint");
oPageCtl.onSheetClick = _onTabSheetClick;

oPageCtl.selectPage(0);
frmConsole.target = "frmBody";
frmConsole.wa = "/wap/Info.wa";
//frmConsole.submit();

</SCRIPT>
</BODY>
</HTML>

