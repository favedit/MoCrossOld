package org.mo.eng.template.tag;

import org.mo.com.lang.FFatalError;
import org.mo.com.lang.RString;
import org.mo.com.lang.generic.MString;

public class FTplConvertTag
      extends FAbstractTplTag
{
   public static String NAME = "Convert";

   protected String _default;

   protected String _from;

   protected String _source;

   protected String _to;

   @Override
   public void onDump(MString dump){
      dump.append("Convert ");
      dump.append("[ source=", _source);
      dump.append(", from=", _from);
      dump.append(", to=", _to);
      dump.append(" ]");
   }

   @Override
   public int onStart(){
      String source = RString.nvl(_context.parseString(_source));
      String from = _context.parseString(_from);
      String to = _context.parseString(_to);
      String def = _context.parseString(_default);
      String[] froms = RString.split(from, '|');
      String[] tos = RString.split(to, '|');
      if(froms.length != tos.length){
         throw new FFatalError("[From] count not is [To] count");
      }
      boolean appended = false;
      int count = froms.length;
      for(int n = 0; n < count; n++){
         if(source.equalsIgnoreCase(froms[n])){
            _context.append(tos[n]);
            appended = true;
            break;
         }
      }
      if(!appended){
         _context.append(def);
      }
      return STOP;
   }

   public void setDefault(String value){
      _default = value;
   }

   public void setFrom(String value){
      _from = value;
   }

   public void setSource(String value){
      _source = value;
   }

   public void setTo(String value){
      _to = value;
   }
}
