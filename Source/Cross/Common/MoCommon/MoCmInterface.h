#ifndef __MO_CM_INTERFACE_H__
#define __MO_CM_INTERFACE_H__

#ifndef __MO_CM_RUNTIME_H__
#include "MoCmRuntime.h"
#endif // __MO_CM_RUNTIME_H__

MO_NAMESPACE_INCLUDE;

//============================================================
// <T>删除内存。</T>
//
// @param pMemory 内存指针
//============================================================
MO_INLINE void operator delete(TAny* pMemory){
   free(pMemory);
}

//============================================================
// <T>删除内存。</T>
//
// @param pMemory 内存指针
// @param pFileName 文件名称
// @param lineNumber 文件行号
//============================================================
MO_INLINE void operator delete(TAny* pMemory, TCharC* pFileName, TInt lineNumber){
   free(pMemory);
}

//============================================================
// <T>删除数组内存。</T>
//
// @param pMemory 数组内存
//============================================================
MO_INLINE void operator delete[](TAny* pMemory){
   free(pMemory);
}

//============================================================
// <T>删除数组内存。</T>
//
// @param pMemory 内存指针
// @param pFileName 文件名称
// @param lineNumber 文件行号
//============================================================
MO_INLINE void operator delete[](TAny* pMemory, TCharC* pFileName, TInt lineNumber){
   free(pMemory);
}

MO_NAMESPACE_BEGIN

//============================================================
// <T>基础接口。</T>
//
// @history 130227 MAOCY 创建
//============================================================
class MO_CM_DECLARE IBase{
public:
   //------------------------------------------------------------
   // <T>析构基础接口。</T>
   MO_ABSTRACT ~IBase(){
   }
public:
   MO_VIRTUAL THashCode HashCode() const = 0;
};

//============================================================
// <T>构造接口。</T>
//
// @history 130130 MAOCY 创建
//============================================================
class MO_CM_DECLARE IConstruct{
public:
   //------------------------------------------------------------
   // <T>析构构造接口。</T>
   MO_ABSTRACT ~IConstruct(){
   }
public:
   MO_VIRTUAL void Construct() = 0;
};

//============================================================
// <T>自初始化接口。</T>
//============================================================
class MO_CM_DECLARE IInitialize{
public:
   //------------------------------------------------------------
   // <T>析构自初始化接口。</T>
   MO_ABSTRACT ~IInitialize(){
   }
public:
   MO_VIRTUAL TResult Initialize() = 0;
};

//============================================================
// <T>自释放接口。</T>
//============================================================
class MO_CM_DECLARE IReleasable{
public:
   //------------------------------------------------------------
   // <T>析构自释放接口。</T>
   MO_ABSTRACT ~IReleasable(){
   }
public:
   MO_VIRTUAL TResult ReferIncrease() = 0;
   MO_VIRTUAL TResult ReferDecrease() = 0;
   MO_VIRTUAL TResult Release() = 0;
};

//============================================================
// <T>释放接口。</T>
//
// @history 130130 MAOCY 创建
//============================================================
class MO_CM_DECLARE IDispose{
public:
   //------------------------------------------------------------
   // <T>析构释放接口。</T>
   MO_ABSTRACT ~IDispose(){
   }
public:
   MO_VIRTUAL TResult Dispose() = 0;
};

//============================================================
// <T>比较接口。</T>
//============================================================
template <typename T>
class IComparable{
public:
   //------------------------------------------------------------
   // <T>析构比较接口。</T>
   MO_ABSTRACT ~IComparable(){
   }
public:
   MO_VIRTUAL TResult Compare(const IComparable<T>& source) = 0;
};

//============================================================
// <T>静态接口。</T>
//
// @history 130130 MAOCY 创建
//============================================================
class MO_CM_DECLARE IStatic : public IDispose{
public:
   //------------------------------------------------------------
   // <T>析构静态接口。</T>
   MO_ABSTRACT ~IStatic(){
   }
public:
   MO_VIRTUAL TResult Construct() = 0;
   MO_VIRTUAL TResult Dispose() = 0;
};

