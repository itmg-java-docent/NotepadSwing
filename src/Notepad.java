import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Notepad {
    private JPanel mainPanel;
    private JTextArea textArea;
    private JMenuBar menuBar;
    private JPanel panel;
    private JMenu menu;
    private JMenuItem mOpen;
    private JMenuItem mSave;
    private JMenuItem mSaveAs;

    public Notepad() {
        mOpen.addActionListener(ae -> openFile());
    }

    private void openFile() {
        JFileChooser fileChooser = new JFileChooser();
        int status = fileChooser.showOpenDialog(mainPanel);
        if (status == JFileChooser.APPROVE_OPTION) {
            try {
                FileReader fileReader = new FileReader(fileChooser.getSelectedFile());
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                String line = bufferedReader.readLine();
                while (line != null) {
                    textArea.append(line);
                    line = bufferedReader.readLine();
                }

            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("Notepad");
        frame.setContentPane(new Notepad().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
