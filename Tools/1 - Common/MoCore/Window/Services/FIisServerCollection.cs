using System;
using System.Collections;
using System.Collections.Generic;
using System.Text;
using System.DirectoryServices;
using MO.Common.Lang;

namespace MO.Core.Window.Services {

   public class FIisServerCollection : FAdDirectory {

      private static ILogger _logger = RLogger.Find(typeof(FIisServerCollection));

      /// <summary>
      /// 怴婯僒僀僩偺斣崋傪庢摼偡傞
      /// </summary>
      /// <returns>僒僀僩偺斣崋</returns>
      public string NewSiteID() {
         ArrayList list = new ArrayList();
         FAdDirectory directory = Connection.Root;
         foreach (DirectoryEntry child in directory.Entry.Children) {
            if (child.SchemaClassName == "IIsWebServer") {
               list.Add(Convert.ToInt32(child.Name.ToString()));
            }
         }
         list.Sort();
         int n = 1;
         foreach (int i in list) {
            if (n == i) {
               n++;
            }
         }
         return n.ToString();
      }

      /// <summary>
      /// 僒僀僩偺柤慜偵傛傝丄懚嵼傪敾抐偡傞
      /// </summary>
      /// <param name="name">僒僀僩偺柤慜</param>
      /// <returns>懚嵼寢壥</returns>
      public bool ExistsByName(string name) {
         bool result = (FindByName(name) != null);
         _logger.Debug(this, "ExistsByName", "Exists server name=" + name + ":" + result);
         return result;
      }

      /// <summary>
      /// 僒僀僩偺斣崋偵傛傝丄僒僀僩偺僀儞僗僞儞僗傪庢摼偡傞
      /// </summary>
      /// <param name="id">僒僀僩偺斣崋</param>
      /// <returns>僒僀僩偺僀儞僗僞儞僗</returns>
      public FIisServer FindById(string id) {
         _logger.Debug(this, "FindById", "Find server by id=" + id);
         FAdDirectory root = Connection.Root;
         foreach (DirectoryEntry entry in root.Entry.Children) {
            if (FIisServer.CLASS_NAME.Equals(entry.SchemaClassName)) {
               if (id.Equals(entry.Name)) {
                  _logger.Debug(this, "FindByName", "Finded server id=" + entry.Name);
                  FIisServer server = new FIisServer();
                  server.Connection = Connection;
                  server.Entry = entry;
                  server.Site = entry.Properties[FIisServer.PTY_SERVER_COMMENT].Value.ToString();
                  return server;
               }
            }
         }
         return null;
      }

      /// <summary>
      /// 僒僀僩偺柤慜偵傛傝丄僒僀僩偺僀儞僗僞儞僗傪庢摼偡傞
      /// </summary>
      /// <param name="name">僒僀僩偺柤慜</param>
      /// <returns>僒僀僩偺僀儞僗僞儞僗</returns>
      public FIisServer FindByName(string name) {
         _logger.Debug(this, "FindByName", "Find server by name=" + name);
         FAdDirectory root = Connection.Root;
         foreach (DirectoryEntry entry in root.Entry.Children) {
            if (FIisServer.CLASS_NAME.Equals(entry.SchemaClassName)) {
               if (name.Equals(entry.Properties[FIisServer.PTY_SERVER_COMMENT].Value)) {
                  _logger.Debug(this, "FindByName", "Finded server id=" + entry.Name);
                  FIisServer server = new FIisServer();
                  server.Connection = Connection;
                  server.Entry = entry;
                  server.Site = name;
                  return server;
               }
            }
         }
         return null;
      }

