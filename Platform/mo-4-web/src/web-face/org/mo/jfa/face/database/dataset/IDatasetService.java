package org.mo.jfa.face.database.dataset;

import org.mo.web.protocol.context.IWebContext;
import org.mo.web.protocol.context.IWebInput;
import org.mo.web.protocol.context.IWebOutput;

//============================================================
// <T>数据集合服务接口。</T>
//============================================================
public interface IDatasetService
{
   //============================================================
   // <T>获得数据集合目录。</T>
   //
   // @param context 网页环境
   // @param input 输入信息
   // @param output 输出信息
   //============================================================
   void catalog(IWebContext context,
                IWebInput input,
                IWebOutput output);

   void list(IWebContext context,
             IWebInput input,
             IWebOutput output);

   void search(IWebContext context,
               IWebInput input,
               IWebOutput output);

   void insert(IWebContext context,
               IWebInput input,
               IWebOutput output);

   void update(IWebContext context,
               IWebInput input,
               IWebOutput output);

   void delete(IWebContext context,
               IWebInput input,
               IWebOutput output);

   void sort(IWebContext context,
             IWebInput input,
             IWebOutput output);

   void dsFetch(IWebContext context,
                IWebInput input,
                IWebOutput output);

   void dsPicker(IWebContext context,
                 IWebInput input,
                 IWebOutput output);

   void dsUpdate(IWebContext context,
                 IWebInput input,
                 IWebOutput output);
}
