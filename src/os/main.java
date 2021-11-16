package command_line;
import java.util.Scanner;
public class Command_Line {

    public static void main(String[] args) {
        Parser parser = new Parser();
        Terminal terminal = new Terminal();  
        String command;
        String[] Args;
        String currentPath; 
        String userInput;
        boolean keep = true ; 
        Scanner input = new Scanner(System.in);
        System.out.print("input the path -> ");
	currentPath = input.nextLine();
        while (keep){
            System.out.print(">");
	    userInput = input.nextLine();
            if (userInput.equals("exit")){
                keep = false ; 
                break ; 
            }
            while( parser.parse(userInput) ) {
                command = parser.getCommandName();
                Args = parser.getArgs(); 
                 switch(command){
                        case "echo" :
                            //
                            break ; 
                         case "pwd" :
                            //
                            break ; 
                         case "cd" :
                            //
                            break ; 
                         case "1s" :
                            //
                            break ;
                         case "1s -r" :
                            //
                            break ; 
                         case "mkdir" :
                            //
                            break ; 
                         case "rmdir" :
                            //
                            break ; 
                         case "touch" :
                            //
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
                            //
                            break ; 
                        default :
                            break ; 
                }
            }
           
        }
    }
    
}
