using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using MO.Core.Content.Picture.Helpers;

namespace MO.Core.Content.Picture.Quantizers.Median
{
   /// <summary>
   /// The premise behind median cut algorithms is to have every entry in the color map represent 
   /// the same number of pixels in the original image. In contrast to uniform sub-division, these 
   /// algorithms divide the color space based on the distribution of the original colors. The 
   /// process is as follows[2]: 
   /// 
   /// 1. Find the smallest box which contains all the colors in the image.
   /// 2. Sort the enclosed colors along the longest axis of the box.
   /// 3. Split the box into 2 regions at median of the sorted list.
   /// 4. Repeat the above process until the original color space has been divided into 256 regions.
   /// 5. The algorithm then divides the color space in a manner depicted below.
   /// 6. The representative colors are found by averaging the colors in each box, and the appropriate color map index assigned to each color in that box.
   /// 
   /// Because these algorithms use image information in dividing the color space this class of 
   /// algorithms consistently produce good results while having memory and time complexity no 
   /// worse than popularity algorithms[1].
   /// </summary>
   public class FMedianCutQuantizer : IColorQuantizer
   {
      private readonly List<Color> _colorList;

      private readonly List<FMedianCutCube> _cubeList;

      private readonly Dictionary<Color, int> _cache;

      /// <summary>
      /// Initializes a new instance of the <see cref="FMedianCutQuantizer"/> class.
      /// </summary>
      public FMedianCutQuantizer() {
         _cache = new Dictionary<Color, Int32>();
         _cubeList = new List<FMedianCutCube>();
         _colorList = new List<Color>();
      }

      #region | Methods |

      /// <summary>
      /// Splits all the cubes on the list.
      /// </summary>
      /// <param name="colorCount">The color count.</param>
      private void SplitCubes(Int32 colorCount) {
         // creates a holder for newly added cubes
         List<FMedianCutCube> newCubes = new List<FMedianCutCube>();

         foreach (FMedianCutCube cube in _cubeList) {
            // if another new cubes should be over the top; don't do it and just stop here
            // if (newCubes.Count >= colorCount) break;

            FMedianCutCube newMedianCutCubeA, newMedianCutCubeB;

            // splits the cube along the red axis
            if (cube.RedSize >= cube.GreenSize && cube.RedSize >= cube.BlueSize) {
               cube.SplitAtMedian(0, out newMedianCutCubeA, out newMedianCutCubeB);
            } else if (cube.GreenSize >= cube.BlueSize) // splits the cube along the green axis
                {
               cube.SplitAtMedian(1, out newMedianCutCubeA, out newMedianCutCubeB);
            } else // splits the cube along the blue axis
                {
               cube.SplitAtMedian(2, out newMedianCutCubeA, out newMedianCutCubeB);
            }

            // adds newly created cubes to our list; but one by one and if there's enough cubes stops the process
            newCubes.Add(newMedianCutCubeA);
            // if (newCubes.Count >= colorCount) break;
            newCubes.Add(newMedianCutCubeB);
         }

         // clears the old cubes
         _cubeList.Clear();

         // adds the new cubes to the official cube list
         _cubeList.AddRange(newCubes);
      }

      #endregion

      #region [ IColorQuantizer ]

      /// ============================================================
      /// <summary>增加一个新颜色。</summary>
      /// <param name="color">颜色</param>
      /// ============================================================
      public void AddColor(Color color) {
         color = RQuantizationHelper.ConvertAlpha(color);
         _colorList.Add(color);
      }

      /// ============================================================
      /// <summary>获得颜色总数。</summary>
      /// <returns>颜色总数</returns>
      /// ============================================================
      public int GetColorCount() {
         return _colorList.Distinct().Count();
      }

      /// ============================================================
      /// <summary>生成指定颜色数目的调色板。</summary>
      /// <param name="count">颜色数目</param>
      /// <returns>颜色列表</returns>
      /// ============================================================
      public List<Color> MakePalette(Int32 count) {
         FMedianCutCube initalMedianCutCube = new FMedianCutCube(_colorList);
         _cubeList.Add(initalMedianCutCube);
         Int32 iterationCount = 1;
         while ((1 << iterationCount) < count) { 
            iterationCount++; 
         }
         for (Int32 iteration = 0; iteration < iterationCount; iteration++) {
            SplitCubes(count);
         }
         List<Color> result = new List<Color>();
         Int32 paletteIndex = 0;
         foreach (FMedianCutCube cube in _cubeList) {
            result.Add(cube.Color);
            cube.SetPaletteIndex(paletteIndex++);
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
         int result;
         if (!_cache.TryGetValue(color, out result)) {
            foreach (FMedianCutCube cube in _cubeList) {
               if (cube.IsColorIn(color)) {
                  result = cube.PaletteIndex;
               }
            }
         }
         return result;
      }

      /// ============================================================
      /// <summary>清除所有内容。</summary>
      /// ============================================================
      public void Clear() {
         _cache.Clear();
         _cubeList.Clear();
         _colorList.Clear();
      }

      #endregion
   }
}
