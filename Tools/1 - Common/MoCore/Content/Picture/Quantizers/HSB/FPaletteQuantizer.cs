using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using MO.Core.Content.Picture.Helpers;

namespace MO.Core.Content.Picture.Quantizers.HSB
{
   /// <summary>
   /// This is my baby. Read more in the article on the Code Project:
   /// http://www.codeproject.com/KB/recipes/SimplePaletteQuantizer.aspx
   /// </summary>
   public class FPaletteQuantizer : IColorQuantizer
   {
      private readonly List<Color> _palette;

      private readonly Dictionary<Color, Int32> _cache;

      private readonly Dictionary<Color, SColorInfo> _colorMap;

      /// <summary>
      /// Initializes a new instance of the <see cref="FPaletteQuantizer"/> class.
      /// </summary>
      public FPaletteQuantizer() {
         _palette = new List<Color>();
         _cache = new Dictionary<Color, Int32>();
         _colorMap = new Dictionary<Color, SColorInfo>();
      }

      #region [ IColorQuantizer ]

      /// ============================================================
      /// <summary>增加一个新颜色。</summary>
      /// <param name="color">颜色</param>
      /// ============================================================
      public void AddColor(Color color) {
         color = RQuantizationHelper.ConvertAlpha(color);
         SColorInfo value;
         if (_colorMap.TryGetValue(color, out value)) {
            value.IncreaseCount();
         } else {
            SColorInfo colorInfo = new SColorInfo(color);
            _colorMap.Add(color, colorInfo);
         }
      }

      /// ============================================================
      /// <summary>获得颜色总数。</summary>
      /// <returns>颜色总数</returns>
      /// ============================================================
      public int GetColorCount() {
         return _colorMap.Count;
      }

      /// ============================================================
      /// <summary>生成指定颜色数目的调色板。</summary>
      /// <param name="count">颜色数目</param>
      /// <returns>颜色列表</returns>
      /// ============================================================
      public List<Color> MakePalette(int count) {
         _palette.Clear();
         Random random = new Random(13);
         IEnumerable<SColorInfo> colorInfoList = _colorMap.
             OrderBy(entry => random.NextDouble()).
             Select(entry => entry.Value);
         if (_colorMap.Count > count) {
            colorInfoList = SolveRootLevel(colorInfoList, count);
            if (colorInfoList.Count() > count) {
               colorInfoList.OrderByDescending(colorInfo => colorInfo.Count);
               colorInfoList = colorInfoList.Take(count);
            }
         }
         _cache.Clear();
         _palette.AddRange(colorInfoList.Select(colorInfo => colorInfo.Color));
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
         _colorMap.Clear();
      }

      #endregion

      #region | Helper methods |

      /// <summary>
      /// Selects three lists, based on distinct values of each hue, saturation and brightness color
      /// components, in a single pass.
      /// </summary>
      private static void SelectDistinct(IEnumerable<SColorInfo> colors, out Dictionary<Single, SColorInfo> hueColors, out Dictionary<Single, SColorInfo> saturationColors, out Dictionary<Single, SColorInfo> brightnessColors) {
         hueColors = new Dictionary<Single, SColorInfo>();
         saturationColors = new Dictionary<Single, SColorInfo>();
         brightnessColors = new Dictionary<Single, SColorInfo>();

         foreach (SColorInfo colorInfo in colors) {
            if (!hueColors.ContainsKey(colorInfo.Hue)) {
               hueColors.Add(colorInfo.Hue, colorInfo);
            }

            if (!saturationColors.ContainsKey(colorInfo.Saturation)) {
               saturationColors.Add(colorInfo.Saturation, colorInfo);
            }

            if (!brightnessColors.ContainsKey(colorInfo.Brightness)) {
               brightnessColors.Add(colorInfo.Brightness, colorInfo);
            }
         }
      }

