import com.alibaba.fastjson.JSON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class ClientOutputThread extends Thread
{
    private Socket socket;

    public ClientOutputThread(Socket socket)
    {
        super();
        this.socket = socket;
    }

    @Override
    public void run()
    {
        try
        {

            OutputStream os = socket.getOutputStream();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(System.in));

            while (true){
                String line = reader.readLine();

//                os.write(line.getBytes());

                AccountInfoDTO accountInfoDTO = new AccountInfoDTO();
                accountInfoDTO.accountName = "admin";
                accountInfoDTO.password = "admin";

                SocketModel model = new SocketModel();
                model.type = 0;
                model.command = 0;
                model.message = accountInfoDTO;
                os.write(JSON.toJSONBytes(model));
                System.out.println(" ‰»Î£∫ "+line);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

}