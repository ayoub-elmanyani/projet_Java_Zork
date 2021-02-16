/**
* Un objet dans un jeu d'aventure. <p>
*
* Cette classe fait partie du jeu d'aventure en mode texte
* One Piece, base sur le logiciel Zork . </p> <p>
*
* Un "ObjetZork" represente un objet dans un jeu d'aventure. 
* Il est caracterise dans son nom, son poids et est transportable ou non. </p>
* @author Ayoub Elmanyani
* @version 1.0
* @since Octobre 2018
*/
public class ObjetZork
{
    /*@
    @invariant getDesc() != null;
    @invariant getPoids() >= 0;
    @*/
	private String  desc;
	private int poids;
	private boolean transp;
    /**
    *Initialise un objet avec la chaine de caractere et le poids correspondant. La chaine doit être non null et le poids positif. Il est transportable par le joueur
    *@param d Description de l'objet
    *@param p Poids de l'objet
    **/
    /*@
    @requires d != null;
    @requires p >= 0;
    @ensures getDesc().equals(d);
    @ensures p == getPoids();
    @ensures estTransp() == true;
    @*/
	public ObjetZork(String d,int p)
	{
		this.desc = d;
		this.poids = p;
		this.transp = true;
	}
    /**
    * Initialise un objet avec la chaine de caractere correspondant.La chaine doit être non null. L'objet est alors non transportable par le joueur
    *@param d Description de l'objet
    **/
    /*@
    @requires d != null;
    @ensures getDesc().equals(d);
    @ensures estTransp() == false;
    @ensures getPoids() == Integer.MAX_VALUE;
    @*/
	public ObjetZork(String d)
	{
		this.desc = d;
		this.poids = Integer.MAX_VALUE;
		this.transp = false;
	}
    /**
    * Renvoie le poids d'un objet 
    * @return   Le poids de l'objet
    * 
    **/
    //@pure
	public int getPoids()
	{
		return (this.poids);
	}
    /**
    * Renvoie la  description d'un objet 
    * @return   La description de l'objet
    * 
    **/
	//@pure
	public String getDesc()
	{
		return (this.desc);
	}
    /**
    * Renvoie la transportabilite d'un objet  
    * @return   true si l'objet est transportable;false sinon
    * 
    **/
    //@pure
	public boolean estTransp()
	{
		return (this.transp);
	}
    /**
    * Affiche la description longue, constituee de la description, du poids et de la transportabilite d'un objet de la forme:<pre>
	*  Nom : Livre Poids: 25 Transportable: Oui</pre>
    **/
    //@pure
	public String descLongue()
	{
		if(estTransp())
			return ("Nom:"+ " " + this.getDesc() +" "+ "Poids:" + " " + this.getPoids()+ " " + "Transportable:" + " " + "Oui");	
		
		return ("Nom:"+ " " +this.getDesc() + " " + "Transportable:"+ " " +"Non");
	}
    /**
    * Teste si 2 objets identiques 
    * @param o un objet 
    * @return  true si les 2 objets sont identiques ; false sinon 
    **/
	public boolean equals(Object o)
	{
		if(!(o instanceof ObjetZork))
			return false;
		ObjetZork oz = (ObjetZork) o;
		return (this.getPoids()== oz.getPoids())&&(this.getDesc().equals(oz.getDesc()))&&(this.estTransp() == oz.estTransp());	
	}
	/**
	*Renvoie le hash code pour un objet
	*@return	Hash code de l'objet
	*/
	public int hashCode()
	{
		if(estTransp()) return (getDesc().hashCode() + getPoids());
		return getDesc().hashCode();
	}
    
}
