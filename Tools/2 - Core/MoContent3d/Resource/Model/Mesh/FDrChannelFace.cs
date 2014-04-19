using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using MO.Common.Geom;
using MO.Common.IO;

namespace MO.Content3d.Resource.Model.Mesh
{
   public class FDrChannelFace
   {
      public int[] Indexs = new int[3];

      public SFloatPoint3[] Points = new SFloatPoint3[3];
   
      //============================================================
      public void DataUnserialize(IInput input) {
         // 读取顶点坐标
         for (int n = 0; n < 3; n++) {
            Indexs[n] = input.ReadInt32();
            Points[n] = new SFloatPoint3();
            Points[n].Unserialize(input);
         }
      }
   }
}
