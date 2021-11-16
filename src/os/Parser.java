//////////////////////////////////////////////////     
package command_line;

import java.util.Arrays;

class Parser {
String commandName;
String[] Args;
public static String[] allCommands = {"echo","pwd","cd","1s","1s -r","mkdir",
			              "rmdir","touch","rm","cp","cp -r","rm",
			               "cat","clear",">",">>","help","exit" };

public boolean parse(String input) {
     String[] inputSplit = input.split(" ") ;
    commandName = inputSplit[0]; 
    String[] newArray = new String[inputSplit.length-1]; 
    for (int i= 1 ; i< inputSplit.length ;i++){
        newArray[i-1] = inputSplit[i] ; 
    }
   Args = Arrays.copyOf(newArray,newArray.length);
   for (int i = 0 ;i <Args.length;i++){
       System.out.println(Args[i]);
   }
    if (commandName == "exit"){
        return false ; 
    }
    ////////////////////////////////////  
    if ( ( commandName =="pwd" || commandName =="ls" ||commandName =="ls -r" 
         ||commandName =="help") && Args.length != 0){
        System.out.println("ERROR: " + commandName + " not take any argumnent!");
	return false;
    }
    /////////////////////////////////////
    if ( ( commandName =="mkdir" || commandName =="cp" ||commandName =="cp -r" 
         ||commandName =="cat" ) && Args.length == 0 ){
        System.out.println("ERROR: " + commandName + " take at least 1 argument!");
	return false;
    }
    /////////////////////////////////////
     if ( ( commandName =="echo" || commandName =="rmdir" ||commandName =="touch" 
         ||commandName =="rm" ) && Args.length != 1 ){
        System.out.println("ERROR: " + commandName + " take one argument!");
	return false;
    }
    /////////////////////////////////////
    return true ; 
}
 public String getCommandName(){
  return commandName;
 }
 public String[] getArgs(){
  return Args; 
}
 
}

/////////////////////////////////////////////////////














/*
package os;

public class Parser {
     public class Command{
        private String command;
        private String helpText;
        private int parameterCount;

        public Command(String cmd, int c){
            command = cmd;
            parameterCount = c;
            helpText = "";
        }
        public Command(){}
        public int getParameterCount(){
            return parameterCount;
        }

        public void addHelp(String text){
            helpText += (helpText.isEmpty() ? "" : "\n") + text;
        }

        public void clearHelp(){
            helpText = "";
        }

        public String getHelp(){
            return helpText;
        }

        public String getCommand(){
            return command;
        }

        public String toString(){
            return command;
        }
    }

    private String cmd; //command taken from parse method
    private String[] args; //filled by cmd arguments from parse method
    private boolean isValidParse; // is true when a command is parsed correctly
    private ArrayList<Command> commands;

    /*
        Checks if path is a valid file path under the Windows path convention
     
   // public boolean isFilePath(String path){
       // return Pattern.matches("[a-zA-Z]:(/[a-zA-Z][^\\s/]*)///?", path);
    }

    public Parser(){
        commands = new ArrayList<>(); // init ArrayList
    }

    public int getArgsCount(){
        return args == null ? 0 : args.length;
    }

    public void initializeCommands(String commandListPath) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader(commandListPath));

        Pattern commentPattern = Pattern.compile("^(//).*"); // Regex that matches comments
        Pattern helpPattern = Pattern.compile("^(::).*"); // Regex that matches help text
        Pattern commandPattern = Pattern.compile("[a-zA-Z?]+\\s\\d"); // Regex that matches commands

        String line = br.readLine();
        while (line != null){ // will exit loop when eof is reached
            if (commentPattern.matcher(line).matches()){ } //ignore if comment
            else if (helpPattern.matcher(line).matches()){ // line is a help element
                if (!commands.isEmpty()){
                    commands.get(commands.size() - 1).addHelp(line.substring(2)); // add help text to last command
                }
            }
            else if (commandPattern.matcher(line).matches()){ // line is a valid command
                String[] lineElements = line.split("\\s");
                commands.add(new Command(lineElements[0], Integer.parseInt(lineElements[1])));
            }
            line = br.readLine();
        }

        br.close(); // close file
    }

    /*verifies input,
     //once verified, extracts input and fills parameters accordingly 
    public boolean parse(String input) {
        cmd = null;
        args = null;
        isValidParse = false;
        Matcher matcher = Pattern.compile("\"([^\"]*)\"|(\\S+)").matcher(input);
        ArrayList<String> argsList = new ArrayList<>();
        if (!matcher.find()){
            return false;
        }
        cmd = matcher.group();
        while (matcher.find()){
            if (matcher.group(1) != null){
                argsList.add(matcher.group(1));
            }
            else{
                argsList.add(matcher.group(2));
            }
        }
        args = new String[argsList.size()];
        argsList.toArray(args);

       Command c = getCommand(cmd, args.length);
        if (c != null){
            isValidParse = true;
            return true;
        }
        cmd = null;
        args = null;
        return false;
    }
    public Command getCommand(String c , int p){
        for (int i = 0; i < commands.size(); i++)
            if (commands.get(i).getCommand().equals(c) && p == commands.get(i).getParameterCount()){
                return commands.get(i);
            } //TODO uhh do logic to return multiple commands with diff parameter count
        return null;
    }

    public Command getCommand(String c){
        for (int i = 0; i < commands.size(); i++)
            if (commands.get(i).getCommand().equals(c)){
                return commands.get(i);
            } //TODO uhh do logic to return multiple commands with diff parameter count
        return null;
    }

    public ArrayList<Command> getCommands() {
        return commands;
    }

    /*
            Getter for cmd. Returns null if no valid parse is made.
         
    public String getCmd() {
        return isValidParse ? cmd : null;
    }

    /*
        Getter for args. Returns null if no valid parse is made.
     
    public String[] getArgs() {
        return isValidParse ? args: null;
    }

    /*
        Getter for isValidParse.
     
    public boolean isValidParse() {
        return isValidParse;
    }
}*/
     
     



