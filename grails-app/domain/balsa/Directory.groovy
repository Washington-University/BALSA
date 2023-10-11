package balsa

class Directory {
    String name
    String path
    String pass

    static constraints = {
        pass black: true, nullable: true
    }

    static mapping = {
        id name: 'name'
    }
}