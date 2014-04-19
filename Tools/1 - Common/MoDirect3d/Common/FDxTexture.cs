using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using SlimDX.Direct3D11;
using MO.Common.Geom;
using System.IO;

namespace MO.DX.Core.Common
{
   //============================================================
   public class FDxTexture : FDxContextObject
   {
      protected string _name;

      protected string _typeName;

      protected SIntSize _size = new SIntSize();

      protected Texture2D _nativeTexture;

      protected ShaderResourceView _nativeResource;

      //============================================================
      public FDxTexture() {
      }

      //============================================================
      public string Name {
         get { return _name; }
      }

      //============================================================
      public string TypeName {
         get { return _typeName; }
      }

      //============================================================
      public SIntSize Size {
         get { return _size; }
      }

      //============================================================
      public Texture2D NativeTexture {
         get { return _nativeTexture; }
      }

      //============================================================
      public ShaderResourceView NativeResource {
         get { return _nativeResource; }
      }

      //============================================================
      public void LoadFile(string fileName) {
         _nativeTexture = Texture2D.FromFile(_device.NativeAdapter, fileName);
         _size.Set(_nativeTexture.Description.Width, _nativeTexture.Description.Height);
         _nativeResource = new ShaderResourceView(_device.NativeAdapter, _nativeTexture);
      }
   
      //============================================================
      public void LoadStream(Stream stream) {
         if (null != _nativeTexture) {
            _nativeTexture.Dispose();
         }
         if (null != _nativeResource) {
            _nativeResource.Dispose();
         }
         stream.Position = 0;
         _nativeTexture = Texture2D.FromStream(_device.NativeAdapter, stream, (int)stream.Length);
         _nativeResource = new ShaderResourceView(_device.NativeAdapter, _nativeTexture);
      }

      //============================================================
      public void CopyFrom(FDxTexture texture) {
         _device.NativeDevice.CopyResource(texture.NativeTexture, _nativeTexture);
         //_device.NativeDevice.CopySubresourceRegion(texture.NativeTexture, 1, new ResourceRegion(0,0,0,_size.Width,_size.Height,1), _nativeTexture, 0, 0, 0, 0);
      }
   }
}
