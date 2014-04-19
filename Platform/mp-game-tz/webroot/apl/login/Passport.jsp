<%@ page contentType='text/html;charset=utf-8' %>
<%@ include file='/apl/public.inc' %>
<jh:define source='&session.properties' alias='user_property'/>
<HTML>
<HEAD>
<TITLE>eUIS - 蓝港通行证 - 认证</TITLE>
<LINK rel='shortcut icon' href='<jh:context path='/ars/icon/site.ico'/>'>
<link rel='bookmark' href='<jh:context path='/ars/icon/site.ico'/>'>
<je:css/>
<je:js type='runtime'/>
<!--------------------------------------------------------->
<SCRIPT language='javascript'>
//----------------------------------------------------------
function onLoginKeyDown(){
	if(EKey.Enter == event.keyCode){
		goLogin();
	}
}
//----------------------------------------------------------
function goLogin(){
	// 页面跳转
	RTag.goPage(fmLogin, null, RContext.context('/apl/login/Login.wa?do=passportCheck'));
}
//----------------------------------------------------------
function _onloadAll(){
   // 创建唯一随机数
   var rn = new TRandomInts();;
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
   // 设置焦点
   fmLogin.passport.focus();
}
//----------------------------------------------------------
function _onload(){
   RWindow.connect(window);
   RLoader.loadJs('mobj', 'logic');
   RLoader.waitJs(new TInvoke(null, _onloadAll), 'mobj', 'logic');
}
</SCRIPT>
<!--------------------------------------------------------->
<BODY onload='_onload()' style='margin:0; padding:0;filter:progid:DXImageTransform.Microsoft.Gradient(gradienttype=0, startcolorstr=#CAEEEE, endcolorstr=#1D8BB5);'>
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
<jh:notEmpty source='&session.loginMessage'>
<TABLE width="604" border='0' cellpadding='0' cellspacing='0'>
<TR><TD height='8' background='<jh:img type='path' src='/login/messageBoxT.png'/>'></TD></TR>
<TR>
	<TD background='<jh:img type='path' src='/login/messageBoxM.png'/>' style='padding:0 16;line-height:12pt'>
		<FONT color='red'><B>消息提示：</B></FONT>
		<FONT color='#FFFFFF'><jh:write source='&session.loginMessage' format='text'/></FONT>
   </TD>
</TR>
<TR><TD height='13' background='<jh:img type='path' src='/login/messageBoxB.png'/>'></TD></TR>
</TABLE>
</jh:notEmpty>
<!-- Message - end ---------------------------------------->
<!-- Detail <S> ------------------------------------------->
<TABLE width="100%" height='100%' border="0" cellpadding="0" cellspacing="8">
<TR align='center' valign="top">
	<TD>
      <TABLE width='700' bgcolor='#FFFFFF' style='border:1 solid #E55441' cellpadding='12'>
      <TR>
         <TD>
            <TABLE width='100%' style='color:#E55441'>
            <TR><TD colspan='2'><B><jh:write source='&user_property.label'/><jh:write source='&user_property.label_last'/></B> 您好： </TD></TR>
            <TR><TD width='20'><jh:img icon='com.alert'/></TD><TD>当前画面只有在<B>第一次登录</B>时才会出现，通行证的认证通过后，将不再出现本页面。</TD></TR>
            <TR><TD><jh:img icon='com.help'/></TD><TD>请输入您在蓝港的通行证，如果还没有通行证的话，请<A target='_blank' href='http://passport.linekong.com'>点击这里</A>为自己申请一个新的通行证。</TD></TR>
            <TR><TD></TD><TD>认证后，以后登录本系统都使用这个账号和密码。</TD></TR>
            </TABLE>
         </TD>
      </TR>
      </TABLE>
		<TABLE width="120" border="0" cellpadding="0" cellspacing="12">
		<TR>
			<TD valign='top'>
				<B>蓝港通行证：</B><BR>
   			<jh:edit name='passport' source="&session.passport" maxlength='40' onkeypress='onLoginKeyDown()'/>
         </TD>
		</TR>
		<TR>
         <TD>
				<B>密码：</B><BR>
				<jh:password name='password' source="&session.password" maxlength='40' onkeypress='onLoginKeyDown()'/>
         </TD>
		</TR>
		<TR>
         <TD>
				<IMG src='<jh:img type='path' src='/login/loginBtn.gif'/>' style='cursor:hand' onclick='goLogin()'>
			</TD>
		</TR>
		</TABLE>
	</TD>
</TR>
</TABLE>
<!-- Detail <E> ------------------------------------------->
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
