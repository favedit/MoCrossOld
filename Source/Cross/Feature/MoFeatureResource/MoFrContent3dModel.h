#ifndef __MO_EG_CONTENT3D_MODEL_H__
#define __MO_EG_CONTENT3D_MODEL_H__
//************************************************************

#ifndef __MO_FR_COMMON_H__
#include "MoFrCommon.h"
#endif // __MO_FR_COMMON_H__

#ifndef __MO_FR_CONTENT3D_BASE_H__
#include "MoFrContent3dBase.h"
#endif // __MO_FR_CONTENT3D_BASE_H__

MO_NAMESPACE_BEGIN

//============================================================
class FRs3dFrame;
class FRs3dTrack;
class FRs3dAnimation;
class FRs3dBone;
typedef MO_FR_DECLARE FVector<FRs3dBone*> FRs3dBoneVector;
class FRs3dSkeleton;
class FRs3dVertexBuffer;
class FRs3dIndexBuffer;
class FRs3dModel;

//============================================================
typedef TInt32 TBoneId;
typedef MO_FR_DECLARE FVector<TBoneId> FBoneIds;

//============================================================
// <T>资源数据流。</T>
//============================================================
class MO_FR_DECLARE FRs3dStream : public FObject{
protected:
   // 代码
   TString _code;
   // 索引
   TInt _index;
   // 元素类型
   TInt _elementDataCd;
   // 元素个数
   TInt _elementCount;
   // 数据宽度
   TInt _dataStride;
   // 数据个数
   TInt _dataCount;
   // 数据长度
   FByteStream* _pData;
public:
   FRs3dStream();
   MO_ABSTRACT ~FRs3dStream();
public:
   MO_SOURCE_GETSET(TCharC*, Code, _code);
   MO_SOURCE_GETSET(TInt, Index, _index);
   MO_SOURCE_GETSET(TInt, ElementDataCd, _elementDataCd);
   MO_SOURCE_GETSET(TInt, ElementCount, _elementCount);
   MO_SOURCE_GETSET(TInt, DataStride, _dataStride);
   MO_SOURCE_GETSET(TInt, DataCount, _dataCount);
   MO_SOURCE_GETTER(FByteStream*, Data, _pData);
public:
   MO_ABSTRACT TResult Serialize(IDataOutput* pOutput);
};
//............................................................
typedef FObjects<FRs3dStream*> FRs3dStreams;

//============================================================
// <T>资源3D帧信息。</T>
//============================================================
struct MO_FR_DECLARE SRs3dFrameInfo{
public:
   TTimeTick tick;
   TFloat playRate;
   FRs3dFrame* currentPtr;
   FRs3dFrame* nextPtr;
   TFloat rate;
   TFloat alpha;
   SFloatMatrix3d matrix;
public:
   SRs3dFrameInfo();
public:
   TResult Update();
};

//============================================================
// <T>资源3D帧。</T>
//============================================================
class MO_FR_DECLARE FRs3dFrame : public FInstance
{
   MO_CLASS_DECLARE_INHERITS(FRs3dFrame, FInstance);
protected:
   TInt _index;
   TInt _tick;
   TFloat _alpha;
   SFloatMatrix3d _matrix;
public:
   FRs3dFrame();
   MO_ABSTRACT ~FRs3dFrame();
public:
   //------------------------------------------------------------
   // <T>获得索引。</T>
   MO_INLINE TInt Index(){
      return _index;
   }
   //------------------------------------------------------------
   // <T>设置索引。</T>
   MO_INLINE void SetIndex(TInt index){
      _index = index;
   }
   //------------------------------------------------------------
   // <T>获得时刻。</T>
   MO_INLINE TInt Tick(){
      return _tick;
   }
   //------------------------------------------------------------
   // <T>设置时刻。</T>
   MO_INLINE void SetTick(TInt tick){
      _tick = tick;
   }
   //------------------------------------------------------------
   // <T>获得透明。</T>
   MO_INLINE TFloat Alpha(){
      return _alpha;
   }
   //------------------------------------------------------------
   // <T>设置透明。</T>
   MO_INLINE void SetAlpha(TFloat alpha){
      _alpha = alpha;
   }
   //------------------------------------------------------------
   // <T>获得矩阵。</T>
   MO_INLINE SFloatMatrix3d& Matrix(){
      return _matrix;
   }
public:
   MO_ABSTRACT TResult Unserialize(IDataInput* pInput);
};
//------------------------------------------------------------
typedef MO_FR_DECLARE GPtrs<FRs3dFrame> GRs3dFramePtrs;

