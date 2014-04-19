package org.mo.platform.face.test;

import java.io.File;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.mo.com.lang.RDouble;
import org.mo.com.lang.RLong;
import org.mo.com.lang.type.TDateTime;
import org.mo.com.xml.FXmlDocument;
import org.mo.com.xml.FXmlNode;


public class FileSave {

	public static void main(String[] args) {
	   
	   Calendar c = Calendar.getInstance();
	   int year = c.get(Calendar.YEAR);
	   System.out.println(year);
	   Date da = new Date();
	   //System.out.println(da.toString());
	   
	   SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	   long Str = RLong.parse(sdf.format(new Date()));
	   System.out.println(Str+"***");
	   
	   TDateTime recordDate = new TDateTime(new Date());
	   
	   recordDate.add(-1000*60*60*24*7);
	   
	   System.out.println(recordDate.format("yyMMdd")+"+++++");
      
//	   String time = "130913";
//	   File file = new File("D:\\log/");
//	   File[] test = file.listFiles();
//	   for (int i = 0; i < test.length; i++) {
//	      if(test[i].isFile()){
//	         //System.out.println(test[i]);
//	         String filename = test[i].getName();
//	         String filename1 = filename.substring(filename.lastIndexOf("_")-6,filename.lastIndexOf("_"));
//	         System.out.println(filename1+"----");
//	         /*String filename2 = filename1.substring(filename1.lastIndexOf("_")+1);
//	         System.out.println(filename2);
//	         if(filename2.equals(time)){
//	            test[i].delete();  
//	         }*/
//	      }
//         
//      }
	   
		String sh = "~/ZW-QKB-Server.WK";
		System.out.println(sh.substring(sh.indexOf('/')+1));
		System.out.println(formatTwoDecmals(RDouble.parse(2.2310201)));
		String reg = "[' ']+";
		Pattern p = Pattern.compile(reg);
		Matcher m = p.matcher("文件系统	      容量  已用  可用 已用%% 挂载点");
		String re = m.replaceAll(" ");
		String[] hehe = re.split(" ");
		for(int i = 0;i<hehe.length;i++){
			System.out.println(hehe[i]);
		}
	    String gren="23G";
		System.out.println(gren.substring(0,gren.lastIndexOf("G")));
		 FXmlDocument xmldoc = new FXmlDocument();
   	     xmldoc.loadFile("E:/工作/服务器部署/test.xml");
   	     for(FXmlNode node :xmldoc.root().nodes()){
   	    	 FXmlNode xml1 = node.createNode("property","今天");
   	   	     xml1.set("hh","132");
   		     for(FXmlNode n :node.nodes()){
   			     String name = n.name();//得到节点名称
   			     if("Host".equals(name)){
   			   	    n.set("host", "192.168.2.1");
   			     }
   		     }
   	     }
   	    
   	     xmldoc.saveFile("E:/工作/服务器部署/test.xml");
	}
   private static double formatTwoDecmals(double parse) {
	   BigDecimal bd = new BigDecimal(parse);
	  return bd.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
	}
//============================================================
   // <T>把多个空格替换成一个空格。</T>
   //
   // @param str 传入字符串
   //============================================================
   public String replaceSpace(String str){
	   String regEX = "[' ']+";
	   Pattern p = Pattern.compile(regEX);
	   Matcher m = p.matcher(str);
	   return m.replaceAll(" ");
   }
}
