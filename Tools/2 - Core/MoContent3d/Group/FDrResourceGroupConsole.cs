using MO.Common.Collection;
using MO.Common.Content;
using MO.Common.IO;
using MO.Common.Lang;
using MO.Common.System;
using MO.Content2d.Common;
using MO.Content2d.Core;
using MO.Core;
using MO.Content3d.Resource.Common;
using MO.Content3d.Resource.Model;
using MO.Content3d.Resource.Template;
using MO.Content3d.Resource.Texture;
using MO.Resource.Data;

namespace MO.Content3d.Group
{
   //============================================================
   // <T>资源控制台。</T>   
   //============================================================
   public class FDrResourceGroupConsole : FConsole
   {
      // 日志输出接口
      private static ILogger _logger = RLogger.Find(typeof(FDrResourceGroupConsole));

      // 模板目录
      protected string _templateDirectory;

      // 导出存储目录
      protected string _exportStorageDirectory;

      // 导出资源组目录
      protected string _exportGroupDirectory;

      // 导出资源目录
      protected string _exportResourceDirectory;

      // 导出设置文件
      protected string _exportConfigFile;

      // 资源组字典
      protected FDictionary<FDrResourceGroup> _resourceGroups = new FDictionary<FDrResourceGroup>();

      //============================================================
      // <T>构造资源控制台。</T>   
      //============================================================
      public FDrResourceGroupConsole() {
         _name = "resource3d.resource.group.console";
      }

      //============================================================
      // <T>获得资源目录。</T>
      //============================================================
      public string TemplateDirectory {
         get { return _templateDirectory; }
      }

      //============================================================
      // <T>获得导出存储目录。</T>
      //============================================================
      public string ExportStorageDirectory {
         get { return _exportStorageDirectory; }
      }

      //============================================================
      // <T>获得导出资源组目录。</T>
      //============================================================
      public string ExportGroupDirectory {
         get { return _exportGroupDirectory; }
      }

      //============================================================
      // <T>获得导出资源目录。</T>
      //============================================================
      public string ExportResourceDirectory {
         get { return _exportResourceDirectory; }
      }

      //============================================================
      // <T>获得导出设置文件。</T>
      //============================================================
      public string ExportConfigFile {
         get { return _exportConfigFile; }
      }

      //============================================================
      // <T>获得资源组字典。</T>
      //============================================================
      public FDictionary<FDrResourceGroup> ResourceGroups {
         get { return _resourceGroups; }
      }

      //============================================================
      // <T>根据代码查找资源组对象。</T>
      //
      // @param code 代码
      // @return 资源组对象
      //============================================================
      public FDrResourceGroup Find(string code) {
         return _resourceGroups.Find(code);
      }

      //============================================================
      // <T>加载设置信息。</T>
      //
      // @param xconfig 设置节点
      // @author TYFNG 20120409
      //============================================================
      public override void LoadConfig(FXmlNode xconfig) {
         foreach (FXmlNode xnode in xconfig.Nodes) {
            if (xnode.IsName("Property")) {
               string name = xnode.Get("name");
               // 创建资源文件夹
               switch (name) {
                  case "template.directory":
                     _templateDirectory = xnode.Text;
                     break;
                  case "export.storage.directory":
                     _exportStorageDirectory = xnode.Text;
                     break;
                  case "export.group.directory":
                     _exportGroupDirectory = xnode.Text;
                     break;
                  case "export.resource.directory":
                     _exportResourceDirectory = xnode.Text;
                     break;
                  case "export.config.file":
                     _exportConfigFile = xnode.Text;
                     break;
               }
            }
         }
      }

