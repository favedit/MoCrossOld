//============================================================
// <T>枚举工具</T>
//============================================================
#ifndef __MO_CM_ENUMERATOR_H__
#define __MO_CM_ENUMERATOR_H__

#ifndef __MO_CM_CLASS_H__
#include "MoCmClass.h"
#endif // __MO_CM_CLASS_H__

MO_NAMESPACE_BEGIN

//============================================================
// <T>枚举项目。</T>
//
// @history 140514 MAOCY 创建
//============================================================
class MO_CM_DECLARE FEnumeratorItem : public FInstance
{
   MO_CLASS_DECLARE_INHERITS(FEnumeratorItem, FInstance);
protected:
   TInt _code;
   TString _name;
   TString _description;
public:
   FEnumeratorItem();
   MO_ABSTRACT ~FEnumeratorItem();
public:
   //------------------------------------------------------------
   // <T>获得代码。</T>
   MO_INLINE TInt Code(){
      return _code;
   }
   //------------------------------------------------------------
   // <T>设置代码。</T>
   MO_INLINE void SetCode(TInt code){
      _code = code;
   }
   //------------------------------------------------------------
   // <T>判断是否指定名称。</T>
   MO_INLINE TBool IsName(TCharC* pName){
      return _name.Equals(pName);
   }
   //------------------------------------------------------------
   // <T>获得名称。</T>
   MO_INLINE TCharC* Name(){
      return _name;
   }
   //------------------------------------------------------------
   // <T>设置名称。</T>
   MO_INLINE void SetName(TCharC* pName){
      _name = pName;
   }
   //------------------------------------------------------------
   // <T>获得描述。</T>
   MO_INLINE TCharC* Description(){
      return _description;
   }
   //------------------------------------------------------------
   // <T>设置描述。</T>
   MO_INLINE void SetDescription(TCharC* pDescription){
      _description = pDescription;
   }
public:
   TResult Dump(MString* pDump);
};
//------------------------------------------------------------
typedef MO_CM_DECLARE GPtrs<FEnumeratorItem> GEnumeratorItemPtrs;

//============================================================
// <T>枚举器。</T>
//
// @history 140514 MAOCY 创建
//============================================================
class MO_CM_DECLARE FEnumerator : public FInstance
{
   MO_CLASS_DECLARE_INHERITS(FEnumerator, FInstance);
protected:
   TString _name;
   GEnumeratorItemPtrs _items;
public:
   FEnumerator();
   MO_ABSTRACT ~FEnumerator();
public:
   //------------------------------------------------------------
   // <T>判断是否指定名称。</T>
   MO_INLINE TBool IsName(TCharC* pName){
      return _name.Equals(pName);
   }
   //------------------------------------------------------------
   // <T>获得名称。</T>
   MO_INLINE TCharC* Name(){
      return _name;
   }
   //------------------------------------------------------------
   // <T>设置名称。</T>
   MO_INLINE void SetName(TCharC* pName){
      _name = pName;
   }
public:
   MO_ABSTRACT TResult Setup();
public:
   FEnumeratorItem* FindByCode(TInt code);
   FEnumeratorItem* FindByName(TCharC* pName);
public:
   TInt Parse(TCharC* pName);
   TCharC* Format(TInt code);
public:
   TResult Register(FEnumeratorItem* pItem);
   TResult Unrgister(FEnumeratorItem* pItem);
public:
   MO_ABSTRACT TResult Dump(MString* pDump);
};
//------------------------------------------------------------
typedef MO_CM_DECLARE GPtrDictionary<FEnumerator> GEnumeratorDictionary;

//============================================================
// <T>枚举器控制台。</T>
//
// @history 140514 MAOCY 创建
//============================================================
class FEnumeratorConsole : public FConsole
{
   MO_CLASS_DECLARE_INHERITS(FEnumeratorConsole, FConsole);
protected:
   GEnumeratorDictionary _enumerators;
public:
   FEnumeratorConsole();
   MO_ABSTRACT ~FEnumeratorConsole();
public:
   //------------------------------------------------------------
   // <T>获得枚举集合。</T>
   MO_INLINE GEnumeratorDictionary& Enumerators(){
      return _enumerators;
   }
public:
   //------------------------------------------------------------
   // <T>根据名称获得枚举。</T>
   MO_INLINE FEnumerator* Find(TCharC* pName){
      FEnumerator* pEnumerator = _enumerators.Find(pName);
      if(pEnumerator == NULL){
         return NULL;
      }
      return pEnumerator;
   }
   //------------------------------------------------------------
   // <T>根据名称获得枚举。</T>
   template<class T>
   MO_INLINE T* Find(TCharC* pName){
      FEnumerator* pEnumerator = _enumerators.Find(pName);
      if(pEnumerator == NULL){
         return NULL;
      }
      return pEnumerator->Convert<T>();
   }
public:
   MO_ABSTRACT TResult Register(FEnumerator* pEnumerator);
   MO_ABSTRACT TResult Unrgister(FEnumerator* pEnumerator);
};

//============================================================
// <T>枚举器管理器。</T>
//
// @history 140514 MAOCY 创建
//============================================================
class REnumeratorManager : public RSingleton<FEnumeratorConsole>{
public:
   static FEnumerator* Find(TCharC* pName);
public:
   //------------------------------------------------------------
   // <T>根据名称获得枚举。</T>
   template<class T>
   static MO_INLINE T* Find(TCharC* pName){
      FEnumerator* pEnumerator = Find(pName);
      if(pEnumerator == NULL){
         return NULL;
      }
      return pEnumerator->Convert<T>();
   }
};
   
MO_NAMESPACE_END

#endif // __MO_CM_ENUMERATOR_H__
