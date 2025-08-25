/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ie.philb.gitbash;

import com.jediterm.core.util.TermSize;
import com.jediterm.terminal.TtyConnector;
import com.pty4j.PtyProcess;
import com.pty4j.WinSize;
import java.io.IOException;
import java.nio.charset.Charset;

public class PtyProcessTtyConnector implements TtyConnector {

    private final PtyProcess process;
    private final Charset charset;

    public PtyProcessTtyConnector(PtyProcess process, Charset charset) {
        this.process = process;
        this.charset = charset;
    }

    @Override
    public int read(char[] chars, int offset, int length) throws IOException {
        byte[] buf = new byte[length];
        int read = process.getInputStream().read(buf, 0, length);
        if (read > 0) {
            String s = new String(buf, 0, read, charset);
            int n = Math.min(s.length(), length);
            s.getChars(0, n, chars, offset);
            return n;
        }
        return read;
    }

    @Override
    public void write(byte[] bytes) throws IOException {
        process.getOutputStream().write(bytes);
        process.getOutputStream().flush();
    }

    @Override
    public void write(String string) throws IOException {
        write(string.getBytes(charset));
    }

    @Override
    public boolean isConnected() {
        return process.isAlive();
    }

    @Override
    public void resize(TermSize termSize) {
        process.setWinSize(new WinSize(termSize.getColumns(), termSize.getRows()));
    }

    @Override
    public int waitFor() throws InterruptedException {
        return process.waitFor();
    }

    @Override
    public boolean ready() throws IOException {
        return process.getInputStream().available() > 0;
    }

    @Override
    public String getName() {
        return "PtyProcess";
    }

    @Override
    public void close() {
        process.destroy();
    }
}
