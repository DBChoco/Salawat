package io.github.dbchoco.Salawat.helpers;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class BrowserOpener {

    private static final String os = System.getProperty("os.name").toLowerCase();

    public BrowserOpener(){

    }

    public static void openLink(String url) throws IOException, URISyntaxException {
        if (os.contains("nix") || os.contains("nux")){
            Runtime rt = Runtime.getRuntime();
            String[] browsers = { "google-chrome", "firefox", "mozilla", "epiphany", "konqueror",
                    "netscape", "opera", "links", "lynx" };

            StringBuffer cmd = new StringBuffer();
            for (int i = 0; i < browsers.length; i++)
                if(i == 0)
                    cmd.append(String.format(    "%s \"%s\"", browsers[i], url));
                else
                    cmd.append(String.format(" || %s \"%s\"", browsers[i], url));
            // If the first didn't work, try the next browser and so on

            rt.exec(new String[] { "sh", "-c", cmd.toString() });
        }
        else{
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                Desktop.getDesktop().browse(new URI(url));
            }
        }
    }
}
