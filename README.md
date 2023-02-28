Bonjour, 
nous sommes 
    Evan Froc, Nolan Guiziou, 
    Evan Charrier, Chene Adji, 
    Kawrantin Arzel--Guiziou, 
    Matéo Delsaut, Mathis Gaultier 
    et Thomas Hild

ce projet sert à : à partir d'une requête, 
                   renvoyer une page Html contenant les liens vivaStreet satisfaisant la requête
penser à lire da documentation si vous ne comprenez pas (j'ai pu me tromper dans la description des fonctions mais les 
                                                        @return et @param sont corrects)
une requête se formule par des mots interconnectés par des or et des and 
                    (un or et un and peuvent nécessiter des parenthèses pour indiquer la priorité)

par exemple : si vous recherchez une peluche de chien ou de lion on aura pour requête :
              peluche and (chien or lion)
              on obtiendra alors une page html répertoriant
              toutes les annonces vivaStreet de peluches de chien et de peluches de lion

Dans l'idée du déroulement de la recherche : -on lance une application qui demande une requête à l'utilisateur
                                             -on produit toutes les pages vivastreet qui répondent à la requête
                                             -on isole les annonces
                                             -on filtre celles qui correspondent bien à la requête
                                             -on renvoi un nouveau fichier html qui répertorie les résultats
Problème : -lors de l'étape 2 on ne vérifie pas les annonces des pages >1
           -la page du résultats n'est peut-être pas du goût de tout le monde
           -on peut aussi noter que les performances sont améliorables, 20~30s à peu près pour répondre à une requête