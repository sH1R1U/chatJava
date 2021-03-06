package cl.alex.data;

import javax.persistence.Entity;
import javax.persistence.FetchType;

import java.io.Serializable;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.CascadeType;
import javax.persistence.Column;

@Entity
@Table(name = "Mensaje")
public class Mensaje implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@SequenceGenerator(name = "Mensaje_ID_GENERATOR", sequenceName = "SEQ_MENSAJE", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Mensaje_ID_GENERATOR")
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;

	@Column(length = 500, name = "mensaje")
	private String mensaje;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name="ID_CHAT", nullable=false, updatable=true)
	private Chat chat;

	public Long getId() {
		return this.id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	@Override
	public String toString() {
		String result = getClass().getSimpleName() + " ";
		if (id != null)
			result += "id: " + id;
		if (mensaje != null && !mensaje.trim().isEmpty())
			result += ", mensaje: " + mensaje;
		return result;
	}
}