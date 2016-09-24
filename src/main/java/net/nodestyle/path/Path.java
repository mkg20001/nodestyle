/**
 *
 *         Copyright (C) 2016 Maciej Krüger <mkg20001@gmail.com>
 *
 *         NodeStyle is free software: you can redistribute it and/or modify
 *         it under the terms of the GNU General Public License as published by
 *         the Free Software Foundation, either version 3 of the License, or
 *         (at your option) any later version.
 *
 *         NodeStyle is distributed in the hope that it will be useful,
 *         but WITHOUT ANY WARRANTY; without even the implied warranty of
 *         MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *         GNU General Public License for more details.
 *
 *         You should have received a copy of the GNU General Public License
 *         along with NodeStyle.  If not, see <http://www.gnu.org/licenses/>.
 */
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
