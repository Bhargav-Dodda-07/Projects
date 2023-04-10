import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor implements ActionListener {
    //declaring properties of text editor
    JFrame frame;
    JMenuBar menuBar;

    JMenu file, edit;

    //file Menu items
    JMenuItem newFile, openFile, saveFile;
    //edit menu items
    JMenuItem cut, copy, paste, selectAll,close;

    JTextArea textArea;
    TextEditor(){
        frame = new JFrame();


        // initialize menu bar;
        menuBar = new JMenuBar();

        //Initialize text Area

        textArea = new JTextArea();

        //Initialize menus

        file = new JMenu("File");
        edit = new JMenu("Edit");

        // initialize  file menu items;
        newFile = new JMenuItem("New Window");
        openFile = new JMenuItem("Open File");
        saveFile = new JMenuItem("Save File");

        //Add action Listeners to file menu items
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);

        //Add menu items to file
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);

        //initialize edit items

        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("paste");
        selectAll = new JMenuItem("Select All");
        close = new JMenuItem("Close");

        // Add action listener to edit menu items;
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        saveFile.addActionListener(this);
        close.addActionListener(this);

        // Add edit items to edit menu
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);

        // Add menus to menu bar
        menuBar.add(file);
        menuBar.add(edit);

        //set menu bar;
        frame.setJMenuBar(menuBar);
        // create content pane
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5,5,5,5));
        panel.setLayout(new BorderLayout(0,0));
        // add text area to panel
        panel.add(textArea,BorderLayout.CENTER);
        //create Scroll pane
        JScrollPane scrollPane = new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        //add scroll pane to panel
        panel.add(scrollPane);
        // add pane to frame
        frame.add(panel);
        //set dimensions of frame
        frame.setBounds(0,0,400, 400);
        frame.setTitle("Text Editor");
        frame.setVisible(true);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }
    @Override
    public void actionPerformed(ActionEvent actionEvent){
        if(actionEvent.getSource() == cut){
            //perform cut operation;
            textArea.cut();
        }
        if(actionEvent.getSource() == copy){
            //perform copy operation;
            textArea.copy();
        }
        if(actionEvent.getSource() == paste){
            //perform paste operation;
            textArea.paste();
        }
        if(actionEvent.getSource() == selectAll){
            //perform select All operation;
            textArea.selectAll();
        }
        if(actionEvent.getSource() == close){
            System.exit(0);
        }
        if(actionEvent.getSource() == openFile){
            //open a file chooser
            JFileChooser fileChooser = new JFileChooser("C:");
            int chooseOption = fileChooser.showOpenDialog(null);
            // if we have clicked on open button
            if(chooseOption == JFileChooser.APPROVE_OPTION){
                //getting the selected file
                File file = fileChooser.getSelectedFile();
                //get the path of selected file
                String filePath = file.getPath();
                try {
                    //Initialise the file
                    FileReader fileReader = new FileReader(filePath);
                    // initialise buffered reader
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    String intermediate = "", output = "";
                    // read contents of file line by line;
                    while ((intermediate = bufferedReader.readLine()) != null){
                        output += intermediate+"\n";
                    }
                    // set the output String to text area
                    textArea.setText(output);
                }
                catch (IOException fileNotFoundException){
                    fileNotFoundException.printStackTrace();
                }
            }
        }
        if(actionEvent.getSource() == saveFile){
            // initialize file picker
            JFileChooser fileChooser = new JFileChooser("c:");
            //get choose option from file chooser
            int chooseOption = fileChooser.showSaveDialog(null);
            // check if we clicked on save button
            if(chooseOption == JFileChooser.APPROVE_OPTION){
                // create a new file with chosen directory path and file name
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath()+ ".txt");
                try {
                    // initialize file writer
                    FileWriter fileWriter = new FileWriter(file);
                    //initialize Buffered writer
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    // write contents of text area to file
                    textArea.write(bufferedWriter);
                    bufferedWriter.close();
                }
                catch (IOException ioException){
                    ioException.printStackTrace();
                }
            }
        }
        if(actionEvent.getSource() == newFile){
            TextEditor newTextEditor = new TextEditor();
        }
    }
    public static void main(String[] args) {
        TextEditor textEditor = new TextEditor();

    }
}