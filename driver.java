import java.io.*;
import java.util.*;
class driver{
    public static void main(String args[])throws IOException
    {
        try{
            int n;
            String t;
            Scanner sc=new Scanner(System.in);
            Inp_Candidates ob=new Inp_Candidates();
            System.out.println("Type \"end\" to Terminate the Program\n");
            System.out.println("The Options Are \n1 : Private Elections \n2 : Political Elections\n3 : Voter Registration \n4 : Voter File Clearance");
            System.out.print("\nKindly Enter Your Choice : ");
            t=sc.nextLine();
            System.out.println("");
            if(t.equalsIgnoreCase("\"end\"")){
                System.out.println("The Election Has Been Terminated");
                System.exit(0);
            }
            n=Integer.parseInt(t);
            if(n==1) //private election
            ob.Private();
            else if(n==2) //political election
            ob.Political();
            else if(n==3) //register voter
            {
                votetaker ob1=new votetaker();
                ob1.newvoter(false);
            }
            else if(n==4){ //clear voter file
                File infile=new File("Voters.txt");
                infile.delete();
                FileWriter fw=new FileWriter("Voters.txt");
                BufferedWriter printing = new BufferedWriter(fw);
                printing.write("");
                System.out.println("Voter Data Has Been Erased");
            }
            else
            {
                System.out.println("Invalid Entry");
                System.exit(0);
            }
        }
        catch(NumberFormatException e){ //exception handling
            System.out.println("Invalid Input. \nProgram Terminated.");
            System.exit(0);
        }
        catch(FileNotFoundException e1){
            System.out.println("Voter Data Not Found. \nProgram terminated");
            System.exit(0);
        }
    }
}