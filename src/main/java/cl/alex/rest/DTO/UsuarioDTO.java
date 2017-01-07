package cl.alex.rest.DTO;

import javax.persistence.EntityManager;

import cl.alex.data.Usuario;

public class UsuarioDTO {
	
	private Long id;
	private String nombre;
	private String password;
	private String email;
	
	public UsuarioDTO() {
		
	}
	
	public UsuarioDTO(final Usuario entity) {
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
