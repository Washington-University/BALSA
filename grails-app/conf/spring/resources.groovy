package spring

import balsa.strategy.BalsaRedirectStrategy

// Place your Spring DSL code here
beans = {
	redirectStrategy(BalsaRedirectStrategy) {
		useHeaderCheckChannelSecurity = false
		portResolver = ref('portResolver')
	}
}