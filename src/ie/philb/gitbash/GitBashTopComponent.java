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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import org.openide.util.Exceptions;
import org.openide.util.ImageUtilities;

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

        setName(COMPONENT_ID);

        setLayout(new BorderLayout());


        JToolBar toolbar = new JToolBar();
        toolbar.setFloatable(false);
        toolbar.setOrientation(SwingConstants.VERTICAL);
        
        JButton btnNewTerm = new JButton(new LaunchGitBashAction(Icons.NEW_TERM));
        btnNewTerm.setText(null);
        btnNewTerm.setToolTipText("New Terminal");
        toolbar.add(btnNewTerm);
        
        JButton btnOptions = new JButton(new LaunchGitBashAction(Icons.OPTIONS));
        btnOptions.setText(null);
        btnOptions.setToolTipText("Configure");
        toolbar.add(btnOptions);
        
        add(toolbar, BorderLayout.WEST);

        JediTermWidget terminal = new JediTermWidget(80, 24, new DefaultSettingsProvider());
        add(terminal, BorderLayout.CENTER);

        try {
            String[] command = {GitBashSettings.getGitBashPath(), "--login", "-i"};

            PtyProcess process = PtyProcess.exec(command, null, null);

            TtyConnector connector = new PtyProcessTtyConnector(process, StandardCharsets.UTF_8);
            terminal.createTerminalSession(connector).start();
            terminal.start();
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
    }

}
