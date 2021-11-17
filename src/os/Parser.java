package os;

import java.util.Arrays;



class Parser {
    String commandName;
    String[] Args;
    int ind1 , ind2;
    String command2 , fileNameG;

    public boolean parse(String input) {

        if (input.contains(">") || input.contains(">>"))
        {
            String[] inputSplit = input.split(" ") ;
            this.command2=inputSplit[0];
            int j=0;
            for( ; j<inputSplit.length ; j++)
            {
                if(inputSplit[j].equals(">"))
                {
                    ind1=j;
                    commandName=inputSplit[j];
                    fileNameG=inputSplit[ind1+1];
                    break;
                }
                else  if(inputSplit[j].equals(">>"))
                {
                    ind2=j;
                    commandName=inputSplit[j];
                    fileNameG=inputSplit[ind2+1];
                    break;
                }
            }
            if (inputSplit[j+1].equals(""))
            {
                System.out.println("ERROR: " + commandName + " take 1 argument!");
                return false;
            }

            String[] newArray = new String[inputSplit.length-3];
            for (int i= 1 ; i< j ;i++){
                newArray[i-1] = inputSplit[i] ;
            }
            Args = Arrays.copyOf(newArray,newArray.length);
            if ( ( command2.equals("pwd") || command2.equals("help")) && Args.length != 0){
                System.out.println("ERROR: " + command2 + " not take any argumnent!");
                return false;
            }
            if ( ( command2.equals("mkdir") ||command2.equals("cat") ) && Args.length == 0 ){
                System.out.println("ERROR: " + command2 + " take at least 1 argument!");
                return false;
            }
            if ( ( command2.equals("echo") || command2.equals("rmdir") ||command2.equals("touch")
                    ||command2.equals("rm") ) && Args.length != 1 ){
                System.out.println("ERROR: " + command2 + " take one argument!");
                return false;
            }


        }else{
        String[] inputSplit = input.split(" ") ;
        commandName = inputSplit[0];
        String[] newArray = new String[inputSplit.length-1];
        for (int i= 1 ; i< inputSplit.length ;i++){
            newArray[i-1] = inputSplit[i] ;
        }
        Args = Arrays.copyOf(newArray,newArray.length);
        ////////////////////////////////////
        if ( ( commandName.equals("pwd") || commandName.equals("help")) && Args.length != 0){
            System.out.println("ERROR: " + commandName + " not take any argumnent!");
            return false;
        }
        /////////////////////////////////////
        if ( ( commandName.equals("mkdir") ||commandName.equals("cat") ) && Args.length == 0 ){
            System.out.println("ERROR: " + commandName + " take at least 1 argument!");
            return false;
        }
        /////////////////////////////////////
        if ( ( commandName.equals("echo") || commandName.equals("rmdir") ||commandName.equals("touch")
                ||commandName.equals("rm") ) && Args.length != 1 ){
            System.out.println("ERROR: " + commandName + " take one argument!");
            return false;
        }
        if (commandName.equals("cp") || commandName.equals("cp -r") && Args.length<1)
        {
            System.out.println("ERROR: "+ commandName + " takes 2 argument" );
        }
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
