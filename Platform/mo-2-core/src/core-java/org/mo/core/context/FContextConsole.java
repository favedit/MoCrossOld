package org.mo.core.context;

import org.mo.com.lang.FDictionary;
import org.mo.com.lang.FFatalError;

//============================================================
// <T>上下文控制台。</T>
//============================================================
public class FContextConsole
      implements
         IContextConsole
{
   // 环境字典
   protected FDictionary<IContext> _contexts = new FDictionary<IContext>(IContext.class);

   //============================================================
   // <T>构造上下文控制台。</T>
   //============================================================
   public FContextConsole(){
   }

   //============================================================
   // <T>绑定上下文。</T>
   //
   // @context 上下文
   //============================================================
   @Override
   public void bind(IContext context){
      // 检查上下文存在性
      String code = context.code();
      synchronized(_contexts){
         if(_contexts.contains(code)){
            throw new FFatalError("Context is already exists. (code={1})", code);
         }
         // 设置上下文存在性
         _contexts.set(code, context);
      }
   }

   //============================================================
   // <T>解除上下文绑定。</T>
   //
   // @param code 代码
   // @return 上下文
   //============================================================
   @Override
   @SuppressWarnings("unchecked")
   public <V extends IContext> V unbind(String code){
      IContext context = null;
      // 检查上下文存在性
      synchronized(_contexts){
         context = _contexts.find(code);
         if(null == context){
            throw new FFatalError("Context is not exists. (code={1})", code);
         }
         // 设置上下文存在性
         _contexts.remove(code);
      }
      return (V)context;
   }

   //============================================================
   // <T>解除上下文绑定。</T>
   //
   // @param context 上下文
   //============================================================
   @Override
   public void unbind(IContext context){
      String code = context.code();
      // 检查上下文存在性
      synchronized(_contexts){
         IContext find = _contexts.find(code);
         if(null == find){
            throw new FFatalError("Context is not exists. (context={1})", context);
         }
         if(context != find){
            throw new FFatalError("Context is not equals. (context={1})", context);
         }
         // 设置上下文存在性
         _contexts.remove(code);
      }
   }

   //============================================================
   // <T>关联上下文。</T>
   //
   // @param code 代码
   //============================================================
   @Override
   @SuppressWarnings("unchecked")
   public <V extends IContext> V link(String code){
      IContext context = null;
      // 检查上下文存在性
      synchronized(_contexts){
         context = _contexts.find(code);
      }
      return (V)context;
   }

   //============================================================
   // <T>断开上下文绑定。</T>
   //
   // @param code 代码
   //============================================================
   @Override
   public void unlink(String code){
      // 检查上下文存在性
      synchronized(_contexts){
         IContext context = _contexts.find(code);
         if(null == context){
            throw new FFatalError("Context is not exists. (code={1})", code);
         }
         // 设置上下文存在性
         _contexts.remove(code);
         // 释放上下文
         context.release();
      }
   }
}
