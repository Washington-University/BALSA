package balsa

import groovy.transform.ToString

import org.apache.commons.lang.builder.HashCodeBuilder

@ToString(cache=true, includeNames=true, includePackage=false)
class PersistentLogin implements Serializable {

	private static final long serialVersionUID = 1

	String series
	String username
	String token
	Date lastUsed

	@Override
	int hashCode() {
		new HashCodeBuilder().append(series).append(username).toHashCode()
	}

	@Override
	boolean equals(other) {
		is(other) || (
			other instanceof PersistentLogin &&
			other.series == series &&
			other.username == username)
	}

	static constraints = {
		series maxSize: 64
		token maxSize: 64
		username maxSize: 64
	}

	static mapping = {
		table 'persistent_login'
		id name: 'series', generator: 'assigned'
		version false
	}
}
