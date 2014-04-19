package org.mo.core.aop.config;

import org.mo.com.console.FConsole;
import org.mo.com.io.RFile;
import org.mo.com.lang.FFatalError;
import org.mo.com.lang.FStrings;
import org.mo.com.lang.RString;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.com.resource.IResource;
import org.mo.com.resource.RResource;
import org.mo.com.xml.FXmlDocument;
import org.mo.com.xml.FXmlNode;

//============================================================
// <T>AOP配置控制台。</T>
//============================================================
public class FAopConfigConsole
      extends FConsole
{
   // 日志输出接口
   private static ILogger _logger = RLogger.find(FAopConfigConsole.class);

   // 关联资源接口
   private static IResource _resource = RResource.find(FAopConfigConsole.class);

   // 属性名称
   private final static String PTY_NAME = "name";

   // 内容设置
   private final static String VAL_CONFIG = "Config";

   // 控件集合
   private XAopComponentCollection _component;

   // 控件集合集合
   private XAopComponentsCollection _components;

   // 设置集合
   private XAopConfigCollection _configs;

   // 定义集合
   private XAopDefineCollection _defines;

   // 文件集合
   private final FStrings _loadedFiles = new FStrings();

   // 节点集合表
   private final XAopNodesMap _nodesMap = new XAopNodesMap();

   //============================================================
   // <T>构造AOP配置控制台。</T>
   //============================================================
   public FAopConfigConsole(){
   }

   //============================================================
   // <T>获得设置集合。</T>
   //
   // @return 设置集合
   //============================================================
   public XAopConfigCollection configCollection(){
      if(null == _configs){
         _configs = (XAopConfigCollection)_nodesMap.find(XAopConfig.TAG_NAME);
      }
      return _configs;
   }

   //============================================================
   // <T>获得定义集合。</T>
   //
   // @return 定义集合
   //============================================================
   public XAopDefineCollection defineCollection(){
      if(null == _defines){
         _defines = (XAopDefineCollection)_nodesMap.find(XAopDefine.TAG_NAME);
      }
      return _defines;
   }

   //============================================================
   // <T>获得控件配置集合。</T>
   //
   // @return 控件配置集合
   //============================================================
   public XAopComponentCollection componentCollection(){
      if(null == _component){
         _component = (XAopComponentCollection)_nodesMap.find(XAopComponent.TAG_NAME);
      }
      return _component;
   }

   //============================================================
   // <T>获得控件集合配置集合。</T>
   //
   // @return 控件集合配置集合
   //============================================================
   public XAopComponentsCollection componentsCollection(){
      if(null == _components){
         _components = (XAopComponentsCollection)_nodesMap.find(XAopComponents.TAG_NAME);
      }
      return _components;
   }

   //============================================================
   // <T>获得节点集合哈希表。</T>
   //
   // @return 节点集合哈希表
   //============================================================
   public XAopNodesMap nodesMaps(){
      return _nodesMap;
   }

   //============================================================
   // <T>查找配置节点。</T>
   //
   // @param type 类型
   // @param key 主键
   // @return 配置节点
   //============================================================
   @SuppressWarnings("unchecked")
   public <V extends IAopNode> V findNode(String type,
                                          String key){
      if(!RString.isEmpty(type, key)){
         XAopNodeCollection<IAopNode> nodes = (XAopNodeCollection<IAopNode>)_nodesMap.find(type);
         if(null != nodes){
            return (V)nodes.findByKey(key);
         }
      }
      return null;
   }

   //============================================================
   // <T>查找配置节点集合。</T>
   //
   // @param type 类型
   // @return 配置节点集合
   //============================================================
   public XAopNodeCollection<?> findNodes(String type){
      XAopNodeCollection<?> nodes = null;
      if(!RString.isEmpty(type)){
         nodes = (XAopNodeCollection<?>)_nodesMap.find(type);
      }
      return nodes;
   }

   //============================================================
   // <T>解析定义。</T>
   //
   // @param value 字符串
   // @return 字符串
   //============================================================
   public String parseDefine(String value){
      return defineCollection().parse(value);
   }

   //============================================================
   // <T>加载配置节点。</T>
   //
   // @param xconfig 配置节点
   //============================================================
   @SuppressWarnings("unchecked")
   protected boolean loadNode(FXmlNode xconfig){
      // 检查子节点
      if(!xconfig.hasNode()){
         return false;
      }
      //............................................................
      // 加载设置
      for(FXmlNode node : xconfig.nodes()){
         // 检查配置设置
         String nodeName = node.getNvl(PTY_NAME);
         if(node.isName(XAopConfig.TAG_NAME) && nodeName.equals(VAL_CONFIG)){
            if(null != _configs){
               throw new FFatalError("Aop config is already exists");
            }
            // 创建配置信息
            XAopConfig aopConfig = new XAopConfig();
            aopConfig.loadConfig(node);
            // 创建集合
            _configs = aopConfig.createCollection();
            _configs.push(aopConfig);
            _nodesMap.set(VAL_CONFIG, _configs);
            continue;
         }
         //............................................................
         // 检查配置设置
         String name = node.name();
         XAopConfig aopConfig = _configs.findByKey(name);
         if(null == aopConfig){
            throw new FFatalError("Can't find aop config. (name={1})", name);
         }
         // 获得设置信息
         IAopNodes<IAopNode> aopNodes = _nodesMap.find(name);
         if(null == aopNodes){
            aopNodes = aopConfig.createCollection();
            _nodesMap.set(name, aopNodes);
         }
         // 创建实体
         IAopNode aopNode = aopConfig.createInstance();
         aopNode.loadConfig(node);
         aopNodes.push(aopNode);
      }
      return true;
   }

   //============================================================
   // <T>加载默认配置。</T>
   //============================================================
   public void loadDefaultConfig(){
      FXmlNode xconfig = _resource.config();
      loadNode(xconfig);
   }

   //============================================================
   // <T>加载配置文件。</T>
   //
   // @param fileName 文件名称
   //============================================================
   public void loadFile(String fileName){
      // 获得本地名称
      String localName = parseDefine(fileName);
      localName = RFile.formatFileName(localName);
      // 检查文件存性
      if(!RFile.exists(localName)){
         throw new FFatalError("Config file is not exist (filename={1})", localName);
      }
      // 防止重复加载
      if(!_loadedFiles.contains(localName)){
         _loadedFiles.push(localName);
         //............................................................
         // 加载设置文件
         try{
            FXmlDocument xdocument = new FXmlDocument(localName);
            FXmlNode xconfig = xdocument.root();
            loadNode(xconfig);
            _logger.debug(this, "loadFile", "Load config success. (file_name={1})", localName);
         }catch(Exception e){
            throw new FFatalError(e, "Load config failed. (file_name={1})", localName);
         }
      }
   }
}
