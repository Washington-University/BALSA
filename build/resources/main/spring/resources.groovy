// Place your Spring DSL code here
beans = {
	ldapUserDetailsMapper(balsa.authentication.BalsaUserDetailsContextMapper)
	
	redirectStrategy(balsa.strategy.BalsaRedirectStrategy) {
		useHeaderCheckChannelSecurity = false
		portResolver = ref('portResolver')
	}
	
	passwordEncoder(org.springframework.security.crypto.password.LdapShaPasswordEncoder)
}