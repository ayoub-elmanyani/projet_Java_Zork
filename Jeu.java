import java.util.*;
/**
 *  Classe principale du jeu "One Piece". <p>
 *
 *  One Piece est un jeu d'aventure tres rudimentaire avec une interface en mode
 *  texte inspire de Zork: les joueurs peuvent juste se deplacer parmi les differentes pieces et ramasser et jeter differents objets.
 *  </p> <p>
 *
 *  Pour jouer a ce jeu, creer une instance de cette classe et appeler sa
 *  methode "jouer". </p> <p>
 *
 *  Cette classe cree et initialise des instances de toutes les autres classes:
 *  elle cree toutes les pieces, cree l'analyseur syntaxique et demarre le jeu.
 *  Elle se charge aussi d'executer les commandes que lui renvoie l'analyseur
 *  syntaxique.</p>
 *
 * @author     Michael Kolling
 * @author     Marc Champesme (pour la traduction francaise)
 * @author     Harris Hamdani (gestion des nouvelles commandes)
 * @author     Ilyes Ben Yahia (creation des pieces du jeu)
 * @author     Ayoub Elmanyani (conditions de fin de partie)
 * @version    2.0
 * @since      March 2000
 * @since      Octobre 2018 (ajout des notions d'objets et nouvelles commandes)
 */

public class Jeu {
	private AnalyseurSyntaxique analyseurSyntaxique;
	private Piece pieceCourante;
	private Piece piecePrecedente; //Stocke la piece precedente
	private Joueur player;


	/**
	 *  Cree le jeu et initialise la carte du jeu (i.e. les pieces).
	 */
	public Jeu() {
        player = new Joueur();
		creerPieces();
		analyseurSyntaxique = new AnalyseurSyntaxique();
		
	}
	


