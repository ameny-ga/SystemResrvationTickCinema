package Model.Cinema;

public  class Salle  {
     private String labelle;
     private int capacite;
    private categoryecran typeecran;

    public Salle(categoryecran typeecran, int capacite, String labelle) {
        this.typeecran = typeecran;
        this.capacite = capacite;
        this.labelle = labelle;

    }

    public String getLabelle() {
        return labelle;
    }

    public int getCapacite() {
        return capacite;
    }

    public categoryecran getTypeecran() {
        return typeecran;
    }

    public void setLabelle(String labelle) {
        this.labelle = labelle;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public void setTypeecran(categoryecran typeecran) {
        this.typeecran = typeecran;
    }

    @Override
    public boolean equals(Object obj)
    {
        Salle salle = (Salle) obj ;
        if(salle.getLabelle().equals(this.labelle)){return true;}
        else{return false;}
    }
    @Override
    public String toString(){
        return "Labbel = "+labelle+"Capacite = "+capacite+" TypeEcrant = "+typeecran;
    }







}
