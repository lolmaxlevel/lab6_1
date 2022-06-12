import modules.Server;

import java.util.logging.Level;
import java.util.logging.Logger;
public class Main {
    static int port;
    public static void main(String[] args) {
        final Logger log = Logger.getLogger(Main.class.getName());
        log.setLevel(Level.ALL);
        if (args.length == 1){
            try {port = Integer.parseInt(args[0]);}
            catch (NumberFormatException e) {
                log.warning("Введен неверный порт, будет использован порт по умолчанию");
                port=1703;
            }
        }
        else {
            log.info("порт по умолчанию 1703");
        port = 1703;
        }
        Server server = new Server(port);
            server.load();
            if (server.start()) {
                server.connect();
            }
            server.close();
    }
}