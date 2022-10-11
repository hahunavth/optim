package org.example.Customize;

import org.apache.commons.cli.CommandLine;
import org.moeaframework.analysis.diagnostics.DiagnosticTool;
import org.moeaframework.core.Settings;
import org.moeaframework.util.CommandLineUtility;

import javax.swing.*;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import org.apache.commons.cli.CommandLine;
import org.moeaframework.core.Settings;
import org.moeaframework.util.CommandLineUtility;

public class CustomLauncherDiagnosticTool extends CommandLineUtility {
    public CustomLauncherDiagnosticTool() {
    }

    public void run(CommandLine commandLine) throws Exception {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception var2) {
                }
                DiagnosticTool diagnosticTool = new DiagnosticTool();
                diagnosticTool.setIconImages(Settings.getIconImages());
                diagnosticTool.setVisible(true);
            }
        });
    }

    public static void main(String[] args) throws Exception {
        (new org.moeaframework.analysis.diagnostics.LaunchDiagnosticTool()).start(args);
    }
}
