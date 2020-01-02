package com.offcn.controller;

import com.offcn.pojo.User;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author 陈旺生
 * @create 2019-12-30 20:44
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private List<User> listUser = Collections.synchronizedList(new ArrayList<>());

    /***
     * 获取全部用户信息
     * @return
     */
    @GetMapping("/getUserList")
    @ApiOperation(value="查询所有用户信息", notes="查询所有用户信息")
    public List<User> getUserList() {
        return listUser;
    }

    /***
     * 新增用户
     * @param user
     * @return
     */
    @PostMapping("/addUser")
    @ApiOperation(value="保存用户信息", notes="保存用户信息,接收一个user对象的json数据格式")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    })
    public String addUser(@RequestBody User user) {
        listUser.add(user);
        return "add-ok";
    }

    /***
     * 获取指定id用户信息
     * @param id
     * @return
     */
    @GetMapping("/findOne/{id}")
    public User findOne(@PathVariable("id") Long id) {
        for (User user : listUser) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    /**
     * 更新指定id用户信息
     *
     * @param id
     * @param user
     * @return
     */
    @PutMapping("/updateUser/{id}")
    @ApiOperation(value="更新指定id用户信息", notes="根据id更新用户信息")
	@ApiImplicitParams({
         @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long"),
         @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
     })
    public String updateUser(@PathVariable("id") Long id, User user) {
        for (User user2 : listUser) {
            if (user2.getId() == id) {
                user2.setName(user.getName());
                user2.setAge(user.getAge());
            }
        }
        return "update-ok";
    }

    /***
     * 删除指定id用户
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    @ApiOperation(value="删除指定id用户信息", notes="根据id删除用户信息")
    @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "Long")
    public String deleteUser(@PathVariable("id") Long id) {

        listUser.remove(findOne(id));
        return "delete-ok";

    }
    @RequestMapping("/show")
    public String show(){
        System.out.println("ok");
        return "show-test";
    }
}
