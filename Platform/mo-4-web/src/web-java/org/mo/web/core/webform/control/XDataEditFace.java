/*
 * @(#)XDataEditFace.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.web.core.webform.control;

/**
 * <p>数据编辑接口对象的XML节点基类</p>
 *
 * @author system
 */
public interface XDataEditFace
      extends
         XDataFace
{

   String NAME = "IDataEdit";

   /**
    * 编辑权限的名称定义
    */
   String PTY_EDIT_ACCESS = "edit_access";

   /**
    * 自动完成的名称定义
    */
   String PTY_EDIT_COMPLETE = "edit_complete";

   /**
    * 编辑复制可的名称定义
    */
   String PTY_EDIT_COPY = "edit_copy";

   /**
    * 删除编辑可的名称定义
    */
   String PTY_EDIT_DELETE = "edit_delete";

   /**
    * 编辑高度的名称定义
    */
   String PTY_EDIT_HEIGHT = "edit_height";

   /**
    * 新建编辑可的名称定义
    */
   String PTY_EDIT_INSERT = "edit_insert";

   /**
    * 搜索编辑可的名称定义
    */
   String PTY_EDIT_SEARCH = "edit_search";

   /**
    * 编辑提示的名称定义
    */
   String PTY_EDIT_TIP = "edit_tip";

   /**
    * 计量单位的名称定义
    */
   String PTY_EDIT_UNIT = "edit_unit";

   /**
    * 修改编辑可的名称定义
    */
   String PTY_EDIT_UPDATE = "edit_update";

   /**
    * 编辑宽度的名称定义
    */
   String PTY_EDIT_WIDTH = "edit_width";

   /**
    * 自动完成服务的名称定义
    */
   String PTY_SERVICE_COMPLETE = "service_complete";

   /**
    * 检查服务的名称定义
    */
   String PTY_SERVICE_VALID = "service_valid";

   /**
    * 检查权限的名称定义
    */
   String PTY_VALID_ACCESS = "valid_access";

   /**
    * 删除检查可的名称定义
    */
   String PTY_VALID_DELETE = "valid_delete";

   /**
    * 新建检查可的名称定义
    */
   String PTY_VALID_INSERT = "valid_insert";

   /**
    * 检查必须的名称定义
    */
   String PTY_VALID_REQUIRE = "valid_require";

   /**
    * 更新检查可的名称定义
    */
   String PTY_VALID_UPDATE = "valid_update";

   /**
    * 获得编辑权限的内容。
    *
    * @return 编辑权限
    */
   String getEditAccess();

   /**
    * 获得自动完成的内容。
    *
    * @return 自动完成
    */
   String getEditComplete();

   /**
    * 获得编辑复制可的内容。
    *
    * @return 编辑复制可
    */
   String getEditCopy();

   /**
    * 获得删除编辑可的内容。
    *
    * @return 删除编辑可
    */
   Boolean getEditDelete();

   /**
    * 获得编辑高度的内容。
    *
    * @return 编辑高度
    */
   String getEditHeight();

   /**
    * 获得新建编辑可的内容。
    *
    * @return 新建编辑可
    */
   Boolean getEditInsert();

   /**
    * 获得搜索编辑可的内容。
    *
    * @return 搜索编辑可
    */
   Boolean getEditSearch();

   /**
    * 获得编辑提示的内容。
    *
    * @return 编辑提示
    */
   String getEditTip();

   /**
    * 获得计量单位的内容。
    *
    * @return 计量单位
    */
   String getEditUnit();

   /**
    * 获得修改编辑可的内容。
    *
    * @return 修改编辑可
    */
   Boolean getEditUpdate();

   /**
    * 获得编辑宽度的内容。
    *
    * @return 编辑宽度
    */
   String getEditWidth();

   /**
    * 获得自动完成服务的内容。
    *
    * @return 自动完成服务
    */
   String getServiceComplete();

   /**
    * 获得检查服务的内容。
    *
    * @return 检查服务
    */
   String getServiceValid();

   /**
    * 获得检查权限的内容。
    *
    * @return 检查权限
    */
   String getValidAccess();

   /**
    * 获得删除检查可的内容。
    *
    * @return 删除检查可
    */
   Boolean getValidDelete();

   /**
    * 获得新建检查可的内容。
    *
    * @return 新建检查可
    */
   Boolean getValidInsert();

   /**
    * 获得检查必须的内容。
    *
    * @return 检查必须
    */
   String getValidRequire();

   /**
    * 获得更新检查可的内容。
    *
    * @return 更新检查可
    */
   Boolean getValidUpdate();

   /**
    * 设置编辑权限的内容。
    *
    * @param value 编辑权限
    */
   void setEditAccess(String value);

   /**
    * 设置自动完成的内容。
    *
    * @param value 自动完成
    */
   void setEditComplete(String value);

   /**
    * 设置编辑复制可的内容。
    *
    * @param value 编辑复制可
    */
   void setEditCopy(String value);

   /**
    * 设置删除编辑可的内容。
    *
    * @param value 删除编辑可
    */
   void setEditDelete(Boolean value);

   /**
    * 设置编辑高度的内容。
    *
    * @param value 编辑高度
    */
   void setEditHeight(String value);

   /**
    * 设置新建编辑可的内容。
    *
    * @param value 新建编辑可
    */
   void setEditInsert(Boolean value);

   /**
    * 设置搜索编辑可的内容。
    *
    * @param value 搜索编辑可
    */
   void setEditSearch(Boolean value);

   /**
    * 设置编辑提示的内容。
    *
    * @param value 编辑提示
    */
   void setEditTip(String value);

   /**
    * 设置计量单位的内容。
    *
    * @param value 计量单位
    */
   void setEditUnit(String value);

   /**
    * 设置修改编辑可的内容。
    *
    * @param value 修改编辑可
    */
   void setEditUpdate(Boolean value);

   /**
    * 设置编辑宽度的内容。
    *
    * @param value 编辑宽度
    */
   void setEditWidth(String value);

   /**
    * 设置自动完成服务的内容。
    *
    * @param value 自动完成服务
    */
   void setServiceComplete(String value);

   /**
    * 设置检查服务的内容。
    *
    * @param value 检查服务
    */
   void setServiceValid(String value);

   /**
    * 设置检查权限的内容。
    *
    * @param value 检查权限
    */
   void setValidAccess(String value);

   /**
    * 设置删除检查可的内容。
    *
    * @param value 删除检查可
    */
   void setValidDelete(Boolean value);

   /**
    * 设置新建检查可的内容。
    *
    * @param value 新建检查可
    */
   void setValidInsert(Boolean value);

   /**
    * 设置检查必须的内容。
    *
    * @param value 检查必须
    */
   void setValidRequire(String value);

   /**
    * 设置更新检查可的内容。
    *
    * @param value 更新检查可
    */
   void setValidUpdate(Boolean value);

}
