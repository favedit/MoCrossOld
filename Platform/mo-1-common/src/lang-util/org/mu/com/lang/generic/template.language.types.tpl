package org.mo.com.lang.type;

//============================================================
// <T>{type_label}数据类型工具。</T>
//
// @history 130219 创建
//============================================================
public class RBase{type_name}
{
   // 默认值
   public final static {type_base} DEFAULT = {type_default};

   // 空集合
   public final static {type_base} EMPTY_COLLECTION[] = new {type_base}[0];

   //============================================================
   // <T>判断来源{type_label}数组和目标{type_label}数组是否相等。</T>
   //
   // @param source 来源{type_label}数组
   // @param target 目标{type_label}数组
   // @return 是否相等
   //============================================================
   public final static boolean equals({type_base}[] source, {type_base}[] target){
      // 内容都为空
      if((source == null) && (target == null)){
         return true;
      }
      // 内容都不为空
      if((source != null) && (target != null)){
         int sourceLength = source.length;
         int targetLength = target.length;
         if(sourceLength != targetLength){
            return false;
         }
         for(int n = 0; n < sourceLength; n++){
            if(source[n] != target[n]){
               return false;
            }
         }
         return true;
      }
      return false;
   }
   
   //============================================================
   // <T>判断来源{type_label}数组和目标{type_label}数组是否相等。</T>
   //
   // @param source 来源{type_label}数组
   // @param sourceLength 来源长度
   // @param target 目标{type_label}数组
   // @param targetLength 来源长度
   // @return 是否相等
   //============================================================
   public final static boolean equals({type_base}[] source, int sourceLength, {type_base}[] target, int targetLength){
      // 内容都为空
      if((source == null) && (sourceLength == 0) && (target == null) && (targetLength == 0)){
         return true;
      }
      // 内容都不为空
      if((source != null) && (target != null)){
         if(sourceLength != targetLength){
            return false;
         }
         for(int n = 0; n < sourceLength; n++){
            if(source[n] != target[n]){
               return false;
            }
         }
         return true;
      }
      return false;
   }
   
   //============================================================
   // <T>判断来源{type_label}数组和目标{type_label}数组是否相等。</T>
   //
   // @param source 来源{type_label}数组
   // @param sourceOffset 来源位置
   // @param sourceLength 来源长度
   // @param target 目标{type_label}数组
   // @param targetOffset 来源位置
   // @param targetLength 来源长度
   // @return 是否相等
   //============================================================
   public final static boolean equals({type_base}[] source, int sourceOffset, int sourceLength, {type_base}[] target, int targetOffset, int targetLength){
      // 内容都为空
      if((source == null) && (sourceLength == 0) && (target == null) && (targetLength == 0)){
         return true;
      }
      // 内容都不为空
      if((source != null) && (target != null)){
         if(sourceLength != targetLength){
            return false;
         }
         for(int n = 0; n < sourceLength; n++){
            if(source[sourceOffset + n] != target[targetOffset + n]){
               return false;
            }
         }
         return true;
      }
      return false;
   }
   
   //============================================================
   // <T>在{type_label}数组中是否含有指定{type_label}内容。</T>
   //
   // @param values {type_label}数组
   // @param find {type_label}内容
   // @return 是否含有
   //============================================================
   public final static boolean contains({type_base}[] values, {type_base} find){
      if(null != values){
         int loop = values.length;
         for(int n = 0; n < loop; n++){
            if(values[n] == find){
               return true;
            }
         }
      }
      return false;
   }
   
   //============================================================
   // <T>在{type_label}数组中是否含有指定{type_label}内容。</T>
   //
   // @param values {type_label}数组
   // @param offset 位置
   // @param length 长度
   // @param find {type_label}内容
   // @return 是否含有
   //============================================================
   public final static boolean contains({type_base}[] values, int offset, int length, {type_base} find){
      if(null != values){
         int loop = offset + length;
         for(int n = offset; n < loop; n++){
            if(values[n] == find){
               return true;
            }
         }
      }
      return false;
   }
{compare_source}
   //============================================================
   // <T>从{type_label}数组中查找{type_label}内容的索引位置。</T>
   //
   // @param values {type_label}数组
   // @param find {type_label}内容
   // @return 索引位置
   //============================================================
   public final static int indexOf({type_base}[] values, {type_base} find){
      if(null != values){
         int loop = values.length;
         for(int n = 0; n < loop; n++){
            if(values[n] == find){
               return n;
            }
         }
      }
      return -1;
   }

