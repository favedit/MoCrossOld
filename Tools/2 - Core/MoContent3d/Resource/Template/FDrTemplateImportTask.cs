using MO.Common.Collection;
using MO.Core.Logic.Task;
using MO.Content3d.Resource.Template;

namespace MO.Content3d.Resource.Template
{
   //============================================================
   // <T>模板导入任务。<T>
   //============================================================
   public class FDrTemplateImportTask : FTask
   {
      // 模板对象
      protected FDrTemplate _template;

      //============================================================
      // <T>构造模板导入任务。<T>
      //============================================================
      public FDrTemplateImportTask() {
         _label = "resource3d.template.import";
      }

      //============================================================
      // <T>获得或设置模板。<T>
      //============================================================
      public FDrTemplate Template {
         get { return _template; }
         set { _template = value; }
      }

      //============================================================
      // <T>执行任务处理。<T>
      //============================================================
      public override void OnProcess() {
         _template.Import();
      }
   }
}
