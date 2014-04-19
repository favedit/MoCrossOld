package org.mt.com.lang;

import org.mo.com.lang.type.TDateTime;

public class RDateTest
{
   public static void main(String[] args){
      TDateTime date = new TDateTime("2011-12-01 06:00:12", "yyyy-mm-dd hh24:mi:ss");
      System.out.println(date.format("yyyy-mm-dd hh24:mi:ss"));
   }
}
