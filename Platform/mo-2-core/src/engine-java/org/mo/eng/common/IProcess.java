/*
 * @(#)IProcessAble.java
 *
 * Copyright 2005 microbject, All Rights Reserved.
 *
 */
package org.mo.eng.common;

/**
 * <p>可纷发的处理</p>
 * 
 * @author ALEX
 */
public interface IProcess
{
   /**
    * <p>纷发的处理</p>
    *
    * @param sAction 处理命令
    * @param oParam 
    */
   public void process(String sAction,
                       Object oParam);
}
