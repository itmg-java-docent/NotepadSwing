import javax.swing.*;
import java.io.*;

public class Notepad {
    private static JFrame FRAME;
    private JFileChooser fileChooser = new JFileChooser();
    private JPanel mainPanel;
    private JTextArea textArea;
    private JMenuBar menuBar;
    private JPanel panel;
    private JMenu menu;
    private JMenuItem mOpen;
    private JMenuItem mSave;
    private JMenuItem mSaveAs;
    private JScrollPane scrollPane;
    private File file = new File("Document1.txt");


    public Notepad() {
        mOpen.addActionListener(ae -> openFile());
        mSaveAs.addActionListener(ae -> saveAsFile());
        mSave.addActionListener(ae->saveFile());
    }

    private void openFile() {
        int status = fileChooser.showOpenDialog(mainPanel);
        if (status == JFileChooser.APPROVE_OPTION) {
            try {
                file = fileChooser.getSelectedFile();
                FileReader fileReader = new FileReader(file);
                FRAME.setTitle(file.getName());
                textArea.setText("");
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                String line = bufferedReader.readLine();
                while (line != null) {
                    textArea.append(line+"\n");
                    line = bufferedReader.readLine();
                }

            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }

    private void doSave() {
        try (BufferedWriter fileOut = new BufferedWriter(new FileWriter(fileChooser.getSelectedFile()))) {
            textArea.write(fileOut);
            FRAME.setTitle(fileChooser.getSelectedFile().getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveAsFile() {
        fileChooser.setSelectedFile(file);
        int status = fileChooser.showSaveDialog(mainPanel);
        if (status == JFileChooser.APPROVE_OPTION) {
        }
    }

    public void saveFile() {
        //new file
        if (file.lastModified()==0) {
          saveAsFile();
        } else
        //old file
        {
            doSave();
        }
    }

    public static void main(String[] args) {
        FRAME = new JFrame("Unsaved Document");
        FRAME.setContentPane(new Notepad().mainPanel);
        FRAME.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        FRAME.pack();
        FRAME.setVisible(true);
    }
}
