using MO.Common.Collection;
using MO.Common.Content;
using MO.Common.IO;
using MO.Common.Lang;
using MO.Content2d.Theme;
using MO.Design2d.Face.Console;
using System.ComponentModel;

namespace MO.Content2d.Frame.Common
{
   //============================================================
   // <T>界面对象。</T>
   //
   // @history MAOCY 120707
   //============================================================
   [DefaultProperty("Name")]
   public class FRcObject : FObject
   {
      // 控制台
      protected FRcFrameConsole _console;

      // 父对象
      protected FRcObject _parent;

      // 配置节点
      protected FXmlNode _config;

      // 类型名称
      protected string _typeName;

      // 样式名称
      protected string _styleName;

      // 类名称
      protected string _className;

      // 配置有效性
      protected bool _optionValid = true;

      // 名称
      protected string _name;

      // 标签
      protected string _label;

      // 提示信息
      protected string _hint;

      // 注释
      protected string _note;

      // 属性集合
      protected FProperties _properties = new FProperties();

      // 节点数据
      protected object _linkerNode;

      //============================================================
      // <T>构造界面对象。</T>
      //
      // @param console 控制台
      //============================================================
      public FRcObject(FRcFrameConsole console = null) {
         _console = console;
      }

      //============================================================
      // <T>获得或设置控制台。</T>
      //============================================================
      public FRcFrameConsole Console {
         get { return _console; }
         set { _console = value; }
      }

      //============================================================
      // <T>获得或设置父对象。</T>
      //============================================================
      public FRcObject Parent {
         get { return _parent; }
         set { _parent = value; }
      }

      //============================================================
      // <T>获得或设置配置节点。</T>
      //============================================================
      [Browsable(false)]
      public FXmlNode Config {
         get { return _config; }
         set { _config = value; }
      }

      //============================================================
      // <T>获得类型名称。</T>
      //============================================================
      public string TypeName {
         get { return _typeName; }
      }

      //============================================================
      // <T>获得类型名称。</T>
      //============================================================
      public string ClassName {
         get { return _className; }
         set { _className = value; }
      }

      //============================================================
      // <T>获得或设置有效性。</T>
      //============================================================
      public bool OptionValid {
         get { return _optionValid; }
         set { _optionValid = value; }
      }

      //============================================================
      // <T>判断是否指定名称。</T>
      //
      // @return 是否指定名称
      //============================================================
      public bool IsName(string name) {
         return (_name == name);
      }

      //============================================================
      // <T>获得或设置名称。</T>
      //============================================================
      public string Name {
         get { return _name; }
         set { _name = value; }
      }

      //============================================================
      // <T>获得或设置标签。</T>
      //============================================================
      public string Label {
         get { return _label; }
         set { _label = value; }
      }

      //============================================================
      // <T>获得或设置背景提示信息。</T>
      //============================================================
      public string Hint {
         get { return _hint; }
         set { _hint = value; }
      }
      
      //============================================================
      // <T>获得或设置注释。</T>
      //============================================================
      public string Note {
         get { return _note; }
         set { _note = value; }
      }

      //============================================================
      // <T>获得或设置注释。</T>
      //============================================================
      public FProperties Properties {
         get { return _properties; }
         set { _properties.Assign(value); }
      }

      //============================================================
      // <T>获得或设置附加数据。</T>
      //============================================================
      [Browsable(false)]
      public object LinkerNode {
         get { return _linkerNode; }
         set { _linkerNode = value; }
      }

      //============================================================
      // <T>获得格式化内容。</T>
      //
      // @return 格式化内容
      //============================================================
      public virtual string Format() {
         string result = _name;
         if(!RString.IsEmpty(_label)) {
            result += " [" + _label + "]";
         } else if(!RString.IsEmpty(_hint)) {
            result += " [" + _hint + "]";
         }
         if(RString.IsEmpty(result)){
            result = _typeName;
         }
         return result;
      }

      //============================================================
      // <T>接收对象内容</T>
      //
      // @param value 对象
      //============================================================
      public virtual void Assign(FRcObject value) {
         _name = value._name;
         _label = value._label;
         _hint = value._hint;
         _note = value._note;
      }

