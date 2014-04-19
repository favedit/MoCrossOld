<%@ page contentType='text/html;charset=utf-8' %>
<%@ include file='/apl/public.inc' %>

<HTML>
<HEAD>
<TITLE>Welcome</TITLE>
<jg:css/>
<jg:js type='runtime'/>
</HEAD>
<!------------------------------------------------------------>
<SCRIPT language='javascript'>
// font-weight:bold; 
function chLang(sel){
	fmMain.action = '?do=changeLanguage';
	fmMain.target = 'frmTitle';
	fmMain.submit();
}
//----------------------------------------------------------
function changeTitleStatus(ph, ps){
   ph._status = ps;
   var hTable = ph.children[0];
   var hTr = hTable.rows[0];
   var hTdl = hTr.cells[0];
   var hTd = hTr.cells[1];
   var hTdr = hTr.cells[2];
   if(ps == 2){
      // Select
      hTdl.style.backgroundImage = "url(tbl.gif)";
      hTdl.style.borderBottom = '0px solid #FFFFFF';
      hTd.style.color = 'black';
      hTd.bgColor = '#FFFFFF';
      hTd.style.fontWeight = 'bold';
      hTd.style.borderTop = '1px solid #BAC9DB'
      hTd.style.borderBottom = '0px solid #FFFFFF';
      hTdr.style.backgroundImage = "url(tbr.gif)";
      hTdr.style.borderBottom = '0px solid #FFFFFF';
   }else if(ps == 1){
      // Hover
      hTdl.style.backgroundImage = "url(tbe.gif)";
      hTdl.style.borderBottom = '1px solid #BAC9DB';
      hTd.style.color = 'blue';
      hTd.bgColor = '#c8d1dc';
      hTd.style.fontWeight = 'normal';
      hTd.style.borderTop = '0px solid #BAC9DB';
      hTd.style.borderBottom = '1px solid #BAC9DB';
      hTdr.style.backgroundImage = "url(tbe.gif)";
      hTdr.style.borderBottom = '1px solid #BAC9DB';
   }else{
      // Normal
      hTdl.style.backgroundImage = "url(tbe.gif)";
      hTdl.style.borderBottom = '1px solid #BAC9DB';
      hTd.style.color = 'black';
      hTd.bgColor = '';
      hTd.style.fontWeight = 'normal';
      hTd.style.borderTop = '0px solid #BAC9DB';
      hTd.style.borderBottom = '1px solid #BAC9DB';
      hTdr.style.backgroundImage = "url(tbe.gif)";
      hTdr.style.borderBottom = '1px solid #BAC9DB';
   }
}
function selPage(ph){
   // Select page
	var index = 0;
   var hPages = document.all('_id_page');
   var pc = hPages.length;
	for(var n = 0; n < pc; n++){
		var hPage = hPages[n];
		if(hPage == ph){
			index = n;
         changeTitleStatus(hPage, 2);
		}else{
         changeTitleStatus(hPage, 0);
		}
	}
   // Select button
   var hTools = document.all('_id_tools');
   var tc = hTools.length;
	for(var n = 0; n < tc; n++){
		var t = hTools[n];
		if(index == n && t.style.display == 'none'){
			t.style.display = 'block';
		}
		if(index != n && t.style.display == 'block'){
			t.style.display = 'none';
		}
	}
}
function title_onmenter(){
	if(this._status == 0){
      changeTitleStatus(this, 1);
	}
}
function title_onmleave(){
	if(this._status == 1){
      changeTitleStatus(this, 0);
	}
}
function title_onmdown(){
	selPage(this);
}
//----------------------------------------------------------
function changeButtonStatus(ph, ps){
   ph._status = ps;
   var hTable = ph.children[0];
   var hTr = hTable.rows[0];
   var hTdl = hTr.cells[0];
   var hTd = hTr.cells[1];
   var hTdr = hTr.cells[2];
   if(ps == 2){
      // Select
      hTdl.style.backgroundImage = "url(bl.gif)";
      hTd.style.color = 'black';
      hTd.bgColor = '#FFFFFF';
      hTd.style.fontWeight = 'bold';
      hTd.style.backgroundImage = "url(bm.gif)";
      hTdr.style.backgroundImage = "url(br.gif)";
   }else if(ps == 1){
      // Hover
      hTdl.style.backgroundImage = "url()";
      hTd.style.color = 'blue';
      hTd.bgColor = '#c8d1dc';
      hTd.style.fontWeight = 'normal';
      hTd.style.backgroundImage = "url()";
      hTdr.style.backgroundImage = "url()";
   }else{
      // Normal
      hTdl.style.backgroundImage = "url()";
      hTd.style.color = 'black';
      hTd.bgColor = '';
      hTd.style.fontWeight = 'normal';
      hTd.style.backgroundImage = "url()";
      hTdr.style.backgroundImage = "url()";
   }
}
function tbn_onmenter(){
	if(this._status == 0){
      changeButtonStatus(this, 1);
	}
}
function btn_onmleave(){
	if(this._status == 1){
      changeButtonStatus(this, 0);
	}
}
function btn_onmdownk(self, page){
   var hButtons = document.all('_id_button');
   var bc = hButtons.length;
	for(var n = 0; n < bc; n++){
		var hButton = hButtons[n];
		if(hButton == this){
			alert(this);
		}
      changeButtonStatus(hButton, 0);
	}
   changeButtonStatus(self, 2);
	fmMain.action = '<jh:context/>' + page;
	fmMain.target = 'frmCatalog';
	fmMain.submit();
	fmMain.action = 'Empty.wa';
	fmMain.target = 'frmMain';
	fmMain.submit();
}
//----------------------------------------------------------
function _onloadAll(){
   MoJS.initialize();
	// split
	var cs = RClass.create(FLgSpliter);
	cs.link($('#id_split'), $('#id_left'));
	// build pages
   var hPages = document.all('_id_page');
   var pc = hPages.length;
	for(var n = 0; n < pc; n++){
		var hPage = hPages[n];
      var hs = hPage.innerHTML;
		hPage.innerHTML = "<TABLE width='100' height='24' border='0' cellpadding='0' cellspacing='0' style='cursor:pointer'><TR>" +
            "<TD width='7'></TD>" +
            "<TD align='center'>" + hs + "</TD>" +
            "<TD width='7'></TD>" +
            "</TR></TABLE>";
		hPage.onmouseenter = title_onmenter;
		hPage.onmouseleave = title_onmleave;
		hPage.onmousedown = title_onmdown;
	}
   // build buttons
   var hButtons = document.all('_id_button');
   var bc = hButtons.length;
	for(var n = 0; n < bc; n++){
		var hButton = hButtons[n];
      var hs = hButton.innerHTML;
      hButton.innerHTML = "<TABLE height='20' border='0' cellpadding='0' cellspacing='0' style='cursor:pointer'><TR>" +
            "<TD width='3'></TD>" +
            "<TD align='center' style='padding-left:8px;padding-right:8px'>" + hs + "</TD>" +
            "<TD width='3'></TD>" +
            "</TR></TABLE>";
		hButton.onmouseenter = tbn_onmenter;
		hButton.onmouseleave = btn_onmleave;
      changeButtonStatus(hButton, 0);
	}
	selPage(hPages[1]);
   RWindow.setEnable(true);
}
//----------------------------------------------------------
function doResize(){
   // 获得内容
   var hLeft = $('#id_left');
   var hCatalog = hLeft.children[0];
   hCatalog.style.display = 'none';
   var hBody = $('#id_body');
   var hMain = hBody.children[0];
   hMain.style.display = 'none';
   // 设置高度
   var height = hLeft.offsetHeight;
   //hCatalog.height = height;
   //hMain.height = height - 5;
   hCatalog.style.display = 'block';
   hMain.style.display = 'block';
}
//----------------------------------------------------------
function _onload(){
   RWindow.connect(window);
   // 改变大小
   doResize();
   RWindow.lsnsResize.register(null, doResize);
   // 加载内容
   RWindow.setEnable(false, true);
   RLoader.loadJs('mobj', 'logic');
   RLoader.wait(new TInvoke(null, _onloadAll), 'pg:catalog', 'pg:main', 'js:mobj', 'js:logic');
}
</SCRIPT>
<!------------------------------------------------------------>
<BODY scroll='no' onload='_onload()' style='margin:0; padding:0;background-color:#FFFFFF;'>
<jh:form name='fmMain'>
<jh:hidden name='page_index'/>
<!-- 框架部分-->
<TABLE width='100%' height='100%' border='0' cellpadding='0' cellspacing='0'>

