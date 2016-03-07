#ifndef __MO_FR_CONTENT2D_H__
#define __MO_FR_CONTENT2D_H__
//************************************************************

#ifndef __MO_FR_COMMON_H__
#include "MoFrCommon.h"
#endif // __MO_FR_COMMON_H__

#define MO_GM_ENV_RESOURCE_ROOT "resource.root"

MO_NAMESPACE_BEGIN

//============================================================
// <T>资源工作器类型。</T>
//============================================================
enum EResourceType{
   EResourceType_Unknown   = 0,
   EResourceType_Group     = 1,
   EResourceType_Shader    = 2,
   EResourceType_Picture   = 3,
   EResourceType_Animation = 4,
   EResourceType_Ground    = 5,
};
typedef TInt32 TResourceType;

//============================================================
// <T>资源标识。</T>
//============================================================
static const TChar pictureStr[] = TC("2p");
static const TChar animationStr[] = TC("2a");
static const TChar groundStr[] = TC("2g");

//============================================================
class FAnimationFrame;
class FAnimationClip;
class FAnimationResource;

//============================================================
typedef FVector<TInt> FIntVector;

//============================================================
// <T>资源。</T>
//============================================================
class MO_FR_DECLARE FResource2d : public FObject{
protected:
   TResourceType _typeCd;
   TInt _code;
   TInt _timeout;
public:
   FResource2d();
   MO_ABSTRACT ~FResource2d();
public:
   //------------------------------------------------------------
   // <T>获得类型。</T>
   MO_INLINE TResourceType TypeCd(){
      return _typeCd;
   }
   //------------------------------------------------------------
   // <T>设置类型。</T>
   MO_INLINE void SetTypeCd(TResourceType typeCd){
      _typeCd = typeCd;
   }
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
   // <T>获得超时。</T>
   MO_INLINE TInt Timeout(){
      return _timeout;
   }
   //------------------------------------------------------------
   // <T>设置超时。</T>
   MO_INLINE void SetTimeout(TInt timeout){
      _timeout = timeout;
   }
public:
   MO_ABSTRACT TBool TestReady();
   MO_ABSTRACT TBool TestValid();
public:
   MO_ABSTRACT TResult Unserialize(IDataInput* pInput);
public:
   MO_ABSTRACT TBool Process();
};
//------------------------------------------------------------
typedef MO_FR_DECLARE FVector<FResource2d*> FResourceVector;
typedef MO_FR_DECLARE FList<FResource2d*> FResourceList;
typedef MO_FR_DECLARE FSet<TResourceId, FResource2d*> FResourceSet;

//============================================================
// <T>图片资源。</T>
//============================================================
class MO_FR_DECLARE FPictureResource : public FResource2d{
protected:
   TBool _optionPadding;
   TBool _optionAlpha;
   SIntSize2 _size;
   SIntPoint2 _validLocation;
   SIntSize2 _validSize;
   SIntPoint2 _barycenter;
   //FBitmap* _pBitmap;
public:
   FPictureResource();
   MO_ABSTRACT ~FPictureResource();
public:
   //------------------------------------------------------------
   // <T>获得大小。</T>
   MO_INLINE SIntSize2& Size(){
      return _size;
   }
   //------------------------------------------------------------
   // <T>获得有效位置。</T>
   MO_INLINE SIntPoint2& ValidLocation(){
      return _validLocation;
   }
   //------------------------------------------------------------
   // <T>获得有效大小。</T>
   MO_INLINE SIntSize2& ValidSize(){
      return _validSize;
   }
   //------------------------------------------------------------
   // <T>获得重心。</T>
   MO_INLINE SIntPoint2& Barycenter(){
      return _barycenter;
   }
   //------------------------------------------------------------
   // <T>获得输出流。</T>
   //MO_INLINE FBitmap* Bitmap(){
   //   return _pBitmap;
   //}
public:
   MO_OVERRIDE TResult Unserialize(IDataInput* pInput);
};

