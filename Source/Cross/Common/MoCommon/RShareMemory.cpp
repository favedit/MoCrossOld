#ifdef _MO_LINUX
#include <sys/ipc.h>
#include <sys/shm.h>
#include <sys/stat.h>
#endif
#include "MoCmMemory.h"
#include "MoCmLanguage.h"

#define MO_SHARE_MEMORY_ACCESS 0666
#define MO_SHARE_MEMORY_FLAG "MOSM"

MO_NAMESPACE_BEGIN

//============================================================
// <T>创建一块共享内存。</T>
// <P>如果以前存在，则产生例外退出。</P>
//
// @param key 共享内存键值
// @param size 内存大小
// @return 共享内存
//============================================================
TAny* RShareMemory::InnerCreate(TShareKey key, TSize size){
#ifdef _MO_LINUX
   // 创建共享内存
   TChar format[MO_MEMORY_FORMATLENGTH];
   MO_STATIC_DEBUG(TC("Create share memory. (key=0x%08X, size=%s)"),
         key, RInt::FormatCapacity(size, format, MO_MEMORY_FORMATLENGTH));
   // 包含共享内存头信息的大小
   size += sizeof(SShareMemoryInfo);
   TInt handle = shmget(key, size, IPC_CREAT | IPC_EXCL | MO_SHARE_MEMORY_ACCESS);
   if(handle < 0){
      MO_STATIC_PFATAL(shmget);
   }
   // 获得当前线程可以访问的内存
   TByte* pMemory = (TByte*)shmat(handle, NULL, 0);
   MO_ASSERT(pMemory);
   // 设置信息
   SShareMemoryInfo* pInfo = (SShareMemoryInfo*)pMemory;
   memcpy(pInfo->flag, MO_SHARE_MEMORY_FLAG, sizeof(pInfo->flag));
   pInfo->size = size;
   pInfo->createDate = RDateTime::Current();
   pInfo->updateDate = pInfo->createDate;
   // 获得结果指针
   TByte* pResult = pMemory + sizeof(SShareMemoryInfo);
   memset(pResult, 0, size - sizeof(SShareMemoryInfo));
   return pResult;
#endif
   return NULL;
}

//============================================================
// <T>生成一块共享内存的键值。</T>
//
// @param pFileName 文件名称
// @param id 标识
//============================================================
TShareKey RShareMemory::MakeKey(TCharC* pFileName, TInt id){
#ifdef _MO_LINUX
   // TODO: ftok 获得函数有可能重复
   // key_t tk = ftok(pFileName, id);
   // TShareKey key = ftok(pFileName, id);
   // if(key < 0){
   //    MO_STATIC_PFATAL(ftok);
   // }
   struct stat info;
   TInt result = stat(pFileName, &info);
   if(result < 0){
      MO_STATIC_PFATAL(stat);
   }
   TInt devId = info.st_dev << 16;
   TInt indexId = info.st_ino;
   key_t key = indexId + devId + (id << 8);
   return key;
#endif // _MO_LINUX
   return id;
}

//============================================================
// <T>获取共享内存的定义信息。</T>
//
// @param key 共享内存键值
// @param [out] info 定义信息
// @return 是否获取成功
//============================================================
TBool RShareMemory::FetchInfo(TShareKey key, SShareMemoryInfo& info){
#ifdef _MO_LINUX
   MO_STATIC_DEBUG(TC("Fetch info share memory info. (key=0x%08X)"), key);
   // 关联一块共享内存
   TInt handle = shmget(key, 0, MO_SHARE_MEMORY_ACCESS);
   if(handle < 0){
      return EFalse;
   }
   // 获得当前线程可以访问的内存
   TByte* pMemory = (TByte*)shmat(handle, NULL, 0);
   MO_ASSERT(pMemory);
   SShareMemoryInfo* pInfo = (SShareMemoryInfo*)pMemory;
   // 验证标志
   if(0 != memcmp(pInfo->flag, MO_SHARE_MEMORY_FLAG, sizeof(pInfo->flag))){
      MO_STATIC_DEBUG(TC("Share memory is not system type memory. (key=0x%08X)"), key);
      return EFalse;
   }
   // 获取信息
   info = *pInfo;
   TChar format[MO_MEMORY_FORMATLENGTH];
   MO_STATIC_INFO(TC("Fetch share memory info success. (key=0x%08X, memory=0x%08X, size=%s)"),
         key, pInfo, RInt::FormatCapacity(info.size, format, MO_MEMORY_FORMATLENGTH));
#endif // _MO_LINUX
   return ETrue;
}

