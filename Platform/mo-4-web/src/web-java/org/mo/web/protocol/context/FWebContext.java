package org.mo.web.protocol.context;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.mo.com.collections.FObjectDictionary;
import org.mo.com.io.RFile;
import org.mo.com.lang.FAttributes;
import org.mo.com.lang.FFatalError;
import org.mo.com.lang.FObject;
import org.mo.com.lang.IAttributes;
import org.mo.com.lang.IObjectId;
import org.mo.com.lang.RString;
import org.mo.com.lang.generic.TDumpInfo;
import org.mo.com.lang.reflect.RProperty;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.com.message.FMessages;
import org.mo.com.xml.FXmlNode;
import org.mo.core.aop.RAop;
import org.mo.core.convert.RObjectConvert;
import org.mo.logic.session.ISqlSessionContext;
import org.mo.web.core.container.EContainerScope;
import org.mo.web.core.container.RWebContainer;
import org.mo.web.core.container.common.FWebContainerCollection;
import org.mo.web.core.session.IWebSession;
import org.mo.web.core.upload.IWebUploadConsole;
import org.mo.web.protocol.common.FWebUpdateParser;
import org.mo.web.protocol.common.FWebUploadFile;
import org.mo.web.protocol.common.FWebUploadFiles;
import org.mo.web.protocol.common.RWebRequest;

