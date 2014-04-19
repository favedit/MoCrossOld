package org.mo.web.tag.control;

import org.mo.web.tag.common.FAbstractTag;

public class FHistoryTag
      extends FAbstractTag
{

   @Override
   public int onEnd(){
      // TODO Auto-generated method stub
      return 0;
   }

   @Override
   public int onStart(){
      // TODO Auto-generated method stub
      return 0;
   }
   //
   //   // 内容项目
   //   private String m_sAction = null;
   //
   //   // 值项目
   //   private String m_sParams = null;
   //
   //   // 值项目
   //   private String m_sLevel = null;
   //
   //   /**
   //    * <p>开始标签的逻辑</p>
   //    * <p>create date:2005/02/18</p>
   //    *
   //    * @param oContext 环境对象
   //    * @return 逻辑执行后的状态
   //    * @exception FError 逻辑例外
   //    */
   //   public int doStartTag(FWebContext oContext)
   //         throws FError{
   //      String sHistory = null;
   //      m_sAction = RString.nvl(m_sAction).toLowerCase();
   //      int nLevel = RInteger.parse(RString.nvl(m_sLevel, "1"));
   //      if(m_sAction.equals("prior")){
   //         sHistory = oContext.history().prior(nLevel);
   //      }
   //      // 获得参数
   //      if(!RString.isEmpty(m_sParams)){
   //         String[] arHistory = RString.splitIndex(sHistory, '?');
   //         if(arHistory.length == 1){
   //            sHistory = sHistory + "?" + m_sParams;
   //         }else if(arHistory.length == 2){
   //            boolean bFirst = true;
   //            boolean bFind = false;
   //            String[] arHistoryParams = RString.split(arHistory[1], "&");
   //            String[] arParams = RString.split(m_sParams, "&");
   //            String[] arParam = null;
   //            String[] arHistoryParam = null;
   //            // Build history
   //            FString sResult = new FString();
   //            sResult.append(arHistory[0]);
   //            sResult.append("?");
   //            for(String sHistoryParam : arHistoryParams){
   //               bFind = false;
   //               arHistoryParam = RString.splitIndex(sHistoryParam, '=');
   //               for(String sParam : arParams){
   //                  arParam = RString.splitIndex(sParam, '=');
   //                  if(arHistoryParam[0].equalsIgnoreCase(arParam[0])){
   //                     bFind = true;
   //                     break;
   //                  }
   //               }
   //               if(!bFind){
   //                  if(bFirst){
   //                     bFirst = false;
   //                  }else{
   //                     sResult.append("&");
   //                  }
   //                  sResult.append(sHistoryParam);
   //               }
   //            }
   //            for(String sParam : arParams){
   //               if(bFirst){
   //                  bFirst = false;
   //               }else{
   //                  sResult.append("&");
   //               }
   //               sResult.append(sParam);
   //            }
   //            sHistory = sResult.toString();
   //         }
   //      }
   //      // 生成内容
   //      FString sBuffer = oContext.responseBuffer();
   //      sBuffer.append(sHistory);
   //      flush();
   //      return SKIP_BODY;
   //   }
   //
   //   /**
   //    * <p>设置内容项目</p>
   //    * <p>create date:2005/02/18</p>
   //    *
   //    * @param sValue 内容项目
   //    */
   //   public void setAction(String sAction){
   //      m_sAction = sAction;
   //   }
   //
   //   /**
   //    * <p>设置内容项目</p>
   //    * <p>create date:2005/02/18</p>
   //    *
   //    * @param sValue 内容项目
   //    */
   //   public void setParams(String sParams){
   //      m_sParams = sParams;
   //   }
   //
   //   public void setLevel(String sLevel){
   //      m_sLevel = sLevel;
   //   }
}
