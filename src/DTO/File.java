package DTO;

public class File {
private String name;
private String data;
public File(String name,String data){
	this.name=name;
	this.data=data;
}
public File(String name) {
	this.name=name;
}
public String getname() {
	return name;
}
public String getdata() {
	return data;
}
}