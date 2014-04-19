package org.mo.com.lang;

import org.mo.com.lang.generic.MString;

//============================================================
// <T>字符串集合工具类。</T>
//============================================================
public class RStrings
{
   //============================================================
   // <T>获得所有字符串中最短的长度。</T>
   //
   // @param values 字符串集合
   // @return 长度
   //============================================================
   public static int lengthMin(String[] values){
      return (null != values) ? lengthMin(values, 0, values.length) : 0;
   }

   //============================================================
   // <T>获得所有字符串中最短的长度。</T>
   //
   // @param values 字符串集合
   // @param offset 位置
   // @param length 长度
   // @return 长度
   //============================================================
   public static int lengthMin(String[] values,
                               int offset,
                               int length){
      int result = 0;
      if(null != values){
         for(int n = offset + length - 1; n >= offset; n--){
            if(null != values[n]){
               if(result > 0){
                  result = Math.min(result, values[n].length());
               }else{
                  result = values[n].length();
               }
            }
         }
      }
      return result;
   }

   //============================================================
   // <T>获得所有字符串中最长的长度。</T>
   //
   // @param values 字符串集合
   // @return 长度
   //============================================================
   public static int lengthMax(String[] values){
      return (null != values) ? lengthMax(values, 0, values.length) : 0;
   }

   //============================================================
   // <T>获得所有字符串中最长的长度。</T>
   //
   // @param values 字符串集合
   // @param offset 位置
   // @param length 长度
   // @return 长度
   //============================================================
   public static int lengthMax(String[] values,
                               int offset,
                               int length){
      int result = 0;
      if(null != values){
         for(int n = offset + length - 1; n >= offset; n--){
            if(null != values[n]){
               result = Math.max(result, values[n].length());
            }
         }
      }
      return result;
   }

   //============================================================
   // <T>将一个数组字符串中的空项目去掉，生成一个新的字符串数组。</T>
   //
   // @param values 字符串集合
   // @return 没有空项的数组字符串
   //============================================================
   public static String[] filterNotEmpty(String[] values){
      int count = values.length;
      int resultCount = 0;
      for(int n = 0; n < count; n++){
         if(!RString.isEmpty(values[n])){
            resultCount++;
         }
      }
      if(resultCount == count){
         return values;
      }
      String[] result = new String[resultCount];
      int position = 0;
      for(int n = 0; n < count; n++){
         if(!RString.isEmpty(values[n])){
            result[position] = values[n];
            position++;
         }
      }
      return result;
   }

   //============================================================
   // <T>将一个数组字符串中的空项目去掉，生成一个新的字符串数组。</T>
   //
   // @param values 字符串集合
   // @return 没有空项的数组字符串
   //============================================================
   public static String[] filterNotBlank(String[] values){
      int count = values.length;
      int resultCount = 0;
      for(int n = 0; n < count; n++){
         if(!RString.isBlank(values[n])){
            resultCount++;
         }
      }
      if(resultCount == count){
         return values;
      }
      String[] result = new String[resultCount];
      int position = 0;
      for(int n = 0; n < count; n++){
         if(!RString.isBlank(values[n])){
            result[position] = values[n];
            position++;
         }
      }
      return result;
   }

   //============================================================
   // <T>分割字符串，返回非空去掉两侧空格后的字符串数组。</T>
   //
   // @param source 字符串
   // @param split 分割字符
   // @return 没有空项的字符串数组
   //============================================================
   public static String[] splitTrimNotEmpty(String source,
                                            char split){
      String[] values = RString.split(source, split);
      RString.trim(values);
      return filterNotEmpty(values);
   }

   //============================================================
   // <T>将名称和内容合并到字符串中。</T>
   //
   // @param source 字符串
   // @param names 名称集合
   // @param values 内容集合
   // @param offset 位置
   // @param count 总数
   // @param nameSplit 名称分割
   // @param valueSplit 内容分割
   // @return 字符串
   //============================================================
   public static void join(MString source,
                           String[] names,
                           String[] values,
                           int offset,
                           int count,
                           String nameSplit,
                           String valueSplit){
      int n = offset - 1;
      int loop = offset + count;
      while(++n < loop){
         if(n > 0){
            source.append(valueSplit);
         }
         source.append(names[n]);
         source.append(nameSplit);
         source.append(values[n]);
      }
   }

