package org.mo.platform.face.test;

import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.com.xml.FXmlDocument;
import org.mo.com.xml.FXmlNode;
import org.mo.web.protocol.context.IWebContext;

//============================================================
// <T>标签测试。</T>
//============================================================
public class FTagAction implements ITagAction {
	private static ILogger _logger = RLogger.find(FTagAction.class);

	public final String PAGE_CATALOG = "Catalog";

	public final String SEL_COL = "sel_collection";

	public final static String PAGE_SOURCE = "Source";

	// ============================================================
	// <T>构造处理</T>
	//
	// @return 处理结果
	// ============================================================
	@Override
	public String construct(IWebContext context) {
		String code = context.parameter("code");
		_logger.debug(this, "construct", "hello = {1}/{2}", code, code);
		return null;
	}

	// ============================================================
	// <T>构造处理</T>
	//
	// @return 处理结果
	// ============================================================
	@Override
	public String hello(IWebContext context, FTagContainer container) {
		String code = context.parameter("code");
		_logger.debug(this, "hello", "hello = {1}/{2}", code, code);
		container.setHello(code);
		FXmlDocument xdoc = new FXmlDocument();
		xdoc.loadFile("D:/ZW-Platform.WK/mp-platform/webroot/WEB-INF/config/web.tag/control.xml");
		container.setConfig(xdoc.root());
		FXmlNode xnode = xdoc.root();
		for(FXmlNode node : xdoc.root().nodes()){
			
		    //String name = node.get("name");//节点属性name的值
			//String name= node.name();//节点名称
			if(node.hasAttribute("name")){
				System.out.println("--------------------");
				for(FXmlNode no : node.nodes()){
					System.out.println("***"+no.get("name"));
					
				}
			}
			
			node.set("name","444444");
			for(FXmlNode no : node.nodes()){
				System.out.println("---"+no.get("name"));
				
			}
	    }
		// container.setHello(label);
		/*for (FXmlNode node : xnode.nodes()) {
		   System.out.println(node.get("name"));
			// node.setText("123154");
			if (node.get("name").equals("create_user_name")) {
				container.setUserName(node.text());
			}
			if (node.get("name").equals("project_url")) {
				container.setProjectUrl(node.text());
			}
			if (node.get("name").equals("project_name")) {
				container.setProjectName(node.text());
			}
		}*/
			 
		return null;
	}
}
