package application
import library.Html
import library.Tag
import library.Texte
import library.Expression
import library.OutilsWebObjet
import library.AnalysePage

object ObjectAnalysePage extends AnalysePage{
 type URL = String
 type Titre = String
 val objFiltrageUrls: URLFiltres = new URLFiltres
 val objFiltrageHtml: FiltreHTML= new FiltreHTML
 def resultats(url:String,exp:Expression):List[(Titre,URL)]= {
    val leHtml : Html = OutilsWebObjet.obtenirHtml(url)
    val lURLs : List[URL] = objFiltrageUrls.filtreAnnonce(leHtml)
    val lURLsHtml: List[(Html,URL)] = listUrlsToListHtmls(lURLs)
    val listeCouplesURLHTML: List[(URL,Html)] = coupleListfromRequete(lURLsHtml,exp)
    getResultats(listeCouplesURLHTML)
 }

 def getResultats(lh:List[(URL,Html)] ) : List[(Titre,URL)]= {
 lh match {
 case (url,html)::queue => (getTitre(html),url)::getResultats(queue)
 case Nil => Nil
 }
 }
 def getTitre(h:Html) : Titre= {
    val bcd = Set("html","body","title","head")
    h match {
        case Tag(name,_,enfants) if(bcd.contains(name) ) => getTitreEnfants(enfants) ;
        case Tag(name,_,_) => name;
        case Texte(x) => x
    }
 }
 def getTitreEnfants(lh:List[Html]) : Titre= {
    val bcd = Set("html","body","title","head")
    lh match {
        case Tag(name,_,enfants)::suite if (name =="title") => getTitreEnfants(enfants);
        case Tag(name,_,enfants)::suite => getTitreEnfants(enfants)
        case Texte(x)::_ => x
        case Nil => "niled"
    }
 }
 def coupleListfromRequete(lh:List[(Html,URL)],exp:Expression): List[(URL,Html)] = {
    lh match {
        case (html,url)::reste if (objFiltrageHtml.filtreHtml(html,exp))=>(url,html)::coupleListfromRequete(reste,exp)
        case premier::reste => coupleListfromRequete(reste,exp)
        case Nil => Nil
    }
 }

 def getUrl(h: Html) : URL= {
   objFiltrageUrls.filtreAnnonce(h)(0)
 }

 def listUrlsToListHtmls(ls:List[URL]) : List[(Html,URL)]= {
    var res = List[(Html,URL)]()
    for (url <- ls){
        res = res :+ (OutilsWebObjet.obtenirHtml(url),url)
    }
    res
 }
}