package com.priyanshudev.productcatalog;

import com.priyanshudev.productcatalog.inheritanceExamples.cascadeTypes.Address;
import com.priyanshudev.productcatalog.inheritanceExamples.cascadeTypes.AddressRepository;
import com.priyanshudev.productcatalog.inheritanceExamples.cascadeTypes.Person;
import com.priyanshudev.productcatalog.inheritanceExamples.cascadeTypes.PersonRepository;
import com.priyanshudev.productcatalog.inheritanceExamples.joinedTable.StudentJoinedTableRepository;
import com.priyanshudev.productcatalog.inheritanceExamples.joinedTable.Student_jt;
import com.priyanshudev.productcatalog.inheritanceExamples.mappedSuperclass.MappedSuperClassMentorRepository;
import com.priyanshudev.productcatalog.inheritanceExamples.mappedSuperclass.Mentor;
import com.priyanshudev.productcatalog.inheritanceExamples.mappedSuperclass.Student;
import com.priyanshudev.productcatalog.inheritanceExamples.mappedSuperclass.User;
import com.priyanshudev.productcatalog.inheritanceExamples.singleTable.Pen;
import com.priyanshudev.productcatalog.inheritanceExamples.singleTable.PenRepository;
import com.priyanshudev.productcatalog.inheritanceExamples.tablePerClass.Car;
import com.priyanshudev.productcatalog.inheritanceExamples.tablePerClass.Car_tpc_Repository;
import com.priyanshudev.productcatalog.inheritanceExamples.tablePerClass.Vehicle;
import com.priyanshudev.productcatalog.inheritanceExamples.tablePerClass.Vehicle_tpc_Repository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ProductCatalogApplicationTests {
	@Autowired
	MappedSuperClassMentorRepository repository;
	@Autowired
	StudentJoinedTableRepository studentJoinedTableRepository;

	@Autowired
	Car_tpc_Repository tpcRepository;
	@Autowired
	Vehicle_tpc_Repository vehicleTpcRepository;
	@Autowired
	PenRepository penRepository;

	@Autowired
	AddressRepository addressRepository;
	@Autowired
	PersonRepository personRepository;
@Test
	void testAddingMentor() {
	Mentor mentor = new Mentor();
	mentor.setName("Mentor");
	mentor.setId(11);
	repository.save(mentor);
}
@Test
void addNewStudentInJoinedTable() {
	Student_jt student = new Student_jt();
	student.setCourse("course");
	student.setGrade("A");
	studentJoinedTableRepository.save(student);
}

@Test
void testTablePerClass() {
	Car car = new Car();
	car.setColor("Red");
	tpcRepository.save(car);

	Vehicle vehicle = new Vehicle();
	vehicle.setManufacturer("Maruti");
	vehicleTpcRepository.save(vehicle);

}
@Test
void testSingleTable() {
	Pen pen = new Pen();
	pen.setColor("Red");
	penRepository.save(pen);
}

@Test
void testCascadingAll() {
	Person person = new Person();
	person.setName("Pri");
	Address address = new Address();
	address.setCity("HYD");
	address.setState("Tel");
	address.setZip(50089);
	person.setAddresses(List.of(address));
	personRepository.save(person);
}



}
