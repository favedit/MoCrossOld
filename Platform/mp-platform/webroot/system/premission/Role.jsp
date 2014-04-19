<%@ include file='/apl/public.inc' %>

<HTML>
<!-- Html Header Initialize ------------------------------->
<HEAD>
<js:meta/>
<js:css/>
<js:js/>
<jh:title caption='FavInfo'/>
</HEAD>

<SCRIPT>
function initialize(){
   top.MoveBorderManager.resetPosition();
   WindowManager.createWindow(window, 'com.pms.tbwRole');
   WindowManager.createWindow(window, 'com.pms.fmwRole');
}
</SCRIPT>

<!-- Body Initialize -------------------------------------->
<js:body styleClass='frmMDI' onload='initialize();'>
<js:form name='frmConsole'/>
</js:body>
</HTML>

