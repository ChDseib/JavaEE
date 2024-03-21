<template>
  <div class="app-container home">
    <el-row :gutter="20">
      <el-col :sm="24" :lg="12" style="padding-left: 20px">
        <h2>关于系统</h2>
        <p>
          本系统是专为组织、管理各种证书、奖项而设计的软件。它集成了认证机构、证书管理、竞赛管理、奖项管理等功能，旨在为教师和在校学生各项成果信息的管理提供全面、高效的解决方案。
        </p>
        <p>
          杉意指杉达，“小满”是中国传统廿四节气之一。小满时节，气温渐高，夏意渐浓，绿树成荫，花香四溢。愿各位老师在杉达收获满满的幸福与喜悦，感受生活的美好与温馨。万物初盈满、美好正此时。
        </p>
        <p>
          “小满”也意味着谷物开始饱满，但还未到最饱满的时候。愿同学们在杉达收获属于自己的成长增量，未来可期。
        </p>
        <p>
          logo为一朵手绘的小红花，寓意：
          <li>鼓励和赞美：孩提时代在幼儿园里的每一天，小红花都是我们最期待的奖励。</li>
          <li>美好愿望：成长路上来自家人和亲朋好友的爱与关怀是埋藏在我们心底的小红花。</li>
          <li>积极心态：人生如逆旅，小红花鼓励我们在面对困难时以积极的态度面对生活的挑战。</li>
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
            @click="goTarget('https://gitee.com/goldenpond/RuoYi-Vue-cas/wikis/Home')"
            >关于项目</el-button
          >
          <el-button
            type="primary"
            size="mini"
            icon="el-icon-user"
            plain
            @click="goTarget('https://gitee.com/goldenpond/RuoYi-Vue-cas/wikis/%E5%BC%80%E5%8F%91%E5%9B%A2%E9%98%9F')"
            >开发团队</el-button
          >
        </p>
      </el-col>

      <el-col :sm="24" :lg="12" style="padding-left: 50px">
        <el-row>
          <el-col :span="12">
            <h2>基础功能</h2>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="6">
            <h4>教师成果</h4>
            <ul>
              <li>师资培训</li>
              <li>社会兼职</li>
              <li>教材出版</li>
              <li>课程建设</li>
              <li>社会活动</li>
              <li>教学成果</li>
            </ul>
          </el-col>
          <el-col :span="6">
            <h4>学生成果</h4>
            <ul>
              <li>竞赛获奖</li>
              <li>资格证书</li>
              <li>评奖评优</li>
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
                <li>第一个正式版本发布</li>
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

