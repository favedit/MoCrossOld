package org.mo.com.lang.generic;

import java.util.Iterator;
import org.mo.com.lang.FFatalError;
import org.mo.com.lang.FString;
import org.mo.com.lang.IAttributes;
import org.mo.com.lang.IStringPair;
import org.mo.com.lang.IStringPairs;
import org.mo.com.lang.RDump;
import org.mo.com.lang.RString;
import org.mo.com.lang.RStrings;
import org.mo.com.lang.face.AObjects;

//============================================================
// <T>值对集合。</T>
//============================================================

@AObjects
public abstract class MStringPairs
      extends MNamePairs<IStringPair, String>
      implements
         IStringPairs,
         IAttributes
{
   //============================================================
   // <T>构造值对集合。</T>
   //============================================================
   public MStringPairs(){
      super(String.class);
   }

   //============================================================
   // <T>构造值对集合。</T>
   //
   // @param capacity 容量
   //============================================================
   public MStringPairs(int capacity){
      super(String.class, capacity);
   }

   //============================================================
   // <T>获得迭代器。</T>
   //
   // @return 迭代器
   //============================================================
   @Override
   public Iterator<IStringPair> iterator(){
      return new FPairIterator<IStringPair, String, String>(new FStringPair(), _names, _values, 0, _count);
   }

   //============================================================
   // <T>分割字符串为属性集合。</T>
   //
   // @param value 字符串
   //============================================================
   public void split(String value){
      split(value, "=", ",", false);
   }

   //============================================================
   // <T>分割字符串为属性集合。</T>
   //
   // @param value 字符串
   // @param splitValue 内容分割符
   //============================================================
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
               add(items[0], items[1]);
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
                  add(items[0], items[1]);
               }
            }
         }else{
            for(int n = 0; n < values.length; n++){
               add(Integer.toString(_count), values[n]);
            }
         }
      }
   }

   //============================================================
   // <T>获得名称和内容的打包字符串。</T>
   //
   // @return 打包字符串
   //============================================================
   public String pack(){
      FString pack = new FString();
      RStrings.pack(pack, _names, _values, 0, _count, false);
      return pack.toString();
   }

   //============================================================
   // <T>获得名称和内容的打包字符串。</T>
   //
   // @param ignoreEmpty 忽略空字符串
   // @return 打包字符串
   //============================================================
   public String pack(boolean ignoreEmpty){
      FString pack = new FString();
      RStrings.pack(pack, _names, _values, 0, _count, ignoreEmpty);
      return pack.toString();
   }

   //============================================================
   // <T>获得名称和内容的打包字符串。</T>
   //
   // @param pack 打包字符串
   // @param ignoreEmpty 忽略空字符串
   //============================================================
   public void pack(FString pack,
                    boolean ignoreEmpty){
      RStrings.pack(pack, _names, _values, 0, _count, ignoreEmpty);
   }

   //============================================================
   // <T>解包打包字符串。</T>
   //
   // @param pack 打包字符串
   //============================================================
   public void unpack(String pack){
      unpack(pack, false);
   }

   //============================================================
   // <T>解包打包字符串。</T>
   //
   // @param pack 打包字符串
   // @param merge 是否合并
   //============================================================
   public void unpack(String pack,
                      boolean merge){
      if(!merge){
         clear();
      }
      if((null != pack) && !pack.isEmpty()){
         try{
            String name;
            int len, length, n = 0;
            int total = pack.length();
            char[] chars = pack.toCharArray();
            while(n < total){
               // Name
               len = chars[n++] - '0';
               length = Integer.parseInt(new String(chars, n, len));
               n += len;
               name = new String(chars, n, length);
               n += length;
               // Value
               len = chars[n++] - '0';
               if(len > 0){
                  length = Integer.parseInt(new String(chars, n, len));
                  n += len;
                  add(name, new String(chars, n, length));
                  n += length;
               }else{
                  add(name, null);
               }
            }
         }catch(Exception e){
            throw new FFatalError(e);
         }
      }
   }

   //============================================================
   // <T>获得内容字符串。</T>
   //
   // @return 内容字符串
   //============================================================
   @Override
   public String toString(){
      FString source = new FString();
      RStrings.join(source, _names, _values, 0, _count, "=", ",");
      return source.toString();
   }

   //============================================================
   // <T>获得内容字符串。</T>
   //
   // @param nameSplit 名称分割符
   // @param valueSplit 内容分割符
   // @return 内容字符串
   //============================================================
   public String toString(String nameSplit,
                          String valueSplit){
      FString source = new FString();
      RStrings.join(source, _names, _values, 0, _count, nameSplit, valueSplit);
      return source.toString();
   }

   //============================================================
   // <T>获得内容字符串。</T>
   //
   // @param result 结果字符串
   // @param nameSplit 名称分割符
   // @param valueSplit 内容分割符
   // @return 内容字符串
   //============================================================
   public void toString(FString result,
                        String nameSplit,
                        String valueSplit){
      RStrings.join(result, _names, _values, 0, _count, nameSplit, valueSplit);
   }

   //============================================================
   // <T>生成运行信息。</T>
   //
   // @param info 运行信息
   // @return 运行信息
   //============================================================
   @Override
   public TDumpInfo dump(TDumpInfo info){
      RDump.dump(info, this);
      info.append(" ");
      info.appendInt(_count);
      info.append(" [ ");
      int n = -1;
      while(++n < _count){
         if(n != 0){
            info.append(", ");
         }
         info.append(_names[n]);
         info.append('=');
         info.append(_values[n]);
      }
      info.append(" ]");
      return info;
   }
}