//============================================================
// <T>资源3D跟踪。</T>
//============================================================
class MO_FR_DECLARE FRs3dTrack : public FInstance
{
   MO_CLASS_DECLARE_INHERITS(FRs3dTrack, FInstance);
protected:
   FRs3dAnimation* _pAnimation;
   TBool _optionBoneScale;
   TInt _boneId;
   TInt _frameTick;
   SFloatMatrix3d _matrix;
   SFloatMatrix3d _matrixInvert;
   GRs3dFramePtrs _frames;
public:
   FRs3dTrack();
   MO_ABSTRACT ~FRs3dTrack();
public:
   //------------------------------------------------------------
   // <T>获得动画。</T>
   MO_INLINE FRs3dAnimation* Animation(){
      return _pAnimation;
   }
   //------------------------------------------------------------
   // <T>设置动画。</T>
   MO_INLINE void SetAnimation(FRs3dAnimation* pAnimation){
      _pAnimation = pAnimation;
   }
   //------------------------------------------------------------
   // <T>获得骨骼缩放。</T>
   MO_INLINE TBool OptionBoneScale(){
      return _optionBoneScale;
   }
   //------------------------------------------------------------
   // <T>获得骨头编号。</T>
   MO_INLINE TInt BoneId(){
      return _boneId;
   }
   //------------------------------------------------------------
   // <T>获得世界矩阵。</T>
   MO_INLINE SFloatMatrix3d& Matrix(){
      return _matrix;
   }
   //------------------------------------------------------------
   // <T>获得世界逆矩阵。</T>
   MO_INLINE SFloatMatrix3d& MatrixInvert(){
      return _matrixInvert;
   }
   //------------------------------------------------------------
   // <T>获得帧间隔。</T>
   MO_INLINE TInt FrameTick(){
      return _frameTick;
   }
   //------------------------------------------------------------
   // <T>获得帧集合。</T>
   MO_INLINE GRs3dFramePtrs& Frames(){
      return _frames;
   }
   //------------------------------------------------------------
   // <T>获得是否含有帧。</T>
   MO_INLINE TBool HasFrame(){
      return !_frames.IsEmpty();
   }
public:
   TResult Unserialize(IDataInput* pInput);
   TResult CalculateFrameInfo(SRs3dFrameInfo& info, TTimeTick tick);
   void Clear();
};
//------------------------------------------------------------
typedef MO_FR_DECLARE GPtrs<FRs3dTrack> GRs3dTrackPtrs;

