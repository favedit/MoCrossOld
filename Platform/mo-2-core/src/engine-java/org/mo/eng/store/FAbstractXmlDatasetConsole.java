package org.mo.eng.store;

import org.mo.com.io.RFile;
import org.mo.com.lang.FFatalError;
import org.mo.com.lang.FStrings;
import org.mo.com.lang.RString;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.com.xml.FXmlDocument;
import org.mo.core.aop.face.ALink;
import org.mo.core.aop.face.AProperty;
import org.mo.core.monitor.IMonitorConsole;
import org.mo.core.monitor.common.FRefreshMonitor;

//============================================================
// <T>XML数据集合管理器。</T>
//============================================================
public class FAbstractXmlDatasetConsole
//implements IMonitorRefresh
{
   // 日志输出接口
   private static ILogger _logger = RLogger.find(FAbstractXmlDatasetConsole.class);

   // 数据集合字典
   protected FXmlDatasetDictionary _datasetMaps = new FXmlDatasetDictionary();

   // 扩展名称
   @AProperty
   protected String _extension;

   // 处理间隔
   @AProperty
   protected int _interval;

   // 刷新监视器
   protected FRefreshMonitor _monitor;

   // 监视器管理器
   @ALink
   protected IMonitorConsole _monitorConsole;

   // 工作路径
   @AProperty
   protected String _workpath;

   //============================================================
   // <T>构造XML数据集合管理器。</T>
   //============================================================
   public FAbstractXmlDatasetConsole(){
   }

   //============================================================
   // <T>创建数据集合。</T>
   //
   // @param fileName 文件名称
   // @return 数据集合
   //============================================================
   protected FXmlDataset createDataset(String fileName){
      FXmlDocument document = new FXmlDocument();
      document.root().setName("Dataset");
      document.saveFile(fileName);
      if(_logger.debugAble()){
         _logger.debug(this, "createDataset", "Create xml dataset. [{0}]", document.fileName());
      }
      return new FXmlDataset();
   }

   //============================================================
   // <T>根据名称查找一个数据集合。</T>
   //
   // @param name 名称
   // @return 数据集合
   //============================================================
   protected FXmlDataset findDataset(String name){
      if(null != name){
         return _datasetMaps.get(name.toLowerCase());
      }
      return null;
   }

   //   protected FXmlDataset findDataset(String type,
   //                                     String path){
   //      String pathname = RFile.makePathname(_workpath, type.toLowerCase());
   //      File pathDir = new File(pathname);
   //      if(!pathDir.isDirectory()){
   //         throw new FFatalError("Directory is not exists. [{0}]", pathname);
   //      }
   //      String filename = RFile.makeFilename(pathname, path.toLowerCase()) + ".xml";
   //      File file = new File(filename);
   //      if(!file.isFile()){
   //         throw new FFatalError("File is not exists. [{0}]", file);
   //      }
   //      return loadDataset(file);
   //   }
   public void initializeConfig(){
      if(!RFile.isDirectory(_workpath)){
         throw new FFatalError("Unkonwn path (path={0})", _workpath);
      }
      int startLength = _workpath.length();
      FStrings files = RFile.listAllFile(_workpath);
      for(String filename : files){
         if(RFile.isExtension(filename, _extension)){
            String name = RFile.removeExtension(filename.substring(startLength + 1));
            name = RString.replaceChars(name, '/', '.', '\\', '.');
            // Dataset
            FXmlDataset dataset = new FXmlDataset();
            dataset.meta().setName(name);
            dataset.meta().setFileName(filename);
            if(_logger.debugAble()){
               _logger.debug(this, "initializeConfig", "Initialize data config ({0}: {1})", name, filename);
            }
            _datasetMaps.set(name, dataset);
         }
      }
   }
   //
   //   public void initializeMonitor(){
   //      _monitor = new FRefreshMonitor(this);
   //      _monitor.setInterval(_interval);
   //      _monitorConsole.push(_monitor);
   //   }
   //
   //   public FXmlDatasetMeta[] listMetas(){
   //      int count = _datasetMaps.count();
   //      FXmlDatasetMeta[] metas = new FXmlDatasetMeta[count];
   //      for(int n = 0; n < count; n++){
   //         metas[n] = _datasetMaps.value(n).meta();
   //      }
   //      return metas;
   //   }
   //
   //   protected FXmlDataset loadDataset(File file){
   //      FXmlDocument document = new FXmlDocument(file.getAbsolutePath());
   //      FXmlNode root = document.root();
   //      String name, type;
   //      FXmlNodeDictionary nodes;
   //      FXmlDataset dataset = new FXmlDataset();
   //      FXmlNodeDictionarys nodeMap = dataset.nodesMap();
   //      for(FXmlNode node : root.nodes()){
   //         type = node.name().toLowerCase();
   //         nodes = nodeMap.get(type);
   //         if(nodes == null){
   //            nodes = new FXmlNodeDictionary();
   //            nodeMap.set(type, nodes);
   //         }
   //         name = node.get("id");
   //         if(RString.isEmpty(name)){
   //            _logger.warn(this, "loadDataset", "Node name(id) is empty. [{0}]", node.dump());
   //         }else{
   //            nodes.set(name, node);
   //         }
   //      }
   //
   //      if(_logger.debugAble()){
   //         _logger.debug(this, "loadDataset", "Load xml dataset. [{0}]", document.fileName());
   //      }
   //      return dataset;
   //   }
   //
   //   protected String makeFilename(String type, String path){
   //      String pathname = RFile.makePathname(_workpath, type.toLowerCase());
   //      return RFile.makeFilename(pathname, path.toLowerCase()) + ".xml";
   //   }
   //
   //   @Override
   //   public void refreshMonitor(){
   //      //      String filename, type, path;
   //      //      FXmlDataset dataset;
   //      //      FXmlDatasetMap datasetMap;
   //      //      int count = _datasetMaps.count();
   //      //      for(int n = 0; n < count; n++){
   //      //         type = _datasetMaps.name(n);
   //      //         datasetMap = _datasetMaps.value(n);
   //      //         int nc = datasetMap.count();
   //      //         for(int i = 0; i < nc; i++){
   //      //            path = datasetMap.name(n);
   //      //            dataset = datasetMap.value(i);
   //      //            if(dataset.testModified()){
   //      //               filename = makeFilename(type, path);
   //      //               saveDataset(new File(filename), dataset);
   //      //            }
   //      //         }
   //      //      }
   //   }
   //
   //   public void registerType(String type){
   //      //      if(type != null){
   //      //         type = type.toLowerCase();
   //      //         if(!_datasetMaps.contains(type)){
   //      //            _datasetMaps.set(type, new FXmlDatasetMap());
   //      //         }
   //      //      }
   //   }
   //
   //   public FXmlDataset remove(String type, String path){
   //      FXmlDataset dataset = null;
   //      //      if(type != null && path != null){
   //      //         FXmlDatasetMap datasetMap = _datasetMaps.get(type.toLowerCase());
   //      //         if(datasetMap == null){
   //      //            throw new FFatalError("Can't find type dataset");
   //      //         }
   //      //         path = path.toLowerCase();
   //      //         dataset = datasetMap.get(path);
   //      //         if(dataset == null){
   //      //            throw new FFatalError("Can't find path dataset");
   //      //         }
   //      //         datasetMap.remove(path);
   //      //      }
   //      return dataset;
   //   }
   //
   //   protected void saveDataset(File file, FXmlDataset dataset){
   //      FXmlDocument document = new FXmlDocument();
   //      FXmlNode root = document.root();
   //      FXmlNodeDictionarys nodeMap = dataset.nodesMap();
   //      String name, type;
   //      FXmlNodeDictionary nodes;
   //      FXmlNode node;
   //      int count = nodeMap.count();
   //      for(int n = 0; n < count; n++){
   //         type = nodeMap.name(n);
   //         nodes = nodeMap.value(n);
   //         int nc = nodes.count();
   //         for(int i = 0; i < nc; i++){
   //            name = nodes.name(i);
   //            node = nodes.value(i);
   //            node.setName(type);
   //            node.set("objid", name);
   //            root.nodes().push(node);
   //         }
   //      }
   //      document.saveFile(file.getAbsolutePath());
   //      dataset.setLastModified(System.currentTimeMillis());
   //
   //      if(_logger.debugAble()){
   //         _logger.debug(this, "saveDataset", "Save xml dataset. [{1}]", document.fileName());
   //      }
   //   }
   //
   //   protected FXmlDataset syncDataset(String type, String path){
   //      String pathname = RFile.makePathname(_workpath, type.toLowerCase());
   //      File pathDir = new File(pathname);
   //      if(!pathDir.isDirectory()){
   //         pathDir.mkdirs();
   //      }
   //      String filename = RFile.makeFilename(pathname, path.toLowerCase()) + ".xml";
   //      File file = new File(filename);
   //      if(file.isFile()){
   //         return loadDataset(file);
   //      }
   //      return createDataset(file);
   //   }
   //
   //   protected FXmlDatasetDictionary syncDatasetMap(String type){
   //      //      if(type == null){
   //      //         throw new FFatalError("type is null");
   //      //      }
   //      //FXmlDatasetMap datasetMap;
   //      //      type = type.toLowerCase();
   //      //      datasetMap = _datasetMaps.get(type);
   //      //      if(datasetMap == null){
   //      //         datasetMap = new FXmlDatasetMap();
   //      //         _datasetMaps.set(type, datasetMap);
   //      //      }
   //      //return datasetMap;
   //      return null;
   //   }
   //
   //   public FXmlDataset synchronize(String name){
   //      if(null != name){
   //         name = name.toLowerCase();
   //         FXmlDataset dataset = _datasetMaps.get(name);
   //         if(null == dataset){
   //            //            FXmlDataset dataset = datasetMap.get(path);
   //            //            if(dataset == null){
   //            //               dataset = syncDataset(type, path);
   //            //               datasetMap.set(path, dataset);
   //            //            }
   //            //            return dataset;
   //         }
   //      }
   //      return null;
   //   }
}
