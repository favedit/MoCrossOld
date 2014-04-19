package org.mo.com.regex;

public class FPatternTest
{
   public static void main(String[] params){
      FPattern p = new FPattern("/*/*");
      System.out.println(p.replace("/1/2/3", "org.mo.jfa.face.*.I*Action"));
      //System.out.println(p.matches("InASDdexActionSDF"));
   }
}
