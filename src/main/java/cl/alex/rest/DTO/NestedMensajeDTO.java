package cl.alex.rest.DTO;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import cl.alex.data.Mensaje;

public class NestedMensajeDTO {
	
	private Long id;
	private String mensaje;
	
	public NestedMensajeDTO() {
		
	}
	
	public NestedMensajeDTO(final Mensaje entity) {
		if (entity != null) {
			this.id = entity.getId();
			this.mensaje = entity.getMensaje();
		}
	}
	
	public Mensaje fromDTO(Mensaje entity, EntityManager em) {
		if (entity == null) {
			entity = new Mensaje();
		}
		if (((Long) this.id) != null) {
			TypedQuery<Mensaje> findByIdQuery = em.createQuery(
					"SELECT DISTINCT m FROM Mensaje m WHERE m.id = :entityId",
					Mensaje.class);
			findByIdQuery.setParameter("entityId", this.id);
			try {
				entity = findByIdQuery.getSingleResult();
			} catch (javax.persistence.NoResultException nre) {
				entity = null;
			}
			return entity;
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
