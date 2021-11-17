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














 
