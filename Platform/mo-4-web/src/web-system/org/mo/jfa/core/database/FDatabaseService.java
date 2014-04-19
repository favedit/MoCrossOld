package org.mo.jfa.core.database;

import org.mo.com.lang.FAttributes;
import org.mo.com.xml.FXmlNodes;
import org.mo.core.aop.face.ALink;
import org.mo.eng.data.common.ISqlContext;
import org.mo.web.protocol.context.IWebContext;
import org.mo.web.protocol.context.IWebInput;
import org.mo.web.protocol.context.IWebOutput;

public class FDatabaseService
      implements
         IDatabaseService
{

   @ALink
   private IDatabaseConsole _console;

   public void list(IWebContext webContext,
                    ISqlContext sqlContext,
                    IWebInput input,
                    IWebOutput output){
      FXmlNodes nodes = _console.list(sqlContext);
      output.config().nodes().append(nodes);
   }

   public void listTables(IWebContext webContext,
                          ISqlContext sqlContext,
                          IWebInput input,
                          IWebOutput output){
      FTableNodes nodes = _console.listTables(sqlContext);
      output.config().nodes().append(nodes.toObjects());
   }

   public void listViews(IWebContext webContext,
                         ISqlContext sqlContext,
                         IWebInput input,
                         IWebOutput output){
      FViewNodes nodes = _console.listViews(sqlContext);
      output.config().nodes().append(nodes.toObjects());
   }

   public void listFields(IWebContext webContext,
                          ISqlContext sqlContext,
                          IWebInput input,
                          IWebOutput output){
      String attrsStr = input.config().nodeText("Attributes");
      FAttributes attrs = new FAttributes();
      attrs.unpack(attrsStr);
      System.out.println(attrs.dump());
      FXmlNodes nodes = _console.listFields(sqlContext, attrs.get("name"));
      output.config().nodes().append(nodes);
   }

   // public final static String TRANS_LABEL = "label";
   //
   //      public final static String PROPERTY_VALUE = "PropertyValue";
   //
   //      public void service(IWebContext iContext,
   //                          FXmlNode oConfigNode,
   //                          FXmlNode oInputNode,
   //                          FXmlNode oOutputNode)
   //            throws FException{
   //         String sAction = oInputNode.nodeText("Action");
   //         if(FStringUtil.isEmpty(sAction)){
   //            throw new FFatalException(this, "service", "Action is null.");
   //         }
   //         if(sAction.equalsIgnoreCase("tree.node")){
   //            serviceTree(iContext, oConfigNode, oInputNode.syncNode("Node"), oOutputNode);
   //         }else{
   //            serviceProperty(iContext, oConfigNode, oInputNode, oOutputNode);
   //         }
   //      }
   //
   //      public void serviceTree(IWebContext iContext,
   //                              FXmlNode oConfigNode,
   //                              FXmlNode oInputNode,
   //                              FXmlNode oOutputNode)
   //            throws FException{
   //         String sUser = oConfigNode.nodeText("user");
   //         if(FStringUtil.isEmpty(sUser)){
   //            sUser = iContext.activeConnection().connectUser();
   //         }
   //         String sType = oInputNode.attribute(FWebTreeView.NODE_TYPE);
   //         if(FStringUtil.isEmpty(sType)){
   //            sType = "oracle";
   //         }
   //         String sPtyFix = "pty:db.oracle|";
   //         String sDispLabel = null;
   //         FWebTreeNode oTvNode = null;
   //         FTranslateConsole oTsConsole = FTranslateManager.console();
   //         String sLanguage = iContext.language();
   //         FSysDatabase oSysDatabase = new FSysDatabase(iContext);
   //         if(sType.equalsIgnoreCase(IWfcSqlConstants.TABLE_LIST)){
   //            // 建立数据表列表
   //            String sTableName = null;
   //            for(FUnit oUnit : oSysDatabase.listTable()){
   //               sTableName = oUnit.value("name");
   //               sDispLabel = FHtmlUtil.nodeLabel(sTableName, oUnit.value("label"));
   //               oTvNode = new FWebTreeNode(IWfcSqlConstants.TABLE, sDispLabel, true, "db.tab");
   //               oTvNode.setAttribute("table_name", sTableName);
   //               oOutputNode.nodes().push(oTvNode);
   //            }
   //         }else if(sType.equalsIgnoreCase(IWfcSqlConstants.TABLE)){
   //            // 建立数据表字段列表
   //            String sFieldName = null;
   //            String sTableName = oInputNode.attribute("table_name");
   //            for(FUnit oUnit : oSysDatabase.listTableField(sTableName)){
   //               sFieldName = oUnit.value("name");
   //               if(sFieldName.equalsIgnoreCase("obj_uid") || sFieldName.equalsIgnoreCase("obj_ver")){
   //                  continue;
   //               }
   //               sDispLabel = FHtmlUtil.nodeLabel(sFieldName, oUnit.value("label"));
   //               oTvNode = new FWebTreeNode(IWfcSqlConstants.TABLE_FIELD, sDispLabel, false, "db.col");
   //               oTvNode.setAttribute("table_name", sTableName);
   //               oTvNode.setAttribute("field_name", sFieldName);
   //               oOutputNode.nodes().push(oTvNode);
   //            }
   //         }else if(sType.equalsIgnoreCase(IWfcSqlConstants.VIEW_LIST)){
   //            // 建立数据视图列表
   //            String sViewName = null;
   //            FSqlQuery oQuery = new FSqlQuery(iContext, sPtyFix + "view.list");
   //            oQuery.bindString("owner", sUser);
   //            for(FUnit oUnit : oQuery.fetchDataset()){
   //               sViewName = oUnit.value("view_name");
   //               sDispLabel = sViewName;
   //               sDispLabel = oTsConsole.translate(ITranslateConstants.DATABASE_VIEW, sViewName,
   //                     TRANS_LABEL, sLanguage);
   //               sDispLabel = FHtmlUtil.nodeLabel(sViewName, sDispLabel);
   //               oTvNode = new FWebTreeNode(IWfcSqlConstants.TABLE, sDispLabel, true, "db.vw");
   //               oTvNode.setAttribute(IWfcSqlConstants.USER, sUser);
   //               oTvNode.setAttribute("view_name", sViewName);
   //               oOutputNode.nodes().push(oTvNode);
   //            }
   //         }else if(sType.equalsIgnoreCase(IWfcSqlConstants.VIEW)){
   //            // 建立数据视图字段列表
   //            String sViewName = oInputNode.attribute("view_name");
   //            String sViewField = null;
   //            FSqlQuery oQuery = new FSqlQuery(iContext, sPtyFix + "view.column.list");
   //            oQuery.bindString("owner", sUser);
   //            oQuery.bindString("view_name", sViewName);
   //            for(FUnit oUnit : oQuery.fetchDataset()){
   //               sViewField = oUnit.value("column_name");
   //               sDispLabel = oTsConsole.translate(ITranslateConstants.DATABASE_VIEW_FIELD, sViewName
   //                     + "." + sViewField, TRANS_LABEL, sLanguage);
   //               sDispLabel = FHtmlUtil.nodeLabel(sDispLabel, sViewField);
   //               oTvNode = new FWebTreeNode(IWfcSqlConstants.VIEW_FIELD, sDispLabel, false, "db.col");
   //               oTvNode.setAttribute(IWfcSqlConstants.USER, sUser);
   //               oTvNode.setAttribute("view_name", sViewName);
   //               oTvNode.setAttribute("field_name", sViewField);
   //               oOutputNode.nodes().push(oTvNode);
   //            }
   //         }else if(sType.equalsIgnoreCase(IWfcSqlConstants.SEQUENCE_LIST)){
   //            // 建立序列列表
   //            String sSequenceName = null;
   //            FSqlQuery oQuery = new FSqlQuery(iContext, sPtyFix + "sequence.list");
   //            oQuery.bindString("owner", sUser);
   //            for(FUnit oUnit : oQuery.fetchDataset()){
   //               sSequenceName = oUnit.value("sequence_name");
   //               sDispLabel = oTsConsole.translate(ITranslateConstants.DATABASE_SEQUENCE, sSequenceName,
   //                     TRANS_LABEL, sLanguage);
   //               sDispLabel = FHtmlUtil.nodeLabel(sSequenceName, sDispLabel);
   //               oTvNode = new FWebTreeNode(IWfcSqlConstants.SEQUENCE, sDispLabel, false, "db.seq");
   //               oTvNode.setAttribute(IWfcSqlConstants.USER, sUser);
   //               oTvNode.setAttribute("sequence_name", sSequenceName);
   //               oOutputNode.nodes().push(oTvNode);
   //            }
   //         }else if(sType.equalsIgnoreCase(IWfcSqlConstants.PACKAGE_LIST)){
   //            // 建立逻辑包列表
   //            String sPackageName = null;
   //            FSqlQuery oQuery = new FSqlQuery(iContext, sPtyFix + "package.list");
   //            oQuery.bindString("owner", sUser);
   //            for(FUnit oUnit : oQuery.fetchDataset()){
   //               sPackageName = oUnit.value("package_name");
   //               sDispLabel = oTsConsole.translate(ITranslateConstants.DATABASE_PACKAGE, sPackageName,
   //                     TRANS_LABEL, sLanguage);
   //               sDispLabel = FHtmlUtil.nodeLabel(sPackageName, sDispLabel);
   //               oTvNode = new FWebTreeNode(IWfcSqlConstants.PACKAGE, sPackageName, false, "db.pkg");
   //               oTvNode.setAttribute(IWfcSqlConstants.USER, sUser);
   //               oTvNode.setAttribute("package_name", sPackageName);
   //               oOutputNode.nodes().push(oTvNode);
   //            }
   //         }else{
   //            // 建立数据库类型列表
   //            oTvNode = new FWebTreeNode(IWfcSqlConstants.TABLE_LIST, "TABLES", true, "db.tablist");
   //            oTvNode.setAttribute(IWfcSqlConstants.USER, sUser);
   //            oOutputNode.nodes().push(oTvNode);
   //            oTvNode = new FWebTreeNode(IWfcSqlConstants.VIEW_LIST, "VIEWS", true, "db.vwlist");
   //            oTvNode.setAttribute(IWfcSqlConstants.USER, sUser);
   //            oOutputNode.nodes().push(oTvNode);
   //            oTvNode = new FWebTreeNode(IWfcSqlConstants.SEQUENCE_LIST, "SEQUENCES", true, "db.seqlist");
   //            oTvNode.setAttribute(IWfcSqlConstants.USER, sUser);
   //            oOutputNode.nodes().push(oTvNode);
   //            oTvNode = new FWebTreeNode(IWfcSqlConstants.PACKAGE_LIST, "PACKAGES", true, "db.pkglist");
   //            oTvNode.setAttribute(IWfcSqlConstants.USER, sUser);
   //            oOutputNode.nodes().push(oTvNode);
   //         }
   //      }
   //
   //      public void serviceProperty(IWebContext oContext,
   //                                  FXmlNode oConfigNode,
   //                                  FXmlNode oInputNode,
   //                                  FXmlNode oOutputNode)
   //            throws FException{
   //         String sPtyType = null;
   //         String sPtyId = null;
   //         String[] arItems = null;
   //         FDatabaseConsole oDbConsole = FDatabaseManager.console();
   //         String sAction = oInputNode.nodeText("Action");
   //         if(sAction.equalsIgnoreCase("property.value.load")){
   //            for(FXmlNode oPtyNode : oInputNode.nodes()){
   //               if(oPtyNode.name().equalsIgnoreCase(PROPERTY_VALUE)){
   //                  sPtyType = oPtyNode.attribute("pty_type");
   //                  sPtyId = oPtyNode.attribute("pty_id");
   //                  arItems = FStringUtil.split(sPtyId, '|');
   //                  if(sPtyType.equalsIgnoreCase("table")){
   //                     FXmlNode oPtyValNode = oDbConsole.findTableValue(oContext, arItems[0]);
   //                     if(oPtyValNode == null){
   //                        oPtyValNode = new FXmlNode(PROPERTY_VALUE);
   //                     }
   //                     oPtyValNode.setName(PROPERTY_VALUE);
   //                     oPtyValNode.setAttribute("pty_type", sPtyType);
   //                     oPtyValNode.setAttribute("pty_id", sPtyId);
   //                     oOutputNode.nodes().push(oPtyValNode);
   //                  }else if(sPtyType.equalsIgnoreCase("table.field")){
   //                     FXmlNode oPtyValNode = oDbConsole.findTableFieldValue(oContext, arItems[0],
   //                           arItems[1]);
   //                     if(oPtyValNode == null){
   //                        oPtyValNode = new FXmlNode(PROPERTY_VALUE);
   //                     }
   //                     oPtyValNode.setName(PROPERTY_VALUE);
   //                     oPtyValNode.setAttribute("pty_type", sPtyType);
   //                     oPtyValNode.setAttribute("pty_id", sPtyId);
   //                     oOutputNode.nodes().push(oPtyValNode);
   //                  }
   //               }
   //            }
   //         }else if(sAction.equalsIgnoreCase("property.value.save")){
   //            for(FXmlNode oPtyNode : oInputNode.nodes()){
   //               if(oPtyNode.name().equalsIgnoreCase(PROPERTY_VALUE)){
   //                  sPtyType = oPtyNode.attribute("pty_type");
   //                  sPtyId = oPtyNode.attribute("pty_id");
   //                  arItems = FStringUtil.split(sPtyId, '|');
   //                  if(sPtyType.equalsIgnoreCase("table")){
   //                     oDbConsole.saveTableValue(oContext, arItems[0], oPtyNode);
   //                     oOutputNode.nodes().push(new FXmlNode(PROPERTY_VALUE));
   //                  }else if(sPtyType.equalsIgnoreCase("table.field")){
   //                     oDbConsole.saveTableFieldValue(oContext, arItems[0], arItems[1], oPtyNode);
   //                     oOutputNode.nodes().push(new FXmlNode(PROPERTY_VALUE));
   //                  }
   //               }
   //            }
   //         }
   //      }

}
