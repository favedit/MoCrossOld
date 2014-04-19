package org.mo.eng.store;

import java.io.File;
import org.mo.com.console.FConsole;
import org.mo.com.io.RFile;
import org.mo.com.lang.FFatalError;
import org.mo.com.lang.FStrings;
import org.mo.com.lang.RString;
import org.mo.com.lang.reflect.RClass;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.com.xml.FXmlDocument;
import org.mo.core.aop.face.ALink;
import org.mo.core.aop.face.AProperty;
import org.mo.core.monitor.IMonitorConsole;
import org.mo.core.monitor.common.IMonitorRefresh;

//============================================================
// <T>配置对象抽象管理器。</T>
//============================================================
public abstract class FAbstractXmlConfigConsole
      extends FConsole
      implements
         IXmlConfigConvert,
         IMonitorRefresh
{
   // 日志输出接口
   private static ILogger _logger = RLogger.find(FAbstractXmlConfigConsole.class);

   // 监视对象管理器
   @ALink
   protected IMonitorConsole _monitorConsole;

   // 检查间隔
   @AProperty
   protected int _interval;

   // 存储间隔
   @AProperty
   protected int _storeInterval;

   // 配置后缀
   @AProperty
   protected String _extension;

   // 工作路径
   @AProperty
   protected String _workpath;

   // 设置文件监视器
   protected FXmlConfigMonitor _monitor;

   // 设置字典
   protected FXmlConfigDictionary _configs = new FXmlConfigDictionary();

   //============================================================
   // <T>根据对象实例获得存储用的名称。</T>
   //
   // @param xobject 实例
   // @return 名称
   //============================================================
   protected abstract String makeName(Object xobject);

   //============================================================
   // <T>根据名称生成文件名称。</T>
   //
   // @param name 名称
   // @return 文件名称
   //============================================================
   protected String makeFileName(String name){
      name = RString.replace(name, '.', '/');
      return RFile.makeFilename(_workpath, name) + ".xml";
   }

   //============================================================
   // <T>根据指定内容打开一个配置文档。</T>
   //
   // @param name 名称
   // @return 配置文档
   //============================================================
   protected FXmlDocument openDocument(String name){
      String filename = RFile.makeFilename(_workpath, name) + ".xml";
      File file = new File(filename);
      if(!file.isFile()){
         throw new FFatalError("File is not exists. [{1}]", filename);
      }
      return new FXmlDocument(file.getAbsolutePath());
   }

   //============================================================
   // <T>根据指定内容打开一个配置文档。</T>
   //
   // @param path 路径
   // @param name 名称
   // @return 配置文档
   //============================================================
   protected FXmlDocument openDocument(String path,
                                       String name){
      String pathname = RFile.makePathname(_workpath, path);
      File pathDir = new File(pathname);
      if(!pathDir.isDirectory()){
         throw new FFatalError("Directory is not exists. [{1}]", pathname);
      }
      String filename = RFile.makeFilename(pathname, name) + ".xml";
      File file = new File(filename);
      if(!file.isFile()){
         throw new FFatalError("File is not exists. [{1}]", file);
      }
      return new FXmlDocument(file.getAbsolutePath());
   }

   //============================================================
   // <T>根据指定内容打开一个配置文档。</T>
   //
   // @param type 类型
   // @param path 路径
   // @param name 名称
   // @return 配置文档
   //============================================================
   protected FXmlDocument openDocument(String type,
                                       String path,
                                       String name){
      String pathname = RFile.makePathname(_workpath, type, path);
      File pathDir = new File(pathname);
      if(!pathDir.isDirectory()){
         throw new FFatalError("Directory is not exists. [{1}]", pathname);
      }
      String filename = RFile.makeFilename(pathname, name) + ".xml";
      File file = new File(filename);
      if(!file.isFile()){
         throw new FFatalError("File is not exists. [{1}]", file);
      }
      return new FXmlDocument(file.getAbsolutePath());
   }

   //============================================================
   // <T>根据文件名称保存配置对象。</T>
   //
   // @param fileName 文件名称
   //============================================================
   protected void saveFile(String fileName){
      RFile.checkExists(fileName);
      FXmlConfig config = _configs.getByFileName(fileName);
      if(null == config){
         throw new FFatalError("Can't find file name (file_name={1})", fileName);
      }
      config.store();
      if(_logger.debugAble()){
         _logger.debug(this, "saveConfig", "Save file. (name={1}, file_name={2})", RClass.dump(config.instance()), fileName);
      }
   }

   //============================================================
   // <T>根据文件名称重新加载一个配置文件。</T>
   //
   // @param fileName 文件名称
   //============================================================
   protected void freeConfig(String fileName){
      RFile.checkExists(fileName);
      synchronized(_configs){
         FXmlConfig config = _configs.getByFileName(fileName);
         if(null != config){
            config.free();
            if(_logger.debugAble()){
               String name = config.meta().name();
               _logger.debug(this, "freeConfig", "Free config. (name={1}, file_name={2})", name, fileName);
            }
         }
      }
   }

   //============================================================
   // <T>根据名称查找一个配置对象。</T>
   //
   // @param name 名称
   // @return 配置对象
   //============================================================
   public FXmlConfig findConfig(String name){
      FXmlConfig config = null;
      if(!RString.isEmpty(name)){
         config = _configs.find(name);
         if(null != config){
            if(!config.isLoaded()){
               config.load();
            }
            return config;
         }
      }
      return config;
   }

   //============================================================
   // <T>根据名称查找一个配置对象。</T>
   // <P>如果对象不存在，则根据名称建立一个XML设置对象，并保存到XML对象列表中。</P>
   //
   // @param name 名称
   // @return 配置对象
   //============================================================
   public FXmlConfig synchronizeConfig(String name){
      FXmlConfig config = findConfig(name);
      if(null == config){
         // 创建新的XML设置对象
         config = new FXmlConfig(this);
         config.meta().setName(name);
         config.meta().setFileName(makeFileName(name));
         config.store();
         _configs.set(name, config);
      }
      return config;
   }

   //============================================================
   // <T>根据名称查找一个配置对象实例。</T>
   //
   // @param name 名称
   // @return 配置对象实例
   //============================================================
   public Object findInstance(String name){
      FXmlConfig config = findConfig(name);
      if(null != config){
         return config.instance();
      }
      return null;
   }

   //============================================================
   // <T>列出所有的配置描述集合。</T>
   //
   // @return 配置描述集合
   //============================================================
   public FXmlConfigMeta[] listMetas(){
      int count = _configs.count();
      FXmlConfigMeta[] metas = new FXmlConfigMeta[count];
      for(int n = 0; n < count; n++){
         metas[n] = _configs.value(n).meta();
      }
      return metas;
   }

   //============================================================
   // <T>保存配置信息对象到文件。</T>
   //
   // @param config 配置信息对象
   //============================================================
   protected void storeConfig(FXmlConfig config){
      if(null != config){
         String fileName = config.meta().fileName();
         if(_logger.debugAble()){
            _logger.debug(this, "storeConfig", "Store config. (name={1}, file_name={2})", RClass.dump(config.instance()), fileName);
         }
         _monitor.store(fileName);
      }
   }

   //============================================================
   // <T>从配置信息集合中移除一个配置信息对象。</T>
   //
   // @param config 配置信息对象
   //============================================================
   protected void removeConfig(FXmlConfig config){
      String name = config.meta().name();
      String fileName = config.meta().fileName();
      if(null != _configs.remove(name)){
         // 删除文件
         RFile.delete(fileName);
         if(_logger.debugAble()){
            _logger.debug(this, "removeConfig", "Remove config (file_name={1})", fileName);
         }
         // 删除空文件夹
         String path = RFile.path(fileName);
         if(!RFile.hasFile(path)){
            RFile.delete(path);
         }
      }else{
         _logger.warn(this, "removeConfig", "Remove config failed (file_name={1})", fileName);
      }
   }

   //============================================================
   // <T>转换一个数据对象，新建到配置对象集合中。</T>
   //
   // @param xobject 数据对象
   //============================================================
   protected void persistInstance(Object xobject){
      // 检查参数
      if(xobject == null){
         throw new FFatalError("Persist object is null.");
      }
      // 生成名称
      String name = makeName(xobject);
      FXmlConfig config = findConfig(name);
      if(null != config){
         throw new FFatalError("Xml config has exists (name={1})", name);
      }
      // 检查文件
      String fileName = makeFileName(name);
      if(RFile.exists(fileName)){
         throw new FFatalError("Xml config file has exists (filename={1})", fileName);
      }
      // 建立设置
      config = new FXmlConfig(this);
      config.meta().setName(name);
      config.meta().setFileName(fileName);
      config.setInstance(xobject);
      config.store();
      _configs.set(name, config);
      // 放入监视器
      _monitor.push(fileName);
   }

   //============================================================
   // <T>转换一个数据对象，保存到配置对象集合中。</T>
   //
   // @param xobject 数据对象
   //============================================================
   protected void storeInstance(Object xobject){
      // 检查参数
      if(xobject == null){
         throw new FFatalError("Store object is null.");
      }
      // 生成名称
      String name = makeName(xobject);
      FXmlConfig config = findConfig(name);
      if(null == config){
         throw new FFatalError("Config is not exists. (name={1})", name);
      }
      // 存储设置
      storeConfig(config);
   }

   //============================================================
   // <T>转换一个数据对象，从配置对象集合中删除。</T>
   //
   // @param xobject 数据对象
   //============================================================
   protected void removeInstance(Object xobject){
      // 检查参数
      if(xobject == null){
         throw new FFatalError("Remove object is null.");
      }
      // 生成名称
      String name = makeName(xobject);
      // 移除设置
      removeConfig(findConfig(name));
   }

   //============================================================
   // <T>初始化设置信息。</T>
   //============================================================
   public void initializeConfig(){
      if(!RFile.isDirectory(_workpath)){
         throw new FFatalError("Unkonwn path. (path={1})", _workpath);
      }
      int startLength = _workpath.length();
      FStrings files = RFile.listAllFile(_workpath);
      for(String filename : files){
         if(RFile.isExtension(filename, _extension)){
            String name = RFile.removeExtension(filename.substring(startLength + 1));
            name = RString.replaceChars(name, '/', '.', '\\', '.');
            // 加载设置信息
            FXmlConfig config = new FXmlConfig(this);
            config.meta().setName(name);
            config.meta().setFileName(filename);
            if(_logger.debugAble()){
               // _logger.debug(this, "initializeConfig", "Initialize xml config [{0}={1}]", name, filename);
            }
            _configs.set(name, config);
         }
      }
   }

   //============================================================
   // <T>初始化文件监视器。</T>
   //============================================================
   public void initializeMonitor(){
      _monitor = new FXmlConfigMonitor(this, _workpath);
      _monitor.setValid(true);
      _monitor.setInterval(_interval);
      _monitor.setStoreInterval(_storeInterval);
      _monitorConsole.register(_monitor);
   }

   //============================================================
   // <T>监视器刷新处理。</T>
   //============================================================
   @Override
   public void refreshMonitor(){
      //            synchronized(_configs){
      //               String filename, type, path;
      //               FXmlDataset dataset;
      //               int count = _configs.count();
      //               for(int n = 0; n < count; n++){
      //                  FXmlConfig config = _configs.value(n);
      //                  if(config.){
      //                     dataset = datasetMap.value(i);
      //                  }
      //                  if(dataset.testModified()){
      //                     filename = makeFilename(type, path);
      //                     saveDataset(new File(filename), dataset);
      //                  }
      //               }
      //            }
   }

   //============================================================
   // <T>中断当前操作，保存所有未保存数据。</T>
   //============================================================
   public void releaseInterrupt(){
      _monitor.storeAll();
   }

}
