interface Mapper<fromObject, toObject> {

    fun toObject(fromObject: fromObject): toObject

    fun toObjectList(fromObjects: List<fromObject>): List<toObject> {
        return fromObjects.map { toObject(it) }
    }

}

interface MapperNullable<fromObject, toObject> {

    fun toObjectNullable(fromObject: fromObject?): toObject?

    fun toObjectNullableList(fromObjects: List<fromObject?>): List<toObject?> {
        return fromObjects.map { toObjectNullable(it) }
    }

}