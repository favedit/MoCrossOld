using MO.Common.Lang;

namespace MO.Content2d.Common
{
   //============================================================
   // <T>资源类型。</T>
   //============================================================
   public class ERsResource
   {
      // 位置类型
      public const int Unknown = 0;

      // 组类型
      public const int Group = 1;

      // 组代码
      public const string GroupCode = "g";

      // 组名称
      public const string GroupName = "group";

      // 程序类型
      public const int Shader = 2;

      // 程序代码
      public const string ShaderCode = "s";

      // 程序名称
      public const string ShaderName = "shader";

      // 图片类型
      public const int Picture = 3;

      // 图片代码
      public const string PictureCode = "2p";

      // 图片名称
      public const string PictureName = "Picture";

      // 动画类型
      public const int Animation = 4;

      // 动画代码
      public const string AnimationCode = "2a";

      // 动画名称
      public const string AnimationName = "Animation";

      // 地图类型
      public const int Map = 5;

      // 地图类型
      public const string MapCode = "2m";

      // 地面类型
      public const int Ground = 6;

      // 地面类型
      public const string GroundCode = "2g";

      // 音乐类型
      public const int Music = 7;

      // 音乐类型
      public const string MusicCode = "wm";

      // 声音类型
      public const int Sound = 8;

      // 音效类型
      public const string SoundCode = "ws";

      // 地图瓦片
      public const int MapTile = 9;

      // SWF类型
      public const int Swf = 10;

      // SWF类型
      public const string SwfCode = "s";

      //============================================================
      // <T>获得类型全名称。</T>
      //============================================================
      public static string ToFullName(string typeName) {
         switch (typeName) {
            case PictureCode:
               return "Picture";
            case AnimationCode:
               return "Animation";
            case MusicCode:
               return "Music";
            default:
               throw new FFatalException("Type name is invalid.");
         }
      }
   }
}
