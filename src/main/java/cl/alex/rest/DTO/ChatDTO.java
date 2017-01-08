package cl.alex.rest.DTO;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;

import cl.alex.data.Chat;
import cl.alex.data.Mensaje;
import cl.alex.data.Usuario;

public class ChatDTO {
	
	private Long id;
	private List<NestedUsuarioDTO> emisor;
	private List<NestedUsuarioDTO> receptor;
	private List<NestedMensajeDTO> mensaje;
	
	public ChatDTO() {
		
	}
	
	public ChatDTO(final Chat entity) {
		if (entity != null) {
			this.id = entity.getId();
			Iterator<Usuario> iterEmisor = entity
					.getEmisor().iterator();
			while (iterEmisor.hasNext()) {
				Usuario element = iterEmisor
						.next();
				this.emisor.add(new NestedUsuarioDTO(element));
			}
			
			Iterator<Usuario> iterReceptor = entity
					.getReceptor().iterator();
			while (iterReceptor.hasNext()) {
				Usuario element = iterReceptor
						.next();
				this.receptor.add(new NestedUsuarioDTO(element));
			}
			
			Iterator<Mensaje> iterMensaje = entity
					.getMensaje().iterator();
			while (iterMensaje.hasNext()) {
				Mensaje element = iterMensaje
						.next();
				this.mensaje.add(new NestedMensajeDTO(element));
				
			}
		}
	}
	
	public Chat fromDTO(Chat entity, EntityManager em) {
		if (entity == null) {
			entity = new Chat();
		}
		
		Iterator<Usuario> iterEmisor = entity.getEmisor().iterator();
		while (iterEmisor.hasNext()) {
			boolean found = false;
			Usuario emisor = iterEmisor.next();
			Iterator<NestedUsuarioDTO> iterDtoEmisor = this.getEmisor().iterator();
			while (iterDtoEmisor.hasNext()) {
				NestedUsuarioDTO dtoEmisor = iterDtoEmisor.next();
				if (((Long) dtoEmisor.getId()).equals((Long) emisor.getId())) {
					found = true;
					break;
				}
			}
		}
		Iterator<NestedUsuarioDTO> iterDtoEmisor = this.getReceptor().iterator();
		while (iterDtoEmisor.hasNext()) {

			NestedUsuarioDTO dtoEmisor = iterDtoEmisor.next();
			
			Usuario e = new Usuario();
			e.setId(dtoEmisor.getId());
			e.setNombre(dtoEmisor.getNombre());
			e.setPassword(dtoEmisor.getPassword());
			e.setEmail(dtoEmisor.getEmail());
			
			entity.getEmisor().add(e);

		}
		
		Iterator<Usuario> iterReceptor = entity.getReceptor().iterator();
		while (iterReceptor.hasNext()) {
			boolean found = false;
			Usuario emisor = iterReceptor.next();
			Iterator<NestedUsuarioDTO> iterDtoReceptor = this.getReceptor().iterator();
			while (iterDtoReceptor.hasNext()) {
				NestedUsuarioDTO dtoReceptor = iterDtoReceptor.next();
				if (((Long) dtoReceptor.getId()).equals((Long) emisor.getId())) {
					found = true;
					break;
				}
			}
		}
		Iterator<NestedUsuarioDTO> iterDtoReceptor = this.getReceptor().iterator();
		while (iterDtoReceptor.hasNext()) {

			NestedUsuarioDTO dtoReceptor = iterDtoReceptor.next();
			
			Usuario r = new Usuario();
			r.setId(dtoReceptor.getId());
			r.setNombre(dtoReceptor.getNombre());
			r.setPassword(dtoReceptor.getPassword());
			r.setEmail(dtoReceptor.getEmail());
			
			entity.getReceptor().add(r);

		}
		
		Iterator<Mensaje> iterMensaje = entity.getMensaje().iterator();
		while (iterMensaje.hasNext()) {
			boolean found = false;
			Mensaje mensaje = iterMensaje.next();
			Iterator<NestedUsuarioDTO> iterDtoMensaje = this.getEmisor().iterator();
			while (iterDtoMensaje.hasNext()) {
				NestedUsuarioDTO dtoMensaje = iterDtoMensaje.next();
				if (((Long) dtoMensaje.getId()).equals((Long) mensaje.getId())) {
					found = true;
					break;
				}
			}
		}
		Iterator<NestedMensajeDTO> iterDtoMensaje = this.getMensaje().iterator();
		while (iterDtoMensaje.hasNext()) {

			NestedMensajeDTO dtoMensaje = iterDtoMensaje.next();
			
			Mensaje m = new Mensaje();
			m.setId(dtoMensaje.getId());
			m.setMensaje(dtoMensaje.getMensaje());
			
			entity.getMensaje().add(m);

		}

		entity = em.merge(entity);
		return entity;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<NestedUsuarioDTO> getEmisor() {
		return emisor;
	}

	public void setEmisor(List<NestedUsuarioDTO> emisor) {
		this.emisor = emisor;
	}

	public List<NestedUsuarioDTO> getReceptor() {
		return receptor;
	}

	public void setReceptor(List<NestedUsuarioDTO> receptor) {
		this.receptor = receptor;
	}

	public List<NestedMensajeDTO> getMensaje() {
		return mensaje;
	}

	public void setMensaje(List<NestedMensajeDTO> mensaje) {
		this.mensaje = mensaje;
	}

}
