// IServer.aidl
package com.dream.server;
import com.dream.server.Student;

// Declare any non-default types here with import statements

interface IServer {

    int getAge();

    void setName(String name);

    List<Student> getStudentList();

    void addStudent(inout Student student);
}