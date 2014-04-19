package org.mo.eng.persistence;

import org.mo.com.io.FStringFile;
import org.mo.com.io.RFile;
import org.mo.com.lang.FFatalError;
import org.mo.com.lang.FObjects;
import org.mo.com.lang.FString;
import org.mo.com.lang.RString;
import org.mo.com.lang.cultrue.REncoding;
import org.mo.com.lang.reflect.RClass;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.com.xml.EXmlConfig;
import org.mo.com.xml.FXmlDocument;
import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.FXmlNodes;
import org.mo.com.xml.IXmlObject;
import org.mo.com.xml.RXmlObject;
import org.mo.core.aop.RAop;
import org.mo.core.aop.face.ALink;
import org.mo.core.aop.face.AProperty;
import org.mo.eng.persistence.common.XAttribute;
import org.mo.eng.persistence.common.XComponent;
import org.mo.eng.persistence.common.XInterface;
import org.mo.eng.persistence.common.XPersistence;
import org.mo.eng.store.FXmlConfig;
import org.mo.eng.store.FXmlConfigConsole;
import org.mo.eng.template.ITemplateConsole;
import org.mo.eng.template.ITemplateParser;

public class FPersistenceConsole
      extends FXmlConfigConsole<XPersistence>
      implements
         IPersistenceConsole
{
   // 日志输出接口
   private static ILogger _logger = RLogger.find(FPersistenceConsole.class);

   // 模板控制台接口
   @ALink
   protected ITemplateConsole _templateConsole;

   // 模板对象
   @AProperty
   protected String _templateObject;

   // 模板接口
   @AProperty
   protected String _templateFace;

   // 持久化集合
   protected FPersistences _persistences = new FPersistences();

   protected void buildAttributes(XPersistence xpersistence,
                                  IXmlObject xcomponent,
                                  FXmlNodes attributes){
      // 分解继承的接口或类
      String name = xcomponent.innerGet(XComponent.PTY_NAME);
      String inherits = xcomponent.innerGet(XComponent.PTY_INHERITS);
      if(!RString.isEmpty(inherits)){
         String[] inheritList = RString.trimNoEmpty(RString.split(inherits, ','));
         for(String inherit : inheritList){
            // 查找被继承的接口或类
            IXmlObject xinherit = xpersistence.find(XComponent.PTY_NAME, inherit);
            if(null == xinherit){
               throw new FFatalError("Can't find inherit. (component={1}, inherit={2})", name, inherit);
            }
            buildAttributes(xpersistence, xinherit, attributes);
         }
      }
      // 增加新的属性到属性列表
      if(xcomponent.hasChild()){
         int count = xcomponent.children().count();
         for(int n = 0; n < count; n++){
            IXmlObject xchild = xcomponent.children().get(n);
            if(XAttribute.NAME.equals(xchild.name())){
               String attrName = xchild.innerGet(XAttribute.PTY_NAME);
               if(null != attributes.findNode(XAttribute.NAME, XAttribute.PTY_NAME, attrName)){
                  throw new FFatalError("Attribute is duplication. (component={1}, attribute={2})", name, attrName);
               }
               attributes.push(RXmlObject.convertToNode(xchild, EXmlConfig.Value));
            }
         }
      }
   }

   protected void buildInterface(XPersistence xpersistence,
                                 XInterface xinterface,
                                 FString source,
                                 EPersistenceBuildAction action){
      ITemplateParser parser = _templateConsole.getParser(_templateFace);
      FXmlNode compNode = RXmlObject.convertDeepToNode(xinterface, EXmlConfig.Value);
      String className = xinterface.getClassName();
      if(RString.isEmpty(className)){
         throw new FFatalError("Class name is null.");
      }
      // 分解继承关系
      FXmlNodes importNodes = new FXmlNodes();
      String inherits = xinterface.getInherits();
      if(!RString.isEmpty(inherits)){
         for(String inherit : RString.split(inherits, ',')){
            if(!RString.isEmpty(inherit)){
               IXmlObject xobject = xpersistence.find("name", inherit);
               if(null == xobject){
                  throw new FFatalError("inherits is not found. (inherit={1})", inherit);
               }
               FXmlNode importNode = importNodes.create("Import");
               importNode.set("class_name", xobject.innerGet("class_name"));
            }
         }
      }
      // 解析代码
      parser.reset();
      parser.define("imports", importNodes);
      parser.define("interface", compNode);
      parser.define("attributes", compNode.nodes());
      source.assign(parser.parse());
      if(EPersistenceBuildAction.Store == action){
         // 获得文件名称
         String persistenceSource = xpersistence.getSource();
         String path = RAop.configConsole().parseDefine(persistenceSource);
         String target = RString.replace(className, '.', '/');
         target = RFile.makeFilename(path, target + ".java");
         // 存储文件内容
         FStringFile file = new FStringFile();
         file.append(source);
         file.saveFile(target, REncoding.UTF8.toString());
         _logger.debug(this, "buildInterface", "Build interface source. (target={1})", target);
      }
   }

   protected void buildComponent(XPersistence xpersistence,
                                 XComponent xcomponent,
                                 FString source,
                                 EPersistenceBuildAction action){
      // String template = xpersistence.getTemplate();
      ITemplateParser parser = _templateConsole.getParser(_templateObject);
      FXmlNode compNode = RXmlObject.convertToNode(xcomponent, EXmlConfig.Value);
      String clsName = xcomponent.getBaseName();
      compNode.set("class", clsName);
      if(RString.isEmpty(clsName)){
         throw new FFatalError("Class name is null.");
      }
      // 分解继承关系
      FXmlNodes importNodes = new FXmlNodes();
      String inherits = xcomponent.getInherits();
      if(!RString.isEmpty(inherits)){
         for(String inherit : RString.split(inherits, ',')){
            if(!RString.isEmpty(inherit)){
               IXmlObject xobject = xpersistence.find("name", inherit);
               if(null == xobject){
                  throw new FFatalError("inherits is not found. (inherit={1})", inherit);
               }
               FXmlNode importNode = importNodes.create("Import");
               importNode.set("class_name", xobject.innerGet("class_name"));
            }
         }
      }
      // 递归建立所有属性
      FXmlNodes attrs = new FXmlNodes();
      try{
         buildAttributes(xpersistence, xcomponent, attrs);
      }catch(Exception e){
         throw new FFatalError(e, "Build attributes error. (persistence={1}, component={2})", xpersistence.getName(), xcomponent.innerGet("name"));
      }
      System.out.println(compNode.dump());
      System.out.println(attrs.dump());
      // 解析代码
      parser.reset();
      parser.define("imports", importNodes);
      parser.define("component", compNode);
      parser.define("attributes", attrs);
      source.assign(parser.parse());
      if(EPersistenceBuildAction.Store == action){
         // 获得文件名称
         String path = RAop.configConsole().parseDefine(xpersistence.getSource());
         String target = RString.replace(clsName, '.', '/');
         target = RFile.makeFilename(path, target + ".java");
         // 存储文件内容
         FStringFile file = new FStringFile();
         file.append(source);
         file.saveFile(target, REncoding.UTF8.toString());
         _logger.debug(this, "buildComponent", "Build component source. (target={1})", target);
      }
   }

   protected void buildObject(XPersistence xpersistence,
                              IXmlObject xobject,
                              FString source,
                              EPersistenceBuildAction action){
      if(XComponent.isInstance(xobject)){
         buildComponent(xpersistence, (XComponent)xobject, source, action);
      }else if(XInterface.isInstance(xobject)){
         buildInterface(xpersistence, (XInterface)xobject, source, action);
      }else{
         throw new FFatalError("Invalid component type.");
      }
   }

   @Override
   public void build(FPersistenceBuilderArgs args){
      EPersistenceBuildAction action = args.action();
      EPersistenceSourceType type = args.type();
      XPersistence persistence = args.persistence();
      if(EPersistenceSourceType.Persistence == type){
         int count = persistence.children().count();
         for(int n = 0; n < count; n++){
            IXmlObject xobject = persistence.children().get(n);
            buildObject(persistence, xobject, args.source(), action);
         }
      }else if(EPersistenceSourceType.Component == type){
         buildObject(persistence, args.component(), args.source(), action);
      }
   }

   @Override
   protected FObjects<XPersistence> createCollection(){
      return new FObjects<XPersistence>(XPersistence.class);
   }

   @Override
   @SuppressWarnings("unchecked")
   public <V> V find(Class<V> clazz,
                     String name){
      String id = name + "@" + clazz.getName();
      IPersistence persistence = _persistences.find(id);
      if(null == persistence){
         FXmlNode config = findFileConfig(name);
         persistence = (IPersistence)RClass.newInstance(clazz);
         persistence.loadConfig(config);
         _persistences.set(id, persistence);
      }
      return (V)persistence;
   }

   public FXmlNode findFileConfig(String name){
      name = RString.replace(name, '.', '/');
      String filename = RFile.makeFilename(_workpath, name + ".xml");
      FXmlDocument doc = new FXmlDocument(filename);
      return doc.root();
   }

   @Override
   public XPersistence synchronize(String name){
      FXmlConfig confg = super.synchronizeConfig(name);
      if(null != confg){
         if(null == confg.instance()){
            confg.setInstance(new XPersistence());
         }
         return (XPersistence)confg.instance();
      }
      return null;
   }
}