//============================================================
// <T>创建一块指定大小的共享内存。</T>
// <P>如果共享内存存在，则删除旧共享内存，重新创建指定大小的共享内存。</P>
//
// @param key 共享内存键值
// @param size 内存大小
// @return 共享内存
//============================================================
TAny* RShareMemory::Create(TShareKey key, TSize size){
#ifdef _MO_LINUX
   // 关联一块共享内存
   TInt handle = shmget(key, 0, MO_SHARE_MEMORY_ACCESS);
   if(handle < 0){
      MO_STATIC_PWARN(shmget);
   }else{
      // 获得当前线程可以访问的内存
      TByte* pMemory = (TByte*)shmat(handle, NULL, 0);
      MO_ASSERT(pMemory);
      // 禁止本进程访问这块共享内存
      if(ESuccess != shmdt(pMemory)){
         MO_STATIC_PFATAL(shmdt);
      }
      // 删除共享内存句柄
      MO_STATIC_DEBUG(TC("Remove old share memory. (key=0x%08X)"), key);
      if(ESuccess != shmctl(handle, IPC_RMID, NULL)){
         MO_STATIC_PFATAL(shmctl);
      }
   }
#endif
   // 创建共享内存
   return InnerCreate(key, size);
}

//============================================================
// <T>尝试创建一块共享内存。</T>
// <P>如果共享内存大小一致，则直接使用旧共享内存。</P>
// <P>如果共享内存大小不一致，则删除旧共享内存，重新创建指定大小的共享内存。</P>
//
// @param key 共享内存键值
// @param size 内存大小
// @param created 是否创建
// @return 共享内存
//============================================================
TAny* RShareMemory::TryCreate(TShareKey key, TSize size, TBool& created){
#ifdef _MO_LINUX
   MO_STATIC_DEBUG(TC("Fetch info share memory info. (key=0x%08X)"), key);
   // 关联一块共享内存
   TInt handle = shmget(key, 0, MO_SHARE_MEMORY_ACCESS);
   if(handle >= 0){
      // 获得当前线程可以访问的内存
      TByte* pMemory = (TByte*)shmat(handle, NULL, 0);
      MO_ASSERT(pMemory);
      SShareMemoryInfo* pInfo = (SShareMemoryInfo*)pMemory;
      // 验证标志
      if(memcmp(pInfo->flag, MO_SHARE_MEMORY_FLAG, sizeof(pInfo->flag))){
         // 使用当前内存
         if((TSize)pInfo->size == size){
            created = EFalse;
            return pMemory + sizeof(SShareMemoryInfo);
         }
      }
      // 禁止本进程访问这块共享内存
      if(ESuccess != shmdt(pMemory)){
         MO_STATIC_PFATAL(shmdt);
      }
      // 删除共享内存句柄
      MO_STATIC_DEBUG(TC("Remove share memory. (key=0x%08X)"), key);
      if(ESuccess != shmctl(handle, IPC_RMID, NULL)){
         MO_STATIC_PFATAL(shmctl);
      }
   }
#endif // _MO_LINUX
   // 创建共享内存
   created = ETrue;
   return InnerCreate(key, size);
}

//============================================================
// <T>链接一块共享内存。</T>
//
// @param key 共享内存键值
// @return 共享内存
//============================================================
TAny* RShareMemory::Connect(TShareKey key, TBool readOnly){
   TByte* pResult = NULL;
   MO_STATIC_DEBUG(TC("Connect share memory. (key=0x%08X)"), key);
#ifdef _MO_LINUX
   // 关联一块共享内存
   TInt handle = shmget(key, 0, MO_SHARE_MEMORY_ACCESS);
   if(handle < 0){
      MO_STATIC_PFATAL(shmget);
   }
   // 获得当前线程可以访问的内存
   TByte* pMemory = NULL;
   if(readOnly){
      pMemory = (TByte*)shmat(handle, NULL, SHM_RDONLY);
   }else{
      pMemory = (TByte*)shmat(handle, NULL, 0);
   }
   MO_ASSERT(pMemory);
   SShareMemoryInfo* pInfo = (SShareMemoryInfo*)pMemory;
   // 验证标志
   if(memcmp(pInfo->flag, MO_SHARE_MEMORY_FLAG, sizeof(pInfo->flag))){
      MO_STATIC_DEBUG(TC("Connect share memory failure. (key=0x%08X)"), key);
      return NULL;
   }
   TChar format[MO_MEMORY_FORMATLENGTH];
   MO_STATIC_DEBUG(TC("Connect share memory success. (key=0x%08X, memory=0x%08X, size=%s)"),
         key, pMemory, RInt::FormatCapacity(pInfo->size, format, MO_MEMORY_FORMATLENGTH));
   pResult = pMemory + sizeof(SShareMemoryInfo);
#endif // _MO_LINUX
   return pResult;
}

//============================================================
// <T>释放一块共享内存。</T>
//
// @param key 共享内存键值
//============================================================
void RShareMemory::Free(TShareKey key){
#ifdef _MO_LINUX
   // 关联一块共享内存
   TInt handle = shmget(key, 0, MO_SHARE_MEMORY_ACCESS);
   if(handle < 0){
      MO_STATIC_PFATAL(shmget);
   }
   // 删除共享内存句柄
   if(ESuccess != shmctl(handle, IPC_RMID, NULL)){
      MO_STATIC_PFATAL(shmctl);
   }
   MO_STATIC_DEBUG("Free share memory. (key=0x%08X)", key);
#endif // _MO_LINUX
}

MO_NAMESPACE_END
