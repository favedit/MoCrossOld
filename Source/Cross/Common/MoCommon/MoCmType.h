#ifndef __MO_CM_TYPE_H__
#define __MO_CM_TYPE_H__

#ifndef __MO_CM_RUNTIME_H__
#include "MoCmRuntime.h"
#endif // __MO_CM_RUNTIME_H__

#ifndef __MO_CM_INTERFACE_H__
#include "MoCmInterface.h"
#endif // __MO_CM_INTERFACE_H__

//============================================================
// <T>逻辑定义。</T>
//============================================================
/// @define 获得绝对值
#define MO_LIB_ABS(V)         ((V) > 0 ? (V) : -(V))
/// @define 获得两个值中较小的值
#define MO_LIB_MIN(A, B)      ((A) < (B) ? (A) : (B))
/// @define 获得两个值中较大的值
#define MO_LIB_MAX(A, B)      ((A) > (B) ? (A) : (B))
/// @define 获得在指定范围中的值
#define MO_LIB_RANGE(A, B, C) (((A) > (B)) ? ((A) < (C) ? (A) : (C)) : (B))
/// @define 如果第一个参数为NULL，则取第二个参数
#define MO_LIB_NVL(V, D)      (NULL == (V) ? (V) : (D))
/// @define 取一个类型的默认值
#define MO_LIB_DEFAULT(T)     (T)NULL
/// @define 交换两个值
#define MO_LIB_SWAP(T, A, B)  {T (V) = (A); (A) = (B); (B) = (V);}
//------------------------------------------------------------
/// @define 字节对齐
#define MO_LIB_ALIGN_2(x) (((x) + 1) & ~1U)
#define MO_LIB_ALIGN_4(x) (((x) + 3) & ~3U)
#define MO_LIB_ALIGN_8(x) (((x) + 7) & ~7U)

//------------------------------------------------------------
// 布尔类型定义
#define MO_TYPE_BOOL_TRUE_CHAR     'Y'
#define MO_TYPE_BOOL_TRUE_STRING   TC("Y")
#define MO_TYPE_BOOL_TRUE_DISPLAY  TC("True")
#define MO_TYPE_BOOL_FALSE_CHAR    'N'
#define MO_TYPE_BOOL_FALSE_STRING  TC("N")
#define MO_TYPE_BOOL_FALSE_DISPLAY TC("False")

//------------------------------------------------------------
#define MO_TYPE_SORT_THRESHOLD     7

//------------------------------------------------------------
#define MO_TP_BYTE_BIT 8
#define MO_TP_BYTE_MIN 0x00
#define MO_TP_BYTE_MAX 0xFF

#ifdef _UNICODE
#define MO_TP_CHAR_BIT 16
#define MO_TP_CHAR_MIN (-32768)
#define MO_TP_CHAR_MAX 32767
#else
#define MO_TP_CHAR_BIT 8
#define MO_TP_CHAR_MIN (-128)
#define MO_TP_CHAR_MAX 127
#endif

#define MO_TP_INT8_BIT 8
#define MO_TP_INT8_MIN (-127 - 1)
#define MO_TP_INT8_MAX 127

#define MO_TP_INT16_BIT 16
#define MO_TP_INT16_MIN (-32767 - 1)
#define MO_TP_INT16_MAX 32767

#define MO_TP_INT32_BIT 32
#define MO_TP_INT32_MIN (-2147483647 - 1)
#define MO_TP_INT32_MAX 2147483647

#define MO_TP_INT64_BIT 64
#define MO_TP_INT64_MIN (-9223372036854775807i64 - 1)
#define MO_TP_INT64_MAX 9223372036854775807i64

#define MO_TP_UINT8_BIT 8
#define MO_TP_UINT8_MIN 0x00
#define MO_TP_UINT8_MAX 0xFF

#define MO_TP_UINT16_BIT 16
#define MO_TP_UINT16_MIN 0x0000
#define MO_TP_UINT16_MAX 0xFFFF

#define MO_TP_UINT32_BIT 32
#define MO_TP_UINT32_MIN 0x00000000
#define MO_TP_UINT32_MAX 0xFFFFFFFF

#define MO_TP_UINT64_BIT 64
#define MO_TP_UINT64_MIN 0x0000000000000000UL
#define MO_TP_UINT64_MAX 0xFFFFFFFFFFFFFFFFUL

#define MO_TP_FLOAT_DIG       6
#define MO_TP_FLOAT_BIT       32
#define MO_TP_FLOAT_MIN       1.175494351e-38F
#define MO_TP_FLOAT_VAL       0.0F
#define MO_TP_FLOAT_MAX       3.402823466e+38F
#define MO_TP_FLOAT_MIN_EXP10 (-37)
#define MO_TP_FLOAT_MIN_EXP   (-125)
#define MO_TP_FLOAT_MAX_EXP10 38
#define MO_TP_FLOAT_MAX_EXP   128

#define MO_TP_DOUBLE_DIG       15
#define MO_TP_DOUBLE_BIT       64
#define MO_TP_DOUBLE_MIN       2.2250738585072014e-308
#define MO_TP_DOUBLE_VAL       0.0F
#define MO_TP_DOUBLE_MAX       1.7976931348623158e+308
#define MO_TP_DOUBLE_MIN_EXP10 (-307)
#define MO_TP_DOUBLE_MIN_EXP   (-1021)
#define MO_TP_DOUBLE_MAX_EXP10 308
#define MO_TP_DOUBLE_MAX_EXP   1024

//------------------------------------------------------------
// 时间类型定义
#define MO_TP_DATE_MICROSECONDS_PER_DAY    86400000000L
#define MO_TP_DATE_MICROSECONDS_PER_HOUR    3600000000L
#define MO_TP_DATE_MICROSECONDS_PER_MINUTE    60000000L
#define MO_TP_DATE_MICROSECONDS_PER_SECONDE    1000000L
#define MO_TP_DATE_MILLISECONDS_PER_DAY       86400000
#define MO_TP_DATE_MILLISECONDS_PER_HOUR       3600000
#define MO_TP_DATE_MILLISECONDS_PER_MINUTE       60000
#define MO_TP_DATE_MILLISECONDS_PER_SECONDE       1000
#define MO_TP_DATE_SECONDS_PER_DAY               86400
#define MO_TP_DATE_SECONDS_PER_HOUR               3600
#define MO_TP_DATE_SECONDS_PER_MINUTE               60

//============================================================
// 格式化
#ifdef _MO_X64
#define MO_FMT_INT      TC("ld")
#define MO_FMT_HEX      TC("lX")
#define MO_FMT_HEX_FULL TC("016lX")
#define MO_FMT_POINTER  TC("0x%016X")
#else
#define MO_FMT_INT      TC("d")
#define MO_FMT_HEX      TC("X")
#define MO_FMT_HEX_FULL TC("08X")
#define MO_FMT_POINTER  TC("0x%08X")
#endif // _MO_X64
//------------------------------------------------------------
#ifdef _MO_LINUX
#define MO_FMT_INT64   TC("lld")
#else
#ifdef _MO_X64
#define MO_FMT_INT64   TC("l64d")
#else
#define MO_FMT_INT64   TC("lld")
#endif // _MO_X64
#endif // _MO_LINUX

//------------------------------------------------------------
// 逻辑定义
#define MO_LG_SORT_THRESHOLD 7
#define MO_SORT_SWAP(tmp, a, b) do { (tmp) = (a); (a) = (b); (b) = (tmp); } while(0)

MO_NAMESPACE_BEGIN

const TInt MoSortThreshold = 7;

//============================================================
// <T>字符类型</T>
//============================================================
enum EChar{
   EChar_Unknown = 0,
   EChar_Char8   = 1,
   EChar_Char16  = 2,
   EChar_Char32  = 3,
};

//============================================================
// <T>基本类型的管理类</T>
//============================================================
template <typename T>
class RType{
protected:
   static T _default;
public:
   //------------------------------------------------------------
   // 获得默认值
   MO_INLINE static T Default() {
      return _default;
   }
   //------------------------------------------------------------
   // 获得非空值
   MO_INLINE static T Nvl(T value, T other) {
      return (_default == value) ? other : _default;
   }
   //------------------------------------------------------------
   // 清空指针
   MO_INLINE static void Clear(T* pMemory) {
      if(NULL != pMemory){
         MO_LIB_TYPE_CLEAR(T, pMemory);
      }
   }
};
//------------------------------------------------------------
template <typename T> T RType<T>::_default;

