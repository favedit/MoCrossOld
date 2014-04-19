package org.mo.com.lang;

import java.util.Arrays;
import org.mo.com.lang.generic.MObjects;

//============================================================
// <T>字符串集合。</T>
//
// @history 050218 MAOCY 创建
//============================================================
public class FStrings
      extends MObjects<String>
{
   //============================================================
   // <T>构造字符串集合。</T>
   //============================================================
   public FStrings(){
      super(String.class);
   }

   //============================================================
   // <T>构造字符串集合。</T>
   //
   // @param capacity 容量
   //============================================================
   public FStrings(int capacity){
      super(String.class, capacity);
   }

   //============================================================
   // <T>构造字符串集合。</T>
   //
   // @param pack 打包字符串
   //============================================================
   public FStrings(String pack){
      super(String.class);
      unpack(pack);
   }

   //============================================================
   // <T>构造字符串集合。</T>
   //
   // @param value 字符串
   // @param split 分割字符
   //============================================================
   public FStrings(String value,
                   char split){
      super(String.class);
      split(value, split);
   }

   //============================================================
   // <T>构造字符串集合。</T>
   //
   // @param values 字符串集合
   // @param offset 开始位置
   // @param count 总数
   //============================================================
   public FStrings(String[] values,
                   int offset,
                   int count){
      super(String.class);
      append(values, offset, count);
   }

   //============================================================
   // <T>判断来源和目标是否相等。</T>
   //
   // @param source 来源
   // @param target 目标
   // @return 是否相等
   //============================================================
   @Override
   protected boolean innerEquals(String source,
                                 String target){
      return source.equals(target);
   }

   //============================================================
   // <T>获得所有字符串中最短的长度。</T>
   //
   // @return 长度
   //============================================================
   public int lengthMin(){
      return RStrings.lengthMin(_items, 0, _count);
   }

   //============================================================
   // <T>获得所有字符串中最长的长度。</T>
   //
   // @return 长度
   //============================================================
   public int lengthMax(){
      return RStrings.lengthMax(_items, 0, _count);
   }

   //============================================================
   // <T>链接所有字符串成为一个字符串。</T>
   //
   // @return 字符串
   //============================================================
   public String join(){
      FString result = new FString();
      for(int n = 0; n < _count; n++){
         result.append(_items[n]);
      }
      return result.toString();
   }

   //============================================================
   // <T>链接所有字符串成为一个字符串。</T>
   //
   // @param split 分割符
   // @return 字符串
   //============================================================
   public String join(char split){
      FString result = new FString();
      for(int n = 0; n < _count; n++){
         if(n > 0){
            result.append(split);
         }
         result.append(_items[n]);
      }
      return result.toString();
   }

   //============================================================
   // <T>链接所有字符串成为一个字符串。</T>
   //
   // @param split 分割符
   // @return 字符串
   //============================================================
   public String join(String split){
      FString result = new FString();
      for(int n = 0; n < _count; n++){
         result.append(_items[n]);
         result.append(split);
      }
      return result.toString();
   }

   //============================================================
   // <T>链接所有字符串成为一个字符串。</T>
   //
   // @param split 分割符
   // @return 字符串
   //============================================================
   public String join(int offset,
                      int count){
      FString result = new FString();
      int end = offset + count;
      for(int n = offset; n < end; n++){
         result.append(_items[n]);
      }
      return result.toString();
   }

   //============================================================
   // <T>根据指定分隔符，分割字符串到自己内部成为数组。</T>
   //
   // @param pack 字符串
   // @param split 分隔符
   //============================================================
   public void split(String pack,
                     char split){
      int position = 0;
      int total = pack.length();
      while(position < total){
         int find = pack.indexOf(split, position);
         if(-1 == find){
            break;
         }
         push(pack.substring(position, find - 1).toString());
         position = find + 1;
      }
   }

   //============================================================
   // <T>将内部字符串集合打包为一个字符串。</T>
   //
   // @return 打包字符串
   //============================================================
   public String pack(){
      FString pack = new FString();
      for(int n = 0; n < _count; n++){
         String value = _items[n];
         if(null != value){
            String length = Integer.toString(value.length());
            pack.appendInt(length.length());
            pack.append(length);
            pack.append(value);
         }
      }
      return pack.toString();
   }

   //============================================================
   // <T>将一个字符串解包为内部字符串数据。</T>
   //
   // @param pack 打包字符串
   //============================================================
   public void unpack(String pack){
      if((null != pack) && !pack.isEmpty()){
         try{
            char[] chars = pack.toCharArray();
            int total = chars.length;
            for(int n = 0; n < total;){
               int lengthLength = chars[n++] - '0';
               int length = Integer.parseInt(new String(chars, n, lengthLength));
               n += lengthLength;
               push(new String(chars, n, length));
               n += length;
            }
         }catch(Exception e){
            throw new FFatalError(e, "Pack string = [{0}]", pack);
         }
      }
   }

   //============================================================
   // <T>对所有字符串进行排序。</T>
   //
   // @param pack 打包字符串
   //============================================================
   public void sort(){
      Arrays.sort(_items, 0, _count);
   }
}
