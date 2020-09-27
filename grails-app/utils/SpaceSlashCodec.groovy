class SpaceSlashCodec {
	static encode = { str ->
		str.replace('/', ' / ')
	}
}
