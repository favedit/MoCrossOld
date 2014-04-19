using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using MO.Common.Lang;
using MO.DX.Core.Common;
using MO.DX.Core.Device;
using MO.DX.Core.Common.Geom;
using SlimDX;

namespace MO.DX.Core.Program.Effect
{
   //============================================================
   public class FDxEffect : FDxProgram
   {
      protected FDxProgramBinder _binder;

      //============================================================
      public FDxEffect() {
      }

      //============================================================
      public virtual void Setup() {
      }
      
      //============================================================
      public void Build(FDxProgramHelper helper) {
      }

      //============================================================
      public void Draw(FDxRenderable renderable) {
      }

      //============================================================
      public void Draws(FObjects<FDxRenderable> renderables, int offset, int count) {
      }

      //============================================================
      public void SetNativeMatrix(string name, Matrix matrix) {
         _nativeEffect.GetVariableByName(name).AsMatrix().SetMatrix(matrix);
      }

      //============================================================
      public void SetMatrix(string name, SDxMatrix matrix) {
         matrix.UpdateNative();
         _nativeEffect.GetVariableByName(name).AsMatrix().SetMatrix(matrix.Native);
      }
   }
}
