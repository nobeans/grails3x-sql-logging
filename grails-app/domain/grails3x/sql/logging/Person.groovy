package grails3x.sql.logging

class Person {

    String name
    Integer age

    static constraints = {
        name blank: false, maxSize: 1000
        age min: 0
    }
}
