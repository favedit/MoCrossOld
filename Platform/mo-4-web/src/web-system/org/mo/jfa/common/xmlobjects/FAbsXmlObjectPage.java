package org.mo.jfa.common.xmlobjects;

import org.mo.com.lang.IAttributes;
import org.mo.com.xml.IXmlObject;
import org.mo.jfa.common.page.FAbstractFormPage;
import org.mo.web.protocol.context.IWebContext;

//============================================================
// <T>配置对象表单页面基类。</T>
//============================================================
public abstract class FAbsXmlObjectPage<V>
      extends FAbstractFormPage
{
   private static final long serialVersionUID = 1L;

   public final static String PTY_NODE_FILTER = "node_filter";

   public final static String PTY_NODE_SORT = "node_sort";

   public final static String PTY_NODE_TYPE = "node_type";

   public final static String PTY_SEL_COLLECTION = "sel_collection";

   public final static String PTY_SEL_COMPONENT = "sel_component";

   public final static String PTY_SEL_TYPE = "sel_type";

   private String _componentType;

   private String _help;

   private String _nodeFilter;

   private String _nodeSort;

   private String _nodeType;

   private String _pageAction;

   private String _selectCollection;

   private String _selectComponent;

   private String _selectType;

   private V _xcollection;

   private IXmlObject _xcomponent;

   @Override
   public void appachContext(IWebContext context){
      super.appachContext(context);
      // 获得选中的数据
      IAttributes parameters = context.parameters();
      _pageAction = parameters.get("do", null);
      _nodeType = parameters.get(PTY_NODE_TYPE, null);
      _nodeFilter = parameters.get(PTY_NODE_FILTER, null);
      _nodeSort = parameters.get(PTY_NODE_SORT, null);
      _selectType = parameters.get(PTY_SEL_TYPE, null);
      _selectCollection = parameters.get(PTY_SEL_COLLECTION, null);
      _selectComponent = parameters.get(PTY_SEL_COMPONENT, null);
      _componentType = parameters.get("component_type", null);
      // 设置环境对象
      if(parameters.contains(PTY_NODE_TYPE)){
         setEnv(PTY_NODE_TYPE, _nodeType);
      }
      if(parameters.contains(PTY_NODE_FILTER)){
         setEnv(PTY_NODE_FILTER, _nodeFilter);
      }
      if(parameters.contains(PTY_NODE_SORT)){
         setEnv(PTY_NODE_SORT, _nodeSort);
      }
      if(parameters.contains(PTY_SEL_TYPE)){
         setEnv("sel_type", _selectType);
      }
      if(parameters.contains(PTY_SEL_COLLECTION)){
         setEnv(PTY_SEL_COLLECTION, _selectCollection);
      }
      if(parameters.contains(PTY_SEL_COMPONENT)){
         setEnv(PTY_SEL_COMPONENT, _selectComponent);
      }
   }

   public V getCollection(){
      return _xcollection;
   }

   public IXmlObject getComponent(){
      return _xcomponent;
   }

   public String getComponentType(){
      return _componentType;
   }

   public String getHelp(){
      return _help;
   }

   public String getNodeFilter(){
      return _nodeFilter;
   }

   public String getNodeSort(){
      return _nodeSort;
   }

   public String getNodeType(){
      return _nodeType;
   }

   public String getPageAction(){
      return _pageAction;
   }

   public String getSelectCollection(){
      return _selectCollection;
   }

   public String getSelectComponent(){
      return _selectComponent;
   }

   public String getSelectType(){
      return _selectType;
   }

   public void setCollection(V xcollection){
      _xcollection = xcollection;
   }

   public void setComponent(IXmlObject xcomponent){
      _xcomponent = xcomponent;
   }

   public void setComponentType(String componentType){
      _componentType = componentType;
   }

   public void setHelp(String help){
      _help = help;
   }

   public void setNodeFilter(String nodeFilter){
      _nodeFilter = nodeFilter;
   }

   public void setNodeSort(String nodeSort){
      _nodeSort = nodeSort;
   }

   public void setNodeType(String _nodeType){
      this._nodeType = _nodeType;
   }

   public void setPageAction(String pageAction){
      _pageAction = pageAction;
   }

   public void setSelectCollection(String selectCollection){
      _selectCollection = selectCollection;
   }

   public void setSelectComponent(String selectComponent){
      _selectComponent = selectComponent;
   }

   public void setSelectType(String selectType){
      _selectType = selectType;
   }

}
