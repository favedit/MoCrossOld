using MO.Common.Geom;
using MO.Common.Lang;

namespace MO.Content3d.Resource.Scene
{
   //============================================================
   // <T>场景可绘制对象。</T>
   //============================================================
   public class FDrSceneDrawable : FObject
   {
      // 父对象
      protected FDrSceneDrawable _parent;

      // 矩阵
      protected SFloatMatrix _matrix = new SFloatMatrix();

      // 模型矩阵
      protected SFloatMatrix _modelMatrix = new SFloatMatrix();

      //============================================================
      // <T>构造场景节点。</T>
      //============================================================
      public FDrSceneDrawable() {
      }

      //============================================================
      // <T>获得或设置父对象。</T>
      //============================================================
      public FDrSceneDrawable Parent {
         get { return _parent; }
         set { _parent = value; }
      }
      
      //============================================================
      // <T>获得矩阵。</T>
      //============================================================
      public SFloatMatrix Matrix {
         get { return _matrix; }
      }

      //============================================================
      // <T>获得模型矩阵。</T>
      //============================================================
      public SFloatMatrix ModelMatrix {
         get { return _modelMatrix; }
      }
   
      //============================================================
      // <T>更新矩阵。</T>
      //============================================================
      public void UpdateMatrix() {
         // 追加自己信息
         _matrix.Assign(_modelMatrix);
         // 追加父信息
         FDrSceneDrawable drawable = _parent;
         while(null != drawable){
            _matrix.Append(drawable.ModelMatrix);
            drawable = drawable.Parent;
         }
      }
   }
}
