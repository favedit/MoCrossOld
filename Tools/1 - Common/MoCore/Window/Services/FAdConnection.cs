using System;
using System.Collections.Generic;
using System.Text;
using System.DirectoryServices;
using MO.Common.Lang;

namespace MO.Core.Window.Services {

   public class FAdConnection : IAdConnection{

      private ILogger _logger = RLogger.Find(typeof(FAdConnection));

      public static string ROOT_PATH = "{0}://{1}";

      private FAdConnectInfo _connectInfo = new FAdConnectInfo();

      private FAdDirectory _root;

      public FAdConnectInfo ConnectInfo {
         get { return _connectInfo; }
      }

      /// <summary>
      /// 崻Entry傪庢摼偡傞
      /// </summary>
      /// <returns>Entry</returns>
      public FAdDirectory Root {
         get { return _root; }
      }

      public void Connect() {
         _root = new FAdDirectory(this);
         if (_logger.DebugAble) {
            _logger.Debug(this, "Connect", "Connect info=", _connectInfo.Dump());
         }
         if (_connectInfo.HasLogin) {
            _root._entry = new DirectoryEntry(_connectInfo.FullPath, _connectInfo.UserName, _connectInfo.UserPassword, AuthenticationTypes.Secure);
         } else {
            _root._entry = new DirectoryEntry(_connectInfo.FullPath);
         }
      }

      /*protected string MakePath(string path) {
         string fullpath = String.Format(ROOT_PATH, _protocol, _host);
         if (_rootPath != null) {
            fullpath += "/" + _rootPath;
         }
         if (path != null) {
            fullpath += path;
         }
         return fullpath;
      }*/

      /// <summary>
      /// 僷僗偵傛傝丄Entry傪庢摼偡傞
      /// </summary>
      /// <param name="path">僷僗</param>
      /// <returns>Entry</returns>
      public FAdDirectory Get(string path) {
         /*FAdDirectory directory = new FAdDirectory(this);
         path = MakePath(path);
         if (_loginUid == null) {
            _logger.Debug(this, "Get", "Get directory " + path);
            directory.Entry = new DirectoryEntry(path);
         } else {
            //directory.Entry = new DirectoryEntry(path, _loginUid, _loginPwd, AuthenticationTypes.Secure);
            _logger.Debug(this, "Get", "Get directory " + path + " uid=" + _loginUid + " pwd=" + _loginPwd);
            directory.Entry = new DirectoryEntry(path, _loginUid, _loginPwd, AuthenticationTypes.Secure);
         }
         return directory;*/
         return null;
      }
   }

}
