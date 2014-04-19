<%@ page contentType='text/html;charset=utf-8' %>
<%@ include file='/apl/public.inc' %>
<HTML>
<HEAD>
<TITLE>eUIS - 用户登录</TITLE>
<LINK rel='shortcut icon' href='<jh:context path='/ars/icon/site.ico'/>'>
<link rel='bookmark' href='<jh:context path='/ars/icon/site.ico'/>'>
<je:css/>
<je:js type='runtime'/>
<STYLE>
.RBody_Panel{
   height:100%;
   width:100%;
   filter:progid:dximagetransform.microsoft.alphaimageloader(src=background.jpg,sizingmethod=scale);
   background-image:none;
}
</STYLE>
<!--------------------------------------------------------->
<SCRIPT language='javascript'>
function goLogin(){
	// 存储Cookie
	var loginMind = RHtml.checkGet(fmLogin.login_mind);
	RCookie.set('passport', RBool.isTrue(loginMind) ? fmLogin.passport.value : null);
	RCookie.set('login_date', RDate.format());
	RCookie.set('login_mind', loginMind);
	RCookie.set('login_type', RHtml.radioGet(fmLogin.login_type));
	RCookie.disconnect();
	// 页面跳转
	RTag.goPage(fmLogin, null, "<jh:context/>/apl/login/Login.wa?do=login");
}
//----------------------------------------------------------
function onLoginKeyDown(){
	if(EKey.Enter == event.keyCode){
		goLogin();
	}
}
//----------------------------------------------------------
function _onloadAll(){
   // 从Cookie中读取数据
   RCookie.connect();
   var loginMind = RCookie.get('login_mind');
   if(loginMind){
      RHtml.checkSet(fmLogin.login_mind, loginMind);
      var passport = RCookie.get('passport');
      if(!RString.isEmpty(passport)){
         fmLogin.passport.value = passport;
      }
   }
   var loginType = RCookie.get('login_type');
   if(loginType){
      RHtml.radioSet(fmLogin.login_type, loginType);
   }
   // 清空用户密码
   fmLogin.password.value = '';
   // 默认用户名获得焦点
   fmLogin.passport.focus();
   fmLogin.passport.select();
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

<TABLE width="100%" height='100%' border='0' cellpadding='0' cellspacing='0'>
<TR>
<TD width='50%'><DIV></DIV></TD>
<TD width="1" bgcolor="#1C89B3"><jh:img icon='n'/></TD>
<TD>
<TABLE width="100%" height='100%' border='0' cellpadding='0' cellspacing='0' background="<jh:img type='path' src='/login/loginBg2.jpg'/>">
<TR>
   <TD height='1'>
      <TABLE width="100%" height='100%' border="0" cellpadding='0' cellspacing='0'>
         <TR>
            <TD align='right' style='padding-top:4;padding-right:4;'><A href='<jh:context/>/apl/login/Help.wp'><IMG src='help.png' width='32' height='32' style='border:0'></A></TD>
         </TR>
      </TABLE>
   </TD>
</TR>
<TR><TD height='1'>
<!--------------------------------------------------------->
<TABLE width="933" height='392' border='0' cellpadding='0' cellspacing='0'>
  <TR>
    <TD height='57'>&nbsp;</TD>
  </TR>
  <TR>
    <TD align='center' valign='top'>
      <TABLE width="100%" height="300" border='0' cellpadding='0' cellspacing='0' STYLE="table-layout:fixed">
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
<TABLE width="100%" height='100%' border='0' cellpadding='0' cellspacing="8">
<TR valign="top">
	<TD width="110">&nbsp;</td>
	<!--------------------------------------------------------->
	<TD width="140">
		<TABLE width="100%" height='100%' border='0' cellpadding='0' cellspacing='0'>
		<TR>
			<TD valign='top'>
				<B>通行证：</B><BR>
				<jh:edit name='passport' source="&session.passport" maxlength='40' onkeypress='onLoginKeyDown()'/>
				<BR><BR>
				<B>密码：</B><BR>
				<jh:password name='password' source="&session.password" maxlength='40' onkeypress='onLoginKeyDown()'/>
				<BR><BR>
				<INPUT type='checkbox' name='login_mind' style='border:0;cursor:hand;'> <FONT color='green'>记住通行证内容</FONT>
				<BR><BR>
				<B>登录有效期：</B><BR>
				&nbsp;<INPUT type="radio" name="login_type" value="F" style='border:0;cursor:hand;'>&nbsp;永久<BR>
				&nbsp;<INPUT type="radio" name="login_type" value="M" style='border:0;cursor:hand;'>&nbsp;一月<BR>
				&nbsp;<INPUT type="radio" name="login_type" value="D" style='border:0;cursor:hand;'>&nbsp;一天<BR>
				&nbsp;<INPUT type="radio" name="login_type" value="N" style='border:0;cursor:hand;' checked>&nbsp;不记录
				<BR><BR>
				<IMG src='<jh:img type='path' src='/login/loginBtn.gif'/>' style='cursor:hand' onclick='goLogin()'>
			</TD>
		</TR>
		</TABLE>
	</TD>
	<!--------------------------------------------------------->
	<TD width="1">
		<TABLE width='1' height='100%' border='0' cellpadding='0' cellspacing='0'>
		<TR>
			<TD height="100" style='filter:progid:DXImageTransform.Microsoft.Gradient(gradienttype=0, startcolorstr=#E0EFF6, endcolorstr=#7196CA);'></TD>
		</TR>
		<TR>
			<TD bgcolor='#7196CA'><jh:img icon='n'/></TD>
		</TR>
		</TABLE>
	</TD>
	<!--------------------------------------------------------->
	<TD valign="top" bgcolor="#DCEDF5">
		<TABLE width="100%" height='360' border='0' cellpadding='0' cellspacing='0'>
		<TR>
			<TD width='523' valign='top' background='<jh:img type='path' src='/login/loginMsg.gif'/>' style='background-repeat:no-repeat'>
				<TABLE width="100%" height="100%" border='0' cellpadding='0' cellspacing="16" style='tableLayout:fixed'>
				<TR>
					<TD valign='top'>
						<DIV width="100%" height="100%" style='overflow:auto;color:#656f79'>
                     <TABLE border='0' width='100%'>
                     <jh:loop source='&session.notifyNode' alias='type'>
                     <TR><TD colspan=2><B><jh:write source='&type.type'/></B></TD></TR>
                     <jh:loop source='&type' alias='message'>
                     <TR valign='top'>
                        <TD width='28' align='center'>
                           <jh:equals source='&message.is_new' value='Y'><IMG src='dotA.gif'></jh:equals>
                           <jh:equals source='&message.is_new' value='N'><IMG src='dotB.gif'></jh:equals>
                        </TD>
                        <TD style='line-height:12pt;' width='64'><jh:write source='&message.content_date'/></TD>
                        <TD style='line-height:12pt;'><jh:write source='&message.content' format='text'/></TD>
                     </TR>
                     </jh:loop>
					      </jh:loop>
                     </TABLE>
						</DIV>
					</TD>
				</TR>
				</TABLE>
			</TD>
			<TD style='filter:progid:DXImageTransform.Microsoft.Gradient(gradienttype=1, startcolorstr=#e1eff6, endcolorstr=#dcedf5);'>&nbsp;</TD>
		</TR>
		</TABLE>
	</TD>
	<!--------------------------------------------------------->
	<TD width="20">&nbsp;</td>
</TR>
</TABLE>
<!-- Detail <E> ------------------------------------------->
</TD></TR>
<TR><TD bgcolor='#DCEDF5' align='center'>

<!-- Copyright <S> ---------------------------------------->
<TABLE width="100%" border='0' cellpadding='0' cellspacing='0'>
<TR height="1">
	<TD width="100" style='filter:progid:DXImageTransform.Microsoft.Gradient(gradienttype=1, startcolorstr=#E0EFF6, endcolorstr=#7196CA);'></TD>
	<TD bgcolor='#7196CA'><jh:img icon='n'/></TD>
	<TD width="100" style='filter:progid:DXImageTransform.Microsoft.Gradient(gradienttype=1, startcolorstr=#7196CA, endcolorstr=#E0EFF6);'></TD>
</TR>
</TABLE>
<!--------------------------------------------------------->
<TABLE width="100%" border='0' cellpadding='0' cellspacing='0'>
<TR>
	<TD height="30" align="center">
		联系我们　|　友情链接　|　网站地图　|　公司介绍　|　诚聘英才　|　法律声明
	</TD>
</TR>
</TABLE>
<!--------------------------------------------------------->
<TABLE width="460" border='0' cellpadding='0' cellspacing='0'>
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
