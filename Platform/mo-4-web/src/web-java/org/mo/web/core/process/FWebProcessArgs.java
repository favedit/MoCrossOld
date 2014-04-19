package org.mo.web.core.process;

import org.mo.com.lang.FMap;
import org.mo.eng.data.common.ISqlContext;
import org.mo.web.protocol.context.IWebContext;

public class FWebProcessArgs
{

   private ISqlContext _sqlContext;

   @SuppressWarnings("rawtypes")
   private final FMap<Class, Object> _typeValue = new FMap<Class, Object>(Class.class, Object.class);

   private IWebContext _webContext;

   public void define(Class<?> clazz,
                      Object value){
      _typeValue.set(clazz, value);
   }

   public boolean defineContains(Class<?> clazz){
      return _typeValue.contains(clazz);
   }

   public Object defineGet(Class<?> clazz){
      return _typeValue.get(clazz);
   }

   public ISqlContext getSqlContext(){
      return _sqlContext;
   }

   public IWebContext getWebContext(){
      return _webContext;
   }

   public void setSqlContext(ISqlContext sqlContext){
      _sqlContext = sqlContext;
   }

   public void setWebContext(IWebContext webContext){
      _webContext = webContext;
   }

}
