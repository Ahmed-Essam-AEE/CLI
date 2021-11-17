package command_line;
import java.io.File;
import java.io.IOException;
public class Terminal {
Parser parser;
File currentPath = new File(System.getProperty("user.dir"));
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
    return String.join(" ", args);
}
////////////////////////////////////////////
public String pwd() {
       return currentPath.getAbsolutePath();
}
/////////////////////////////////////////////
  public void cd(String [] args)
  {
      if (args.length == 0){
           String pathofUser;
           currentPath= new File(System.getProperty("user.home"));
           pathofUser=(System.getProperty("user.home"));
      }
      else if (args.length == 1 && args[0].equals("..")){
              currentPath = currentPath.getParentFile(); 
      }
      else {
            File file1=new File(args[0]);
            currentPath = file1.getAbsoluteFile();
      }
 }
///////////////////////////////////////////
public String ls(String [] args) {
        String [] contents = currentPath.list();
        StringBuilder text = new StringBuilder();
        if (args.length == 0){
               for (int i = 0; i < contents.length; i++)
               {
                   if (i==0){
                       text.append(contents[i]); 
                   }
                   else{
                       text.append("\n"+contents[i]);   
                   }
               }
        }
        else if (args.length == 1 && args[0].equals("-r")){
            for (int i = contents.length-1; i>=0; i--)
               {
                   if (i == contents.length-1){
                        text.append(contents[i]);
                   }
                   else {
                       text.append("\n"+contents[i]);
                   }
               }
        }
    return text.toString() ; 
}
////////////////////////////////////////////
public void mkdir(String [] args) {
        if (args.length == 1 && args[0].charAt(1)==':' ){
            File theDir = new File(args[0]);
            if (!theDir.exists()) {
                theDir.mkdirs();
            }
        }
        else {
            for (int i = 0; i < args.length; i++) {
                File theDir = new File(currentPath,args[i]);
                if (!theDir.exists()) {
                    theDir.mkdirs();
                }
            }
        }
    }
 ////////////////////////////////////////////
public void rmdir(String [] args) {
        String dir = args[0];
        try {
            if (dir.equals("*")) {
                File theDir = currentPath;
               // File theDir = new File(System.getProperty("user.dir"));
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
            file = new File(currentPath + "\\" + args[0]);
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
        System.out.println("rm     -> delete directories and the contents within them.");
        System.out.println("cat    -> Prints all contents in files.");
        System.out.println(">      -> redirects the output of the first command to be written to a file. I");
        System.out.println(">>     -> like > but appends to the file if it exists        ");
        System.out.println("help   -> display all command to help you.");
        System.out.println("exit   -> stop program.");
} 
}
