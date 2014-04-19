package org.mo.util.rpc;

import java.security.MessageDigest;
import java.util.Vector;
import org.apache.xmlrpc.XmlRpcClient;
import org.mo.com.lang.FString;
import org.mo.com.lang.RByte;

public class RPassportTest{

   /**
    * @param args
    */
   public static void main(String[] args){

      try{
         //XmlRpcClient client = new XmlRpcClient(url, 80);
         //XmlRpcClient client = new XmlRpcClient("http://passport.linekong.com/ePassport-Mid/xmlRpcServerServlet");

         //XmlRpcClient client = new XmlRpcClient("http://passport.linekong.com/ePassport-Mid/xmlRpcServerServlet");
         //XmlRpcClient client = new XmlRpcClient("http://passport.linekong.com/epassport-mid/xmlRpcServerServlet");
         //XmlRpcClient client = new XmlRpcClient("http://59.151.39.161/epassport_mid/xmlRpcServerServlet");

         MessageDigest md5 = MessageDigest.getInstance("MD5");
         //BASE64Encoder base64en = new BASE64Encoder();
         //加密后的字符串
         //String newstr = base64en.encode(md5.digest("iamalexer".getBytes("utf-8")));
         byte[] bytes = md5.digest("zhangfan fanzhang".getBytes());
         FString buffer = new FString();
         for(byte value : bytes){
            buffer.append(RByte.toHexChars(value));
         }

         //byte[] bytes2 = md5.digest("zhangfan linekongline".getBytes());
         //byte[] bytes2 = md5.digest("zhangfanlinekongline".getBytes());
         byte[] bytes2 = md5.digest("zhangfanlinekongline".getBytes());
         FString buffer2 = new FString();
         for(byte value : bytes2){
            buffer2.append(RByte.toHexChars(value));
         }

         //String newstr = new String(md5.digest("iamalexer".getBytes()));
         System.out.println(buffer);
         System.out.println(buffer2);

         XmlRpcClient client = new XmlRpcClient("http://192.168.41.229:8088/epassport_mid/xmlRpcServerServlet");

         Vector<String> params = new Vector<String>();
         params.addElement("zhangfan");
         params.addElement(buffer.toString().toLowerCase());
         params.addElement("192.168.41.34");
         params.addElement(buffer2.toString().toLowerCase());

         Object result = client.execute("ePassportMid.login", params);
         System.out.println(result);

      }catch(Exception e){
         e.printStackTrace();
      }

   }
}
