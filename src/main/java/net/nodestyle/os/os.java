package net.nodestyle.os;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by maciej on 23.09.16.
 */
public class os {
    String EOL=System.getProperty("line.separator");
    public static String arch() { //returns the architecture
        return System.getProperty("os.arch");
    }
    // TODO: 23.09.16 add os.constants
    // TODO: 23.09.16 add os.cpus() (use class CPU)
    // TODO: 23.09.16 add os.endianness() ???
    // TODO: 23.09.16 add os.endianness() (for which it was compiled)
    public static String homedir() {
        return System.getProperty("user.dir");
    }
    public static String hostname() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return null;
        }
    }
}
