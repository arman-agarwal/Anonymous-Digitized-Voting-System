import java.io.*; // Import the File class
import java.util.*; // Import the IOException class to handle errors
public class votetaker
{
    Scanner sc=new Scanner(System.in);
    String name,str;
    String Gen_ID() //generate unique id for voter
    {
        long gen = 1000000000L + (long) (Math.random() * (9999999999L - 1000000000L));
        return Long.toString(gen);
    }
    void newvoter(boolean f){ //add new voter along with details to voter file
        System.out.print("Full Name : ");
        name=sc.nextLine();
        if(name.equalsIgnoreCase("\"end\"")) //user wishes to terminate election
        {
            System.out.println("Election Has Been Terminated");
            System.exit(0);
        }
        str=Gen_ID(); //generate unique id for new voter
        System.out.println("Unique Voter ID : "+str);
        if(!f){ //incase voter wants to register and not vote
            try{
                FileWriter fw=new FileWriter("Voters.txt",true);
                BufferedWriter printing = new BufferedWriter(fw);
                printing.write(name+"\t"+str+" \n");
                printing.close();
            }
            catch (IOException e) //exception handling
            {
                System.out.println("Exception Occured : " + e);
            }
            System.out.println("Voter Registered");
        }
    }
    int existingvoter(){ //check if voter id is valid and voter is eligible to vote
        String str1,temp,str2="";
        try
        {
            FileReader fr=new FileReader("Voters.txt");
            BufferedReader br = new BufferedReader(fr);
            System.out.println("Enter Your 10 digit Voter ID : ");
            str1=sc.nextLine();
            if(str1.equalsIgnoreCase("\"end\"")){ //user wishes to terminate election
                System.out.println("Election Has Been Terminated");
                System.exit(0);
            }
            if(str1.equalsIgnoreCase("\"done\"")) //voting is completeN
            return 2;
            while((temp = br.readLine())!=null)
            {
                int c=0;
                int k=0;
                temp=temp+" ";
                int l=temp.length();
                while(c<l) //extracting unique id of from each line of file
                {
                    if(Character.isDigit(temp.charAt(c)))
                    {
                        if(str2.equals(""))
                        k=c;
                        str2=str2+temp.charAt(c);
                    }
                    c++;
                }
                if(str1.equals(str2)) //checking if voter entered unique id matches file
                {
                    
                    if((temp.substring(l-7).trim()).equals("Voted")) //checking if voter has voted
                    {
                        str=str2;
                        name=temp.substring(0,k).trim();
                        return 11;
                    }
                    else
                    {
                        str=str2;
                        name=temp.substring(0,k).trim();
                        return 10;
                    }
                }
                str2="";
            }
            br.close();
        }
        catch (IOException e) //exception handling
        {
            System.out.println("Voter Data Not Found");
            return 5;
        }
        return 0;
    }
    int check() //check if user is new voter or existing voter
    {
        Scanner sc=new Scanner(System.in);
        boolean flag=true;
        String s;
        System.out.println("\nThe Options Are :");
        System.out.println("1 : New Voter \n2 : Registered Voter");
        System.out.print("\nKindly Enter Your Choice : ");
        s=sc.nextLine();
        if(s.equalsIgnoreCase("\"end\"")){ //user wishes to terminate election
            System.out.println("The Election Has Been Terminated");
            System.exit(0);
        }
        if(s.equalsIgnoreCase("\"done\"")) //voting is complete
        return 2;
        int k=Integer.parseInt(s);
        int t=0;
        if(k==1) //new voter
        {
            // System.out.println("reached here");
            return 1;
        }
        else if(k==2) //exisitng voter
        {
            
            t=existingvoter();
            if(t==10) //exisitng voter who has not voted yet
            flag=true;
            else if (t==0||t==11) //vote has already been cast
            flag=false;
            else if(t==5) //error
            return 5;
            else
            return 2;
        }
        else{ //inavlid
            System.out.println("Invalid Input");
            return 5;
        }
        if(flag)
        {
            System.out.println("Please Vote");
            return 3;
        }
        else
        {
            if(t==0)
            System.out.println("Invalid Voter ID");
            else if(t==11)
            System.out.println("Your Vote Has Already Been Registered");
            return 0;
        }
    }
    void removeline(String s) throws IOException //to remove particular line from file
    {
        PrintWriter pw = new PrintWriter("Temp.txt"); //creating temporary file
        BufferedReader br = new BufferedReader(new FileReader("Voters.txt"));
        String line = br.readLine();
        while(line != null) //copying all lines except line to remove to temporary file
        {
            if(!s.equals(line))
            pw.println(line);
            line = br.readLine();
        }
        pw.flush();
        br.close();
        pw.close();
        File infiletemp=new File("Temp.txt");
        File infile=new File("Voters.txt");
        infiletemp.renameTo(infile); //renaming temporary file to main file
    }
}