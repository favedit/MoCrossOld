using Microsoft.Win32;
using MO.Common.Lang;

namespace MO.Core.Content.Register
{
   //============================================================
   // <T>注册表路径操作类。</T>
   //============================================================
   public class FRegisterPath : FObject
   {
      // 路径
      protected string _path;

      // 顶级节点
      protected RegistryKey _rootNode;

      //============================================================
      // <T>读取或设置路径。</T>            
      //============================================================
      public string Path {
         get { return _path; }
         set { _path = value; }
      }

      //============================================================
      // <T>读取或设置路径。</T>            
      //============================================================
      public RegistryKey RootNode {
         get { return _rootNode; }
         set { _rootNode = value; }
      }

      //============================================================
      // <T>读取默认值。</T>            
      //============================================================
      public object Value {
         get { return _rootNode.GetValue(_rootNode.Name); }         
      }

      //============================================================
      // <T>创建一个新子项或打开一个现有子项以进行写访问。</T>
      // 
      // @param path 要创建或打开的子项的名称或路径
      //============================================================
      public FRegister CreateSubKey(string path) {
         if(_rootNode != null){
            FRegister reg = CreateSubKey(path, RegistryKeyPermissionCheck.Default);            
            return reg;
         }
         return null;
      }

      //============================================================
      // <T>创建一个新子项或打开一个现有子项以进行写访问。</T>
      // 
      // @param sunName 要创建或打开的子项的名称或路径
      // @param check 类型检查
      //============================================================
      public FRegister CreateSubKey(string path, RegistryKeyPermissionCheck check) {
         return CreateSubKey(path, check, RegistryOptions.None);
      }

      //============================================================
      // <T>创建一个新子项或打开一个现有子项以进行写访问。</T>
      // 
      // @param path 要创建或打开的子项的名称或路径,
      // @param check 类型检查,
      // @param option 项的选项
      //============================================================
      public FRegister CreateSubKey(string path, RegistryKeyPermissionCheck check, RegistryOptions options) {
         FRegister register = new FRegister();
         RegistryKey key = _rootNode.CreateSubKey(path, check, options);
         register.Key = key;
         return register;
      }

      //============================================================
      // <T>创建一个新子项或打开一个现有子项以进行写访问。</T>
      // 
      // @param sunName 要创建或打开的子项的名称或路径
      // @param check 类型检查
      //============================================================
      public FRegister CreateSubKey(string[] names) {
         FRegister register = new FRegister();
         register.Key = _rootNode;
         if(names != null) {
            int count = names.Length;
            if(count > 0) {
               RegistryKey key = _rootNode;
               for(int n = 0; n < count; n++) {
                  key = key.CreateSubKey(names[n]);
               }
               register.Key = key;
            }
         }
         return register;
      }

      //============================================================
      // <T>删除一个子项。</T>
      // 
      // @param sunName 要删除子项的名称
      //============================================================
      public void DeleteSubKey(string sunName) {
         if(null != _rootNode) {
            DeleteSubKey(sunName,false);
         }
      }

      //============================================================
      // <T>删除一个子项。</T>
      // 
      // @param sunName 要删除子项的名称,是否出异常
      //============================================================
      public void DeleteSubKey(string sunName, bool throwExption) {
         if(null != _rootNode) {
            _rootNode.DeleteSubKey(sunName, false);
         }
      }
   }
}
