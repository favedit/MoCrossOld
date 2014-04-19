using MO.Common.Lang;
using MO.DX.Core.Device;
using SlimDX;
using SlimDX.Direct3D11;
using MO.Common.Collection;
using DxBuffer = SlimDX.Direct3D11.Buffer;

namespace MO.DX.Core.Common
{
   //============================================================
   public class FDxVertexBuffer : FDxContextObject
   {
      protected uint _vertexSize;

      protected VertexBufferBinding _nativeBuffer;

      protected VertexBufferBinding[] _nativeBuffers;

      protected SlimDX.Direct3D11.Buffer _nativeD3dBuffer;

      protected BufferDescription _nativeDescription;

      //============================================================
      public FDxVertexBuffer() {
      }

      //============================================================
      public VertexBufferBinding NativeBuffer {
         get { return _nativeBuffer; }
      }

      //============================================================
      public VertexBufferBinding[] NativeBuffers {
         get { return _nativeBuffers; }
      }

      //============================================================
      public void Create(uint vertexSize) {
         _vertexSize = vertexSize;
         // 设置缓冲描述
         //_description = new BufferDescription();
         //_description.Usage = Usage.Default;
         //_description.ByteWidth = vertexSize;
         //_description.BindingOptions = BindingOptions.VertexBuffer;
         // 创建缓冲
         // _nativeD3dBuffer = _device._nativeAdapter.CreateBuffer(_description);
         // 创建顶点缓冲
         // _nativeBuffer = new VertexBuffer(_nativeD3dBuffer, vertexSize, 0);
      }

      //============================================================
      public void UploadNormal(int vertexSize, byte[] data, int offset, int length) {
         int byteSize = sizeof(float) * vertexSize;

         var stream = new DataStream(length, true, true);
         stream.Write(data, offset, length);
         stream.Position = 0;

         _nativeDescription = new BufferDescription() {
            BindFlags = BindFlags.VertexBuffer,
            CpuAccessFlags = CpuAccessFlags.None,
            OptionFlags = ResourceOptionFlags.None,
            SizeInBytes = length,
            Usage = ResourceUsage.Default,
         };

         _nativeD3dBuffer = new DxBuffer(_device.NativeAdapter, stream, _nativeDescription);
         stream.Dispose();

         FVector<VertexBufferBinding> bindings = new FVector<VertexBufferBinding>();
         bindings.Push(new VertexBufferBinding(_nativeD3dBuffer, byteSize, 0));   // Position 4
         bindings.Push(new VertexBufferBinding(_nativeD3dBuffer, byteSize, 16));  // Coord 2
         bindings.Push(new VertexBufferBinding(_nativeD3dBuffer, byteSize, 24));  // Normal 4
         _nativeBuffers = bindings.ToArray();
      }
      
      //============================================================
      public void Upload(int vertexSize, byte[] data, int offset, int length){
         int byteSize = sizeof(float) * vertexSize;

         var stream = new DataStream(length, true, true);
         stream.Write(data, offset, length);
         stream.Position = 0;

         _nativeDescription = new BufferDescription() {
            BindFlags = BindFlags.VertexBuffer,
            CpuAccessFlags = CpuAccessFlags.None,
            OptionFlags = ResourceOptionFlags.None,
            SizeInBytes = length,
            Usage = ResourceUsage.Default,
         };

         _nativeD3dBuffer = new DxBuffer(_device.NativeAdapter, stream, _nativeDescription);
         stream.Dispose();

         FVector<VertexBufferBinding> bindings = new FVector<VertexBufferBinding>();
         bindings.Push(new VertexBufferBinding(_nativeD3dBuffer, byteSize, 0));   // Position 4
         bindings.Push(new VertexBufferBinding(_nativeD3dBuffer, byteSize, 16));  // Color 4
         bindings.Push(new VertexBufferBinding(_nativeD3dBuffer, byteSize, 32));  // Coord 2
         bindings.Push(new VertexBufferBinding(_nativeD3dBuffer, byteSize, 40));  // Normal 4
         bindings.Push(new VertexBufferBinding(_nativeD3dBuffer, byteSize, 56));  // Binormal 3
         bindings.Push(new VertexBufferBinding(_nativeD3dBuffer, byteSize, 68));  // Tangent 3
         _nativeBuffers = bindings.ToArray();
      }

      //============================================================
      public void Upload5(int vertexSize, byte[] data, int offset, int length) {
         int byteSize = sizeof(float) * vertexSize;

         var stream = new DataStream(length, true, true);
         stream.Write(data, offset, length);
         stream.Position = 0;

         _nativeDescription = new BufferDescription() {
            BindFlags = BindFlags.VertexBuffer,
            CpuAccessFlags = CpuAccessFlags.None,
            OptionFlags = ResourceOptionFlags.None,
            SizeInBytes = length,
            Usage = ResourceUsage.Default,
         };

         _nativeD3dBuffer = new DxBuffer(_device.NativeAdapter, stream, _nativeDescription);
         stream.Dispose();

         FVector<VertexBufferBinding> bindings = new FVector<VertexBufferBinding>();
         bindings.Push(new VertexBufferBinding(_nativeD3dBuffer, byteSize, 0));   // Position 4
         bindings.Push(new VertexBufferBinding(_nativeD3dBuffer, byteSize, 16));  // Coord 2
         _nativeBuffers = bindings.ToArray();
      }
   }
}
