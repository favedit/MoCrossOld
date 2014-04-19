using System.ComponentModel;
using MO.Common.Content;
using MO.Common.IO;
using MO.Common.Lang;
using MO.Content2d.Frame.Common;
using MO.Design2d.Face.Console;

namespace MO.Content2d.Frame.Common
{
   //============================================================
   // <T>界面数据控件。</T>
   //
   // @history MAOCY 120707
   //============================================================
   public class FRcDataContainer : FRcContainer
   {
      // 数据来源
      protected string _dataSource;

      // 链接名称
      protected string _connectionName;

      // 父来源
      protected string _parentSource;

      // 数据判断
      protected string _dataWhere;

      // 数据排序
      protected string _dataOrder;

      // 数据分组
      protected string _dataGroup;

      // 是否可查询
      protected bool _queryAble;

      // 是否可新建
      protected bool _insertAble;

      // 是否可更新
      protected bool _updateAble;

      // 是否可删除
      protected bool _deleteAble;

      // 新建时是否检查
      protected bool _validInsert;

      // 更新是否检查
      protected bool _validUpdate;

      // 删除时是否检查
      protected bool _validDelete;

      // 新建逻辑
      protected string _logicInsert;

      // 修改逻辑
      protected string _logicUpdate;

      // 删除逻辑
      protected string _logicDelete;

      //============================================================
      // <T>构造界面数据控件。</T>
      //
      // @param console 控制台
      //============================================================
      public FRcDataContainer(FRcFrameConsole console = null)
         : base(console) {
      }

      //============================================================
      // <T>获得或设置数据名称。</T>
      //============================================================
      [Category("2-数据信息")]
      [Browsable(true)]
      [DefaultValue(true)]
      [Description("数据来源。")]
      public string DataSource {
         get { return _dataSource; }
         set { _dataSource = value; }
      }

      //============================================================
      // <T>获得或设置数据链接名称。</T>
      //============================================================
      [Category("2-数据信息")]
      [Browsable(true)]
      [Description("链接名称。")]
      public string ConnectionName {
         get { return _connectionName; }
         set { _connectionName = value; }
      }

      //============================================================
      // <T>获得或设置父名称。</T>
      //============================================================
      [Category("2-数据信息")]
      [Browsable(true)]
      [Description("父名称。")]
      public string ParentSource {
         get { return _parentSource; }
         set { _parentSource = value; }
      }

      //============================================================
      // <T>获得或设置数据判断。</T>
      //============================================================
      [Category("2-数据信息")]
      [Browsable(true)]
      [Description("数据判断。")]
      public string DataWhere {
         get { return _dataWhere; }
         set { _dataWhere = value; }
      }

      //============================================================
      // <T>获得或设置数据排序。</T>
      //============================================================
      [Category("2-数据信息")]
      [Browsable(true)]
      [Description("数据排序。")]
      public string DataOrder {
         get { return _dataOrder; }
         set { _dataOrder = value; }
      }

      //============================================================
      // <T>获得或设置数据分组。</T>
      //============================================================
      [Category("2-数据信息")]
      [Browsable(true)]
      [Description("数据分组。")]
      public string DataGroup {
         get { return _dataGroup; }
         set { _dataGroup = value; }
      }

      //============================================================
      // <T>获得或设置是否可查询。</T>
      //============================================================
      [Category("2-数据信息")]
      [Browsable(true)]
      [Description("是否可查询。")]
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
      // <T>获得或设置删除是否检查。</T>
      //============================================================
      [Category("2-数据信息")]
      [Browsable(true)]
      [Description("删除时是否检查。")]
      public bool ValidDelete {
         get { return _validDelete; }
         set { _validDelete = value; }
      }

      //============================================================
      // <T>获得或设置新建逻辑。</T>
      //============================================================
      [Category("2-数据信息")]
      [Browsable(true)]
      [Description("新建逻辑。")]
      public string LogicInsert {
         get { return _logicInsert; }
         set { _logicInsert = value; }
      }

