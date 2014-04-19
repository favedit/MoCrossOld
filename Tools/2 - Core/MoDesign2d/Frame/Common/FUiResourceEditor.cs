using System;
using System.ComponentModel;
using System.Drawing.Design;
using System.Windows.Forms.Design;

namespace MO.Design2d.Frame.Common
{
   //============================================================
   // <T>界面资源编辑器。</T>
   //============================================================
   public class FUiResourceEditor : UITypeEditor
   {
      //============================================================
      // <T>取得外部资源。</T>
      //============================================================
      public override UITypeEditorEditStyle GetEditStyle(ITypeDescriptorContext context) {
         if(context != null && context.Instance != null) {
            return UITypeEditorEditStyle.Modal;
         }
         return base.GetEditStyle(context);
      }

      //============================================================
      // <T>引用应用程序资源。</T>
      //============================================================
      public override object EditValue(ITypeDescriptorContext context, IServiceProvider provider, object value) {
         IWindowsFormsEditorService editorService = null;
         if(null != context && null != context.Instance && null != provider) {
            editorService = (IWindowsFormsEditorService)provider.GetService(typeof(IWindowsFormsEditorService));
            if(null != editorService) {
               // 获得内容
               string resourceCode = (string)value;
               // 加载资源表单
               //QUiResourcePickerForm qForm = QUiResourcePickerForm.Instance();
               //qForm.ShowCd = ERsResource.Picture;
               //qForm.loadFolder();
               //qForm.SelectResourceByCode(resourceCode);
               //DialogResult result = editorService.ShowDialog(qForm);
               //if(DialogResult.OK == result) {
               //   //QUiDesignForm desingForm = QUiDesignForm.Instance();
               //   FDsResource resource = qForm.SelectedResource;
               //   // 如果所选资源为图片则将它作为表单中的控件的背景图片显示
               //   if(ERsResource.Picture == resource.TypeCd) {
               //      // 将图片绑定到表单窗口上
               //      //desingForm.Tag = qForm.SelectedResource;
               //      // 将图片作为表单控件的背景图
               //      //desingForm.updateBackGround();
               //   }
               //   return resource.Code;
               //}
            }
         }
         return base.EditValue(context, provider, value);
      }
   }
}
