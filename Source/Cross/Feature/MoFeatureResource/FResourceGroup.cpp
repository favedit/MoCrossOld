#include "MoFrContent2d.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造资源。</T>
//============================================================
FResourceGroup::FResourceGroup(){
   _typeCd = EResourceType_Group;
   _code = 0;
}

//============================================================
// <T>析构资源。</T>
//============================================================
FResourceGroup::~FResourceGroup(){
}

//============================================================
// <T>从输入流里反序列化数据内容</T>
//
// @param pInput 输入流
// @return 处理结果
//============================================================
TBool FResourceGroup::Unserialize(IDataInput* pInput){
   //// 读取组编号
   //pInput->ReadInt32();
   //// 读取组代码
   //_groupCode = pInput->ReadString();
   //// 读取组名称
   //_groupName = pInput->ReadString();
   //// 取得资源信息 
   //TInt count = pInput->ReadInt16();
   //TLinkInput inputLink;
   //FResource* pResource = NULL; 
   //for(TInt n = 0; n < count; n++){
   //   // 资源长度
   //   TInt length = pInput->ReadInt32();
   //   // 资源数据
   //   inputLink.Link(pInput->PositionMemory(), length);
   //   
   //   //inputLink.ReadString(); // compressCd
   //   //inputLink.ReadInt32();  // dataLength
   //   //inputLink.ReadString(); // vertifyCode
   //   TString codeName =inputLink.ReadString();
   //   TString typeName = inputLink.ReadString();
   //   // 创建资源工作器
   //   if(RString::Equals(typeName, pictureStr)){
   //      FPictureResource* pPictureResource = MO_CREATE(FPictureResource);
   //      pPictureResource->InputStream()->Assign(pInput->PositionMemory(), length);
   //      pResource = pPictureResource;
   //   }else if(RString::Equals(typeName, animationStr)){
   //      FAnimationResource* pAnimationResource = MO_CREATE(FAnimationResource);
   //      pAnimationResource->InputStream()->Assign(pInput->PositionMemory(), length);
   //      pResource = pAnimationResource;
   //   }else if(RString::Equals(typeName, groundStr)){
   //      FGroundResource* pGroundResource = MO_CREATE(FGroundResource);
   //      pGroundResource->InputStream()->Assign(pInput->PositionMemory(), length);
   //      pResource = pGroundResource;
   //   }else{
   //      MO_DEBUG("Unknown resource type!");
   //      return EFalse;
   //   }
   //   // 移动到下个资源
   //   pInput->Skip(length);
   //   // 放入队列
   //   if(NULL != pResource){
   //      pResource->SetCode(RInt::Parse(codeName));
   //      RResourceManager::Instance().PushWaitResource(pResource);
   //   }
   //}
   return ETrue;
}



MO_NAMESPACE_END
