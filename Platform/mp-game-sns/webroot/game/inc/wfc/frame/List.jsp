<%@ include file='/apl/public.inc' %>
<jh:define form='wfc.frame' alias='frame_form'/>
<jh:define item='&frame_form.dataset|{&frame_form.name}' alias='dataset'/>
<jh:define item='&frame_form.page|{&frame_form.name}' alias='page'/>

<HTML>
<!-- Html Header Initialize ------------------------------->
<HEAD>
<js:meta/>
<js:css/>
<js:js top='Y'/>
<jh:title caption='FavInfo'/>
</HEAD>

<SCRIPT>
var m_bExecute = false;
function _li(oTr){
   oTr.style.cursor = 'hand';
   oTr.style.backgroundColor = '#EEEEEE';
}
function _lo(oTr){
   oTr.style.cursor = 'normal';
   oTr.style.backgroundColor = '#FFFFFF';
}
function _rc(oTr){
   if(!m_bExecute){
      m_bExecute = true;
      var oWindow = dialogArguments['window'];
      var sCallback = dialogArguments['callback'];
      var oList = new FList();
      oList.unpack(oTr.pack_);
      eval('oWindow.' + sCallback + '(oList)');
      close();
   }
}
function doPage(nPage){
   frmConsole.<jh:name item='&page.page'/>.value = nPage;
   frmConsole.action = '<jh:context/>/inc/wfc/frame/List.wa?fa=p'
   frmConsole.submit();
}
function doPageSize(nPage){
   frmConsole.target = '_self';
   frmConsole.action = '<jh:context/>/inc/wfc/frame/List.wa?fa=p'
   frmConsole.submit();
}
</SCRIPT>

<!-- Body Initialize -------------------------------------->
<js:body styleClass='frmWork' scroll='auto'>
<js:form name='frmConsole'>
<jh:edit type='hidden' item='&page.page'/>

<!-- Page Control Initialize ------------------------------>
<TABLE width='100%' border='0' cellspacing='0' cellpadding='8'>
<TR><TD>

<TABLE width='100%' border='0' cellspacing='0' cellpadding='0'>
<TR>
<TD align='right'>
<jc:navigator item='&dataset' action='doPage'
   first='trs:sys|navigator.first|First'
   prior='trs:sys|navigator.prior|Prior'
   next='trs:sys|navigator.next|Next'
   last='trs:sys|navigator.last|Last'
   format='{page}/{pages}   {first} | {prior} | {next} | {last}'/>
</TD>
<TD width='8'></TD>
<TD width='40'>
<jh:combo item='&page.size' source='lst:com.frame.page.size' onchange='doPageSize()'/>
</TD>
</TR>
</TABLE>

<js:lov/>

</TD></TR>
</TABLE>

</js:form>
</js:body>
</HTML>

