//this is where the course and notes data will be stored and retrieved

package com.ciru.notekeeper

object DataManager {
    val courses = HashMap<String, CourseInfo>()
    val notes = ArrayList<NoteInfo>()

    init {
        initializeCourses()
        initializeNotes()
    }

    private fun initializeNotes() {

    }

    private  fun initializeCourses(){
        var course = CourseInfo("android_intents" , "Android Programming with Intents")
        courses.set(course.courseId, course)

        course = CourseInfo("android_async", "Android Async Programming and Services" )
        courses.set(course.courseId, course)

        course = CourseInfo(title =  "Java Fundamentals: The Java Language", courseId = "Java lang" )
        courses.set(course.courseId, course)

        course = CourseInfo("java_core", "Java Fundamentals: The Core Platform" )
        courses.set(course.courseId, course)
    }
}