      //============================================================
      // <T>扫描资源控制台。</T>
      //
      // @author TYFNG 20120409
      //============================================================
      public void Scan() {
         //FRsDataset datasetGroup = RContent3dManager.DataConsole.FetchDataset("system.resource.group3.d");
         //FRsDataset datasetResource = RContent3dManager.DataConsole.FetchDataset("system.resource3.d");
         //if ((null == datasetGroup) || (null == datasetResource)) {
         //   return;
         //}
         //// 加载资源组
         //foreach (FRsDataRow row in datasetGroup.Rows){
         //   FDrResourceGroup group = new FDrResourceGroup();
         //   group.LoadRow(row);
         //   _resourceGroups.Set(group.Code, group);
         //}
         //// 加载资源
         //foreach (FRsDataRow row in datasetResource.Rows) {
         //   // 处理资源
         //   bool valid = RBool.IsTrue(row.Get("valid"));
         //   if (!valid) {
         //      continue;
         //   }
         //   // 获得资源组
         //   string groupCode = row.Get("group_tid");
         //   FDrResourceGroup group = _resourceGroups.Get(groupCode);
         //   string code = row.Get("tid");
         //   if (null == group) {
         //      RMoCore.TrackConsole.Write(this, "Scan", "Group is not exists. (group={0}, resource={1})", groupCode, code);
         //      continue;
         //   }
         //   // 获得资源
         //   string name = row.Get("name");
         //   string typeCd = row.Get("type_cd");
         //   FDrResource resource = null;
         //   switch (typeCd) {
         //      case "texture.color":
         //      case "texture.layer":
         //      case "texture.light":
         //      case "texture.clip":
         //         resource = RContent3dManager.TextureConsole.Find(name);
         //         break;
         //      case "model":
         //         resource = RContent3dManager.ModelConsole.Find(name);
         //         break;
         //      case "template":
         //         resource = RContent3dManager.TemplateConsole.Find(name);
         //         break;
         //      case "scene":
         //         resource = RContent3dManager.SenceConsole.Find(name);
         //         continue;
         //      default:
         //         RMoCore.TrackConsole.Write(this, "Scan", "Unknown resource type. (group={0}, type={1}, resource={2})", groupCode, typeCd, code);
         //         continue;
         //   }
         //   if(null == resource){
         //      RMoCore.TrackConsole.Write(this, "Scan", "Group resource is not exists. (group={0}, type={1}, resource={2}:{3})", groupCode, typeCd, code, name);
         //      continue;
         //   }
         //   resource.OptionExport = true;
         //   group.Resources.Push(resource);
         //}
      }

      //============================================================
      // <T>存储所有资源信息。</T>
      //============================================================
      public void SaveAll() {
         foreach (FDrResourceGroup resourceGroup in _resourceGroups.Values) {
            resourceGroup.Store();
         }
      }

