package org.mo.eng.cache;

public class FPathCacheConsole
      extends FCacheConsole
{
   //private final FStrings _idsArray = new FStrings();
   //   public FPathCache cache(Object oOwner,
   //                           String sCacheId)
   //         throws FError{
   //      FPathCache oCahce = super.cache(oOwner, sCacheId);
   //      if(oCahce == null){
   //         String sPath = makeCacheName(oOwner, sCacheId);
   //         int nIndex = _idsArray.indexOf(sPath);
   //         if(nIndex == -1){
   //            _idsArray.push(sPath);
   //            nIndex = _idsArray.indexOf(sPath);
   //         }
   //         String sId = RInteger.format(nIndex, 8);
   //         oCahce = new FPathCache(60 * 60 * 100);
   //         oCahce.setId(sId);
   //         oCahce.setPath(RFile.makeFilename(workPath(), sId));
   //         super.push(oOwner, sCacheId, oCahce);
   //      }
   //      return oCahce;
   //   }
}
