package org.mo.web.core.webtheme;

import org.mo.com.lang.FFatalError;
import org.mo.com.lang.FObjects;
import org.mo.com.lang.RBoolean;
import org.mo.com.lang.RString;
import org.mo.com.xml.EXmlConfig;
import org.mo.com.xml.EXmlConfigCopy;
import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.IXmlObject;
import org.mo.com.xml.RXmlObject;
import org.mo.eng.store.FXmlConfigConsole;
import org.mo.web.core.webtheme.common.XControl;
import org.mo.web.core.webtheme.common.XPackage;
import org.mo.web.core.webtheme.common.XWebTheme;

public class FWebThemeConsole
      extends FXmlConfigConsole<XWebTheme>
      implements
         IWebThemeConsole
{

   public final static String PTY_PARENT_NAME = "parent_name";

   @Override
   public FXmlNode buildConfig(String themeName){
      XWebTheme xtheme = get(themeName);
      FXmlNode config = new FXmlNode();
      xtheme.saveConfig(config, EXmlConfig.Simple);
      // 创建所有包节点
      for(IXmlObject xpackage : xtheme.children()){
         if(XPackage.isInstance(xpackage)){
            buildPackage(config, xpackage);
         }
      }
      return config;
   }

   protected void buildPackage(FXmlNode config,
                               IXmlObject xpackage){
      FXmlNode packageNode = config.createNode();
      xpackage.saveConfig(config, EXmlConfig.Simple);
      // 创建所有控件节点
      for(IXmlObject xcontrol : xpackage.children()){
         if(XControl.isInstance(xcontrol)){
            // 创建控件节点信息
            FXmlNode controlNode = packageNode.createNode();
            xcontrol.saveConfig(controlNode, EXmlConfig.Simple);
            // 建立节点部分
            buildPackageControl(controlNode, xpackage, xcontrol);
         }
      }
   }

   protected void buildPackageControl(FXmlNode controlNode,
                                      IXmlObject xpackage,
                                      IXmlObject xcontrol){
      // 复制父节点
      String parentName = xcontrol.innerGet("parent_name");
      if(RString.isNotEmpty(parentName)){
         IXmlObject xparentControl = xpackage.find("name", parentName);
         if(null == xparentControl){
            throw new FFatalError("Parent is not exists. (parent_name={0})", parentName);
         }
         buildPackageControl(controlNode, xpackage, xparentControl);
      }
      for(IXmlObject xpart : xcontrol.children()){
         // 复制父节点的所有子节点
         FXmlNode partNode = controlNode.createNode();
         xpart.saveConfig(partNode, EXmlConfig.Simple);
      }
   }

   @Override
   public void copy(IXmlObject xsource,
                    IXmlObject xtarget){
      // 递规建立所有子节点
      if(xsource.hasChild()){
         for(IXmlObject xpackage : xsource.children()){
            String name = xpackage.innerGet(PTY_NAME);
            IXmlObject xtargetPackage = xtarget.find(PTY_NAME, name);
            if(null == xtargetPackage){
               FXmlNode packageNode = new FXmlNode(xpackage.name());
               xtargetPackage = createElement(packageNode, EXmlConfig.Full);
               xtarget.children().push(xtargetPackage);
               RXmlObject.copyAttributes(EXmlConfigCopy.SkipEmpty, xpackage, xtargetPackage);
            }
            copyPackage(xpackage, xtargetPackage);
         }
      }
   }

   protected void copyPackage(IXmlObject xsourcePackage,
                              IXmlObject xtargetPackage){
      // 递规建立所有子节点
      if(xsourcePackage.hasChild()){
         for(IXmlObject xcontrol : xsourcePackage.children()){
            String name = xcontrol.innerGet(PTY_NAME);
            IXmlObject xtargetControl = xtargetPackage.find(PTY_NAME, name);
            if(null == xtargetControl){
               FXmlNode childNode = new FXmlNode(xcontrol.name());
               xtargetControl = createElement(childNode, EXmlConfig.Full);
               xtargetPackage.children().push(xtargetControl);
               RXmlObject.copyAttributes(EXmlConfigCopy.SkipEmpty, xcontrol, xtargetControl);
            }
            copyPackageControl(xsourcePackage, xcontrol, xtargetControl);
         }
      }
   }

   protected void copyPackageControl(IXmlObject xsourcePackage,
                                     IXmlObject xsourceControl,
                                     IXmlObject xtargetControl){
      // 复制父类的属性
      String parentName = xsourceControl.innerGet("parent_name");
      if(RString.isNotEmpty(parentName)){
         String[] parentNames = RString.split(parentName, ",");
         for(String name : parentNames){
            if(!RString.isEmpty(name)){
               IXmlObject xparentControl = xsourcePackage.find("name", name);
               if(null == xparentControl){
                  throw new FFatalError("Parent is not exists. (parent_name={0})", name);
               }
               copyPackageControl(xsourcePackage, xparentControl, xtargetControl);
            }
         }
      }
      // 递规建立所有子节点
      if(xsourceControl.hasChild()){
         for(IXmlObject xpart : xsourceControl.children()){
            String name = xpart.innerGet(PTY_NAME);
            IXmlObject xtargetPart = xtargetControl.find(PTY_NAME, name);
            boolean isValid = true;
            if(null == xtargetPart){
               FXmlNode childNode = new FXmlNode(xpart.name());
               xtargetPart = createElement(childNode, EXmlConfig.Full);
               xtargetControl.children().push(xtargetPart);
               isValid = false;
            }else{
               isValid = RBoolean.parse(xtargetPart.innerGet("is_valid"));
            }
            deepCopy(EXmlConfigCopy.SkipEmpty, xpart, xtargetPart, PTY_NAME);
            xtargetPart.innerSet("label", xpart.innerGet("label"));
            xtargetPart.innerSet("is_valid", RBoolean.toString(isValid));
         }
      }
   }

   @Override
   protected FObjects<XWebTheme> createCollection(){
      return new FObjects<XWebTheme>(XWebTheme.class);
   }
}