   //============================================================
   // <T>从{type_label}数组中查找{type_label}内容的索引位置。</T>
   //
   // @param values {type_label}数组
   // @param offset 位置
   // @param length 长度
   // @param find {type_label}内容
   // @return 索引位置
   //============================================================
   public final static int indexOf({type_base}[] values, int offset, int length, {type_base} find){
      if(null != values){
         int loop = offset + length;
         for(int n = 0; n < loop; n++){
            if(values[n] == find){
               return n;
            }
         }
      }
      return -1;
   }

   //============================================================
   // <T>从来源{type_label}数组中查找目标{type_label}数组的索引位置。</T>
   //
   // @param source 来源{type_label}数组
   // @param target 目标{type_label}数组
   // @return 索引位置
   //============================================================
   public final static int search({type_base}[] source, {type_base}[] target){
      return search(source, 0, source.length, target, 0, target.length);
   }

   //============================================================
   // <T>从来源{type_label}数组中查找目标{type_label}数组的索引位置。</T>
   //
   // @param source 来源{type_label}数组
   // @param offset 来源位置
   // @param length 来源长度
   // @param target 目标{type_label}数组
   // @return 索引位置
   //============================================================
   public final static int search({type_base}[] source, int offset, int length, {type_base}[] target){
      return search(source, offset, length, target, 0, target.length);
   }

   //============================================================
   // <T>从来源{type_label}数组中查找目标{type_label}数组的索引位置。</T>
   //
   // @param source 来源{type_label}数组
   // @param sourceOffset 来源位置
   // @param sourceLength 来源长度
   // @param target 目标{type_label}数组
   // @param targetOffset 目标位置
   // @param targetLength 目标长度
   // @return 索引位置
   //============================================================
   public final static int search({type_base}[] source, int sourceOffset, int sourceLength, {type_base}[] target, int targetOffset, int targetLength){
      if((null != source) && (null != target)){
         {type_base} first = target[targetOffset];
         int m = sourceOffset - 1;
         int end = sourceOffset + sourceLength;
         while(++m < end){
            if(source[m] == first){
               int n = targetOffset;
               while(++n < targetLength){
                  if(source[m + n] != target[n]){
                     break;
                  }
               }
               if(n == targetLength - 1){
                  return m;
               }
            }
         }
      }
      return -1;
   }

   //============================================================
   // <T>以指定字符开头和结尾，从源{type_label}数组中查找目标{type_label}数组存在索引位置。</T>
   //
   // @param source 源对象数组
   // @param offset 开始位置
   // @param count 项目总数
   // @param before 开头{type_label}
   // @param find 查找{type_label}数组
   // @param findOffset 查找位置
   // @param findCount 查找总数
   // @param after 结尾{type_label}
   // @return 索引位置
   //============================================================
   public final static int search({type_base}[] source, int offset, int length, {type_base} before, {type_base}[] target, int targetOffset, int targetLength, {type_base} after){
      int i;
      int el;
      int n = offset - 1;
      int loop = length - targetLength - 2;
      while(++n <= loop){
         if(source[n] == before){
            n++;
            i = -1;
            el = 0;
            while(++i < targetLength){
               if(source[n + i] != target[targetOffset + i]){
                  break;
               }
               el++;
            }
            if(el == targetLength && source[n + el] == after){
               return n - 1;
            }
         }
      }
      return -1;
   }

   //============================================================
   // <T>填充指定内容到{type_label}数组。</T>
   //
   // @param source {type_label}数组
   // @param value {type_label}内容
   //============================================================
   public final static void fill({type_base}[] source, {type_base} value){
      fill(source, 0, source.length, value);
   }

   //============================================================
   // <T>填充指定内容到{type_label}数组。</T>
   //
   // @param source {type_label}数组
   // @param offset 开始位置
   // @param count 数组长度
   // @param value {type_label}内容
   //============================================================
   public final static void fill({type_base}[] source, int offset, int length, {type_base} value){
      int n = offset - 1;
      int end = offset + length;
      while(++n < end){
         source[n] = value;
      }
   }

   //============================================================
   // <T>替换{type_label}数组中一{type_label}为另一{type_label}。</T>
   //
   // @param source 字符数组
   // @param from 要替换字符数组
   // @param to 被替换字符数组
   //============================================================
   public final static void replace({type_base}[] source, {type_base} from, {type_base} to){
      replace(source, 0, source.length, from, to);
   }

