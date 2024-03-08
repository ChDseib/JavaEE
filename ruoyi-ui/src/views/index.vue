<template>
  <div class="app-container home">
    <el-row :gutter="20">
      <el-col :sm="24" :lg="12" style="padding-left: 20px">
        <h2>成果管理系统</h2>
        <p>
          本系统是专为组织、管理各种证书、奖项而设计的软件。它集成了学生管理、认证机构、证书管理、竞赛管理、奖项管理等功能，旨在为学生获奖信息的管理提供全面、高效的解决方案。
        </p>
        <p>
          系统logo为一朵手绘的小红花，寓意：
          <li>鼓励和赞美：小红花常常被用来表示对某个人或事物的鼓励、赞美或奖励。在手绘小红花时，通常会用心地描绘出花瓣和花形，以表达对美好事物的欣赏和鼓励。</li>
          <li>美好愿望：小红花也被视为一种代表美好愿望的符号，常常被送给亲朋好友或自己心爱的人，表达美好的祝愿和关心。通过手绘小红花，可以传递出对美好未来的期望和祝福。</li>
          <li>积极心态：小红花也可以被视为一种积极向上的符号，代表着热情、活力和勇气。通过手绘小红花，可以激励自己保持积极的心态，迎接生活中的挑战。</li>
        </p>
        <p>
          <b>当前版本:</b> <span>v{{ version }}</span>
        </p>
        <p>
          <el-button
            type="primary"
            size="mini"
            icon="el-icon-cloudy"
            plain
            @click="goTarget('https://gitee.com/goldenpond/RuoYi-Vue-cas')"
            >访问码云</el-button
          >
        </p>
      </el-col>

      <el-col :sm="24" :lg="12" style="padding-left: 50px">
        <el-row>
          <el-col :span="12">
            <h2>功能实现</h2>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="6">
            <h4>职业资格证书</h4>
            <ul>
              <li>新增</li>
              <li>修改</li>
              <li>删除</li>
              <li>查询</li>
              <li>上传附件</li>
              <li>导出数据</li>
              <li>下载附件</li>
            </ul>
          </el-col>
          <el-col :span="6">
            <h4>学科竞赛获奖</h4>
            <ul>
              <li>新增</li>
              <li>修改</li>
              <li>删除</li>
              <li>查询</li>
              <li>上传附件</li>
              <li>导出数据</li>
              <li>下载附件</li>
            </ul>
          </el-col>
        </el-row>
      </el-col>
    </el-row>
    <el-divider />
    <el-row :gutter="20">
      <el-col :xs="24" :sm="24" :md="12" :lg="8">
        <el-card class="update-log">
          <div slot="header" class="clearfix">
            <span>联系信息</span>
          </div>
          <div class="body">
            <p>
              <i class="el-icon-s-promotion"></i> 官网：<el-link
                href="http://www.sandau.edu.cn"
                target="_blank"
                >http://www.sandau.edu.cn</el-link
              >
            </p>
            <p>
              <i class="el-icon-chat-dot-round"></i> 邮箱：<a
                href="javascript:;"
                ></a
              >
            </p>
          </div>
        </el-card>
      </el-col>
      <el-col :xs="24" :sm="24" :md="12" :lg="8">
        <el-card class="update-log">
          <div slot="header" class="clearfix">
            <span>更新日志</span>
          </div>
          <el-collapse accordion>
            <el-collapse-item title="v1.0.0 - 2024-01-20">
              <ol>
                <li>成果管理系统第一个正式版本发布</li>
              </ol>
            </el-collapse-item>
          </el-collapse>
        </el-card>
      </el-col>
      <el-col :xs="24" :sm="24" :md="12" :lg="8">
        <el-card class="update-log">
          <div slot="header" class="clearfix">
            <span>技术支持</span>
          </div>
          <div class="body">
            <span style="display: inline-block; height: 30px; line-height: 30px"
              >上海杉达学院 信息科学与技术学院</span
            >
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: "Index",
  data() {
    return {
      // 版本号
      version: "1.0.0",
    };
  },
  methods: {
    goTarget(href) {
      window.open(href, "_blank");
    },
  },
};
</script>

<style scoped lang="scss">
.home {
  blockquote {
    padding: 10px 20px;
    margin: 0 0 20px;
    font-size: 17.5px;
    border-left: 5px solid #eee;
  }
  hr {
    margin-top: 20px;
    margin-bottom: 20px;
    border: 0;
    border-top: 1px solid #eee;
  }
  .col-item {
    margin-bottom: 20px;
  }

  ul {
    padding: 0;
    margin: 0;
  }

  font-family: "open sans", "Helvetica Neue", Helvetica, Arial, sans-serif;
  font-size: 13px;
  color: #676a6c;
  overflow-x: hidden;

  ul {
    list-style-type: none;
  }

  h4 {
    margin-top: 0px;
  }

  h2 {
    margin-top: 10px;
    font-size: 26px;
    font-weight: 100;
  }

  p {
    margin-top: 10px;

    b {
      font-weight: 700;
    }
  }

  .update-log {
    ol {
      display: block;
      list-style-type: decimal;
      margin-block-start: 1em;
      margin-block-end: 1em;
      margin-inline-start: 0;
      margin-inline-end: 0;
      padding-inline-start: 40px;
    }
  }
}
</style>

