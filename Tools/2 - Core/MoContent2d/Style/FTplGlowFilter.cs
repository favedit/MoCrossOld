using System.Drawing;
using MO.Common.Content;
using MO.Common.IO;
using MO.Common.Lang;

namespace MO.Content2d.Style
{
   //============================================================
   // <T>模版泛光效果器。</T>
   //============================================================
   public class FTplGlowFilter : FTplFilter
   {
      // 颜色
      protected Color _color;

      // 半透明
      protected float _alpha;

      // 横向模糊
      protected int _blurX;

      // 纵向模糊
      protected int _blurY;

      // 粗细
      protected int _strength;

      // 品质
      protected int _quality;

      // 内颜色
      protected bool _inner;

      // 外颜色
      protected bool _knockout;

      //============================================================
      // <T>构造模版泛光效果器。</T>
      //============================================================
      public FTplGlowFilter() {
      }

      //============================================================
      // <T>获得或设置颜色。</T>
      //============================================================
      public Color Color {
         get { return _color; }
         set { _color = value; }
      }

      //============================================================
      // <T>获得或设置半透明。</T>
      //============================================================
      public float Alpha {
         get { return _alpha; }
         set { _alpha = value; }
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
      // <T>获得或设置内颜色。</T>
      //============================================================
      public bool Inner {
         get { return _inner; }
         set { _inner = value; }
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
         _color = Color.FromArgb(RInt.ParseHex(xconfig.Get("color")));
         _alpha = xconfig.GetFloat("alpha");
         _blurX = xconfig.GetInteger("blur_x");
         _blurY = xconfig.GetInteger("blur_y");
         _strength = xconfig.GetInteger("strength");
         _quality = xconfig.GetInteger("quality");
         _inner = xconfig.GetBoolean("inner");
         _knockout = xconfig.GetBoolean("knockout");
      }

      //============================================================
      // <T>存储设置节点。</T>
      //
      // @param xconfig 配置节点
      //============================================================
      public override void SaveConfig(FXmlNode xconfig) {
         base.SaveConfig(xconfig);
         xconfig.Set("color", _color.ToArgb().ToString("X"));
         xconfig.Set("alpha", _alpha);
         xconfig.Set("blur_x", _blurX);
         xconfig.Set("blur_y", _blurY);
         xconfig.Set("strength", _strength);
         xconfig.Set("quality", _quality);
         xconfig.Set("inner", _inner);
         xconfig.Set("knockout", _knockout);
      }

      //============================================================
      // <T>序列化内容到输出流。</T>
      //
      // @param output 输出流
      //============================================================
      public override void Serialize(IOutput output) {
         base.Serialize(output);
         output.WriteInt32(_color.ToArgb());
         output.WriteUint8((byte)(_alpha * 255));
         output.WriteInt8((sbyte)_blurX);
         output.WriteInt8((sbyte)_blurY);
         output.WriteInt8((sbyte)_strength);
         output.WriteInt8((sbyte)_quality);
         output.WriteBool(_inner);
         output.WriteBool(_knockout);
      }
   }
}
