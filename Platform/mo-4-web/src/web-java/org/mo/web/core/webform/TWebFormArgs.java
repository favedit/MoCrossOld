package org.mo.web.core.webform;

import org.mo.com.lang.RDump;
import org.mo.com.lang.generic.TDumpInfo;
import org.mo.com.xml.EXmlConfig;
import org.mo.eng.data.common.ISqlContext;

public class TWebFormArgs
{

   private EXmlConfig _config = EXmlConfig.Simple;

   private String _name;

   private boolean _skipUnused;

   private ISqlContext _sqlContext;

   public TWebFormArgs(){
   }

   public TWebFormArgs(String name){
      _name = name;
   }

   public TWebFormArgs(String name,
                       EXmlConfig configType){
      _name = name;
      _config = configType;
   }

   public TWebFormArgs(String name,
                       EXmlConfig configType,
                       ISqlContext sqlContext){
      _name = name;
      _config = configType;
      _sqlContext = sqlContext;
   }

   public TWebFormArgs(String name,
                       EXmlConfig configType,
                       ISqlContext sqlContext,
                       boolean skipUnused){
      _name = name;
      _config = configType;
      _sqlContext = sqlContext;
      _skipUnused = skipUnused;
   }

   public EXmlConfig config(){
      return _config;
   }

   public TDumpInfo dump(){
      TDumpInfo info = new TDumpInfo();
      RDump.dump(info, this);
      info.append("[name=", _name);
      info.append(",config=", _config);
      info.append(",sqlContext=", _sqlContext);
      info.append("]");
      return info;
   }

   public boolean isSkipUnused(){
      return _skipUnused;
   }

   public String name(){
      return _name;
   }

   public void setConfig(EXmlConfig config){
      _config = config;
   }

   public void setName(String name){
      _name = name;
   }

   public void setSqlContext(ISqlContext sqlContext){
      _sqlContext = sqlContext;
   }

   public ISqlContext sqlContext(){
      return _sqlContext;
   }

}
