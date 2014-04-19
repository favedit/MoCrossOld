package org.mo.web.core.parser.js;

import org.mo.com.lang.FFatalError;
import org.mo.com.lang.FString;
import org.mo.com.lang.FStrings;
import org.mo.com.lang.RDump;
import org.mo.com.lang.RString;
import org.mo.com.lang.generic.TDumpInfo;
import org.mo.com.xml.FXmlNode;

public class FJsMethod
      extends FJsObject
{

   private String _description;

   private String _function;

   private String _name;

   private EJsMethodType _type;

   private FJsParameters _parameters;

   private FJsReturn _return;

   private FJsSee _see;

   private FJsMethodEvent _event;

   private String _functions;

   public String description(){
      return _description;
   }

   @Override
   public TDumpInfo dump(TDumpInfo info){
      RDump.append(info);
      info.appendLine();
      info.appendLine("name       =[", _name, "]");
      info.appendLine("type       =[", _type, "]");
      info.appendLine("function   =[", _function, "]");
      info.append("description=[", _description, "]");
      if(null != _parameters && !_parameters.isEmpty()){
         info.appendLine();
         info.append(RDump.LINE_L1);
         for(FJsParameter parameter : _parameters.values()){
            info.appendLine();
            info.append("P: " + parameter.dump());
         }
      }
      return info;
   }

   public String function(){
      return _function;
   }

   public FJsMethodEvent getEvent(){
      if(null == _event){
         _event = new FJsMethodEvent();
      }
      return _event;
   }

   public FJsReturn getReturn(){
      if(null == _return){
         _return = new FJsReturn();
      }
      return _return;
   }

   public FJsSee getSee(){
      if(null == _see){
         _see = new FJsSee();
      }
      return _see;
   }

   public FXmlNode makeNode(){
      FXmlNode node = new FXmlNode("Method");
      node.set("name", _name);
      if(_name.startsWith("on") || _name.startsWith("oe")){
         _type = EJsMethodType.Event;
      }
      node.set("type", _type.toString());
      node.set("description", _description);
      if(null != _functions){
         FXmlNode functionsNode = new FXmlNode("Functions");
         functionsNode.setText(_functions);
         node.push(functionsNode);
         node.set("is_functions", "Y");
      }else{
         node.set("is_functions", "N");
      }
      if(null != _parameters){
         FXmlNode parametersNode = new FXmlNode("Parameters");
         for(FJsParameter parameter : _parameters.toObjects()){
            if(parameter._name != null){
               parametersNode.push(parameter.makeNode());
            }
            node.push(parametersNode);
         }
         node.set("is_parameters", "Y");
      }else{
         node.set("is_parameters", "N");
      }
      if(null != _see){
         node.push(_see.makeSeeNode());
         node.set("is_see", "Y");
      }else{
         node.set("is_see", "N");
      }
      if(null != _event){
         node.push(_event.makeNode());
         node.set("is_event", "Y");
      }else{
         node.set("is_event", "N");
      }
      if(null != _return){
         node.push(_return.makeReturnNode());
         node.set("is_return", "Y");
      }else{
         node.set("is_return", "N");
      }
      return node;
   }

   public String name(){
      return _name;
   }

   public FJsParameters parameters(){
      if(null == _parameters){
         _parameters = new FJsParameters();
      }
      return _parameters;
   }

   public int parse(FStrings lines,
                    int start,
                    int end,
                    FStrings lines2){
      for(int n = start; n < end; n++){
         String line = lines.get(n);
         if(line.contains(RJsTag.METHOD)){
            _type = EJsMethodType.Method;
            n = parseMethod(lines, start, end, lines2);
            break;
         }
      }
      return end;
   }

   public String parse1(String line){

      return line;
   }

   protected int parseMethod(FStrings lines,
                             int start,
                             int end,
                             FStrings lines2){
      boolean comment = false;
      FString sub = new FString();
      FString subFun = new FString();
      for(int n = start; n < end; n++){
         String line = lines.get(n).trim().replaceAll("\t", "");
         // 判断是否是注释部分
         if(line.startsWith("/**")){
            comment = true;
            continue;
         }else if(line.endsWith("*/")){
            comment = false;
            continue;
         }
         // 分解注释信息
         if(comment){
            if(line.startsWith("*")){
               line = line.substring(1).trim();
            }
            if(RJsTag.METHOD.equals(line)){
               _description = sub.toString().trim();
               sub.clear();
            }else if(line.startsWith(RJsTag.PARAM)){
               line = line.substring(RJsTag.PARAM.length()).trim();
               FJsParameter parameter = new FJsParameter();
               parameter.parse(line);
               if(RString.isEmpty(parameter.name())){
                  throw new FFatalError("Name is null. (line={0})", line);
               }
               parameters().push(parameter);
               continue;
            }else if(line.startsWith(RJsTag.SEE)){
               line = line.substring(RJsTag.SEE.length()).trim();
               getSee().parse(line);
               continue;
            }else if(line.startsWith(RJsTag.EVENT)){
               line = line.substring(RJsTag.EVENT.length()).trim();
               getEvent().parse(line);
               continue;
            }else if(line.startsWith(RJsTag.RETURN)){
               line = line.substring(RJsTag.RETURN.length()).trim();
               getReturn().parse(line);
               continue;
            }
            // 增加新的行
            sub.appendLine(line);
         }else{
            // 判断是否是函数部分
            try{
               // 判断有下一个函数时
               if(line.startsWith(RJsTag.CM_FUNCTION) && n <= end - 1){
                  for(int i = n + 1; i < end - 1; i++){
                     String nextFunction = lines2.get(i);
                     int k = 0;
                     if(nextFunction.startsWith(RJsTag.CM_FUNCTION)){
                        for(int j = i; j > n; j--){
                           if(lines2.get(j).startsWith("}")){
                              k = j;
                              break;
                           }
                        }
                        for(int j = n; j <= k; j++){
                           subFun.appendLine(lines2.get(j).replaceAll("\t", " "));
                        }
                        _functions = RJsTag.formatJavaScript(subFun);
                        subFun.clear();
                        return n;
                     }
                  }
               }
               // 判断最后一个函数时
               if(n + 1 >= end){
                  int k = 0;
                  for(int i = end - 1; i > 0; i--){
                     if(lines.get(i).startsWith(RJsTag.CM_FUNCTION)){
                        k = i;
                        break;
                     }
                  }
                  for(int j = k; j <= end - 1; j++){
                     try{
                        if(null != lines2.get(j)){
                           subFun.appendLine(lines2.get(j).replaceAll("\t", " "));
                        }
                     }catch(Exception e){
                        throw new FFatalError(e, "Parse function error. (line={0})", lines2.get(j).toString());
                     }
                  }
                  _functions = RJsTag.formatJavaScript(subFun);
                  subFun.clear();
                  return n;
               }
            }catch(Exception e){
               throw new FFatalError(e, "Parse function error. (line={0})", line);
            }
         }
      }
      return end;
   }

   public void setDescription(String description){
      _description = description;
   }

   public void setFunction(String function){
      _function = function;
   }

   public void setName(String name){
      _name = name;
   }

   public void setType(EJsMethodType type){
      _type = type;
   }

   public EJsMethodType type(){
      return _type;
   }

}
