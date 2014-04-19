using System.ComponentModel;
using MO.Common.Content;
using MO.Common.IO;
using MO.Common.Lang;
using MO.Design2d.Frame.Common;
using MO.Design2d.Frame;

namespace MO.Design2d.Frame.Common
{
   //============================================================
   // <T>界面数据控件。</T>
   //
   // @history MAOCY 120707
   //============================================================
   public class FUiDataControl : FUiControl
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

      // 新建时是否显示
      protected bool _displayInsert;

      // 更新时是否显示
      protected bool _displayUpdate;

      // 删除时是否显示
      protected bool _displayDelete;

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
      // <T>构造界面数据控件。</T>
      //
      // @param console 控制台
      //============================================================
      public FUiDataControl(FUiFrameConsole console = null)
         : base(console) {
      }

      //============================================================
      // <T>获得或设置数据名称。</T>
      //============================================================
      //[Category("2-数据信息")]
      //[Browsable(true)]
      //[DefaultValue(true)]
      //[Description("数据来源。")]
      //public string DataSource {
      //   get { return _dataSource; }
      //   set { _dataSource = value; }
      //}

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
      // <T>获得或设置新建时是否显示。</T>
      //============================================================
      [Category("2-数据信息")]
      [Browsable(true)]
      [Description("新建时是否显示。")]
      public bool DisplayInsert {
         get { return _displayInsert; }
         set { _displayInsert = value; }
      }

      //============================================================
      // <T>获得或设置更新时是否显示。</T>
      //============================================================
      [Category("2-数据信息")]
      [Browsable(true)]
      [Description("更新时是否显示。")]
      public bool DisplayUpdate {
         get { return _displayUpdate; }
         set { _displayUpdate = value; }
      }

      //============================================================
      // <T>获得或设置删除时是否显示。</T>
      //============================================================
      [Category("2-数据信息")]
      [Browsable(true)]
      [Description("删除时是否显示。")]
      public bool DisplayDelete {
         get { return _displayDelete; }
         set { _displayDelete = value; }
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
      // <T>回收资源。</T>
      //============================================================
      public override void OnFree() {
         base.OnFree();
      }
      
      //============================================================
      // <T>释放资源。</T>
      //============================================================
      public override void OnDispose() {
         base.OnDispose();
      }
   }
}
