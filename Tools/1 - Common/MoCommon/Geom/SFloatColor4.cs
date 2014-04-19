using System;
using System.Globalization;
using MO.Common.Content;
using MO.Common.IO;
using MO.Common.Lang;

namespace MO.Common.Geom
{
   //============================================================
   // <T>浮点数四维颜色。</T>
   //============================================================
   public class SFloatColor4
   {
      // 红色分量
      public float R;

      // 绿色分量
      public float G;

      // 蓝色分量
      public float B;

      // 透明分量
      public float A;

      //============================================================
      // <T>构造浮点数四维颜色。</T>
      //============================================================
      public SFloatColor4() {
         R = 0.0f;
         G = 0.0f;
         B = 0.0f;
         A = 1.0f;
      }

      //============================================================
      // <T>构造浮点数四维颜色。</T>
      //
      // @param value 内容
      //============================================================
      public SFloatColor4(float value) {
         R = value;
         G = value;
         B = value;
         A = value;
      }

      //============================================================
      // <T>构造浮点数四维颜色。</T>
      //
      // @param r 红色
      // @param g 绿色
      // @param b 蓝色
      // @param a 透明
      //============================================================
      public SFloatColor4(float r, float g, float b, float a) {
         R = r;
         G = g;
         B = b;
         A = a;
      }

      //============================================================
      // <T>设置四维颜色。</T>
      //
      // @param r 红色
      // @param g 绿色
      // @param b 蓝色
      // @param a 透明
      //============================================================
      public void Set(float r, float g, float b, float a) {
         R = r;
         G = g;
         B = b;
         A = a;
      }

      //============================================================
      // <T>设置四维颜色。</T>
      //
      // @param value 颜色
      //============================================================
      public void Set(SColor value) {
         R = value.ValueR;
         G = value.ValueG;
         B = value.ValueB;
         A = value.ValueA;
      }

      //============================================================
      // <T>接收四维颜色。</T>
      //
      // @param value 颜色
      //============================================================
      public void Assign(SFloatColor4 value) {
         R = value.R;
         G = value.G;
         B = value.B;
         A = value.A;
      }

      //============================================================
      // <T>接收三维颜色。</T>
      //
      // @param value 颜色
      //============================================================
      public void Assign3(SFloatColor3 value) {
          R = value.R;
          G = value.G;
          B = value.B;
      }

      //============================================================
      // <T>重置内容。</T>
      //============================================================
      public void Reset() {
         A = 1.0f;
         R = 0.0f;
         G = 0.0f;
         B = 0.0f;
      }

      //============================================================
      // <T>解析字符串内容。</T>
      //
      // @param value 字符串内容
      // @param alpha 是否包含透明
      //============================================================
      public void Parse(int value, bool alpha = true) {
         // 设置颜色
         int r = (value >> 16) & 0xFF;
         int g = (value >> 8) & 0xFF;
         int b = value & 0xFF;
         R = (float)r / 255.0f;
         G = (float)g / 255.0f;
         B = (float)b / 255.0f;
         // 设置透明
         if(alpha) {
            int a = (value >> 24) & 0xFF;
            A = (float)a / 255.0f;
         }
      }

      //============================================================
      // <T>解析字符串内容。</T>
      //
      // @param value 字符串内容
      // @param alpha 是否包含透明
      //============================================================
      public void ParseHex(string value, bool alpha = true) {
         Reset();
         if(!RString.IsEmpty(value)) {
            if(value.StartsWith("#")) {
               value = value.Substring(1);
            }
            if(value.StartsWith("0x")) {
               value = value.Substring(2);
            }
            int result = Convert.ToInt32(value, 16);
            Parse(result, alpha);
         }
      }

      //============================================================
      // <T>从配置节点信息中读取属性。</T>
      //
      // @param xconfig 配置节点
      //============================================================
      public void LoadConfig(FXmlNode xconfig) {
         R = xconfig.GetFloat("r");
         G = xconfig.GetFloat("g");
         B = xconfig.GetFloat("b");
         A = xconfig.GetFloat("a");
      }

