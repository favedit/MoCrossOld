using MO.Core.Forms.Device;
using MO.DX.Core;
using MO.DX.Core.Common;
using MO.DX.Core.Model;
using MO.DX.Core.Program.Technique.Function;
using MO.DX.Core.Stage.Scene;
using MO.Design3d.Common;
using MO.Design3d.Core.Model;

namespace MO.Design3d.Core.Scene
{
   //============================================================
   public class FDxDesignScene : FDxScene
   {
      protected EDxDesignMode _modeCd = EDxDesignMode.Select;

      protected EDxDesignCommand _commandCd = EDxDesignCommand.CameraRotation;

      protected FDxDesignTechnique _techniqueDesign;

      protected FDxDesignGeometry _focusGeometryUi;

      protected FDxDesignGeometry _focusGeometry;

      protected FDxSpatial _spatialTranslate;

      protected FDxSpatial _spatialRotation;

      protected FDxSpatial _spatialScale;

      public FDxRenderableCollection _renderablesSelected = new FDxRenderableCollection();

      //============================================================
      public FDxDesignScene() {
      }

      //============================================================
      public EDxDesignMode ModeCd {
         get { return _modeCd; }
         set {
            // 隐藏所有UI对象
            _spatialTranslate.SetVisible(false);
            _spatialRotation.SetVisible(false);
            _spatialScale.SetVisible(false);
            // 根据类型处理
            switch(value) {
               case EDxDesignMode.Select:
                  _commandCd = EDxDesignCommand.CameraRotation;
                  break;
               case EDxDesignMode.Translation:
                  _spatialTranslate.SetVisible(true);
                  break;
               case EDxDesignMode.Rotation:
                  _spatialRotation.SetVisible(true);
                  break;
               case EDxDesignMode.Scale:
                  _spatialScale.SetVisible(true);
                  break;
            }
            _modeCd = value;
         }
      }

      //============================================================
      public void Setup() {
         _technique = RDxCore.TechniqueConsole.Get(_device, "design");
         _techniqueDesign = _technique as FDxDesignTechnique;
         // 获得基础对象
         _spatialTranslate = RDxCore.SpatialConsole.Create(_device, "com.design.translation");
         _spatialTranslate.SetUi(true);
         _spatialTranslate.SetVisible(false);
         _spatialRotation = RDxCore.SpatialConsole.Create(_device, "com.design.rotation");
         _spatialRotation.SetUi(true);
         _spatialRotation.SetVisible(false);
         _spatialScale = RDxCore.SpatialConsole.Create(_device, "com.design.scale");
         _spatialScale.SetUi(true);
         _spatialScale.SetVisible(false);
      }

      //============================================================
      public void Reset() {
         Clear();
         Push(_spatialTranslate);
         Push(_spatialRotation);
         Push(_spatialScale);
      }

      //============================================================
      public FDxDesignGeometry FocusGeometry {
         get { return _focusGeometry; }
         set { _focusGeometry = value; }
      }

      //============================================================
      public EDxDesignCommand MakeCommandByName(string name) {
         switch(name) {
            case "ms_translation_x":
               return EDxDesignCommand.MatrixTranslationX;
            case "ms_translation_y":
               return EDxDesignCommand.MatrixTranslationY;
            case "ms_translation_z":
               return EDxDesignCommand.MatrixTranslationZ;
            case "ms_rotation_x":
               return EDxDesignCommand.MatrixRotationX;
            case "ms_rotation_y":
               return EDxDesignCommand.MatrixRotationY;
            case "ms_rotation_z":
               return EDxDesignCommand.MatrixRotationZ;
            case "ms_scale_all":
               return EDxDesignCommand.MatrixScaleAll;
            case "ms_scale_x":
               return EDxDesignCommand.MatrixScaleX;
            case "ms_scale_y":
               return EDxDesignCommand.MatrixScaleY;
            case "ms_scale_z":
               return EDxDesignCommand.MatrixScaleZ;
         }
         return EDxDesignCommand.None;
      }