//============================================================
// <T>动画帧。</T>
//============================================================
class MO_FR_DECLARE FAnimationFrame : public FObject{
protected:
   FAnimationResource* _pAnimation;
   FAnimationClip* _pClip;
   TBool _optionEmpty;
   TInt _delay;
   SIntSize2 _size;
   SIntPoint2 _validLocation;
   SIntSize2 _validSize;
   SIntPoint2 _validBarycenter;
   SIntPoint2 _mergeLocation;
   SFloatCoord _coord;
public:
   FAnimationFrame();
   MO_ABSTRACT ~FAnimationFrame();
public:
   //------------------------------------------------------------
   // <T>获得动画对象。</T>
   MO_INLINE FAnimationResource* Animation(){
      return _pAnimation;
   }
   //------------------------------------------------------------
   // <T>设置动画对象。</T>
   MO_INLINE void SetAnimation(FAnimationResource* pAnimation){
      _pAnimation = pAnimation;
   }
   //------------------------------------------------------------
   // <T>获得动画桢对象。</T>
   MO_INLINE FAnimationClip* Clip(){
      return _pClip;
   }
   //------------------------------------------------------------
   // <T>设置动画桢对象。</T>
   MO_INLINE void SetClip(FAnimationClip* pClip){
      _pClip = pClip;
   }
   //------------------------------------------------------------
   // <T>获得是否为空。</T>
   MO_INLINE TBool OptionEmpty(){
      return _optionEmpty;
   }
   //------------------------------------------------------------
   // <T>设置是否为空。</T>
   MO_INLINE void SetOptionEmpty(TBool optionEmpty){
      _optionEmpty = optionEmpty;
   }
   //------------------------------------------------------------
   // <T>获得延时。</T>
   MO_INLINE TInt Delay(){
      return _delay;
   }
   //------------------------------------------------------------
   // <T>设置延时。</T>
   MO_INLINE void SetDelay(TInt delay){
      _delay = delay;
   }
   //------------------------------------------------------------
   // <T>获得尺寸。</T>
   MO_INLINE SIntSize2& Size(){
      return _size;
   }
   //------------------------------------------------------------
   // <T>获得有效位置。</T>
   MO_INLINE SIntPoint2& VaildLocation(){
      return _validLocation;
   }
   //------------------------------------------------------------
   // <T>获得有效尺寸。</T>
   MO_INLINE SIntSize2& ValidSize(){
      return _validSize;
   }
   //------------------------------------------------------------
   // <T>获得有效重心。</T>
   MO_INLINE SIntPoint2& ValidBarycenter(){
      return _validBarycenter;
   }
   //------------------------------------------------------------
   // <T>获得有效位置。</T>
   MO_INLINE SIntPoint2& MergeLocation(){
      return _mergeLocation;
   }
   //------------------------------------------------------------
   // <T>获得纹理。</T>
   MO_INLINE SFloatCoord& Coord(){
      return _coord;
   }
public:
   TBool TestInvalid();
public:
   TResult Unserialize(IDataInput* pInput);
};
//------------------------------------------------------------
typedef MO_FR_DECLARE FVector<FAnimationFrame*> FAnimationFrameVector;

