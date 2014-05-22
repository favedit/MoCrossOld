using MO.Common.Content;
using MO.Common.IO;
using MO.Common.Lang;
using MO.Content3d.Common;
using MO.Content3d.Resource.Common;
using MO.Content3d.Resource.Texture;
using MO.Core;

namespace MO.Content3d.Resource.Material
{
   //============================================================
   // <T>材质纹理信息。</T>
   //============================================================
   public class FDrMaterialTexture : FObject
   {
      // 日志输出接口
      private static ILogger _logger = RLogger.Find(typeof(FDrMaterialTexture));

      // 材质
      protected FDrMaterialGroup _material;

      // 打开标志
      protected bool _opened;

      // 有效性
      protected bool _valid;

      // 类型
      protected int _typeCd;

      // 来源
      protected string _source;

      // 来源类型
      protected int _sourceTypeCd;

      // 来源索引
      protected int _sourceIndex;

      // 纹理
      private FDrTexture _texture;

      // 纹理图片
      private FDrTextureBitmap _textureBitmap;

      //============================================================
      // <T>构造材质纹理信息。</T>
      //============================================================
      public FDrMaterialTexture() {
      }

      //============================================================
      // <T>获得或设置材质。</T>
      //============================================================
      public FDrMaterialGroup Material {
         get { return _material; }
         set { _material = value; }
      }

      //============================================================
      // <T>获得或设置有效性。</T>
      //============================================================
      public bool IsValid {
         get { return _valid; }
         set { _valid = value; }
      }

      //============================================================
      // <T>获得或设置类型。</T>
      //============================================================
      public int TypeCd {
         get { return _typeCd; }
         set { _typeCd = value; }
      }

      //============================================================
      // <T>获得或设置类型名称。</T>
      //============================================================
      public string TypeName {
         get { return EDrTexture.ToString(_typeCd); }
      }

      //============================================================
      // <T>获得来源代码。</T>
      //============================================================
      public string SourceCode {
         get { return RDrUtil.FormatPathToCode(_source); }
      }

      //============================================================
      // <T>获得或设置来源代码。</T>
      //============================================================
      public string Source {
         get { return _source; }
         set { _source = value; }
      }

      //============================================================
      // <T>获得或设置来源类型名称。</T>
      //============================================================
      public string SourceTypeName {
         get { return EDrTexture.ToString(_sourceTypeCd); }
      }

      //============================================================
      // <T>获得或设置来源类型。</T>
      //============================================================
      public int SourceTypeCd {
         get { return _sourceTypeCd; }
         set { _sourceTypeCd = value; }
      }

      //============================================================
      // <T>获得或设置来源索引。</T>
      //============================================================
      public int SourceIndex {
         get { return _sourceIndex; }
         set { _sourceIndex = value; }
      }

      //============================================================
      // <T>获得或设置材质纹理信息。</T>
      //============================================================
      public FDrTexture Texture {
         get { return _texture; }
         set { _texture = value; }
      }

      //============================================================
      // <T>获得或设置材质纹理图片。</T>
      //============================================================
      public FDrTextureBitmap Bitmap {
         get { return _textureBitmap; }
      }

      //============================================================
      // <T>加载配置信息。</T>
      //
      // @param config 配置信息
      //============================================================
      public void LoadConfig(FXmlNode xconfig) {
         string typeName = null;
         if(xconfig.Contains("type")) {
            typeName = xconfig.Get("type");
         } else {
            typeName = xconfig.Get("type_name");
         }
         _typeCd = EDrTexture.Parse(typeName);
         _source = xconfig.Nvl("source").Replace('/', '\\');
         string sourceTypeName = xconfig.Nvl("source_type");
         _sourceTypeCd = EDrTexture.Parse(typeName);
         _sourceIndex = xconfig.GetInteger("source_index", _sourceIndex);
         // 获得关联纹理
         _texture = RContent3dManager.TextureConsole.Find(Source);
         if(null == _texture) {
            _texture = RContent3dManager.TextureConsole.Find(Source);
            RMoCore.TrackConsole.Write(this, "LoadConfig", "Texture source is not exists. (material={0}, texture={1})",
                  _material.Code, SourceCode);
            return;
         }
         _textureBitmap = _texture.FindByTypeCd(_sourceTypeCd, _sourceIndex);
      }

      //============================================================
      // <T>存储配置信息。</T>
      //
      // @param config 配置信息
      //============================================================
      public void SaveConfig(FXmlNode config) {
         config.Set("type_name", TypeName);
         config.Set("source", _source);
         config.Set("source_type", SourceTypeName);
         config.Set("source_index", _sourceIndex);
      }

      //============================================================
      // <T>序列化内部数据到输出流。</T>
      //
      // @param output 输出流
      //============================================================
      public void Serialize(IOutput output) {
         string code = EContent3dSampler.ToString(_typeCd);
         output.WriteAnsiString(code);
         string packCode = EContent3dSampler.ToString(EContent3dSampler.ToPack(_typeCd));
         output.WriteAnsiString(packCode);
         //output.WriteUint8((byte)_typeCd);
         if (_texture == null) {
            output.WriteString(null);
         } else {
            output.WriteString(_texture.Code);
         }
         //output.WriteInt32(_texture.CodeNumber);
         //output.WriteUint8((byte)_typeCd);
         //output.WriteString(SourceCode);
         //output.WriteString(_texture.TypeName);
         //output.WriteUint8((byte)_sourceIndex);
      }

      //============================================================
      // <T>打开数据内容。</T>
      //============================================================
      public void Open() {
         if(!_opened) {
            if(null != _texture) {
               _texture.Open();
            }
            _opened = true;
         }
      }

      //============================================================
      // <T>释放内容。</T>
      //============================================================
      public void Dispose() {
         if(_opened) {
            if(null != _texture) {
               _texture.Dispose();
            }
            _opened = false;
         }
      }
   }
}
