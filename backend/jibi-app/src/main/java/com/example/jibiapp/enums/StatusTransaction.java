package com.example.jibiapp.enums;

public enum StatusTransaction {
    ENATTENTE,// La transaction a été créée mais pas encore validée.
    VALIDEE,// La transaction a été validée et est en attente de confirmation.
    CONFIRMEE,//La transaction a été confirmée
    ECHOUEE,// La transaction n'a pas pu être complétée.
    ANNULEE // La transaction a été annulée.

}
