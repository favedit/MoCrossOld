package org.mo.logic.status.process;

import org.mo.com.lang.FObjects;
import org.mo.com.xml.FXmlNode;
//============================================================
// <T>cpu信息。</T>
//============================================================
public class SCpuInfo {
   
	
	  //cpu编号	
	  public String cpuId;
	  
	  //已用
	  public double used;
	  
	  //可用
	  public double avail;
	  
	  //cpu信息集合
	  public FObjects<SCpuInfo> cpus = new FObjects<SCpuInfo>(SCpuInfo.class);
	   
	  //============================================================
	  // <T>保存cpu信息。</T>
	  //
	  // @param xconfig 配置节点
	  //============================================================
	  public void saveConfig(FXmlNode xconfig){
		 xconfig.set("cpu_id",cpuId);
	     xconfig.set("cpu_used", used);
	     xconfig.set("cpu_avail", avail);
	  }
	  
	  //============================================================
	  // <T>保存cpu集合信息。</T>
	  //
	  // @param xconfig 配置节点
	  //============================================================
	  public void saveCpuConfig(FXmlNode xconfig){
	     xconfig.set("count", cpus.count());
	     for(SCpuInfo cpu : cpus){
	        FXmlNode xcpuInfo = xconfig.createNode("cpuInfo");
	        cpu.saveConfig(xcpuInfo);
	     }
	  }
}
