import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.*;
/**
*  Une piece dans un jeu d'aventure. 
*   <p>
*
*  Cette classe fait partie du jeu One Piece, un jeu d'aventure simple en mode
*  texte avec comme base le logiciel Zork.</p> <p>
*
*  Une "Piece" represente un des lieux dans lesquels se deroule l'action du
*  jeu. Elle est reliee a au plus quatre autres "Piece" par des sorties. Les
*  sorties sont etiquettees "nord", "est", "sud", "ouest". Pour chaque
*  direction, la "Piece" possede une reference sur la piece voisine ou null
*  s'il n'y a pas de sortie dans cette direction. Une "Piece" peut egalement comporter des objets, transportables par le joueur ou non.</p> 
*
* @author     Michael Kolling
* @author     Marc Champesme (pour la traduction francaise)
* @author     Harris Hamdani (ajout d'objets)
* @version    1.2
* @since      August 2000
* @version    Octobre 2018 (ajout de la notion d'objets)
*/

public class Piece {
	/*@
	@invariant descriptionCourte() != null;
	@invariant getNbObjets() >= 0;
    @invariant getObjets() != null;
	@*/  
	private String description;
	private int nbObjets;
	private ArrayList<ObjetZork> lesObjets;
	// memorise les sorties de cette piece.
	private Map<String,Piece> sorties; 



	/**
	 *  Initialise une piece decrite par la chaine de caracteres specifiee.
	 *  Initialement, cette piece ne possede aucune sortie et aucun objet.
	 *  La description fournie est une courte phrase comme "la bibliotheque" ou "la salle de TP".
	 *  
	 * @param  description  Description de la piece.
	 */
	/*@
	@requires description != null;
	@ensures getNbObjets() == 0;
	@ensures descriptionCourte().equals(description);
	@*/
	public Piece(String description) {
		this.description = description;
		this.nbObjets = 0;
		this.lesObjets = new ArrayList<ObjetZork>(10);
		sorties = new HashMap<String,Piece>();
	}


	/** 
	 *  Initialise une piece decrite par la chaine de caracteres, le nombre d'objet et la liste d'objets specifies.
	 *  Initialement, cette piece ne possede aucune sortie.
	 *  La description fournie est une courte phrase comme "la bibliotheque" ou "la salle de TP".
	 * @param  description  Description de la piece.
	 * @param  nbObj  Nombre d'objets present dans la piece.
	 * @param  lesObjet  Objets presents dans la piece
	 */
	/*@
	@requires description != null;
	@requires lesObjet != null;
    @requires nbObj > 0;
    @requires nbObj < lesObjet.size();
    @requires (\forall int i; i >=0 && i< nbObj; lesObjet.get(i) != null);
	@ensures getNbObjets() ==  nbObj;
	@ensures descriptionCourte().equals(description);
    @ensures (\forall int i; i >= 0 && i < getNbObjets();contient(lesObjet.get(i)));
    @
	@*/
	public Piece(String description, int nbObj, ArrayList<ObjetZork> lesObjet) {
	
		this.description = description;
		this.nbObjets = nbObj;
		this.lesObjets = new ArrayList<ObjetZork>(nbObjets);
		for(int i = 0;i<nbObjets;i++)
		{
			lesObjets.add(lesObjet.get(i));
		}
		sorties = new HashMap<String,Piece>();
	}


	/**
	 *  Definit les sorties de cette piece. A chaque direction correspond ou bien
	 *  une piece ou bien la valeur null signifiant qu'il n'y a pas de sortie dans
	 *  cette direction.
	 *
	 * @param  nord   La sortie nord
	 * @param  est    La sortie est
	 * @param  sud    La sortie sud
	 * @param  ouest  La sortie ouest
	 */
	public void setSorties(Piece nord, Piece est, Piece sud, Piece ouest) {
		if (nord != null) {
			sorties.put("nord", nord);
		}
		if (est != null) {
			sorties.put("est", est);
		}
		if (sud != null) {
			sorties.put("sud", sud);
		}
		if (ouest != null) {
			sorties.put("ouest", ouest);
		}
	}


	/**
	 *  Renvoie la description de cette piece (i.e. la description specifiee lors
	 *  de la creation de cette instance).
	 * 
	 * @return    Description de cette piece
	 */
    //@pure
	public String descriptionCourte() {
		return description;
	}


