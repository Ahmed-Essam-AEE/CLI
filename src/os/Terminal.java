package os;

import java.io.File;
import java.io.IOException;
import java.nio.file.NoSuchFileException;

public class Terminal {
    //class to organize process print
   private class consolePrint{
        private String packet = "";
        private int limitOfLine = 8;
        private int newLineCount = 0;
        private PrintStream outputStream = System.out;
        public void setPrintStream(PrintStream p){
            if(outputStream != System.out){
                outputStream.close();
            }
            outputStream = p;
        }
        public void print(String s){
            if(outputStream == System.out) {
                packet += s;
                if (newLineCount < limitOfLine)
                    print();
                
            }
            else{
                outputStream.print(s);
            }
        }
        public  void print(){
            int i;
            for(i = 0;i < packet.length() && newLineCount < limitOfLine; i++){
                if(packet.charAt(i) == '\n')
                    newLineCount++;
                outputStream.print(packet.charAt(i));
            }
            packet = packet.substring(i);
            if (newLineCount == limitOfLine)
                outputStream.print("...");
        }
        public  void println(String s){
            print(s + System.getProperty("line.separator"));
           
        }
        
        public boolean isEmpty(){
            return packet.isEmpty();
        }
        public void end(){
            newLineCount = 0;
            packet = "";
        }
         
      
    }
    private File currentDirectory; //stores absolute path of current directory
    private consolePrint printManager = new consolePrint();
    private Parser parser;
    
     //cds into default working directory
    public Terminal(){
        currentDirectory = new File(System.getProperty("user.home"));

        parser = new Parser();
        try {
            
            parser.initializeCommands("C:\\Users\\LENOVO\\Documents\\NetBeansProjects\\Terminal\\build.xml");
        }
        catch(IOException e){
            printManager.println("IO error reading command list: " + e);
            
        }
    }
     public void console(){//this function will take input  and check if it empty or no ,andthen pass it to procsscommand fuction
        Scanner scan = new Scanner(System.in);
        while (true){
            printManager.setPrintStream(System.out);//take the input to stream
            if(printManager.isEmpty())
                printManager.print(currentDirectory.getAbsolutePath() + ">");
            try {
                processCommand(scan.nextLine());
            }
            catch(Exception e){
                printManager.println(e.getClass().getName() + ": " + e.getMessage());
            }
        }
    }
    public static void main(String[] args) {
         Terminal mainTerminal = new Terminal();
          
        mainTerminal.console();
    }
    
     public void processCommand(String input) throws Exception{
        if(input.trim().isEmpty()){//trim is used to remove space from start and end of string 
            printManager.end();
            return;
        }
        
        String[] commands = input.trim().split("\\|");//ex on trim :"   hey    man i am here       " trim function will produce "hey man i am here"
        for (int i = 0; i < commands.length; i++){
            printManager.setPrintStream(System.out);
            String command = commands[i].trim();
            
             //ex: echo hellow > file.text
             //so,it will not do anything but print hellow in file
            if (command.contains(">")){//if file does not exist will be created   
                String filePath = command.substring(command.indexOf(">") + 1).trim();//get path from after > to end , to get path of ile
                File file = getAbsolutepath(filePath);
                command = command.substring(0, command.indexOf(">")).trim();//move from index 0 to > to get the command
                printManager.setPrintStream(new PrintStream(new FileOutputStream(file, false)));
            }

            if (!parser.parse(command)){
                throw new Exception("Error: Command not  recognized.");
            }
            else{
                
                switch(parser.getCmd()){
                     //change the current directory to anthor path
                    case "cd":
                        if (parser.getArgsCount() == 0)
                            cd();
                        else cd(parser.getArgs()[0]);
                        break;
                     //list of file in ordered
                    case "ls":
                        if (parser.getArgsCount() == 0)
                            ls();
                         
                        break;
                        
                    //get the directry path
                    case "pwd":
                        pwd();
                        break;
                        
                    //list of file in reverse order
                    case "ls_r":
                        ls_r();
                        break;
                        
                     //echo function
                    case "echo":
                        echo(parser.getArgs()[0]);
                        break;
                    //exit from terminal
                    case "exit":
                        exit();
                        break;
                }
            }
        }
    }
    //echo fuction is take string and print it
    
    public void echo(String Data){
     System.out.println("you entered:"+Data);
    
    }
       //given a relative path changed into absolute, if directory exists
       //examople: the absolute path is = home/downloads/sold/ex.text  ,,,the relative path is=sold/ex.text
    public File getAbsolutepath(String sourcePath){ //make file absolute 
        File f = new File(sourcePath);
        if(!f.isAbsolute()) { //check if the file is absolute or not ,,if the file is found in desktop or no
            f = new File(currentDirectory.getAbsolutePath(), sourcePath);//getAbsolutePath to get pathname completely
            //file(basedirectory as parent,actual file path as child)
        }
        return f.getAbsoluteFile();
        
    }
    //changes the current directory to the given one
    public void cd(String sourcePath)throws NoSuchFileException,IOException{
        if(sourcePath.equals("..")){ //if we need to go the parent folder
            String parentfolderpath = currentDirectory.getParent();
            File f = new File(parentfolderpath);
            currentDirectory = f.getAbsoluteFile();
        }
        else{ //if we need to go particular path, tf the path is full or relative
            File f = getAbsolutepath(sourcePath); 
            if(!f.exists()){//if path not found
                throw new NoSuchFileException(f.getAbsolutePath(),null,"does not exist");
            }
            if(f.isFile()){//if path founded
                throw new IOException("Can't cd into file");
            }
            else currentDirectory = f.getAbsoluteFile();
        }
    }
   //changes into default directory
    public void cd(){//go to home directory

        currentDirectory = new File(System.getProperty("user.home"));
    }
     //prints working directory ,print current path
    public void pwd(){
        printManager.println(currentDirectory.getAbsolutePath());
    }

  
 
    //lists all files in current working directory
    public void ls(){
        String[] arr =  currentDirectory.list();
        int n = arr.length;
        for(int i = 0;i < n;i++){
            printManager.println(arr[i]);
        }
    }
    //list all files in current directory in reversed
     public void ls_r(){
       String[] arr =  currentDirectory.list();
        int n = arr.length;
        for(int i = n;i >= 0;i++){
            printManager.println(arr[i]);
        }
    }

     
    //terminates program
    public void exit(){System.exit(0);}
    
}
