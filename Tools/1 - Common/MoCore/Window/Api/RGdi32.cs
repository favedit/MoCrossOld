using System;
using System.Runtime.InteropServices;

namespace MO.Core.Window.Api
{
   public class RGdi32
   {
      public const int SRCCOPY     = 0x00CC0020; /* dest = source                   */
      public const int SRCPAINT    = 0x00EE0086; /* dest = source OR dest           */
      public const int SRCAND      = 0x008800C6; /* dest = source AND dest          */
      public const int SRCINVERT   = 0x00660046; /* dest = source XOR dest          */
      public const int SRCERASE    = 0x00440328; /* dest = source AND (NOT dest )   */
      public const int NOTSRCCOPY  = 0x00330008; /* dest = (NOT source)             */
      public const int NOTSRCERASE = 0x001100A6; /* dest = (NOT src) AND (NOT dest) */
      public const int MERGECOPY   = 0x00C000CA; /* dest = (source AND pattern)     */
      public const int MERGEPAINT  = 0x00BB0226; /* dest = (NOT source) OR dest     */
      public const int PATCOPY     = 0x00F00021; /* dest = pattern                  */
      public const int PATPAINT    = 0x00FB0A09; /* dest = DPSnoo                   */
      public const int PATINVERT   = 0x005A0049; /* dest = pattern XOR dest         */
      public const int DSTINVERT   = 0x00550009; /* dest = (NOT dest)               */
      public const int BLACKNESS   = 0x00000042; /* dest = BLACK                    */
      public const int WHITENESS   = 0x00FF0062; /* dest = WHITE                    */

      //============================================================
      [DllImport("gdi32.dll")]
      public static extern IntPtr CreateDC(
            string lpszDriver, // driver name
            string lpszDevice, // device name
            string lpszOutput, // not used; should be NULL
            Int64 lpInitData // optional printer data
         );

      //============================================================
      [DllImport("gdi32.dll")]
      public static extern IntPtr CreateCompatibleDC(
            IntPtr hdc // handle to DC
         );

      //============================================================
      //[DllImport("gdi32.dll")]
      //public static extern int GetDeviceCaps(
      //      IntPtr hdc, // handle to DC
      //      GetDeviceCapsIndex nIndex // index of capability
      //   );

      //============================================================
      [DllImport("gdi32.dll")]
      public static extern IntPtr CreateCompatibleBitmap(
            IntPtr hdc, // handle to DC
            int nWidth, // width of bitmap, in pixels
            int nHeight // height of bitmap, in pixels
         );

      //============================================================
      [DllImport("gdi32.dll")]
      public static extern IntPtr SelectObject(
            IntPtr hdc, // handle to DC
            IntPtr hgdiobj // handle to object
         );

      //============================================================
      [DllImport("gdi32.dll")]
      public static extern int BitBlt(
            IntPtr hdcDest, // handle to destination DC
            int nXDest, // x-coord of destination upper-left corner
            int nYDest, // y-coord of destination upper-left corner
            int nWidth, // width of destination rectangle
            int nHeight, // height of destination rectangle
            IntPtr hdcSrc, // handle to source DC
            int nXSrc, // x-coordinate of source upper-left corner
            int nYSrc, // y-coordinate of source upper-left corner
            UInt32 dwRop // raster operation code
         );

      //============================================================
      [DllImport("gdi32.dll")]
      public static extern int DeleteDC(
            IntPtr hdc // handle to DC
         );
   }
}
