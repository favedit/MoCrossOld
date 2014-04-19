package org.mo.com.resource;

import java.io.InputStream;
import org.mo.com.lang.FFatalError;
import org.mo.com.lang.FMultiString;
import org.mo.com.lang.RBoolean;
import org.mo.com.lang.RInteger;
import org.mo.com.lang.RLong;
import org.mo.com.lang.RString;
import org.mo.com.lang.cultrue.RCulture;
import org.mo.com.lang.reflect.RClass;
import org.mo.com.xml.FXmlDocument;
import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.FXmlNodeDictionary;

//============================================================
// <T>资源对象。</T>
//============================================================
public class FResource
      implements
         IResource
{
   // 设置信息
   protected FXmlNode _config;

   // 设置节点表
   protected final FXmlNodeDictionary _configs = new FXmlNodeDictionary();

   // 类引用对象
   protected Class<?> _reference;

   //============================================================
   // <T>构造资源对象。</T>
   //============================================================
   protected FResource(){
   }

   //============================================================
   // <T>获得设置信息。</T>
   //
   // @return 设置信息
   //============================================================
   @Override
   public FXmlNode config(){
      return _config;
   }

   public void construct(Class<?> clazz){
      _reference = clazz;
      String name = RClass.shortName(clazz) + ".xml";
      InputStream input = clazz.getResourceAsStream(name);
      if(input == null){
         throw new FFatalError("Can't open resource stream {0}", name);
      }
      FXmlDocument doc = new FXmlDocument();
      doc.loadStream(input);
      _config = doc.root();
      for(FXmlNode node : doc.root().nodes()){
         if(node.isName("Resource")){
            _configs.set(node.get("name"), node);
         }
      }
   }

   @Override
   public Boolean findBoolean(String name){
      FXmlNode config = _configs.get(name);
      if(null != config){
         return RBoolean.parse(config.text());
      }
      return null;
   }

   @Override
   public Class<?> findClass(String name){
      FXmlNode config = _configs.get(name);
      if(null != config){
         return RClass.findClass(config.text());
      }
      return null;
   }

   @Override
   public FXmlNode findConfig(String name){
      return _configs.get(name);
   }

   @Override
   public Integer findInteger(String name){
      FXmlNode config = _configs.get(name);
      if(null != config){
         return RInteger.parse(config.text());
      }
      return null;
   }

   @Override
   public String findDisplay(String name){
      return findString(name, RCulture.currentLanguage());
   }

   @Override
   public Long findLong(String name){
      FXmlNode config = _configs.get(name);
      if(null != config){
         return RLong.parse(config.text());
      }
      return null;
   }

   @Override
   public FMultiString findMultiString(String name){
      FMultiString result = null;
      FXmlNode config = _configs.get(name);
      if(null != config){
         result = new FMultiString();
         result.unpack(config.text());
      }
      return result;
   }

   @Override
   public String findMultiString(String name,
                                 String language){
      FXmlNode config = _configs.get(name);
      if(null != config){
         FMultiString result = new FMultiString();
         result.unpack(config.text());
         result.get(language);
      }
      return null;
   }

   @Override
   public String findString(String name){
      FXmlNode config = _configs.get(name);
      if(null != config){
         return config.text();
      }
      return null;
   }

   @Override
   public String findString(String name,
                            String language){
      FXmlNode config = _configs.get(name);
      if(null != config){
         String value = config.nodeText(language);
         if(RString.isEmpty(value)){
            return config.nodeText(RCulture.currentLanguage());
         }
         return value;
      }
      return null;
   }

   @Override
   public Class<?> reference(){
      return _reference;
   }
}
