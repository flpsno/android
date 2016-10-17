package br.com.impacta.t_026_download;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by nalmir on 15/10/2016.
 */
public class ToolBox {

    public static void GDownload(String urlServer, String local) throws Exception {

        URL url = null;
        //
        url = new URL(urlServer);
        //
        URLConnection connection = url.openConnection();
        //
        File fileArquivo = new File(local);
        if (!fileArquivo.exists()){
            fileArquivo.delete();
        }
        // ARquivo local do Aparelho
        FileOutputStream outputStream =
                new FileOutputStream(local, true);

        // Acesso Web
        InputStream inputStream =
                new BufferedInputStream(connection.getInputStream());

        //
        byte[] buffer = new byte[1024];
        int n;
        //
        while((n=inputStream.read(buffer)) != -1){
            outputStream.write(buffer, 0, n);
        }
        //
        outputStream.flush();
        outputStream.close();
        //
        inputStream.close();
    }
}
