<%@ page contentType='text/html;charset=utf-8' %>
<%@ include file='/apl/public.inc' %>
<HTML>
<HEAD>
<je:css/>
<je:js type='runtime.client'/>
</HEAD>
<!-- Script ----------------------------------------------->
<SCRIPT>
function onUpdateEnd(s){
   alert("新建记录完成，开始处理下一条记录。");
   var f = s.form;
   f.dsMode(EMode.Insert);
   f.focus();
}
//----------------------------------------------------------
function _onloadAll(){
   // 初始化表单
   MoJS.connect();
   window.isLoaded = false;
   // 输出调试信息
   //RConsole.find(FLoggerConsole).connect();
   // 获得初始化数据
   var emode = EMode.Insert;
   var formName = "<jh:write source='&#parameter.form_name'/>";
   // 初始化工作空间
   RFormSpace.initialize();
   RFormSpace.hTitlePanel = $('#id_title');
   RFormSpace.hHistoryPanel = $('#id_historybar');
   RFormSpace.hToolPanel = $('#id_toolbar');
   RFormSpace.hFormPanel = $('#id_form');
   // 建立标题栏，历史导航栏，工具栏
   RFormSpace.createTitleBar($('#id_title'));
   RFormSpace.createHistoryBar($('#id_historybar'));
   RFormSpace.createToolBar($('#id_toolbar'), xToolBar);
   // 创建一个工作空间
   var _startForm = new Date().getTime();
   var space = RFormSpace.createSpace();
   var form = space.findForm(formName);
   form.dsSearchs.unpack(fmMain.form_search.value);
   form.dsOrders.unpack(fmMain.form_order.value);
   // 根据模式确定初始操作
   space.select(true);
   form.dsMode(emode);
   // 设置工作空间信息
   RFormSpace.titleBar.setCaption(form.label);
   RFormSpace.lsnsUpdateEnd.register(null, onUpdateEnd);
   // 设置表单
   var end = new Date().getTime();
   var runTime = ' ( Show page in ' + (end - _start) + 'ms. ' + 'Build form: ' + (end - _startForm) + 'ms )';
   RWindow.setCaption(form.label + runTime);
}
</SCRIPT>
<!-- Body - Begin ----------------------------------------->
<%@ include file='/apl/logic/form/InnerFormPart.jsp' %>
<!-- Body - End ------------------------------------------->
</HTML>
