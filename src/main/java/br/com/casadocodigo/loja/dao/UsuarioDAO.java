package br.com.casadocodigo.loja.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Repository;

import br.com.casadocodigo.loja.modelo.Usuario;

@Repository
public class UsuarioDAO implements UserDetailsService {
	
	@PersistenceContext
	private EntityManager manager;
	
	
	public Usuario loadUserByUsername(String email) {
		
		Usuario usuario = manager.createQuery("SELECT u FROM Usuario u WHERE email = :email", Usuario.class)
					.setParameter("email", email).getSingleResult();
		
//		if(usuarios.isEmpty()) {
//			throw new UsernameNotFoundException("Usuário " + email + " não encontrado");
//		}
		
		return usuario;
	}
	
	public void gravar(Usuario usuario) {
		manager.persist(usuario);
	}

}
