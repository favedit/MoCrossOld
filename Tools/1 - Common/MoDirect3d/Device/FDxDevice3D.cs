using System;
using System.Drawing;
using MO.Common.Geom;
using MO.Common.Lang;
using MO.DX.Core.Common;
using SlimDX;
using SlimDX.Direct3D11;
using SlimDX.DXGI;
using DxDevice = SlimDX.Direct3D11.Device;
using DxResource = SlimDX.Direct3D11.Resource;

namespace MO.DX.Core.Device
{
   //============================================================
   public class FDxDevice3D : FObject
   {
      protected FDxAdapter _adapter;

      protected DxDevice _nativeAdapter;

      protected SwapChain _nativeSwap;

      protected DeviceContext _nativeDevice;

      protected RenderTargetView _nativeTargetActive;

      protected RenderTargetView _nativeTarget;

      protected FDxBufferedTexture _target = new FDxBufferedTexture();

      protected Viewport _nativeViewport;

      protected DepthStencilView _nativeDepth;

      protected FDxProgram _program;

      protected SIntSize _size = new SIntSize();

      //============================================================
      public FDxDevice3D() {
      }

      //============================================================
      public FDxAdapter Adapter {
         get { return _adapter; }
         set { _adapter = value; }
      }

      //============================================================
      public DxDevice NativeAdapter {
         get { return _nativeAdapter; }
      }

      //============================================================
      public DeviceContext NativeDevice {
         get { return _nativeDevice; }
      }

      //============================================================
      public RenderTargetView NativeTarget {
         get { return _nativeTarget; }
      }

      //============================================================
      public FDxBufferedTexture Target {
         get { return _target; }
      }

      //============================================================
      public DepthStencilView NativeDepth {
         get { return _nativeDepth; }
      }

      //============================================================
      public SIntSize Size {
         get { return _size; }
      }

      //============================================================
      // 创建背景缓冲
      private void CreatePrimaryRenderTarget() {
         using (Texture2D texture = DxResource.FromSwapChain<Texture2D>(_nativeSwap, 0)) {
            _nativeTarget = new RenderTargetView(_nativeAdapter, texture);
            _target.NativeTarget = _nativeTarget;
         }
      }

      //============================================================
      // 创建深度缓冲
      protected void CreateDepthBuffer() {
         var description = new Texture2DDescription {
            BindFlags = BindFlags.DepthStencil,
            CpuAccessFlags = CpuAccessFlags.None,
            Usage = ResourceUsage.Default,
            ArraySize = 1,
            Format = Format.D32_Float,
            Width = _size.Width,
            Height = _size.Height,
            MipLevels = 1,
            OptionFlags = ResourceOptionFlags.None,
            SampleDescription = new SampleDescription(1, 0),
         };
         using (Texture2D texture = new Texture2D(_nativeAdapter, description)) {
            _nativeDepth = new DepthStencilView(_nativeAdapter, texture);
         }
      }

      //============================================================
      public void Setup(int width, int height) {
         // 创建设备
         SwapChainDescription description = new SwapChainDescription() {
            BufferCount = 1,
            ModeDescription = new ModeDescription(width, height, new Rational(60, 1), Format.R8G8B8A8_UNorm),
            IsWindowed = false,
            SampleDescription = new SampleDescription(1, 0),
            SwapEffect = SwapEffect.Discard,
            Usage = Usage.RenderTargetOutput,
         };
#if DEBUG
         DxDevice.CreateWithSwapChain(DriverType.Hardware, DeviceCreationFlags.Debug, description, out _nativeAdapter, out _nativeSwap);
#else
         DxDevice.CreateWithSwapChain(DriverType.Hardware, DeviceCreationFlags.None, description, out _nativeAdapter, out _nativeSwap);
#endif
         _nativeDevice = _nativeAdapter.ImmediateContext;
         // 设置缓冲大小
         ConfigureBackBuffer(width, height);
      }
      
      //============================================================
      public void Setup(IntPtr handle, int width, int height) {
         // 创建设备
         SwapChainDescription description = new SwapChainDescription() {
            BufferCount = 1,
            ModeDescription = new ModeDescription(width, height, new Rational(60, 1), Format.R8G8B8A8_UNorm),
            IsWindowed = true,
            OutputHandle = handle,
            SampleDescription = new SampleDescription(1, 0),
            SwapEffect = SwapEffect.Discard,
            Usage = Usage.RenderTargetOutput,
         };
#if DEBUG
         DxDevice.CreateWithSwapChain(DriverType.Hardware, DeviceCreationFlags.Debug, description, out _nativeAdapter, out _nativeSwap);
#else
         DxDevice.CreateWithSwapChain(DriverType.Hardware, DeviceCreationFlags.None, description, out _nativeAdapter, out _nativeSwap);
#endif
         _nativeAdapter.Factory.SetWindowAssociation(handle, WindowAssociationFlags.IgnoreAll);
         _nativeDevice = _nativeAdapter.ImmediateContext;
         // 设置缓冲大小
         ConfigureBackBuffer(width, height);
      }

      //============================================================
      public bool ModeWireFrame {
         set {
            // 设置绘制模式
            RasterizerStateDescription rasterizerStateDescription = new RasterizerStateDescription() {
               CullMode = CullMode.None,
               FillMode = value ? FillMode.Wireframe : FillMode.Solid,
               IsAntialiasedLineEnabled = false,
               IsDepthClipEnabled = true,
            };
            RasterizerState rasterizerState = RasterizerState.FromDescription(_nativeAdapter, rasterizerStateDescription);
            _nativeDevice.Rasterizer.State = rasterizerState;
            rasterizerState.Dispose();
         }
      }

