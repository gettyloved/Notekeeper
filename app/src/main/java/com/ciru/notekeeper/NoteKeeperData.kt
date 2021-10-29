// data classes are used to simplify data model classes
// when used te primary constructor must have properties only i.e they use var or val keyword
// it generates toString,Hashmap,copy and equals methods
// i think its used to replace getters and setters

package com.ciru.notekeeper

data class CourseInfo(val courseId:String, val title:String) {
    override fun toString(): String {
        return title
    }
}

data class NoteInfo(var course:CourseInfo? = null, var title: String? = null, var text:String? = null)