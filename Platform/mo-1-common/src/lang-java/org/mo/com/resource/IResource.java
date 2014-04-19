/*
 * @(#)IResource.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.com.resource;

import org.mo.com.lang.FMultiString;
import org.mo.com.xml.FXmlNode;

//============================================================
// <T>资源接口。</T>
//============================================================
public interface IResource
{
   FXmlNode config();

   Boolean findBoolean(String name);

   Class<?> findClass(String name);

   FXmlNode findConfig(String name);

   Integer findInteger(String name);

   String findDisplay(String name);

   Long findLong(String name);

   FMultiString findMultiString(String name);

   String findMultiString(String name,
                          String language);

   String findString(String name);

   String findString(String name,
                     String language);

   Class<?> reference();
}
