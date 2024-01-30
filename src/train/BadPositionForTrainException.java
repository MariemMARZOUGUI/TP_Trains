package train;

// La classe BadPositionForTrainException qui étend la classe Exception
public class BadPositionForTrainException extends Exception {
    // Déclaration d'un identifiant unique pour la sérialisation de la classe
    private static final long serialVersionUID = 1L;
    
    // Constructeur de la classe prenant un argument de type String
    public BadPositionForTrainException(String name){
        // Appel du constructeur de la classe mère (Exception) avec le message d'erreur
        super(name);
    }
    
    // Cette méthode retourne le message d'erreur associé à l'exception
    public String getMessage(){
        // Appel de la méthode getMessage() de la classe mère (Exception)
        return super.getMessage();
    }
}
