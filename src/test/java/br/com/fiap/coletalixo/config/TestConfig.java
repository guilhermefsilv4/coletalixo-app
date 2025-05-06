package br.com.fiap.coletalixo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

/**
 * Configuração para os testes.
 */
@Configuration
@Profile("test")
public class TestConfig {

    /**
     * Bean que indica se estamos em modo de teste/mock.
     * @return true para testes automatizados
     */
    @Bean
    @Primary
    public Boolean testMode() {
        return true;
    }
} 