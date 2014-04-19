using MO.Core.Logic.Task;

namespace MO.Content2d.Common
{
   //============================================================
   // <T>资源导出任务。<T>
   //============================================================
   public class FRsExportTask: FTask
   {
      // 导出接口
      protected IRsExport _exporter;

      // 导出模式
      protected ERsExportMode _modeCd = ERsExportMode.CompressDeflate;

      //============================================================
      // <T>构造资源导出任务。<T>
      //
      // @param exporter 导出接口
      // @param modeCd 导出模式
      //============================================================
      public FRsExportTask(IRsExport exporter = null, ERsExportMode modeCd = ERsExportMode.CompressDeflate) {
         _exporter = exporter;
         _modeCd = modeCd;
      }

      //============================================================
      // <T>获得或设置导出接口。<T>
      //============================================================
      public IRsExport Exporter {
         get { return _exporter; }
         set { _exporter = value; }
      }

      //============================================================
      // <T>获得或设置导出模式。<T>
      //============================================================
      public ERsExportMode ModeCd {
         get { return _modeCd; }
         set { _modeCd = value; }
      }

      //============================================================
      // <T>处理中回调事件。<T>
      //============================================================      
      public override void OnProcess() {
         _exporter.Export(_modeCd);
      }

      //============================================================
      // <T>获得字符串信息。<T>
      //
      // @return 字符串信息
      //============================================================      
      public override string ToString() {
         return "Export task. (exporter=" + _exporter + ")";
      }
   }
}
