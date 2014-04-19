package org.mo.web.core.servlet.common;

import org.mo.com.lang.RDump;
import org.mo.com.lang.RString;
import org.mo.com.lang.generic.TDumpInfo;
import org.mo.com.lang.reflect.FClass;
import org.mo.com.lang.reflect.RClass;
import org.mo.com.xml.FXmlNode;
import org.mo.core.aop.config.XAbsAopNode;

public class XAopServlet
      extends XAbsAopNode
{

   public final static String NAME = "WebServlet";

   public final static String PTY_FACE = "face";

   public final static String PTY_NAME = "name";

   protected String _face;

   private FClass<?> _faceClass;

   protected String _name;

   public XAopServlet(){
   }

   public void assign(XAopServlet action){
      _name = action._name;
      _face = action._face;
      _faceClass = action._faceClass;
   }

   @Override
   public TDumpInfo dump(TDumpInfo info){
      info.appendLine("name:       " + _name);
      info.appendLine("Face:       " + _face);
      info.appendLine("Face class: " + _faceClass);
      if(_config != null){
         info.appendLine(RDump.LINE_L2);
         info.appendLine(_config.dump());
      }
      return info;
   }

   public String face(){
      return _face;
   }

   public FClass<?> faceClass(){
      return _faceClass;
   }

   @Override
   public String key(){
      return _name;
   }

   @Override
   public void loadConfig(FXmlNode config){
      super.loadConfig(config);
      setName(RString.trim(config.get(PTY_NAME)));
      setFace(RString.trim(config.get(PTY_FACE)));
   }

   public String name(){
      return _name;
   }

   public void setFace(String face){
      _face = face;
      _faceClass = RClass.find(_face);
   }

   public void setName(String name){
      _name = name;
   }

}
