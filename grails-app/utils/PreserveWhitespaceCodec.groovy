
class PreserveWhitespaceCodec {
	static encode = { str ->
		str.replace('\n\r', '<br/>').replace('\n', '<br/>').replace('  ', '&nbsp;&nbsp;')
	}
	
	static decode = { str ->
		str.replace('<br/>', '\n').replace('&nbsp;', ' ')
	}
}
