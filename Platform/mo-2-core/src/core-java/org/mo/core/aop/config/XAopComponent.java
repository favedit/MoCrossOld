package org.mo.core.aop.config;

import org.mo.com.lang.FFatalError;
import org.mo.com.lang.RDump;
import org.mo.com.lang.RString;
import org.mo.com.lang.generic.TDumpInfo;
import org.mo.com.lang.reflect.FClass;
import org.mo.com.lang.reflect.RClass;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.com.xml.FXmlNode;
import org.mo.core.aop.descriptor.IAopDescriptor;

//============================================================
// <T>AOP组件。</T>
//============================================================
public class XAopComponent
      extends XAbsAopNode
{
   // 日志输出接口
   @SuppressWarnings("unused")
   private static ILogger _logger = RLogger.find(XAopComponent.class);

   // 标签名称
   public final static String TAG_NAME = "Component";

   // 属性类名
   public final static String PTY_CLASS = "class";

   // 属性接口
   public final static String PTY_FACE = "face";

   // 属性名称
   public final static String PTY_NAME = "name";

   // 属性部署
   public final static String PTY_DEPLOY = "deploy";

   // 属性范围
   public final static String PTY_SCOPE = "scope";

   // 名称
   protected String _name;

   // 接口
   protected String _face;

   // 接口类对象
   protected FClass<?> _faceClass;

   // 类名称
   protected String _className;

   // 范围
   protected EComponentScope _scopeCd;

   // 部署
   protected EComponentDeploy _deploy;

   // 描述器
   protected IAopDescriptor _descriptor;

   // 属性集合
   protected XAopPropertyCollection _properties;

   // 初始化集合
   protected XAopInitializeCollection _initializes;

   // 释放集合
   protected XAopReleaseCollection _releases;

   //============================================================
   // <T>构造AOP组件。</T>
   //============================================================
   public XAopComponent(){
   }

   //============================================================
   // <T>获得主键。</T>
   //
   // @return 主键
   //============================================================
   @Override
   public String key(){
      return _face;
   }

   //============================================================
   // <T>获得名称。</T>
   //
   // @return 名称
   //============================================================
   public String name(){
      return _name;
   }

   //============================================================
   // <T>获得是否含有接口。</T>
   //
   // @return 是否含有接口
   //============================================================
   public boolean hasFace(){
      return !RString.isEmpty(_face);
   }

   //============================================================
   // <T>获得接口。</T>
   //
   // @return 接口
   //============================================================
   public String face(){
      return _face;
   }

   //============================================================
   // <T>设置接口。</T>
   //
   // @param face 接口
   //============================================================
   public void setFace(String face){
      _face = face;
   }

   //============================================================
   // <T>获得类名称。</T>
   //
   // @return 类名称
   //============================================================
   public String className(){
      return _className;
   }

   //============================================================
   // <T>设置类名称。</T>
   //
   // @param className 类名称
   //============================================================
   public void setClassName(String className){
      _className = className;
   }

   //============================================================
   // <T>获得接口类对象。</T>
   //
   // @return 接口类对象
   //============================================================
   public FClass<?> faceClass(){
      return _faceClass;
   }

   //============================================================
   // <T>设置接口类对象。</T>
   //
   // @param clazz 接口类对象
   //============================================================
   public void setFaceClass(FClass<?> clazz){
      _faceClass = clazz;
   }

   //============================================================
   // <T>判断是否指定范围。</T>
   //
   // @param scope 范围
   //============================================================
   public boolean isScope(EComponentScope scope){
      return (_scopeCd == scope);
   }

   //============================================================
   // <T>获得范围。</T>
   //
   // @return 范围
   //============================================================
   public EComponentScope scopeCd(){
      return _scopeCd;
   }

   //============================================================
   // <T>设置范围。</T>
   //
   // @param scope 范围
   //============================================================
   public void setScopeCd(EComponentScope scope){
      _scopeCd = scope;
   }

   //============================================================
   // <T>获得部署。</T>
   //
   // @return 部署
   //============================================================
   public EComponentDeploy deploy(){
      return _deploy;
   }

   //============================================================
   // <T>设置部署。</T>
   //
   // @param deploy 部署
   //============================================================
   public void deploy(EComponentDeploy deploy){
      _deploy = deploy;
   }

   //============================================================
   // <T>获得描述器。</T>
   //
   // @return 描述器
   //============================================================
   public IAopDescriptor descriptor(){
      return _descriptor;
   }

   //============================================================
   // <T>获得描述器。</T>
   //
   // @return 描述器
   //============================================================
   public void setDescriptor(IAopDescriptor descriptor){
      _descriptor = descriptor;
   }

   //============================================================
   // <T>判断是否含有属性。</T>
   //
   // @return 是否含有属性
   //============================================================
   public boolean hasProperties(){
      return (null != _properties) ? !_properties.isEmpty() : false;
   }

   //============================================================
   // <T>获得属性集合。</T>
   //
   // @return 属性集合
   //============================================================
   public XAopPropertyCollection properties(){
      if(null == _properties){
         _properties = new XAopPropertyCollection();
      }
      return _properties;
   }

   //============================================================
   // <T>判断是否含有初始化集合。</T>
   //
   // @return 是否含有初始化集合
   //============================================================
   public boolean hasInitializes(){
      return (null != _initializes) ? !_initializes.isEmpty() : false;
   }

   //============================================================
   // <T>获得初始化集合。</T>
   //
   // @return 初始化集合
   //============================================================
   public XAopInitializeCollection initializeMethods(){
      if(null == _initializes){
         _initializes = new XAopInitializeCollection();
      }
      return _initializes;
   }

   //============================================================
   // <T>判断是否含有释放集合。</T>
   //
   // @return 是否含有释放集合
   //============================================================
   public boolean hasReleases(){
      return (null != _releases) ? !_releases.isEmpty() : false;
   }

   //============================================================
   // <T>获得释放集合。</T>
   //
   // @return 释放集合
   //============================================================
   public XAopReleaseCollection releaseMethods(){
      if(null == _releases){
         _releases = new XAopReleaseCollection();
      }
      return _releases;
   }

   //============================================================
   // <T>生成节点内容。</T>
   //
   // @param xconfig 配置节点
   // @return 节点内容
   //============================================================
   protected Object makeNodeValue(FXmlNode xconfig){
      if(xconfig.hasNode()){
         for(FXmlNode node : xconfig.nodes()){
            if(node.isName(TAG_NAME)){
               XAopComponent component = new XAopComponent();
               component.loadConfig(node);
               return component;
            }
         }
      }
      return xconfig.text();
   }

   //============================================================
   // <T>接收对象内容。</T>
   //
   // @param component 对象内容
   //============================================================
   public void assign(XAopComponent component){
      _name = component._name;
      _className = component._className;
      _face = component._face;
      _faceClass = component._faceClass;
      _deploy = component._deploy;
      _scopeCd = component._scopeCd;
      _descriptor = component._descriptor;
      _properties = component._properties;
      _initializes = component._initializes;
      _releases = component._releases;
   }

   //============================================================
   // <T>加载设置信息。</T>
   //
   // @param xconfig 配置节点
   //============================================================
   @Override
   public void loadConfig(FXmlNode xconfig){
      loadConfig(xconfig, true);
   }

   //============================================================
   // <T>加载设置信息。</T>
   //
   // @param xconfig 配置节点
   // @param all 全部加载
   //============================================================
   public void loadConfig(FXmlNode xconfig,
                          boolean all){
      super.loadConfig(xconfig);
      try{
         _config = xconfig;
         _name = RString.trim(xconfig.getNvl(PTY_NAME));
         _face = RString.trim(xconfig.getNvl(PTY_FACE));
         if(!RString.isEmpty(_face) && all){
            _faceClass = RClass.find(_face);
         }
         _className = RString.trim(xconfig.get(PTY_CLASS));
         _deploy = EComponentDeploy.parse(xconfig.getNvl(PTY_DEPLOY));
         _scopeCd = EComponentScope.parse(xconfig.getNvl(PTY_SCOPE));
         if(xconfig.hasNode()){
            for(FXmlNode node : xconfig.nodes()){
               if(node.isName(XAopProperty.TAG_NAME)){
                  // 新建属性
                  XAopProperty property = new XAopProperty();
                  property.loadConfig(node);
                  properties().push(property);
               }else if(node.isName(XAopInitialize.TAG_NAME)){
                  // 新建初始化
                  XAopInitialize initialize = new XAopInitialize();
                  initialize.loadConfig(node);
                  initializeMethods().push(initialize);
               }else if(node.isName(XAopRelease.TAG_NAME)){
                  // 新建释放
                  XAopRelease release = new XAopRelease();
                  release.loadConfig(node);
                  releaseMethods().push(release);
               }
            }
         }
      }catch(Throwable e){
         throw new FFatalError(e, "Load config failure. (config={1})", xconfig);
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
      info.appendLine("Name:       " + _name);
      info.appendLine("Class:      " + _className);
      info.appendLine("Face:       " + _face);
      info.appendLine("Face class: " + _faceClass);
      info.appendLine("Scope:      " + _scopeCd);
      if(_properties != null){
         info.appendLine("Property:   ");
         //_properties.dump(info);
      }
      if(_initializes != null){
         info.appendLine("Initialize: ");
         // _initializes.dump()
      }
      if(_releases != null){
         info.appendLine("Release:    ");
         // _releases.dump()
      }
      if(_config != null){
         info.appendLine(RDump.LINE_L2);
         info.appendLine(_config.dump());
      }
      return info;
   }
}
