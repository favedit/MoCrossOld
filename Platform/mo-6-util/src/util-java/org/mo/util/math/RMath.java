package org.mo.util.math;

public class RMath{

//   // 利用中心极限定理生成符合正态分布的随机量
//   public double Norm_rand(double miu,
//                           double sigma2){
//      double N = 12;
//      double x = 0, temp = N;
//      do{
//         x = 0;
//         for(int i = 0; i < N; i++){
//            x = x + (Math.random());
//         }
//         x = (x - temp / 2) / (Math.sqrt(temp / 12));
//         x = miu + x * Math.sqrt(sigma2);
//      }while(x <= 0); // 把小于0的数排除掉了
//      return x;
//   }
//
//   // 泊松分布随机数
//   public double P_rand(double Lamda){
//      double x = 0, b = 1, c = Math.exp(-Lamda), u;
//      do{
//         u = Math.random();
//         b *= u;
//         if(b >= c){
//            x++;
//         }
//      }while(b >= c);
//      return x;
//   }
}
