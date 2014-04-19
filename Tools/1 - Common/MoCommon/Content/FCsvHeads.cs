using MO.Common.Collection;
using MO.Common.Lang;

namespace MO.Common.Content
{
   public class FCsvHeads : FDictionary<FCsvHead>
   {
      public const string TAG_NAME = "Heads";

      private static ILogger _logger = RLogger.Find(typeof(FCsvHeads));

      public string[] Keys {
         get {
            FStrings keys = new FStrings();
            for(int n = 0; n < _count; n++) {
               FCsvHead head = _values[n];
               if(head.Key) {
                  keys.Push(head.Name);
               }
            }
            return keys.ToArray();
         }
      }

      public bool HasFormator(ECsvWorkType type) {
         for(int n = 0; n < _count; n++) {
            FCsvHead head = _values[n];
            if(type == ECsvWorkType.Read) {
               if(head.ReadFormator != null) {
                  return true;
               }
            } else if(type == ECsvWorkType.Write) {
               if(head.WriteFormator != null) {
                  return true;
               }
            }
         }
         return false;
      }

      public bool HasValidator(ECsvWorkType type) {
         for(int n = 0; n < _count; n++) {
            if(_values[n].HasValidator(type)) {
               return true;
            }
         }
         return false;
      }

      public void LoadConfig(FXmlNode config) {
         Clear();
         FXmlNodes nodes = config.Nodes;
         if(nodes != null) {
            foreach(FXmlNode node in nodes) {
               if(node.IsName(FCsvHead.HEAD_TYPE)) {
                  FCsvHead head = new FCsvHead();
                  head.LoadConfig(node);
                  Set(head.Name, head);
               }
            }
         }
         // Dump
         if(_logger.DebugAble) {
            _logger.Debug(this, "LoadConfig", "Load all heads (count={0})", Count);
         }
      }
   }
}
