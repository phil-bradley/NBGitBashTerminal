/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ie.philb.gitbash;

import java.util.prefs.Preferences;

/**
 *
 * @author philip.bradley
 */
public class GitBashSettings {

    private static final Preferences prefs = Preferences.userNodeForPackage(GitBashSettings.class);

    public static final String KEY_GIT_BASH_PATH = "gitBashPath";
    public static final String DEFAULT_GIT_BASH_PATH = "C:\\Program Files\\Git\\bin\\bash.exe";

    public static void setGitBashPath(String path) {
        prefs.put(KEY_GIT_BASH_PATH, path);
    }

    public static String getGitBashPath() {
        return prefs.get(KEY_GIT_BASH_PATH, DEFAULT_GIT_BASH_PATH);
    }

}
