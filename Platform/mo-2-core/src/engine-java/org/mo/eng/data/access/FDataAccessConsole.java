package org.mo.eng.data.access;

public class FDataAccessConsole
{
   //implements IDataAccessConsole{
   //   private FNameMap<FDataAccessFace> _dataFaces = new FNameMap<FDataAccessFace>(
   //         FDataAccessFace.class);
   //
   //   @ALink
   //   private IDatabaseConsole _databaseConsole;
   //
   //   @ALink
   //   private IContextConsole _contextConsole;
   //
   //   protected FDataAccessFace syncFace(Class clazz){
   //      FDataAccessFace face = _dataFaces.value(clazz.getName());
   //      if(face == null){
   //         face = new FDataAccessFace(clazz);
   //         _dataFaces.setValue(clazz.getName(), face);
   //      }
   //      return face;
   //   }
   //
   //   public Object proxy(FAttributes attributes,
   //                       Class clazz,
   //                       Method method,
   //                       Object[] params){
   //      String cnnName = attributes.value("connection");
   //      if(FStringUtil.isEmpty(cnnName)){
   //         IContext context = _contextConsole.link();
   //         if(context instanceof IConnect){
   //            IConnection connection = ((IConnect) context).activeConnection();
   //            return syncFace(clazz).execute(connection, method, null, params);
   //         }else{
   //            throw new FFatalException("Context sql connect disable. {0}", context);
   //         }
   //      }
   //      IConnection connection = null;
   //      try{
   //         String[] paramNames = null;
   //         connection = _databaseConsole.allocConnection(cnnName);
   //         String path = attributes.value("path");
   //         if(!FStringUtil.isEmpty(path)){
   //            String classname = FStringUtil.replaceChars(clazz.getName(), '.', '/');
   //            String filename = FFileUtil.makeFilename(path, classname) + ".java";
   //            if(FFileUtil.exists(filename)){
   //               FJavaParserWorker worker = new FJavaParserWorker(new FStringFile(filename));
   //               worker.parse();
   //               FJavaClass javaClass = worker.result();
   //               FJavaMethod javaMethod = javaClass.methods().value(method.getName());
   //               if(javaMethod != null){
   //                  int count = javaMethod.parameters().count();
   //                  paramNames = new String[count];
   //                  for(int n = 0; n < count; n++){
   //                     paramNames[n] = javaMethod.parameters().value(n).name();
   //                  }
   //               }
   //            }
   //         }
   //         return syncFace(clazz).execute(connection, method, paramNames, params);
   //      }catch(Exception e){
   //         throw new FFatalException(e);
   //      }finally{
   //         if(connection != null){
   //            connection.close();
   //         }
   //      }
   //
   //   }
}
