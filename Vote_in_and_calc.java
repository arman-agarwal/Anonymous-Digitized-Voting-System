import java.io.*;
import java.util.*;
class Vote_in_and_calc{
    double[] count(int n,String arr[])throws IOException{
        int x=0;
        double arrvote[]=new double[n];
        double c=0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        votetaker ob= new votetaker();
        for(int i=0;i<n;i++) //initialising all votes to zero
        arrvote[i]=0;
        System.out.println("Type \"done\" To End The Voting Procedure");
        do
        {
            int t=ob.check();
            if(t==2){ //voting is complete
                for(int i =0;i<n;i++){ //convert votes to percentage
                    arrvote[i]=(arrvote[i]*100.0)/c;
                }
                return arrvote;
            }
            if(t==1){ //new voter
                try{
                    FileWriter fw=new FileWriter("Voters.txt",true);
                    BufferedWriter out = new BufferedWriter(fw);
                    ob.newvoter(true);
                    x=tovote(arrvote,arr,n); //take vote
                    if(x==2){ // new voter does not wish to vote
                        break;
                    }
                    else if(x==1){ //voting successful
                        out.write(ob.name+"\t"+ob.str+"\tVoted \n");
                        out.close();
                        c++;
                    }
                    else{ // voting unsucesful
                        out.write(ob.name+"\t"+ob.str+" \n");
                        out.close();
                    }
                    out.close();
                }
                catch (IOException e) // exception handling
                {
                    System.out.println("Exception Occured" + e);
                }
            }
            if(t==3){ //existing voter eligible to vote
                try{
                    FileWriter fw=new FileWriter("Voters.txt",true);
                    BufferedWriter out = new BufferedWriter(fw);
                    x=tovote(arrvote,arr,n); //take vote
                    if(x==2){ // existing voter does not wish to vote
                        break;
                    }
                    else if(x==1){ // voting sucesfull
                        out.write(ob.name+"\t"+ob.str+"\tVoted \n");
                        out.close();
                        ob.removeline(ob.name+"\t"+ob.str+" ");
                        c++;
                    }
                    out.close();
                }
                catch (IOException e) //exception handling
                {
                    System.out.println("Exception Occured" + e);
                }
            }
            else
            ;
        }while(true);
        return arrvote;
    }
    int tovote(double arrvote[],String arr[],int n)throws IOException{ //take vote from approved voter
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter Your Vote");
        boolean f=false;
        String str=br.readLine();
        if(str.equalsIgnoreCase("\"end\"")){ //user wishes to terminate election
            System.out.println("The Election Has Been Terminated");
            System.exit(0);
        }
        if(str.equalsIgnoreCase("\"done\"")) //voting is complete
        return 2;
        if(Character.isDigit(str.charAt(0))){ //user votes using serial number
            if(Integer.parseInt(str)<=n){
                f=true;
                arrvote[Integer.parseInt(str)-1]++;
                System.out.println("Vote Registered");
            }
        }
        else{
            for(int i=0;i<n;i++){ // user votes using candidate name
                if(str.equals(arr[i].substring(3))){
                    f=true;
                    arrvote[i]++;
                    System.out.println("Vote Registered");
                }
            }
        }
        if(!f){ //invalid vote by user
            System.out.println("Invalid Input. \nVote Not Registered");
            return 0;
        }
        return 1;
    }
    int[] votecalc(int n,double arr[]){ //calculate winners of the election
        int win[]=new int[n];
        for(int i=0;i<n;i++){
            win[i]=i;
        }
        for(int i=0; i< n ; i ++){
            for(int j=0;j< n-1-i;j++){
                if(arr[win[j]]<arr[win[j+1]]){
                    int temp=win[j];
                    win[j]=win[j+1];
                    win[j+1]=temp;
                }
            }
        }
        return win;
    }
    void display(int win[],double votes[],String arr[],int n){ //display reuslts for private election
        System.out.printf("\nWinner : %s( %.2f%% )",arr[win[0]].substring(3),votes[win[0]]);
        for(int j=1;j<n;j++){
            if(votes[win[j]]==votes[win[0]]){
                System.out.printf(" and %s( %.2f %% )",arr[win[j]].substring(3),votes[win[j]]);
            }
            else
            break;
        }
        System.out.printf("\n\nCandidate\tVote\n");
        for(int i=0;i<n;i++){
            System.out.printf("%s\t\t%.2f%%\n",arr[win[i]].substring(3),votes[win[i]]);
        }
    }
    void display(int win[],double votes[],String arr[],int n,String party[],int age[]){ //display results for political election
        System.out.printf("\nWinner : %s ( %.2f , %s)",arr[win[0]].substring(3),votes[win[0]],
        party[win[0]]);
        for(int j=1;j<n;j++){
            if(votes[win[j]]==votes[win[0]])
            System.out.printf("and %s ( %.2f , %s)",arr[win[j]].substring(3),votes[win[j]],party[win[j]]);
            else
            break;
        }
        System.out.printf("\n\nCandidate\tParty\tVote\n");
        for(int i=0;i<n;i++)
        System.out.printf("%s\t\t%s\t%.2f\n",arr[win[i]].substring(3),party[win[i]],votes[win[i]]);
    }
}