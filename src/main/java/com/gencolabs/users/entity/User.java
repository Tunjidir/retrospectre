package com.gencolabs.users.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @author olatunji
 */
@Getter
@Setter
@EqualsAndHashCode
public class User {

	/**
	 * unique identifier for a user
	 */
	private String username;

}
