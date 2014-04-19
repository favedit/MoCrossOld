package org.mo.web.core.webform;

import org.mo.com.data.RSql;
import org.mo.com.lang.FAttributes;
import org.mo.com.lang.IAttributes;
import org.mo.com.xml.IXmlObject;
import org.mo.eng.data.common.FSqlOrderFields;
import org.mo.eng.data.common.FSqlSearchFields;
import org.mo.eng.data.common.ISqlContext;
import org.mo.web.protocol.context.IWebContext;

public class FWebFormDatasetArgs
{

   private IXmlObject _control;

   private String _controlPath;

   private IXmlObject _form;

   private String _formName;

   private String _formPath;

   private IAttributes _logic;

   private FSqlOrderFields _orders = new FSqlOrderFields();

   private int _page = 0;

   private int _pageSize = RSql.PAGE_SIZE;

   private IAttributes _parentParameters;

   private IAttributes _prepares;

   private boolean _research;

   private FSqlSearchFields _searchs = new FSqlSearchFields();

   private String _sessionConnectId;

   private IAttributes _sessionParameters;

   private ISqlContext _sqlContext;

   private IAttributes _values;

   private IWebContext _webContext;

   public FWebFormDatasetArgs(IWebContext webContext,
                              ISqlContext sqlContext){
      setWebContext(webContext);
      setSqlContext(sqlContext);
   }

   @Override
   public FWebFormDatasetArgs clone(){
      FWebFormDatasetArgs args = new FWebFormDatasetArgs(_webContext, _sqlContext);
      args._sqlContext = _sqlContext;
      args._formName = _formName;
      args._controlPath = _controlPath;
      args._form = _form;
      args._control = _control;
      args._pageSize = _pageSize;
      args._page = _page;
      args._logic = _logic;
      args._searchs = _searchs;
      args._orders = _orders;
      args._values = _values;
      return args;
   }

   public IXmlObject control(){
      return _control;
   }

   public String controlPath(){
      return _controlPath;
   }

   public IXmlObject form(){
      return _form;
   }

   public String formName(){
      return _formName;
   }

   public String formPath(){
      return _formPath;
   }

   public IAttributes getLogic(){
      return _logic;
   }

   public FSqlOrderFields getOrder(){
      return _orders;
   }

   public FSqlSearchFields getSearch(){
      return _searchs;
   }

   public String getSessionConnectId(){
      return _sessionConnectId;
   }

   public IAttributes getValues(){
      return _values;
   }

   public boolean isResearch(){
      return _research;
   }

   public IAttributes logic(){
      if(null == _logic){
         _logic = new FAttributes();
      }
      return _logic;
   }

   public FSqlOrderFields order(){
      if(null == _orders){
         _orders = new FSqlOrderFields();
      }
      return _orders;
   }

   public int page(){
      return _page;
   }

   public int pageSize(){
      return _pageSize;
   }

   public String parentParameter(String name){
      return parentParameters().get(name);
   }

   public IAttributes parentParameters(){
      if(null == _parentParameters){
         _parentParameters = new FAttributes();
      }
      return _parentParameters;
   }

   public IAttributes prepares(){
      if(null == _prepares){
         _prepares = new FAttributes();
      }
      return _prepares;
   }

   public FSqlSearchFields search(){
      if(null == _searchs){
         _searchs = new FSqlSearchFields();
      }
      return _searchs;
   }

   public IAttributes sessionParameters(){
      if(null == _sessionParameters){
         _sessionParameters = new FAttributes();
      }
      return _sessionParameters;
   }

   public void setControl(IXmlObject control){
      _control = control;
   }

   public void setControlPath(String controlPath){
      _controlPath = controlPath;
   }

   public void setForm(IXmlObject xform){
      _form = xform;
      _formName = xform.innerGet("name");
   }

   public void setFormName(String name){
      _formName = name;
   }

   public void setFormPath(String formPath){
      _formPath = formPath;
   }

   public void setLogic(IAttributes logic){
      _logic = logic;
   }

   public void setPage(int page){
      _page = page;
   }

   public void setPageSize(int pageSize){
      _pageSize = pageSize;
   }

   public void setPrepares(IAttributes prepares){
      _prepares = prepares;
   }

   public void setResearch(boolean research){
      _research = research;
   }

   public void setSessionConnectId(String _sessionConnectId){
      this._sessionConnectId = _sessionConnectId;
   }

   public void setSqlContext(ISqlContext context){
      _sqlContext = context;
   }

   public void setValues(IAttributes values){
      _values = values;
   }

   public void setWebContext(IWebContext webContext){
      _webContext = webContext;
   }

   public ISqlContext sqlContext(){
      return _sqlContext;
   }

   public IAttributes values(){
      if(null == _values){
         _values = new FAttributes();
      }
      return _values;
   }

   public IWebContext webContext(){
      return _webContext;
   }

}
