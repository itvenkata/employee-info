package com.tvc.employee.mapper;

import com.tvc.employee.entity.Employee;
import com.tvc.employee.vo.EmployeeVo;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {

  public Employee toEmployee(EmployeeVo empVO) {
    ModelMapper modelMapper = new ModelMapper();
    return modelMapper.map(empVO, Employee.class);
  }

  public EmployeeVo toEmployeeVO(Employee emp) {
    ModelMapper modelMapper = new ModelMapper();
    return modelMapper.map(emp, EmployeeVo.class);
  }

  public Employee updateEmployee(EmployeeVo sourceObject, Employee destinationObject) {
    ModelMapper mapper = new ModelMapper();
    mapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
    mapper.map(sourceObject, destinationObject);
    return destinationObject;
  }
}
