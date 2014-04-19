package org.mo.web.core.session;

import org.mo.com.lang.RDump;
import org.mo.com.lang.generic.TDumpInfo;
import org.mo.eng.session.common.FSession;
import org.mo.web.core.container.EContainerScope;
import org.mo.web.core.container.common.FWebContainerCollection;
import org.mo.web.protocol.common.FWebHistory;
import org.mo.web.protocol.common.FWebUser;
import org.mo.web.protocol.common.IWebUser;

//============================================================
// <T>网络会话。</T>
//============================================================
public class FWebSession
      extends FSession
      implements
         IWebSession
{
   // 序列化标志
   private static final long serialVersionUID = 1L;

   // 关联标识
   private String _connectId = "0";

   // 主题标识
   private String _themeId;

   // 登录用户
   private FWebUser _user;

   // 容器集合
   private FWebContainerCollection _containers;

   // 历史管理器
   private FWebHistory _history;

   //============================================================
   // <T>构造网络会话。</T>
   //============================================================
   public FWebSession(){
   }

   //============================================================
   // <T>获得链接编号。</T>
   //
   // @return 链接编号
   //============================================================
   @Override
   public String connectId(){
      return _connectId;
   }

   //============================================================
   // <T>设置链接编号。</T>
   //
   // @param connectId 链接编号
   //============================================================
   @Override
   public void setConnectId(String connectId){
      _connectId = connectId;
   }

   //============================================================
   // <T>获得主题编号。</T>
   //
   // @return 主题编号
   //============================================================
   @Override
   public String themeId(){
      if(_themeId == null){
         _themeId = "00";
      }
      return _themeId;
   }

   //============================================================
   // <T>设置主题编号。</T>
   //
   // @param themeId 主题编号
   //============================================================
   @Override
   public void setThemeId(String themeId){
      _themeId = themeId;
   }

   //============================================================
   // <T>判断是否登录。</T>
   //
   // @return 是否登录
   //============================================================
   @Override
   public boolean isLogin(){
      return user().isLogin();
   }

   //============================================================
   // <T>获得网络用户。</T>
   //
   // @return 网络用户
   //============================================================
   @Override
   public IWebUser user(){
      if(_user == null){
         _user = new FWebUser();
      }
      return _user;
   }

   //============================================================
   // <T>获得容器集合。</T>
   //
   // @return 容器集合
   //============================================================
   @Override
   public FWebContainerCollection containers(){
      if(_containers == null){
         _containers = new FWebContainerCollection();
         _containers.setScopeCd(EContainerScope.Session);
      }
      return _containers;
   }

   //============================================================
   // <T>获得网络历史。</T>
   //
   // @return 网络历史
   //============================================================
   @Override
   public FWebHistory history(){
      if(_history == null){
         _history = new FWebHistory(this);
      }
      return _history;
   }

   //============================================================
   // <T>生成运行信息。</T>
   //
   // @param info 运行信息
   // @return 运行信息
   //============================================================
   @Override
   public TDumpInfo dump(TDumpInfo info){
      RDump.dump(info);
      info.append("\n   User:");
      info.append(_user.parameters().dump());
      return info;
   }
}
