namespace MO.Content3d.Common
{
   //============================================================
   // <T>编译器设置。</T>
   //============================================================
   public class RDrCompiler
   {
      // 顶点最大容量
      public static int VertexMaxCount = 65535;

      // 索引最大容量
      public static int IndexMaxCount = 174762;

      // 属性总数
      public static int AttributeMaxCount = 8;

      // 插值器总数
      public static int VaryingMaxCount = 8;

      // 取样器总数
      public static int SamplerMaxCount = 8;

      // 顶点常量总数
      public static int VertexConstMaxCount = 128;
      //public static int VertexConstMaxCount = 250;

      // 定点变量总数
      public static int VertexTemporaryMaxCount = 8;

      // 像素常量总数
      public static int FragmentConstMaxCount = 28;

      // 像素变量总数
      public static int FragmentTemporaryMaxCount = 8;
   }
}
