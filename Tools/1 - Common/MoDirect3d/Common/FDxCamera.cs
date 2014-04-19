using MO.Common.Geom;
using MO.Common.Lang;
using SlimDX;
using DxMatrix = SlimDX.Matrix;
using MO.DX.Core.Common.Geom;

namespace MO.DX.Core.Common
{
   //============================================================
   public class FDxCamera : FObject
   {
      protected FDxViewport _viewport = new FDxViewport();

      // 位置
      protected SFloatPoint3 _position = new SFloatPoint3();

      // 方向
      protected SFloatVector3 _direction = new SFloatVector3();

      // 距离
      protected float _distance = 1.0f;

      // 目标点(只计算用)
      protected SFloatPoint3 _target = new SFloatPoint3();

      // 变换矩阵
      protected SDxMatrix _matrix = new SDxMatrix();

      // X轴线
      protected SFloatVector3 _axisX = new SFloatVector3();

      // Y轴线
      protected SFloatVector3 _axisY = new SFloatVector3();

      // Z轴线
      protected SFloatVector3 _axisZ = new SFloatVector3();

      //============================================================
      public FDxCamera() {
      }

      //============================================================
      public SFloatPoint3 Position {
         get { return _position; }
      }

      //============================================================
      public SFloatVector3 Direction {
         get { return _direction; }
      }

      //============================================================
      public float Distance {
         get { return _distance; }
         set { _distance = value; }
      }

      //============================================================
      public SFloatPoint3 Target {
         get { return _target; }
      }

      //============================================================
      public SDxMatrix Matrix {
         get { return _matrix; }
      }

      //============================================================
      public FDxViewport Viewport {
         get { return _viewport; }
      }

      //============================================================
      // <T>向前/向后移动</T>
      //
      // @param units 单位
      //============================================================
      public void DoWalk(float distance) {
         _position.X += _direction.X * distance;
         _position.Z += _direction.Z * distance;
         UpdateTarget();
      }

      //============================================================
      // <T>向上/向下移动</T>
      //
      // @param units 单位
      //============================================================
      public void DoFly(float distance) {
         _position.Y += distance;
         UpdateTarget();
      }

      //============================================================
      // <T>向左/向右旋转</T>
      //
      // @param angle 角度
      //============================================================
      public void DoYaw(float angle) {
         SFloatVector3 direction = new SFloatVector3();
         // 旋转Y轴
         SDxMatrix matrix = new SDxMatrix();
         matrix.ry = angle;
         matrix.UpdateForce();
         matrix.Transform3x3Vector3(direction, _direction);
         // 旋转方向
         direction.Normalize();
         _direction.Assign(direction);
         UpdateTarget();
      }

      //============================================================
      // <T>向上/向下旋转</T>
      //
      // @param angle 角度
      //============================================================
      public void DoPitch(float angle) {
         SFloatVector3 axis = new SFloatVector3();
         SFloatVector3 axisUp = new SFloatVector3(0, 1, 0);
         axisUp.Cross(_direction, axis);
         DxMatrix dxMatrix = DxMatrix.RotationAxis(new Vector3(axis.X, axis.Y, axis.Z), angle);
         SFloatVector3 direction = new SFloatVector3();
         // 旋转Y轴
         SDxMatrix matrix = new SDxMatrix();
         matrix.AssignNative(dxMatrix);
         matrix.UpdateData();
         matrix.Transform3x3Vector3(direction, _direction);
         // 旋转方向
         direction.Normalize();
         _direction.Assign(direction);
         UpdateTarget();
      }

      //============================================================
      public void UpdateTarget() {
         _target.X = _position.X + (_distance * _direction.X);
         _target.Y = _position.Y + (_distance * _direction.Y);
         _target.Z = _position.Z + (_distance * _direction.Z);
      }

      //============================================================
      public void LookAt(float x, float y, float z) {
         _target.Set(x, y, z);
         _direction.X = x - _position.X;
         _direction.Y = y - _position.Y;
         _direction.Z = z - _position.Z;
         _direction.Normalize();
         Update();
      }

      //============================================================
      public void Update() {
         Vector3 position = new Vector3(_position.X, _position.Y, _position.Z);
         Vector3 target = new Vector3(_target.X, _target.Y, _target.Z);
         _matrix.AssignNative(DxMatrix.LookAtLH(position, target, Vector3.UnitY));
      }

      // 位置
      protected SFloatPoint3 _storePosition = new SFloatPoint3();

      // 目标点(只计算用)
      protected SFloatPoint3 _storeTarget = new SFloatPoint3();

      // 目标点(只计算用)
      protected SFloatVector3 _storeDirection = new SFloatVector3();

      //============================================================
      public void Store() {
         _storePosition.Assign(_position);
         _storeTarget.Assign(_target);
         _storeDirection.Assign(_direction);
      }

      //============================================================
      public void Restore() {
         _position.Assign(_storePosition);
         _target.Assign(_storeTarget);
         _direction.Assign(_storeDirection);
      }
   }
}
