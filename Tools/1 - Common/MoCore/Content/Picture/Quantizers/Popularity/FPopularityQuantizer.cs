using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using MO.Core.Content.Picture.Helpers;

namespace MO.Core.Content.Picture.Quantizers.Popularity
{
   /// <summary>
   /// Popularity algorithms are another form of uniform quantization. However, instead of 
   /// dividing the color space into 256 regions these algorithms break the color space into 
   /// much smaller, and consequently many more, regions. One possible implementation is to 
   /// divide the space into regions 4x4x4 in size (262,144 regions). The original colors are 
   /// again mapped to the region they fall in. The representative color for each region is the 
   /// average of the colors mapped to it. The color map is selected by taking the representative 
   /// colors of the 256 most popular regions (the regions that had the most colors mapped to them). 
   /// If a non-empty region is not selected for the color map its index into the color map (the 
   /// index that will be assigned to colors that map to that region) is then the entry in the color 
   /// map that is closest (Euclidean distance) to its representative color). 
   ///
   /// These algorithms are also easy to implement and yield better results than the uniform 
   /// quantization algorithm. They do, however, take slightly longer to execute and can have a 
   /// significantly larger storage requirement depending on the size of regions. Also depending 
   /// on the image characteristics this may not produce a good result. This can be said about all 
   /// uniform sub-division schemes, because the method for dividing the color space does utilize 
   /// any information about the image.
   /// </summary>
   public class FPopularityQuantizer : IColorQuantizer
   {
      private readonly List<Color> _palette;

      private readonly Dictionary<Color, Int32> _cache;

      private readonly Dictionary<Int32, FPopularityColorSlot> _colorMap;

      /// <summary>
      /// Initializes a new instance of the <see cref="FPopularityQuantizer"/> class.
      /// </summary>
      public FPopularityQuantizer() {
         _palette = new List<Color>();
         _cache = new Dictionary<Color, Int32>();
         _colorMap = new Dictionary<Int32, FPopularityColorSlot>();
      }

      #region | Methods |

      private static Int32 GetColorIndex(Color color) {
         // determines the index by splitting the RGB cube to 4x4x4 (1 >> 2 = 4)
         Int32 redIndex = color.R >> 2;
         Int32 greenIndex = color.G >> 2;
         Int32 blueIndex = color.B >> 2;

         // calculates the whole unique index of the slot: Index = R*4096 + G*64 + B
         return (redIndex << 12) + (greenIndex << 6) + blueIndex;
      }

      #endregion

      #region [ IColorQuantizer ]

      /// ============================================================
      /// <summary>增加一个新颜色。</summary>
      /// <param name="color">颜色</param>
      /// ============================================================
      public void AddColor(Color color) {
         FPopularityColorSlot slot;
         color = RQuantizationHelper.ConvertAlpha(color);
         Int32 index = GetColorIndex(color);
         if (_colorMap.TryGetValue(index, out slot)) {
            slot.AddValue(color);
         } else {
            _colorMap[index] = new FPopularityColorSlot(color);
         }
      }

      /// ============================================================
      /// <summary>获得颜色总数。</summary>
      /// <returns>颜色总数</returns>
      /// ============================================================
      public Int32 GetColorCount() {
         return _colorMap.Count;
      }

      /// ============================================================
      /// <summary>生成指定颜色数目的调色板。</summary>
      /// <param name="count">颜色数目</param>
      /// <returns>颜色列表</returns>
      /// ============================================================
      public List<Color> MakePalette(int count) {
         Random random = new Random();
         IEnumerable<Color> colors = _colorMap.
              OrderBy(entry => random.NextDouble()).
              OrderByDescending(entry => entry.Value.PixelCount).
              Take(count).
              Select(entry => entry.Value.GetAverage());
         _palette.Clear();
         _palette.AddRange(colors);
         return _palette;
      }

      /// ============================================================
      /// <summary>找到指定颜色在调色板内的索引位置。</summary>
      /// <param name="color">颜色</param>
      /// <returns>索引位置</returns>
      /// ============================================================
      public int FindPaletteIndex(Color color) {
         color = RQuantizationHelper.ConvertAlpha(color);
         int result;
         if (!_cache.TryGetValue(color, out result)) {
            result = RQuantizationHelper.GetNearestColor(_palette, color);
            _cache[color] = result;
         }
         return result;
      }

      /// ============================================================
      /// <summary>清除所有内容。</summary>
      /// ============================================================
      public void Clear() {
         _cache.Clear();
         _palette.Clear();
         _colorMap.Clear();
      }

      #endregion
   }
}
