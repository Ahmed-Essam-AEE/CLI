package os;

public class Parser {
    String commandName;
    String[] args;

    //This method will divide the input into commandName and args
    //where "input" is the string command entered by the user
    public boolean parse(String input){
      
    }
    public String getCommandName(){
      
    }
    public String[] getArgs(){
      
    }
}

//////////////////////////////////////////////////////////
package command_line;
class Parser {
String commandName;
String[] allArgs;
public static String[] allCommands = {"echo","pwd","cd","1s","1s -r","mkdir",
			              "rmdir","touch","rm","cp","cp -r","rm",
			               "cat","clear",">",">>","help","exit" };

public boolean parse(String input) {
    String[] inputSplit = input.split(" ") ;
    commandName = inputSplit[0];
    for (int i = 1;i <inputSplit.length;i++){
             allArgs[i-1] = inputSplit[i];
       // System.out.println(inputSplit[i]);
        
    }
    /*for (int i = 0;i<Args.length;i++){
        System.out.println(Args[i]);
    }*/
    if (commandName == "exit"){
        return false ; 
    }
    ////////////////////////////////////  
    if ( ( commandName =="pwd" || commandName =="ls" ||commandName =="ls -r" 
         ||commandName =="help") && inputSplit.length != 0){
        System.out.println("ERROR: " + commandName + " not take any argumnent!");
	return false;
    }
    /////////////////////////////////////
    if ( ( commandName =="mkdir" || commandName =="cp" ||commandName =="cp -r" 
         ||commandName =="cat" ) && inputSplit.length == 0 ){
        System.out.println("ERROR: " + commandName + " take at least 1 argument!");
	return false;
    }
    /////////////////////////////////////
     if ( ( commandName =="echo" || commandName =="rmdir" ||commandName =="touch" 
         ||commandName =="rm" ) && inputSplit.length != 1 ){
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
  return allArgs; 
}
 
}


