using System;
using System.Collections.Generic;
using System.IO;
using MO.Common.Content;
using MO.Common.Geom;
using MO.Common.IO;
using MO.Common.Lang;
using MO.Core.Content.Drawing;
using MO.Content3d.Common;

namespace MO.Content3d.Resource.Texture
{
   //============================================================
   // <T>位图信息。</T>
   //============================================================
   public class FDrTextureBitmap : FObject, IComparer<FDrTextureBitmap>, IDisposable
   {
      // 纹理对象
      protected FDrTexture _texture;

      // 名称
      protected string _name;

      // 类型
      protected int _typeCd;

      // 索引
      protected int _index;

      // 间隔
      protected int _tick = 100;

      // 来源
      protected string _source;

      // 文件名称
      protected string _fileName;

      // 文件长度
      protected long _length;

      // 图片大小
      protected SIntSize _size = new SIntSize();

      // 图片对象
      protected FBitmap _image;

      //============================================================
      // <T>构造位图信息。</T>
      //============================================================
      public FDrTextureBitmap() {
      }

      //============================================================
      // <T>获得或设置材质对象。</T>
      //============================================================
      public FDrTexture Texture {
         get { return _texture; }
         set { _texture = value; }
      }

      //============================================================
      // <T>获得代码。</T>
      //============================================================
      public string Code {
         get { return RDrUtil.FormatPathToCode(_name); }
      }

      //============================================================
      // <T>获得或设置类型。</T>
      //============================================================
      public string Name {
         get { return _name; }
         set { _name = value; }
      }

      //============================================================
      // <T>获得或设置类型。</T>
      //============================================================
      public int TypeCd {
         get { return _typeCd; }
         set { _typeCd = value; }
      }

      //============================================================
      // <T>获得类型名称。</T>
      //============================================================
      public string TypeName {
         get { return EDrTexture.ToString(_typeCd); }
      }

      //============================================================
      // <T>获得或设置索引。</T>
      //============================================================
      public int Index {
         get { return _index; }
         set { _index = value; }
      }

      //============================================================
      // <T>获得或设置间隔。</T>
      //============================================================
      public int Tick {
         get { return _tick; }
         set { _tick = value; }
      }
      
      //============================================================
      // <T>获得来源代码。</T>
      //============================================================
      public string SourceCode {
         get { return RDrUtil.FormatPathToCode(_source); }
      }

      //============================================================
      // <T>获得或设置来源。</T>
      //============================================================
      public string Source {
         get { return _source; }
         set { _source = value; }
      }

      //============================================================
      // <T>获得或设置文件名称。</T>
      //============================================================
      public string FileName {
         get { return _fileName; }
         set { _fileName = value; }
      }

      //============================================================
      // <T>获得或设置文件大小。</T>
      //============================================================
      public long Length {
         get { return _length; }
         set { _length = value; }
      }

      //============================================================
      // <T>获得图片大小。</T>
      //============================================================
      public SIntSize Size {
         get { return _size; }
      }

      //============================================================
      // <T>获得图片对象。</T>
      //============================================================
      public FBitmap Image {
         get { return _image; }         
      }

      //============================================================
      // <T>按照类型排序。</T>
      //============================================================
      public int Compare(FDrTextureBitmap source, FDrTextureBitmap target) {
         if(source.TypeCd == target.TypeCd) {
            return source.FileName.CompareTo(target.FileName);
         }
         return source.TypeCd - target.TypeCd;
      }

      //============================================================
      // <T>加载设置信息。</T>
      //
      // @param xconfig 配置信息
      //============================================================
      public void LoadConfig(FXmlNode xconfig) {
         string typeName = null;
         // 读取类型
         if(xconfig.Contains("type")) {
            typeName = xconfig.Get("type");
         }else{
            typeName = xconfig.Get("type_name");
         }
         _typeCd = EDrTexture.Parse(typeName);
         // 读取属性
         _tick = xconfig.GetInteger("tick", _tick);
      }

      //============================================================
      // <T>保存设置信息。</T>
      //
      // @param xconfig 配置信息
      //============================================================
      public void SaveConfig(FXmlNode config) {
         config.Set("type_name", TypeName);
         config.Set("index", _index);
         config.Set("tick", _tick);
         config.Set("source", _source);
      }

      //============================================================
      // <T>序列化数据内容到输出流。</T>
      //
      // @param output 输出流
      //============================================================
      public void Serialize(IOutput output) {
         output.WriteString(Code);
         output.WriteInt8((sbyte)_typeCd);
         output.WriteUTFString(SourceCode);
      }

      //============================================================
      // <T>打开数据内容。</T>
      //============================================================
      public void Open() {
         if (null != _image) {
            _image.Dispose();
            _image = null;
         }
         _image = new FBitmap();
         _image.LoadFile(_fileName);
         _size.Set(_image.Width, _image.Height);
         _length = new FileInfo(_fileName).Length;
      }

      //============================================================
      // <T>释放数据内容。</T>
      //============================================================
      public virtual void Dispose() {
         _image.Dispose();
         _image = null;
      }
   }
}
