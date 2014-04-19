using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using MO.Common.Content;
using MO.Common.IO;
using MO.Common.Lang;
using MO.Design2d.Face.Console;

namespace MO.Content2d.Frame.Common
{
   public class FRcDataColumns : FRcControl
   {
      // 数据名称
      protected string _dataName;

      // 数据名称
      protected EDataType _dataType = EDataType.String;

      // 数据代码
      protected string _dataCode;

      // 是否主键
      protected bool _dataKey;

      // 数据是否必须
      protected bool _dataRequire;

      // 数据内容
      protected string _dataValue;

      // 数据默认值
      protected string _dataDefault;

      // 数据顺序
      protected string _dataOrder;

      // 是否可查询
      protected bool _queryAble;

      // 是否可新建
      protected bool _insertAble;

      // 是否可更新
      protected bool _updateAble;

      // 是否可删除
      protected bool _deleteAble;

      // 搜索时是否显示
      protected bool _displaySearch;

      // 选取时是否显示
      protected bool _displayPicker;

      // 放大时是否显示
      protected bool _displayZoom;

      // 新建时是否编辑
      protected bool _editInsert;

      // 更新时是否编辑
      protected bool _editUpdate;

      // 删除时是否编辑
      protected bool _editDelete;

      // 新建时是否检查
      protected bool _validInsert;

      // 更新时是否检查
      protected bool _validUpdate;

      // 删除时是否检查
      protected bool _validDelete;

      // 是否可搜索
      protected bool _searchAble;

      // 搜索内容
      protected string _searchValue;

      // 搜索类型
      protected EDataSearch _searchTypeCd = EDataSearch.None;

      // 搜索标签
      protected string _searchLabel;

      //============================================================
      // <T>构造界面数据列。</T>
      //
      // @param console 控制台
      //============================================================
      public FRcDataColumns(FRcFrameConsole console = null)
         : base(console) {

      }

      //============================================================
      // <T>获得或设置数据名称。</T>
      //============================================================
      [Category("2-数据信息")]
      [Browsable(true)]
      [DefaultValue(true)]
      [Description("数据名称。")]
      public string DataName {
         get { return _dataName; }
         set { _dataName = value; }
      }

      //============================================================
      // <T>获得或设置数据类型。</T>
      //============================================================
      [Category("2-数据信息")]
      [Browsable(true)]
      [Description("数据类型。")]
      public EDataType DataType {
         get { return _dataType; }
         set { _dataType = value; }
      }

      //============================================================
      // <T>获得或设置数据代码。</T>
      //============================================================
      [Category("2-数据信息")]
      [Browsable(true)]
      [Description("数据代码。")]
      public string DataCode {
         get { return _dataCode; }
         set { _dataCode = value; }
      }

      //============================================================
      // <T>获得或设置是否主键。</T>
      //============================================================
      [Category("2-数据信息")]
      [Browsable(true)]
      [Description("是否主键。")]
      public bool DataKey {
         get { return _dataKey; }
         set { _dataKey = value; }
      }

      //============================================================
      // <T>获得或设置数据是否必须。</T>
      //============================================================
      [Category("2-数据信息")]
      [Browsable(true)]
      [Description("数据是否必须。")]
      public bool DataRequire {
         get { return _dataRequire; }
         set { _dataRequire = value; }
      }

      //============================================================
      // <T>获得或设置数据内容。</T>
      //============================================================
      [Category("2-数据信息")]
      [Browsable(true)]
      [Description("数据内容。")]
      public string DataValue {
         get { return _dataValue; }
         set { _dataValue = value; }
      }

      //============================================================
      // <T>获得或设置数据默认值。</T>
      //============================================================
      [Category("2-数据信息")]
      [Browsable(true)]
      [Description("数据默认值。")]
      public string DataDefault {
         get { return _dataDefault; }
         set { _dataDefault = value; }
      }

      //============================================================
      // <T>获得或设置数据名称。</T>
      //============================================================
      [Category("2-数据排序")]
      [Browsable(true)]
      [Description("数据排序。")]
      public string DataOrder {
         get { return _dataOrder; }
         set { _dataOrder = value; }
      }

      //============================================================
      // <T>获得或设置是否可查询。</T>
      //============================================================
      [Category("2-数据信息")]
      [Browsable(true)]
      [Description("数据是否可查询。")]
      public bool QueryAble {
         get { return _queryAble; }
         set { _queryAble = value; }
      }