	/**
	 *  Cree toutes les pieces et relie leurs sorties les unes aux autres.
	 */
	public void creerPieces() {
		Piece tougato;
		Piece confiture;
		Piece cacao;
		Piece bonbon;
		Piece biscuit;
		Piece glace;
		Piece gateau;
		Piece croquant;
		Piece fromage;
		Piece bateau;
		
		// creation des pieces initialiser avec des objets
		ArrayList<ObjetZork> oz0 = new ArrayList<ObjetZork>(10);
		ObjetZork oz01= new ObjetZork("table");
		ObjetZork oz02= new ObjetZork("Sabre",10);
		oz0.add(oz01);
		oz0.add(oz02);
		tougato = new Piece("l'ile Tougato",2,oz0);

		ArrayList<ObjetZork> oz1 = new ArrayList<ObjetZork>(10);
		ObjetZork oz11= new ObjetZork("cerisier");
		oz1.add(oz11);
		confiture = new Piece("l'ile Confiture",1,oz1);

		ArrayList<ObjetZork> oz2 = new ArrayList<ObjetZork>(10);
		ObjetZork oz21= new ObjetZork("Chocolat",10);
		oz2.add(oz21);
		cacao = new Piece("l'ile Cacao",1,oz2);

		ArrayList<ObjetZork> oz3 = new ArrayList<ObjetZork>(10);
		ObjetZork oz31= new ObjetZork("echelle",30);
		ObjetZork oz32= new ObjetZork("Bonbon",5);
		ObjetZork oz33= new ObjetZork("Miroir");
		oz3.add(oz31);
		oz3.add(oz32);
		oz3.add(oz33);
		bonbon = new Piece("l'ile de Bonbon",3,oz3);
	
		biscuit = new Piece("l'ile de Biscuit");

		ArrayList<ObjetZork> oz4 = new ArrayList<ObjetZork>(10);
		ObjetZork oz41= new ObjetZork("refrigerateur");
		ObjetZork oz42= new ObjetZork("Fraise",10);
		ObjetZork oz43= new ObjetZork("Glace",15);
		oz4.add(oz41);
		oz4.add(oz42);
		oz4.add(oz43);
		glace = new Piece("l'ile Glace",3,oz4);

		ArrayList<ObjetZork> oz5 = new ArrayList<ObjetZork>(10);
		ObjetZork oz51= new ObjetZork("four");
		ObjetZork oz52= new ObjetZork("Gateau",40);
		oz5.add(oz51);
		oz5.add(oz52);
		gateau = new Piece("l'ile Gateau",2,oz5);

		ArrayList<ObjetZork> oz6 = new ArrayList<ObjetZork>(10);
		ObjetZork oz61= new ObjetZork("Viande",25);
		oz6.add(oz61);	
		croquant = new Piece("l'ile Croquant",1,oz6);

		ArrayList<ObjetZork> oz7 = new ArrayList<ObjetZork>(10);
		ObjetZork oz71= new ObjetZork("vache");
		ObjetZork oz72= new ObjetZork("Camembert",10);
		oz7.add(oz71);
		oz7.add(oz72);
		fromage = new Piece("l'ile Fromage",2,oz7);

		ArrayList<ObjetZork> oz8 = new ArrayList<ObjetZork>(10);
		ObjetZork oz81= new ObjetZork("coffre");
		ObjetZork oz82= new ObjetZork("miroir");
		oz8.add(oz81);
		oz8.add(oz82);
		bateau = new Piece("Bateau",2,oz8);

		// placement des tours de survillance
		ObjetZork tarta = new ObjetZork ("tarta");
		croquant.ajouter(tarta);
		bonbon.ajouter(tarta);
		fromage.ajouter(tarta);

		// placement du Poneglyphe
		ObjetZork Poneglyphe = new ObjetZork("Poneglyphe",50);
		tougato.ajouter(Poneglyphe);
 
		// initialise les sorties des pieces
		tougato.setSorties(cacao, null, fromage, null);
		confiture.setSorties(null, null, bateau, cacao);
		cacao.setSorties(null, confiture, tougato, bonbon);
		bonbon.setSorties(null, cacao, null, null);
		biscuit.setSorties(null, null, gateau, glace);
		glace.setSorties(null, biscuit, croquant, null);
		gateau.setSorties(biscuit, fromage, null, croquant);
		croquant.setSorties(glace, gateau, null, null);
		fromage.setSorties(tougato, null, null, gateau);
		bateau.setSorties(confiture, null , null, null);
		
		// le jeu commence sur l'ile Glace
		pieceCourante = glace;
		piecePrecedente = null;
	}
   
	/**
	 *  Pour lancer le jeu. Boucle jusqu'a la fin du jeu.
	 */
	public void jouer() {
		afficherMsgBienvennue();
        ObjetZork Poneglyphe = new ObjetZork("Poneglyphe",50); 
		// Entree dans la boucle principale du jeu. Cette boucle lit
		// et execute les commandes entrees par l'utilisateur, jusqu'a
		// ce que la commande choisie soit la commande "quitter"
		boolean termine = false;
		while (!termine) 
		{
			Commande commande = analyseurSyntaxique.getCommande();
			termine = traiterCommande(commande);
           	if (player.getNbVu() == 3) // On verifie apres chaque commande si le joueur a ete vu 3 fois. Si c'est le cas, il a perdu. Sinon le jeu continue
            {
               	System.out.println("Vous avez ete repere par Big Mom: elle vous a capture.Vous avez perdu");
				termine = true;
                System.out.println(player);
			}
			if (pieceCourante.descriptionCourte().equals("Bateau") && player.contient(Poneglyphe)) // On verifie si le joueur est en possession du poneglyphe et est sur le bateau 
			{
				System.out.println("*** Felicitations vous avez gagne ***"); // Si c'est le cas, il a gagne. Sinon on continue le jeu
                System.out.println(player);
				termine = true;
			}
		}
		System.out.println("Merci d'avoir jouer.  Au revoir.");
	}


