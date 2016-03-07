#pragma once

#include "MoFbxDefine.h"
#include "MoFbxResource.h"

MO_NAMESPACE_BEGIN;

class FFbxMesh;
class FFbxScene;
class FFbxManager;

//============================================================
// <T>FBX枚举。</T>
//============================================================
enum EFbxVertexAttribute{
   EFbxVertexAttribute_Position,
   EFbxVertexAttribute_Color,
   EFbxVertexAttribute_Normal,
   EFbxVertexAttribute_Binormal,
   EFbxVertexAttribute_Tangent,
   EFbxVertexAttribute_Coord1,
   EFbxVertexAttribute_Coord2,
   EFbxVertexAttribute_Coord3,
   EFbxVertexAttribute_Coord4,
   EFbxVertexAttribute_Count,
};

//============================================================
// <T>FBX枚举。</T>
//============================================================
class RFbxEnum{
public:
   static TCharC* ParseName(FbxNodeAttribute::EType typeCd);
};

//============================================================
// <T>FBX版本号。</T>
//============================================================
struct SFbxSdkVersion{
   TInt32 major;
   TInt32 minor;
   TInt32 revision;
};

//============================================================
// <T>FBX顶点信息。</T>
//============================================================
struct SFbxVertex{
public:
   TInt index;
   SFloatPoint3 position;
   SFloatColor4 color;
   SFloatVector3 normal;
   SFloatPoint2 coord;
public:
   SFbxVertex(){
   }
};
//............................................................
typedef FObjects<SFbxVertex*> FFbxVertexs;

//============================================================
// <T>FBX面信息。</T>
//============================================================
struct SFbxFace{
public:
   TInt corners[3];
public:
   SFbxFace(){
   }
};
//............................................................
typedef FObjects<SFbxFace*> FFbxFaces;

//============================================================
// <T>FBX网格。</T>
//============================================================
class MO_FBX_DECLARE FFbxMesh : public FObject {
protected:
   TString _code;
   FFbxScene* _pScene;
   FbxNode* _pFbxNode;
   FbxMesh* _pFbxMesh;
   TInt _vertexAttributeCount;
   TBool _vertexAttributes[EFbxVertexAttribute_Count];
   FFbxVertexs* _pVertexs;
   FFbxFaces* _pFaces;
public:
   FFbxMesh();
   MO_ABSTRACT ~FFbxMesh();
public:
   MO_SOURCE_GETTER(TCharC*, Code, _code);
   MO_SOURCE_GETSET(FFbxScene*, Scene, _pScene);
   MO_SOURCE_GETSET(FbxNode*, NodeHandle, _pFbxNode);
   MO_SOURCE_GETTER(FbxMesh*, MeshHandle, _pFbxMesh);
   MO_SOURCE_GETTER(FFbxVertexs*, Vertexs, _pVertexs);
   MO_SOURCE_GETTER(FFbxFaces*, Faces, _pFaces);
public:
   TResult Setup();
public:
   MO_INLINE TBool ContainsAttribute(EFbxVertexAttribute attributeCd){
      return _vertexAttributes[attributeCd];
   }
public:
   TResult ReadVertexPosition(TInt vertexIndex, SFloatPoint3* pPosition);
   TResult ReadVertexColor(TInt vertexIndex, TInt vertexCounter, SFloatColor4* pColor);
   TResult ReadVertexNormal(TInt vertexIndex, TInt vertexCounter, SFloatVector3* pNormal);
   TResult ReadVertexBinormal(TInt vertexIndex, TInt vertexCounter, SFloatVector3* pBinormal);
   TResult ReadVertexTangent(TInt vertexIndex, TInt vertexCounter, SFloatVector3* pTangent);
   TResult ReadVertexCoord(TInt layer, TInt vertexIndex, TInt vertexCounter, SFloatPoint2* pCoord);
   SFbxVertex* SyncVertex(SFloatPoint3* pPosition, SFloatColor4* pColor, SFloatVector3* pNormal, SFloatPoint2* pCoord);
   TResult Serialize(IDataOutput* pOutput);
public:
   TResult Store(FFbxResModelMesh* pResMesh);
};
//............................................................
typedef FObjects<FFbxMesh*> FFbxMeshs;