      private static IEnumerable<SColorInfo> SolveRootLevel(IEnumerable<SColorInfo> colors, Int32 colorCount) {
         // initializes the comparers based on hue, saturation and brightness (HSB color model)
         ColorHueComparer hueComparer = new ColorHueComparer();
         ColorSaturationComparer saturationComparer = new ColorSaturationComparer();
         ColorBrightnessComparer brightnessComparer = new ColorBrightnessComparer();

         // selects three palettes: 1) hue is unique, 2) saturation is unique, 3) brightness is unique
         Dictionary<Single, SColorInfo> hueColors, saturationColors, brightnessColors;
         SelectDistinct(colors, out hueColors, out saturationColors, out brightnessColors);

         // selects the palette (from those 3) which has the most colors, because an image has some details in that category)
         if (hueColors.Count > saturationColors.Count && hueColors.Count > brightnessColors.Count) {
            colors = Solve2ndLevel(colors, hueColors, saturationComparer, brightnessComparer, colorCount);
         } else if (saturationColors.Count > hueColors.Count && saturationColors.Count > brightnessColors.Count) {
            colors = Solve2ndLevel(colors, saturationColors, hueComparer, brightnessComparer, colorCount);
         } else {
            colors = Solve2ndLevel(colors, brightnessColors, hueComparer, saturationComparer, colorCount);
         }

         return colors;
      }

      /// <summary>
      /// If the color count is still high, determine which of the remaining color components 
      /// are prevalent, and filter all the non-distinct values of that color component.
      /// </summary>
      private static IEnumerable<SColorInfo> Solve2ndLevel(IEnumerable<SColorInfo> colors, Dictionary<Single, SColorInfo> defaultColors, IEqualityComparer<SColorInfo> firstComparer, IEqualityComparer<SColorInfo> secondComparer, Int32 colorCount) {
         IEnumerable<SColorInfo> result = colors;

         if (defaultColors.Count() > colorCount) {
            result = defaultColors.Select(entry => entry.Value);

            IEnumerable<SColorInfo> firstColors = result.Distinct(firstComparer);
            IEnumerable<SColorInfo> secondColors = result.Distinct(secondComparer);

            Int32 firstColorsCount = firstColors.Count();
            Int32 secondColorsCount = secondColors.Count();

            if (firstColorsCount > secondColors.Count()) {
               if (firstColorsCount > colorCount) {
                  result = Solve3rdLevel(result, firstColors, secondComparer, colorCount);
               }
            } else {
               if (secondColorsCount > colorCount) {
                  result = Solve3rdLevel(result, secondColors, firstComparer, colorCount);
               }
            }
         }

         return result;
      }

      /// <summary>
      /// If the color count is still high even so, filter all the non-distinct values of the last color component.
      /// </summary>
      private static IEnumerable<SColorInfo> Solve3rdLevel(IEnumerable<SColorInfo> colors, IEnumerable<SColorInfo> defaultColors, IEqualityComparer<SColorInfo> comparer, Int32 colorCount) {
         IEnumerable<SColorInfo> result = colors;

         if (result.Count() > colorCount) {
            result = defaultColors;

            IEnumerable<SColorInfo> filteredColors = result.Distinct(comparer);

            if (filteredColors.Count() >= colorCount) {
               result = filteredColors;
            }
         }

         return result;
      }

      #endregion

      #region | Helper classes (comparers) |

      /// <summary>
      /// Compares a hue components of a color info.
      /// </summary>
      private class ColorHueComparer : IEqualityComparer<SColorInfo>
      {
         public Boolean Equals(SColorInfo x, SColorInfo y) {
            return x.Hue == y.Hue;
         }

         public Int32 GetHashCode(SColorInfo color) {
            return color.HueHashCode;
         }
      }

      /// <summary>
      /// Compares a saturation components of a color info.
      /// </summary>
      private class ColorSaturationComparer : IEqualityComparer<SColorInfo>
      {
         public Boolean Equals(SColorInfo x, SColorInfo y) {
            return x.Saturation == y.Saturation;
         }

         public Int32 GetHashCode(SColorInfo color) {
            return color.SaturationHashCode;
         }
      }

      /// <summary>
      /// Compares a brightness components of a color info.
      /// </summary>
      private class ColorBrightnessComparer : IEqualityComparer<SColorInfo>
      {
         public Boolean Equals(SColorInfo x, SColorInfo y) {
            return x.Brightness == y.Brightness;
         }

         public Int32 GetHashCode(SColorInfo color) {
            return color.BrightnessHashCode;
         }
      }

      #endregion
   }
}
