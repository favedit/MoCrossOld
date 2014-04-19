using System;
using System.Collections.Generic;
using System.Text;
using System.Security.Policy;
using System.Reflection;
using MO.Common.System;
using System.IO;

namespace MO.Common.Lang.Reflection {

   public class FAppDomain {

      internal AppDomain _domain;

      internal FAppLoader _loader;

      internal AppDomainSetup _setup;

      internal Evidence _evidence;

      public FAppDomain() {
         AppDomain current = AppDomain.CurrentDomain;
         _setup = new AppDomainSetup();
         _setup.ApplicationBase = current.BaseDirectory;
         _setup.ConfigurationFile = current.SetupInformation.ConfigurationFile;
         _setup.ShadowCopyFiles = "true";
         //_setup.ShadowCopyDirectories = RSystem.Location("Plus");
         _setup.ApplicationName = "Dynamic";
         _evidence = new Evidence(current.Evidence);
      }

      public FAppLoader Loader {
         get { return _loader; }
      }

      public AppDomainSetup Setup {
         get { return _setup; }
      }

      public Evidence Evidence {
         get { return _evidence; }
      }

      public void Load() {
         // Create AppDomain
         string domainName = RDate.Format("Domain-HH24MISS");
         _domain = AppDomain.CreateDomain(domainName, _evidence, _setup);
         // Create Loader
         string assemblyName = typeof(FAppLoader).Assembly.FullName;
         string loaderName = typeof(FAppLoader).FullName;
         _loader = (FAppLoader)_domain.CreateInstanceAndUnwrap(assemblyName, loaderName);
      }

      public void Unload() {
         if (_domain != null) {
            AppDomain.Unload(_domain);
            _domain = null;
         }
      }
   }

}
