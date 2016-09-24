package net.nodestyle.path;

import java.util.regex.Pattern;

import static net.nodestyle.path.Helper.replaceLast;
import static net.nodestyle.path.Helper.unixSeperator;

public class Path {
    public String delimiter;
    public String sep;
    private boolean isWindows;
    public Path(String delimiter, String sep, boolean isWindows) {
        this.sep=sep;
        this.delimiter = delimiter;
        this.isWindows=isWindows;
    }
    public String basename(String path,String ext) {
        return replaceLast(basename(path),ext,"");
    }
    public String basename(String path) {
        String[] split=path.split(isWindows?Pattern.quote(sep)+"|"+Pattern.quote(unixSeperator):Pattern.quote(sep));
        return split[split.length-1];
    }
}
