package os;

import java.io.File;
import java.io.IOException;
import java.nio.file.NoSuchFileException;

public class Terminal {
    Parser parser;
    //Implement each command in a method, for example:
    public String pwd(){}
    public void cd(String[] args){}
    public static File currentDirectory;

    public void rm(String fileName) throws IOException, NoSuchFileException {
     
    }

    //This method will choose the suitable command method to be called
    public void chooseCommandAction(){}
    public static void main(String[] args){}

}
