package com.gencolabs;

import java.util.Map;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 */
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class VotingResource {

	@Inject
	Moderator moderator;

	@POST
	@Path("/{roomId}/{username}/vote")
	public void castVote(@PathParam("roomId") final String roomId, @PathParam("username") final String username,
			final Float vote) {
		moderator.castVote(roomId, username, vote);
	}

	@GET
	@Path("/new-retro")
	public String createRoom() {
		return moderator.createRoom();
	}

	@GET
	@Path("{roomId}/collate")
	public Map<Float, Long> collateVotes(@PathParam("roomId") final String roomId) {
		return moderator.collateVotes(roomId);
	}
}