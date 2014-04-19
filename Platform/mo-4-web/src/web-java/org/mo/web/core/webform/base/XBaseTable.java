package org.mo.web.core.webform.base;

import org.mo.com.lang.FMultiString;
import org.mo.com.lang.RBoolean;
import org.mo.com.lang.RString;
import org.mo.com.lang.face.AName;
import org.mo.com.xml.EXmlConfig;
import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.FXmlObject;
import org.mo.com.xml.IXmlObject;
import org.mo.web.core.webform.control.XControlFace;
import org.mo.web.core.webform.control.XDatasetFace;
import org.mo.web.core.webform.control.XGridFace;

//============================================================
// <T>列表控件对象的XML节点基类。</T>
//============================================================
public abstract class XBaseTable
      extends FXmlObject
      implements
         XControlFace,
         XDatasetFace,
         XGridFace
{
   // 名称定义
   public static final String NAME = "Table";

   //============================================================
   // <T>获得名称定义。</T>
   //
   // @return 名称定义
   //============================================================
   public String name(){
      return NAME;
   }

   //============================================================
   // <T>判断是否指定名称。</T>
   //
   // @param name 名称
   // @return 是否指定
   //============================================================
   public static boolean isName(String name){
      return NAME.equals(name);
   }

   //============================================================
   // <T>判断是否指定实例。</T>
   //
   // @param xobject 对象
   // @return 是否指定
   //============================================================
   public static boolean isInstance(IXmlObject xobject){
      return NAME.equals(xobject.name());
   }

   // 名称的名称定义
   public static final String PTY_NAME = "name";

   // 标签的名称定义
   public static final String PTY_LABEL = "label";

   // 有效性的名称定义
   public static final String PTY_IS_VALID = "is_valid";

   // 注释的名称定义
   public static final String PTY_NOTE = "note";

   // 显示模式的名称定义
   public static final String PTY_DISP_MODE = "disp_mode";

   // 显示设置的名称定义
   public static final String PTY_DISP_CONFIG = "disp_config";

   // 查询的名称定义
   public static final String PTY_DISP_FETCH = "disp_fetch";

   // 搜索显示的名称定义
   public static final String PTY_DISP_SEARCH = "disp_search";

   // 选取显示的名称定义
   public static final String PTY_DISP_PICKER = "disp_picker";

   // LOV显示的名称定义
   public static final String PTY_DISP_LOV = "disp_lov";

   // 放大显示的名称定义
   public static final String PTY_DISP_ZOOM = "disp_zoom";

   // 列表显示的名称定义
   public static final String PTY_DISP_LIST = "disp_list";

   // 显示显示的名称定义
   public static final String PTY_DISP_DISPLAY = "disp_display";

   // 新建显示的名称定义
   public static final String PTY_DISP_INSERT = "disp_insert";

   // 更新显示的名称定义
   public static final String PTY_DISP_UPDATE = "disp_update";

   // 删除显示的名称定义
   public static final String PTY_DISP_DELETE = "disp_delete";

   // 自动调整的名称定义
   public static final String PTY_DISP_AUTO = "disp_auto";

   // 可变大小的名称定义
   public static final String PTY_DISP_SIZE = "disp_size";

   // 可被拖拽的名称定义
   public static final String PTY_DISP_DRAG = "disp_drag";

   // 印刷显示的名称定义
   public static final String PTY_DISP_PRINT = "disp_print";

   // 左空余的名称定义
   public static final String PTY_PAD_LEFT = "pad_left";

   // 上空余的名称定义
   public static final String PTY_PAD_TOP = "pad_top";

   // 下空余的名称定义
   public static final String PTY_PAD_BOTTOM = "pad_bottom";

   // 右空余的名称定义
   public static final String PTY_PAD_RIGHT = "pad_right";

   // 左位置的名称定义
   public static final String PTY_LEFT = "left";

   // 上位置的名称定义
   public static final String PTY_TOP = "top";

   // 右位置的名称定义
   public static final String PTY_RIGHT = "right";

   // 下位置的名称定义
   public static final String PTY_BOTTOM = "bottom";

   // 宽度的名称定义
   public static final String PTY_WIDTH = "width";

   // 高度的名称定义
   public static final String PTY_HEIGHT = "height";

   // 无回行的名称定义
   public static final String PTY_NOWRAP = "nowrap";

   // 状态栏提示的名称定义
   public static final String PTY_HINT = "hint";

   // 控件样式的名称定义
   public static final String PTY_STYLE_REFER = "style_refer";

   // 标签样式的名称定义
   public static final String PTY_STYLE_LABEL = "style_label";

   // 编辑样式的名称定义
   public static final String PTY_STYLE_EDIT = "style_edit";

   // 版本的名称定义
   public static final String PTY_VERSION = "version";

   // 数据集名称的名称定义
   public static final String PTY_DATASET = "dataset";

   // 数据来源的名称定义
   public static final String PTY_DATASET_FROM = "dataset_from";

   // 数据处理的名称定义
   public static final String PTY_DATASET_PROCEDURE = "dataset_procedure";

   // 数据服务的名称定义
   public static final String PTY_DATASET_SERVICE = "dataset_service";

   // 数据搜索的名称定义
   public static final String PTY_DATASET_SEARCH = "dataset_search";

   // 数据分组的名称定义
   public static final String PTY_DATASET_GROUP = "dataset_group";

   // 数据排序的名称定义
   public static final String PTY_DATASET_ORDER = "dataset_order";

   // 数据参数的名称定义
   public static final String PTY_DATASET_PARAMETERS = "dataset_parameters";

   // 操作数据准备的名称定义
   public static final String PTY_DATASET_PREPARE = "dataset_prepare";

   // 新建数据参数的名称定义
   public static final String PTY_DATASET_INSERT_PARAMETERS = "dataset_insert_parameters";

   // 新建数据的名称定义
   public static final String PTY_DATASET_INSERT = "dataset_insert";

   // 更新数据参数的名称定义
   public static final String PTY_DATASET_UPDATE_PARAMETERS = "dataset_update_parameters";

   // 更新数据的名称定义
   public static final String PTY_DATASET_UPDATE = "dataset_update";

   // 删除数据参数的名称定义
   public static final String PTY_DATASET_DELETE_PARAMETERS = "dataset_delete_parameters";

   // 删除数据的名称定义
   public static final String PTY_DATASET_DELETE = "dataset_delete";

   // 编辑模式的名称定义
   public static final String PTY_EDIT_MODE = "edit_mode";

   // 编辑设置的名称定义
   public static final String PTY_EDIT_CONFIG = "edit_config";

   // 复制编辑可的名称定义
   public static final String PTY_EDIT_COPY = "edit_copy";

   // 查询编辑可的名称定义
   public static final String PTY_EDIT_FETCH = "edit_fetch";

   // 搜索编辑可的名称定义
   public static final String PTY_EDIT_SEARCH = "edit_search";

   // 选取编辑可的名称定义
   public static final String PTY_EDIT_PICKER = "edit_picker";

   // 放大编辑可的名称定义
   public static final String PTY_EDIT_ZOOM = "edit_zoom";

   // 打印编辑可的名称定义
   public static final String PTY_EDIT_PRINT = "edit_print";

   // 操作编辑可的名称定义
   public static final String PTY_EDIT_ACTION = "edit_action";

   // 新建编辑可的名称定义
   public static final String PTY_EDIT_INSERT = "edit_insert";

   // 修改编辑可的名称定义
   public static final String PTY_EDIT_UPDATE = "edit_update";

   // 删除编辑可的名称定义
   public static final String PTY_EDIT_DELETE = "edit_delete";

   // 检查权限的名称定义
   public static final String PTY_VALID_ACCESS = "valid_access";

   // 新建检查可的名称定义
   public static final String PTY_VALID_INSERT = "valid_insert";

   // 更新检查可的名称定义
   public static final String PTY_VALID_UPDATE = "valid_update";

   // 删除检查可的名称定义
   public static final String PTY_VALID_DELETE = "valid_delete";

   // 新建处理的名称定义
   public static final String PTY_ACTION_INSERT = "action_insert";

   // 更新处理的名称定义
   public static final String PTY_ACTION_UPDATE = "action_update";

   // 删除处理的名称定义
   public static final String PTY_ACTION_DELETE = "action_delete";

   // 操作准备的名称定义
   public static final String PTY_ACTION_PREPARE = "action_prepare";

   // 工具栏显示的名称定义
   public static final String PTY_DISP_TOOLBAR = "disp_toolbar";

   // 逻辑名称的名称定义
   public static final String PTY_LG_NAME = "lg_name";

   // 面板权限的名称定义
   public static final String PTY_PANEL_ACCESS = "panel_access";

   // 行工具栏的名称定义
   public static final String PTY_DISP_ROWBAR = "disp_rowbar";

   // 显示标题栏的名称定义
   public static final String PTY_PANEL_TITLE = "panel_title";

   // 显示表头栏的名称定义
   public static final String PTY_PANEL_HEAD = "panel_head";

   // 显示搜索框栏的名称定义
   public static final String PTY_PANEL_SEARCH = "panel_search";

   // 显示提示栏的名称定义
   public static final String PTY_PANEL_HINT = "panel_hint";

   // 合计的名称定义
   public static final String PTY_PANEL_TOTAL = "panel_total";

   // 准备查询的名称定义
   public static final String PTY_QUERY_PREPARE = "query_prepare";

   // 是否已关联详细表单的名称定义
   public static final String PTY_FORM_LINKED = "form_linked";

   // 传输方式的名称定义
   public static final String PTY_FORM_CUSTOM = "form_custom";

   // 传输参数的名称定义
   public static final String PTY_FORM_PARAMETER = "form_parameter";

   // 显示行选中的名称定义
   public static final String PTY_DISP_SELECTED = "disp_selected";

   // 命令的名称定义
   public static final String PTY_ACTION = "action";

   // 表单名称的名称定义
   public static final String PTY_FORM_NAME = "form_name";

   // 分页大小的名称定义
   public static final String PTY_PAGE_SIZE = "page_size";

   // 父名称的名称定义
   public static final String PTY_PARENT_NAME = "parent_name";

   // 表单引用的名称定义
   public static final String PTY_FORM_REFER = "form_refer";

   // 表单转移条件的名称定义
   public static final String PTY_FORM_REFER_WHERE = "form_refer_where";

   // 表单链接的名称定义
   public static final String PTY_FORM_LINK = "form_link";

   // 表单查询的名称定义
   public static final String PTY_FORM_SEARCH = "form_search";

   // 表单排序的名称定义
   public static final String PTY_FORM_ORDER = "form_order";

   // 显示行数的名称定义
   public static final String PTY_DISP_COUNT = "disp_count";

   // 名称的定义
   @AName("name")
   protected String _name;

   // 标签的定义
   @AName("label")
   protected FMultiString _label = new FMultiString();

   // 有效性的定义
   @AName("is_valid")
   protected Boolean _isValid = Boolean.FALSE;

   // 注释的定义
   @AName("note")
   protected FMultiString _note = new FMultiString();

   // 查询的定义
   @AName("disp_fetch")
   protected Boolean _dispFetch = Boolean.FALSE;

   // 搜索显示的定义
   @AName("disp_search")
   protected Boolean _dispSearch = Boolean.FALSE;

   // 选取显示的定义
   @AName("disp_picker")
   protected Boolean _dispPicker = Boolean.FALSE;

   // LOV显示的定义
   @AName("disp_lov")
   protected Boolean _dispLov = Boolean.FALSE;

   // 放大显示的定义
   @AName("disp_zoom")
   protected Boolean _dispZoom = Boolean.FALSE;

   // 列表显示的定义
   @AName("disp_list")
   protected Boolean _dispList = Boolean.FALSE;

   // 显示显示的定义
   @AName("disp_display")
   protected Boolean _dispDisplay = Boolean.FALSE;

   // 新建显示的定义
   @AName("disp_insert")
   protected Boolean _dispInsert = Boolean.FALSE;

   // 更新显示的定义
   @AName("disp_update")
   protected Boolean _dispUpdate = Boolean.FALSE;

   // 删除显示的定义
   @AName("disp_delete")
   protected Boolean _dispDelete = Boolean.FALSE;

   // 自动调整的定义
   @AName("disp_auto")
   protected Boolean _dispAuto = Boolean.FALSE;

   // 可变大小的定义
   @AName("disp_size")
   protected Boolean _dispSize = Boolean.FALSE;

   // 可被拖拽的定义
   @AName("disp_drag")
   protected Boolean _dispDrag = Boolean.FALSE;

   // 印刷显示的定义
   @AName("disp_print")
   protected Boolean _dispPrint = Boolean.FALSE;

   // 左空余的定义
   @AName("pad_left")
   protected String _padLeft;

   // 上空余的定义
   @AName("pad_top")
   protected String _padTop;

   // 下空余的定义
   @AName("pad_bottom")
   protected String _padBottom;

   // 右空余的定义
   @AName("pad_right")
   protected String _padRight;

   // 左位置的定义
   @AName("left")
   protected String _left;

   // 上位置的定义
   @AName("top")
   protected String _top;

   // 右位置的定义
   @AName("right")
   protected String _right;

   // 下位置的定义
   @AName("bottom")
   protected String _bottom;

   // 宽度的定义
   @AName("width")
   protected String _width;

   // 高度的定义
   @AName("height")
   protected String _height;

   // 无回行的定义
   @AName("nowrap")
   protected Boolean _nowrap = Boolean.FALSE;

   // 状态栏提示的定义
   @AName("hint")
   protected String _hint;

   // 控件样式的定义
   @AName("style_refer")
   protected String _styleRefer;

   // 标签样式的定义
   @AName("style_label")
   protected String _styleLabel;

   // 编辑样式的定义
   @AName("style_edit")
   protected String _styleEdit;

   // 版本的定义
   @AName("version")
   protected String _version;

   // 数据集名称的定义
   @AName("dataset")
   protected String _dataset;

   // 数据来源的定义
   @AName("dataset_from")
   protected String _datasetFrom;

   // 数据处理的定义
   @AName("dataset_procedure")
   protected String _datasetProcedure;

   // 数据服务的定义
   @AName("dataset_service")
   protected String _datasetService;

   // 数据搜索的定义
   @AName("dataset_search")
   protected String _datasetSearch;

   // 数据分组的定义
   @AName("dataset_group")
   protected String _datasetGroup;

   // 数据排序的定义
   @AName("dataset_order")
   protected String _datasetOrder;

   // 数据参数的定义
   @AName("dataset_parameters")
   protected String _datasetParameters;

   // 操作数据准备的定义
   @AName("dataset_prepare")
   protected String _datasetPrepare;

   // 新建数据参数的定义
   @AName("dataset_insert_parameters")
   protected String _datasetInsertParameters;

   // 新建数据的定义
   @AName("dataset_insert")
   protected String _datasetInsert;

   // 更新数据参数的定义
   @AName("dataset_update_parameters")
   protected String _datasetUpdateParameters;

   // 更新数据的定义
   @AName("dataset_update")
   protected String _datasetUpdate;

   // 删除数据参数的定义
   @AName("dataset_delete_parameters")
   protected String _datasetDeleteParameters;

   // 删除数据的定义
   @AName("dataset_delete")
   protected String _datasetDelete;

   // 复制编辑可的定义
   @AName("edit_copy")
   protected Boolean _editCopy = Boolean.FALSE;

   // 查询编辑可的定义
   @AName("edit_fetch")
   protected Boolean _editFetch = Boolean.FALSE;

   // 搜索编辑可的定义
   @AName("edit_search")
   protected Boolean _editSearch = Boolean.FALSE;

   // 选取编辑可的定义
   @AName("edit_picker")
   protected Boolean _editPicker = Boolean.FALSE;

   // 放大编辑可的定义
   @AName("edit_zoom")
   protected Boolean _editZoom = Boolean.FALSE;

   // 打印编辑可的定义
   @AName("edit_print")
   protected Boolean _editPrint = Boolean.FALSE;

   // 操作编辑可的定义
   @AName("edit_action")
   protected Boolean _editAction = Boolean.FALSE;

   // 新建编辑可的定义
   @AName("edit_insert")
   protected Boolean _editInsert = Boolean.FALSE;

   // 修改编辑可的定义
   @AName("edit_update")
   protected Boolean _editUpdate = Boolean.FALSE;

   // 删除编辑可的定义
   @AName("edit_delete")
   protected Boolean _editDelete = Boolean.FALSE;

   // 新建检查可的定义
   @AName("valid_insert")
   protected Boolean _validInsert = Boolean.FALSE;

   // 更新检查可的定义
   @AName("valid_update")
   protected Boolean _validUpdate = Boolean.FALSE;

   // 删除检查可的定义
   @AName("valid_delete")
   protected Boolean _validDelete = Boolean.FALSE;

   // 新建处理的定义
   @AName("action_insert")
   protected String _actionInsert;

   // 更新处理的定义
   @AName("action_update")
   protected String _actionUpdate;

   // 删除处理的定义
   @AName("action_delete")
   protected String _actionDelete;

   // 操作准备的定义
   @AName("action_prepare")
   protected String _actionPrepare;

   // 工具栏显示的定义
   @AName("disp_toolbar")
   protected Boolean _dispToolbar = Boolean.FALSE;

   // 逻辑名称的定义
   @AName("lg_name")
   protected String _lgName;

   // 行工具栏的定义
   @AName("disp_rowbar")
   protected String _dispRowbar;

   // 显示标题栏的定义
   @AName("panel_title")
   protected String _panelTitle;

   // 显示表头栏的定义
   @AName("panel_head")
   protected String _panelHead;

   // 显示搜索框栏的定义
   @AName("panel_search")
   protected String _panelSearch;

   // 显示提示栏的定义
   @AName("panel_hint")
   protected String _panelHint;

   // 合计的定义
   @AName("panel_total")
   protected String _panelTotal;

   // 准备查询的定义
   @AName("query_prepare")
   protected String _queryPrepare;

   // 是否已关联详细表单的定义
   @AName("form_linked")
   protected Boolean _formLinked = Boolean.FALSE;

   // 传输方式的定义
   @AName("form_custom")
   protected String _formCustom;

   // 传输参数的定义
   @AName("form_parameter")
   protected String _formParameter;

   // 显示行选中的定义
   @AName("disp_selected")
   protected String _dispSelected;

   // 命令的定义
   @AName("action")
   protected String _action;

   // 表单名称的定义
   @AName("form_name")
   protected String _formName;

   // 分页大小的定义
   @AName("page_size")
   protected String _pageSize;

   // 父名称的定义
   @AName("parent_name")
   protected String _parentName;

   // 表单引用的定义
   @AName("form_refer")
   protected String _formRefer;

   // 表单转移条件的定义
   @AName("form_refer_where")
   protected String _formReferWhere;

   // 表单链接的定义
   @AName("form_link")
   protected String _formLink;

   // 表单查询的定义
   @AName("form_search")
   protected String _formSearch;

   // 表单排序的定义
   @AName("form_order")
   protected String _formOrder;

   // 显示行数的定义
   @AName("disp_count")
   protected String _dispCount;

   //============================================================
   // <T>获得名称的内容。</T>
   //
   // @return 名称
   //============================================================
   public String getName(){
      return _name;
   }

   //============================================================
   // <T>设置名称的内容。</T>
   //
   // @param value 名称
   //============================================================
   public void setName(String value){
      _name = value;
   }

   //============================================================
   // <T>获得标签的内容。</T>
   //
   // @return 标签
   //============================================================
   public String getLabel(){
      return _label.get();
   }

   //============================================================
   // <T>设置标签的内容。</T>
   //
   // @param value 标签
   //============================================================
   public void setLabel(String value){
      _label.set(value);
   }

   //============================================================
   // <T>获得有效性的内容。</T>
   //
   // @return 有效性
   //============================================================
   public Boolean getIsValid(){
      return _isValid;
   }

   //============================================================
   // <T>设置有效性的内容。</T>
   //
   // @param value 有效性
   //============================================================
   public void setIsValid(Boolean value){
      _isValid = value;
   }

   //============================================================
   // <T>获得注释的内容。</T>
   //
   // @return 注释
   //============================================================
   public String getNote(){
      return _note.get();
   }

   //============================================================
   // <T>设置注释的内容。</T>
   //
   // @param value 注释
   //============================================================
   public void setNote(String value){
      _note.set(value);
   }

   //============================================================
   // <T>获得显示模式的内容。</T>
   //
   // @return 显示模式
   //============================================================
   public abstract String getDispMode();

   //============================================================
   // <T>设置显示模式的内容。</T>
   //
   // @param value 显示模式
   //============================================================
   public abstract void setDispMode(String value);

   //============================================================
   // <T>获得显示设置的内容。</T>
   //
   // @return 显示设置
   //============================================================
   public abstract String getDispConfig();

   //============================================================
   // <T>设置显示设置的内容。</T>
   //
   // @param value 显示设置
   //============================================================
   public abstract void setDispConfig(String value);

   //============================================================
   // <T>获得查询的内容。</T>
   //
   // @return 查询
   //============================================================
   public Boolean getDispFetch(){
      return _dispFetch;
   }

   //============================================================
   // <T>设置查询的内容。</T>
   //
   // @param value 查询
   //============================================================
   public void setDispFetch(Boolean value){
      _dispFetch = value;
   }

   //============================================================
   // <T>获得搜索显示的内容。</T>
   //
   // @return 搜索显示
   //============================================================
   public Boolean getDispSearch(){
      return _dispSearch;
   }

   //============================================================
   // <T>设置搜索显示的内容。</T>
   //
   // @param value 搜索显示
   //============================================================
   public void setDispSearch(Boolean value){
      _dispSearch = value;
   }

   //============================================================
   // <T>获得选取显示的内容。</T>
   //
   // @return 选取显示
   //============================================================
   public Boolean getDispPicker(){
      return _dispPicker;
   }

   //============================================================
   // <T>设置选取显示的内容。</T>
   //
   // @param value 选取显示
   //============================================================
   public void setDispPicker(Boolean value){
      _dispPicker = value;
   }

   //============================================================
   // <T>获得LOV显示的内容。</T>
   //
   // @return LOV显示
   //============================================================
   public Boolean getDispLov(){
      return _dispLov;
   }

   //============================================================
   // <T>设置LOV显示的内容。</T>
   //
   // @param value LOV显示
   //============================================================
   public void setDispLov(Boolean value){
      _dispLov = value;
   }

   //============================================================
   // <T>获得放大显示的内容。</T>
   //
   // @return 放大显示
   //============================================================
   public Boolean getDispZoom(){
      return _dispZoom;
   }

   //============================================================
   // <T>设置放大显示的内容。</T>
   //
   // @param value 放大显示
   //============================================================
   public void setDispZoom(Boolean value){
      _dispZoom = value;
   }

   //============================================================
   // <T>获得列表显示的内容。</T>
   //
   // @return 列表显示
   //============================================================
   public Boolean getDispList(){
      return _dispList;
   }

   //============================================================
   // <T>设置列表显示的内容。</T>
   //
   // @param value 列表显示
   //============================================================
   public void setDispList(Boolean value){
      _dispList = value;
   }

   //============================================================
   // <T>获得显示显示的内容。</T>
   //
   // @return 显示显示
   //============================================================
   public Boolean getDispDisplay(){
      return _dispDisplay;
   }

   //============================================================
   // <T>设置显示显示的内容。</T>
   //
   // @param value 显示显示
   //============================================================
   public void setDispDisplay(Boolean value){
      _dispDisplay = value;
   }

   //============================================================
   // <T>获得新建显示的内容。</T>
   //
   // @return 新建显示
   //============================================================
   public Boolean getDispInsert(){
      return _dispInsert;
   }

   //============================================================
   // <T>设置新建显示的内容。</T>
   //
   // @param value 新建显示
   //============================================================
   public void setDispInsert(Boolean value){
      _dispInsert = value;
   }

   //============================================================
   // <T>获得更新显示的内容。</T>
   //
   // @return 更新显示
   //============================================================
   public Boolean getDispUpdate(){
      return _dispUpdate;
   }

   //============================================================
   // <T>设置更新显示的内容。</T>
   //
   // @param value 更新显示
   //============================================================
   public void setDispUpdate(Boolean value){
      _dispUpdate = value;
   }

   //============================================================
   // <T>获得删除显示的内容。</T>
   //
   // @return 删除显示
   //============================================================
   public Boolean getDispDelete(){
      return _dispDelete;
   }

   //============================================================
   // <T>设置删除显示的内容。</T>
   //
   // @param value 删除显示
   //============================================================
   public void setDispDelete(Boolean value){
      _dispDelete = value;
   }

   //============================================================
   // <T>获得自动调整的内容。</T>
   //
   // @return 自动调整
   //============================================================
   public Boolean getDispAuto(){
      return _dispAuto;
   }

   //============================================================
   // <T>设置自动调整的内容。</T>
   //
   // @param value 自动调整
   //============================================================
   public void setDispAuto(Boolean value){
      _dispAuto = value;
   }

   //============================================================
   // <T>获得可变大小的内容。</T>
   //
   // @return 可变大小
   //============================================================
   public Boolean getDispSize(){
      return _dispSize;
   }

   //============================================================
   // <T>设置可变大小的内容。</T>
   //
   // @param value 可变大小
   //============================================================
   public void setDispSize(Boolean value){
      _dispSize = value;
   }

   //============================================================
   // <T>获得可被拖拽的内容。</T>
   //
   // @return 可被拖拽
   //============================================================
   public Boolean getDispDrag(){
      return _dispDrag;
   }

   //============================================================
   // <T>设置可被拖拽的内容。</T>
   //
   // @param value 可被拖拽
   //============================================================
   public void setDispDrag(Boolean value){
      _dispDrag = value;
   }

   //============================================================
   // <T>获得印刷显示的内容。</T>
   //
   // @return 印刷显示
   //============================================================
   public Boolean getDispPrint(){
      return _dispPrint;
   }

   //============================================================
   // <T>设置印刷显示的内容。</T>
   //
   // @param value 印刷显示
   //============================================================
   public void setDispPrint(Boolean value){
      _dispPrint = value;
   }

   //============================================================
   // <T>获得左空余的内容。</T>
   //
   // @return 左空余
   //============================================================
   public String getPadLeft(){
      return _padLeft;
   }

   //============================================================
   // <T>设置左空余的内容。</T>
   //
   // @param value 左空余
   //============================================================
   public void setPadLeft(String value){
      _padLeft = value;
   }

   //============================================================
   // <T>获得上空余的内容。</T>
   //
   // @return 上空余
   //============================================================
   public String getPadTop(){
      return _padTop;
   }

   //============================================================
   // <T>设置上空余的内容。</T>
   //
   // @param value 上空余
   //============================================================
   public void setPadTop(String value){
      _padTop = value;
   }

   //============================================================
   // <T>获得下空余的内容。</T>
   //
   // @return 下空余
   //============================================================
   public String getPadBottom(){
      return _padBottom;
   }

   //============================================================
   // <T>设置下空余的内容。</T>
   //
   // @param value 下空余
   //============================================================
   public void setPadBottom(String value){
      _padBottom = value;
   }

   //============================================================
   // <T>获得右空余的内容。</T>
   //
   // @return 右空余
   //============================================================
   public String getPadRight(){
      return _padRight;
   }

   //============================================================
   // <T>设置右空余的内容。</T>
   //
   // @param value 右空余
   //============================================================
   public void setPadRight(String value){
      _padRight = value;
   }

   //============================================================
   // <T>获得左位置的内容。</T>
   //
   // @return 左位置
   //============================================================
   public String getLeft(){
      return _left;
   }

   //============================================================
   // <T>设置左位置的内容。</T>
   //
   // @param value 左位置
   //============================================================
   public void setLeft(String value){
      _left = value;
   }

   //============================================================
   // <T>获得上位置的内容。</T>
   //
   // @return 上位置
   //============================================================
   public String getTop(){
      return _top;
   }

   //============================================================
   // <T>设置上位置的内容。</T>
   //
   // @param value 上位置
   //============================================================
   public void setTop(String value){
      _top = value;
   }

   //============================================================
   // <T>获得右位置的内容。</T>
   //
   // @return 右位置
   //============================================================
   public String getRight(){
      return _right;
   }

   //============================================================
   // <T>设置右位置的内容。</T>
   //
   // @param value 右位置
   //============================================================
   public void setRight(String value){
      _right = value;
   }

   //============================================================
   // <T>获得下位置的内容。</T>
   //
   // @return 下位置
   //============================================================
   public String getBottom(){
      return _bottom;
   }

   //============================================================
   // <T>设置下位置的内容。</T>
   //
   // @param value 下位置
   //============================================================
   public void setBottom(String value){
      _bottom = value;
   }

   //============================================================
   // <T>获得宽度的内容。</T>
   //
   // @return 宽度
   //============================================================
   public String getWidth(){
      return _width;
   }

   //============================================================
   // <T>设置宽度的内容。</T>
   //
   // @param value 宽度
   //============================================================
   public void setWidth(String value){
      _width = value;
   }

   //============================================================
   // <T>获得高度的内容。</T>
   //
   // @return 高度
   //============================================================
   public String getHeight(){
      return _height;
   }

   //============================================================
   // <T>设置高度的内容。</T>
   //
   // @param value 高度
   //============================================================
   public void setHeight(String value){
      _height = value;
   }

   //============================================================
   // <T>获得无回行的内容。</T>
   //
   // @return 无回行
   //============================================================
   public Boolean getNowrap(){
      return _nowrap;
   }

   //============================================================
   // <T>设置无回行的内容。</T>
   //
   // @param value 无回行
   //============================================================
   public void setNowrap(Boolean value){
      _nowrap = value;
   }

   //============================================================
   // <T>获得状态栏提示的内容。</T>
   //
   // @return 状态栏提示
   //============================================================
   public String getHint(){
      return _hint;
   }

   //============================================================
   // <T>设置状态栏提示的内容。</T>
   //
   // @param value 状态栏提示
   //============================================================
   public void setHint(String value){
      _hint = value;
   }

   //============================================================
   // <T>获得控件样式的内容。</T>
   //
   // @return 控件样式
   //============================================================
   public String getStyleRefer(){
      return _styleRefer;
   }

   //============================================================
   // <T>设置控件样式的内容。</T>
   //
   // @param value 控件样式
   //============================================================
   public void setStyleRefer(String value){
      _styleRefer = value;
   }

   //============================================================
   // <T>获得标签样式的内容。</T>
   //
   // @return 标签样式
   //============================================================
   public String getStyleLabel(){
      return _styleLabel;
   }

   //============================================================
   // <T>设置标签样式的内容。</T>
   //
   // @param value 标签样式
   //============================================================
   public void setStyleLabel(String value){
      _styleLabel = value;
   }

   //============================================================
   // <T>获得编辑样式的内容。</T>
   //
   // @return 编辑样式
   //============================================================
   public String getStyleEdit(){
      return _styleEdit;
   }

   //============================================================
   // <T>设置编辑样式的内容。</T>
   //
   // @param value 编辑样式
   //============================================================
   public void setStyleEdit(String value){
      _styleEdit = value;
   }

   //============================================================
   // <T>获得版本的内容。</T>
   //
   // @return 版本
   //============================================================
   public String getVersion(){
      return _version;
   }

   //============================================================
   // <T>设置版本的内容。</T>
   //
   // @param value 版本
   //============================================================
   public void setVersion(String value){
      _version = value;
   }

   //============================================================
   // <T>获得数据集名称的内容。</T>
   //
   // @return 数据集名称
   //============================================================
   public String getDataset(){
      return _dataset;
   }

   //============================================================
   // <T>设置数据集名称的内容。</T>
   //
   // @param value 数据集名称
   //============================================================
   public void setDataset(String value){
      _dataset = value;
   }

   //============================================================
   // <T>获得数据来源的内容。</T>
   //
   // @return 数据来源
   //============================================================
   public String getDatasetFrom(){
      return _datasetFrom;
   }

   //============================================================
   // <T>设置数据来源的内容。</T>
   //
   // @param value 数据来源
   //============================================================
   public void setDatasetFrom(String value){
      _datasetFrom = value;
   }

   //============================================================
   // <T>获得数据处理的内容。</T>
   //
   // @return 数据处理
   //============================================================
   public String getDatasetProcedure(){
      return _datasetProcedure;
   }

   //============================================================
   // <T>设置数据处理的内容。</T>
   //
   // @param value 数据处理
   //============================================================
   public void setDatasetProcedure(String value){
      _datasetProcedure = value;
   }

   //============================================================
   // <T>获得数据服务的内容。</T>
   //
   // @return 数据服务
   //============================================================
   public String getDatasetService(){
      return _datasetService;
   }

   //============================================================
   // <T>设置数据服务的内容。</T>
   //
   // @param value 数据服务
   //============================================================
   public void setDatasetService(String value){
      _datasetService = value;
   }

   //============================================================
   // <T>获得数据搜索的内容。</T>
   //
   // @return 数据搜索
   //============================================================
   public String getDatasetSearch(){
      return _datasetSearch;
   }

   //============================================================
   // <T>设置数据搜索的内容。</T>
   //
   // @param value 数据搜索
   //============================================================
   public void setDatasetSearch(String value){
      _datasetSearch = value;
   }

   //============================================================
   // <T>获得数据分组的内容。</T>
   //
   // @return 数据分组
   //============================================================
   public String getDatasetGroup(){
      return _datasetGroup;
   }

   //============================================================
   // <T>设置数据分组的内容。</T>
   //
   // @param value 数据分组
   //============================================================
   public void setDatasetGroup(String value){
      _datasetGroup = value;
   }

   //============================================================
   // <T>获得数据排序的内容。</T>
   //
   // @return 数据排序
   //============================================================
   public String getDatasetOrder(){
      return _datasetOrder;
   }

   //============================================================
   // <T>设置数据排序的内容。</T>
   //
   // @param value 数据排序
   //============================================================
   public void setDatasetOrder(String value){
      _datasetOrder = value;
   }

   //============================================================
   // <T>获得数据参数的内容。</T>
   //
   // @return 数据参数
   //============================================================
   public String getDatasetParameters(){
      return _datasetParameters;
   }

   //============================================================
   // <T>设置数据参数的内容。</T>
   //
   // @param value 数据参数
   //============================================================
   public void setDatasetParameters(String value){
      _datasetParameters = value;
   }

   //============================================================
   // <T>获得操作数据准备的内容。</T>
   //
   // @return 操作数据准备
   //============================================================
   public String getDatasetPrepare(){
      return _datasetPrepare;
   }

   //============================================================
   // <T>设置操作数据准备的内容。</T>
   //
   // @param value 操作数据准备
   //============================================================
   public void setDatasetPrepare(String value){
      _datasetPrepare = value;
   }

   //============================================================
   // <T>获得新建数据参数的内容。</T>
   //
   // @return 新建数据参数
   //============================================================
   public String getDatasetInsertParameters(){
      return _datasetInsertParameters;
   }

   //============================================================
   // <T>设置新建数据参数的内容。</T>
   //
   // @param value 新建数据参数
   //============================================================
   public void setDatasetInsertParameters(String value){
      _datasetInsertParameters = value;
   }

   //============================================================
   // <T>获得新建数据的内容。</T>
   //
   // @return 新建数据
   //============================================================
   public String getDatasetInsert(){
      return _datasetInsert;
   }

   //============================================================
   // <T>设置新建数据的内容。</T>
   //
   // @param value 新建数据
   //============================================================
   public void setDatasetInsert(String value){
      _datasetInsert = value;
   }

   //============================================================
   // <T>获得更新数据参数的内容。</T>
   //
   // @return 更新数据参数
   //============================================================
   public String getDatasetUpdateParameters(){
      return _datasetUpdateParameters;
   }

   //============================================================
   // <T>设置更新数据参数的内容。</T>
   //
   // @param value 更新数据参数
   //============================================================
   public void setDatasetUpdateParameters(String value){
      _datasetUpdateParameters = value;
   }

   //============================================================
   // <T>获得更新数据的内容。</T>
   //
   // @return 更新数据
   //============================================================
   public String getDatasetUpdate(){
      return _datasetUpdate;
   }

   //============================================================
   // <T>设置更新数据的内容。</T>
   //
   // @param value 更新数据
   //============================================================
   public void setDatasetUpdate(String value){
      _datasetUpdate = value;
   }

   //============================================================
   // <T>获得删除数据参数的内容。</T>
   //
   // @return 删除数据参数
   //============================================================
   public String getDatasetDeleteParameters(){
      return _datasetDeleteParameters;
   }

   //============================================================
   // <T>设置删除数据参数的内容。</T>
   //
   // @param value 删除数据参数
   //============================================================
   public void setDatasetDeleteParameters(String value){
      _datasetDeleteParameters = value;
   }

   //============================================================
   // <T>获得删除数据的内容。</T>
   //
   // @return 删除数据
   //============================================================
   public String getDatasetDelete(){
      return _datasetDelete;
   }

   //============================================================
   // <T>设置删除数据的内容。</T>
   //
   // @param value 删除数据
   //============================================================
   public void setDatasetDelete(String value){
      _datasetDelete = value;
   }

   //============================================================
   // <T>获得编辑模式的内容。</T>
   //
   // @return 编辑模式
   //============================================================
   public abstract String getEditMode();

   //============================================================
   // <T>设置编辑模式的内容。</T>
   //
   // @param value 编辑模式
   //============================================================
   public abstract void setEditMode(String value);

   //============================================================
   // <T>获得编辑设置的内容。</T>
   //
   // @return 编辑设置
   //============================================================
   public abstract String getEditConfig();

   //============================================================
   // <T>设置编辑设置的内容。</T>
   //
   // @param value 编辑设置
   //============================================================
   public abstract void setEditConfig(String value);

   //============================================================
   // <T>获得复制编辑可的内容。</T>
   //
   // @return 复制编辑可
   //============================================================
   public Boolean getEditCopy(){
      return _editCopy;
   }

   //============================================================
   // <T>设置复制编辑可的内容。</T>
   //
   // @param value 复制编辑可
   //============================================================
   public void setEditCopy(Boolean value){
      _editCopy = value;
   }

   //============================================================
   // <T>获得查询编辑可的内容。</T>
   //
   // @return 查询编辑可
   //============================================================
   public Boolean getEditFetch(){
      return _editFetch;
   }

   //============================================================
   // <T>设置查询编辑可的内容。</T>
   //
   // @param value 查询编辑可
   //============================================================
   public void setEditFetch(Boolean value){
      _editFetch = value;
   }

   //============================================================
   // <T>获得搜索编辑可的内容。</T>
   //
   // @return 搜索编辑可
   //============================================================
   public Boolean getEditSearch(){
      return _editSearch;
   }

   //============================================================
   // <T>设置搜索编辑可的内容。</T>
   //
   // @param value 搜索编辑可
   //============================================================
   public void setEditSearch(Boolean value){
      _editSearch = value;
   }

   //============================================================
   // <T>获得选取编辑可的内容。</T>
   //
   // @return 选取编辑可
   //============================================================
   public Boolean getEditPicker(){
      return _editPicker;
   }

   //============================================================
   // <T>设置选取编辑可的内容。</T>
   //
   // @param value 选取编辑可
   //============================================================
   public void setEditPicker(Boolean value){
      _editPicker = value;
   }

   //============================================================
   // <T>获得放大编辑可的内容。</T>
   //
   // @return 放大编辑可
   //============================================================
   public Boolean getEditZoom(){
      return _editZoom;
   }

   //============================================================
   // <T>设置放大编辑可的内容。</T>
   //
   // @param value 放大编辑可
   //============================================================
   public void setEditZoom(Boolean value){
      _editZoom = value;
   }

   //============================================================
   // <T>获得打印编辑可的内容。</T>
   //
   // @return 打印编辑可
   //============================================================
   public Boolean getEditPrint(){
      return _editPrint;
   }

   //============================================================
   // <T>设置打印编辑可的内容。</T>
   //
   // @param value 打印编辑可
   //============================================================
   public void setEditPrint(Boolean value){
      _editPrint = value;
   }

   //============================================================
   // <T>获得操作编辑可的内容。</T>
   //
   // @return 操作编辑可
   //============================================================
   public Boolean getEditAction(){
      return _editAction;
   }

   //============================================================
   // <T>设置操作编辑可的内容。</T>
   //
   // @param value 操作编辑可
   //============================================================
   public void setEditAction(Boolean value){
      _editAction = value;
   }

   //============================================================
   // <T>获得新建编辑可的内容。</T>
   //
   // @return 新建编辑可
   //============================================================
   public Boolean getEditInsert(){
      return _editInsert;
   }

   //============================================================
   // <T>设置新建编辑可的内容。</T>
   //
   // @param value 新建编辑可
   //============================================================
   public void setEditInsert(Boolean value){
      _editInsert = value;
   }

   //============================================================
   // <T>获得修改编辑可的内容。</T>
   //
   // @return 修改编辑可
   //============================================================
   public Boolean getEditUpdate(){
      return _editUpdate;
   }

   //============================================================
   // <T>设置修改编辑可的内容。</T>
   //
   // @param value 修改编辑可
   //============================================================
   public void setEditUpdate(Boolean value){
      _editUpdate = value;
   }

   //============================================================
   // <T>获得删除编辑可的内容。</T>
   //
   // @return 删除编辑可
   //============================================================
   public Boolean getEditDelete(){
      return _editDelete;
   }

   //============================================================
   // <T>设置删除编辑可的内容。</T>
   //
   // @param value 删除编辑可
   //============================================================
   public void setEditDelete(Boolean value){
      _editDelete = value;
   }

   //============================================================
   // <T>获得检查权限的内容。</T>
   //
   // @return 检查权限
   //============================================================
   public abstract String getValidAccess();

   //============================================================
   // <T>设置检查权限的内容。</T>
   //
   // @param value 检查权限
   //============================================================
   public abstract void setValidAccess(String value);

   //============================================================
   // <T>获得新建检查可的内容。</T>
   //
   // @return 新建检查可
   //============================================================
   public Boolean getValidInsert(){
      return _validInsert;
   }

   //============================================================
   // <T>设置新建检查可的内容。</T>
   //
   // @param value 新建检查可
   //============================================================
   public void setValidInsert(Boolean value){
      _validInsert = value;
   }

   //============================================================
   // <T>获得更新检查可的内容。</T>
   //
   // @return 更新检查可
   //============================================================
   public Boolean getValidUpdate(){
      return _validUpdate;
   }

   //============================================================
   // <T>设置更新检查可的内容。</T>
   //
   // @param value 更新检查可
   //============================================================
   public void setValidUpdate(Boolean value){
      _validUpdate = value;
   }

   //============================================================
   // <T>获得删除检查可的内容。</T>
   //
   // @return 删除检查可
   //============================================================
   public Boolean getValidDelete(){
      return _validDelete;
   }

   //============================================================
   // <T>设置删除检查可的内容。</T>
   //
   // @param value 删除检查可
   //============================================================
   public void setValidDelete(Boolean value){
      _validDelete = value;
   }

   //============================================================
   // <T>获得新建处理的内容。</T>
   //
   // @return 新建处理
   //============================================================
   public String getActionInsert(){
      return _actionInsert;
   }

   //============================================================
   // <T>设置新建处理的内容。</T>
   //
   // @param value 新建处理
   //============================================================
   public void setActionInsert(String value){
      _actionInsert = value;
   }

   //============================================================
   // <T>获得更新处理的内容。</T>
   //
   // @return 更新处理
   //============================================================
   public String getActionUpdate(){
      return _actionUpdate;
   }

   //============================================================
   // <T>设置更新处理的内容。</T>
   //
   // @param value 更新处理
   //============================================================
   public void setActionUpdate(String value){
      _actionUpdate = value;
   }

   //============================================================
   // <T>获得删除处理的内容。</T>
   //
   // @return 删除处理
   //============================================================
   public String getActionDelete(){
      return _actionDelete;
   }

   //============================================================
   // <T>设置删除处理的内容。</T>
   //
   // @param value 删除处理
   //============================================================
   public void setActionDelete(String value){
      _actionDelete = value;
   }

   //============================================================
   // <T>获得操作准备的内容。</T>
   //
   // @return 操作准备
   //============================================================
   public String getActionPrepare(){
      return _actionPrepare;
   }

   //============================================================
   // <T>设置操作准备的内容。</T>
   //
   // @param value 操作准备
   //============================================================
   public void setActionPrepare(String value){
      _actionPrepare = value;
   }

   //============================================================
   // <T>获得工具栏显示的内容。</T>
   //
   // @return 工具栏显示
   //============================================================
   public Boolean getDispToolbar(){
      return _dispToolbar;
   }

   //============================================================
   // <T>设置工具栏显示的内容。</T>
   //
   // @param value 工具栏显示
   //============================================================
   public void setDispToolbar(Boolean value){
      _dispToolbar = value;
   }

   //============================================================
   // <T>获得逻辑名称的内容。</T>
   //
   // @return 逻辑名称
   //============================================================
   public String getLgName(){
      return _lgName;
   }

   //============================================================
   // <T>设置逻辑名称的内容。</T>
   //
   // @param value 逻辑名称
   //============================================================
   public void setLgName(String value){
      _lgName = value;
   }

   //============================================================
   // <T>获得面板权限的内容。</T>
   //
   // @return 面板权限
   //============================================================
   public abstract String getPanelAccess();

   //============================================================
   // <T>设置面板权限的内容。</T>
   //
   // @param value 面板权限
   //============================================================
   public abstract void setPanelAccess(String value);

   //============================================================
   // <T>获得行工具栏的内容。</T>
   //
   // @return 行工具栏
   //============================================================
   public String getDispRowbar(){
      return _dispRowbar;
   }

   //============================================================
   // <T>设置行工具栏的内容。</T>
   //
   // @param value 行工具栏
   //============================================================
   public void setDispRowbar(String value){
      _dispRowbar = value;
   }

   //============================================================
   // <T>获得显示标题栏的内容。</T>
   //
   // @return 显示标题栏
   //============================================================
   public String getPanelTitle(){
      return _panelTitle;
   }

   //============================================================
   // <T>设置显示标题栏的内容。</T>
   //
   // @param value 显示标题栏
   //============================================================
   public void setPanelTitle(String value){
      _panelTitle = value;
   }

   //============================================================
   // <T>获得显示表头栏的内容。</T>
   //
   // @return 显示表头栏
   //============================================================
   public String getPanelHead(){
      return _panelHead;
   }

   //============================================================
   // <T>设置显示表头栏的内容。</T>
   //
   // @param value 显示表头栏
   //============================================================
   public void setPanelHead(String value){
      _panelHead = value;
   }

   //============================================================
   // <T>获得显示搜索框栏的内容。</T>
   //
   // @return 显示搜索框栏
   //============================================================
   public String getPanelSearch(){
      return _panelSearch;
   }

   //============================================================
   // <T>设置显示搜索框栏的内容。</T>
   //
   // @param value 显示搜索框栏
   //============================================================
   public void setPanelSearch(String value){
      _panelSearch = value;
   }

   //============================================================
   // <T>获得显示提示栏的内容。</T>
   //
   // @return 显示提示栏
   //============================================================
   public String getPanelHint(){
      return _panelHint;
   }

   //============================================================
   // <T>设置显示提示栏的内容。</T>
   //
   // @param value 显示提示栏
   //============================================================
   public void setPanelHint(String value){
      _panelHint = value;
   }

   //============================================================
   // <T>获得合计的内容。</T>
   //
   // @return 合计
   //============================================================
   public String getPanelTotal(){
      return _panelTotal;
   }

   //============================================================
   // <T>设置合计的内容。</T>
   //
   // @param value 合计
   //============================================================
   public void setPanelTotal(String value){
      _panelTotal = value;
   }

   //============================================================
   // <T>获得准备查询的内容。</T>
   //
   // @return 准备查询
   //============================================================
   public String getQueryPrepare(){
      return _queryPrepare;
   }

   //============================================================
   // <T>设置准备查询的内容。</T>
   //
   // @param value 准备查询
   //============================================================
   public void setQueryPrepare(String value){
      _queryPrepare = value;
   }

   //============================================================
   // <T>获得是否已关联详细表单的内容。</T>
   //
   // @return 是否已关联详细表单
   //============================================================
   public Boolean getFormLinked(){
      return _formLinked;
   }

   //============================================================
   // <T>设置是否已关联详细表单的内容。</T>
   //
   // @param value 是否已关联详细表单
   //============================================================
   public void setFormLinked(Boolean value){
      _formLinked = value;
   }

   //============================================================
   // <T>获得传输方式的内容。</T>
   //
   // @return 传输方式
   //============================================================
   public String getFormCustom(){
      return _formCustom;
   }

   //============================================================
   // <T>设置传输方式的内容。</T>
   //
   // @param value 传输方式
   //============================================================
   public void setFormCustom(String value){
      _formCustom = value;
   }

   //============================================================
   // <T>获得传输参数的内容。</T>
   //
   // @return 传输参数
   //============================================================
   public String getFormParameter(){
      return _formParameter;
   }

   //============================================================
   // <T>设置传输参数的内容。</T>
   //
   // @param value 传输参数
   //============================================================
   public void setFormParameter(String value){
      _formParameter = value;
   }

   //============================================================
   // <T>获得显示行选中的内容。</T>
   //
   // @return 显示行选中
   //============================================================
   public String getDispSelected(){
      return _dispSelected;
   }

   //============================================================
   // <T>设置显示行选中的内容。</T>
   //
   // @param value 显示行选中
   //============================================================
   public void setDispSelected(String value){
      _dispSelected = value;
   }

   //============================================================
   // <T>获得命令的内容。</T>
   //
   // @return 命令
   //============================================================
   public String getAction(){
      return _action;
   }

   //============================================================
   // <T>设置命令的内容。</T>
   //
   // @param value 命令
   //============================================================
   public void setAction(String value){
      _action = value;
   }

   //============================================================
   // <T>获得表单名称的内容。</T>
   //
   // @return 表单名称
   //============================================================
   public String getFormName(){
      return _formName;
   }

   //============================================================
   // <T>设置表单名称的内容。</T>
   //
   // @param value 表单名称
   //============================================================
   public void setFormName(String value){
      _formName = value;
   }

   //============================================================
   // <T>获得分页大小的内容。</T>
   //
   // @return 分页大小
   //============================================================
   public String getPageSize(){
      return _pageSize;
   }

   //============================================================
   // <T>设置分页大小的内容。</T>
   //
   // @param value 分页大小
   //============================================================
   public void setPageSize(String value){
      _pageSize = value;
   }

   //============================================================
   // <T>获得父名称的内容。</T>
   //
   // @return 父名称
   //============================================================
   public String getParentName(){
      return _parentName;
   }

   //============================================================
   // <T>设置父名称的内容。</T>
   //
   // @param value 父名称
   //============================================================
   public void setParentName(String value){
      _parentName = value;
   }

   //============================================================
   // <T>获得表单引用的内容。</T>
   //
   // @return 表单引用
   //============================================================
   public String getFormRefer(){
      return _formRefer;
   }

   //============================================================
   // <T>设置表单引用的内容。</T>
   //
   // @param value 表单引用
   //============================================================
   public void setFormRefer(String value){
      _formRefer = value;
   }

   //============================================================
   // <T>获得表单转移条件的内容。</T>
   //
   // @return 表单转移条件
   //============================================================
   public String getFormReferWhere(){
      return _formReferWhere;
   }

   //============================================================
   // <T>设置表单转移条件的内容。</T>
   //
   // @param value 表单转移条件
   //============================================================
   public void setFormReferWhere(String value){
      _formReferWhere = value;
   }

   //============================================================
   // <T>获得表单链接的内容。</T>
   //
   // @return 表单链接
   //============================================================
   public String getFormLink(){
      return _formLink;
   }

   //============================================================
   // <T>设置表单链接的内容。</T>
   //
   // @param value 表单链接
   //============================================================
   public void setFormLink(String value){
      _formLink = value;
   }

   //============================================================
   // <T>获得表单查询的内容。</T>
   //
   // @return 表单查询
   //============================================================
   public String getFormSearch(){
      return _formSearch;
   }

   //============================================================
   // <T>设置表单查询的内容。</T>
   //
   // @param value 表单查询
   //============================================================
   public void setFormSearch(String value){
      _formSearch = value;
   }

   //============================================================
   // <T>获得表单排序的内容。</T>
   //
   // @return 表单排序
   //============================================================
   public String getFormOrder(){
      return _formOrder;
   }

   //============================================================
   // <T>设置表单排序的内容。</T>
   //
   // @param value 表单排序
   //============================================================
   public void setFormOrder(String value){
      _formOrder = value;
   }

   //============================================================
   // <T>获得显示行数的内容。</T>
   //
   // @return 显示行数
   //============================================================
   public String getDispCount(){
      return _dispCount;
   }

   //============================================================
   // <T>设置显示行数的内容。</T>
   //
   // @param value 显示行数
   //============================================================
   public void setDispCount(String value){
      _dispCount = value;
   }

   //============================================================
   // <T>内部获得内容置信息。</T>
   //
   // @param name 名称
   // @return 内容
   //============================================================
   public String innerGet(String name){
      if(RString.isEmpty(name)){
         return null;
      }else if(PTY_NAME.equalsIgnoreCase(name)){
         return getName();
      }else if(PTY_LABEL.equalsIgnoreCase(name)){
         return getLabel();
      }else if(PTY_IS_VALID.equalsIgnoreCase(name)){
         return RBoolean.toString(getIsValid());
      }else if(PTY_NOTE.equalsIgnoreCase(name)){
         return getNote();
      }else if(PTY_DISP_MODE.equalsIgnoreCase(name)){
         return getDispMode();
      }else if(PTY_DISP_CONFIG.equalsIgnoreCase(name)){
         return getDispConfig();
      }else if(PTY_DISP_FETCH.equalsIgnoreCase(name)){
         return RBoolean.toString(getDispFetch());
      }else if(PTY_DISP_SEARCH.equalsIgnoreCase(name)){
         return RBoolean.toString(getDispSearch());
      }else if(PTY_DISP_PICKER.equalsIgnoreCase(name)){
         return RBoolean.toString(getDispPicker());
      }else if(PTY_DISP_LOV.equalsIgnoreCase(name)){
         return RBoolean.toString(getDispLov());
      }else if(PTY_DISP_ZOOM.equalsIgnoreCase(name)){
         return RBoolean.toString(getDispZoom());
      }else if(PTY_DISP_LIST.equalsIgnoreCase(name)){
         return RBoolean.toString(getDispList());
      }else if(PTY_DISP_DISPLAY.equalsIgnoreCase(name)){
         return RBoolean.toString(getDispDisplay());
      }else if(PTY_DISP_INSERT.equalsIgnoreCase(name)){
         return RBoolean.toString(getDispInsert());
      }else if(PTY_DISP_UPDATE.equalsIgnoreCase(name)){
         return RBoolean.toString(getDispUpdate());
      }else if(PTY_DISP_DELETE.equalsIgnoreCase(name)){
         return RBoolean.toString(getDispDelete());
      }else if(PTY_DISP_AUTO.equalsIgnoreCase(name)){
         return RBoolean.toString(getDispAuto());
      }else if(PTY_DISP_SIZE.equalsIgnoreCase(name)){
         return RBoolean.toString(getDispSize());
      }else if(PTY_DISP_DRAG.equalsIgnoreCase(name)){
         return RBoolean.toString(getDispDrag());
      }else if(PTY_DISP_PRINT.equalsIgnoreCase(name)){
         return RBoolean.toString(getDispPrint());
      }else if(PTY_PAD_LEFT.equalsIgnoreCase(name)){
         return getPadLeft();
      }else if(PTY_PAD_TOP.equalsIgnoreCase(name)){
         return getPadTop();
      }else if(PTY_PAD_BOTTOM.equalsIgnoreCase(name)){
         return getPadBottom();
      }else if(PTY_PAD_RIGHT.equalsIgnoreCase(name)){
         return getPadRight();
      }else if(PTY_LEFT.equalsIgnoreCase(name)){
         return getLeft();
      }else if(PTY_TOP.equalsIgnoreCase(name)){
         return getTop();
      }else if(PTY_RIGHT.equalsIgnoreCase(name)){
         return getRight();
      }else if(PTY_BOTTOM.equalsIgnoreCase(name)){
         return getBottom();
      }else if(PTY_WIDTH.equalsIgnoreCase(name)){
         return getWidth();
      }else if(PTY_HEIGHT.equalsIgnoreCase(name)){
         return getHeight();
      }else if(PTY_NOWRAP.equalsIgnoreCase(name)){
         return RBoolean.toString(getNowrap());
      }else if(PTY_HINT.equalsIgnoreCase(name)){
         return getHint();
      }else if(PTY_STYLE_REFER.equalsIgnoreCase(name)){
         return getStyleRefer();
      }else if(PTY_STYLE_LABEL.equalsIgnoreCase(name)){
         return getStyleLabel();
      }else if(PTY_STYLE_EDIT.equalsIgnoreCase(name)){
         return getStyleEdit();
      }else if(PTY_VERSION.equalsIgnoreCase(name)){
         return getVersion();
      }else if(PTY_DATASET.equalsIgnoreCase(name)){
         return getDataset();
      }else if(PTY_DATASET_FROM.equalsIgnoreCase(name)){
         return getDatasetFrom();
      }else if(PTY_DATASET_PROCEDURE.equalsIgnoreCase(name)){
         return getDatasetProcedure();
      }else if(PTY_DATASET_SERVICE.equalsIgnoreCase(name)){
         return getDatasetService();
      }else if(PTY_DATASET_SEARCH.equalsIgnoreCase(name)){
         return getDatasetSearch();
      }else if(PTY_DATASET_GROUP.equalsIgnoreCase(name)){
         return getDatasetGroup();
      }else if(PTY_DATASET_ORDER.equalsIgnoreCase(name)){
         return getDatasetOrder();
      }else if(PTY_DATASET_PARAMETERS.equalsIgnoreCase(name)){
         return getDatasetParameters();
      }else if(PTY_DATASET_PREPARE.equalsIgnoreCase(name)){
         return getDatasetPrepare();
      }else if(PTY_DATASET_INSERT_PARAMETERS.equalsIgnoreCase(name)){
         return getDatasetInsertParameters();
      }else if(PTY_DATASET_INSERT.equalsIgnoreCase(name)){
         return getDatasetInsert();
      }else if(PTY_DATASET_UPDATE_PARAMETERS.equalsIgnoreCase(name)){
         return getDatasetUpdateParameters();
      }else if(PTY_DATASET_UPDATE.equalsIgnoreCase(name)){
         return getDatasetUpdate();
      }else if(PTY_DATASET_DELETE_PARAMETERS.equalsIgnoreCase(name)){
         return getDatasetDeleteParameters();
      }else if(PTY_DATASET_DELETE.equalsIgnoreCase(name)){
         return getDatasetDelete();
      }else if(PTY_EDIT_MODE.equalsIgnoreCase(name)){
         return getEditMode();
      }else if(PTY_EDIT_CONFIG.equalsIgnoreCase(name)){
         return getEditConfig();
      }else if(PTY_EDIT_COPY.equalsIgnoreCase(name)){
         return RBoolean.toString(getEditCopy());
      }else if(PTY_EDIT_FETCH.equalsIgnoreCase(name)){
         return RBoolean.toString(getEditFetch());
      }else if(PTY_EDIT_SEARCH.equalsIgnoreCase(name)){
         return RBoolean.toString(getEditSearch());
      }else if(PTY_EDIT_PICKER.equalsIgnoreCase(name)){
         return RBoolean.toString(getEditPicker());
      }else if(PTY_EDIT_ZOOM.equalsIgnoreCase(name)){
         return RBoolean.toString(getEditZoom());
      }else if(PTY_EDIT_PRINT.equalsIgnoreCase(name)){
         return RBoolean.toString(getEditPrint());
      }else if(PTY_EDIT_ACTION.equalsIgnoreCase(name)){
         return RBoolean.toString(getEditAction());
      }else if(PTY_EDIT_INSERT.equalsIgnoreCase(name)){
         return RBoolean.toString(getEditInsert());
      }else if(PTY_EDIT_UPDATE.equalsIgnoreCase(name)){
         return RBoolean.toString(getEditUpdate());
      }else if(PTY_EDIT_DELETE.equalsIgnoreCase(name)){
         return RBoolean.toString(getEditDelete());
      }else if(PTY_VALID_ACCESS.equalsIgnoreCase(name)){
         return getValidAccess();
      }else if(PTY_VALID_INSERT.equalsIgnoreCase(name)){
         return RBoolean.toString(getValidInsert());
      }else if(PTY_VALID_UPDATE.equalsIgnoreCase(name)){
         return RBoolean.toString(getValidUpdate());
      }else if(PTY_VALID_DELETE.equalsIgnoreCase(name)){
         return RBoolean.toString(getValidDelete());
      }else if(PTY_ACTION_INSERT.equalsIgnoreCase(name)){
         return getActionInsert();
      }else if(PTY_ACTION_UPDATE.equalsIgnoreCase(name)){
         return getActionUpdate();
      }else if(PTY_ACTION_DELETE.equalsIgnoreCase(name)){
         return getActionDelete();
      }else if(PTY_ACTION_PREPARE.equalsIgnoreCase(name)){
         return getActionPrepare();
      }else if(PTY_DISP_TOOLBAR.equalsIgnoreCase(name)){
         return RBoolean.toString(getDispToolbar());
      }else if(PTY_LG_NAME.equalsIgnoreCase(name)){
         return getLgName();
      }else if(PTY_PANEL_ACCESS.equalsIgnoreCase(name)){
         return getPanelAccess();
      }else if(PTY_DISP_ROWBAR.equalsIgnoreCase(name)){
         return getDispRowbar();
      }else if(PTY_PANEL_TITLE.equalsIgnoreCase(name)){
         return getPanelTitle();
      }else if(PTY_PANEL_HEAD.equalsIgnoreCase(name)){
         return getPanelHead();
      }else if(PTY_PANEL_SEARCH.equalsIgnoreCase(name)){
         return getPanelSearch();
      }else if(PTY_PANEL_HINT.equalsIgnoreCase(name)){
         return getPanelHint();
      }else if(PTY_PANEL_TOTAL.equalsIgnoreCase(name)){
         return getPanelTotal();
      }else if(PTY_QUERY_PREPARE.equalsIgnoreCase(name)){
         return getQueryPrepare();
      }else if(PTY_FORM_LINKED.equalsIgnoreCase(name)){
         return RBoolean.toString(getFormLinked());
      }else if(PTY_FORM_CUSTOM.equalsIgnoreCase(name)){
         return getFormCustom();
      }else if(PTY_FORM_PARAMETER.equalsIgnoreCase(name)){
         return getFormParameter();
      }else if(PTY_DISP_SELECTED.equalsIgnoreCase(name)){
         return getDispSelected();
      }else if(PTY_ACTION.equalsIgnoreCase(name)){
         return getAction();
      }else if(PTY_FORM_NAME.equalsIgnoreCase(name)){
         return getFormName();
      }else if(PTY_PAGE_SIZE.equalsIgnoreCase(name)){
         return getPageSize();
      }else if(PTY_PARENT_NAME.equalsIgnoreCase(name)){
         return getParentName();
      }else if(PTY_FORM_REFER.equalsIgnoreCase(name)){
         return getFormRefer();
      }else if(PTY_FORM_REFER_WHERE.equalsIgnoreCase(name)){
         return getFormReferWhere();
      }else if(PTY_FORM_LINK.equalsIgnoreCase(name)){
         return getFormLink();
      }else if(PTY_FORM_SEARCH.equalsIgnoreCase(name)){
         return getFormSearch();
      }else if(PTY_FORM_ORDER.equalsIgnoreCase(name)){
         return getFormOrder();
      }else if(PTY_DISP_COUNT.equalsIgnoreCase(name)){
         return getDispCount();
      }
      return null;
   }

   //============================================================
   // <T>内部设置内容置信息。</T>
   //
   // @param name 名称
   // @param value 内容
   //============================================================
   public void innerSet(String name, String value){
      if(RString.isEmpty(name)){
         return;
      }else if(PTY_NAME.equalsIgnoreCase(name)){
         setName(value);
      }else if(PTY_LABEL.equalsIgnoreCase(name)){
         setLabel(value);
      }else if(PTY_IS_VALID.equalsIgnoreCase(name)){
         setIsValid(RBoolean.parse(value));
      }else if(PTY_NOTE.equalsIgnoreCase(name)){
         setNote(value);
      }else if(PTY_DISP_MODE.equalsIgnoreCase(name)){
         setDispMode(value);
      }else if(PTY_DISP_CONFIG.equalsIgnoreCase(name)){
         setDispConfig(value);
      }else if(PTY_DISP_FETCH.equalsIgnoreCase(name)){
         setDispFetch(RBoolean.parse(value));
      }else if(PTY_DISP_SEARCH.equalsIgnoreCase(name)){
         setDispSearch(RBoolean.parse(value));
      }else if(PTY_DISP_PICKER.equalsIgnoreCase(name)){
         setDispPicker(RBoolean.parse(value));
      }else if(PTY_DISP_LOV.equalsIgnoreCase(name)){
         setDispLov(RBoolean.parse(value));
      }else if(PTY_DISP_ZOOM.equalsIgnoreCase(name)){
         setDispZoom(RBoolean.parse(value));
      }else if(PTY_DISP_LIST.equalsIgnoreCase(name)){
         setDispList(RBoolean.parse(value));
      }else if(PTY_DISP_DISPLAY.equalsIgnoreCase(name)){
         setDispDisplay(RBoolean.parse(value));
      }else if(PTY_DISP_INSERT.equalsIgnoreCase(name)){
         setDispInsert(RBoolean.parse(value));
      }else if(PTY_DISP_UPDATE.equalsIgnoreCase(name)){
         setDispUpdate(RBoolean.parse(value));
      }else if(PTY_DISP_DELETE.equalsIgnoreCase(name)){
         setDispDelete(RBoolean.parse(value));
      }else if(PTY_DISP_AUTO.equalsIgnoreCase(name)){
         setDispAuto(RBoolean.parse(value));
      }else if(PTY_DISP_SIZE.equalsIgnoreCase(name)){
         setDispSize(RBoolean.parse(value));
      }else if(PTY_DISP_DRAG.equalsIgnoreCase(name)){
         setDispDrag(RBoolean.parse(value));
      }else if(PTY_DISP_PRINT.equalsIgnoreCase(name)){
         setDispPrint(RBoolean.parse(value));
      }else if(PTY_PAD_LEFT.equalsIgnoreCase(name)){
         setPadLeft(value);
      }else if(PTY_PAD_TOP.equalsIgnoreCase(name)){
         setPadTop(value);
      }else if(PTY_PAD_BOTTOM.equalsIgnoreCase(name)){
         setPadBottom(value);
      }else if(PTY_PAD_RIGHT.equalsIgnoreCase(name)){
         setPadRight(value);
      }else if(PTY_LEFT.equalsIgnoreCase(name)){
         setLeft(value);
      }else if(PTY_TOP.equalsIgnoreCase(name)){
         setTop(value);
      }else if(PTY_RIGHT.equalsIgnoreCase(name)){
         setRight(value);
      }else if(PTY_BOTTOM.equalsIgnoreCase(name)){
         setBottom(value);
      }else if(PTY_WIDTH.equalsIgnoreCase(name)){
         setWidth(value);
      }else if(PTY_HEIGHT.equalsIgnoreCase(name)){
         setHeight(value);
      }else if(PTY_NOWRAP.equalsIgnoreCase(name)){
         setNowrap(RBoolean.parse(value));
      }else if(PTY_HINT.equalsIgnoreCase(name)){
         setHint(value);
      }else if(PTY_STYLE_REFER.equalsIgnoreCase(name)){
         setStyleRefer(value);
      }else if(PTY_STYLE_LABEL.equalsIgnoreCase(name)){
         setStyleLabel(value);
      }else if(PTY_STYLE_EDIT.equalsIgnoreCase(name)){
         setStyleEdit(value);
      }else if(PTY_VERSION.equalsIgnoreCase(name)){
         setVersion(value);
      }else if(PTY_DATASET.equalsIgnoreCase(name)){
         setDataset(value);
      }else if(PTY_DATASET_FROM.equalsIgnoreCase(name)){
         setDatasetFrom(value);
      }else if(PTY_DATASET_PROCEDURE.equalsIgnoreCase(name)){
         setDatasetProcedure(value);
      }else if(PTY_DATASET_SERVICE.equalsIgnoreCase(name)){
         setDatasetService(value);
      }else if(PTY_DATASET_SEARCH.equalsIgnoreCase(name)){
         setDatasetSearch(value);
      }else if(PTY_DATASET_GROUP.equalsIgnoreCase(name)){
         setDatasetGroup(value);
      }else if(PTY_DATASET_ORDER.equalsIgnoreCase(name)){
         setDatasetOrder(value);
      }else if(PTY_DATASET_PARAMETERS.equalsIgnoreCase(name)){
         setDatasetParameters(value);
      }else if(PTY_DATASET_PREPARE.equalsIgnoreCase(name)){
         setDatasetPrepare(value);
      }else if(PTY_DATASET_INSERT_PARAMETERS.equalsIgnoreCase(name)){
         setDatasetInsertParameters(value);
      }else if(PTY_DATASET_INSERT.equalsIgnoreCase(name)){
         setDatasetInsert(value);
      }else if(PTY_DATASET_UPDATE_PARAMETERS.equalsIgnoreCase(name)){
         setDatasetUpdateParameters(value);
      }else if(PTY_DATASET_UPDATE.equalsIgnoreCase(name)){
         setDatasetUpdate(value);
      }else if(PTY_DATASET_DELETE_PARAMETERS.equalsIgnoreCase(name)){
         setDatasetDeleteParameters(value);
      }else if(PTY_DATASET_DELETE.equalsIgnoreCase(name)){
         setDatasetDelete(value);
      }else if(PTY_EDIT_MODE.equalsIgnoreCase(name)){
         setEditMode(value);
      }else if(PTY_EDIT_CONFIG.equalsIgnoreCase(name)){
         setEditConfig(value);
      }else if(PTY_EDIT_COPY.equalsIgnoreCase(name)){
         setEditCopy(RBoolean.parse(value));
      }else if(PTY_EDIT_FETCH.equalsIgnoreCase(name)){
         setEditFetch(RBoolean.parse(value));
      }else if(PTY_EDIT_SEARCH.equalsIgnoreCase(name)){
         setEditSearch(RBoolean.parse(value));
      }else if(PTY_EDIT_PICKER.equalsIgnoreCase(name)){
         setEditPicker(RBoolean.parse(value));
      }else if(PTY_EDIT_ZOOM.equalsIgnoreCase(name)){
         setEditZoom(RBoolean.parse(value));
      }else if(PTY_EDIT_PRINT.equalsIgnoreCase(name)){
         setEditPrint(RBoolean.parse(value));
      }else if(PTY_EDIT_ACTION.equalsIgnoreCase(name)){
         setEditAction(RBoolean.parse(value));
      }else if(PTY_EDIT_INSERT.equalsIgnoreCase(name)){
         setEditInsert(RBoolean.parse(value));
      }else if(PTY_EDIT_UPDATE.equalsIgnoreCase(name)){
         setEditUpdate(RBoolean.parse(value));
      }else if(PTY_EDIT_DELETE.equalsIgnoreCase(name)){
         setEditDelete(RBoolean.parse(value));
      }else if(PTY_VALID_ACCESS.equalsIgnoreCase(name)){
         setValidAccess(value);
      }else if(PTY_VALID_INSERT.equalsIgnoreCase(name)){
         setValidInsert(RBoolean.parse(value));
      }else if(PTY_VALID_UPDATE.equalsIgnoreCase(name)){
         setValidUpdate(RBoolean.parse(value));
      }else if(PTY_VALID_DELETE.equalsIgnoreCase(name)){
         setValidDelete(RBoolean.parse(value));
      }else if(PTY_ACTION_INSERT.equalsIgnoreCase(name)){
         setActionInsert(value);
      }else if(PTY_ACTION_UPDATE.equalsIgnoreCase(name)){
         setActionUpdate(value);
      }else if(PTY_ACTION_DELETE.equalsIgnoreCase(name)){
         setActionDelete(value);
      }else if(PTY_ACTION_PREPARE.equalsIgnoreCase(name)){
         setActionPrepare(value);
      }else if(PTY_DISP_TOOLBAR.equalsIgnoreCase(name)){
         setDispToolbar(RBoolean.parse(value));
      }else if(PTY_LG_NAME.equalsIgnoreCase(name)){
         setLgName(value);
      }else if(PTY_PANEL_ACCESS.equalsIgnoreCase(name)){
         setPanelAccess(value);
      }else if(PTY_DISP_ROWBAR.equalsIgnoreCase(name)){
         setDispRowbar(value);
      }else if(PTY_PANEL_TITLE.equalsIgnoreCase(name)){
         setPanelTitle(value);
      }else if(PTY_PANEL_HEAD.equalsIgnoreCase(name)){
         setPanelHead(value);
      }else if(PTY_PANEL_SEARCH.equalsIgnoreCase(name)){
         setPanelSearch(value);
      }else if(PTY_PANEL_HINT.equalsIgnoreCase(name)){
         setPanelHint(value);
      }else if(PTY_PANEL_TOTAL.equalsIgnoreCase(name)){
         setPanelTotal(value);
      }else if(PTY_QUERY_PREPARE.equalsIgnoreCase(name)){
         setQueryPrepare(value);
      }else if(PTY_FORM_LINKED.equalsIgnoreCase(name)){
         setFormLinked(RBoolean.parse(value));
      }else if(PTY_FORM_CUSTOM.equalsIgnoreCase(name)){
         setFormCustom(value);
      }else if(PTY_FORM_PARAMETER.equalsIgnoreCase(name)){
         setFormParameter(value);
      }else if(PTY_DISP_SELECTED.equalsIgnoreCase(name)){
         setDispSelected(value);
      }else if(PTY_ACTION.equalsIgnoreCase(name)){
         setAction(value);
      }else if(PTY_FORM_NAME.equalsIgnoreCase(name)){
         setFormName(value);
      }else if(PTY_PAGE_SIZE.equalsIgnoreCase(name)){
         setPageSize(value);
      }else if(PTY_PARENT_NAME.equalsIgnoreCase(name)){
         setParentName(value);
      }else if(PTY_FORM_REFER.equalsIgnoreCase(name)){
         setFormRefer(value);
      }else if(PTY_FORM_REFER_WHERE.equalsIgnoreCase(name)){
         setFormReferWhere(value);
      }else if(PTY_FORM_LINK.equalsIgnoreCase(name)){
         setFormLink(value);
      }else if(PTY_FORM_SEARCH.equalsIgnoreCase(name)){
         setFormSearch(value);
      }else if(PTY_FORM_ORDER.equalsIgnoreCase(name)){
         setFormOrder(value);
      }else if(PTY_DISP_COUNT.equalsIgnoreCase(name)){
         setDispCount(value);
      }
   }

   //============================================================
   // <T>加载设置信息。</T>
   //
   // @param config 设置信息
   // @param type 类型
   //============================================================
   public void loadConfig(FXmlNode config, EXmlConfig type){
      super.loadConfig(config, type);
      if(EXmlConfig.Full == type){
         if(config.contains("name")){
            setName(config.get(PTY_NAME));
         }
         if(config.contains("label")){
            _label.unpack(config.get(PTY_LABEL));
         }
         if(config.contains("is_valid")){
            setIsValid(RBoolean.parse(config.get(PTY_IS_VALID)));
         }
         if(config.contains("note")){
            _note.unpack(config.get(PTY_NOTE));
         }
         if(config.contains("disp_fetch")){
            setDispFetch(RBoolean.parse(config.get(PTY_DISP_FETCH)));
         }
         if(config.contains("disp_search")){
            setDispSearch(RBoolean.parse(config.get(PTY_DISP_SEARCH)));
         }
         if(config.contains("disp_picker")){
            setDispPicker(RBoolean.parse(config.get(PTY_DISP_PICKER)));
         }
         if(config.contains("disp_lov")){
            setDispLov(RBoolean.parse(config.get(PTY_DISP_LOV)));
         }
         if(config.contains("disp_zoom")){
            setDispZoom(RBoolean.parse(config.get(PTY_DISP_ZOOM)));
         }
         if(config.contains("disp_list")){
            setDispList(RBoolean.parse(config.get(PTY_DISP_LIST)));
         }
         if(config.contains("disp_display")){
            setDispDisplay(RBoolean.parse(config.get(PTY_DISP_DISPLAY)));
         }
         if(config.contains("disp_insert")){
            setDispInsert(RBoolean.parse(config.get(PTY_DISP_INSERT)));
         }
         if(config.contains("disp_update")){
            setDispUpdate(RBoolean.parse(config.get(PTY_DISP_UPDATE)));
         }
         if(config.contains("disp_delete")){
            setDispDelete(RBoolean.parse(config.get(PTY_DISP_DELETE)));
         }
         if(config.contains("disp_auto")){
            setDispAuto(RBoolean.parse(config.get(PTY_DISP_AUTO)));
         }
         if(config.contains("disp_size")){
            setDispSize(RBoolean.parse(config.get(PTY_DISP_SIZE)));
         }
         if(config.contains("disp_drag")){
            setDispDrag(RBoolean.parse(config.get(PTY_DISP_DRAG)));
         }
         if(config.contains("disp_print")){
            setDispPrint(RBoolean.parse(config.get(PTY_DISP_PRINT)));
         }
         if(config.contains("pad_left")){
            setPadLeft(config.get(PTY_PAD_LEFT));
         }
         if(config.contains("pad_top")){
            setPadTop(config.get(PTY_PAD_TOP));
         }
         if(config.contains("pad_bottom")){
            setPadBottom(config.get(PTY_PAD_BOTTOM));
         }
         if(config.contains("pad_right")){
            setPadRight(config.get(PTY_PAD_RIGHT));
         }
         if(config.contains("left")){
            setLeft(config.get(PTY_LEFT));
         }
         if(config.contains("top")){
            setTop(config.get(PTY_TOP));
         }
         if(config.contains("right")){
            setRight(config.get(PTY_RIGHT));
         }
         if(config.contains("bottom")){
            setBottom(config.get(PTY_BOTTOM));
         }
         if(config.contains("width")){
            setWidth(config.get(PTY_WIDTH));
         }
         if(config.contains("height")){
            setHeight(config.get(PTY_HEIGHT));
         }
         if(config.contains("nowrap")){
            setNowrap(RBoolean.parse(config.get(PTY_NOWRAP)));
         }
         if(config.contains("hint")){
            setHint(config.get(PTY_HINT));
         }
         if(config.contains("style_refer")){
            setStyleRefer(config.get(PTY_STYLE_REFER));
         }
         if(config.contains("style_label")){
            setStyleLabel(config.get(PTY_STYLE_LABEL));
         }
         if(config.contains("style_edit")){
            setStyleEdit(config.get(PTY_STYLE_EDIT));
         }
         if(config.contains("version")){
            setVersion(config.get(PTY_VERSION));
         }
         if(config.contains("dataset")){
            setDataset(config.get(PTY_DATASET));
         }
         if(config.contains("dataset_from")){
            setDatasetFrom(config.get(PTY_DATASET_FROM));
         }
         if(config.contains("dataset_procedure")){
            setDatasetProcedure(config.get(PTY_DATASET_PROCEDURE));
         }
         if(config.contains("dataset_service")){
            setDatasetService(config.get(PTY_DATASET_SERVICE));
         }
         if(config.contains("dataset_search")){
            setDatasetSearch(config.get(PTY_DATASET_SEARCH));
         }
         if(config.contains("dataset_group")){
            setDatasetGroup(config.get(PTY_DATASET_GROUP));
         }
         if(config.contains("dataset_order")){
            setDatasetOrder(config.get(PTY_DATASET_ORDER));
         }
         if(config.contains("dataset_parameters")){
            setDatasetParameters(config.get(PTY_DATASET_PARAMETERS));
         }
         if(config.contains("dataset_prepare")){
            setDatasetPrepare(config.get(PTY_DATASET_PREPARE));
         }
         if(config.contains("dataset_insert_parameters")){
            setDatasetInsertParameters(config.get(PTY_DATASET_INSERT_PARAMETERS));
         }
         if(config.contains("dataset_insert")){
            setDatasetInsert(config.get(PTY_DATASET_INSERT));
         }
         if(config.contains("dataset_update_parameters")){
            setDatasetUpdateParameters(config.get(PTY_DATASET_UPDATE_PARAMETERS));
         }
         if(config.contains("dataset_update")){
            setDatasetUpdate(config.get(PTY_DATASET_UPDATE));
         }
         if(config.contains("dataset_delete_parameters")){
            setDatasetDeleteParameters(config.get(PTY_DATASET_DELETE_PARAMETERS));
         }
         if(config.contains("dataset_delete")){
            setDatasetDelete(config.get(PTY_DATASET_DELETE));
         }
         if(config.contains("edit_copy")){
            setEditCopy(RBoolean.parse(config.get(PTY_EDIT_COPY)));
         }
         if(config.contains("edit_fetch")){
            setEditFetch(RBoolean.parse(config.get(PTY_EDIT_FETCH)));
         }
         if(config.contains("edit_search")){
            setEditSearch(RBoolean.parse(config.get(PTY_EDIT_SEARCH)));
         }
         if(config.contains("edit_picker")){
            setEditPicker(RBoolean.parse(config.get(PTY_EDIT_PICKER)));
         }
         if(config.contains("edit_zoom")){
            setEditZoom(RBoolean.parse(config.get(PTY_EDIT_ZOOM)));
         }
         if(config.contains("edit_print")){
            setEditPrint(RBoolean.parse(config.get(PTY_EDIT_PRINT)));
         }
         if(config.contains("edit_action")){
            setEditAction(RBoolean.parse(config.get(PTY_EDIT_ACTION)));
         }
         if(config.contains("edit_insert")){
            setEditInsert(RBoolean.parse(config.get(PTY_EDIT_INSERT)));
         }
         if(config.contains("edit_update")){
            setEditUpdate(RBoolean.parse(config.get(PTY_EDIT_UPDATE)));
         }
         if(config.contains("edit_delete")){
            setEditDelete(RBoolean.parse(config.get(PTY_EDIT_DELETE)));
         }
         if(config.contains("valid_insert")){
            setValidInsert(RBoolean.parse(config.get(PTY_VALID_INSERT)));
         }
         if(config.contains("valid_update")){
            setValidUpdate(RBoolean.parse(config.get(PTY_VALID_UPDATE)));
         }
         if(config.contains("valid_delete")){
            setValidDelete(RBoolean.parse(config.get(PTY_VALID_DELETE)));
         }
         if(config.contains("action_insert")){
            setActionInsert(config.get(PTY_ACTION_INSERT));
         }
         if(config.contains("action_update")){
            setActionUpdate(config.get(PTY_ACTION_UPDATE));
         }
         if(config.contains("action_delete")){
            setActionDelete(config.get(PTY_ACTION_DELETE));
         }
         if(config.contains("action_prepare")){
            setActionPrepare(config.get(PTY_ACTION_PREPARE));
         }
         if(config.contains("disp_toolbar")){
            setDispToolbar(RBoolean.parse(config.get(PTY_DISP_TOOLBAR)));
         }
         if(config.contains("lg_name")){
            setLgName(config.get(PTY_LG_NAME));
         }
         if(config.contains("disp_rowbar")){
            setDispRowbar(config.get(PTY_DISP_ROWBAR));
         }
         if(config.contains("panel_title")){
            setPanelTitle(config.get(PTY_PANEL_TITLE));
         }
         if(config.contains("panel_head")){
            setPanelHead(config.get(PTY_PANEL_HEAD));
         }
         if(config.contains("panel_search")){
            setPanelSearch(config.get(PTY_PANEL_SEARCH));
         }
         if(config.contains("panel_hint")){
            setPanelHint(config.get(PTY_PANEL_HINT));
         }
         if(config.contains("panel_total")){
            setPanelTotal(config.get(PTY_PANEL_TOTAL));
         }
         if(config.contains("query_prepare")){
            setQueryPrepare(config.get(PTY_QUERY_PREPARE));
         }
         if(config.contains("form_linked")){
            setFormLinked(RBoolean.parse(config.get(PTY_FORM_LINKED)));
         }
         if(config.contains("form_custom")){
            setFormCustom(config.get(PTY_FORM_CUSTOM));
         }
         if(config.contains("form_parameter")){
            setFormParameter(config.get(PTY_FORM_PARAMETER));
         }
         if(config.contains("disp_selected")){
            setDispSelected(config.get(PTY_DISP_SELECTED));
         }
         if(config.contains("action")){
            setAction(config.get(PTY_ACTION));
         }
         if(config.contains("form_name")){
            setFormName(config.get(PTY_FORM_NAME));
         }
         if(config.contains("page_size")){
            setPageSize(config.get(PTY_PAGE_SIZE));
         }
         if(config.contains("parent_name")){
            setParentName(config.get(PTY_PARENT_NAME));
         }
         if(config.contains("form_refer")){
            setFormRefer(config.get(PTY_FORM_REFER));
         }
         if(config.contains("form_refer_where")){
            setFormReferWhere(config.get(PTY_FORM_REFER_WHERE));
         }
         if(config.contains("form_link")){
            setFormLink(config.get(PTY_FORM_LINK));
         }
         if(config.contains("form_search")){
            setFormSearch(config.get(PTY_FORM_SEARCH));
         }
         if(config.contains("form_order")){
            setFormOrder(config.get(PTY_FORM_ORDER));
         }
         if(config.contains("disp_count")){
            setDispCount(config.get(PTY_DISP_COUNT));
         }
      }else if(EXmlConfig.Simple == type){
         if(config.contains("name")){
            setName(config.get(PTY_NAME));
         }
         if(config.contains("label")){
            setLabel(config.get(PTY_LABEL));
         }
         if(config.contains("note")){
            setNote(config.get(PTY_NOTE));
         }
         if(config.contains("disp_mode")){
            setDispMode(config.get(PTY_DISP_MODE));
         }
         if(config.contains("disp_config")){
            setDispConfig(config.get(PTY_DISP_CONFIG));
         }
         if(config.contains("disp_fetch")){
            setDispFetch(RBoolean.parse(config.get(PTY_DISP_FETCH)));
         }
         if(config.contains("pad_left")){
            setPadLeft(config.get(PTY_PAD_LEFT));
         }
         if(config.contains("pad_top")){
            setPadTop(config.get(PTY_PAD_TOP));
         }
         if(config.contains("pad_bottom")){
            setPadBottom(config.get(PTY_PAD_BOTTOM));
         }
         if(config.contains("pad_right")){
            setPadRight(config.get(PTY_PAD_RIGHT));
         }
         if(config.contains("left")){
            setLeft(config.get(PTY_LEFT));
         }
         if(config.contains("top")){
            setTop(config.get(PTY_TOP));
         }
         if(config.contains("right")){
            setRight(config.get(PTY_RIGHT));
         }
         if(config.contains("bottom")){
            setBottom(config.get(PTY_BOTTOM));
         }
         if(config.contains("width")){
            setWidth(config.get(PTY_WIDTH));
         }
         if(config.contains("height")){
            setHeight(config.get(PTY_HEIGHT));
         }
         if(config.contains("nowrap")){
            setNowrap(RBoolean.parse(config.get(PTY_NOWRAP)));
         }
         if(config.contains("hint")){
            setHint(config.get(PTY_HINT));
         }
         if(config.contains("style_label")){
            setStyleLabel(config.get(PTY_STYLE_LABEL));
         }
         if(config.contains("style_edit")){
            setStyleEdit(config.get(PTY_STYLE_EDIT));
         }
         if(config.contains("version")){
            setVersion(config.get(PTY_VERSION));
         }
         if(config.contains("dataset")){
            setDataset(config.get(PTY_DATASET));
         }
         if(config.contains("dataset_service")){
            setDatasetService(config.get(PTY_DATASET_SERVICE));
         }
         if(config.contains("dataset_prepare")){
            setDatasetPrepare(config.get(PTY_DATASET_PREPARE));
         }
         if(config.contains("dataset_insert_parameters")){
            setDatasetInsertParameters(config.get(PTY_DATASET_INSERT_PARAMETERS));
         }
         if(config.contains("dataset_insert")){
            setDatasetInsert(config.get(PTY_DATASET_INSERT));
         }
         if(config.contains("dataset_update_parameters")){
            setDatasetUpdateParameters(config.get(PTY_DATASET_UPDATE_PARAMETERS));
         }
         if(config.contains("dataset_update")){
            setDatasetUpdate(config.get(PTY_DATASET_UPDATE));
         }
         if(config.contains("dataset_delete_parameters")){
            setDatasetDeleteParameters(config.get(PTY_DATASET_DELETE_PARAMETERS));
         }
         if(config.contains("dataset_delete")){
            setDatasetDelete(config.get(PTY_DATASET_DELETE));
         }
         if(config.contains("edit_mode")){
            setEditMode(config.get(PTY_EDIT_MODE));
         }
         if(config.contains("edit_config")){
            setEditConfig(config.get(PTY_EDIT_CONFIG));
         }
         if(config.contains("valid_access")){
            setValidAccess(config.get(PTY_VALID_ACCESS));
         }
         if(config.contains("action_insert")){
            setActionInsert(config.get(PTY_ACTION_INSERT));
         }
         if(config.contains("action_update")){
            setActionUpdate(config.get(PTY_ACTION_UPDATE));
         }
         if(config.contains("action_delete")){
            setActionDelete(config.get(PTY_ACTION_DELETE));
         }
         if(config.contains("action_prepare")){
            setActionPrepare(config.get(PTY_ACTION_PREPARE));
         }
         if(config.contains("disp_toolbar")){
            setDispToolbar(RBoolean.parse(config.get(PTY_DISP_TOOLBAR)));
         }
         if(config.contains("lg_name")){
            setLgName(config.get(PTY_LG_NAME));
         }
         if(config.contains("panel_access")){
            setPanelAccess(config.get(PTY_PANEL_ACCESS));
         }
         if(config.contains("disp_rowbar")){
            setDispRowbar(config.get(PTY_DISP_ROWBAR));
         }
         if(config.contains("query_prepare")){
            setQueryPrepare(config.get(PTY_QUERY_PREPARE));
         }
         if(config.contains("form_linked")){
            setFormLinked(RBoolean.parse(config.get(PTY_FORM_LINKED)));
         }
         if(config.contains("form_custom")){
            setFormCustom(config.get(PTY_FORM_CUSTOM));
         }
         if(config.contains("form_parameter")){
            setFormParameter(config.get(PTY_FORM_PARAMETER));
         }
         if(config.contains("disp_selected")){
            setDispSelected(config.get(PTY_DISP_SELECTED));
         }
         if(config.contains("action")){
            setAction(config.get(PTY_ACTION));
         }
         if(config.contains("form_name")){
            setFormName(config.get(PTY_FORM_NAME));
         }
         if(config.contains("page_size")){
            setPageSize(config.get(PTY_PAGE_SIZE));
         }
         if(config.contains("form_refer")){
            setFormRefer(config.get(PTY_FORM_REFER));
         }
         if(config.contains("form_refer_where")){
            setFormReferWhere(config.get(PTY_FORM_REFER_WHERE));
         }
         if(config.contains("form_link")){
            setFormLink(config.get(PTY_FORM_LINK));
         }
         if(config.contains("form_search")){
            setFormSearch(config.get(PTY_FORM_SEARCH));
         }
         if(config.contains("form_order")){
            setFormOrder(config.get(PTY_FORM_ORDER));
         }
         if(config.contains("disp_count")){
            setDispCount(config.get(PTY_DISP_COUNT));
         }
      }else if(EXmlConfig.Value == type){
         if(config.contains("name")){
            setName(config.get(PTY_NAME));
         }
         if(config.contains("label")){
            setLabel(config.get(PTY_LABEL));
         }
         if(config.contains("is_valid")){
            setIsValid(RBoolean.parse(config.get(PTY_IS_VALID)));
         }
         if(config.contains("note")){
            setNote(config.get(PTY_NOTE));
         }
         if(config.contains("disp_mode")){
            setDispMode(config.get(PTY_DISP_MODE));
         }
         if(config.contains("disp_config")){
            setDispConfig(config.get(PTY_DISP_CONFIG));
         }
         if(config.contains("disp_fetch")){
            setDispFetch(RBoolean.parse(config.get(PTY_DISP_FETCH)));
         }
         if(config.contains("disp_search")){
            setDispSearch(RBoolean.parse(config.get(PTY_DISP_SEARCH)));
         }
         if(config.contains("disp_picker")){
            setDispPicker(RBoolean.parse(config.get(PTY_DISP_PICKER)));
         }
         if(config.contains("disp_lov")){
            setDispLov(RBoolean.parse(config.get(PTY_DISP_LOV)));
         }
         if(config.contains("disp_zoom")){
            setDispZoom(RBoolean.parse(config.get(PTY_DISP_ZOOM)));
         }
         if(config.contains("disp_list")){
            setDispList(RBoolean.parse(config.get(PTY_DISP_LIST)));
         }
         if(config.contains("disp_display")){
            setDispDisplay(RBoolean.parse(config.get(PTY_DISP_DISPLAY)));
         }
         if(config.contains("disp_insert")){
            setDispInsert(RBoolean.parse(config.get(PTY_DISP_INSERT)));
         }
         if(config.contains("disp_update")){
            setDispUpdate(RBoolean.parse(config.get(PTY_DISP_UPDATE)));
         }
         if(config.contains("disp_delete")){
            setDispDelete(RBoolean.parse(config.get(PTY_DISP_DELETE)));
         }
         if(config.contains("disp_auto")){
            setDispAuto(RBoolean.parse(config.get(PTY_DISP_AUTO)));
         }
         if(config.contains("disp_size")){
            setDispSize(RBoolean.parse(config.get(PTY_DISP_SIZE)));
         }
         if(config.contains("disp_drag")){
            setDispDrag(RBoolean.parse(config.get(PTY_DISP_DRAG)));
         }
         if(config.contains("disp_print")){
            setDispPrint(RBoolean.parse(config.get(PTY_DISP_PRINT)));
         }
         if(config.contains("pad_left")){
            setPadLeft(config.get(PTY_PAD_LEFT));
         }
         if(config.contains("pad_top")){
            setPadTop(config.get(PTY_PAD_TOP));
         }
         if(config.contains("pad_bottom")){
            setPadBottom(config.get(PTY_PAD_BOTTOM));
         }
         if(config.contains("pad_right")){
            setPadRight(config.get(PTY_PAD_RIGHT));
         }
         if(config.contains("left")){
            setLeft(config.get(PTY_LEFT));
         }
         if(config.contains("top")){
            setTop(config.get(PTY_TOP));
         }
         if(config.contains("right")){
            setRight(config.get(PTY_RIGHT));
         }
         if(config.contains("bottom")){
            setBottom(config.get(PTY_BOTTOM));
         }
         if(config.contains("width")){
            setWidth(config.get(PTY_WIDTH));
         }
         if(config.contains("height")){
            setHeight(config.get(PTY_HEIGHT));
         }
         if(config.contains("nowrap")){
            setNowrap(RBoolean.parse(config.get(PTY_NOWRAP)));
         }
         if(config.contains("hint")){
            setHint(config.get(PTY_HINT));
         }
         if(config.contains("style_refer")){
            setStyleRefer(config.get(PTY_STYLE_REFER));
         }
         if(config.contains("style_label")){
            setStyleLabel(config.get(PTY_STYLE_LABEL));
         }
         if(config.contains("style_edit")){
            setStyleEdit(config.get(PTY_STYLE_EDIT));
         }
         if(config.contains("version")){
            setVersion(config.get(PTY_VERSION));
         }
         if(config.contains("dataset")){
            setDataset(config.get(PTY_DATASET));
         }
         if(config.contains("dataset_from")){
            setDatasetFrom(config.get(PTY_DATASET_FROM));
         }
         if(config.contains("dataset_procedure")){
            setDatasetProcedure(config.get(PTY_DATASET_PROCEDURE));
         }
         if(config.contains("dataset_service")){
            setDatasetService(config.get(PTY_DATASET_SERVICE));
         }
         if(config.contains("dataset_search")){
            setDatasetSearch(config.get(PTY_DATASET_SEARCH));
         }
         if(config.contains("dataset_group")){
            setDatasetGroup(config.get(PTY_DATASET_GROUP));
         }
         if(config.contains("dataset_order")){
            setDatasetOrder(config.get(PTY_DATASET_ORDER));
         }
         if(config.contains("dataset_parameters")){
            setDatasetParameters(config.get(PTY_DATASET_PARAMETERS));
         }
         if(config.contains("dataset_prepare")){
            setDatasetPrepare(config.get(PTY_DATASET_PREPARE));
         }
         if(config.contains("dataset_insert_parameters")){
            setDatasetInsertParameters(config.get(PTY_DATASET_INSERT_PARAMETERS));
         }
         if(config.contains("dataset_insert")){
            setDatasetInsert(config.get(PTY_DATASET_INSERT));
         }
         if(config.contains("dataset_update_parameters")){
            setDatasetUpdateParameters(config.get(PTY_DATASET_UPDATE_PARAMETERS));
         }
         if(config.contains("dataset_update")){
            setDatasetUpdate(config.get(PTY_DATASET_UPDATE));
         }
         if(config.contains("dataset_delete_parameters")){
            setDatasetDeleteParameters(config.get(PTY_DATASET_DELETE_PARAMETERS));
         }
         if(config.contains("dataset_delete")){
            setDatasetDelete(config.get(PTY_DATASET_DELETE));
         }
         if(config.contains("edit_mode")){
            setEditMode(config.get(PTY_EDIT_MODE));
         }
         if(config.contains("edit_config")){
            setEditConfig(config.get(PTY_EDIT_CONFIG));
         }
         if(config.contains("edit_copy")){
            setEditCopy(RBoolean.parse(config.get(PTY_EDIT_COPY)));
         }
         if(config.contains("edit_fetch")){
            setEditFetch(RBoolean.parse(config.get(PTY_EDIT_FETCH)));
         }
         if(config.contains("edit_search")){
            setEditSearch(RBoolean.parse(config.get(PTY_EDIT_SEARCH)));
         }
         if(config.contains("edit_picker")){
            setEditPicker(RBoolean.parse(config.get(PTY_EDIT_PICKER)));
         }
         if(config.contains("edit_zoom")){
            setEditZoom(RBoolean.parse(config.get(PTY_EDIT_ZOOM)));
         }
         if(config.contains("edit_print")){
            setEditPrint(RBoolean.parse(config.get(PTY_EDIT_PRINT)));
         }
         if(config.contains("edit_action")){
            setEditAction(RBoolean.parse(config.get(PTY_EDIT_ACTION)));
         }
         if(config.contains("edit_insert")){
            setEditInsert(RBoolean.parse(config.get(PTY_EDIT_INSERT)));
         }
         if(config.contains("edit_update")){
            setEditUpdate(RBoolean.parse(config.get(PTY_EDIT_UPDATE)));
         }
         if(config.contains("edit_delete")){
            setEditDelete(RBoolean.parse(config.get(PTY_EDIT_DELETE)));
         }
         if(config.contains("valid_access")){
            setValidAccess(config.get(PTY_VALID_ACCESS));
         }
         if(config.contains("valid_insert")){
            setValidInsert(RBoolean.parse(config.get(PTY_VALID_INSERT)));
         }
         if(config.contains("valid_update")){
            setValidUpdate(RBoolean.parse(config.get(PTY_VALID_UPDATE)));
         }
         if(config.contains("valid_delete")){
            setValidDelete(RBoolean.parse(config.get(PTY_VALID_DELETE)));
         }
         if(config.contains("action_insert")){
            setActionInsert(config.get(PTY_ACTION_INSERT));
         }
         if(config.contains("action_update")){
            setActionUpdate(config.get(PTY_ACTION_UPDATE));
         }
         if(config.contains("action_delete")){
            setActionDelete(config.get(PTY_ACTION_DELETE));
         }
         if(config.contains("action_prepare")){
            setActionPrepare(config.get(PTY_ACTION_PREPARE));
         }
         if(config.contains("disp_toolbar")){
            setDispToolbar(RBoolean.parse(config.get(PTY_DISP_TOOLBAR)));
         }
         if(config.contains("lg_name")){
            setLgName(config.get(PTY_LG_NAME));
         }
         if(config.contains("panel_access")){
            setPanelAccess(config.get(PTY_PANEL_ACCESS));
         }
         if(config.contains("disp_rowbar")){
            setDispRowbar(config.get(PTY_DISP_ROWBAR));
         }
         if(config.contains("panel_title")){
            setPanelTitle(config.get(PTY_PANEL_TITLE));
         }
         if(config.contains("panel_head")){
            setPanelHead(config.get(PTY_PANEL_HEAD));
         }
         if(config.contains("panel_search")){
            setPanelSearch(config.get(PTY_PANEL_SEARCH));
         }
         if(config.contains("panel_hint")){
            setPanelHint(config.get(PTY_PANEL_HINT));
         }
         if(config.contains("panel_total")){
            setPanelTotal(config.get(PTY_PANEL_TOTAL));
         }
         if(config.contains("query_prepare")){
            setQueryPrepare(config.get(PTY_QUERY_PREPARE));
         }
         if(config.contains("form_linked")){
            setFormLinked(RBoolean.parse(config.get(PTY_FORM_LINKED)));
         }
         if(config.contains("form_custom")){
            setFormCustom(config.get(PTY_FORM_CUSTOM));
         }
         if(config.contains("form_parameter")){
            setFormParameter(config.get(PTY_FORM_PARAMETER));
         }
         if(config.contains("disp_selected")){
            setDispSelected(config.get(PTY_DISP_SELECTED));
         }
         if(config.contains("action")){
            setAction(config.get(PTY_ACTION));
         }
         if(config.contains("form_name")){
            setFormName(config.get(PTY_FORM_NAME));
         }
         if(config.contains("page_size")){
            setPageSize(config.get(PTY_PAGE_SIZE));
         }
         if(config.contains("parent_name")){
            setParentName(config.get(PTY_PARENT_NAME));
         }
         if(config.contains("form_refer")){
            setFormRefer(config.get(PTY_FORM_REFER));
         }
         if(config.contains("form_refer_where")){
            setFormReferWhere(config.get(PTY_FORM_REFER_WHERE));
         }
         if(config.contains("form_link")){
            setFormLink(config.get(PTY_FORM_LINK));
         }
         if(config.contains("form_search")){
            setFormSearch(config.get(PTY_FORM_SEARCH));
         }
         if(config.contains("form_order")){
            setFormOrder(config.get(PTY_FORM_ORDER));
         }
         if(config.contains("disp_count")){
            setDispCount(config.get(PTY_DISP_COUNT));
         }
      }else if(EXmlConfig.Default == type){
         if(RString.isEmpty(getPadLeft())){
            if(config.contains("pad_left")){
               setPadLeft(config.get(PTY_PAD_LEFT));
            }
         }
         if(RString.isEmpty(getPadTop())){
            if(config.contains("pad_top")){
               setPadTop(config.get(PTY_PAD_TOP));
            }
         }
         if(RString.isEmpty(getPadBottom())){
            if(config.contains("pad_bottom")){
               setPadBottom(config.get(PTY_PAD_BOTTOM));
            }
         }
         if(RString.isEmpty(getPadRight())){
            if(config.contains("pad_right")){
               setPadRight(config.get(PTY_PAD_RIGHT));
            }
         }
         if(RString.isEmpty(getLeft())){
            if(config.contains("left")){
               setLeft(config.get(PTY_LEFT));
            }
         }
         if(RString.isEmpty(getTop())){
            if(config.contains("top")){
               setTop(config.get(PTY_TOP));
            }
         }
         if(RString.isEmpty(getRight())){
            if(config.contains("right")){
               setRight(config.get(PTY_RIGHT));
            }
         }
         if(RString.isEmpty(getBottom())){
            if(config.contains("bottom")){
               setBottom(config.get(PTY_BOTTOM));
            }
         }
         if(RString.isEmpty(getWidth())){
            if(config.contains("width")){
               setWidth(config.get(PTY_WIDTH));
            }
         }
         if(RString.isEmpty(getHeight())){
            if(config.contains("height")){
               setHeight(config.get(PTY_HEIGHT));
            }
         }
         if(RString.isEmpty(getStyleLabel())){
            if(config.contains("style_label")){
               setStyleLabel(config.get(PTY_STYLE_LABEL));
            }
         }
         if(RString.isEmpty(getStyleEdit())){
            if(config.contains("style_edit")){
               setStyleEdit(config.get(PTY_STYLE_EDIT));
            }
         }
         if(RString.isEmpty(getVersion())){
            if(config.contains("version")){
               setVersion(config.get(PTY_VERSION));
            }
         }
         if(RString.isEmpty(getDatasetPrepare())){
            if(config.contains("dataset_prepare")){
               setDatasetPrepare(config.get(PTY_DATASET_PREPARE));
            }
         }
         if(RString.isEmpty(getDatasetInsert())){
            if(config.contains("dataset_insert")){
               setDatasetInsert(config.get(PTY_DATASET_INSERT));
            }
         }
         if(RString.isEmpty(getDatasetUpdate())){
            if(config.contains("dataset_update")){
               setDatasetUpdate(config.get(PTY_DATASET_UPDATE));
            }
         }
         if(RString.isEmpty(getDatasetDelete())){
            if(config.contains("dataset_delete")){
               setDatasetDelete(config.get(PTY_DATASET_DELETE));
            }
         }
         if(RString.isEmpty(getActionPrepare())){
            if(config.contains("action_prepare")){
               setActionPrepare(config.get(PTY_ACTION_PREPARE));
            }
         }
         if(RString.isEmpty(getLgName())){
            if(config.contains("lg_name")){
               setLgName(config.get(PTY_LG_NAME));
            }
         }
         if(RString.isEmpty(getDispRowbar())){
            if(config.contains("disp_rowbar")){
               setDispRowbar(config.get(PTY_DISP_ROWBAR));
            }
         }
         if(RString.isEmpty(getPanelTotal())){
            if(config.contains("panel_total")){
               setPanelTotal(config.get(PTY_PANEL_TOTAL));
            }
         }
         if(RString.isEmpty(getFormReferWhere())){
            if(config.contains("form_refer_where")){
               setFormReferWhere(config.get(PTY_FORM_REFER_WHERE));
            }
         }
         if(RString.isEmpty(getDispCount())){
            if(config.contains("disp_count")){
               setDispCount(config.get(PTY_DISP_COUNT));
            }
         }
      }
   }

   //============================================================
   // <T>保存设置信息。</T>
   //
   // @param config 设置信息
   // @param type 类型
   //============================================================
   public void saveConfig(FXmlNode config, EXmlConfig type){
      config.setName(NAME);
      super.saveConfig(config, type);
      if(EXmlConfig.Full == type){
         if(RString.isNotEmpty(getName())){
            config.set(PTY_NAME, getName());
         }
         String label = _label.pack().toString();
         if(RString.isNotEmpty(label)){
            config.set(PTY_LABEL, label);
         }
         if(RBoolean.parse(getIsValid())){
            config.set(PTY_IS_VALID, RBoolean.toString(getIsValid()));
         }
         String note = _note.pack().toString();
         if(RString.isNotEmpty(note)){
            config.set(PTY_NOTE, note);
         }
         if(RBoolean.parse(getDispFetch())){
            config.set(PTY_DISP_FETCH, RBoolean.toString(getDispFetch()));
         }
         if(RBoolean.parse(getDispSearch())){
            config.set(PTY_DISP_SEARCH, RBoolean.toString(getDispSearch()));
         }
         if(RBoolean.parse(getDispPicker())){
            config.set(PTY_DISP_PICKER, RBoolean.toString(getDispPicker()));
         }
         if(RBoolean.parse(getDispLov())){
            config.set(PTY_DISP_LOV, RBoolean.toString(getDispLov()));
         }
         if(RBoolean.parse(getDispZoom())){
            config.set(PTY_DISP_ZOOM, RBoolean.toString(getDispZoom()));
         }
         if(RBoolean.parse(getDispList())){
            config.set(PTY_DISP_LIST, RBoolean.toString(getDispList()));
         }
         if(RBoolean.parse(getDispDisplay())){
            config.set(PTY_DISP_DISPLAY, RBoolean.toString(getDispDisplay()));
         }
         if(RBoolean.parse(getDispInsert())){
            config.set(PTY_DISP_INSERT, RBoolean.toString(getDispInsert()));
         }
         if(RBoolean.parse(getDispUpdate())){
            config.set(PTY_DISP_UPDATE, RBoolean.toString(getDispUpdate()));
         }
         if(RBoolean.parse(getDispDelete())){
            config.set(PTY_DISP_DELETE, RBoolean.toString(getDispDelete()));
         }
         if(RBoolean.parse(getDispAuto())){
            config.set(PTY_DISP_AUTO, RBoolean.toString(getDispAuto()));
         }
         if(RBoolean.parse(getDispSize())){
            config.set(PTY_DISP_SIZE, RBoolean.toString(getDispSize()));
         }
         if(RBoolean.parse(getDispDrag())){
            config.set(PTY_DISP_DRAG, RBoolean.toString(getDispDrag()));
         }
         if(RBoolean.parse(getDispPrint())){
            config.set(PTY_DISP_PRINT, RBoolean.toString(getDispPrint()));
         }
         if(RString.isNotEmpty(getPadLeft())){
            config.set(PTY_PAD_LEFT, getPadLeft());
         }
         if(RString.isNotEmpty(getPadTop())){
            config.set(PTY_PAD_TOP, getPadTop());
         }
         if(RString.isNotEmpty(getPadBottom())){
            config.set(PTY_PAD_BOTTOM, getPadBottom());
         }
         if(RString.isNotEmpty(getPadRight())){
            config.set(PTY_PAD_RIGHT, getPadRight());
         }
         if(RString.isNotEmpty(getLeft())){
            config.set(PTY_LEFT, getLeft());
         }
         if(RString.isNotEmpty(getTop())){
            config.set(PTY_TOP, getTop());
         }
         if(RString.isNotEmpty(getRight())){
            config.set(PTY_RIGHT, getRight());
         }
         if(RString.isNotEmpty(getBottom())){
            config.set(PTY_BOTTOM, getBottom());
         }
         if(RString.isNotEmpty(getWidth())){
            config.set(PTY_WIDTH, getWidth());
         }
         if(RString.isNotEmpty(getHeight())){
            config.set(PTY_HEIGHT, getHeight());
         }
         if(RBoolean.parse(getNowrap())){
            config.set(PTY_NOWRAP, RBoolean.toString(getNowrap()));
         }
         if(RString.isNotEmpty(getHint())){
            config.set(PTY_HINT, getHint());
         }
         if(RString.isNotEmpty(getStyleRefer())){
            config.set(PTY_STYLE_REFER, getStyleRefer());
         }
         if(RString.isNotEmpty(getStyleLabel())){
            config.set(PTY_STYLE_LABEL, getStyleLabel());
         }
         if(RString.isNotEmpty(getStyleEdit())){
            config.set(PTY_STYLE_EDIT, getStyleEdit());
         }
         if(RString.isNotEmpty(getVersion())){
            config.set(PTY_VERSION, getVersion());
         }
         if(RString.isNotEmpty(getDataset())){
            config.set(PTY_DATASET, getDataset());
         }
         if(RString.isNotEmpty(getDatasetFrom())){
            config.set(PTY_DATASET_FROM, getDatasetFrom());
         }
         if(RString.isNotEmpty(getDatasetProcedure())){
            config.set(PTY_DATASET_PROCEDURE, getDatasetProcedure());
         }
         if(RString.isNotEmpty(getDatasetService())){
            config.set(PTY_DATASET_SERVICE, getDatasetService());
         }
         if(RString.isNotEmpty(getDatasetSearch())){
            config.set(PTY_DATASET_SEARCH, getDatasetSearch());
         }
         if(RString.isNotEmpty(getDatasetGroup())){
            config.set(PTY_DATASET_GROUP, getDatasetGroup());
         }
         if(RString.isNotEmpty(getDatasetOrder())){
            config.set(PTY_DATASET_ORDER, getDatasetOrder());
         }
         if(RString.isNotEmpty(getDatasetParameters())){
            config.set(PTY_DATASET_PARAMETERS, getDatasetParameters());
         }
         if(RString.isNotEmpty(getDatasetPrepare())){
            config.set(PTY_DATASET_PREPARE, getDatasetPrepare());
         }
         if(RString.isNotEmpty(getDatasetInsertParameters())){
            config.set(PTY_DATASET_INSERT_PARAMETERS, getDatasetInsertParameters());
         }
         if(RString.isNotEmpty(getDatasetInsert())){
            config.set(PTY_DATASET_INSERT, getDatasetInsert());
         }
         if(RString.isNotEmpty(getDatasetUpdateParameters())){
            config.set(PTY_DATASET_UPDATE_PARAMETERS, getDatasetUpdateParameters());
         }
         if(RString.isNotEmpty(getDatasetUpdate())){
            config.set(PTY_DATASET_UPDATE, getDatasetUpdate());
         }
         if(RString.isNotEmpty(getDatasetDeleteParameters())){
            config.set(PTY_DATASET_DELETE_PARAMETERS, getDatasetDeleteParameters());
         }
         if(RString.isNotEmpty(getDatasetDelete())){
            config.set(PTY_DATASET_DELETE, getDatasetDelete());
         }
         if(RBoolean.parse(getEditCopy())){
            config.set(PTY_EDIT_COPY, RBoolean.toString(getEditCopy()));
         }
         if(RBoolean.parse(getEditFetch())){
            config.set(PTY_EDIT_FETCH, RBoolean.toString(getEditFetch()));
         }
         if(RBoolean.parse(getEditSearch())){
            config.set(PTY_EDIT_SEARCH, RBoolean.toString(getEditSearch()));
         }
         if(RBoolean.parse(getEditPicker())){
            config.set(PTY_EDIT_PICKER, RBoolean.toString(getEditPicker()));
         }
         if(RBoolean.parse(getEditZoom())){
            config.set(PTY_EDIT_ZOOM, RBoolean.toString(getEditZoom()));
         }
         if(RBoolean.parse(getEditPrint())){
            config.set(PTY_EDIT_PRINT, RBoolean.toString(getEditPrint()));
         }
         if(RBoolean.parse(getEditAction())){
            config.set(PTY_EDIT_ACTION, RBoolean.toString(getEditAction()));
         }
         if(RBoolean.parse(getEditInsert())){
            config.set(PTY_EDIT_INSERT, RBoolean.toString(getEditInsert()));
         }
         if(RBoolean.parse(getEditUpdate())){
            config.set(PTY_EDIT_UPDATE, RBoolean.toString(getEditUpdate()));
         }
         if(RBoolean.parse(getEditDelete())){
            config.set(PTY_EDIT_DELETE, RBoolean.toString(getEditDelete()));
         }
         if(RBoolean.parse(getValidInsert())){
            config.set(PTY_VALID_INSERT, RBoolean.toString(getValidInsert()));
         }
         if(RBoolean.parse(getValidUpdate())){
            config.set(PTY_VALID_UPDATE, RBoolean.toString(getValidUpdate()));
         }
         if(RBoolean.parse(getValidDelete())){
            config.set(PTY_VALID_DELETE, RBoolean.toString(getValidDelete()));
         }
         if(RString.isNotEmpty(getActionInsert())){
            config.set(PTY_ACTION_INSERT, getActionInsert());
         }
         if(RString.isNotEmpty(getActionUpdate())){
            config.set(PTY_ACTION_UPDATE, getActionUpdate());
         }
         if(RString.isNotEmpty(getActionDelete())){
            config.set(PTY_ACTION_DELETE, getActionDelete());
         }
         if(RString.isNotEmpty(getActionPrepare())){
            config.set(PTY_ACTION_PREPARE, getActionPrepare());
         }
         if(RBoolean.parse(getDispToolbar())){
            config.set(PTY_DISP_TOOLBAR, RBoolean.toString(getDispToolbar()));
         }
         if(RString.isNotEmpty(getLgName())){
            config.set(PTY_LG_NAME, getLgName());
         }
         if(RString.isNotEmpty(getDispRowbar())){
            config.set(PTY_DISP_ROWBAR, getDispRowbar());
         }
         if(RString.isNotEmpty(getPanelTitle())){
            config.set(PTY_PANEL_TITLE, getPanelTitle());
         }
         if(RString.isNotEmpty(getPanelHead())){
            config.set(PTY_PANEL_HEAD, getPanelHead());
         }
         if(RString.isNotEmpty(getPanelSearch())){
            config.set(PTY_PANEL_SEARCH, getPanelSearch());
         }
         if(RString.isNotEmpty(getPanelHint())){
            config.set(PTY_PANEL_HINT, getPanelHint());
         }
         if(RString.isNotEmpty(getPanelTotal())){
            config.set(PTY_PANEL_TOTAL, getPanelTotal());
         }
         if(RString.isNotEmpty(getQueryPrepare())){
            config.set(PTY_QUERY_PREPARE, getQueryPrepare());
         }
         if(RBoolean.parse(getFormLinked())){
            config.set(PTY_FORM_LINKED, RBoolean.toString(getFormLinked()));
         }
         if(RString.isNotEmpty(getFormCustom())){
            config.set(PTY_FORM_CUSTOM, getFormCustom());
         }
         if(RString.isNotEmpty(getFormParameter())){
            config.set(PTY_FORM_PARAMETER, getFormParameter());
         }
         if(RString.isNotEmpty(getDispSelected())){
            config.set(PTY_DISP_SELECTED, getDispSelected());
         }
         if(RString.isNotEmpty(getAction())){
            config.set(PTY_ACTION, getAction());
         }
         if(RString.isNotEmpty(getFormName())){
            config.set(PTY_FORM_NAME, getFormName());
         }
         if(RString.isNotEmpty(getPageSize())){
            config.set(PTY_PAGE_SIZE, getPageSize());
         }
         if(RString.isNotEmpty(getParentName())){
            config.set(PTY_PARENT_NAME, getParentName());
         }
         if(RString.isNotEmpty(getFormRefer())){
            config.set(PTY_FORM_REFER, getFormRefer());
         }
         if(RString.isNotEmpty(getFormReferWhere())){
            config.set(PTY_FORM_REFER_WHERE, getFormReferWhere());
         }
         if(RString.isNotEmpty(getFormLink())){
            config.set(PTY_FORM_LINK, getFormLink());
         }
         if(RString.isNotEmpty(getFormSearch())){
            config.set(PTY_FORM_SEARCH, getFormSearch());
         }
         if(RString.isNotEmpty(getFormOrder())){
            config.set(PTY_FORM_ORDER, getFormOrder());
         }
         if(RString.isNotEmpty(getDispCount())){
            config.set(PTY_DISP_COUNT, getDispCount());
         }
      }else if(EXmlConfig.Simple == type){
         if(RString.isNotEmpty(getName())){
            config.set(PTY_NAME, getName());
         }
         if(RString.isNotEmpty(getLabel())){
            config.set(PTY_LABEL, getLabel());
         }
         if(RString.isNotEmpty(getNote())){
            config.set(PTY_NOTE, getNote());
         }
         if(RString.isNotEmpty(getDispMode())){
            config.set(PTY_DISP_MODE, getDispMode());
         }
         if(RString.isNotEmpty(getDispConfig())){
            config.set(PTY_DISP_CONFIG, getDispConfig());
         }
         if(RBoolean.parse(getDispFetch())){
            config.set(PTY_DISP_FETCH, RBoolean.toString(getDispFetch()));
         }
         if(RString.isNotEmpty(getPadLeft())){
            config.set(PTY_PAD_LEFT, getPadLeft());
         }
         if(RString.isNotEmpty(getPadTop())){
            config.set(PTY_PAD_TOP, getPadTop());
         }
         if(RString.isNotEmpty(getPadBottom())){
            config.set(PTY_PAD_BOTTOM, getPadBottom());
         }
         if(RString.isNotEmpty(getPadRight())){
            config.set(PTY_PAD_RIGHT, getPadRight());
         }
         if(RString.isNotEmpty(getLeft())){
            config.set(PTY_LEFT, getLeft());
         }
         if(RString.isNotEmpty(getTop())){
            config.set(PTY_TOP, getTop());
         }
         if(RString.isNotEmpty(getRight())){
            config.set(PTY_RIGHT, getRight());
         }
         if(RString.isNotEmpty(getBottom())){
            config.set(PTY_BOTTOM, getBottom());
         }
         if(RString.isNotEmpty(getWidth())){
            config.set(PTY_WIDTH, getWidth());
         }
         if(RString.isNotEmpty(getHeight())){
            config.set(PTY_HEIGHT, getHeight());
         }
         if(RBoolean.parse(getNowrap())){
            config.set(PTY_NOWRAP, RBoolean.toString(getNowrap()));
         }
         if(RString.isNotEmpty(getHint())){
            config.set(PTY_HINT, getHint());
         }
         if(RString.isNotEmpty(getStyleLabel())){
            config.set(PTY_STYLE_LABEL, getStyleLabel());
         }
         if(RString.isNotEmpty(getStyleEdit())){
            config.set(PTY_STYLE_EDIT, getStyleEdit());
         }
         if(RString.isNotEmpty(getVersion())){
            config.set(PTY_VERSION, getVersion());
         }
         if(RString.isNotEmpty(getDataset())){
            config.set(PTY_DATASET, getDataset());
         }
         if(RString.isNotEmpty(getDatasetService())){
            config.set(PTY_DATASET_SERVICE, getDatasetService());
         }
         if(RString.isNotEmpty(getDatasetPrepare())){
            config.set(PTY_DATASET_PREPARE, getDatasetPrepare());
         }
         if(RString.isNotEmpty(getDatasetInsertParameters())){
            config.set(PTY_DATASET_INSERT_PARAMETERS, getDatasetInsertParameters());
         }
         if(RString.isNotEmpty(getDatasetInsert())){
            config.set(PTY_DATASET_INSERT, getDatasetInsert());
         }
         if(RString.isNotEmpty(getDatasetUpdateParameters())){
            config.set(PTY_DATASET_UPDATE_PARAMETERS, getDatasetUpdateParameters());
         }
         if(RString.isNotEmpty(getDatasetUpdate())){
            config.set(PTY_DATASET_UPDATE, getDatasetUpdate());
         }
         if(RString.isNotEmpty(getDatasetDeleteParameters())){
            config.set(PTY_DATASET_DELETE_PARAMETERS, getDatasetDeleteParameters());
         }
         if(RString.isNotEmpty(getDatasetDelete())){
            config.set(PTY_DATASET_DELETE, getDatasetDelete());
         }
         if(RString.isNotEmpty(getEditMode())){
            config.set(PTY_EDIT_MODE, getEditMode());
         }
         if(RString.isNotEmpty(getEditConfig())){
            config.set(PTY_EDIT_CONFIG, getEditConfig());
         }
         if(RString.isNotEmpty(getValidAccess())){
            config.set(PTY_VALID_ACCESS, getValidAccess());
         }
         if(RString.isNotEmpty(getActionInsert())){
            config.set(PTY_ACTION_INSERT, getActionInsert());
         }
         if(RString.isNotEmpty(getActionUpdate())){
            config.set(PTY_ACTION_UPDATE, getActionUpdate());
         }
         if(RString.isNotEmpty(getActionDelete())){
            config.set(PTY_ACTION_DELETE, getActionDelete());
         }
         if(RString.isNotEmpty(getActionPrepare())){
            config.set(PTY_ACTION_PREPARE, getActionPrepare());
         }
         if(RBoolean.parse(getDispToolbar())){
            config.set(PTY_DISP_TOOLBAR, RBoolean.toString(getDispToolbar()));
         }
         if(RString.isNotEmpty(getLgName())){
            config.set(PTY_LG_NAME, getLgName());
         }
         if(RString.isNotEmpty(getPanelAccess())){
            config.set(PTY_PANEL_ACCESS, getPanelAccess());
         }
         if(RString.isNotEmpty(getDispRowbar())){
            config.set(PTY_DISP_ROWBAR, getDispRowbar());
         }
         if(RString.isNotEmpty(getQueryPrepare())){
            config.set(PTY_QUERY_PREPARE, getQueryPrepare());
         }
         if(RBoolean.parse(getFormLinked())){
            config.set(PTY_FORM_LINKED, RBoolean.toString(getFormLinked()));
         }
         if(RString.isNotEmpty(getFormCustom())){
            config.set(PTY_FORM_CUSTOM, getFormCustom());
         }
         if(RString.isNotEmpty(getFormParameter())){
            config.set(PTY_FORM_PARAMETER, getFormParameter());
         }
         if(RString.isNotEmpty(getDispSelected())){
            config.set(PTY_DISP_SELECTED, getDispSelected());
         }
         if(RString.isNotEmpty(getAction())){
            config.set(PTY_ACTION, getAction());
         }
         if(RString.isNotEmpty(getFormName())){
            config.set(PTY_FORM_NAME, getFormName());
         }
         if(RString.isNotEmpty(getPageSize())){
            config.set(PTY_PAGE_SIZE, getPageSize());
         }
         if(RString.isNotEmpty(getFormRefer())){
            config.set(PTY_FORM_REFER, getFormRefer());
         }
         if(RString.isNotEmpty(getFormReferWhere())){
            config.set(PTY_FORM_REFER_WHERE, getFormReferWhere());
         }
         if(RString.isNotEmpty(getFormLink())){
            config.set(PTY_FORM_LINK, getFormLink());
         }
         if(RString.isNotEmpty(getFormSearch())){
            config.set(PTY_FORM_SEARCH, getFormSearch());
         }
         if(RString.isNotEmpty(getFormOrder())){
            config.set(PTY_FORM_ORDER, getFormOrder());
         }
         if(RString.isNotEmpty(getDispCount())){
            config.set(PTY_DISP_COUNT, getDispCount());
         }
      }else if(EXmlConfig.Value == type){
         String sName = getName();
         if(RString.isNotEmpty(sName)){
            config.set(PTY_NAME, sName);
         }
         String sLabel = getLabel();
         if(RString.isNotEmpty(sLabel)){
            config.set(PTY_LABEL, sLabel);
         }
         Boolean bIsValid = getIsValid();
         if(RBoolean.parse(bIsValid)){
            config.set(PTY_IS_VALID, RBoolean.toString(bIsValid));
         }
         String sNote = getNote();
         if(RString.isNotEmpty(sNote)){
            config.set(PTY_NOTE, sNote);
         }
         String sDispMode = getDispMode();
         if(RString.isNotEmpty(sDispMode)){
            config.set(PTY_DISP_MODE, sDispMode);
         }
         String sDispConfig = getDispConfig();
         if(RString.isNotEmpty(sDispConfig)){
            config.set(PTY_DISP_CONFIG, sDispConfig);
         }
         Boolean bDispFetch = getDispFetch();
         if(RBoolean.parse(bDispFetch)){
            config.set(PTY_DISP_FETCH, RBoolean.toString(bDispFetch));
         }
         Boolean bDispSearch = getDispSearch();
         if(RBoolean.parse(bDispSearch)){
            config.set(PTY_DISP_SEARCH, RBoolean.toString(bDispSearch));
         }
         Boolean bDispPicker = getDispPicker();
         if(RBoolean.parse(bDispPicker)){
            config.set(PTY_DISP_PICKER, RBoolean.toString(bDispPicker));
         }
         Boolean bDispLov = getDispLov();
         if(RBoolean.parse(bDispLov)){
            config.set(PTY_DISP_LOV, RBoolean.toString(bDispLov));
         }
         Boolean bDispZoom = getDispZoom();
         if(RBoolean.parse(bDispZoom)){
            config.set(PTY_DISP_ZOOM, RBoolean.toString(bDispZoom));
         }
         Boolean bDispList = getDispList();
         if(RBoolean.parse(bDispList)){
            config.set(PTY_DISP_LIST, RBoolean.toString(bDispList));
         }
         Boolean bDispDisplay = getDispDisplay();
         if(RBoolean.parse(bDispDisplay)){
            config.set(PTY_DISP_DISPLAY, RBoolean.toString(bDispDisplay));
         }
         Boolean bDispInsert = getDispInsert();
         if(RBoolean.parse(bDispInsert)){
            config.set(PTY_DISP_INSERT, RBoolean.toString(bDispInsert));
         }
         Boolean bDispUpdate = getDispUpdate();
         if(RBoolean.parse(bDispUpdate)){
            config.set(PTY_DISP_UPDATE, RBoolean.toString(bDispUpdate));
         }
         Boolean bDispDelete = getDispDelete();
         if(RBoolean.parse(bDispDelete)){
            config.set(PTY_DISP_DELETE, RBoolean.toString(bDispDelete));
         }
         Boolean bDispAuto = getDispAuto();
         if(RBoolean.parse(bDispAuto)){
            config.set(PTY_DISP_AUTO, RBoolean.toString(bDispAuto));
         }
         Boolean bDispSize = getDispSize();
         if(RBoolean.parse(bDispSize)){
            config.set(PTY_DISP_SIZE, RBoolean.toString(bDispSize));
         }
         Boolean bDispDrag = getDispDrag();
         if(RBoolean.parse(bDispDrag)){
            config.set(PTY_DISP_DRAG, RBoolean.toString(bDispDrag));
         }
         Boolean bDispPrint = getDispPrint();
         if(RBoolean.parse(bDispPrint)){
            config.set(PTY_DISP_PRINT, RBoolean.toString(bDispPrint));
         }
         String sPadLeft = getPadLeft();
         if(RString.isNotEmpty(sPadLeft)){
            config.set(PTY_PAD_LEFT, sPadLeft);
         }
         String sPadTop = getPadTop();
         if(RString.isNotEmpty(sPadTop)){
            config.set(PTY_PAD_TOP, sPadTop);
         }
         String sPadBottom = getPadBottom();
         if(RString.isNotEmpty(sPadBottom)){
            config.set(PTY_PAD_BOTTOM, sPadBottom);
         }
         String sPadRight = getPadRight();
         if(RString.isNotEmpty(sPadRight)){
            config.set(PTY_PAD_RIGHT, sPadRight);
         }
         String sLeft = getLeft();
         if(RString.isNotEmpty(sLeft)){
            config.set(PTY_LEFT, sLeft);
         }
         String sTop = getTop();
         if(RString.isNotEmpty(sTop)){
            config.set(PTY_TOP, sTop);
         }
         String sRight = getRight();
         if(RString.isNotEmpty(sRight)){
            config.set(PTY_RIGHT, sRight);
         }
         String sBottom = getBottom();
         if(RString.isNotEmpty(sBottom)){
            config.set(PTY_BOTTOM, sBottom);
         }
         String sWidth = getWidth();
         if(RString.isNotEmpty(sWidth)){
            config.set(PTY_WIDTH, sWidth);
         }
         String sHeight = getHeight();
         if(RString.isNotEmpty(sHeight)){
            config.set(PTY_HEIGHT, sHeight);
         }
         Boolean bNowrap = getNowrap();
         if(RBoolean.parse(bNowrap)){
            config.set(PTY_NOWRAP, RBoolean.toString(bNowrap));
         }
         String sHint = getHint();
         if(RString.isNotEmpty(sHint)){
            config.set(PTY_HINT, sHint);
         }
         String sStyleRefer = getStyleRefer();
         if(RString.isNotEmpty(sStyleRefer)){
            config.set(PTY_STYLE_REFER, sStyleRefer);
         }
         String sStyleLabel = getStyleLabel();
         if(RString.isNotEmpty(sStyleLabel)){
            config.set(PTY_STYLE_LABEL, sStyleLabel);
         }
         String sStyleEdit = getStyleEdit();
         if(RString.isNotEmpty(sStyleEdit)){
            config.set(PTY_STYLE_EDIT, sStyleEdit);
         }
         String sVersion = getVersion();
         if(RString.isNotEmpty(sVersion)){
            config.set(PTY_VERSION, sVersion);
         }
         String sDataset = getDataset();
         if(RString.isNotEmpty(sDataset)){
            config.set(PTY_DATASET, sDataset);
         }
         String sDatasetFrom = getDatasetFrom();
         if(RString.isNotEmpty(sDatasetFrom)){
            config.set(PTY_DATASET_FROM, sDatasetFrom);
         }
         String sDatasetProcedure = getDatasetProcedure();
         if(RString.isNotEmpty(sDatasetProcedure)){
            config.set(PTY_DATASET_PROCEDURE, sDatasetProcedure);
         }
         String sDatasetService = getDatasetService();
         if(RString.isNotEmpty(sDatasetService)){
            config.set(PTY_DATASET_SERVICE, sDatasetService);
         }
         String sDatasetSearch = getDatasetSearch();
         if(RString.isNotEmpty(sDatasetSearch)){
            config.set(PTY_DATASET_SEARCH, sDatasetSearch);
         }
         String sDatasetGroup = getDatasetGroup();
         if(RString.isNotEmpty(sDatasetGroup)){
            config.set(PTY_DATASET_GROUP, sDatasetGroup);
         }
         String sDatasetOrder = getDatasetOrder();
         if(RString.isNotEmpty(sDatasetOrder)){
            config.set(PTY_DATASET_ORDER, sDatasetOrder);
         }
         String sDatasetParameters = getDatasetParameters();
         if(RString.isNotEmpty(sDatasetParameters)){
            config.set(PTY_DATASET_PARAMETERS, sDatasetParameters);
         }
         String sDatasetPrepare = getDatasetPrepare();
         if(RString.isNotEmpty(sDatasetPrepare)){
            config.set(PTY_DATASET_PREPARE, sDatasetPrepare);
         }
         String sDatasetInsertParameters = getDatasetInsertParameters();
         if(RString.isNotEmpty(sDatasetInsertParameters)){
            config.set(PTY_DATASET_INSERT_PARAMETERS, sDatasetInsertParameters);
         }
         String sDatasetInsert = getDatasetInsert();
         if(RString.isNotEmpty(sDatasetInsert)){
            config.set(PTY_DATASET_INSERT, sDatasetInsert);
         }
         String sDatasetUpdateParameters = getDatasetUpdateParameters();
         if(RString.isNotEmpty(sDatasetUpdateParameters)){
            config.set(PTY_DATASET_UPDATE_PARAMETERS, sDatasetUpdateParameters);
         }
         String sDatasetUpdate = getDatasetUpdate();
         if(RString.isNotEmpty(sDatasetUpdate)){
            config.set(PTY_DATASET_UPDATE, sDatasetUpdate);
         }
         String sDatasetDeleteParameters = getDatasetDeleteParameters();
         if(RString.isNotEmpty(sDatasetDeleteParameters)){
            config.set(PTY_DATASET_DELETE_PARAMETERS, sDatasetDeleteParameters);
         }
         String sDatasetDelete = getDatasetDelete();
         if(RString.isNotEmpty(sDatasetDelete)){
            config.set(PTY_DATASET_DELETE, sDatasetDelete);
         }
         String sEditMode = getEditMode();
         if(RString.isNotEmpty(sEditMode)){
            config.set(PTY_EDIT_MODE, sEditMode);
         }
         String sEditConfig = getEditConfig();
         if(RString.isNotEmpty(sEditConfig)){
            config.set(PTY_EDIT_CONFIG, sEditConfig);
         }
         Boolean bEditCopy = getEditCopy();
         if(RBoolean.parse(bEditCopy)){
            config.set(PTY_EDIT_COPY, RBoolean.toString(bEditCopy));
         }
         Boolean bEditFetch = getEditFetch();
         if(RBoolean.parse(bEditFetch)){
            config.set(PTY_EDIT_FETCH, RBoolean.toString(bEditFetch));
         }
         Boolean bEditSearch = getEditSearch();
         if(RBoolean.parse(bEditSearch)){
            config.set(PTY_EDIT_SEARCH, RBoolean.toString(bEditSearch));
         }
         Boolean bEditPicker = getEditPicker();
         if(RBoolean.parse(bEditPicker)){
            config.set(PTY_EDIT_PICKER, RBoolean.toString(bEditPicker));
         }
         Boolean bEditZoom = getEditZoom();
         if(RBoolean.parse(bEditZoom)){
            config.set(PTY_EDIT_ZOOM, RBoolean.toString(bEditZoom));
         }
         Boolean bEditPrint = getEditPrint();
         if(RBoolean.parse(bEditPrint)){
            config.set(PTY_EDIT_PRINT, RBoolean.toString(bEditPrint));
         }
         Boolean bEditAction = getEditAction();
         if(RBoolean.parse(bEditAction)){
            config.set(PTY_EDIT_ACTION, RBoolean.toString(bEditAction));
         }
         Boolean bEditInsert = getEditInsert();
         if(RBoolean.parse(bEditInsert)){
            config.set(PTY_EDIT_INSERT, RBoolean.toString(bEditInsert));
         }
         Boolean bEditUpdate = getEditUpdate();
         if(RBoolean.parse(bEditUpdate)){
            config.set(PTY_EDIT_UPDATE, RBoolean.toString(bEditUpdate));
         }
         Boolean bEditDelete = getEditDelete();
         if(RBoolean.parse(bEditDelete)){
            config.set(PTY_EDIT_DELETE, RBoolean.toString(bEditDelete));
         }
         String sValidAccess = getValidAccess();
         if(RString.isNotEmpty(sValidAccess)){
            config.set(PTY_VALID_ACCESS, sValidAccess);
         }
         Boolean bValidInsert = getValidInsert();
         if(RBoolean.parse(bValidInsert)){
            config.set(PTY_VALID_INSERT, RBoolean.toString(bValidInsert));
         }
         Boolean bValidUpdate = getValidUpdate();
         if(RBoolean.parse(bValidUpdate)){
            config.set(PTY_VALID_UPDATE, RBoolean.toString(bValidUpdate));
         }
         Boolean bValidDelete = getValidDelete();
         if(RBoolean.parse(bValidDelete)){
            config.set(PTY_VALID_DELETE, RBoolean.toString(bValidDelete));
         }
         String sActionInsert = getActionInsert();
         if(RString.isNotEmpty(sActionInsert)){
            config.set(PTY_ACTION_INSERT, sActionInsert);
         }
         String sActionUpdate = getActionUpdate();
         if(RString.isNotEmpty(sActionUpdate)){
            config.set(PTY_ACTION_UPDATE, sActionUpdate);
         }
         String sActionDelete = getActionDelete();
         if(RString.isNotEmpty(sActionDelete)){
            config.set(PTY_ACTION_DELETE, sActionDelete);
         }
         String sActionPrepare = getActionPrepare();
         if(RString.isNotEmpty(sActionPrepare)){
            config.set(PTY_ACTION_PREPARE, sActionPrepare);
         }
         Boolean bDispToolbar = getDispToolbar();
         if(RBoolean.parse(bDispToolbar)){
            config.set(PTY_DISP_TOOLBAR, RBoolean.toString(bDispToolbar));
         }
         String sLgName = getLgName();
         if(RString.isNotEmpty(sLgName)){
            config.set(PTY_LG_NAME, sLgName);
         }
         String sPanelAccess = getPanelAccess();
         if(RString.isNotEmpty(sPanelAccess)){
            config.set(PTY_PANEL_ACCESS, sPanelAccess);
         }
         String sDispRowbar = getDispRowbar();
         if(RString.isNotEmpty(sDispRowbar)){
            config.set(PTY_DISP_ROWBAR, sDispRowbar);
         }
         String sPanelTitle = getPanelTitle();
         if(RString.isNotEmpty(sPanelTitle)){
            config.set(PTY_PANEL_TITLE, sPanelTitle);
         }
         String sPanelHead = getPanelHead();
         if(RString.isNotEmpty(sPanelHead)){
            config.set(PTY_PANEL_HEAD, sPanelHead);
         }
         String sPanelSearch = getPanelSearch();
         if(RString.isNotEmpty(sPanelSearch)){
            config.set(PTY_PANEL_SEARCH, sPanelSearch);
         }
         String sPanelHint = getPanelHint();
         if(RString.isNotEmpty(sPanelHint)){
            config.set(PTY_PANEL_HINT, sPanelHint);
         }
         String sPanelTotal = getPanelTotal();
         if(RString.isNotEmpty(sPanelTotal)){
            config.set(PTY_PANEL_TOTAL, sPanelTotal);
         }
         String sQueryPrepare = getQueryPrepare();
         if(RString.isNotEmpty(sQueryPrepare)){
            config.set(PTY_QUERY_PREPARE, sQueryPrepare);
         }
         Boolean bFormLinked = getFormLinked();
         if(RBoolean.parse(bFormLinked)){
            config.set(PTY_FORM_LINKED, RBoolean.toString(bFormLinked));
         }
         String sFormCustom = getFormCustom();
         if(RString.isNotEmpty(sFormCustom)){
            config.set(PTY_FORM_CUSTOM, sFormCustom);
         }
         String sFormParameter = getFormParameter();
         if(RString.isNotEmpty(sFormParameter)){
            config.set(PTY_FORM_PARAMETER, sFormParameter);
         }
         String sDispSelected = getDispSelected();
         if(RString.isNotEmpty(sDispSelected)){
            config.set(PTY_DISP_SELECTED, sDispSelected);
         }
         String sAction = getAction();
         if(RString.isNotEmpty(sAction)){
            config.set(PTY_ACTION, sAction);
         }
         String sFormName = getFormName();
         if(RString.isNotEmpty(sFormName)){
            config.set(PTY_FORM_NAME, sFormName);
         }
         String sPageSize = getPageSize();
         if(RString.isNotEmpty(sPageSize)){
            config.set(PTY_PAGE_SIZE, sPageSize);
         }
         String sParentName = getParentName();
         if(RString.isNotEmpty(sParentName)){
            config.set(PTY_PARENT_NAME, sParentName);
         }
         String sFormRefer = getFormRefer();
         if(RString.isNotEmpty(sFormRefer)){
            config.set(PTY_FORM_REFER, sFormRefer);
         }
         String sFormReferWhere = getFormReferWhere();
         if(RString.isNotEmpty(sFormReferWhere)){
            config.set(PTY_FORM_REFER_WHERE, sFormReferWhere);
         }
         String sFormLink = getFormLink();
         if(RString.isNotEmpty(sFormLink)){
            config.set(PTY_FORM_LINK, sFormLink);
         }
         String sFormSearch = getFormSearch();
         if(RString.isNotEmpty(sFormSearch)){
            config.set(PTY_FORM_SEARCH, sFormSearch);
         }
         String sFormOrder = getFormOrder();
         if(RString.isNotEmpty(sFormOrder)){
            config.set(PTY_FORM_ORDER, sFormOrder);
         }
         String sDispCount = getDispCount();
         if(RString.isNotEmpty(sDispCount)){
            config.set(PTY_DISP_COUNT, sDispCount);
         }
      }else if(EXmlConfig.Default == type){
         String sPadLeft = getPadLeft();
         if(RString.isNotEmpty(sPadLeft)){
            config.set(PTY_PAD_LEFT, sPadLeft);
         }
         String sPadTop = getPadTop();
         if(RString.isNotEmpty(sPadTop)){
            config.set(PTY_PAD_TOP, sPadTop);
         }
         String sPadBottom = getPadBottom();
         if(RString.isNotEmpty(sPadBottom)){
            config.set(PTY_PAD_BOTTOM, sPadBottom);
         }
         String sPadRight = getPadRight();
         if(RString.isNotEmpty(sPadRight)){
            config.set(PTY_PAD_RIGHT, sPadRight);
         }
         String sLeft = getLeft();
         if(RString.isNotEmpty(sLeft)){
            config.set(PTY_LEFT, sLeft);
         }
         String sTop = getTop();
         if(RString.isNotEmpty(sTop)){
            config.set(PTY_TOP, sTop);
         }
         String sRight = getRight();
         if(RString.isNotEmpty(sRight)){
            config.set(PTY_RIGHT, sRight);
         }
         String sBottom = getBottom();
         if(RString.isNotEmpty(sBottom)){
            config.set(PTY_BOTTOM, sBottom);
         }
         String sWidth = getWidth();
         if(RString.isNotEmpty(sWidth)){
            config.set(PTY_WIDTH, sWidth);
         }
         String sHeight = getHeight();
         if(RString.isNotEmpty(sHeight)){
            config.set(PTY_HEIGHT, sHeight);
         }
         String sStyleLabel = getStyleLabel();
         if(RString.isNotEmpty(sStyleLabel)){
            config.set(PTY_STYLE_LABEL, sStyleLabel);
         }
         String sStyleEdit = getStyleEdit();
         if(RString.isNotEmpty(sStyleEdit)){
            config.set(PTY_STYLE_EDIT, sStyleEdit);
         }
         String sVersion = getVersion();
         if(RString.isNotEmpty(sVersion)){
            config.set(PTY_VERSION, sVersion);
         }
         String sDatasetPrepare = getDatasetPrepare();
         if(RString.isNotEmpty(sDatasetPrepare)){
            config.set(PTY_DATASET_PREPARE, sDatasetPrepare);
         }
         String sDatasetInsert = getDatasetInsert();
         if(RString.isNotEmpty(sDatasetInsert)){
            config.set(PTY_DATASET_INSERT, sDatasetInsert);
         }
         String sDatasetUpdate = getDatasetUpdate();
         if(RString.isNotEmpty(sDatasetUpdate)){
            config.set(PTY_DATASET_UPDATE, sDatasetUpdate);
         }
         String sDatasetDelete = getDatasetDelete();
         if(RString.isNotEmpty(sDatasetDelete)){
            config.set(PTY_DATASET_DELETE, sDatasetDelete);
         }
         String sActionPrepare = getActionPrepare();
         if(RString.isNotEmpty(sActionPrepare)){
            config.set(PTY_ACTION_PREPARE, sActionPrepare);
         }
         String sLgName = getLgName();
         if(RString.isNotEmpty(sLgName)){
            config.set(PTY_LG_NAME, sLgName);
         }
         String sDispRowbar = getDispRowbar();
         if(RString.isNotEmpty(sDispRowbar)){
            config.set(PTY_DISP_ROWBAR, sDispRowbar);
         }
         String sPanelTotal = getPanelTotal();
         if(RString.isNotEmpty(sPanelTotal)){
            config.set(PTY_PANEL_TOTAL, sPanelTotal);
         }
         String sFormReferWhere = getFormReferWhere();
         if(RString.isNotEmpty(sFormReferWhere)){
            config.set(PTY_FORM_REFER_WHERE, sFormReferWhere);
         }
         String sDispCount = getDispCount();
         if(RString.isNotEmpty(sDispCount)){
            config.set(PTY_DISP_COUNT, sDispCount);
         }
      }
   }
}