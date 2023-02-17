package com.pfe.campagne.helper;

public class EntrepriseException extends Exception{

    public EntrepriseException(){
        super("Entreprise avec ce nom existe déjà dans la base de donnée choisir un autre");
    }

    public EntrepriseException(String msg){ super(msg);}
    }
