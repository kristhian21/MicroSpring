package arep.microSpring.services;

import arep.microSpring.annotations.Component;
import arep.microSpring.annotations.RequestMapping;

@Component
public class CurrentTimeMillisService {

    @RequestMapping("time")
    public static String currentTimeMillis(){
        return "HTTP/1.1 200 OK\r\n"
                + "Content-Type: text/html\r\n"
                + "\r\n"
                + "<!DOCTYPE html>"
                + "<html>"
                + "<head>"
                + "<meta charset=\"UTF-8\">"
                + "<title>CurrenTimeMillis</title>\n"
                + "</head>"
                + "<body>"
                + "<h1>"
                + "The current time in milliseconds is:"
                + "</h1>"
                + "<p>" + String.valueOf(System.currentTimeMillis()) + "</p>"
                + "</body>"
                + "</html>";
    }
}
