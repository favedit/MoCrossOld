using System.Drawing;
using MO.Common.Content;
using MO.Common.IO;
using MO.Common.Lang;

namespace MO.Content2d.Style
{
   //============================================================
   // <T>模版外发光效果器。</T>
   //============================================================
   public class FTplGradientGlowFilter : FTplFilter
   {
      // 距离
      protected int _distance;

      // 角度
      protected int _angle;

      // 效果集合
      protected FObjects<STplEffect> _effects = new FObjects<STplEffect>();

      // 横向模糊
      protected int _blurX;

      // 纵向模糊
      protected int _blurY;

      // 粗细
      protected int _strength;

      // 品质
      protected int _quality;

      // 类型
      protected string _type;

      // 外颜色
      protected bool _knockout;

      //============================================================
      // <T>构造模版外发光效果器。</T>
      //============================================================
      public FTplGradientGlowFilter() {
      }

      //============================================================
      // <T>获得或设置距离。</T>
      //============================================================
      public int Distance {
         get { return _distance; }
         set { _distance = value; }
      }

      //============================================================
      // <T>获得或设置角度。</T>
      //============================================================
      public int Angle {
         get { return _angle; }
         set { _angle = value; }
      }

      //============================================================
      // <T>获得或设置颜色。</T>
      //============================================================
      public FObjects<STplEffect> Effects {
         get { return _effects; }
      }

      //============================================================
      // <T>获得或设置横向模糊。</T>
      //============================================================
      public int BlurX {
         get { return _blurX; }
         set { _blurX = value; }
      }

      //============================================================
      // <T>获得或设置纵向模糊。</T>
      //============================================================
      public int BlurY {
         get { return _blurY; }
         set { _blurY = value; }
      }

      //============================================================
      // <T>获得或设置粗细。</T>
      //============================================================
      public int Strength {
         get { return _strength; }
         set { _strength = value; }
      }

      //============================================================
      // <T>获得或设置品质。</T>
      //============================================================
      public int Quality {
         get { return _quality; }
         set { _quality = value; }
      }

      //============================================================
      // <T>获得或设置内类型。</T>
      //============================================================
      public string Type {
         get { return _type; }
         set { _type = value; }
      }

      //============================================================
      // <T>获得或设置外颜色。</T>
      //============================================================
      public bool Knockout {
         get { return _knockout; }
         set { _knockout = value; }
      }

      //============================================================
      // <T>加载设置节点。</T>
      //
      // @param xconfig 配置节点
      //============================================================
      public override void LoadConfig(FXmlNode xconfig) {
         base.LoadConfig(xconfig);
         _distance = xconfig.GetInteger("distance");
         _angle = xconfig.GetInteger("angle");
         _blurX = xconfig.GetInteger("blur_x");
         _blurY = xconfig.GetInteger("blur_y");
         _strength = xconfig.GetInteger("strength");
         _quality = xconfig.GetInteger("quality");
         _type = xconfig.Get("type");
         _knockout = xconfig.GetBoolean("knockout");
         foreach(FXmlNode xnode in xconfig.Nodes) {
            if(xnode.IsName("Effect")) {
               STplEffect effect = new STplEffect();
               effect.color = Color.FromArgb(RInt.ParseHex(xnode.Get("color")));
               effect.alpha = xnode.GetFloat("alpha");
               effect.ratio = xnode.GetInteger("ratio");
               _effects.Push(effect);
            }
         }
      }

      //============================================================
      // <T>存储设置节点。</T>
      //
      // @param xconfig 配置节点
      //============================================================
      public override void SaveConfig(FXmlNode xconfig) {
         base.SaveConfig(xconfig);
         xconfig.Set("distance", _distance);
         xconfig.Set("angle", _angle);
         xconfig.Set("blur_x", _blurX);
         xconfig.Set("blur_y", _blurY);
         xconfig.Set("strength", _strength);
         xconfig.Set("quality", _quality);
         xconfig.Set("type", _type);
         xconfig.Set("knockout", _knockout);
         foreach(STplEffect effect in _effects) {
            FXmlNode xeffect = xconfig.CreateNode("Effect");
            xeffect.Set("color", effect.color.ToArgb().ToString("X"));
            xeffect.Set("alpha", effect.alpha);
            xeffect.Set("ratio", effect.ratio);
         }
      }

      //============================================================
      // <T>序列化内容到输出流。</T>
      //
      // @param output 输出流
      //============================================================
      public override void Serialize(IOutput output) {
         base.Serialize(output);
         output.WriteInt8((sbyte)_distance);
         output.WriteInt8((sbyte)_angle);
         output.WriteInt8((sbyte)_effects.Count);
         foreach(STplEffect effect in _effects) {
            output.WriteInt32(effect.color.ToArgb());
            output.WriteUint8((byte)(effect.alpha * 255));
            output.WriteInt8((sbyte)effect.ratio);
         }
         output.WriteInt8((sbyte)_blurX);
         output.WriteInt8((sbyte)_blurY);
         output.WriteInt8((sbyte)_strength);
         output.WriteInt8((sbyte)_quality);
         output.WriteString(_type);
         output.WriteBool(_knockout);
      }
   }
}