<!-- 标题部分(上) - start -->
<TR><TD height='26' bgcolor='#DFE9F5'>
<TABLE width='100%' height='100%' border='0' cellpadding="0" cellspacing='0'>
<TR>
	<TD width='10'>
      <TABLE width='100%' height='100%' style='border-bottom:1px solid #BAC9DB'><TR><TD></TD></TR></TABLE>
   </TD>
	<TD>
      <TABLE width='100%' height='100%' style='border-bottom:1px solid #BAC9DB'><TR><TD>
         <B>ZQINET GAME PLATFORM</B>
      </TD></TR></TABLE>
   </TD>
	<TD align='right' valign='bottom'>
		<TABLE width='100%' height='24' border='0' cellpadding='0' cellspacing='0'>
		<TR>
			<TD>
            <TABLE width='100%' height='100%' style='border-bottom:1px solid #BAC9DB'><TR><TD></TD></TR></TABLE>
         </TD>
			<TD id='_id_page' width='90' valign='bottom'><js:icon source='tools.home'/> 主页</TD>
			<TD id='_id_page' width='90' valign='bottom'><js:icon source='tools.system'/> 服务器</TD>
			<TD id='_id_page' width='90' valign='bottom'><js:icon source='tools.support'/> 支持</TD>
			<TD id='_id_page' width='90' valign='bottom'><js:icon source='tools.help'/> 帮助</TD>
		</TR>
		</TABLE>
   </TD>
	<TD width='10'>
      <TABLE width='100%' height='100%' style='border-bottom:1px solid #BAC9DB'><TR><TD></TD></TR></TABLE>
   </TD>
