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


    public Notepad() {
        mOpen.addActionListener(ae -> openFile());
        mSave.addActionListener(ae -> saveFile());
    }

    private void openFile() {
        int status = fileChooser.showOpenDialog(mainPanel);
        if (status == JFileChooser.APPROVE_OPTION) {
            try {
                FileReader fileReader = new FileReader(fileChooser.getSelectedFile());
                FRAME.setTitle(fileChooser.getSelectedFile().getName());
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

    public void saveFile() {
        fileChooser.setSelectedFile(new File("Document1.txt"));
        int status = fileChooser.showSaveDialog(mainPanel);
        if (status == JFileChooser.APPROVE_OPTION) {
            try (BufferedWriter fileOut = new BufferedWriter(new FileWriter(fileChooser.getSelectedFile()))) {
                textArea.write(fileOut);
                FRAME.setTitle(fileChooser.getSelectedFile().getName());
            } catch (IOException e) {
                e.printStackTrace();
            }
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