//============================================================
// <T>动画段。</T>
//============================================================
class MO_FR_DECLARE FAnimationClip : public FResource2d{
protected:
   FAnimationResource* _pAnimation;
   TBool _optionData;
   TInt _directionCd;
   TBool _reverseOption;
   TInt _reverseCd;
   TInt _reverseDirection;
   SIntSize2 _size;
   SIntPoint2 _baryCenter;
   TInt _delay;
   FAnimationFrameVector* _pFrames;
public:
   FAnimationClip();
   MO_ABSTRACT ~FAnimationClip();
public:
   //------------------------------------------------------------
   // <T>获得动画对象。</T>
   MO_INLINE FAnimationResource* Animation(){
      return _pAnimation;
   }
   //------------------------------------------------------------
   // <T>设置动画对象。</T>
   MO_INLINE void SetAnimation(FAnimationResource* pAnimation){
      _pAnimation = pAnimation;
   }
   //------------------------------------------------------------
   // <T>获得动画方向。</T>
   MO_INLINE TInt DirectionCd(){
      return _directionCd;
   }
   //------------------------------------------------------------
   // <T>设置动画方向。</T>
   MO_INLINE void SetDirectionCd(TInt directionCd){
      _directionCd = directionCd;
   }
   //------------------------------------------------------------
   // <T>是否有翻转。</T>
   MO_INLINE TBool ReverseOption(){
      return _reverseOption;
   }
   //------------------------------------------------------------
   // <T>设置翻转属性。</T>
   MO_INLINE void SetReverseOption(TBool reverseOption){
      _reverseOption = reverseOption;
   }
   //------------------------------------------------------------
   // <T>获得翻转类型。</T>
   MO_INLINE TInt ReverseCd(){
      return _reverseCd;
   }
   //------------------------------------------------------------
   // <T>设置翻转类型。</T>
   MO_INLINE void SetReverseCd(TInt reverseCd){
      _reverseCd = reverseCd;
   }
   //------------------------------------------------------------
   // <T>获得翻转方向。</T>
   MO_INLINE TInt ReverseDirection(){
      return _reverseDirection;
   }
   //------------------------------------------------------------
   // <T>设置翻转方向。</T>
   MO_INLINE void SetReverseDirection(TInt reverseDirection){
      _reverseDirection = reverseDirection;
   }
   //------------------------------------------------------------
   // <T>获得大小。</T>
   MO_INLINE SIntSize2 Size(){
      return _size;
   }
   //------------------------------------------------------------
   // <T>设置大小。</T>
   MO_INLINE void SetSize(SIntSize2& size){
      _size = size;
   }
   //------------------------------------------------------------
   // <T>获得中心。</T>
   MO_INLINE SIntPoint2 BaryCenter(){
      return _baryCenter;
   }
   //------------------------------------------------------------
   // <T>设置中心。</T>
   MO_INLINE void SetBaryCenter(SIntPoint2& baryCenter){
      _baryCenter = baryCenter;
   }
   //------------------------------------------------------------
   // <T>获得延迟。</T>
   MO_INLINE TInt Delay(){
      return _delay;
   }
   //------------------------------------------------------------
   // <T>获得延迟。</T>
   MO_INLINE void SetDelay(TInt delay){
      _delay = delay;
   }
   //------------------------------------------------------------
   // <T>获得帧集合。</T>
   MO_INLINE FAnimationFrameVector* Frames(){
      return _pFrames;
   }
   //------------------------------------------------------------
   // <T>根据索引获得帧。</T>
   MO_INLINE FAnimationFrame* Frame(TInt index){
      return _pFrames->Get(index);
   }
public:
   TBool TestInvalid();
public:
   TResult Unserialize(IDataInput* pInput);
};
//------------------------------------------------------------
typedef MO_FR_DECLARE FVector<FAnimationClip*> FAnimationClipVector;

//============================================================
// <T>动画资源。</T>
//============================================================
class MO_FR_DECLARE FAnimationResource : public FResource2d{
protected:
   SIntSize2 _size;
   SIntPoint2 _baryCenter;
   SIntSize2 _mergeSize;
   SFloatSize2 _mergeFitSize;
   TInt _spanTotal;
   FIntVector* _pFrames;
   FAnimationClipVector* _pClips;
   //FBitmap* _pBitmap;
public:
   FAnimationResource();
   MO_ABSTRACT ~FAnimationResource();
public:
   //------------------------------------------------------------
   // <T>获得尺寸。</T>
   MO_INLINE SIntSize2& Size(){
      return _size;
   }
   //------------------------------------------------------------
   // <T>获得中心。</T>
   MO_INLINE SIntPoint2& BaryCenter(){
      return _baryCenter;
   }
   //------------------------------------------------------------
   // <T>获得合并尺寸。</T>
   MO_INLINE SIntSize2& MergeSize(){
      return _mergeSize;
   }
   //------------------------------------------------------------
   // <T>获得合并适合尺寸。</T>
   MO_INLINE SFloatSize2& MergeFitSize(){
      return _mergeFitSize;
   }
   //------------------------------------------------------------
   // <T>获得帧信息。</T>
   MO_INLINE FIntVector* Frames(){
      return _pFrames;
   }
   //------------------------------------------------------------
   // <T>获得剪辑集合。</T>
   MO_INLINE FAnimationClipVector* Clips(){
      return _pClips;
   }
   //------------------------------------------------------------
   // <T>根据方向类型获得剪辑。</T>
   MO_INLINE FAnimationClip* FindClipByDirection(TInt directionCd){
      return _pClips->Get(directionCd);
   }
   //------------------------------------------------------------
   // <T>获得位图。</T>
   //MO_INLINE FBitmap* Bitmap(){
   //   return _pBitmap;
   //}
public:
   MO_OVERRIDE TBool TestInvalid();
public:
   TInt CalculateFrameIndex(TInt span);
   MO_OVERRIDE TResult Unserialize(IDataInput* pInput);
};

