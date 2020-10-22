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
import javax.swing.JOptionPane;

public class searchNameController {
    searchName view;
    public searchNameController(searchName view) {
        this.view = view;
    }

    public searchNameController() {
    }
    
    List<String> nameList;
    String DESTINATION_FILE = "C:/Users/Ilham A/Documents/Kuliah smt 5/Networking/week 7 Mini Project/nama.txt";    
    public void getNameList(){
        File file = new File(DESTINATION_FILE);
        try {
            Scanner reader = new Scanner(file);
            nameList = new ArrayList<>();
            while (reader.hasNext()) {
                nameList.add(reader.nextLine());
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(searchNameController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void getNameDetail(String name){
        getNameList();
        int rank = nameList.indexOf(name);
        
        // Now, check if this line contains our keyword. If it does, print the line
        if(nameList.contains(name)) { // check if line contain that word then prints the line
             JOptionPane.showMessageDialog(view, name + " is on rank " + rank, 
                 "Informasi", JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(view, name + " is not popular", 
                 "Informasi", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
}
