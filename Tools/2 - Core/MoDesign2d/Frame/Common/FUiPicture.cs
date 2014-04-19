using MO.Common.Content;
using MO.Common.Geom;
using MO.Common.IO;
using MO.Common.Lang;
using MO.Content2d.Frame.Common;
using MO.Content2d.Resource.Common;
using MO.Core.Forms.Geom;
using MO.Direct2d.Draw;
using System.ComponentModel;
using System.Drawing;
using System.Drawing.Design;

namespace MO.Design2d.Frame.Common
{
   //============================================================
   // <T>界面资源。</T>
   //============================================================
   public class FUiPicture : FObject
   {
      public FRcPicture picture;

      // 可见性
      public bool visible = true;

      // 图像
      public Image image;

      // 图片
      public FDxBitmap bitmap;

      // 资源
      public FRsResource resource;

      //============================================================
      // <T>测试是否为空。</T>
      //
      // @return 是否为空
      //============================================================
      public bool IsEmpty() {
         if (picture.Code != 0) {
            return false;
         }
         if (!picture.Location.IsEmpty()) {
            return false;
         }
         if (!picture.Padding.IsEmpty()) {
            return false;
         }
         return true;
      }

      //============================================================
      // <T>测试是否有效。</T>
      //
      // @return 是否有效
      //============================================================
      public bool IsValid() {
         if (picture == null) {
            return false;
         }
         if (picture.Code == 0) {
            return false;
         }
         return true;
      }

      //============================================================
      // <T>获得或设置资源编号。</T>
      //============================================================
      public int Code {
         get { return picture.Code; }
         set { picture.Code = value; }
      }

      //============================================================
      // <T>获得或设置对齐方式。</T>
      //============================================================
      public ERcPictureAlign AlignCd {
         get { return picture.AlignCd; }
         set { picture.AlignCd = value; }
      }

      //============================================================
      // <T>获得或设置前景资源坐标。</T>
      //============================================================
      public FIntPoint2 Location {
         get { return picture.Location; }
         set { picture.Location.Assign(value); }
      }

      //============================================================
      // <T>获得或设置前景资源空白。</T>
      //============================================================
      public FIntPadding Padding {
         get { return picture.Padding; }
         set { picture.Padding.Assign(value); }
      }

      //============================================================
      // <T>判断是否含有位图。</T>
      //
      // @return 是否含有位图
      //============================================================
      public bool HasBitmap() {
         return (bitmap != null);
      }

      //============================================================
      // <T>获得尺寸。</T>
      //============================================================
      [Browsable(false)]
      public SIntSize2 Size {
         get {
            if (bitmap == null) {
               return null;
            }
            return bitmap.Size;
         }
      }

      //============================================================
      // <T>接收尺寸对象。</T>
      //
      // @param size 尺寸对象
      //============================================================
      public void Assign(FUiPicture picture) {
         if (picture == null) {
            return;
         }
         //id = picture.id;
         //_alignCd = picture._alignCd;
         //_location.Assign(picture._location);
         //_padding.Assign(picture._padding);
      }

      //============================================================
      // <T>加载资源。</T>
      //
      // @param resource 资源
      //============================================================
      public void LoadResource(FRcPicture resource) {
         picture = resource;
      }

      //============================================================
      // <T>获得字符串内容。</T>
      //
      // @return 字符串内容
      //============================================================
      public override string ToString() {
         return picture.ToString();
      }

      //============================================================
      // <T>释放资源。</T>
      //============================================================
      public void Dispose() {
         if (bitmap != null) {
            bitmap.Dispose();
            bitmap = null;
         }
      }
   }
}
