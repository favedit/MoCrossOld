package org.mo.web.core.container;

import org.mo.com.collections.FObjectDictionary;
import org.mo.com.collections.FObjectSet;
import org.mo.com.lang.FFatalError;
import org.mo.com.lang.RHex;
import org.mo.com.lang.RString;
import org.mo.com.lang.reflect.RClass;

public class FWebContainerDefine
{
   private FObjectDictionary _defines = new FObjectDictionary();

   @SuppressWarnings("unused")
   private FObjectSet _hashs = new FObjectSet();

   public String buildName(String source){
      return source;
   }

   public String buildName(String source,
                           String name){
      Object item = find(source);
      return buildObjectName(item, name);
   }

   public String buildObjectName(Object item,
                                 String name){
      return "_" + RHex.format(item.hashCode()) + "_" + name;
   }

   /**
    * <p>检查是否定义了当前别称</p>
    * <p>create date:2005/02/18</p>
    * 
    * @param sAlias 别称
    * @return true：是<br>false：否
    */
   public boolean contains(String alias){
      return _defines.contains(alias);
   }

   //   public Object[] parseArray(String source){
   //      if(source != null){
   //         int start = source.indexOf("&{");
   //         if(start != -1){
   //            int end = source.indexOf('}', start);
   //            if(end != -1){
   //               Object value = _defines.value(source.substring(start + 1, end));
   //               if(value instanceof String){
   //                  source = source.substring(0, start) + "&" + (String) value
   //                        + source.substring(end + 1);
   //                  return parseArray(source);
   //               }
   //               return null;
   //            }
   //         }
   //         if(source.startsWith("&")){
   //            source = source.substring(1).toLowerCase();
   //            String[] items = FStringUtil.split(source, '.');
   //            if(items.length == 2){
   //               Object value = _defines.value(items[0]);
   //               if(value != null){
   //                  value = convertConsole().findDescriptor(value).value(value, items[1]);
   //                  if(value != null){
   //                     return convertConsole().convetToArray(value);
   //                  }
   //               }
   //            }else{
   //               Object value = _defines.value(source);
   //               if(value != null){
   //                  return convertConsole().convetToArray(value);
   //               }
   //            }
   //         }
   //      }
   //      return null;
   //   }

   public void define(String alias,
                      Object item){
      _defines.set(alias.toLowerCase(), item);
      //_hashs.set(item.hashCode(), item);
   }

   // &{&{value.&{class}}.&{class2}}.name
   // &{item}.name
   // &{&{item}}.name
   public Object find(String source){
      if(source == null){
         return null;
      }
      int start = source.lastIndexOf("&{");
      if(start != -1){
         int end = source.indexOf('}', start);
         if(end == -1){
            throw new FFatalError("Invalid source[{0}].", source);
         }
         String sub = source.substring(start + 1, end).toLowerCase();
         Object value = _defines.get(sub);
         if(value instanceof String){
            source = source.substring(0, start) + value + source.substring(end + 1);
            return find(source);
         }
         return value;
      }
      source = source.substring(1).toLowerCase();
      String[] items = RString.split(source, '.');
      if(items.length == 2){
         Object value = _defines.get(items[0]);
         if(value != null){
            //return convertConsole().findDescriptor(value).value(value, items[1]);
         }
      }else{
         return _defines.get(source);
      }
      return null;
   }

   //   public String sourceObjectName(Object oItem,
   //                                  String sName)
   //         {
   //      if(oItem instanceof FWebForm){
   //         return sName.toLowerCase();
   //      }else if(oItem instanceof IObjectId){
   //         FString sItemName = new FString();
   //         sItemName.append("_");
   //         sItemName.append(((IObjectId) oItem).objectId());
   //         sItemName.append("_");
   //         sItemName.append(sName);
   //         return sItemName.toString().toLowerCase();
   //      }
   //      return sName.toLowerCase();
   //   }
   //
   //   public String sourceName(String sItem)
   //         {
   //      return sourceName(sItem, null);
   //   }
   //
   //   public String sourceName(String sItem,
   //                            String sName)
   //         {
   //      String sLink = null;
   //      if(FStringUtil.isEmpty(sName)){
   //         if(FStringUtil.startsWith(sItem, "&")){
   //            sItem = sItem.toLowerCase().substring(1);
   //            String[] arItems = FStringUtil.split(sItem, '.');
   //            if(arItems.length == 2){
   //               sLink = arItems[0];
   //               sItem = arItems[1];
   //               //if (sItem.startsWith("{&") && sItem.endsWith("}")) {
   //               //this.sourceObject()
   //               //sItem = parseSource(sItem.substring(1, sItem.length() - 1));
   //               //}
   //            }else{
   //               sLink = sItem;
   //               sItem = "";
   //            }
   //         }
   //         if(_defines.containsName(sLink)){
   //            Object oValue = _defines.value(sLink);
   //            if(oValue instanceof FWebForm){
   //               return sItem.toLowerCase();
   //            }else if(oValue instanceof IObjectId){
   //               FString sItemName = new FString();
   //               sItemName.append("_");
   //               sItemName.append(((IObjectId) oValue).objectId());
   //               sItemName.append("_");
   //               sItemName.append(sItem);
   //               return sItemName.toString().toLowerCase();
   //            }
   //         }
   //      }
   //      return sName;
   //   }

   /**
    * <p>获得指定内容的相关信息</p>
    * <p>create date:2005/02/18</p>
    * 
    * @param sValue 内容
    * @return 相关信息
    */
   public String parserString(String source){
      if(source != null){
         int start = source.indexOf("&{");
         if(start != -1){
            int end = source.indexOf('}', start);
            if(end != -1){
               String midStr = source.substring(start + 2, end);
               String endStr = source.substring(end + 1);
               Object item = _defines.get(midStr);
               if(endStr.startsWith(".")){
                  item = RClass.invokeGet(item, "get" + endStr.substring(1));
               }
               return RString.toString(item);
            }
         }
         if(source.startsWith("trs:")){
            int index = source.lastIndexOf('|');
            return source.substring(index + 1);
         }
         if(source.startsWith("&")){
            source = source.toLowerCase().substring(1);
            String[] items = RString.split(source, '.');
            if(items.length == 2){
               Object item = _defines.get(items[0]);
               if(item != null){
                  item = RClass.invokeGet(item, items[1]);
                  return RString.toString(item);
               }
            }else{
               return RString.toString(_defines.get(source));
            }
         }
      }
      return source;
      //      if(FAliasManager.console().isAlias(source)){
      //         return FAliasManager.console().alias(source);
      //         //      } else if (FTranslateManager.console().isTranslate(sSource)) {
      //         //         // 如果只能分为两段，则使用页面ID重组信息
      //         //         char chSplitter = '|';
      //         //         int nCount = FStringUtil.count(sSource, chSplitter);
      //         //         if (nCount == 1) {
      //         //            String[] arPath = FStringUtil.split(sSource, chSplitter);
      //         //            FString sItem = new FString();
      //         //            sItem.append(arPath[0]);
      //         //            sItem.append("|");
      //         //            sItem.append(attribute(ITagEnvironment.MULTI_TEXT_ID));
      //         //            sItem.append("|");
      //         //            sItem.append(arPath[1]);
      //         //            sSource = sItem.toString();
      //         //         }
      //         //         return FTranslateManager.console().translate(sSource, m_sLanguage);
      //      }else if(FPropertyManager.console().isProperty(source)){
      //         return FPropertyManager.console().property(source);
      //      }
   }

   public void undefine(String alias){
      _defines.remove(alias);
   }

}
