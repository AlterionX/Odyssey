package loghandle;

import java.rmi.UnexpectedException;

/**
 * Created by benbe on 3/9/2017.
 */
public class ChatLogEntry {
    private int delay;
    private int speaker;
    private String line;

    public ChatLogEntry(String line, int basicDelay, int systemInd) throws UnexpectedException {
        String[] segments = line.split(":", 3);
        if (segments.length < 3) {
            throw new UnexpectedException("Bad file format. Not enough parts. "
                    + "Line: " + line + ", Parts: " + segments.length);
        }
        if (segments[0].length() == 0) {
            delay = basicDelay;
        } else {
            delay = Integer.parseInt(segments[0]);
        }
        if (segments[1].length() == 0) {
            speaker = systemInd;
        } else {
            speaker = Integer.parseInt(segments[1]);
        }
        this.line = segments[2];
    }

    public int delay() {
        return delay;
    }
    public int speaker() {
        return speaker;
    }
    public String text() {
        return line;
    }

    @Override
    public String toString() {
        return "Delay:" + delay() + ", Speaker:" + speaker() + ",LineText:" + text();
    }
}