//============================================================
// <T>数组的基本功能函数。</T>
//
// @reference
// @author maocy
// @version 1.0.1
//============================================================
template <typename T>
class RTypes{
public:
   //------------------------------------------------------------
   // <T>根据条件，比较两个对象的大小，当小于时返回负值生成递增排序。</T>
   typedef TInt (*HComparer)(const T& source, const T& target);
   //------------------------------------------------------------
   // <T>根据条件，比较两个对象的大小，当小于时返回负值生成递增排序。</T>
   typedef TInt (*HComparerObject)(const T& source, const T& target, TAny* pCondition);
   //------------------------------------------------------------
   // <T>根据条件，比较两个对象的大小，当小于时返回负值生成递增排序。</T>
   typedef TInt (*HComparerValue)(T source, T target, TAny* pCondition);
public:
   //------------------------------------------------------------
   // <T>根据以前长度和期望长度，计算出类型扩张后长度。</T>
   MO_INLINE static TInt CalculateTypeCapacity(TInt lengthOld, TInt lengthNew){
      TInt capacity = 0;
      do{
         // 但收集量不得小于默认值<C>MO_MEMORY_CAPACITY</C>的长度
         capacity = lengthNew;
         if(capacity < MO_MEMORY_CAPACITY){
            capacity = MO_MEMORY_CAPACITY;
         }
         // 首次调用
         if(0 == lengthOld){
            break;
         }
         // 当长度小于阀值时，扩大2倍长度
         if(lengthOld <= MO_MEMORY_EXTEND_CAPACITY){
            capacity += capacity;
            break;
         }
         // 当长度小于阀值时，扩大1.5倍长度
         capacity += (capacity >> 1);
      }while(0);
      // 对齐到整块数据
      TInt blockCount = capacity / MO_MEMORY_CAPACITY;
      if(0 != (capacity % MO_MEMORY_CAPACITY)){
         blockCount++;
      }
      capacity = MO_MEMORY_CAPACITY * blockCount;
      return capacity;
   }
   //------------------------------------------------------------
   // <T>根据以前个数和期望个数，计算出对象扩张后个数。</T>
   MO_INLINE static TInt CalculateObjectCapacity(TInt countOld, TInt countNew){
      TInt capacity = 0;
      do{
         // 但收集量不得小于默认值<C>MO_OBJECT_CAPACITY</C>的长度
         capacity = countNew;
         if(capacity < MO_OBJECT_CAPACITY){
            capacity = MO_OBJECT_CAPACITY;
         }
         // 首次调用
         if(0 == countOld){
            break;
         }
         // 当长度小于阀值时，扩大2倍长度
         if(countOld <= MO_OBJECT_EXTEND_CAPACITY){
            capacity += capacity;
            break;
         }
         // 当长度小于阀值时，扩大1.5倍长度
         capacity += (capacity >> 1);
      }while(0);
      // 对齐到整块数据
      TInt blockCount = capacity / MO_OBJECT_CAPACITY;
      if(0 != (capacity % MO_OBJECT_CAPACITY)){
         blockCount++;
      }
      capacity = MO_OBJECT_CAPACITY * blockCount;
      return capacity;
   }
public:
   //------------------------------------------------------------
   // <T>判断一个值是否存在于指定数组中。</T>
   MO_INLINE static TBool Contains(const T* pValues, TInt length, T value){
      if((NULL != pValues) && (length > 0)){
         while(--length >= 0){
            if(*pValues++ == value){
               return ETrue;
            }
         }
      }
      return EFalse;
   }
   //------------------------------------------------------------
   // <T>判断两个数组是否相等。</T>
   MO_INLINE static TBool Equals(const T* pSource, const T* pTarget, TInt length){
      if((NULL != pSource) && (NULL != pTarget) && (length >= 0)){
         // 循环判断每一项是否相等
         while(--length >= 0){
            if(*pSource++ != *pTarget++){
               return EFalse;
            }
         }
         return ETrue;
      }
      return EFalse;
   }
   //------------------------------------------------------------
   // <T>判断两个数组是否相等。</T>
   MO_INLINE static TBool Equals(const T* pSource, TInt sourceLength, const T* pTarget, TInt targetLength){
      if((NULL != pSource) && (sourceLength >= 0) && (NULL != pTarget) && (targetLength >= 0)){
         // 判断长度是否相等
         if(sourceLength != targetLength){
            return EFalse;
         }
         // 循环判断每一项是否相等
         while(--sourceLength >= 0){
            if(*pSource++ != *pTarget++){
               return EFalse;
            }
         }
      }
      return ETrue;
   }
   //------------------------------------------------------------
   // <T>查找指定数组是否出现在当前数组的开始位置。</T>
   MO_INLINE static TBool StartsWith(const T* pSource, TInt sourceLength, const T* pFind, TInt findLength){
      if((NULL != pSource) && (sourceLength >= 0) && (NULL != pFind) && (findLength >= 0)){
         // 长度不足时直接返回结果
         if(sourceLength < findLength){
            return EFalse;
         }
         // 开始查找数据
         return Equals(pSource, pFind, findLength);
      }
      return EFalse;
   }
   //------------------------------------------------------------
   // <T>查找指定数组是否出现在当前数组的结束位置。</T>
   MO_INLINE static TBool EndsWith(const T* pSource, TInt sourceLength, const T* pFind, TInt findLength){
      if((NULL != pSource) && (sourceLength >= 0) && (NULL != pFind) && (findLength >= 0)){
         // 长度不足时直接返回结果
         if(sourceLength < findLength){
            return EFalse;
         }
         // 开始查找数据
         return Equals(pSource + sourceLength - findLength, pFind, findLength);
      }
      return EFalse;
   }
   //------------------------------------------------------------
   // <T>判断两个数组的大小。</T>
   MO_INLINE static TInt Compare(const T* pSource, TInt sourceLength, const T* pTarget, TInt targetLength){
      if((NULL != pSource) && (sourceLength >= 0) && (NULL != pTarget) && (targetLength >= 0)){
         // 循环判断每一项的大小
         TInt n = -1;
         TInt loop = (sourceLength < targetLength) ? sourceLength : targetLength;
         while(++n < loop){
            if(pSource[n] != pTarget[n]){
               return pSource[n] - pTarget[n];
            }
         }
         // 全部数据一样的时候，就以长度判断数组大小
         return sourceLength - targetLength;
      }
      return EFalse;
   }
   //------------------------------------------------------------
   // <T>判断一个值存在于指定数组中首次出现的索引位置。</T>
   MO_INLINE static TInt IndexOf(const T* pValues, T value){
      if(NULL != pValues){
         const T* pSearch = pValues;
         while(ETrue){
            if(*pSearch == value){
               return pSearch - pValues;
            }
            pSearch++;
         }
      }
      return ENotFound;
   }
   //------------------------------------------------------------
   // <T>判断一个值存在于指定数组中首次出现的索引位置。</T>
   MO_INLINE static TInt IndexOf(const T* pValues, TInt length, T value){
      if((NULL != pValues) && (length > 0)){
         const T* pSearch = pValues;
         while(--length >= 0){
            if(*pSearch == value){
               return pSearch - pValues;
            }
            pSearch++;
         }
      }
      return ENotFound;
   }
   //------------------------------------------------------------
   // <T>判断一组数值存在于指定数组中首次出现的索引位置。</T>
   MO_INLINE static TInt IndexsOf(const T* pValues, TInt length, const T* pFind, TInt findLength){
      if((NULL != pValues) && (length > 0) && (NULL != pFind) && (findLength > 0)){
         const T* pSearch = pValues;
         while(--length >= 0){
            for(TInt n = 0; n < findLength; n++){
               if(*pSearch == pFind[n]){
                  return pSearch - pValues;
               }
            }
            pSearch++;
         }
      }
      return ENotFound;
   }
   //------------------------------------------------------------
   // <T>判断一个值存在于指定数组中最后出现的索引位置。</T>
   MO_INLINE static TInt LastIndexOf(const T* pValues, TInt length, T value){
      if((NULL != pValues) && (length > 0)){
         const T* pSearch = pValues + length;
         while(--length >= 0){
            pSearch--;
            if(*pSearch == value){
               return pSearch - pValues;
            }
         }
      }
      return ENotFound;
   }
public:
   //------------------------------------------------------------
   // <T>在一个数组中查找另一个数组中首次出现的索引位置。</T>
   static TInt Find(const T* pValues, TInt valueLength, const T* pFind, TInt findLength){
      if((NULL != pValues) && (valueLength >=0) && (NULL != pFind) && (findLength >= 0)){
         // 判断长度
         if((0 == valueLength) && (0 == findLength)){
            return ETrue;
         }
         // 取出要查找的第一个数据
         const T& first = pFind[0];
         // 循环查找每一个数据
         TInt position = -1;
         TInt length = valueLength - findLength;
         while(++position <= length){
            // 如果查到第一个则继续查找后面的数据
            if(pValues[position] == first){
               TInt n = 0;
               while(++n < findLength){
                  if(pValues[position + n] != pFind[n]){
                      break;
                  }
               }
               // 如果符合查找长度则返回索引位置
               if(n == findLength){
                  return position;
               }
            }
         }
      }
      // 没有查到数据
      return ENotFound;
   }
   //------------------------------------------------------------
   // <T>在一个数组中查找另一个数组中首次出现的索引位置。</T>
   static TInt LastFind(const T* pValues, TInt valueLength, const T* pFind, TInt findLength){
      if((NULL != pValues) && (valueLength >=0) && (NULL != pFind) && (findLength >= 0)){
         // 判断长度
         if((0 == valueLength) && (0 == findLength)){
            return ETrue;
         }
         // 取出要查找的第一个数据
         const T& first = pFind[0];
         // 循环查找每一个数据
         TInt position = -1;
         TInt length = valueLength - findLength;
         while(++position <= length){
            // 如果查到第一个则继续查找后面的数据
            if(pValues[length - position] == first){
               TInt n = 0;
               while (++n < findLength){
                  if (pValues[length - position + n] != pFind[n]){
                      break;
                  }
               }
               // 如果符合查找长度则返回索引位置
               if(n == findLength){
                  return length - position;
               }
            }
         }
      }
      // 没有查到数据
      return ENotFound;
   }
   //------------------------------------------------------------
   // <T>判断一个内容在数组中出现的次数。</T>
   static TInt Count(const T* pValues, TInt length, T value){
      TInt count = 0;
      if((NULL != pValues) && (length > 0)){
         // 遍历数组找到相同的部分
         for(TInt n = 0; n < length; n++){
            if(pValues[n] == value){
               count++;
            }
         }
      }
      return count;
   }
   //------------------------------------------------------------
   // <T>判断一个数组内容在数组中出现的次数。</T>
   static TInt Count(const T* pValues, TInt valueLength, const T* pFind, TInt findLength){
      TInt count = 0;
      if((NULL != pValues) && (valueLength > 0) && (NULL != pFind) && (findLength > 0)){
         // 取出要查找的第一个数据
         const T& first = pFind[0];
         // 遍历数组找到相同的部分
         TInt position = -1;
         TInt loop = valueLength - findLength;
         while(++position < loop){
            // 如果查到第一个则继续查找后面的数据
            if(pValues[position] == first){
               TBool result = ETrue;
               for(TInt n = 1; n < findLength; n++){
                  if(pValues[position + n] != pFind[n]){
                     result = EFalse;
                     break;
                  }
               }
               // 如果符合查找长度则计数
               if(result){
                  count++;
               }
            }
         }
      }
      return count;
   }
   //------------------------------------------------------------
   // <T>计算根据数组内每一项值的综合哈希值。</T>
   static THashCode MakeHashCode(const T* pValues, TInt length){
      THashCode hash = 0;
      if((NULL != pValues) && (length > 0)){
         while(--length >= 0){
            hash += (hash << 4) + (hash << 3) + (hash << 2) + (hash << 1) + pValues[length];
         }
      }
      return hash;
   }
   //------------------------------------------------------------
   // <T>填充一个数组的内容为填充内容。</T>
   static void Fill(T* pValues, TInt length, T value){
      if((NULL != pValues) && (length > 0)){
         while(--length >= 0){
            *pValues++ = value;
         }
      }
   }
   //------------------------------------------------------------
   // <T>复制原数组到目标数组。</T>
   // <P>底层汇编实现，速度较快，但：(源地址) < (目标首地址) < (源地址+复制长度时)，
   //    数据会产生覆盖情况，从而复制错误。</P>
   MO_INLINE static void Copy(T* pTarget, const T* pSource, TInt length){
      if((NULL != pTarget) && (NULL != pSource) && (length > 0)){
         if(pTarget != pSource){
            memcpy(pTarget, pSource, sizeof(T) * length);
         }
      }
   }
   //------------------------------------------------------------
   // <T>移动原数组到目标数组。</T>
   // <P>底层代码实现，速度较慢，但不会产生复制错误。</P>
   MO_INLINE static void Move(T* pTarget, const T* pSource, TInt length){
      if((NULL != pTarget) && (NULL != pSource) && (length > 0)){
         if(pTarget != pSource){
            memmove(pTarget, pSource, sizeof(T) * length);
         }
      }
   }
   //------------------------------------------------------------
   // <T>替换数组中指定内容为另一个内容。</T>
   MO_INLINE static void Replace(T* pValues, TInt length, T source, T target){
      if((NULL != pValues) && (length > 0)){
         while(--length >= 0){
            if(pValues[length] == source){
               pValues[length] = target;
            }
         }
      }
   }
   //------------------------------------------------------------
   // <T>替换数组中指定数组内容为另一个数组内容。</T>
   static TInt Replace(T* pValue, TInt length, const T* pSource, TInt sourceLength, const T* pTarget, TInt targetLength, T* pBuffer, TInt bufferLength){
      TInt count = 0;
      if((NULL != pValue) && (length > 0) && (NULL != pSource) && (sourceLength > 0) && (NULL != pTarget) && (targetLength > 0)){
         // 获得首个对象
         const T& sourceFirst = pSource[0];
         // 遍历数组找到相同的部分
         TInt start = 0;
         TInt end = 0;
         for(TInt n = 0; n < length; n++){
            if(pValue[n] == sourceFirst){
               // 查找位置
               start = n;
               TBool result = ETrue;
               for(TInt m = 1; m < sourceLength; m++){
                  if(pValue[n + m] != pSource[m]){
                     result = EFalse;
                     break;
                  }
               }
               // 开始替换
               if(result){
                  // 复制中间数据
                  for(TInt i = end; i < start; i++){
                     *pBuffer++ = pValue[i];
                     count++;
                  }
                  // 复制目标数据
                  for(TInt i = 0; i < targetLength; i++){
                     *pBuffer++ = pTarget[i];
                     count++;
                  }
                  end = start + sourceLength;
               }
            }
         }
         // 复制残留数据
         for(TInt n = end; n < length; n++){
            *pBuffer++ = pValue[n];
            count++;
         }
         // 检测长度
         if(count > bufferLength){
            return -1;
         }
      }
      return count;
   }
   //------------------------------------------------------------
   // <T>从数组中移除指定的数据内容。</T>
   static TInt Remove(T* pValue, TInt length, T value){
      TInt count = 0;
      if((NULL != pValue) && (length > 0)){
         T* pWriter = pValue;
         while(--length >= 0){
            if(pValue[length] != value){
               *pWriter++ = pValue[length];
               count++;
            }
         }
      }
      return count;
   }
   //------------------------------------------------------------
   // <T>设置一个数组的所有数据内容为零。</T>
   MO_INLINE static void Clear(T* pValue, TInt length){
      if((NULL != pValue) && (length > 0)){
         memset(pValue, 0, sizeof(T) * length);
      }
   }
protected:
   //------------------------------------------------------------
   // <T>对数据集合进行快速排序。</T>
   static void InnerQuickSort(T* pItems, int left, int right){
      // 检查范围
      if(left >= right){
         return;
      }
      // 少于基准值时，使用冒泡排序
      TInt size = right - left + 1;
      if(size <= MO_TYPE_SORT_THRESHOLD){
         SortBubble(&pItems[left], size);
         return;
      }
      // 获得基准值
      T value = pItems[left];
      // 部分排序
      TInt begin = left;
      TInt end = right;
      while(begin != end){
         // 后面向前比较
         while((begin < end) && (pItems[end] >= value)){
            end--;
         }
         pItems[begin] = pItems[end];
         // 前面向后比较
         while((begin < end) && (pItems[begin] <= value)){
            begin++;
         }
         pItems[end] = pItems[begin];
      }
      pItems[begin] = value;
      // 递归排序
      InnerQuickSort(pItems, left, begin - 1);
      InnerQuickSort(pItems, begin + 1, right);
   }
   //------------------------------------------------------------
   // <T>对数据集合进行快速排序。</T>
   static void InnerQuickSort(T* pItems, int left, int right, HComparer hComparer){
      // 检查范围
      if(left >= right){
         return;
      }
      // 少于基准值时，使用冒泡排序
      TInt size = right - left + 1;
      if(size <= MO_TYPE_SORT_THRESHOLD){
         SortBubble(&pItems[left], size, hComparer);
         return;
      }
      // 获得基准值
      T value = pItems[left];
      // 部分排序
      TInt begin = left;
      TInt end = right;
      while(begin != end){
         // 后面向前比较
         while((begin < end) && (hComparer(pItems[end], value) >= 0)){
            end--;
         }
         pItems[begin] = pItems[end];
         // 前面向后比较
         while((begin < end) && (hComparer(pItems[begin], value) <= 0)){
            begin++;
         }
         pItems[end] = pItems[begin];
      }
      pItems[begin] = value;
      // 递归排序
      InnerQuickSort(pItems, left, begin - 1, hComparer);
      InnerQuickSort(pItems, begin + 1, right, hComparer);
   }
   //------------------------------------------------------------
   // <T>对数据集合进行快速排序。</T>
   static void InnerQuickSortObject(T* pItems, int left, int right, HComparerObject hComparer, TAny* pCondition = NULL){
      // 检查范围
      if(left >= right){
         return;
      }
      // 少于基准值时，使用冒泡排序
      TInt size = right - left + 1;
      if(size <= MO_TYPE_SORT_THRESHOLD){
         SortBubbleObject(&pItems[left], size, hComparer, pCondition);
         return;
      }
      // 获得基准值
      T value = pItems[left];
      // 部分排序
      TInt begin = left;
      TInt end = right;
      while(begin != end){
         // 后面向前比较
         while((begin < end) && (hComparer(pItems[end], value, pCondition) >= 0)){
            end--;
         }
         pItems[begin] = pItems[end];
         // 前面向后比较
         while((begin < end) && (hComparer(pItems[begin], value, pCondition) <= 0)){
            begin++;
         }
         pItems[end] = pItems[begin];
      }
      pItems[begin] = value;
      // 递归排序
      InnerQuickSortObject(pItems, left, begin - 1, hComparer, pCondition);
      InnerQuickSortObject(pItems, begin + 1, right, hComparer, pCondition);
   }
   //------------------------------------------------------------
   // <T>对数据集合进行快速排序。</T>
   static void InnerQuickSortValue(T* pItems, int left, int right, HComparerValue hComparer, TAny* pCondition = NULL){
      // 检查范围
      if(left >= right){
         return;
      }
      // 少于基准值时，使用冒泡排序
      TInt size = right - left + 1;
      if(size <= MO_TYPE_SORT_THRESHOLD){
         SortBubbleValue(&pItems[left], size, hComparer, pCondition);
         return;
      }
      // 获得基准值
      T value = pItems[left];
      // 部分排序
      TInt begin = left;
      TInt end = right;
      while(begin != end){
         // 后面向前比较
         while((begin < end) && (hComparer(pItems[end], value, pCondition) >= 0)){
            end--;
         }
         pItems[begin] = pItems[end];
         // 前面向后比较
         while((begin < end) && (hComparer(pItems[begin], value, pCondition) <= 0)){
            begin++;
         }
         pItems[end] = pItems[begin];
      }
      pItems[begin] = value;
      // 递归排序
      InnerQuickSortValue(pItems, left, begin - 1, hComparer, pCondition);
      InnerQuickSortValue(pItems, begin + 1, right, hComparer, pCondition);
   }
public:
   //------------------------------------------------------------
   // <T>对数据集合进行冒泡排序。</T>
   static void SortBubble(T* pItems, TInt length){
      TInt loop = length - 1;
      for(TInt j = 0; j < loop; j++){
         for(TInt i = loop; i > j; i--){
            if(pItems[i] < pItems[i-1]){
               T temp = pItems[i];
               pItems[i] = pItems[i-1];
               pItems[i-1] = temp;
            }
         }
      }
   }
   //------------------------------------------------------------
   // <T>对数据集合进行冒泡排序。</T>
   static void SortBubble(T* pItems, TInt length, HComparer hComparer){
      TInt loop = length - 1;
      for(TInt j = 0; j < loop; j++){
         for(TInt i = loop; i > j; i--){
            if(hComparer(pItems[i], pItems[i-1]) < 0){
               T temp = pItems[i];
               pItems[i] = pItems[i-1];
               pItems[i-1] = temp;
            }
         }
      }
   }
   //------------------------------------------------------------
   // <T>对数据集合进行冒泡排序。</T>
   static void SortBubbleObject(T* pItems, TInt length, HComparerObject hComparer, TAny* pCondition = NULL){
      TInt loop = length - 1;
      for(TInt j = 0; j < loop; j++){
         for(TInt i = loop; i > j; i--){
            if(hComparer(pItems[i], pItems[i-1], pCondition) < 0){
               T temp = pItems[i];
               pItems[i] = pItems[i-1];
               pItems[i-1] = temp;
            }
         }
      }
   }
   //------------------------------------------------------------
   // <T>对数据集合进行冒泡排序。</T>
   static void SortBubbleValue(T* pItems, TInt length, HComparerValue hComparer, TAny* pCondition = NULL){
      TInt loop = length - 1;
      for(TInt j = 0; j < loop; j++){
         for(TInt i = loop; i > j; i--){
            if(hComparer(pItems[i], pItems[i-1], pCondition) < 0){
               T temp = pItems[i];
               pItems[i] = pItems[i-1];
               pItems[i-1] = temp;
            }
         }
      }
   }
   //------------------------------------------------------------
   // <T>对数据集合进行快速排序。</T>
   static void SortQuick(T* pItems, TInt count){
      InnerQuickSort(pItems, 0, count - 1);
   }
   //------------------------------------------------------------
   // <T>对数据集合进行快速排序。</T>
   static void SortQuick(T* pItems, TInt count, HComparer hComparer){
      InnerQuickSort(pItems, 0, count - 1, hComparer);
   }
   //------------------------------------------------------------
   // <T>对数据集合进行快速排序。</T>
   static void SortQuickObject(T* pItems, TInt count, HComparerObject hComparer, TAny* pCondition = NULL){
      InnerQuickSortObject(pItems, 0, count - 1, hComparer, pCondition);
   }
   //------------------------------------------------------------
   // <T>对数据集合进行快速排序。</T>
   static void SortQuickValue(T* pItems, TInt count, HComparerValue hComparer, TAny* pCondition = NULL){
      InnerQuickSortValue(pItems, 0, count - 1, hComparer, pCondition);
   }
};
//------------------------------------------------------------
typedef MO_CM_DECLARE RTypes<TBool> RBools;
typedef MO_CM_DECLARE RTypes<TByte> RBytes;
typedef MO_CM_DECLARE RTypes<TInt> RInts;
typedef MO_CM_DECLARE RTypes<TInt8> RInt8s;
typedef MO_CM_DECLARE RTypes<TInt16> RInt16s;
typedef MO_CM_DECLARE RTypes<TInt32> RInt32s;
typedef MO_CM_DECLARE RTypes<TInt64> RInt64s;
typedef MO_CM_DECLARE RTypes<TUint> RUints;
typedef MO_CM_DECLARE RTypes<TUint8> RUint8s;
typedef MO_CM_DECLARE RTypes<TUint16> RUint16s;
typedef MO_CM_DECLARE RTypes<TUint32> RUint32s;
typedef MO_CM_DECLARE RTypes<TUint64> RUint64s;
typedef MO_CM_DECLARE RTypes<TFloat> RFloats;
typedef MO_CM_DECLARE RTypes<TDouble> RDoubles;

