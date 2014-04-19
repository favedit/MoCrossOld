package org.mo.jfa.face.test;

import org.mo.com.collections.FDataset;
import org.mo.com.collections.FRow;
import org.mo.com.lang.FObjectId;

public class FSectorPage
      extends FObjectId
{

   private FRow _sector;

   private String _sectorId;

   private FDataset _sectors;

   public FRow getSector(){
      return _sector;
   }

   public String getSectorId(){
      return _sectorId;
   }

   public FDataset getSectors(){
      return _sectors;
   }

   public void setSector(FRow sector){
      _sector = sector;
   }

   public void setSectorId(String sectorId){
      _sectorId = sectorId;
   }

   public void setSectors(FDataset sectors){
      _sectors = sectors;
   }

}
