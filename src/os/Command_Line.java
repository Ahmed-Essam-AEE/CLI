package command_line;
import java.util.Scanner;
public class Command_Line {

    public static void main(String[] args) {
        Parser parser = new Parser();
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
            if(parser.parse(userInput) ) {
                command = parser.getCommandName();
                Args = parser.getArgs(); 
                terminal.chooseCommandAction(command ,Args);
            } 
        }
        System.out.println("exit.");
    }
    
}
