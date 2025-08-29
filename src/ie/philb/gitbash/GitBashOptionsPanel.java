/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ie.philb.gitbash;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author philip.bradley
 */

public class GitBashOptionsPanel extends JPanel {

    private JTextField pathField;

    public GitBashOptionsPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(new JLabel("Path to bash.exe:"));
        pathField = new JTextField(GitBashSettings.getGitBashPath(), 30);
        add(pathField);

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e
                -> GitBashSettings.setGitBashPath(pathField.getText())
        );
        add(saveButton);
    }

    public void setGitBashPath(String gitBashPath) {
        pathField.setText(gitBashPath);
    }
    
    public String getGitBashPath() {
        return pathField.getText();
    }
}
