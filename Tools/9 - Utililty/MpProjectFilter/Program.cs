using MO.Common;
using MO.Common.Collection;
using MO.Common.Content;
using MO.Common.IO;
using MO.Common.Lang;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MpProjectFilter
{
   class Program
   {
      class SFileInfo
      {
         public string fileName;
         public bool include;
      };

      static void FilterProject(string projectName, string path) {
         // 获得项目文件集合
         FDictionary<SFileInfo> infos = new FDictionary<SFileInfo>();
         foreach (string fileName in RDirectory.ListFiles(path)) {
            if (fileName.EndsWith(".h") || fileName.EndsWith(".cpp")) {
               string formatName = fileName.Replace('\\', '/');
               string name = RString.Right(formatName, "/");
               SFileInfo info = new SFileInfo();
               info.fileName = formatName;
               info.include = false;
               infos.Set(name, info);
            }
         }
         // 查找使用中的文件集合
         string configFileName = path + "/vcproject/" + projectName + ".vcxproj";
         FXmlDocument document = new FXmlDocument(configFileName);
         foreach (FXmlNode xnode in document.Root.Nodes) {
            if (xnode.IsName("ItemGroup")) {
               foreach (FXmlNode xfile in xnode.Nodes) {
                  if (xfile.IsName("ClInclude") || xfile.IsName("ClCompile")) {
                     string include = xfile.Get("Include");
                     string name = RString.Right(include, "\\");
                     SFileInfo info = infos.Find(name);
                     if (info != null) {
                        info.include = true;
                     }
                  }
               }
            }
         }
         // 查找删除集合
         foreach(SFileInfo info in infos.Values){
            if (!info.include) {
               RLogger.Find(typeof(SFileInfo)).Debug(null, "FilterProject", info.fileName);
               File.Delete(info.fileName);
            }
         }
         // 查找没用目录
         RDirectory.Delete(path + "/build");
         RDirectory.Delete(path + "/dist");
         RDirectory.Delete(path + "/nbproject/private");
         RDirectory.Delete(path + "/maproject/libs");
         RDirectory.Delete(path + "/maproject/obj");
         RDirectory.Delete(path + "/vcproject/Debug");
         RDirectory.Delete(path + "/vcproject/Release");
         RDirectory.Delete(path + "/vcproject/x64");
         File.Delete(path + "/vcproject/" + projectName + ".vcxproj.user");
      }

      static void Main(string[] args) {
         RMoCommon.Initialize();
         // 基础库
         FilterProject("MoCommon", "D:/ZW-Mobile-Work/Cross/Common/MoCommon");
         FilterProject("MoCore", "D:/ZW-Mobile-Work/Cross/Common/MoCore");
         FilterProject("MoMath", "D:/ZW-Mobile-Work/Cross/Common/MoMath");
         FilterProject("MpCommonTest", "D:/ZW-Mobile-Work/Cross/Common/MpCommonTest");
         // 功能库
         FilterProject("MoFeatureGraphic", "D:/ZW-Mobile-Work/Cross/Feature/MoFeatureGraphic");
         FilterProject("MoFeatureParticle", "D:/ZW-Mobile-Work/Cross/Feature/MoFeatureParticle");
         FilterProject("MoFeaturePhysics", "D:/ZW-Mobile-Work/Cross/Feature/MoFeaturePhysics");
         FilterProject("MoFeatureResource", "D:/ZW-Mobile-Work/Cross/Feature/MoFeatureResource");
         FilterProject("MoFeatureSound", "D:/ZW-Mobile-Work/Cross/Feature/MoFeatureSound");
         FilterProject("MoFeatureScript", "D:/ZW-Mobile-Work/Cross/Feature/MoFeatureScript");
         // 引擎库
         FilterProject("MoEngine", "D:/ZW-Mobile-Work/Cross/Engine/MoEngine");
         FilterProject("MoEngine2d", "D:/ZW-Mobile-Work/Cross/Engine/MoEngine2d");
         FilterProject("MoEngine3d", "D:/ZW-Mobile-Work/Cross/Engine/MoEngine3d");
         FilterProject("MoEngineAnimation", "D:/ZW-Mobile-Work/Cross/Engine/MoEngineAnimation");
         // FilterProject("MoEngineAndroid", "D:/ZW-Mobile-Work/Cross/Engine/MoEngineAndroid");
         // FilterProject("MoEngineDirectX", "D:/ZW-Mobile-Work/Cross/Engine/MoEngineDirectX");
         // FilterProject("MoEngineFlash", "D:/ZW-Mobile-Work/Cross/Engine/MoEngineFlash");
         FilterProject("MoEngineFace", "D:/ZW-Mobile-Work/Cross/Engine/MoEngineFace");
         FilterProject("MoEngineRender", "D:/ZW-Mobile-Work/Cross/Engine/MoEngineRender");
         FilterProject("MoEngineOpenGL", "D:/ZW-Mobile-Work/Cross/Engine/MoEngineOpenGL");
         FilterProject("MoEngineWindows", "D:/ZW-Mobile-Work/Cross/Engine/MoEngineWindows");
         // 应用库
         FilterProject("MoGameEngine", "D:/ZW-Mobile-Work/Cross/Game/MoGameEngine");
         // 应用库
         FilterProject("MpTest001", "D:/ZW-Mobile-Work/Cross/Test/MpTest001");
         FilterProject("MpTest002", "D:/ZW-Mobile-Work/Cross/Test/MpTest002");
         FilterProject("MpTest003", "D:/ZW-Mobile-Work/Cross/Test/MpTest003");
         FilterProject("MpTestDemo", "D:/ZW-Mobile-Work/Cross/Test/MpTestDemo");
         FilterProject("MpTestShadowMap", "D:/ZW-Mobile-Work/Cross/Test/MpTestShadowMap");
         RMoCommon.Release();
      }
   }
}