//============================================================
// <T>FBX场景。</T>
//============================================================
class MO_FBX_DECLARE FFbxScene : public FObject{
protected:
   TString _code;
   FFbxManager* _pManager;
   FbxScene* _pFbxScene;
   FFbxMeshs* _pMeshs;
public:
   FFbxScene();
   MO_ABSTRACT ~FFbxScene();
public:
   MO_SOURCE_GETTER(TCharC*, Code, _code);
   MO_SOURCE_GETSET(FFbxManager*, Manager, _pManager);
   MO_SOURCE_GETTER(FbxScene*, FbxScene, _pFbxScene);
   MO_SOURCE_GETTER(FFbxMeshs*, Meshs, _pMeshs);
public:
   TResult Setup();
public:
   TResult ProcessMesh(FbxNode* pFbxNode);
   TResult ProcessNode(FbxNode* pFbxNode);
public:
   TResult Store(FFbxResModel* pResModel);
public:
   TResult LoadFile(TCharC* pFileName);
};

//============================================================
// <T>FBX管理器。</T>
//============================================================
class MO_FBX_DECLARE FFbxManager : public FObject{
protected:
   FbxManager* _pFbxManager;
   FbxIOSettings* _pFbxIoSettings;
   SFbxSdkVersion _version;
public:
   FFbxManager();
   MO_ABSTRACT ~FFbxManager();
public:
   MO_SOURCE_GETTER(FbxManager*, FbxManager, _pFbxManager);
   MO_SOURCE_GETTER(FbxIOSettings*, FbxIoSettings, _pFbxIoSettings);
   MO_SOURCE_GETTER(SFbxSdkVersion&, Version, _version);
public:
   TResult Setup();
};

