import java.util.*;

/**
 *  Un Joueur dans un jeu d'aventure. 
 * <p>Cette classe cree un joueur (un personnage) pour le jeu One Piece,
 * un jeu d'aventure simple en mode texte avec comme base le logiciel Zork.</p>
 *
 * <p>Un Joueur est caracterise par un nom, un poids maximal
 * ainsi que par les Objets qu'il les transporte avec lui et leur poids total
 * et le nombre de fois qu'il ete vu par l'ennemi.</p>
 *
 * Un "Joueur" peut transporter des objets transportables,
 * mais en verifiant que la somme des poids de tous les Objets transportes
 * est inferieure ou egale a son poids maximal.
 *
 * @author Ilyes BEN YAHIA
 * @version 1.0
 * @since Octobre 2018
 */

public class Joueur
{
    /*@ 
     @invariant getNom() != null;
     @invariant getPoids() >= 0 && getPoids() <= getPoidsMax();
     @invariant getPoidsMax() > 0;
     @invariant getNbObjets() >= 0;
     @invariant getNbVu() >= 0;
     @*/
	private String nom;
	private int poids;
	private int poidsMax;
	private int nbObjets;
	private ArrayList<ObjetZork> objets;
	private int vuParTarta;

    /**
     * Initalise un joueur qui a pour nom "Luffy".
     * Initialement, le joueur ne transporte aucun objet.
     * Le poids maximal est initialise a 100.
     *
     */
    /*@
     @ensures getNom().equals("Luffy");
     @ensures getPoidsMax() == 100;
     @ensures getPoids() == 0;
     @ensures getNbObjets() == 0;
     @
     @*/
	public Joueur()
	{
		this.nom = "Luffy";
		this.poidsMax = 100;
		this.poids = 0;
		this.nbObjets = 0;
		this.objets = new ArrayList<ObjetZork>(10);
	}

    /**
     * Initalise un joueur qui a pour nom la chaîne de caracteres entree en argument.
     * Initialement, le joueur ne transporte aucun objet.
     * Le poids maximal est initialise a 100.
     *
     * @param nom Le Nom du joueur.
     *
     */
    /*@
     @requires nom != null;
     @ensures getNom().equals(nom);
     @ensures getPoidsMax() == 100;
     @ensures getPoids() == 0;
     @ensures getNbObjets() == 0;
     @
     @*/
	public Joueur(String nom)
	{
		this.nom = nom;
		this.poidsMax = 100;
		this.poids = 0;
		this.nbObjets = 0;
		this.objets = new ArrayList<ObjetZork>(10);
	}
	
    /**
     * Initalise un joueur qui a pour nom la chaîne de caracteres entree en argument.
     * Le poids maximal est initialise a l'entier entre en argument .
     * Initialement, le joueur ne transporte aucun objet.
     *
     * @param nom Le nom du joueur.
     * @param poidsMax Le poids maximal qui peut transporter le joueur.
     *
     */
    /*@
     @requires nom != null;
     @requires poidsMax > 0;
     @ensures getNom().equals(nom);
     @ensures getPoidsMax() == poidsMax;
     @ensures getPoids() == 0;
     @ensures getNbObjets() == 0;
     @
     @*/
	public Joueur(String nom,int poidsMax)
	{
		this.nom = nom;
        this.poidsMax = poidsMax;
        this.poids = 0;
		this.nbObjets = 0;
		this.objets = new ArrayList<ObjetZork>(10);
	}

    /**
     * Renvoie le nom du Joueur.
     *
     * @return Nom de ce joueur
     * 
     */
    //@pure
	public String getNom()
	{
		return this.nom;
	}

    /**
     * Renvoie la somme des poids des Objets transportes par le Joueur.
     *
     * @return Poids total des ObjetZork de ce Joueur
     * 
     */
    //@pure
	public int getPoids()
	{
		return this.poids;
	}
	
    /**
     * Renvoie le poids maximal des objets que le joueur peut porter.
     *
     * @return Poids maximal transportable par ce Joueur
     * 
     */
    //@pure
	public int getPoidsMax()
	{
		return this.poidsMax;
	}

    /**
     * Renvoie le nombre des objets que le joueur transporte.
     *
     * @return Nombre d'objet transporter par ce Joueur
     * 
     */
    //@pure
	public int getNbObjets()
	{
		return this.nbObjets;
	}

    /**
     * Renvoie la liste des objets que le joueur transporte.
     *
     * @return Liste des objets transportable par ce Joueur
     * 
     */
    //@pure
	public ArrayList<ObjetZork> getObjets()
	{
		ArrayList<ObjetZork> obs = new ArrayList<ObjetZork>(this.nbObjets);	
        Iterator<ObjetZork> i = objets.iterator();
		while (i.hasNext())
		{
			obs.add(i.next());
		}
        return obs;
		
	}

    /**
     * Renvoie le nombre de fois que le joueur a ete vu par les tours de servillance (Tarta).
     *
     * @return Nombre de vu de ce joueur par les tours de servillance.
     * 
     */
    //@pure
	public int getNbVu()
	{
		return this.vuParTarta;
	}
    
    /**
     * Incremente le nombre de fois ou le joueur se fait reperer
     * par les tours de servillance (Tarta).
     */
    //@ensures getNbVu() == \old(getNbVu()) + 1;
	public void vuParTarta()
	{
		vuParTarta ++;
	}
    
