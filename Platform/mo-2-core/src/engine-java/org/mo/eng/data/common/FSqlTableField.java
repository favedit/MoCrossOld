package org.mo.eng.data.common;

public class FSqlTableField
{
   private String m_sName = null;

   //private int m_nType = ISqlType.UNKNOWN;
   private int m_nType;

   private boolean m_bAuto = false;

   public FSqlTableField(){
   }

   public FSqlTableField(String sName,
                         int nType){
      m_sName = sName;
      m_nType = nType;
   }

   public FSqlTableField(String sName,
                         int nType,
                         boolean bAuto){
      m_sName = sName;
      m_nType = nType;
      m_bAuto = bAuto;
   }

   public String name(){
      return m_sName;
   }

   public int type(){
      return m_nType;
   }

   public boolean auto(){
      return m_bAuto;
   }

   @Override
   public String toString(){
      return m_sName;
   }
}
