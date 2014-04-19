using MO.Common.Content;
using MO.Common.IO;
using MO.Common.Lang;

namespace MO.Content3d.Resource.Scene
{
   //============================================================
   // <T>场景光源阴影。</T>
   //============================================================
   public class FDrSceneLightShadow : FObject
   {
      // 基础
      protected float _base;

      // 比率
      protected float _rate;

      // 级别
      protected float _level;

      // 范围
      protected float _range;

      //============================================================
      // <T>构造场景光源阴影。</T>
      //============================================================
      public FDrSceneLightShadow() {
      }

      //============================================================
      // <T>获得或设置基础。</T>
      //============================================================
      public float Base {
         get { return _base; }
         set { _base = value; }
      }

      //============================================================
      // <T>获得或设置比率。</T>
      //============================================================
      public float Rate {
         get { return _rate; }
         set { _rate = value; }
      }

      //============================================================
      // <T>获得或设置级别。</T>
      //============================================================
      public float Level {
         get { return _level; }
         set { _level = value; }
      }

      //============================================================
      // <T>获得或设置范围。</T>
      //============================================================
      public float Range {
         get { return _range; }
         set { _range = value; }
      }

      //============================================================
      // <T>加载配置信息。</T>
      //
      // @param xconfig 配置信息
      //============================================================
      public void LoadOrignConfig(FXmlNode xconfig, string code) {
         if(xconfig.Contains("base" + code)) {
            _base = xconfig.GetFloat("base" + code);
         }
         if(xconfig.Contains("rate" + code)) {
            _rate = xconfig.GetFloat("rate" + code);
         }
         if(xconfig.Contains("level" + code)) {
            _level = xconfig.GetFloat("level" + code);
         }
         if(xconfig.Contains("range" + code)) {
            _range = xconfig.GetFloat("range" + code);
         }
      }
      
      //============================================================
      // <T>加载配置信息。</T>
      //
      // @param xconfig 配置信息
      //============================================================
      public void LoadConfig(FXmlNode xconfig, string code) {
         _base = xconfig.GetFloat("base" + code);
         _rate = xconfig.GetFloat("rate" + code);
         _level = xconfig.GetFloat("level" + code);
         _range = xconfig.GetFloat("range" + code);
      }

      //============================================================
      // <T>存储配置信息。</T>
      //
      // @param xconfig 配置信息
      //============================================================
      public void SaveConfig(FXmlNode xconfig, string code) {
         xconfig.Set("base" + code, _base);
         xconfig.Set("rate" + code, _rate);
         xconfig.Set("level" + code, _level);
         xconfig.Set("range" + code, _range);
      }

      //============================================================
      // <T>序列化内部数据到输出流。</T>
      //
      // @param output 输出流
      //============================================================
      public void Serialize(IOutput output) {
         output.WriteFloat(_base);
         output.WriteFloat(_rate);
         output.WriteFloat(_level);
         output.WriteFloat(_range);
      }
   }
}
