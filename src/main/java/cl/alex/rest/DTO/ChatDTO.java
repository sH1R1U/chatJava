package cl.alex.rest.DTO;

import java.util.Iterator;
import java.util.List;

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