//============================================================
// <T>网页环境。</T>
//============================================================
public class FWebContext
      extends FObject
      implements
         IWebContext
{
   // 日志输出接口
   protected static ILogger _logger = RLogger.find(FWebContext.class);

   // 网页会话
   protected IWebSession _session;

   // 页面设置
   protected FPageConfig _pageConfig;

   // 页面状态
   protected IPageStatus _status;

   // 远程地址
   protected String _remoteAddress;

   // 远程主机
   protected String _remoteHost;

   // 远程端口
   protected int _remotePort;

   // 远程用户
   protected String _remoteUser;

   // 请求路径
   protected String _requestPath;

   // 请求相对地址
   protected String _requestUri;

   // 请求全地址
   protected String _requestUrl;

   // 请求字符串
   protected String _queryString;

   // 环境路径
   protected String _contextPath;

   // 环境标签
   protected String _contextTag;

   // 头信息
   protected FAttributes _heads;

   // 参数集合
   protected FAttributes _parameters;

   // Cookie字符串
   protected String _cookieStore;

   // Cookie集合
   protected FAttributes _cookies = new FAttributes();

   // 上传文件
   protected FWebUploadFiles _files;

   // 容器集合
   protected FWebContainerCollection _containers;

   // 网络应答对象
   protected FWebResponse _response = new FWebResponse();

   // 属性字典
   protected FObjectDictionary _attributes = new FObjectDictionary();

   // SQL会话环境
   protected ISqlSessionContext _sqlContext;

   // 消息列表
   protected FMessages _messages;

   // 定义字典
   public FObjectDictionary _defines = new FObjectDictionary();

   //============================================================
   // <T>构造网页环境。</T>
   //============================================================
   public FWebContext(IWebSession session,
                      HttpServletRequest request,
                      HttpServletResponse response){
      _session = session;
      innerBuild(request, response);
      request.setAttribute(IWebGlobals.INSTANCE_WEB_CONTEXT, this);
   }

   //============================================================
   // <T>建立网页环境内部信息。</T>
   //
   // @param request 网页请求
   // @param response 网页应答
   //============================================================
   protected void innerBuild(HttpServletRequest request,
                             HttpServletResponse response){
      _contextPath = request.getContextPath();
      _heads = RWebRequest.makeHeads(request);
      _parameters = RWebRequest.makeParameters(request);
      _requestUri = request.getRequestURI().substring(_contextPath.length());
      // 读取Cookie
      Cookie[] cookies = request.getCookies();
      if(null != cookies){
         for(Cookie cookie : cookies){
            if("MOCK".equalsIgnoreCase(cookie.getName())){
               cookies().unpack(cookie.getValue());
               _cookieStore = cookies().pack().toString();
               break;
            }
         }
      }
      // 默认定义
      define("#head", _heads);
      define("#cookies", _cookies);
      define("#parameter", _parameters);
      define("#history", session().history());
      _requestUrl = request.getRequestURL().toString();
      _remoteAddress = request.getRemoteAddr();
      _remoteHost = request.getRemoteHost();
      _remotePort = request.getRemotePort();
      _remoteUser = request.getRemoteUser();
      if(_requestUrl.startsWith("http://")){
         _requestPath = _requestUrl;
         int nFind = _requestPath.indexOf("/", 7);
         if(nFind > 0){
            _requestPath = _requestPath.substring(0, nFind) + _contextPath;
         }
      }
      _queryString = request.getQueryString();
      // if(m_sRequestUri.startsWith(_contextPath)){
      //    m_sRequestUri = m_sRequestUri.substring(_contextPath.length());
      // }
      // 建立上传文件信息信息
      if(FWebUpdateParser.isMultipart(request)){
         IWebUploadConsole uploadConsole = RAop.find(IWebUploadConsole.class);
         uploadConsole.parse(request, _parameters, files());
      }
      // 建立其他相关信息
      // m_sLanguage = oSession.environment().country().language();
   }

   //============================================================
   // <T>获得网页会话。</T>
   //
   // @return 网页会话
   //============================================================
   @Override
   public IWebSession session(){
      return _session;
   }

   //============================================================
   // <T>获得页面设置。</T>
   //
   // @return 页面设置
   //============================================================
   @Override
   public FPageConfig pageConfig(){
      return _pageConfig;
   }

   //============================================================
   // <T>设置页面设置。</T>
   //
   // @param pageConfig 页面设置
   //============================================================
   public void setPageConfig(FPageConfig pageConfig){
      _pageConfig = pageConfig;
   }

   //============================================================
   // <T>获得页面状态。</T>
   //
   // @return 页面状态
   //============================================================
   @Override
   public IPageStatus pageStatus(){
      if(_status == null){
         _status = new FPageStatus();
      }
      return _status;
   }

   //============================================================
   // <T>获得环境路径。</T>
   //
   // @return 环境路径
   //============================================================
   @Override
   public String contextPath(){
      return _contextPath;
   }

   //============================================================
   // <T>获得环境路径。</T>
   //
   // @param uri 相对路径
   // @return 环境路径
   //============================================================
   @Override
   public String contextPath(String uri){
      if((uri != null) && uri.startsWith("#")){
         if(null == _contextTag){
            return _contextPath + uri.substring(1);
         }
         return _contextPath + _contextTag + uri.substring(1);
      }
      return _contextPath + uri;
   }

   //============================================================
   // <T>获得环境标签。</T>
   //
   // @return 环境标签
   //============================================================
   @Override
   public String contextTag(){
      return _contextTag;
   }

   //============================================================
   // <T>获得环境标签。</T>
   //
   // @param uri 相对路径
   // @return 环境标签
   //============================================================
   @Override
   public String contextTag(String uri){
      if(null != uri && uri.startsWith("#")){
         if(null == _contextTag){
            return uri.substring(1);
         }
         return _contextTag + uri.substring(1);
      }
      return uri;
   }

   //============================================================
   // <T>设置环境标签。</T>
   //
   // @param contextTag 环境标签
   //============================================================
   public void setContextTag(String contextTag){
      _contextTag = contextTag;
   }

   //============================================================
   // <T>获得远程地址。</T>
   //
   // @return 远程地址
   //============================================================
   @Override
   public String remoteAddress(){
      return _remoteAddress;
   }

   //============================================================
   // <T>获得远程主机。</T>
   //
   // @return 远程主机
   //============================================================
   @Override
   public String remoteHost(){
      return _remoteHost;
   }

   //============================================================
   // <T>获得远程端口。</T>
   //
   // @return 远程端口
   //============================================================
   @Override
   public int remotePort(){
      return _remotePort;
   }

   //============================================================
   // <T>获得远程用户。</T>
   //
   // @return 远程用户
   //============================================================
   @Override
   public String remoteUser(){
      return _remoteUser;
   }

   //============================================================
   // <T>获得远程路径。</T>
   //
   // @return 远程路径
   //============================================================
   @Override
   public String requestPath(){
      return _requestPath;
   }

   //============================================================
   // <T>获得请求相对地址。</T>
   //
   // @return 请求相对地址
   //============================================================
   @Override
   public String requestUri(){
      return _requestUri;
   }

   //============================================================
   // <T>设置请求相对地址。</T>
   //
   // @param requestUri 请求相对地址
   //============================================================
   @Override
   public void setRequestUri(String requestUri){
      _requestUri = requestUri;
   }

   //============================================================
   // <T>获得请求全地址。</T>
   //
   // @return 请求全地址
   //============================================================
   @Override
   public String requestUrl(){
      return _requestUrl;
   }

   //============================================================
   // <T>获得请求字符串。</T>
   //
   // @return 请求字符串
   //============================================================
   @Override
   public String queryString(){
      return _queryString;
   }

   //============================================================
   // <T>获得头信息集合。</T>
   //
   // @return 头信息集合
   //============================================================
   @Override
   public IAttributes heads(){
      return _heads;
   }

   //============================================================
   // <T>根据名称获得头信息。</T>
   //
   // @param name 名称
   // @return 头信息
   //============================================================
   @Override
   public String head(String sName){
      return _heads.get(sName);
   }

   //============================================================
   // <T>获得参数集合。</T>
   //
   // @return 参数集合
   //============================================================
   @Override
   public IAttributes parameters(){
      return _parameters;
   }

   //============================================================
   // <T>根据名称获得参数信息。</T>
   //
   // @param name 名称
   // @return 参数信息
   //============================================================
   @Override
   public String parameter(String name){
      return _parameters.find(name);
   }

   //============================================================
   // <T>获得COOKIE信息集合。</T>
   //
   // @return COOKIE信息集合
   //============================================================
   @Override
   public IAttributes cookies(){
      return _cookies;
   }

   //============================================================
   // <T>根据名称获得COOKIE信息。</T>
   //
   // @param name 名称
   // @return COOKIE信息
   //============================================================
   @Override
   public String cookie(String name){
      return _cookies.find(name);
   }

   //============================================================
   // <T>测试COOKIE是否发生变更。</T>
   //
   // @return 是否变更
   //============================================================
   @Override
   public boolean testCookieChanged(){
      if(null != _cookieStore){
         String cookieString = cookies().pack();
         return !_cookieStore.equals(cookieString);
      }
      return false;
   }

   //============================================================
   // <T>判断是否含有文件。</T>
   //
   // @return 是否含有
   //============================================================
   @Override
   public boolean hasFile(){
      return (null != _files) ? !_files.isEmpty() : false;
   }

   //============================================================
   // <T>获得文件集合。</T>
   //
   // @return 文件集合
   //============================================================
   @Override
   public FWebUploadFiles files(){
      if(null == _files){
         _files = new FWebUploadFiles();
      }
      return _files;
   }

   //============================================================
   // <T>获得容器集合。</T>
   //
   // @return 容器集合
   //============================================================
   @Override
   public FWebContainerCollection containers(){
      if(_containers == null){
         _containers = new FWebContainerCollection();
         _containers.setScopeCd(EContainerScope.Page);
      }
      return _containers;
   }

   //============================================================
   // <T>获得网络应答。</T>
   //
   // @return 网络应答
   //============================================================
   @Override
   public IWebResponse response(){
      return _response;
   }

   //============================================================
   // <T>根据名称获得属性。</T>
   //
   // @param name 名称
   // @return 属性
   //============================================================
   @Override
   public Object attribute(String name){
      return _attributes.find(name);
   }

   //============================================================
   // <T>根据名称设置属性。</T>
   //
   // @param name 名称
   // @param value 属性
   //============================================================
   @Override
   public void setAttribute(String name,
                            Object value){
      _attributes.set(name, value);
   }

   //============================================================
   // <T>获得SQL环境。</T>
   //
   // @return SQL环境
   //============================================================
   @Override
   public ISqlSessionContext sqlContext(){
      return _sqlContext;
   }

   //============================================================
   // <T>设置SQL环境。</T>
   //
   // @param sqlContext SQL环境
   //============================================================
   public void setSqlContext(ISqlSessionContext sqlContext){
      _sqlContext = sqlContext;
   }

   //============================================================
   // <T>检查是否含有消息。</T>
   //
   // @return 是否含有
   //============================================================
   @Override
   public boolean hasMessages(){
      return (null != _messages) ? !_messages.isEmpty() : false;
   }

   //============================================================
   // <T>获得消息集合。</T>
   //
   // @return 消息集合
   //============================================================
   @Override
   public FMessages messages(){
      if(_messages == null){
         _messages = new FMessages();
      }
      return _messages;
   }

   //============================================================
   // <T>检查是否定义了当前别称。</T>
   //
   // @param alias 别称
   // @return 是否定义
   //============================================================
   @Override
   public boolean hasDefine(String alias){
      return _defines.contains(alias);
   }

   //============================================================
   // <T>给指定对象定义一个别称。</T>
   //
   // @param alias 别称
   // @param item 对象
   //============================================================
   @Override
   public void define(String alias,
                      Object item){
      if(alias == null){
         throw new FFatalError("Alias object can't be null. (alias={1})", alias);
      }
      _defines.set(alias.toLowerCase(), item);
   }

   //============================================================
   // <T>根据别称移除一个定义。</T>
   //
   // @param alias 别称
   //============================================================
   @Override
   public void undefine(String alias){
      if(alias == null){
         throw new FFatalError("Parameter alias is null. (alias={1})", alias);
      }
      _defines.remove(alias);
   }

   //============================================================
   // <T>解析配置节点。</T>
   //
   // @param item 项目
   // @param value 内容
   // @return 配置节点
   //============================================================
   protected FXmlNode parseXmlNode(Object item,
                                   String path){
      if(item instanceof FXmlNode){
         FXmlNode node = (FXmlNode)item;
         return node.findPath(path);
      }else{
         throw new FFatalError("Unknown xml instance. (item={1}, path={2})", item, path);
      }
   }

   //============================================================
   // <T>解析配置信息。</T>
   //
   // @param item 项目
   // @param value 内容
   // @return 配置信息
   //============================================================
   protected Object parseXmlValue(Object item,
                                  String value){
      if(item instanceof FXmlNode){
         FXmlNode node = (FXmlNode)item;
         if("name".equals(value)){
            return node.name();
         }else if("text".equals(value)){
            return node.text();
         }else if("xml".equals(value)){
            return node.xml();
         }else{
            throw new FFatalError("Unknown xml type. (item={1}, value={2})", item, value);
         }
      }else{
         throw new FFatalError("Unknown xml instance. (item={1}, value={2})", item, value);
      }
   }

   //============================================================
   // <T>解析来源为对象。</T>
   // <P>&config#Config:xml | &config#Config:text</P>
   // <P>{&config#Config}.value</P>
   //
   // @param source 来源
   // @return 对象
   //============================================================
   public Object parse(String source){
      // 如果源为空的情况
      if(RString.isEmpty(source)){
         return RString.EMPTY;
      }
      // 可以分解的情况
      try{
         // 分解 {&?}的情况
         int start = source.lastIndexOf("{&");
         if(-1 != start){
            int end = source.indexOf('}', start);
            if(-1 == end){
               throw new FFatalError("Invalid source. (source={1}).", source);
            }
            // 获得内部定义的对象
            String subSource = source.substring(start + 1, end);
            Object item = parse(subSource);
            if(null == item){
               item = RString.EMPTY;
            }
            // 将对象转换为系统标识，重新取最终内容
            source = source.substring(0, start) + item + source.substring(end + 1);
            return parse(source);
         }
         // 分解 &? 的情况，表示是变量内容
         if(source.startsWith("&")){
            source = source.toLowerCase().substring(1);
            // 分解以#开头的特殊参数
            if(source.startsWith("#")){
               // 分解 &config.? 的情况
               String[] items = RString.splitTwo(source, '.', true);
               if(items.length == 1){
                  return RString.toString(_defines.get(source));
               }else if(items.length == 2){
                  Object item = _defines.get(items[0]);
                  if(item != null){
                     return RProperty.get(item, items[1]);
                  }
                  return null;
               }else{
                  throw new FFatalError("Unknown source. (source={1})", source);
               }
            }
            // 分解 &config#? 的情况
            String[] items = RString.splitTwo(source, '#', true);
            if(null != items){
               Object item = _defines.get(items[0]);
               if(null == item){
                  return null;
               }
               String name = items[1];
               items = RString.splitTwo(name, ':', true);
               if(null != items){
                  // 分解 name#object:? 的情况
                  item = parseXmlNode(item, items[0]);
                  if(null == item){
                     return null;
                  }
                  return parseXmlValue(item, items[1]);
               }
               // 分解 name#object 的情况
               return parseXmlNode(item, name);
            }
            // 分解 &config:? 的情况
            items = RString.splitTwo(source, ':', true);
            if(null != items){
               Object item = _defines.get(items[0]);
               if(null == item){
                  return null;
               }
               return parseXmlValue(item, items[1]);
            }
            // 分解 &config.? 的情况
            items = RString.splitTwo(source, '.', true);
            if(null != items){
               Object item = _defines.get(items[0]);
               if(item == null){
                  return null;
               }
               return RProperty.get(item, items[1]);
            }
            // 分解 &config 的情况
            return _defines.find(source);
         }
         return source;
      }catch(Exception e){
         throw new FFatalError(e, "Parse object errror. (source={1})", source);
      }
   }

   //============================================================
   // <T>解析页面项目。</T>
   //
   // @param source 来源
   // @return 页面项目
   //============================================================
   public SPageItem parseItem(String source){
      SPageItem item = new SPageItem();
      if(source != null){
         // Parse {&name}
         int start = source.lastIndexOf("{&");
         if(start != -1){
            int end = source.indexOf('}', start);
            if(end == -1){
               throw new FFatalError("Invalid source. (source={1}).", source);
            }
            String value = null;
            String subStr = source.substring(start + 2, end);
            String[] items = RString.split(subStr, '.');
            if(items.length == 1){
               value = (String)_defines.get(subStr);
            }else if(items.length == 2){
               Object finditem = _defines.get(items[0]);
               if(finditem != null){
                  value = RProperty.getAsString(finditem, items[1]);
               }
            }else{
               throw new FFatalError("Unknown source. (source={1}", source);
            }
            if(value != null){
               value = source.substring(0, start) + value + source.substring(end + 1);
               if(value.indexOf('&') != -1){
                  return parseItem(value);
               }
            }
            return null;
         }
         // Parse &{name}
         start = source.lastIndexOf("&{");
         if(start != -1){
            int end = source.indexOf('}', start);
            if(end == -1){
               throw new FFatalError("Invalid source. (source={1}", source);
            }
            String subStr = source.substring(start + 2, end);
            String endStr = source.substring(end + 1);
            Object value = _defines.get(subStr);
            item.object = value;
            if(value != null && endStr.startsWith(".")){
               String name = endStr.substring(1);
               if(value instanceof IObjectId){
                  String id = ((IObjectId)value).objectId();
                  item.name = RWebContainer.makeName(id, name);
               }else{
                  throw new FFatalError("Object id is undefined. (value={1}, class={2})", value, value.getClass());
               }
               item.value = RProperty.getAsString(value, name);
            }
         }
         // Parse translae (trs:xxx)
         if(source.startsWith("trs:")){
            item.value = source.substring(source.lastIndexOf('|') + 1);
         }
         // Parse name (&xxx)
         if(source.startsWith("&")){
            source = source.toLowerCase().substring(1);
            String[] items = RString.split(source, '.');
            if(items.length == 1){
               item.object = _defines.get(source);
            }else if(items.length == 2){
               Object value = _defines.find(items[0]);
               item.object = value;
               if(value != null){
                  if(value instanceof IObjectId){
                     String id = ((IObjectId)value).objectId();
                     item.name = RWebContainer.makeName(id, items[1]);
                  }else{
                     throw new FFatalError("Object id is undefined. (value={1}, class={2})", value, value.getClass());
                  }
                  item.value = RProperty.get(value, items[1]);
               }
            }else{
               throw new FFatalError("Unknown source. (source={1})", source);
            }
         }
      }
      return item;
   }

   //============================================================
   // <T>解析对象名称。</T>
   //
   // @param item 项目
   // @param name 名称
   // @return 名称
   //============================================================
   public String parseObjectName(Object item,
                                 String name){
      return name;
   }

   //============================================================
   // <T>解析对象集合。</T>
   //
   // @param source 来源
   // @return 对象集合
   //============================================================
   public Object[] parseObjects(String source){
      if(source != null){
         int start = source.indexOf("&{");
         if(start != -1){
            int end = source.indexOf('}', start);
            if(end != -1){
               Object value = _defines.get(source.substring(start + 1, end));
               if(value instanceof String){
                  source = source.substring(0, start) + "&" + (String)value + source.substring(end + 1);
                  return parseObjects(source);
               }
               return null;
            }
         }
         if(source.startsWith("&")){
            source = source.substring(1).toLowerCase();
            String[] items = RString.split(source, '.');
            if(items.length == 2){
               Object value = _defines.get(items[0]);
               if(value != null){
                  Object item = RProperty.get(value, items[1]);
                  return RObjectConvert.toObjects(item);
               }
            }else{
               Object value = _defines.get(source);
               if(value != null){
                  return RObjectConvert.toObjects(value);
               }
            }
         }
      }
      return null;
   }

   //============================================================
   // <T>解析字符串。</T>
   //
   // @param source 来源
   // @return 字符串
   //============================================================
   public String parseString(String source){
      Object item = parse(source);
      if(null != item){
         return item.toString();
      }
      return RString.EMPTY;
   }

   //============================================================
   // <T>解析图标路径。</T>
   //
   // @param source 来源
   // @return 图标路径
   //============================================================
   @Override
   public String parseIconPath(String source){
      // Icon
      String path = parseString(source);
      boolean real = path.startsWith("#");
      if(real){
         path = path.substring(1);
      }
      path = RString.replace(path, ".", "/");
      return "/ats/" + session().themeId() + "/rs/icon/" + path + ".gif";
   }

   //============================================================
   // <T>解析图片路径。</T>
   //
   // @param source 来源
   // @return 图片路径
   //============================================================
   @Override
   public String parseImagePath(String source){
      // Icon
      String path = parseString(source);
      boolean real = path.startsWith("#");
      if(real){
         path = path.substring(1);
      }
      path = RString.replace(path, ".", "/");
      return "/ats/" + session().themeId() + "/rs/img/" + path + ".gif";
   }

   //============================================================
   // <T>释放环境对象资源。</T>
   //============================================================
   @Override
   public void release(){
      if(hasFile()){
         for(FWebUploadFile file : _files){
            if(RFile.isFile(file.uploadName())){
               if(!RFile.delete(file.uploadName())){
                  _logger.warn(this, "release", "Delete upload temp file error. (file_name={1})", file.uploadName());
               }
            }
         }
      }
   }

   //============================================================
   // <T>生成运行信息。</T>
   //
   // @param info 运行信息
   // @return 运行信息
   //============================================================
   @Override
   public TDumpInfo dump(TDumpInfo info){
      info.appendLine(this);
      if((_heads != null) && !_heads.isEmpty()){
         info.append("Head     : ");
         info.appendLine(_heads.dump());
      }
      if((_cookies != null) && !_cookies.isEmpty()){
         info.append("Cookies  : ");
         info.appendLine(_cookies.dump());
      }
      if((_parameters != null) && !_parameters.isEmpty()){
         info.append("Parameter: ");
         info.append(_parameters.dump());
      }
      return info;
   }
}
