using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace MO.Design3d.Common
{
   public enum EDxDesignCommand
   {
      None,
      // 相机旋转
      CameraRotation,
      // 焦点对象平移
      MatrixTranslationX,
      MatrixTranslationY,
      MatrixTranslationZ,
      // 焦点对象旋转
      MatrixRotationX,
      MatrixRotationY,
      MatrixRotationZ,
      // 焦点对象缩放
      MatrixScaleAll,
      MatrixScaleX,
      MatrixScaleY,
      MatrixScaleZ,
   }
}
