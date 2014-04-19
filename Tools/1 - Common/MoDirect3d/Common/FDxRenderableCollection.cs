using MO.DX.Core.Common.Geom;
using MO.Common.Lang;

namespace MO.DX.Core.Common
{
   //============================================================
   public class FDxRenderableCollection : FObjects<FDxRenderable>
   {
      //============================================================
      public FDxRenderable FindById(int renderableId) {
         for (int n = 0; n < _count; n++) {
            if (_items[n].Id == renderableId) {
               return _items[n];
            }
         }
         return null;
      }

      //============================================================
      public void SetSelected(bool selected) {
         for (int n = 0; n < _count; n++) {
            _items[n].IsSelected = selected;
         }
      }

      //============================================================
      public void SetSelected(bool forceUi, bool selected) {
         for(int n = 0; n < _count; n++) {
            if(_items[n].IsUi == forceUi) {
               _items[n].IsSelected = selected;
            }
         }
      }

      //============================================================
      public void FilterSelected(FDxRenderableCollection renderables, bool isUi) {
         renderables.Clear();
         for(int n = 0; n < _count; n++) {
            if(_items[n].IsSelected) {
               if(_items[n].IsUi == isUi) {
                  renderables.Push(_items[n]);
               }
            }
         }
      }

      //============================================================
      public void Store() {
         for (int n = 0; n < _count; n++) {
            _items[n].Store();
         }
      }

      //============================================================
      public void Restore() {
         for (int n = 0; n < _count; n++) {
            _items[n].Restore();
         }
      }

      //============================================================
      public void AppendTranslation(float x, float y, float z) {
         for (int n = 0; n < _count; n++) {
            _items[n].Matrix.AppendTranslate(x, y, z);
         }
      }

      //============================================================
      public void AppendRotation(float x, float y, float z) {
         for (int n = 0; n < _count; n++) {
            _items[n].Matrix.AppendRotation(x, y, z);
         }
      }

      //============================================================
      public void AppendScale(float x, float y, float z) {
         for (int n = 0; n < _count; n++) {
            _items[n].Matrix.AppendScale(x, y, z);
         }
      }

      //============================================================
      public void Update() {
         for(int n = 0; n < _count; n++) {
            _items[n].Update();
         }
      }
   }
}
