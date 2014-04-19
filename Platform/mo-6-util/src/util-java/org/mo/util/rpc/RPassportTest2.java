package org.mo.util.rpc;

import org.mo.core.aop.RAop;

public class RPassportTest2{

   public static void main(String[] args){

      try{
         if(args.length == 1){
            RAop.configConsole().loadFile(args[0]);
         }else{
            RAop.configConsole().loadFile("D:/Workspace/eUIS/webroot/WEB-INF/classes/application-develop.xml");
         }
         //         IPassportConsole pc = RAop.find(IPassportConsole.class);
         //         TPassportArgs arg = new TPassportArgs();
         //         arg.setPassport("em_yang0");
         //         arg.setPassword("em_yang0");
         //         pc.login(arg);
         RAop.release();
      }catch(Exception e){
         e.printStackTrace();
      }

   }
}
