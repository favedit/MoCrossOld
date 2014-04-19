using System;
using System.Collections.Generic;
using System.Text;
using MO.Common.Content;
using System.IO;
using MO.Common.Lang;

namespace MObj.Windows.Schedule {

   public class FScheduleConditionFile : FScheduleCondition {

      public const string RES_DETAIL_FILE = "detail.file";

      private static IResource _resource = RResource.Find(typeof(FScheduleEventExecute));

      public FScheduleConditionFile() {
         _type = EScheduleConditionType.File;
      }

      public override string GetNameInfo() {
         return _resource.FindDisplay(RES_NAME);
      }

      public override string GetDetailInfo() {
         if (RString.IsEmpty(_fileName)) {
            return null;
         }
         return _resource.FindDisplay(RES_DETAIL_FILE, _fileName);
      }

      public override bool CheckCondition() {
         return File.Exists(_fileName);
      }

   }

}
