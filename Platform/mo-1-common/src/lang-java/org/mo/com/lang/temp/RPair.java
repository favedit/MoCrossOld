package org.mo.com.lang.temp;

import org.mo.com.lang.IPairs;

public class RPair
{
   /**
    * <p>将目标列表中在源列表中值为空的所有项目增加到源列表中</p>
    * 
    * @param sourceItems 源列表
    * @param targetItems 目标列表
    */
   public static <N, V> void appendEmpty(IPairs<N, V> sourceItems,
                                         IPairs<N, V> targetItems){
      //      if(sourceItems != null && targetItems != null){
      //         int count = targetItems.count();
      //         for(int n = 0; n < count; n++){
      //            N name = targetItems.name(n);
      //            if(RObject.isEmpty(sourceItems.get(name))){
      //               sourceItems.set(name, targetItems.get(name));
      //            }
      //         }
      //      }
   }

   /**
    * <p>将目标列表中在源列表中没有的项目增加到源列表中</p>
    * 
    * @param sourceItems 源列表
    * @param targetItems 目标列表
    */
   public static <N, V> void appendNameEmpty(IPairs<N, V> sourceItems,
                                             IPairs<N, V> targetItems){
      if(sourceItems != null && targetItems != null){
         int count = targetItems.count();
         for(int n = 0; n < count; n++){
            N name = targetItems.name(n);
            if(!sourceItems.contains(name)){
               sourceItems.set(name, targetItems.get(name));
            }
         }
      }
   }

