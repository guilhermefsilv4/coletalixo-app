package br.com.fiap.coletalixo.config.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

interface IAuthenticationFacade {
    Authentication getAuthentication();
}

@Component
public class AuthenticationData implements IAuthenticationFacade {

    @Override
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public String getEmail() {
        return getAuthentication().getName();
    }

    public boolean isAdmin() {
        return getAuthentication().getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));
    }
}