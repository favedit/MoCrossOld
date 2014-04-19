package org.mo.jfa.face.apl.page;

//============================================================
// <T>公共页面定义。</T>
//============================================================
public interface IPublicPage
{
   // 表单显示
   public final String FORM_SHOW = "/apl/form/Show.jsp";

   public final String DIALOG_PROCESS_SUCCESS = "construct@#/apl/page/process/DlgEndProcess.wa";

   public final String PROCESS_END_DELETE = "delete@#/apl/page/process/EndProcess.wa";

   public final String PROCESS_END_INSERT = "insert@#/apl/page/process/EndProcess.wa";

   public final String PROCESS_END_UPDATE = "update@#/apl/page/process/EndProcess.wa";

   public final String PROCESS_SUCCESS = "construct@#/apl/page/process/EndProcess.wa";

   public final String XOBJECT_DESIGN = "#/apl/page/xmlobject/Design.jsp";

   public final String XOBJECT_FORM = "#/apl/page/xmlobject/Form.jsp";

   public final String XOBJECT_HELP = "#/apl/page/xmlobject/Help.jsp";

   public final String XOBJECT_SORT = "#/apl/page/xmlobject/Sort.jsp";

}
