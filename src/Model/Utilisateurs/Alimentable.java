package Model.Utilisateurs;

@FunctionalInterface
 interface Alimentable {
    void AlimenterCompte(Utilisateur utilisateur , double montant);
}