//============================================================
// <T>字符集合工具类。</T>
//
// @refer
//============================================================
template <typename T>
class RCharTypes : public RTypes<T>{
public:
   //------------------------------------------------------------
   // <T>获得字符长度。</T>
   MO_INLINE static TInt Length(const T* pValue){
      TInt length = 0;
      if(NULL != pValue){
         while(*pValue++){
            length++;
         }
      }
      return length;
   }
   //------------------------------------------------------------
   // <T>获得字符长度。</T>
   MO_INLINE static TInt IndexsOf(const T* pValue, const T* pFind){
      TInt valueLength = Length(pValue);
      TInt findLength = Length(pFind);
      TInt result = RTypes<T>::IndexsOf(pValue, valueLength, pFind, findLength);
      return result;
   }
   //------------------------------------------------------------
   // <T>获得字符哈希值。</T>
   MO_INLINE static THashCode MakeHashCode(const T* pValue){
      TInt length = Length(pValue);
      return RTypes<T>::MakeHashCode(pValue, length);
   }
public:
   //------------------------------------------------------------
   // <T>判断字符串是否相等。</T>
   MO_INLINE static TBool Equals(const T* pSource, const T* pTarget){
      TBool result = EFalse;
      if((NULL != pSource) && (NULL != pTarget)){
         TInt sourceLength = Length(pSource);
         TInt targetLength = Length(pTarget);
         result = RTypes<T>::Equals(pSource, sourceLength, pTarget, targetLength);
      }
      return result;
   }
};
//------------------------------------------------------------
typedef MO_CM_DECLARE RCharTypes<TChar8> RChar8s;
typedef MO_CM_DECLARE RCharTypes<TChar16> RChar16s;
typedef MO_CM_DECLARE RCharTypes<TChar32> RChar32s;
typedef MO_CM_DECLARE RCharTypes<TChar> RChars;

