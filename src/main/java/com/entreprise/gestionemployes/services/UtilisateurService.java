package com.entreprise.gestionemployes.services;
import com.entreprise.gestionemployes.entities.Utilisateur;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;


@Stateless
public class UtilisateurService {
    @PersistenceContext(unitName = "gestionPU")
    private EntityManager em;

    public void inscrire(Utilisateur utilisateur) {
        em.persist(utilisateur);
    }

    public Utilisateur trouverParEmailEtMotDePasse(String email, String motDePasse) {
        try {
            return em.createQuery(
                            "SELECT u FROM Utilisateur u WHERE u.email = :email AND u.motDePasse = :mdp",
                            Utilisateur.class
                    )
                    .setParameter("email", email)
                    .setParameter("mdp", motDePasse)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