   //============================================================
   // <T>将名称和内容打包到字符串中。</T>
   //
   // @param source 字符串
   // @param names 名称集合
   // @param values 内容集合
   // @param offset 位置
   // @param count 总数
   // @param ignoreEmpty 忽略空字符串
   // @return 打包字符串
   //============================================================
   public static void pack(MString pack,
                           String[] names,
                           String[] values,
                           int offset,
                           int count,
                           boolean ignoreEmpty){
      int n = offset - 1;
      int loop = offset + count;
      while(++n < loop){
         String name = names[n];
         if(null != name){
            String value = values[n];
            if(ignoreEmpty && RString.isEmpty(value)){
               continue;
            }
            String length = Integer.toString(name.length());
            pack.appendInt(length.length());
            pack.append(length);
            pack.append(name);
            if(value == null){
               pack.append("0");
            }else{
               length = Integer.toString(value.length());
               pack.appendInt(length.length());
               pack.append(length);
               pack.append(value);
            }
         }
      }
   }
   //   public static boolean inRange(String value, String[] ranges){
   //      if(null != value){
   //         for(String item : ranges){
   //            if(value.equals(item)){
   //               return true;
   //            }
   //         }
   //      }
   //      return false;
   //   }
   //
   //   public static boolean inRangeIgnoreCase(String value, String[] ranges){
   //      if(null != value){
   //         for(String item : ranges){
   //            if(value.equalsIgnoreCase(item)){
   //               return true;
   //            }
   //         }
   //      }
   //      return false;
   //   }
   //
   //   /**
   //    * <p>将一个数组字符串中的空项目去掉，生成一个新的字符串数组</p>
   //    *
   //    * @param values 数组字符串
   //    * @return 没有空项的数组字符串
   //    */
   //   public static String[] filterNotEmpty(String[] values){
   //      int count = values.length;
   //      int notEmptyCount = 0;
   //      for(int n = 0; n < count; n++){
   //         if(!RString.isEmpty(values[n])){
   //            notEmptyCount++;
   //         }
   //      }
   //      if(notEmptyCount == count){
   //         return values;
   //      }
   //      String[] result = new String[notEmptyCount];
   //      int position = 0;
   //      for(int n = 0; n < count; n++){
   //         if(!RString.isEmpty(values[n])){
   //            result[position] = values[n];
   //            position++;
   //         }
   //      }
   //      return result;
   //   }
   //
   //   public static void innerLower(String[] values){
   //      if(values != null){
   //         innerLower(values, 0, values.length);
   //      }
   //   }
   //
   //   public static void innerLower(String[] values, int offset, int length){
   //      if(values != null){
   //         for(int n = offset + length - 1; n >= offset; n--){
   //            if(values[n] != null){
   //               values[n] = values[n].toLowerCase();
   //            }
   //         }
   //      }
   //   }
   //
   //   public static void innerUpper(String[] values){
   //      if(values != null){
   //         innerUpper(values, 0, values.length);
   //      }
   //   }
   //
   //   public static void innerUpper(String[] values, int offset, int length){
   //      if(values != null){
   //         for(int n = offset + length - 1; n >= offset; n--){
   //            if(values[n] != null){
   //               values[n] = values[n].toUpperCase();
   //            }
   //         }
   //      }
   //   }
   //
   //   public static String[] toLower(String[] values){
   //      return (values != null) ? toLower(values, 0, values.length) : null;
   //   }
   //
   //   public static String[] toLower(String[] values, int offset, int length){
   //      String[] result = new String[length];
   //      if(values != null){
   //         for(int n = 0; n < length; n++){
   //            if(values[offset + n] != null){
   //               result[n] = values[offset + n].toLowerCase();
   //            }
   //         }
   //      }
   //      return result;
   //   }
   //
   //   public static String[] toUpper(String[] values){
   //      return (values != null) ? toUpper(values, 0, values.length) : null;
   //   }
   //
   //   public static String[] toUpper(String[] values, int offset, int length){
   //      String[] result = new String[length];
   //      if(values != null){
   //         for(int n = 0; n < length; n++){
   //            if(values[offset + n] != null){
   //               result[n] = values[offset + n].toUpperCase();
   //            }
   //         }
   //      }
   //      return result;
   //   }
   //
   //   //   /**
   //   //    * <p>将字符列表中名称和值对调</p>
   //   //    * <p>create date:2005/11/18</p>
   //   //    *
   //   //    */
   //   //   public static void flip(FStringList oList){
   //   //      int nSize = oList.size();
   //   //      String[] arMemory = new String[nSize];
   //   //      System.arraycopy(oList.memoryNames(), 0, arMemory, 0, nSize);
   //   //      System.arraycopy(oList.memoryValues(), 0, oList.memoryNames(), 0, nSize);
   //   //      System.arraycopy(arMemory, 0, oList.memoryValues(), 0, nSize);
   //   //   }
   //
   //   //   /**
   //   //    * <p>将指定字符串数组中的内容追加到自己内部</p>
   //   //    * <p>create date:2005/02/18</p>
   //   //    *
   //   //    * @param arValues 字符串数组
   //   //    * @return TRUE：成功 <br>FALSE：失败
   //   //    */
   //   //   public void append(String[] arValues) {
   //   //      if (arValues != null) {
   //   //         int nAppendSize = arValues.length;
   //   //         int nSize = size();
   //   //         ensureCapacity(nSize + nAppendSize);
   //   //         @SuppressWarnings("unused")
   //   //         String[] arMemoryNames = memoryNames();
   //   //         @SuppressWarnings("unused")
   //   //         String[] arMemoryValues = (String[]) memoryValues();
   //   //         for (int n = 0; n < nAppendSize; n++) {
   //   //            add(Integer.toString(nSize + n), arValues[n]);
   //   //            // arMemoryNames[nSize + n] = Integer.toString(nSize + n);
   //   //            // arMemoryValues[nSize + n] = arValues[n];
   //   //         }
   //   //      }
   //   //   }
   //   //
   //   //   /**
   //   //    * <p>和另一个字符串列表进行比较</p>
   //   //    * <p>create date:2005/02/16</p>
   //   //    * 
   //   //    * @param oList 字符串列表
   //   //    * @param oParam 参数
   //   //    * @return 比较结果
   //   //    */
   //   //   public int compare(FStringList oList, Object oParam) {
   //   //      if (oParam instanceof String) {
   //   //         return FStringUtil.compareIgnoreCase(value((String) oParam), oList
   //   //               .value((String) oParam));
   //   //      }
   //   //      return 0;
   //   //   }
   //   //
   //   //   /**
   //   //    * <p>返回名称迭代器</p>
   //   //    * 
   //   //    * @return 名称迭代器
   //   //    */
   //   //   public FNamedStringPairIterator iterator() {
   //   //      return new FNamedStringPairIterator(size(), memoryNames(), memoryValues());
   //   //   }
   //   //
   //   //   /**
   //   //    * <p>将当前列表内容打包为一个字符串值</p>
   //   //    * <p>create date:2005/02/18</p>
   //   //    *
   //   //    * @return 打包字符串
   //   //    */
   //   //   public FString pack() {
   //   //      return pack(false);
   //   //   }
   //   //
   //   //   /**
   //   //    * <p>将当前列表内容打包为一个字符串值</p>
   //   //    * <p>打包格式为：主键长度 + "_" + 主键 + 值长度 + "_" + 值 + ...<br>
   //   //    * 例如：(1)Item1 = Value1 (2)Item2 = Value2<<br>
   //   //    * 打包后：5_Item16_Value15_Item26_Value2</p>
   //   //    * <p>create date:2005/02/18</p>
   //   //    *
   //   //    * @param bIgnoreEmptyValue 是否忽略空值
   //   //    * @return 打包字符串
   //   //    */
   //   //   public FString pack(boolean bIgnoreEmptyValue) {
   //   //      FString sPack = new FString();
   //   //      int nSize = size();
   //   //      if (nSize > 0) {
   //   //         String sName = null;
   //   //         String sValue = null;
   //   //         String[] arNames = memoryNames();
   //   //         String[] arValues = (String[]) memoryValues();
   //   //         for (int n = 0; n < nSize; n++) {
   //   //            sName = arNames[n];
   //   //            sValue = arValues[n];
   //   //            // 当主键存在时,此项目进行打包处理
   //   //            if (sName != null) {
   //   //               // 检查是否忽略空值
   //   //               if (bIgnoreEmptyValue) {
   //   //                  if (sValue == null) {
   //   //                     continue;
   //   //                  } else if (sValue.toString().length() == 0) {
   //   //                     continue;
   //   //                  }
   //   //               }
   //   //               // 增加主键
   //   //               sPack.append(sName.toString().length());
   //   //               sPack.append("_");
   //   //               sPack.append(sName);
   //   //               // 增加内容
   //   //               if (sValue == null) {
   //   //                  sPack.append("0_");
   //   //               } else {
   //   //                  sPack.append(sValue.toString().length());
   //   //                  sPack.append("_");
   //   //                  sPack.append(sValue);
   //   //               }
   //   //            }
   //   //         }
   //   //      }
   //   //      return sPack;
   //   //   }
   //   //
   //   //   /**
   //   //    * <p>按照名称对字符串列表进行升序或降序排序</p>
   //   //    * <p>create date:2005/02/18</p>
   //   //    *
   //   //    * @return TRUE：成功 <br>FALSE：失败
   //   //    */
   //   //   public void sortByValue() {
   //   //      sortByValue(true);
   //   //   }
   //   //
   //   //   /**
   //   //    * <p>按照名称对字符串列表进行升序或降序排序</p>
   //   //    * <p>create date:2005/02/18</p>
   //   //    *
   //   //    * @param bOrderByAsc 是否升序排列
   //   //    * @return TRUE：成功 <br>FALSE：失败
   //   //    */
   //   //   public void sortByValue(boolean bOrderByAsc) {
   //   //   }
   //   //
   //   //   /**
   //   //    * <P>分割字符串到当前对象内部</P>
   //   //    *
   //   //    * @param sValue 分割用的字符串
   //   //    * @return TRUE：成功<br>FALSE：失败
   //   //    */
   //   //   public void split(String sValue) {
   //   //      split(sValue, "=", ",", false);
   //   //   }
   //   //
   //   //   /**
   //   //    * <P>分割字符串到当前对象内部</P>
   //   //    *
   //   //    * @param sValue 分割用的字符串
   //   //    * @param sSplitValue 值的分隔符
   //   //    * @return TRUE：成功<br>FALSE：失败
   //   //    */
   //   //   public void split(String sValue, String sSplitValue) {
   //   //      split(sValue, "=", sSplitValue, false);
   //   //   }
   //   //
   //   //   /**
   //   //    * <P>分割字符串到当前对象内部</P>
   //   //    *
   //   //    * @param sValue 分割用的字符串
   //   //    * @param sSplitKey 主键的分隔符
   //   //    * @param sSplitValue 值的分隔符
   //   //    * @return TRUE：成功<br>FALSE：失败
   //   //    */
   //   //   public void split(String sValue, String sSplitName, String sSplitValue) {
   //   //      split(sValue, sSplitName, sSplitValue, false);
   //   //   }
   //   //
   //   //   /**
   //   //    * <P>分割字符串到当前对象内部</P>
   //   //    * <P>例如：splitItems("1=first, , 2=second", "=", ",", false, true, true)<br>
   //   //    * 经过值分解后： "1=first", "", "2=second" 三项<br>
   //   //    * 分解完成后为 add("1", "first"); add("2", "second") 两项<br>
   //   //    *
   //   //    * @param sValue 分割用的字符串
   //   //    * @param sSplitKey 主键的分隔符
   //   //    * @param sSplitValue 值的分隔符
   //   //    * @param bMergeFlag 合并标志
   //   //    * @return TRUE：成功<br>FALSE：失败
   //   //    */
   //   //   public void split(String sValue,
   //   //                     String sSplitName,
   //   //                     String sSplitValue,
   //   //                     boolean bMergeFlag) {
   //   //      if (!bMergeFlag) {
   //   //         clear();
   //   //      }
   //   //      if (!FStringUtil.isEmpty(sValue)) {
   //   //         String[] arValues = sValue.split(sSplitValue);
   //   //         if (!FStringUtil.isEmpty(sSplitName)) {
   //   //            String[] arItems = null;
   //   //            for (int n = 0; n < arValues.length; n++) {
   //   //               arItems = arValues[n].split(sSplitName, 2);
   //   //               if (arItems.length == 2) {
   //   //                  add(arItems[0], arItems[1]);
   //   //               }
   //   //            }
   //   //         } else {
   //   //            for (int n = 0; n < arValues.length; n++) {
   //   //               push(arValues[n]);
   //   //            }
   //   //         }
   //   //      }
   //   //   }
   //   //
   //   //   /**
   //   //    * <p>连接主键和值为一个字符串</p>
   //   //    * <p>create date:2005/02/18</p>
   //   //    *
   //   //    * @return 字符串
   //   //    */
   //   //   public FString toString(String sNameSplitter, String sValueSplitter) {
   //   //      int nSize = size();
   //   //      String[] arMemoryNames = memoryNames();
   //   //      String[] arMemoryValues = (String[]) memoryValues();
   //   //      FString sResult = new FString();
   //   //      for (int n = 0; n < nSize; n++) {
   //   //         if (n > 0) {
   //   //            sResult.append(sValueSplitter);
   //   //         }
   //   //         sResult.append(arMemoryNames[n]);
   //   //         sResult.append(sNameSplitter);
   //   //         sResult.append(arMemoryValues[n]);
   //   //      }
   //   //      return sResult;
   //   //   }
   //   //
   //   //   /**
   //   //    * <p>将打包字符串内容,反解到当前列表中</p>
   //   //    * <p>create date:2005/02/18</p>
   //   //    *
   //   //    * @param packString 打包字符串
   //   //    * @return TRUE：成功<br>FALSE：失败
   //   //    */
   //   //   public boolean unpack(String sPack) throws FException {
   //   //      return unpack(new FString(sPack), false);
   //   //   }
   //   //
   //   //   public boolean unpack(FString sPack) throws FException {
   //   //      return unpack(sPack, false);
   //   //   }
   //   //
   //   //   /**
   //   //    * <p>将打包字符串内容,反解到当前列表中</p>
   //   //    * <p>create date:2005/02/18</p>
   //   //    *
   //   //    * @param packString 打包字符串
   //   //    * @param mergeFlag 合并标志
   //   //    * @return TRUE：成功<br>FALSE：失败
   //   //    */
   //   //   public boolean unpack(FString sTmpPackString, boolean bMergeFlag)
   //   //         throws FException {
   //   //      if (sTmpPackString == null) {
   //   //         throw new NullPointerException("PackString");
   //   //      }
   //   //      if (!bMergeFlag) {
   //   //         clear();
   //   //      }
   //   //      if (sTmpPackString.isEmpty()) {
   //   //         return true;
   //   //      }
   //   //      // 初始化参数
   //   //      int nPosition = 0;
   //   //      int nFind = -1;
   //   //      int nItemLength = 0;
   //   //      int nIndex = -1;
   //   //      String sName = "";
   //   //      String sValue = "";
   //   //      String sPackString = sTmpPackString.toString();
   //   //      // 解压缩字符串
   //   //      try {
   //   //         while (true) {
   //   //            // 获得属性名称
   //   //            nFind = sPackString.indexOf("_", nPosition);
   //   //            if (nFind >= 0) {
   //   //               nItemLength = Integer.parseInt(sPackString.substring(nPosition,
   //   //                     nFind));
   //   //               sName = sPackString
   //   //                     .substring(nFind + 1, nFind + 1 + nItemLength);
   //   //            } else {
   //   //               break;
   //   //            }
   //   //            nPosition = nFind + 1 + nItemLength;
   //   //            // 获得属性内容
   //   //            nFind = sPackString.indexOf("_", nPosition);
   //   //            if (nFind >= 0) {
   //   //               nItemLength = Integer.parseInt(sPackString.substring(nPosition,
   //   //                     nFind));
   //   //               sValue = sPackString.substring(nFind + 1, nFind + 1
   //   //                     + nItemLength);
   //   //            } else {
   //   //               break;
   //   //            }
   //   //            nPosition = nFind + 1 + nItemLength;
   //   //            // 新建子属性
   //   //            if (bMergeFlag) {
   //   //               nIndex = indexOf(sName);
   //   //               if (nIndex == -1) {
   //   //                  add(sName, sValue);
   //   //               } else {
   //   //                  setValue(nIndex, sValue);
   //   //               }
   //   //            } else {
   //   //               add(sName, sValue);
   //   //            }
   //   //         }
   //   //      } catch (Exception oException) {
   //   //         throw new FFatalException(this, "unpack", oException);
   //   //      }
   //   //      return true;
   //   //   }
   //   //
   //   //   /**
   //   //    * <p>将所有内容合并为一个字符串</p>
   //   //    * <p>create date:2005/02/18</p>
   //   //    *
   //   //    * @return 字符串
   //   //    */
   //   //   public FString valueString() {
   //   //      int nSize = this.size();
   //   //      FString sResult = new FString(256 * nSize);
   //   //      String[] arValues = (String[]) memoryValues();
   //   //      for (int n = 0; n < nSize; n++) {
   //   //         sResult.append(arValues[n]);
   //   //      }
   //   //      return sResult;
   //   //   }
   //   //
   //   //   /**
   //   //    * <p>将所有内容合并为一个字符串</p>
   //   //    * <p>create date:2005/02/18</p>
   //   //    *
   //   //    * @param sPreFix 前缀字符串
   //   //    * @param sEndFix 后缀字符串
   //   //    * @return 字符串
   //   //    */
   //   //   public FString valueString(String sBeginFix, String sEndFix) {
   //   //      int nSize = this.size();
   //   //      FString sResult = new FString(256 * nSize);
   //   //      String[] arValues = (String[]) memoryValues();
   //   //      boolean bBeginFix = !FStringUtil.isEmpty(sBeginFix);
   //   //      boolean bEndFix = !FStringUtil.isEmpty(sEndFix);
   //   //      for (int n = 0; n < nSize; n++) {
   //   //         if (bBeginFix) {
   //   //            sResult.append(sBeginFix);
   //   //         }
   //   //         sResult.append(arValues[n]);
   //   //         if (bEndFix) {
   //   //            sResult.append(sEndFix);
   //   //         }
   //   //      }
   //   //      return sResult;
   //   //   }
   //   //
   //   //   /**
   //   //    * <p>运行时获得当前实例的内部信息</p>
   //   //    * <p>create date:2005/02/18</p>
   //   //    *
   //   //    * @return 内部信息
   //   //    */
   //   //   public FString dump() throws FException {
   //   //      int nCount = size();
   //   //      FString sDump = new FString();
   //   //      sDump.append(FClassUtil.dump(this));
   //   //      sDump.append(" ");
   //   //      sDump.append(nCount);
   //   //      sDump.append(" [ ");
   //   //      for (int n = 0; n < nCount; n++) {
   //   //         if (n > 0) {
   //   //            sDump.append(", ");
   //   //         }
   //   //         sDump.append(name(n) + "=" + value(n));
   //   //      }
   //   //      sDump.append(" ]");
   //   //      return sDump;
   //   //   }
}
