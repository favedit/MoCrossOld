package org.mo.eng.template;

import org.mo.com.collections.FObjectDictionary;
import org.mo.com.lang.EStringCase;
import org.mo.com.lang.FFatalError;
import org.mo.com.lang.FObject;
import org.mo.com.lang.FString;
import org.mo.com.lang.RString;
import org.mo.com.lang.reflect.RProperty;
import org.mo.com.xml.FXmlNode;

//============================================================
// <T>模板环境。</T>
//============================================================
public class FTemplateContext
      extends FObject
{
   // 解析器
   protected ITemplateParser _parser;

   // 定义字典
   protected FObjectDictionary _defines = new FObjectDictionary();

   // 下个编号
   protected int _nextId = 0;

   // 一次删除尾空格
   protected boolean _trimOnceFlag = false;

   // 输出缓冲
   protected FString _buffer = new FString();

   //============================================================
   // <T>构造模板环境。</T>
   //
   // @param parser 解析器
   //============================================================
   public FTemplateContext(ITemplateParser parser){
      _parser = parser;
      _defines.setNameCase(EStringCase.Lower);
   }

   //============================================================
   // <T>获得解析器。</T>
   //
   // @return 解析器
   //============================================================
   public ITemplateParser parser(){
      return _parser;
   }

   //============================================================
   // <T>获得输出缓冲。</T>
   //
   // @return 输出缓冲
   //============================================================
   public FString buffer(){
      return _buffer;
   }

   public void append(String value){
      if(_trimOnceFlag){
         value = RString.trimFirstEnter(value);
         _trimOnceFlag = false;
      }
      _buffer.append(value);
   }

   public void setTrimOnceFlag(){
      _trimOnceFlag = true;
   }

   public void trimLastLine(){
      char ch1 = _buffer.get(_buffer.length() - 1);
      char ch2 = _buffer.get(_buffer.length() - 2);
      if(ch2 == '\r' && ch1 == '\n'){
         _buffer.pop();
         _buffer.pop();
      }else if(ch1 == '\r' || ch1 == '\n'){
         _buffer.pop();
      }
   }

   public void define(String name,
                      Object value){
      _defines.set(name, value);
   }

   public String parseString(String source){
      Object item = parse(source);
      if(null != item){
         return item.toString();
      }
      return null;
   }

   protected Object parseXmlNode(Object item,
                                 String path){
      if(item instanceof FXmlNode){
         FXmlNode node = (FXmlNode)item;
         return node.findPath(path);
      }else{
         throw new FFatalError("Unknown xml instance. {0}", item);
      }
   }

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
            throw new FFatalError("Unknown xml type. {0}", value);
         }
      }else{
         throw new FFatalError("Unknown xml instance. {0}", item);
      }
   }

   // $config#Config:xml | $config#Config:text
   // {$config#Config}.value
   public Object parse(String source){
      // 如果源为空的情况
      if(source == null){
         return null;
      }
      try{
         // 分解 {$?}的情况
         int start = source.lastIndexOf("{$");
         if(-1 != start){
            int end = source.indexOf('}', start);
            if(-1 == end){
               throw new FFatalError("Invalid source (source={0}).", source);
            }
            // 获得内部定义的对象
            String subSource = source.substring(start + 1, end);
            Object item = parse(subSource);
            if(null == item){
               return item;
            }
            // 将对象转换为系统标识，重新取最终内容
            String tempId = "sys_tmp_" + _nextId++;
            _defines.set(tempId, item);
            source = source.substring(0, start) + tempId + source.substring(end + 1);
            return parse(source);
         }
         // 分解 $? 的情况，表示是变量内容
         if(source.startsWith("$")){
            source = source.substring(1);
            // 分解 $config#? 的情况
            String[] items = RString.splitTwo(source, '#', true);
            if(items != null){
               Object item = _defines.get(items[0]);
               if(item == null){
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
            // 分解 $config:? 的情况
            items = RString.splitTwo(source, ':', true);
            if(items != null){
               Object item = _defines.get(items[0]);
               if(item == null){
                  return null;
               }
               return parseXmlValue(item, items[1]);
            }
            // 分解 $config.? 的情况
            items = RString.splitTwo(source, '.', true);
            if(items != null){
               Object item = _defines.get(items[0], null);
               if(item == null){
                  throw new FFatalError("Parse object item is null. (source={1})", source);
               }
               return RProperty.get(item, items[1]);
            }
            // 分解 $config 的情况
            return _defines.get(source, null);
         }
         return source;
      }catch(Throwable e){
         throw new FFatalError(e, "Parse object failure. (source={1})", source);
      }
   }

   public void reset(){
      _buffer.clear();
      _defines.clear();
   }
}
