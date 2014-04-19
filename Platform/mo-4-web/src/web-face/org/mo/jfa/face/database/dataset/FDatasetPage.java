package org.mo.jfa.face.database.dataset;

import org.mo.com.lang.FString;
import org.mo.com.xml.IXmlObject;
import org.mo.jfa.common.xmlobjects.FAbsXmlObjectPage;

public class FDatasetPage
      extends FAbsXmlObjectPage<IXmlObject>
{

   private static final long serialVersionUID = 1L;

   private String _selectDataset;

   private String _selectField;

   private String _buildPackageHead;

   private String _buildPackageBody;

   private String _buildTable;

   private String _buildTableDel;

   private String _buildView;

   private String _buildSequence;

   private String _buildSequenceDel;

   private String _buildStore;

   private String _buildRestore;

   private String _buildHsPackageHead;

   private String _buildHsPackageBody;

   private String _buildHsTable;

   private String _buildHsTableDel;

   private String _buildHsView;

   private String _buildHsSequence;

   private String _buildHsSequenceDel;

   private String _buildHsStore;

   private String _buildHsRestore;

   private String _viewSql;

   private FString _source;

   public String getBuildHsPackageBody(){
      return _buildHsPackageBody;
   }

   public String getBuildHsPackageHead(){
      return _buildHsPackageHead;
   }

   public String getBuildHsRestore(){
      return _buildHsRestore;
   }

   public String getBuildHsSequence(){
      return _buildHsSequence;
   }

   public String getBuildHsSequenceDel(){
      return _buildHsSequenceDel;
   }

   public String getBuildHsStore(){
      return _buildHsStore;
   }

   public String getBuildHsTable(){
      return _buildHsTable;
   }

   public String getBuildHsTableDel(){
      return _buildHsTableDel;
   }

   public String getBuildHsView(){
      return _buildHsView;
   }

   public String getBuildPackageBody(){
      return _buildPackageBody;
   }

   public String getBuildPackageHead(){
      return _buildPackageHead;
   }

   public String getBuildRestore(){
      return _buildRestore;
   }

   public String getBuildSequence(){
      return _buildSequence;
   }

   public String getBuildSequenceDel(){
      return _buildSequenceDel;
   }

   public String getBuildStore(){
      return _buildStore;
   }

   public String getBuildTable(){
      return _buildTable;
   }

   public String getBuildTableDel(){
      return _buildTableDel;
   }

   public String getBuildView(){
      return _buildView;
   }

   public String getSelectDataset(){
      return _selectDataset;
   }

   public String getSelectField(){
      return _selectField;
   }

   public FString getSource(){
      return _source;
   }

   public String getViewSql(){
      return _viewSql;
   }

   public void setBuildHsPackageBody(String buildHsPackageBody){
      _buildHsPackageBody = buildHsPackageBody;
   }

   public void setBuildHsPackageHead(String buildHsPackageHead){
      _buildHsPackageHead = buildHsPackageHead;
   }

   public void setBuildHsRestore(String buildHsRestore){
      _buildHsRestore = buildHsRestore;
   }

   public void setBuildHsSequence(String buildHsSequence){
      _buildHsSequence = buildHsSequence;
   }

   public void setBuildHsSequenceDel(String buildHsSequenceDel){
      _buildHsSequenceDel = buildHsSequenceDel;
   }

   public void setBuildHsStore(String buildHsStore){
      _buildHsStore = buildHsStore;
   }

   public void setBuildHsTable(String buildHsTable){
      _buildHsTable = buildHsTable;
   }

   public void setBuildHsTableDel(String buildHsTableDel){
      _buildHsTableDel = buildHsTableDel;
   }

   public void setBuildHsView(String buildHsView){
      _buildHsView = buildHsView;
   }

   public void setBuildPackageBody(String buildPackageBody){
      _buildPackageBody = buildPackageBody;
   }

   public void setBuildPackageHead(String buildPackageHead){
      _buildPackageHead = buildPackageHead;
   }

   public void setBuildRestore(String buildRestore){
      _buildRestore = buildRestore;
   }

   public void setBuildSequence(String buildSequence){
      _buildSequence = buildSequence;
   }

   public void setBuildSequenceDel(String buildSequenceDel){
      _buildSequenceDel = buildSequenceDel;
   }

   public void setBuildStore(String buildStore){
      _buildStore = buildStore;
   }

   public void setBuildTable(String buildTable){
      _buildTable = buildTable;
   }

   public void setBuildTableDel(String buildTableDel){
      _buildTableDel = buildTableDel;
   }

   public void setBuildView(String buildView){
      _buildView = buildView;
   }

   public void setSelectDataset(String dataset){
      _selectDataset = dataset;
   }

   public void setSelectField(String field){
      _selectField = field;
   }

   public void setSource(FString source){
      _source = source;
   }

   public void setViewSql(String viewSql){
      _viewSql = viewSql;
   }

}
