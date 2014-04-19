package org.mo.com.net.http;

import java.io.UnsupportedEncodingException;

import org.mo.com.lang.FFatalError;
import org.mo.com.lang.FObject;
import org.mo.com.lang.RString;
import org.mo.com.xml.FXmlDocument;
import org.mo.com.xml.FXmlNode;

//============================================================
// <T>网页服务。</T>
//============================================================
public class FHttpService
      extends FObject
{
   // 输入文档
   protected FXmlDocument _inputDocument = new FXmlDocument();

   // 输出文档
   protected FXmlDocument _outputDocument = new FXmlDocument();

   //============================================================
   // <T>网页服务。</T>
   //============================================================
   public FHttpService(){
   }

   //============================================================
   // <T>获得输入文档。</T>
   //
   // @return 输入文档
   //============================================================
   public FXmlDocument inputDocument(){
      return _inputDocument;
   }

   //============================================================
   // <T>获得输入节点。</T>
   //
   // @return 输入节点
   //============================================================
   public FXmlNode inputNode(){
      return _inputDocument.root();
   }

   //============================================================
   // <T>获得输出文档。</T>
   //
   // @return 输出文档
   //============================================================
   public FXmlDocument outputDocument(){
      return _outputDocument;
   }

   //============================================================
   // <T>获得输出节点。</T>
   //
   // @return 输出节点
   //============================================================
   public FXmlNode outputNode(){
      return _outputDocument.root();
   }

   //============================================================
   // <T>发送处理。</T>
   //
   // @param url 网络地址
   // @param action 命令
   // @return 输出节点
   //============================================================
   public boolean load(String url,
                       String action){
      // 获得输入内容
      if(!RString.isEmpty(action)){
         inputNode().set("action", action);
      }
      //-E "mogametemplate|mogameproxy"
      String requestValue = inputNode().xml().toString();
      // 获取数据
      FHttpConnection http = new FHttpConnection(url);
      try {
		   http.connect();
	   } catch (Exception ex) {
		   //ec.printStackTrace();
		   return false;
		  
	   }
     
      http.request().setText(requestValue);//将命令发给服务器
      http.fetch();//发送数据流，接收数据
      http.disconnect();//断开连接
      try{
         byte[] responseData = http.response().data();
         String responseValue = new String(responseData, "UTF-8");
         outputDocument().loadString(responseValue);
      }catch(UnsupportedEncodingException e){
         throw new FFatalError(e);
      }
      return true;
   }

   //============================================================
   // <T>发送处理。</T>
   //
   // @param url 网络地址
   // @return 输出节点
   //============================================================
   public boolean load(String url){
      return load(url, null);
   }
}
