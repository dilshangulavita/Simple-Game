/* This is a simple game which can be played when there are more than 4 players. 
At the start each player is assigned with a character and game starts. Steps are stated as below
1.Assign the players
2.Night
3.Only Mafia wakes up and points whom to kill and then sleeps
4. Only Doctor wakes up and points whom to save
5.Only Sherriff wakes up and points someone whom he thinks is the mafia. Narrator nods yes/no
6.Daytime
7. Narrator announces who is dead
8.Everybody wakes up and decides who is the mafia. A vote would be necessary.
9.The suspect dies
10. Step 2 again (if mafia<the rest)
*/

import java.util.Scanner;
import java.util.Random;


public class DemoMafia {
    public static void main(String[] args){

        int numPlayers=0;   //defining the num of players

		Scanner reader = new Scanner(System.in);  // Reading from System.in

        System.out.println("Enter the number of players: ");
        //numPlayers=6;        //deafault value for umber of players for testing

        numPlayers = reader.nextInt();  //user inputs the number of players

        //Assigning each player with a character-calling the constructor
        AssignPlayers a1=new AssignPlayers(numPlayers);

        //Creating a "Steps" Object to run the steps
        Steps s1=new Steps(numPlayers);    //Calling the constructor
        s1.enter("Press Enter to Start the Game");  //Calling the enter method

        int j=0;
        /*The loop is used to run the game until one team wins the game*/
        while(j==0) {

            //Game Ending conditions
            if((a1.mafiaCount>=a1.restCount)){
                System.out.println("Mafia Wins !!!");
                break;
            }
            else if(a1.mafiaCount==0){
                System.out.println("Mafia is chased out of the village...You are safe!!!");
                break;
            }

            //For each time the game is played the active players are displayed
            a1.livePlayers();
            int tmpDead=0; //to keep track of the shot person

            System.out.println("\nIt's Night");
            System.out.println("Now Maifa Wakeup !!!");

            //press enter to continue
            s1.enter("Press Enter to Continue");

            //Mafia in Action Step
            int[] mafiaNumbers = a1.getMafiaNumbers();
            int shot = s1.mafiaKill(mafiaNumbers,a1);

            tmpDead=(shot);
            a1.setLiveplayer((shot-1),0);

            //Display the information
            System.out.println(a1);

            //Sheriff Step
            int sherriffNum = a1.getSherriffNumbers();
            int sus = s1.sherriffCheck(sherriffNum,a1);
            if(sherriffNum!=-1) {
                a1.checkIfMafia(sus);
            }

            //Doctor saving step
            int docNum = a1.getDoctorNumbers();
            int med = s1.docSave(docNum,a1);
            //Doctor can heal only if he is alive
            if(docNum!=-1) {
                a1.setLiveplayer((med - 1), 1);
            }
            if(med==tmpDead){
                tmpDead=0;
            }

            //display the information
            System.out.println(a1);

            System.out.println("Its DayTime");
            //out=new int[a1.getOutCount()];

            //out=a1.getOutPlayers();

           if(tmpDead!=0){
               System.out.println("Shots were fired and player "+tmpDead+" Could not be saved");
               a1.setSelectChar(tmpDead-1,"o"); //to indicate that it is not active
               a1.deductRestCount();
           }
           else {
               System.out.println("Shots were fired but doctor was able to save the injured");
           }

            //Game Ending conditions
            if((a1.mafiaCount>=a1.restCount)){
                System.out.println("Mafia Wins !!!");
                break;
            }
            else if(a1.mafiaCount==0){
                System.out.println("Mafia is chased out of the village...You are safe!!!");
                break;
            }

           int catchs=s1.catchMafia(a1);
            a1.setLiveplayer((catchs-1),0);
           if((a1.getSelectChar(catchs-1)).equals("m")){
               a1.setSelectChar(catchs-1,"o"); //to indicate that it is not active
               a1.deductMafiaCount();
           }
           else{
               a1.setSelectChar(catchs-1,"o"); //to indicate that it is not active
               a1.deductRestCount();
           }



        }
    }
}
