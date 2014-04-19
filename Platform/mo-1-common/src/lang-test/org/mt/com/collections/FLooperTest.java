package org.mt.com.collections;

import org.mo.com.lang.FLooper;
import org.mo.com.lang.generic.MLooper;

public class FLooperTest
{
   @SuppressWarnings({"unchecked", "rawtypes"})
   public static void main(String[] args){
      MLooper looper = new FLooper();
      for(int i = 0; i < 10; i++){
         looper.push(i);
      }
      looper.pushUnique("t");
      //		MLooper looper2 = new FLooper();
      //		for(int i = 0; i < 10; i++){
      //			looper2.push(i + "********");
      //		}
      System.out.println("erase() ========   >>>   " + looper.erase("t"));
      //		looper.remove();
      //		looper.clear();
      System.out.println("contains() ========   >>>   " + looper.contains(5));
      System.out.println("isEmpty() ========   >>>   " + looper.isEmpty());
      System.out.println("current() ========   >>>   " + looper.current());
      int length = looper.count;
      for(int i = 0; i < length; i++){
         System.out.println("next() ========   >>>   " + looper.next());
      }
      System.out.println(looper.count);
      //		record
      //		unrecord
   }
}
