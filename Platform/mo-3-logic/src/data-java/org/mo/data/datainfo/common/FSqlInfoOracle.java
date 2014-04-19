package org.mo.data.datainfo.common;

import org.mo.com.collections.FDataset;
import org.mo.com.collections.FRow;
import org.mo.com.data.ISqlConnect;
import org.mo.com.data.MSqlConnect;
import org.mo.core.aop.RAop;
import org.mo.eng.property.IPropertyConsole;

public class FSqlInfoOracle
      extends MSqlConnect
{
   private IPropertyConsole _ptyConsole;

   public FSqlInfoOracle(ISqlConnect connect){
      super(connect);
      _ptyConsole = RAop.find(IPropertyConsole.class);
   }

   public FSqlInfoPackages listPackages(){
      FSqlInfoPackages infos = new FSqlInfoPackages();
      String sql = _ptyConsole.property("database.oracle|package.list");
      FDataset ds = activeConnection().fetchDataset(sql);
      for(FRow row : ds){
         FSqlInfoPackage info = new FSqlInfoPackage();
         info.setName(row.get("PACKAGE_NAME"));
         infos.push(info);
      }
      return infos;
   }

   public FSqlInfoTables listTables(){
      FSqlInfoTables infos = new FSqlInfoTables();
      String sql = _ptyConsole.property("database.oracle|table.list");
      FDataset ds = activeConnection().fetchDataset(sql);
      for(FRow row : ds){
         FSqlInfoTable info = new FSqlInfoTable();
         info.setName(row.get("TABLE_NAME"));
         infos.push(info);
      }
      return infos;
   }

   public FSqlInfoViews listViews(){
      FSqlInfoViews infos = new FSqlInfoViews();
      String sql = _ptyConsole.property("database.oracle|view.list");
      FDataset ds = activeConnection().fetchDataset(sql);
      for(FRow row : ds){
         FSqlInfoView info = new FSqlInfoView();
         info.setName(row.get("VIEW_NAME"));
         infos.push(info);
      }
      return infos;
   }
}
