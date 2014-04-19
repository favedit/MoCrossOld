package org.mo.core.aop.config;

import org.mo.com.lang.FFatalError;
import org.mo.com.lang.RString;
import org.mo.com.xml.FXmlNode;

//============================================================
// <T>AOP函数。</T>
//============================================================
public abstract class XAbsAopMethod
      extends XAbsAopNode
{
   // 属性名称
   public final static String PTY_NAME = "name";

   // 名称
   protected String _name;

   // 参数集合
   protected XAopParameterCollection _parameters;

   //============================================================
   // <T>构造AOP函数。</T>
   //============================================================
   public XAbsAopMethod(){
   }

   //============================================================
   // <T>获得主键。</T>
   //
   // @return 主键
   //============================================================
   @Override
   public String key(){
      return _name;
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
   // <T>设置名称。</T>
   //
   // @param name 名称
   //============================================================
   public void setName(String name){
      _name = name;
   }

   //============================================================
   // <T>是否含有参数。</T>
   //
   // @return 是否含有
   //============================================================
   public boolean hasParameters(){
      if(null == _parameters){
         return false;
      }
      return !_parameters.isEmpty();
   }

   //============================================================
   // <T>获得参数集合。</T>
   //
   // @return 参数集合
   //============================================================
   public XAopParameterCollection parameters(){
      if(null == _parameters){
         _parameters = new XAopParameterCollection();
      }
      return _parameters;
   }

   //============================================================
   // <T>加载配置节点信息。</T>
   //
   // @param xconfig 配置节点
   //============================================================
   @Override
   public void loadConfig(FXmlNode config){
      // 加载属性
      _name = RString.trim(config.get(PTY_NAME));
      if(RString.isEmpty(_name)){
         throw new FFatalError("Config node define error (name={1}) .\n{2}", _name, config.dump());
      }
      // 加载参数集合
      if(config.hasNode()){
         for(FXmlNode node : config.nodes()){
            if(node.isName(XAopParameter.TAG_NAME)){
               XAopParameter parameter = new XAopParameter();
               parameter.loadConfig(node);
               parameters().push(parameter);
            }
         }
      }
   }
}
