using System;
using System.Collections.Generic;
using System.Text;
using System.DirectoryServices;
using MO.Common.Lang;

namespace MO.Core.Window.Services {

   public class FIisAppPoolCollection : FAdDirectory {

      private static ILogger _logger = RLogger.Find(typeof(FIisAppPoolCollection));

      public bool Exists(string name) {
         return Find(name) != null;
      }

         /// <summary>
      /// 傾僾儕億乕儖傪憑嶕偡傞
      /// </summary>
      /// <param name="name">傾僾儕億乕儖柤</param>
      /// <returns>傾僾儕億乕儖</returns>
      public FIisAppPool Find(string name) {
         FAdDirectory pools = Connection.Get("/AppPools");
         foreach (DirectoryEntry entry in pools.Entry.Children) {
            if (FIisAppPool.CLASS_NAME.Equals(entry.SchemaClassName)) {
               if(name.Equals( entry.Name)){
                  FIisAppPool pool = new FIisAppPool();
                  pool._connection = Connection;
                  pool.Entry = entry;
                  return pool;
               }
            }
         }
         return null;
      }

      /// <summary>
      /// 傾僾儕億乕儖傪怴婯偡傞
      /// </summary>
      /// <returns>僒僀僩偺斣崋</returns>
      public FIisAppPool Create(string name) {
         _logger.Debug(this, "Create", "Create pool " + name);
         FAdDirectory directory = Connection.Get("/AppPools");
         DirectoryEntry entry = directory.Entry.Children.Add(name, "IIsApplicationPool");
         entry.Properties["AppPoolAutoStart"].Value = true;
         entry.CommitChanges();
         FIisAppPool pool = new FIisAppPool();
         pool._connection = Connection;
         pool.Entry = entry;
         return pool;
      }

      /// <summary>
      /// 傾僾儕億乕儖傪嶍彍偡傞
      /// </summary>
      /// <returns>僒僀僩偺斣崋</returns>
      public void Delete(string name) {
         _logger.Debug(this, "Delete", "Delete pool " + name);
         FAdDirectory directory = Connection.Get("/AppPools/" + name);
         directory.Entry.DeleteTree();
      }
   }

}
