package org.mo.web.tag.page;

import org.mo.web.protocol.common.FWebEncoding;
import org.mo.web.tag.common.FAbstractTag;

/**
 * <T>下拉列表标签</T>
 * <P>当格式化方式为指定时,格式化为HTML代码</P>
 * 
 * @author MAOCY
 * @version 1.00 - 2008/08/05 CREATE BY MAOCY
 * @version 1.01 - 2009/01/05 UPDATE BY YANGH
 */
public class FMetaTag
      extends FAbstractTag
{

   @Override
   public int onEnd(){
      // TODO Auto-generated method stub
      return 0;
   }

   @Override
   public int onStart(){
      //String sLanguage = oContext.session().environment().isoCountry()
      //.language();
      String sLanguage = "ZH";
      _writer.append("<META");
      _writer.append(" http-equiv='content-type'");
      _writer.append(" content='text/html; charset='");
      _writer.append(FWebEncoding.languageEncoding(sLanguage));
      _writer.append("'>");
      _writer.flush();
      return SKIP_BODY;
   }
}
