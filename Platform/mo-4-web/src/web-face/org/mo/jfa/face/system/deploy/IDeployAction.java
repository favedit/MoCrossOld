package org.mo.jfa.face.system.deploy;

import org.mo.web.core.container.AContainer;

import org.mo.web.protocol.context.IWebContext;

public interface IDeployAction
{

   String catalog(IWebContext context,
                  @AContainer(name = "page") FDeployPage page);

   String construct(IWebContext context,
                    @AContainer(name = "page") FDeployPage page);

   /**
    * <T>执行树目录节点的删除操作。</T>
    * 
    * @param context 页面连接
    * @param page 数据容器
    * @return 返回显示页面
    */
   String delete(IWebContext context,
                 @AContainer(name = "page") FDeployPage page);

   /**
    * <T>执行树节点的插入操作。</T>
    * 
    * @param context 页面环境
    * @param page 数据容器
    * @return 返回页面
    */
   String insert(IWebContext context,
                 @AContainer(name = "page") FDeployPage page);

   /**
    * <T>执行树节点展开操作。</T>
    * 
    * @param context 页面连接
    * @param page 数据容器
    * @return 返回显示页面
    */
   String list(IWebContext context,
               @AContainer(name = "page") FDeployPage page);

   String show(IWebContext context,
               @AContainer(name = "page") FDeployPage page);

   /**
    * <T>执行树节点排序操作。</T>
    * 
    * @param context 页面连接
    * @param page 数据容器
    * @return 返回显示页面
    */
   String sort(IWebContext context,
               @AContainer(name = "page") FDeployPage page);

   /**
    * <T>执行树节点更新操作。</T>
    * 
    * @param context 页面连接
    * @param page 数据容器
    * @return 返回显示页面
    */
   String update(IWebContext context,
                 @AContainer(name = "page") FDeployPage page);

   String execute(IWebContext context,
                  @AContainer(name = "page") FDeployPage page);

   String executeProcess(IWebContext context,
                         @AContainer(name = "page") FDeployPage page);
}
