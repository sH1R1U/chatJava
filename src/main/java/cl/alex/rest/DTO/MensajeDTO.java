package cl.alex.rest.DTO;

import javax.persistence.EntityManager;

import cl.alex.data.Mensaje;
import cl.alex.data.Usuario;
public class MensajeDTO {
	
	private Long id;
	private String mensaje;
	
	public MensajeDTO() {
		
	}
	
	public MensajeDTO(final Mensaje entity) {
		if (entity != null) {
			this.id = entity.getId();
			this.mensaje = entity.getMensaje();
		}
	}
	
	public Mensaje fromDTO(Mensaje entity, EntityManager em) {
		if (entity == null) {
			entity = new Mensaje();
		}
		entity.setMensaje(this.mensaje);
		entity = em.merge(entity);
		return entity;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

}
