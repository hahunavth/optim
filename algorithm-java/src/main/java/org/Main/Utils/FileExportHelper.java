package org.Main.Utils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileExportHelper extends ExportHelper{
    private String fileName;
    private PrintWriter pw;

    public FileExportHelper(String fileName) throws IOException {
        this.fileName = fileName;
        pw = new PrintWriter(new FileWriter(fileName));
    }

    public void writeLine() {
        pw.write(getLine());
    }

    @Override
    protected void finalize() throws Throwable {
        pw.close();
        super.finalize();
    }
}
