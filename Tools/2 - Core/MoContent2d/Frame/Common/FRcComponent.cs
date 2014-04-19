using MO.Common.Content;
using MO.Common.IO;
using MO.Content2d.Theme;
using MO.Design2d.Face.Console;
using System.ComponentModel;

namespace MO.Content2d.Frame.Common
{
   //============================================================
   // <T>界面组件。</T>
   //
   // @history MAOCY 120707
   //============================================================
   public class FRcComponent : FRcObject
   {
      // 设置对象关联
      protected bool _optionLink;

      // 组件集合
      protected FRcComponentCollection _components;

      //============================================================
      // <T>构造界面组件。</T>
      //
      // @param console 控制台
      //============================================================
      public FRcComponent(FRcFrameConsole console = null)
         : base(console) {
      }

      //============================================================
      // <T>获得或设置鼠标双击事件。</T>
      //============================================================
      public bool OptionLink {
         get { return _optionLink; }
         set { _optionLink = value; }
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
      public FRcComponentCollection Components {
         get {
            if(_components == null) {
               _components = new FRcComponentCollection();
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
      public virtual bool TestParent(FRcComponent parent) {
         return true;
      }

      //============================================================
      // <T>增加一个子组件。</T>
      //
      // @param componment 子组件
      //============================================================
      public virtual void Push(FRcComponent component) {
         component.Parent = this;
         Components.Push(component);
      }

      //============================================================
      // <T>删除一个组件。</T>
      //
      // @param component 组件
      //============================================================
      public virtual void Remove(FRcComponent component) {
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
      public virtual void InsertBefore(FRcComponent sourceComponent, FRcComponent targetComponent) {
         if(sourceComponent != null) {
            sourceComponent.Parent = this;
         }
         Components.InsertBefore(sourceComponent, targetComponent);
      }

      //============================================================
      // <T>将来源组件插入到目标组件之后。</T>
      //
      // @param sourceComponent 组件
      // @param targetComponent 组件
      //============================================================
      public virtual void InsertAfter(FRcComponent sourceComponent, FRcComponent targetComponent) {
         if(sourceComponent != null) {
            sourceComponent.Parent = this;
         }
         Components.InsertAfter(sourceComponent, targetComponent);
      }

      //============================================================
      // <T>激活子节点处理。</T>
      //
      // @param component 组件
      //============================================================
      public virtual void ActiveChildren(FRcComponent component) {
      }

      //============================================================
      // <T>在父对象中激活自己。</T>
      //
      // @param component 组件
      //============================================================
      protected void ActiveFromParent(FRcComponent component) {
         if(component != null) {
            FRcComponent parent = component.Parent as FRcComponent;
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
         FRcComponent parent = _parent as FRcComponent;
         if(parent != null) {
            parent.Remove(this);
         }
      }

      //============================================================
      // <T>加载样式属性。</T>
      //============================================================
      public override void LoadStyleProperty() {
         // 加载父样式信息
         base.LoadStyleProperty();
         // 加载样式信息
         FTplThemeStyle style = RContent2dManager.ThemeConsole.FindActiveStyle("frame.component");
         if (style != null) {
         }
      }

      //============================================================
      // <T>加载样式内容。</T>
      //============================================================
      public override void LoadStyleValue() {
         // 加载父样式信息
         base.LoadStyleValue();
         // 加载样式信息
         FTplThemeStyle style = RContent2dManager.ThemeConsole.FindActiveStyle("frame.component");
         if (style != null) {
         }
      }

      //============================================================
      // <T>加载设置信息</T>
      //
      // @param xconfig 设置信息
      //============================================================
      public override void OnLoadConfig(FXmlNode xconfig) {
         base.OnLoadConfig(xconfig);
         // 加载配置
         _optionLink = xconfig.GetBoolean("option_link", _optionLink);
      }

      //============================================================
      // <T>存储设置信息</T>
      //
      // @param xconfig 设置信息
      //============================================================
      public override void OnSaveConfig(FXmlNode xconfig) {
         base.OnSaveConfig(xconfig);
         // 存储配置
         xconfig.SetNvl("option_link", _optionLink);
      }

      //============================================================
      // <T>加载配置信息。</T>
      //
      // @param xconfig 配置节点
      //============================================================
      public override void LoadConfig(FXmlNode xconfig) {
         base.LoadConfig(xconfig);
         if(xconfig.HasNode()) {
            // 创建所有子节点
            foreach(FXmlNode xnode in xconfig.Nodes) {
               string nodeName = xnode.Name;
               FRcComponent component = _console.CreateComponent(nodeName);
               if(component != null) {
                  // 增加为子节点
                  component.Parent = this;
                  Push(component);
                  // 加载配置信息
                  component.LoadConfig(xnode);
               }
            }
         }
      }

      //============================================================
      // <T>加载配置信息。</T>
      //
      // @param xconfig 配置节点
      //============================================================
      public override void SaveConfig(FXmlNode xconfig) {
         base.SaveConfig(xconfig);
         if(_components != null) {
            // 创建所有子节点
            foreach(FRcComponent component in _components) {
               FXmlNode xnode = xconfig.CreateNode(component.TypeName);
               component.SaveConfig(xnode);
            }
         }
      }
      
      //============================================================
      // <T>测试子组件是否需要序列化。</T>
      //
      // @param component 子组件
      //============================================================
      public virtual bool TestSerializeComponent(FRcComponent component) {
         if(!component.OptionValid){
            return false;
         }
         return true;
      }

      //============================================================
      // <T>获得有效子组件个数。</T>
      //
      // @return 子组件个数
      //============================================================
      public int CalculateValidCount() {
         int count = 0;
         if (_components != null) {
            foreach (FRcComponent component in _components) {
               if (TestSerializeComponent(component)) {
                  count++;
               }
            }
         }
         return count;
      }

      //============================================================
      // <T>生成标志集合。</T>
      //
      // @return 标志集合
      //============================================================
      public override int MakeSerializeFlags() {
         int flags = base.MakeSerializeFlags();
         // 设置信息
         if (_optionLink) {
            flags |= (int)ERcFlag.Link;
         }
         return flags;
      }

      //============================================================
      // <T>序列化内容到输出流。</T>
      //
      // @param output 输出流
      //============================================================
      public override void Serialize(IOutput output) {
         base.Serialize(output);
         // 序列化所有子节点
         int count = CalculateValidCount();
         output.WriteInt16((short)count);
         if (_components != null) {
            foreach (FRcComponent component in _components) {
               if (TestSerializeComponent(component)) {
                  component.Serialize(output);
               }
            }
         }
      }

      //============================================================
      // <T>从输入流中反序列化内容。</T>
      //
      // @param input 输入流
      //============================================================
      public override void Unserialize(IInput input) {
         base.Unserialize(input);
         // 创建所有子节点
         int count = input.ReadInt16();
         for(int n = 0; n < count; n++) {
            string typeName = input.ReadString();
            FRcComponent component = _console.CreateComponent(typeName);
            component.Unserialize(input);
         }
      }

      //============================================================
      // <T>释放资源。</T>
      //============================================================
      public override void Dispose() {
         base.Dispose();
         if(_components != null) {
            foreach(FRcComponent component in _components) {
               if(component != null) {
                  component.Dispose();
               }
            }
            _components.Clear();
         }
      }
   }
}