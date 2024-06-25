package com.eduConnect.eduConnect.Service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eduConnect.eduConnect.Exception.UserNotFoundException;
import com.eduConnect.eduConnect.Model.ClassesManagement;
import com.eduConnect.eduConnect.Repositroy.ClassesManagementRepository;
import com.eduConnect.eduConnect.RespositroyImpl.ClassesManagementRepositoryImpl;

@Service
public class ClassesManagementService implements ClassesManagementRepositoryImpl {

	@Autowired
	private ClassesManagementRepository classesManagementRepository;

	@Override
	public ClassesManagement saveClassesManagement(ClassesManagement classesManagement) throws Exception {
		classesManagement.setClassAddDateTime(LocalDate.now());
		return classesManagementRepository.save(classesManagement);
	}

	@Override
	public List<ClassesManagement> getAllClassesManagement() {
		return this.classesManagementRepository.findAll();
	}

	@Override
	public ClassesManagement getClassesManagementById(Long classId) {
		try {
			return classesManagementRepository.findById(classId).orElse(null);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void deleteClassesManagementById(Long classId) {
		this.classesManagementRepository.deleteById(classId);
	}

	@Override
	public ClassesManagement updatedClassesManagement(ClassesManagement updatedClasses, Long classId) throws Exception {
		ClassesManagement existingclasses = classesManagementRepository.findById(classId).orElse(null);
		if (existingclasses == null) {
			throw new UserNotFoundException("Classes not found");
		}

		existingclasses.setClassName(updatedClasses.getClassName());
		existingclasses.setClassSection(updatedClasses.getClassSection());
		return classesManagementRepository.save(existingclasses);
	}

}
