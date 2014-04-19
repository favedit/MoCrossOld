package org.mo.com.lang;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

//============================================================
// <T>唯一编号管理器。</T>
//============================================================
public class RUuid
{
   //============================================================
   // <T>生成唯一编号管理器。</T>
   //
   // @return 唯一编号
   //============================================================
   public final static String makeUuid(){
      return UUID.randomUUID().toString();
   }

   //private static int _seed = 0;

   private static char[] ENCODE_CHARS = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o',
         'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '[', ']'};

   private static String encode(byte[] data){
      FString result = new FString();
      int length = data.length;
      int i = 0;
      int b1, b2, b3;
      while(i < length){
         b1 = data[i++] & 0xff;
         if(i == length){
            result.append(ENCODE_CHARS[b1 >>> 2]);
            result.append(ENCODE_CHARS[(b1 & 0x3) << 4]);
            break;
         }
         b2 = data[i++] & 0xff;
         if(i == length){
            result.append(ENCODE_CHARS[b1 >>> 2]);
            result.append(ENCODE_CHARS[((b1 & 0x03) << 4) | ((b2 & 0xf0) >>> 4)]);
            result.append(ENCODE_CHARS[(b2 & 0x0f) << 2]);
            break;
         }
         b3 = data[i++] & 0xff;
         result.append(ENCODE_CHARS[b1 >>> 2]);
         result.append(ENCODE_CHARS[((b1 & 0x03) << 4) | ((b2 & 0xf0) >>> 4)]);
         result.append(ENCODE_CHARS[((b2 & 0x0f) << 2) | ((b3 & 0xc0) >>> 6)]);
         result.append(ENCODE_CHARS[b3 & 0x3f]);
      }
      return result.toString();
   }

   public static String simpleUuid(){
      UUID uuid = UUID.randomUUID();
      byte[] bytes = new byte[16];
      RLong.toBytes(uuid.getMostSignificantBits(), bytes, 0);
      RLong.toBytes(uuid.getLeastSignificantBits(), bytes, 8);
      return encode(bytes);
   }

   //   public static String simpleUuid(){
   //      byte[] data = new byte[12];
   //      RInteger.toBytes(++_seed, data, 0);
   //      RLong.toBytes(System.currentTimeMillis(), data, 4);
   //      return RBase64.encode(data).toString();
   //   }

   /**
    * <p>获得32位唯一编号的字符串</p>
    * <p>create date:2005/02/14</p>
    *
    * @return 32位唯一编号的字符串
    */
   public static String uuid(){
      return uuid(32);
   }

   /**
    * <p>获得指定长度的唯一编号的字符串</p>
    *
    * @param nLength 字符串长度
    * @return 唯一编号的字符串
    */
   public static String uuid(int nLength){
      Calendar oCalendar = Calendar.getInstance();
      oCalendar.setTime(new Date());
      Random oRand = new Random(oCalendar.get(Calendar.MILLISECOND));
      String sRand = RString.rightBytePad(Double.toString(oRand.nextDouble()), 17, "0");
      String sUUID = RInteger.format(oCalendar.get(Calendar.YEAR), 4) + RInteger.format(oCalendar.get(Calendar.MONTH + 1), 2) + RInteger.format(oCalendar.get(Calendar.DAY_OF_MONTH), 2) + RInteger.format(oCalendar.get(Calendar.HOUR_OF_DAY), 2)
            + RInteger.format(oCalendar.get(Calendar.MINUTE), 2) + RInteger.format(oCalendar.get(Calendar.SECOND), 2) + RInteger.format(oCalendar.get(Calendar.MILLISECOND), 3) + sRand.substring(2, 17);
      return sUUID;
   }
}
