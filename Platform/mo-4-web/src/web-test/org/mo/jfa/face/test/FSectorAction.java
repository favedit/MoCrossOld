package org.mo.jfa.face.test;

public class FSectorAction
      implements
         ISectorAction
{

   //   public String construct(ISqlContext sqlContext,
   //                           FSectorPage page){
   //      String sql = "SELECT * FROM FIN_SECTOR_DS";
   //      FSqlQuery query = new FSqlQuery(sqlContext, sql);
   //      FDataset ds = query.fetchDataset();
   //      System.out.println("------- Hello: " + ds.dump());
   //      page.setSectors(ds);
   //      return "sectorList";
   //   }
   //
   //   public String delete(ISqlContext sqlContext,
   //                        FSectorPage page){
   //      String sql = "SELECT * FROM FIN_SECTOR_DS WHERE SECTOR_ID=" + page.getSectorId();
   //      FSqlQuery query = new FSqlQuery(sqlContext, sql);
   //      FRow row = query.fetchRow();
   //      page.setSector(row);
   //      System.out.println("------- Delete: " + page);
   //      return "sectorItem";
   //   }
   //
   //   public String deleteSave(ISqlContext sqlContext,
   //                            FSectorPage page){
   //      FRow row = page.getSector();
   //      System.out.println("------- Insert Save: " + row.dump());
   //      FFinSectorDpi dpiSector = new FFinSectorDpi(sqlContext);
   //      dpiSector.doDelete(null, row.pack().toString(), null, null);
   //      return construct(sqlContext, page);
   //   }
   //
   //   public String insert(FSectorPage page){
   //      System.out.println("------- Insert: " + page);
   //      page.setSector(new FRow());
   //      return "sectorItem";
   //   }
   //
   //   public String insertSave(ISqlContext sqlContext,
   //                            FSectorPage page){
   //      FRow row = page.getSector();
   //      System.out.println("------- Insert Save: " + row.dump());
   //      FFinSectorDpi dpiSector = new FFinSectorDpi(sqlContext);
   //      dpiSector.doInsert(null, row.pack().toString(), null);
   //
   //      return construct(sqlContext, page);
   //   }
   //
   //   public String update(ISqlContext sqlContext,
   //                        FSectorPage page){
   //      String sql = "SELECT * FROM FIN_SECTOR_DS WHERE SECTOR_ID=" + page.getSectorId();
   //      FSqlQuery query = new FSqlQuery(sqlContext, sql);
   //      FRow row = query.fetchRow();
   //      page.setSector(row);
   //      System.out.println("------- Update: " + row);
   //      return "sectorItem";
   //   }
   //
   //   public String updateSave(ISqlContext sqlContext,
   //                            FSectorPage page){
   //      FRow row = page.getSector();
   //      System.out.println("------- Insert Save: " + row.dump());
   //      FFinSectorDpi dpiSector = new FFinSectorDpi(sqlContext);
   //      dpiSector.doUpdate(null, row.pack().toString(), null, null);
   //      return construct(sqlContext, page);
   //   }

}
