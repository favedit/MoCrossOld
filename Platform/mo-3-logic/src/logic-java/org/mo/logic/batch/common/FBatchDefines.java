package org.mo.logic.batch.common;

import org.mo.com.lang.FAttributes;
import org.mo.com.lang.FObjects;
import org.mo.com.lang.IAttributes;
import org.mo.com.lang.RString;
import org.mo.com.lang.temp.RPack;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.com.xml.FXmlNode;
import org.mo.logic.batch.IBatchConsole;

/**
 * <T>批处理变量定义列表。</T>
 * 
 * @history 091223 YJHUA 创建
 */
public class FBatchDefines
      extends FObjects<FBatchDefine>
{
   protected IBatchConsole _console;

   private final IAttributes _defines = new FAttributes();

   private static ILogger _logger = RLogger.find(FBatchDefine.class);

   /**
    * <T>构造函数</T>
    * 
    * @param console 批处理控制台
    */
   public FBatchDefines(IBatchConsole console){
      _console = console;
   }

   /**
    * <T>获得第一列表</T>
    * 
    * @return IAttributes类型
    */
   public IAttributes getDefine(){
      if(_defines.isEmpty()){
         if(!isEmpty()){
            for(FBatchDefine define : this){
               _defines.set(define.name(), define.value());
            }
         }
      }
      return _defines;
   }

   /**
    * <T>根据定义名称获得定义</T>
    * 
    * @param name 定义名称
    */
   public FBatchDefine getDefine(String name){
      // 根据定义名称查找定义
      if(!isEmpty()){
         for(FBatchDefine define : this){
            if(name.equals(define.name())){
               _logger.debug(this, "getDefine", "Found define Success.[name={0}]", name);
               return define;
            }
         }
      }
      return null;
   }

   /**
    * <T>根据定义名称获得定义的内容</T>
    * 
    * @param name 定义名称
    */
   public String getDefineValue(String name){
      if(!isEmpty()){
         // 根据定义名称查找定义
         for(FBatchDefine define : this){
            if(name.equals(define.name())){
               _logger.debug(this, "getDefine", "Found define's value Success.[name={0}]", name);
               return define.value();
            }
         }
      }
      return null;
   }

   /**
    * <T>往定义列表中插入定义</T>
    * <P>根据定义名称查找定义列表中是否存在，如果存在更新，否则插入</P>
    * 
    * @param define 定义对象
    */
   @Override
   public void push(FBatchDefine define){
      FBatchDefine oldDefine = getDefine(define.name());
      if(null != oldDefine){
         // 2010年注掉（原因是以父的定义为准）
         //oldDefine.setValue(define.value());
         //_defines.set(define.name(), define.value());
         return;
      }
      super.push(define);
      _defines.set(define.name(), define.value());
   }

   /**
    * <T>把传进的xml节点的变量替换成事先定义好的定义的内容</T>
    * <P>只替换当前xml节点</P>
    * <P>变量格式${变量名称}</P>
    * <P>替换过程：如果${变量名称}的变量名称和定义名称相同则替换成定义内容</P>
    * 
    * @param config xml节点对象
    */
   public void replace(FXmlNode config){
      // 先替换节点属性
      boolean hasReplace = false;
      if(config.hasAttribute()){
         IAttributes attributes = config.attributes();
         IAttributes newAttributes = config.attributes();
         for(int n = 0; n < attributes.count(); n++){
            String name = attributes.name(n);
            String value = attributes.value(n);
            // 替换name里的变量
            String newName = RPack.replaceLink(name, _defines).toString();
            if(!name.equals(newName)){
               hasReplace = true;
               name = newName;
            }
            // 替换value里的变量
            String newValue = RPack.replaceLink(value, _defines).toString();
            if(!value.equals(newValue)){
               hasReplace = true;
               value = newValue;
            }
            // 设置新的xml节点属性
            newAttributes.set(name, value);
         }
         if(hasReplace){
            // xml节点属性修改成已经替换过的属性
            attributes = newAttributes;
         }
      }
      // 替换xml节点的文本内容
      String nodeText = config.text();
      if(RString.isNotEmpty(nodeText)){
         nodeText = RPack.replaceLink(nodeText, _defines).toString();
         config.setText(nodeText);
      }
      _logger.debug(this, "setDefineValue", "Replace Success[node={0}].", config.get("name"));
   }

   /**
    * <T>把传进的xml节点的变量替换成事先定义好的定义的内容</T>
    * <P>替换当前xml节点以及该节点的所有子节点</P>
    * <P>变量格式${变量名称}</P>
    * <P>替换过程：如果${变量名称}的变量名称和定义名称相同则替换成定义内容</P>
    * 
    * @param config xml节点对象
    */
   public void replaceAll(FXmlNode config){
      if(!_defines.isEmpty()){
         // 先替换当前节点
         replace(config);
         for(FXmlNode node : config){
            replaceAll(node);
         }
      }
      _logger.debug(this, "setDefineValue", "Replace all node Success.");
   }

   /**
    * <T>根据定义名称设置定义的内容</T>
    * <P>如果根据定义名称没有查找到定义则新建定义</P>
    * 
    * @param name 定义名称
    * @param value 定义内容
    */
   public void setDefineValue(String name,
                              String value){
      // 根据定义名称查找定义,如果查找到则更新值，否则新建定义
      boolean exist = false;
      if(!isEmpty()){
         for(FBatchDefine define : this){
            if(name.equals(define.name())){
               exist = true;
               define.setValue(value);
               return;
            }
         }
      }
      if(!exist){
         FBatchDefine define = new FBatchDefine(_console);
         define.setName(name);
         define.setValue(value);
         push(define);
      }
      _logger.debug(this, "setDefineValue", "Set define value Success.[name={0}]", name);
   }
}
