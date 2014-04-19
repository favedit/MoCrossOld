using System;
using System.Collections.Generic;
using System.Drawing;
using MO.Core.Content.Picture.Helpers;

namespace MO.Core.Content.Picture.Quantizers.Uniform
{
   /// <summary>
   /// In uniform quantization each axis of the color space is treated independently. 
   /// Each axis is then divided into equal sized segments. The planes perpendicular to 
   /// the axis' that pass through the division points then define regions in the color 
   /// space. The number of these regions is dependent on the scheme used for dividing the 
   /// color space. One possible scheme is to divide the red and green axis into 8 segments 
   /// each and the blue axis into 4 resulting in 256 regions. Another possibility is dividing 
   /// the red and blue into 6 and the green into 7 segments resulting in 252 regions [3]. Each 
   /// one of these regions will produce a color for the color map.
   /// 
   /// Once the color space has been divided each of the original colors is then mapped to the 
   /// region which it falls in. The representative colors for each region is then the average 
   /// of all the colors mapped to that region. Because each of the regions represents an entry
   /// in the color map, the same process for mapping the original colors to a region can be 
   /// repeated for mapping the original colors to colors in the color map. While this algorithm 
   /// is quick and easy to implement it does not yield very good results. Often region in the 
   /// color space will not have any colors mapped to them resulting in color map entries to be
   /// wasted.
   ///
   /// This algorithm can also be applied in a non-uniform manner if the axis are broken on a 
   /// logarithmic scale instead of linear. This will produce slightly better results because 
   /// the human eye cannot distinguish dark colors as well as bright ones.
   /// </summary>
   public class FUniformQuantizer : IColorQuantizer
   {
      private readonly SUniformColorSlot[] _redSlots;

      private readonly SUniformColorSlot[] _greenSlots;

      private readonly SUniformColorSlot[] _blueSlots;

      /// <summary>
      /// Initializes a new instance of the <see cref="FUniformQuantizer"/> class.
      /// </summary>
      public FUniformQuantizer() {
         _redSlots = new SUniformColorSlot[8];
         _greenSlots = new SUniformColorSlot[8];
         _blueSlots = new SUniformColorSlot[4];
      }

      #region [ IColorQuantizer ]

      /// ============================================================
      /// <summary>增加一个新颜色。</summary>
      /// <param name="color">颜色</param>
      /// ============================================================
      public void AddColor(Color color) {
         color = RQuantizationHelper.ConvertAlpha(color);
         int redIndex = color.R >> 5;
         int greenIndex = color.G >> 5;
         int blueIndex = color.B >> 6;
         _redSlots[redIndex].AddValue(color.R);
         _greenSlots[greenIndex].AddValue(color.G);
         _blueSlots[blueIndex].AddValue(color.B);
      }

      /// ============================================================
      /// <summary>获得颜色总数。</summary>
      /// <returns>颜色总数</returns>
      /// ============================================================
      public int GetColorCount() {
         return 256;
      }

      /// ============================================================
      /// <summary>生成指定颜色数目的调色板。</summary>
      /// <param name="count">颜色数目</param>
      /// <returns>颜色列表</returns>
      /// ============================================================
      public List<Color> MakePalette(Int32 colorCount) {
         List<Color> result = new List<Color>();
         foreach (SUniformColorSlot redSlot in _redSlots) {
            foreach (SUniformColorSlot greenSlot in _greenSlots) {
               foreach (SUniformColorSlot blueSlot in _blueSlots) {
                  Int32 red = redSlot.GetAverage();
                  Int32 green = greenSlot.GetAverage();
                  Int32 blue = blueSlot.GetAverage();
                  Color color = Color.FromArgb(255, red, green, blue);
                  result.Add(color);
               }
            }
         }
         return result;
      }

      /// ============================================================
      /// <summary>找到指定颜色在调色板内的索引位置。</summary>
      /// <param name="color">颜色</param>
      /// <returns>索引位置</returns>
      /// ============================================================
      public int FindPaletteIndex(Color color) {
         color = RQuantizationHelper.ConvertAlpha(color);
         int redIndex = color.R >> 5;
         int greenIndex = color.G >> 5;
         int blueIndex = color.B >> 6;
         return (redIndex << 5) + (greenIndex << 2) + blueIndex;
      }

      /// ============================================================
      /// <summary>清除所有内容。</summary>
      /// ============================================================
      public void Clear() {
         Array.Clear(_redSlots, 0, 8);
         Array.Clear(_greenSlots, 0, 8);
         Array.Clear(_blueSlots, 0, 4);
      }

      #endregion
   }
}