//============================================================
// <T>基本数字的工具类</T>
//============================================================
template <typename T>
class RNumber{
public:
   //------------------------------------------------------------
   // <T>解析字符串为有符号数字</T>
   template <typename C>
   static MO_INLINE TBool IsSignInteger(const C* pValue){
      // 检查为空
      if(NULL == pValue){
         return EFalse;
      }
      // 检查长度
      TInt length = RCharTypes<C>::Length(pValue);
      if(0 == length){
         return EFalse;
      }
      // 检查内容
      for(TInt n = 0; n < length; n++){
         C value = pValue[n];
         if(' ' == n){
            continue;
         }else if((0 == n) && (('-' == value) || ('+' == value))){
            continue;
         }else if((value >= '0') && (value <= '9')){
            continue;
         }
         return EFalse;
      }
      return ETrue;
   }
   //------------------------------------------------------------
   // <T>解析字符串为有符号数字</T>
   template <typename C>
   static MO_INLINE TBool IsUnsignInteger(const C* pValue){
      // 检查为空
      if(NULL == pValue){
         return EFalse;
      }
      // 检查长度
      TInt length = RCharTypes<C>::Length(pValue);
      if(0 == length){
         return EFalse;
      }
      // 检查内容
      for(TInt n = 0; n < length; n++){
         C value = pValue[n];
         if(' ' == n){
            continue;
         }else if((value >= '0') && (value <= '9')){
            continue;
         }
         return EFalse;
      }
      return ETrue;
   }
public:
   //------------------------------------------------------------
   // <T>解析十进制字符串为数字</T>
   template <typename C>
   static MO_INLINE T ParseSign(const C* pValue){
      T result = 0;
      // 检查为空
      if(NULL == pValue){
         return result;
      }
      // 检查长度
      TInt length = RCharTypes<C>::Length(pValue);
      if(0 == length){
         return result;
      }
      // 转换内容
      TBool negative = EFalse;
      TBool hasNumber = EFalse;
      // 处理字符集合
      for(TInt n = 0; n < length; n++){
         C value = pValue[n];
         // 不处理空格
         if(' ' == value){
            continue;
         }
         // 处理负号
         if('-' == value){
            if(hasNumber){
               MO_STATIC_FATAL(TC("Input value is invalid. (value=%s)"), pValue);
            }
            negative = ETrue;
            continue;
         }
         // 检查数字
         if((value < '0') || (value > '9')){
            MO_STATIC_FATAL(TC("Input value is not number. (value=%s)"), pValue);
         }
         // 计算结果
         hasNumber = ETrue;
         result *= 10;
         result += value - '0';
      }
      // 处理负数
      if(negative){
         result = -result;
      }
      return result;
   }
   //------------------------------------------------------------
   // <T>解析十进制字符串为数字</T>
   template <typename C>
   static MO_INLINE T ParseSign(const C* pValue, T defaultValue){
      // 检查为空
      if(NULL == pValue){
         return defaultValue;
      }
      // 检查长度
      TInt length = RCharTypes<C>::Length(pValue);
      if(0 == length){
         return defaultValue;
      }
      // 转换内容
      T result = 0;
      TBool negative = EFalse;
      TBool hasNumber = EFalse;
      for(TInt n = 0; n < length; n++){
         C value = pValue[n];
         // 不处理空格
         if(' ' == value){
            continue;
         }
         // 处理负号
         if('-' == value){
            if(hasNumber){
               return defaultValue;
            }
            negative = ETrue;
            continue;
         }
         // 检查数字
         if((value < '0') || (value > '9')){
            return defaultValue;
         }
         // 计算结果
         hasNumber = ETrue;
         result *= 10;
         result += value - '0';
      }
      // 处理负数
      if(negative){
         result = -result;
      }
      return result;
   }
   //------------------------------------------------------------
   // <T>解析十进制字符串为数字</T>
   template <typename C>
   static MO_INLINE T ParseUnsign(const C* pValue){
      T result = 0;
      // 检查为空
      if(NULL == pValue){
         return result;
      }
      // 检查长度
      TInt length = RCharTypes<C>::Length(pValue);
      if(0 == length){
         return result;
      }
      // 转换内容
      for(TInt n = 0; n < length; n++){
         C value = pValue[n];
         // 不处理空格
         if(' ' == value){
            continue;
         }
         // 处理负号
         if('-' == value){
            MO_STATIC_FATAL(TC("Input value is invalid. (value=%s)"), pValue);
         }
         // 检查数字
         if((value < '0') || (value > '9')){
            MO_STATIC_FATAL(TC("Input value is not number. (value=%s)"), pValue);
         }
         // 计算结果
         result *= 10;
         result += value - '0';
      }
      return result;
   }
   //------------------------------------------------------------
   // <T>解析十进制字符串为数字</T>
   template <typename C>
   static MO_INLINE T ParseUnsign(const C* pValue, T defaultValue){
      // 检查为空
      if(NULL == pValue){
         return defaultValue;
      }
      // 检查长度
      TInt length = RCharTypes<C>::Length(pValue);
      if(0 == length){
         return defaultValue;
      }
      // 转换内容
      T result = 0;
      for(TInt n = 0; n < length; n++){
         C value = pValue[n];
         // 不处理空格
         if(' ' == value){
            continue;
         }
         // 处理负号
         if('-' == value){
            return defaultValue;
         }
         // 检查数字
         if((value < '0') || (value > '9')){
            return defaultValue;
         }
         // 计算结果
         result *= 10;
         result += value - '0';
      }
      return result;
   }
   //------------------------------------------------------------
   // <T>解析十六进制字符串为数字</T>
   template <typename C>
   static MO_INLINE T ParseSignHex(const C* pValue){
      T result = 0;
      // 检查为空
      if(NULL == pValue){
         return result;
      }
      // 检查长度
      TInt length = RCharTypes<C>::Length(pValue);
      if(0 == length){
         return result;
      }
      // 转换内容
      TBool negative = EFalse;
      TBool hasNumber = EFalse;
      // 处理字符集合
      for(TInt n = 0; n < length; n++){
         C value = pValue[n];
         // 不处理空格
         if(' ' == value){
            continue;
         }
         // 处理负号
         if('-' == value){
            if(hasNumber){
               MO_STATIC_FATAL(TC("Input value is invalid. (value=%s)"), pValue);
            }
            negative = ETrue;
            continue;
         }
         // 变换数字
         TInt temp = -1;
         if((value >= '0') && (value <= '9')){
            temp = value - '0';
         }else if((value >= 'a') && (value <= 'f')){
            temp = value - 'a' + 10;
         }else if((value >= 'A') && (value <= 'F')){
            temp = value - 'A' + 10;
         }
         // 检查数字
         if((temp < 0) || (temp > 15)){
            MO_STATIC_FATAL(TC("Input value is not hex. (value=%s)"), pValue);
         }
         // 计算结果
         hasNumber = ETrue;
         result <<= 4;
         result += temp;
      }
      // 处理负数
      if(negative){
         result = -result;
      }
      return result;
   }
   //------------------------------------------------------------
   // <T>解析十六进制字符串为数字</T>
   template <typename C>
   static MO_INLINE T ParseSignHex(const C* pValue, T defaultValue){
      // 检查为空
      if(NULL == pValue){
         return defaultValue;
      }
      // 检查长度
      TInt length = RCharTypes<C>::Length(pValue);
      if(0 == length){
         return defaultValue;
      }
      // 转换内容
      T result = 0;
      TBool negative = EFalse;
      TBool hasNumber = EFalse;
      for(TInt n = 0; n < length; n++){
         C value = pValue[n];
         // 不处理空格
         if(' ' == value){
            continue;
         }
         // 处理负号
         if('-' == value){
            if(hasNumber){
               return defaultValue;
            }
            negative = ETrue;
            continue;
         }
         // 变换数字
         TInt temp = -1;
         if((value >= '0') && (value <= '9')){
            temp = value - '0';
         }else if((value >= 'a') && (value <= 'f')){
            temp = value - 'a' + 10;
         }else if((value >= 'A') && (value <= 'F')){
            temp = value - 'A' + 10;
         }
         // 检查数字
         if((temp < 0) || (temp > 15)){
            return defaultValue;
         }
         // 计算结果
         hasNumber = ETrue;
         result <<= 4;
         result += temp;
      }
      // 处理负数
      if(negative){
         result = -result;
      }
      return result;
   }
      //------------------------------------------------------------
   // <T>解析十六进制字符串为数字</T>
   template <typename C>
   static MO_INLINE T ParseUnsignHex(const C* pValue){
      T result = 0;
      // 检查为空
      if(NULL == pValue){
         return result;
      }
      // 检查长度
      TInt length = RCharTypes<C>::Length(pValue);
      if(0 == length){
         return result;
      }
      // 转换内容
      for(TInt n = 0; n < length; n++){
         C value = pValue[n];
         // 不处理空格
         if(' ' == value){
            continue;
         }
         // 处理负号
         if('-' == value){
            MO_STATIC_FATAL(TC("Input value is invalid. (value=%s)"), pValue);
         }
         // 变换数字
         TInt temp = -1;
         if((value >= '0') && (value <= '9')){
            temp = value - '0';
         }else if((value >= 'a') && (value <= 'f')){
            temp = value - 'a' + 10;
         }else if((value >= 'A') && (value <= 'F')){
            temp = value - 'A' + 10;
         }
         // 检查数字
         if((temp < 0) || (temp > 15)){
            MO_STATIC_FATAL(TC("Input value is not hex. (value=%s)"), pValue);
         }
         // 计算结果
         result <<= 4;
         result += temp;
      }
      return result;
   }
   //------------------------------------------------------------
   // <T>解析十六进制字符串为数字</T>
   template <typename C>
   static MO_INLINE T ParseUnsignHex(const C* pValue, T defaultValue){
      // 检查为空
      if(NULL == pValue){
         return defaultValue;
      }
      // 检查长度
      TInt length = RCharTypes<C>::Length(pValue);
      if(0 == length){
         return defaultValue;
      }
      // 转换内容
      T result = 0;
      for(TInt n = 0; n < length; n++){
         C value = pValue[n];
         // 不处理空格
         if(' ' == value){
            continue;
         }
         // 处理负号
         if('-' == value){
            return defaultValue;
         }
         // 变换数字
         TInt temp = -1;
         if((value >= '0') && (value <= '9')){
            temp = value - '0';
         }else if((value >= 'a') && (value <= 'f')){
            temp = value - 'a' + 10;
         }else if((value >= 'A') && (value <= 'F')){
            temp = value - 'A' + 10;
         }
         // 检查数字
         if((temp < 0) || (temp > 15)){
            return defaultValue;
         }
         // 计算结果
         result <<= 4;
         result += temp;
      }
      return result;
   }
public:
   //------------------------------------------------------------
   // <T>格式化数字为十进制字符串</T>
   template <typename C, typename U>
   static MO_INLINE const C* ToSignString(C* pBuffer, TInt capacity, T value){
      // 检查为空
      if(NULL == pBuffer){
         MO_STATIC_FATAL(TC("Conver buffer is null. (buffer=0x%08X, capacity=%d, value=%d)"), pBuffer, capacity, value);
         return pBuffer;
      }
      // 检查容量
      if(0 == capacity){
         MO_STATIC_FATAL(TC("Conver buffer length is not enought. (buffer=0x%08X, capacity=%d, value=%d)"), pBuffer, capacity, value);
         return pBuffer;
      }
      // 如果为0
      if(0 == value){
         pBuffer[0] = TC('0');
         pBuffer[1] = 0;
         return pBuffer;
      }
      // 如果为负数
      C* pWriter = pBuffer;
      TInt position = 0;
      U data = value;
      if(value < 0){
         MO_ASSERT(capacity > 1);
         data = -value;
         *pWriter++ = '-';
         position++;
      }
      // 输出到字符串
      TInt index = MO_FS_NUMBER_LENGTH;
      C buffer[MO_FS_NUMBER_LENGTH];
      while(data > 0){
         buffer[--index] = (data % 10) + TC('0');
         data /= 10;
      }
      // 反转字符串
      while((position++ < capacity) && (index < MO_FS_NUMBER_LENGTH)){
         *pWriter++ = buffer[index++];
      }
      *pWriter++ = 0;
      // 检查容量
      if(position > capacity){
         MO_STATIC_FATAL(TC("Conver buffer length is not enought. (buffer=0x%08X, capacity=%d, value=%d)"), pBuffer, capacity, value);
         return pBuffer;
      }
      return pBuffer;
   }
   //------------------------------------------------------------
   // <T>格式化数字为十进制字符串</T>
   template <typename C>
   static MO_INLINE const C* ToUnsignString(C* pBuffer, TInt capacity, T value){
      // 检查为空
      if(NULL == pBuffer){
         MO_STATIC_FATAL(TC("Conver buffer is null. (buffer=0x%08X, capacity=%d, value=%d)"), pBuffer, capacity, value);
         return pBuffer;
      }
      // 检查容量
      if(0 == capacity){
         MO_STATIC_FATAL(TC("Conver buffer length is not enought. (buffer=0x%08X, capacity=%d, value=%d)"), pBuffer, capacity, value);
         return pBuffer;
      }
      // 如果为0
      if(0 == value){
         pBuffer[0] = TC('0');
         pBuffer[1] = 0;
         return pBuffer;
      }
      // 输出到字符串
      C* pWriter = pBuffer;
      TInt index = MO_FS_NUMBER_LENGTH;
      C buffer[MO_FS_NUMBER_LENGTH];
      while(value > 0){
         buffer[--index] = (value % 10) + TC('0');
         value /= 10;
      }
      // 反转字符串
      TInt position = 0;
      while((position++ < capacity) && (index < MO_FS_NUMBER_LENGTH)){
         *pWriter++ = buffer[index++];
      }
      *pWriter++ = 0;
      // 检查容量
      if(position > capacity){
         MO_STATIC_FATAL(TC("Conver buffer length is not enought. (buffer=0x%08X, capacity=%d, value=%d)"), pBuffer, capacity, value);
         return pBuffer;
      }
      return pBuffer;
   }
   //------------------------------------------------------------
   // <T>格式化数字为十六进制字符串</T>
   template <typename C>
   static MO_INLINE TCharC* ToHexString(C* pBuffer, TInt capacity, T value){
      // 检查为空
      if(NULL == pBuffer){
         MO_STATIC_FATAL(TC("Conver buffer is null. (buffer=0x%08X, capacity=%d, value=%d)"), pBuffer, capacity, value);
         return pBuffer;
      }
      // 检查容量
      if(0 == capacity){
         MO_STATIC_FATAL(TC("Conver buffer length is not enought. (buffer=0x%08X, capacity=%d, value=%d)"), pBuffer, capacity, value);
         return pBuffer;
      }
      // 如果为0
      if(0 == value){
         pBuffer[0] = TC('0');
         pBuffer[1] = 0;
         return pBuffer;
      }
      // 输出到字符串
      C* pWriter = pBuffer;
      TInt position = 0;
      TInt index = MO_FS_NUMBER_LENGTH;
      C buffer[MO_FS_NUMBER_LENGTH];
      while(value > 0){
         TInt temp = value & 0x0F;
         if(temp > 9){
            buffer[--index] = temp + 'A';
         }else{
            buffer[--index] = temp + '0';
         }
         value >>= 4;
      }
      // 反转字符串
      while((position++ < capacity) && (index < MO_FS_NUMBER_LENGTH)){
         *pWriter++ = buffer[index++];
      }
      *pWriter++ = 0;
      // 检查容量
      if(position > capacity){
         MO_STATIC_FATAL(TC("Conver buffer length is not enought. (buffer=0x%08X, capacity=%d, value=%d)"), pBuffer, capacity, value);
         return pBuffer;
      }
      return pBuffer;
   }
};

//============================================================
// <T>布尔值操作的引用类。</T>
//============================================================
class MO_CM_DECLARE RBool : public RType<TBool>{
public:
   static TBool IsTrue(TChar value);
   static TBool IsTrue(TCharC* pValue, TBool defaultValue = EFalse);
public:
   static TBool Parse(TChar value);
   static TBool Parse(TCharC* pValue, TBool defaultValue = EFalse);
   static TBool ParseNvl(TCharC* pValue, TBool defaultValue = EFalse);
public:
   static TChar ToChar(TBool value);
   static TCharC* ToString(TBool value);
   static TCharC* ToDisplay(TBool value);
};

