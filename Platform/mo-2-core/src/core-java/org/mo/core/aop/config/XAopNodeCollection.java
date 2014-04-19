package org.mo.core.aop.config;

import org.mo.com.collections.FObjectDictionary;
import org.mo.com.lang.FFatalError;
import org.mo.com.lang.FObjects;
import org.mo.com.lang.RString;
import org.mo.com.lang.RStringFormat;

//============================================================
// <T>AOP节点集合。</T>
//============================================================
public class XAopNodeCollection<T extends IAopNode>
      extends FObjects<T>
      implements
         IAopNodes<T>
{
   // 是否允许重复
   protected boolean _duplicate = false;

   // 主键集合
   protected FObjectDictionary _keys = new FObjectDictionary();

   //============================================================
   // <T>构造AOP节点集合。</T>
   //============================================================
   public XAopNodeCollection(Class<T> clazz){
      super(clazz);
   }

   //============================================================
   // <T>根据主键查找节点。</T>
   //
   // @param key 主键
   // @return 节点
   //============================================================
   @SuppressWarnings("unchecked")
   public T findByKey(String key){
      return (T)_keys.find(key);
   }

   //============================================================
   // <T>根据主键查找节点。</T>
   //
   // @param key 主键
   // @return 节点
   //============================================================
   @SuppressWarnings("unchecked")
   public T matchByKey(String key){
      // 正常匹配
      if(!RString.isEmpty(key)){
         T value = (T)_keys.find(key);
         if(null != value){
            return value;
         }
      }
      // 加入下划线匹配
      if(!RString.isEmpty(key)){
         String format = RStringFormat.formatLineWordsLower(key);
         T value = (T)_keys.find(format);
         if(null != value){
            return value;
         }
      }
      // 去掉下划线匹配
      if(!RString.isEmpty(key)){
         String format = RStringFormat.formatFirstLower(key);
         T value = (T)_keys.find(format);
         if(null != value){
            return value;
         }
      }
      return null;
   }

   //============================================================
   // <T>追加新节点。</T>
   //
   // @param node 节点
   //============================================================
   @Override
   public void push(T node){
      super.push(node);
      String key = node.key();
      if(!RString.isEmpty(key)){
         if(!_duplicate){
            if(_keys.contains(key)){
               throw new FFatalError("Duplicate aop node key. (key={1})\n{2}", key, node.dump());
            }
         }
         _keys.set(key, node);
      }
   }
}
