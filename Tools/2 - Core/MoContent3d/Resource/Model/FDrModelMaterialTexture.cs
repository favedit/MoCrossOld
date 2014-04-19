using MO.Common.Content;
using MO.Common.IO;
using MO.Common.Lang;
using MO.Content3d.Common;
using MO.Content3d.Resource.Texture;

namespace MO.Content3d.Resource.Material
{
   //============================================================
   // <T>材质纹理信息。</T>
   //============================================================
   public class FDrModelMaterialTexture : FObject
   {
      private static ILogger _logger = RLogger.Find(typeof(FDrModelMaterialTexture));

      protected bool _opened;

      protected bool _valid;

      protected int _typeCd;

      protected string _source;

      protected string _sourceType;

      private FDrTexture _texture;

      //============================================================
      // <T>构造材质纹理信息。</T>
      //============================================================
      public FDrModelMaterialTexture() {
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
         get {
            FDrTextureBitmap bitmap = null;
            if(null != _texture) {
               bitmap = _texture.GetByTypeCd(_typeCd);
            }
            return bitmap; 
         }
      }

      //============================================================
      public string SourceCode {
         get { return RDrUtil.FormatPathToCode(_source); }
      }

      //============================================================
      public string Source {
         get { return _source; }
         set { _source = value; }
      }

      //============================================================
      public string SourceType {
         get { return _sourceType; }
         set { _sourceType = value; }
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
         _source = xconfig.Nvl("source");
         _source  = _source.Replace('/', '\\');
         _sourceType = xconfig.Nvl("source_type");
      }

      //============================================================
      // <T>存储配置信息。</T>
      //
      // @param config 配置信息
      //============================================================
      public void SaveConfig(FXmlNode config) {
         config.Set("type_name", TypeName);
         config.Set("source", _source);
         config.Set("source_type", _sourceType);
      }

      //============================================================
      public void Serialize(IOutput output) {
         output.WriteInt8((sbyte)_typeCd);
         output.WriteString(SourceCode);
         output.WriteString(_sourceType);
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
