import java.util.Random;

public class AssignPlayers {
    int numPlayers;             //number of players of the game
    int mafiaReq=0;             //number of required maifa players
    int civReq=0;               //number of required civillians
    int mafiaCount=0;           //number of mafia players
    int civCount=0;             //number of players who are civillians
    int restCount;              //number of players who are not mafia
    private String[] selectChar;        //Array of Strings to store each player's character
    private Person[] players;           //Array of Person objects

    //constructor to set default values
    AssignPlayers(){
        this.numPlayers=0;
        selectChar=new String[numPlayers];
        players=new Person[numPlayers];
        restCount=numPlayers-mafiaCount;
    }

    //constructor to set values
    AssignPlayers(int numPlayers){

        this.numPlayers=numPlayers;
        selectChar=new String[numPlayers];
        players=new Person[numPlayers];       //setting the size of players array

        /*the algorithm to determine the number of mafia players depending
          on the number of players of the game*/
        if(numPlayers>=6){
            mafiaReq=2;
        }
        else if(numPlayers>=4){
            mafiaReq=1;
        }
        else {
            System.out.println("Sorry You dont have enough players for the game !!!");
            System.exit(0);
        }
        civReq=numPlayers-(2+mafiaReq);

        /*---------------------------------------------------------------------------*/

        /*required for the select characters part*/
        for (int i=0;i<numPlayers;i++){
            selectChar[i]="";
        }

        Random rand = new Random();
        //select mafia
        while (mafiaCount<mafiaReq) {
            int n = rand.nextInt(numPlayers);

            /*while loop will run unil unassigned player is found*/
            while (!(selectChar[n].equals(""))) {
                n = rand.nextInt(numPlayers);
            }
            selectChar[n] = "m";
            players[n]=new Person(1,1,0,0,0);
            mafiaCount++;
        }
        //select civillians
        while (civCount<civReq){
            int n = rand.nextInt(numPlayers);
            while (!(selectChar[n].equals(""))) {
                n = rand.nextInt(numPlayers);
            }
            selectChar[n] = "c";
            players[n]=new Person(1,0,0,0,1);
            civCount++;
        }
        //select sherriff
        int n = rand.nextInt(numPlayers);
        while (!(selectChar[n].equals(""))) {
            n = rand.nextInt(numPlayers);
        }
        selectChar[n] = "s";
        players[n]=new Person(1,0,1,0,0);
        //select Doctor
        for(int i=0;i<numPlayers;i++){
            if(selectChar[i].equals("")){
                selectChar[i] = "d";
                players[i]=new Person(1,0,0,1,0);
            }
        }

        //Displaying each players character and life status
        for (int i=0;i<numPlayers;i++){
            System.out.println("Player "+ (i+1)+ " : " +players[i]);
        }

        restCount=numPlayers-mafiaCount;   //keeping track of the number of active players who are not mafia
    }

    /*-----------------Mutators-----------------------------*/
    /*******************************************************/

    //Setting the character
    public void setSelectChar(int x,String s){
        selectChar[x]=s;
    }
    //Setting the number of active mafia players
    public void setMafiaCount(int mafiaCount) {
        this.mafiaCount = mafiaCount;
    }
    //Setting the number of active players who are not mafia
    public void setRestCount(int restCount){
        this.restCount=restCount;
    }
    //Setting the player's life status
    public void setLiveplayer(int x,int y){
        players[x].setLive(y);
    }
    //When a player who is not mafia gets defeated
    public void deductRestCount(){
        restCount--;
    }
    //When a mafia player gets defeated
    public void deductMafiaCount(){
        mafiaCount--;
    }


    /*******************************************************/
    /*------------------------------------------------------*/


    /*-------------Accessors------------------------------*/
    /*******************************************************/

    //Getting the whole array of characters
    public String[] getSelectChar(){
        return selectChar;
    }
    //Getting the character of a given player
    public String getSelectChar(int x){
        return selectChar[x];
    }
    //Getting the player numbers of mafia players
    public int[] getMafiaNumbers(){
        int[] mafiaNum=new int[mafiaCount];
        int j=0;
        for(int i=0;i<numPlayers;i++){
            if(selectChar[i].equals("m")){
                mafiaNum[j]=i;
                j++;
            }
        }
        return mafiaNum;
    }
    //Getting the player number of the doctor
    public int getDoctorNumbers(){
        int docNum=-1;
        int j=0;
        for(int i=0;i<numPlayers;i++){
            if(selectChar[i].equals("d")){
                docNum=i;
            }
        }
        return docNum;
    }
    //Getting teh player number of Sherriff
    public int getSherriffNumbers(){
        int SheriffNum=-1;
        int j=0;
        for(int i=0;i<numPlayers;i++){
            if(selectChar[i].equals("s")){
                SheriffNum=i;
            }
        }
        return SheriffNum;
    }
    //Getting the life status of a player
    public int getPlayerLive(int x){
        return (players[x].getLive());
    }
    //Getting the player numbers of all active players
    public void livePlayers(){
        for (int i=0;i<numPlayers;i++){
            if(players[i].getLive()==1){
                System.out.println("Player "+ (i+1));
            }
        }
    }


    /*******************************************************/
    /*---------------------------------------------------*/

    //To check if a player is mafia or not
    public void checkIfMafia(int sus){
        if((getSelectChar((sus-1))).equals("m")){
            System.out.println("You are correct");
        }
        else {
            System.out.println("You are wrong");
        }
    }

    //Displaying info about players
    public String toString(){
        String info="";
        for (int i=0;i<numPlayers;i++){
            info+=("Player "+ (i+1)+ " : " +players[i]+"\n");
        }
        return info;
    }


}
