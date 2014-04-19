package org.mo.com.lang.temp;

import org.mo.com.lang.FFatalError;
import org.mo.com.lang.FObjects;
import org.mo.com.lang.FString;
import org.mo.com.lang.IAttributes;

public class FParameterPack
{
   private enum EPackItem{
      Item,
      String
   }

   private class TPackItem
   {
      EPackItem _type;

      String _value;

      public TPackItem(EPackItem type,
                       String value){
         _type = type;
         _value = value;
      }
   }

   public static String FIX_BEGIN = "${";

   public static String FIX_END = "}";

   private FObjects<TPackItem> _items = new FObjects<TPackItem>(TPackItem.class);

   public FParameterPack(){
   }

   public FParameterPack(String pack){
      parse(pack);
   }

   public FString packEmpty(IAttributes parameters){
      FString result = new FString();
      for(TPackItem item : _items){
         if(EPackItem.String == item._type){
            result.append(item._value);
         }else if(EPackItem.Item == item._type){
            result.append(parameters.get(item._value));
         }
      }
      return result;
   }

   public FString packRemain(IAttributes parameters){
      FString result = new FString();
      for(TPackItem item : _items){
         if(EPackItem.String == item._type){
            result.append(item._value);
         }else if(EPackItem.Item == item._type){
            if(parameters.contains(item._value)){
               result.append(parameters.get(item._value));
            }else{
               result.append(FIX_BEGIN);
               result.append(item._value);
               result.append(FIX_END);
            }
         }
      }
      return result;
   }

   public void parse(String pack){
      int position = 0;
      int length = pack.length();
      while(position < length){
         // 获得代码内容
         int start = pack.indexOf(FIX_BEGIN, position);
         if(-1 == start){
            break;
         }
         int end = pack.indexOf(FIX_END, start);
         if(-1 == end){
            throw new FFatalError("Invalid link format. ({0})", pack);
         }
         // 替换信息
         _items.push(new TPackItem(EPackItem.String, pack.substring(position, start)));
         _items.push(new TPackItem(EPackItem.Item, pack.substring(start + FIX_BEGIN.length(), end)));
         position = end + FIX_END.length();
      }
      // 追加尾部
      if(position < length){
         _items.push(new TPackItem(EPackItem.String, pack.substring(position, length)));
      }
   }
}
