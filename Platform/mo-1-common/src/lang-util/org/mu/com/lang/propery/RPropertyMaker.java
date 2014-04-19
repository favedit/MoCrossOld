package org.mu.com.lang.propery;

import org.mo.com.io.FStringFile;
import org.mo.com.lang.RBoolean;
import org.mo.com.lang.RString;
import org.mo.com.xml.FXmlDocument;
import org.mo.com.xml.FXmlNode;

public class RPropertyMaker
{
   static String PATH = "D:/WP-Platform/mo-1-common/src/lang-util/org/mu/com/lang/propery";

   static String GETTER_SOURCE = "";

   static String SETTER_SOURCE = "";
   static{
      GETTER_SOURCE += "//============================================================\n";
      GETTER_SOURCE += "// <T>获得{label}。</T>\n";
      GETTER_SOURCE += "//\n";
      GETTER_SOURCE += "// @return {label}\n";
      GETTER_SOURCE += "//============================================================\n";
      GETTER_SOURCE += "public {type} {name}(){\n";
      GETTER_SOURCE += "   return _{name};\n";
      GETTER_SOURCE += "}\n";
      GETTER_SOURCE += "\n";
      SETTER_SOURCE += "//============================================================\n";
      SETTER_SOURCE += "// <T>设置{label}。</T>\n";
      SETTER_SOURCE += "//\n";
      SETTER_SOURCE += "// @param {name} {label}\n";
      SETTER_SOURCE += "//============================================================\n";
      SETTER_SOURCE += "public void set{name.upper.first}({type} {name}){\n";
      SETTER_SOURCE += "   _{name} = {name};\n";
      SETTER_SOURCE += "}\n";
      SETTER_SOURCE += "\n";
   }

   public static String parseSource(FXmlNode xconfig,
                                    String source){
      String name = xconfig.get("name");
      String label = xconfig.get("label");
      String type = xconfig.get("type");
      source = RString.replace(source, "{name}", name);
      source = RString.replace(source, "{name.upper.first}", RString.firstUpper(name));
      source = RString.replace(source, "{label}", label);
      source = RString.replace(source, "{type}", type);
      return source;
   }

   public static void main(String args[]){
      FXmlDocument xdocument = new FXmlDocument();
      xdocument.loadFile(PATH + "/resource.xml");
      FStringFile file = new FStringFile();
      for(FXmlNode xnode : xdocument.root()){
         if(xnode.isName("Property")){
            if(RBoolean.parse(xnode.get("getter"))){
               file.append(parseSource(xnode, GETTER_SOURCE));
            }
            if(RBoolean.parse(xnode.get("setter"))){
               file.append(parseSource(xnode, SETTER_SOURCE));
            }
         }
      }
      file.saveFile(PATH + "/resource.txt");
      System.out.println("Ok " + PATH + "/resource.txt");
   }
}
