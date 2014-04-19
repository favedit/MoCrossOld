package org.mo.web.core.webform.base;

import org.mo.com.lang.FMultiString;
import org.mo.com.lang.RBoolean;
import org.mo.com.lang.RString;
import org.mo.com.lang.face.AName;
import org.mo.com.xml.EXmlConfig;
import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.FXmlObject;
import org.mo.com.xml.IXmlObject;
import org.mo.web.core.webform.control.XColumnEditControlFace;
import org.mo.web.core.webform.control.XDescEditFace;
import org.mo.web.core.webform.control.XListViewFace;

//============================================================
// <T>可编辑列对象的XML节点基类。</T>
//============================================================
public abstract class XBaseColumnEdit
      extends FXmlObject
      implements
         XColumnEditControlFace,
         XDescEditFace,
         XListViewFace
{
   // 名称定义
   public static final String NAME = "ColumnEdit";

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

   // 对齐方式的名称定义
   public static final String PTY_LABEL_ALIGN = "label_align";

   // 标签颜色的名称定义
   public static final String PTY_LABEL_COLOR = "label_color";

   // 标签图标的名称定义
   public static final String PTY_LABEL_ICON = "label_icon";

   // 是否查询的名称定义
   public static final String PTY_DATA_FETCH = "data_fetch";

   // 数据名称的名称定义
   public static final String PTY_DATA_NAME = "data_name";

   // 数据代码的名称定义
   public static final String PTY_DATA_CODE = "data_code";

   // 数据准备的名称定义
   public static final String PTY_DATA_PREPARE = "data_prepare";

   // 缺省内容的名称定义
   public static final String PTY_DATA_DEFAULT = "data_default";

   // 编辑模式的名称定义
   public static final String PTY_EDIT_MODE = "edit_mode";

   // 编辑设置的名称定义
   public static final String PTY_EDIT_CONFIG = "edit_config";

   // 搜索编辑可的名称定义
   public static final String PTY_EDIT_SEARCH = "edit_search";

   // 新建编辑可的名称定义
   public static final String PTY_EDIT_INSERT = "edit_insert";

   // 修改编辑可的名称定义
   public static final String PTY_EDIT_UPDATE = "edit_update";

   // 删除编辑可的名称定义
   public static final String PTY_EDIT_DELETE = "edit_delete";

   // 放大编辑可的名称定义
   public static final String PTY_EDIT_ZOOM = "edit_zoom";

   // 计量单位的名称定义
   public static final String PTY_EDIT_UNIT = "edit_unit";

   // 编辑提示的名称定义
   public static final String PTY_EDIT_TIP = "edit_tip";

   // 复制可的名称定义
   public static final String PTY_EDIT_COPY = "edit_copy";

   // 检查权限的名称定义
   public static final String PTY_VALID_ACCESS = "valid_access";

   // 新建检查可的名称定义
   public static final String PTY_VALID_INSERT = "valid_insert";

   // 更新检查可的名称定义
   public static final String PTY_VALID_UPDATE = "valid_update";

   // 删除检查可的名称定义
   public static final String PTY_VALID_DELETE = "valid_delete";

   // 检查必须的名称定义
   public static final String PTY_VALID_REQUIRE = "valid_require";

   // 数据内容的名称定义
   public static final String PTY_DATA_VALUE = "data_value";

   // 引用表单的名称定义
   public static final String PTY_ZOOM_REFER = "zoom_refer";

   // 放大字段的名称定义
   public static final String PTY_ZOOM_FIELD = "zoom_field";

   // 类型图片集的名称定义
   public static final String PTY_VIEW_ICONS = "view_icons";

   // 显示固定的名称定义
   public static final String PTY_DISP_FIXED = "disp_fixed";

   // 搜索方式的名称定义
   public static final String PTY_SEARCH_TYPE = "search_type";

   // 数据类型的名称定义
   public static final String PTY_DATA_TYPE = "data_type";

   // 字体颜色的名称定义
   public static final String PTY_EDIT_COLOR = "edit_color";

   // 背景颜色的名称定义
   public static final String PTY_EDIT_BGCOLOR = "edit_bgcolor";

   // 搜索字段的名称定义
   public static final String PTY_DATA_SEARCH = "data_search";

   // 排序可的名称定义
   public static final String PTY_ORDER_ABLE = "order_able";

   // 编辑父的名称定义
   public static final String PTY_EDIT_PARENT = "edit_parent";

   // 自动完成的名称定义
   public static final String PTY_EDIT_COMPLETE = "edit_complete";

   // 编辑对齐的名称定义
   public static final String PTY_EDIT_ALIGN = "edit_align";

   // 编辑长度的名称定义
   public static final String PTY_EDIT_LENGTH = "edit_length";

   // 输入格式的名称定义
   public static final String PTY_EDIT_CASE = "edit_case";

   // 输入模板的名称定义
   public static final String PTY_EDIT_PATTERN = "edit_pattern";

   // 编辑格式的名称定义
   public static final String PTY_EDIT_FORMAT = "edit_format";

   // 最大长度的名称定义
   public static final String PTY_VALID_LENMAX = "valid_lenmax";

   // 选取服务的名称定义
   public static final String PTY_LOV_SERVICE = "lov_service";

   // 选取引用的名称定义
   public static final String PTY_LOV_REFER = "lov_refer";

   // 选取字段的名称定义
   public static final String PTY_LOV_FIELDS = "lov_fields";

   // 选取条件的名称定义
   public static final String PTY_LOV_WHERE = "lov_where";

   // 选取顺序的名称定义
   public static final String PTY_LOV_ORDER = "lov_order";

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

   // 对齐方式的定义
   @AName("label_align")
   protected String _labelAlign;

   // 标签颜色的定义
   @AName("label_color")
   protected String _labelColor;

   // 标签图标的定义
   @AName("label_icon")
   protected String _labelIcon;

   // 是否查询的定义
   @AName("data_fetch")
   protected Boolean _dataFetch = Boolean.FALSE;

   // 数据名称的定义
   @AName("data_name")
   protected String _dataName;

   // 数据代码的定义
   @AName("data_code")
   protected String _dataCode;

   // 数据准备的定义
   @AName("data_prepare")
   protected String _dataPrepare;

   // 缺省内容的定义
   @AName("data_default")
   protected String _dataDefault;

   // 搜索编辑可的定义
   @AName("edit_search")
   protected Boolean _editSearch = Boolean.FALSE;

   // 新建编辑可的定义
   @AName("edit_insert")
   protected Boolean _editInsert = Boolean.FALSE;

   // 修改编辑可的定义
   @AName("edit_update")
   protected Boolean _editUpdate = Boolean.FALSE;

   // 删除编辑可的定义
   @AName("edit_delete")
   protected Boolean _editDelete = Boolean.FALSE;

   // 放大编辑可的定义
   @AName("edit_zoom")
   protected String _editZoom;

   // 计量单位的定义
   @AName("edit_unit")
   protected String _editUnit;

   // 编辑提示的定义
   @AName("edit_tip")
   protected String _editTip;

   // 复制可的定义
   @AName("edit_copy")
   protected Boolean _editCopy = Boolean.FALSE;

   // 新建检查可的定义
   @AName("valid_insert")
   protected Boolean _validInsert = Boolean.FALSE;

   // 更新检查可的定义
   @AName("valid_update")
   protected Boolean _validUpdate = Boolean.FALSE;

   // 删除检查可的定义
   @AName("valid_delete")
   protected Boolean _validDelete = Boolean.FALSE;

   // 检查必须的定义
   @AName("valid_require")
   protected Boolean _validRequire = Boolean.FALSE;

   // 数据内容的定义
   @AName("data_value")
   protected String _dataValue;

   // 引用表单的定义
   @AName("zoom_refer")
   protected String _zoomRefer;

   // 放大字段的定义
   @AName("zoom_field")
   protected String _zoomField;

   // 类型图片集的定义
   @AName("view_icons")
   protected String _viewIcons;

   // 显示固定的定义
   @AName("disp_fixed")
   protected Boolean _dispFixed = Boolean.FALSE;

   // 搜索方式的定义
   @AName("search_type")
   protected String _searchType;

   // 数据类型的定义
   @AName("data_type")
   protected String _dataType;

   // 字体颜色的定义
   @AName("edit_color")
   protected String _editColor;

   // 背景颜色的定义
   @AName("edit_bgcolor")
   protected String _editBgcolor;

   // 搜索字段的定义
   @AName("data_search")
   protected String _dataSearch;

   // 排序可的定义
   @AName("order_able")
   protected String _orderAble;

   // 编辑父的定义
   @AName("edit_parent")
   protected String _editParent;

   // 自动完成的定义
   @AName("edit_complete")
   protected Boolean _editComplete = Boolean.FALSE;

   // 编辑对齐的定义
   @AName("edit_align")
   protected String _editAlign;

   // 编辑长度的定义
   @AName("edit_length")
   protected String _editLength;

   // 输入格式的定义
   @AName("edit_case")
   protected String _editCase;

   // 输入模板的定义
   @AName("edit_pattern")
   protected String _editPattern;

   // 编辑格式的定义
   @AName("edit_format")
   protected String _editFormat;

   // 最大长度的定义
   @AName("valid_lenmax")
   protected String _validLenmax;

   // 选取服务的定义
   @AName("lov_service")
   protected String _lovService;

   // 选取引用的定义
   @AName("lov_refer")
   protected String _lovRefer;

   // 选取字段的定义
   @AName("lov_fields")
   protected String _lovFields;

   // 选取条件的定义
   @AName("lov_where")
   protected String _lovWhere;

   // 选取顺序的定义
   @AName("lov_order")
   protected String _lovOrder;

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
   // <T>获得对齐方式的内容。</T>
   //
   // @return 对齐方式
   //============================================================
   public String getLabelAlign(){
      return _labelAlign;
   }

   //============================================================
   // <T>设置对齐方式的内容。</T>
   //
   // @param value 对齐方式
   //============================================================
   public void setLabelAlign(String value){
      _labelAlign = value;
   }

   //============================================================
   // <T>获得标签颜色的内容。</T>
   //
   // @return 标签颜色
   //============================================================
   public String getLabelColor(){
      return _labelColor;
   }

   //============================================================
   // <T>设置标签颜色的内容。</T>
   //
   // @param value 标签颜色
   //============================================================
   public void setLabelColor(String value){
      _labelColor = value;
   }

   //============================================================
   // <T>获得标签图标的内容。</T>
   //
   // @return 标签图标
   //============================================================
   public String getLabelIcon(){
      return _labelIcon;
   }

   //============================================================
   // <T>设置标签图标的内容。</T>
   //
   // @param value 标签图标
   //============================================================
   public void setLabelIcon(String value){
      _labelIcon = value;
   }

   //============================================================
   // <T>获得是否查询的内容。</T>
   //
   // @return 是否查询
   //============================================================
   public Boolean getDataFetch(){
      return _dataFetch;
   }

   //============================================================
   // <T>设置是否查询的内容。</T>
   //
   // @param value 是否查询
   //============================================================
   public void setDataFetch(Boolean value){
      _dataFetch = value;
   }

   //============================================================
   // <T>获得数据名称的内容。</T>
   //
   // @return 数据名称
   //============================================================
   public String getDataName(){
      return _dataName;
   }

   //============================================================
   // <T>设置数据名称的内容。</T>
   //
   // @param value 数据名称
   //============================================================
   public void setDataName(String value){
      _dataName = value;
   }

   //============================================================
   // <T>获得数据代码的内容。</T>
   //
   // @return 数据代码
   //============================================================
   public String getDataCode(){
      return _dataCode;
   }

   //============================================================
   // <T>设置数据代码的内容。</T>
   //
   // @param value 数据代码
   //============================================================
   public void setDataCode(String value){
      _dataCode = value;
   }

   //============================================================
   // <T>获得数据准备的内容。</T>
   //
   // @return 数据准备
   //============================================================
   public String getDataPrepare(){
      return _dataPrepare;
   }

   //============================================================
   // <T>设置数据准备的内容。</T>
   //
   // @param value 数据准备
   //============================================================
   public void setDataPrepare(String value){
      _dataPrepare = value;
   }

   //============================================================
   // <T>获得缺省内容的内容。</T>
   //
   // @return 缺省内容
   //============================================================
   public String getDataDefault(){
      return _dataDefault;
   }

   //============================================================
   // <T>设置缺省内容的内容。</T>
   //
   // @param value 缺省内容
   //============================================================
   public void setDataDefault(String value){
      _dataDefault = value;
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
   // <T>获得放大编辑可的内容。</T>
   //
   // @return 放大编辑可
   //============================================================
   public String getEditZoom(){
      return _editZoom;
   }

   //============================================================
   // <T>设置放大编辑可的内容。</T>
   //
   // @param value 放大编辑可
   //============================================================
   public void setEditZoom(String value){
      _editZoom = value;
   }

   //============================================================
   // <T>获得计量单位的内容。</T>
   //
   // @return 计量单位
   //============================================================
   public String getEditUnit(){
      return _editUnit;
   }

   //============================================================
   // <T>设置计量单位的内容。</T>
   //
   // @param value 计量单位
   //============================================================
   public void setEditUnit(String value){
      _editUnit = value;
   }

   //============================================================
   // <T>获得编辑提示的内容。</T>
   //
   // @return 编辑提示
   //============================================================
   public String getEditTip(){
      return _editTip;
   }

   //============================================================
   // <T>设置编辑提示的内容。</T>
   //
   // @param value 编辑提示
   //============================================================
   public void setEditTip(String value){
      _editTip = value;
   }

   //============================================================
   // <T>获得复制可的内容。</T>
   //
   // @return 复制可
   //============================================================
   public Boolean getEditCopy(){
      return _editCopy;
   }

   //============================================================
   // <T>设置复制可的内容。</T>
   //
   // @param value 复制可
   //============================================================
   public void setEditCopy(Boolean value){
      _editCopy = value;
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
   // <T>获得检查必须的内容。</T>
   //
   // @return 检查必须
   //============================================================
   public Boolean getValidRequire(){
      return _validRequire;
   }

   //============================================================
   // <T>设置检查必须的内容。</T>
   //
   // @param value 检查必须
   //============================================================
   public void setValidRequire(Boolean value){
      _validRequire = value;
   }

   //============================================================
   // <T>获得数据内容的内容。</T>
   //
   // @return 数据内容
   //============================================================
   public String getDataValue(){
      return _dataValue;
   }

   //============================================================
   // <T>设置数据内容的内容。</T>
   //
   // @param value 数据内容
   //============================================================
   public void setDataValue(String value){
      _dataValue = value;
   }

   //============================================================
   // <T>获得引用表单的内容。</T>
   //
   // @return 引用表单
   //============================================================
   public String getZoomRefer(){
      return _zoomRefer;
   }

   //============================================================
   // <T>设置引用表单的内容。</T>
   //
   // @param value 引用表单
   //============================================================
   public void setZoomRefer(String value){
      _zoomRefer = value;
   }

   //============================================================
   // <T>获得放大字段的内容。</T>
   //
   // @return 放大字段
   //============================================================
   public String getZoomField(){
      return _zoomField;
   }

   //============================================================
   // <T>设置放大字段的内容。</T>
   //
   // @param value 放大字段
   //============================================================
   public void setZoomField(String value){
      _zoomField = value;
   }

   //============================================================
   // <T>获得类型图片集的内容。</T>
   //
   // @return 类型图片集
   //============================================================
   public String getViewIcons(){
      return _viewIcons;
   }

   //============================================================
   // <T>设置类型图片集的内容。</T>
   //
   // @param value 类型图片集
   //============================================================
   public void setViewIcons(String value){
      _viewIcons = value;
   }

   //============================================================
   // <T>获得显示固定的内容。</T>
   //
   // @return 显示固定
   //============================================================
   public Boolean getDispFixed(){
      return _dispFixed;
   }

   //============================================================
   // <T>设置显示固定的内容。</T>
   //
   // @param value 显示固定
   //============================================================
   public void setDispFixed(Boolean value){
      _dispFixed = value;
   }

   //============================================================
   // <T>获得搜索方式的内容。</T>
   //
   // @return 搜索方式
   //============================================================
   public String getSearchType(){
      return _searchType;
   }

   //============================================================
   // <T>设置搜索方式的内容。</T>
   //
   // @param value 搜索方式
   //============================================================
   public void setSearchType(String value){
      _searchType = value;
   }

   //============================================================
   // <T>获得数据类型的内容。</T>
   //
   // @return 数据类型
   //============================================================
   public String getDataType(){
      return _dataType;
   }

   //============================================================
   // <T>设置数据类型的内容。</T>
   //
   // @param value 数据类型
   //============================================================
   public void setDataType(String value){
      _dataType = value;
   }

   //============================================================
   // <T>获得字体颜色的内容。</T>
   //
   // @return 字体颜色
   //============================================================
   public String getEditColor(){
      return _editColor;
   }

   //============================================================
   // <T>设置字体颜色的内容。</T>
   //
   // @param value 字体颜色
   //============================================================
   public void setEditColor(String value){
      _editColor = value;
   }

   //============================================================
   // <T>获得背景颜色的内容。</T>
   //
   // @return 背景颜色
   //============================================================
   public String getEditBgcolor(){
      return _editBgcolor;
   }

   //============================================================
   // <T>设置背景颜色的内容。</T>
   //
   // @param value 背景颜色
   //============================================================
   public void setEditBgcolor(String value){
      _editBgcolor = value;
   }

   //============================================================
   // <T>获得搜索字段的内容。</T>
   //
   // @return 搜索字段
   //============================================================
   public String getDataSearch(){
      return _dataSearch;
   }

   //============================================================
   // <T>设置搜索字段的内容。</T>
   //
   // @param value 搜索字段
   //============================================================
   public void setDataSearch(String value){
      _dataSearch = value;
   }

   //============================================================
   // <T>获得排序可的内容。</T>
   //
   // @return 排序可
   //============================================================
   public String getOrderAble(){
      return _orderAble;
   }

   //============================================================
   // <T>设置排序可的内容。</T>
   //
   // @param value 排序可
   //============================================================
   public void setOrderAble(String value){
      _orderAble = value;
   }

   //============================================================
   // <T>获得编辑父的内容。</T>
   //
   // @return 编辑父
   //============================================================
   public String getEditParent(){
      return _editParent;
   }

   //============================================================
   // <T>设置编辑父的内容。</T>
   //
   // @param value 编辑父
   //============================================================
   public void setEditParent(String value){
      _editParent = value;
   }

   //============================================================
   // <T>获得自动完成的内容。</T>
   //
   // @return 自动完成
   //============================================================
   public Boolean getEditComplete(){
      return _editComplete;
   }

   //============================================================
   // <T>设置自动完成的内容。</T>
   //
   // @param value 自动完成
   //============================================================
   public void setEditComplete(Boolean value){
      _editComplete = value;
   }

   //============================================================
   // <T>获得编辑对齐的内容。</T>
   //
   // @return 编辑对齐
   //============================================================
   public String getEditAlign(){
      return _editAlign;
   }

   //============================================================
   // <T>设置编辑对齐的内容。</T>
   //
   // @param value 编辑对齐
   //============================================================
   public void setEditAlign(String value){
      _editAlign = value;
   }

   //============================================================
   // <T>获得编辑长度的内容。</T>
   //
   // @return 编辑长度
   //============================================================
   public String getEditLength(){
      return _editLength;
   }

   //============================================================
   // <T>设置编辑长度的内容。</T>
   //
   // @param value 编辑长度
   //============================================================
   public void setEditLength(String value){
      _editLength = value;
   }

   //============================================================
   // <T>获得输入格式的内容。</T>
   //
   // @return 输入格式
   //============================================================
   public String getEditCase(){
      return _editCase;
   }

   //============================================================
   // <T>设置输入格式的内容。</T>
   //
   // @param value 输入格式
   //============================================================
   public void setEditCase(String value){
      _editCase = value;
   }

   //============================================================
   // <T>获得输入模板的内容。</T>
   //
   // @return 输入模板
   //============================================================
   public String getEditPattern(){
      return _editPattern;
   }

   //============================================================
   // <T>设置输入模板的内容。</T>
   //
   // @param value 输入模板
   //============================================================
   public void setEditPattern(String value){
      _editPattern = value;
   }

   //============================================================
   // <T>获得编辑格式的内容。</T>
   //
   // @return 编辑格式
   //============================================================
   public String getEditFormat(){
      return _editFormat;
   }

   //============================================================
   // <T>设置编辑格式的内容。</T>
   //
   // @param value 编辑格式
   //============================================================
   public void setEditFormat(String value){
      _editFormat = value;
   }

   //============================================================
   // <T>获得最大长度的内容。</T>
   //
   // @return 最大长度
   //============================================================
   public String getValidLenmax(){
      return _validLenmax;
   }

   //============================================================
   // <T>设置最大长度的内容。</T>
   //
   // @param value 最大长度
   //============================================================
   public void setValidLenmax(String value){
      _validLenmax = value;
   }

   //============================================================
   // <T>获得选取服务的内容。</T>
   //
   // @return 选取服务
   //============================================================
   public String getLovService(){
      return _lovService;
   }

   //============================================================
   // <T>设置选取服务的内容。</T>
   //
   // @param value 选取服务
   //============================================================
   public void setLovService(String value){
      _lovService = value;
   }

   //============================================================
   // <T>获得选取引用的内容。</T>
   //
   // @return 选取引用
   //============================================================
   public String getLovRefer(){
      return _lovRefer;
   }

   //============================================================
   // <T>设置选取引用的内容。</T>
   //
   // @param value 选取引用
   //============================================================
   public void setLovRefer(String value){
      _lovRefer = value;
   }

   //============================================================
   // <T>获得选取字段的内容。</T>
   //
   // @return 选取字段
   //============================================================
   public String getLovFields(){
      return _lovFields;
   }

   //============================================================
   // <T>设置选取字段的内容。</T>
   //
   // @param value 选取字段
   //============================================================
   public void setLovFields(String value){
      _lovFields = value;
   }

   //============================================================
   // <T>获得选取条件的内容。</T>
   //
   // @return 选取条件
   //============================================================
   public String getLovWhere(){
      return _lovWhere;
   }

   //============================================================
   // <T>设置选取条件的内容。</T>
   //
   // @param value 选取条件
   //============================================================
   public void setLovWhere(String value){
      _lovWhere = value;
   }

   //============================================================
   // <T>获得选取顺序的内容。</T>
   //
   // @return 选取顺序
   //============================================================
   public String getLovOrder(){
      return _lovOrder;
   }

   //============================================================
   // <T>设置选取顺序的内容。</T>
   //
   // @param value 选取顺序
   //============================================================
   public void setLovOrder(String value){
      _lovOrder = value;
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
      }else if(PTY_LABEL_ALIGN.equalsIgnoreCase(name)){
         return getLabelAlign();
      }else if(PTY_LABEL_COLOR.equalsIgnoreCase(name)){
         return getLabelColor();
      }else if(PTY_LABEL_ICON.equalsIgnoreCase(name)){
         return getLabelIcon();
      }else if(PTY_DATA_FETCH.equalsIgnoreCase(name)){
         return RBoolean.toString(getDataFetch());
      }else if(PTY_DATA_NAME.equalsIgnoreCase(name)){
         return getDataName();
      }else if(PTY_DATA_CODE.equalsIgnoreCase(name)){
         return getDataCode();
      }else if(PTY_DATA_PREPARE.equalsIgnoreCase(name)){
         return getDataPrepare();
      }else if(PTY_DATA_DEFAULT.equalsIgnoreCase(name)){
         return getDataDefault();
      }else if(PTY_EDIT_MODE.equalsIgnoreCase(name)){
         return getEditMode();
      }else if(PTY_EDIT_CONFIG.equalsIgnoreCase(name)){
         return getEditConfig();
      }else if(PTY_EDIT_SEARCH.equalsIgnoreCase(name)){
         return RBoolean.toString(getEditSearch());
      }else if(PTY_EDIT_INSERT.equalsIgnoreCase(name)){
         return RBoolean.toString(getEditInsert());
      }else if(PTY_EDIT_UPDATE.equalsIgnoreCase(name)){
         return RBoolean.toString(getEditUpdate());
      }else if(PTY_EDIT_DELETE.equalsIgnoreCase(name)){
         return RBoolean.toString(getEditDelete());
      }else if(PTY_EDIT_ZOOM.equalsIgnoreCase(name)){
         return getEditZoom();
      }else if(PTY_EDIT_UNIT.equalsIgnoreCase(name)){
         return getEditUnit();
      }else if(PTY_EDIT_TIP.equalsIgnoreCase(name)){
         return getEditTip();
      }else if(PTY_EDIT_COPY.equalsIgnoreCase(name)){
         return RBoolean.toString(getEditCopy());
      }else if(PTY_VALID_ACCESS.equalsIgnoreCase(name)){
         return getValidAccess();
      }else if(PTY_VALID_INSERT.equalsIgnoreCase(name)){
         return RBoolean.toString(getValidInsert());
      }else if(PTY_VALID_UPDATE.equalsIgnoreCase(name)){
         return RBoolean.toString(getValidUpdate());
      }else if(PTY_VALID_DELETE.equalsIgnoreCase(name)){
         return RBoolean.toString(getValidDelete());
      }else if(PTY_VALID_REQUIRE.equalsIgnoreCase(name)){
         return RBoolean.toString(getValidRequire());
      }else if(PTY_DATA_VALUE.equalsIgnoreCase(name)){
         return getDataValue();
      }else if(PTY_ZOOM_REFER.equalsIgnoreCase(name)){
         return getZoomRefer();
      }else if(PTY_ZOOM_FIELD.equalsIgnoreCase(name)){
         return getZoomField();
      }else if(PTY_VIEW_ICONS.equalsIgnoreCase(name)){
         return getViewIcons();
      }else if(PTY_DISP_FIXED.equalsIgnoreCase(name)){
         return RBoolean.toString(getDispFixed());
      }else if(PTY_SEARCH_TYPE.equalsIgnoreCase(name)){
         return getSearchType();
      }else if(PTY_DATA_TYPE.equalsIgnoreCase(name)){
         return getDataType();
      }else if(PTY_EDIT_COLOR.equalsIgnoreCase(name)){
         return getEditColor();
      }else if(PTY_EDIT_BGCOLOR.equalsIgnoreCase(name)){
         return getEditBgcolor();
      }else if(PTY_DATA_SEARCH.equalsIgnoreCase(name)){
         return getDataSearch();
      }else if(PTY_ORDER_ABLE.equalsIgnoreCase(name)){
         return getOrderAble();
      }else if(PTY_EDIT_PARENT.equalsIgnoreCase(name)){
         return getEditParent();
      }else if(PTY_EDIT_COMPLETE.equalsIgnoreCase(name)){
         return RBoolean.toString(getEditComplete());
      }else if(PTY_EDIT_ALIGN.equalsIgnoreCase(name)){
         return getEditAlign();
      }else if(PTY_EDIT_LENGTH.equalsIgnoreCase(name)){
         return getEditLength();
      }else if(PTY_EDIT_CASE.equalsIgnoreCase(name)){
         return getEditCase();
      }else if(PTY_EDIT_PATTERN.equalsIgnoreCase(name)){
         return getEditPattern();
      }else if(PTY_EDIT_FORMAT.equalsIgnoreCase(name)){
         return getEditFormat();
      }else if(PTY_VALID_LENMAX.equalsIgnoreCase(name)){
         return getValidLenmax();
      }else if(PTY_LOV_SERVICE.equalsIgnoreCase(name)){
         return getLovService();
      }else if(PTY_LOV_REFER.equalsIgnoreCase(name)){
         return getLovRefer();
      }else if(PTY_LOV_FIELDS.equalsIgnoreCase(name)){
         return getLovFields();
      }else if(PTY_LOV_WHERE.equalsIgnoreCase(name)){
         return getLovWhere();
      }else if(PTY_LOV_ORDER.equalsIgnoreCase(name)){
         return getLovOrder();
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
      }else if(PTY_LABEL_ALIGN.equalsIgnoreCase(name)){
         setLabelAlign(value);
      }else if(PTY_LABEL_COLOR.equalsIgnoreCase(name)){
         setLabelColor(value);
      }else if(PTY_LABEL_ICON.equalsIgnoreCase(name)){
         setLabelIcon(value);
      }else if(PTY_DATA_FETCH.equalsIgnoreCase(name)){
         setDataFetch(RBoolean.parse(value));
      }else if(PTY_DATA_NAME.equalsIgnoreCase(name)){
         setDataName(value);
      }else if(PTY_DATA_CODE.equalsIgnoreCase(name)){
         setDataCode(value);
      }else if(PTY_DATA_PREPARE.equalsIgnoreCase(name)){
         setDataPrepare(value);
      }else if(PTY_DATA_DEFAULT.equalsIgnoreCase(name)){
         setDataDefault(value);
      }else if(PTY_EDIT_MODE.equalsIgnoreCase(name)){
         setEditMode(value);
      }else if(PTY_EDIT_CONFIG.equalsIgnoreCase(name)){
         setEditConfig(value);
      }else if(PTY_EDIT_SEARCH.equalsIgnoreCase(name)){
         setEditSearch(RBoolean.parse(value));
      }else if(PTY_EDIT_INSERT.equalsIgnoreCase(name)){
         setEditInsert(RBoolean.parse(value));
      }else if(PTY_EDIT_UPDATE.equalsIgnoreCase(name)){
         setEditUpdate(RBoolean.parse(value));
      }else if(PTY_EDIT_DELETE.equalsIgnoreCase(name)){
         setEditDelete(RBoolean.parse(value));
      }else if(PTY_EDIT_ZOOM.equalsIgnoreCase(name)){
         setEditZoom(value);
      }else if(PTY_EDIT_UNIT.equalsIgnoreCase(name)){
         setEditUnit(value);
      }else if(PTY_EDIT_TIP.equalsIgnoreCase(name)){
         setEditTip(value);
      }else if(PTY_EDIT_COPY.equalsIgnoreCase(name)){
         setEditCopy(RBoolean.parse(value));
      }else if(PTY_VALID_ACCESS.equalsIgnoreCase(name)){
         setValidAccess(value);
      }else if(PTY_VALID_INSERT.equalsIgnoreCase(name)){
         setValidInsert(RBoolean.parse(value));
      }else if(PTY_VALID_UPDATE.equalsIgnoreCase(name)){
         setValidUpdate(RBoolean.parse(value));
      }else if(PTY_VALID_DELETE.equalsIgnoreCase(name)){
         setValidDelete(RBoolean.parse(value));
      }else if(PTY_VALID_REQUIRE.equalsIgnoreCase(name)){
         setValidRequire(RBoolean.parse(value));
      }else if(PTY_DATA_VALUE.equalsIgnoreCase(name)){
         setDataValue(value);
      }else if(PTY_ZOOM_REFER.equalsIgnoreCase(name)){
         setZoomRefer(value);
      }else if(PTY_ZOOM_FIELD.equalsIgnoreCase(name)){
         setZoomField(value);
      }else if(PTY_VIEW_ICONS.equalsIgnoreCase(name)){
         setViewIcons(value);
      }else if(PTY_DISP_FIXED.equalsIgnoreCase(name)){
         setDispFixed(RBoolean.parse(value));
      }else if(PTY_SEARCH_TYPE.equalsIgnoreCase(name)){
         setSearchType(value);
      }else if(PTY_DATA_TYPE.equalsIgnoreCase(name)){
         setDataType(value);
      }else if(PTY_EDIT_COLOR.equalsIgnoreCase(name)){
         setEditColor(value);
      }else if(PTY_EDIT_BGCOLOR.equalsIgnoreCase(name)){
         setEditBgcolor(value);
      }else if(PTY_DATA_SEARCH.equalsIgnoreCase(name)){
         setDataSearch(value);
      }else if(PTY_ORDER_ABLE.equalsIgnoreCase(name)){
         setOrderAble(value);
      }else if(PTY_EDIT_PARENT.equalsIgnoreCase(name)){
         setEditParent(value);
      }else if(PTY_EDIT_COMPLETE.equalsIgnoreCase(name)){
         setEditComplete(RBoolean.parse(value));
      }else if(PTY_EDIT_ALIGN.equalsIgnoreCase(name)){
         setEditAlign(value);
      }else if(PTY_EDIT_LENGTH.equalsIgnoreCase(name)){
         setEditLength(value);
      }else if(PTY_EDIT_CASE.equalsIgnoreCase(name)){
         setEditCase(value);
      }else if(PTY_EDIT_PATTERN.equalsIgnoreCase(name)){
         setEditPattern(value);
      }else if(PTY_EDIT_FORMAT.equalsIgnoreCase(name)){
         setEditFormat(value);
      }else if(PTY_VALID_LENMAX.equalsIgnoreCase(name)){
         setValidLenmax(value);
      }else if(PTY_LOV_SERVICE.equalsIgnoreCase(name)){
         setLovService(value);
      }else if(PTY_LOV_REFER.equalsIgnoreCase(name)){
         setLovRefer(value);
      }else if(PTY_LOV_FIELDS.equalsIgnoreCase(name)){
         setLovFields(value);
      }else if(PTY_LOV_WHERE.equalsIgnoreCase(name)){
         setLovWhere(value);
      }else if(PTY_LOV_ORDER.equalsIgnoreCase(name)){
         setLovOrder(value);
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
         if(config.contains("label_align")){
            setLabelAlign(config.get(PTY_LABEL_ALIGN));
         }
         if(config.contains("label_color")){
            setLabelColor(config.get(PTY_LABEL_COLOR));
         }
         if(config.contains("label_icon")){
            setLabelIcon(config.get(PTY_LABEL_ICON));
         }
         if(config.contains("data_fetch")){
            setDataFetch(RBoolean.parse(config.get(PTY_DATA_FETCH)));
         }
         if(config.contains("data_name")){
            setDataName(config.get(PTY_DATA_NAME));
         }
         if(config.contains("data_code")){
            setDataCode(config.get(PTY_DATA_CODE));
         }
         if(config.contains("data_prepare")){
            setDataPrepare(config.get(PTY_DATA_PREPARE));
         }
         if(config.contains("data_default")){
            setDataDefault(config.get(PTY_DATA_DEFAULT));
         }
         if(config.contains("edit_search")){
            setEditSearch(RBoolean.parse(config.get(PTY_EDIT_SEARCH)));
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
         if(config.contains("edit_zoom")){
            setEditZoom(config.get(PTY_EDIT_ZOOM));
         }
         if(config.contains("edit_unit")){
            setEditUnit(config.get(PTY_EDIT_UNIT));
         }
         if(config.contains("edit_tip")){
            setEditTip(config.get(PTY_EDIT_TIP));
         }
         if(config.contains("edit_copy")){
            setEditCopy(RBoolean.parse(config.get(PTY_EDIT_COPY)));
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
         if(config.contains("valid_require")){
            setValidRequire(RBoolean.parse(config.get(PTY_VALID_REQUIRE)));
         }
         if(config.contains("data_value")){
            setDataValue(config.get(PTY_DATA_VALUE));
         }
         if(config.contains("zoom_refer")){
            setZoomRefer(config.get(PTY_ZOOM_REFER));
         }
         if(config.contains("zoom_field")){
            setZoomField(config.get(PTY_ZOOM_FIELD));
         }
         if(config.contains("view_icons")){
            setViewIcons(config.get(PTY_VIEW_ICONS));
         }
         if(config.contains("disp_fixed")){
            setDispFixed(RBoolean.parse(config.get(PTY_DISP_FIXED)));
         }
         if(config.contains("search_type")){
            setSearchType(config.get(PTY_SEARCH_TYPE));
         }
         if(config.contains("data_type")){
            setDataType(config.get(PTY_DATA_TYPE));
         }
         if(config.contains("edit_color")){
            setEditColor(config.get(PTY_EDIT_COLOR));
         }
         if(config.contains("edit_bgcolor")){
            setEditBgcolor(config.get(PTY_EDIT_BGCOLOR));
         }
         if(config.contains("data_search")){
            setDataSearch(config.get(PTY_DATA_SEARCH));
         }
         if(config.contains("order_able")){
            setOrderAble(config.get(PTY_ORDER_ABLE));
         }
         if(config.contains("edit_parent")){
            setEditParent(config.get(PTY_EDIT_PARENT));
         }
         if(config.contains("edit_complete")){
            setEditComplete(RBoolean.parse(config.get(PTY_EDIT_COMPLETE)));
         }
         if(config.contains("edit_align")){
            setEditAlign(config.get(PTY_EDIT_ALIGN));
         }
         if(config.contains("edit_length")){
            setEditLength(config.get(PTY_EDIT_LENGTH));
         }
         if(config.contains("edit_case")){
            setEditCase(config.get(PTY_EDIT_CASE));
         }
         if(config.contains("edit_pattern")){
            setEditPattern(config.get(PTY_EDIT_PATTERN));
         }
         if(config.contains("edit_format")){
            setEditFormat(config.get(PTY_EDIT_FORMAT));
         }
         if(config.contains("valid_lenmax")){
            setValidLenmax(config.get(PTY_VALID_LENMAX));
         }
         if(config.contains("lov_service")){
            setLovService(config.get(PTY_LOV_SERVICE));
         }
         if(config.contains("lov_refer")){
            setLovRefer(config.get(PTY_LOV_REFER));
         }
         if(config.contains("lov_fields")){
            setLovFields(config.get(PTY_LOV_FIELDS));
         }
         if(config.contains("lov_where")){
            setLovWhere(config.get(PTY_LOV_WHERE));
         }
         if(config.contains("lov_order")){
            setLovOrder(config.get(PTY_LOV_ORDER));
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
         if(config.contains("label_align")){
            setLabelAlign(config.get(PTY_LABEL_ALIGN));
         }
         if(config.contains("label_color")){
            setLabelColor(config.get(PTY_LABEL_COLOR));
         }
         if(config.contains("label_icon")){
            setLabelIcon(config.get(PTY_LABEL_ICON));
         }
         if(config.contains("data_name")){
            setDataName(config.get(PTY_DATA_NAME));
         }
         if(config.contains("data_code")){
            setDataCode(config.get(PTY_DATA_CODE));
         }
         if(config.contains("data_prepare")){
            setDataPrepare(config.get(PTY_DATA_PREPARE));
         }
         if(config.contains("data_default")){
            setDataDefault(config.get(PTY_DATA_DEFAULT));
         }
         if(config.contains("edit_mode")){
            setEditMode(config.get(PTY_EDIT_MODE));
         }
         if(config.contains("edit_config")){
            setEditConfig(config.get(PTY_EDIT_CONFIG));
         }
         if(config.contains("edit_unit")){
            setEditUnit(config.get(PTY_EDIT_UNIT));
         }
         if(config.contains("edit_tip")){
            setEditTip(config.get(PTY_EDIT_TIP));
         }
         if(config.contains("valid_access")){
            setValidAccess(config.get(PTY_VALID_ACCESS));
         }
         if(config.contains("valid_require")){
            setValidRequire(RBoolean.parse(config.get(PTY_VALID_REQUIRE)));
         }
         if(config.contains("zoom_refer")){
            setZoomRefer(config.get(PTY_ZOOM_REFER));
         }
         if(config.contains("zoom_field")){
            setZoomField(config.get(PTY_ZOOM_FIELD));
         }
         if(config.contains("view_icons")){
            setViewIcons(config.get(PTY_VIEW_ICONS));
         }
         if(config.contains("search_type")){
            setSearchType(config.get(PTY_SEARCH_TYPE));
         }
         if(config.contains("data_type")){
            setDataType(config.get(PTY_DATA_TYPE));
         }
         if(config.contains("edit_color")){
            setEditColor(config.get(PTY_EDIT_COLOR));
         }
         if(config.contains("edit_bgcolor")){
            setEditBgcolor(config.get(PTY_EDIT_BGCOLOR));
         }
         if(config.contains("data_search")){
            setDataSearch(config.get(PTY_DATA_SEARCH));
         }
         if(config.contains("order_able")){
            setOrderAble(config.get(PTY_ORDER_ABLE));
         }
         if(config.contains("edit_parent")){
            setEditParent(config.get(PTY_EDIT_PARENT));
         }
         if(config.contains("edit_complete")){
            setEditComplete(RBoolean.parse(config.get(PTY_EDIT_COMPLETE)));
         }
         if(config.contains("edit_align")){
            setEditAlign(config.get(PTY_EDIT_ALIGN));
         }
         if(config.contains("edit_length")){
            setEditLength(config.get(PTY_EDIT_LENGTH));
         }
         if(config.contains("edit_case")){
            setEditCase(config.get(PTY_EDIT_CASE));
         }
         if(config.contains("edit_pattern")){
            setEditPattern(config.get(PTY_EDIT_PATTERN));
         }
         if(config.contains("edit_format")){
            setEditFormat(config.get(PTY_EDIT_FORMAT));
         }
         if(config.contains("valid_lenmax")){
            setValidLenmax(config.get(PTY_VALID_LENMAX));
         }
         if(config.contains("lov_service")){
            setLovService(config.get(PTY_LOV_SERVICE));
         }
         if(config.contains("lov_refer")){
            setLovRefer(config.get(PTY_LOV_REFER));
         }
         if(config.contains("lov_fields")){
            setLovFields(config.get(PTY_LOV_FIELDS));
         }
         if(config.contains("lov_where")){
            setLovWhere(config.get(PTY_LOV_WHERE));
         }
         if(config.contains("lov_order")){
            setLovOrder(config.get(PTY_LOV_ORDER));
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
         if(config.contains("label_align")){
            setLabelAlign(config.get(PTY_LABEL_ALIGN));
         }
         if(config.contains("label_color")){
            setLabelColor(config.get(PTY_LABEL_COLOR));
         }
         if(config.contains("label_icon")){
            setLabelIcon(config.get(PTY_LABEL_ICON));
         }
         if(config.contains("data_fetch")){
            setDataFetch(RBoolean.parse(config.get(PTY_DATA_FETCH)));
         }
         if(config.contains("data_name")){
            setDataName(config.get(PTY_DATA_NAME));
         }
         if(config.contains("data_code")){
            setDataCode(config.get(PTY_DATA_CODE));
         }
         if(config.contains("data_prepare")){
            setDataPrepare(config.get(PTY_DATA_PREPARE));
         }
         if(config.contains("data_default")){
            setDataDefault(config.get(PTY_DATA_DEFAULT));
         }
         if(config.contains("edit_mode")){
            setEditMode(config.get(PTY_EDIT_MODE));
         }
         if(config.contains("edit_config")){
            setEditConfig(config.get(PTY_EDIT_CONFIG));
         }
         if(config.contains("edit_search")){
            setEditSearch(RBoolean.parse(config.get(PTY_EDIT_SEARCH)));
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
         if(config.contains("edit_zoom")){
            setEditZoom(config.get(PTY_EDIT_ZOOM));
         }
         if(config.contains("edit_unit")){
            setEditUnit(config.get(PTY_EDIT_UNIT));
         }
         if(config.contains("edit_tip")){
            setEditTip(config.get(PTY_EDIT_TIP));
         }
         if(config.contains("edit_copy")){
            setEditCopy(RBoolean.parse(config.get(PTY_EDIT_COPY)));
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
         if(config.contains("valid_require")){
            setValidRequire(RBoolean.parse(config.get(PTY_VALID_REQUIRE)));
         }
         if(config.contains("data_value")){
            setDataValue(config.get(PTY_DATA_VALUE));
         }
         if(config.contains("zoom_refer")){
            setZoomRefer(config.get(PTY_ZOOM_REFER));
         }
         if(config.contains("zoom_field")){
            setZoomField(config.get(PTY_ZOOM_FIELD));
         }
         if(config.contains("view_icons")){
            setViewIcons(config.get(PTY_VIEW_ICONS));
         }
         if(config.contains("disp_fixed")){
            setDispFixed(RBoolean.parse(config.get(PTY_DISP_FIXED)));
         }
         if(config.contains("search_type")){
            setSearchType(config.get(PTY_SEARCH_TYPE));
         }
         if(config.contains("data_type")){
            setDataType(config.get(PTY_DATA_TYPE));
         }
         if(config.contains("edit_color")){
            setEditColor(config.get(PTY_EDIT_COLOR));
         }
         if(config.contains("edit_bgcolor")){
            setEditBgcolor(config.get(PTY_EDIT_BGCOLOR));
         }
         if(config.contains("data_search")){
            setDataSearch(config.get(PTY_DATA_SEARCH));
         }
         if(config.contains("order_able")){
            setOrderAble(config.get(PTY_ORDER_ABLE));
         }
         if(config.contains("edit_parent")){
            setEditParent(config.get(PTY_EDIT_PARENT));
         }
         if(config.contains("edit_complete")){
            setEditComplete(RBoolean.parse(config.get(PTY_EDIT_COMPLETE)));
         }
         if(config.contains("edit_align")){
            setEditAlign(config.get(PTY_EDIT_ALIGN));
         }
         if(config.contains("edit_length")){
            setEditLength(config.get(PTY_EDIT_LENGTH));
         }
         if(config.contains("edit_case")){
            setEditCase(config.get(PTY_EDIT_CASE));
         }
         if(config.contains("edit_pattern")){
            setEditPattern(config.get(PTY_EDIT_PATTERN));
         }
         if(config.contains("edit_format")){
            setEditFormat(config.get(PTY_EDIT_FORMAT));
         }
         if(config.contains("valid_lenmax")){
            setValidLenmax(config.get(PTY_VALID_LENMAX));
         }
         if(config.contains("lov_service")){
            setLovService(config.get(PTY_LOV_SERVICE));
         }
         if(config.contains("lov_refer")){
            setLovRefer(config.get(PTY_LOV_REFER));
         }
         if(config.contains("lov_fields")){
            setLovFields(config.get(PTY_LOV_FIELDS));
         }
         if(config.contains("lov_where")){
            setLovWhere(config.get(PTY_LOV_WHERE));
         }
         if(config.contains("lov_order")){
            setLovOrder(config.get(PTY_LOV_ORDER));
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
         if(RString.isEmpty(getLabelAlign())){
            if(config.contains("label_align")){
               setLabelAlign(config.get(PTY_LABEL_ALIGN));
            }
         }
         if(RString.isEmpty(getLabelColor())){
            if(config.contains("label_color")){
               setLabelColor(config.get(PTY_LABEL_COLOR));
            }
         }
         if(RString.isEmpty(getDataCode())){
            if(config.contains("data_code")){
               setDataCode(config.get(PTY_DATA_CODE));
            }
         }
         if(RString.isEmpty(getDataPrepare())){
            if(config.contains("data_prepare")){
               setDataPrepare(config.get(PTY_DATA_PREPARE));
            }
         }
         if(RString.isEmpty(getEditZoom())){
            if(config.contains("edit_zoom")){
               setEditZoom(config.get(PTY_EDIT_ZOOM));
            }
         }
         if(RString.isEmpty(getEditUnit())){
            if(config.contains("edit_unit")){
               setEditUnit(config.get(PTY_EDIT_UNIT));
            }
         }
         if(RString.isEmpty(getZoomField())){
            if(config.contains("zoom_field")){
               setZoomField(config.get(PTY_ZOOM_FIELD));
            }
         }
         if(RString.isEmpty(getViewIcons())){
            if(config.contains("view_icons")){
               setViewIcons(config.get(PTY_VIEW_ICONS));
            }
         }
         if(RString.isEmpty(getSearchType())){
            if(config.contains("search_type")){
               setSearchType(config.get(PTY_SEARCH_TYPE));
            }
         }
         if(RString.isEmpty(getDataType())){
            if(config.contains("data_type")){
               setDataType(config.get(PTY_DATA_TYPE));
            }
         }
         if(RString.isEmpty(getEditColor())){
            if(config.contains("edit_color")){
               setEditColor(config.get(PTY_EDIT_COLOR));
            }
         }
         if(RString.isEmpty(getEditBgcolor())){
            if(config.contains("edit_bgcolor")){
               setEditBgcolor(config.get(PTY_EDIT_BGCOLOR));
            }
         }
         if(RString.isEmpty(getEditParent())){
            if(config.contains("edit_parent")){
               setEditParent(config.get(PTY_EDIT_PARENT));
            }
         }
         if(RString.isEmpty(getEditAlign())){
            if(config.contains("edit_align")){
               setEditAlign(config.get(PTY_EDIT_ALIGN));
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
         if(RString.isNotEmpty(getLabelAlign())){
            config.set(PTY_LABEL_ALIGN, getLabelAlign());
         }
         if(RString.isNotEmpty(getLabelColor())){
            config.set(PTY_LABEL_COLOR, getLabelColor());
         }
         if(RString.isNotEmpty(getLabelIcon())){
            config.set(PTY_LABEL_ICON, getLabelIcon());
         }
         if(RBoolean.parse(getDataFetch())){
            config.set(PTY_DATA_FETCH, RBoolean.toString(getDataFetch()));
         }
         if(RString.isNotEmpty(getDataName())){
            config.set(PTY_DATA_NAME, getDataName());
         }
         if(RString.isNotEmpty(getDataCode())){
            config.set(PTY_DATA_CODE, getDataCode());
         }
         if(RString.isNotEmpty(getDataPrepare())){
            config.set(PTY_DATA_PREPARE, getDataPrepare());
         }
         if(RString.isNotEmpty(getDataDefault())){
            config.set(PTY_DATA_DEFAULT, getDataDefault());
         }
         if(RBoolean.parse(getEditSearch())){
            config.set(PTY_EDIT_SEARCH, RBoolean.toString(getEditSearch()));
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
         if(RString.isNotEmpty(getEditZoom())){
            config.set(PTY_EDIT_ZOOM, getEditZoom());
         }
         if(RString.isNotEmpty(getEditUnit())){
            config.set(PTY_EDIT_UNIT, getEditUnit());
         }
         if(RString.isNotEmpty(getEditTip())){
            config.set(PTY_EDIT_TIP, getEditTip());
         }
         if(RBoolean.parse(getEditCopy())){
            config.set(PTY_EDIT_COPY, RBoolean.toString(getEditCopy()));
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
         if(RBoolean.parse(getValidRequire())){
            config.set(PTY_VALID_REQUIRE, RBoolean.toString(getValidRequire()));
         }
         if(RString.isNotEmpty(getDataValue())){
            config.set(PTY_DATA_VALUE, getDataValue());
         }
         if(RString.isNotEmpty(getZoomRefer())){
            config.set(PTY_ZOOM_REFER, getZoomRefer());
         }
         if(RString.isNotEmpty(getZoomField())){
            config.set(PTY_ZOOM_FIELD, getZoomField());
         }
         if(RString.isNotEmpty(getViewIcons())){
            config.set(PTY_VIEW_ICONS, getViewIcons());
         }
         if(RBoolean.parse(getDispFixed())){
            config.set(PTY_DISP_FIXED, RBoolean.toString(getDispFixed()));
         }
         if(RString.isNotEmpty(getSearchType())){
            config.set(PTY_SEARCH_TYPE, getSearchType());
         }
         if(RString.isNotEmpty(getDataType())){
            config.set(PTY_DATA_TYPE, getDataType());
         }
         if(RString.isNotEmpty(getEditColor())){
            config.set(PTY_EDIT_COLOR, getEditColor());
         }
         if(RString.isNotEmpty(getEditBgcolor())){
            config.set(PTY_EDIT_BGCOLOR, getEditBgcolor());
         }
         if(RString.isNotEmpty(getDataSearch())){
            config.set(PTY_DATA_SEARCH, getDataSearch());
         }
         if(RString.isNotEmpty(getOrderAble())){
            config.set(PTY_ORDER_ABLE, getOrderAble());
         }
         if(RString.isNotEmpty(getEditParent())){
            config.set(PTY_EDIT_PARENT, getEditParent());
         }
         if(RBoolean.parse(getEditComplete())){
            config.set(PTY_EDIT_COMPLETE, RBoolean.toString(getEditComplete()));
         }
         if(RString.isNotEmpty(getEditAlign())){
            config.set(PTY_EDIT_ALIGN, getEditAlign());
         }
         if(RString.isNotEmpty(getEditLength())){
            config.set(PTY_EDIT_LENGTH, getEditLength());
         }
         if(RString.isNotEmpty(getEditCase())){
            config.set(PTY_EDIT_CASE, getEditCase());
         }
         if(RString.isNotEmpty(getEditPattern())){
            config.set(PTY_EDIT_PATTERN, getEditPattern());
         }
         if(RString.isNotEmpty(getEditFormat())){
            config.set(PTY_EDIT_FORMAT, getEditFormat());
         }
         if(RString.isNotEmpty(getValidLenmax())){
            config.set(PTY_VALID_LENMAX, getValidLenmax());
         }
         if(RString.isNotEmpty(getLovService())){
            config.set(PTY_LOV_SERVICE, getLovService());
         }
         if(RString.isNotEmpty(getLovRefer())){
            config.set(PTY_LOV_REFER, getLovRefer());
         }
         if(RString.isNotEmpty(getLovFields())){
            config.set(PTY_LOV_FIELDS, getLovFields());
         }
         if(RString.isNotEmpty(getLovWhere())){
            config.set(PTY_LOV_WHERE, getLovWhere());
         }
         if(RString.isNotEmpty(getLovOrder())){
            config.set(PTY_LOV_ORDER, getLovOrder());
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
         if(RString.isNotEmpty(getLabelAlign())){
            config.set(PTY_LABEL_ALIGN, getLabelAlign());
         }
         if(RString.isNotEmpty(getLabelColor())){
            config.set(PTY_LABEL_COLOR, getLabelColor());
         }
         if(RString.isNotEmpty(getLabelIcon())){
            config.set(PTY_LABEL_ICON, getLabelIcon());
         }
         if(RString.isNotEmpty(getDataName())){
            config.set(PTY_DATA_NAME, getDataName());
         }
         if(RString.isNotEmpty(getDataCode())){
            config.set(PTY_DATA_CODE, getDataCode());
         }
         if(RString.isNotEmpty(getDataPrepare())){
            config.set(PTY_DATA_PREPARE, getDataPrepare());
         }
         if(RString.isNotEmpty(getDataDefault())){
            config.set(PTY_DATA_DEFAULT, getDataDefault());
         }
         if(RString.isNotEmpty(getEditMode())){
            config.set(PTY_EDIT_MODE, getEditMode());
         }
         if(RString.isNotEmpty(getEditConfig())){
            config.set(PTY_EDIT_CONFIG, getEditConfig());
         }
         if(RString.isNotEmpty(getEditUnit())){
            config.set(PTY_EDIT_UNIT, getEditUnit());
         }
         if(RString.isNotEmpty(getEditTip())){
            config.set(PTY_EDIT_TIP, getEditTip());
         }
         if(RString.isNotEmpty(getValidAccess())){
            config.set(PTY_VALID_ACCESS, getValidAccess());
         }
         if(RBoolean.parse(getValidRequire())){
            config.set(PTY_VALID_REQUIRE, RBoolean.toString(getValidRequire()));
         }
         if(RString.isNotEmpty(getZoomRefer())){
            config.set(PTY_ZOOM_REFER, getZoomRefer());
         }
         if(RString.isNotEmpty(getZoomField())){
            config.set(PTY_ZOOM_FIELD, getZoomField());
         }
         if(RString.isNotEmpty(getViewIcons())){
            config.set(PTY_VIEW_ICONS, getViewIcons());
         }
         if(RString.isNotEmpty(getSearchType())){
            config.set(PTY_SEARCH_TYPE, getSearchType());
         }
         if(RString.isNotEmpty(getDataType())){
            config.set(PTY_DATA_TYPE, getDataType());
         }
         if(RString.isNotEmpty(getEditColor())){
            config.set(PTY_EDIT_COLOR, getEditColor());
         }
         if(RString.isNotEmpty(getEditBgcolor())){
            config.set(PTY_EDIT_BGCOLOR, getEditBgcolor());
         }
         if(RString.isNotEmpty(getDataSearch())){
            config.set(PTY_DATA_SEARCH, getDataSearch());
         }
         if(RString.isNotEmpty(getOrderAble())){
            config.set(PTY_ORDER_ABLE, getOrderAble());
         }
         if(RString.isNotEmpty(getEditParent())){
            config.set(PTY_EDIT_PARENT, getEditParent());
         }
         if(RBoolean.parse(getEditComplete())){
            config.set(PTY_EDIT_COMPLETE, RBoolean.toString(getEditComplete()));
         }
         if(RString.isNotEmpty(getEditAlign())){
            config.set(PTY_EDIT_ALIGN, getEditAlign());
         }
         if(RString.isNotEmpty(getEditLength())){
            config.set(PTY_EDIT_LENGTH, getEditLength());
         }
         if(RString.isNotEmpty(getEditCase())){
            config.set(PTY_EDIT_CASE, getEditCase());
         }
         if(RString.isNotEmpty(getEditPattern())){
            config.set(PTY_EDIT_PATTERN, getEditPattern());
         }
         if(RString.isNotEmpty(getEditFormat())){
            config.set(PTY_EDIT_FORMAT, getEditFormat());
         }
         if(RString.isNotEmpty(getValidLenmax())){
            config.set(PTY_VALID_LENMAX, getValidLenmax());
         }
         if(RString.isNotEmpty(getLovService())){
            config.set(PTY_LOV_SERVICE, getLovService());
         }
         if(RString.isNotEmpty(getLovRefer())){
            config.set(PTY_LOV_REFER, getLovRefer());
         }
         if(RString.isNotEmpty(getLovFields())){
            config.set(PTY_LOV_FIELDS, getLovFields());
         }
         if(RString.isNotEmpty(getLovWhere())){
            config.set(PTY_LOV_WHERE, getLovWhere());
         }
         if(RString.isNotEmpty(getLovOrder())){
            config.set(PTY_LOV_ORDER, getLovOrder());
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
         String sLabelAlign = getLabelAlign();
         if(RString.isNotEmpty(sLabelAlign)){
            config.set(PTY_LABEL_ALIGN, sLabelAlign);
         }
         String sLabelColor = getLabelColor();
         if(RString.isNotEmpty(sLabelColor)){
            config.set(PTY_LABEL_COLOR, sLabelColor);
         }
         String sLabelIcon = getLabelIcon();
         if(RString.isNotEmpty(sLabelIcon)){
            config.set(PTY_LABEL_ICON, sLabelIcon);
         }
         Boolean bDataFetch = getDataFetch();
         if(RBoolean.parse(bDataFetch)){
            config.set(PTY_DATA_FETCH, RBoolean.toString(bDataFetch));
         }
         String sDataName = getDataName();
         if(RString.isNotEmpty(sDataName)){
            config.set(PTY_DATA_NAME, sDataName);
         }
         String sDataCode = getDataCode();
         if(RString.isNotEmpty(sDataCode)){
            config.set(PTY_DATA_CODE, sDataCode);
         }
         String sDataPrepare = getDataPrepare();
         if(RString.isNotEmpty(sDataPrepare)){
            config.set(PTY_DATA_PREPARE, sDataPrepare);
         }
         String sDataDefault = getDataDefault();
         if(RString.isNotEmpty(sDataDefault)){
            config.set(PTY_DATA_DEFAULT, sDataDefault);
         }
         String sEditMode = getEditMode();
         if(RString.isNotEmpty(sEditMode)){
            config.set(PTY_EDIT_MODE, sEditMode);
         }
         String sEditConfig = getEditConfig();
         if(RString.isNotEmpty(sEditConfig)){
            config.set(PTY_EDIT_CONFIG, sEditConfig);
         }
         Boolean bEditSearch = getEditSearch();
         if(RBoolean.parse(bEditSearch)){
            config.set(PTY_EDIT_SEARCH, RBoolean.toString(bEditSearch));
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
         String sEditZoom = getEditZoom();
         if(RString.isNotEmpty(sEditZoom)){
            config.set(PTY_EDIT_ZOOM, sEditZoom);
         }
         String sEditUnit = getEditUnit();
         if(RString.isNotEmpty(sEditUnit)){
            config.set(PTY_EDIT_UNIT, sEditUnit);
         }
         String sEditTip = getEditTip();
         if(RString.isNotEmpty(sEditTip)){
            config.set(PTY_EDIT_TIP, sEditTip);
         }
         Boolean bEditCopy = getEditCopy();
         if(RBoolean.parse(bEditCopy)){
            config.set(PTY_EDIT_COPY, RBoolean.toString(bEditCopy));
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
         Boolean bValidRequire = getValidRequire();
         if(RBoolean.parse(bValidRequire)){
            config.set(PTY_VALID_REQUIRE, RBoolean.toString(bValidRequire));
         }
         String sDataValue = getDataValue();
         if(RString.isNotEmpty(sDataValue)){
            config.set(PTY_DATA_VALUE, sDataValue);
         }
         String sZoomRefer = getZoomRefer();
         if(RString.isNotEmpty(sZoomRefer)){
            config.set(PTY_ZOOM_REFER, sZoomRefer);
         }
         String sZoomField = getZoomField();
         if(RString.isNotEmpty(sZoomField)){
            config.set(PTY_ZOOM_FIELD, sZoomField);
         }
         String sViewIcons = getViewIcons();
         if(RString.isNotEmpty(sViewIcons)){
            config.set(PTY_VIEW_ICONS, sViewIcons);
         }
         Boolean bDispFixed = getDispFixed();
         if(RBoolean.parse(bDispFixed)){
            config.set(PTY_DISP_FIXED, RBoolean.toString(bDispFixed));
         }
         String sSearchType = getSearchType();
         if(RString.isNotEmpty(sSearchType)){
            config.set(PTY_SEARCH_TYPE, sSearchType);
         }
         String sDataType = getDataType();
         if(RString.isNotEmpty(sDataType)){
            config.set(PTY_DATA_TYPE, sDataType);
         }
         String sEditColor = getEditColor();
         if(RString.isNotEmpty(sEditColor)){
            config.set(PTY_EDIT_COLOR, sEditColor);
         }
         String sEditBgcolor = getEditBgcolor();
         if(RString.isNotEmpty(sEditBgcolor)){
            config.set(PTY_EDIT_BGCOLOR, sEditBgcolor);
         }
         String sDataSearch = getDataSearch();
         if(RString.isNotEmpty(sDataSearch)){
            config.set(PTY_DATA_SEARCH, sDataSearch);
         }
         String sOrderAble = getOrderAble();
         if(RString.isNotEmpty(sOrderAble)){
            config.set(PTY_ORDER_ABLE, sOrderAble);
         }
         String sEditParent = getEditParent();
         if(RString.isNotEmpty(sEditParent)){
            config.set(PTY_EDIT_PARENT, sEditParent);
         }
         Boolean bEditComplete = getEditComplete();
         if(RBoolean.parse(bEditComplete)){
            config.set(PTY_EDIT_COMPLETE, RBoolean.toString(bEditComplete));
         }
         String sEditAlign = getEditAlign();
         if(RString.isNotEmpty(sEditAlign)){
            config.set(PTY_EDIT_ALIGN, sEditAlign);
         }
         String sEditLength = getEditLength();
         if(RString.isNotEmpty(sEditLength)){
            config.set(PTY_EDIT_LENGTH, sEditLength);
         }
         String sEditCase = getEditCase();
         if(RString.isNotEmpty(sEditCase)){
            config.set(PTY_EDIT_CASE, sEditCase);
         }
         String sEditPattern = getEditPattern();
         if(RString.isNotEmpty(sEditPattern)){
            config.set(PTY_EDIT_PATTERN, sEditPattern);
         }
         String sEditFormat = getEditFormat();
         if(RString.isNotEmpty(sEditFormat)){
            config.set(PTY_EDIT_FORMAT, sEditFormat);
         }
         String sValidLenmax = getValidLenmax();
         if(RString.isNotEmpty(sValidLenmax)){
            config.set(PTY_VALID_LENMAX, sValidLenmax);
         }
         String sLovService = getLovService();
         if(RString.isNotEmpty(sLovService)){
            config.set(PTY_LOV_SERVICE, sLovService);
         }
         String sLovRefer = getLovRefer();
         if(RString.isNotEmpty(sLovRefer)){
            config.set(PTY_LOV_REFER, sLovRefer);
         }
         String sLovFields = getLovFields();
         if(RString.isNotEmpty(sLovFields)){
            config.set(PTY_LOV_FIELDS, sLovFields);
         }
         String sLovWhere = getLovWhere();
         if(RString.isNotEmpty(sLovWhere)){
            config.set(PTY_LOV_WHERE, sLovWhere);
         }
         String sLovOrder = getLovOrder();
         if(RString.isNotEmpty(sLovOrder)){
            config.set(PTY_LOV_ORDER, sLovOrder);
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
         String sLabelAlign = getLabelAlign();
         if(RString.isNotEmpty(sLabelAlign)){
            config.set(PTY_LABEL_ALIGN, sLabelAlign);
         }
         String sLabelColor = getLabelColor();
         if(RString.isNotEmpty(sLabelColor)){
            config.set(PTY_LABEL_COLOR, sLabelColor);
         }
         String sDataCode = getDataCode();
         if(RString.isNotEmpty(sDataCode)){
            config.set(PTY_DATA_CODE, sDataCode);
         }
         String sDataPrepare = getDataPrepare();
         if(RString.isNotEmpty(sDataPrepare)){
            config.set(PTY_DATA_PREPARE, sDataPrepare);
         }
         String sEditZoom = getEditZoom();
         if(RString.isNotEmpty(sEditZoom)){
            config.set(PTY_EDIT_ZOOM, sEditZoom);
         }
         String sEditUnit = getEditUnit();
         if(RString.isNotEmpty(sEditUnit)){
            config.set(PTY_EDIT_UNIT, sEditUnit);
         }
         String sZoomField = getZoomField();
         if(RString.isNotEmpty(sZoomField)){
            config.set(PTY_ZOOM_FIELD, sZoomField);
         }
         String sViewIcons = getViewIcons();
         if(RString.isNotEmpty(sViewIcons)){
            config.set(PTY_VIEW_ICONS, sViewIcons);
         }
         String sSearchType = getSearchType();
         if(RString.isNotEmpty(sSearchType)){
            config.set(PTY_SEARCH_TYPE, sSearchType);
         }
         String sDataType = getDataType();
         if(RString.isNotEmpty(sDataType)){
            config.set(PTY_DATA_TYPE, sDataType);
         }
         String sEditColor = getEditColor();
         if(RString.isNotEmpty(sEditColor)){
            config.set(PTY_EDIT_COLOR, sEditColor);
         }
         String sEditBgcolor = getEditBgcolor();
         if(RString.isNotEmpty(sEditBgcolor)){
            config.set(PTY_EDIT_BGCOLOR, sEditBgcolor);
         }
         String sEditParent = getEditParent();
         if(RString.isNotEmpty(sEditParent)){
            config.set(PTY_EDIT_PARENT, sEditParent);
         }
         String sEditAlign = getEditAlign();
         if(RString.isNotEmpty(sEditAlign)){
            config.set(PTY_EDIT_ALIGN, sEditAlign);
         }
      }
   }
}