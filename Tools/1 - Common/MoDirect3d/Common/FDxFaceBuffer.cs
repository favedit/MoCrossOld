using MO.Common.Lang;
using MO.DX.Core.Device;
using SlimDX;
using SlimDX.Direct3D11;
using DxBuffer = SlimDX.Direct3D11.Buffer;

namespace MO.DX.Core.Common
{
   //============================================================
   public class FDxFaceBuffer : FDxContextObject
   {
      protected DxBuffer _nativedBuffer;

      protected BufferDescription _nativeDescription;

      public int count;

      //============================================================
      public FDxFaceBuffer() {
      }

      //============================================================
      public DxBuffer NativeBuffer {
         get { return _nativedBuffer; }
      }

      //============================================================
      public void Create(uint vertexSize) {
         //// 设置缓冲描述
         //_nativeDescription = new BufferDescription();
         //_nativeDescription.Usage = Usage.Default;
         //_nativeDescription.ByteWidth = vertexSize;
         //_nativeDescription.BindingOptions = BindingOptions.IndexBuffer;
         //// 创建缓冲
         //_nativeD3dBuffer = _device._nativeAdapter.CreateBuffer(_nativeDescription);
         //// 创建顶点缓冲
         //_nativeBuffer = new IndexBuffer(_nativeD3dBuffer, Format.B8G8R8A8UNorm, 0);
      }

      //============================================================
      public void Upload(byte[] data, int offset, int length) {
         count = length / 4;

         var stream = new DataStream(length, true, true);
         stream.Write(data, offset, length);
         stream.Position = 0;

         _nativeDescription = new BufferDescription() {
            BindFlags = BindFlags.IndexBuffer,
            CpuAccessFlags = CpuAccessFlags.None,
            OptionFlags = ResourceOptionFlags.None,
            SizeInBytes = length,
            Usage = ResourceUsage.Default,
         };

         _nativedBuffer = new DxBuffer(_device.NativeAdapter, stream, _nativeDescription);

         stream.Dispose();
      }
      
      //============================================================
      public void Upload32(byte[] data, int offset, int length) {
         count = length / 4;
         var stream = new DataStream(length, true, true);
         stream.Write(data, offset, length);
         stream.Position = 0;
         _nativeDescription = new BufferDescription() {
            BindFlags = BindFlags.IndexBuffer,
            CpuAccessFlags = CpuAccessFlags.None,
            OptionFlags = ResourceOptionFlags.None,
            SizeInBytes = length,
            Usage = ResourceUsage.Default,
         };
         _nativedBuffer = new DxBuffer(_device.NativeAdapter, stream, _nativeDescription);
         stream.Dispose();
      }
   }
}
