/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ie.philb.gitbash;

import org.openide.windows.TopComponent;
import org.openide.windows.TopComponent.OpenActionRegistration;
import com.pty4j.PtyProcess;
import com.jediterm.terminal.ui.settings.DefaultSettingsProvider;
import com.jediterm.terminal.ui.JediTermWidget;
import com.jediterm.terminal.TtyConnector;
import static ie.philb.gitbash.GitBashTopComponent.COMPONENT_ID;
import java.awt.BorderLayout;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import javax.swing.JOptionPane;
import org.openide.util.Exceptions;

/**
 *
 * @author philb
 */
@TopComponent.Description(
        preferredID = COMPONENT_ID,
        persistenceType = TopComponent.PERSISTENCE_NEVER
)
@TopComponent.Registration(
        mode = "output",
        openAtStartup = false
)
@OpenActionRegistration(
        displayName = COMPONENT_ID
)
public class GitBashTopComponent extends TopComponent {

    public static final String COMPONENT_ID = "GitBash";

    public GitBashTopComponent() {

        try {
            setName(COMPONENT_ID);
            JediTermWidget terminal = new JediTermWidget(80, 24, new DefaultSettingsProvider());

            setLayout(new BorderLayout());
            add(terminal, BorderLayout.CENTER);

            String[] command = {"C:\\Program Files\\Git\\bin\\bash.exe", "--login", "-i"};

            PtyProcess process = PtyProcess.exec(command, null, null);

            TtyConnector connector = new PtyProcessTtyConnector(process, StandardCharsets.UTF_8);
            terminal.createTerminalSession(connector).start();
            terminal.start();
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
    }

}
