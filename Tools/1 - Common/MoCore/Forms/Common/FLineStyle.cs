using System.ComponentModel;
using System.Drawing;

namespace MO.Core.Forms.Common
{
   public class FLineStyle
   {
      protected int _width = 1;

      protected Color _color = Color.Black;

      protected ELineStyle _style = ELineStyle.Solid;

      //============================================================
      public FLineStyle() {
      }

      //============================================================
      [DefaultValue(1)]
      public int Width {
         get { return _width; }
         set { _width = value; }
      }

      //============================================================
      public Color Color {
         get { return _color; }
         set { _color = value; }
      }

      //============================================================
      [DefaultValue(ELineStyle.Solid)]
      public ELineStyle Style {
         get { return _style; }
         set { _style = value; }
      }

      //============================================================
      public void Assign(FLineStyle style) {
         _width = style._width;
         _color = style._color;
         _style = style._style;
      }

      //============================================================
      public override string ToString() {
         return _width + "," + _color + "," + _style;
      }
   }
}