//============================================================
// <T>运行信息的接口。</T>
//
// @history 130130 MAOCY 创建
//============================================================
class MO_CM_DECLARE IDump{
public:
   //------------------------------------------------------------
   // <T>析构运行信息的接口。</T>
   MO_ABSTRACT ~IDump(){
   }
public:
   MO_VIRTUAL void Dump(TChar* pDump, TInt capacity) = 0;
};

//============================================================
// <T>基础对象。</T>
//
// @history 130130 MAOCY 创建
//============================================================
class MO_CM_DECLARE FBase{
public:
   TAny* operator new(TSize size);
   TAny* operator new(TSize size, TChar8C* pClassName, TChar8C* pFileName, TInt lineNumber);
   TAny* operator new(TSize size, TAny* pMemory);
   TAny* operator new(TSize size, TAny* pMemory, TChar8C* pClassName, TChar8C* pFileName, TInt lineNumber);
   void operator delete(TAny* pMemory);
   void operator delete(TAny* pMemory, TAny* pAlloc);
   void operator delete(TAny* pMemory, TChar8C* pClassName, TChar8C* pFileName, TInt lineNumber);
};

//============================================================
// <T>自由对象。</T>
// <P>从当前类派生的任何类，必须使用(MO_NEW)宏来实例化对象。</P>
//
// @history 130130 MAOCY 创建
//============================================================
class MO_CM_DECLARE FFree{
public:
   FFree();
   MO_ABSTRACT ~FFree();
};

//============================================================
// <T>对象基类。</T>
// <P>从当前类派生的任何类，必须使用(MO_CREATE)宏来实例化对象。</P>
//
// @history 121121 MAOCY 创建
//============================================================
class MO_CM_DECLARE FObject{
public:
   FObject();
   MO_ABSTRACT ~FObject();
public:
   TAny* operator new(TSize size);
   TAny* operator new(TSize size, TChar8C* pClassName, TChar8C* pFileName, TInt lineNumber);
   TAny* operator new(TSize size, TAny* pMemory);
   TAny* operator new(TSize size, TAny* pMemory, TChar8C* pClassName, TChar8C* pFileName, TInt lineNumber);
   void operator delete(TAny* pMemory);
   void operator delete(TAny* pMemory, TAny* pAlloc);
   void operator delete(TAny* pMemory, TChar8C* pClassName, TChar8C* pFileName, TInt lineNumber);
public:
   MO_ABSTRACT THashCode HashCode() const;
};

//============================================================
// <T>托管对象基类。</T>
//
// @history 131202 MAOCY 创建
//============================================================
class MO_CM_DECLARE FManagedObject :
      public FObject,
      public IDispose{
protected:
   TInt _referenceCount;
public:
   FManagedObject();
   MO_ABSTRACT ~FManagedObject();
public:
   //------------------------------------------------------------
   // <T>析构运行信息的接口。</T>
   MO_INLINE TBool IsDisposed(){
      return (_referenceCount == 0);
   }
public:
   //------------------------------------------------------------
   // <T>增加一次引用。</T>
   MO_INLINE void ReferIncrease(){
      _referenceCount++;
   }
   //------------------------------------------------------------
   // <T>增加指定次数的引用。</T>
   MO_INLINE void ReferIncrease(TInt referCount){
      _referenceCount += referCount;
   }
   //------------------------------------------------------------
   // <T>减少一次引用。</T>
   MO_INLINE void ReferDecrease(){
      // 检查变量
      if(_referenceCount <= 0){
         return;
      }
      // 释放处理
      if(_referenceCount == 1){
         Dispose();
      }
      // 减少处理
      _referenceCount--;
   }
   //------------------------------------------------------------
   // <T>减少指定次数的引用。</T>
   MO_INLINE void ReferDecrease(TInt referCount){
      // 检查变量
      if(referCount <= 0){
         return;
      }
      if(_referenceCount <= 0){
         return;
      }
      if(_referenceCount - referCount < 0){
         referCount = _referenceCount;
      }
      // 释放处理
      if(_referenceCount - referCount == 0){
         Dispose();
      }
      // 减少处理
      _referenceCount -= referCount;
   }
public:
   MO_ABSTRACT TResult Dispose();
};

MO_NAMESPACE_END

#endif // __MO_CM_INTERFACE_H__
