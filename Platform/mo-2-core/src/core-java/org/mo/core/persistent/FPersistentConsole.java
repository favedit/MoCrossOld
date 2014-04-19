package org.mo.core.persistent;

import org.mo.com.lang.FDictionary;
import org.mo.core.aop.face.AProperty;
import org.mo.core.persistent.common.FPersistentContext;
import org.mo.core.persistent.loader.FPersistentDescriptor;
import org.mo.core.persistent.loader.FPersistentLoader;

public class FPersistentConsole
      implements
         IPersistentConsole
{
   //private static ILogger _logger = new FLogger(FPersistentConsole.class);
   private FPersistentContext _context;

   private FPersistentLoader _loader;

   //@ALink
   //private IConfigConsole _configConsole;
   @AProperty
   private String _path;

   @Override
   public FPersistentDescriptor descriptor(Class<?> clazz){
      return _loader.descriptor(clazz);
   }

   private final FDictionary<FSegment> _segments = new FDictionary<FSegment>(FSegment.class);

   public void initializeContext(){
      _context = new FPersistentContext();
      _context.setPath(_path);
   }

   public void initializePersistent(){
      //      if (true) {
      //         return;
      //      }
      //      _loader = new FPersistentLoader(getClass().getClassLoader());
      //      FXmlNodeMap nodeMap = _configConsole.nodeMap("Persistent");
      //      if (nodeMap != null) {
      //         FXmlNode node;
      //         String className;
      //         FSegment segment;
      //         int count = nodeMap.count();
      //         for (int n = 0; n < count; n++) {
      //            node = nodeMap.value(n);
      //            _loader.configs().setValue(node.attribute("class"), node);
      //         }
      //         for (int n = 0; n < count; n++) {
      //            node = nodeMap.value(n);
      //            className = node.attribute("class");
      //            if (_logger.debugAble()) {
      //               _logger.debug("Build persistent class [{0}]", className);
      //            }
      //            try {
      //               Class clazz = _loader.loadClass(className);
      //               segment = new FSegment(_context, clazz);
      //               _segments.setValue(className, segment);
      //            } catch (ClassNotFoundException e) {
      //               _logger.error(e);
      //            }
      //         }
      //      }
   }

   public void releasePersistent(){
      int count = _segments.count();
      for(int n = 0; n < count; n++){
         _segments.value(n).close();
      }
   }

   @Override
   public FSegment findSegment(Class<?> clazz){
      return _segments.get(clazz.getName());
   }

   @Override
   public FSegment syncSegment(Class<?> clazz){
      FSegment segment = _segments.get(clazz.getName());
      if(segment == null){
         segment = new FSegment(_context, clazz);
         _segments.set(clazz.getName(), segment);
      }
      return segment;
   }

   @Override
   public Object persist(Class<?> clazz,
                         Object item){
      return syncSegment(clazz).persist(item);
   }

   public Object synchronize(Class<?> clazz,
                             Object item){
      //return syncSegment(clazz).synchronize(item);
      return null;
   }

   @Override
   public Object modify(Class<?> clazz,
                        Object item){
      return syncSegment(clazz).modify(item);
   }

   @Override
   public Object remove(Class<?> clazz,
                        Object item){
      return syncSegment(clazz).remove(item);
   }

   @Override
   public Object find(Class<?> clazz,
                      int oid){
      return syncSegment(clazz).find(oid);
   }

   @Override
   public Object find(Class<?> clazz,
                      String name){
      return syncSegment(clazz).find(name);
   }

   @Override
   public boolean exists(Class<?> clazz,
                         String name){
      return syncSegment(clazz).find(name) != null;
   }

   @Override
   public FDictionary<?> list(Class<?> clazz){
      return null;
   }
}
