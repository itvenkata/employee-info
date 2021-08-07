package com.tvc.employee.mapper;

import com.tvc.employee.entity.Employee;
import com.tvc.employee.vo.EmployeeVO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Component
public class EmployeeMapper {

    public Employee toEmployee(EmployeeVO empVO){
        ModelMapper modelMapper = new ModelMapper();
       return  modelMapper.map(empVO,Employee.class);
    }

    public EmployeeVO toEmployeeVO(Employee emp){
        ModelMapper modelMapper = new ModelMapper();
        return  modelMapper.map(emp,EmployeeVO.class);
    }
}
