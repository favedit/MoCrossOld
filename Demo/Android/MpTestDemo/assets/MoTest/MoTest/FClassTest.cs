using System;
using System.Runtime.CompilerServices;
using System.Runtime.InteropServices;
using System.Text;

namespace MoTest
{
   public class FClassTest
   {
      public int _testValue = 11;

      public string _testString = "hello2";

      //[DllImport("__Internal", EntryPoint = "CalculateNumber")]
      [MethodImplAttribute(MethodImplOptions.InternalCall)]
      public static extern int CalculateNumber(object owner, int value);

      public int Test(){
         return CalculateNumber(this, 11);
      }
   }
}
