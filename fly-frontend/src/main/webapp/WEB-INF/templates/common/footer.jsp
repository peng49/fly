<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="fly-footer">
    <p><a href="http://fly.layui.com/" target="_blank">Fly社区</a> 2017 &copy; <a href="http://www.layui.com/"
                                                                                target="_blank">layui.com 出品</a></p>
    <p>
        <a href="http://fly.layui.com/jie/3147/" target="_blank">付费计划</a>
        <a href="http://www.layui.com/template/fly/" target="_blank">获取Fly社区模版</a>
        <a href="http://fly.layui.com/jie/2461/" target="_blank">微信公众号</a>
    </p>
</div>

<script src="https://cdn.jsdelivr.net/npm/vue@2.6.0"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>

<script src="https://cdn.bootcdn.net/ajax/libs/jquery/3.5.1/jquery.js"></script>
<script src="https://cdn.bootcdn.net/ajax/libs/layer/3.1.1/layer.min.js"></script>

<script type="text/javascript">
    // 添加响应拦截器
    axios.interceptors.response.use(function (response) {
        if (response.status != 200) {
            layer.alert("请求失败");
        }
        return response.data;
    }, function (error) {
        // 对响应错误做点什么
        layer.msg("请求异常");
        return Promise.reject(error);
    });
</script>