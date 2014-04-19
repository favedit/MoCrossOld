using System;
using System.Collections.Generic;
using System.Text;
using System.Reflection;
using MO.Common.Content;
using MO.Common.Lang;
using MO.Common.Lang.Reflection;
using MO.Core.Aop.Component;

namespace MO.Core.Aop.Config {

   [Serializable]
   public class XAopComponent : XAbsAopComponent {

      public const string TAG = "Component";

      protected FType _linkFace;

      protected FType _linkType;

      protected object _instance;

      protected FAopDescriptor _descriptor = new FAopDescriptor();

      public FType LinkFace {
         get { return _linkFace; }
         set { _linkFace = value; }
      }

      public FType LinkType {
         get { return _linkType; }
         set { _linkType = value; }
      }

      public object Instance {
         get { return _instance; }
         set { _instance = value; }
      }

      public FAopDescriptor Descriptor {
         get { return _descriptor; }
      }

      public override void LoadConfig(FXmlNode config) {
         base.LoadConfig(config);
         // Face
         if(!RString.IsEmpty(_faceName)) {
            _linkFace = _console.FindType(_faceName);
         }
         // Type
         if(!RString.IsEmpty(_typeName)) {
            _linkType = _console.FindType(_typeName); 
            _descriptor.Parse(_linkType);
         }
      }

      public object CreateInstance() {
         return RClass.CreateInstance(_linkType.Type);
      }

      public object CreateInstance(params object[] args) {
         return RClass.CreateInstance(_linkType.Type, args);
      }

   }

}
