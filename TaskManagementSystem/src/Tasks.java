import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;

public class Tasks extends JFrame {
    FileOutput obj1 = new FileOutput();
    JPanel panel1;
    JTextField textField1;
    JButton addTaskButton;
    JList<String> list1 = new JList();;
    JButton completeTaskButton;
    JButton deleteTaskButton;
    DefaultListModel<String> listModel = new DefaultListModel<>();

    public Tasks() throws IOException {
        ArrayList<String> tasksFromFile = obj1.readtasks();
        for(String task : tasksFromFile){
            listModel.addElement(task);
        }
        list1.setModel(listModel);
    }


    private void addTaskFromInput() {
        String task = textField1.getText().trim();
        if (!task.isEmpty()) {
            listModel.addElement(task);
            obj1.FileWriting(task);
            textField1.setText("");
        }
    }


    public JComponent display(){
        panel1 = new JPanel();
        panel1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        final JLabel label1 = new JLabel();
        label1.setText("Enter a task");
        panel1.add(label1);
        textField1 = new JTextField();
        textField1.setColumns(20);
        textField1.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {  // Check if Enter key is pressed
                    addTaskFromInput();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        panel1.add(textField1);
        addTaskButton = new JButton();
        addTaskButton.setFocusable(false);
        addTaskButton.setText("Add Task");
        addTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addTaskFromInput();
            }
        });
        panel1.add(addTaskButton);
        list1.setPreferredSize(new Dimension(400, 300));
        list1.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                int selectedIndex = list1.getSelectedIndex();
                if (e.getKeyCode() == KeyEvent.VK_DELETE){
                    if(selectedIndex != -1){
                        listModel.removeElementAt(selectedIndex);
                        obj1.writeAllTasks(listModel);
                        list1.setModel(listModel);
                    }
                } else if(e.getKeyCode() == KeyEvent.VK_C){
                    String originalText = listModel.getElementAt(selectedIndex);
                    String updatedText = originalText + " (Completed)";
                    listModel.set(selectedIndex, updatedText);
                    obj1.writeAllTasks(listModel);
                    list1.setModel(listModel);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        panel1.add(list1);
        completeTaskButton = new JButton();
        completeTaskButton.setFocusable(false);
        completeTaskButton.setText("Complete Task");
        completeTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = list1.getSelectedIndex();
                if (selectedIndex != -1) {
                    String originalText = listModel.getElementAt(selectedIndex);
                    String updatedText = originalText + " (Completed)";
                    listModel.set(selectedIndex, updatedText);
                    obj1.writeAllTasks(listModel);
                    list1.setModel(listModel);
                }
            }
        });
        panel1.add(completeTaskButton);
        deleteTaskButton = new JButton();
        deleteTaskButton.setFocusable(false);
        deleteTaskButton.setText("Delete Task");
        deleteTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listModel.removeElement(list1.getSelectedValue());
                obj1.writeAllTasks(listModel);
            }
        });
        deleteTaskButton.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_2){
                    listModel.removeElement(list1.getSelectedValue());
                    list1.setModel(listModel);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        panel1.add(deleteTaskButton);
        return panel1;

    }

}