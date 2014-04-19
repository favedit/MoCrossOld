namespace MO.Content2d.Frame.Common
{
   //============================================================
   // <T>图片对齐方式。</T>
   //============================================================
   public enum ERcPictureAlign : int
   {
      // 无
      None = 0,

      // 左
      Left = 1,

      // 左上
      LeftTop = 2,

      // 上
      Top = 3,

      // 右上
      RightTop = 4,

      // 右
      Right = 5,

      // 右下
      RightBottom = 6,

      // 下
      Bottom = 7,

      // 左下
      LeftBottom = 8,

      // 中间
      Center = 9,

      // 周围空白
      Padding = 10,

      // 九宫格
      Square = 11,
   }

   //============================================================
   // <T>图片对齐方式工具类。</T>
   //============================================================
   public class RUiPictureAlign
   {
      //============================================================
      // <T>根据从字符串获得枚举内容。</T>
      //
      // @param value 字符串
      // @return 枚举内容
      //============================================================
      public static ERcPictureAlign Parse(string value) {
         switch (value) {
            case "left":
               return ERcPictureAlign.Left;
            case "left.top":
               return ERcPictureAlign.LeftTop;
            case "top":
               return ERcPictureAlign.Top;
            case "right.top":
               return ERcPictureAlign.RightTop;
            case "right":
               return ERcPictureAlign.Right;
            case "right.bottom":
               return ERcPictureAlign.RightBottom;
            case "bottom":
               return ERcPictureAlign.Bottom;
            case "left.bottom":
               return ERcPictureAlign.LeftBottom;
            case "center":
               return ERcPictureAlign.Center;
            case "padding":
               return ERcPictureAlign.Padding;
            case "square":
               return ERcPictureAlign.Square;
         }
         return ERcPictureAlign.None;
      }

      //============================================================
      // <T>根据枚举内容获得字符串。</T>
      //
      // @param value 枚举内容
      // @return 字符串
      //============================================================
      public static string ToString(ERcPictureAlign value) {
         switch (value) {
            case ERcPictureAlign.Left:
               return "left";
            case ERcPictureAlign.LeftTop:
               return "left.top";
            case ERcPictureAlign.Top:
               return "top";
            case ERcPictureAlign.RightTop:
               return "right.top";
            case ERcPictureAlign.Right:
               return "right";
            case ERcPictureAlign.RightBottom:
               return "right.bottom";
            case ERcPictureAlign.Bottom:
               return "bottom";
            case ERcPictureAlign.LeftBottom:
               return "left.bottom";
            case ERcPictureAlign.Center:
               return "center";
            case ERcPictureAlign.Padding:
               return "padding";
            case ERcPictureAlign.Square:
               return "square";
         }
         return "none";
      }
   }
}
