package org.mo.eng.template;

import org.mo.com.lang.FAttributes;
import org.mo.com.lang.FFatalError;
import org.mo.com.lang.FObject;
import org.mo.com.lang.FString;
import org.mo.com.lang.IAttributes;
import org.mo.com.lang.RString;
import org.mo.com.lang.reflect.RClass;
import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.parser.FXmlParser;
import org.mo.core.aop.RAop;
import org.mo.eng.template.tag.FTplTag;
import org.mo.eng.template.tag.FTplTags;
import org.mo.eng.template.tag.ITplTag;

//============================================================
// <T>模板解析器。</T>
//============================================================
public class FTemplateParser
      extends FObject
      implements
         ITemplateParser
{
   // 分割字符定义
   public final static String SPLITTER = " `~!@#$%^&*()-=\\+|<>[]{};':\",./?\t\r\n";

   // 标签对象前缀
   public final static String PRE_TAG = "jt";

   // 属性名称定义
   public final static String PTY_NAME = "name";

   // 标签定义
   public final static String TAG_DEFINE = "Define";

   // 变量定义
   public final static String TAG_VARIABLE = "Variable";

   // 标签代码
   public final static String TAG_SOURCE = "Source";

   // 模板名称
   private String _templateName;

   // 模板设置信息
   protected FXmlNode _template;

   // 模板控制台
   protected ITemplateConsole _console;

   // 模板环境
   protected FTemplateContext _context = new FTemplateContext(this);

   // 根节点的标签对象
   protected FTplTag _rootTag;

   // 根节点集合的标签对象
   protected FTplTags _rootTags = new FTplTags();;

   // 变量列表
   private IAttributes _variables;

   //============================================================
   // <T>构造模板解析器。</T>
   //============================================================
   public FTemplateParser(){
   }

   //============================================================
   // <T>构造模板解析器。</T>
   //
   // @param config 设置信息
   //============================================================
   public FTemplateParser(FXmlNode config){
      _console = RAop.find(ITemplateConsole.class);
      _template = config;
      build();
   }

   //============================================================
   // <T>获得模板控制台接口。</T>
   //
   // @return 模板控制台接口
   //============================================================
   @Override
   public ITemplateConsole console(){
      return _console;
   }

   //============================================================
   // <T>获得模板环境。</T>
   //
   // @return 模板环境
   //============================================================
   @Override
   public FTemplateContext context(){
      return _context;
   }

   //============================================================
   // <T>获得模板名称。</T>
   //
   // @return 模板名称
   //============================================================
   @Override
   public String templateName(){
      return _templateName;
   }

   //============================================================
   // <T>设置模板名称。</T>
   //
   // @param templateName 模板名称
   //============================================================
   public void setTemplateName(String templateName){
      _templateName = templateName;
   }

   //============================================================
   // <T>获得变量集合。</T>
   //
   // @return 变量集合
   //============================================================
   @Override
   public IAttributes variables(){
      if(null == _variables){
         _variables = new FAttributes();
      }
      return _variables;
   }

   //============================================================
   // <T>从设置节点中同步标签对象。</T>
   //
   // @param tag 标签对象
   // @param node 设置节点
   //============================================================
   protected void syncTagFromNode(ITplTag tag,
                                  FXmlNode node){
      // 设置一个节点信息
      if(tag instanceof FTplTag){
         ((FTplTag)tag).setText(node.text());
      }else{
         FAttributes attrs = node.attributes();
         int count = attrs.count();
         for(int n = 0; n < count; n++){
            String name = attrs.name(n);
            // 检查是否支持设置属性的函数
            boolean result = RClass.invokeSet(tag, "set" + name, attrs.value(n));
            if(!result){
               throw new FFatalError("Unknown tag property. (tag={1}, name={2})\n{3}", tag, name, node.dump());
            }
         }
      }
      // 循环处理子节点
      if(node.hasNode()){
         for(FXmlNode childNode : node.nodes()){
            ITplTag childTag = _console.createTag(childNode.name());
            syncTagFromNode(childTag, childNode);
            tag.push(childTag);
         }
      }
   }

   //============================================================
   // <T>从配置设置信息中建立全部定义信息。</T>
   //
   // @param config 设置信息
   //============================================================
   protected void buildDefine(FXmlNode config){
      String name = config.get("name");
      String value = config.get("value");
      _context.define(name, value);
   }

   //============================================================
   // <T>从XML设置信息中建立变量信息。</T>
   //
   // @param config 设置信息
   //============================================================
   protected void buildVariable(FXmlNode config){
      String name = config.get("name");
      String value = config.get("value");
      variables().set(name, value);
   }

   //============================================================
   // <T>从XML设置信息中建立节点。</T>
   //
   // @param config 设置信息
   //============================================================
   protected void buildSource(FXmlNode config){
      FString xml = new FString();
      xml.append(config.text());
      // 解析XML内容
      FXmlParser parser = new FXmlParser(PRE_TAG);
      parser.parse(xml);
      // 建立根节点
      FTplTag tag = new FTplTag();
      syncTagFromNode(tag, parser.root());
      tag.setText(null);
      // 设置根节点信息
      String name = config.get(PTY_NAME, null);
      if(!RString.isEmpty(name)){
         _rootTags.set(name, tag);
      }else{
         _rootTag = tag;
      }
   }

   //============================================================
   // <T>解析全部设置信息，建立内部缓冲。</T>
   //============================================================
   protected void build(){
      for(FXmlNode node : _template.nodes()){
         String name = node.name();
         if(TAG_DEFINE.equalsIgnoreCase(name)){
            // 建立定义
            buildDefine(node);
         }else if(TAG_VARIABLE.equalsIgnoreCase(name)){
            // 建议变量
            buildVariable(node);
         }else if(TAG_SOURCE.equalsIgnoreCase(name)){
            // 建立代码
            buildSource(node);
         }
      }
      // 设置默认模板
      if(null == _rootTag){
         _rootTag = _rootTags.value(0);
      }
   }

   //============================================================
   // <T>接收解析器内容。</T>
   //
   // @param parser 解析器
   //============================================================
   @Override
   public void attach(ITemplateParser parser){
      _context = parser.context();
   }

   //============================================================
   // <T>定义内容。</T>
   //
   // @param name 名称
   // @param value 内容
   //============================================================
   @Override
   public void define(String name,
                      Object value){
      _context.define(name, value);
   }

   //============================================================
   // <T>解析一个标签内的所有内容。</T>
   //
   // @param parent 父标签
   // @param index 索引位置
   // @param tag 当前标签
   //============================================================
   protected void parseTag(ITplTag parent,
                           int index,
                           ITplTag tag){
      try{
         // 设置环境内容
         tag.construct(_context);
         // 处理左换行符
         if(tag.nowrapLeft()){
            _context.trimLastLine();
         }
         if(tag.nowrapRight()){
            _context.setTrimOnceFlag();
         }
         // 处理开始部分
         int result = tag.onStart();
         if(ITplTag.INCLUDE == result){
            // 防止无限递归
            for(int i = 0; i < 10000; i++){
               int count = tag.count();
               for(int n = 0; n < count; n++){
                  ITplTag child = tag.child(n);
                  parseTag(tag, n, child);
               }
               // 处理循环部分
               result = tag.onLoop();
               if(ITplTag.CONTINUE != result){
                  break;
               }else{
                  // 处理循环部分的右换行符
                  if(tag.nowrapRight()){
                     _context.setTrimOnceFlag();
                  }
               }
            }
            // 处理结束部分
            tag.onEnd();
            // 处理结束部分的右换行符
            if(tag.nowrapRight()){
               _context.trimLastLine();
            }
         }
      }catch(Exception e){
         throw new FFatalError(e, "Parse tag errror. (tag={1})", tag.dump());
      }
   }

   //============================================================
   // <T>解析一个部分内的所有内容。</T>
   //
   // @param part 部分名称
   // @return 内容
   //============================================================
   @Override
   public FString parse(String part){
      try{
         // 获得模板名称
         String partName = part;
         if(part.contains("@")){
            partName = part.substring(0, part.indexOf('@'));
         }
         ITplTag tag = _rootTags.get(partName);
         if(null == tag){
            throw new FFatalError("Unknown part name. (template={1}, part={2})", _templateName, partName);
         }
         parseTag(null, -1, tag);
         return _context.buffer();
      }catch(Exception e){
         throw new FFatalError(e, "Parse error. (template={1})", _templateName);
      }
   }

   //============================================================
   // <T>解析默认部分内的所有内容。</T>
   //
   // @return 内容
   //============================================================
   @Override
   public FString parse(){
      try{
         parseTag(null, -1, _rootTag);
         return _context.buffer();
      }catch(Exception e){
         throw new FFatalError(e, "Parse error. (template={1})", _templateName);
      }
   }

   //============================================================
   // <T>重置处理。</T>
   //============================================================
   @Override
   public void reset(){
      _context.reset();
   }
}
