package org.mo.game.editor.web.session;

import org.mo.com.data.ISqlConnect;
import org.mo.com.lang.IAttributes;
import org.mo.com.xml.FXmlNode;
import org.mo.eng.data.common.ISqlContext;
import org.mo.web.core.session.IWebSession;
import org.mo.web.protocol.context.IWebContext;

public class FUserSessionArgs{

   private FXmlNode _catalogNode;

   private String _code;

   private FXmlNode _componentNode;

   private IAttributes _configuration;

   private FXmlNode _friendNode;

   private String _loginCode;

   private String _loginId;

   private String _loginMessage;

   private String _loginPassword;

   private ELoginStatus _loginStatus;

   private FXmlNode _messageNode;

   private FXmlNode _notifyNode;

   private IAttributes _parameters;

   private String _passport;

   private String _password;

   private FXmlNode _pendingNode;

   private IAttributes _properties;

   private String _remoteAddr;

   private String _remoteHost;

   private int _remotePort;

   private String _remoteUser;

   private IWebSession _session;

   private String _sessionId;

   private FUserSessionPage _sessionPage;

   private ISqlConnect _sqlContext;

   private String _userGuid;

   private String _userId;

   private String _viewName;

   private FXmlNode _visiteNode;

   public FUserSessionArgs(){
   }

   public FUserSessionArgs(ISqlContext sqlContext){
      _sqlContext = sqlContext;
   }

   public void append(IWebContext context){
      _sessionId = context.session().connectId();
   }

   public FXmlNode catalogNode(){
      return _catalogNode;
   }

   public String code(){
      return _code;
   }

   public FXmlNode componentNode(){
      return _componentNode;
   }

   public IAttributes configuration(){
      return _configuration;
   }

   public FXmlNode friendNode(){
      return _friendNode;
   }

   public FXmlNode getPendingNode(){
      return _pendingNode;
   }

   public String getUserGuid(){
      return _userGuid;
   }

   public String getUserId(){
      return _userId;
   }

   public String loginCode(){
      return _loginCode;
   }

   public String loginId(){
      return _loginId;
   }

   public String loginMessage(){
      return _loginMessage;
   }

   public String loginPassword(){
      return _loginPassword;
   }

   public ELoginStatus loginStatus(){
      return _loginStatus;
   }

   public FXmlNode messageNode(){
      return _messageNode;
   }

   public FXmlNode notifyNode(){
      return _notifyNode;
   }

   public IAttributes parameters(){
      return _parameters;
   }

   public String passport(){
      return _passport;
   }

   public String password(){
      return _password;
   }

   public IAttributes properties(){
      return _properties;
   }

   public String remoteAddr(){
      return _remoteAddr;
   }

   public String remoteHost(){
      return _remoteHost;
   }

   public int remotePort(){
      return _remotePort;
   }

   public String remoteUser(){
      return _remoteUser;
   }

   public IWebSession session(){
      return _session;
   }

   public String sessionId(){
      return _sessionId;
   }

   public FUserSessionPage sessionPage(){
      return _sessionPage;
   }

   public void setCatalogNode(FXmlNode catalogNode){
      _catalogNode = catalogNode;
   }

   public void setCode(String code){
      _code = code;
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

   public void setLoginCode(String loginCode){
      _loginCode = loginCode;
   }

   public void setLoginId(String loginId){
      _loginId = loginId;
   }

   public void setLoginMessage(String loginMessage){
      _loginMessage = loginMessage;
   }

   public void setLoginPassword(String loginPassword){
      _loginPassword = loginPassword;
   }

   public void setLoginStatus(ELoginStatus loginStatus){
      _loginStatus = loginStatus;
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

   public void setRemoteAddr(String remoteAddr){
      _remoteAddr = remoteAddr;
   }

   public void setRemoteHost(String remoteHost){
      _remoteHost = remoteHost;
   }

   public void setRemotePort(int remotePort){
      _remotePort = remotePort;
   }

   public void setRemoteUser(String remoteUser){
      _remoteUser = remoteUser;
   }

   public void setSession(IWebSession session){
      _session = session;
   }

   public void setSessionId(String sessionId){
      _sessionId = sessionId;
   }

   public void setSessionPage(FUserSessionPage sessionPage){
      _sessionPage = sessionPage;
   }

   public void setSqlContext(ISqlConnect sqlContext){
      _sqlContext = sqlContext;
   }

   public void setUserGuid(String userGuid){
      _userGuid = userGuid;
   }

   public void setUserId(String userId){
      _userId = userId;
   }

   public void setViewName(String viewName){
      _viewName = viewName;
   }

   public void setVisiteNode(FXmlNode visiteNode){
      _visiteNode = visiteNode;
   }

   public ISqlConnect sqlContext(){
      return _sqlContext;
   }

   public String viewName(){
      return _viewName;
   }

   public FXmlNode visiteNode(){
      return _visiteNode;
   }
}
