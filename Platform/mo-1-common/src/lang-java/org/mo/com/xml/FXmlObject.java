package org.mo.com.xml;

import org.mo.com.lang.IAttributes;
import org.mo.com.lang.RString;
import org.mo.com.lang.RUuid;

//============================================================
// <T>XML对象的基类。</T>
//
// @author MAOCY
// @version 1.00 - 2008/12/08
//============================================================
public abstract class FXmlObject
      implements
         IXmlObject
{
   // 唯一标识
   protected String _objectId;

   // 子节点列表
   protected IXmlObjects _children;

   // 节点内容
   protected String _innerText;

   //============================================================
   // <T>构造XML对象的基类。</T>
   //============================================================
   public FXmlObject(){
   }

   //============================================================
   // <T>获得对象编号。</T>
   //
   // @param 对象编号
   //============================================================
   @Override
   public String objectId(){
      if(_objectId == null){
         _objectId = RUuid.makeUuid();
      }
      return _objectId;
   }

   //============================================================
   // <T>设置对象编号。</T>
   //
   // @param objectId 对象编号
   //============================================================
   public void setObjectId(String objectId){
      if(!RString.isEmpty(objectId)){
         _objectId = objectId;
      }
   }

   //============================================================
   // <T>判断当前节点下是否含有子节点信息。</T>
   //
   // @return 是否含有子节点
   //============================================================
   @Override
   public boolean hasChild(){
      return (null != _children) ? (_children.count() > 0) : false;
   }

   //============================================================
   // <T>获得子节点集合。</T>
   //
   // @param 子节点集合
   //============================================================
   @Override
   public IXmlObjects children(){
      if(null == _children){
         _children = new FXmlObjects();
      }
      return _children;
   }

   //============================================================
   // <T>查找当前节点下的子节点信息。</T>
   // <P>参数需要成对使用，第一个参数是属性名称，第二个参数是属性内容。</P>
   // <P>如果没有找到则返回空。</P>
   //
   // @param values 参数集合
   // @return 查找到的子节点
   //============================================================
   @Override
   public IXmlObject find(String... values){
      if(null != _children){
         int count = _children.count();
         for(int n = 0; n < count; n++){
            IXmlObject child = _children.get(n);
            if(null != child){
               if(isFindChild(child, values)){
                  return child;
               }
            }
         }
      }
      return null;
   }

   //============================================================
   // <T>获得当前节点下的节点内容。</T>
   //
   // @return 节点内容
   //============================================================
   @Override
   public String innerText(){
      return _innerText;
   }

   //============================================================
   // <T>设置当前节点下的节点内容。</T>
   //
   // @param innerText 节点内容
   //============================================================
   @Override
   public void setInnerText(String innerText){
      _innerText = innerText;
   }

   //============================================================
   // <T>根据当前唯一标识搜索父对象。</T>
   //
   // @param objectId 唯一标识
   // @return 父对象
   //============================================================
   @Override
   public IXmlObject searchParent(String objectId){
      if(null != objectId && null != _children){
         int count = _children.count();
         for(int n = 0; n < count; n++){
            IXmlObject object = _children.get(n);
            if(null != object){
               if(objectId.equals(object.objectId())){
                  return this;
               }
               if(object.hasChild()){
                  IXmlObject result = object.searchParent(objectId);
                  if(null != result){
                     return result;
                  }
               }
            }
         }
      }
      return null;
   }

   //============================================================
   // <T>从当前的节点下搜索所有子节点中指定唯一标识的节点对象。</T>
   //
   // @param objectId 唯一标识
   // @return 节点对象
   //============================================================
   @Override
   public IXmlObject search(String objectId){
      if(null != objectId){
         if(objectId.equals(objectId())){
            return this;
         }else if(null != _children){
            return _children.search(objectId);
         }
      }
      return null;
   }

   //============================================================
   // <T>从当前的节点下搜索所有子节点中指定属性名称和内容的节点对象。</T>
   //
   // @param name 属性名称
   // @param value 属性内容
   // @return 节点对象
   //============================================================
   @Override
   public IXmlObject search(String name,
                            String value){
      return (null != name && null != value) ? search(this, name, value) : null;
   }

   //============================================================
   // <T>从当前的节点下搜索路径。</T>
   //
   // @param name 属性名称
   // @param path 路径
   // @param split 分割符
   // @return 节点对象
   //============================================================
   @Override
   public IXmlObject searchPath(String name,
                                String path,
                                char split){
      return null;
   }

   //============================================================
   // <T>从XML节点内加载设置信息。</T>
   //
   // @param xconfig XML节点信息
   // @param typeCd 节点类型
   //============================================================
   @Override
   public void loadConfig(FXmlNode xconfig,
                          EXmlConfig typeCd){
      // 获得对象编号
      if(xconfig.contains(RXmlObject.OBJECT_UUID)){
         String objectId = xconfig.get(RXmlObject.OBJECT_UUID);
         if(!RString.isBlank(objectId)){
            _objectId = objectId;
         }
      }
      // 获得字符串
      if(!RString.isEmpty(xconfig.text())){
         _innerText = xconfig.text();
      }
   }

   //============================================================
   // <T>存储设置信息到XML节点内。</T>
   //
   // @param xconfig XML节点信息
   // @param typeCd 节点类型
   //============================================================
   @Override
   public void saveConfig(FXmlNode xconfig,
                          EXmlConfig typeCd){
      if(EXmlConfig.Full == typeCd || EXmlConfig.Value == typeCd){
         xconfig.set(RXmlObject.OBJECT_UUID, objectId());
         xconfig.set(RXmlObject.OBJECT_TYPE, name());
         xconfig.setText(_innerText);
      }
   }

   //============================================================
   // <T>判断是否查找节点。</T>
   //
   // @param xobject 节点
   // @param values 参数集合
   // @return 是否查找节点
   //============================================================
   protected final static boolean isFindChild(IXmlObject object,
                                              String[] values){
      int count = values.length;
      if(count == 0){
         return true;
      }else if(count == 1){
         return RString.equalsIgnoreCase(object.name(), values[0]);
      }else if(count % 2 == 0){
         for(int n = 0; n < count; n += 2){
            String value = object.innerGet(values[n]);
            if(!RString.equalsIgnoreCase(value, values[n + 1])){
               return false;
            }
         }
         return true;
      }else if(count % 2 == 1){
         if(!RString.equalsIgnoreCase(object.name(), values[0])){
            return false;
         }
         for(int n = 1; n < count; n += 2){
            String value = object.innerGet(values[n]);
            if(!RString.equalsIgnoreCase(value, values[n + 1])){
               return false;
            }
         }
         return true;
      }
      return false;
   }

   //============================================================
   // <T>搜索节点内容。</T>
   //
   // @param xsearch 搜索节点
   // @param searchName 搜索名称
   // @param searchValue 搜索内容
   // @return 节点
   //============================================================
   protected final static IXmlObject search(IXmlObject xsearch,
                                            String searchName,
                                            String searchValue){
      if((null != xsearch) && xsearch.hasChild()){
         for(IXmlObject xobject : xsearch.children()){
            // 查询搜索内容
            String value = xobject.innerGet(searchName);
            if(searchValue.equals(value)){
               return xobject;
            }
            // 递归搜索所有节点
            IXmlObject xresult = search(xobject, searchName, searchValue);
            if(null != xresult){
               return xresult;
            }
         }
      }
      return null;
   }

   //============================================================
   // <T>获得当前节点的XML节点。</T>
   //
   // @param typeCd XML节点类型
   // @return XML节点
   //============================================================
   @Override
   public FXmlNode toNode(EXmlConfig typeCd){
      FXmlNode node = new FXmlNode();
      saveConfig(node, typeCd);
      return node;
   }

   //============================================================
   // <T>获得当前节点的简单属性列表。</T>
   //
   // @return 属性列表
   //============================================================
   @Override
   public IAttributes toSimpleAttributes(){
      return toNode(EXmlConfig.Simple).attributes();
   }

   //============================================================
   // <T>获得当前节点的简单XML节点。</T>
   //
   // @return XML节点
   //============================================================
   @Override
   public FXmlNode toSimpleNode(){
      return toNode(EXmlConfig.Simple);
   }
}
