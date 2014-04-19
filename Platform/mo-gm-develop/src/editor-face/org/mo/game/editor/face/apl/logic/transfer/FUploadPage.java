package org.mo.game.editor.face.apl.logic.transfer;

import org.mo.jfa.common.page.FAbstractFormPage;

public class FUploadPage
      extends FAbstractFormPage{

   private static final long serialVersionUID = 1L;

   private String _resultCode;

   private String _result;

   private String _attachment;

   private String _attachmentExtension;

   private String _attachmentGuid;

   private String _attachmentId;

   private String _attributes;

   private String _workerId;

   public String getAttachment(){
      return _attachment;
   }

   public String getAttachmentExtension(){
      return _attachmentExtension;
   }

   public String getAttachmentGuid(){
      return _attachmentGuid;
   }

   public String getAttachmentId(){
      return _attachmentId;
   }

   public String getAttributes(){
      return _attributes;
   }

   public String getWorkerId(){
      return _workerId;
   }

   public void setAttachment(String attachment){
      _attachment = attachment;
   }

   public void setAttachmentExtension(String attachmentExtension){
      _attachmentExtension = attachmentExtension;
   }

   public void setAttachmentGuid(String attachmentGuid){
      _attachmentGuid = attachmentGuid;
   }

   public void setAttachmentId(String attachmentId){
      _attachmentId = attachmentId;
   }

   public void setAttributes(String _attributes){
      this._attributes = _attributes;
   }

   public void setWorkerId(String workerId){
      _workerId = workerId;
   }

   public void setResult(String result){
      _result = result;
   }

   public String getResult(){
      return _result;
   }

   public void setResultCode(String resultCode){
      _resultCode = resultCode;
   }

   public String getResultCode(){
      return _resultCode;
   }

}
