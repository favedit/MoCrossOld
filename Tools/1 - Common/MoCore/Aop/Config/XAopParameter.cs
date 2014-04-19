using System;
using System.Collections.Generic;
using System.Text;
using MO.Common.Content;
using MO.Common.Lang;

namespace MO.Core.Aop.Config {

   public class XAopParameter : XAopNode {

      public const string TAG = "Parameter";

      public const string PTY_TYPE = "type";

      private EAopParameterType _dataType = EAopParameterType.String;

      private object _dataValue;

      public EAopParameterType DataType {
         get { return _dataType; }
         set { _dataType = value; }
      }

      public Type Type {
         get {
            switch (_dataType) {
               case EAopParameterType.String:
                  return typeof(string);
            }
            throw new FFatalException("Unknown parameter type. {0}", _dataType.ToString());
         }
      }

      public object DataValue {
         get { return _dataValue; }
         set { _dataValue = value; }
      }

      public override void LoadConfig(FXmlNode config) {
         base.LoadConfig(config);
         // Load type
         string type = config[PTY_TYPE];
         if(!RString.IsEmpty(type)) {
            _dataType = REnum.ToValue<EAopParameterType>(type);
         }
         // Load component
         if (config.HasNode()) {
            FXmlNode node = config.Nodes[0];
            if (node.IsName(XAopComponent.TAG)) {
               _dataType = EAopParameterType.Component;
            }
         }
         // Load value
         if (_dataType == EAopParameterType.String) {
            _dataValue = config.Text;
         }
      }

   }

}