//============================================================
// <T>字节操作的引用类。</T>
//============================================================
class MO_CM_DECLARE RByte : public RType<TByte>{
public:
   static TChar HEX_CHARS[16];
   static TByte HEX_BYTES[256];
public:
   static TSize Parse(TCharC* pSource, TSize length, TByte* pBytes, TSize capacity);
public:
   static TCharC* Format(TByteC* pBytes, TInt length, TChar* pSource, TSize capacity);
   static TCharC* FormatDisplay(TByteC* pBytes, TInt length, TChar* pSource, TSize capacity);
   static TCharC* FormatMemory(TByteC* pBytes, TInt count, TInt columns, TChar* pResult, TInt length);
public:
   static TCharC* Dump(TByteC* pBytes, TInt count, TChar* pResult, TInt length);
};

//============================================================
// <T>8位整数操作的引用类。</T>
//============================================================
class MO_CM_DECLARE RInt8 : public RType<TInt8>{
public:
   static TBool Test(TCharC* pSource);
public:
   static TInt8 Parse8(TChar8C* pSource);
   static TInt8 Parse16(TChar16C* pSource);
   static TInt8 Parse32(TChar32C* pSource);
   static TInt8 Parse(TCharC* pSource, TInt8 defaultValue = 0);
   static TInt8 ParseNvl(TCharC* pSource);
   static TInt8 Parse2(TCharC* pSource);
   static TInt8 ParseHex(TCharC* pSource, TInt length = -1);
   static TInt8 ParseHexNvl(TCharC* pSource, TInt length = -1);
public:
   static TCharC* ToString(TInt8 value, TChar* pBuffer, TInt capacity);
   static TCharC* ToString2(TInt8 value, TChar* pBuffer, TInt capacity);
   static TCharC* ToHexString(TInt8 value, TChar* pBuffer, TInt capacity);
};

//============================================================
// <T>16位整数操作的引用类。</T>
//============================================================
class MO_CM_DECLARE RInt16 : public RType<TInt16>{
public:
   static TBool Test(TCharC* pSource);
public:
   static TInt16 Parse8(TChar8C* pValue);
   static TInt16 Parse16(TChar16C* pValue);
   static TInt16 Parse32(TChar32C* pValue);
   static TInt16 Parse(TCharC* pValue, TInt16 defaultValue = 0);
   static TInt16 ParseNvl(TCharC* pValue);
   static TInt16 ParseHex(TCharC* pSource, TInt length = -1);
   static TInt16 ParseHexNvl(TCharC* pSource, TInt length = -1);
public:
   static TCharC* ToString(TInt16 value, TChar* pBuffer, TInt capacity);
   static TCharC* ToHexString(TInt16 value, TChar* pBuffer, TInt capacity);
};

//============================================================
// <T>32位整数操作的引用类。</T>
//============================================================
class MO_CM_DECLARE RInt32 : public RType<TInt32>{
public:
   static TBool Test(TCharC* pSource);
public:
   static TInt32 Parse8(TChar8C* pValue);
   static TInt32 Parse16(TChar16C* pValue);
   static TInt32 Parse32(TChar32C* pValue);
   static TInt32 Parse(TCharC* pValue);
   static TInt32 ParseNvl(TCharC* pValue);
   static TInt32 ParseHex(TCharC* pSource, TInt length = -1);
   static TInt32 ParseHexNvl(TCharC* pSource, TInt length = -1);
public:
   static TCharC* ToString(TInt32 value, TChar* pBuffer, TInt capacity);
   static TCharC* ToHexString(TInt32 value, TChar* pBuffer, TInt capacity);
};

//============================================================
// <T>64位整数操作的引用类。</T>
//============================================================
class MO_CM_DECLARE RInt64 : public RType<TInt64>{
public:
   static TBool Test(TCharC* pSource);
public:
   static TInt64 Parse8(TChar8C* pValue);
   static TInt64 Parse16(TChar16C* pValue);
   static TInt64 Parse32(TChar32C* pValue);
   static TInt64 Parse(TCharC* pValue);
   static TInt64 ParseNvl(TCharC* pValue);
   static TInt64 ParseHex(TCharC* pSource, TInt length = -1);
   static TInt64 ParseHexNvl(TCharC* pSource, TInt length = -1);
public:
   static TChar8C* ToString8(TInt64 value, TChar8* pBuffer, TInt capacity);
   static TChar16C* ToString16(TInt64 value, TChar16* pBuffer, TInt capacity);
   static TChar32C* ToString32(TInt64 value, TChar32* pBuffer, TInt capacity);
   static TCharC* ToString(TInt64 value, TChar* pBuffer, TInt capacity);
   static TCharC* ToHexString(TInt64 value, TChar* pBuffer, TInt capacity);
};

//============================================================
// <T>8位无符号整数操作的引用类。</T>
//============================================================
class MO_CM_DECLARE RUint8 : public RType<TUint8>{
public:
   static TBool Test(TCharC* pSource);
public:
   static TUint8 Parse8(TChar8C* pValue);
   static TUint8 Parse16(TChar16C* pValue);
   static TUint8 Parse32(TChar32C* pValue);
   static TUint8 Parse(TCharC* pValue);
   static TUint8 ParseNvl(TCharC* pValue);
   static TUint8 ParseHex(TCharC* pSource, TInt length = -1);
   static TUint8 ParseHexNvl(TCharC* pSource, TInt length = -1);
public:
   static TCharC* ToString(TUint8 value, TChar* pBuffer, TInt capacity);
   static TCharC* ToHexString(TUint8 value, TChar* pBuffer, TInt capacity);
};

//============================================================
// <T>16位无符号整数操作的引用类。</T>
//============================================================
class MO_CM_DECLARE RUint16 : public RType<TUint16>{
public:
   static TBool Test(TCharC* pValue);
public:
   static TUint16 Parse8(TChar8C* pValue);
   static TUint16 Parse16(TChar16C* pValue);
   static TUint16 Parse32(TChar32C* pValue);
   static TUint16 Parse(TCharC* pValue);
   static TUint16 ParseNvl(TCharC* pValue, TUint16 defaultValue = 0);
   static TUint16 ParseHex(TCharC* pSource, TInt length = -1);
   static TUint16 ParseHexNvl(TCharC* pSource, TInt length = -1);
public:
   static TCharC* ToString(TUint16 value, TChar* pBuffer, TInt capacity);
   static TCharC* ToHexString(TUint16 value, TChar* pBuffer, TInt capacity);
};

//============================================================
// <T>32位无符号整数操作的引用类。</T>
//============================================================
class MO_CM_DECLARE RUint32 : public RType<TUint32>{
public:
   static TBool Test(TCharC* pValue);
public:
   static TUint32 Parse8(TChar8C* pValue);
   static TUint32 Parse16(TChar16C* pValue);
   static TUint32 Parse32(TChar32C* pValue);
   static TUint32 Parse(TCharC* pValue);
   static TUint32 ParseNvl(TCharC* pValue);
   static TUint32 ParseHex(TCharC* pSource, TInt length = -1);
   static TUint32 ParseHexNvl(TCharC* pSource, TInt length = -1);
public:
   static TCharC* ToString(TUint32 value, TChar* pBuffer, TInt capacity);
   static TCharC* ToHexString(TUint32 value, TChar* pBuffer, TInt capacity);
};

//============================================================
// <T>64位无符号整数操作的引用类。</T>
//============================================================
class MO_CM_DECLARE RUint64 : public RType<TUint64>{
public:
   static TBool Test(TCharC* pValue);
public:
   static TUint64 Parse8(TChar8C* pValue);
   static TUint64 Parse16(TChar16C* pValue);
   static TUint64 Parse32(TChar32C* pValue);
   static TUint64 Parse(TCharC* pValue);
   static TUint64 ParseNvl(TCharC* pValue);
   static TUint64 ParseHex(TCharC* pSource, TInt length = -1);
   static TUint64 ParseHexNvl(TCharC* pSource, TInt length = -1);
public:
   static TChar8C* ToString8(TUint64 value, TChar8* pBuffer, TInt capacity);
   static TChar16C* ToString16(TUint64 value, TChar16* pBuffer, TInt capacity);
   static TChar32C* ToString32(TUint64 value, TChar32* pBuffer, TInt capacity);
   static TCharC* ToString(TUint64 value, TChar* pBuffer, TInt capacity);
   static TCharC* ToHexString(TUint64 value, TChar* pBuffer, TInt capacity);
};

//============================================================
// <T>浮点数操作的引用类。</T>
// <P>字节数：4Byte, HEX字符数：8字符。</P>
//============================================================
class MO_CM_DECLARE RFloat : public RType<TFloat>{
public:
   static TBool IsFloat(TCharC* pValue);
public:
   static TFloat InRange(TFloat value, TFloat min, TFloat max);
public:
   static TFloat Parse8(TChar8C* pValue);
   static TFloat Parse16(TChar16C* pValue);
   static TFloat Parse32(TChar32C* pValue);
   static TFloat Parse(TCharC* pValue);
   static TFloat ParseNvl(TCharC* pValue);
   static TFloat ParseHex(TCharC* pValue);
   static TFloat ParseHexNvl(TCharC* pValue);
public:
   static TChar8C* ToString8(TFloat value, TChar8* pBuffer, TInt capacity);
   static TChar16C* ToString16(TFloat value, TChar16* pBuffer, TInt capacity);
   static TChar32C* ToString32(TFloat value, TChar32* pBuffer, TInt capacity);
   static TCharC* ToString(TFloat value, TChar* pBuffer, TInt capacity);
   static TCharC* ToHexString(TFloat value, TChar* pBuffer, TInt capacity);
};

//============================================================
// <T>双精度数工具类。</T>
// <P>字节数：8Byte, HEX字符数：16字符。</P>
//============================================================
class MO_CM_DECLARE RDouble : public RType<TDouble>{
public:
   static TBool IsDouble(TCharC* pValue);
public:
   static TDouble InRange(TDouble value, TDouble min, TDouble max);
public:
   static TDouble Parse8(TChar8C* pValue);
   static TDouble Parse16(TChar16C* pValue);
   static TDouble Parse32(TChar32C* pValue);
   static TDouble Parse(TCharC* pValue);
   static TDouble ParseNvl(TCharC* pValue);
   static TDouble ParseHex(TCharC* pValue);
   static TDouble ParseHexNvl(TCharC* pValue);
public:
   static TChar8C* ToString8(TDouble value, TChar8* pBuffer, TInt capacity);
   static TChar16C* ToString16(TDouble value, TChar16* pBuffer, TInt capacity);
   static TChar32C* ToString32(TDouble value, TChar32* pBuffer, TInt capacity);
   static TCharC* ToString(TDouble value, TChar* pBuffer, TInt capacity);
   static TCharC* ToHexString(TDouble value, TChar* pBuffer, TInt capacity);
};

//============================================================
// <T>8位字符工具类。</T>
//
// @refer
//============================================================
class MO_CM_DECLARE RChar8 : public RType<TChar8>{
public:
   static TBool IsAlphaNumber(TChar8 value);
   static TBool IsAlpha(TChar8 value);
   static TBool IsControl(TChar8 value);
   static TBool IsDigit(TChar8 value);
   static TBool IsDigitX(TChar8 value);
   static TBool IsLower(TChar8 value);
   static TBool IsUpper(TChar8 value);
   static TBool IsGraphics(TChar8 value);
   static TBool IsPrint(TChar8 value);
   static TBool IsPunct(TChar8 value);
   static TBool IsSpace(TChar8 value);
public:
   static TInt CompareIgnoreCase(TChar8C* pSource, TChar8C* pTarget);
public:
   static void ToLower(TChar8* pValues, TInt length);
   static void ToUpper(TChar8* pValues, TInt length);
public:
   static TInt ConvertTo16(TChar8C* pValue);
   static TInt ConvertTo16(TChar16* pOutput, TSize size, TChar8C* pValue);
   static TInt ConvertTo32(TChar8C* pValue);
   static TInt ConvertTo32(TChar32* pOutput, TSize size, TChar8C* pValue);
   static TInt ConvertToUtf8(TChar8C* pValue);
   static TInt ConvertToUtf8(TChar16* pOutput, TSize size, TChar8C* pValue);
};