      //============================================================
      // <T>导出所有资源。</T>
      //============================================================
      public void ExportTransfer() {
         //............................................................
         // 存储数据
         foreach (FDrResourceGroup resourceGroup in _resourceGroups.Values) {
            foreach (FDrResource resource in resourceGroup.Resources) {
               //// 打开资源
               //string fileName = RContent3dManager.ResourceConsole.ExportResourceDirectory;
               //switch(resource.TypeName){
               //   case EDrResourceType.TextureColor:
               //   case EDrResourceType.TextureClip:
               //   case EDrResourceType.TextureLayer:
               //   case EDrResourceType.TextureLight:
               //      fileName += "/tx_" + resource.Code + ".stg";
               //      break;
               //   case EDrResourceType.Model:
               //      fileName += "/md_" + resource.Code + ".stg";
               //      break;
               //   case EDrResourceType.Template:
               //      fileName += "/tp_" + resource.Code + ".stg";
               //      break;
               //   default:
               //      throw new FFatalException("Unknown resource type.");
               //}
               //// 打开一个资源
               //FByteFile file = new FByteFile(fileName);
               //// 创建传输器
               //FRsTransfer transfer = new FRsTransfer();
               //transfer.Data.Assign(file);
               //resource.Transfer = transfer;
               //RResourceManager.TransferConsole.Push(transfer);
            }
         }
         //RResourceManager.TransferConsole.Export(_exportResourceDirectory);
         ////............................................................
         //FXmlDocument xdocument = new FXmlDocument();
         //FXmlNode xconfig = xdocument.Root;
         //// 存储分块
         //FXmlNode xblocks = xconfig.CreateNode("BlockCollection");
         //foreach (FRsTransferBlock block in RResourceManager.TransferConsole.Blocks) {
         //   FXmlNode xblock = xblocks.CreateNode("Block");
         //   xblock.Set("id", block.Id);
         //   xblock.Set("offset", block.Offset);
         //   xblock.Set("length", block.Length);
         //}
         //// 存储数据
         //FXmlNode xdatas = xconfig.CreateNode("DataCollection");
         //foreach (FRsTransferData data in RResourceManager.TransferConsole.Datas) {
         //   FXmlNode xdata = xdatas.CreateNode("Data");
         //   xdata.Set("id", data.Id);
         //   xdata.Set("offset", data.Offset);
         //   xdata.Set("length", data.Length);
         //   FXmlNode xdatablocks = xdata.CreateNode("BlockCollection");
         //   foreach (FRsTransferBlock block in data.Blocks) {
         //      FXmlNode xblock = xdatablocks.CreateNode("Block");
         //      xblock.Set("id", block.Id);
         //   }
         //}
         //// 存储资源
         //int resourceIndex = 0;
         //FXmlNode xresources = xconfig.CreateNode("ResourceCollection");
         //foreach (FDrResourceGroup resourceGroup in _resourceGroups.Values) {
         //   foreach (FDrResource resource in resourceGroup.Resources) {
         //      resource.Index = resourceIndex++;
         //      FRsTransfer transfer = resource.Transfer;
         //      FXmlNode xresource = xresources.CreateNode("Resource");
         //      xresource.Set("index", resource.Index);
         //      xresource.Set("code", resource.Code);
         //      xresource.Set("type_name", resource.TypeName);
         //      xresource.Set("label", resource.Label);
         //      xresource.Set("size", transfer.Data.Length);
         //      // 输出分段
         //      FXmlNode resourceBlocks = xresource.CreateNode("BlockCollection");
         //      foreach (FRsTransferBlock block in transfer.Blocks) {
         //         FXmlNode xblock = resourceBlocks.CreateNode("Block");
         //         xblock.Set("data_id", block.DataId);
         //         xblock.Set("block_id", block.Id);
         //      }
         //   }
         //}
         //// 存储资源组
         //int groupIndex = 0;
         //FXmlNode xgroups = xconfig.CreateNode("GroupCollection");
         //foreach (FDrResourceGroup resourceGroup in _resourceGroups.Values) {
         //   FXmlNode xgroup = xgroups.CreateNode("Group");
         //   xgroup.Set("index", groupIndex++);
         //   xgroup.Set("code", RString.PadLeft(resourceGroup.Code, 6, '0'));
         //   xgroup.Set("label", resourceGroup.Label);
         //   int size = 0;
         //   FXmlNode xgroupresources = xgroup.CreateNode("ResourceCollection");
         //   foreach (FDrResource resource in resourceGroup.Resources) {
         //      //FRsTransfer transfer = resource.Transfer;
         //      FXmlNode xresource = xgroupresources.CreateNode("Resource");
         //      xresource.Set("index", resource.Index);
         //      xresource.Set("type_name", resource.TypeName);
         //      xresource.Set("label", resource.Label);
         //      xresource.Set("code", resource.Code);
         //      //size += transfer.Data.Length;
         //   }
         //   xgroup.Set("size", size);
         //}
         //xdocument.SaveFile(_exportConfigFile);
      }

      //============================================================
      // <T>清空导出目录。</T>
      //============================================================
      public void ExportDirectoryClear() {
         //string exportDeflateDirectory = RContent3dManager.ContentConsole.ExportDeflateDirectory + "\\g3";
         //RDirectory.Clear(exportDeflateDirectory);
         //string exportLzmaDirectory = RContent3dManager.ContentConsole.ExportLzmaDirectory + "\\g3";
         //RDirectory.Clear(exportLzmaDirectory);
      }
      