   //============================================================
   // <T>替换{type_label}数组中一{type_label}为另一{type_label}。</T>
   //
   // @param source 字符数组
   // @param offset 开始位置
   // @param length 数据长度
   // @param from 要替换字符数组
   // @param to 被替换字符数组
   //============================================================
   public final static void replace({type_base}[] source, int offset, int length, {type_base} from, {type_base} to){
      int n = offset - 1;
      while(++n < length){
         if(source[n] == from){
            source[n] = to;
         }
      }
   }

   //============================================================
   // <T>替换{type_label}数组中一{type_label}数组为另一{type_label}数组。</T>
   //
   // @param source 字符数组
   // @param offset 开始位置
   // @param length 数据长度
   // @param from 要替换字符数组
   // @param to 被替换字符数组
   // @return 替换后的字符数组
   //============================================================
   public final static {type_base}[] replace({type_base}[] source, int offset, int length, {type_base}[] from, {type_base}[] to){
      return replace(source, offset, length, from, 0, from.length, to, 0, to.length);
   }

   //============================================================
   // <T>替换{type_label}数组中一{type_label}数组为另一{type_label}数组。</T>
   //
   // @param source 字符数组
   // @param offset 开始位置
   // @param length 数据长度
   // @param from 要替换字符数组
   // @param fromOffset 要替换字符数组开始位置
   // @param fromLength 要替换字符数组数据长度
   // @param to 被替换字符数组
   // @param toOffset 被替换字符数组开始位置
   // @param toLength 被替换字符数组数据长度
   // @return 替换后的字符数组
   //============================================================
   public final static {type_base}[] replace({type_base}[] source, int offset, int length, {type_base}[] from, int fromOffset, int fromLength, {type_base}[] to, int toOffset, int toLength){
      boolean same = true;
      {type_base}[] tempChars = null;
      int p = 0;
      int loop = length - fromLength;
      if(toLength <= fromLength){
         int i = 0;
         while(i <= loop){
            same = true;
            for(int j = 0; j < fromLength; j++){
               if(source[i + j] != from[j]){
                  same = false;
                  break;
               }
            }
            if(same){
               System.arraycopy(to, 0, source, p, toLength);
               i += fromLength;
               p += toLength;
            }else{
               source[p++] = source[i++];
            }
         }
         while(i < length){
            source[p++] = source[i++];
         }
         tempChars = new {type_base}[p];
         System.arraycopy(source, 0, tempChars, 0, p);
      }else{
         int newLength = length;
         int pos = 0;
         while(pos <= loop){
            same = true;
            for(int n = 0; n < fromLength; n++){
               if(source[pos + n] != from[n]){
                  same = false;
                  break;
               }
            }
            if(!same){
               pos++;
            }else{
               pos += fromLength;
               newLength += (toLength - fromLength);
            }
         }
         pos = 0;
         tempChars = new {type_base}[newLength];
         boolean charCopy = (toLength < 20);
         while(pos < length){
            same = true;
            for(int n = 0; n < fromLength; n++){
               if(source[pos + n] != from[n]){
                  same = false;
                  break;
               }
            }
            if(!same){
               tempChars[p++] = source[pos++];
            }else{
               pos += fromLength;
               if(charCopy){
                  for(int n = 0; n < toLength; n++){
                     tempChars[p++] = to[n];
                  }
               }else{
                  System.arraycopy(to, 0, tempChars, p, toLength);
                  p += toLength;
               }
            }
         }
      }
      return tempChars;
   }

   //============================================================
   // <T>获得{type_label}数组中索引位置开始指定长度的{type_label}数组。</T>
   //
   // @param source {type_label}数组
   // @param offset 开始位置
   // @param length 数组长度
   // @return 中间数组
   //============================================================
   public final static {type_base}[] sub({type_base}[] source, int offset, int length){
      {type_base}[] result = null;
      if(length > 0){
         result = new {type_base}[Math.abs(length)];
         System.arraycopy(source, offset, result, 0, length);
      }
      return result;
   }

   //============================================================
   // <T>获得{type_label}数组中开始位置到结束位置之间的{type_label}数组。</T>
   //
   // @param source {type_label}数组
   // @param start 开始位置
   // @param end 结束位置
   // @return 中间数组
   //============================================================
   public static {type_base}[] mid({type_base}[] source, int start, int end){
      int length = Math.abs(end - start);
      if(length > 0){
         {type_base}[] result = new {type_base}[length];
            System.arraycopy(source, start, result, 0, length);
            if(start > end){
               reverse(result, 0, length);
            }
            return result;
         }
      return new {type_base}[0];
   }

