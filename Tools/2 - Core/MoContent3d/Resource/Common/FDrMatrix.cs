using MO.Common.Content;
using MO.Common.Geom;
using MO.Common.IO;
using MO.Common.Lang;

namespace MO.Content3d.Resource.Common
{
   //============================================================
   public class FDrMatrix : FObject
   {
      // 移动信息
      protected SFloatPoint3 _translation = new SFloatPoint3();

      // 缩放信息
      protected SFloatVector3 _euler = new SFloatVector3();

      // 缩放信息
      protected SFloatVector3 _scale = new SFloatVector3(1.0f, 1.0f, 1.0f);

      //============================================================
      // 移动信息
      public SFloatPoint3 Translation {
         get { return _translation; }
      }

      //============================================================
      // 缩放信息
      public SFloatVector3 Euler {
         get { return _euler; }
      }

      //============================================================
      // 缩放信息
      public SFloatVector3 Scale{ 
         get{return _scale;}
      }

      //============================================================
      // <T>加载配置信息。</T>
      //
      // @param config 配置信息
      //============================================================
      public void LoadConfig(FXmlNode config) {
         _translation.LoadConfig(config.Find("Translation"));
         _euler.LoadConfig(config.Find("Euler"));
         _scale.LoadConfig(config.Find("Scale"));
      }

      //============================================================
      // <T>加载简要配置信息。</T>
      //
      // @param config 配置信息
      //============================================================
      public void LoadSimpleConfig(FXmlNode config) {
         _translation.X = config.GetFloat("tx");
         _translation.Y = config.GetFloat("ty");
         _translation.Z = config.GetFloat("tz");
         _euler.X = config.GetFloat("rx");
         _euler.Y = config.GetFloat("ry");
         _euler.Z = config.GetFloat("rz");
         _scale.X = config.GetFloat("sx");
         _scale.Y = config.GetFloat("sy");
         _scale.Z = config.GetFloat("sz");
      }

      //============================================================
      // <T>加载简要配置信息。</T>
      //
      // @param config 配置信息
      //============================================================
      public void LoadSimpleAngleConfig(FXmlNode config) {
         _translation.X = config.GetFloat("tx");
         _translation.Y = config.GetFloat("ty");
         _translation.Z = config.GetFloat("tz");
         _euler.X = config.GetFloat("rx") * RFloat.DegreeRate;
         _euler.Y = config.GetFloat("ry") * RFloat.DegreeRate;
         _euler.Z = config.GetFloat("rz") * RFloat.DegreeRate;
         _scale.X = config.GetFloat("sx");
         _scale.Y = config.GetFloat("sy");
         _scale.Z = config.GetFloat("sz");
      }

      //============================================================
      public void Unserialize(IInput input) {
         _translation.X = input.ReadFloat();
         _translation.Y = input.ReadFloat();
         _translation.Z = input.ReadFloat();
         _euler.X = input.ReadFloat();
         _euler.Y = input.ReadFloat();
         _euler.Z = input.ReadFloat();
         _scale.X = input.ReadFloat();
         _scale.Y = input.ReadFloat();
         _scale.Z = input.ReadFloat();
      }

      //============================================================
      public void UnserializeAngle(IInput input) {
         _translation.X = input.ReadFloat();
         _translation.Y = input.ReadFloat();
         _translation.Z = input.ReadFloat();
         _euler.X = input.ReadFloat() * RFloat.DegreeRate;
         _euler.Y = input.ReadFloat() * RFloat.DegreeRate;
         _euler.Z = input.ReadFloat() * RFloat.DegreeRate;
         _scale.X = input.ReadFloat();
         _scale.Y = input.ReadFloat();
         _scale.Z = input.ReadFloat();
      }

      //============================================================
      public void Serialize(IOutput output) {
         _translation.Serialize(output);
         _euler.Serialize(output);
         _scale.Serialize(output);
      }
   }
}
