using MO.Common.IO;
using MO.Common.Lang;
using MO.Common.System;

namespace MoScout.Core
{
   //============================================================
   // <T>信息控制台基础类。</T>
   //============================================================
   public class FInfoConsole : FConsole
   {
      protected FApplicationInfo _activeInfo;

      protected FObjects<FApplicationInfo> _infos = new FObjects<FApplicationInfo>();

      protected string _storagePath = "E:/Temp/Scout";

      //============================================================
      // <T>构造信息控制台基础类。</T>
      //============================================================
      public FInfoConsole() {
      }

      //============================================================
      public FApplicationInfo CreateInfo() {
         FApplicationInfo info = new FApplicationInfo();
         info.Name = "Application_" + _infos.Count;
         _infos.Push(info);
         _activeInfo = info;
         return info;
      }

      //============================================================
      public FApplicationInfo ActiveInfo {
         get { return _activeInfo; }
      }

      //============================================================
      // <T>存储所有信息到文件中。</T>
      //============================================================
      public void StoreFiles() {
         // 保存属性
         FByteFile file = new FByteFile();
         // 写入个数
         int count = _infos.Count;
         file.WriteInt32(count);
         // 保存所有程序信息
         for(int n = 0; n < count; n++) {
            FApplicationInfo info = _infos.Get(n);
            file.WriteString(info.Name);
            // 保存程序信息
            string fileName = RFile.MakeFileName(_storagePath, info.Name + ".ser");
            info.SaveFile(fileName);
         }
         // 保存文件
         string configName = RFile.MakeFileName(_storagePath, "applications.ser");
         file.SaveFile(configName);
      }

      //============================================================
      // <T>从文件中加载所有信息。</T>
      //============================================================
      public void RestoreFiles() {
         // 保存属性
         string configName = RFile.MakeFileName(_storagePath, "applications.ser");
         FByteFile file = new FByteFile(configName);
         // 写入个数
         int count = file.ReadInt32();
         // 保存所有程序信息
         for(int n = 0; n < count; n++) {
            FApplicationInfo info = new FApplicationInfo();
            info.Name = file.ReadString();
            // 保存程序信息
            string fileName = RFile.MakeFileName(_storagePath, info.Name + ".ser");
            info.LoadFile(fileName);
            _infos.Push(info);
            _activeInfo = info;
         }
      }
   }
}