      //============================================================
      public bool ModeDouble {
         set {
            // 设置绘制模式
            RasterizerStateDescription rasterizerStateDescription = new RasterizerStateDescription() {
               CullMode = value ? CullMode.None : CullMode.Back,
               FillMode = FillMode.Solid,
               IsAntialiasedLineEnabled = false,
               IsDepthClipEnabled = true,
            };
            RasterizerState rasterizerState = RasterizerState.FromDescription(_nativeAdapter, rasterizerStateDescription);
            _nativeDevice.Rasterizer.State = rasterizerState;
            rasterizerState.Dispose();
         }
      }

      //============================================================
      public FDxProgram Program {
         get { return _program; }
         set {
            if (_program != value) {
               // 设置顶点运算器
               if (null != value.VertexShader) {
                  //_nativeDevice.VertexShader.Set(value.VertexShader.NativeShader, null);
               }
               // 设置几何体运算器
               if (null != value.GeometryShader) {
                  //_nativeDevice.GeometryShader.Set(value.GeometryShader.NativeShader, null);
               }
               // 设置像素运算器
               if (null != value.PixelShader) {
                  //_nativeDevice.PixelShader.Set(value.PixelShader.NativeShader, null);
               }
               // 设置参数输入层
               _nativeDevice.InputAssembler.InputLayout = value.NativeInputLayout;
            }
            _program = value;
         }
      }

      //============================================================
      public void Clear(float r = 0.0f, float g = 0.0f, float b = 0.0f, float a = 1.0f) {
         _nativeDevice.ClearRenderTargetView(_nativeTargetActive, new Color4(a, r, g, b));
         _nativeDevice.ClearDepthStencilView(_nativeDepth, DepthStencilClearFlags.Depth | DepthStencilClearFlags.Stencil, 1.0f, 0);
      }

      //============================================================
      public void ClearDepth() {
         _nativeDevice.ClearDepthStencilView(_nativeDepth, DepthStencilClearFlags.Depth | DepthStencilClearFlags.Stencil, 1.0f, 0);
      }

      //============================================================
      public void Draw(FDxFaceBuffer faceBuffer) {
         _nativeDevice.Draw(0, 0);
      }

      //============================================================
      public void Present() {
         _nativeSwap.Present(0, PresentFlags.None);
      }

      //============================================================
      public void ConfigureBackBuffer(int width, int height) {
         _size.Set(width, height);
         // 释放渲染目标
         if (null != _nativeTarget) {
            _nativeTarget.Dispose();
            _nativeTarget = null;
         }
         if (null != _nativeDepth) {
            _nativeDepth.Dispose();
            _nativeDepth = null;
         }
         // 改变缓冲大小
         SwapChainDescription description = _nativeSwap.Description;
         _nativeSwap.ResizeBuffers(description.BufferCount, width, height, Format.R8G8B8A8_UNorm, SwapChainFlags.None);
         // 重新创建渲染目标
         CreatePrimaryRenderTarget();
         CreateDepthBuffer();
         // 设置视角
         _nativeViewport = new Viewport(0, 0, width, height, 0.0f, 1.0f);
         _target.NativeViewport = _nativeViewport;
         _nativeTargetActive = _nativeTarget;
         _nativeDevice.OutputMerger.SetTargets(_nativeDepth, _nativeTargetActive);
         _nativeDevice.Rasterizer.SetViewports(_nativeViewport);
      }

      //============================================================
      public void SetRenderTarget() {
         _nativeTargetActive = _nativeTarget;
         _nativeDevice.OutputMerger.SetTargets(_nativeTargetActive);
         _nativeDevice.Rasterizer.SetViewports(_nativeViewport);
      }

      //============================================================
      public void SetRenderTarget(FDxBufferedTexture target) {
         _nativeTargetActive = target.NativeTarget;
         _nativeDevice.OutputMerger.SetTargets(_nativeTargetActive);
         _nativeDevice.Rasterizer.SetViewports(target.NativeViewport);
      }

      //============================================================
      public void SetRenderTarget(FDxBufferedDepth depth, FDxBufferedTexture target) {
         _nativeTargetActive = target.NativeTarget;
         _nativeDevice.OutputMerger.SetTargets(depth.NativeDepth, _nativeTargetActive);
         _nativeDevice.Rasterizer.SetViewports(target.NativeViewport);
      }

      //============================================================
      public void SetRenderTarget(FDxBufferedTexture[] targets) {
         int count = targets.Length;
         RenderTargetView[] targetViews = new RenderTargetView[count];
         Viewport[] viewports = new Viewport[count];
         for(int n = 0; n < count; n++) {
            targetViews[n] = targets[n].NativeTarget;
            viewports[n] = targets[n].NativeViewport;
         }
         _nativeDevice.OutputMerger.SetTargets(_nativeDepth, targetViews);
         _nativeDevice.Rasterizer.SetViewports(viewports);
      }

      //============================================================
      public void SetRenderTarget(FDxBufferedDepth depth, FDxBufferedTexture[] targets) {
         int count = targets.Length;
         RenderTargetView[] targetViews = new RenderTargetView[count];
         Viewport[] viewports = new Viewport[count];
         for(int n = 0; n < count; n++) {
            targetViews[n] = targets[n].NativeTarget;
            viewports[n] = targets[n].NativeViewport;
         }
         _nativeDevice.OutputMerger.SetTargets(depth.NativeDepth, targetViews);
         //_nativeDevice.OutputMerger.SetTargets(targetViews);
         _nativeDevice.Rasterizer.SetViewports(viewports);
      }
   }
}
