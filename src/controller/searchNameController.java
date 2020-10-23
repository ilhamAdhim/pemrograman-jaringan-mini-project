/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

/**
 *
 * @author Ilham A
 */
import views.searchName;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PushbackReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

public class searchNameController {
    searchName view;
    public searchNameController(searchName view) {
        this.view = view;
    }

    public searchNameController() {
    }
    
    List<String> nameList;
    File DESTINATION_FILE;
    
    public void getNameList(){
        JFileChooser loadFile = view.getLoadFile();
        if(JFileChooser.OPEN_DIALOG == loadFile.showOpenDialog(view)){
            try {
                DESTINATION_FILE = loadFile.getSelectedFile();
                Scanner sc = new Scanner(DESTINATION_FILE);
                nameList = new ArrayList<>();
                while (sc.hasNext()) {
                    nameList.add(sc.nextLine());
                }
                getCurrentReadingFileName(DESTINATION_FILE);
            }catch (FileNotFoundException ex) {
                Logger.getLogger(editFileController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(editFileController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    public void getNameDetail(String name){
        int rank = nameList.indexOf(name) + 1;
        // Now, check if this line contains our keyword. If it does, print the line
        if(nameList.contains(name)) { // check if line contain that word then prints the line
             JOptionPane.showMessageDialog(view, name + " is on rank " + rank, 
                 "Information", JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(view, name + " is not popular", 
                 "Information", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    public void getCurrentReadingFileName(File file){
        String fileName = file.getName();
        //Set file name information
        view.getjLabel1().setText("Reading " + fileName);
    }
    
    public boolean hasAnyFile(){
        if(DESTINATION_FILE == null){
            JOptionPane.showMessageDialog(view, "Select a file first", 
                 "Information", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        return true;
    }
    
}
