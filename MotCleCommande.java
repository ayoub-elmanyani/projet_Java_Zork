/**
 *  <p>
 *
 *  Cette classe fait partie du jeu One Piece, un jeu d'aventure simple en mode
 *  texte base sur Zork.</p> <p>
 *
 *  Classe repertoriant l'ensemble des mots-cle utilisables comme commande dans
 *  le jeu. Elle est utilisee pour verifier la validite des commandes de
 *  l'utilisateur.
 *
 * @author     Michael Kolling
 * @author     Marc Champesme (pour la traduction francaise)
 * @version    1.0
 * @since      July 1999
 */

public class MotCleCommande {
	/**
	 *  Un tableau constant contenant tous les mots-cle valides comme commandes.
	 */
	private final static String commandesValides[] = {"aller", "quitter", "aide","retour","ramasser","jeter","inventaire","quete"}; //ajout de 5 commandes


	/**
	 *  Initialise la liste des mots-cle utilisables comme commande.
	 */
	public MotCleCommande() { }


	/**
	 *  Teste si la chaine de caracteres specifiee est un mot-cle de commande
	 *  valide. 
	 *
	 * @param  aString  Chaine de caracteres a tester
	 * @return          true si le parametre est une commande valide, false sinon
	 */
	public boolean estCommande(String aString) {
		for (int i = 0; i < commandesValides.length; i++) {
			if (commandesValides[i].equals(aString)) {
				return true;
			}
		}
		return false;
	}


	/**
	 *  Affiche toutes les commandes (i.e. les mots-cle) valides.
	 */
	public void afficherToutesLesCommandes() {
		for (int i = 0; i < commandesValides.length; i++) {
			System.out.print(commandesValides[i] + "  ");
		}
		System.out.println();
	}
}

