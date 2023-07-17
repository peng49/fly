<div class="fly-footer">
    <p><a href="/" target="_blank">技术社区</a> 2020 &copy; <a href="#" target="_blank">peng49 出品</a></p>
    <p>
        <a href="#" target="_blank">源码</a>
    </p>
</div>

<script src="/static/js/vue@3.3.4/vue.global.prod.js"></script>
<script src="/static/js/axios/axios.min.js"></script>

<script src="/static/js/jquery@3.5.1/jquery.min.js"></script>
<script src="/static/js/layer@3.5.1/layer.js"></script>
<script src="/static/common.js"></script>

<script type="text/javascript">
    // 添加请求拦截器
    axios.interceptors.request.use(function (config) {
        // 在发送请求之前做些什么
        config.headers['X-Requested-With'] = 'XMLHttpRequest';
        return config;
    }, function (error) {
        // 对请求错误做些什么
        return Promise.reject(error);
    });
    // 添加响应拦截器
    axios.interceptors.response.use(function (response) {
        if (response.status !== 200) {
            layer.alert("请求失败");
        }
        return response.data;
    }, function (error) {
        // 对响应错误做点什么
        layer.msg("请求异常");
        return Promise.reject(error);
    });
</script>