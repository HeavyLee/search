理想方案：
1. 后端
    管理员维度
        文件上传接口：csv文件通过管理后台上传，调用FileParseService文件解析服务将数据转换成标准对象，同步到ES中；
    用户维度
        餐车信息查询接口：根据关键词如地点，饮食品类等利用ES统计相关的信息并返回；
2. 前端
    页面整合地图sdk作为组件，支持按经纬度在地图上显示餐车的地理位置，点击餐车显示具体地址，营业时间和饮食品类等信息。

当前方案：
1. 由于时间有限，仅实现理想方案中的后端接口；
2. 考虑到ES搭建整合过程复杂，先用本地缓存代替；
3. 本地缓存不具备强大的检索和统计能力，因此只简单实现按地点和饮食品类维度统计的功能。

环境：
JDK17

验证：
服务启动后，可访问如下url
1. localhost:8080/getFoodTruckDetail?address=1570%20BURKE%20AVE&foodItem=bbq%20chicken%20skewer
2. localhost:8080/uploadFile