//============================================================
// <T>16位字符工具类。</T>
//
// @refer
//============================================================
class MO_CM_DECLARE RChar16 : public RType<TChar16>{
public:
   static TBool IsAlphaNumber(TChar16 value);
   static TBool IsAlpha(TChar16 value);
   static TBool IsControl(TChar16 value);
   static TBool IsDigit(TChar16 value);
   static TBool IsDigitX(TChar16 value);
   static TBool IsLower(TChar16 value);
   static TBool IsUpper(TChar16 value);
   static TBool IsGraphics(TChar16 value);
   static TBool IsPrint(TChar16 value);
   static TBool IsPunct(TChar16 value);
   static TBool IsSpace(TChar16 value);
public:
   static TInt CompareIgnoreCase(TChar16C* pSource, TChar16C* pTarget);
public:
   static void ToLower(TChar16* pValues, TInt length);
   static void ToUpper(TChar16* pValues, TInt length);
public:
   static TInt ConvertTo8(TChar16C* pValue);
   static TInt ConvertTo8(TChar8* pOutput, TSize size, TChar16C* pValue);
   static TInt ConvertTo32(TChar16C* pValue);
   static TInt ConvertTo32(TChar32* pOutput, TSize size, TChar16C* pValue);
   static TInt ConvertToUtf8(TChar16C* pValue);
   static TInt ConvertToUtf8(TChar8* pOutput, TSize size, TChar16C* pValue);
};

//============================================================
// <T>32位字符工具类。</T>
//
// @refer
//============================================================
class MO_CM_DECLARE RChar32 : public RType<TChar32>{
public:
   static TBool IsAlphaNumber(TChar32 value);
   static TBool IsAlpha(TChar32 value);
   static TBool IsControl(TChar32 value);
   static TBool IsDigit(TChar32 value);
   static TBool IsDigitX(TChar32 value);
   static TBool IsLower(TChar32 value);
   static TBool IsUpper(TChar32 value);
   static TBool IsGraphics(TChar32 value);
   static TBool IsPrint(TChar32 value);
   static TBool IsPunct(TChar32 value);
   static TBool IsSpace(TChar32 value);
public:
   static TInt CompareIgnoreCase(TChar32C* pSource, TChar32C* pTarget);
public:
   static void ToLower(TChar32* pValues, TInt length);
   static void ToUpper(TChar32* pValues, TInt length);
public:
   static TInt ConvertTo8(TChar32C* pValue);
   static TInt ConvertTo8(TChar8* pOutput, TSize size, TChar32C* pValue);
   static TInt ConvertTo16(TChar32C* pValue);
   static TInt ConvertTo16(TChar16* pOutput, TSize size, TChar32C* pValue);
};

//============================================================
// <T>引用数据集合。</T>
//
// @history 110113 MAOCY 创建
//============================================================
template <typename T>
class TRfrTypes{
protected:
   T* _pMemory;
   TUint16 _size;
   TUint16 _length;
public:
   //------------------------------------------------------------
   // <T>构造引用数据集合。</T>
   TRfrTypes(){
      _length = 0;
      _size = 0;
      MO_CLEAR(_pMemory);
   }
   //------------------------------------------------------------
   // <T>构造引用数据集合。</T>
   TRfrTypes(T* pMemory, TInt size){
      _pMemory = pMemory;
      _size = size;
      _length = 0;
   }
public:
   //------------------------------------------------------------
   // <T>转成指针类型。</T>
   MO_INLINE operator const T*() const{
      return _pMemory;
   }
   //------------------------------------------------------------
   // <T>转成指针类型。</T>
   MO_INLINE operator T*(){
      return _pMemory;
   }
   //------------------------------------------------------------
   // <T>获得索引位置的数据。</T>
   MO_INLINE const T& operator[](TInt index) const{
      MO_ASSERT_RANGE(index, 0, _length);
      return _pMemory[index];
   }
   //------------------------------------------------------------
   // <T>获得索引位置的数据。</T>
   MO_INLINE T& operator[](TInt index){
      MO_ASSERT_RANGE(index, 0, _length);
      return _pMemory[index];
   }
public:
   //------------------------------------------------------------
   // <T>判断是否为空。</T>
   MO_INLINE TBool IsEmpty() const{
      return (0 == _length);
   }
   //------------------------------------------------------------
   // <T>获得数据长度。</T>
   MO_INLINE TInt Length() const{
      return _length;
   }
   //------------------------------------------------------------
   // <T>获得内部数据指针。</T>
   MO_INLINE const T* MemoryC() const{
      return _pMemory;
   }
   //------------------------------------------------------------
   // <T>获得内部数据指针。</T>
   MO_INLINE T* Memory(){
      return _pMemory;
   }
   //------------------------------------------------------------
   // <T>获得索引位置的数据。</T>
   MO_INLINE const T& Get(TInt index) const{
      MO_ASSERT_RANGE(index, 0, _length);
      return _pMemory[index];
   }
   //------------------------------------------------------------
   // <T>获得索引位置的数据。</T>
   MO_INLINE T& Get(TInt index){
      MO_ASSERT_RANGE(index, 0, _length);
      return _pMemory[index];
   }
public:
   //------------------------------------------------------------
   // <T>设置数据引用。</T>
   void Attach(T* pMemory, TInt size){
      _pMemory = pMemory;
      _size = size;
      _length = 0;
   }
   //------------------------------------------------------------
   // <T>追加数据内容。</T>
   void Appand(const T* pMemory, TInt length){
      MO_ASSERT(_length + length < _size);
      memcpy(_pMemory + _length, pMemory, sizeof(T) * length);
      _length += length;
   }
   //------------------------------------------------------------
   // <T>增加一个数据。</T>
   void Push(const T& value){
      MO_ASSERT(_length + 1 < _size);
      _pMemory[_length++] = value;
   }
   //------------------------------------------------------------
   // <T>重置内部数据。</T>
   void Reset(){
      _length = 0;
   }
};

//============================================================
// <T>引用对象集合。</T>
//
// @history 110113 MAOCY 创建
//============================================================
template <typename T>
class TRfrObjects{
protected:
   T* _pItems;
   TUint16 _size;
   TUint16 _count;
public:
   //------------------------------------------------------------
   // <T>构造引用对象集合。</T>
   TRfrObjects(){
      _count = 0;
      _size = 0;
      MO_CLEAR(_pItems);
   }
   //------------------------------------------------------------
   // <T>构造引用对象集合。</T>
   TRfrObjects(T* pItems, TInt size){
      _pItems = pItems;
      _size = size;
      _count = 0;
   }
public:
   //------------------------------------------------------------
   // <T>转成指针类型。</T>
   MO_INLINE operator const T*() const{
      return _pItems;
   }
   //------------------------------------------------------------
   // <T>转成指针类型。</T>
   MO_INLINE operator T*(){
      return _pItems;
   }
   //------------------------------------------------------------
   // <T>获得索引位置的数据。</T>
   MO_INLINE const T& operator[](TInt index) const{
      MO_ASSERT_RANGE(index, 0, _count);
      return _pItems[index];
   }
   //------------------------------------------------------------
   // <T>获得索引位置的数据。</T>
   MO_INLINE T& operator[](TInt index){
      MO_ASSERT_RANGE(index, 0, _count);
      return _pItems[index];
   }
public:
   //------------------------------------------------------------
   // <T>判断是否为空。</T>
   MO_INLINE TBool IsEmpty() const{
      return (0 == _count);
   }
   //------------------------------------------------------------
   // <T>获得数据长度。</T>
   MO_INLINE TInt Count() const{
      return _count;
   }
   //------------------------------------------------------------
   // <T>获得内部数据指针。</T>
   MO_INLINE const T* MemoryC() const{
      return _pItems;
   }
   //------------------------------------------------------------
   // <T>获得内部数据指针。</T>
   MO_INLINE T* Memory(){
      return _pItems;
   }
   //------------------------------------------------------------
   // <T>获得索引位置的数据。</T>
   MO_INLINE const T& Get(TInt index) const{
      MO_ASSERT_RANGE(index, 0, _count);
      return _pItems[index];
   }
   //------------------------------------------------------------
   // <T>获得索引位置的数据。</T>
   MO_INLINE T& Get(TInt index){
      MO_ASSERT_RANGE(index, 0, _count);
      return _pItems[index];
   }
public:
   //------------------------------------------------------------
   // <T>设置数据引用。</T>
   MO_INLINE void Attach(T* pItems, TInt size){
      _pItems = pItems;
      _size = size;
      _count = 0;
   }
   //------------------------------------------------------------
   // <T>追加数据内容。</T>
   MO_INLINE void Appand(const T* pItems, TInt count){
      MO_ASSERT(_count + count < _size);
      memcpy(_pItems + _count, pItems, sizeof(T) * count);
      _count += count;
   }
   //------------------------------------------------------------
   // <T>增加一个数据。</T>
   MO_INLINE void Push(const T& value){
      MO_ASSERT(_count + 1 < _size);
      _pItems[_count++] = value;
   }
   //------------------------------------------------------------
   // <T>重置内部数据。</T>
   MO_INLINE void Reset(){
      _count = 0;
   }
};

//============================================================
// <T>只读节点迭代器。</T>
//
// @tool
// @history 100318 MAOCY 创建
//============================================================
template <typename T>
class TStackIteratorC{
protected:
   T* _pMemory;
   TInt _length;
   TInt _index;
public:
   //------------------------------------------------------------
   // <T>构造只读迭代器。</T>
   TStackIteratorC(){
      _pMemory = NULL;
      _length = 0;
      _index = -1;
   }
   //------------------------------------------------------------
   // <T>构造只读迭代器。</T>
   TStackIteratorC(const TStackIteratorC& iterator){
      _pMemory = iterator._pMemory;
      _length = iterator._length;
      _index = iterator._index;
   }
   //------------------------------------------------------------
   // <T>构造只读迭代器。</T>
   TStackIteratorC(T* pMemory, TInt length){
      _pMemory = pMemory;
      _length = length;
      _index = -1;
   }
public:
   //------------------------------------------------------------
   // <T>获得当前位置的数据内容。</T>
   MO_INLINE const TBool operator==(T value) const{
      MO_ASSERT(_pMemory);
      MO_ASSERT_RANGE(_index, 0, _length);
      return (_pMemory[_index] == value);
   }
   //------------------------------------------------------------
   // <T>获得当前位置的数据内容。</T>
   MO_INLINE const T& operator *() const{
      MO_ASSERT(_pMemory);
      MO_ASSERT_RANGE(_index, 0, _length);
      return _pMemory[_index];
   }
   //------------------------------------------------------------
   // <T>获得当前位置的数据内容。</T>
   MO_INLINE const T* operator->() const{
      MO_ASSERT(_pMemory);
      MO_ASSERT_RANGE(_index, 0, _length);
      return &_pMemory[_index];
   }
public:
   //------------------------------------------------------------
   // <T>当前节点是否含有数据。</T>
   MO_INLINE TBool IsEmpty(){
      return (0 == _length);
   }
   //------------------------------------------------------------
   // <T>判断数据内容是否相等。</T>
   MO_INLINE TBool Equals(T value){
      MO_ASSERT(_pMemory);
      MO_ASSERT_RANGE(_index, 0, _length);
      return (_pMemory[_index] == value);
   }
   //------------------------------------------------------------
   // <T>获得当前位置的数据内容。</T>
   MO_INLINE T& Get(){
      MO_ASSERT(_pMemory);
      MO_ASSERT_RANGE(_index, 0, _length);
      return _pMemory[_index];
   }
   //------------------------------------------------------------
   // <T>判断是否存在下一个位置。</T>
   MO_INLINE TBool HasNext(){
      return (_length > 0) ? _index + 1 < _length : EFalse;
   }
   //------------------------------------------------------------
   //<T> 移动到下一个位置。</T>
   MO_INLINE TBool Next(){
      if(_length > 0 && (_index + 1 < _length)){
         _index++;
         return ETrue;
      }
      return EFalse;
   }
   //------------------------------------------------------------
   // <T>判断是否存在下一个位置。</T>
   MO_INLINE TBool HasPrior(){
      return (_length > 0) ? _index - 1 >= 0 : EFalse;
   }
   //------------------------------------------------------------
   // <T>移动到上一个位置。</T>
   MO_INLINE TBool Prior(){
      if(_length > 0 && (_index + 1 < _length)){
         _index--;
         return ETrue;
      }
      return EFalse;
   }
   //------------------------------------------------------------
   // <T>重置位置。</T>
   MO_INLINE void Reset(){
      _index = -1;
   }
};

