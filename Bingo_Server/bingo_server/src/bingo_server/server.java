package bingo_server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
 
public class server {
 
    private static int serverport = 5050;
    private static ServerSocket serverSocket;
 
    // �Φ�C���x�s�C��client
    private static ArrayList<Socket> players=new ArrayList<Socket>();
 
    // �{���i�J�I
    public static void main(String[] args) {
        try {
            serverSocket = new ServerSocket(serverport);
            System.out.println("Server is start.");
 
            // ��Server�B�@����
            while (!serverSocket.isClosed()) {
                // ��ܵ��ݫȤ�ݳs��
                System.out.println("Wait new clinet connect");
 
                // �I�s���ݱ����Ȥ�ݳs��
                waitNewPlayer();
            }
 
        } catch (IOException e) {
            System.out.println("Server Socket ERROR");
        }
 
    }
 
    // ���ݱ����Ȥ�ݳs��
    public static void waitNewPlayer() {
        try {
            Socket socket = serverSocket.accept();
 
            // �I�s�гy�s���ϥΪ�
            createNewPlayer(socket);
        } catch (IOException e) {
 
        }
 
    }
 
    // �гy�s���ϥΪ�
    public static void createNewPlayer(final Socket socket) {
 
        // �H�s��������Ӱ���
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // �W�[�s���ϥΪ�
                    players.add(socket);
 
                    // ���o������y 
                    BufferedReader br = new BufferedReader(
                            new InputStreamReader(socket.getInputStream()));
 
                    // ��Socket�w�s���ɳs�����
                    while (socket.isConnected()) {
                        // ���o������y���T��
                        String msg= br.readLine();
 
                        // ��X�T�� 
                        System.out.println(msg);
 
                        // �s���T�����䥦���Ȥ��
                        castMsg(msg);
                    }
 
                } catch (IOException e) {
 
                }
 
                // �����Ȥ��
                players.remove(socket);            
            }
        });
 
        // �Ұʰ����
        t.start();
    }
 
    // �s���T�����䥦���Ȥ��
    public static void castMsg(String Msg){
        // �гysocket�}�C
        Socket[] ps=new Socket[players.size()]; 
 
        // �Nplayers�ഫ���}�C�s�Jps
        players.toArray(ps);
 
        // ���Xps�����C�@�Ӥ���
        for (Socket socket :ps ) {
            try {
                // �гy������X��y
                BufferedWriter bw;
                bw = new BufferedWriter( new OutputStreamWriter(socket.getOutputStream()));
 
                // �g�J�T�����y
                bw.write(Msg+"\n");
 
                // �ߧY�o�e
                bw.flush();
            } catch (IOException e) {
 
            }
        }
    }
}