//============================================================
// <T>资源3D骨头。</T>
//============================================================
class MO_FR_DECLARE FRs3dBone : public FInstance
{
   MO_CLASS_DECLARE_INHERITS(FRs3dBone, FInstance);
protected:
   FRs3dSkeleton* _pSkeleton;
   TBoneId _id;
   FRs3dBone* _pParent;
   FRs3dTrack* _pTrack;
   FRs3dBoneVector* _pBones;
public:
   FRs3dBone();
   MO_ABSTRACT ~FRs3dBone();
public:
   //------------------------------------------------------------
   // <T>获得骨骼。</T>
   MO_INLINE FRs3dSkeleton* Skeleton(){
      return _pSkeleton;
   }
   //------------------------------------------------------------
   // <T>设置骨骼。</T>
   MO_INLINE void SetSkeleton(FRs3dSkeleton* pSkeleton){
      _pSkeleton = pSkeleton;
   }
   //------------------------------------------------------------
   // <T>获得编号。</T>
   MO_INLINE TBoneId Id(){
      return _id;
   }
   //------------------------------------------------------------
   // <T>获得父骨头。</T>
   MO_INLINE FRs3dBone* Parent(){
      return _pParent;
   }
   //------------------------------------------------------------
   // <T>设置父骨头。</T>
   MO_INLINE void SetParent(FRs3dBone* pParent){
      _pParent = pParent;
   }
   //------------------------------------------------------------
   // <T>获得跟踪。</T>
   MO_INLINE FRs3dTrack* Track(){
      return _pTrack;
   }
   //------------------------------------------------------------
   // <T>设置跟踪。</T>
   MO_INLINE void SetTrack(FRs3dTrack* pTrack){
      _pTrack = pTrack;
   }
   //------------------------------------------------------------
   // <T>获得子骨头集合。</T>
   MO_INLINE FRs3dBoneVector* Bones(){
      return _pBones;
   }
public:
   void Push(FRs3dBone* pBone);
   MO_ABSTRACT TResult Unserialize(IDataInput* pInput);
   void Clear();
};
//------------------------------------------------------------
typedef MO_FR_DECLARE FSet<TBoneId, FRs3dBone*> FRs3dBoneSet;

//============================================================
// <T>资源3D骨骼。</T>
//============================================================
class MO_FR_DECLARE FRs3dSkeleton : public FObject{
protected:
   FRs3dBoneVector* _pRootBones;
   FRs3dBoneSet* _pBones;
public:
   FRs3dSkeleton();
   MO_ABSTRACT ~FRs3dSkeleton();
public:
   //------------------------------------------------------------
   // <T>获得根骨头集合。</T>
   MO_INLINE FRs3dBoneVector* RootBones(){
      return _pRootBones;
   }
   //------------------------------------------------------------
   // <T>获得骨头集合。</T>
   MO_INLINE FRs3dBoneSet* Bones(){
      return _pBones;
   }
public:
   FRs3dBone* Find(TInt boneId);
   void PushBone(FRs3dBone* pBone);
public:
   MO_ABSTRACT TResult Unserialize(IDataInput* pInput);
   void Clear();
};

//============================================================
// <T>资源3D跟踪。</T>
//============================================================
class MO_FR_DECLARE FRs3dMovie : public FInstance
{
   MO_CLASS_DECLARE_INHERITS(FRs3dMovie, FInstance);
protected:
   FRs3dAnimation* _pAnimation;
   TInt _playCd;
   TInt _frameBegin;
   TInt _frameEnd;
   TFloat _frameRate;
public:
   FRs3dMovie();
   MO_ABSTRACT ~FRs3dMovie();
public:
   //------------------------------------------------------------
   // <T>获得动画。</T>
   MO_INLINE FRs3dAnimation* Animation(){
      return _pAnimation;
   }
   //------------------------------------------------------------
   // <T>设置动画。</T>
   MO_INLINE void SetAnimation(FRs3dAnimation* pAnimation){
      _pAnimation = pAnimation;
   }
   //------------------------------------------------------------
   // <T>获得播放类型。</T>
   MO_INLINE TInt PlayCd(){
      return _playCd;
   }
   //------------------------------------------------------------
   // <T>获得开始帧。</T>
   MO_INLINE TInt FrameBegin(){
      return _frameBegin;
   }
   //------------------------------------------------------------
   // <T>获得结束帧。</T>
   MO_INLINE TInt FrameEnd(){
      return _frameEnd;
   }
   //------------------------------------------------------------
   // <T>获得播放总数。</T>
   MO_INLINE TInt FrameCount(){
      return _frameEnd - _frameBegin + 1;
   }
   //------------------------------------------------------------
   // <T>获得帧速率。</T>
   MO_INLINE TFloat FrameRate(){
      return _frameRate;
   }
public:
   MO_ABSTRACT TResult Unserialize(IDataInput* pInput);
};
//------------------------------------------------------------
typedef MO_FR_DECLARE GPtrs<FRs3dMovie> GRs3dMoviePtrs;

