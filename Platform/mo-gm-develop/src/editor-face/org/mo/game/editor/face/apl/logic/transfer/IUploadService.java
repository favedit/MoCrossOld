package org.mo.game.editor.face.apl.logic.transfer;

import org.mo.logic.data.ISyAttachmentDi;
import org.mo.web.protocol.context.IWebContext;
import org.mo.web.protocol.context.IWebInput;
import org.mo.web.protocol.context.IWebOutput;

public interface IUploadService{

   void deleteFile(IWebContext context,
                   ISyAttachmentDi syAttachmentDi,
                   IWebInput input,
                   IWebOutput output);

   void previewSave(IWebContext context,
                    ISyAttachmentDi syAttachmentDi,
                    IWebInput input,
                    IWebOutput output);
}
