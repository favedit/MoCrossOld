<%@ include file='/apl/public.inc' %>

<HTML>
<HEAD>
<js:css/>
<js:js type='runtime.client'/>
</HEAD>
<SCRIPT>
function _onload(){
   top.RLoader.loaded('pg:main');
}
</SCRIPT>
<!------------------------------------------------------------>
<jh:body style='FrameMain' scroll='no' onload='_onload()'>
<jh:form name='fmMain'/>
</jh:body>

</HTML>

