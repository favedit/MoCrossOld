<%@ page contentType='text/html;charset=utf-8' %>
<%@ include file='/apl/public.inc' %>

<HTML>
<HEAD>
<TITLE>User - Login</TITLE>
<je:css/>
<je:js type='runtime'/>
<!--------------------------------------------------------->
<SCRIPT language='javascript'>
function goLogin(){
	// 页面跳转
	RTag.goPage(fmLogin, '_top', "<jh:context/>/apl/login/Login.wa");
}
//----------------------------------------------------------
function onLoginKeyDown(){
	if(EKey.Enter == event.keyCode){
		goLogin();
	}
}
//----------------------------------------------------------
function _onloadAll(){
   // 创建唯一随机数
   var rn = new TRandomInts();
   rn.setBetween(1, 6);
   // 创建滑动栏
   var src = "<jh:img type='path' src='/login/loginBk'/>";
   var bar = RClass.create(FLgMoveBar);
   bar.layerWidth = 485;
   bar.hPanel = $('#id_movebar');
   var lay1 = RClass.create(FLgMoveLayer);
   lay1.link($('#id_flow1'), src + rn.next() + '.jpg');
   bar.push(lay1);
   var lay2 = RClass.create(FLgMoveLayer);
   lay2.link($('#id_flow2'), src + rn.next() + '.jpg');
   bar.push(lay2);
   var lay3 = RClass.create(FLgMoveLayer);
   lay3.link($('#id_flow3'), src + rn.next() + '.jpg');
   bar.push(lay3);
   bar.update();
}
//----------------------------------------------------------
function _onload(){
   RWindow.connect(window);
   RLoader.loadJs('mobj', 'logic');
   RLoader.waitJs(new TInvoke(null, _onloadAll), 'mobj', 'logic');
}
</SCRIPT>
<!--------------------------------------------------------->
<BODY style='margin:0; padding:0' onload='_onload()' style='filter:progid:DXImageTransform.Microsoft.Gradient(gradienttype=0, startcolorstr=#caeeee, endcolorstr=#1d8bb5);'>
<jh:form name='fmLogin' method='post'>

<TABLE width="100%" height='100%' border='0' cellpadding="0" cellspacing="0">
<TR>
<TD width='50%'><DIV></DIV></TD>
<TD width="1" bgcolor="#1C89B3"><jh:img icon='n'/></TD>
<TD>
<TABLE width="100%" height='100%' border="0" cellpadding="0" cellspacing="0" background="<jh:img type='path' src='/login/loginBg.jpg'/>">
<TR><TD height='1'>
<!--------------------------------------------------------->
<TABLE width="933" height='433' border="0" cellpadding="0" cellspacing="0">
  <TR>
    <TD height='94'>&nbsp;</TD>
  </TR>
  <TR>
    <TD align='center' valign='top'>
      <TABLE width="100%" height="323" border="0" cellpadding="0" cellspacing="0" STYLE="table-layout:fixed">
      <TR>
        <TD width="116"><jh:img icon='n'/></TD>
        <TD width="100%" valign='top'>
            <SPAN id='id_movebar' style='width:100%;height:100%;overflow:hidden;position:relative;'>
               <SPAN id='id_flow1' style="border:1 solid #FFFFFF; filter:Alpha(opacity=100); position:absolute; left:0px; top:0px; z-index:3;"></SPAN>
               <SPAN id='id_flow2' style="border:1 solid #FFFFFF; filter:Alpha(opacity=100); position:absolute; left:104px; top:0px; z-index:2;"></SPAN>
               <SPAN id='id_flow3' style="border:1 solid #FFFFFF; filter:Alpha(opacity=100); position:absolute; left:209px; top:0px; z-index:1;"></SPAN>
            </SPAN>
        </TD>
        <TD width="116"><jh:img icon='n'/></TD>
      </TR>
      </TABLE>
    </TD>
  </TR>
</TABLE>
<!--------------------------------------------------------->
</TD></TR>
<TR><TD bgcolor='#DCEDF5' align='center'>
<!-- Message - begin -------------------------------------->
<TABLE width="604" border='0' cellpadding='0' cellspacing='0'>
<TR><TD height='8' background='<jh:img type='path' src='/login/messageBoxT.png'/>'></TD></TR>
<TR>
	<TD background='<jh:img type='path' src='/login/messageBoxM.png'/>' style='padding:0 16;line-height:12pt'>
		<FONT color='red'><B>错误消息：</B></FONT>
		<FONT color='#FFFFFF'>用户未登录，或用户登录超时。
		点击这里到 <A target='_top' href='<jh:context path="/apl/login/Login.wa"/>'><FONT color='yellow'><B><U>登录页面</U></B></FONT></A> 。</FONT>
   </TD>
</TR>
<TR><TD height='13' background='<jh:img type='path' src='/login/messageBoxB.png'/>'></TD></TR>
</TABLE>
<!-- Message - end ---------------------------------------->
</TD></TR>
<TR><TD bgcolor='#DCEDF5' align='center'>

<!-- Copyright <S> ---------------------------------------->
<TABLE width="100%" border="0" cellpadding="0" cellspacing="0">
<TR height="1">
	<TD width="100" style='filter:progid:DXImageTransform.Microsoft.Gradient(gradienttype=1, startcolorstr=#E0EFF6, endcolorstr=#7196CA);'></TD>
	<TD bgcolor='#7196CA'><jh:img icon='n'/></TD>
	<TD width="100" style='filter:progid:DXImageTransform.Microsoft.Gradient(gradienttype=1, startcolorstr=#7196CA, endcolorstr=#E0EFF6);'></TD>
</TR>
</TABLE>
<!--------------------------------------------------------->
<TABLE width="100%" border="0" cellpadding="0" cellspacing="0">
<TR>
	<TD height="30" align="center">
		联系我们　|　友情链接　|　网站地图　|　公司介绍　|　诚聘英才　|　法律声明
	</TD>
</TR>
</TABLE>
<!--------------------------------------------------------->
<TABLE width="460" border="0" cellpadding="0" cellspacing="0">
<TR>
	<TD height="60"><IMG src='<jh:img type='path' src='/login/loginCp.gif'/>'></TD>
	<TD>
		Copyright &copy; 2008 linekong.com, All Rights Reserved.<BR>
		蓝港在线（北京）科技有限公司　京ICP证070501号 </td>
	</TD>
</TR>
</TABLE>
<!-- Copyright <E> ---------------------------------------->
</TD></TR>
</TABLE>
</TD>
<TD width="1" bgcolor="#1C89B3"><jh:img icon='n'/></TD>
<TD width='50%'><DIV></DIV></TD>
</TR>
</TABLE>

</jh:form>
</BODY>

</HTML>
