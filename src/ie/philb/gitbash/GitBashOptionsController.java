/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ie.philb.gitbash;

import java.beans.PropertyChangeListener;
import javax.swing.JComponent;
import org.netbeans.spi.options.OptionsPanelController;
import org.openide.util.HelpCtx;
import org.openide.util.Lookup;
import org.openide.util.NbPreferences;

/**
 *
 * @author philip.bradley
 */

@OptionsPanelController.SubRegistration(
    location = "Miscellaneous",
    displayName = "#LBL_GitBashOptions",
    keywords = "#KW_GitBash",
    keywordsCategory = "Git/GitBash"
)
public class GitBashOptionsController extends OptionsPanelController {

    private GitBashOptionsPanel panel;

    @Override
    public void update() {
        panel.setGitBashPath(NbPreferences.forModule(GitBashOptionsController.class).get(GitBashSettings.KEY_GIT_BASH_PATH, GitBashSettings.DEFAULT_GIT_BASH_PATH));
    }

    @Override
    public void applyChanges() {
        NbPreferences.forModule(GitBashOptionsController.class).put(GitBashSettings.KEY_GIT_BASH_PATH, panel.getGitBashPath());
    }

    @Override
    public void cancel() {
    }

    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public boolean isChanged() {
        return true;
    }

    @Override
    public JComponent getComponent(Lookup lkp) {
        if (panel == null) {
            panel = new GitBashOptionsPanel();
        }

        return panel;
    }

    @Override
    public HelpCtx getHelpCtx() {
        return new HelpCtx("");
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener pl) {
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener pl) {
    }

}