      //============================================================
      // <T>加载样式属性。</T>
      //============================================================
      public virtual void LoadStyleProperty() {
         FTplThemeStyle style = RContent2dManager.ThemeConsole.FindActiveStyle("frame.obejct");
         if (style != null) {
         }
      }

      //============================================================
      // <T>加载样式内容。</T>
      //============================================================
      public virtual void LoadStyleValue() {
         FTplThemeStyle style = RContent2dManager.ThemeConsole.FindActiveStyle("frame.obejct");
         if (style != null) {
         }
      }

      //============================================================
      // <T>加载样式信息。</T>
      //============================================================
      public virtual void LoadStyle() {
         LoadStyleProperty();
         LoadStyleValue();
      }
      
      //============================================================
      // <T>加载配置信息。</T>
      //
      // @param xconfig 配置节点
      //============================================================
      public virtual void OnLoadConfig(FXmlNode xconfig) {
         _className = xconfig.Get("class", _className);
         _name = xconfig.Get("name", _name);
         _label = xconfig.Get("label", _label);
         _optionValid = xconfig.GetBoolean("option_valid", _optionValid);
         _hint = xconfig.Get("hint", _hint);
         _note = xconfig.Get("note", _note);
         // 读取属性集合
         if(xconfig.Contains("properties")){
            _properties.Parse(xconfig.Get("properties"));
         }
      }

      //============================================================
      // <T>保存配置信息。</T>
      //
      // @param xconfig 配置节点
      //============================================================
      public virtual void OnSaveConfig(FXmlNode xconfig) {
         xconfig.Name = _typeName;
         xconfig.SetNvl("class", _className);
         xconfig.SetNvl("option_valid", _optionValid);
         xconfig.SetNvl("name", _name);
         xconfig.SetNvl("label", _label);
         xconfig.SetNvl("hint", _hint);
         xconfig.SetNvl("note", _note);
         // 保存属性集合
         if(!_properties.IsEmpty()) {
            xconfig.SetNvl("properties", _properties.Pack());
         }
      }

      //============================================================
      // <T>加载配置信息。</T>
      //
      // @param xconfig 配置节点
      //============================================================
      public virtual void LoadConfig(FXmlNode xconfig) {
         OnLoadConfig(xconfig);
      }

      //============================================================
      // <T>加载配置信息。</T>
      //
      // @param xconfig 配置节点
      //============================================================
      public virtual void SaveConfig(FXmlNode xconfig) {
         OnSaveConfig(xconfig);
      }

      //============================================================
      // <T>生成标志集合。</T>
      //
      // @return 标志集合
      //============================================================
      public virtual int MakeSerializeFlags() {
         int flags = 0;
         if(!_properties.IsEmpty()) {
            flags |= (int)ERcFlag.Property;
         }
         return flags;
      }

      //============================================================
      // <T>序列化内容到输出流。</T>
      //
      // @param output 输出流
      //============================================================
      public virtual void OnSerialize(IOutput output) {
         int flags = MakeSerializeFlags();
         output.WriteInt16((short)REnum.ToValue<ERcComponent>(_typeName));
         output.WriteString(_className);
         output.WriteUint32((uint)flags);
         output.WriteString(_name);
         output.WriteWideString(_label);
         // 保存属性
         //if(!_properties.IsEmpty()) {
         //   _properties.Serialize(output);
         //}
      }

      //============================================================
      // <T>从输入流中反序列化内容。</T>
      //
      // @param input 输入流
      //============================================================
      public virtual void OnUnserialize(IInput input) {
         _name = input.ReadString();
         _label = input.ReadUTFString();
      }

      //============================================================
      // <T>序列化内容到输出流。</T>
      //
      // @param output 输出流
      //============================================================
      public virtual void Serialize(IOutput output) {
         OnSerialize(output);
      }

      //============================================================
      // <T>从输入流中反序列化内容。</T>
      //
      // @param input 输入流
      //============================================================
      public virtual void Unserialize(IInput input) {
         OnUnserialize(input);
      }

      //============================================================
      // <T>释放资源。</T>
      //============================================================
      public virtual void OnDispose() {
      }

      //============================================================
      // <T>释放资源。</T>
      //============================================================
      public virtual void Dispose() {
         OnDispose();
      }
   }
}
