package test;

public class Student {
	private String studentName;
	private String studentAge;
	
	public Student(String studentName, String studentAge) {
		this.studentName = studentName;
		this.studentAge = studentAge;
	}
	
	//getters and setters
	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getStudentAge() {
		return studentAge;
	}

	public void setStudentAge(String studentAge) {
		this.studentAge = studentAge;
	}
	
	public String toString () {
		return "name: " + this.studentName + ", age: " + this.studentAge;
	}
}
