package org.mo.util.data;

import org.mo.com.lang.RDateTime;
import org.mo.com.lang.type.TDateTime;

public class RDateTest
{

   /**
    * @param args
    */
   public static void main(String[] args){
      TDateTime dt = RDateTime.currentDateTime();
      System.out.println(RDateTime.format(dt.get(), "YYYY-MM-DD HH24:MI:SS.MS"));
      //System.out.println(t.hour() + ":" + t.minute() + ":" + t.second() + "." + t.milliSecond());
      //RDate.
   }

}
