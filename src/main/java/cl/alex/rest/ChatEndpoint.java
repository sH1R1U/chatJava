package cl.alex.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.Response.Status;

import cl.alex.data.Chat;
import cl.alex.rest.DTO.ChatDTO;

@Stateless
@Path("/mensaje")

public class ChatEndpoint {
	
	@PersistenceContext(unitName = "sit-web-persistence-unit")
	public EntityManager em;

	@POST
	@Consumes("application/json")
	public Response create(@Context HttpServletRequest request,ChatDTO dto) {
		
		Chat entity = dto.fromDTO(null, em);
		em.persist(entity);
		
		return Response.created(UriBuilder.fromResource(UsuarioEndpoint.class).path(String.valueOf(entity.getId())).build()).build();
	}

	@DELETE
	@Path("/{id:[0-9][0-9]*}")
	public Response deleteById(
			@Context HttpServletRequest request,
			@PathParam("id") long id) {
		
		
		
		Chat entity = em.find(Chat.class, id);
		if (entity == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		em.remove(entity);
		
		return Response.noContent().build();
	}

	@GET
	@Path("/{id:[0-9][0-9]*}")
	@Produces("application/json")
	public Response findById(@Context HttpServletRequest request,@PathParam("id") long id) {
		
		TypedQuery<Chat> findByIdQuery = em
				.createQuery(
						"SELECT DISTINCT c FROM Chat c WHERE c.id = :entityId ORDER BY c.id",
						Chat.class);
		findByIdQuery.setParameter("entityId", id);
		Chat entity;
		try {
			entity = findByIdQuery.getSingleResult();
		} catch (NoResultException nre) {
			entity = null;
		}
		if (entity == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		ChatDTO dto = new ChatDTO(entity);
		
		return Response.ok(dto).build();
	}

	@GET
	@Produces("application/json")
	public List<ChatDTO> listAll(
			@Context HttpServletRequest request,
			@QueryParam("start") Integer startPosition,
			@QueryParam("max") Integer maxResult) {
		
		TypedQuery<Chat> findAllQuery = em
				.createQuery(
						"SELECT DISTINCT c FROM Chat c ORDER BY c.id",
						Chat.class);
		if (startPosition != null) {
			findAllQuery.setFirstResult(startPosition);
		}
		if (maxResult != null) {
			findAllQuery.setMaxResults(maxResult);
		}
		final List<Chat> searchResults = findAllQuery.getResultList();
		final List<ChatDTO> results = new ArrayList<ChatDTO>();
		for (Chat searchResult : searchResults) {
			ChatDTO dto = new ChatDTO(searchResult);
			results.add(dto);
		}
		
		return results;
	}

	@PUT
	@Path("/{id:[0-9][0-9]*}")
	@Consumes("application/json")
	public Response update(
			@Context HttpServletRequest request,
			@PathParam("id") long id, MensajeDTO dto) {
		
		if (dto == null) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		if (id != dto.getId()) {
			return Response.status(Status.CONFLICT).entity(dto).build();
		}
		Chat entity = em.find(Chat.class, id);
		if (entity == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		entity = dto.fromDTO(entity, em);
		try {
			entity = em.merge(entity);
		} catch (OptimisticLockException e) {
			return Response.status(Status.CONFLICT).entity(e.getEntity())
					.build();
		}
		
		return Response.noContent().build();
	}

}