//============================================================
// <T>定长栈数据集合。</T>
//
// @history 100318 MAOCY 创建
//============================================================
template <typename T, TInt S>
class TStackTypes{
public:
   typedef TInt (*HComparer) (const T&, const T&);
   typedef TStackIteratorC<T> TIteratorC;
protected:
   TInt _length;
#ifdef _MO_DEBUG
   T _memory[S];
#else
   TByte _memory[sizeof(T) * S];
#endif // _MO_DEBUG
public:
   //------------------------------------------------------------
   // <T>构造定长栈数据集合。</T>
   TStackTypes(){
      _length = 0;
   }
public:
   //------------------------------------------------------------
   // <T>获得只读数据指针。</T>
   MO_INLINE operator const T*() const{
      return (T*)_memory;
   }
   //------------------------------------------------------------
   // <T>获得数据指针。</T>
   MO_INLINE operator T*(){
      return (T*)_memory;
   }
   //------------------------------------------------------------
   // <T>获得索引位置的只读数据。</T>
   MO_INLINE const T& operator[](TInt index) const{
      MO_ASSERT_RANGE(index, 0, S);
      return ((T*)_memory)[index];
   }
   //------------------------------------------------------------
   // <T>获得索引位置的数据。</T>
   MO_INLINE T& operator[](TInt index){
      MO_ASSERT_RANGE(index, 0, S);
      return ((T*)_memory)[index];
   }
public:
   //------------------------------------------------------------
   // <T>判断是否为空。</T>
   MO_INLINE TBool IsEmpty() const{
      return (0 == _length);
   }
   //------------------------------------------------------------
   // <T>判断是否已满。</T>
   MO_INLINE TBool IsFull() const{
      return (S == _length);
   }
   //------------------------------------------------------------
   // <T>获得数据长度。</T>
   MO_INLINE TSize Length() const{
      return _length;
   }
   //------------------------------------------------------------
   // <T>获得数据容量。</T>
   MO_INLINE static TInt Size(){
      return S;
   }
   //------------------------------------------------------------
   // <T>获得只读数据指针。</T>
   MO_INLINE const T* MemoryC() const{
      return (T*)_memory;
   }
   //------------------------------------------------------------
   // <T>获得数据指针。</T>
   MO_INLINE T* Memory(){
      return (T*)_memory;
   }
   //------------------------------------------------------------
   // <T>获得只读首部数据。</T>
   MO_INLINE const T& FirstC() const{
      MO_ASSERT(_length > 0);
      T* pMemory = (T*)_memory;
      return pMemory[0];
   }
   //------------------------------------------------------------
   // <T>获得首部数据。</T>
   MO_INLINE T& First(){
      MO_ASSERT(_length > 0);
      T* pMemory = (T*)_memory;
      return pMemory[0];
   }
   //------------------------------------------------------------
   // <T>获得只读尾部数据。</T>
   MO_INLINE const T& LastC() const{
      MO_ASSERT(_length > 0);
      T* pMemory = (T*)_memory;
      return pMemory[_length - 1];
   }
   //------------------------------------------------------------
   // <T>获得尾部数据。</T>
   MO_INLINE T& Last(){
      MO_ASSERT(_length > 0);
      T* pMemory = (T*)_memory;
      return pMemory[_length - 1];
   }
   //------------------------------------------------------------
   // <T>获得索引位置的只读数据。</T>
   MO_INLINE const T& GetC(TInt index) const{
      MO_ASSERT_RANGE(index, 0, S);
      T* pMemory = (T*)_memory;
      return pMemory[index];
   }
   //------------------------------------------------------------
   // <T>获得索引位置的数据。</T>
   MO_INLINE T& Get(TInt index){
      MO_ASSERT_RANGE(index, 0, S);
      T* pMemory = (T*)_memory;
      return pMemory[index];
   }
   //------------------------------------------------------------
   // <T>获得只读迭代器。</T>
   MO_INLINE TIteratorC IteratorC(){
      return TIteratorC((T*)_memory, _length);
   }
public:
   //------------------------------------------------------------
   // <T>获得只读指针。</T>
   MO_INLINE TPtrC<T> PtrC(){
      return TPtrC<T>((T*)_memory, _length);
   }
   //------------------------------------------------------------
   // <T>获得指针。</T>
   MO_INLINE TPtr<T> Ptr(){
      return TPtr<T>((T*)_memory, &_length, S);
   }
public:
   //------------------------------------------------------------
   // <T>判断是否相等。</T>
   MO_INLINE TBool Equals(const T* pMemory, TInt length){
      MO_ASSERT(pMemory);
      if(_length != length){
         return EFalse;
      }
      return RTypes<T>::Equals((T*)_memory, pMemory, length);
   }
   //------------------------------------------------------------
   // <T>判断是否相等。</T>
   MO_INLINE TBool Equals(const TStackTypes<T, S>& value){
      return Equals(value.MemoryC(), value.Length());
   }
   //------------------------------------------------------------
   // <T>设置数据长度。</T>
   MO_INLINE void SetLength(TInt length){
      MO_ASSERT_BETWEEN(length, 0, S);
      _length = length;
   }
   //------------------------------------------------------------
   // <T>设置数据的确保长度。</T>
   MO_INLINE void EnsureLength(TInt length){
      MO_ASSERT_BETWEEN(length, 0, S);
      if(length > _length){
         _length = length;
      }
   }
public:
   //------------------------------------------------------------
   // <T>接受外部数据。</T>
   MO_INLINE void Assign(const T* pMemory, TInt length){
      if(NULL != pMemory){
         if((_length < 0) || (_length > S) || (length < 0) || (length > S)){
            MO_FATAL(TC("Assign range is invalid. (current=%d, assign=%d, size=%d)"), _length, length, S);
         }else{
            MO_LIB_TYPES_COPY(T, (T*)_memory, S, pMemory, length);
            _length = length;
         }
      }else{
         MO_FATAL(TC("Assign memory is null."));
      }
   }
   //------------------------------------------------------------
   // <T>接受外部数据。</T>
   MO_INLINE void Assign(TStackTypes<T, S>& values){
      Assign(values.MemoryC(), values.Length());
   }
   //------------------------------------------------------------
   // <T>追加外部数据到尾部。</T>
   MO_INLINE void Append(const T* pMemory, TInt length){
      // 检查自身对象合法性
      if((_length < 0) || (_length > S) || (_length < 0) || (_length > S)){
         MO_FATAL(TC("This is invalid. (length=%d, size=%d)"), _length, S);
         return;
      }
      // 追加外部数据
      if(NULL != pMemory){
         if((length < 0) || (_length + length > S)){
            MO_FATAL(TC("Append range is invalid. (current=%d, assign=%d, size=%d)"), _length, length, S);
         }else{
            T* pTarget = (T*)_memory;
            MO_LIB_TYPES_COPY(T, pTarget + _length, S - _length, pMemory, length);
            _length += length;
         }
      }else{
         MO_FATAL(TC("Append memory is null."));
      }
   }
   //------------------------------------------------------------
   // <T>追加外部数据到尾部。</T>
   MO_INLINE void Append(TStackTypes<T, S>& values){
      Append(values.MemoryC(), values.Length());
   }
   //------------------------------------------------------------
   // <T>收集一个空数据。</T>
   MO_INLINE T& Alloc(){
      T* pMemory = (T*)_memory;
      if((_length < 0) || (_length >= S)){
         T& value = pMemory[S - 1];
         MO_FATAL(TC("Alloc memory full. (length=%d, size=%d)"), _length, S);
         return value;
      }else{
         T& value = pMemory[_length++];
         MO_LIB_TYPE_CLEAR(T, &value);
         return value;
      }
   }
   //------------------------------------------------------------
   // <T>收集一个指定索引位置的空数据。</T>
   MO_INLINE T& Alloc(TInt index){
      T* pMemory = (T*)_memory;
      if((_length < 0) || (_length > S) || (index < 0) || (index >= S)){
         T& value = pMemory[S - 1];
         MO_FATAL(TC("Alloc index memory invalid. (index=%d, length=%d, size=%d)"), index, _length, S);
         return value;
      }else{
         T& value = pMemory[index++];
         MO_LIB_TYPE_CLEAR(T, &value);
         if(index > _length){
            _length = index;
         }
         return value;
      }
   }
   //------------------------------------------------------------
   // <T>推入一个数值到尾部。</T>
   MO_INLINE void Push(T& value){
      if((_length < 0) || (_length >= S)){
         MO_FATAL(TC("Push memory full. (length=%d, size=%d)"), _length, S);
      }else{
         T* pMemory = (T*)_memory;
         MO_LIB_TYPE_COPY(T, pMemory + _length, &value);
         _length++;
      }
   }
   //------------------------------------------------------------
   // <T>删除一个指定索引位置的数据。</T>
   MO_INLINE TBool Delete(TInt index, T* pValue = NULL){
      // 检查参数
      if((_length < 0) || (_length >= S) || (index < 0) || (index >= _length)){
         MO_FATAL(TC("Delete index is invalid. (index=%d, length=%d, size=%d)"), index, _length, S);
         return EFalse;
      }
      // 复制数据
      T* pMemory = (T*)_memory;
      if(NULL != pValue){
         MO_LIB_TYPE_COPY(T, pValue, pMemory + index);
      }
      // 删除数据
      TInt size = _length - index - 1;
      if(size > 0){
         MO_LIB_TYPES_MOVE(T, pMemory + index, size, pMemory + index + 1, size);
      }
      _length--;
      return ETrue;
   }
   //------------------------------------------------------------
   // <T>删除首部数据。</T>
   MO_INLINE TBool RemoveFirst(T* pValue = NULL){
      if(_length > 0){
         return Delete(0, pValue);
      }
      return EFalse;
   }
   //------------------------------------------------------------
   // <T>删除尾部数据。</T>
   MO_INLINE TBool RemoveLast(T* pValue = NULL){
      if(_length > 0){
         return Delete(_length - 1, pValue);
      }
      return EFalse;
   }
   //------------------------------------------------------------
   // <T>清空内部。</T>
   MO_INLINE void Clear(){
      _length = 0;
   }
   //------------------------------------------------------------
   // <T>重置内部。</T>
   MO_INLINE void Reset(){
      _length = 0;
   }
};

//============================================================
// <T>定长栈字符串。</T>
//
// @history 100902 MAOCY 创建
//============================================================
template <TInt S>
class TStackString : public TStackTypes<TChar, S>{
public:
   //------------------------------------------------------------
   // <T>构造定长栈字符串。</T>
   TStackString(){
      Set(NULL);
   }
   //------------------------------------------------------------
   // <T>构造定长栈字符串。</T>
   TStackString(TCharC* pValue){
      Set(pValue);
   }
public:
   //------------------------------------------------------------
   MO_INLINE void operator=(TCharC* pValue){
      Set(pValue);
   }
public:
   //------------------------------------------------------------
   // <T>获得当前容积，保留一个尾结束字符。</T>
   MO_INLINE TSize Capacity(){
      TSize capacity = sizeof(TUint16) + 1;
      capacity += sizeof(TChar) * this->_length;
      return capacity;
   }
   //------------------------------------------------------------
   // <T>复制信息到自己内部。</T>
   MO_INLINE void Assign(const TStackString<S>& value){
      Set((TChar*)value._memory);
      this->_length = value._length;
   }
   //------------------------------------------------------------
   // <T>追加一个字符串到当前字符串对象的末尾。</T>
   // <P>如果长度小于0，则自动计算指针的数据长度。</P>
   MO_INLINE void Append(TCharC* pValue, TInt length = -1){
      // 检查指针
      if(NULL == pValue){
         return;
      }
      // 获取长度
      if(length <= 0){
         length = RTypes<TChar>::IndexOf(pValue, 0);
      }
      // 检查长度
      if(this->_length + length + 1 >= S){
         MO_FATAL("Append string length invalid. (length=%d, size=%d, value=%d:%s)", this->_length, S, length, pValue);
         length = S - this->_length - 1;
      }
      // 复制数据
      if(length > 0){
         TChar* pMemory = (TChar*)this->_memory;
         MO_LIB_TYPES_COPY(TChar, pMemory + this->_length, S - this->_length, pValue, length);
         this->_length += length;
         pMemory[this->_length] = 0;
      }
   }
   //------------------------------------------------------------
   // <T>追加一个格式化字符串到对象尾部。</T>
   MO_INLINE void AppendFormat(TCharC* pFormat, ...){
      // 格式化可变参数字符串信息
      va_list params;
      va_start(params, pFormat);
      // 输出日志信息
   #ifdef _MO_WINDOWS
      TInt length = _vscprintf(pFormat, params);
      MO_ASSERT_RANGE(this->_length + length + 1, 0, S);
      length = vsprintf_s(_pMemory + _length, length + 1, pFormat, params);
      this->_length += length;
   #else
      TChar buffer[MO_FS_SPRINT_LENGTH];
      vsnprintf(buffer, MO_FS_SPRINT_LENGTH, pFormat, params);
      Append(buffer);
   #endif
      va_end(params);
   }
   // ------------------------------------------------------------
   // 追加一个格式字符串
   MO_INLINE void AppendFormat(TCharC* pFormat, va_list& params){
#ifdef _MO_WINDOWS
      TInt length = _vscprintf(pFormat, params);
      MO_ASSERT_RANGE(this->_length + length + 1, 0, S);
      length = vsprintf_s(_pMemory + _length, length + 1, pFormat, params);
      this->_length += length;
#else
      TChar buffer[MO_FS_SPRINT_LENGTH];
      vsnprintf(buffer, MO_FS_SPRINT_LENGTH, pFormat, params);
      Append(buffer);
#endif
   }
   //------------------------------------------------------------
   // <T>设置内部数据。</T>
   MO_INLINE void Set(TCharC* pValue){
      TInt length = 0;
      if(NULL != pValue){
         length = RTypes<TChar>::IndexOf(pValue, 0);
         if(length >= S){
            MO_FATAL("Set string length invalid. (size=%d, value=%d:%s)", S, length, pValue);
            length = S - 1;
         }
         if(length > 0){
            MO_LIB_TYPES_COPY(TChar, this->_memory, S, pValue, length);
         }
      }
      this->_length = length;
      ((TChar*)this->_memory)[this->_length] = 0;
   }
   //------------------------------------------------------------
   // <T>序列化内部数据到数据区。</T>
   // <P>多保存一个结尾空字符。</P>
   MO_INLINE TInt Serialize(TAny* pMemory){
      TByte* pPtr = (TByte*)pMemory;
      *(TUint16*)pPtr = this->_length;
      TInt total = sizeof(TChar) * this->_length;
      if(total > 0){
         MO_LIB_MEMORY_COPY(pPtr + sizeof(TUint16), total, this->_memory, total);
      }
      return sizeof(TUint16) + total;
   }
   //------------------------------------------------------------
   // <T>反序列化数据区到内部数据。</T>
   MO_INLINE TInt Unserialize(TAnyC* pMemory){
      TByteC* pPtr = (TByteC*)pMemory;
      this->_length = *(TUint16*)pPtr;
      TInt total = sizeof(TChar) * this->_length;
      if(total > 0){
         MO_LIB_MEMORY_COPY(this->_memory, S, pPtr + sizeof(TUint16), total);
      }
      MO_ASSERT(this->_length + 1 < S);
      ((TChar*)this->_memory)[this->_length] = 0;
      return sizeof(TUint16) + total;
   }
   //------------------------------------------------------------
   // <T>修正数据。</T>
   MO_INLINE void Fix(){
      this->_length = RTypes<TChar>::IndexOf((TChar*)this->_memory, 0);
   }
   //------------------------------------------------------------
   // <T>输出内部数据到外部。</T>
   MO_INLINE TCharC* ToString(TChar* pValue, TInt capacity){
      MO_ASSERT(pValue);
      MO_ASSERT(capacity > this->_length + 1);
      MO_LIB_TYPES_COPY(TChar, pValue, capacity, this->_memory, this->_length + 1);
      return pValue;
   }
   //------------------------------------------------------------
   // <T>清空数据。</T>
   MO_INLINE void Clear(){
      this->_length = 0;
      ((TChar*)this->_memory)[0] = 0;
   }
   //------------------------------------------------------------
   // <T>重置数据。</T>
   MO_INLINE void Reset(){
      this->_length = 0;
      ((TChar*)this->_memory)[0] = 0;
   }
};

