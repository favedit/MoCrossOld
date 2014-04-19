package org.mo.core.aop.config;

import org.mo.com.lang.reflect.RClass;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.com.regex.FPattern;
import org.mo.com.xml.FXmlNode;

//============================================================
// <T>AOP组件集合。</T>
//============================================================
public class XAopComponents
      extends XAbsAopNode
{
   // 日志输出接口
   private static ILogger _logger = RLogger.find(XAopComponents.class);

   // 标签名称
   public final static String TAG_NAME = "Components";

   // 类名称
   public final static String PTY_CLASS = "class";

   // 接口名称
   public final static String PTY_FACE = "face";

   // 类名称
   protected String _className;

   // 接口名称
   private String _face;

   // 匹配器
   private FPattern _pattern;

   //============================================================
   // <T>构造AOP组件集合。</T>
   //============================================================
   public XAopComponents(){
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
   // <T>是否含有匹配接口的对象。</T>
   //
   // @return 是否含有
   //============================================================
   public boolean match(String face){
      return _pattern.matches(face);
   }

   //============================================================
   // <T>根据接口名称获得匹配的类名。</T>
   //
   // @param face 接口名称
   // @return 类名称
   //============================================================
   public String matchClassName(String face){
      String match = null;
      String[] names = _face.split("\\*");
      String[] values = _className.split("\\*");
      if(names.length == values.length){
         int nameCount = names.length;
         if(3 == nameCount){
            int find = face.lastIndexOf(names[1]);
            String first = face.substring(names[0].length(), find);
            String end = face.substring(find + names[1].length(), face.length() - names[2].length());
            match = values[0] + first + values[1] + end + values[2];
            _logger.debug(this, "matchClassName", "Match class face [{1}]->[{2}]", face, match);
         }else if(2 == nameCount){
            int find = face.lastIndexOf(names[1]);
            String sub = face.substring(names[0].length(), find);
            match = values[0] + sub + values[1];
            if(_face.endsWith("*")){
               match += face.substring(find + names[1].length());
            }
            _logger.debug(this, "matchClassName", "Match class face [{1}]->[{2}]", face, match);
         }
      }
      return match;
   }

   //============================================================
   // <T>创建接口关联的组件配置。</T>
   //
   // @param face 接口名称
   // @return 组件配置
   //============================================================
   public XAopComponent createComponent(String face){
      // 创建组件配置
      XAopComponent xcomponent = new XAopComponent();
      xcomponent.loadConfig(_config, false);
      xcomponent.setFace(face);
      xcomponent.setFaceClass(RClass.find(face));
      xcomponent.setClassName(matchClassName(face));
      return xcomponent;
   }

   //============================================================
   // <T>加载配置节点信息。</T>
   //
   // @param xconfig 配置节点
   //============================================================
   @Override
   public void loadConfig(FXmlNode config){
      super.loadConfig(config);
      _face = config.get(PTY_FACE);
      _className = config.get(PTY_CLASS);
      _pattern = new FPattern(key());
   }

   //============================================================
   // <T>获得字符串内容。</T>
   //
   // @return 字符串内容
   //============================================================
   @Override
   public String toString(){
      return _face + "[" + _className + "]";
   }
}
