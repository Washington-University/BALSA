package balsa.file.gifti

import net.kaleidos.hibernate.usertype.JsonMapType

class Label extends Gifti {
    Map labelTable

    static constraints = {
        labelTable nullable: true, blank: true
    }

    static mapping = {
        labelTable type: JsonMapType
    }

    def setValuesFromFile(InputStream input) {
        def giftiFile = xmlSlurper().parse(input)
        for (md in giftiFile.MetaData.MD) {
            switch (md.Name.text()) {
                case "Caret-Version":
                    this.caretVersion = md.Value.text()
                    break
                case "UniqueID":
                    this.uniqueID = md.Value.text()
                    break
                case "encoding":
                    this.encoding = md.Value.text()
                    break
            }
        }
        labelTable = [lt:[]]
        for (label in giftiFile.LabelTable.Label) {
            labelTable.lt.add([index:label.@Key.text(),
                                  red:Math.round(label.@Red.text().toFloat() * 255),
                                  green:Math.round(label.@Green.text().toFloat() * 255),
                                  blue:Math.round(label.@Blue.text().toFloat() * 255),
                                  alpha:Math.round(label.@Alpha.text().toFloat() * 255),
                                  label:label.text()])
        }
    }
}
