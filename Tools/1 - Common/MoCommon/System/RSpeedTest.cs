using MO.Common.Collection;
using MO.Common.Lang;

namespace MO.Common.System
{
   public class RSpeedTest
   {
      private static FDictionary<FSpeedTest> _globals = new FDictionary<FSpeedTest>();

      private static FObjects<FSpeedTest> _tests = new FObjects<FSpeedTest>();

      public static FSpeedTest Sync(string key) {
         key = key.ToLower();
         FSpeedTest test = _globals[key];
         if(test == null) {
            test = new FSpeedTest();
            _globals[key] = test;
         }
         return test;
      }

      public static FSpeedTest TestStart(string key) {
         FSpeedTest test = Sync(key);
         test.DoStart();
         return test;
      }

      public static FSpeedTest TestEnd(string key) {
         FSpeedTest test = Sync(key);
         test.DoEnd();
         return test;
      }

      public static FSpeedTest TestStart() {
         FSpeedTest test = new FSpeedTest();
         _tests.Push(test);
         return test;
      }

      public static FSpeedTest TestEnd() {
         FSpeedTest test = _tests.Pop();
         if(test != null) {
            test.DoEnd();
         }
         return test;
      }
   }
}
