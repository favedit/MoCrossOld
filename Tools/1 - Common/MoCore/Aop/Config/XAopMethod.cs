using System;
using System.Collections.Generic;
using System.Text;
using MO.Common.Content;
using MO.Common.Lang;

namespace MO.Core.Aop.Config {

   public abstract class XAopMethod : XAopNode {

      public const string PTY_METHOD = "method";

      private string _method;

      private XAopParameters _parameters = new XAopParameters();

      public override string Id {
         get { return _method; }
      }

      public string Method {
         get { return _method; }
         set { _method = value; }
      }

      public XAopParameters Parameters {
         get { return _parameters; }
      }

      public override void LoadConfig(FXmlNode config) {
         _method = config[PTY_METHOD];
         // Load config
         if (config.HasNode()) {
            foreach (FXmlNode node in config.Nodes) {
               if (node.IsName(XAopParameter.TAG)) {
                  // Parameter
                  XAopParameter parameter = new XAopParameter();
                  parameter.LoadConfig(node);
                  _parameters.Push(parameter);
               }
            }
         }
      }

      public Type[] GetParameterTypes() {
         int count = _parameters.Count;
         Type[] types = new Type[count];
         for (int n = 0; n < count; n++) {
            types[n] = _parameters[n].Type;
         }
         return types;
      }

      public override FDumpInfo Dump(FDumpInfo info) {
         info.Append(this);
         return info;
      }

   }

}
