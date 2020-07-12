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

<div class="panel">
    <div class="panel-head">
        <p class="breadcrumb"><span>首页</span> / <a href="">详情</a></p>
        <h1 class="title">文章标题</h1>
    </div>
    <div class="panel-content">
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
    <div class="panel-footer">
        <span><a class="column" href="">栏目</a></span>
        <span class="point">•</span>
        <span><a class="author" href="">作者</a></span>
        <span class="point">•</span>
        <span><time>6分钟前</time></span>
    </div>
</div>

<div class="panel">
    <div class="panel-head">
        <p>共1条评论</p>
    </div>
    <div class="panel-content">
        <ul class="comments">
            <li class="item">
                <div class="avatar">
                    <a href="">
                        <img src="https://img.boxopened.com/avatars/x129s291i.png">
                    </a>
                </div>
                <div class="content">
                    <div class="comment-head">
                        <span class="username">
                            <a href="">username</a>
                        </span>
                        <span>
                            <time date="" title="">4 天前</time>
                        </span>
                        <span class="right">#1</span>
                    </div>
                    <div class="comment">
                        评论内容 评论内容 评论内容 评论内容 评论内容 评论内容 评论内容 评论内容 评论内容 评论内容 评论内容 评论内容 评论内容 评论内容 评论内容 评论内容 评论内容 评论内容 评论内容
                        评论内容
                        评论内容
                    </div>
                </div>
            </li>
            <li class="item">
                <div class="avatar">
                    <a href="">
                        <img src="https://img.boxopened.com/avatars/x129s291i.png">
                    </a>
                </div>
                <div class="content">
                    <div class="comment-head">
                        <span class="username">
                            <a href="">username</a>
                        </span>
                        <span>
                            <time date="" title="">4 天前</time>
                        </span>
                        <span class="right">#1</span>
                    </div>
                    <div class="comment">
                        <div class="comment-parent">
                            <div class="a">引用 @username 的回复</div>
                            <div>
                                评论
                            </div>
                        </div>
                        <div style="overflow: hidden;">
                            回复评论
                        </div>
                    </div>
                </div>
            </li>
        </ul>
    </div>
</div>

<jsp:include page="../common/footer.jsp"/>
</body>
</html>
