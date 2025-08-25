/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ie.philb.gitbash;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.openide.util.Exceptions;
import org.openide.windows.TopComponent;
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
public final class LaunchGitBashAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        TopComponent tc = WindowManager.getDefault().findTopComponent(GitBashTopComponent.COMPONENT_ID);
        if (tc != null) {
            tc.open();
            tc.requestActive();
        } else {
            Exceptions.printStackTrace(new Exception("Failed to find top component " + GitBashTopComponent.COMPONENT_ID));
        }
    }

}
