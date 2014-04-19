using MO.Content2d.Common;
using MO.Core.Logic.Task;

namespace MO.Content3d.Group
{
   //============================================================
   // <T>资源组导出任务。<T>
   //============================================================
   public class FDrResourceGroupExportTask : FTask
   {
      // 资源组
      protected FDrResourceGroup _group;

      //============================================================
      // <T>构造模型导出任务。<T>
      //============================================================
      public FDrResourceGroupExportTask() {
         _label = "resource3d.group.export";
      }

      //============================================================
      // <T>获得或设置模型。<T>
      //============================================================
      public FDrResourceGroup Group {
         get { return _group; }
         set { _group = value; }
      }

      //============================================================
      // <T>执行任务处理。<T>
      //============================================================
      public override void OnProcess() {
         _group.Export(ERsExportMode.Data);
      }
   
      //============================================================
      // <T>获得字符串信息。<T>
      //============================================================
      public override string ToString() {
         return _group.ToString();
      }
   }
}
