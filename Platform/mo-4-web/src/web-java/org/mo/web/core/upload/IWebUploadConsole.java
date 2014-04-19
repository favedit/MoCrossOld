package org.mo.web.core.upload;

import javax.servlet.http.HttpServletRequest;
import org.mo.com.lang.IAttributes;
import org.mo.web.protocol.common.FWebUploadFiles;

public interface IWebUploadConsole
{

   void parse(HttpServletRequest request,
              IAttributes parameters,
              FWebUploadFiles files);

}
