import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class FileOutput {
    private String fileName;

    public FileOutput() {
        try {
            File jarDir = new File(FileOutput.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getParentFile();
            fileName = new File(jarDir, "Text.txt").getAbsolutePath();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Failed to locate Text.txt: " + e.getMessage());
            fileName = "Text.txt"; // fallback
        }
    }

    public void FileWriting(String tasks) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))){
            writer.write(tasks);
            writer.newLine();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error in writing to file: " + e.getMessage());
        }
    }

    public ArrayList<String> readtasks(){
        ArrayList<String> tasks = new ArrayList<>();
        try(BufferedReader read = new BufferedReader(new FileReader(fileName));){
            String lines;
            while((lines = read.readLine()) != null){
                tasks.add(lines);
            }
        } catch (Exception e){JOptionPane.showMessageDialog(null, "Error in reading file" + e.getMessage());}
        return tasks;
    }

    public void writeAllTasks(DefaultListModel<String> listModel){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for(int i = 0; i < listModel.size(); i++){
                writer.write(listModel.getElementAt(i));
                writer.newLine();
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error writing tasks: " + e.getMessage());
        }
    }
}
