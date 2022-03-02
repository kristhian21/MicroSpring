package arep.microSpring.services;

import arep.microSpring.annotations.Component;
import arep.microSpring.annotations.RequestMapping;
import arep.microSpring.servers.HttpServer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;

@Component
public class ReturnImageService {
    @RequestMapping("image")
    public static String loadImage() throws URISyntaxException {
        String respuesta;
        // Se establecen los headers de la respuesta
        respuesta = "HTTP/1.1 200 OK \r\n"
                + "Content-Type: " + "image/png" + "\r\n"
                + "\r\n";
        // Se carga el archivo con la imagen
        File file = new File("src/main/resources/public/cheems.png");
        try {
            // Se procesa la imagen
            BufferedImage bufferedImage = ImageIO.read(file);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(HttpServer.outputStream);
            ImageIO.write(bufferedImage, "png", byteArrayOutputStream);
            dataOutputStream.writeBytes(respuesta);
            dataOutputStream.write(byteArrayOutputStream.toByteArray());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return respuesta;
    }
}
