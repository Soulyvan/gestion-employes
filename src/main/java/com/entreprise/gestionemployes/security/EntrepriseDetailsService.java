package com.entreprise.gestionemployes.security;

import com.entreprise.gestionemployes.entities.Entreprise;
import com.entreprise.gestionemployes.repositories.EntrepriseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class EntrepriseDetailsService implements UserDetailsService {
    private final EntrepriseRepository entrepriseRepository;

    @Autowired
    public EntrepriseDetailsService(EntrepriseRepository entrepriseRepository) {
        this.entrepriseRepository = entrepriseRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Entreprise entreprise = entrepriseRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Entreprise non trouvée: " + username));

        return new User(
                entreprise.getUsername(),
                entreprise.getPassword(),
                entreprise.isActive(),
                true,
                true,
                true,
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
        );
    }
}
