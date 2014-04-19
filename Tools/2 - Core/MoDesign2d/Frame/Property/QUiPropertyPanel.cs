using MO.Common.Collection;
using MO.Content2d.Frame.Common;
using MO.Content2d.Frame.Controls;
using System;
using System.Windows.Forms;

namespace MO.Design2d.Frame.Property
{
   //============================================================
   // <T>属性编辑器</T>
   //============================================================
   public partial class QUiPropertyPanel : UserControl
   {
      // 属性变更事件
      public event HUiPropertyChanged PropertyChanged;

      // 资源对象
      protected FRcObject _resource;

      // 属性集合
      protected FDictionary<QUiProperty> _properties = new FDictionary<QUiProperty>();

      protected bool _setup;

      //============================================================
      // <T>构造属性编辑器</T>
      //============================================================
      public QUiPropertyPanel() {
         InitializeComponent();
      }

      //============================================================
      // <T>创建属性编辑器</T>
      //
      // @param typeName 类型
      // @return 属性编辑器
      //============================================================
      public IUiPropertyEditor CreateProperty(string typeName){
         switch(typeName){
            case "Object":
               return new QUiObjectProperty();
            case "Component":
               return new QUiComponentProperty();
            case "Control":
               return new QUiControlProperty();
            case "Frame":
               return new QUiFrameProperty();
            case "Label":
               return new QUiLabelProperty();
            case "Button":
               return new QUiButtonProperty();
         }
         return null;
      }

      //============================================================
      // <T>同步属性编辑器</T>
      //
      // @param typeName 类型
      // @return 属性编辑器
      //============================================================
      public QUiProperty SyncProperty(string typeName, FRcObject resource, bool type) {
         QUiProperty property = _properties.Find(typeName);
         if (property == null) {
            property = new QUiProperty();
            property.Dock = DockStyle.Top;
            IUiPropertyEditor editor = CreateProperty(typeName);
            editor.Setup();
            editor.PropertyChanged += OnPropertyChanged;
            UserControl control = editor as UserControl;
            property.LinkPanel(control);
            _properties.Set(typeName, property);
         }
         if (type) {
            property.LoadResource(resource);
            property.Visible = true;
         } else {
            property.Visible = false;
         }
         return property;
      }

      //============================================================
      // <T>加载资源。</T>
      //
      // @param resource 资源
      //============================================================
      public void LoadResource(FRcObject resource) {
         // 保存以前内容
         if (_resource != null) {
            SaveResource();
         }
         // 配置处理
         if (!_setup) {
            Controls.Add(SyncProperty("Button", null, true));
            Controls.Add(SyncProperty("Label", null, true));
            Controls.Add(SyncProperty("Frame", null, true));
            Controls.Add(SyncProperty("Control", null, true));
            Controls.Add(SyncProperty("Component", null, true));
            Controls.Add(SyncProperty("Object", null, true));
            _setup = true;
         }
         // 加载新内容
         _resource = resource;
         // 开始更新界面
         SuspendLayout();
         // 增加组件
         SyncProperty("Label", resource, resource is FRcLabel);
         SyncProperty("Button", resource, resource is FRcButton);
         // 增加继承
         SyncProperty("Frame", resource, resource is FRcFrame);
         SyncProperty("Control", resource, resource is FRcControl);
         SyncProperty("Component", resource, resource is FRcComponent);
         SyncProperty("Object", resource, resource is FRcObject);
         // 结束更新界面
         ResumeLayout();
      }

      //============================================================
      // <T>保存资源。</T>
      //============================================================
      public void SaveResource() {
         // 保存组件
         if (_resource is FRcLabel) {
            _properties.Find("Label").SaveResource();
         }
         if (_resource is FRcButton) {
            _properties.Find("Button").SaveResource();
         }
         // 保存继承
         if (_resource is FRcFrame) {
            _properties.Find("Frame").SaveResource();
         }
         if (_resource is FRcControl) {
            _properties.Find("Control").SaveResource();
         }
         if (_resource is FRcComponent) {
            _properties.Find("Component").SaveResource();
         }
         if (_resource is FRcObject) {
            _properties.Find("Object").SaveResource();
         }
      }

      //============================================================
      // <T>属性变更事件。</T>
      //============================================================
      public void OnPropertyChanged(object sender, object resource, string name) {
         if (PropertyChanged != null) {
            PropertyChanged(sender, resource, name);
         }
      }
   }
}
