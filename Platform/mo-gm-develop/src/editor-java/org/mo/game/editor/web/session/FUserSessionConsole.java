package org.mo.game.editor.web.session;

import org.mo.com.data.FSqlProcedure;
import org.mo.com.encoding.RSha1;
import org.mo.com.lang.FAttributes;
import org.mo.com.lang.IAttributes;
import org.mo.com.lang.REnum;
import org.mo.com.lang.RString;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.core.aop.face.ALink;
import org.mo.core.bind.IBindConsole;
import org.mo.eng.environment.IEnvironmentConsole;
import org.mo.eng.security.IPermission;
import org.mo.logic.data.ICpSession;
import org.mo.logic.data.impl.FCpSessionImpl;
import org.mo.web.core.session.IWebSession;
import org.mo.web.protocol.common.IWebUser;

public class FUserSessionConsole
      implements
         IUserSessionConsole
{

   private static ILogger _logger = RLogger.find(FUserSessionConsole.class);

   public static String VIEW_COMPANY = "Company";

   public final String FLG_BEGIN = "D:/Workspace/eUIS/webroot";

   @ALink
   protected IBindConsole _bindConsole;

   @ALink
   protected IEnvironmentConsole _environmentConsole;

   @Override
   public void fetchDetailInfo(FUserSessionArgs args){
      //      if(RString.isEmpty(args.viewName())){
      //         args.setViewName(VIEW_COMPANY);
      //      }
      //      // 获得用户信息
      //      String userId = args.getUserId();
      //      IPmUserDi pmUserDi = new FPmUserImpl(args.sqlContext());
      //      FSqlProcedure procedure = pmUserDi.fetchUserDetailInfo(userId, null, null, null, null, null, null, null);
      //      // 建立返回数据 
      //      IAttributes configuration = procedure.parameter("configuration_").asAttributes();
      //      args.setConfiguration(configuration);
      //      // 设置返回数据
      //      IAttributes properties = procedure.parameter("properties_").asAttributes();
      //      // 获得用户图片文件路径
      //      String server = _environmentConsole.findRegister("server_web");
      //      String path = _environmentConsole.parseServer(server, "${system.develop|home.webroot.ars.usr.avatar}");
      //      String rootPath = _environmentConsole.parseServer(server, path);
      //      String root = rootPath.substring(rootPath.lastIndexOf(FLG_BEGIN) + FLG_BEGIN.length());
      //      //String root = "/ars/usr/";
      //      String ogid = properties.get("ogid");
      //      properties.set("avatar_path", root + ogid + "/private");
      //      if(RBoolean.parse(properties.get("has_picture"))){
      //         properties.set("avatar_small", root + ogid + "/private/small.jpg");
      //         properties.set("avatar_head", root + ogid + "/private/head.jpg");
      //         properties.set("avatar_picture", root + ogid + "/private/picture.jpg");
      //      }else{
      //         String avatar = properties.get("avatar");
      //         properties.set("avatar_small", "/ars/psn/" + avatar + ".gif");
      //         properties.set("avatar_head", "/ars/psn/" + avatar + ".gif");
      //         properties.set("avatar_picture", "/ars/psn/" + avatar + ".gif");
      //      }
      //      args.setProperties(properties);
      //      if(_logger.debugAble()){
      //         _logger.debug(this, "fetchInfo", "Connect id         :{0}", args.sessionId());
      //         _logger.debug(this, "fetchInfo", "User configuration :{0}", configuration.dump());
      //         _logger.debug(this, "fetchInfo", "User properties    :{0}", properties.dump());
      //      }
      //      // 建立目录列表
      //      FXmlPackParser packParser = new FXmlPackParser();
      //      FXmlNode catalogNode = new FXmlNode(RXml.DEFAULT_ROOT_NAME);
      //      packParser.parse(catalogNode, procedure.parameter("catalog_pack_").asString());
      //      catalogNode.setName("LgMenuBar");
      //      if(catalogNode.hasNode()){
      //         for(FXmlNode menuNode : catalogNode.nodes()){
      //            menuNode.setName("LgMenu");
      //            if(menuNode.hasNode()){
      //               for(FXmlNode subMenuNode : menuNode.nodes()){
      //                  subMenuNode.setName("LgSubMenu");
      //                  if(subMenuNode.hasNode()){
      //                     FXmlNode menuGroupNode = new FXmlNode("LgSubMenuGroup");
      //                     for(FXmlNode menuItemNode : subMenuNode.nodes()){
      //                        menuItemNode.setName("LgSubMenuItem");
      //                        menuGroupNode.push(menuItemNode);
      //                     }
      //                     subMenuNode.nodes().clear();
      //                     subMenuNode.push(menuGroupNode);
      //                  }
      //               }
      //            }
      //         }
      //      }
      //      args.setCatalogNode(catalogNode);
      //      if(_logger.debugAble()){
      //         _logger.debug(this, "fetchInfo", "User catalog:\n{0}", catalogNode.xml());
      //      }
      //      // 建立组件列表
      //      FXmlNode componentNode = new FXmlNode(RXml.DEFAULT_ROOT_NAME);
      //      packParser.parse(componentNode, procedure.parameter("component_pack_").asString());
      //      args.setComponentNode(componentNode);
      //      if(_logger.debugAble()){
      //         _logger.debug(this, "fetchInfo", "User components:\n{0}", componentNode.xml());
      //      }
      //      // 建立通知列表
      //      FXmlNode notifyNode = new FXmlNode(RXml.DEFAULT_ROOT_NAME);
      //      packParser.parse(notifyNode, procedure.parameter("notify_pack_").asString());
      //      args.setNotifyNode(notifyNode);
      //      if(_logger.debugAble()){
      //         _logger.debug(this, "fetchInfo", "User notifies:\n{0}", notifyNode.xml());
      //      }
      //      // 建立好友列表
      //      FXmlNode friendNode = new FXmlNode(RXml.DEFAULT_ROOT_NAME);
      //      packParser.parse(friendNode, procedure.parameter("friend_pack_").asString());
      //      args.setFriendNode(friendNode);
      //      if(_logger.debugAble()){
      //         _logger.debug(this, "fetchInfo", "User friend:\n{0}", friendNode.xml());
      //      }
      //      // 建立访问列表
      //      FXmlNode visitNode = new FXmlNode(RXml.DEFAULT_ROOT_NAME);
      //      packParser.parse(visitNode, procedure.parameter("visit_pack_").asString());
      //      args.setVisiteNode(visitNode);
      //      if(_logger.debugAble()){
      //         _logger.debug(this, "fetchInfo", "User visit:\n{0}", visitNode.xml());
      //      }
   }

   @Override
   public void fetchDetailInfoByGuid(FUserSessionArgs args){
      //      String userGuid = args.getUserGuid();
      //      IPmUserDi pmUserDi = new FPmUserImpl(args.sqlContext());
      //      String userId = pmUserDi.getIdByOgid(userGuid).returnAsString();
      //      args.setUserId(userId);
      //      // 获得用户信息
      //      fetchDetailInfo(args);
   }

   @Override
   public void fetchInfo(FUserSessionArgs args){
      //      if(RString.isEmpty(args.viewName())){
      //         args.setViewName(VIEW_COMPANY);
      //      }
      //      // 获得用户信息
      //      String connectId = args.sessionId();
      //      String userId = args.getUserId();
      //      IWebSession session = args.session();
      //      if(null != session){
      //         connectId = session.connectId();
      //         userId = session.user().userId();
      //      }
      //      IPmUserDi pmUserDi = new FPmUserImpl(args.sqlContext());
      //      FSqlProcedure procedure = pmUserDi.fetchUserInfo(userId, null, null);
      //      // 建立返回数据 
      //      IAttributes configuration = procedure.parameter("configuration_").asAttributes();
      //      args.setConfiguration(configuration);
      //      // 设置返回数据
      //      IAttributes properties = procedure.parameter("properties_").asAttributes();
      //      // 获得用户图片文件路径
      //      String server = _environmentConsole.findRegister("server_web");
      //      String path = _environmentConsole.parseServer(server, "${system.develop|home.webroot.ars.usr.avatar}");
      //      String rootPath = _environmentConsole.parseServer(server, path);
      //      String root = rootPath.substring(rootPath.lastIndexOf(FLG_BEGIN) + FLG_BEGIN.length());
      //      //String root = "/ars/usr/";
      //      String ogid = properties.get("ogid");
      //      if(RBoolean.parse(properties.get("has_picture"))){
      //         properties.set("avatar_small", root + ogid + "/private/small.jpg");
      //         properties.set("avatar_head", root + ogid + "/private/head.jpg");
      //         properties.set("avatar_picture", root + ogid + "/private/picture.jpg");
      //      }else{
      //         String avatar = properties.get("avatar");
      //         properties.set("avatar_small", "/ars/psn/" + avatar + ".gif");
      //         properties.set("avatar_head", "/ars/psn/" + avatar + ".gif");
      //         properties.set("avatar_picture", "/ars/psn/" + avatar + ".gif");
      //      }
      //      args.setProperties(properties);
      //      if(_logger.debugAble()){
      //         _logger.debug(this, "fetchInfo", "Connect id         :{0}", connectId);
      //         _logger.debug(this, "fetchInfo", "User configuration :{0}", configuration.dump());
      //         _logger.debug(this, "fetchInfo", "User properties    :{0}", properties.dump());
      //      }
   }

   @Override
   public void fetchInfoByGuid(FUserSessionArgs args){
      //      String userGuid = args.getUserGuid();
      //      IPmUserDi pmUserDi = new FPmUserImpl(args.sqlContext());
      //      String userId = pmUserDi.getIdByOgid(userGuid).returnAsString();
      //      args.setUserId(userId);
      //      // 获得用户信息
      //      fetchInfo(args);
   }

   @Override
   public void fetchMessage(FUserSessionArgs args){
      //      // 获得用户信息
      //      String userId = args.getUserId();
      //      IPmUserDi pmUserDi = new FPmUserImpl(args.sqlContext());
      //      FSqlProcedure procedure = pmUserDi.fetchUserMessage(null, userId, null, null, null);
      //      // 建立解析器
      //      FXmlPackParser packParser = new FXmlPackParser();
      //      // 建立等待列表
      //      FXmlNode pendingNode = new FXmlNode(RXml.DEFAULT_ROOT_NAME);
      //      packParser.parse(pendingNode, procedure.parameter("pending_pack_").asString());
      //      args.setPendingNode(pendingNode);
      //      if(_logger.debugAble() && pendingNode.hasNode()){
      //         _logger.debug(this, "fetchMessage", "User pendings:\n{0}", pendingNode.xml());
      //      }
      //      // 建立消息列表
      //      FXmlNode messageNode = new FXmlNode(RXml.DEFAULT_ROOT_NAME);
      //      packParser.parse(messageNode, procedure.parameter("message_pack_").asString());
      //      args.setMessageNode(messageNode);
      //      if(_logger.debugAble() && messageNode.hasNode()){
      //         _logger.debug(this, "fetchMessage", "User messages:\n{0}", messageNode.xml());
      //      }
      //      // 建立通知列表
      //      FXmlNode notifyNode = new FXmlNode(RXml.DEFAULT_ROOT_NAME);
      //      packParser.parse(notifyNode, procedure.parameter("notify_pack_").asString());
      //      args.setNotifyNode(notifyNode);
      //      if(_logger.debugAble() && notifyNode.hasNode()){
      //         _logger.debug(this, "fetchMessage", "User notifies:\n{0}", notifyNode.xml());
      //      }
   }

   /* (non-Javadoc)
    * @see com.linekong.euis.core.session.IUserSessionConsole#login(com.linekong.euis.core.session.FUserSessionArgs)
    */
   @Override
   public void login(FUserSessionArgs args){
      // 构建登录参数
      IAttributes parameters = new FAttributes();
      parameters.set("passport", args.passport());
      parameters.set("password", args.password());
      parameters.set("password_encryption", RSha1.encode(args.password()));
      // 用户登录系统
      loginUser(args, parameters, ELoginMode.Login);
   }

   /* (non-Javadoc)
    * @see com.linekong.euis.core.session.IUserSessionConsole#loginDirect(com.linekong.euis.core.session.FUserSessionArgs)
    */
   @Override
   public void loginDirect(FUserSessionArgs args){
      // 构建登录参数
      IAttributes parameters = new FAttributes();
      parameters.set("login_id", args.loginId());
      parameters.set("user_guid", args.getUserGuid());
      // 用户登录系统
      loginUser(args, parameters, ELoginMode.LoginDirect);
   }

   protected void loginUser(FUserSessionArgs args,
                            IAttributes parameters,
                            ELoginMode mode){
      // 构建登录参数
      IAttributes logic = new FAttributes();
      IAttributes configuration = new FAttributes();
      IAttributes properties = new FAttributes();
      parameters.set("code", args.code());
      parameters.set("view_name", RString.nvl(args.viewName(), "Company"));
      parameters.set("server_code", _environmentConsole.registers().get("server_process"));
      parameters.set("client_ip", args.remoteAddr());
      parameters.set("client_host", args.remoteHost());
      parameters.set("client_port", Integer.toString(args.remotePort()));
      parameters.set("client_user", args.remoteUser());
      // 用户登录系统
      ICpSession cpSession = new FCpSessionImpl(args.sqlContext());
      FSqlProcedure procedure = null;
      if(ELoginMode.LoginDirect == mode){
         procedure = cpSession.loginDirect(logic, parameters, configuration, properties, null, null, null, null, null);
      }else{
         procedure = cpSession.login(logic, parameters, configuration, properties, null, null, null, null, null);
      }
      logic.unpack(procedure.parameter("logic_").asString());
      parameters.unpack(procedure.parameter("parameters_").asString());
      configuration.unpack(procedure.parameter("configuration_").asString());
      properties.unpack(procedure.parameter("properties_").asString());
      // 获得用户登录结果
      String sessionId = logic.get("session_id");
      String loginResult = logic.get("login_result");
      args.setLoginStatus(REnum.parse(ELoginStatus.class, loginResult));
      args.setSessionId(sessionId);
      args.setLoginMessage(logic.get("login_message"));
      args.setLoginPassword(logic.get("login_password"));
      args.setLoginCode(logic.get("login_code"));
      args.setParameters(parameters);
      args.setConfiguration(configuration);
      args.setProperties(properties);
      // 关联当前线程信息
      IWebSession session = args.session();
      if(null != session){
         session.setConnectId(sessionId);
         IWebUser user = session.user();
         user.setUserId(logic.get("user_id"));
         user.parameters().assign(parameters);
         user.configuration().assign(configuration);
         user.properties().assign(properties);
         // 建立页面信息
         FUserSessionPage page = args.sessionPage();
         if(null != page){
            session.setConnectId(args.sessionId());
            page.setUserGuid(logic.get("user_guid"));
            page.setConfiguration(user.configuration());
            page.setParameters(user.parameters());
            page.setProperties(user.properties());
            if(_logger.debugAble()){
               _logger.debug(this, "loginUser", "Connect id         :{0}", args.sessionId());
               _logger.debug(this, "loginUser", "User configuration :{0}", user.configuration().dump());
               _logger.debug(this, "loginUser", "User parameters    :{0}", user.parameters().dump());
               _logger.debug(this, "loginUser", "User properties    :{0}", user.properties().dump());
            }
            // 获得当前用户的角色集合
            String roles = user.properties().get("ROLES");
            String roleCodes = user.properties().get("ROLE_CODES");
            IPermission permission = args.session().permission();
            permission.setUserName(user.properties().get("login_passport"));
            permission.setRoles(roles);
            permission.setRoleCodes(roleCodes);
         }
      }
   }

   /* (non-Javadoc)
    * @see com.linekong.euis.core.session.IUserSessionConsole#logout(com.linekong.euis.core.session.FUserSessionArgs)
    */
   @Override
   public void logout(FUserSessionArgs args){
      // 构建登录参数
      IAttributes logic = new FAttributes();
      IAttributes parameters = new FAttributes();
      parameters.set("session_id", args.sessionId());
      // 用户登录系统
      ICpSession cpSession = new FCpSessionImpl(args.sqlContext());
      cpSession.logout(logic, parameters);
   }

}
