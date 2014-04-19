package org.mo.logic.status.process;

import org.mo.com.lang.FObjects;
import org.mo.com.xml.FXmlNode;


//============================================================
//<T>硬盘信息。</T>
//============================================================
public class SDiskInfo {
	
	  //文件系统
	  public String fileSystem;
	  
	  //硬盘容量
	  public long size;
	  
	  //已用
	  public long used;
	  
	  //可用
	  public long avail;
	  
	  //使用率
	  public long usePercent;
	  
	  //挂载点
	  public String mountedOn;
	  
      //硬盘信息集合
      public FObjects<SDiskInfo> disks = new FObjects<SDiskInfo>(SDiskInfo.class);
      
	  //============================================================
	  // <T>保存硬盘信息。</T>
	  //
	  // @param xconfig 配置节点
	  //============================================================
	  public void saveConfig(FXmlNode xconfig){
	     xconfig.set("file_system", fileSystem);
	     xconfig.set("disk_size", size);
	     xconfig.set("disk_used", used);
	     xconfig.set("disk_avail", avail);
	     xconfig.set("disk_usePercent",usePercent);
	     xconfig.set("mounted_on",mountedOn);
	  }
	  
      //============================================================
	  // <T>保存硬盘集合信息。</T>
	  //
	  // @param xconfig 配置节点
	  //============================================================
	  public void saveDiskConfig(FXmlNode xconfig){
	     xconfig.set("count", disks.count());
	     for(SDiskInfo disk : disks){
	        FXmlNode xprocess = xconfig.createNode("PartitionInfo");
	        disk.saveConfig(xprocess);
	     }
	  }
}
