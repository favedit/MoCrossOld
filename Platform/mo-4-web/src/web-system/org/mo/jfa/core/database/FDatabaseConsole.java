package org.mo.jfa.core.database;

import org.mo.com.collections.FRow;
import org.mo.com.lang.FAttributes;
import org.mo.com.lang.RBoolean;
import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.FXmlNodes;
import org.mo.core.aop.face.ALink;
import org.mo.core.aop.face.AProperty;
import org.mo.eng.data.common.FSqlQuery;
import org.mo.eng.data.common.ISqlContext;

public class FDatabaseConsole
      implements
         IDatabaseConsole
{
   @AProperty
   private String _connection;

   @ALink
   private org.mo.eng.data.IDatabaseConsole _dbConsole;

   public final String ATTR_NAME = "name";

   public final String m_sDatabaseName = "oracle";

   public final String NODE_FIELD = "Field";

   public final String NODE_SEQUENCE = "seq";

   public final String NODE_SEQUENCES = "seqs";

   public final String NODE_TABLE = "Table";

   public final String NODE_TABLES = "Tables";

   public final String NODE_VIEW = "View";

   //@ALink
   //private IXmlDataConsole _xmlConsole;

   public final String NODE_VIEWS = "Views";

   public FDatabaseConsole(){
   }

   @Override
   public FTableNode findTable(ISqlContext context,
                               String name){
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public FViewNode findView(ISqlContext context,
                             String name){
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public FXmlNodes list(ISqlContext context){
      FXmlNodes types = new FXmlNodes();

      FXmlNode tables = types.create();
      tables.set("type", "tables");
      tables.set("method", "listTables");
      tables.set("name", "Tables");
      tables.set("icon", "db.tablist");
      tables.set("label", "Tables");
      tables.set("child", RBoolean.TRUE_STR);

      FXmlNode views = types.create();
      views.set("type", "views");
      views.set("method", "listViews");
      views.set("name", "Views");
      views.set("icon", "db.vwlist");
      views.set("label", "Views");
      views.set("child", RBoolean.TRUE_STR);

      return types;
   }

   public FFieldNodes listFields(ISqlContext context){
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public FXmlNodes listFields(ISqlContext context,
                               String name){
      FXmlNodes fields = new FXmlNodes();
      // Query
      String sqlId = "pty:database.oracle|table.column.list";
      FSqlQuery query = new FSqlQuery(context, sqlId);
      query.bindString("table_name", name);
      for(FRow row : query.fetchDataset()){
         FXmlNode field = fields.create();
         field.set("type", "field");
         field.set("name", row.get("field_name"));
         field.set("label", row.get("field_name"));
         field.set("icon", "db.tab");
      }
      return fields;
   }

   @Override
   public FTableNodes listTables(ISqlContext context){
      FTableNodes tables = new FTableNodes();
      // Query
      FAttributes attrs = new FAttributes();
      String sqlId = "pty:database.oracle|table.list";
      FSqlQuery query = new FSqlQuery(context, sqlId);
      for(FRow row : query.fetchDataset()){
         String name = row.get("table_name");
         if(name.contains("$")){
            continue;
         }
         attrs.clear();
         attrs.set("type", "table");
         attrs.set("name", name);
         FTableNode table = new FTableNode();
         table.set("method", "listFields");
         table.set("page", "wfc.database|table");
         table.set("name", name);
         table.set("label", name);
         table.set("icon", "db.tab");
         table.set("attrs", attrs.pack().toString());
         table.set("child", RBoolean.TRUE_STR);
         tables.push(table);
      }
      return tables;
   }

   @Override
   public FViewNodes listViews(ISqlContext context){
      FAttributes attrs = new FAttributes();
      FViewNodes views = new FViewNodes();
      // Query
      String sqlId = "pty:database.oracle|view.list";
      FSqlQuery query = new FSqlQuery(context, sqlId);
      for(FRow row : query.fetchDataset()){
         String name = row.get("view_name");
         attrs.clear();
         attrs.set("type", "view");
         attrs.set("name", name);

         FViewNode view = new FViewNode();
         view.set("method", "listFields");
         view.set("name", name);
         view.set("label", name);
         view.set("icon", "db.vw");
         view.set("attrs", attrs.pack().toString());
         view.set("child", RBoolean.TRUE_STR);
         views.push(view);
      }
      return views;
   }

   /*
    public void buildDataset(IWebContext iContext,
    String sTableName)
    throws FException{
    FComDbiObject oDbiObject = new FComDbiObject(iContext);
    oDbiObject.buildDataset(null, sTableName);
    }

    public void buildTableSource(IWebContext iContext,
    String sTableName)
    throws FException{
    String sPath = "tables." + sTableName.toLowerCase();
    FXmlDocument oDoc = syncDocument(sPath);
    FXmlNode oTabNode = oDoc.rootNode();
    oTabNode.clear();
    oTabNode.setName("Table");

    FWfcPropertyConsole oPtyConsole = FWfcPropertyManager.console();
    FStringList oTabPty = oPtyConsole.fetchProperty(iContext, "com.database.table", sTableName);
    oTabNode.attributes().append(oTabPty);

    String sFieldName = null;
    FStringList oPtys = null;
    String sSqlCmd = "pty:db.oracle|table.column.infolist";
    FSqlQuery oQuery = new FSqlQuery(iContext, sSqlCmd);
    oQuery.bindString("table_name", sTableName);
    for(FRow oField : oQuery.fetchDataset()){
    sFieldName = oField.value("field_name");
    oPtys = oPtyConsole.fetchProperty(iContext, "com.database.table.field", sTableName + "|"
    + sFieldName);
    FXmlNode oFieldNode = oTabNode.createNode("Field");
    FStringListUtil.appendEmpty(oFieldNode.attributes(), oField);
    FStringListUtil.appendEmpty(oFieldNode.attributes(), oPtys);
    oFieldNode.setAttribute("name", sFieldName);
    if(FStringUtil.isNotEmpty(oPtys.value("data_type"))){
    oFieldNode.setAttribute("data_type", oPtys.value("data_type"));
    }
    if(FStringUtil.isNotEmpty(oPtys.value("data_default"))){
    oFieldNode.setAttribute("data_default", oPtys.value("data_default"));
    }
    }
    saveDocument(oDoc, true);
    }

    public FXmlNode fetchTableInfo(IWebContext iContext,
    String sTableName)
    throws FException{
    FXmlDocument oDocument = syncDocument("tables." + sTableName);
    return oDocument.rootNode();
    }

    public FXmlNodes findSequences(String sCfgPath)
    throws FException{
    FXmlDocument oDocument = findDocument(sCfgPath);
    if(oDocument != null){
    return oDocument.rootNode().syncNode(NODE_SEQUENCE).nodes();
    }
    return new FXmlNodes();
    }

    public FXmlNode findTable(IWebContext iContext,
    String sTableName)
    throws FException{
    return findTable(iContext, sTableName, true);
    }

    public FXmlNode findTable(IWebContext iContext,
    String sTableName,
    boolean bDeep)
    throws FException{
    FXmlNode oCpyTableNode = new FXmlNode(NODE_TABLE, ATTR_NAME, sTableName);
    FXmlDocument oDocument = findDocument(m_sDatabaseName);
    FWfcPropertyConsole oPtyCfgConsole = FWfcPropertyManager.console();
    if(oDocument != null){
    FXmlNode oTableNode = oDocument.rootNode().syncNode(NODE_TABLES).syncNode(NODE_TABLE,
    ATTR_NAME, sTableName);
    if(oTableNode != null){
    oCpyTableNode.attributes().assign(oTableNode.attributes());
    fixTableInfo(sTableName, oCpyTableNode);
    oPtyCfgConsole.fixPropertyValueOnLoad(iContext, "db." + m_sDatabaseName, "table."
    + sTableName.toLowerCase(), "database", "table", oCpyTableNode);
    if(bDeep){
    String sFieldName = null;
    FXmlNode oCpyFieldNode = null;
    for(FXmlNode oFieldNode : oTableNode.nodes()){
    sFieldName = oFieldNode.attribute("name");
    oCpyFieldNode = new FXmlNode(NODE_FIELD, ATTR_NAME, sFieldName);
    oCpyFieldNode.attributes().assign(oFieldNode.attributes());
    fixTableFieldInfo(sTableName, sFieldName, oCpyFieldNode);
    oPtyCfgConsole.fixPropertyValueOnLoad(iContext, "db." + m_sDatabaseName, "field."
    + sTableName.toLowerCase() + "." + sFieldName.toLowerCase(), "database",
    "table.field", oCpyFieldNode);
    oCpyTableNode.nodes().push(oCpyFieldNode);
    }
    }
    }
    }
    return oCpyTableNode;
    }

    public FXmlNodes findTableFields(IWebContext iContext,
    String sCfgPath,
    String sTableName)
    throws FException{
    FWfcPropertyConsole oPtyCfgConsole = FWfcPropertyManager.console();
    FXmlDocument oDocument = findDocument(sCfgPath);
    if(oDocument != null){
    FXmlNodes oFieldList = oDocument.rootNode().syncNode(NODE_TABLES).syncNode(NODE_TABLE,
    ATTR_NAME, sTableName).nodes();
    for(FXmlNode oField : oFieldList){
    oPtyCfgConsole.fixPropertyValueOnLoad(iContext, "db.oracle", "field."
    + sTableName.toLowerCase() + "." + oField.attribute(ATTR_NAME), "database",
    "table.field", oField);
    }
    return oFieldList;
    }
    return new FXmlNodes();
    }

    public FXmlNode findTableFieldValue(IWebContext iContext,
    String sTableName,
    String sFieldName)
    throws FException{
    FXmlNode oNode = new FXmlNode(NODE_FIELD, ATTR_NAME, sFieldName);
    FXmlDocument oDocument = findDocument(m_sDatabaseName);
    if(oDocument != null){
    FXmlNode oFieldNode = oDocument.rootNode().syncNode(NODE_TABLES).syncNode(NODE_TABLE,
    ATTR_NAME, sTableName).syncNode(NODE_FIELD, ATTR_NAME, sFieldName);
    if(oFieldNode != null){
    oNode.attributes().assign(oFieldNode.attributes());
    }
    }
    fixTableFieldInfo(sTableName, sFieldName, oNode);
    FWfcPropertyConsole oPtyCfgConsole = FWfcPropertyManager.console();
    oPtyCfgConsole.fixPropertyValueOnLoad(iContext, "db." + m_sDatabaseName, "field."
    + sTableName.toLowerCase() + "." + sFieldName.toLowerCase(), "database", "table.field",
    oNode);
    return oNode;
    }

    public FXmlNodes findTables(String sCfgPath)
    throws FException{
    FXmlDocument oDocument = findDocument(sCfgPath);
    if(oDocument != null){
    return oDocument.rootNode().syncNode(NODE_TABLES).nodes();
    }
    return new FXmlNodes();
    }

    public FXmlNode findTableValue(IWebContext iContext,
    String sTableName)
    throws FException{
    FXmlNode oNode = new FXmlNode(NODE_TABLE, ATTR_NAME, sTableName);
    FXmlDocument oDocument = findDocument(m_sDatabaseName);
    if(oDocument != null){
    FXmlNode oTableNode = oDocument.rootNode().syncNode(NODE_TABLES).syncNode(NODE_TABLE,
    ATTR_NAME, sTableName);
    if(oTableNode != null){
    oNode.attributes().assign(oTableNode.attributes());
    }
    }
    fixTableInfo(sTableName, oNode);
    FWfcPropertyConsole oPtyCfgConsole = FWfcPropertyManager.console();
    oPtyCfgConsole.fixPropertyValueOnLoad(iContext, "db." + m_sDatabaseName, "table."
    + sTableName.toLowerCase(), "database", "table", oNode);
    return oNode;
    }

    public FXmlNodes findViewFields(String sCfgPath,
    String sViewName)
    throws FException{
    FXmlDocument oDocument = findDocument(sCfgPath);
    if(oDocument != null){
    return oDocument.rootNode().syncNode(NODE_VIEWS).syncNode(NODE_VIEW, ATTR_NAME, sViewName)
    .nodes();
    }
    return new FXmlNodes();
    }

    public FXmlNodes findViews(String sCfgPath)
    throws FException{
    FXmlDocument oDocument = findDocument(sCfgPath);
    if(oDocument != null){
    return oDocument.rootNode().syncNode(NODE_VIEWS).nodes();
    }
    return new FXmlNodes();
    }

    protected void fixTableFieldInfo(String sTableName,
    String sFieldName,
    FXmlNode oNode)
    throws FException{
    ISqlConnection piConnection = null;
    try{
    piConnection = FDatabaseManager.instance().allocateConnection();
    FSqlQuery oColumnQuery = new FSqlQuery(piConnection, IWfcSqlConstants.ORACLE_PROPERTY
    + "table.column.info");
    oColumnQuery.bindString("table_name", sTableName);
    oColumnQuery.bindString("column_name", sFieldName);
    FRow oUnit = oColumnQuery.fetchUnit();
    if(FStringUtil.isEmpty(oNode.attribute("label"))){
    oNode.setAttribute("label", FDatabaseFormat.toDisplayName(sFieldName));
    }
    oNode.setAttribute("data_name", sFieldName.toUpperCase());
    oNode.setAttribute("data_type", oUnit.value("data_type"));
    oNode.setAttribute("data_key", oUnit.value("is_key"));
    oNode.setAttribute("data_null", oUnit.value("is_null"));
    oNode.setAttribute("data_size", oUnit.value("data_length"));
    setNodeValue(oNode, oUnit, "data_default", "data_default");
    setNodeValue(oNode, oUnit, "public_able", "is_public");
    FXmlNodes oTypeXNode = FWfcListManager.console().findList("db|table.field.type").nodes();
    FXmlNode oItemNode = oTypeXNode.findNode("Item", "value", oUnit.value("data_type"));
    if(oItemNode != null){
    String sBaseType = oItemNode.attribute("base_type");
    if(FStringUtil.isEmpty(oNode.attribute("base_type"))){
    oNode.setAttribute("base_type", sBaseType);
    }
    // Fix data valid option
    if(sBaseType.equalsIgnoreCase("String")){
    setNodeValue(oNode, oUnit, "valid_maxlen", "data_length");
    if(FStringUtil.isEmpty(oNode.attribute("valid_maxlen_eq"))){
    oNode.setAttribute("valid_maxlen_eq", FBooleanUtil.TRUE);
    }
    }
    }
    // Valid Option
    if(!FBooleanUtil.isTrue(oUnit.value("is_null"))){
    oNode.setAttribute("valid_null", FBooleanUtil.FALSE);
    }
    }finally{
    if(piConnection != null){
    piConnection.close();
    }
    }
    }

    protected void fixTableInfo(String sTableName,
    FXmlNode oNode)
    throws FException{
    if(FStringUtil.isEmpty(oNode.attribute("catalog"))){
    String sCatalog = sTableName.toLowerCase();
    if(sCatalog.endsWith("_ds")){
    sCatalog = sCatalog.substring(0, sCatalog.length() - 3);
    }
    sCatalog = FStringUtil.replace(sCatalog, '_', '.');
    oNode.setAttribute("catalog", sCatalog);
    }
    oNode.setAttribute("data_name", sTableName.toUpperCase());
    }

    public void saveTableFieldsValue(IWebContext iContext,
    String sTableName,
    FXmlNodes oNodeList)
    throws FException{
    for(FXmlNode oNode : oNodeList){
    saveTableFieldValue(iContext, sTableName, oNode.attribute(ATTR_NAME), oNode);
    }
    }

    public void saveTableFieldValue(IWebContext iContext,
    String sTableName,
    String sFieldName,
    FXmlNode oNode)
    throws FException{
    FWfcPropertyConsole oPtyCfgConsole = FWfcPropertyManager.console();
    FXmlDocument oDocument = syncDocument(m_sDatabaseName);
    FXmlNode oFieldNode = oDocument.rootNode().syncNode(NODE_TABLES).syncNode(NODE_TABLE,
    ATTR_NAME, sTableName).syncNode(NODE_FIELD, ATTR_NAME, sFieldName);
    oPtyCfgConsole.checkPropertyValue("database", "table.field", oNode);
    oPtyCfgConsole
    .fixPropertyValueOnSave(iContext, "db." + m_sDatabaseName, "field."
    + sTableName.toLowerCase() + "." + sFieldName.toLowerCase(), "database", "table",
    oNode);
    // Valid Option Fix
    if(!FBooleanUtil.isTrue(oNode.attribute("data_null"))){
    oNode.setAttribute("valid_null", FBooleanUtil.FALSE);
    }
    oFieldNode.attributes().assign(oNode.attributes());
    saveDocument(oDocument);
    }

    public void saveTableValue(IWebContext iContext,
    String sTableName,
    FXmlNode oNode)
    throws FException{
    saveTableValue(iContext, sTableName, oNode, false);
    }

    public void saveTableValue(IWebContext iContext,
    String sTableName,
    FXmlNode oNode,
    boolean bDeep)
    throws FException{
    FWfcPropertyConsole oPtyCfgConsole = FWfcPropertyManager.console();
    FXmlDocument oDocument = syncDocument(m_sDatabaseName);
    FXmlNode oTableNode = oDocument.rootNode().syncNode(NODE_TABLES).syncNode(NODE_TABLE,
    ATTR_NAME, sTableName);
    oPtyCfgConsole.checkPropertyValue("database", "table", oNode);
    oPtyCfgConsole.fixPropertyValueOnSave(iContext, "db." + m_sDatabaseName, "table."
    + sTableName.toLowerCase(), "database", "table", oNode);
    oTableNode.attributes().assign(oNode.attributes());
    if(bDeep){
    for(FXmlNode oFldNode : oNode.nodes()){
    saveTableFieldValue(iContext, sTableName, oFldNode.attribute(ATTR_NAME), oFldNode);
    }
    }
    saveDocument(oDocument);
    }

    private void setNodeValue(FXmlNode oNode,
    FRow oUnit,
    String sNodeAttrName,
    String sUnitAttrName){
    if(FStringUtil.isEmpty(oNode.attribute(sNodeAttrName))){
    oNode.setAttribute(sNodeAttrName, oUnit.value(sUnitAttrName));
    }
    }

    public void syncDatabaseInfo(IWebContext iContext)
    throws FException{
    syncDatabaseInfo(iContext, "en");
    syncDatabaseInfo(iContext, "zh");
    syncDatabaseInfo(iContext, "jp");
    }

    public void syncDatabaseInfo(IWebContext iContext,
    String sLanguage)
    throws FException{
    FXmlNodes oTablesNode = findTables("oracle");
    for(FXmlNode oTableNode : oTablesNode){
    syncTableInfo(iContext, oTableNode.attribute("name"), sLanguage);
    }
    }

    public void syncPackageInfo(IWebContext iContext,
    String sLogicName)
    throws FException{
    syncPackageInfo(iContext, sLogicName, "en");
    syncPackageInfo(iContext, sLogicName, "zh");
    syncPackageInfo(iContext, sLogicName, "jp");
    }

    public void syncPackageInfo(IWebContext iContext,
    String sLogicName,
    String sLanguage)
    throws FException{
    FTranslateConsole oTrsConsole = FTranslateManager.console();
    String sFileId = "db.pkg." + FStringUtil.replace(sLogicName, ".", "_");
    FXmlDocument oDocument = oTrsConsole.findDocument(sFileId);
    // Field Names
    FStringList oParams = new FStringList();
    FSqlProcedure oProcedure = null;
    for(FXmlNode oContentNode : oDocument.rootNode().nodes()){
    oParams.clear();
    oParams.setValue("logic_name", sLogicName);
    oParams.setValue("content", oContentNode.attribute("name"));
    oParams.setValue("label", oContentNode.nodeText(sLanguage));
    oParams.setValue("language", sLanguage);
    oProcedure = new FSqlProcedure("SYS_DBI_DATASET.Set_Logic_Content_Info");
    oProcedure.createParameter("logic_", null, ISqlType.STRING, ISqlDirection.INPUT_OUTPUT);
    oProcedure.createParameter("params_", oParams.pack().toString(), ISqlType.STRING,
    ISqlDirection.INPUT_OUTPUT);
    iContext.activeConnection().execute(oProcedure);
    }
    }

    public void syncTableInfo(IWebContext iContext,
    String sTableName)
    throws FException{
    syncTableInfo(iContext, sTableName, "en");
    syncTableInfo(iContext, sTableName, "zh");
    syncTableInfo(iContext, sTableName, "jp");
    }

    public void syncTableInfo(IWebContext iContext,
    String sTableName,
    String sLanguage)
    throws FException{
    //      FXmlNode oTableNode = findTable(iContext, sTableName);
    //      FTranslateConsole oTrsConsole = FTranslateManager.console();
    //      String sFieldName = null;
    //      String sFieldLabel = null;
    //      FStringList oParams = new FStringList();
    //      oParams.setValue("dataset", sTableName);
    //      oParams.setValue("label", oTableNode.attribute("label"));
    //      oParams.setValue("language", sLanguage);
    //      oParams.setValue("logic_name", oTableNode.attribute("catalog"));
    //      FSqlProcedure oProcedure = new FSqlProcedure(
    //            "SYS_DBI_DATASET.Set_Dataset_Info");
    //      oProcedure.createParameter("logic_", null, ISqlType.STRING,
    //            ISqlDirection.INPUT_OUTPUT);
    //      oProcedure.createParameter("params_", oParams.pack().toString(),
    //            ISqlType.STRING, ISqlDirection.INPUT_OUTPUT);
    //      iContext.activeConnection().execute(oProcedure);
    //      // Field Names
    //      for (FXmlNode oFieldNode : oTableNode.nodes()) {
    //         sFieldName = oFieldNode.attribute("name");
    //         sFieldLabel = oTrsConsole.translate("db.oracle", "trs:this|field."
    //               + sTableName + "." + sFieldName + ".label|" + sFieldName,
    //               sLanguage);
    //         oParams.clear();
    //         oParams.setValue("dataset", sTableName);
    //         oParams.setValue("field", sFieldName);
    //         oParams.setValue("label", sFieldLabel);
    //         oParams.setValue("language", sLanguage);
    //         oProcedure = new FSqlProcedure(
    //               "SYS_DBI_DATASET.Set_Dataset_Field_Info");
    //         oProcedure.createParameter("logic_", null, ISqlType.STRING,
    //               ISqlDirection.INPUT_OUTPUT);
    //         oProcedure.createParameter("params_", oParams.pack().toString(),
    //               ISqlType.STRING, ISqlDirection.INPUT_OUTPUT);
    //         iContext.activeConnection().execute(oProcedure);
    //      }
    }
    */
}