	/**
	 *  Affiche le message d'accueil pour le joueur.
	 */
	public void afficherMsgBienvennue() {
		System.out.println();
		System.out.println("Bienvenue dans le monde de One Piece, le celebre manga de pirate!");
		System.out.println("Vous incarnez Luffy et son equipage");
        System.out.println("Vous recherchez les poneglyphes,ces indicateurs de l'emplacement du tresor ultime : le One Piece");
        System.out.println("Vous etes sur le territoire de Big Mom, un des 4 plus puissants pirates du monde a la recherche de l'un d'entre eux");
        System.out.println("Vous etes separes du groupe, tous presents sur le bateau, pret a partir au sud de l'ile Confiture");
        System.out.println("Votre but sera donc de ramener le Poneglyphe sur le bateau");
        System.out.println("Mais attention : Big Mom a installe dans certains coins des tours de surveillance, appelees Tarta");
        System.out.println("Si vous passez 3 fois devant l'un d'entre eux, Big Mom vous trouvera et vous tuera");
        System.out.println("Bonne chance et (bon courage)");
		System.out.println("Tapez 'aide' si vous avez besoin d'aide.");
		System.out.println();
		System.out.println(pieceCourante.descriptionLongue());
	}


	/**
	 *  Execute la commande specifiee. Si cette commande termine le jeu, la valeur
	 *  true est renvoyee, sinon false est renvoyee
	 *
	 * @param  commande  La commande a executer
	 * @return           true si cette commande termine le jeu ; false sinon.
	 */
	public boolean traiterCommande(Commande commande) {
		if (commande.estInconnue()) {
			System.out.println("Je ne comprends pas ce que vous voulez...");
			return false;
		}
		String motCommande = commande.getMotCommande();
		if (motCommande.equals("aide")) 
		{
			afficherAide();
		} 
		else if (motCommande.equals("aller")) 
		{
			deplacerVersAutrePiece(commande);
		} 
		else if (motCommande.equals("quitter")) 
		{
			if (commande.aSecondMot()) 
			{
				System.out.println("Quitter quoi ?");
			} 
			else 
			{
				return true;
			}
		} 
		else if (motCommande.equals("inventaire")) //On verifie que la commande est correctement utilise (sans deuxieme mot)
		{
			if(commande.aSecondMot()) //S'il y a un deuxieme mot, on affiche un message d'erreur
			{
				System.out.println("Faire quoi dans ton inventaire ?");
			}
			else
			{
				inventaire(); //Sinon la commande est realisee
			}
		}
		else if (motCommande.equals("retour")) //On verifie que la commande est correctement utilise (sans deuxieme mot)
		{
			if(commande.aSecondMot()) //S'il y a un deuxieme mot, on affiche un message d'erreur
			{
				System.out.println("Retourner où ?");
			}
			else
			{
				retourPiece(); //Sinon, on appelle la methode pour effectuer la commande
			}
		}
        else if (motCommande.equals("ramasser")) //On verifie si les conditions de ramasser sont satisfaites(a savoir un deuxieme mot)
		{
			if(commande.aSecondMot()) //Si c'est le cas, on appelle la methode qui correspond
			{
                ramasser(commande.getSecondMot());
            }
			else //Sinon, on affiche un message d'erreur
			{
				System.out.println("Que ramasser ?");
			}
            System.out.println(pieceCourante.descriptionLongue());
         }
		 else if (motCommande.equals("jeter")) //On verifie si les conditions de ramasser sont satisfaites(a savoir un deuxieme mot)
		{
			if(commande.aSecondMot())
			{
                jeter(commande.getSecondMot()); //Si c'est le cas, on appelle la methode qui correspond
            }
            else
            {
                System.out.println("Jeter quoi ?"); //Sinon, on affiche un message d'erreur
            }   
         System.out.println(pieceCourante.descriptionLongue());
        }
        else if (motCommande.equals("quete")) //On verifie que la commande est correctement utilise (sans deuxieme mot)
		{
			if(commande.aSecondMot())
			{
                System.out.println("Que faire de votre quete ?"); //S'il y a un deuxieme mot, on affiche un message d'erreur
           }
            else
            {
                quete(); //Sinon, on appelle la methode effectuant la commande
            }   
         System.out.println(pieceCourante.descriptionLongue());
        }
        return false;
	}


