/*
 * Created on 2005/07/07
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.mo.web.core.profile;

/**
 * @author maocy
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class FWebProfileWindowConsole
{
   //   private String userId(IWebContext iContext)
   //         throws FException{
   //      FForm oSessionForm = iContext.fillForm("session");
   //      FUnit oUserUnit = (FUnit) oSessionForm.get("user");
   //      if (oUserUnit != null) {
   //         return "u" + oUserUnit.get("user_id");
   //      }
   //      return null;
   //   }
   //
   //   public FXmlNode findSearch(IWebContext iContext,
   //                              String sWindow)
   //         throws FException{
   //      String sUserId = userId(iContext);
   //      if (!RString.isEmpty(sUserId)) {
   //         //String sWindowPath = sUserId + "." + sWindow;
   //         //FXmlDocument oDocument = syncDocument(sWindowPath);
   //         FXmlDocument oDocument = null;
   //         FXmlNode oSearchNode = oDocument.rootNode().findNode("SearchList");
   //         if (oSearchNode != null) {
   //            oSearchNode.setAttribute("window", sWindow);
   //            return oSearchNode;
   //         }
   //      }
   //      return null;
   //   }
   //
   //   public void saveSearch(IWebContext iContext,
   //                          String sWindow,
   //                          String sDataset,
   //                          FXmlNode oSearch)
   //         throws FException{
   //      String sUserId = userId(iContext);
   //      if (!RString.isEmpty(sUserId)) {
   //         //String sWindowPath = sUserId + "." + sWindow;
   //         String sSearch = oSearch.attribute("name");
   //         //FXmlDocument oDocument = syncDocument(sWindowPath);
   //         FXmlDocument oDocument = null;
   //         FXmlNode oSlNode = oDocument.rootNode().syncNode("SearchList");
   //         FXmlNode oDsNode = oSlNode.syncNode("Dataset", "name", sDataset);
   //         oDsNode.removeNode("Search", "name", sSearch);
   //         oDsNode.nodes().push((FXmlNode) oSearch.copy());
   //         oDocument.saveToFile();
   //      }
   //   }
   //
   //   public void removeSearch(IWebContext iContext,
   //                            String sWindow,
   //                            String sDataset,
   //                            String sSearch)
   //         throws FException{
   //      String sUserId = userId(iContext);
   //      if (!RString.isEmpty(sUserId)) {
   //         //String sWindowPath = sUserId + "." + sWindow;
   //         //FXmlDocument oDocument = syncDocument(sWindowPath);
   //         FXmlDocument oDocument = null;
   //         FXmlNode oSlNode = oDocument.rootNode().syncNode("SearchList");
   //         FXmlNode oDsNode = oSlNode.syncNode("Dataset", "name", sDataset);
   //         oDsNode.removeNode("Search", "name", sSearch);
   //         oDocument.saveToFile();
   //      }
   //   }
}