      //============================================================
      public void ProcessKeyDown(int keyCode) {
         // 移动
         if(RKeybord.IsPressedKey(EKeyCode.D1)) {
            _commandCd = EDxDesignCommand.MatrixTranslationX;
         } else if(RKeybord.IsPressedKey(EKeyCode.D2)) {
            _commandCd = EDxDesignCommand.MatrixTranslationY;
         } else if(RKeybord.IsPressedKey(EKeyCode.D3)) {
            _commandCd = EDxDesignCommand.MatrixTranslationZ;
         }
         // 旋转
         if(RKeybord.IsPressedKey(EKeyCode.D4)) {
            _commandCd = EDxDesignCommand.MatrixRotationX;
         } else if(RKeybord.IsPressedKey(EKeyCode.D5)) {
            _commandCd = EDxDesignCommand.MatrixRotationY;
         } else if(RKeybord.IsPressedKey(EKeyCode.D6)) {
            _commandCd = EDxDesignCommand.MatrixRotationZ;
         }
         // 缩放
         if(RKeybord.IsPressedKey(EKeyCode.D7)) {
            _commandCd = EDxDesignCommand.MatrixScaleX;
         } else if(RKeybord.IsPressedKey(EKeyCode.D8)) {
            _commandCd = EDxDesignCommand.MatrixScaleY;
         } else if(RKeybord.IsPressedKey(EKeyCode.D9)) {
            _commandCd = EDxDesignCommand.MatrixScaleZ;
         }
      }

      //============================================================
      public void DoDesignMode(EDxDesignMode modeCd) {
         _spatialTranslate.SetVisible(false);
         _spatialRotation.SetVisible(false);
         _spatialScale.SetVisible(false);
         switch(_modeCd) {
            case EDxDesignMode.Select:
               break;
            case EDxDesignMode.Translation:
               _spatialTranslate.SetVisible(true);
               break;
            case EDxDesignMode.Rotation:
               _spatialRotation.SetVisible(true);
               break;
            case EDxDesignMode.Scale:
               _spatialScale.SetVisible(true);
               break;
         }
      }
      
      //============================================================
      public void DoGeometryFocus(FDxDesignGeometry geometry) {
         if(null != geometry) {
            bool selected = geometry.IsSelected;
            if(geometry.IsUi) {
               // 选中UI时
               _region.Renderables.SetSelected(true, false);
               geometry.IsSelected = true;
               _commandCd = MakeCommandByName(geometry.MeshName);
            } else {
               // 选中渲染对象时
               if(!RKeybord.IsPressedKey(EKeyCode.ShiftKey)) {
                  _region.Renderables.SetSelected(false);
               }
               geometry.IsSelected = true;
               // 设置设计模式
               DoDesignMode(_modeCd);
               // 设置位置
               _spatialTranslate.Matrix.Assign(geometry.Matrix);
               _spatialTranslate.Update();
            }
         } else {
            // 未选中任何对象
            _region.Renderables.SetSelected(false);
            _spatialTranslate.SetVisible(false);
            _spatialRotation.SetVisible(false);
            _spatialScale.SetVisible(false);
         }
         // 设置焦点对象
         if(null != geometry) {
            if(geometry.IsUi) {
               _focusGeometryUi = geometry;
            } else {
               _focusGeometry = geometry;
            }
         } else {
            _focusGeometryUi = geometry;
            _focusGeometry = geometry;
         }
      }

      //============================================================
      public void DoGeometryBlur(FDxDesignGeometry geometry) {
         _focusGeometry = null;
      }

      //============================================================
      public void ProcessKeyUp(int keyCode) {
      }

      //============================================================
      // <T>处理鼠标落下。</T>
      //============================================================
      public void ProcessMouseDown(int mouseButton, int x, int y)
      {
         RMouse.ProcessDown(mouseButton);
         RMouse.LocationDown.Set(x, y);
         // 左键按下
         if(mouseButton == EMouseButton.Left) {
            // 存储相机
            _region.Camera.Store();
            // 点击测试
            if(RKeybord.IsPressedKey(EKeyCode.D1) || RKeybord.IsPressedKey(EKeyCode.D2) || RKeybord.IsPressedKey(EKeyCode.D3) ||
               RKeybord.IsPressedKey(EKeyCode.D4) || RKeybord.IsPressedKey(EKeyCode.D5) || RKeybord.IsPressedKey(EKeyCode.D6) ||
               RKeybord.IsPressedKey(EKeyCode.D7) || RKeybord.IsPressedKey(EKeyCode.D8) || RKeybord.IsPressedKey(EKeyCode.D9)) {
            } else {
               int renderableId = _techniqueDesign.HitTest(_region, x, y);
               FDxDesignGeometry geometry = null;
               if(renderableId > 0) {
                  geometry = _region.Renderables.FindById(renderableId) as FDxDesignGeometry;
               }
               DoGeometryFocus(geometry);
            }
            // 过滤所有选择对象
            _region.Renderables.FilterSelected(_renderablesSelected, false);
            _renderablesSelected.Store();
         }
      }

