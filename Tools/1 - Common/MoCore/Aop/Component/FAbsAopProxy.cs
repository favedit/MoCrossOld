using System;
using System.Collections.Generic;
using System.Text;
using System.Runtime.Remoting.Proxies;
using System.Runtime.Remoting.Messaging;
using System.Runtime.Remoting.Activation;
using System.Runtime.Remoting.Services;
using System.Runtime.Remoting;

namespace MO.Core.Aop.Component {

   public abstract class FAbsAopProxy : RealProxy, IAopProxy {

      private bool _proxy;

      private readonly MarshalByRefObject _object;

      public FAbsAopProxy(MarshalByRefObject marshalObject, Type type)
         : base(type) {
         _object = marshalObject;
      }

      public override IMessage Invoke(IMessage message) {
         IMethodCallMessage caller = (IMethodCallMessage)message;
         // Get proxy able
         bool proxyAble = _proxy;
         if (!proxyAble) {
            foreach (AProxyMethodAttribute attribute in caller.MethodBase.GetCustomAttributes(typeof(AProxyMethodAttribute), false)) {
               if (attribute.IsProxy) {
                  proxyAble = true;
                  break;
               }
            }
         }
         // Process before
         if (proxyAble) {
            ProcessBefore(caller);
         }
         // Process
         IMethodReturnMessage response = null;
         IConstructionCallMessage constructor = caller as IConstructionCallMessage;
         if (constructor != null) {
            // Constructor
            RealProxy defaultProxy = RemotingServices.GetRealProxy(_object);
            defaultProxy.InitializeServerObject(constructor);
            MarshalByRefObject transparentProxy = (MarshalByRefObject)this.GetTransparentProxy();
            response = EnterpriseServicesHelper.CreateConstructionReturnMessage(constructor, transparentProxy);
         } else {
            // Method
            response = RemotingServices.ExecuteMessage(_object, caller);
         }
         // Process after
         if (proxyAble) {
            ProcessAfter(caller, response);
         }
         return response;
      }

      #region IAopProxy members

      public bool IsProxy {
         get { return _proxy; }
         set { _proxy = value; }
      }

      public MarshalByRefObject ProxyObject {
         get { return (MarshalByRefObject)GetTransparentProxy(); }
      }

      public abstract void ProcessBefore(IMethodCallMessage request);

      public abstract void ProcessAfter(IMethodCallMessage request, IMethodReturnMessage response);

      #endregion

   }

}
