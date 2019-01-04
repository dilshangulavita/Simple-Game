import java.util.Scanner;

public class Steps {
    private int numplayers=0;   //number of players

    //Constructor to set a default value to number of players
    Steps(){
        numplayers=0;
    }
    //Constructor to set the number of players
    Steps(int numplayers){
        this.numplayers=numplayers;
    }

    //This method is defined to request the user input(Enter) to start the game
    public void enter(String ss){
        Scanner reader = new Scanner(System.in);  // Reading from System.in
        System.out.println("\n\n"+ss);
        String res="C";
        res = reader.nextLine();

        while(res.equals("C")){

            if(!res.equals("C")){
                break;
            }
            else {
                res = reader.nextLine();
            }
        }
    }

    public int mafiaKill(int mafianumbers[],AssignPlayers a){
        Scanner reader = new Scanner(System.in);  // Reading from System.in
        //Displaying the mafia players
        for (int i=0;i<mafianumbers.length;i++){
            System.out.print("Player "+(mafianumbers[i]+1) + " ");
        }

        System.out.println("...Which player you want to kill ?");
        int ply=0;

        int j=1;
        /*Loop to run until mafia selects a valid player to shoot*/
        while (j==1) {
            j=0;
            ply=reader.nextInt();
            //If mafia selects an invalid player
            if((ply>numplayers)){
                System.out.println("Invalid player number !!! try again... ");
                j=1;  //to keep the while loop
            }
            //If mafia selects a player who is already defeated
            else if(a.getPlayerLive(ply-1)==0){
                System.out.println("Player is defeated already");
                j=1;  //to keep the while loop
            }
            //If mafia selects another mafia to shoot
            else {
                for (int i = 0; i < mafianumbers.length; i++) {
                    if (ply == (mafianumbers[i]+1)) {
                        System.out.println("You cant't kill your own !!! try again...");
                        j = 1; //to keep the while loop
                    }
                }
            }
        }
        return ply;
    }

    public int docSave(int docNum,AssignPlayers a){
        Scanner reader = new Scanner(System.in);  // Reading from System.in
        int med=0;
        if(docNum==-1){
            System.out.println("Doctor Whom are you going to save ?");
            enter("Press Enter to Continue");
        }
        else {
            System.out.println("Doctor (Player" + (docNum + 1) + ") Whom are you going to save ?");
            int j=1;
            while(j==1){
                j=0;
                med = reader.nextInt();
                if(med>numplayers) {
                    System.out.println("Invalid player !!! try again...");
                    j=1;
                }
                else if(a.getSelectChar(med-1)=="o"){
                    System.out.println("Player is already defeated...cannot be saved!!! try again...");
                    j=1;
                }
            }
        }

        return med;
    }

    public int sherriffCheck(int sherriffNum,AssignPlayers a){
        Scanner reader = new Scanner(System.in);  // Reading from System.in
        int sus=0;
        if(sherriffNum==-1){
            System.out.println("Sherriff (Player Whom do you think is mafia?");
            enter("Press Enter to Continue");

        }
        else {
            System.out.println("Sherriff (Player "+(sherriffNum+1)+") Whom do you think is mafia?");
            int j=1;
            while(j==1){
                j=0;
                sus = reader.nextInt();
                if(sus>numplayers) {
                    System.out.println("Invalid player !!! try again...");
                    j=1;
                }
                else if(a.getSelectChar(sus-1)=="o"){
                    System.out.println("Player is already defeated!!! try again...");
                    j=1;
                }
            }
        }

        return sus;
    }

    public int catchMafia(AssignPlayers a){

        Scanner reader = new Scanner(System.in);  // Reading from System.in
        System.out.println("Whom you want to imprison?");

        int sus=0;

        int j=1;
        while(j==1){
            j=0;
            sus = reader.nextInt();
            if(sus>numplayers) {
                System.out.println("Invalid player !!! try again...");
                j=1;
            }
            else if(a.getSelectChar(sus-1)=="o"){
                System.out.println("Player is already defeated!!! try again...");
                j=1;
            }
        }
        return sus;
    }
}
