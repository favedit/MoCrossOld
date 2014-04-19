using MO.DX.Core.Common;
using MO.DX.Core.Program.Technique;

namespace MO.DX.Core.Stage.Scene
{
   //============================================================
   public class FDxScene : FDxContextObject
   {
      protected FDxTechnique _technique;

      protected FDxDisplayCollection _displays = new FDxDisplayCollection();

      protected FDxRenderableCollection _renderables = new FDxRenderableCollection();

      protected FDxRegion _region = new FDxRegion();

      //============================================================
      public FDxScene() {
      }

      //============================================================
      public FDxTechnique Technique {
         get { return _technique; }
         set { _technique = value; }
      }

      //============================================================
      public FDxRegion Region {
         get { return _region; }
      }

      //============================================================
      public FDxDisplayCollection Displays{
         get { return _displays; }
      }

      //============================================================
      public void Push(FDxDisplay display) {
         _displays.Push(display);
      }

      //============================================================
      public void Clear() {
         _displays.Clear();
      }

      //============================================================
      public void FilterRenderables(FDxRenderableCollection renderables) {
         renderables.Clear();
         foreach (FDxDisplay display in _displays) {
            display.FilterRenderables(renderables);
         }
      }

      //============================================================
      // <T>切换帧处理。</T>
      //============================================================
      public void ProcessInput(){
         // 检查按键
         //if(RKeybord.IsPressed()){
         //   //var entity:FGmPlayerEntity = player.entity;
         //   //var camera:FGeCamera = geScene.camera;
         //   //var input:FGeInputConsole = RGeCore.inputConsole;
         //   //var sensitivity:Number = RGlobal.inputSensitivity;
         //   //var sensitivityTranslation:Number = input.sensitivityTranslation * geScene.region.sensitivityTranslation;
         //   //var sensitivityRotation:Number = input.sensitivityRotation * geScene.region.sensitivityRotation;
         //   //var sensitivityScale:Number = input.sensitivityScale * geScene.region.sensitivityScale;
         //   //camera.smoothTranslation = geScene.region.smoothRotation;
         //   //camera.smoothRotation = geScene.region.smoothRotation;
         //   // 设置相机
         //   //camera.target.set(entity.location.x, entity.location.z, entity.location.y);
         //   float step = 20f;
         //   float stepRotation = 0.06f;
         //   FDxCamera camera = _region.Camera;
         //   // 控制摄像机 (前后移动)
         //   if(RKeybord.IsPressedKey(EKeyCode.W)){
         //      //entity.walk(camera, step);
         //      camera.DoWalk(step);
         //      //focus.node.selectMovie(EGeMovie.Walk);
         //   }
         //   if(RKeybord.IsPressedKey(EKeyCode.S)) {
         //      //entity.walk(camera, -step);
         //      camera.DoWalk(-step);
         //      //focus.node.selectMovie(EGeMovie.Walk);
         //   }
         //   // 控制摄像机 (左右旋转)
         //   if(RKeybord.IsPressedKey(EKeyCode.A)) {
         //      camera.DoYaw(-stepRotation);
         //   }
         //   if(RKeybord.IsPressedKey(EKeyCode.D)) {
         //      camera.DoYaw(stepRotation);
         //   }
         //   // 控制摄像机 (上下移动)
         //   if(RKeybord.IsPressedKey(EKeyCode.Q)) {
         //      camera.DoFly(step);
         //   }
         //   if(RKeybord.IsPressedKey(EKeyCode.E)) {
         //      camera.DoFly(-step);
         //   }
         //   // 控制摄像机 (上下旋转)
         //   if(RKeybord.IsPressedKey(EKeyCode.Z)) {
         //      camera.DoPitch(stepRotation);
         //   }
         //   if(RKeybord.IsPressedKey(EKeyCode.X)) {
         //      camera.DoPitch(-stepRotation);
         //   }
         //   camera.Update();
         //}
      }

      //============================================================
      public void Process() {
         ProcessInput();
         FilterRenderables(_region.Renderables);
         _technique.Draw(_region);
         _technique.Present();
      }
   }
}
