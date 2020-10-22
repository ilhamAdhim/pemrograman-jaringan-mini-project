package controller;


import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PushbackReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import views.editFile;


public class editFileController {
    editFile view;
    public editFileController(editFile view) {
        this.view = view;
    }

    public editFileController() {
    }
    
    public void save(){
        JFileChooser loadFile = view.getLoadFile();
        if(JFileChooser.APPROVE_OPTION == loadFile.showSaveDialog(view)){
            BufferedOutputStream writer = null;
            try {
                 String contents = view.getTxtArea().getText();
                 if (contents != null && !contents.isEmpty()) {
                     writer = new BufferedOutputStream(new FileOutputStream(loadFile.getSelectedFile()));
                     writer.write(contents.getBytes(), 0, contents.length());
                     JOptionPane.showMessageDialog(view, "File is updated", 
                             "Information", JOptionPane.INFORMATION_MESSAGE);
                 }else{
                     JOptionPane.showMessageDialog(view, "Text Editor is empty. Cannot Save the document.", 
                             "Warning", JOptionPane.WARNING_MESSAGE);
                     
                 }

             } catch (FileNotFoundException ex) {
                 Logger.getLogger(editFileController.class.getName()).log(Level.SEVERE, null, ex);
             } catch (IOException ex) {
                 Logger.getLogger(editFileController.class.getName()).log(Level.SEVERE, null, ex);
             } finally {
                 if (writer != null) {
                     try {
                         writer.flush();
                         writer.close();
                         view.getTxtArea().setText("");
                     } catch (IOException ex) {
                         Logger.getLogger(editFileController.class.getName()).log(Level.SEVERE, null, ex);
                     }
                 }
             }
        }
    }
    public void open(){
        view.getTxtArea().setText("");
        JFileChooser loadFile = view.getLoadFile();
        Document doc = view.getTxtArea().getDocument();
        if(JFileChooser.OPEN_DIALOG == loadFile.showOpenDialog(view)){
            PushbackReader reader=null;
            try {
                reader = new PushbackReader(new InputStreamReader(new FileInputStream(loadFile.getSelectedFile())));
                doc.insertString(0, "", null);
                int dec;
                char ascii;
                String word="";
                int countWord=1;
                int countLine=1;
                int countChar=0;
                String status="";
                while((dec=reader.read()) != -1){
                    ascii = (char) dec;
                    if((ascii == '\n' || ascii ==' ') && word.length()>1){
                        countWord++;
                        word="";
                    }
                    if(ascii == '\n'){
                        countLine++;
                    }
                   if((ascii != '\n' && ascii != ' ')){
                       word+=ascii;
                   }
                    countChar++;
                    doc.insertString(doc.getLength(), ""+ascii, null);
                }
                status+="\nAmount of names: "+countLine+"\nCharacters : "+countChar;
                JOptionPane.showMessageDialog(view, "File Loaded."+status, 
                        "Informasi", JOptionPane.INFORMATION_MESSAGE);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(editFileController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (BadLocationException ex) {
                Logger.getLogger(editFileController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(editFileController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
