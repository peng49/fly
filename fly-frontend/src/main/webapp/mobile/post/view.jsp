<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <meta charset='utf-8'>
    <title>详情页</title>
    <link rel="stylesheet" href="/static/editor.md/css/editormd.min.css"/>
    <jsp:include page="../common/link.jsp"/>
</head>
<body>
<jsp:include page="../common/header.jsp"/>
<jsp:include page="../common/nav.jsp"/>

<div class="weui-panel weui-panel_access view-content">
    <div class="weui-panel__hd">
        <h3>首页 / 详情</h3>
        <h1>文章标题</h1>
    </div>
    <div class="weui-panel__bd content">
        <div class="markdown-body editormd-preview-container">
            <p>开头文本内容</p>
            <pre class="prettyprint linenums prettyprinted"><ol class="linenums"><li class="L0"><code class="lang-java"><span
                    class="pln">    </span><span class="typ">System</span><span class="pun">.</span><span class="pln">out</span><span
                    class="pun">.</span><span class="pln">println</span><span class="pun">(</span><span class="str">"hello world"</span><span
                    class="pun">);</span></code></li><li class="L1"><code class="lang-java"><span
                    class="pln">    </span><span class="kwd">int</span><span class="pln"> a </span><span
                    class="pun">=</span><span class="pln"> </span><span class="lit">1</span><span
                    class="pln"> </span><span class="pun">+</span><span class="pln"> </span><span
                    class="lit">3</span><span class="pun">;</span></code></li><li class="L2"><code
                    class="lang-java"><span class="pln">    </span><span class="typ">System</span><span
                    class="pun">.</span><span class="pln">out</span><span class="pun">.</span><span
                    class="pln">println</span><span class="pun">(</span><span class="pln">a</span><span
                    class="pun">);</span></code></li></ol></pre>
        </div>
    </div>
    <div class="weui-panel__ft bottom">
        <span><a class="column" href="">column</a></span>
        <span class="point">•</span>
        <span><a class="author" href="">author</a></span>
        <span class="point">•</span>
        <span><time>time</time></span>
    </div>
</div>

<div class="weui-panel weui-panel_access">
    <div class="weui-panel__hd panel-head">
        <h3>首页/详情</h3>
    </div>
    <ul class="weui-panel__bd">
       <li>评论内容</li>
    </ul>
</div>

<jsp:include page="../common/footer.jsp"/>
</body>
</html>