      //============================================================
      // <T>获得或设置是否可新建。</T>
      //============================================================
      [Category("2-数据信息")]
      [Browsable(true)]
      [Description("是否可新建。")]
      public bool InsertAble {
         get { return _insertAble; }
         set { _insertAble = value; }
      }

      //============================================================
      // <T>获得或设置是否可更新。</T>
      //============================================================
      [Category("2-数据信息")]
      [Browsable(true)]
      [Description("是否可更新。")]
      public bool UpdateAble {
         get { return _updateAble; }
         set { _updateAble = value; }
      }

      //============================================================
      // <T>获得或设置是否可删除。</T>
      //============================================================
      [Category("2-数据信息")]
      [Browsable(true)]
      [Description("是否可删除。")]
      public bool DeleteAble {
         get { return _deleteAble; }
         set { _deleteAble = value; }
      }

      //============================================================
      // <T>获得或设置搜索时是否显示。</T>
      //============================================================
      [Category("2-数据信息")]
      [Browsable(true)]
      [Description("搜索时是否显示。")]
      public bool DisplaySearch {
         get { return _displaySearch; }
         set { _displaySearch = value; }
      }

      //============================================================
      // <T>获得或设置选取时是否显示。</T>
      //============================================================
      [Category("2-数据信息")]
      [Browsable(true)]
      [Description("选取时是否显示。")]
      public bool DisplayPicker {
         get { return _displayPicker; }
         set { _displayPicker = value; }
      }

      //============================================================
      // <T>获得或设置放大时是否显。</T>
      //============================================================
      [Category("2-数据信息")]
      [Browsable(true)]
      [Description("放大时是否显示。")]
      public bool DisplayZoom {
         get { return _displayZoom; }
         set { _displayZoom = value; }
      }

      //============================================================
      // <T>获得或设置新建时是否编辑。</T>
      //============================================================
      [Category("2-数据信息")]
      [Browsable(true)]
      [Description("新建时是否编辑。")]
      public bool EditInsert {
         get { return _editInsert; }
         set { _editInsert = value; }
      }

      //============================================================
      // <T>获得或设置更新时是否编辑。</T>
      //============================================================
      [Category("2-数据信息")]
      [Browsable(true)]
      [Description("更新时是否编辑。")]
      public bool EditUpdate {
         get { return _editUpdate; }
         set { _editUpdate = value; }
      }

      //============================================================
      // <T>获得或设置删除时是否编辑。</T>
      //============================================================
      [Category("2-数据信息")]
      [Browsable(true)]
      [Description("删除时是否编辑。")]
      public bool EditDelete {
         get { return _editDelete; }
         set { _editDelete = value; }
      }

      //============================================================
      // <T>获得或设置新建时是否检查。</T>
      //============================================================
      [Category("2-数据信息")]
      [Browsable(true)]
      [Description("新建时是否检查。")]
      public bool ValidInsert {
         get { return _validInsert; }
         set { _validInsert = value; }
      }

      //============================================================
      // <T>获得或设置更新时是否检查。</T>
      //============================================================
      [Category("2-数据信息")]
      [Browsable(true)]
      [Description("更新时是否检查。")]
      public bool ValidUpdate {
         get { return _validUpdate; }
         set { _validUpdate = value; }
      }

      //============================================================
      // <T>获得或设置删除时是否检查。</T>
      //============================================================
      [Category("2-数据信息")]
      [Browsable(true)]
      [Description("删除时是否检查。")]
      public bool ValidDelete {
         get { return _validDelete; }
         set { _validDelete = value; }
      }

      //============================================================
      // <T>获得或设置是否可搜索。</T>
      //============================================================
      [Category("2-数据信息")]
      [Browsable(true)]
      [Description("是否可搜索。")]
      public bool SearchAble {
         get { return _searchAble; }
         set { _searchAble = value; }
      }

      //============================================================
      // <T>获得或设置搜索内容。</T>
      //============================================================
      [Category("2-数据信息")]
      [Browsable(true)]
      [Description("搜索内容。")]
      public string SearchValue {
         get { return _searchValue; }
         set { _searchValue = value; }
      }

      //============================================================
      // <T>获得或设置搜索类型。</T>
      //============================================================
      [Category("2-数据信息")]
      [Browsable(true)]
      [Description("搜索类型。")]
      public EDataSearch SearchTypeCd {
         get { return _searchTypeCd; }
         set { _searchTypeCd = value; }
      }