   //============================================================
   // <T>对{type_label}数组的{type_label}内容进行反转。</T>
   //
   // @param values {type_label}数组
   //============================================================
   public final static void reverse({type_base}[] values){
      reverse(values, 0, values.length);
   }

   //============================================================
   // <T>对{type_label}数组的{type_label}内容进行反转。</T>
   //
   // @param values {type_label}数组
   // @param index 索引位置
   // @param length 索引长度
   //============================================================
   public final static void reverse({type_base}[] values, int index, int length){
      int s = index - 1;
      int e = index + length + 1;
      while(++s < --e){
         {type_base} temp = values[s];
         values[s] = values[e];
         values[e] = temp;
      }
   }
   
   //============================================================
   // <T>擦除{type_label}数组中指定索引位置的{type_label}内容。</T>
   //
   // @param values {type_label}数组
   // @param index 索引位置
   //============================================================
   public final static int erase(char[] values, int index){
      if(null != values){
         int length = values.length;
         if((index >= 0) && (index < length)){
            System.arraycopy(values, index + 1, values, index, length - index);
            return length - 1;
         }
         return length;
      }
      return 0;
   }
   
   //============================================================
   // <T>擦除{type_label}数组中从开始位置到结束位置之间的{type_label}内容。</T>
   //
   // @param source {type_label}数组
   // @param index 索引位置
   // @param length 索引长度
   //============================================================
   public final static int erase({type_base}[] values, int index, int length){
      if(null != values){
         int total = values.length;
         if((index >= 0) && (index + length < total)){
            System.arraycopy(values, index + length, values, index, total - index - length);
            return total - length;
         }
         return total;
      }
      return 0;
   }

   //============================================================
   // <T>删除{type_label}数组中的指定{type_label}内容。</T>
   //
   // @param values {type_label}数组
   // @param offset 开始位置
   // @param length 数组长度
   // @param find {type_label}内容
   //============================================================
   public final static int remove({type_base}[] values, int offset, int length, {type_base} find){
      int index = offset;
      for(int n = offset; n < length; n++){
         if(values[n] != find){
            if(index != n){
               values[index] = values[n];
            }
            index++;
         }
      }
      return index;
   }

   //============================================================
   // <T>删除来源{type_label}数组中的全部目标{type_label}数组。</T>
   //
   // @param source 来源{type_label}数组
   // @param sourceOffset 来源位置
   // @param sourceLength 来源长度
   // @param target 目标{type_label}数组
   // @param targetOffset 目标位置
   // @param targetLength 目标长度
   //============================================================
   public final static int remove({type_base}[] source, int sourceOffset, int sourceLength, {type_base}[] target, int targetOffset, int targetLength){
      if((null != source) && (sourceLength > 0) && (null != target) && (targetLength > 0)){
         int p = -1;
         int index = 0;
         int loop = sourceLength - targetLength;
         {type_base} first = target[targetOffset];
         while(++p <= loop){
            if(source[p] == first){
               boolean same = true;
               int i = targetOffset;
               while(++i < targetLength){
                  if(source[p + i] != target[i]){
                     same = false;
                     break;
                  }
               }
               if(same){
                  p += targetLength - 1;
               }else{
                  source[index++] = source[p];
               }
            }else{
               source[index++] = source[p];
            }
         }
         p--;
         while(++p < sourceLength){
            source[index++] = source[p];
         }
         return index;
      }
      return sourceLength;
   }
   
   //============================================================
   // <T>清空{type_label}数组。</T>
   //
   // @param values {type_label}数组
   //============================================================
   public final static void clear({type_base}[] values){
      if(null != values){
         int loop = values.length;
         for(int n = 0; n < loop; n++){
            values[n] = {type_default};
         }
      }
   }

   //============================================================
   // <T>清空{type_label}数组。</T>
   //
   // @param source {type_label}数组
   // @param offset 开始位置
   // @param count 数组长度
   //============================================================
   public final static void clear({type_base}[] values, int offset, int length){
      if(null != values){
         int loop = offset + length;
         for(int n = offset; n < loop; n++){
            values[n] = {type_default};
         }
      }
   }
}
