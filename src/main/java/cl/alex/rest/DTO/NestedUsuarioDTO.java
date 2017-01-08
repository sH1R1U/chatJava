package cl.alex.rest.DTO;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import cl.alex.data.Usuario;

public class NestedUsuarioDTO {
	
	private Long id;
	private String nombre;
	private String password;
	private String email;
	
	public NestedUsuarioDTO() {
		
	}
	
	public NestedUsuarioDTO(final Usuario entity) {
		if (entity != null) {
			this.id = entity.getId();
			this.nombre = entity.getNombre();
			this.password = entity.getPassword();
			this.email = entity.getEmail();
		}
	}
	
	public Usuario fromDTO(Usuario entity, EntityManager em) {
		if (entity == null) {
			entity = new Usuario();
		}
		if (((Long) this.id) != null) {
			TypedQuery<Usuario> findByIdQuery = em.createQuery(
					"SELECT DISTINCT u FROM Usuario u WHERE u.id = :entityId",
					Usuario.class);
			findByIdQuery.setParameter("entityId", this.id);
			try {
				entity = findByIdQuery.getSingleResult();
			} catch (javax.persistence.NoResultException nre) {
				entity = null;
			}
			return entity;
		}
		entity.setNombre(this.nombre);
		entity.setPassword(this.password);
		entity.setEmail(this.email);
		
		entity = em.merge(entity);
		return entity;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}