package com.gencolabs.entity;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.gencolabs.users.entity.User;
import lombok.Getter;
import lombok.Setter;

/**
 * @author olatunji
 */
@Getter
@Setter
public class RetroRoom {

	/**
	 * retro room id
	 */
	private String roomId;

	/**
	 * list of users in this retro room
	 */
	private List<User> users = new ArrayList<>();

	/**
	 * current votes being casted by the users
	 * each user is associated to one vote per voting session
	 */
	private Map<String, Float> votes = new LinkedHashMap<>();

}