	/**
	 *  Renvoie une description de cette piece mentionant ses sorties, ses objets et
	 *  directement formatee pour affichage, de la forme: <pre>
	 *  Vous etes dans la bibliotheque.
	 *  Sorties: nord ouest
     *  1 Objet:
     *  Nom : Livre Poids: 25 Transportable: Oui</pre> Cette description utilise la chaine de caracteres
	 *  renvoyee par la methode descriptionSorties pour decrire les sorties de
	 *  cette piece.
	 *
	 * @return    Description de cette piece 
	 */
    //@pure
	public String descriptionLongue() {
		String res = "Vous etes dans " + description + ".\n" + descriptionSorties() + "\n";
		res += getNbObjets() +" Objet:"+"\n";
		for(int i = 0;i < getNbObjets();i++)
		{
			res += lesObjets.get(i).descLongue();
            res += "\n";    
		}
		return res;  
	}


	/**
	 *  Renvoie une description des sorties de cette piece, de la forme: <pre>
	 *  Sorties: nord ouest</pre> Cette description est utilisee dans la
	 *  description longue d'une piece.
	 *
	 * @return    Une description des sorties de cette piece.
	 */
    //@pure
	public String descriptionSorties() {
		String resulString = "Sorties:";
		Set<String> keys = sorties.keySet();
		for (Iterator<String> iter = keys.iterator(); iter.hasNext(); ) {
			resulString += " " + iter.next();
		}
		return resulString;
	}


	/**
	 *  Renvoie la piece atteinte lorsque l'on se deplace a partir de cette piece
	 *  dans la direction specifiee. Si cette piece ne possede aucune sortie dans cette direction,
	 *  renvoie null.
	 *
	 * @param  direction  La direction dans laquelle on souhaite se deplacer
	 * @return            Piece atteinte lorsque l'on se deplace dans la direction
	 *      specifiee ou null.
	 */
	public Piece pieceSuivante(String direction) {
		return (Piece) sorties.get(direction);
	}
	/**
	*Renvoie le nombre d'objets presents dans la piece
	*
	*@return Nombre d'objets
    *
	*/
    //@pure
	public int getNbObjets()
	{
		return nbObjets;
	}
	/**
	*Renvoie les objets presents dans la piece
	*
	*@return Tableau des objets presents dans la piece
    *
	*/
    //@pure
	public ArrayList<ObjetZork> getObjets()
	{
		ArrayList<ObjetZork> res = new ArrayList<ObjetZork>(getNbObjets());
        Iterator<ObjetZork> I = lesObjets.iterator();
        while (I.hasNext())
            res.add(I.next());
        return res;
	}
	/**
	*Permet de savoir si un objet est present dans la salle ou non
	*
	*@param ob Objet pour lequel nous voulons connaitre sa presence ou non dans la piece 
	*@return true si l'objet est present; false sinon
	*/
    //@pure
	public boolean contient(ObjetZork ob)
	{
		return this.lesObjets.contains(ob);
	}
	/**
	*Renvoie le nombre d'exemplaires d'un meme objet dans la piece 
	*@param o Objet dont on souhaite savoir le nombre d'exemplaires 
	*@return 	Nombre d'exemplaires de l'objet precise dans la piece
	*/
    /*@pure
    @*/
	public int contientCombienDe(ObjetZork o)
	{
		if (!contient(o)) return 0;
		int c = 0; 
		for(ObjetZork thisoz :lesObjets)
		{
			if (thisoz.equals(o))
				c++;
		}
		return c;
	}
	/**
	*Ajoute un objet a une piece. 
	*
	*@param ob Objet que vous voulez ajouter dans une piece 
	*/
    /*@
    @requires ob != null;
    @ensures getNbObjets() == \old(getNbObjets()) + 1;
    @*/
	public void ajouter(ObjetZork ob)
	{
        nbObjets ++;
		this.lesObjets.add(ob);
		
	}
	/**
	*Renvoie si un objet a bien ete retire de la liste par un boolean
	*
	*@param ob Objet a retirer de la piece
	*@return true si l'objet a ete retire de la piece; false sinon(l'objet n'etait pas present pas la piece)
	*/
    /*@
    @requires ob != null;
    @ensures (getObjets().contains(ob)) <==> (getNbObjets() == \old(getNbObjets()) - 1);
    @ensures (!getObjets().contains(ob)) <==> (getNbObjets() == \old(getNbObjets()));
    @*/
	public boolean retirer(ObjetZork ob)
	{
        if(lesObjets.contains(ob))
            nbObjets --;
		return this.lesObjets.remove(ob);
	}

}

