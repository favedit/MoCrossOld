package org.mo.eng.data.common;

//FILE_ID        NUMBER not null,
//FILE_TYPE      VARCHAR2(80) not null,
//FILE_SIZE      NUMBER,
//FILE_DATA      LONG RAW,
//OBJECT_OWNER   VARCHAR2(20),
//OBJECT_VERSION DATE
public class FSqlFileTable
{
   //   private String m_sTableName = null;
   //
   //   private String m_sSequenceName = null;
   //
   //   private FContext m_oContext = null;
   //
   //   public FSqlFileTable(FContext oContext)
   //         throws FException {
   //      m_oContext = oContext;
   //   }
   //
   //   public FSqlFileTable(FContext oContext,
   //                     String sTableName,
   //                     String sSequenceName)
   //         throws FException {
   //      m_oContext = oContext;
   //      m_sTableName = sTableName;
   //      m_sSequenceName = sSequenceName;
   //   }
   //
   //   public boolean doInsert(String sFileName,
   //                           FStringList oFileInfo)
   //         throws FException {
   //      FileInputStream oFileInputStream = null;
   //      PreparedStatement oStatement = null;
   //      try {
   //         FSequence oSequence = new FSequence(m_oContext, m_sSequenceName);
   //         int nNextValue = oSequence.getNextValueAsInteger();
   //         int nFileSize = oFileInfo.getValueAsInteger("file_size", 0);
   //         String sSqlCmd =
   //               "INSERT INTO " + m_sTableName +
   //               "(FILE_ID,FILE_NAME,FILE_TYPE,FILE_SIZE,FILE_DATA,OBJECT_OWNER,OBJECT_VERSION) VALUES(" +
   //               nNextValue + ",?,?,?,?,'0',SYSDATE)";
   //         oStatement = m_oContext.getActiveConnection().
   //               getSqlConnection().prepareStatement(sSqlCmd);
   //         oStatement.setString(1, oFileInfo.getValue("file_name"));
   //         oStatement.setString(2, oFileInfo.getValue("file_type"));
   //         oStatement.setInt(3, nFileSize);
   //         oFileInputStream = new FileInputStream(sFileName);
   //         oStatement.setBinaryStream(4, oFileInputStream, nFileSize);
   //         oStatement.executeUpdate();
   //         oFileInfo.setValue("file", nNextValue);
   //      } catch (Exception oException) {
   //         throw new FFatalException(this, oException);
   //      } finally {
   //         if (oStatement != null) {
   //            try {
   //               oStatement.close();
   //            } catch (Exception oException) {
   //               throw new FFatalException(this, oException);
   //            }
   //         }
   //         if (oFileInputStream != null) {
   //            try {
   //               oFileInputStream.close();
   //            } catch (Exception oException) {
   //               throw new FFatalException(this, oException);
   //            }
   //         }
   //      }
   //      return true;
   //   }
   //
   //   public boolean doUpdate(String sFileName,
   //                           FStringList oFileInfo,
   //                           String sFileId)
   //         throws FException {
   //      String sSqlCmd =
   //            "DELETE FROM " + m_sTableName + " WHERE FILE_ID=" + sFileId;
   //      m_oContext.getActiveConnection().executeSQL(sSqlCmd);
   //      return doInsert(sFileName, oFileInfo);
   //      FileInputStream oFileInputStream = null;
   //      PreparedStatement oStatement = null;
   //      try {
   //         int nFileSize = oFileInfo.getValueAsInteger("file_size", 0);
   //         String sSqlCmd =
   //               "UPDATE " + m_sTableName + " SET " +
   //               "FILE_NAME=?,FILE_TYPE=?,FILE_SIZE=?,FILE_DATA=?,OBJECT_OWNER='0',OBJECT_VERSION=SYSDATE " +
   //               "WHERE FILE_ID=" + sFileId;
   //         oStatement = m_oContext.getActiveConnection().
   //               getSqlConnection().prepareStatement(sSqlCmd);
   //         oStatement.setString(1, oFileInfo.getValue("file_name"));
   //         oStatement.setString(2, oFileInfo.getValue("file_type"));
   //         oStatement.setInt(3, nFileSize);
   //         oFileInputStream = new FileInputStream(sFileName);
   //         oStatement.setBinaryStream(4, oFileInputStream, nFileSize);
   //         oStatement.executeUpdate();
   //      } catch (Exception oException) {
   //         throw new FFatalException(this, oException);
   //      } finally {
   //         if (oStatement != null) {
   //            try {
   //               oStatement.close();
   //            } catch (Exception oException) {
   //               throw new FFatalException(this, oException);
   //            }
   //         }
   //         if (oFileInputStream != null) {
   //            try {
   //               oFileInputStream.close();
   //            } catch (Exception oException) {
   //               throw new FFatalException(this, oException);
   //            }
   //         }
   //         return true;
   //   }
}
