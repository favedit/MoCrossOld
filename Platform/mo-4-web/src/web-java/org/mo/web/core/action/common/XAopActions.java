package org.mo.web.core.action.common;

import org.mo.com.lang.RString;
import org.mo.com.regex.FPattern;
import org.mo.com.xml.FXmlNode;
import org.mo.core.aop.config.XAbsAopNode;

public class XAopActions
      extends XAbsAopNode
{

   public final static String NAME = "Actions";

   public final static String PTY_FACE = "face";

   public final static String PTY_URI = "uri";

   protected String _face;

   private FPattern _pattern;

   protected String _uri;

   public String buildFace(String uri){
      if(_uri.endsWith("/*/*")){
         String pre = _uri.substring(0, _uri.length() - "/*/*".length());
         if(uri.startsWith(pre)){
            uri = uri.substring(pre.length());
         }
         if(uri.indexOf('/') != uri.lastIndexOf('/')){
            String[] faces = RString.split(_face, '*');
            String first = uri.substring(uri.indexOf('/') + 1, uri.lastIndexOf('/'));
            first = RString.replaceChars(first, '/', '.');
            String end = uri.substring(uri.lastIndexOf('/') + 1);
            return faces[0] + first + faces[1] + end + faces[2];
         }
      }else if(_uri.endsWith("/*")){
         String pre = _uri.substring(0, _uri.length() - "/*".length());
         if(uri.startsWith(pre)){
            uri = uri.substring(pre.length());
         }
         if(uri.indexOf('/') == uri.lastIndexOf('/')){
            String[] faces = RString.split(_face, '*');
            String mid = uri.substring(uri.indexOf('/') + 1);
            return faces[0] + mid + faces[1];
         }
      }
      return null;
   }

   public XAopAction createAction(String uri){
      XAopAction xaction = new XAopAction();
      xaction = new XAopAction();
      xaction.loadConfig(_config);
      xaction.setUri(uri);
      xaction.setFace(buildFace(uri));
      return xaction;
   }

   public String face(){
      return _face;
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
      _pattern = new FPattern(_uri);
   }

   public boolean match(String uri){
      return _pattern.matches(uri);
   }

   public void setFace(String face){
      _face = face;
   }

   public void setUri(String uri){
      _uri = uri;
   }

   public String uri(){
      return _uri;
   }

}
