public class Person {
    private int live;
    private int mafia;
    private int sherriff;
    private int doctor;
    private int civilian;

    //live 0 means not alive,mafia=1 means the person is mafia
    public Person(){
        live=1;
        mafia=0;
        sherriff=0;
        doctor=0;
        civilian=0;
    }
    public Person(int live,int mafia,int sherriff,int doctor,int civilian){
        this.live=live;
        this.mafia=mafia;
        this.sherriff=sherriff;
        this.doctor=doctor;
        this.civilian=civilian;
    }
    public void setLive(int live){
        this.live=live;
    }
    public int getLive(){
        return live;
    }
    public void setMafia(){
        this.mafia=1;
    }
    public void setSherriff(){
        this.sherriff=1;
    }
    public void setDoctor(){
        this.doctor=1;
    }
    public void setCivilian(){
        this.civilian=1;
    }
    public String toString(){
        String des="";
        if(live==1){
            if(mafia==1){
                des+="Maifa and alive";
            }
            else if(sherriff==1){
                des+="Sheriff and alive";
            }
            else if(doctor==1){
                des+="Doctor and alive";
            }
            else {
                des+="Civillian and alive";
            }
        }
        else {
            if(mafia==1){
                des+="Maifa out";
            }
            else if(sherriff==1){
                des+="Sheriff out";
            }
            else if(doctor==1){
                des+="Doctor out";
            }
            else {
                des+="Civillian out";
            }
        }
        return des;
    }



}
