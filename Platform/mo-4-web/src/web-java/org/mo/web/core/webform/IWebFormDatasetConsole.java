package org.mo.web.core.webform;

import org.mo.com.collections.FDatasets;
import org.mo.com.xml.FXmlNode;

public interface IWebFormDatasetConsole
{

   /**
    * <T>根据表单定义和数据内容，删除数据数据。</T>
    * 
    * @param args 参数对象
    */
   void delete(FWebFormDatasetArgs args);

   /**
    * <T>根据表单对象，查询数据库，生成结果对象的集合。</T>
    * 
    * @param args 查询用的参数
    * @return 数据集集合
    */
   FDatasets fetch(FWebFormDatasetArgs args);

   /**
    * 根据查询参数内容，根据Form对象，查询数据库，生成结果对象。
    * 
    * @param args 查询用的参数
    * @return 获得查询结果（XML节点）
    */
   FXmlNode fetchNode(FWebFormDatasetArgs args);

   FXmlNode fetchNodeAll(FWebFormDatasetArgs args);

   FXmlNode find(FWebFormDatasetArgs args);

   /**
    * <T>根据表单定义和数据内容，新建数据数据。</T>
    * 
    * @param args 参数对象
    */
   void insert(FWebFormDatasetArgs args);

   /**
    * <T>根据表单对象，查询数据库，生成新建记录的数据集集合。</T>
    * 
    * @param args 查询用的参数
    * @return 数据集集合
    * @history 090706 MAOCY 返回数据集集合
    */
   FDatasets prepare(FWebFormDatasetArgs args);

   /**
    * <T>连接与数据库的线程。</T>
    * 
    * @param args 参数对象
    */
   void sessionLink(FWebFormDatasetArgs args);

   /**
    * <T>断开与数据库的线程。</T>
    * 
    * @param args 参数对象
    */
   void sessionUnlink(FWebFormDatasetArgs args);

   /**
    * <T>根据表单定义和数据内容，更新数据数据。</T>
    * 
    * @param args 参数对象
    */
   void update(FWebFormDatasetArgs args);

}
