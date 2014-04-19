package org.mo.game.editor.face.apl.logic.transfer;

import java.sql.SQLException;
import org.mo.com.data.FSqlProcedure;
import org.mo.com.io.RFile;
import org.mo.com.lang.FAttributes;
import org.mo.com.lang.FFatalError;
import org.mo.com.lang.IAttributes;
import org.mo.com.lang.RString;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.core.aop.face.ALink;
import org.mo.logic.data.ISyAttachmentDi;
import org.mo.logic.store.IStoreConsole;
import org.mo.web.protocol.common.FWebUploadFile;
import org.mo.web.protocol.context.IWebContext;

public class FUploadAction
      implements
         IUploadAction
{

   private static ILogger _logger = RLogger.find(FUploadAction.class);

   public static final String PAGE_UPLOADED = "Uploaded";

   @ALink
   protected IStoreConsole _storeConsole;

   public String construct(IWebContext context,
                           ISyAttachmentDi syAttachmentDi,
                           FUploadPage page){
      page.setWorkerId(context.parameter("worker_id"));
      if(context.hasFile()){
         FWebUploadFile uploadFile = context.files().get(0);
         String fileName = uploadFile.fileName();
         // 获得文件参数
         String typeCode = context.parameter("type_code");
         String storeType = context.parameter("store_type");
         String storeCode = context.parameter("store_code");
         String storeName = context.parameter("store_name");
         String storeId = context.parameter("store_id");
         String extendName = RFile.extension(fileName);
         // 更新数据库信息 
         FAttributes params = new FAttributes();
         params.set("TYPE_CODE", typeCode);
         params.set("STORE_TYPE", storeType);
         params.set("STORE_CODE", storeCode);
         params.set("STORE_NAME", storeName);
         params.set("STORE_ID", storeId);
         params.set("DATA_SIZE", uploadFile.length() + "");
         params.set("MIME_TYPE", extendName);
         params.set("PATH", fileName);
         FSqlProcedure procedure = syAttachmentDi.doInsertFile(null, params, null);
         IAttributes results = procedure.parameter("result_").asAttributes();
         String attachmentId = results.get("ouid");
         String attachmentGuid = results.get("ogid");
         // 保存文件
         String path = _storeConsole.systemResourcePath(storeCode, storeId, attachmentGuid, extendName);
         if(_logger.debugAble()){
            _logger.debug(this, "construct", "Upload file to {0}", path);
         }
         uploadFile.move(path);
         // 设置输出
         page.setAttachment(attachmentGuid + "_" + attachmentId + "." + extendName);
         page.setAttachmentId(attachmentId);
         page.setAttachmentGuid(attachmentGuid);
         page.setAttachmentExtension(extendName);
      }
      return PAGE_UPLOADED;
   }

   @Override
   public String preview(IWebContext context,
                         FUploadPage page){
      page.setWorkerId(context.parameter("worker_id"));
      if(context.hasFile()){
         //         FWebUploadFile file = context.files().get(0);
         //         String fileName = file.uploadName();
         //         // 打开图片文件
         //         ISizeableImage image = RPicture.openFile(fileName);
         //         // 将图片变换为适合大小的预览图标
         //         String uuid = RUuid.simpleUuid();
         //         String previewPath = _storeConsole.temporaryPath(uuid + ".preview");
         //         // 生成预览图
         //         image.adjustSave(previewPath, 300, 300);
         //         // 保存文件
         //         String path = _storeConsole.temporaryPath(uuid);
         //         file.move(path);
         //         // 设置输出内容
         //         IAttributes info = new FAttributes();
         //         info.set("guid", uuid);
         //         info.set("path", file.fileName());
         //         info.set("width", Integer.toString(image.width()));
         //         info.set("height", Integer.toString(image.height()));
         //         page.setAttachment(uuid);
         //         page.setAttributes(info.pack().toString());
      }
      return PAGE_UPLOADED;
   }

   @Override
   public String upload(IWebContext context,
                        ISyAttachmentDi syAttachmentDi,
                        FUploadPage page){
      page.setWorkerId(context.parameter("worker_id"));
      if(context.hasFile()){
         try{
            FWebUploadFile uploadFile = context.files().get(0);
            String fileName = uploadFile.fileName();
            String extendName = RFile.extension(fileName);
            // 获得文件参数
            String typeCode = context.parameter("type");
            String recordCode = context.parameter("code");
            String recordName = context.parameter("name");
            String recordGuid = context.parameter("guid");
            // 更新数据库信息 
            FAttributes params = new FAttributes();
            params.set("TYPE_CODE", typeCode);
            params.set("RECORD_CODE", recordCode);
            params.set("RECORD_GUID", recordGuid);
            params.set("RECORD_NAME", RString.nvl(recordName, fileName));
            params.set("DATA_SIZE", Integer.toString(uploadFile.length()));
            params.set("MIME_TYPE", extendName);
            params.set("PATH", fileName);
            FSqlProcedure procedure = syAttachmentDi.doInsertFile(null, params, null);
            String resultPack = procedure.parameter("result_").asString();
            IAttributes results = new FAttributes();
            results.unpack(resultPack);
            String attachmentGuid = results.get("GUID");
            // 保存文件
            String path = _storeConsole.systemResourcePath(recordCode, recordGuid, attachmentGuid, extendName);
            if(_logger.debugAble()){
               _logger.debug(this, "upload", "Upload file succes. (path={0})", path);
            }
            uploadFile.move(path);
            // 设置输出
            page.setResultCode("S");
            page.setAttachment(RString.toLower(attachmentGuid + "." + extendName));
            page.setAttachmentGuid(attachmentGuid);
            page.setAttributes(resultPack);
         }catch(Exception e){
            String message = e.getMessage();
            if(e instanceof FFatalError){
               FFatalError fatalError = (FFatalError)e;
               Throwable throwable = fatalError.rootThrowable();
               if(throwable instanceof SQLException){
                  message = throwable.getMessage();
               }
            }
            page.setResultCode("E");
            message = RString.replace(message, "\"", "\\\"");
            message = RString.replace(message, "\n", "\\\n");
            page.setResult(message);
            _logger.error(this, "upload", e);
         }
      }
      return PAGE_UPLOADED;
   }
}
