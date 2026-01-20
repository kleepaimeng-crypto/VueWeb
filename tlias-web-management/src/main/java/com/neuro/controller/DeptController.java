package com.neuro.controller;

import com.neuro.pojo.Dept;
import com.neuro.pojo.Result;
import com.neuro.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class DeptController {



    @Autowired
    private DeptService deptService;

    @RequestMapping(value = "/depts",method = RequestMethod.GET)
    //指定请求方式method或者写GetMapping
    public Result list(){
        System.out.println("查询全部部门数据");
        List<Dept> deptList = deptService.findAll();
        return Result.success(deptList);
    }

    /*
      删除部门
     */
        @DeleteMapping("/depts")
        public Result delete(Integer id){
            System.out.println(id);
            deptService.deleteById(id);
            return Result.success();
        }

        @PostMapping("/depts")
        public Result add(@RequestBody Dept dept){
            System.out.println(dept);
            deptService.add(dept);
            return Result.success();
        }

        @GetMapping("/depts/{id}")
        public Result getInfo(@PathVariable Integer id){
            System.out.println("查询参数"+id);
            return Result.success(deptService.getById(id));
        }

        @PutMapping("/depts/{id}")
        public Result update(@RequestBody Dept dept){
            System.out.println("修改部门"+dept);
            deptService.update(dept);
            return Result.success();
        }
 }




/*
      删除部门

    public Result delete(HttpServletRequest request){
        String idStr = request.getParameter("id");
        int id = Integer.parseInt(idStr);
        System.out.println("根据ID删除部门"+id);
        return Result.success();


        //前端传递的参数名与服务端的方法形参名一致可以省略注解RequestParam
         public Result delete(@RequestParam("id") Integer deptId){
            System.out.println(deptId);
            return Result.success();
        }

 */