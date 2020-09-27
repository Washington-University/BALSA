package balsa.security

import org.apache.commons.lang.builder.HashCodeBuilder

class UserRole implements Serializable {

	private static final long serialVersionUID = 1

	BalsaUser user
	Role role

	boolean equals(other) {
		if (!(other instanceof UserRole)) {
			return false
		}

		other.user?.id == user?.id &&
		other.role?.id == role?.id
	}

	int hashCode() {
		def builder = new HashCodeBuilder()
		if (user) builder.append(user.id)
		if (role) builder.append(role.id)
		builder.toHashCode()
	}

	static UserRole get(String userId, String roleId) {
		UserRole.where {
			user == BalsaUser.load(userId) &&
			role == Role.load(roleId)
		}.get()
	}

	static boolean exists(String userId, String roleId) {
		UserRole.where {
			user == BalsaUser.load(userId) &&
			role == Role.load(roleId)
		}.count() > 0
	}

	static UserRole create(BalsaUser user, Role role, boolean flush = false) {
		def instance = new UserRole(user: user, role: role)
		instance.save(flush: flush, insert: true)
		instance
	}

	static boolean remove(BalsaUser u, Role r, boolean flush = false) {
		if (u == null || r == null) return false

		int rowCount = UserRole.where {
			user == BalsaUser.load(u.id) &&
			role == Role.load(r.id)
		}.deleteAll()

		if (flush) { UserRole.withSession { it.flush() } }

		rowCount > 0
	}

	static void removeAll(BalsaUser u, boolean flush = false) {
		if (u == null) return

		UserRole.where {
			user == BalsaUser.load(u.id)
		}.deleteAll()

		if (flush) { UserRole.withSession { it.flush() } }
	}

	static void removeAll(Role r, boolean flush = false) {
		if (r == null) return

		UserRole.where {
			role == Role.load(r.id)
		}.deleteAll()

		if (flush) { UserRole.withSession { it.flush() } }
	}

	static constraints = {
		role validator: { Role r, UserRole ur ->
			if (ur.user == null) return
			boolean existing = false
			UserRole.withNewSession {
				existing = UserRole.exists(ur.user.id, r.id)
			}
			if (existing) {
				return 'userRole.exists'
			}
		}
	}

	static mapping = {
		id composite: ['role', 'user']
		version false
	}
}