//============================================================
// <T>地图资源。</T>
//============================================================
class MO_FR_DECLARE FGroundResource : public FResource2d{
protected:
   SIntSize2 _size;
   FByteStream* _pOutputStream;
public:
   FGroundResource();
   MO_ABSTRACT ~FGroundResource();
public:
   //------------------------------------------------------------
   // <T>获得大小。</T>
   MO_INLINE SIntSize2& Size(){
      return _size;
   }
   //------------------------------------------------------------
   // <T>获得输出流。</T>
   MO_INLINE FByteStream* OutputStream(){
      return _pOutputStream;
   }
public:
   MO_OVERRIDE TBool UnserializeInfo(IDataInput* pInput);
   MO_OVERRIDE TBool UnserializeData(IDataInput* pInput);
   MO_OVERRIDE TBool Process();
public:
   MO_OVERRIDE TBool FetchInfo(IDataOutput* pOutput);
   //MO_OVERRIDE TBool FetchBitmap(SBitmapData& bitmapData, TInt bitmapCode);
};

//============================================================
// <T>资源。</T>
//============================================================
class MO_FR_DECLARE FResourceGroup : public FObject{
protected:
   TResourceType _typeCd;
   TInt _code;
   TString _groupCode;
   TString _groupName;
public:
   FResourceGroup();
   MO_ABSTRACT ~FResourceGroup();
public:
   //------------------------------------------------------------
   // <T>获得类型。</T>
   MO_INLINE TResourceType TypeCd(){
      return _typeCd;
   }
   //------------------------------------------------------------
   // <T>设置类型。</T>
   MO_INLINE void SetTypeCd(TResourceType typeCd){
      _typeCd = typeCd;
   }
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
   // <T>获得组代码。</T>
   MO_INLINE TString& GroupCode(){
      return _groupCode;
   }
   //------------------------------------------------------------
   // <T>获得代码。</T>
   MO_INLINE TString& GroupName(){
      return _groupName;
   }
public:
   MO_ABSTRACT TBool Unserialize(IDataInput* pInput);
};
//------------------------------------------------------------
typedef MO_FR_DECLARE FList<FResourceGroup*> FResourceGroupList;

//============================================================
// <T>资源加载器。</T>
//============================================================
//class MO_FR_DECLARE FResourceLoader : public FLoader{
//protected:
//   TResourceType _typeCd;
//   TInt _code;
//public:
//   FResourceLoader();
//   MO_ABSTRACT ~FResourceLoader();
//public:
//   //------------------------------------------------------------
//   // <T>获得类型。</T>
//   MO_INLINE TResourceType TypeCd(){
//      return _typeCd;
//   }
//   //------------------------------------------------------------
//   // <T>设置类型。</T>
//   MO_INLINE void SetTypeCd(TResourceType typeCd){
//      _typeCd = typeCd;
//   }
//   //------------------------------------------------------------
//   // <T>取得资源编号。</T>
//   MO_INLINE TInt Code(){
//      return _code;
//   }
//   //------------------------------------------------------------
//   // <T>设置资源编号。</T>
//   MO_INLINE void SetCode(TInt code){
//      _code = code;
//   }
//public:
//   MO_OVERRIDE TBool Process();
//};

//============================================================
// <T>资源工作器。</T>
//============================================================
class MO_FR_DECLARE FResourceWorker : public FWorker{
public:
   FResourceWorker();
   MO_ABSTRACT ~FResourceWorker();
public:
   MO_OVERRIDE TResult OnProcess();
};

//============================================================
// <T>资源控制台。</T>
//============================================================
class MO_FR_DECLARE FResourceConsole : public FConsole{
protected:
   FResourceSet* _pResources;
public:
   FResourceConsole();
   MO_ABSTRACT ~FResourceConsole();
public:
   //------------------------------------------------------------
   // <T>获得资源集合。</T>
   MO_INLINE FResourceSet* Resources(){
      return _pResources;
   }
public:
   FResource2d* CreateResource(TResourceType typeCd);
public:
   void Startup();
   void Shutdown();
public:
   FResource2d* Find(TResourceId resourceId);
};

//============================================================
// <T>资源管理器。</T>
//============================================================
class MO_FR_DECLARE RResourceManager : public RSingleton<FResourceConsole>{
};

MO_NAMESPACE_END

//************************************************************
#endif // __MO_FR_CONTENT2D_H__
