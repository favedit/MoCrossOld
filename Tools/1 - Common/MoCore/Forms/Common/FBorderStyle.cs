using System.Drawing;
using System.ComponentModel;
namespace MO.Core.Forms.Common
{
   public class FBorderStyle
   {
      protected FLineStyle _left = new FLineStyle();

      protected FLineStyle _top = new FLineStyle();

      protected FLineStyle _right = new FLineStyle();

      protected FLineStyle _bottom = new FLineStyle();

      public static FBorderStyle StyleNone = new FBorderStyle();

      public static FBorderStyle StyleFace = new FBorderStyle();

      public static FBorderStyle StyleSunken = new FBorderStyle();

      public static FBorderStyle StyleRaised = new FBorderStyle();

      //============================================================
      static FBorderStyle() {
         //
         StyleFace.Left.Color = SystemColors.ButtonFace;
         StyleFace.Top.Color = SystemColors.ButtonFace;
         StyleFace.Right.Color = SystemColors.ButtonFace;
         StyleFace.Bottom.Color = SystemColors.ButtonFace;
         //
         StyleSunken.Left.Color = SystemColors.ButtonShadow;
         StyleSunken.Top.Color = SystemColors.ButtonShadow;
         StyleSunken.Right.Color = SystemColors.ButtonHighlight;
         StyleSunken.Bottom.Color = SystemColors.ButtonHighlight;
      }

      //============================================================
      public FBorderStyle() {
      }

      //============================================================
      [TypeConverter(typeof(ExpandableObjectConverter))]
      public FLineStyle Left {
         get { return _left; }
      }

      //============================================================
      [TypeConverter(typeof(ExpandableObjectConverter))]
      public FLineStyle Top {
         get { return _top; }
      }

      //============================================================
      [TypeConverter(typeof(ExpandableObjectConverter))]
      public FLineStyle Right {
         get { return _right; }
      }

      //============================================================
      [TypeConverter(typeof(ExpandableObjectConverter))]
      public FLineStyle Bottom {
         get { return _bottom; }
      }

      //============================================================
      public void Assign(FBorderStyle style) {
         _left.Assign(style._left);
         _top.Assign(style._top);
         _right.Assign(style._right);
         _bottom.Assign(style._bottom);
      }
   }
}
