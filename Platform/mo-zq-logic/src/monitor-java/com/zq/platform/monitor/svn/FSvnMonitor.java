package com.zq.platform.monitor.svn;

import java.util.ArrayList;
import java.util.List;
import org.mo.com.lang.FFatalError;
import org.mo.com.lang.FString;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.core.aop.face.ALink;
import org.mo.core.aop.face.AProperty;
import org.mo.core.monitor.common.FAbstractMonitor;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNLogEntry;
import org.tmatesoft.svn.core.SVNLogEntryPath;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;
import org.tmatesoft.svn.core.wc.SVNWCUtil;

//============================================================
// <T>版本监视器。</T>
//============================================================
public class FSvnMonitor
      extends FAbstractMonitor
      implements
         ISvnMonitor
{
   // 日志输出接口
   private static ILogger _logger = RLogger.find(FSvnMonitor.class);

   // 网络地址
   @AProperty
   protected String _url;

   // 账号
   @AProperty
   protected String _passport;

   // 密码
   @AProperty
   protected String _password;

   // 路径
   @AProperty
   protected String _path;

   // 接收者集合
   @AProperty
   protected String _receivers;

   // RTX控制台
   @ALink
   //protected IRtxConsole _rtxConsole;
   // 版本仓库
   protected SVNRepository _repository;

   // 最终版本号
   protected long _latestRevision;

   //============================================================
   // <T>构造服务监视。</T>
   //============================================================
   public FSvnMonitor(){
   }

   //============================================================
   // <T>初始化监视器。</T>
   //============================================================
   @Override
   public void initialize(){
      super.initialize();
      try{
         // 解析地址
         SVNURL url = SVNURL.parseURIEncoded(_url);
         // 创建仓库
         _repository = SVNRepositoryFactory.create(url);
         // 设置权限
         ISVNAuthenticationManager authenticationManager = SVNWCUtil.createDefaultAuthenticationManager(_passport, _password);
         _repository.setAuthenticationManager(authenticationManager);
         // 获得最后版本
         _latestRevision = _repository.getLatestRevision();
      }catch(SVNException e){
         throw new FFatalError(e);
      }
      _logger.info(this, "initialize", "Svn monitor initialize. (url={1}, revision={2})", _url, _latestRevision);
   }

   //============================================================
   // <T>执行监视器的逻辑。</T>
   //
   // @return 处理结果
   //============================================================
   @Override
   public boolean onExecute(){
      try{
         // 获得当前版本
         long latestRevision = _repository.getLatestRevision();
         if(_latestRevision == latestRevision){
            _logger.info(this, "onExecute", "Execute svn monitor. (url={1}, revision={2})", _url, _latestRevision);
            return false;
         }
         _logger.info(this, "onExecute", "Execute svn monitor. (url={1}, revision={2}-{3})", _url, _latestRevision, latestRevision);
         // 获得提交日志
         List<SVNLogEntry> entries = new ArrayList<SVNLogEntry>();
         _repository.log(new String[]{}, entries, _latestRevision, latestRevision, true, true);
         // 生成信息
         for(SVNLogEntry entry : entries){
            // 检查版本
            long revision = entry.getRevision();
            if(revision == _latestRevision){
               continue;
            }
            // 获得信息
            FString info = new FString();
            info.appendLine("作者: " + entry.getAuthor());
            info.appendLine("内容: " + entry.getMessage().trim());
            info.appendLine("关联文件:");
            for(SVNLogEntryPath entrypath : entry.getChangedPaths().values()){
               String path = entrypath.getPath().trim();
               if(path.startsWith(_path)){
                  path = path.substring(_path.length());
               }
               info.appendLine("- " + path + "(" + entrypath.getType() + ")");
            }
            // 发送信息
            String title = "SVN更新 - " + _name + ":" + revision;
            //_rtxConsole.sendNotify(_receivers, title, info.toString());
         }
         // 设置最终版本
         _latestRevision = latestRevision;
      }catch(SVNException e){
         throw new FFatalError(e);
      }
      return true;
   }
}
