using MO.Content2d.Common;
using MO.Core.Logic.Task;

namespace MO.Content3d.Resource.Scene
{
   //============================================================
   // <T>场景导出任务。<T>
   //============================================================
   public class FDrSceneGroupExportTask : FTask
   {
      // 场景对象
      protected FDrSceneGroup _sceneGroup;

      //============================================================
      // <T>构造场景导出任务。<T>
      //============================================================
      public FDrSceneGroupExportTask() {
         _label = "resource3d.scene.export";
      }

      //============================================================
      // <T>获得或设置场景。<T>
      //============================================================
      public FDrSceneGroup SceneGroup {
         get { return _sceneGroup; }
         set { _sceneGroup = value; }
      }

      //============================================================
      // <T>执行任务处理。<T>
      //============================================================
      public override void OnProcess() {
         _sceneGroup.Export(ERsExportMode.CompressLzma);
      }
   
      //============================================================
      // <T>获得字符串信息。<T>
      //============================================================
      public override string ToString() {
         return _sceneGroup.ToString();
      }
   }
}
