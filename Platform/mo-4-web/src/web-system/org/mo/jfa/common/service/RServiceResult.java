package org.mo.jfa.common.service;

import org.mo.com.lang.RString;
import org.mo.com.xml.FXmlNode;
import org.mo.web.protocol.context.IWebOutput;

public class RServiceResult
{

   public static String NODE_COMMANDS = "Commands";

   public static String NODE_COMMAND = "Command";

   public static String PTY_NAME = "name";

   public static String CMD_TREE_RELOAD = "tree.reload";

   public static String CMD_TREE_NODE_REFRESH = "tree.node.refresh";

   public static FXmlNode commandsNode(IWebOutput output){
      FXmlNode commands = output.config().nodes().findNode(NODE_COMMANDS);
      if(null == commands){
         commands = output.config().nodes().create(NODE_COMMANDS);
      }
      return commands;
   }

   public static void setPageRedirect(IWebOutput output,
                                      String page){
      FXmlNode commands = commandsNode(output);
      FXmlNode command = commands.createNode(NODE_COMMAND);
      command.set(PTY_NAME, "page.redirect");
      String[] items = RString.splitTwo(page, '@', true);
      if(null == items){
         command.set("page", page);
      }else{
         command.set("action", items[0]);
         command.set("page", items[1]);
      }
   }

   public static void setTreeNodeRefresh(IWebOutput output,
                                         String uuid){
      FXmlNode commands = commandsNode(output);
      FXmlNode command = commands.createNode(NODE_COMMAND);
      command.set(PTY_NAME, "tree.node.refresh");
      command.set("uuid", uuid);
   }

   public static void setTreeParentRefresh(IWebOutput output){
      FXmlNode commands = commandsNode(output);
      FXmlNode command = commands.createNode(NODE_COMMAND);
      command.set(PTY_NAME, "tree.parent.refresh");
   }

   public static void setTreeRefresh(IWebOutput output){
      FXmlNode commands = commandsNode(output);
      FXmlNode command = commands.createNode(NODE_COMMAND);
      command.set(PTY_NAME, "tree.node.refresh");
   }

   public static void setTreeReload(IWebOutput output){
      FXmlNode commands = commandsNode(output);
      FXmlNode command = commands.createNode(NODE_COMMAND);
      command.set(PTY_NAME, CMD_TREE_RELOAD);
   }

}
