package org.mo.core.aop.config;

import org.mo.com.lang.RString;
import org.mo.com.lang.reflect.RClass;
import org.mo.com.xml.FXmlNode;

//============================================================
// <T>AOP关系。</T>
//============================================================
public class XAopRelation
      extends XAbsAopNode
{
   // 标签名称
   public static final String TAG_NAME = "Relation";

   // 属性类型
   public static final String PTY_TYPE = "type";

   // 路径
   protected String _path;

   // 关系类型
   protected ERelationType _typeCd = ERelationType.None;

   //============================================================
   // <T>构造AOP关系。</T>
   //============================================================
   public XAopRelation(){
   }

   //============================================================
   // <T>获得主键。</T>
   //
   // @return 主键
   //============================================================
   @Override
   public String key(){
      return _path;
   }

   //============================================================
   // <T>获得路径。</T>
   //
   // @return 路径
   //============================================================
   public String path(){
      return _path;
   }

   //============================================================
   // <T>设置路径。</T>
   //
   // @param path 路径
   //============================================================
   public void setPath(String path){
      _path = path;
   }

   //============================================================
   // <T>获得类型。</T>
   //
   // @return 类型
   //============================================================
   public ERelationType typeCd(){
      return _typeCd;
   }

   //============================================================
   // <T>设置路径。</T>
   //
   // @param typeCd 路径
   //============================================================
   public void setTypeCd(ERelationType typeCd){
      _typeCd = typeCd;
   }

   //============================================================
   // <T>加载配置节点信息。</T>
   //
   // @param xconfig 配置节点
   //============================================================
   @Override
   public void loadConfig(FXmlNode xconfig){
      super.loadConfig(xconfig);
      // 读取属性
      String typeCd = xconfig.get(PTY_TYPE);
      if(typeCd.equals("class.path")){
         _typeCd = ERelationType.ClassPath;
      }
      _path = RString.trim(xconfig.text());
      // 包含类型
      if(ERelationType.ClassPath == _typeCd){
         RClass.appendClassPath(_path);
      }
   }
}
