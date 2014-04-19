using MO.Common.Collection;
using MO.Common.IO;
using MO.Common.Lang;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace MpProfileLogger
{
   static class Program
   {
      static void Convert(string inputFileName, string outputFileName)
      {
         FDictionary<string> dateValues = new FDictionary<string>();
         FLineFile inputFile = new FLineFile(inputFileName);
         FLineFile outputFile = new FLineFile();
         foreach (string line in inputFile.Items)
         {
            if (line != null && line.Contains("FPSCounter"))
            {
               // 解析字符串
               // 04-10 12:24:50.635: D/FPSCounter(21430): fps=5 span=6
               //string dateValue = line.Substring(0, 18);
               //long dateH = RLong.Parse(dateValue.Substring(6, 2));
               //long dateM = RLong.Parse(dateValue.Substring(9, 2));
               //long dateS = RLong.Parse(dateValue.Substring(12, 2));
               string dateValue = "2014-" + line.Substring(0, 14);
               if (dateValues.Contains(dateValue))
               {
                  continue;
               }
               int findFps = line.IndexOf("fps=");
               int findEnd = line.IndexOf(" ", findFps);
               string fpsValue = line.Substring(findFps + 4, findEnd - findFps - 4);
               int fps = RInt.Parse(fpsValue);
               int findSpan = line.IndexOf("span=");
               int length = line.Length;
               string spanValue = line.Substring(findSpan + 5, length - findSpan - 5);
               int span = RInt.Parse(spanValue);
               Console.WriteLine(dateValue + " Fps=[" + fps + "] Span=[" + span + "]");
               outputFile.Add(dateValue + "," + fps + "," + span);
               dateValues.Set(dateValue, line);
            }
         }
         outputFile.SaveFile(outputFileName);
      }

      [STAThread]
      static void Main()
      {
         //Convert("E:/Log/log_instance_g3d.txt", "E:/Log/log_instance_g3d.csv");
         //Convert("E:/Log/log_instance_test.txt", "E:/Log/log_instance_test.csv");
         Convert("E:/Log/log_instance_stest.txt", "E:/Log/log_instance_stest.csv");
      }
   }
}