	// implementations des commandes utilisateur:

	/**
	 *  Affichage de l'aide. Affiche notament la liste des commandes utilisables.
	 */
	public void afficherAide() {
		System.out.println("Vous etes sur le territoire de Big Mom a la recherche d'un poneglyphe");
		System.out.println("En evitant de croiser ses tours de surveillance (tarta)");
        System.out.println("Pour verifier votre progression dans le jeu, tapez 'quete'");
        System.out.println("Pour verifier les objets en votre possession, tapez 'inventaire'");
        System.out.println("Pour aller dans une piece, tapez 'aller direction' ou direction peut etre nord,sud,est ou ouest");
        System.out.println("Pour retourner a la piece precedente, tapez 'retour' ");
        System.out.println("Pour ramasser un objet, tapez 'ramasser objet' ou objet est le nom d'un objet present dans la piece courante");
        System.out.println("Pour jeter un objet en votre possession, tapez 'jeter objet' ou objet est le nom d'un objet present dans votre inventaire");
        System.out.println("Pour quitter le jeu, tapez quitter");
		System.out.println();
		System.out.println("Les commandes reconnues sont:");
		analyseurSyntaxique.afficherToutesLesCommandes();
	}


	/**
	 *  Tente d'aller dans la direction specifiee par la commande. Si la piece
	 *  courante possede une sortie dans cette direction, la piece correspondant a
	 *  cette sortie devient la piece courante, dans les autres cas affiche un
	 *  message d'erreur.
	 *
	 * @param  commande  Commande dont le second mot specifie la direction a suivre
	 */
	public void deplacerVersAutrePiece(Commande commande) {
		if (!commande.aSecondMot()) {
			// si la commande ne contient pas de second mot, nous ne
			// savons pas ou aller.. 
			System.out.println("Aller ou ?");
			return;
		}

		String direction = commande.getSecondMot();

		// Tentative d'aller dans la direction indiquee.
		Piece pieceSuivante = pieceCourante.pieceSuivante(direction);

		if (pieceSuivante == null) 
		{
			System.out.println("Il n'y a pas de porte dans cette direction!");
		} 
		else 
		{
            ObjetZork tarta = new ObjetZork ("tarta");
			piecePrecedente = pieceCourante;
			pieceCourante = pieceSuivante;
			System.out.println(pieceCourante.descriptionLongue());
			if(pieceCourante.contient(tarta)) //On verifie si le joueur se deplace vers une salle avec une tour de controle
                		player.vuParTarta(); //Si c'est le cas, on incremente le nombre de fois que le joueur a ete vu par eux
		}
	}
    /**
    *Retourne a la piece precedente si cela est possible.
    * Si cela n'est pas possible(debut du jeu),un message d'erreur s'affichera.
    */
	public void retourPiece()
	{
		if(piecePrecedente == null) // On teste si la piece precedente n'existe pas (au debut du jeu)
		{
			System.out.println("Impossible"); //On affiche un message d'erreur si c'est le cas
		}
		else
		{
            ObjetZork tarta = new ObjetZork ("tarta");
			Piece tmp = pieceCourante; //On fait donc revenir le joueur dans la derniere salle qu'il a visitee
			pieceCourante = piecePrecedente;
			piecePrecedente = tmp;
			System.out.println(pieceCourante.descriptionLongue());
			if(pieceCourante.contient(tarta)) // On verifie si le joueur se deplace dans une salle sous surveillance
				player.vuParTarta(); //Si oui, on incremente le compteur 
		}
	}
    /**
    * Affiche les objets que le joueur a en sa possession
    */
    public void inventaire()
    {
				if(player.getNbObjets() == 0) // S'il n'a pas d'objets, on affiche le message suivant
                    System.out.println("Tu n'as aucun objet dans ton inventaire");
                else
                {
                    Iterator <ObjetZork> i = player.getObjets().iterator(); //Sinon on affiche un par un ses objets 
                    while (i.hasNext())
                        System.out.println(i.next().descLongue());
                 
                }
				System.out.println("Poids :" + " " + player.getPoids() + "/" + player.getPoidsMax()); //On affiche en plus le poids de ses objets et la capacite maximale
	}
    /**
    * Tente de ramasser l'objet dont le nom est en argument, pour que le joueur puisse l'avoir dans son inventaire.
    * Si l'objet est non transportable, fait depasser la capacite maximale ou n'est pas present dans la piece, 
    * un message d'erreur correspond au cas s'affichera.
    * @param Objet nom de l'objet voulant etre ramasse
    */
    public void ramasser(String Objet)
    {
                boolean trouve = false; // Booleen testant si l'objet est present ou non dans la piece
                for(int i = 0;i < pieceCourante.getNbObjets() && !trouve;i++) //On parcourt les objets a sa recherche 
                {
                    if(pieceCourante.getObjets().get(i).getDesc().equals(Objet)) 
                    {
                        trouve = true; //Si on trouve un nom identique, on tente de l'ajouter 
                        ObjetZork oz = pieceCourante.getObjets().get(i);
                        if (player.ajouter(oz)) // Si ajouter donne vrai, on peut l'ajouter a l'inventaire, apres avoir enleve celui-ci de la piece
                        {
                          
                            pieceCourante.retirer(oz);
                            System.out.println("Objet("+ Objet + ")ramasse avec succes");
                           
                        }
                        else //Sinon, on affiche un message d'erreur correspondant
                        {
                            if (oz.estTransp())
                                System.out.println("Objet faisant depasse la limite de poids autorise des objets du joueur");
                            else
                                System.out.println("Objet non transportable");
                        }
                    }
                       
                    
                }
                if (!trouve) // Si trouve est toujours faux, l'objet n'est pas dans la piece. On affiche donc un message d'erreur
                    System.out.println("Objet non present dans cette piece");
               
    }
    /**
    * Tente de jeter l'objet dont le nom est en argument, pour que le joueur puisse ne plus l'avoir dans son inventaire.
    * Si l'objet n'est pas present dans son inventaire,
    * un message d'erreur correspond au cas s'affichera.
    * @param Objet nom de l'objet voulant etre jete
    */
    public void jeter(String Objet)
    {
        boolean trouve = false;
        
        for(int i = 0;i < player.getNbObjets()&& !trouve ;i++) //On parcourt les objets de l'inventaire pour essayer de trouver l'objet en question
        {
          
            if(player.getObjets().get(i).getDesc().equals(Objet))
            {
                ObjetZork oz = player.getObjets().get(i);
                pieceCourante.ajouter(oz);
                player.retirer(oz); 
                System.out.println("Objet bien enleve de votre inventaire");
                trouve = true;
            }
         }
         if (!trouve)
         {
             System.out.println("Objet non present dans votre inventaire");
         }
    }
    /**
    *Affiche l'objectif actuel du joueur et le nombre de fois que le joueur a ete aperçu par les Tarta
    */
    public void quete()
    {
         ObjetZork m = new ObjetZork("Poneglyphe",50);
         if (player.contient(m)) //Selon si le joueur est en possession du Poneglyphe, on affichera un objectif different
                System.out.println("Le Poneglypne etant maintenant en votre possession, courez vers votre bateau sans vous faire reperer");
         else
               System.out.println("Recherchez le poneglyphe(avec comme nom d'objet Poneglyphe) sans vous faire reperer avant de partir avec votre bateau");
         System.out.println("Nombre de detection par les Tarta :" + player.getNbVu() + "/3");
    }
            
}

