package os;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
public class Terminal {
        private File founddir;//this file will be used in this assignment
        //echo fuction is take string and print it
    
        public String echo(String value)
        {
           return ("the user enter :"+value);
        }
        //print current path
        public void pwd() {
        String current_directory;
         
        current_directory = System.getProperty("user.dir");//here we need curdir for project if we go any place this function will return path
        founddir= new File(current_directory);
        System.out.println("Current directory:" +current_directory); 
         
        
        }
        //lists all files in current working directory
        public  void ls() {
        //String currentdir=System.getProperty("user.dir");
        //String s=founddir.getAbsolutePath();
         
        String [] contents = founddir.list();
        
        for (int i = 0; i < contents.length; i++)
        {
            System.out.println(contents[i]);
        }
    }
         public  void ls_r() {
            // String s=founddir.getAbsolutePath();
       // String currentdir=System.getProperty("user.dir");
      
        String [] contents = founddir.list();
        
        //collections is funtion build in arrays and mde it as list
        Collections.reverse(Arrays.asList(contents));//here trnsfer from last to first
         System.out.println(Arrays.asList(contents));
        }
         public void exit(){System.exit(0);}
        void cd(){
          String pathofUser;
          founddir = new File(System.getProperty("user.home"));
          pathofUser=(System.getProperty("user.home"));
            System.out.println(pathofUser);
        }
        public void cd(String path)
        {
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
        public static void main(String[] args) {
        Terminal t=new Terminal();
        System.out.println(t.echo("alaa"));
        t.pwd();
        t.ls();
        t.ls_r();
        t.cd();
        t.cd("C:\\Users\\LENOVO\\Documents\\NetBeansProjects");
        
    }
}
