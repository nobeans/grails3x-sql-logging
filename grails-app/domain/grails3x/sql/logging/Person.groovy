package grails3x.sql.logging

class Person {

    String name
    Integer age
    BloodType bloodType

    static constraints = {
        name blank: false, maxSize: 1000
        age min: 0
        bloodType()
    }

    static mapping = {
        bloodType enumType: 'string' // インデックスではなく文字列自体をDBに格納する
    }
}

enum BloodType {
    A, B, AB, O
}
