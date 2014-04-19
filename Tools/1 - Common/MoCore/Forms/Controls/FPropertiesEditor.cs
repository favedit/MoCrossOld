using System;
using System.ComponentModel;
using System.Drawing.Design;
using System.Windows.Forms;
using System.Windows.Forms.Design;
using MO.Common.Collection;
using MO.Common.Lang;

namespace MO.Core.Forms.Controls
{
   //============================================================
   // <T>属性集合编辑器。</T>
   //============================================================
   public class FPropertiesEditor : UITypeEditor
   {
      //============================================================
      // <T>构造属性集合编辑器。</T>
      //============================================================
      public FPropertiesEditor() {
      }

      //============================================================
      // <T>获得编辑样式。</T>
      //
      // @param context 类型描述器环境
      // @return 编辑样式
      //============================================================
      public override UITypeEditorEditStyle GetEditStyle(ITypeDescriptorContext context) {
         if ((null != context) && (null != context.Instance)) {
            return UITypeEditorEditStyle.Modal;
         }
         return base.GetEditStyle(context);
      }

      //============================================================
      // <T>编辑属性内容。</T>
      //
      // @param context 类型描述器环境
      // @param provider 服务提供对象
      // @param value 属性内容
      // @return 属性内容
      //============================================================
      public override object EditValue(ITypeDescriptorContext context, IServiceProvider provider, object value) {
         if ((null != context) && (null != context.Instance) && (null != provider)) {
            IWindowsFormsEditorService editorService = provider.GetService(typeof(IWindowsFormsEditorService)) as IWindowsFormsEditorService;
            if (null != editorService) {
               // 获得属性集合
               FProperties properties = value as FProperties;
               if(properties == null) {
                  throw new FFatalException("Properties is not exists.");
               }
               // 显示属性表单
               context.OnComponentChanging();
               // 编辑控件属性
               QPropertiesEditorForm qForm = new QPropertiesEditorForm();
               qForm.Properties = properties;
               if(editorService.ShowDialog(qForm) == DialogResult.OK) {
                  context.OnComponentChanged();
                  return properties;
               }
            }
         }
         return base.EditValue(context, provider, value);
      }
   }
}