      //============================================================
      // <T>导出所有资源。</T>
      //============================================================
      public void ExportAll() {
         //// 清除导出目录
         //ExportDirectoryClear();
         ////............................................................
         //string storageDeflateDirectory = RContent3dManager.ContentConsole.StorageDeflateDirectory + ".3d";
         //string storageLzmaDirectory = RContent3dManager.ContentConsole.StorageLzmaDirectory + ".3d";
         //string exportDeflateDirectory = RContent3dManager.ContentConsole.ExportDeflateDirectory + "\\g3";
         //string exportLzmaDirectory = RContent3dManager.ContentConsole.ExportLzmaDirectory + "\\g3";
         ////............................................................
         //// 获得所有资源
         //FDictionary<FDrResource> resources = new FDictionary<FDrResource>();
         //foreach (FDrResourceGroup group in _resourceGroups.Values) {
         //   foreach (FDrResource resource in group.Resources) {
         //      string resourceCode = resource.TypeName + "|" + resource.Code;
         //      if (resources.Contains(resourceCode)) {
         //         RMoCore.TrackConsole.Write(this, "ExportAll", "Resource is already exists. (type_name={0}, code={1})", resource.TypeName, resource.Code);
         //      }
         //      if ((resource is FDrColorTexture) || (resource is FDrLayerTexture) || (resource is FDrModel) || (resource is FDrTemplate)) {
         //         resources.Set(resourceCode, resource);
         //      }
         //   }
         //}
         //int count = resources.Count;
         ////............................................................
         //// 存储Deflate资源集合
         //FByteFile fileDeflate = new FByteFile();
         //fileDeflate.WriteInt32(count);
         //foreach (FDrResource resource in resources.Values) {
         //   // 读取校验代码
         //   string verifyFileName = storageDeflateDirectory + "\\" + resource.TypeCode + "_" + resource.Code + ".stg";
         //   string verifyCode = null;
         //   int totalBytes = 0;
         //   using (FRsCompressFile resourceFile = new FRsCompressFile(verifyFileName)) {
         //      //verifyCode = resourceFile.VertifyCode;
         //      totalBytes = resourceFile.Length;
         //   }
         //   // 写入资源信息
         //   fileDeflate.WriteString(resource.Code);
         //   fileDeflate.WriteString(resource.TypeName);
         //   fileDeflate.WriteString(verifyCode);
         //   fileDeflate.WriteInt32(totalBytes);
         //}
         //// 存储资源组集合
         //fileDeflate.WriteInt32(_resourceGroups.Count);
         //foreach (FDrResourceGroup group in _resourceGroups.Values) {
         //   group.ExportPack(fileDeflate, ERsCompress.Deflate);
         //}
         //fileDeflate.SaveFile(_exportStorageDirectory + "\\rd.group3d.swf");
         ////............................................................
         //// 存储LZMA资源集合
         //FByteFile fileLzma = new FByteFile();
         //fileLzma.WriteInt32(count);
         //foreach (FDrResource resource in resources.Values) {
         //   // 读取校验代码
         //   string verifyFileName = storageLzmaDirectory + "\\" + resource.TypeCode + "_" + resource.Code + ".stg";
         //   string verifyCode = null;
         //   int totalBytes = 0;
         //   using (FRsCompressFile resourceFile = new FRsCompressFile(verifyFileName)) {
         //      //verifyCode = resourceFile.VertifyCode;
         //      totalBytes = resourceFile.Length;
         //   }
         //   // 写入资源信息
         //   fileLzma.WriteString(resource.Code);
         //   fileLzma.WriteString(resource.TypeName);
         //   fileLzma.WriteString(verifyCode);
         //   fileLzma.WriteInt32(totalBytes);
         //}
         //// 存储资源组集合
         //fileLzma.WriteInt32(_resourceGroups.Count);
         //foreach (FDrResourceGroup group in _resourceGroups.Values) {
         //   group.ExportPack(fileLzma, ERsCompress.Lzma);
         //}
         //fileLzma.SaveFile(_exportStorageDirectory + "\\ra.group3d.swf");
      }

      //============================================================
      // <T>导出所有资源。</T>
      //============================================================
      public void TaskExportAll() {
         foreach (FDrResourceGroup resourceGroup in _resourceGroups.Values) {
            FDrResourceGroupExportTask task = new FDrResourceGroupExportTask();
            task.Group = resourceGroup;
            RMoCore.TaskConsole.Push(task);
         }
      }
   }
}

