package org.mo.eng.data.common;

public abstract class FSqlLogic
{
   //   private FSqlFields m_oFields = new FSqlFields();
   //
   //   private FSqlFields m_oKeyFields = new FSqlFields();
   //
   //   // 包名称
   //   private String m_sDataset = null;
   //
   //   private String m_sPackageName = null;
   //
   //   @SuppressWarnings("unused")
   //   private String m_sTableName = null;
   //
   //   /**
   //    * <p>创建数据库包处理对象的实例</p>
   //    * <p>create date:2005/11/03</p>
   //    * 
   //    * @param iConnectionAble 可获得链接的接口
   //    */
   //   public FSqlLogic(ISqlConnectAble iConnectAble,
   //                    String sDataset)
   //         throws FException{
   //      super(iConnectAble);
   //      m_sDataset = sDataset;
   //      m_sTableName = sDataset + "_DS";
   //      m_sPackageName = sDataset + "_DI";
   //      initialize();
   //   };
   //
   //   public FSqlProcedure doDelete(String sLogic,
   //                                 FStringList oParams)
   //         throws FException{
   //      return doDelete(sLogic, oParams.pack().toString());
   //   }
   //
   //   public FSqlProcedure doDelete(String sLogic,
   //                                 String sParams)
   //         throws FException{
   //      FSqlProcedure oProcedure = new FSqlProcedure(methodName("Do_Delete"));
   //      oProcedure.createParameter("logic_", sLogic, ISqlType.STRING, ISqlDirection.INPUT);
   //      oProcedure.createParameter("params_", sParams, ISqlType.STRING, ISqlDirection.INPUT_OUTPUT);
   //      activeConnection().execute(oProcedure);
   //      return oProcedure;
   //   }
   //
   //   public FSqlProcedure doInsert(String sLogic,
   //                                 FStringList oParams)
   //         throws FException{
   //      return doInsert(sLogic, oParams.pack().toString());
   //   }
   //
   //   public FSqlProcedure doInsert(String sLogic,
   //                                 String sParams)
   //         throws FException{
   //      FSqlProcedure oProcedure = new FSqlProcedure(methodName("Do_Insert"));
   //      oProcedure.createParameter("logic_", sLogic, ISqlType.STRING, ISqlDirection.INPUT);
   //      oProcedure.createParameter("params_", sParams, ISqlType.STRING, ISqlDirection.INPUT_OUTPUT);
   //      activeConnection().execute(oProcedure);
   //      return oProcedure;
   //   };
   //
   //   public FSqlProcedure doSync(String sLogic,
   //                               FStringList oParams)
   //         throws FException{
   //      return doSync(sLogic, oParams.pack().toString());
   //   }
   //
   //   public FSqlProcedure doSync(String sLogic,
   //                               String sParams)
   //         throws FException{
   //      FSqlProcedure oProcedure = new FSqlProcedure(methodName("Do_Sync"));
   //      oProcedure.createParameter("logic_", sLogic, ISqlType.STRING, ISqlDirection.INPUT);
   //      oProcedure.createParameter("params_", sParams, ISqlType.STRING, ISqlDirection.INPUT_OUTPUT);
   //      activeConnection().execute(oProcedure);
   //      return oProcedure;
   //   }
   //
   //   public FSqlProcedure doUpdate(String sLogic,
   //                                 FStringList oParams)
   //         throws FException{
   //      return doUpdate(sLogic, oParams.pack().toString());
   //   }
   //
   //   public FSqlProcedure doUpdate(String sLogic,
   //                                 String sParams)
   //         throws FException{
   //      FSqlProcedure oProcedure = new FSqlProcedure(methodName("Do_Update"));
   //      oProcedure.createParameter("logic_", sLogic, ISqlType.STRING, ISqlDirection.INPUT);
   //      oProcedure.createParameter("params_", sParams, ISqlType.STRING, ISqlDirection.INPUT_OUTPUT);
   //      activeConnection().execute(oProcedure);
   //      return oProcedure;
   //   }
   //
   //   protected boolean existsByKey(FStringList oValues)
   //         throws FException{
   //      FString sSqlCmd = new FString();
   //      sSqlCmd.append("SELECT * FROM ");
   //      sSqlCmd.append(m_sDataset);
   //      sSqlCmd.append(" WHERE ");
   //      sSqlCmd.append(makeWhereSql(oValues));
   //      return activeConnection().fetchUnit(sSqlCmd) != null;
   //   }
   //
   //   protected FRow fetchByKey(FStringList oValues)
   //         throws FException{
   //      FString sSqlCmd = new FString();
   //      sSqlCmd.append("SELECT * FROM ");
   //      sSqlCmd.append(m_sDataset);
   //      sSqlCmd.append(" WHERE ");
   //      sSqlCmd.append(makeWhereSql(oValues));
   //      return activeConnection().fetchUnit(sSqlCmd);
   //   }
   //
   //   public FDataset fetchDataset()
   //         throws FException{
   //      return fetchDataset(null, null, null);
   //   }
   //
   //   public FDataset fetchDataset(String sWhereSql)
   //         throws FException{
   //      return fetchDataset(null, sWhereSql, null);
   //   }
   //
   //   public FDataset fetchDataset(String sWhereSql,
   //                                String sOrderBy)
   //         throws FException{
   //      return fetchDataset(null, sWhereSql, sOrderBy);
   //   }
   //
   //   public FDataset fetchDataset(String sFields,
   //                                String sWhereSql,
   //                                String sOrderBy)
   //         throws FException{
   //      FString sSqlCmd = new FString();
   //      sSqlCmd.append("SELECT ");
   //      if(!FStringUtil.isEmpty(sFields)){
   //         sSqlCmd.append(sFields);
   //      }else{
   //         sSqlCmd.append("*");
   //      }
   //      sSqlCmd.append(" FROM ");
   //      sSqlCmd.append(m_sDataset);
   //      if(!FStringUtil.isEmpty(sWhereSql)){
   //         sSqlCmd.append(" WHERE ");
   //         sSqlCmd.append(sWhereSql);
   //      }
   //      if(!FStringUtil.isEmpty(sOrderBy)){
   //         sSqlCmd.append(" ORDER BY ");
   //         sSqlCmd.append(sOrderBy);
   //      }
   //      return activeConnection().fetchDataset(sSqlCmd);
   //   }
   //
   //   public FSqlFields fields(){
   //      return m_oFields;
   //   }
   //
   //   protected String fieldValueByKey(FSqlField oField,
   //                                    FStringList oParams)
   //         throws FException{
   //      FString sSqlCmd = new FString();
   //      sSqlCmd.append("SELECT ");
   //      sSqlCmd.append(oField.name());
   //      sSqlCmd.append(" FROM ");
   //      sSqlCmd.append(m_sDataset);
   //      sSqlCmd.append(" WHERE ");
   //      sSqlCmd.append(makeWhereSql(oParams));
   //      FRow oUnit = activeConnection().fetchUnit(sSqlCmd);
   //      return oUnit.value(oField.name());
   //   }
   //
   //   protected abstract void initialize()
   //         throws FException;
   //
   //   public FSqlFields keyFields(){
   //      return m_oFields;
   //   }
   //
   //   protected FString makeWhereSql(FStringList oValues){
   //      FString sSqlCmd = new FString();
   //      for(FSqlField oField : m_oKeyFields){
   //         if(sSqlCmd.isNotEmpty()){
   //            sSqlCmd.append(" AND ");
   //         }
   //         sSqlCmd.append("(");
   //         sSqlCmd.append(oField.name());
   //         sSqlCmd.append("='");
   //         sSqlCmd.append(oValues.value(oField.name()));
   //         sSqlCmd.append("')");
   //      }
   //      return sSqlCmd;
   //   }
   //
   //   // 生成调用方法的
   //   protected String methodName(String sMethodName)
   //         throws FException{
   //      return (sMethodName.indexOf(".") > 0) ? sMethodName : m_sPackageName + "." + sMethodName;
   //   }
   //
   //   // 生成调用方法的
   //   protected void pushField(FSqlField oField)
   //         throws FException{
   //      pushField(oField, false);
   //   }
   //
   //   protected void pushField(FSqlField oField,
   //                            boolean bIsKey)
   //         throws FException{
   //      if(bIsKey){
   //         m_oKeyFields.push(oField);
   //      }
   //      m_oFields.push(oField);
   //   }
}
