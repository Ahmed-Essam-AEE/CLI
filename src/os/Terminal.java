package os;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Terminal {
    Parser parser;
    File currentPath = new File(System.getProperty("user.dir"));
    //Implement each command in a method, for example:
    //public String pwd(){}
    //public void cd(String[] args){}

    //private File founddir;//this file will be used in this assignment
    //echo fuction is take string and print it

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



    //lists all files in current working directory
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






    public File makeAbsolute(String sourcePath){
        File f = new File(sourcePath);
        if(!f.isAbsolute()) {
            f = new File(currentPath, sourcePath);
        }
        return f.getAbsoluteFile();
    }
    public void cp(String fileName1 , String fileName2) throws IOException {
        File file1 ;
        Path fileName;
        if (fileName1.charAt(1)==':')   file1=new File(fileName1);
        else file1 =new File(currentPath , fileName1);
        if (fileName2.charAt(1)==':')    fileName = Path.of(fileName2);
        //file2=new File(fileName2);
        else fileName= Path.of(currentPath+ "\\" +fileName2);
            //file2 =new File(currentPath , fileName2);

        StringBuilder text = new StringBuilder();
        if (file1.exists() && file1.canRead()){
            Scanner read = new Scanner(file1);
            while (read.hasNextLine())  text.append(read.nextLine()).append("\n");
            Files.writeString(fileName,text.toString());

        }
    }
    public void rm(String fileName) throws IOException, NoSuchFileException {
        File file = new File(currentPath,fileName);

        if(!file.exists())
            throw new NoSuchFileException(fileName,null,"No such file.");
        else if(file.isDirectory())
            throw new IOException("Cannot delete directory.");
        else if (!file.delete())
            throw  new IOException("Cannot delete file.");
    }

    public void cat(String fileName) throws IOException {
        File file = new File(currentPath , fileName);
        Scanner fileReader = new Scanner(file);
        StringBuilder text= new StringBuilder();
        if (file.exists() && file.isFile())
        {
            while (fileReader.hasNextLine())
            {
                text.append(fileReader.nextLine());
            }
            if (text.length()>0){System.out.println(text+", in " + fileName);}
        }
        else
            {
                throw new IOException("No such file.");
            }
    }
    public void tranfer2(String input , String fileName)  {
        File f = new File(currentPath , fileName);
        if (f.exists())
        {
            try{
                FileWriter fileOutput = new FileWriter(f);
                fileOutput.append("\n").append(input);
                fileOutput.close();
            } catch (Exception e)
            {
                e.printStackTrace();
            }

        }else{
            //System.out.println("Error: "+fileName+" not found");
            try{
                f.createNewFile();
                FileWriter fileOutput = new FileWriter(f);
                fileOutput.write("\n" + input);
                fileOutput.close();
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }

    }
    public void tranfer(String input , String fileName) throws IOException {
        File file = new File(currentPath , fileName);
        try{
                file.createNewFile();
                FileWriter fileOutput = new FileWriter(file);
                fileOutput.write(input);
                fileOutput.close();

        }catch (Exception e){e.printStackTrace();}


    }
    public void exit() {
        System.exit(0);
    }


    //This method will choose the suitable command method to be called
    public void chooseCommandAction(String command, String [] args) throws IOException {
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
                cp(args[0] , args[1]);
                break ;
            case "cp -r" :
                //
                break ;
            case "rm" :
                rm(args[0]);
                break ;
            case "cat" :
                cat(args[0]);
                if (args.length>1){
                    cat(args[1]);
                }
                break ;
            case ">" :
                String s="";

                if (parser.command2.equals("echo")) s=echo(args);
                else if (parser.command2.equals("pwd")) s=pwd();
                else if (parser.command2.equals("ls")) s=ls(args);
                else if (parser.command2.equals("ls -r")) s= ls(args);
                tranfer( s , parser.fileNameG);

                break ;
            case ">>" :
                String s2="";
                if (parser.command2.equals("echo")) s2=echo(args);
                else if (parser.command2.equals("pwd")) s2=pwd();
                else if (parser.command2.equals("ls")) s2=ls(args);
                else if (parser.command2.equals("ls -r")) s2= ls(args);
                tranfer2( s2 , parser.fileNameG);

                break ;
            case "help" :
                help();
                break ;
            case "exit" :
                exit();
                break ;
            default :
                break ;
        }
    }
    /*public static void main(String[] args) throws IOException {
        Scanner read = new Scanner(System.in);
        Terminal terminal = new Terminal();
        boolean keep = true ;
        String userInput="";
        while (!userInput.equals("exit")){
            System.out.print(">");
            userInput = read.nextLine();
            if (terminal.parser.parse(userInput)){
                terminal.chooseCommandAction(terminal.parser.commandName , terminal.parser.Args);
            }
        }
    }*/
    public static void main(String[] args) throws IOException {
        //Parser parser = new Parser();
        Terminal terminal = new Terminal();
        String command;
        String[] Args;
        String userInput;
        boolean keep = true ;
        Scanner input = new Scanner(System.in);
        while (keep){
            System.out.print(">");
            userInput = input.nextLine();
            if (userInput.equals("exit")){
                keep = false ;
                break ;
            }
            if(terminal.parser.parse(userInput) ) {
                command = terminal.parser.getCommandName();
                Args = terminal.parser.getArgs();
                terminal.chooseCommandAction(command ,Args);
            }
        }
        //System.out.println("exit.");
    }

}