    /**
     * Renvoie true si le joueur possede l'objet entrer en argument dans son inventaire.
     * Sinon renvoier false.
	 * @param ob Objet pour lequel nous voulons connaitre sa presence ou non dans l'inventaire de ce joueur. 
	 * @return true si l'objet est present dans l'inventeur de ce joueur; false sinon;
	 * 
     */ 
    //@pure
    public boolean contient(ObjetZork ob)
	{
		return this.objets.contains(ob);
	}
    
    /**
	 * Renvoie le nombre d'exemplaires de l'objet entre en argument 
     * dans l'inventaire du joueur.
     *
	 * @param oz Objet dont on souhaite savoir le nombre d'exemplaires 
	 * @return Nombre d'exemplaires de l'objet dans l'inventaire de ce joueur
     * 
     */
    //@ pure
	public int contientCombienDe(ObjetZork oz)
	{
		if (!contient(oz)) 
            return 0;
		int nbExemp = 0; 
		for(ObjetZork obj :objets)
		{
			if (obj.equals(oz))
				nbExemp++;
		}
		return nbExemp;
	}
    
    /**
     * Renvoie un boolean qui precise si l'objet passe en argument a ete ajoute a l'inventaire de ce joueur. 
     * L'objet est ajouté s'il est transportable et que son poids ne fasse pas depasser la capacite maxiamal du joueur 
     * @param oz L'objet qu'on veut ajouter a l'inventaire de ce joueur
     * @return true si l'objet a ete ajoute a l'inventaire de ce Joueur ;
     * false sinon.
     */
    /*@
     @requires oz != null;
     @ensures ((oz.estTransp()) && (oz.getPoids() + getPoids() <= getPoidsMax()))<==> (\result == true);
     @ensures (!(oz.estTransp()) || (oz.getPoids() + getPoids() > getPoidsMax()))<==> (\result == false);
     @ensures (\result == false) ==> ((\old(getPoids()) == getPoids()) &&  (contient(oz) == false) && (\old(getNbObjets()) == getNbObjets())&& (contientCombienDe(oz) == 0));
     @ensures (\result == true) ==> ((getPoids() == \old(getPoids()) + oz.getPoids())  && (contient(oz) == true) && (getNbObjets() == \old(getNbObjets()) + 1)&& (contientCombienDe(oz) == \old(contientCombienDe(oz))+1));
     @ 
     @
     @*/ 
	public boolean ajouter(ObjetZork oz)
	{
        if (oz.estTransp())
        {
		    if((oz.getPoids()+poids)<= poidsMax)
		    {
			    nbObjets ++;
			    objets.add(oz);
			    poids += oz.getPoids();
			    return true;
		    }
        }
		return false;
	}
	
    /**
     * Renvoie un boolean qui precise si l'objet passe en argument a ete retiree
     * de l'inventaire de ce Joueur.
     * Retire le premier exemplaire de l'objet.
     *
     * @param oz L'objet qu'on veut retirer de l'inventaire de ce joueur
     * @return true si l'objet a ete retire de l'inventaire de ce Joueur ;
     * false sinon.
     */
    /*@
     @ensures \result == \old(contient(oz));
     @ensures (\result == true) <==> ((contientCombienDe(oz) == \old(contientCombienDe(oz)) - 1) && (getNbObjets()== \old(getNbObjets()) -1)  && (getPoids() == (\old(getPoids()) - oz.getPoids())));
     @ensures (\result == false) <==> ((contientCombienDe(oz) == 0) && (getNbObjets() == \old(getNbObjets()))  && (getPoids() == \old(getPoids())));
     @ensures \old(contientCombienDe(oz) == 1) ==> !contient(oz);
     @ensures \old(contientCombienDe(oz) > 1) <==> contient(oz);
     @
     @*/
	public boolean retirer(ObjetZork oz)
	{
		if (objets.remove(oz))
		{
			nbObjets --;
			poids -= oz.getPoids();
		}
		return objets.remove(oz);
	}
    
     /**
     * Verifie si les joueurs ont les memes caracteristiques
     * @param o un joueur a comparer 
     * @return true si les deux joueurs sont identiques ; false sinon  
     */
	public boolean equals(Object o)
	{
		if (!(o instanceof Joueur))
			return false;
		Joueur p = (Joueur) o;
		if (((p.getPoidsMax() == this.getPoidsMax()) && (p.getNbObjets() == this.getNbObjets()) && (p.getNom() == this.getNom()) && (p.getPoids() == this.getPoids())))
			return p.objets.equals(this.objets);
		else
			return false;
	}
	
     /**
     * Renvoie hash code du joueur 
     *
     * @return Un entier qui donne le hash code de ce joueur 
     */
	public int hashCode ()
	{
		int hashObjets = 0;
		for(ObjetZork oz : objets)
			hashObjets += oz.hashCode();
		return nom.hashCode() + poidsMax + nbObjets + vuParTarta + hashObjets;
	}

    /**
     * Renvoie une description du joueur en mentionnant:
     * son nom
     * Le poids des objets qu'il le transporte
     * Le poids maximal qu'il peut  transporter
     * Le nombre de fois qu'il a ete repere
     * Le nombre d'objet qu'il transporte
     * La description longue de tous les objets
     *
     * @return Description de ce joueur 
     */
	public String toString()
	{
		String descObjets = "";
		for( int i=0; i< nbObjets; i++)
			descObjets += objets.get(i).descLongue()+"\n";
		return "Nom du joueur: " + getNom() + "\tCapacite : "+ getPoids() + "/" + getPoidsMax() + "\t Vous avez ete detecte :" + getNbVu() + "/3" + "\nVous avez : " + getNbObjets() + " objets\n" + descObjets;
	}
		  	
}	
