package org.mo.platform.face.test;

import java.io.Serializable;

import org.mo.com.lang.FObjectId;
import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.FXmlNodes;

//============================================================
// <T>服务器容器。</T>
//============================================================
public class FTagContainer extends FObjectId implements Serializable {
	// 序列化编号
	private static final long serialVersionUID = 1L;

	// 服务器名称
	private String _hello;

	// 服务器名称
	private FXmlNode _config;

	private String _userName;

	private String _projectUrl;

	private String _projectName;

	public String getUserName() {
		return _userName;
	}

	public void setUserName(String _userName) {
		this._userName = _userName;
	}

	public String getProjectUrl() {
		return _projectUrl;
	}

	public void setProjectUrl(String _projectUrl) {
		this._projectUrl = _projectUrl;
	}

	public String getProjectName() {
		return _projectName;
	}

	public void setProjectName(String _projectName) {
		this._projectName = _projectName;
	}

	// ============================================================
	// <T>获得服务器名称。</T>
	//
	// @return 服务器名称
	// ============================================================
	public String getHello() {
		return _hello;
	}

	// ============================================================
	// <T>设置服务器名称。</T>
	//
	// @param serverName 服务器名称
	// ============================================================
	public void setHello(String hello) {
		_hello = hello;
	}

	// ============================================================
	// <T>获得服务器名称。</T>
	//
	// @return 服务器名称
	// ============================================================
	public FXmlNode config() {
		return _config;
	}

	// ============================================================
	// <T>获得服务器名称。</T>
	//
	// @return 服务器名称
	// ============================================================
	public void setConfig(FXmlNode config) {
		_config = config;
	}
}
