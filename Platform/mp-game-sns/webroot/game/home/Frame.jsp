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
function selPage(o){
	var index = 0;
	for(var n=0; n<_id_page.length; n++){
		var p = _id_page[n];
		if(n == 0){
			p.style.borderLeft = '1 solid #999999';
		}
		if(p == o){
			index = n;
			if(o.className != 'titleCellSel'){
				o.className = 'titleCellSel';
			}
		}else{
			if(p.className == 'titleCellSel'){
				p.className = 'titleCellNor';
			}
		}
	}
	for(var n=0; n<_id_tools.length; n++){
		var t = _id_tools[n];
		if(index == n && t.style.display == 'none'){
			t.style.display = 'block';
		}
		if(index != n && t.style.display == 'block'){
			t.style.display = 'none';
		}
	}
}
//----------------------------------------------------------
function title_onmenter(){
	if(this.className == 'titleCellNor'){
		this.className = 'titleCellHover';
	}
}
function title_onmleave(){
	if(this.className == 'titleCellHover'){
		this.className = 'titleCellNor';
	}
}
function title_onmdown(){
	selPage(this);
}
//----------------------------------------------------------
function tbn_onmenter(){
	if(this.className == 'tbarBtnNor' && this.className != 'tbarBtnSel'){
		this.className = 'tbarBtnHover';
	}
}
function btn_onmleave(){
	if(this.className == 'tbarBtnHover' && this.className != 'tbarBtnSel'){
		this.className = 'tbarBtnNor';
	}
}
function btn_onmdownk(self, page){
	for(var n=0; n<_id_button.length; n++){
		var btn = _id_button[n];
		if(btn == this){
			alert(this);
		}
		btn.className = 'tbarBtnNor';
	}
	self.className = 'tbarBtnSel';
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
	// build menu
	for(var n=0; n<_id_page.length; n++){
		var page = _id_page[n];
		page.className = 'titleCellNor';
		page.onmouseenter = title_onmenter;
		page.onmouseleave = title_onmleave;
		page.onmousedown = title_onmdown;
	}
	for(var n=0; n<_id_button.length; n++){
		var btn = _id_button[n];
		btn.className = 'tbarBtnNor';
		btn.onmouseenter = tbn_onmenter;
		btn.onmouseleave = btn_onmleave;
	}
	selPage(_id_page[0]);
   RWindow.setEnable(true);
}
//----------------------------------------------------------
function _onload(){
   RWindow.connect(window);
   RWindow.setEnable(false, true);
   RLoader.loadJs('mobj', 'logic');
   RLoader.wait(new TInvoke(null, _onloadAll), 'pg:catalog', 'pg:main', 'js:mobj', 'js:logic');
}
</SCRIPT>
<!------------------------------------------------------------>
<BODY style='margin:0; padding:0' scroll='no' onload='_onload()'>
<jh:form name='fmMain'>
<jh:hidden name='page_index'/>
<!-- 框架部分-->
<TABLE border='0' cellpadding='0' cellspacing='0' width='100%' height='100%'>
<TR><TD height='24' background='ground.jpg'>

<!-- 标题部分(上) - start -->
<TABLE border='0' cellpadding="0" cellspacing='0' width='100%' height='24'>
<TR>
	<TD width='10'></TD>
	<TD><B>Welcome</B> | <B>Sign In</B></TD>
	<TD align='right' valign='bottom'>
		Home | Help | Language:
		<jh:select source='&home.language' onchange='chLang(this)' style='font-size:9pt;' enum='system.culture.Language'/>
	</TD>
	<TD width='10'></TD>
</TR>
</TABLE>
<!-- 标题部分(上) - end -->

<!-- 标题部分(中) - 主菜单 - start -->
<TABLE width='100%' border='0' cellpadding="0" cellspacing='0'>
<TR>
	<TD width='104' align='right'><IMG src='title.gif'></TD>
	<TD align='right' valign='bottom'>
	<DIV style='width:1; border-top:1 solid #8E909C; border-left:1 solid #76758c;'>
		<TABLE border='0' cellpadding="0" cellspacing='0' height='40'>
		<TR><TD width='180' align='center' bgcolor='#9b9ab4' style='padding-top:3'><IMG src='sysmgr.gif'></TD>
		<TD width='1' align='right' valign='bottom' style='filter:progid:DXImageTransform.Microsoft.Gradient(startColorStr=#9b9ab4, endColorStr=#FFFFFF, gradientType=1);'>
			<TABLE border='0' cellpadding="0" cellspacing='0'>
			<TR>
				<TD><js:icon width='12'/></TD>
				<TD id='_id_page'><js:icon source='tools.design'/> 游戏设计</TD>
				<TD id='_id_page'><js:icon source='tools.support'/> 技术支持</TD>
				<TD><js:icon width='12'/></TD>
			</TR>
			</TABLE>
		</TD></TR>
		</TABLE>
	</DIV>
	</TD>
