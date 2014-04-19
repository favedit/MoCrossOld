package org.mo.mime.csv;

import java.util.Iterator;
import org.mo.com.lang.FAttributes;
import org.mo.com.lang.FFatalError;
import org.mo.com.lang.FObject;
import org.mo.com.lang.FStrings;
import org.mo.com.lang.IMap;
import org.mo.com.lang.IStringPair;
import org.mo.com.lang.RString;
import org.mo.com.lang.generic.TDumpInfo;

//============================================================
// <T>CSV行对象。</T>
//============================================================
public class FCsvLine
      extends FObject
      implements
         ICsvLine
{
   // 容器
   protected ICsvContainer _container;

   // 内容集合
   protected FStrings _values = new FStrings();

   //============================================================
   // <T>构造CSV行对象。</T>
   //============================================================
   protected FCsvLine(){
   }

   @Override
   public void append(IMap<String, String> map){
      int count = map.count();
      for(int n = 0; n < count; n++){
         set(map.name(n), map.value(n));
      }
   }

   @Override
   public void assign(IMap<String, String> map){
      clear();
      append(map);
   }

   @Override
   public void clear(){
      _values.clear();
   }

   @Override
   public boolean contains(String name){
      return _container.columns().contains(name);
   }

   @Override
   public int count(){
      return _values.count();
   }

   @Override
   public String get(String name){
      int index = _container.columns().indexOf(name);
      return RString.nvl(_values.get(index));
   }

   @Override
   public String get(String name,
                     String defaultValue){
      int index = _container.columns().indexOf(name);
      return RString.nvl(_values.get(index), defaultValue);
   }

   @Override
   public boolean isEmpty(){
      return _values.isEmpty();
   }

   @Override
   public Iterator<IStringPair> iterator(){
      FAttributes values = new FAttributes();
      int count = count();
      for(int n = 0; n < count; n++){
         values.set(name(n), value(n));
      }
      return values.iterator();
   }

   @Override
   public String name(int index){
      return _container.columns().name(index);
   }

   public void push(String value){
      _values.push(value);
   }

   @Override
   public String remove(String name){
      String result = null;
      int index = _container.columns().indexOf(name);
      if(index != -1){
         result = _values.erase(index);
      }
      return result;
   }

   //============================================================
   // <T>设置属性内容。</T>
   //
   // @param name 名称
   // @param value 内容
   //============================================================
   @Override
   public void set(String name,
                   String value){
      int index = _container.columns().indexOf(name);
      if(-1 == index){
         throw new FFatalError("Unknown csv column name. (name={1})", name);
      }
      _values.ensureSet(index, value);
   }

   //============================================================
   // <T>分割字符串为属性集合。</T>
   //
   // @param value 字符串
   //============================================================
   @Override
   public void split(String value){
      split(value, "=", ",", false);
   }

   //============================================================
   // <T>分割字符串为属性集合。</T>
   //
   // @param value 字符串
   // @param splitValue 内容分割符
   //============================================================
   @Override
   public void split(String value,
                     String splitValue){
      split(value, "=", splitValue, false);
   }

   //============================================================
   // <T>分割字符串为属性集合。</T>
   //
   // @param value 字符串
   // @param splitName 名称分割符
   // @param splitValue 内容分割符
   //============================================================
   @Override
   public void split(String value,
                     String splitName,
                     String splitValue){
      split(value, splitName, splitValue, false);
   }

   //============================================================
   // <T>分割字符串为属性集合。</T>
   //
   // @param value 字符串
   // @param splitName 名称分割符
   // @param splitValue 内容分割符
   // @param merge 是否合并
   //============================================================
   @Override
   public void split(String value,
                     char splitName,
                     char splitValue,
                     boolean merge){
      if(!merge){
         clear();
      }
      if(!RString.isEmpty(value)){
         String[] values = RString.split(value, splitValue);
         for(int n = 0; n < values.length; n++){
            String[] items = RString.splitTwo(values[n], splitName);
            if(null != items){
               set(items[0], items[1]);
            }
         }
      }
   }

   //============================================================
   // <T>分割信息字符串成为集合。</T>
   //
   // @param source 字符串
   // @param splitName 名称分割字符串
   // @param splitValue 内容分割字符串
   // @param merge 是否合并
   //============================================================
   @Override
   public void split(String source,
                     String splitName,
                     String splitValue,
                     boolean merge){
      if(!merge){
         clear();
      }
      if(!RString.isEmpty(source)){
         String[] values = RString.split(source, splitValue);
         if(!RString.isEmpty(splitName)){
            String[] items = null;
            for(int n = 0; n < values.length; n++){
               items = values[n].split(splitName, 2);
               if(items.length == 2){
                  set(items[0], items[1]);
               }
            }
         }else{
            for(int n = 0; n < values.length; n++){
               set(Integer.toString(_values.count()), values[n]);
            }
         }
      }
   }

   //============================================================
   // <T>生成打包字符串。</T>
   //
   // @return 打包字符串
   //============================================================
   @Override
   public String pack(){
      FAttributes values = new FAttributes();
      FCsvColumns columns = _container.columns();
      int count = columns.count();
      for(int n = 0; n < count; n++){
         FCsvColumn column = columns.value(n);
         if(column.isValid()){
            values.set(column.dataName(), _values.get(n));
         }
      }
      return values.pack();
   }

   //============================================================
   // <T>解包字符串。</T>
   //
   // @param pack 打包字符串
   //============================================================
   @Override
   public void unpack(String pack){
      FAttributes values = new FAttributes();
      values.unpack(pack);
      for(IStringPair pair : values){
         set(pair.name(), pair.value());
      }
   }

   //============================================================
   // <T>解包字符串。</T>
   //
   // @param pack 打包字符串
   // @param merge 合并内容
   //============================================================
   @Override
   public void unpack(String pack,
                      boolean merge){
      FAttributes values = new FAttributes();
      values.unpack(pack, merge);
      for(IStringPair pair : values){
         set(pair.name(), pair.value());
      }
   }

   public String[] toObjects(){
      return _values.toObjects();
   }

   @Override
   public String value(int index){
      return RString.nvl(_values.get(index));
   }

   //============================================================
   // <T>生成运行信息。</T>
   //
   // @param info 运行信息
   // @return 运行信息
   //============================================================
   @Override
   public TDumpInfo dump(TDumpInfo info){
      return _values.dump(info);
   }
}
