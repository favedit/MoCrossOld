<%@ include file='/apl/public.inc' %>
<jh:define form='session' alias='session_form'/>
<jh:define form='sys.toolbar' alias='toolbar_form'/>

<HTML>
<!-- Html Header Initialize ------------------------------->
<HEAD>
<js:meta/>
<js:css/>
<js:js/>
<jh:js src='/js/mo_pgc.js'/>
<jh:title caption='FavInfo'/>
</HEAD>

<!-- Body Initialize -------------------------------------->
<js:body styleClass='frmToolbar' onload='pageInitialize();'>
<js:form name='frmConsole'>

<!-- Page Control Initialize ------------------------------>
<TABLE width='100%' border='0' cellspacing='0' cellpadding='0' class='comPageCtlTab'>
<TR><TD height='2'></TD></TR>
<TR background='/res/img/mark/h1.gif'><TD>
<DIV id='id_oPageCtl'></DIV>
<SCRIPT>
var oPageCtl = null;
function __onSheetClick(oNode){
   if(oNode.tvNode){
      frmConsole.node_xml.value = oNode.tvNode.xmlNode.xml;
   }
   frmConsole.page_xml.value = oNode.xmlNode.xml;
   frmConsole.target = 'frmWork';
   frmConsole.action = oNode.action;
   frmConsole.submit();
}
function pageInitialize(){
   oPageCtl = new FPageControl();
   oPageCtl.bodyHTML = id_oPageCtl;
   oPageCtl.pageURL = '<jh:context/>/dev.doc.page.wss';
   oPageCtl.targetForm = 'frmBody';
   oPageCtl.targetJSP = '';
   oPageCtl.onSheetClick = __onSheetClick;
   oPageCtl.loadSheets();
}
</SCRIPT>
</TD>
</TR>
</TABLE>

<!-- Toolbar Initialize ----------------------------------->
<TABLE width='100%' bgcolor='#666666' border='0' cellspacing='0' cellpadding='0' height='1px'><TR><TD></TD></TR></TABLE>
<TABLE width='100%' bgcolor='#F0F0F0' border='0' cellpadding='0' cellspacing='0' height='22px'>
   <TR>
      <TD width='8px' valign='middle'>
         <TABLE border='0' width='2' cellpadding='0' cellspacing='1'>
            <TR><TD><IMG style='border:1px inset' height='1px' width='1px'></TD></TR>
            <TR><TD><IMG style='border:1px inset' height='1px' width='1px'></TD></TR>
            <TR><TD><IMG style='border:1px inset' height='1px' width='1px'></TD></TR>
            <TR><TD><IMG style='border:1px inset' height='1px' width='1px'></TD></TR>
         </TABLE>
      </TD>
      <TD><jc:toolbar name='oToolbar' action='init'/></TD>
   </TR>
</TABLE>
<TABLE width='100%' bgcolor='#666666' border='0' cellspacing='0' cellpadding='0' height='1px'><TR><TD></TD></TR></TABLE>
</js:form>
</js:body>
</HTML>

