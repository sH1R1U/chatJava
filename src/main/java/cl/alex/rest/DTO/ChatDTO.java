package cl.alex.rest.DTO;

import cl.alex.data.Chat;
import cl.alex.data.Mensaje;
import cl.alex.data.Usuario;

public class ChatDTO {
	
	private Long id;
	private Usuario usuario1;
	private Usuario usuario2;
	private Mensaje mensaje;
	
	public ChatDTO() {
		
	}
	
	public ChatDTO(final Chat entity) {
		if (entity != null) {
			this.id = entity.getId();
		}
	}

}
