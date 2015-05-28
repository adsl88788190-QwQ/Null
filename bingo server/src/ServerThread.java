import java.io.BufferedReader;
import java.io.IOException;

public class ServerThread extends Thread {
 
    private String usermsg = "";
    private BufferedReader clientin;
 
    @SuppressWarnings("unused")
    private ServerThread() {
        // can't use Constructor
    }
 
    public ServerThread(BufferedReader in) {
        clientin = in;// 接進來處理
    }
 
    @Override
    public void run() {
        // loop to listen User's input
        while (true) {
            try {
                usermsg = clientin.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("使用者說:" + usermsg);
             
            if (usermsg=="再見"||usermsg=="bye"){
                System.out.println("中斷連接");
                try {
                    clientin.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                Thread.sleep(500); // 休息0.5秒
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
             
        }
    }
 
}