      //============================================================
      public void ProcessMouseMove(int mouseButton, int x, int y) {
         if(RMouse.IsPressed()) {
            int cx = x - RMouse.LocationDown.X;
            int cy = y - RMouse.LocationDown.Y;
            // 鼠标左键点击
            if(RMouse.IsPressedKey(EMouseButton.Left)) {
               // 相机旋转
               if (_commandCd == EDxDesignCommand.CameraRotation) {
                  _region.Camera.Restore();
                  _region.Camera.DoYaw(-cx * 0.005f);
                  _region.Camera.DoPitch(-cy * 0.005f);
                  _region.Camera.Update();
               }
               // 检查对象有选中的时候，进行操作
               if (!_renderablesSelected.IsEmpty()) {
                  float delta = cx + cy;
                  // 移动
                  if(_commandCd == EDxDesignCommand.MatrixTranslationX) {
                     _renderablesSelected.Restore();
                     _renderablesSelected.AppendTranslation(delta, 0, 0);
                  } else if(_commandCd == EDxDesignCommand.MatrixTranslationY) {
                     _renderablesSelected.Restore();
                     _renderablesSelected.AppendTranslation(0, -delta, 0);
                  } else if(_commandCd == EDxDesignCommand.MatrixTranslationZ) {
                     _renderablesSelected.Restore();
                     _renderablesSelected.AppendTranslation(0, 0, delta);
                  }
                  // 旋转
                  if(_commandCd == EDxDesignCommand.MatrixRotationX) {
                     _renderablesSelected.Restore();
                     _renderablesSelected.AppendRotation(delta * 0.05f, 0, 0);
                  } else if(_commandCd == EDxDesignCommand.MatrixRotationY) {
                     _renderablesSelected.Restore();
                     _renderablesSelected.AppendRotation(0, -delta * 0.05f, 0);
                  } else if(_commandCd == EDxDesignCommand.MatrixRotationZ) {
                     _renderablesSelected.Restore();
                     _renderablesSelected.AppendRotation(0, 0, delta * 0.05f);
                  }
                  // 缩放
                  float scaleRate = 1.0f + delta * 0.001f;
                  if(_commandCd == EDxDesignCommand.MatrixScaleAll) {
                     _renderablesSelected.Restore();
                     _renderablesSelected.AppendScale(scaleRate, scaleRate, scaleRate);
                  } else if(_commandCd == EDxDesignCommand.MatrixScaleX) {
                     _renderablesSelected.Restore();
                     _renderablesSelected.AppendScale(scaleRate, 1, 1);
                  } else if(_commandCd == EDxDesignCommand.MatrixScaleY) {
                     _renderablesSelected.Restore();
                     _renderablesSelected.AppendScale(1, scaleRate, 1);
                  } else if(_commandCd == EDxDesignCommand.MatrixScaleZ) {
                     _renderablesSelected.Restore();
                     _renderablesSelected.AppendScale(1, 1, scaleRate);
                  }
                  // 更新
                  _renderablesSelected.Update();
                  _spatialTranslate.Matrix.Assign(_focusGeometry.Matrix);
                  _spatialTranslate.Update();
                  _spatialRotation.Matrix.Assign(_focusGeometry.Matrix);
                  _spatialRotation.Update();
                  _spatialScale.Matrix.Assign(_focusGeometry.Matrix);
                  _spatialScale.Update();
               }
            }
         }
      }

      //============================================================
      public void ProcessMouseUp(int mouseButton, int x, int y) {
         RMouse.ProcessUp(mouseButton);
         _region.Renderables.SetSelected(true, false);
      }
   }
}
