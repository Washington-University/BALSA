
class RemoveScriptCodec {
	static encode = { str ->
		str.replace('<script','&lt;script').replace('</script', '&lt;/script')
	}
}