      //============================================================
      // <T>获得或设置更新逻辑。</T>
      //============================================================
      [Category("2-数据信息")]
      [Browsable(true)]
      [Description("更新逻辑。")]
      public string LogicUpdate {
         get { return _logicUpdate; }
         set { _logicUpdate = value; }
      }

      //============================================================
      // <T>获得或设置删除逻辑。</T>
      //============================================================
      [Category("2-数据信息")]
      [Browsable(true)]
      [Description("删除逻辑。")]
      public string LogicDelete {
         get { return _logicDelete; }
         set { _logicDelete = value; }
      }

      //============================================================
      // <T>加载设置信息</T>
      //
      // @param xconfig 设置信息
      //============================================================
      public override void OnLoadConfig(FXmlNode xconfig) {
         base.OnLoadConfig(xconfig);
         // 加载属性
         _dataSource = xconfig.Get("data_source", null);
         _connectionName = xconfig.Get("connection_name");
         _parentSource = xconfig.Get("parent_source");
         _dataWhere = xconfig.Get("data_where");
         _dataOrder = xconfig.Get("data_order");
         _dataGroup = xconfig.Get("data_group");
         _queryAble = xconfig.GetBoolean("query_able");
         _insertAble = xconfig.GetBoolean("insert_able");
         _updateAble = xconfig.GetBoolean("update_able");
         _deleteAble = xconfig.GetBoolean("delete_able");
         _validInsert = xconfig.GetBoolean("valid_insert");
         _validUpdate = xconfig.GetBoolean("valid_update");
         _validDelete = xconfig.GetBoolean("valid_delete");
         _logicInsert = xconfig.Get("logic_insert");
         _logicUpdate = xconfig.Get("logic_update");
         _logicDelete = xconfig.Get("logic_delete");
      }

      //============================================================
      // <T>存储设置信息</T>
      //
      // @param xconfig 设置信息
      //============================================================
      public override void OnSaveConfig(FXmlNode xconfig) {
         base.OnSaveConfig(xconfig);
         // 存储属性
         xconfig.Set("data_source",_dataSource);
         xconfig.Set("connection_name",_connectionName);
         xconfig.Set("parent_source",_parentSource);
         xconfig.Set("data_where",_dataWhere);
         xconfig.Set("data_order", _dataOrder);
         xconfig.Set("data_group", _dataGroup);
         xconfig.Set("query_able", _queryAble);
         xconfig.Set("insert_able", _insertAble);
         xconfig.Set("update_able", _updateAble);
         xconfig.Set("delete_able", _deleteAble);
         xconfig.Set("valid_insert", _validInsert);
         xconfig.Set("valid_update", _validUpdate);
         xconfig.Set("valid_delete", _validDelete);
         xconfig.Set("logic_insert", _logicInsert);
         xconfig.Set("logic_update", _logicUpdate);
         xconfig.Set("logic_delete", _logicDelete);
      }

      //============================================================
      // <T>生成标志集合。</T>
      //
      // @return 标志集合
      //============================================================
      public override int MakeSerializeFlags() {
         int flags = base.MakeSerializeFlags();
         // 设置信息
         //if(!RString.IsEmpty(_dataName)){
         //   flags |= (int)EUiFlag.Data;
         //}
         return flags;
      }

      //============================================================
      // <T>序列化内容到输出流。</T>
      //
      // @param output 输出流
      //============================================================
      public override void OnSerialize(IOutput output) {
         base.OnSerialize(output);
         // 存储属性
         //if(!RString.IsEmpty(_dataName)) {
         //   output.WriteString(_dataSource);
         //   output.WriteString(_dataName);
         //}
      }
      
      //============================================================
      // <T>释放资源。</T>
      //============================================================
      public override void OnDispose() {
         base.OnDispose();
      }
   }
}