      /// <summary>
      /// 僒僀僩偺僷僗偵傛傝丄僒僀僩偺僀儞僗僞儞僗傪庢摼偡傞
      /// </summary>
      /// <param name="path">僒僀僩偺僷僗</param>
      /// <returns>僒僀僩偺僀儞僗僞儞僗</returns>
      public FIisServer FindByPath(string path) {
         _logger.Debug(this, "FindByName", "Find server by path=" + path);
         FAdDirectory root = Connection.Root;
         foreach (DirectoryEntry entry in root.Entry.Children) {
            if (FIisServer.CLASS_NAME.Equals(entry.SchemaClassName)) {
               String rootPath = "/" + entry.Name + "/ROOT";
               FAdDirectory pathDir = Connection.Get(rootPath);
               if (FIisVirtualDir.CLASS_NAME.Equals(pathDir.Entry.SchemaClassName)) {
                  PropertyValueCollection ptys = pathDir.Entry.Properties["Path"];
                  foreach (object pty in ptys) {
                     if (pty.Equals(path)) {
                        _logger.Debug(this, "FindByPath", "Finded server id=" + entry.Name + " at " + path);
                        FIisServer server = new FIisServer();
                        server.Connection = Connection;
                        server.Entry = entry;
                        server.Site = entry.Properties[FIisServer.PTY_SERVER_COMMENT].Value.ToString();
                        return server;
                     }
                  }
               }
            }
         }
         return null;
      }

      /// <summary>
      /// 僒僀僩偺僀儞僼僅偵傛傝丄僒僀僩傪怴婯偡傞
      /// </summary>
      /// <param name="server">僒僀僩</param>
      public void Create(FIisServer server) {
         FIisConnection cnn = (FIisConnection)Connection;
         FAdDirectory sitesDir = cnn.Root;
         string siteId = NewSiteID();
         // Site property setup
         FAdDirectory serverDir = sitesDir.CreateChild(siteId, FIisServer.CLASS_NAME);
         //serverDir["AuthFlags"] = EAuthFlags.None;
         serverDir["DefaultDoc"] = "Default.aspx";
         serverDir["ServerBindings"] = ":" + server.Port + ":";
         serverDir["ServerComment"] = server.Site;
         serverDir["ServerAutoStart"] = true;
         serverDir.CommitChanges();
         // Root property setup
         FAdDirectory rootDir = serverDir.CreateChild(FIisVirtualDir.PATH_ROOT, FIisVirtualDir.CLASS_NAME);
         rootDir["AccessFlags"] = EWebAccess.Read | EWebAccess.Execute | EWebAccess.Script;
         //rootDir["AppIsolated"] = EAppIsolated.PooledProcess;
         if (server.PoolName != null) {
            // Build pool
            if (!cnn.Pools.Exists(server.PoolName)) {
               cnn.Pools.Create(server.PoolName);
            }
         } else {
            server.PoolName = FIisAppPool.DEFAULT_NAME;
         }
         rootDir["AppRoot"] = "LM/W3SVC/" + siteId + "/Root";
         rootDir["AuthFlags"] = EAuthFlags.AuthNTLM;
         rootDir["Path"] = server.WebPath;
         rootDir.CommitChanges();
         // Set pool
         rootDir.Invoke("AppCreate3", new object[] { 0, server.PoolName, true });
         // Set pool
         rootDir["AppFriendlyName"] = server.Site;
         rootDir.CommitChanges();
         server.Entry = serverDir.Entry;
      }

      /// <summary>
      /// 僒僀僩偺僀儞僼僅偵傛傝丄僒僀僩傪嶍彍偡傞
      /// </summary>
      /// <param name="server"></param>
      public void Delete(FIisServer server) {
         if (server != null && server.Entry != null) {
            FIisConnection cnn = (FIisConnection)Connection;
            _logger.Debug(this, "Delete", "Delete site " + server.Site + "@" + server.Entry.Name);
            FAdDirectory rootEntry = Connection.Get("/" + server.Entry.Name + "/ROOT");
            string poolId = (string)rootEntry.Entry.Properties["AppPoolId"].Value;
            if (poolId != null && !FIisAppPool.DEFAULT_NAME.Equals(poolId)) {
               if (cnn.Pools.Exists(poolId)) {
                  cnn.Pools.Delete(poolId);
               }
            }
            FAdDirectory root = Connection.Root;
            root.Entry.Children.Remove(server.Entry);
            root.Entry.CommitChanges();
            _logger.Debug(this, "Delete", "Delete site success " + server.Site);
         }
      }

   }

}