</TR>
</TABLE>
<!-- 标题部分(中) - 主菜单 - end -->

</TD></TR>
<TR><TD bgcolor='#9e9eba' height='2' colspan='2'></TD></TR>
<TR><TD bgcolor='#FFFFFF' colspan='2' valign='middle' background='toolbar.gif' height='21'>

<!-- 标题部分(下) - 子菜单 - start -->
<TABLE border='0' cellpadding="0" cellspacing='0' width='100%' height='21'>
<TR>
	<TD width='14'>&nbsp;</TD>
	<TD>
		<TABLE border='0' cellpadding="0" cellspacing='1' height='21'>
		<TR id='_id_tools' style='display:none'>
         <TD id='_id_button' onmousedown='btn_onmdownk(this, "/editor/enums/Enum.wa?do=catalog")'>枚举定义</TD>
         <TD>&nbsp;</TD>
         <TD id='_id_button' onmousedown='btn_onmdownk(this, "/editor/instance/Instance.wa?do=catalog")'>实例定义</TD>
         <TD>&nbsp;</TD>
         <TD id='_id_button' onmousedown='btn_onmdownk(this, "/editor/entity/Entity.wa?do=catalog")'>实体定义</TD>
         <TD>&nbsp;</TD>
         <TD id='_id_button' onmousedown='btn_onmdownk(this, "/editor/message/Message.wa?do=catalog")'>消息定义</TD>
         <TD>&nbsp;</TD>
         <TD id='_id_button' onmousedown='btn_onmdownk(this, "/editor/module/Module.wa?do=catalog")'>模块管理</TD>
         <TD id='_id_button' onmousedown='btn_onmdownk(this, "/editor/processor/Processor.wa?do=catalog")'>消息处理</TD>
         <TD>&nbsp;</TD>
         <TD id='_id_button' onmousedown='btn_onmdownk(this, "/editor/storage/Storage.wa?do=catalog")'>数据存储</TD>
			<TD id='_id_button' onmousedown='btn_onmdownk(this, "/editor/dataset/Dataset.wa?do=catalog")'>数据集合</TD>
         <TD>&nbsp;</TD>
         <TD id='_id_button' onmousedown='btn_onmdownk(this, "/editor/logic/Logic.wa?do=catalog")'>逻辑处理</TD>
		</TR>
		<TR id='_id_tools' style='display:none'>
			<TD id='_id_button' onmousedown='btn_onmdownk(this, "/support/framework/Framework.wa")'>Framework</TD>
			<TD id='_id_button' onmousedown='btn_onmdownk(this, "/support/enging/Framework.wa")'>JS Enging</TD>
			<TD id='_id_button' onmousedown='btn_onmdownk(this, "/support/help/Help.wa")'>Help</TD>
			<TD></TD>
		</TR>
		</TABLE>
	</TD>
</TR>
</TABLE>
<!-- 标题部分(下) - 子菜单 - end -->

</TD></TR>
<TR><TD bgcolor='#9e9eba' height='3' colspan='2'></TD></TR>
<TR>
	<TD>

<!-- 分页部分 - start -->
<TABLE width='100%' height='100%' border='0' cellpadding='0' cellspacing='0' bgcolor='#a5eaea'>
<TR>
	<TD id='id_left' width='500'>
		<IFRAME name='frmCatalog' width='100%' height='100%' frameborder='0' src='<jh:context path='/system/home/Catalog.wp'/>'></IFRAME>
	</TD>
	<TD width='3' id='id_split' bgcolor='#9e9eba'></TD>
	<TD>
		<IFRAME name='frmMain' width='100%' height='100%' frameborder='0' src='<jh:context path='/system/home/Body.wa'/>'></IFRAME>
	</TD>
</TR>
</TABLE>
<!-- 分页部分 - end -->

	</TD>
</TR>
</TABLE>

</jh:form>
</BODY>

</HTML>

