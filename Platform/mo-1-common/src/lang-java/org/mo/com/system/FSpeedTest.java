package org.mo.com.system;

import java.util.Date;

public class FSpeedTest
//implements IDump
{
   public final static String FORMAT_DATE = "YYMMDD-HH24MISS";

   private Date _end;

   private final Date _first = new Date();

   private Date _last;

   private Date _start = new Date();

   private long _total;

   public FSpeedTest(){
   }

   public long diff(){
      return _end.getTime() - _start.getTime();
   }

   public void doEnd(){
      _end = new Date();
      _last = new Date();
      _total += _end.getTime() - _start.getTime();
   }

   public void doStart(){
      _start = new Date();
   }

   //   @Override
   //   public TDumpInfo dump(){
   //      return dump(new TDumpInfo(this));
   //   }
   //   @Override
   //   public TDumpInfo dump(TDumpInfo info){
   //      RDump.append(info);
   //      info.append("[", RDateTime.format(_start, FORMAT_DATE));
   //      info.append(" - ", RDateTime.format(_end, FORMAT_DATE));
   //      //info.append("] = ", GetDiff().ToString(), "s");
   //      return info;
   //   }
   public Date end(){
      return _end;
   }

   public Date first(){
      return _first;
   }

   public String formatDiffSecond(){
      return formatSpan(diff());
   }

   public String formatNowSecond(){
      return formatSpan(new Date().getTime() - _start.getTime());
   }

   public String formatSpan(long span){
      String upper = Long.toString(span / 1000000);
      String lower = Long.toString(span / 1000);
      return upper + "." + lower + "s";
   }

   public String formatTotalNowSecond(){
      return formatSpan(new Date().getTime() - _first.getTime());
   }

   public String formatTotalSecond(){
      return formatSpan(_total);
   }

   public Date last(){
      return _last;
   }

   public Date start(){
      return _start;
   }

   public long total(){
      return _total;
   }
}
