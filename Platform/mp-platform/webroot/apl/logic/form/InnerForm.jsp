<%@ page contentType='text/html;charset=utf-8' %>
<%@ include file='/apl/public.inc' %>
<HTML>
<HEAD>
<je:css/>
<je:js type='runtime.client'/>
</HEAD>
<!-- Script ----------------------------------------------->
<SCRIPT>
// ---------------------------------------------------------
function goPage(uri){
   fmMain.target = 'frmDetail';
   fmMain.action = top.RContext.context(uri);
   fmMain.submit();
   top.setSideVisible(false);
}
// ---------------------------------------------------------
function goForm(md, fn, fa, sp, op, vp){
   fmMain.target = 'frmDetail';
   fmMain.form_name.value = RString.nvl(fn);
   fmMain.form_action.value = RString.nvl(fa);
   fmMain.form_search.value = RString.nvl(sp);
   fmMain.form_order.value = RString.nvl(op);
   fmMain.form_value.value = RString.nvl(vp);
   fmMain.action = top.RContext.context(md ? '/psn/logic/' + md + '/InnerForm.wa?do=update' : '/psn/apl/logic/form/WebForm.wa?do=update');
   fmMain.submit();
   top.setSideVisible(false);
}
// ---------------------------------------------------------
function goComponent(md, fn, sp, op, vp){
   fmMain.target = 'frmDetail';
   fmMain.form_name.value = fn;
   fmMain.action = top.RContext.context('/psn/logic/' + md + '/InnerForm.wa?do=update');
   fmMain.submit();
   top.setSideVisible(false);
}
// ---------------------------------------------------------
function goUser(c){
   fmMain.target = '_top';
   fmMain.action = top.RContext.context('/psn/home/User.wa?code=' + c);
   fmMain.submit();
}
// ---------------------------------------------------------
function _onloadAll(){
   // 初始化表单
   MoJS.connect();
   window.isLoaded =false;
   // 输出调试信息
   //RConsole.find(FLoggerConsole).connect();
   // 获得初始化数据
   var action = "<jh:write source='&#parameter.do'/>";
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
   // 根据模式切换操作
   var emode = REnum.encode(EMode, action);
   form.psMode(emode);
   space.select(true);
   // 获取表单数据
   if(EMode.Insert == emode){
      form.dsPrepare(true);
   }else if(EMode.Delete == emode){
      form.dsDelete(true);
   }else{
      form.dsFetch(true);
   }
   // 设置工作空间信息
   RFormSpace.titleBar.setCaption(form.label);
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
