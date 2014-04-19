import org.mo.com.net.FServerSocket;
import org.mo.com.net.FSocket;

public class RFlashTest{

   public static void main(String[] args){
      new RFlashTest().start();
   }

   protected FServerSocket _server = new FServerSocket(1000);

   public void start(){
      byte[] buffer = new byte[8192];
      while(true){
         FSocket client = _server.accept();
         client.input().read(buffer);
         System.out.println(new String(buffer));
      }
   }
}
