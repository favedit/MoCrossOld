package org.mo.util.data;

import org.mo.com.lang.FBytes;
import org.mo.com.lang.RByte;

public class RDataValid{

   public static void main(String[] args){
      //      byte[] passwordShaBytes = RSha1.encodeBytes("1234");
      //      String passwordSha = RByte.toHexString(passwordShaBytes);
      //      byte[] passwordBytes = "iamalex12".getBytes();
      //      FString loginCode = new FString();
      //      for(int n = 0; n < passwordBytes.length; n++){
      //         System.out.println(passwordBytes[n] + "^" + passwordShaBytes[n % passwordShaBytes.length] + "=" + (passwordBytes[n] ^ passwordShaBytes[n % passwordShaBytes.length]));
      //         byte value = (byte) (passwordBytes[n] ^ passwordShaBytes[n % passwordShaBytes.length]);
      //         RByte.toHexChars(loginCode, value);
      //      }
      //      System.out.println("SHA  = " + passwordSha);
      //      System.out.println("CODE = " + loginCode.toString());
      byte[] sha = RByte.fromHexString("");
      byte[] code = RByte.fromHexString("");
      FBytes result = new FBytes();
      for(int n = 0; n < code.length; n++){
         byte value = (byte) (code[n] ^ sha[n % sha.length]);
         result.append(value);
      }
      System.out.println(new String(result.toArray()));
   }
}
