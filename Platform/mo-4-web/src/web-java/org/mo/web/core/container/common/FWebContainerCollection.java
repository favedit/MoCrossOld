package org.mo.web.core.container.common;

import java.io.Serializable;
import org.mo.com.lang.reflect.RClass;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.web.core.container.EContainerScope;

//============================================================
// <T>根表单。</T>
//============================================================
public class FWebContainerCollection
      implements
         Serializable
{
   // 序列化编号
   private static final long serialVersionUID = 1L;

   // 日志输出接口
   private static ILogger _logger = RLogger.find(FWebContainerCollection.class);

   // 容器类型
   protected EContainerScope _scopeCd;

   // 容器集合
   protected FWebContainerMap _containers = new FWebContainerMap();

   //============================================================
   // <T>构造根表单。</T>
   //============================================================
   public FWebContainerCollection(){
   }

   //============================================================
   // <T>获得容器类型。</T>
   //
   // @return 容器类型
   //============================================================
   public EContainerScope scopeCd(){
      return _scopeCd;
   }

   //============================================================
   // <T>设置容器类型。</T>
   //
   // @param scopeCd 容器类型
   //============================================================
   public void setScopeCd(EContainerScope scopeCd){
      _scopeCd = scopeCd;
   }

   //============================================================
   // <T>创建表单。</T>
   //
   // @param clazz 类对象
   // @return 表单
   //============================================================
   public FWebContainerItem createContainer(Class<?> clazz){
      _logger.debug(this, "createContainer", "Create container. (class={1})", clazz);
      // 创建容器
      Object container = RClass.newInstance(clazz);
      // 创建项目
      FWebContainerItem item = new FWebContainerItem();
      item.setContainer(container);
      return item;
   }

   //============================================================
   // <T>根据名称和类对象获得表单。</T>
   //
   // @param name 名称
   // @param clazz 类对象
   // @return 表单
   //============================================================
   public FWebContainerItem findContainer(String name,
                                          Class<?> clazz){
      String containerName = name + "@" + clazz.getName();
      FWebContainerItem container = _containers.find(containerName);
      if(container == null){
         container = createContainer(clazz);
         _containers.set(containerName, container);
      }
      return container;
   }
}
