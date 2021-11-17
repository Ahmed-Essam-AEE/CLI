package command_line;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
public class Terminal {
 Parser parser;
///////////////////////////////////////////
 public void chooseCommandAction(String command, String [] args){
       switch(command){
        case "echo" :
            System.out.println(echo(args));
            break ; 
        case "pwd" :
            System.out.println(pwd());
            break ; 
        case "cd" :
            cd(args);
            break ; 
        case "ls" :
            System.out.println(ls(args));
            break ;
        case "ls -r" :
            System.out.println(ls(args));
            break ; 
        case "mkdir" :
            mkdir(args);
            break ; 
        case "rmdir" :
            rmdir(args);
            break ; 
        case "touch" :
            touch(args);
            break ;
        case "cp" :
            //
            break ;
        case "cp -r" :
            //
            break ; 
        case "rm" :
            //
            break ;
        case "cat" :
            //
            break ; 
        case ">" :
            //
            break ;
        case ">>" :
            //
            break ; 
        case "help" :
            help();
            break ; 
        default :
            break ; 
    }
 }
//////////////////////////////////////////
public String echo(String [] args) {
   // return String.join(" ", args);
    return ("the user enter :"+value);
}
////////////////////////////////////////////
  private File founddir;
public String pwd() {
  //  return System.getProperty("user.dir");
    String current_directory;
         
        current_directory = System.getProperty("user.dir");//here we need curdir for project if we go any place this function will return path
        founddir= new File(current_directory);
        System.out.println("Current directory:" +current_directory); 
 
}
///////////////////////////////////////////
public void cd(String [] args){
    if(path=="..")
          {
            String pathOfUperFloder = founddir.getParent();
            founddir=new File(pathOfUperFloder).getParentFile();
            System.out.println(pathOfUperFloder);
          }
          else {
           File file1=new File(path);//to get full path we create new file to get from it
           founddir=file1.getAbsoluteFile();
              System.out.println(founddir.getAbsolutePath());
          }
  }
///////////////////////////////////////////
public String ls(String [] args) {
          String [] contents = founddir.list();
        
        for (int i = 0; i < contents.length; i++)
        {
            System.out.println(contents[i]);
        }
}
////////////////////////////////////////////
public void mkdir(String [] args) {
        for (int i = 0; i < args.length; i++) {
            File theDir = new File(args[i]);
            if (!theDir.exists()) {
                theDir.mkdirs();
            }
        }
    }
 ////////////////////////////////////////////
public void rmdir(String [] args) {
        String dir = args[0];
        try {
            if (dir.equals("*")) {
                File theDir = new File(System.getProperty("user.dir"));
                File[] tmp = theDir.listFiles();
                for (int i = 0; i < tmp.length; i++) {
                    File file = tmp[i];
                    if (!file.isFile()) {
                        if (file.listFiles().length == 0) {
                            file.delete();
                        }
                    }
                }
            } else {

                File theDir;
                if (dir.contains(":")) {
                    theDir = new File(dir);
                } else {
                    theDir = new File(System.getProperty("user.dir") + "\\" + dir);
                }
                if (theDir.listFiles().length == 0) {
                    theDir.delete();
                }

            }
        } catch (Exception e) {
            System.out.println(e);
        }
}
/////////////////////////////////////////////
 public void touch(String [] args) {
        File file;
        if (args[0].contains(":")) {
            file = new File(args[0]);
        } else {
            file = new File(System.getProperty("user.dir") + "\\" + args[0]);
        }

        try {
            if (!new File(args[0]).exists())
                file.createNewFile();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
/////////////////////////////////////////////
 public void help(){
        System.out.println("echo   -> takes 1 argument and prints it..");
        System.out.println("pwd    -> return an absolute (full) path.");
        System.out.println("cd     -> change the directory.");
        System.out.println("ls     -> lists the contents of the current directory sorted alphabetically");
        System.out.println("ls -r  -> lists the contents of the current directory in reverse order.");
        System.out.println("mkdir  -> make a new directory.");
        System.out.println("rmdir  -> delete a directory.");
        System.out.println("touch  -> create a file ");
        System.out.println("cp     -> copy files from the current directory to a different directory.");
        System.out.println("cp -r  -> copy files from the current directory to a different directory (empty or not).");
        System.out.println("rm     -> delete directories and the contents within them.");
        System.out.println("cat    -> Prints all contents in files.");
        System.out.println(">      -> redirects the output of the first command to be written to a file. I");
        System.out.println(">>     -> like > but appends to the file if it exists        ");
        System.out.println("help   -> display all command to help you.");
        System.out.println("exit   -> stop program.");
} 
}
