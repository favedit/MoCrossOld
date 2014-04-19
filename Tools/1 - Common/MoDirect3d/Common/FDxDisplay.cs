using MO.DX.Core.Common.Geom;

namespace MO.DX.Core.Common
{
   //============================================================
   public class FDxDisplay : FDxContextObject
   {
      protected SDxMatrix _matrix = new SDxMatrix();

      protected SDxMatrix _modelMatrix = new SDxMatrix();

      protected FDxRenderableCollection _renderables = new FDxRenderableCollection();

      //============================================================
      public FDxDisplay() {
      }

      //============================================================
      public SDxMatrix Matrix {
         get { return _matrix; }
      }

      //============================================================
      public SDxMatrix ModelMatrix {
         get { return _modelMatrix; }
      }

      //============================================================
      public FDxRenderableCollection Renderables{
         get { return _renderables; }
      }

      //============================================================
      public void FilterRenderables(FDxRenderableCollection renderables) {
         foreach(FDxRenderable renderable in _renderables) {
            if(renderable.IsVisible) {
               renderables.Append(_renderables);
            }
         }
      }

      //============================================================
      public void SetUi(bool ui) {
         foreach(FDxRenderable renderable in _renderables) {
            renderable.IsUi = true;
         }
      }

      //============================================================
      public void SetVisible(bool visible) {
         foreach(FDxRenderable renderable in _renderables) {
            renderable.IsVisible = visible;
         }
      }

      //============================================================
      public void Update() {
         _modelMatrix.Assign(_matrix);
         foreach(FDxRenderable renderable in _renderables) {
            renderable.Update();
         }
      }
   }
}
