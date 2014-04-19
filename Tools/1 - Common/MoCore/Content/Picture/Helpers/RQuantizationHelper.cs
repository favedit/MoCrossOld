using System;
using System.Collections.Generic;
using System.Drawing;

namespace MO.Core.Content.Picture.Helpers
{
   //============================================================
   // <T>颜色优化器。</T>
   //============================================================
   public class RQuantizationHelper
   {
      // 背景颜色
      private static readonly Color _backgroundColor;

      // 透明率
      private static readonly Double[] _factors = new double[256];

      //============================================================
      // <T>颜色优化器。</T>
      //============================================================
      static RQuantizationHelper() {
         _backgroundColor = SystemColors.Control;
         // 预计算颜色透明率
         for (int value = 0; value < 256; value++) {
            _factors[value] = value / 255.0;
         }
      }

      //============================================================
      // <T>转换透明颜色。</T>
      //
      // @param color 颜色
      // @return 颜色
      //============================================================
      internal static Color ConvertAlpha(Color color) {
         Color result = color;
         if (color.A < 255) {
            double colorFactor = _factors[color.A];
            double backgroundFactor = _factors[255 - color.A];
            int red = (int)(color.R * colorFactor + _backgroundColor.R * backgroundFactor);
            int green = (int)(color.G * colorFactor + _backgroundColor.G * backgroundFactor);
            int blue = (int)(color.B * colorFactor + _backgroundColor.B * backgroundFactor);
            result = Color.FromArgb(255, red, green, blue);
         }
         return result;
      }

      //============================================================
      // <T>获得调色板中最相近颜色的索引位置。</T>
      //
      // @param color 颜色
      // @param palette 调色板
      // @return 索引位置
      //============================================================
      internal static Int32 GetNearestColor(IList<Color> palette, Color color) {
         int bestIndex = 0;
         int leastDifference = Int32.MaxValue;
         // 计算调色板中差值最小的颜色
         int count = palette.Count;
         for (int index = 0; index < palette.Count; index++) {
            Color targetColor = palette[index];
            // 计算颜色差值
            int factorA = (color.A - targetColor.A) * (color.A - targetColor.A);
            int factorR = (color.R - targetColor.R) * (color.R - targetColor.R);
            int factorG = (color.G - targetColor.G) * (color.G - targetColor.G);
            int factorB = (color.B - targetColor.B) * (color.B - targetColor.B);
            int difference = factorA + factorR + factorG + factorB;
            // 如果颜色没有差值，立刻返回
            if (0 == difference) {
               bestIndex = index;
               break;
            }
            // 存储最小颜色差值
            if (difference < leastDifference) {
               leastDifference = difference;
               bestIndex = index;
            }
         }
         // 获得索引位置
         return bestIndex;
      }
   }
}