//============================================================
// <T>资源3D动画。</T>
//============================================================
class MO_FR_DECLARE FRs3dAnimation : public FInstance
{
   MO_CLASS_DECLARE_INHERITS(FRs3dAnimation, FInstance);
protected:
   FRs3dModel* _pModel;
   TInt _frameCount;
   TInt _frameTick;
   TInt _frameTotal;
   GRs3dTrackPtrs _tracks;
   GRs3dMoviePtrs _movies;
public:
   FRs3dAnimation();
   MO_ABSTRACT ~FRs3dAnimation();
public:
   //------------------------------------------------------------
   // <T>获得模型。</T>
   MO_INLINE FRs3dModel* Model(){
      return _pModel;
   }
   //------------------------------------------------------------
   // <T>设置模型。</T>
   MO_INLINE void SetModel(FRs3dModel* pModel){
      _pModel = pModel;
   }
   //------------------------------------------------------------
   // <T>获得帧总数。</T>
   MO_INLINE TInt FrameCount(){
      return _frameCount;
   }
   //------------------------------------------------------------
   // <T>获得帧间隔。</T>
   MO_INLINE TInt FrameTick(){
      return _frameTick;
   }
   //------------------------------------------------------------
   // <T>获得帧总数。</T>
   MO_INLINE TInt FrameTotal(){
      return _frameTotal;
   }
   //------------------------------------------------------------
   // <T>获得跟踪集合。</T>
   MO_INLINE GRs3dTrackPtrs& Tracks(){
      return _tracks;
   }
   //------------------------------------------------------------
   // <T>获得动画集合。</T>
   MO_INLINE GRs3dMoviePtrs& Movies(){
      return _movies;
   }
public:
   TResult Unserialize(IDataInput* pInput);
   void TrackClear();
   void MovieClear();
};

//============================================================
// <T>资源3D顶点数据。</T>
//============================================================
class FRs3dVertexStream : public FInstance
{
   MO_CLASS_DECLARE_INHERITS(FRs3dVertexStream, FInstance);
protected:
   TString _code;
   EContent3dVertexFormat _formatCd;
   TInt _stride;
   TInt _offset;
   FRs3dVertexBuffer* _pBuffer;
public:
   FRs3dVertexStream();
   MO_ABSTRACT ~FRs3dVertexStream();
public:
   //------------------------------------------------------------
   // <T>判断是否指定代码。</T>
   MO_INLINE TBool IsCode(TCharC* pCode){
      return _code.Equals(pCode);
   }
   //------------------------------------------------------------
   // <T>获得代码。</T>
   MO_INLINE TCharC* Code(){
      return _code;
   }
   //------------------------------------------------------------
   // <T>获得格式。</T>
   MO_INLINE EContent3dVertexFormat FormatCd(){
      return _formatCd;
   }
   //------------------------------------------------------------
   // <T>获得宽度。</T>
   MO_INLINE TInt Stride(){
      return _stride;
   }
   //------------------------------------------------------------
   // <T>获得偏移。</T>
   MO_INLINE TInt Offset(){
      return _offset;
   }
   //------------------------------------------------------------
   // <T>获得缓冲。</T>
   MO_INLINE FRs3dVertexBuffer* Buffer(){
      return _pBuffer;
   }
   //------------------------------------------------------------
   // <T>设置缓冲。</T>
   MO_INLINE void SetBuffer(FRs3dVertexBuffer* pBuffer){
      _pBuffer = pBuffer;
   }
public:
   MO_ABSTRACT TResult Unserialize(IDataInput* pInput);
};
//------------------------------------------------------------
typedef MO_FR_DECLARE GPtrs<FRs3dVertexStream> GRs3dVertexStreamPtrs;

