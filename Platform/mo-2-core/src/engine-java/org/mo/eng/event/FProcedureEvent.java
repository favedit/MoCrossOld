/*
 * @(#)FDatabaseEvent.java
 *
 * Copyright 2005 microbject, All Rights Reserved.
 *
 */
package org.mo.eng.event;

public class FProcedureEvent
      extends FDatabaseEvent
{
   //private String m_sProcedureName = null;
   //private String m_sErrorSQL = null;
   public FProcedureEvent(){
   }

   public FProcedureEvent(String sProcedureName,
                          String sErrorSQL){
      //m_sProcedureName = sProcedureName;
      //m_sErrorSQL = sErrorSQL;
   }
   //   public void execute(FContext oContext){
   //      //FDBProcedure oProcedure = new FDBProcedure(m_sProcedureName);
   //      //FDBConnection oConnection = oContext.getActiveConnection();
   //      //try {
   //      //   oConnection.execute(oProcedure);
   //      //} catch (Exception oException) {
   //      //   oConnection.rollback();
   //      //   oConnection.executeSQL(m_sErrorSQL);
   //      //   oConnection.commit();
   //      //   throw new FFatalException(this, oException);
   //      //}
   //   }
}
