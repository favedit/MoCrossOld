package org.mo.web.core.action.common;

import org.mo.com.lang.RDump;
import org.mo.com.lang.RString;
import org.mo.com.lang.generic.TDumpInfo;
import org.mo.com.lang.reflect.FClass;
import org.mo.com.lang.reflect.RClass;
import org.mo.com.xml.FXmlNode;
import org.mo.core.aop.config.XAbsAopNode;

public class XAopAction
      extends XAbsAopNode
{

   //private static ILogger _logger = RLogger.find(XAopAction.class);

   public final static String NAME = "Action";

   public final static String PTY_FACE = "face";

   public final static String PTY_URI = "uri";

   protected String _face;

   private FClass<?> _faceClass;

   protected String _uri;

   public XAopAction(){
   }

   public void assign(XAopAction action){
      _uri = action._uri;
      _face = action._face;
      _faceClass = action._faceClass;
   }

   public void assign(XAopActions actions){
      _uri = actions._uri;
      _face = actions._face;
   }

   public TDumpInfo dump(TDumpInfo info){
      info.appendLine("Uri:       " + _uri);
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
      return _uri;
   }

   @Override
   public void loadConfig(FXmlNode config){
      super.loadConfig(config);
      setUri(RString.trim(config.get(PTY_URI)));
      setFace(RString.trim(config.get(PTY_FACE)));
   }

   public void setFace(String face){
      _face = face;
      if(RClass.exists(_face)){
         _faceClass = RClass.find(_face);
      }
   }

   public void setUri(String uri){
      _uri = uri;
   }

   public String uri(){
      return _uri;
   }

}
