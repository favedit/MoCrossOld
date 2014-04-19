package org.mu.com.lang.system;

import org.mo.com.system.FProcess;

public class RProcessTest{

   public static void main(String[] args){
      FProcess process = new FProcess();
      process.setCommand("ps");
      process.setOptionWait(true);
      process.start();
      System.out.println(process.fetchResult());
   }
}
