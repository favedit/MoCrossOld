using System;
using System.Collections.Generic;
using System.Text;
using System.Runtime.Remoting.Messaging;
using MO.Common.Lang;

namespace MO.Core.Aop.Component {

   public class FAopProxy : FAbsAopProxy {

      private ILogger _logger = RLogger.Find(typeof(FAopProxy));

      public FAopProxy(MarshalByRefObject marshalObject, Type type)
         : base(marshalObject, type) {
      }

      public override void ProcessBefore(IMethodCallMessage request) {
         if (_logger.DebugAble) {
            _logger.Debug(GetProxiedType(), request.MethodName + " -Before", RAopProxy.GetCallerInfo(request));
         }
      }

      public override void ProcessAfter(IMethodCallMessage request, IMethodReturnMessage response) {
         if (_logger.DebugAble) {
            _logger.Debug(GetProxiedType(), response.MethodName + " -After", "{0} {1}", RAopProxy.GetCallerInfo(request), RAopProxy.GetReturnInfo(response));
         }
      }
   }
}
