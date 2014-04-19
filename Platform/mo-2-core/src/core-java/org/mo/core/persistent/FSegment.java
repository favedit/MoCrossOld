package org.mo.core.persistent;

import org.mo.core.persistent.common.IPersistentContext;
import org.mo.core.persistent.io.FSegmentFile;

public class FSegment
{
   private final FSegmentFile _file;

   private final Class<?> _class;

   public FSegment(IPersistentContext context,
                   Class<?> clazz){
      _file = new FSegmentFile(context, clazz);
      _class = clazz;
   }

   public FSegmentFile file(){
      return _file;
   }

   public Class<?> clazz(){
      return _class;
   }

   public Object find(int oid){
      return _file.findById(oid);
   }

   public boolean exists(String name){
      return _file.existsByName(name);
   }

   public boolean exists(int oid){
      return _file.existsById(oid);
   }

   public Object find(String name){
      return _file.findByName(name);
   }

   public Object persist(Object value){
      _file.persist("", value);
      return value;
   }

   public Object modify(Object value){
      _file.modifyById(0, value);
      return value;
   }

   public Object synchronize(String name,
                             Object value){
      if(_file.findByName(name) == null){
         persist(value);
      }else{
         modify(value);
      }
      return value;
   }

   public Object remove(Object value){
      _file.removeById(0);
      return value;
   }

   public void close(){
      _file.close();
   }
}
