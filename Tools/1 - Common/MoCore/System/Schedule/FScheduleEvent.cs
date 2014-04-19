using System;
using System.Collections.Generic;
using System.Text;
using MO.Common.Content;
using MO.Common.Lang;

namespace MObj.Windows.Schedule {

   public class FScheduleEvent : IScheduleEvent {

      public const string RES_NAME = "name";

      public const string NAME = "Event";

      public const string PTY_TYPE = "type";

      public const string PTY_VALID = "valid";

      public const string PTY_FILE = "file";

      protected EScheduleEventType _type;

      protected bool _valid = true;

      protected string _fileName;

      private object _tag;

      internal FScheduleEvents _events;

      public FScheduleEvent() {
      }

      public EScheduleEventType Type {
         get { return _type; }
      }

      public string TypeName {
         get { return REnum.ToString<EScheduleEventType>(_type); }
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

      public virtual string GetNameInfo() {
         return "Not support for GetNameInfo";
      }

      public virtual string GetDetailInfo() {
         return "Not support for GetDetailInfo";
      }

      public void Assign(FScheduleEvent scheduleEvent) {
         _events = scheduleEvent._events;
         _type = scheduleEvent._type;
         _valid = scheduleEvent._valid;
         _fileName = scheduleEvent._fileName;
         _tag = scheduleEvent._tag;
      }

      public virtual void LoadConfig(FXmlNode config) {
         if (config.Contains(PTY_TYPE)) {
            _type = REnum.ToValue<EScheduleEventType>(config[PTY_TYPE]);
         }
         if (config.Contains(PTY_VALID)) {
            _valid = config.GetBoolean(PTY_VALID);
         }
         if (config.Contains(PTY_FILE)) {
            _fileName = config[PTY_FILE];
         }
      }

      public virtual void SaveConfig(FXmlNode config) {
         config[PTY_TYPE] = REnum.ToString<EScheduleEventType>(_type);
         config.Set(PTY_VALID, _valid);
         config[PTY_FILE] = _fileName;
      }

      public virtual void Process() {
         throw new FFatalException("Not support - Process");
      }

      public FScheduleEvent ConvertToType() {
         return _events.ConvertToType(this, _type);
      }

      public FScheduleEvent ConvertToType(EScheduleEventType type) {
         return _events.ConvertToType(this, type);
      }

   }

}