//============================================================
// <T>定长栈对象集合</T>
//
// @history 100318 MAOCY 创建
//============================================================
template <typename T, TInt S>
class TStackObjects{
public:
   typedef TInt (*HComparer) (const T&, const T&);
   typedef TStackIteratorC<T> TIteratorC;
protected:
   TInt _count;
#ifdef _MO_DEBUG
   T _items[S];
#else
   TByte _items[sizeof(T) * S];
#endif // _MO_DEBUG
public:
   //------------------------------------------------------------
   // <T>构造定长栈对象集合。</T>
   TStackObjects(){
      _count = 0;
   }
public:
   //------------------------------------------------------------
   // <T>转成指针类型。</T>
   MO_INLINE operator const T*() const{
      return (T*)_items;
   }
   //------------------------------------------------------------
   // <T>转成指针类型。</T>
   MO_INLINE operator T*(){
      return (T*)_items;
   }
   //------------------------------------------------------------
   // <T>获得索引位置的数据。</T>
   MO_INLINE const T& operator[](TInt index) const{
      MO_ASSERT_RANGE(index, 0, S);
      return ((T*)_items)[index];
   }
   //------------------------------------------------------------
   // <T>获得索引位置的数据。</T>
   MO_INLINE T& operator[](TInt index){
      MO_ASSERT_RANGE(index, 0, S);
      return ((T*)_items)[index];
   }
public:
   //------------------------------------------------------------
   // <T>判断是否为空。</T>
   MO_INLINE TBool IsEmpty() const{
      return (0 == _count);
   }
   //------------------------------------------------------------
   // <T>判断是否不为空。</T>
   MO_INLINE TBool IsNotEmpty() const{
      return (_count > 0);
   }
   //------------------------------------------------------------
   // <T>判断是否已满。</T>
   MO_INLINE TBool IsFull() const{
      return (S == _count);
   }
   //------------------------------------------------------------
   // <T>判断是否未满。</T>
   MO_INLINE TBool IsNotFull() const{
      return (_count < S);
   }
   //------------------------------------------------------------
   // <T>获得数据总数。</T>
   MO_INLINE TInt Count() const{
      return _count;
   }
   //------------------------------------------------------------
   // <T>获得数据容量。</T>
   MO_INLINE static TInt Size(){
      return S;
   }
   //------------------------------------------------------------
   // <T>获得只读内部数据指针。</T>
   MO_INLINE const T* ItemsC() const{
      return (T*)_items;
   }
   //------------------------------------------------------------
   // <T>获得内部数据指针。</T>
   MO_INLINE T* Items(){
      return (T*)_items;
   }
   //------------------------------------------------------------
   // <T>获得只读首部数据。</T>
   MO_INLINE const T& FirstC() const{
      MO_ASSERT(_count > 0);
      T* pItems = (T*)_items;
      return pItems[0];
   }
   //------------------------------------------------------------
   // <T>获得首部数据。</T>
   MO_INLINE T& First(){
      MO_ASSERT(_count > 0);
      T* pItems = (T*)_items;
      return pItems[0];
   }
   //------------------------------------------------------------
   // <T>获得只读尾部数据。</T>
   MO_INLINE const T& LastC() const{
      MO_ASSERT(_count > 0);
      T* pItems = (T*)_items;
      return pItems[_count - 1];
   }
   //------------------------------------------------------------
   // <T>获得只读尾部数据。</T>
   MO_INLINE T& Last(){
      MO_ASSERT(_count > 0);
      T* pItems = (T*)_items;
      return pItems[_count - 1];
   }
   //------------------------------------------------------------
   // <T>获得索引位置的数据。</T>
   MO_INLINE const T& GetC(TInt index) const{
      MO_ASSERT_RANGE(index, 0, S);
      T* pItems = (T*)_items;
      return pItems[index];
   }
   //------------------------------------------------------------
   // <T>获得索引位置的数据。</T>
   MO_INLINE T& Get(TInt index){
      MO_ASSERT_RANGE(index, 0, S);
      T* pItems = (T*)_items;
      return pItems[index];
   }
   //------------------------------------------------------------
   // <T>获得只读迭代器。</T>
   MO_INLINE TIteratorC IteratorC(){
      return TIteratorC((T*)_items, _count);
   }
public:
   //------------------------------------------------------------
   // <T>是否相等。</T>
   MO_INLINE TBool Equals(const T* pItems, TInt count){
      MO_ASSERT(pItems);
      if(_count != count){
         return EFalse;
      }
      return RTypes<T>::Equals((T*)_items, pItems, count);
   }
   //------------------------------------------------------------
   // <T>设置数据的长度。</T>
   MO_INLINE void SetCount(TInt count){
      MO_ASSERT_BETWEEN(count, 0, S);
      _count = count;
   }
   //------------------------------------------------------------
   // <T>设置数据的确保长度。</T>
   MO_INLINE void EnsureCount(TInt count){
      MO_ASSERT_BETWEEN(count, 0, S);
      if(count > _count){
         _count = count;
      }
   }
   //------------------------------------------------------------
   // <T>使用排序器对内部对象进行排序。</T>
   MO_INLINE void Sort(HComparer hComparer){
      RTypes<T>::SortQuick((T*)_items, _count, hComparer);
   }
public:
   //------------------------------------------------------------
   // <T>接受外部数据。</T>
   MO_INLINE void Assign(const T* pItems, TInt count){
      if(NULL != pItems){
         if((_count < 0) || (_count > S) || (count < 0) || (count > S)){
            MO_FATAL("Assign range is invalid. (current=%d, assign=%d, size=%d)", _count, count, S);
         }else{
            MO_LIB_TYPES_COPY(T, (T*)_items, S, pItems, count);
            _count = count;
         }
      }else{
         MO_FATAL("Assign memory is null.");
      }
   }
   //------------------------------------------------------------
   // <T>接受外部数据。</T>
   MO_INLINE void Assign(TStackObjects<T, S>& objects){
      Assign(objects.ItemsC(), objects.Count());
   }
   //------------------------------------------------------------
   // <T>追加外部数据到尾部。</T>
   MO_INLINE void Append(const T* pItems, TInt count){
      if(NULL != pItems){
         if((_count < 0) || (_count > S) || (count < 0) || (_count + count > S)){
            MO_FATAL("Append range is invalid. (current=%d, assign=%d, size=%d)", _count, count, S);
         }else{
            T* pTarget = (T*)_items;
            MO_LIB_TYPES_COPY(T, pTarget + _count, S - _count, pItems, count);
            _count += count;
         }
      }else{
         MO_FATAL("Append memory is null.");
      }
   }
   //------------------------------------------------------------
   // <T>追加外部数据到尾部。</T>
   MO_INLINE void Append(TStackObjects<T, S>& objects){
      Append(objects.ItemsC(), objects.Count());
   }
   //------------------------------------------------------------
   // <T>收集一个空数据。</T>
   MO_INLINE T& Alloc(){
      T* pItems = (T*)_items;
      if((_count < 0) || (_count >= S)){
         T& value = pItems[S - 1];
         MO_FATAL("Alloc memory full. (count=%d, size=%d)", _count, S);
         return value;
      }else{
         T& value = pItems[_count++];
         MO_LIB_TYPE_CLEAR(T, &value);
         return value;
      }
   }
   //------------------------------------------------------------
   // <T>收集一个指定索引位置的空数据。</T>
   MO_INLINE T& Alloc(TInt index){
      T* pItems = (T*)_items;
      if((_count < 0) || (_count > S) || (index < 0) || (index >= S)){
         T& value = pItems[S - 1];
         MO_FATAL("Alloc index memory invalid. (index=%d, count=%d, size=%d)", index, _count, S);
         return value;
      }else{
         T& value = pItems[index++];
         MO_LIB_TYPE_CLEAR(T, &value);
         if(index > _count){
            _count = index;
         }
         return value;
      }
   }
   //------------------------------------------------------------
   // <T>推入一个数值到尾部。</T>
   MO_INLINE void Push(T& value){
      if((_count < 0) || (_count >= S)){
         MO_FATAL("Push memory full. (count=%d, size=%d)", _count, S);
      }else{
         T* pItems = (T*)_items;
         MO_LIB_TYPE_COPY(T, pItems + _count, &value);
         _count++;
      }
   }
   //------------------------------------------------------------
   // <T>删除一个指定索引位置的数据。</T>
   MO_INLINE TBool Delete(TInt index, T* pValue = NULL){
      // 检查参数
      if((_count < 0) || (_count > S) || (index < 0) || (index >= _count)){
         MO_FATAL("Delete index is invalid. (index=%d, count=%d, size=%d)", index, _count, S);
         return EFalse;
      }
      // 复制数据
      T* pItems = (T*)_items;
      if(NULL != pValue){
         MO_LIB_TYPE_COPY(T, pValue, pItems + index);
      }
      // 删除数据
      TInt size = _count - index - 1;
      if(size > 0){
         MO_LIB_TYPES_MOVE(T, pItems + index, size, pItems + index + 1, size);
      }
      _count--;
      return ETrue;
   }
   //------------------------------------------------------------
   // <T>删除首部数据。</T>
   MO_INLINE TBool RemoveFirst(T* pValue = NULL){
      if(_count > 0){
         return Delete(0, pValue);
      }
      return EFalse;
   }
   //------------------------------------------------------------
   // <T>删除尾部数据。</T>
   MO_INLINE TBool RemoveLast(T* pValue = NULL){
      if(_count > 0){
         return Delete(_count - 1, pValue);
      }
      return EFalse;
   }
   //------------------------------------------------------------
   // <T>清空内部。</T>
   MO_INLINE void Clear(){
      _count = 0;
   }
   //------------------------------------------------------------
   // <T>重置内部。</T>
   MO_INLINE void Reset(){
      _count = 0;
   }
};

MO_NAMESPACE_END

#endif // __MO_CM_TYPE_H__
