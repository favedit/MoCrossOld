using System;
using System.Runtime.InteropServices;
using Microsoft.Win32;

namespace MO.Core.Content.Register
{
   //============================================================
   // <T>注册表修正类。</T>
   //============================================================
   public class RRegister
   {
      static UIntPtr HKEY_CLASSES_ROOT = (UIntPtr)0x80000000;

      static UIntPtr HKEY_CURRENT_USER = (UIntPtr)0x80000001;
      
      static UIntPtr HKEY_LOCAL_MACHINE = (UIntPtr)0x80000002;
      
      static UIntPtr HKEY_USERS = (UIntPtr)0x80000003;
      
      static UIntPtr HKEY_CURRENT_CONFIG = (UIntPtr)0x80000005;      

      // 关闭64位（文件系统）的操作转向
      [DllImport("Kernel32.dll",CharSet = CharSet.Auto,SetLastError = true)]
      public static extern bool Wow64DisableWow64FsRedirection(ref IntPtr ptr);

      // 开启64位（文件系统）的操作转向
      [DllImport("Kernel32.dll",CharSet = CharSet.Auto,SetLastError = true)]
      public static extern bool Wow64RevertWow64FsRedirection(IntPtr ptr);

      // 获取操作Key值句柄          
      [DllImport("Advapi32.dll",CharSet = CharSet.Auto,SetLastError = true)]
      public static extern uint RegOpenKeyEx(UIntPtr hKey,string lpSubKey,uint ulOptions,int samDesired,out IntPtr phkResult);

      //关闭注册表转向（禁用特定项的注册表反射）
      [DllImport("Advapi32.dll",CharSet = CharSet.Auto,SetLastError = true)]
      public static extern long RegDisableReflectionKey(IntPtr hKey);

      //使能注册表转向（开启特定项的注册表反射） 
      [DllImport("Advapi32.dll",CharSet = CharSet.Auto,SetLastError = true)]
      public static extern long RegEnableReflectionKey(IntPtr hKey);

      //获取Key值（即：Key值句柄所标志的Key对象的值） 
      [DllImport("Advapi32.dll",CharSet = CharSet.Auto,SetLastError = true)]
      private static extern int RegQueryValueEx(IntPtr hKey,string lpValueName,int lpReserved,out uint lpType,System.Text.StringBuilder lpData, ref uint lpcbData);

      //============================================================
      private static UIntPtr TransferKeyName(ERegister type) {
         switch(type) {
            case ERegister.ClassesRoot:
               return HKEY_CLASSES_ROOT;
            case ERegister.CurrentConfig:
               break;
            case ERegister.CurrentUser:
               return HKEY_CURRENT_USER;
            case ERegister.DynData:
               break;
            case ERegister.LocalMachine:
               return HKEY_LOCAL_MACHINE;
            case ERegister.PerformanceData:
               break;
            case ERegister.Users:
               return HKEY_USERS;
            default:
               break;
         }
         return HKEY_CLASSES_ROOT;
      }

      //============================================================
      // <T>判断当前系统的类型。</T>
      //============================================================
      public static unsafe bool IsRunningOn64Bit { 
         get { return (sizeof(IntPtr) == sizeof(long)); }
      }

      //============================================================
      // <T>强转注册表信息操作64系统<T>
      // 
      // @param type 类型
      // @param sunKeyName 首节点
      //============================================================
      public static FRegisterPath Sync(ERegister type, string path) {
         // 构建对象
         FRegisterPath registerPath = new FRegisterPath();
         if(IsRunningOn64Bit){
            RegistryKey localMachineKey = Registry.LocalMachine;
            RegistryKey pathKey = localMachineKey.CreateSubKey(path);
            registerPath.RootNode = pathKey;
            return registerPath;
         }              
         // 存储路径
         registerPath.Path = path;
         int KEY_QUERY_VALUE = (0x0001);
         int KEY_WOW64_64KEY = (0x0100);
         int KEY_ALL_WOW64 = (KEY_QUERY_VALUE | KEY_WOW64_64KEY);
         // 将Windows注册表主键名转化成为不带正负号的整形句柄（与平台是32或者64位有关）
         UIntPtr hKey = TransferKeyName(type);
         // 声明将要获取Key值的句柄 
         IntPtr pHKey = IntPtr.Zero;         
         // 关闭文件系统转向  
         IntPtr oldWOW64State = new IntPtr();
         if(Wow64DisableWow64FsRedirection(ref oldWOW64State)) {
            // 获得操作Key值的句柄 
            RegOpenKeyEx(hKey,path,0,KEY_ALL_WOW64,out pHKey);
            Microsoft.Win32.SafeHandles.SafeRegistryHandle safeHandle = new Microsoft.Win32.SafeHandles.SafeRegistryHandle(pHKey,true);
            RegistryKey key = RegistryKey.FromHandle(safeHandle);
            registerPath.RootNode = key;            
            // 关闭注册表转向（禁止特定项的注册表反射）
            RegDisableReflectionKey(pHKey);            
            // 打开注册表转向（开启特定项的注册表反射）
            RegEnableReflectionKey(pHKey);
         }
         // 打开文件系统转向
         Wow64RevertWow64FsRedirection(oldWOW64State);
         return registerPath;
      }
   }
}
