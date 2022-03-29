package com.bjpowernode.crm.workbench.web.controller;

import com.bjpowernode.crm.settings.domain.User;
import com.bjpowernode.crm.settings.service.UserService;
import com.bjpowernode.crm.settings.service.impl.UserServiceImpl;
import com.bjpowernode.crm.utils.DateTimeUtil;
import com.bjpowernode.crm.utils.PrintJson;
import com.bjpowernode.crm.utils.ServiceFactory;
import com.bjpowernode.crm.utils.UUIDUtil;
import com.bjpowernode.crm.vo.PaginationVO;
import com.bjpowernode.crm.workbench.domain.Activity;
import com.bjpowernode.crm.workbench.domain.ActivityRemark;
import com.bjpowernode.crm.workbench.service.ActivityService;
import com.bjpowernode.crm.workbench.service.Impl.ActivityServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActivityController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("进入到市场活动控制器");

        String path = request.getServletPath();
        if ("/workbench/activity/getUserList.do".equals(path)) {
            getUserList(request, response);

        } else if ("/workbench/activity/saveBtn.do".equals(path)) {
            save(request, response);

        }else if("/workbench/activity/pageList.do".equals(path)){
            pageList(request, response);

        }else if("/workbench/activity/delete.do".equals(path)){
            delete(request, response);

        }else if("/workbench/activity/getUserListAndActivity.do".equals(path)){
            getUserListAndActivity(request, response);

        }if("/workbench/activity/update.do".equals(path)){
            update(request, response);

        }if("/workbench/activity/detail.do".equals(path)){
            detail(request, response);

        }if("/workbench/activity/getRemarkListByActivity.do".equals(path)){
            getRemarkListByActivity(request, response);

        }if("/workbench/activity/deleteRemark.do".equals(path)){
            deleteRemark(request, response);

        }if("/workbench/activity/saveRemark.do".equals(path)){
            saveRemark(request, response);

        }if("/workbench/activity/updateRemark.do".equals(path)){
            updateRemark(request, response);

        }
    }

    private void updateRemark(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("执行修改备注的操作");

        String noteContent = request.getParameter("noteContent");
        String id = request.getParameter("id");
        String editTime = DateTimeUtil.getSysTime();
        String editBy = ((User)request.getSession().getAttribute("user")).getName();
        String editFlag = "1";

        ActivityRemark ar = new ActivityRemark();
        ar.setId(id);
        ar.setNoteContent(noteContent);
        ar.setEditBy(editBy);
        ar.setEditTime(editTime);
        ar.setEditFlag(editFlag);

        ActivityService as = (ActivityService)ServiceFactory.getService(new ActivityServiceImpl());

        boolean flag = as.updateRemark(ar);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("success",flag);
        map.put("ar",ar);

        PrintJson.printJsonObj(response,map);

    }

    private void saveRemark(HttpServletRequest request, HttpServletResponse response) {

        System.out.println("执行添加备注的操作");

        String noteContent = request.getParameter("noteContent");
        String activityId = request.getParameter("activityId");
        String id = UUIDUtil.getUUID();
        String createTime = DateTimeUtil.getSysTime();
        String createBy = ((User)request.getSession().getAttribute("user")).getName();
        String editFlag = "0";

        ActivityRemark ar = new ActivityRemark();
        ar.setId(id);
        ar.setNoteContent(noteContent);
        ar.setActivityId(activityId);
        ar.setCreateBy(createBy);
        ar.setCreateTime(createTime);
        ar.setEditFlag(editFlag);

        ActivityService as = (ActivityService)ServiceFactory.getService(new ActivityServiceImpl());

        boolean flag = as.saveRemark(ar);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("success",flag);
        map.put("ar",ar);

        PrintJson.printJsonObj(response,map);

    }

    private void deleteRemark(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("删除备注操作");

        String id = request.getParameter("id");


        ActivityService as = (ActivityService)ServiceFactory.getService(new ActivityServiceImpl());

        boolean flag = as.deleteRemark(id);

        PrintJson.printJsonFlag(response,flag);
    }

    private void getRemarkListByActivity(HttpServletRequest request, HttpServletResponse response) {

        System.out.println("根据市场活动id，取得备注信息列表");

        String activityId = request.getParameter("activityId");


        ActivityService as = (ActivityService)ServiceFactory.getService(new ActivityServiceImpl());

        List<ActivityRemark> arList = as.getRemarkListByActivity(activityId);

        PrintJson.printJsonObj(response,arList);

    }

    private void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        System.out.println("进入到跳转到详细信息页的操作");

        String id = request.getParameter("id");

        ActivityService as = (ActivityService)ServiceFactory.getService(new ActivityServiceImpl());

        Activity a = as.detail(id);

        request.setAttribute("a",a);

        request.getRequestDispatcher("/workbench/activity/detail.jsp").forward(request,response);



    }

    private void update(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("执行市场活动的修改操作");

        String id = request.getParameter("id");
        String owner = request.getParameter("owner");
        String name = request.getParameter("name");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String cost = request.getParameter("cost");
        String description = request.getParameter("description");
        //修改时间：当前时间
        String editTime = DateTimeUtil.getSysTime();
        //修改人：当前登录用户
        String editeBy = ((User)request.getSession().getAttribute("user")).getName();

        Activity a = new Activity();
        a.setId(id);
        a.setOwner(owner);
        a.setName(name);
        a.setStartDate(startDate);
        a.setEndDate(endDate);
        a.setCost(cost);
        a.setDescription(description);
        a.setEditTime(editTime);
        a.setEditBy(editeBy);

        ActivityService as = (ActivityService)ServiceFactory.getService(new ActivityServiceImpl());

        boolean flag = as.update(a);
        PrintJson.printJsonFlag(response,flag);
    }

    private void getUserListAndActivity(HttpServletRequest request, HttpServletResponse response) {

        System.out.println("进入到查询用户列表和根据市场活动id查询单条记录的操作");

        String id = request.getParameter("id");

        ActivityService as = (ActivityService)ServiceFactory.getService(new ActivityServiceImpl());

        /*
            总结：
                controller调用service的方法，返回值应该是什么
                你的想一想前端要什么，就要从service层取什么

            前端需要的，管业务层取要
            uList
            a

            以上两项信息，复用率不高，我们选择使用map打包这两项信息即可
            map


         */

        Map<String,Object> map = as.getUserListAndActivity(id);

        PrintJson.printJsonObj(response,map);





    }

    private void delete(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("执行市场活动的删除操作");

        String ids[] = request.getParameterValues("id");

        ActivityService as = (ActivityService)ServiceFactory.getService(new ActivityServiceImpl());

        boolean flag = as.delete(ids);

        PrintJson.printJsonFlag(response,flag);

    }

    private void pageList(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("进入到查询市场活动信息列表的操作（结合条件查询+分页查询）");
        
        String name = request.getParameter("name");
        String owner = request.getParameter("owner");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String pageNoStr = request.getParameter("pageNo");
        int pageNo = Integer.valueOf(pageNoStr);
        //每页展现的记录数
        String pageSizeStr = request.getParameter("pageSize");
        int pageSize = Integer.valueOf(pageSizeStr);
        //计算出略过的记录数
        int skipCount = (pageNo-1)*pageSize;


        Map<String,Object> map = new HashMap<String,Object>();
        map.put("name",name);
        map.put("owner",owner);
        map.put("startDate",startDate);
        map.put("endDate",endDate);
        map.put("skipCount",skipCount);
        map.put("pageSize",pageSize);

        ActivityService as = (ActivityService)ServiceFactory.getService(new ActivityServiceImpl());

        /*
                前端要：市场活动信息列表
                    查询的总条数

                    业务层拿到了以上两项信息之后，如果做返回
                        map
                            map.put("dataList":dataList)
                            map.put("total":total)
                            PrintJSON map -->json
                            {"total":100, "dataList":[{市场活动1}，{2}，{3}]}

                        vo
                            PaginationVo<T>
                                private int total;
                                private List<T> dataList;


                        PaginationVO<Activity> vo = new PaginationVO<>;
                        vo.setTotal(total);
                        vo.setDataList(dataList);
                        PrintJSON vo -->json

                        将来分页查询，每个模块都有，所以我们选择一个通用vo，操作起来比较方便

         */
        PaginationVO<Activity> vo = as.pageList(map);
        //vo-->{"total":100, "dataList":[{市场活动1}，{2}，{3}]}

        PrintJson.printJsonObj(response,vo);


    }

    private void save(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("执行市场活动添加操作");

        String id = UUIDUtil.getUUID();
        String owner = request.getParameter("owner");
        String name = request.getParameter("name");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String cost = request.getParameter("cost");
        String description = request.getParameter("description");
        //创建时间：当前时间
        String createTime = DateTimeUtil.getSysTime();
        //创建人：当前登录用户
        String createBy = ((User)request.getSession().getAttribute("user")).getName();

        Activity a = new Activity();
        a.setId(id);
        a.setOwner(owner);
        a.setName(name);
        a.setStartDate(startDate);
        a.setEndDate(endDate);
        a.setCost(cost);
        a.setDescription(description);
        a.setCreateTime(createTime);
        a.setCreateBy(createBy);

        ActivityService as = (ActivityService)ServiceFactory.getService(new ActivityServiceImpl());

        boolean flag = as.save(a);
        PrintJson.printJsonFlag(response,flag);
    }

    private void getUserList(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("取得用户信息列表");

        UserService us = (UserService) ServiceFactory.getService(new UserServiceImpl());

        List<User> uList = us.getUserList();

        PrintJson.printJsonObj(response,uList);


    }
}