      //============================================================
      // <T>从配置节点信息中读取属性。</T>
      //
      // @param xconfig 配置节点
      //============================================================
      public void LoadConfigPower(FXmlNode config) {
         R = config.GetFloat("r", R);
         G = config.GetFloat("g", G);
         B = config.GetFloat("b", B);
         A = config.GetFloat("power", 1.0f);
      }

      //============================================================
      // <T>从配置节点信息中读取属性。</T>
      //
      // @param xconfig 配置节点
      //============================================================
      public void LoadConfig3(FXmlNode config) {
         if (config.Contains("cr")) {
            R = config.GetFloat("cr") / 255;
         } else if (config.Contains("r")) {
            R = config.GetFloat("r");
         }
         if (config.Contains("cg")) {
            G = config.GetFloat("cg") / 255;
         } else if (config.Contains("g")) {
            G = config.GetFloat("g");
         }
         if (config.Contains("cb")) {
            B = config.GetFloat("cb") / 255;
         } else if (config.Contains("b")) {
            B = config.GetFloat("b");
         }
         if (config.Contains("ca")) {
            A = config.GetFloat("ca") / 255;
         } else if (config.Contains("a")) {
            A = config.GetFloat("a");
         }
      }

      //============================================================
      public void SaveConfig3(FXmlNode config) {
         config.Set("r", R);
         config.Set("g", G);
         config.Set("b", B);
         config.Set("a", A);
      }

      //============================================================
      public void LoadConfig(FXmlNode config, string r, string g, string b, string a) {
         R = config.GetFloat(r, R);
         G = config.GetFloat(g, G);
         B = config.GetFloat(b, B);
         A = config.GetFloat(a, A);
      }

      //============================================================
      public void SaveConfig(FXmlNode config, string r, string g, string b, string a) {
         config.Set(r, R);
         config.Set(g, G);
         config.Set(b, B);
         config.Set(a, A);
      }

      //============================================================
      public void SaveConfigPower(FXmlNode config) {
         config.Set("r", R);
         config.Set("g", G);
         config.Set("b", B);
         config.Set("power", A);
      }

      //============================================================
      // <T>序列化内部数据到输出流。</T>
      //
      // @param output 输出流
      //============================================================
      public void Serialize(IOutput output) {
         output.WriteFloat(R);
         output.WriteFloat(G);
         output.WriteFloat(B);
         output.WriteFloat(A);
      }

       //============================================================
      public void SerializeByteUnsigned4(IOutput output) {
          output.WriteUint8((byte)(R * 255));
          output.WriteUint8((byte)(G * 255));
          output.WriteUint8((byte)(B * 255));
          output.WriteUint8((byte)(A * 255));
      }

      //============================================================
      public void Unserialize(IInput input) {
         R = input.ReadFloat();
         G = input.ReadFloat();
         B = input.ReadFloat();
         A = input.ReadFloat();
      }

      //============================================================
      public string ToHexString3() {
         int r = (int)(R * 255) & 0xFF;
         int g = (int)(G * 255) & 0xFF;
         int b = (int)(B * 255) & 0xFF;
         string strR = Convert.ToString(r, 16).Length == 1 ? "0" + Convert.ToString(r, 16) : Convert.ToString(r, 16);
         string strG = Convert.ToString(g, 16).Length == 1 ? "0" + Convert.ToString(g, 16) : Convert.ToString(g, 16);
         string strB = Convert.ToString(b, 16).Length == 1 ? "0" + Convert.ToString(b, 16) : Convert.ToString(b, 16);
         return (strR + strG + strB).ToUpper();
      }

      //============================================================
      public string ToHexString() {
         return R + "," + G + "," + B + "," + A;
      }

      //============================================================
      public override string ToString() {
          return R + "," + G + "," + B + "," + A;
      }
   }
}
