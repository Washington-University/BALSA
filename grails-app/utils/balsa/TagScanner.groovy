package balsa

import balsa.tagging.TagHandle

class TagScanner {
	static List<String> scan(String stringToScan) {
		def returnList = []
		for (tagHandle in TagHandle.list()) {
			def caseInsensitiveHandle = '(?i)' + tagHandle.handle
			def matcher = stringToScan =~ caseInsensitiveHandle
			if (matcher.size() > 0) {
				if (tagHandle.value == null || tagHandle.value.empty) {
					returnList.add(tagHandle.category.name + ":" + matcher[0])
				}
				else {
					returnList.add(tagHandle.category.name + ":" + tagHandle.value)
				}
			}
		}
		returnList
	}
}
