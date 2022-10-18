import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;

import java.io.EOFException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.*;

class ToDoListFinal {
    public static void main(String args[]) {

        File fileList = new File("D:\\java\\ToDoListFinal\\fileList.txt");
        String savedList = "";
        try {
            Scanner myReader = new Scanner(fileList);
            while (myReader.hasNextLine()) {
                savedList = myReader.nextLine();
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: reading from file.");
            e.printStackTrace();
        }

        ToDoList toDoList = new ToDoList(savedList);

        JFrame frame = new JFrame("ToDoListGUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(550, 500);

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                try {
                    FileWriter myWriter = new FileWriter("fileList.txt");
                    myWriter.write(toDoList.ToString());
                    myWriter.close();
                } catch (IOException e) {
                    System.out.println("Error: writing in flie.");
                    e.printStackTrace();
                }
            }
        });

        JPanel panel = new JPanel();
        JLabel label = new JLabel("Enter Text");
        JTextField tf = new JTextField(20);
        JButton addButton = new JButton("Add");
        JButton deleteButton = new JButton("Delete");
        JButton markButton = new JButton("Mark");

        DefaultListModel<String> list = new DefaultListModel<>();
        for (int i = 0; i < toDoList.GetSize(); i++) {
            list.addElement(toDoList.GetItemName(i));
        }
        JTextArea ta = new JTextArea();
        ta.setRows(15);

        addButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (tf.getText().equals(""))
                    return;
                list.addElement(tf.getText());
                toDoList.Add(new Item(tf.getText()));
                // toDoList.Display();
                tf.setText("");
            }
        });

        JList<String> lista = new JList<String>(list);
        lista.setBounds(20, 0, 400, 360);
        lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        lista.setCellRenderer(new DefaultListCellRenderer() {

            @Override
            public Component getListCellRendererComponent(JList list, Object value, int index,
                    boolean isSelected, boolean cellHasFocus) {
                Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof String) {
                    String nextString = (String) value;
                    setText(nextString);
                    if (toDoList.GetItemMark(index) == true) {
                        setBackground(Color.GREEN);
                    } else {
                        setBackground(Color.WHITE);
                    }
                    if (isSelected) {
                        setBackground(getBackground().darker());
                    }
                } else {
                    setText("wusdat?");
                }
                return c;
            }

        });

        deleteButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (list.getSize() == 0 || lista.isSelectionEmpty())
                    return;
                toDoList.Delete(lista.getSelectedIndex());
                list.removeElementAt(lista.getSelectedIndex());
                toDoList.Display();
            }
        });

        markButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (list.getSize() == 0 || lista.isSelectionEmpty())
                    return;
                if (toDoList.GetItemMark(lista.getSelectedIndex()) == true)
                    toDoList.Unmark(lista.getSelectedIndex());
                else
                    toDoList.Mark(lista.getSelectedIndex());
                lista.updateUI();
                // toDoList.Display();
            }
        });

        panel.add(label);
        panel.add(tf);
        panel.add(addButton);
        panel.add(deleteButton);
        panel.add(markButton);

        frame.add(lista);
        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        frame.getContentPane().add(BorderLayout.CENTER, ta);
        frame.setVisible(true);

    }
}