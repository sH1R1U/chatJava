package cl.alex.data;

import javax.persistence.Entity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.CascadeType;
import javax.persistence.Column;

@Entity
@Table(name = "Chat")
public class Chat implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@SequenceGenerator(name = "Chat_ID_GENERATOR", sequenceName = "SEQ_CHAT", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Usuario_ID_GENERATOR")
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	@OneToMany(mappedBy="chat", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Usuario> emisor = new ArrayList<Usuario>();
	@OneToMany(mappedBy="chat", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Usuario> receptor = new ArrayList<Usuario>();
	@OneToMany(mappedBy="chat", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Mensaje> mensaje = new ArrayList<Mensaje>();

	public Long getId() {
		return this.id;
	}

	public void setId(final Long id) {
		this.id = id;
	}
	
	public List<Usuario> getEmisor() {
		return this.emisor;
	}

	public void setEmisor(final List<Usuario> emisor) {
		this.emisor = emisor;
	}
	
	public List<Usuario> getReceptor() {
		return this.receptor;
	}

	public void setReceptor(final List<Usuario> receptor) {
		this.receptor = receptor;
	}

	public List<Mensaje> getMensaje() {
		return mensaje;
	}

	public void setMensaje(List<Mensaje> mensaje) {
		this.mensaje = mensaje;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
}