</TR>
</TABLE>
</TD></TR>
<!-- 标题部分(上) - end -->


<!-- 标题部分(中) - 主菜单 - end -->
<TR><TD height='28'>
<TABLE width='100%' height='100%' border='0' cellpadding="0" cellspacing='0' background='bb.gif'>
<TR>
	<TD width='12'>&nbsp;</TD>
	<TD>
   	<TABLE border='0' cellpadding="0" cellspacing='1'>
   	<TR id='_id_tools' style='display:block'>
   		<TD id='_id_button' onmousedown='btn_onmdownk(this, "/platform/support/help/Help.wa")'>Help</TD>
   		<TD></TD>
   	</TR>
   	<TR id='_id_tools' style='display:none'>
         <TD id='_id_button' onmousedown='btn_onmdownk(this, "/platform/server/Server.wa?do=catalog")'>服务器</TD>
   		<TD>&nbsp;</TD>
   		<TD id='_id_button' onmousedown='btn_onmdownk(this, "/platform/support/help/Help.wa")'>Help</TD>
   	</TR>
   	<TR id='_id_tools' style='display:none'>
   		<TD id='_id_button' onmousedown='btn_onmdownk(this, "/platform/support/help/Help.wa")'>Help</TD>
   	</TR>
   	<TR id='_id_tools' style='display:none'>
   		<TD id='_id_button' onmousedown='btn_onmdownk(this, "/platform/support/help/Help.wa")'>Help</TD>
   	</TR>
   	</TABLE>
	</TD>
</TR>
</TABLE>
</TD></TR>

<!-- 分页部分 - start -->
<TR><TD>
<TABLE width='100%' height='100%' border='0' cellpadding='0' cellspacing='0' bgcolor='#FFFFFF'>
<TR valign='top'>
	<TD id='id_left' width='400'>
		<IFRAME name='frmCatalog' width='100%' height='99%' border='0' frameborder='0' marginheight='0' marginwidth='0' src='<jh:context path='#/platform/home/Catalog.wp'/>' 
        allowTransparency='true' style='margin:0;padding:0;background-color:#FFFFFF'></IFRAME>
	</TD>
	<TD id='id_split' width='3' bgcolor='#bac9db'></TD>
	<TD id='id_body'>
		<IFRAME name='frmMain' width='100%' height='99%' border='0' frameborder='0' marginheight='0' marginwidth='0' src='<jh:context path='#/platform/home/Body.wa'/>'
         allowTransparency='true' style='margin:0;padding:0;background-color:#FFFFFF'></IFRAME>
	</TD>
</TR>
</TABLE>
</TD></TR>
<!-- 分页部分 - end -->

</TABLE>

</jh:form>
</BODY>
</HTML>
