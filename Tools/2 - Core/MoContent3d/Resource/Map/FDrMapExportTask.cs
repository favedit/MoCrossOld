using MO.Common.Collection;
using MO.Core.Logic.Task;
using MO.Content3d.Resource.Map;

namespace MO.Content3d.Resource.Map
{
   //============================================================
   // <T>地图导出任务。<T>
   //============================================================
   public class FDrMapExportTask : FTask
  {
      protected FDrMapConsole _console;

         protected FDrMap _map;

         protected int _current;

         protected FVector<FDrMap> _maps = new FVector<FDrMap>();

         //============================================================
         public FDrMapExportTask(FDrMapConsole console) {
            _console = console;
         }

         //============================================================
         public int Current {
            get { return _current; }            
         }

         //============================================================
         public int Count {
            get { return _maps.Count; }
         }

         //============================================================
         public FDrMap Map {
            get { return _map; }
         }

         //============================================================
         public FVector<FDrMap> Maps {
            get { return _maps; }
         }

         //============================================================
         public override void OnBegin() {
            _map = _maps[0];
            _console.OnTaskBegin(this);
         }

         //============================================================
         public override void OnProcess() {
            int count = _maps.Count;
            for (int n = 0; n < count; n++) {
               _current = n;
               _map = _maps[n];
               _map.Open();               
               _console.OnTaskProcessing(this);               
               _map.Export();
               _map.Close();
            }
         }

         //============================================================
         public override void OnEnd() {
            _console.OnTaskEnd(this);
         }
      }
   }