      //============================================================
      // <T>获得或设置搜索标签。</T>
      //============================================================
      [Category("2-数据信息")]
      [Browsable(true)]
      [Description("搜索标签。")]
      public string SearchLabel {
         get { return _searchLabel; }
         set { _searchLabel = value; }
      }

      //============================================================
      // <T>加载设置信息</T>
      //
      // @param xconfig 设置信息
      //============================================================
      public override void OnLoadConfig(FXmlNode xconfig) {
         base.OnLoadConfig(xconfig);
         // 加载属性
         _dataName = xconfig.Get("data_name");
         _dataType = REnum.ToValue<EDataType>(xconfig.Get("data_type"));
         _dataKey = xconfig.GetBoolean("data_key");
         _dataRequire = xconfig.GetBoolean("data_require");
         _dataValue = xconfig.Get("data_value");
         _dataDefault = xconfig.Get("data_default");
         _dataOrder = xconfig.Get("data_order");
         _queryAble = xconfig.GetBoolean("query_able");
         _insertAble = xconfig.GetBoolean("insert_able");
         _updateAble = xconfig.GetBoolean("update_able");
         _deleteAble = xconfig.GetBoolean("delete_able");
         _displaySearch = xconfig.GetBoolean("display_search");
         _displayPicker = xconfig.GetBoolean("display_picker");
         _displayZoom = xconfig.GetBoolean("display_zoom");
         _editInsert = xconfig.GetBoolean("edit_insert");
         _editUpdate = xconfig.GetBoolean("edit_update");
         _editDelete = xconfig.GetBoolean("edit_delete");
         _validInsert = xconfig.GetBoolean("valid_insert");
         _validUpdate = xconfig.GetBoolean("valid_update");
         _validDelete = xconfig.GetBoolean("valid_delete");
         _searchAble = xconfig.GetBoolean("search_able");
         _searchValue = xconfig.Get("search_value");
         _searchTypeCd = REnum.ToValue<EDataSearch>(xconfig.Get("search_type_cd"));
         _searchLabel = xconfig.Get("search_label");
      }

      //============================================================
      // <T>存储设置信息</T>
      //
      // @param xconfig 设置信息
      //============================================================
      public override void OnSaveConfig(FXmlNode xconfig) {
         base.OnSaveConfig(xconfig);
         // 存储属性
         xconfig.Set("data_name", _dataName);
         xconfig.Set("data_type", _dataType.ToString());
         xconfig.Set("data_key", _dataKey);
         xconfig.Set("data_require", _dataRequire);
         xconfig.Set("data_value", _dataValue);
         xconfig.Set("data_default", _dataDefault);
         xconfig.Set("data_order", _dataOrder);
         xconfig.Set("query_able", _queryAble);
         xconfig.Set("insert_able", _insertAble);
         xconfig.Set("update_able", _updateAble);
         xconfig.Set("delete_able", _deleteAble);
         xconfig.Set("display_search", _displaySearch);
         xconfig.Set("display_picker", _displayPicker);
         xconfig.Set("display_zoom", _displayZoom);
         xconfig.Set("edit_insert", _editInsert);
         xconfig.Set("edit_update", _editUpdate);
         xconfig.Set("edit_delete", _editDelete);
         xconfig.Set("valid_insert", _validInsert);
         xconfig.Set("valid_update", _validUpdate);
         xconfig.Set("valid_delete", _validDelete);
         xconfig.Set("search_able", _searchAble);
         xconfig.Set("search_value", _searchValue);
         xconfig.Set("search_type_cd", _searchTypeCd.ToString());
         xconfig.Set("search_label", _searchLabel);
      }

      //============================================================
      // <T>生成标志集合。</T>
      //
      // @return 标志集合
      //============================================================
      public override int MakeSerializeFlags() {
         int flags = base.MakeSerializeFlags();


         return flags;
      }

      //============================================================
      // <T>序列化内容到输出流。</T>
      //
      // @param output 输出流
      //============================================================
      public override void OnSerialize(IOutput output) {
         base.OnSerialize(output);
      }

      //============================================================
      // <T>释放资源。</T>
      //============================================================
      public override void OnDispose() {
         base.OnDispose();
      }

   }
}
