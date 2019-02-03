import com.alibaba.fastjson.JSON;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;

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
        os.write(writeData("0","0","",accountInfoDTO));
//        os.write(length);
//        os.write(bytes);
        //开个线程接受服务器消息，以及开个线程，发送消息
        //
    }
    private static  byte[] writeData(String type,String area,String command,Object message){
        byte[] types = type.getBytes();
        byte[] areas = area.getBytes();
        byte[] commands = command.getBytes();
        byte[] messages = "".getBytes();
        if (message != null)
        {
            messages = JSON.toJSONBytes(message);
        }
        byte[] out = new  byte[4+types.length+areas.length+commands.length+messages.length];

        int destLen = 0;
        System.arraycopy("4".getBytes(), 0, out, destLen, "4".getBytes().length);
        destLen += "4".getBytes().length;
        System.arraycopy(types, 0, out, destLen, types.length);
        destLen += types.length;
        System.arraycopy(areas, 0, out, destLen, areas.length);
        destLen += areas.length;
        System.arraycopy(commands, 0, out, destLen, commands.length);
        destLen += commands.length;
        System.arraycopy(messages, 0, out, destLen, messages.length);
        destLen += messages.length;
        return out;
    }


}
