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
   public class XAbsAopComponent : XAopNode {

      public const string PTY_FACE = "face";

      public const string PTY_TYPE = "type";

      public const string PTY_SCOPE = "scope";

      protected string _faceName;

      protected string _typeName;

      protected EScope _scope;

      protected XAopProperties _properties = new XAopProperties();

      protected XAopConstructor _constructor;

      protected XAopMethods _initializes = new XAopMethods();

      protected XAopMethods _releases = new XAopMethods();

      public XAbsAopComponent() {
      }

      public string FaceName {
         get { return _faceName; }
         set { _faceName = value; }
      }

      public string TypeName {
         get { return _typeName; }
         set { _typeName = value; }
      }

      public EScope Scope {
         get { return _scope; }
         set { _scope = value; }
      }

      public bool HasConstructor {
         get { return (_constructor != null); }
      }

      public bool HasProperty {
         get { return !_properties.IsEmpty(); }
      }

      public bool HasInitialize {
         get { return !_initializes.IsEmpty(); }
      }

      public bool HasRelease {
         get { return !_releases.IsEmpty(); }
      }

      public XAopProperties Properties {
         get { return _properties; }
      }

      public XAopConstructor Constructor {
         get { return _constructor; }
         set { _constructor = value; }
      }

      public XAopMethods Initializes {
         get { return _initializes; }
      }

      public XAopMethods Releases {
         get { return _releases; }
      }

      public override void LoadConfig(FXmlNode config) {
         // Id
         _id = config[PTY_ID];
         // Face
         _faceName = config[PTY_FACE];
         // Type
         _typeName = config[PTY_TYPE];
         // Scope
         string scope = config[PTY_SCOPE];
         if (!RString.IsEmpty(scope)) {
            _scope = REnum.ToValue<EScope>(scope);
         }
         // Load config
         if (config.HasNode()) {
            foreach (FXmlNode node in config.Nodes) {
               if (node.IsName(XAopConstructor.TAG)) {
                  // Constructor
                  if (_constructor != null) {
                     throw new FFatalException("Constructor can't be duplicate.\n{1}", config.Dump());
                  }
                  _constructor = new XAopConstructor();
                  _constructor.LoadConfig(node);
               } else if (node.IsName(XAopProperty.TAG)) {
                  // Properties
                  XAopProperty property = new XAopProperty();
                  property.LoadConfig(node);
                  _properties.Push(property);
               } else if (node.IsName(XAopInitialize.TAG)) {
                  // Initializes
                  XAopInitialize method = new XAopInitialize();
                  method.LoadConfig(node);
                  _initializes.Push(method);
               } else if (node.IsName(XAopRelease.TAG)) {
                  // Releases
                  XAopRelease method = new XAopRelease();
                  method.LoadConfig(node);
                  _releases.Push(method);
               }
            }
         }
      }

      #region IDump members

      public override FDumpInfo Dump(FDumpInfo info) {
         RDump.StartDump(info);
         info.AppendProperty(PTY_ID, _id, true);
         info.AppendProperty(PTY_FACE, _faceName, true);
         info.AppendProperty(PTY_TYPE, _typeName, true);
         info.AppendProperty(PTY_SCOPE, _scope, true);
         // Get max id length
         int idlength = 0;
         if(!_initializes.IsEmpty() || _releases.IsEmpty()) {
            // Initialize max id length
            for (int n = 0; n < _initializes.Count; n++) {
               idlength = Math.Max(idlength, RString.Nvl(_initializes[n].Id).Length);
            }
            // Release max id length
            for (int n = 0; n < _releases.Count; n++) {
               idlength = Math.Max(idlength, RString.Nvl(_releases[n].Id).Length);
            }
         }
         // Initializes
         if(!_initializes.IsEmpty()) {
            for (int n = 0; n < _initializes.Count; n++) {
               info.AppendLine();
               RDump.AppendSpace(info, info.Deep + 1);
               info.Append("Initialize: ");
               info.Append(RString.Nvl(_initializes[n].Id).PadRight(idlength));
               info.Append(" = ");
               _initializes[n].Dump(info);
            }
         }
         // Releases
         if(!_releases.IsEmpty()) {
            for (int n = 0; n < _releases.Count; n++) {
               info.AppendLine();
               RDump.AppendSpace(info, info.Deep + 1);
               info.Append("Release:    ");
               info.Append(RString.Nvl(_releases[n].Id).PadRight(idlength));
               info.Append(" = ");
               _releases[n].Dump(info);
            }
         }
         return info;
      }

      #endregion
   }

}
