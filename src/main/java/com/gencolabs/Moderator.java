package com.gencolabs;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.NotFoundException;

import com.gencolabs.moderator.entity.RetroRoom;
import com.gencolabs.users.entity.User;

/**
 * Controller class for saving and collating retro votes
 *
 * @author olatunji oniyide
 */
@ApplicationScoped
public class Moderator {

	private final Map<String, RetroRoom> retroRooms = new LinkedHashMap<>();

	/**
	 * creates a room for a retrospective.
	 *
	 * @return roomId
	 */
	public String createRoom() {
		final String roomId = UUID.randomUUID().toString();
		final var retroRoom = new RetroRoom();
		retroRoom.setRoomId(roomId);

		retroRooms.put(roomId, retroRoom);
		return roomId;
	}

	/**
	 * adds the current vote, given the current retro room id
	 *
	 * @param roomId
	 * @param vote
	 */
	public void castVote(final String roomId, final String username, final Float vote) {
		final var retroRoom = retroRooms.get(roomId);

		//validate user exists in retroRoom
		final var existingUser = retroRoom.getUsers()
						.stream()
						.filter(user -> user.getUsername().equals(username))
						.findAny();

		if (existingUser.isEmpty()) {
			throw new NotFoundException("user does not exist in this retro room, cannot cast vote");
		}

		retroRoom.getVotes().put(username, vote);
	}

	/**
	 * collate the votes for a particular roomId
	 *
	 * @param roomId
	 * 		retro room
	 * @return
	 */
	public Map<Float, Long> collateVotes(final String roomId) {
		final var retroRoom = retroRooms.get(roomId);
		final List<Float> votes = new ArrayList<>(retroRoom.getVotes().values());
		return votes.stream()
				.collect(Collectors.groupingBy(v -> v, Collectors.counting()));
	}

	/**
	 * adds a new user to the retro room.
	 *
	 * @param roomId existing retro room
	 * @param username
	 */
	public void joinRetroRoom(final String roomId, final String username) {
		final var retroRoom = retroRooms.get(roomId);
		if (retroRoom == null) {
			throw new NotFoundException("retro room doesn't exist!");
		}
		final var user = new User();
		user.setUsername(username);

		retroRoom.getUsers().add(user);
	}
}
