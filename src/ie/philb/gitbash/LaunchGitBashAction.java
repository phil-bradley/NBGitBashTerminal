/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ie.philb.gitbash;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.openide.util.Exceptions;
import org.openide.windows.Mode;
import org.openide.windows.WindowManager;

/**
 *
 * @author philb
 */
@ActionID(
        category = "Tools",
        id = "ie.philb.gitbash.LaunchGitBash"
)
@ActionRegistration(
        displayName = "Git Bash"
)
@ActionReference(
        path = "Menu/Tools",
        position = 0
)
public final class LaunchGitBashAction extends AbstractAction {

    public LaunchGitBashAction() {
        this(Icons.NEW_TERM);
    }

    public LaunchGitBashAction(ImageIcon icon) {
        super("New Terminal", icon);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            GitBashTopComponent tc = new GitBashTopComponent();

            Mode mode = WindowManager.getDefault().findMode("output");

            if (mode != null) {
                mode.dockInto(tc);
            }

            tc.open();
            tc.requestActive();

        } catch (Exception ex) {
            Exceptions.printStackTrace(new Exception("Failed to open GitBashTopComponent", ex));
        }
    }

}
