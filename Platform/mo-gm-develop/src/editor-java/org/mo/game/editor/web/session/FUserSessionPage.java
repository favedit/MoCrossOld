package org.mo.game.editor.web.session;

import org.mo.com.lang.FObjectId;
import org.mo.com.lang.IAttributes;
import org.mo.com.xml.FXmlNode;
import org.mo.web.core.session.IWebSession;

public class FUserSessionPage
      extends FObjectId{

   private static final long serialVersionUID = 1L;

   private String _catalogXml;

   private FXmlNode _componentNode;

   // 设置信息
   private IAttributes _configuration;

   private FXmlNode _friendNode;

   private String _loginMessage;

   private String _loginResult;

   private FXmlNode _messageNode;

   private FXmlNode _notifyNode;

   // 参数信息
   private IAttributes _parameters;

   private String _passport;

   private String _password;

   private FXmlNode _pendingNode;

   // 属性信息
   private IAttributes _properties;

   private IWebSession _session;

   private String _userGuid;

   private FXmlNode _visitNode;

   public String getCatalogXml(){
      return _catalogXml;
   }

   public FXmlNode getComponentNode(){
      return _componentNode;
   }

   public IAttributes getConfiguration(){
      return _configuration;
   }

   public FXmlNode getFriendNode(){
      return _friendNode;
   }

   public String getLoginMessage(){
      return _loginMessage;
   }

   public String getLoginResult(){
      return _loginResult;
   }

   public FXmlNode getMessageNode(){
      return _messageNode;
   }

   public FXmlNode getNotifyNode(){
      return _notifyNode;
   }

   public IAttributes getParameters(){
      return _parameters;
   }

   public String getPassport(){
      return _passport;
   }

   public String getPassword(){
      return _password;
   }

   public FXmlNode getPendingNode(){
      return _pendingNode;
   }

   public IAttributes getProperties(){
      return _properties;
   }

   public String getUserGuid(){
      return _userGuid;
   }

   public FXmlNode getVisitNode(){
      return _visitNode;
   }

   public IWebSession session(){
      return _session;
   }

   public void setCatalogXml(String catalogXml){
      _catalogXml = catalogXml;
   }

   public void setComponentNode(FXmlNode componentNode){
      _componentNode = componentNode;
   }

   public void setConfiguration(IAttributes configuration){
      _configuration = configuration;
   }

   public void setFriendNode(FXmlNode friendNode){
      _friendNode = friendNode;
   }

   public void setLoginMessage(String loginMessage){
      _loginMessage = loginMessage;
   }

   public void setLoginResult(String loginResult){
      _loginResult = loginResult;
   }

   public void setMessageNode(FXmlNode messageNode){
      _messageNode = messageNode;
   }

   public void setNotifyNode(FXmlNode notifyNode){
      _notifyNode = notifyNode;
   }

   public void setParameters(IAttributes parameters){
      _parameters = parameters;
   }

   public void setPassport(String passport){
      _passport = passport;
   }

   public void setPassword(String password){
      _password = password;
   }

   public void setPendingNode(FXmlNode pendingNode){
      _pendingNode = pendingNode;
   }

   public void setProperties(IAttributes properties){
      _properties = properties;
   }

   public void setSession(IWebSession session){
      _session = session;
   }

   public void setUserGuid(String userGuid){
      _userGuid = userGuid;
   }

   public void setVisitNode(FXmlNode visitNode){
      _visitNode = visitNode;
   }

}
