using MO.Common.Content;
using MO.Common.IO;
using MO.Common.Lang;
using MO.Core.Forms.Geom;
using System.ComponentModel;

namespace MO.Content2d.Frame.Common
{
   //============================================================
   // <T>界面资源。</T>
   //============================================================
   [TypeConverter(typeof(FRcPictureConverter))]
   [DefaultValue("0,None,(0,0),(0,0,0,0)")]
   public class FRcPicture : FObject
   {
      // 代码
      protected int _code;

      // 可见性
      protected bool _visible = true;

      // 对齐
      protected ERcPictureAlign _alignCd = ERcPictureAlign.None;

      // 坐标
      protected FIntPoint2 _location = new FIntPoint2();

      // 大小
      protected FIntSize2 _size = new FIntSize2();

      // 边框
      protected FIntPadding _padding = new FIntPadding();

      //============================================================
      // <T>获得或设置代码。</T>
      //============================================================
      public int Code {
         get { return _code; }
         set { _code = value; }
      }

      //============================================================
      // <T>获得或设置可见性。</T>
      //============================================================
      public bool Visible {
         get { return _visible; }
         set { _visible = value; }
      }

      //============================================================
      // <T>获得或设置对齐方式。</T>
      //============================================================
      public ERcPictureAlign AlignCd {
         get { return _alignCd; }
         set { _alignCd = value; }
      }

      //============================================================
      // <T>获得或设置前景资源坐标。</T>
      //============================================================
      public FIntPoint2 Location {
         get { return _location; }
         set { _location.Assign(value); }
      }

      //============================================================
      // <T>获得或设置尺寸。</T>
      //============================================================
      public FIntSize2 Size {
         get { return _size; }
         set { _size.Assign(value); }
      }

      //============================================================
      // <T>获得或设置边距。</T>
      //============================================================
      public FIntPadding Padding {
         get { return _padding; }
         set { _padding.Assign(value); }
      }

      //============================================================
      // <T>测试是否有效。</T>
      //
      // @return 是否有效
      //============================================================
      public bool IsValid() {
         if (_code == 0) {
            return false;
         }
         return true;
      }

      //============================================================
      // <T>测试是否为空。</T>
      //
      // @return 是否为空
      //============================================================
      public bool IsEmpty() {
         if (_code == 0) {
            return false;
         }
         if (!_location.IsEmpty()) {
            return false;
         }
         if (!_padding.IsEmpty()) {
            return false;
         }
         return true;
      }

      ////============================================================
      //// <T>获得或设置资源编号。</T>
      ////============================================================
      //[Category("属性")]
      //[Browsable(true)]
      //[Editor(typeof(FUiResourceEditor), typeof(UITypeEditor))]
      //[DefaultValue("")]
      //[Description("资源编号")]
      //public string ResourceId {
      //   get { return id; }
      //   set {
      //      if (id != value) {
      //         id = value;
      //         // SetupResource(_foreResource);
      //      }
      //   }
      //}

      //============================================================
      // <T>接收尺寸对象。</T>
      //
      // @param size 尺寸对象
      //============================================================
      public void Assign(FRcPicture picture) {
         if (picture == null) {
            return;
         }
         _code = picture.Code;
         _alignCd = picture.AlignCd;
         _location.Assign(picture.Location);
         _size.Assign(picture.Size);
         _padding.Assign(picture.Padding);
      }

      //============================================================
      // <T>加载配置信息。</T>
      //
      // @param xconfig 配置节点
      // @param name 名称
      //============================================================
      public void LoadConfig(FXmlNode xconfig, string name) {
         // 加载资源
         if (xconfig.Contains(name + "_rid")) {
            _code = xconfig.GetInteger(name + "_rid");
         }
         // 加载对齐
         if (xconfig.Contains(name + "_align_cd")) {
            _alignCd = RUiPictureAlign.Parse(xconfig.Get(name + "_align_cd"));
         }
         // 加载位置
         if (xconfig.Contains(name + "_location")) {
            _location.Parse(xconfig.Get(name + "_location"));
         }
         // 加载尺寸
         if (xconfig.Contains(name + "_size")) {
            _size.Parse(xconfig.Get(name + "_size"));
         }
         // 加载边框
         if (xconfig.Contains(name + "_padding")) {
            _padding.Parse(xconfig.Get(name + "_padding"));
         }
      }

      //============================================================
      // <T>加载配置信息。</T>
      //
      // @param xconfig 配置节点
      // @param name 名称
      //============================================================
      public void SaveConfig(FXmlNode xconfig, string name) {
         // 保存资源
         if (_code != 0) {
            xconfig.Set(name + "_rid", _code);
         }
         // 保存对齐
         if (_alignCd != ERcPictureAlign.None) {
            xconfig.Set(name + "_align_cd", RUiPictureAlign.ToString(_alignCd));
         }
         // 保存位置
         if (!_location.IsEmpty()) {
            xconfig.Set(name + "_location", _location.ToString());
         }
         // 保存尺寸
         if (!_size.IsEmpty()) {
            xconfig.Set(name + "_size", _size.ToString());
         }
         // 保存边框
         if (!_padding.IsEmpty()) {
            xconfig.SetNvl(name + "_padding", _padding.ToString());
         }
      }

      //============================================================
      // <T>序列化内容到输出流。</T>
      //
      // @param output 输出流
      //============================================================
      public void Serialize(IOutput output) {
         output.WriteInt32(_code);
         output.WriteInt8((sbyte)_alignCd);
         _location.Serialize16(output);
         _size.Serialize16(output);
         _padding.Serialize16(output);
      }

      //============================================================
      // <T>解析字符串内容。</T>
      //
      // @param value 字符串内容
      //============================================================
      public bool Parse(string value) {
         // 获得编号
         int index = value.IndexOf(",");
         if (index == -1) {
            return false;
         }
         _code = RInt.Parse(value.Substring(0, index));
         value = value.Substring(index + 1);
         // 获得对齐方式
         index = value.IndexOf(",");
         if (index == -1) {
            return false;
         }
         _alignCd = REnum.ToValue<ERcPictureAlign>(value.Substring(0, index));
         value = value.Substring(index + 1);
         // 获得位置
         int start = value.IndexOf('(');
         int end = value.IndexOf(')');
         if ((start == -1) || (end == -1)) {
            return false;
         }
         if (!_location.Parse(value.Substring(start + 1, end - start - 1))) {
            return false;
         }
         value = value.Substring(end + 1);
         // 获得空白
         start = value.IndexOf('(');
         end = value.IndexOf(')');
         if ((start == -1) || (end == -1)) {
            return false;
         }
         if (!_padding.Parse(value.Substring(start + 1, end - start - 1))) {
            return false;
         }
         return true;
      }

      //============================================================
      // <T>获得字符串内容。</T>
      //
      // @return 字符串内容
      //============================================================
      public override string ToString() {
         string result = _code.ToString();
         result += "," + _alignCd;
         result += ",(" + _location.ToString() + ")";
         result += ",(" + _padding.ToString() + ")";
         return result;
      }
   }
}