/*
//============================================================
typedef FSet<TInt, Mtl*> FMaterialSet;
typedef FVector<TUint> FMaterialVector;
typedef FSet<TInt, INode*> FBoneSet;
typedef FVector<INode*> FBoneVector;

//============================================================
class R3dsClass{
public:
   static TCharC* GetSuperClassName(SClass_ID classId);
   static TCharC* GetClassName(Class_ID classId);
   static TCharC* GetControlType(IGameControl::MaxControlType type);
};

//============================================================
class R3dsExporter{
public:
   static void StoreColor(FXmlNode* pNode, Color& color);
   static void StorePoint2(FXmlNode* pNode, Point2& point);
   static void StorePoint3(FXmlNode* pNode, Point3& point);
   static void StorePoint4(FXmlNode* pNode, Point4& point);
   static void StoreAngAxis(FXmlNode* pNode, AngAxis& axis);
   static void StoreQuat(FXmlNode* pNode, Quat& quat);
   static void StoreScale(FXmlNode* pNode, ScaleValue& scale);
   static void StoreMatrix3(FXmlNode* pNode, TCharC* pName, Matrix3& matrix);
   static void StoreGameMatrix(FXmlNode* pNode, TCharC* pName, GMatrix& matrix);
   static void StoreGameMatrixSimple(FXmlNode* pNode, GMatrix& matrix);
   static void StoreGameMatrixFull(FXmlNode* pNode, GMatrix& matrix);
   static TBool StoreColorProperty(FXmlNode* pNode, IGameProperty* piProperty);
   static TBool StoreProperty(FXmlNode* pNode, TCharC* pName, IGameProperty* piProperty);
   static TBool StoreProperties(FXmlNode* pNode, TCharC* pName, IPropertyContainer* piContainer);
   static void SerializeMatrix(IDataOutput* pOutput, GMatrix& matrix);
};

//============================================================
class M3dsExporter{
protected:
   TFsFileName _fileName;
   FXmlDocument* _pDocument;
   FByteFile* _pFileStream;
   IScene* _piScene;
   TimeValue _time;
   Interface* _piInterface;
   INode* _piRootNode;
   FMaterialVector* _pMaterials;
   FXmlNode* _pSceneNode;
   FXmlNode* _pStructNode;
   FXmlNode* _pMaterialsNode;
   FXmlNode* _pMeshNode;
   FXmlNode* _pSkeletonNode;
   FXmlNode* _pAnimationNode;
   FXmlNode* _pLoggerNode;
public:
   M3dsExporter();
   M3dsExporter(IScene* piScene, TimeValue time, Interface* piInterface);
   MO_ABSTRACT ~M3dsExporter();
public:
   void InnerInitialize(FXmlNode* pConfig);
public:
   //------------------------------------------------------------
   // <T>获得文件名称。</T>
   MO_INLINE TCharC* FileName(){
      return _fileName;
   }
   //------------------------------------------------------------
   // <T>设置文件名称。</T>
   MO_INLINE void SetFileName(TCharC* pFileName){
      _fileName = pFileName;
   }
   //------------------------------------------------------------
   // <T>获得接口。</T>
   MO_INLINE Interface* GetInterface(){
      return _piInterface;
   }
public:
   MO_ABSTRACT void Convert();
   MO_ABSTRACT void SaveAs(TCharC* pFileName, TCharC* pEncoding);
};

//============================================================
class F3dsExporter : public M3dsExporter{
protected:
   FBoneVector* _pBones;
public:
   F3dsExporter(IScene* piScene, TimeValue time, Interface* piInterface);
   MO_ABSTRACT ~F3dsExporter();
public:
   TBool IsMesh(INode* piNode);
   TBool IsBone(INode* piNode);
   ISkin* FindSkinModifier(INode* piNode);
   Modifier* FindPhysiqueModifier(INode* piNode);
public:
   void ConvertTextures(INode* piNode, FXmlNode* pTexturesNode, Mtl* pMtl);
   void ConvertMaterial(INode* piNode, FXmlNode* pConfig, Mtl* pMtl);
   void ConvertBone(INode* piNode);
   void ConvertMeshBones(INode* piNode, FXmlNode* pBoneVerticesNode, Mesh* pMesh, ISkin* piSkin);
   void ConvertTriangleObject(INode* piNode, FXmlNode* pTriangleNode, TriObject* pTriObject);
   void ConvertGeomObject(INode* piNode, FXmlNode* pNode, GeomObject* pGeomObject);
   void ConvertSkeleton(INode* piNode, FXmlNode* pParentNode);
   void ConvertNode(INode* piNode, FXmlNode* pNode, TInt level);
   void ConvertScene(FXmlNode* pScene);
   void Convert();
};

//============================================================
enum EControlKeyType{
   EControlKeyType_Unknown,
   EControlKeyType_Linear,
   EControlKeyType_Bezier,
   EControlKeyType_Tcb,
   EControlKeyType_Sample,
};
//------------------------------------------------------------
struct SGameBone{
   IGameMesh* piMesh;
   IGameSkin* piSkin;
   IGameNode* piBone;
};
//------------------------------------------------------------
class F3dsGameExporter;
typedef FVector<IGameNode*> FGameNodeVector;
typedef FVector<IGameMaterial*> FGameMaterialVector;
typedef FSet<TInt, IGameMaterial*> FGameMaterialSet;
typedef FVector<TInt> FGameBoneIdVector;
typedef FVector<IGameNode*> FGameBoneVector;
typedef FSet<TInt, SGameBone*> FGameBoneSet;
typedef FSet<TInt, TInt> FNormalVertexSet;
typedef FVector<IGameMaterial*> FGameMaterialVector;
typedef FSet<TInt, IGameNode*> FGameGeometrySet;
typedef FVector<IGameNode*> FGameGeometryVector;

//============================================================
class F3dsGeometryExporter : public FObject{
protected:
   F3dsGameExporter* _pExporter;
   IGameNode* _piNode;
	IGameMesh* _piSubMesh;
   TFixVector<IGameMaterial*, 256> _materials;
public:
   F3dsGeometryExporter(F3dsGameExporter* pExporter, IGameNode* piNode, IGameMesh* piSubMesh);
   MO_ABSTRACT ~F3dsGeometryExporter();
public:
   TBool SerializeTrack(IDataOutput* pOutput);
   TBool SerializeSkin(IDataOutput* pOutput, IGameSkin* piSkin);
   TBool Serialize(IDataOutput* pOutput);
public:
   TBool ConvertSkin(FXmlNode* pSkinNode, IGameSkin* piSkin);
   TBool ConvertVertex(FXmlNode* pVertexNode, TInt32 face, TInt32 corner);
   TBool ConvertControlType(FXmlNode* pTypeNode, IGameControl* piControl, TCharC* pName, IGameControlType keyType);
   TBool ConvertTrack(FXmlNode* pTrackNode);
   TBool Convert(FXmlNode* pConfig);
   TBool ConvertFile(FXmlNode* pConfig);
};

//============================================================
class F3dsGameTrackExporter : public FObject{
protected:
   F3dsGameExporter* _pExporter;
	IGameNode* _piNode;
   FXmlNodeSet* _pFrameSet;
public:
   F3dsGameTrackExporter(F3dsGameExporter* pExporter, IGameNode* piNode);
   MO_ABSTRACT ~F3dsGameTrackExporter();
public:
   TBool GetControlKeys(IGameControl* piControl, IGameKeyTab& keys, IGameControlType keyType, EControlKeyType& typeCd);
   TBool StoreData(FXmlNode* pNode, IGameKey& key, IGameControlType keyType, EControlKeyType typeCd);
public:
   TBool ConvertControlType(FXmlNode* pTypeNode, IGameControl* piControl, TCharC* pName, IGameControlType keyType);
   TBool ConvertTrackFrame(FXmlNode* pFrameNode, IGameKey* piGameKey);
   TBool ConvertTrack(IGameControl* piControl, TCharC* pName, IGameControlType keyType);
   TBool Convert(FXmlNode* pTrackNode);
};

//============================================================
// <T>游戏导出器。</T>
//============================================================
class F3dsGameExporter :
      public FObject,
      public M3dsExporter{
      //public IGameErrorCallBack{
protected:
	IGameScene* _piGameScene;
	IGameConversionManager* _piGameConversionManager;
   FGameNodeVector* _pNodes;
   FGameMaterialVector* _pMaterials;
   FGameBoneVector* _pBones;
   FGameGeometryVector* _pGeometrys;
   FGameGeometrySet* _pGeometrySet;
public:
   F3dsGameExporter(IScene* piScene, TimeValue time, Interface* piInterface);
   MO_ABSTRACT ~F3dsGameExporter();
public:
   IGameScene* GameScene();
   IGameConversionManager* GameConversionManager();
   FGameNodeVector* GameNodes();
   FGameMaterialVector* GameMaterials();
   FGameBoneVector* GameBones();
public:
   //------------------------------------------------------------
   // <T>获得几何体集合。</T>
   MO_INLINE FGameGeometryVector* Geometrys(){
      return _pGeometrys;
   }
   //------------------------------------------------------------
   // <T>获得几何体集合。</T>
   MO_INLINE FGameGeometrySet* GeometrySet(){
      return _pGeometrySet;
   }
public:
   void ErrorProc(IGameError error);
public:
   TBool IsMesh(INode* piNode);
   TBool IsBone(INode* piNode);
   ISkin* FindSkinModifier(INode* piNode);
   Modifier* FindPhysiqueModifier(INode* piNode);
public:
   void ConvertStruct(FXmlNode* pNode, IGameNode* piNode);
   void ConvertSubTexture(FXmlNode* pTextureNode, IGameMaterial* piMaterial, Texmap* pTexmap);
   void ConvertTexture(FXmlNode* pTextureNode, IGameMaterial* piMaterial, IGameTextureMap* piTextureMap);
   void ConvertMaterial(FXmlNode* pMaterialNode, IGameMaterial* piMaterial);
   void ConvertMaterials(FXmlNode* pMaterialsNode);
   void ConvertMesh(FXmlNode* pMeshNode);
   void ConvertSkeleton(FXmlNode* pParentNode, IGameNode* piNode);
   void ConvertScene(FXmlNode* pScene);
   void Convert();
};*/

MO_NAMESPACE_END;
