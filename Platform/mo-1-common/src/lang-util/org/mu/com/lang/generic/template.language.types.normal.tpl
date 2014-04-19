
   //============================================================
   // <T>比较来源{type_label}数组和目标{type_label}数组进行的大小。</T>
   //
   // @param source 来源{type_label}数组
   // @param target 目标{type_label}数组
   // @return 比较结果
   //============================================================
   public final static int compare({type_base}[] source, {type_base}[] target){
      return compare(source, 0, source.length, target, 0, target.length);
   }

   //============================================================
   // <T>比较来源{type_label}数组和目标{type_label}数组进行的大小。</T>
   //
   // @param source 来源{type_label}数组
   // @param sourceOffset 来源位置
   // @param sourceLength 来源长度
   // @param target 目标{type_label}数组
   // @param targetOffset 目标位置
   // @param targetLength 目标长度
   // @return 比较结果
   //============================================================
   public final static int compare({type_base}[] source, int sourceOffset, int sourceLength, {type_base}[] target, int targetOffset, int targetLength){
      int scl = sourceLength - sourceOffset;
      int tcl = targetLength - targetOffset;
      int loop = Math.min(scl, tcl);
      while(loop-- != 0){
         if(source[sourceOffset++] != target[targetOffset++]){
            return (int)(source[sourceOffset - 1] - target[targetOffset - 1]);
         }
      }
      return scl - tcl;
   }