//============================================================
// <T>资源3D顶点缓冲。</T>
//============================================================
class MO_FR_DECLARE FRs3dVertexBuffer : public FInstance
{
   MO_CLASS_DECLARE_INHERITS(FRs3dVertexBuffer, FInstance);
protected:
   TInt _count;
   TInt _stride;
   GRs3dVertexStreamPtrs _streams;
   FBytes* _pData;
public:
   FRs3dVertexBuffer();
   MO_ABSTRACT ~FRs3dVertexBuffer();
public:
   //------------------------------------------------------------
   // <T>获得总数。</T>
   MO_INLINE TInt Count(){
      return _count;
   }
   //------------------------------------------------------------
   // <T>获得宽度。</T>
   MO_INLINE TInt Stride(){
      return _stride;
   }
   //------------------------------------------------------------
   // <T>获得流数据。</T>
   MO_INLINE GRs3dVertexStreamPtrs& Streams(){
      return _streams;
   }
   //------------------------------------------------------------
   // <T>获得流数据。</T>
   MO_INLINE FRs3dVertexStream* Stream(TInt index){
      return _streams.Get(index);
   }
   //------------------------------------------------------------
   // <T>获得数据。</T>
   MO_INLINE FBytes* Data(){
      return _pData;
   }
public:
   FRs3dVertexStream* FindStream(TCharC* pCode);
   MO_ABSTRACT TResult Unserialize(IDataInput* pInput);
   void Clear();
};

//============================================================
// <T>资源3D索引缓冲。</T>
//============================================================
class MO_FR_DECLARE FRs3dIndexBuffer : public FInstance
{
   MO_CLASS_DECLARE_INHERITS(FRs3dIndexBuffer, FInstance);
protected:
   EContent3dIndexStride _strideCd;
   TInt _count;
   FBytes* _pData;
public:
   FRs3dIndexBuffer();
   MO_ABSTRACT ~FRs3dIndexBuffer();
public:
   //------------------------------------------------------------
   // <T>获得宽度类型。</T>
   MO_INLINE EContent3dIndexStride StrideCd(){
      return _strideCd;
   }
   //------------------------------------------------------------
   // <T>获得总数。</T>
   MO_INLINE TInt Count(){
      return _count;
   }
   //------------------------------------------------------------
   // <T>获得数据。</T>
   MO_INLINE FBytes* Data(){
      return _pData;
   }
public:
   MO_ABSTRACT TResult Unserialize(IDataInput* pInput);
};

//============================================================
// <T>资源3D几何体。</T>
//============================================================
class MO_FR_DECLARE FRs3dGeometry : public FInstance
{
   MO_CLASS_DECLARE_INHERITS(FRs3dGeometry, FInstance);
protected:
   TBool _optionInstanced;
   TInt _instanceCount;
   SFloatMatrix3d _matrix;
   SFloatOutline3 _outline;
   TFsName _materialName;
   GPtr<FRs3dVertexBuffer> _vertexBuffer;
   GPtr<FRs3dIndexBuffer> _indexBuffer;
   FBoneIds* _pBoneIds;
   FRs3dTrack* _pTrack;
public:
   FRs3dGeometry();
   MO_ABSTRACT ~FRs3dGeometry();
public:
   //------------------------------------------------------------
   // <T>获得矩阵。</T>
   MO_INLINE SFloatMatrix3d& Matrix(){
      return _matrix;
   }
   //------------------------------------------------------------
   // <T>获得轮廓。</T>
   MO_INLINE SFloatOutline3& Outline(){
      return _outline;
   }
   //------------------------------------------------------------
   // <T>获得资源编号。</T>
   MO_INLINE TCharC* MaterialName(){
      return _materialName;
   }
   //------------------------------------------------------------
   // <T>获得顶点缓冲。</T>
   MO_INLINE FRs3dVertexBuffer* VertexBuffer(){
      return _vertexBuffer;
   }
   //------------------------------------------------------------
   // <T>获得索引缓冲。</T>
   MO_INLINE FRs3dIndexBuffer* IndexBuffer(){
      return _indexBuffer;
   }
   //------------------------------------------------------------
   // <T>获得骨头编号集合。</T>
   MO_INLINE FBoneIds* BoneIds(){
      return _pBoneIds;
   }
   //------------------------------------------------------------
   // <T>获得跟踪。</T>
   MO_INLINE FRs3dTrack* Track(){
      return _pTrack;
   }
public:
   MO_ABSTRACT TResult Unserialize(IDataInput* pInput);
};
//------------------------------------------------------------
typedef MO_FR_DECLARE GPtrs<FRs3dGeometry> GRs3dGeometryPtrs;

