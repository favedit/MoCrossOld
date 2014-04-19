using System;
using System.Collections.Generic;
using System.Text;
using MO.Common.Content;
using MO.Common.Lang;

namespace MObj.Windows.Schedule {

   public class FScheduleCondition : IScheduleCondition {

      public const string NAME = "Condition";

      public const string RES_NAME = "name";

      public const string PTY_TYPE = "type";

      public const string PTY_VALID = "valid";

      public const string PTY_FILE = "file";

      internal FScheduleConditions _conditions;

      protected EScheduleConditionType _type;

      protected bool _valid = true;

      protected string _fileName;

      private object _tag;

      public FScheduleCondition() {
      }

      public EScheduleConditionType Type {
         get { return _type; }
      }

      public string TypeName {
         get { return REnum.ToString<EScheduleConditionType>(_type); }
      }

      public bool Valid {
         get { return _valid; }
         set { _valid = value; }
      }

      public string FileName {
         get { return _fileName; }
         set { _fileName = value; }
      }

      public object Tag {
         get { return _tag; }
         set { _tag = value; }
      }

      public void Assign(FScheduleCondition condition) {
         _conditions = condition._conditions;
         _type = condition._type;
         _valid = condition._valid;
         _tag = condition._tag;
      }

      public virtual string GetNameInfo() {
         return "Not support for GetNameInfo";
      }

      public virtual string GetDetailInfo() {
         return "Not support for GetDetailInfo";
      }

      public virtual void LoadConfig(FXmlNode config) {
         if (config.Contains(PTY_TYPE)) {
            _type = REnum.ToValue<EScheduleConditionType>(config[PTY_TYPE]);
         }
         if (config.Contains(PTY_VALID)) {
            _valid = config.GetBoolean(PTY_VALID);
         }
         if (config.Contains(PTY_FILE)) {
            _fileName = config[PTY_FILE];
         }
      }

      public virtual void SaveConfig(FXmlNode config) {
         config[PTY_TYPE] = REnum.ToString<EScheduleConditionType>(_type);
         config.Set(PTY_VALID, _valid);
         config[PTY_FILE] = _fileName;
      }

      public virtual bool CheckCondition() {
         throw new Exception("The method or operation is not implemented.");
      }

      public FScheduleCondition ConvertToType() {
         return _conditions.ConvertToType(this, _type);
      }

      public FScheduleCondition ConvertToType(EScheduleConditionType type) {
         return _conditions.ConvertToType(this, type);
      }

   }

}