   /**
    * <p>获得一个新的列表，(diff)
    * 内容为源列表减去目标列表相同名称的所有内容。</p>
    *
    * @param sourceItems 源列表
    * @param targetItems 目标列表
    * @return 内容列表
    */
   public static <N, V> void innerSubtract(IPairs<N, V> sourceItems,
                                           IPairs<N, V> targetItems){
      if(sourceItems != null && targetItems != null){
         int count = targetItems.count();
         for(int n = 0; n < count; n++){
            N name = targetItems.name(n);
            if(!sourceItems.contains(name)){
               sourceItems.remove(name);
            }
         }
      }
   }
   //   /**
   //    * <p>获得名称为指定字符串的结尾的子项内容</p>
   //    * <p>create date:2005/10/14</p>
   //    * 
   //    * @param oList 名称列表
   //    * @param sValue 指定字符串
   //    * @return 子项内容
   //    */
   //   public static <V> V endsWithValue(FCustomNamingList<?, V> oList,
   //                                     String sValue) {
   //      int nCount = oList.size();
   //      for (int n = 0; n < nCount; n++) {
   //         if (sValue.endsWith(oList.name(n))) {
   //            return oList.value(n);
   //         }
   //      }
   //      return null;
   //   }
   //
   //   /**
   //    * <p>获得名称为指定开始和结尾内容的子项内容</p>
   //    * <p>create date:2005/02/16</p>
   //    * 
   //    * @param oList 名称列表
   //    * @param sStart 开始名称
   //    * @param sEnd 结束名称
   //    * @return 索引位置
   //    */
   //   public static <V> int indexOf(FCustomNamingList<?, V> oList,
   //                                 String sStart,
   //                                 String sEnd) {
   //      return indexOf(oList, sStart, sEnd, 0);
   //   }
   //
   //   /**
   //    * <p>获得名称为指定开始和结尾内容的子项内容</p>
   //    * <p>如果开始内容为空，则只比较结尾内容<br>
   //    * 如果结尾内容为空，则只比较开始内容<br>
   //    * 如果查找不到，返回-1表示不存在</p>
   //    * <p>create date:2005/02/16</p>
   //    * 
   //    * @param oList 名称列表
   //    * @param sStart 开始名称
   //    * @param sEnd 结束名称
   //    * @param nStart 开始位置
   //    * @return 索引位置
   //    */
   //   public static <V> int indexOf(FCustomNamingList<?, V> oList,
   //                                 String sStart,
   //                                 String sEnd,
   //                                 int nStart) {
   //      boolean bStart = !FStringUtil.isEmpty(sStart);
   //      boolean bEnd = !FStringUtil.isEmpty(sEnd);
   //      if (!bStart && !bEnd) {
   //         return -1;
   //      }
   //      String sName = null;
   //      int nCount = oList.size();
   //      if (oList.careCase()) {
   //         for (int n = nStart; n < nCount; n++) {
   //            sName = oList.name(n);
   //            if (!bStart || (bStart && sName.startsWith(sStart))) {
   //               if (bEnd && sName.endsWith(sEnd)) {
   //                  return n;
   //               }
   //            }
   //         }
   //      } else {
   //         sStart = sStart.toLowerCase();
   //         sEnd = sEnd.toLowerCase();
   //         for (int n = nStart; n < nCount; n++) {
   //            sName = oList.name(n);
   //            if (!bStart || (bStart && sName.toLowerCase().startsWith(sStart))) {
   //               if (bEnd && sName.toLowerCase().endsWith(sEnd)) {
   //                  return n;
   //               }
   //            }
   //         }
   //      }
   //      return -1;
   //   }
   //
   //   /**
   //    * <p>获得名称为指定开始和结尾内容的匹配的子项内容</p>
   //    * <p>create date:2005/02/16</p>
   //    * 
   //    * @param oList 名称列表
   //    * @param sStart 开始名称
   //    * @return 子项内容
   //    */
   //   public static <V> V matchValue(FCustomNamingList<?, V> oList, String sStart) {
   //      return matchValue(oList, sStart, null);
   //   }
   //
   //   /**
   //    * <p>获得名称为指定开始和结尾内容的匹配的子项内容</p>
   //    * <p>create date:2005/02/16</p>
   //    * 
   //    * @param oList 名称列表
   //    * @param sStart 开始名称
   //    * @param sEnd 结束名称
   //    * @return 子项内容
   //    */
   //   public static <V> V matchValue(FCustomNamingList<?, V> oList,
   //                                  String sStart,
   //                                  String sEnd) {
   //      int nIndex = indexOf(oList, sStart, sEnd, 0);
   //      return (oList.indexOf(nIndex) != -1) ? oList.value(nIndex) : null;
   //   }
   //
   //   /**
   //    * <p>获得名称为指定开始和结尾内容的匹配的所有子项内容列表</p>
   //    * <p>create date:2005/02/16</p>
   //    * 
   //    * @param oList 名称列表
   //    * @param sStart 开始名称
   //    * @return 内容列表
   //    * @throws FException 
   //    */
   //   public static <V> FCustomNamingList<?, V> matchValues(FCustomNamingList<?, V> oList,
   //                                                         String sStart)
   //         throws FException {
   //      return matchValues(oList, sStart, null);
   //   }
   //
   //   /**
   //    * <p>获得名称为指定开始和结尾内容的匹配的所有子项内容列表</p>
   //    * <p>如果开始内容为空，则只比较结尾内容<br>
   //    * 如果结尾内容为空，则只比较开始内容<br>
   //    * 如果查找不到，返回-1表示不存在</p>
   //    * <p>create date:2005/02/16</p>
   //    * 
   //    * @param oList 名称列表
   //    * @param sStart 开始名称
   //    * @param sEnd 结束名称
   //    * @return 内容列表
   //    * @throws FException 
   //    */
   //   public static <V> FCustomNamingList<?, V> matchValues(FCustomNamingList<?, V> oList,
   //                                                         String sStart,
   //                                                         String sEnd)
   //         throws FException {
   //      boolean bStart = !FStringUtil.isEmpty(sStart);
   //      boolean bEnd = !FStringUtil.isEmpty(sEnd);
   //      int nCount = oList.size();
   //      FCustomNamingList<?, V> oValues = FClassUtil.newInstance(oList);
   //      if (bStart && bEnd) {
   //         String sName = null;
   //         if (oList.careCase()) {
   //            for (int n = 0; n < nCount; n++) {
   //               sName = oList.name(n);
   //               if (!bStart || (bStart && sName.startsWith(sStart))) {
   //                  if (bEnd && sName.endsWith(sEnd)) {
   //                     oValues.add(sName, oList.value(n));
   //                  }
   //               }
   //            }
   //         } else {
   //            sStart = sStart.toLowerCase();
   //            sEnd = sEnd.toLowerCase();
   //            for (int n = 0; n < nCount; n++) {
   //               sName = oList.name(n);
   //               if (!bStart
   //                     || (bStart && sName.toLowerCase().startsWith(sStart))) {
   //                  if (bEnd && sName.toLowerCase().endsWith(sEnd)) {
   //                     oValues.add(sName, oList.value(n));
   //                  }
   //               }
   //            }
   //         }
   //      }
   //      return oValues;
   //   }
   //
   //   /**
   //    * <p>移除所有名称列表中的该命名的子项</p>
   //    * <p>create date:2005/02/16</p>
   //    * 
   //    * @param oList 名称对象列表
   //    * @param oArray 名称列表
   //    */
   //   public static <V> void remove(FCustomNamingList<?, V> oList,
   //                                 FStringArray oArray) {
   //      int nSize = oArray.size();
   //      for (int n = 0; n < nSize; n++) {
   //         oList.remove(oArray.value(n));
   //      }
   //   }
   //
   //   /**
   //    * <p>获得名称为指定字符串的开始的子项内容</p>
   //    * <p>create date:2005/10/14</p>
   //    * 
   //    * @param oList 名称列表
   //    * @param sValue 指定字符串
   //    * @return 子项内容
   //    */
   //   public static <V> V startsWithValue(FCustomNamingList<?, V> oList,
   //                                       String sValue) {
   //      int nCount = oList.size();
   //      for (int n = 0; n < nCount; n++) {
   //         if (sValue.startsWith(oList.name(n))) {
   //            return oList.value(n);
   //         }
   //      }
   //      return null;
   //   }
   //
   //   /*public void makeChildrenMap(FNamedMap oMap){
   //    super.makeChildrenMap(oMap);
   //    int nSize = size();
   //    Object[] arValues = memoryValues();
   //    for (int n = 0; n < nSize; n++) {
   //    if (arValues[n] instanceof IChildrenMapAble) {
   //    ((IChildrenMapAble) arValues[n]).makeChildrenMap(oMap);
   //    }
   //    }
   //    }*/
   //
   //   /**
   //    * <p>获得一个新的列表，
   //    * 内容为源列表和目标列表中公有项目，重合内容为目标列表内容。</p>
   //    * <p>如果有重复的，则取当前列表中的值</p>
   //    * <p>create date:2005/02/18</p>
   //    *
   //    * @param oSourceList 源列表
   //    * @param oTargetList 目标列表
   //    * @return 内容列表
   //    */
   //   public static <V> FCustomNamingList<?, V> orList(FCustomNamingList<?, V> oSourceList,
   //                                                    FCustomNamingList<?, V> oTargetList)
   //         throws FException {
   //      FCustomNamingList<?, V> oRsList = FClassUtil.newInstance(oSourceList);
   //      if (oSourceList != null) {
   //         //oRsList.assign(oSourceList);
   //      }
   //      if (oTargetList != null) {
   //         int nIndex = -1;
   //         String sName = null;
   //         int nCount = oTargetList.size();
   //         for (int n = 0; n < nCount; n++) {
   //            sName = oTargetList.name(n);
   //            nIndex = oRsList.indexOf(sName);
   //            if (nIndex == -1) {
   //               oRsList.add(sName, oTargetList.value(n));
   //            } else {
   //               oRsList.setValue(nIndex, oTargetList.value(n));
   //            }
   //         }
   //      }
   //      return oRsList;
   //   }
   //
   //   /**
   //    * <p>获得一个新的列表，
   //    * 内容为源列表和目标列表中名称重合的部分，重合内容为目标列表内容。</p>
   //    * <p>create date:2005/02/18</p>
   //    *
   //    * @param oSourceList 源列表
   //    * @param oTargetList 目标列表
   //    * @return 内容列表
   //    */
   //   // intersect 
   //   public static <V> FCustomNamingList<?, V> andList(FCustomNamingList<?, V> oSourceList,
   //                                                     FCustomNamingList<?, V> oTargetList)
   //         throws FException {
   //      FCustomNamingList<?, V> oRsList = FClassUtil.newInstance(oSourceList);
   //      ;
   //      if (oSourceList != null && oTargetList != null) {
   //         String sName = null;
   //         int nCount = oSourceList.size();
   //         for (int n = 0; n < nCount; n++) {
   //            sName = oSourceList.name(n);
   //            if (oTargetList.containsName(sName)) {
   //               oRsList.add(sName, oTargetList.value(n));
   //            }
   //         }
   //      }
   //      return oRsList;
   //   }
   //
   //   /**
   //    * <p>将目标列表中在源列表中值为空的所有项目增加到源列表中</p>
   //    * <p>create date:2005/02/16</p>
   //    * 
   //    * @param oSourceList 源列表
   //    * @param oTargetList 目标列表
   //    */
   //   public static <V> void appendEmpty(FCustomNamingList<?, V> oSourceList,
   //                                      FCustomNamingList<?, V> oTargetList) {
   //      int nIndex = 0;
   //      String sName = null;
   //      int nCount = oTargetList.size();
   //      for (int n = 0; n < nCount; n++) {
   //         sName = oTargetList.name(n);
   //         nIndex = oSourceList.indexOf(sName);
   //         if (nIndex == -1 || FStringUtil.isEmpty(oSourceList.value(nIndex))) {
   //            oSourceList.add(sName, oTargetList.value(n));
   //         }
   //      }
   //   }
   //
   //   /**
   //    * <p>将目标列表中在源列表中没有的项目增加到源列表中</p>
   //    * <p>create date:2005/02/16</p>
   //    * 
   //    * @param oSourceList 源列表
   //    * @param oTargetList 目标列表
   //    */
   //   public static <V> void appendNameEmpty(FCustomNamingList<?, V> oSourceList,
   //                                          FCustomNamingList<?, V> oTargetList) {
   //      String sName = null;
   //      int nCount = oTargetList.size();
   //      for (int n = 0; n < nCount; n++) {
   //         sName = oTargetList.name(n);
   //         if (!oSourceList.containsName(sName)) {
   //            oSourceList.add(sName, oTargetList.value(n));
   //         }
   //      }
   //   }
   //
   //   /**
   //    * <p>获得名称为指定字符串的结尾的子项内容</p>
   //    * <p>create date:2005/10/14</p>
   //    * 
   //    * @param oList 名称列表
   //    * @param sValue 指定字符串
   //    * @return 子项内容
   //    */
   //   public static <V> V endsWithValue(FCustomNamingList<?, V> oList,
   //                                     String sValue) {
   //      int nCount = oList.size();
   //      for (int n = 0; n < nCount; n++) {
   //         if (sValue.endsWith(oList.name(n))) {
   //            return oList.value(n);
   //         }
   //      }
   //      return null;
   //   }
   //
   //   /**
   //    * <p>获得名称为指定开始和结尾内容的子项内容</p>
   //    * <p>create date:2005/02/16</p>
   //    * 
   //    * @param oList 名称列表
   //    * @param sStart 开始名称
   //    * @param sEnd 结束名称
   //    * @return 索引位置
   //    */
   //   public static <V> int indexOf(FCustomNamingList<?, V> oList,
   //                                 String sStart,
   //                                 String sEnd) {
   //      return indexOf(oList, sStart, sEnd, 0);
   //   }
   //
   //   /**
   //    * <p>获得名称为指定开始和结尾内容的子项内容</p>
   //    * <p>如果开始内容为空，则只比较结尾内容<br>
   //    * 如果结尾内容为空，则只比较开始内容<br>
   //    * 如果查找不到，返回-1表示不存在</p>
   //    * <p>create date:2005/02/16</p>
   //    * 
   //    * @param oList 名称列表
   //    * @param sStart 开始名称
   //    * @param sEnd 结束名称
   //    * @param nStart 开始位置
   //    * @return 索引位置
   //    */
   //   public static <V> int indexOf(FCustomNamingList<?, V> oList,
   //                                 String sStart,
   //                                 String sEnd,
   //                                 int nStart) {
   //      boolean bStart = !FStringUtil.isEmpty(sStart);
   //      boolean bEnd = !FStringUtil.isEmpty(sEnd);
   //      if (!bStart && !bEnd) {
   //         return -1;
   //      }
   //      String sName = null;
   //      int nCount = oList.size();
   //      if (oList.careCase()) {
   //         for (int n = nStart; n < nCount; n++) {
   //            sName = oList.name(n);
   //            if (!bStart || (bStart && sName.startsWith(sStart))) {
   //               if (bEnd && sName.endsWith(sEnd)) {
   //                  return n;
   //               }
   //            }
   //         }
   //      } else {
   //         sStart = sStart.toLowerCase();
   //         sEnd = sEnd.toLowerCase();
   //         for (int n = nStart; n < nCount; n++) {
   //            sName = oList.name(n);
   //            if (!bStart || (bStart && sName.toLowerCase().startsWith(sStart))) {
   //               if (bEnd && sName.toLowerCase().endsWith(sEnd)) {
   //                  return n;
   //               }
   //            }
   //         }
   //      }
   //      return -1;
   //   }
   //
   //   /**
   //    * <p>获得名称为指定开始和结尾内容的匹配的子项内容</p>
   //    * <p>create date:2005/02/16</p>
   //    * 
   //    * @param oList 名称列表
   //    * @param sStart 开始名称
   //    * @return 子项内容
   //    */
   //   public static <V> V matchValue(FCustomNamingList<?, V> oList, String sStart) {
   //      return matchValue(oList, sStart, null);
   //   }
   //
   //   /**
   //    * <p>获得名称为指定开始和结尾内容的匹配的子项内容</p>
   //    * <p>create date:2005/02/16</p>
   //    * 
   //    * @param oList 名称列表
   //    * @param sStart 开始名称
   //    * @param sEnd 结束名称
   //    * @return 子项内容
   //    */
   //   public static <V> V matchValue(FCustomNamingList<?, V> oList,
   //                                  String sStart,
   //                                  String sEnd) {
   //      int nIndex = indexOf(oList, sStart, sEnd, 0);
   //      return (oList.indexOf(nIndex) != -1) ? oList.value(nIndex) : null;
   //   }
   //
   //   /**
   //    * <p>获得名称为指定开始和结尾内容的匹配的所有子项内容列表</p>
   //    * <p>create date:2005/02/16</p>
   //    * 
   //    * @param oList 名称列表
   //    * @param sStart 开始名称
   //    * @return 内容列表
   //    * @throws FException 
   //    */
   //   public static <V> FCustomNamingList<?, V> matchValues(FCustomNamingList<?, V> oList,
   //                                                         String sStart)
   //         throws FException {
   //      return matchValues(oList, sStart, null);
   //   }
   //
   //   /**
   //    * <p>获得名称为指定开始和结尾内容的匹配的所有子项内容列表</p>
   //    * <p>如果开始内容为空，则只比较结尾内容<br>
   //    * 如果结尾内容为空，则只比较开始内容<br>
   //    * 如果查找不到，返回-1表示不存在</p>
   //    * <p>create date:2005/02/16</p>
   //    * 
   //    * @param oList 名称列表
   //    * @param sStart 开始名称
   //    * @param sEnd 结束名称
   //    * @return 内容列表
   //    * @throws FException 
   //    */
   //   public static <V> FCustomNamingList<?, V> matchValues(FCustomNamingList<?, V> oList,
   //                                                         String sStart,
   //                                                         String sEnd)
   //         throws FException {
   //      boolean bStart = !FStringUtil.isEmpty(sStart);
   //      boolean bEnd = !FStringUtil.isEmpty(sEnd);
   //      int nCount = oList.size();
   //      FCustomNamingList<?, V> oValues = FClassUtil.newInstance(oList);
   //      if (bStart && bEnd) {
   //         String sName = null;
   //         if (oList.careCase()) {
   //            for (int n = 0; n < nCount; n++) {
   //               sName = oList.name(n);
   //               if (!bStart || (bStart && sName.startsWith(sStart))) {
   //                  if (bEnd && sName.endsWith(sEnd)) {
   //                     oValues.add(sName, oList.value(n));
   //                  }
   //               }
   //            }
   //         } else {
   //            sStart = sStart.toLowerCase();
   //            sEnd = sEnd.toLowerCase();
   //            for (int n = 0; n < nCount; n++) {
   //               sName = oList.name(n);
   //               if (!bStart
   //                     || (bStart && sName.toLowerCase().startsWith(sStart))) {
   //                  if (bEnd && sName.toLowerCase().endsWith(sEnd)) {
   //                     oValues.add(sName, oList.value(n));
   //                  }
   //               }
   //            }
   //         }
   //      }
   //      return oValues;
   //   }
   //
   //   /**
   //    * <p>移除所有名称列表中的该命名的子项</p>
   //    * <p>create date:2005/02/16</p>
   //    * 
   //    * @param oList 名称对象列表
   //    * @param oArray 名称列表
   //    */
   //   public static <V> void remove(FCustomNamingList<?, V> oList,
   //                                 FStringArray oArray) {
   //      int nSize = oArray.size();
   //      for (int n = 0; n < nSize; n++) {
   //         oList.remove(oArray.value(n));
   //      }
   //   }
   //
   //   /**
   //    * <p>获得名称为指定字符串的开始的子项内容</p>
   //    * <p>create date:2005/10/14</p>
   //    * 
   //    * @param oList 名称列表
   //    * @param sValue 指定字符串
   //    * @return 子项内容
   //    */
   //   public static <V> V startsWithValue(FCustomNamingList<?, V> oList,
   //                                       String sValue) {
   //      int nCount = oList.size();
   //      for (int n = 0; n < nCount; n++) {
   //         if (sValue.startsWith(oList.name(n))) {
   //            return oList.value(n);
   //         }
   //      }
   //      return null;
   //   }
   //
   //   /*public void makeChildrenMap(FNamedMap oMap){
   //    super.makeChildrenMap(oMap);
   //    int nSize = size();
   //    Object[] arValues = memoryValues();
   //    for (int n = 0; n < nSize; n++) {
   //    if (arValues[n] instanceof IChildrenMapAble) {
   //    ((IChildrenMapAble) arValues[n]).makeChildrenMap(oMap);
   //    }
   //    }
   //    }*/
   //
   //   /**
   //    * <p>获得一个新的列表，
   //    * 内容为源列表和目标列表中公有项目，重合内容为目标列表内容。</p>
   //    * <p>如果有重复的，则取当前列表中的值</p>
   //    * <p>create date:2005/02/18</p>
   //    *
   //    * @param oSourceList 源列表
   //    * @param oTargetList 目标列表
   //    * @return 内容列表
   //    */
   //   public static <V> FCustomNamingList<?, V> orList(FCustomNamingList<?, V> oSourceList,
   //                                                    FCustomNamingList<?, V> oTargetList)
   //         throws FException {
   //      FCustomNamingList<?, V> oRsList = FClassUtil.newInstance(oSourceList);
   //      if (oSourceList != null) {
   //         //oRsList.assign(oSourceList);
   //      }
   //      if (oTargetList != null) {
   //         int nIndex = -1;
   //         String sName = null;
   //         int nCount = oTargetList.size();
   //         for (int n = 0; n < nCount; n++) {
   //            sName = oTargetList.name(n);
   //            nIndex = oRsList.indexOf(sName);
   //            if (nIndex == -1) {
   //               oRsList.add(sName, oTargetList.value(n));
   //            } else {
   //               oRsList.setValue(nIndex, oTargetList.value(n));
   //            }
   //         }
   //      }
   //      return oRsList;
   //   }
   //
   //   /**
   //    * <p>获得一个新的列表，
   //    * 内容为源列表和目标列表中名称重合的部分，重合内容为目标列表内容。</p>
   //    * <p>create date:2005/02/18</p>
   //    *
   //    * @param oSourceList 源列表
   //    * @param oTargetList 目标列表
   //    * @return 内容列表
   //    */
   //   // intersect 
   //   public static <V> FCustomNamingList<?, V> andList(FCustomNamingList<?, V> oSourceList,
   //                                                     FCustomNamingList<?, V> oTargetList)
   //         throws FException {
   //      FCustomNamingList<?, V> oRsList = FClassUtil.newInstance(oSourceList);
   //      ;
   //      if (oSourceList != null && oTargetList != null) {
   //         String sName = null;
   //         int nCount = oSourceList.size();
   //         for (int n = 0; n < nCount; n++) {
   //            sName = oSourceList.name(n);
   //            if (oTargetList.containsName(sName)) {
   //               oRsList.add(sName, oTargetList.value(n));
   //            }
   //         }
   //      }
   //      return oRsList;
   //   }
   //
   //   /**
   //    * <p>获得一个新的列表，
   //    * 内容为源列表减去目标列表相同名称的所有内容。</p>
   //    * <p>create date:2005/02/18</p>
   //    *
   //    * @param oSourceList 源列表
   //    * @param oTargetList 目标列表
   //    * @return 内容列表
   //    */
   //   // diff 
   //   public static <V> FCustomNamingList<?, V> subList(FCustomNamingList<?, V> oSourceList,
   //                                                     FCustomNamingList<?, V> oTargetList)
   //         throws FException {
   //      FCustomNamingList<?, V> oRsList = FClassUtil.newInstance(oSourceList);
   //      if (oSourceList != null && oTargetList != null) {
   //         String sName = null;
   //         int nCount = oSourceList.size();
   //         for (int n = 0; n < nCount; n++) {
   //            sName = oSourceList.name(n);
   //            if (!oTargetList.containsName(sName)) {
   //               oRsList.add(sName, oSourceList.value(n));
   //            }
   //         }
   //      }
   //      return oRsList;
   //   }
}
