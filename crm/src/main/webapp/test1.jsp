<%--
  Created by IntelliJ IDEA.
  User: 18162
  Date: 2022/2/15
  Time: 0:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
        $.ajax({

            url:"",
            data:{

            },
            type:"",
            dataType:"json",
            success:function(data){

            }
        })

        $("#").click(function(){


        })

        $(".time").datetimepicker({
            minView: "month",
            language: 'zh-CN',
            format: 'yyyy-mm-dd',
            autoclose: true,
            todayBtn: true,
            pickerPosition: "bottom-left"
        });


        String createTime = DateTimeUtil.getSysTime();
        String createBy = ((User)request.getSession().getAttribute("user")).getName();

        id
        noteContent
        createTime
        createBy
        editTime
        editBy
        editFlag
        activityId

        id
        owner
        name
        startDate
        endDate
        cost
        description
        createTime
        createBy
        editTime
        editBy
//数据字典

        code
        name
        description

        id
        value
        text
        orderNo
        typeCode

//线索表
        id
        fullname
        appellation
        owner
        company
        job
        email
        phone
        website
        mphone
        state
        source
        createBy
        createTime
        editBy
        editTime
        description
        contactSummary
        nextContactTime
        address

        id
        owner
        name
        website
        phone
        createBy
        createTime
        editBy
        editTime
        contactSummary
        nextContactTime
        description
        address


        id
        owner
        source
        customerId
        fullname
        appellation
        email
        mphone
        job
        birth
        createBy
        createTime
        editBy
        editTime
        description
        contactSummary
        nextContactTime
        address


        id
        owner
        money
        name
        expectedDate
        customerId
        stage
        type
        source
        activityId
        contactsId
        createBy
        createTime
        editBy
        editTime
        description
        contactSummary
        nextContactTime

        id
        stage
        money
        expectedDate
        createTime
        createBy
        tranId

</body>
</html>
