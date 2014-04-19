using System;
using System.Collections.Generic;
using System.Text;
using MO.Common.Lang;

namespace MO.Core.Window.Services {
   
   public class FAdConnectInfo : IDump{

      public static string DEFAULT_HOST = "localhost";

      private string _host;

      private string _domain;
      
      private string _userName;
      
      private string _userPassword;

      /// <summary>
      /// 儂僗僩
      /// </summary>
      public string Host {
         get { return _host; }
         set { _host = value; }
      }

      /// <summary>
      /// Domain
      /// </summary>
      public string Domain {
         get { return _domain; }
         set { _domain = value; }
      }

      /// <summary>
      /// 儘僌僀儞偺儐乕僓柤
      /// </summary>
      public string UserName {
         get { return _userName; }
         set { _userName = value; }
      }

      /// <summary>
      /// 儘僌僀儞偺僷僗儚乕僪
      /// </summary>
      public string UserPassword {
         get { return _userPassword; }
         set { _userPassword = value; }
      }

      public bool HasLogin {
         get { return !RString.IsEmpty(_userName) && !RString.IsEmpty(_userPassword); }
      }

      public string FullPath {
         get{
            FString path = new FString();
            path.Append(RAdManager.PROTOCOL);
            path.Append("://");
            path.Append(RString.Nvl(_host, DEFAULT_HOST));
            path.Append('/');
            string[] domains = RString.Split(_domain, '.');
            int count = domains.Length;
            for (int n = 0; n < count; n++) {
               path.Append("DC=");
               path.Append(domains[n]);
               if (n < count - 1) {
                  path.Append(',');
               }
            }
            return path.ToString();
         }
      }

      public void Assign(FAdConnectInfo info) {
         _host = info._host;
         _domain = info._domain;
         _userName = info._userName;
         _userPassword = info._userPassword;
      }


      #region IDump members

      public FDumpInfo Dump() {
         return Dump(new FDumpInfo(this));
      }

      public FDumpInfo Dump(FDumpInfo info) {
         info.Append(" Path=", FullPath);
         info.Append(" UserName=", _userName);
         return info;
      }

      #endregion
   }

}
