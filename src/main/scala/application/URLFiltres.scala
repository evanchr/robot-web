package application

import library.FiltrageURLs
import library.Html
import library.Tag
import library.Texte

class URLFiltres extends FiltrageURLs{
    
    /**
     * @param h une page html
     * @return une liste des URLs d'annonce de cette page html
     */ 
    def filtreAnnonce(h:Html):List[String]={
        h match {
            case Tag("a", attributes, children) => clearVoid(fonctionSurListe[Html,String](children,filtreAnnonce):+trouveURL(attributes))
            case Tag(name, attributes, children) => fonctionSurListe[Html,String](children,filtreAnnonce)
            case _: Html => Nil
        }
    }


    /**
     * @param l une liste
     * @tparam A le type des éléments de la liste A
     * @param f une fonction de A vers liste de B
     * @tparam B le type d'élément des listes produites par f
     * @return la concaténation des résultats de f sur chaque élément de l
     */

    def fonctionSurListe[A,B](l:List[A],f:A=>List[B]):List[B]={
        var result = List[B]() 
        for (a<-l){
            result= result ++ f(a)
        }
        result
    }

    /** 
      * @param list une list de String
      * @return la meme liste dont les elements vides ("") furent soustraits
      */
    def clearVoid(list:List[String]):List[String]={
        var result= List[String]()
        list match{
            case head::next=>if (head=="") return clearVoid(next) else head::clearVoid(next)
            case Nil=>Nil
        }
        }


    /**
      * @param l une liste de couple de String (dans ce tp (nom,URL)) 
      * @return l'url de la liste qui a pour nom href et qui contient un lien vivastreet la liste vide sinon
      */

    def trouveURL(l:List[(String,String)]):String={
        if (l.contains("class","clad__ad_link")){
        for ((name,url)<-l){
            if (name=="href"&& url.contains("https://www.vivastreet.com/")) return url
        }
    }
        return ""
    }
    









/*
  /**
    * @param h un element html
    * @return une liste d'URLs sous forme de strings
    */
    def filtreAnnonce(h: Html): List[String] = {
        var listeURL = List[String]("https://www.vivastreet.com/accessoires-piece-auto/creil-60100/culasse-peugeot-boxer-citroen-jumper-2-0-adblue-dw10/271254865?_gl=1*y5n2qs*_ga*MjA5MzkyNjU3NC4xNjc2MzYwMTM4*_ga_M7G8EMPECX*MTY3NjM2NzgyNi4yLjEuMTY3NjM2ODEzMS4wLjAuMA..",
                                    "https://www.vivastreet.com/accessoires-piece-auto/pouille-41110/d-marreur-pour-peugeot-neuf/259607065?_gl=1%2A1t7hgyj%2A_ga%2AMjA5MzkyNjU3NC4xNjc2MzYwMTM4%2A_ga_M7G8EMPECX%2AMTY3NjM2NzgyNi4yLjEuMTY3NjM2ODE2Ny4wLjAuMA..",
                                    "https://www.vivastreet.com/services-sante-forme-beaute/paris-17eme-ardt-75017/t-l--0760264541-m-tro-villier-salon-de-massage-pas-cher/311258779?_gl=1%2Ay5dagh%2A_ga%2AODc3MTM1OTg0LjE2NzY3MjIzMzQ.%2A_ga_M7G8EMPECX%2AMTY3NzIzNjkxOC40LjEuMTY3NzIzNjk5Mi4wLjAuMA..",
                                    "https://www.vivastreet.com/voiture-occasion/nogent-sur-oise-60180/peugeot-308-bluehdi-100-ch-bv6-active-business-105-670km/313706134?_gl=1%2A1qck3mv%2A_ga%2AODc3MTM1OTg0LjE2NzY3MjIzMzQ.%2A_ga_M7G8EMPECX%2AMTY3NzIzNjkxOC40LjEuMTY3NzIzNzMyNi4wLjAuMA..")
        h match {
            case Tag(name, attributes, children) => if (name=="a") {
                                                        listeURL = listeURL :+ filtreAttributes(attributes)
                                                    }
                                                    listeURL = listeURL.concat(filtreAnnonceChildren(children))
            case Texte(content) => Nil
            case _ => Nil
        }
        listeURL
    }
    
    /**
      * @param h une liste d'elements HTML
      * @return la recursivité sur les enfants de filtre annonce
      */
    def filtreAnnonceChildren(lh: List[Html]): List[String] = {
        var l= List[String]()
        for (child<-lh) {
            child match{
                case Tag(name, attributes, children) =>   l.concat(filtreAnnonce(child))
                case Texte(content) => Nil
                case _ => Nil
            }
        }
        l
    }
    /**
      * @param attributs une liste de couples (cle,valeur) qui sont les attributs du Tag "a"
      *                  Ex: List((href,https://www.vivastreet.com/favourites), (class,tablet-mobile-only header-icon-el))
      * @return l'url
      */
    def filtreAttributes(attributs: List[(String,String)]): String={
        var url: String=""
        for ((cle,valeur)<-attributs){
            cle match {
                case "href" if (valeur.contains("https://www.vivastreet.com/")) => url = valeur
                case _ => url = ""
            }
        }
        url
    }
    */
}

