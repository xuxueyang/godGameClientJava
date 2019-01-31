import com.alibaba.fastjson.JSON;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class testOneNPCRoom {
    public  static  void main(String[] args) throws Exception{
        Socket socket =  new Socket("127.0.0.1", 6667);

        new ClientInputThread(socket).start();
        new ClientOutputThread(socket).start();


        AccountInfoDTO accountInfoDTO = new AccountInfoDTO();
        accountInfoDTO.accountName = "admin";
        accountInfoDTO.password = "admin";

        SocketModel model = new SocketModel();
        model.type = 0;
        model.command = 0;
        model.message = accountInfoDTO;
        OutputStream os = socket.getOutputStream();
        System.out.println(JSON.toJSON(model));
        System.out.println(JSON.toJSONBytes(model));
        byte[] bytes = JSON.toJSONBytes(model);
        int length = bytes.length;
        os.write(length);
        os.write(bytes);
        //开个线程接受服务器消息，以及开个线程，发送消息

    }

}