//============================================================
// <T>资源3D模型。</T>
//============================================================
class MO_FR_DECLARE FRs3dModel : public FResource3d
{
   MO_CLASS_DECLARE_INHERITS(FRs3dModel, FResource3d);
protected:
   FRs3dSkeleton* _pSkeleton;
   GRs3dGeometryPtrs _geometrys;
   FRs3dAnimation* _pAnimation;
public:
   FRs3dModel();
   MO_ABSTRACT ~FRs3dModel();
public:
   MO_SOURCE_GETTER(FRs3dSkeleton*, Skeleton, _pSkeleton);
   MO_SOURCE_GETTER(GRs3dGeometryPtrs&, Geometrys, _geometrys);
   MO_SOURCE_GETTER(FRs3dAnimation*, Animation, _pAnimation);
public:
   //------------------------------------------------------------
   // <T>根据索引获得几何体。</T>
   MO_INLINE FRs3dGeometry* Geometry(TInt index){
      return _geometrys.Get(index);
   }
public:
   MO_ABSTRACT TResult Unserialize(IDataInput* pInput);
public:
   MO_OVERRIDE TResult OnOpen();
   MO_OVERRIDE TResult OnClose();
};
//------------------------------------------------------------
typedef MO_FR_DECLARE FObjects<FRs3dModel*> FRs3dModelCollection;
typedef MO_FR_DECLARE FLooper<FRs3dModel*> FRs3dModelLooper;
typedef MO_FR_DECLARE GPtrDictionary<FRs3dModel> GRs3dModelPtrDictionary;

//============================================================
// <T>资源3D模型管理器。</T>
//============================================================
class MO_FR_DECLARE FRs3dModelConsole :
      public FConsole,
      public IMonitorTrigger
{
   MO_CLASS_DECLARE_INHERITS(FRs3dModelConsole, FConsole);
protected:
   TInt _processLimit;
   GMonitorTriggerPtr _trigger;
   FRs3dModelLooper* _pLooper;
   GRs3dModelPtrDictionary _models;
public:
   FRs3dModelConsole();
   MO_ABSTRACT ~FRs3dModelConsole();
public:
   //------------------------------------------------------------
   // <T>获得模型字典。</T>
   MO_INLINE GRs3dModelPtrDictionary& Models(){
      return _models;
   }
public:
   MO_ABSTRACT FRs3dModel* Load(TCharC* pName);
public:
   FRs3dModel* Find(TCharC* pName);
   FRs3dModel* Open(TCharC* pName);
public:
   MO_OVERRIDE TResult TriggerRefresh(TTimeTick currentTick);
   MO_OVERRIDE TResult Startup();
   MO_OVERRIDE TResult Shutdown();
   MO_OVERRIDE TResult Clear();
};

MO_NAMESPACE_END

//************************************************************
#endif // __MO_EG_CONTENT3D_MODEL_H__
