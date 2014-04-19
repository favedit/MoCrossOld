package org.mo.web.core.action.common;

import org.mo.com.lang.FObjectId;
import org.mo.com.xml.FXmlNode;

//============================================================
// <T>消息页面。</T>
//============================================================
public class FWebMessagePage
      extends FObjectId
{
   protected String _type;

   protected String _code;

   protected String _message;

   protected FXmlNode _messages;

   protected String _description;

   public String getType(){
      return _type;
   }

   public void setType(String type){
      _type = type;
   }

   public String getCode(){
      return _code;
   }

   public void setCode(String code){
      _code = code;
   }

   public String getMessage(){
      return _message;
   }

   public void setMessage(String message){
      _message = message;
   }

   public FXmlNode getMessages(){
      return _messages;
   }

   public void setMessages(FXmlNode messages){
      _messages = messages;
   }

   public String getDescription(){
      return _description;
   }

   public void setDescription(String description){
      _description = description;
   }
}
