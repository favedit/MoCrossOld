<%@ page contentType='text/html;charset=utf-8' %>
<%@ include file='/apl/public.inc' %>
<HTML>
<HEAD>
<TITLE>eUIS</TITLE>
<SCRIPT>var pageStart = new Date().getTime();</SCRIPT>
<je:css/>
<je:js type='runtime'/>
<STYLE>
.bottomPic {
   background: url('<jh:img type='path' src='/login/loginBg3.jpg'/>'); 
}
</STYLE>
</HEAD>
<!-- Script - begin --------------------------------------->
<SCRIPT>
// ---------------------------------------------------------
function _onloadAll(){
   MoJS.initialize();
   // 获得初始化数据
   var formName = "<jh:write source='&#parameter.form_name'/>";
   // 建立标题栏，历史导航栏，工具栏，工作区
   RFormSpace.initialize();
   RFormSpace.createTitleBar($('#id_title'));
   RFormSpace.createHistoryBar($('#id_historybar'));
   RFormSpace.createToolBar($('#id_toolbar'), xToolBar);
   RFormSpace.hFormPanel = $('#id_form');
   // 创建一个工作空间
   var _startForm = new Date().getTime();
   var space = RFormSpace.createSpace();
   var form = space.findForm(formName);
   form.dsGlobalSearchs.unpack(fmMain.form_search.value);
   form.dsGlobalOrders.unpack(fmMain.form_order.value);
   form.dsValues.unpack(fmMain.form_value.value);
   // 根据模式确定初始操作
   form.psMode(EMode.Update);
   space.select(true);
   // 开始加载数据
   form.dsFetch(true);
   // 设置工作空间信息
   RFormSpace.titleBar.setCaption(form.label);
   // 设置表单
   var end = new Date().getTime();
   var runTime = ' ( Show page in ' + (end - _start) + 'ms. ' + 'Build form: ' + (end - _startForm) + 'ms )';
   RWindow.setCaption(form.label + runTime);
   RWindow.setEnable(true);
}
// ---------------------------------------------------------
function _onload(){
   _start = new Date().getTime();
   RWindow.connect(window);
   RLoader.loadJs('mobj', 'workspace');
   RLoader.waitJs(new TInvoke(null, _onloadAll), 'mobj', 'workspace');
}
</SCRIPT>
<!-- Script - end ----------------------------------------->
<!-- Body - begin ----------------------------------------->
<BODY style='margin:0; padding:0' onload='_onload()' disable style='filter:progid:DXImageTransform.Microsoft.Gradient(gradienttype=0, startcolorstr=#caeeee, endcolorstr=#1d8bb5);'>
<TABLE width="100%" height='100%' border='0' cellpadding="0" cellspacing="0">
<TR>
<TD width='50%'><DIV></DIV></TD>
<TD width="1" bgcolor="#1C89B3"><jh:img icon='n'/></TD>
<TD>
<TABLE width="100%" height='100%' border="0" cellpadding="0" cellspacing="0" background="<jh:img type='path' src='/login/loginBg2.jpg'/>">
<TR><TD>
<!--------------------------------------------------------->
<TABLE width="933" height='100%' border="0" cellpadding="0" cellspacing="0">
  <TR>
    <TD height='96'>&nbsp;</TD>
  </TR>
  <TR>
    <TD align='center' valign='top'>
      <TABLE width="100%" height="100%" border="0" cellpadding="0" cellspacing="0" bgcolor='#FFFFFF'>
      <TR>
        <TD width="20"><jh:img icon='n'/></TD>
        <TD width="100%" valign='top'>
            <%@ include file='/apl/logic/form/InnerFormPart.jsp' %>
        </TD>
        <TD width="20"><jh:img icon='n'/></TD>
      </TR>
      </TABLE>
    </TD>
  </TR>
</TABLE>
<!--------------------------------------------------------->
</TD></TR>
<TR><TD bgcolor='#DCEDF5' align='center'></TD></TR>
<TR><TD height='20' class='bottomPic'></TD></TR>
</TABLE>
</TD>
<TD width="1" bgcolor="#1C89B3"><jh:img icon='n'/></TD>
<TD width='50%'><DIV></DIV></TD>
</TR>
</TABLE>
</BODY>
<!-- Body - end ------------------------------------------->
</HTML>
