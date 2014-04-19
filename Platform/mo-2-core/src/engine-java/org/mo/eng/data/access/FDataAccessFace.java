package org.mo.eng.data.access;

public class FDataAccessFace
{
   //   private Class _class;
   //
   //   public FDataAccessFace(Class clazz){
   //      _class = clazz;
   //      build();
   //   }
   //
   //   protected void build(){
   //   }
   //
   //   private FString parseSql(FString sql,
   //                            String[] params,
   //                            Object[] values){
   //      String[] lines = FStringUtil.split(sql.toString(), '\n');
   //      sql.clear();
   //      for(String line : lines){
   //         if(line != null){
   //            if(line.trim().length() == 0){
   //               continue;
   //            }else if(line.trim().startsWith("--")){
   //               continue;
   //            }
   //            sql.appendLine(line);
   //         }
   //      }
   //      return sql;
   //   }
   //
   //   public Object execute(IConnection connection,
   //                         Method method,
   //                         String[] params,
   //                         Object[] values){
   //      String name = method.getName();
   //      String className = method.getDeclaringClass().getName();
   //      String resName = FClassUtil.shortName(className) + "_" + method.getName() + ".sql";
   //      URL url = method.getDeclaringClass().getResource(resName);
   //      if(url != null){
   //         FStringFile sql = new FStringFile(url.getFile());
   //         sql = (FStringFile) parseSql(sql, params, values);
   //         return connection.fetchDataset(sql);
   //
   //      }else if(name.startsWith("find")){
   //         return find(connection, method, params);
   //      }
   //      return null;
   //   }
   //
   //   @SuppressWarnings("unchecked")
   //   public Object find(IConnection connection,
   //                      Method method,
   //                      Object[] params){
   //      Class returnType = method.getReturnType();
   //      FGetSetDescriptor returnDsp = new FGetSetDescriptor(returnType);
   //
   //      AName classAname = (AName) returnType.getAnnotation(AName.class);
   //      String tableName = classAname.value();
   //      String sql = "SELECT * FROM " + tableName;
   //
   //      FRow unit = connection.fetchUnit(new FString(sql));
   //      if(unit != null){
   //         try{
   //            Object result = returnType.newInstance();
   //            for(Field filed : returnType.getDeclaredFields()){
   //               AName fieldAname = (AName) filed.getAnnotation(AName.class);
   //               if(fieldAname != null){
   //                  String name = fieldAname.value();
   //                  String fieldName = filed.getName().toLowerCase();
   //                  returnDsp.setValue(result, fieldName, unit.value(name));
   //               }
   //            }
   //            return result;
   //         }catch(Exception e){
   //            throw new FFatalException(e);
   //         }
   //      }
   //      return null;
   //   }
}
