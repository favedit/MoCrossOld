using MO.Content2d.Frame.Common;
using System.ComponentModel;

namespace MO.Design2d.Frame.Common
{
   //============================================================
   // <T>界面组件。</T>
   //
   // @history MAOCY 120707
   //============================================================
   public class FUiComponent : FUiObject
   {
      // 组件集合
      protected FUiComponentCollection _components;

      //============================================================
      // <T>构造界面组件。</T>
      //
      // @param console 控制台
      //============================================================
      public FUiComponent(FUiFrameConsole console = null)
         : base(console) {
      }

      //============================================================
      // <T>获得资源。</T>
      //============================================================
      [Browsable(false)]
      public FRcComponent ComponentResource {
         get { return _resource as FRcComponent; }
      }

      //============================================================
      // <T>判断是否含有子组件。</T>
      //
      // @return 是否含有子组件
      //============================================================
      public bool HasComponment() {
         return (_components != null) ? !_components.IsEmpty() : false;
      }

      //============================================================
      // <T>获得子组件个数。</T>
      //
      // @return 子组件个数
      //============================================================
      [Browsable(false)]
      public int ComponmentCount {
         get { return (_components != null) ? _components.Count : 0; }
      }

      //============================================================
      // <T>获得子组件集合。</T>
      //
      // @return 子组件集合
      //============================================================
      [Browsable(false)]
      public FUiComponentCollection Components {
         get {
            if(_components == null) {
               _components = new FUiComponentCollection();
            }
            return _components;
         }
      }

      //============================================================
      // <T>测试是否可以放入父控件内。</T>
      //
      // @param parent 父组件
      // @return 是否可以放入
      //============================================================
      public virtual bool TestParent(FUiComponent parent) {
         return true;
      }

      //============================================================
      // <T>增加一个子组件。</T>
      //
      // @param componment 子组件
      //============================================================
      public virtual void Push(FUiComponent component) {
         component.Parent = this;
         Components.Push(component);
      }

      //============================================================
      // <T>删除一个组件。</T>
      //
      // @param component 组件
      //============================================================
      public virtual void Remove(FUiComponent component) {
         if(_components != null) {
            _components.Remove(component);
         }
      }

      //============================================================
      // <T>将来源组件插入到目标组件之前。</T>
      //
      // @param sourceComponent 组件
      // @param targetComponent 组件
      //============================================================
      public virtual void InsertBefore(FUiComponent sourceComponent, FUiComponent targetComponent) {
         if(sourceComponent != null) {
            sourceComponent.Parent = this;
         }
         Components.InsertBefore(sourceComponent, targetComponent);
         ComponentResource.InsertBefore(sourceComponent.ComponentResource, targetComponent.ComponentResource);
      }

      //============================================================
      // <T>将来源组件插入到目标组件之后。</T>
      //
      // @param sourceComponent 组件
      // @param targetComponent 组件
      //============================================================
      public virtual void InsertAfter(FUiComponent sourceComponent, FUiComponent targetComponent) {
         if(sourceComponent != null) {
            sourceComponent.Parent = this;
         }
         Components.InsertAfter(sourceComponent, targetComponent);
         ComponentResource.InsertAfter(sourceComponent.ComponentResource, targetComponent.ComponentResource);
      }

      //============================================================
      // <T>配置处理。</T>
      //
      // @param args 参数
      //============================================================
      public override void Setup(SUiSetupArgs args) {
         base.Setup(args);
         // 配置所有组件
         if(_components != null) {
            foreach(FUiComponent componment in _components) {
               componment.Setup(args);
            }
         }
      }

      //============================================================
      // <T>命令处理。</T>
      //
      // @param action 参数
      //============================================================
      public override void Action(SUiAction action) {
         // 命令开始处理
         OnActionBefore(action);
         // 子控件处理
         if(_components != null) {
            foreach(FUiComponent componment in _components) {
               if(componment != null) {
                  componment.Action(action);
               }
            }
         }
         // 命令结束处理
         OnActionAfter(action);
      }

      //============================================================
      // <T>激活子节点处理。</T>
      //
      // @param component 组件
      //============================================================
      public virtual void ActiveChildren(FUiComponent component) {
      }

      //============================================================
      // <T>在父对象中激活自己。</T>
      //
      // @param component 组件
      //============================================================
      protected void ActiveFromParent(FUiComponent component) {
         if(component != null) {
            FUiComponent parent = component.Parent as FUiComponent;
            if(parent != null) {
               parent.ActiveChildren(component);
               ActiveFromParent(parent);
            }
         }
      }

      //============================================================
      // <T>在父对象中激活自己。</T>
      //============================================================
      public void ActiveFromParent() {
         ActiveFromParent(this);
      }

      //============================================================
      // <T>在父对象中删除自己。</T>
      //============================================================
      public void RemoveFromParent() {
         // 资源删除
         ComponentResource.RemoveFromParent();
         // 控件删除
         FUiComponent parent = _parent as FUiComponent;
         if(parent != null) {
            parent.Remove(this);
         }
      }

      //============================================================
      // <T>加载配置信息。</T>
      //
      // @param xconfig 配置节点
      //============================================================
      public override void LoadResource(FRcObject resource) {
         base.LoadResource(resource);
         FRcComponent componentResource = resource as FRcComponent;
         if (componentResource.HasComponment()) {
            // 创建所有子节点
            foreach (FRcComponent childResource in componentResource.Components) {
               FUiComponent child = _console.CreateComponent(childResource.TypeName);
               if (child != null) {
                  // 增加为子节点
                  child.Parent = this;
                  Push(child);
                  // 加载配置信息
                  child.LoadResource(childResource);
               }
            }
         }
      }

      //============================================================
      // <T>回收资源。</T>
      //============================================================
      public override void Free() {
         base.Free();
         if(_components != null) {
            foreach(FUiComponent component in _components) {
               if(component != null) {
                  component.Free();
               }
            }
         }
      }

      //============================================================
      // <T>释放资源。</T>
      //============================================================
      public override void Dispose() {
         base.Dispose();
         if(_components != null) {
            foreach(FUiComponent component in _components) {
               if(component != null) {
                  component.Dispose();
               }
            }
            _components.Clear();
         }
      }
   }
}