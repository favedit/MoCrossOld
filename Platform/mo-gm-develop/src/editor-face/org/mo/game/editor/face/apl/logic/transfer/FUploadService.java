package org.mo.game.editor.face.apl.logic.transfer;

import java.io.File;
import org.mo.com.data.FSqlProcedure;
import org.mo.com.io.RFile;
import org.mo.com.lang.FAttributes;
import org.mo.com.lang.IAttributes;
import org.mo.com.lang.RInteger;
import org.mo.com.lang.RString;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.IXmlObject;
import org.mo.core.aop.face.ALink;
import org.mo.jfa.common.xmlobjects.FAbsXmlObjectService;
import org.mo.logic.data.ISyAttachmentDi;
import org.mo.logic.store.IStoreConsole;
import org.mo.web.protocol.context.IWebContext;
import org.mo.web.protocol.context.IWebInput;
import org.mo.web.protocol.context.IWebOutput;

public class FUploadService
      extends FAbsXmlObjectService<IXmlObject>
      implements
         IUploadService
{

   private static ILogger _logger = RLogger.find(FUploadService.class);

   public static String NAME_FILE = "File";

   @ALink
   protected IStoreConsole _storeConsole;

   @Override
   public void deleteFile(IWebContext context,
                          ISyAttachmentDi syAttachmentDi,
                          IWebInput input,
                          IWebOutput output){
      FXmlNode fileNode = input.config().findNode(NAME_FILE);
      FXmlNode outputFileNode = output.config().syncNode(NAME_FILE);
      if(null != fileNode){
         // 删除数据库文件信息
         FSqlProcedure procedure = syAttachmentDi.doDeleteFile(null, fileNode.attributes(), null);
         IAttributes results = procedure.parameter("result_").asAttributes();
         String recordCode = results.get("RECORD_CODE");
         String recordGuid = results.get("RECORD_GUID");
         String guid = results.get("GUID");
         String extendName = results.get("MIME");
         outputFileNode.attributes().append(results);
         // 获得目标文件名称
         String targetPath = _storeConsole.systemResourcePath(recordCode, recordGuid, guid, extendName);
         String targetIconPath = _storeConsole.systemResourcePath(recordCode, recordGuid, guid + ".icon", extendName);
         // 删除存储文件
         boolean isSuccess = RFile.delete(targetPath);
         if(_logger.debugAble()){
            _logger.debug(this, "deleteFile", "Delete resource file. (result={0},path={1})", isSuccess, targetPath);
         }
         if(RFile.isFile(targetIconPath)){
            isSuccess = RFile.delete(targetIconPath);
            if(_logger.debugAble()){
               _logger.debug(this, "deleteFile", "Delete resource file. (result={0},path={1})", isSuccess, targetIconPath);
            }
         }
      }
   }

   @SuppressWarnings("unused")
   @Override
   public void previewSave(IWebContext context,
                           ISyAttachmentDi syAttachmentDi,
                           IWebInput input,
                           IWebOutput output){
      FXmlNode fileNode = input.config().findNode(NAME_FILE);
      FXmlNode outputFileNode = output.config().syncNode(NAME_FILE);
      if(null != fileNode){
         // 获得文件参数
         String typeCode = fileNode.get("type_code");
         String recordCode = fileNode.get("record_code");
         String recordName = fileNode.get("record_name");
         String recordGuid = fileNode.get("record_guid");
         String guid = fileNode.get("guid");
         String path = fileNode.get("path");
         String adjustWidthStr = fileNode.get("adjust_width");
         int adjustWidth = 48;
         if(RString.isNotBlank(adjustWidthStr)){
            adjustWidth = RInteger.parse(adjustWidthStr);
         }
         String adjustHeightStr = fileNode.get("adjust_height");
         int adjustHeight = 48;
         if(RString.isNotBlank(adjustHeightStr)){
            adjustHeight = RInteger.parse(adjustHeightStr);
         }
         String extendName = RFile.extension(path);
         // 获得存储位置
         String storePath = _storeConsole.temporaryPath(guid);
         String storePreviewPath = _storeConsole.temporaryPath(guid + ".preview");
         // 更新数据库信息 
         FAttributes params = new FAttributes();
         params.set("TYPE_CODE", typeCode);
         params.set("RECORD_CODE", recordCode);
         params.set("RECORD_GUID", recordGuid);
         params.set("RECORD_NAME", RString.nvl(recordName, path));
         params.set("GUID", guid);
         params.set("DATA_SIZE", Long.toString(new File(storePath).length()));
         params.set("MIME_TYPE", extendName);
         params.set("PATH", path);
         FSqlProcedure procedure = syAttachmentDi.doInsertFile(null, params, null);
         String resultPack = procedure.parameter("result_").asString();
         IAttributes results = new FAttributes();
         results.unpack(resultPack);
         outputFileNode.attributes().append(results);
         String attachmentGuid = results.get("GUID");
         // 获得目标文件名称
         String targetPath = _storeConsole.systemResourcePath(recordCode, recordGuid, attachmentGuid, extendName);
         String targetIconPath = _storeConsole.systemResourcePath(recordCode, recordGuid, attachmentGuid + ".icon", extendName);
         // 打开图片文件，保存为图标文件
         //ISizeableImage image = RPicture.openFile(storePath);
         //image.adjustSave(targetIconPath, adjustWidth, adjustHeight);
         // 保存原始文件
         RFile.copyFile(storePath, targetPath);
         if(_logger.debugAble()){
            _logger.debug(this, "previewSave", "Upload file succes. (path={0})", targetPath);
         }
         // 删除临时文件
         boolean isSuccess = RFile.delete(storePath);
         if(_logger.debugAble()){
            _logger.debug(this, "previewSave", "Delete temporary file. (result={0},path={1})", isSuccess, storePath);
         }
         isSuccess = RFile.delete(storePreviewPath);
         if(_logger.debugAble()){
            _logger.debug(this, "previewSave", "Delete temporary file. (result={0},path={1})", isSuccess, storePreviewPath);
         }
      }
   }
}
