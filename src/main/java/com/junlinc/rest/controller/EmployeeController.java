package com.junlinc.rest.controller;
import com.junlinc.rest.domain.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class EmployeeController {
    @RequestMapping("/hello")
    @ResponseBody
    public String hello() {
        return "Hello World";
    }

    @RequestMapping(value = "/employees", method = RequestMethod.GET)
    @ResponseBody
    public List<Employee> list() {
        List<Employee> list = Arrays.asList(new Employee(1L, "xiao", 18));
        return list;
    }
}
