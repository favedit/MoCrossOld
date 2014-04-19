package org.mo.com.regex;

import org.mo.com.lang.FFatalError;
import org.mo.com.lang.FString;
import org.mo.com.lang.RString;

public class FPattern
{
   private String[] _items;

   private String _pattern;

   public FPattern(String value){
      parse(value);
   }

   public boolean matches(String value){
      if(value == null){
         return false;
      }
      if(!value.startsWith(_items[0])){
         return false;
      }
      int pos = 0;
      int count = _items.length;
      for(int n = 0; n < count; n++){
         if(_items[n] != null){
            int index = value.indexOf(_items[n], pos);
            if(index == -1){
               return false;
            }
            pos = index + _items[n].length();
         }
      }
      return true;
   }

   private void parse(String value){
      _pattern = value;
      _items = RString.split(value, '*');
   }

   public String replace(String value){
      return replace(value, _pattern);
   }

   public String replace(String value,
                         String pattern){
      String[] items = RString.split(pattern, '*');
      if(items.length != _items.length){
         throw new FFatalError("Pattern invalid. {0}", pattern);
      }
      FString result = new FString();
      int pos = 0;
      int end = 0;
      int count = _items.length;
      for(int n = 0; n < count; n++){
         if(_items[n] != null){
            String item = _items[n];
            int index = value.indexOf(item, pos);
            if(index == -1){
               throw new FFatalError("Matches invalid. {0}", value);
            }
            result.append(value.substring(end, index));
            result.append(items[n]);
            pos = index + 1;
            end = index + item.length();
         }
      }
      result.append(value.substring(end));
      return result.toString();
   }
}
