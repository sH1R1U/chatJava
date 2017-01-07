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
import javax.ws.rs.core.Response.Status;

import cl.alex.data.Mensaje;
import cl.alex.rest.DTO.MensajeDTO;

import javax.ws.rs.core.UriBuilder;

@Stateless
@Path("/mensaje")
public class MensajeEndpoint {
	
	@PersistenceContext(unitName = "sit-web-persistence-unit")
	public EntityManager em;

	@POST
	@Consumes("application/json")
	public Response create(@Context HttpServletRequest request,MensajeDTO dto) {
		
		Mensaje entity = dto.fromDTO(null, em);
		em.persist(entity);
		
		return Response.created(UriBuilder.fromResource(UsuarioEndpoint.class).path(String.valueOf(entity.getId())).build()).build();
	}

	@DELETE
	@Path("/{id:[0-9][0-9]*}")
	public Response deleteById(
			@Context HttpServletRequest request,
			@PathParam("id") long id) {
		
		
		
		Mensaje entity = em.find(Mensaje.class, id);
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
		
		TypedQuery<Mensaje> findByIdQuery = em
				.createQuery(
						"SELECT DISTINCT u FROM Mensaje u WHERE u.id = :entityId ORDER BY u.id",
						Mensaje.class);
		findByIdQuery.setParameter("entityId", id);
		Mensaje entity;
		try {
			entity = findByIdQuery.getSingleResult();
		} catch (NoResultException nre) {
			entity = null;
		}
		if (entity == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		MensajeDTO dto = new MensajeDTO(entity);
		
		return Response.ok(dto).build();
	}

	@GET
	@Produces("application/json")
	public List<MensajeDTO> listAll(
			@Context HttpServletRequest request,
			@QueryParam("start") Integer startPosition,
			@QueryParam("max") Integer maxResult) {
		
		TypedQuery<Mensaje> findAllQuery = em
				.createQuery(
						"SELECT DISTINCT u FROM Mensaje u ORDER BY u.id",
						Mensaje.class);
		if (startPosition != null) {
			findAllQuery.setFirstResult(startPosition);
		}
		if (maxResult != null) {
			findAllQuery.setMaxResults(maxResult);
		}
		final List<Mensaje> searchResults = findAllQuery.getResultList();
		final List<MensajeDTO> results = new ArrayList<MensajeDTO>();
		for (Mensaje searchResult : searchResults) {
			MensajeDTO dto = new MensajeDTO(searchResult);
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
		Mensaje entity = em.find(Mensaje.class, id);
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
