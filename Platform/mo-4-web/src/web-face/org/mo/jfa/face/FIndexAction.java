package org.mo.jfa.face;

public class FIndexAction
      implements
         IIndexAction
{

   public String construct(FIndexPage page){
      page.setUserid("Hello");
      page.setPassword("MAS");
      System.out.println("------- Hello: " + page);
      return null;
   }

   public String delete(FIndexPage page){
      page.setUserid("Delete UsrId");
      page.setPassword("Delete Pass");
      System.out.println("------- Delete: " + page);
      return null;
   }

   public String insert(FIndexPage page){
      //page.setUserid("Insert UsrId");
      //page.setPassword("Insert Pass");
      System.out.println("------- Insert: " + page);
      return null;
   }

   public String update(FIndexPage page){
      page.setUserid("Update UsrId");
      page.setPassword("Update Pass");
      System.out.println("------- Update: " + page);
      return null;
   }

}
