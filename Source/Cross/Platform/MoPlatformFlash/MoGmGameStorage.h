#ifndef __MO_GM_GAME_STORGE_H__
#define __MO_GM_GAME_STORGE_H__
//************************************************************

#include <MoCmStream.h>
#include "MoEgCommon.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>�洢��Ϣ��</T>
//============================================================
struct SStorgeInfo{
   SStorgeInfo(){
      position = 0;
      length = 0;
   }
   TInt position;
   TInt length;
};

//============================================================
// <T>��Դ����Ϣ��</T>
//============================================================
class FGroupInfo : public FObject{
public:
   TUint id;
   SStorgeInfo data;
};
typedef FVector<FGroupInfo*> FGroupInfoVector;

//============================================================
// <T>��ͼ��ͼ��Ϣ��</T>
//============================================================
class FMapTileInfo : public FObject{
public:
   TInt tileIndex;
   SStorgeInfo data;
};
//------------------------------------------------------------
typedef FVector<FMapTileInfo*> FMapTileInfoVector;

//============================================================
// <T>��ͼ��Ϣ��</T>
//============================================================
class FMapInfo : public FObject{
public:
   TInt id;
   TInt width;
   TInt height;
   FMapTileInfoVector* pTiles;
};
//------------------------------------------------------------
typedef FVector<FMapInfo*> FMapInfoVector;

//============================================================
// <T>��Ϸ�洢������̨��</T>
//============================================================
class FGameStorageConsole : public FConsole{
public:
   FGameStorageConsole();
   MO_ABSTRACT ~FGameStorageConsole();
public:
   TBool Startup();
   void Shutdown();
public:
   TInt FindGroupBySourceId(TUint sourceId, FByteStream* pOutputStream);
   TInt FindGroupById(TUint groupId, FByteStream* pOutputStream);
   TInt FindMapSourceById(TUint mapSourceId, FByteStream* pOutputStream);
public:
   TInt PushMbiDataStream(FByteStream* pInputStream);
protected:
   TBool InitMbi();
   TBool InitMbd();
protected:
   typedef FSet<TInt /*resourceId*/, TInt /*groupId*/> FResourceGroupSet;
   FResourceGroupSet* _pResourceGroupSet;
   FGroupInfoVector* _pGroups;
   FMapInfoVector* _pMaps;
   FFileStream* _pMbdFile;
   TBool _initiailized;
   TBool _pushedMbi;
   FByteFile* _mbiFileStream;
};
//------------------------------------------------------------
typedef RSingleton<FGameStorageConsole> RGmStorageManager;

MO_NAMESPACE_END

#endif //__MO_GM_GAME_STORGE_H__
